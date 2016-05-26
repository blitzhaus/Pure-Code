package Model;

import java.util.ArrayList;

import org.jfree.chart.JFreeChart;

import view.ApplicationInfo;
import view.DisplayContents;
import view.NcaOptionsGui;
import view.ProcessingInputsInfo;

public class NonParametricSuperposition {

	int no_subject;
	int[] subject_obs;

	public WorkSheetOutputs getWsOutputts() {
		return wsOutputts;
	}

	public void setWsOutputts(WorkSheetOutputs wsOutputts) {
		this.wsOutputts = wsOutputts;
	}

	int methodNumberForNPS;
	double lambdaZ;
	double Slope;
	double Intercept;
	ApplicationInfo appInfo;
	ArrayList<String[]> spreedSheetOutput;
	ArrayList<String> textOutput;
	NCA ncaInst;
	WorkSheetOutputs wsOutputts;
	String massUnit;
	String volumeUnit;
	boolean ifTerminalPhaseDefined;
	double startTime;
	double endTime;
	int noOfPtsIn1stDose;

	public ApplicationInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(ApplicationInfo appInfo) {
		this.appInfo = appInfo;
	}

	public NonParametricSuperposition(ApplicationInfo appInfo) {
		setAppInfo(appInfo);
	}

	public NonParametricSuperposition() {

		no_subject = 1;
		subject_obs = new int[1];
		methodNumberForNPS = 0;
		lambdaZ = 0;
		Slope = 0;
		Intercept = 0;
		massUnit = "";
		volumeUnit = "";
		X = new double[1];
		Y = new double[1];
		doseInterval = new double[1];
		numberOfDose = 1;
		numberOfOutputPoints = 1;
		loadingDose = 0;
		maintenanceDose = 0;
		allStartTime = new double[1];
		allConcentration = new double[1];
		noOfObs = 1;
		numberOfSortVariable = 0;
		roundTo = 4;

		textOutput = new ArrayList<String>();
		dosingData = new double[1];
		spreedSheetOutput = new ArrayList<String[]>();
	}

	public static NonParametricSuperposition NPS_OBJECT = null;

	public static NonParametricSuperposition creaetNPSInst() {
		if (NPS_OBJECT == null) {
			NPS_OBJECT = new NonParametricSuperposition();
		}

		return NPS_OBJECT;
	}

	double[] X;
	double[] Y;
	double[] doseInterval;
	double[] dosingTime;
	int numberOfDose = 0;
	int numberOfOutputPoints;
	double loadingDose = 0;
	double maintenanceDose = 0;
	double[] allStartTime;
	double[] allConcentration;
	int noOfObs;
	int numberOfSortVariable;
	int roundTo;
	double[] dosingData;
	ProcessingInputsInfo procInputInst;

