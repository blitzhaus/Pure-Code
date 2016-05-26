package view;

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

import view.ApplicationInfo;
import view.DisplayContents;

public class NcaCoreLambdaZCalc {

	public static NcaCoreLambdaZCalc CORE_LAMBDAZ_INST = null;

	public static NcaCoreLambdaZCalc createCoreLamInst() {
		if (CORE_LAMBDAZ_INST == null) {
			CORE_LAMBDAZ_INST = new NcaCoreLambdaZCalc(
					"Just object creation");
		}
		return CORE_LAMBDAZ_INST;
	}

	public NcaCoreLambdaZCalc(String dummy) {

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

	LambdaZCalculationBeforeNCA lambdaZInst;
	ArrayList<String> intermediateOutPut;

	double CmaxValue;
	double TmaxValue;
	int lambdaZPoint = 0;
	int weightingIndex;
	int routeOfAdmin;
	ApplicationInfo appInfo;

	public void lambdaZCalculation(double[] time, double[] conc,
			double startTime, double endTime, double infusionTime, int methodNo, int currentProfileNumber)
			throws Exception {

		
		
		methodNo = methodNo + 1;
		lambdaZInst = DisplayContents.createInstanceOflzBeforeNCA();
		initialiZVar();
		intermediateOutPut = new ArrayList<String>();
		appInfo = DisplayContents.createAppInfoInstance();
		routeOfAdmin = appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getRootOfAdmistration();
		
		if(routeOfAdmin == 3)
		{
			 try{
				 routeOfAdmin = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getProfileInfoObj().getRouteOfAdminAt(currentProfileNumber);
			 } catch (ArrayIndexOutOfBoundsException e){
				 // assumption: Iv Infusion route of administration
				 routeOfAdmin = 2;
			 }
			
		}

		weightingIndex = NcaOptionsGui.createNcaOptionsInstance().weightingOptions
				.getSelectedIndex();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		LambdaZCalculationMethod = methodNo;

		predictedValue = new double[time.length];
		timeForPredVal = new double[time.length];
		weightValue = new double[time.length];
		countNonZeroConc(time, conc);

		int count;

		if (NoOfNonZeroPoints >= 3) {
			initializaRequiredVariables(conc);
		}

		if (LambdaZCalculationMethod != 4) {

			calculateLambdaZUsingBestFitAndTtt(time, conc, infusionTime,
					appInfo);
		}
		if (LambdaZCalculationMethod == 4) {

			calculateLambdaZUsingRange(time, conc, startTime, endTime);

		}

		lambdaZInst.timeForPredVal = timeForPredVal;
		lambdaZInst.predictedConc = predictedValue;

	}

	private void calculateLambdaZUsingRange(double[] time, double[] conc,
			double startTime, double endTime) throws RowsExceededException,
			WriteException, BiffException, IOException {

		double lambdaZUsingInterval = 0;

		
		int noObs = time.length;
		int noPoints = 0;
		if(noObs == 0){
			
		} else{
			if (time[noObs - 1] < endTime)
				lambdaZUsingInterval = 0;
			for (int i = 0; i < noObs; i++) {
				if (time[i] >= startTime && time[i] <= endTime && conc[i] != 0)
					noPoints++;
			}
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
			
			lambdaZ = lambdaZUsingInterval;
			numberOfPredictedPoints = 0;
			System.out.println();
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
			
			lambdaZInst.noPtsLambdaZ = (int) numberOfPredictedPoints;
			lambdaZInst.lambdaZUpper = time[lastNonZero(predictedValue)];
			lambdaZInst.lambdaZLower = time[firstNonZero(predictedValue)];
			lambdaZInst.lambdaZ = lambdaZ;
			lambdaZInst.rsq = Rsq;
			lambdaZInst.adjRsq = adjRsq;
			lambdaZInst.correlation = CorrTermXY;
			
		}

		

		
	}
	
	void initialiZVar()
	{
		lambdaZInst.noPtsLambdaZ = 0;
		lambdaZInst.lambdaZUpper = 0;
		lambdaZInst.lambdaZLower = 0;
		lambdaZInst.lambdaZ = 0;
		lambdaZInst.rsq = 0;
		lambdaZ = 0;
		Rsq = 0;
		adjRsq = 0;
		lambdaZInst.adjRsq = 0;
		CorrTermXY = 0;
		lambdaZInst.correlation = 0;
		
	}

	private void calculateLambdaZUsingBestFitAndTtt(double[] time,
			double[] conc, double infusionTime, ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int count;

		if (NoOfNonZeroPoints >= 3) {
			calculateLambdaZForAllPts(time, conc);
			intermediateOutputHeaderStore();
			intermediateOutputStoring(infusionTime);

			double Cmax = 0;
			double Tmax = 0;
			int cMaxIndex = 0;
			int count1 = lowerTimePoint.length - 1;
			double maxAdjRsq = adjRsqValue[adjRsqValue.length - 1];
			while (NoOfPtsUsedForlambdaZ[count1] <= 2) {

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
					System.out.println(maxAdjRsq);

					maxAdjRsqIndex = findVal(adjRsqValue, maxAdjRsq);
					lambdaZPoint = NoOfPtsUsedForlambdaZ[maxAdjRsqIndex];
					capturingLambdaZRelatedInfoForM1RoA0(maxAdjRsqIndex);

					if (lambdaZPoint == 0) {
						setLambdaZRelatedParamToZeroForMethod1();

					}

				} else if (routeOfAdmin == 1)

				{
					Cmax = maxVal(conc);
					cMaxIndex = findVal(conc, Cmax);
					Tmax = time[cMaxIndex];

					while (count1 >= 0 && Tmax <= lowerTimePoint[count1]) {
						if (adjRsqValue[count1] >= maxAdjRsq)
							maxAdjRsq = adjRsqValue[count1];
						count1--;
					}

					maxAdjRsqIndex = findVal(adjRsqValue, maxAdjRsq);
					lambdaZPoint = NoOfPtsUsedForlambdaZ[maxAdjRsqIndex];
					capturingLambdaZRelatedInfoForM1RoA1(maxAdjRsqIndex);

					if (lambdaZPoint == 0) {
						setLambdaZRelatedParamToZeroForMethod1();

					}

				} else {
					while (count1 >= 0
							&& infusionTime <= lowerTimePoint[count1]) {
						if (adjRsqValue[count1] >= maxAdjRsq)
							maxAdjRsq = adjRsqValue[count1];
						count1--;
					}

					maxAdjRsqIndex = findVal(adjRsqValue, maxAdjRsq);
					lambdaZPoint = NoOfPtsUsedForlambdaZ[maxAdjRsqIndex];
					capturingLambdaZRelatedInfoForM1RoA1(maxAdjRsqIndex);

					if (lambdaZPoint == 0) {
						setLambdaZRelatedParamToZeroForMethod1();

					}

				}

				calculatePredConcForM1(time, conc, maxAdjRsqIndex);
				calculateCorrelationOfCurrentProfile(time, conc);

			}

			if (LambdaZCalculationMethod == 2) {
				Cmax = maxVal(conc);
				cMaxIndex = findVal(conc, Cmax);
				Tmax = time[cMaxIndex];
				timeRangeForTTT = 2 * Tmax;
				while (timeRangeForTTT <= lowerTimePoint[count1])
					count1--;
				if (count1 == lowerTimePoint.length - 1)
					System.out.println("@@@lambdaZ is zero");
				else {
					capturingLambdaZRelateInfoForM2(count1);
					lambdaZPoint = NoOfPtsUsedForlambdaZ[count1];
					calculatePredConcForM2(time, conc, count1);
					calculateCorrelationOfCurrentProfile(time, conc);
				}
				
			}

			if (LambdaZCalculationMethod == 3) {
				if (routeOfAdmin == 0 || routeOfAdmin == 1) {
					Cmax = maxVal(conc);
					cMaxIndex = findVal(conc, Cmax);
					Tmax = time[cMaxIndex];
					timeRangeForTTT = 2 * Tmax;
					while (timeRangeForTTT < lowerTimePoint[count1]
							&& (adjRsqValue[count1] >= adjRsqValue[count1 - 1] || (maxAdjRsq
									- adjRsqValue[count1] <= 0.0001))) {
						if (adjRsqValue[count1] >= maxAdjRsq)
							maxAdjRsq = adjRsqValue[count1];
						count1--;
					}

					maxAdjRsqIndex = findVal(adjRsqValue, maxAdjRsq);
					lambdaZPoint = NoOfPtsUsedForlambdaZ[maxAdjRsqIndex];
					capturingLambdaZRelatedInfoForM3(count1, maxAdjRsqIndex);

				} else {
					while (timeRangeForTTT < lowerTimePoint[count1]
							&& (adjRsqValue[count1] >= adjRsqValue[count1 - 1] || (maxAdjRsq
									- adjRsqValue[count1] <= 0.0001))) {
						if (adjRsqValue[count1] >= maxAdjRsq)
							maxAdjRsq = adjRsqValue[count1];
						count1--;
					}
					maxAdjRsqIndex = findVal(adjRsqValue, maxAdjRsq);
					capturingLambdaZRelatedInfoForM3(count1, maxAdjRsqIndex);

				}

				calculatePredConcForM3(time, conc, maxAdjRsqIndex);
				calculateCorrelationOfCurrentProfile(time, conc);
			}

		} else
		{
			lambdaZ = 0;
			Rsq = 0;
			adjRsq = 0;
			CorrTermXY = 0;
			
		}

		

	}

