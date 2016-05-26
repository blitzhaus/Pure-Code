package Model;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.tree.TreePath;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import Model.PkParamEstimator;
import Model.PkPdInfo;

import view.ApplicationInfo;
import view.DisplayContents;

public class CurveStripper {

	public CurveStripper() {

	}

	double[] predictedValue;
	double[] timeForPredVal;
	double lambdaZ;
	double[] lambdaZValue;
	int[] NoOfPtsUsedForlambdaZ;
	int NoOfPointsForLambdaZ;
	double[] SlopeValue;
	double[] InterceptValue;
	double Slope;
	double Intercept;
	double[] weightValue;
	double[] Residual;
	double[] RsqValue;
	double[] adjRsqValue;
	double Rsq;
	double adjRsq;
	double[] lowerTimePoint;
	double[] upperTimePoint;
	int NoOfNonZeroPoints;
	int lastNonZeroIndex;
	double[] timeForRegression;
	double[] concForRegression;
	int LambdaZCalculationMethod;
	double CorrTermXY;
	int numberOfPredictedPoints;

	double CmaxValue;
	double TmaxValue;
	int lambdaZPoint = 0;
	int weightingIndex;
	int routeOfAdmin;
	ApplicationInfo appInfo;
	double[] param;
	
	public double[] lambdaZCalculationForCA(double[] time, double[] conc,
			double startTime, double endTime, int methodNo) throws Exception {
		try {

			
			weightingIndex = 0;
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			LambdaZCalculationMethod = methodNo;

			predictedValue = new double[time.length];
			timeForPredVal = new double[time.length];
			weightValue = new double[time.length];
			countNonZeroConc(time, conc);
			param = new double[2];

			int count;

			if (NoOfNonZeroPoints >= 2) {
				initializaRequiredVariables(conc);
			}

			if (LambdaZCalculationMethod != 2) {

				calculateLambdaZUsingBestFit(time, conc, appInfo);
			}
			if (LambdaZCalculationMethod == 2) {

				calculateLambdaZUsingRange(time, conc, startTime, endTime);

			}
			
			param[0] = Math.exp(Intercept);
		//	param[0] = Intercept;
			param[1] = lambdaZ; 

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return param;
	}

	private void calculateLambdaZUsingRange(double[] time, double[] conc,
			double startTime, double endTime) throws RowsExceededException,
			WriteException, BiffException, IOException {

		double lambdaZUsingInterval = 0;

		int noObs = time.length;
		int noPoints = 0;
		if (time[noObs - 1] < endTime)
			lambdaZUsingInterval = 0;
		for (int i = 0; i < noObs; i++) {
			if (time[i] >= startTime && time[i] <= endTime && conc[i] != 0)
				noPoints++;
		}
		if (noPoints < 2)
			lambdaZUsingInterval = 0;
		else {
			double[] newX = new double[noPoints];
			double[] newY = new double[noPoints];
			double[] newLogY = new double[noPoints];
			int count2 = 0;
			for (int i = 0; i < noObs; i++) {
				if (time[i] >= startTime && time[i] <= endTime && conc[i] != 0) {
					newX[count2] = time[i];
					newY[count2] = conc[i];
					newLogY[count2] = Math.log(conc[i]);
					//newLogY[count2] = conc[i];
					count2++;
				}

			}
			simpleLinRegression(newX, newLogY);
			lambdaZUsingInterval = (-1) * Slope;
			CorrTermXY = correlationCalculation(newX, newLogY);

			double[] Ycalc = new double[noPoints];

			for (int i = 0; i < noPoints; i++) {
				Ycalc[i] = newX[i] * Slope + Intercept;

			}
			double sumY = 0;
			double meanY = 0;
			double SST = 0;
			double SSR = 0;
			double SSE = 0;
			double[] error = new double[noPoints];

			for (int i = 0; i < noPoints; i++) {
				sumY = sumY + newLogY[i];
				error[i] = newLogY[i] - Ycalc[i];
				SSE = SSE + Math.pow(error[i], 2);
			}
			meanY = sumY / noPoints;
			for (int i = 0; i < noPoints; i++) {
				SST = SST + Math.pow(newLogY[i] - meanY, 2);
				SSR = SSR + Math.pow(meanY - Ycalc[i], 2);
			}

			Rsq = 1.0 - (SSE / SST);

			adjRsq = 1.0 - ((1.0 - Rsq) * (noPoints - 1)) / (noPoints - 2);
		}
		lambdaZ = lambdaZUsingInterval;
		numberOfPredictedPoints = 0;
		int ii = time.length - 1;
		while (ii >= 0) {
			if (time[ii] >= startTime && time[ii] <= endTime) {
				if (conc[ii] == 0) {
					timeForPredVal[ii] = time[ii];
					predictedValue[ii] = 0;
					weightValue[ii] = 0;
					ii--;
				} else {
					timeForPredVal[ii] = time[ii];
					predictedValue[ii] = Math.exp(time[ii] * Slope + Intercept);
					weightValue[ii] = 1;
					weightValue[ii] = weightValue[ii]
							/ Math.pow(Math.exp(conc[ii]),
									((-1) * weightingIndex));
					numberOfPredictedPoints++;
					ii--;
				}

			} else

			{
				timeForPredVal[ii] = time[ii];
				predictedValue[ii] = 0;
				weightValue[ii] = 0;
				ii--;
			}
		}

		Intercept = Intercept;
		lambdaZ = lambdaZ;

	}

	private void calculateLambdaZUsingBestFit(double[] time, double[] conc,
			ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {
		int count;

		if (NoOfNonZeroPoints >= 2) {
			calculateLambdaZForAllPts(time, conc);

			double Cmax = 0;
			double Tmax = 0;
			int cMaxIndex = 0;
			int count1 = lowerTimePoint.length - 1;
			double maxAdjRsq = adjRsqValue[adjRsqValue.length - 1];
			while (NoOfPtsUsedForlambdaZ[count1] <= 1) {

				count1--;
				maxAdjRsq = adjRsqValue[count1];
			}

			int maxAdjRsqIndex;
			double timeRangeForTTT = 0;

			if (LambdaZCalculationMethod == 1) {
				if (routeOfAdmin == 0)

				{
					Cmax = maxVal(conc);
					cMaxIndex = findVal(conc, Cmax);
					Tmax = time[cMaxIndex];

					while (count1 >= 0 && Tmax < lowerTimePoint[count1]) {
						if (adjRsqValue[count1] >= maxAdjRsq)
							maxAdjRsq = adjRsqValue[count1];
						count1--;
					}
					//System.out.println(maxAdjRsq);

					maxAdjRsqIndex = findVal(adjRsqValue, maxAdjRsq);
					lambdaZPoint = NoOfPtsUsedForlambdaZ[maxAdjRsqIndex];
					lambdaZ = lambdaZValue[maxAdjRsqIndex];
					Intercept = InterceptValue[maxAdjRsqIndex];
				}

			}

		} else
			lambdaZ = 0;
	}

	private void calculateLambdaZForAllPts(double[] time, double[] conc) {
		int count;
		CmaxValue = maxVal(conc);
		TmaxValue = time[findVal(conc, CmaxValue)];

		for (int j = lastNonZeroIndex - 2; j >= 0; j--) {
			lowerTimePoint[j] = time[j];
			upperTimePoint[j] = time[lastNonZeroIndex];
			for (int i = j; i <= lastNonZeroIndex; i++) {
				if (conc[i] != 0)
					NoOfPtsUsedForlambdaZ[j] = NoOfPtsUsedForlambdaZ[j] + 1;

			}
			timeForRegression = new double[NoOfPtsUsedForlambdaZ[j]];
			concForRegression = new double[NoOfPtsUsedForlambdaZ[j]];
			//System.out.println("No of pts" + NoOfPtsUsedForlambdaZ[j]);
			count = 0;
			for (int i = j; i <= lastNonZeroIndex; i++) {
				if (conc[i] != 0) {
					concForRegression[count] = conc[i];
					timeForRegression[count] = time[i];

					count++;
				}

			}
			calculateLambdaZ(timeForRegression, concForRegression);
			//System.out.println("slope" + Slope);
			SlopeValue[j] = Slope;
			InterceptValue[j] = Intercept;
			lambdaZValue[j] = lambdaZ;
			terminalRsq(timeForRegression, concForRegression, SlopeValue[j],
					InterceptValue[j]);
			RsqValue[j] = Rsq;
			adjRsqValue[j] = adjRsq;

		}
	}

	private void initializaRequiredVariables(double[] conc) {
		lastNonZeroIndex = lastNonZero(conc);
		lowerTimePoint = new double[lastNonZeroIndex ];
		upperTimePoint = new double[lastNonZeroIndex ];
		RsqValue = new double[lastNonZeroIndex ];
		adjRsqValue = new double[lastNonZeroIndex ];
		NoOfPtsUsedForlambdaZ = new int[lastNonZeroIndex];
		SlopeValue = new double[lastNonZeroIndex ];
		InterceptValue = new double[lastNonZeroIndex ];
		lambdaZValue = new double[lastNonZeroIndex];
	}

	private void countNonZeroConc(double[] time, double[] conc) {
		NoOfNonZeroPoints = 0;
		for (int i = 0; i < time.length; i++) {
			if (conc[i] != 0) {
				NoOfNonZeroPoints = NoOfNonZeroPoints + 1;
				System.out.println(" nonzero point" + NoOfNonZeroPoints);
			}
		}
	}

	double round(double value, int decimalPlace) {
		double power_of_ten = 1;
		while (decimalPlace-- > 0) {
			power_of_ten *= 10.0;
		}
		return Math.round(value * power_of_ten) / power_of_ten;
	}

	void simpleLinRegression(double[] x, double[] y) {
		int n = x.length;
		double sumX = 0;
		double sumY = 0;
		double sumXY = 0;
		double sumX2 = 0;
		double sumW = 0;
		double sumWXY = 0;
		double sumWX = 0;
		double sumWY = 0;
		double sumWX2 = 0;
		double sumWY2 = 0;
		double Sxx = 0;
		double Syy = 0;
		double Sxy = 0;

		for (int i = 0; i < n; i++) {
			sumX = sumX + x[i];
			sumY = sumY + y[i];
			sumXY = sumXY + x[i] * y[i];
			sumX2 = sumX2 + x[i] * x[i];
			sumW = sumW + 1 / Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWX = sumWX + x[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWXY = sumWXY + x[i] * y[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWY = sumWY + y[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWX2 = sumWX2 + x[i] * x[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
			sumWY2 = sumWX2 + y[i] * y[i] * 1
					/ Math.pow(Math.exp(y[i]), ((-1) * weightingIndex));
		}
		Slope = (sumW * sumWXY - sumWX * sumWY)
				/ (sumW * sumWX2 - sumWX * sumWX);
		Intercept = (sumWX2 * sumWY - sumWX * sumWXY)
				/ (sumW * sumWX2 - sumWX * sumWX);

		// Intercept = (sumY - Slope*sumX)/n;
		Sxx = (sumWX2 - (sumWX * sumWX / sumW));
		Syy = (sumWY2 - (sumWY * sumWY / sumW));
		Sxy = (sumWXY - (sumWX * sumWY / sumW));
	}

	int findVal(double[] mat, double val) {

		for (int i = 0; i < mat.length; i++) {
			if (mat[i] == val)
				return i;
		}
		return -1;
	}

	int firstNonZero(double[] mat) {
		int index = 0;
		for (int i = 0; i < mat.length; i++) {
			if (mat[i] != 0.0) {
				index = i;
				break;
			}
		}
		return index;
	}

	int lastNonZero(double[] mat) {

		int index = 0;
		for (int i = mat.length - 1; i >= 0; i--) {
			if (mat[i] != 0.0) {
				index = i;
				break;
			}
		}
		return index;
	}

	public double minVal(double[] mat) {
		double min = mat[0];
		for (int i = 1; i < mat.length; i++) {
			if (mat[i] < min) {
				min = mat[i];
			}
		}
		return min;
	}

	double maxVal(double[] mat) {
		double max = mat[0];
		for (int i = 1; i < mat.length; i++) {
			if (mat[i] > max) {
				max = mat[i];
			}
		}
		return max;
	}

	void calculateLambdaZ(double[] X, double[] Y) {

		double[] logy = new double[Y.length];

		for (int i = 0; i < Y.length; i++)
			logy[i] = Math.log(Y[i]);
		simpleLinRegression(X, logy);
		lambdaZ = -1.0 * Slope;
	}

	void terminalRsq(double[] X, double[] Y, double Slope1, double Intercept1) {
		double[] Ycalc = new double[Y.length];

		for (int i = 0; i < Y.length; i++) {
			Ycalc[i] = (X[i] * Slope1 + Intercept1);

		}
		double sumY = 0;
		double meanY = 0;
		double SST = 0;
		double SSR = 0;
		double SSE = 0;
		double[] error = new double[Y.length];
		for (int i = 0; i < Y.length; i++) {
			sumY = sumY + Math.log(Y[i]);
			error[i] = Math.log(Y[i]) - Ycalc[i];
			// SSE = SSE + Math.pow(error[i],2);
			SSE = SSE + Math.pow(error[i], 2)
					* (1 / Math.pow(Math.exp(Y[i]), ((-1) * weightingIndex)));
		}
		meanY = sumY / Y.length;
		for (int i = 0; i < Y.length; i++) {
			SST = SST + Math.pow(Math.log(Y[i]) - meanY, 2)
					* (1 / Math.pow(Math.exp(Y[i]), ((-1) * weightingIndex)));
			SSR = SSR + Math.pow(meanY - Ycalc[i], 2)
					* (1 / Math.pow(Math.exp(Y[i]), ((-1) * weightingIndex)));
		}
		Rsq = 1.0 - (SSE / SST);
		adjRsq = 1.0 - ((1.0 - Rsq) * (Y.length - 1)) / (Y.length - 2);
	}

	double correlationCalculation(double x[], double y[]) {
		// int row = Ax.length;
		PkParamEstimator caParamCalInst = PkParamEstimator
				.createPkParamEstimateInstance();
		PkPdInfo pkpdInstance = PkPdInfo.createPKPDInstance();
		double Ra = 0;
		// System.out.println("########### In correlation coefficient calculation");
		int row = x.length;

		for (int i = 0; i < y.length; i++) {
			if (y[i] > 0)
				y[i] = Math.log(y[i]);
			else
				y[i] = 0;
		}

		double sumX = 0;
		double sumY = 0;
		double sumXY = 0;
		double sumX2 = 0;
		double sumW = 0;
		double sumWXY = 0;
		double sumWX = 0;
		double sumWY = 0;
		double sumWX2 = 0;
		double sumWY2 = 0;
		double Sxx = 0;
		double Syy = 0;
		double Sxy = 0;

		for (int i = 0; i < x.length; i++) {
			sumX = sumX + x[i];
			sumY = sumY + y[i];
			// weightSum=weightSum+1/Math.pow(y[i],(weightingIndex-2));
			sumXY = sumXY + x[i] * y[i];
			sumX2 = sumX2 + x[i] * x[i];
			sumW = sumW
					+ (1 / Math.pow(Math.exp(y[i]), ((-1) * weightingIndex)));
			sumWX = sumWX + x[i]
					* (1 / Math.pow(Math.exp(y[i]), ((-1) * weightingIndex)));
			sumWXY = sumWXY + x[i] * y[i]
					* (1 / Math.pow(Math.exp(y[i]), ((-1) * weightingIndex)));
			sumWY = sumWY + y[i]
					* (1 / Math.pow(Math.exp(y[i]), ((-1) * weightingIndex)));
			sumWX2 = sumWX2 + x[i] * x[i]
					* (1 / Math.pow(Math.exp(y[i]), ((-1) * weightingIndex)));
			sumWY2 = sumWY2 + y[i] * y[i]
					* (1 / Math.pow(Math.exp(y[i]), ((-1) * weightingIndex)));

		}

		double corr = (sumW * sumWXY - sumWX * sumWY)
				/ (Math.sqrt(sumW * sumWX2 - sumWX * sumWX) * Math.sqrt(sumW
						* sumWY2 - sumWY * sumWY));

		return corr;

	}

}