	public void Superposition_multidose(ApplicationInfo appInfo)
			throws Exception {

		setAppInfo(appInfo);
		ncaInst = NCA.creaetNCAInst();
		wsOutputts = new WorkSheetOutputs();
		procInputInst = copyProcessingInput();

		textOutputHeader(appInfo);

		wsOutputts.getPlotOutputs().setLogplots(new ArrayList<JFreeChart>());
		wsOutputts.getPlotOutputs().setLinearPlots(new ArrayList<JFreeChart>());

		procInputInst = copyProcessingInput();
		capturingProfilesInfo(appInfo);
		int sum = 0;
		for (int j = 0; j < no_subject; j++) {
			double[] value;
			String[][] totalSortVariables = new String[subject_obs[j]][numberOfSortVariable];
			
			
			if(allStartTime[sum] == 0)
			{
				X = new double[subject_obs[j]];
				Y = new double[subject_obs[j]];
				value = new double[subject_obs[j]];
				
				for (int i = 0; i < subject_obs[j]; i++) {
					X[i] = allStartTime[i + sum];
					Y[i] = allConcentration[i + sum];
					value[i] = allConcentration[i + sum];

				}
			}
			else
			{
				X = new double[subject_obs[j] + 1];
				Y = new double[subject_obs[j] + 1];
				value = new double[subject_obs[j] + 1];
				
				X[0] = 0;
				Y[0] = 0;
				value[0] = 0;
				for (int i = 0; i < subject_obs[j]; i++) {
					X[i + 1] = allStartTime[i + sum];
					Y[i + 1] = allConcentration[i + sum];
					value[i + 1] = allConcentration[i + sum];

				}
			}

			for (int i = 0; i < subject_obs[j]; i++) {
				for (int ii = 0; ii < numberOfSortVariable; ii++) {
					totalSortVariables[i][ii] = procInputInst
							.getProfileInfoObj().getDataSortVariables()[j][ii];
				}
			}

			calculateLambdaZ(X, Y, j);
			capturingModelInputs(j);

			capturingDosingInfo(j);
			int noOfDose;

			double[] computedTime = computingTimepoints(X, doseInterval);
			double[] computedConc = computingConc(computedTime, Y, X,
					doseInterval);

			double[] finalConc = new double[computedConc.length];

			
			if(procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 0)
			{
				for (int i = 0; i < computedConc.length; i++) {

					noOfDose = i / noOfPtsIn1stDose;

					for (int k = 0; k <= noOfDose; k++) {

						
						if (k == 0) {
							finalConc[i] = computedConc[i];
						} else {
							int inc = i - (k * noOfPtsIn1stDose);

							finalConc[i] = finalConc[i]
									+ (maintenanceDose / loadingDose)
									* computedConc[inc];
						}
					}

				}
			}else
			{
				
				for (int i = 0; i < computedConc.length; i++) {

					
					int j2=0;
					while( (j2< dosingTime.length) && (computedTime[i]>= dosingTime[j2]))
					{
						j2++;
					}
					
					noOfDose = j2-1;
					
					for (int k = 0; k <= noOfDose; k++) {

						if (procInputInst.getModleInputForNPSObj()
								.getDosingTypeForNPS() == 1) {
							loadingDose = dosingData[0];
							maintenanceDose = dosingData[k];
						}

						if (k == 0) {
							finalConc[i] = computedConc[i];
						} else {
					
							int inc = decideCurrentIncrement(computedTime,dosingTime[k], computedTime[i]);

							finalConc[i] = finalConc[i]
									+ (maintenanceDose / loadingDose)
									* computedConc[inc];
							
						}
					}

				}
			}
			

			double stepLength;

			if (procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 0) {
				stepLength = (doseInterval[0])
						/ (numberOfOutputPoints -1);
			} else {
				stepLength = (Double.parseDouble(procInputInst
						.getModleInputForNPSObj().getEndTime()) - Double
						.parseDouble(procInputInst.getModleInputForNPSObj()
								.getStartTime()))
						/ (numberOfOutputPoints -1);
			}

			double[] outputTimePoints = new double[numberOfOutputPoints];
			double[] outputConcPoints = new double[numberOfOutputPoints];
			double startTimePoint;

			
			if (procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 1) {
				outputTimePoints[0] = Double
				.parseDouble(procInputInst.getModleInputForNPSObj()
						.getStartTime());
			}else
			{
				outputTimePoints[0] = (numberOfDose -1)*doseInterval[0];
			}
			

			for (int i = 0; i < numberOfOutputPoints; i++) { 
				
				if (methodNumberForNPS == 0)
					outputConcPoints[i] = ncaInst.linearInterpolation(
							computedTime, finalConc, outputTimePoints[i]);
				else
					outputConcPoints[i] = ncaInst.logInterpolation(
							computedTime, finalConc, outputTimePoints[i]);

				storeTextAndSpreedSheetOutput(appInfo, sum, outputTimePoints,
						outputConcPoints, i, j);
				
				if(i == numberOfOutputPoints -2)
				{
					if(procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 0)
					{
						outputTimePoints[i+1] = numberOfDose *doseInterval[0];
					}else
					{
						outputTimePoints[i+1] = Double.parseDouble(procInputInst
								.getModleInputForNPSObj().getEndTime());	
					}
					
				}else
				{
					if(i < numberOfOutputPoints-1)
					outputTimePoints[i+1] = outputTimePoints[i] + stepLength;	
				}
				
			}

			generatPlots(appInfo, sum, outputTimePoints, outputConcPoints, j);

			sum = sum + subject_obs[j];
		}

		wsOutputts.getSpreadSheetOutputs().setVerticalParameters(
				retrievingResultsFromAL(spreedSheetOutput));

		wsOutputts.setTextoutput(textOutput);

		// appInfo.getWorkSheetObjectsAL().get(appInfo.getSelectedSheetIndex()).getNcaInfo().setWorkSheetOutputs(wsOutputts);

	}

