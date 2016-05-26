package Model;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class CurveStripping {

	public CurveStripping() {

	}

	double Slope;
	/**
	 * The Intercept corresponding to selected terminal points. May not be
	 * terminal intercept.
	 */
	double Intercept;
	PkParamEstimator caParamCalInst;

	static CurveStripping csInst = null;

	public static CurveStripping createCSInstance() {
		if (csInst == null) {
			csInst = new CurveStripping();
		}
		return csInst;
	}

	int noOfPtsUsed;

	public double[] parameterCalculationUsingCurveStripping(double[] X,
			double[] Y, int no_param) {

		PkPdInfo pkpdInst = PkPdInfo.createPKPDInstance();
		ProcessingInputsInfo procInputInst = pkpdInst.copyProcessingInput();
		
		double[] result = new double[no_param];
		int no_data = X.length;
		int no_param1 = no_param;
		double Rsq = 0;
		double[] Y1 = new double[no_data];
		caParamCalInst = PkParamEstimator.createPkParamEstimateInstance();
		ApplicationInfo appInfo = caParamCalInst.appInfoinst;
		int modelNumber = procInputInst.getModelInputTab1Obj()
				.getModelNumberForCA();

		int no_obs = X.length;
		double[] x5 = new double[no_obs];
		double[] y5 = new double[no_obs];
		double param[] = new double[2];
		int j1 = 0;

		if (no_param1 == 2) {
			int count = 0;
			j1 = 1;
			while (j1 <= no_obs && count <3) {
				if (Y[no_obs - j1] != 0) {

					if (count == 0 || (count > 0)
							&& y5[count - 1] != Y[no_obs - j1]) {
						x5[count] = X[no_obs - j1];
						y5[count] = Y[no_obs - j1];
						count++;
					}

				}
				j1++;
			}

			double[] adjRsq = new double[no_obs - 1];
			int noOfPoints = 2;
			for (int i = 0; i < adjRsq.length; i++) {
				noOfPoints = 2 + i;
				Rsq = terminalRsq(x5, y5, noOfPoints);
				adjRsq[i] = 1.0 - ((1.0 - Rsq) * (noOfPoints - 1))
						/ (noOfPoints - 2);
			}

			double maxAdjRsq = maxVal(adjRsq);
			noOfPoints = 2 + findVal(adjRsq, maxAdjRsq);

			noOfPtsUsed = noOfPoints;

			param = calculate_param(x5, y5, noOfPoints);

			result[0] = Math.exp(param[0]);
			result[1] = (-1) * param[1];
		}

		if (no_param1 == 4) {
			int count = 0;
			j1 = 1;
			while (count < 2) {
				if (Y[no_obs - j1] != 0) {
					if (count == 0 || (count > 0)
							&& y5[count - 1] != Y[no_obs - j1]) {
						x5[count] = X[no_obs - j1];
						y5[count] = Y[no_obs - j1];
						count++;
					}

				}
				j1++;
			}
			param = calculate_param(x5, y5, 2);
			Rsq = terminalRsq(x5, y5, 2);

			result[0] = Math.exp(param[0]);
			result[1] = (-1) * param[1];

			int j2;
			j2 = j1;

			count = 0;
			j1 = 0;
			while (count < 2) {

				if (modelNumber == 3 || modelNumber == 4 || modelNumber == 5
						|| modelNumber == 6 || modelNumber == 11
						|| modelNumber == 12 || modelNumber == 13
						|| modelNumber == 14) {
					Y1[j1] = (Math.exp(param[0]) * Math.exp(X[j1] * param[1]
							- Y[j1]));
					if (Y1[j1] != 0) {
						x5[count] = X[j1];
						y5[count] = Y1[j1];
						count++;
					}
				} else {
					Y1[j1] = (Y[j1] - Math.exp(param[0])
							* Math.exp(X[j1] * param[1]));
					x5[count] = X[j1];
					y5[count] = Y1[j1];
					count++;

				}
				j1++;

			}

			param = calculate_param(x5, y5, 2);
			Rsq = terminalRsq(x5, y5, 2);

			result[2] = Math.exp(param[0]);
			result[3] = (-1) * param[1];
		}

		if (no_param1 == 5 || no_param1 == 6) {
			int count = 0;
			j1 = 1;
			while (count < 2) {
				if (Y[no_obs - j1] != 0) {
					if (count == 0 || (count > 0)
							&& y5[count - 1] != Y[no_obs - j1]) {
						x5[count] = X[no_obs - j1];
						y5[count] = Y[no_obs - j1];
						count++;
					}

				}
				j1++;
			}

			result[0] = Math.exp(param[0]);
			result[1] = (-1) * param[1];
			int j2;
			j2 = j1;

			count = 0;
			j1 = 0;
			while (count < 2) {

				if (modelNumber == 3 || modelNumber == 4 || modelNumber == 5
						|| modelNumber == 6 || modelNumber == 11
						|| modelNumber == 12 || modelNumber == 13
						|| modelNumber == 14) {
					Y1[j1] = (Math.exp(param[0]) * Math.exp(X[j1] * param[1]
							- Y[j1]));
					if (Y1[j1] != 0) {
						x5[count] = X[j1];
						y5[count] = Y1[j1];
						count++;
					}
				} else {
					Y1[j1] = (Y[j1] - Math.exp(param[0])
							* Math.exp(X[j1] * param[1]));
					x5[count] = X[j1];
					y5[count] = Y1[j1];
					count++;

				}
				j1++;

			}

			param = calculate_param(x5, y5, 2);

			Rsq = terminalRsq(x5, y5, 2);

			count = 0;
			j1 = 0;
			while (count < 2) {

				Y1[j1] = (-Y1[j1] + Math.exp(param[0])
						* Math.exp(X[j1] * param[1]));
				x5[count] = X[j1];
				y5[count] = Y1[j1];
				count++;

			}

			param = calculate_param(x5, y5, j1);
			Rsq = terminalRsq(x5, y5, j1);

			if (no_param1 == 6) {
				result[4] = (-1) * Math.exp(param[0]);
				result[5] = (-1) * param[1];
			}

		}

		return result;

	}

	public double maxVal(double[] mat) {
		double max = mat[0];
		for (int i = 1; i < mat.length; i++) {
			if (mat[i] > max) {
				max = mat[i];
			}
		}
		return max;
	}

	public int findVal(double[] mat, double val) {

		for (int i = 0; i < mat.length; i++) {
			if (mat[i] == val)
				return i;
		}
		return -1;
	}

	public void simpleLinRegression(double[] x, double[] y) {
		int n = x.length;
		double sumX = 0;
		double sumY = 0;
		double sumXY = 0;
		double sumX2 = 0;

		for (int i = 0; i < n; i++) {
			sumX = sumX + x[i];
			sumY = sumY + y[i];
			sumXY = sumXY + x[i] * y[i];
			sumX2 = sumX2 + x[i] * x[i];
		}

		Slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
		//Intercept = (sumY - Slope * sumX) / n;
		
		Intercept = (sumX2 * sumY - sumX * sumXY)
		/ (n * sumX2 - sumX * sumX);
		
		System.out.println();

	}

	public double[] calculate_param(double[] x, double[] y, int noPts) {
		int noPositivePoint = 0;

		for (int i = 0; i < y.length; i++) {
			if (y[i] != 0)
				noPositivePoint++;

		}

		double[] logy = new double[noPositivePoint];
		double[] termX = new double[noPositivePoint];
		double[] param = new double[2];
		int count = 0;
		for (int i = 0; i < noPts; i++) {
			if (y[i] != 0) {
				termX[count] = x[i];
				logy[count] = Math.log(y[i]);
				count++;
			}
		}
		simpleLinRegression(termX, logy);
		param[0] = Intercept;
		param[1] = Slope;
		return param;
	}

	public double terminalRsq(double[] x, double[] y, int noPts) {

		double[] termX = new double[noPts];
		double[] termY = new double[noPts];
		double Rsq = 0;
		double power = 1;

		for (int i = 0; i < noPts; i++) {
			termX[i] = x[i];
			termY[i] = y[i];

		}

		double[] logy = new double[noPts];
		for (int i = 0; i < noPts; i++)
			logy[i] = Math.log(termY[i]);
		simpleLinRegression(termX, logy);

		double[] Ycalc = new double[noPts];
		for (int i = 0; i < noPts; i++) {
			Ycalc[i] = termX[i] * Slope + Intercept;
		}

		double sumY = 0;
		double meanY = 0;
		double SST = 0;
		double SSR = 0;
		double SSE = 0;
		double[] error = new double[noPts];

		for (int i = 0; i < noPts; i++) {
			sumY = sumY + logy[i];
			error[i] = logy[i] - Ycalc[i];
			SSE = SSE + (Math.pow(1 / y[i], power)) * Math.pow(error[i], 2);
		}
		meanY = sumY / noPts;

		for (int i = 0; i < noPts; i++) {
			SST = SST + (Math.pow(1 / y[i], power))
					* (Math.pow(logy[i] - meanY, 2));
			SSR = SSR + Math.pow(meanY - Ycalc[i], 2);
		}

		Rsq = 1.0 - (SSE / SST);
		return Rsq;

	}

}