	private void calculateCorrelationOfCurrentProfile(double[] time,
			double[] conc) {

		double[] timeForCorr = new double[lambdaZPoint];
		double[] concForCorr = new double[lambdaZPoint];
		int count = 0;
		for (int i = 0; i < time.length; i++) {

			if (conc[i] != 0 && predictedValue[i] != 0) {
				timeForCorr[count] = time[i];
				concForCorr[count] = conc[i];
				count++;
			}

		}

		lambdaZInst.correlation = correlationCalculation(timeForCorr,
				concForCorr);
	}

	private void capturingLambdaZRelatedInfoForM3(int count1, int maxAdjRsqIndex) {
		Slope = SlopeValue[maxAdjRsqIndex];
		Intercept = InterceptValue[maxAdjRsqIndex];
		// lambdaZ=lambdaZValue[maxAdjRsqIndex];
		lambdaZInst.lambdaZ = lambdaZValue[maxAdjRsqIndex];
		lambdaZInst.rsq = RsqValue[maxAdjRsqIndex];
		lambdaZInst.adjRsq = adjRsqValue[maxAdjRsqIndex];
		lambdaZInst.noPtsLambdaZ = NoOfPtsUsedForlambdaZ[maxAdjRsqIndex];
		lambdaZInst.lambdaZUpper = upperTimePoint[maxAdjRsqIndex];
		lambdaZInst.lambdaZLower = lowerTimePoint[maxAdjRsqIndex];
	}