	private int decideCurrentIncrement(double[] computedTime,
			double dosingTime, double currentTime) {
		int inc = 0;
		int j = 0;
		
		
		inc = findVal(computedTime, currentTime) - findVal(computedTime, dosingTime);
		 
		return inc;
	}

	private double[] computingConc(double[] x2, double[] conc, double[] time,
			double[] doseInterval2) {
		double[] y2 = new double[x2.length];

		if(procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 0)
		{
			for (int i = 0; i < x2.length; i++) {
				if (x2[i] <= time[time.length - 1]) {
					y2[i] = ncaInst.linearInterpolation(time, conc, x2[i]);
				} else {
					y2[i] = Math.exp((-lambdaZ) * x2[i] + Intercept);
				}

			}
		}else
		{
			for (int i = 0; i < x2.length; i++) {
				
								
				if (x2[i] <= time[time.length - 1]) {
					y2[i] = ncaInst.linearInterpolation(time, conc, x2[i]);
				} else {
					y2[i] = Math.exp((-lambdaZ) * x2[i] + Intercept);
				}

			}
		}
		
		return y2;

	}

	private double[] computingTimepoints(double[] x2, double[] doseInterval) {

		double[] time = null;
		if(procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 0)
		{
			int noOfPtsInFirstDose = 0;
			int i = 0;
			while (x2[i] < doseInterval[0]) {
				i++;
				noOfPtsInFirstDose++;
			}
			noOfPtsIn1stDose = noOfPtsInFirstDose;
			time = new double[noOfPtsInFirstDose * numberOfDose + 1];

			int noOfDose;
			for (int j = 0; j < time.length; j++) {

				noOfDose = j / noOfPtsInFirstDose;
				int inc = j % noOfPtsInFirstDose;

				if (noOfDose == 0) {
					time[j] = x2[inc];
				} else {
					double totalDoseInterval = 0;
					for (int k = 0; k < noOfDose; k++) {
						totalDoseInterval = totalDoseInterval + doseInterval[k];
					}
					time[j] = totalDoseInterval + x2[inc];
				}

			}
		}else
		{
			
			ArrayList<double[]> timePoints = new ArrayList<double[]>();
			
			for (int i = 0; i < dosingTime.length; i++) {
				
				int noOfPtsInCurrentDose = 0;
				int j = 0;
				
				if(i<dosingTime.length -1)
				{
					while (x2[j] < doseInterval[i]) {
						j++;
						noOfPtsInCurrentDose++;
					}
					
				}else
				{
					noOfPtsInCurrentDose = x2.length;
				}
				
				for (int j2 = 0; j2 < noOfPtsInCurrentDose; j2++) {
					double[] temp = new double[1];
					temp[0] = dosingTime[i] + x2[j2];
					timePoints.add(temp);
				}
			}
				time = new double[timePoints.size()];
				for (int j2 = 0; j2 < timePoints.size(); j2++) {
					double[] temp = timePoints.get(j2);
					time[j2] = temp[0];
				}
				
			
								
			
		}
		
		

		return time;
	}

	private void generatPlots(ApplicationInfo appInfo, int sum,
			double[] outputTimePoints, double[] outputConcPoints, int j) {
		String profile = "";
		for (int k = 0; k < procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size(); k++) {
			profile = profile
					+ procInputInst.getMappingDataObj()
							.getSortVariablesListVector().get(k)
					+ "="
					+ procInputInst.getProfileInfoObj().getDataSortVariables()[j][k]
					+ ",";

		}

		NonParametricSuperpositionOutputPlots npsPlotsInst = new NonParametricSuperpositionOutputPlots();

		npsPlotsInst.GenerateplottsForNPSOutput(outputTimePoints,
				outputConcPoints, profile);
	}

	private void storeTextAndSpreedSheetOutput(ApplicationInfo appInfo,
			int sum, double[] outputTimePoints, double[] outputConcPoints,
			int i, int j) {
		String strForTextOutput = "";
		for (int k = 0; k < procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size(); k++) {
			strForTextOutput = strForTextOutput
					+ procInputInst.getProfileInfoObj().getDataSortVariables()[j][k]
					+ "\t";
		}

		String[] s = new String[procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size() + 2];
		for (int k = 0; k < procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size(); k++) {
			s[k] = procInputInst.getProfileInfoObj().getDataSortVariables()[j][k];
		}

		strForTextOutput = strForTextOutput
				+ Double.toString(round(outputTimePoints[i], roundTo)) + "\t";
		strForTextOutput = strForTextOutput
				+ Double.toString(round(outputConcPoints[i], roundTo)) + "\t";
		strForTextOutput = strForTextOutput + "\r\n\r\n";
		textOutput.add(strForTextOutput);

		s[procInputInst.getMappingDataObj().getSortVariablesListVector().size()] = Double
				.toString(round(outputTimePoints[i], roundTo));
		s[procInputInst.getMappingDataObj().getSortVariablesListVector().size() + 1] = Double
				.toString(round(outputConcPoints[i], roundTo));

		spreedSheetOutput.add(s);
	}

	private void textOutputHeader(ApplicationInfo appInfo) {
		String strForTextOutput = "";
		for (int k = 0; k < procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size(); k++) {

			strForTextOutput = strForTextOutput
					+ procInputInst.getMappingDataObj()
							.getSortVariablesListVector().get(k) + "\t";
		}

		strForTextOutput = strForTextOutput + "time" + "("
				+ procInputInst.getParameterUnitsDataObj().getTimeUnit() + ")"
				+ "\t";

		massUnit = procInputInst.getParameterUnitsDataObj().getConcMassUnit();

		volumeUnit = procInputInst.getParameterUnitsDataObj().getVolumeUnit();

		strForTextOutput = strForTextOutput + "Predicted Conc" + "(" + massUnit
				+ "/" + volumeUnit + ")" + "\t";

		strForTextOutput = strForTextOutput + "\r\n\r\n";
		textOutput.add(strForTextOutput);
	}

	private void capturingProfilesInfo(ApplicationInfo appInfo) {
		noOfObs = procInputInst.getProfileInfoObj().getTimeForAllProfile().length;

		allStartTime = new double[noOfObs];
		allConcentration = new double[noOfObs];

		numberOfSortVariable = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		subject_obs = new int[procInputInst.getProfileInfoObj().getNo_subject()];

		subject_obs = procInputInst.getProfileInfoObj().getSubject_obs();
		no_subject = procInputInst.getProfileInfoObj().getNo_subject();

		allStartTime = procInputInst.getProfileInfoObj().getTimeForAllProfile();

		allConcentration = procInputInst.getProfileInfoObj()
				.getConcForAllProfile();

	}

	private void capturingDosingInfo(int profileNo) {

		int noOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		if (procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 0) {

			try {
				String S1 = procInputInst.getModleInputForNPSObj()
						.getAdministeredDosingValueAt(profileNo, noOfSortVar);
				loadingDose = Double.parseDouble(S1);
			} catch (Exception e) {
				String S = procInputInst.getModleInputForNPSObj()
						.getLoadingDose();
				loadingDose = Double.parseDouble(S);

			}

			try {
				String S = procInputInst.getModleInputForNPSObj()
						.getMaintenanceDose();
				maintenanceDose = Double.parseDouble(S);

			} catch (Exception e) {

			}

		} else {

			try {
				String S1 = procInputInst.getModleInputForNPSObj()
						.getAdministeredDosingValueAt(profileNo, noOfSortVar);
				loadingDose = Double.parseDouble(S1);
			} catch (Exception e) {

			}

			doseInterval = new double[numberOfDose];
			dosingData = new double[numberOfDose];
			dosingTime = new double[numberOfDose];

			dosingData[0] = loadingDose;
			dosingTime[0] = 0;

			int noOfcol = procInputInst.getModleInputForNPSObj()
					.getDosingDataForNPS()[0].length;

			for (int i = 0; i < doseInterval.length - 1; i++) {

				String S = procInputInst.getModleInputForNPSObj()
						.getDosingDataForNPS()[(profileNo * (numberOfDose - 1))
						+ i][noOfcol - 1];

				String S1 = procInputInst.getModleInputForNPSObj()
						.getDosingDataForNPS()[(profileNo * (numberOfDose - 1))
						+ i][noOfcol - 2];

				dosingData[i + 1] = Double.parseDouble(S);
				dosingTime[i + 1] = Double.parseDouble(S1);

			}

			for (int i = 0; i < numberOfDose - 1; i++) {
				doseInterval[i] = dosingTime[i + 1] - dosingTime[i];
			}

		}

	}