	private void capturingLambdaZRelateInfoForM2(int count1) {
		Slope = SlopeValue[count1 + 1];
		Intercept = InterceptValue[count1 + 1];
		// lambdaZ=lambdaZValue[count1+1];
		lambdaZInst.lambdaZ = lambdaZValue[count1 + 1];
		lambdaZInst.rsq = RsqValue[count1 + 1];
		lambdaZInst.adjRsq = adjRsqValue[count1 + 1];
		lambdaZInst.noPtsLambdaZ = NoOfPtsUsedForlambdaZ[count1 + 1];
		lambdaZInst.lambdaZUpper = upperTimePoint[count1 + 1];
		lambdaZInst.lambdaZLower = lowerTimePoint[count1 + 1];
	}

	private void calculatePredConcForM3(double[] time, double[] conc,
			int maxAdjRsqIndex) {
		numberOfPredictedPoints = 0;
		int ii = time.length - 1;
		while (ii >= 0) {
			if (numberOfPredictedPoints < NoOfPtsUsedForlambdaZ[maxAdjRsqIndex]) {
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

			} else {
				timeForPredVal[ii] = time[ii];
				predictedValue[ii] = 0;
				weightValue[ii] = 0;
				ii--;
			}
		}
	}

	private void calculatePredConcForM2(double[] time, double[] conc, int count1) {
		numberOfPredictedPoints = 0;
		int ii = time.length - 1;
		while (ii >= 0) {
			if (numberOfPredictedPoints < NoOfPtsUsedForlambdaZ[count1 + 1]) {
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

			} else {
				timeForPredVal[ii] = time[ii];
				predictedValue[ii] = 0;
				weightValue[ii] = 0;
				ii--;
			}
		}
	}