	private void capturingModelInputs(int profileNo) {

		if (procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 0) {

			if (procInputInst.getModleInputForNPSObj()
					.isIfDisplayAtSteadyState() == true) {

				String S = procInputInst.getModleInputForNPSObj()
						.getTauValueInNPS();
				double tempDI = Double.parseDouble(S);
				double temp = Math.log(2) * 10 / (lambdaZ * tempDI);

				numberOfDose = (int) temp;

				if ((temp - numberOfDose) > 0.5) {
					numberOfDose = numberOfDose + 1;
				}

				S = procInputInst.getModleInputForNPSObj().getTauValueInNPS();
				tempDI = Double.parseDouble(S);
				doseInterval = new double[numberOfDose];

				for (int i = 0; i < doseInterval.length; i++) {
					doseInterval[i] = tempDI;
				}

			} else {

				try {
					String S = procInputInst.getModleInputForNPSObj()
							.getNumberOfDoseBeforeDisplay();
					numberOfDose = Integer.parseInt(S);

				} catch (Exception e) {
					numberOfDose = 1;
				}

				String S = procInputInst.getModleInputForNPSObj()
						.getTauValueInNPS();
				double tempDI = Double.parseDouble(S);
				doseInterval = new double[numberOfDose];

				for (int i = 0; i < doseInterval.length; i++) {
					doseInterval[i] = tempDI;
				}

			}

		} else {

			try {
				String S = procInputInst.getModleInputForNPSObj()
						.getNumberOfDoseBeforeDisplay();
				numberOfDose = Integer.parseInt(S);

			} catch (Exception e) {
				numberOfDose = 1;
			}
		}

		try {
			String S = procInputInst.getModleInputForNPSObj()
					.getNoOfOutputPoints();
			numberOfOutputPoints = Integer.parseInt(S);

		} catch (Exception e) {
			numberOfOutputPoints = 100;
		}

	}

	public double round(double value, int decimalPlace) {
		double power_of_ten = 1;
		while (decimalPlace-- > 0) {
			power_of_ten *= 10.0;
		}
		return Math.round(value * power_of_ten) / power_of_ten;
	}

	public int lastNonZero(double[] mat) {

		int index = 0;
		for (int i = mat.length - 1; i >= 0; i--) {
			if (mat[i] != 0.0) {
				index = i;
				break;
			}
		}
		return index;
	}

	public void simpleLinRegression(double[] x, double[] y) {
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
		double weightingIndex = 1.0;
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
		// Rsq=((Sxy*Sxy)/(Sxx*Syy));

	}

	private void calculateLambdaZ(double[] time, double[] conc, int profileNo)
			throws Exception {
		LambdaZCalculationForNPS npsLambdaZCalInst = new LambdaZCalculationForNPS();
		int noOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		try {

			String S1 = procInputInst.getModleInputForNPSObj()
					.getTerminatPhaseValueAt(profileNo, noOfSortVar);
			String S2 = procInputInst.getModleInputForNPSObj()
					.getTerminatPhaseValueAt(profileNo, noOfSortVar + 1);
			startTime = Double.parseDouble(S1);
			endTime = Double.parseDouble(S2);

		} catch (Exception e) {
			ifTerminalPhaseDefined = false;
		}

		if (ifTerminalPhaseDefined == true) {

			npsLambdaZCalInst.lambdaZCalculation(time, conc, startTime,
					endTime, 2);
			// calculateLambdaZUsingRange(time, conc, startTime, endTime);
		} else {
			npsLambdaZCalInst.lambdaZCalculation(time, conc, 0, 0, 1);

			// lambdaZCalculation(time, conc);
		}
	}