	private void calculatePredConcForM1(double[] time, double[] conc,
			int maxAdjRsqIndex) {
		numberOfPredictedPoints = 0;
		int ii = time.length - 1;
		while (ii >= 0) {

			if (numberOfPredictedPoints < NoOfPtsUsedForlambdaZ[maxAdjRsqIndex]
					&& lambdaZPoint != 0) {
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

			} else {
				timeForPredVal[ii] = time[ii];
				predictedValue[ii] = 0;
				weightValue[ii] = 0;
				ii--;
			}
		}
	}

	private void capturingLambdaZRelatedInfoForM1RoA1(int maxAdjRsqIndex) {
		Slope = SlopeValue[maxAdjRsqIndex];
		Intercept = InterceptValue[maxAdjRsqIndex];
		// lambdaZ=lambdaZValue[maxAdjRsqIndex];
		lambdaZInst.lambdaZ = lambdaZValue[maxAdjRsqIndex];
		lambdaZInst.rsq = RsqValue[maxAdjRsqIndex];
		lambdaZInst.adjRsq = adjRsqValue[maxAdjRsqIndex];
		lambdaZInst.noPtsLambdaZ = NoOfPtsUsedForlambdaZ[maxAdjRsqIndex];
		lambdaZInst.lambdaZUpper = upperTimePoint[maxAdjRsqIndex];
		lambdaZInst.lambdaZLower = lowerTimePoint[maxAdjRsqIndex];
	}

	private void capturingLambdaZRelatedInfoForM1RoA0(int maxAdjRsqIndex) {
		Slope = SlopeValue[maxAdjRsqIndex];
		Intercept = InterceptValue[maxAdjRsqIndex];
		lambdaZInst.lambdaZ = lambdaZValue[maxAdjRsqIndex];
		lambdaZInst.rsq = RsqValue[maxAdjRsqIndex];
		lambdaZInst.adjRsq = adjRsqValue[maxAdjRsqIndex];
		lambdaZInst.noPtsLambdaZ = NoOfPtsUsedForlambdaZ[maxAdjRsqIndex];
		lambdaZInst.lambdaZUpper = upperTimePoint[maxAdjRsqIndex];
		lambdaZInst.lambdaZLower = lowerTimePoint[maxAdjRsqIndex];
	}

	private void setLambdaZRelatedParamToZeroForMethod1() {
		lambdaZInst.lambdaZ = 0;
		lambdaZInst.rsq = 0;
		lambdaZInst.adjRsq = 0;
		lambdaZInst.noPtsLambdaZ = 0;
		lambdaZInst.lambdaZUpper = 0;
		lambdaZInst.lambdaZLower = 0;
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

			count = 0;
			for (int i = j; i <= lastNonZeroIndex; i++) {
				if (conc[i] != 0) {
					concForRegression[count] = conc[i];
					timeForRegression[count] = time[i];

					count++;
				}

			}
			calculateLambdaZ(timeForRegression, concForRegression);
			//System.out.println();
			SlopeValue[j] = Slope;
			InterceptValue[j] = Intercept;
			lambdaZValue[j] = lambdaZ;
			terminalRsq(timeForRegression, concForRegression, SlopeValue[j],
					InterceptValue[j]);
			RsqValue[j] = Rsq;
			adjRsqValue[j] = adjRsq;

		}
	}

	private void intermediateOutputStoring(double infusionTime) {

		lambdaZPoint = 0;
		for (int i = 0; i < SlopeValue.length; i++) {
			if (routeOfAdmin == 1)
				if (lowerTimePoint[i] >= TmaxValue) {
					lambdaZPoint++;

					intermediateOutPut.add(StringUtils.rightPad(Double
							.toString(round(lowerTimePoint[i], 4)), 10)
							+ "\t"
							+ StringUtils.rightPad(Double.toString(round(
									upperTimePoint[i], 4)), 10)
							+ "\t"
							+ StringUtils.rightPad(Double
									.toString(NoOfPtsUsedForlambdaZ[i]), 10)
							+ "\t"
							+ StringUtils.rightPad(Double.toString(round(
									lambdaZValue[i], 4)), 10)
							+ "\t"
							+ StringUtils.rightPad(Double.toString(round(
									RsqValue[i], 4)), 10)
							+ "\t"
							+ StringUtils.rightPad(Double.toString(round(
									adjRsqValue[i], 4)), 10) + "\r\n");

				}

			if (routeOfAdmin == 0)
				if (lowerTimePoint[i] > TmaxValue) {
					lambdaZPoint++;

					intermediateOutPut.add(StringUtils.rightPad(Double
							.toString(round(lowerTimePoint[i], 4)), 10)
							+ "\t"
							+ StringUtils.rightPad(Double.toString(round(
									upperTimePoint[i], 4)), 10)
							+ "\t"
							+ StringUtils.rightPad(Double.toString(round(
									NoOfPtsUsedForlambdaZ[i], 4)), 10)
							+ "\t"
							+ StringUtils.rightPad(Double.toString(round(
									lambdaZValue[i], 4)), 10)
							+ "\t"
							+ StringUtils.rightPad(Double.toString(round(
									RsqValue[i], 4)), 10)
							+ "\t"
							+ StringUtils.rightPad(Double.toString(round(
									adjRsqValue[i], 4)), 10) + "\r\n");

				}
			if (routeOfAdmin == 2)
				if (lowerTimePoint[i] >= infusionTime) {
					lambdaZPoint++;

					intermediateOutPut.add(StringUtils.rightPad(Double
							.toString(round(lowerTimePoint[i], 4)), 10)
							+ "\t"
							+ StringUtils.rightPad(Double.toString(round(
									upperTimePoint[i], 4)), 10)
							+ "\t"
							+ StringUtils.rightPad(Double
									.toString(NoOfPtsUsedForlambdaZ[i]), 10)
							+ "\t"
							+ StringUtils.rightPad(Double.toString(round(
									lambdaZValue[i], 4)), 10)
							+ "\t"
							+ StringUtils.rightPad(Double.toString(round(
									RsqValue[i], 4)), 10)
							+ "\t"
							+ StringUtils.rightPad(Double.toString(round(
									adjRsqValue[i], 4)), 10) + "\r\n");

				}
		}
	}

	private void intermediateOutputHeaderStore() {
		intermediateOutPut
				.add("Intermediate Output: Trying different time intervals to get best fit for LambdaZ"
						+ "\r\n");
		intermediateOutPut
				.add("----------------------------------------------------------------------------------------------------------------------"
						+ "\r\n");
		intermediateOutPut.add(StringUtils.rightPad("Lower_Time", 10) + "\t"
				+ StringUtils.center("Upper_Time", 10) + "\t"
				+ StringUtils.center("PtsUsed", 10) + "\t"
				+ StringUtils.center("LambdaZ", 10) + "\t"
				+ StringUtils.center("Rsq", 10) + "\t"
				+ StringUtils.center("AdjRsq", 10) + "\r\n");
		intermediateOutPut
				.add("----------------------------------------------------------------------------------------------------------------------"
						+ "\r\n");
	}

	private void initializaRequiredVariables(double[] conc) {
		lastNonZeroIndex = lastNonZero(conc);
		lowerTimePoint = new double[lastNonZeroIndex - 1];
		upperTimePoint = new double[lastNonZeroIndex - 1];
		RsqValue = new double[lastNonZeroIndex - 1];
		adjRsqValue = new double[lastNonZeroIndex - 1];
		NoOfPtsUsedForlambdaZ = new int[lastNonZeroIndex - 1];
		SlopeValue = new double[lastNonZeroIndex - 1];
		InterceptValue = new double[lastNonZeroIndex - 1];
		lambdaZValue = new double[lastNonZeroIndex - 1];
	}

	private void countNonZeroConc(double[] time, double[] conc) {
		NoOfNonZeroPoints = 0;
		for (int i = 0; i < time.length; i++) {
			if (conc[i] > 0) {
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

	void terminalRsq(double[] X, double[] Y, double Slope1, double Intercept1) {double[] Ycalc = new double[Y.length];

	for (int i = 0; i < Y.length; i++) {
		Ycalc[i] = (X[i] * Slope1 + Intercept1);

	}
	double sumY = 0;
	double meanY = 0;
	double SST = 0;
	double SSR = 0;
	double SSE = 0;
	double sumW = 0;
	double[] error = new double[Y.length];
	for (int i = 0; i < Y.length; i++) {
		sumY = sumY + Math.log(Y[i]);
		sumW = 1/Math.pow((Y[i]),(weightingIndex));
		error[i] = Math.log(Y[i]) - Ycalc[i];
		// SSE = SSE + Math.pow(error[i],2);
		SSE = SSE + Math.pow(error[i], 2)*(1/Math.pow((Math.exp(Ycalc[i])),(weightingIndex)));
	}
	meanY = sumY / Y.length;
	for (int i = 0; i < Y.length; i++) {
		SST = SST + Math.pow(Math.log(Y[i]) - meanY, 2)*(1/Math.pow((Math.exp(Ycalc[i])),(weightingIndex)));
		SSR = SSR + Math.pow(meanY - Ycalc[i], 2)*(1/Math.pow((Math.exp(Ycalc[i])),(weightingIndex)));
		
		/*SST = SST + Math.pow(Math.log(Y[i]) - meanY, 2);
		SSR = SSR + Math.pow(meanY - Ycalc[i], 2);*/
	}
	Rsq = 1.0 - (SSE / SST);
	adjRsq = 1.0 - ((1.0 - Rsq) * (Y.length - 1)) / (Y.length - 2);
	}

double correlationCalculation(double x[], double z[]) {
	
		
		double Ra = 0;
		int row = x.length;
		double[] y = new double[row];

		for (int i = 0; i < y.length; i++) {
			if (z[i] > 0)
				y[i] = Math.log(z[i]);
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
			sumW = sumW + (1/Math.pow(Math.exp(y[i]),weightingIndex));
			sumWX = sumWX + x[i] *(1/Math.pow(Math.exp(y[i]),weightingIndex));
			sumWXY = sumWXY + x[i] * y[i] *(1/Math.pow(Math.exp(y[i]),(weightingIndex)));
			sumWY = sumWY + y[i] *(1/Math.pow(Math.exp(y[i]),(weightingIndex)));
			sumWX2 = sumWX2 + x[i] * x[i] *(1/Math.pow(Math.exp(y[i]),(weightingIndex)));
			sumWY2 = sumWY2 + y[i] * y[i] *(1/Math.pow(Math.exp(y[i]),(weightingIndex)));

		}

		double corr = (sumW * sumWXY - sumWX * sumWY)
				/ (Math.sqrt(sumW * sumWX2 - sumWX * sumWX) * Math.sqrt(sumW
						* sumWY2 - sumWY * sumWY));
		
		return corr;

	
	}
}