	private void calculateLambdaZUsingRange(double[] time, double[] conc,
			double startTime, double endTime) {

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
					count2++;
				}

			}
			simpleLinRegression(newX, newLogY);
			/*
			 * lambdaZUsingInterval = (-1) * Slope;
			 * 
			 * 
			 * double[] Ycalc = new double[noPoints];
			 * 
			 * for (int i = 0; i < noPoints; i++) { Ycalc[i] = newX[i] * Slope +
			 * Intercept;
			 * 
			 * } double sumY = 0; double meanY = 0; double SST = 0; double SSR =
			 * 0; double SSE = 0; double[] error = new double[noPoints];
			 * 
			 * for (int i = 0; i < noPoints; i++) { sumY = sumY + newLogY[i];
			 * error[i] = newLogY[i] - Ycalc[i]; SSE = SSE + Math.pow(error[i],
			 * 2); } meanY = sumY / noPoints; for (int i = 0; i < noPoints; i++)
			 * { SST = SST + Math.pow(newLogY[i] - meanY, 2); SSR = SSR +
			 * Math.pow(meanY - Ycalc[i], 2); }
			 * 
			 * Rsq = 1.0 - (SSE / SST);
			 * 
			 * adjRsq = 1.0 - ((1.0 - Rsq) * (noPoints - 1)) / (noPoints - 2);
			 */
		}

	}

	private void lambdaZCalculation(double[] time, double[] conc) {

		double Rsq = 0;
		double adjRsq = 0;
		int noObs = time.length;
		int noPoints = 0;

		for (int i = 0; i < noObs; i++) {
			if (conc[i] != 0)
				noPoints++;
		}
		if (noPoints < 3)
			lambdaZ = 0;
		else {
			noPoints = 3;
			double previousAdjRsq = 0;
			do {

				previousAdjRsq = adjRsq;

				double[] newX = new double[noPoints];
				double[] newY = new double[noPoints];
				double[] newLogY = new double[noPoints];
				int count2 = 0;
				int count = 1;

				for (int i = time.length - 1; i >= 0; i--) {
					if (conc[i] != 0 && count <= noPoints) {
						newX[count2] = time[i];
						newY[count2] = conc[i];
						newLogY[count2] = Math.log(conc[i]);
						count2++;
						count++;
					}

				}

				simpleLinRegression(newX, newLogY);
				lambdaZ = (-1) * Slope;

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
				noPoints++;

			} while (previousAdjRsq <= adjRsq);

		}

		System.out.println();

	}

	double linearInterpolation(double[] t, double[] C, double tstar) {
		double Cstar = 0;
		int ifInterpReq = findVal(t, tstar);
		int interpIdx = 0;
		if (ifInterpReq == -1) {
			for (int i = 0; i < t.length - 1; i++) {
				if ((tstar > t[i] && tstar < t[i + 1])
						|| (tstar < t[i] && tstar > t[i + 1])) {
					interpIdx = i;
					break;
				}
			}

			if (C[interpIdx] <= C[interpIdx + 1]) {
				// firstIndex = interpIdx;
				Cstar = C[interpIdx]
						+ Math.abs((tstar - t[interpIdx])
								/ (t[interpIdx + 1] - t[interpIdx]))
						* (C[interpIdx + 1] - C[interpIdx]);
			} else {
				Cstar = logInterpolation(t, C, tstar);
			}

		} else
			Cstar = C[ifInterpReq];

		return Cstar;
	}

	double logInterpolation(double[] t, double[] C, double tstar) {
		double Cstar = 0;
		int ifInterpReq = findVal(t, tstar);
		int interpIdx = 0;
		if (ifInterpReq == -1) {
			for (int i = 0; i < t.length - 1; i++) {
				if ((tstar > t[i] && tstar < t[i + 1])
						|| (tstar < t[i] && tstar > t[i + 1])) {
					interpIdx = i;
					break;
				}
			}
			// firstIndex = interpIdx;
			double term1 = Math.log(C[interpIdx]);
			double term2 = Math.abs((tstar - t[interpIdx])
					/ (t[interpIdx + 1] - t[interpIdx]));
			double term3 = Math.log(C[interpIdx + 1]) - Math.log(C[interpIdx]);
			Cstar = Math.exp(term1 + term2 * term3);
		} else
			Cstar = C[ifInterpReq];

		return Cstar;
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

	public double minVal(double[] mat) {
		double min = mat[0];
		for (int i = 1; i < mat.length; i++) {
			if (mat[i] < min) {
				min = mat[i];
			}
		}
		return min;
	}

	private String[][] retrievingResultsFromAL(ArrayList<String[]> arrayList) {
		String[][] results = new String[arrayList.size()][arrayList.get(0).length];

		for (int i = 0; i < arrayList.size(); i++) {

			results[i] = arrayList.get(i);

		}
		return results;
	}

	ProcessingInputsInfo copyProcessingInput() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = null;
		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		procInputInst = appInfo
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
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs();

		return procInputInst;
	}
}
