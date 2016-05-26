package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import view.ApplicationInfo;

public class CalculatePDParamters {
	
	double baseLineIntercept;
	double aucAboveB;
	double aucBelowB;
	double aucNetB;
	double slope1;
	double slope2;
	double rsqSlope1;
	double rsqSlope2;
	double adjRsqSlope1;
	double adjRsqSlope2;
	double corrXYSlope1;
	double corrXYSlope2;
	int noOfPtsInSlope1;
	int noOfPtsInSlope2;
	double slope1Lower;
	double slope2Lower;
	double slope1Upper;
	double slope2Upper;
	double rMax;
	double rMin;
	double timeAboveB;
	double timeBelowB;
	double timePercentageBelowB;
	double threshold;
	double aucAboveT;
	double aucBelowT;
	double aucNetT;
	double timeAboveT;
	double timeBelowT;
	double timePercentageBelowT;
	double tOnset;
	double tOffset;
	double diffTOnsetTOffset;
	double timeBetweenBT;
	double tMax;
	double tMin;
	double aucLowerUpper;
	double thresholdIntercept;
	static double Slope, Intercept;
	NCA ncaInst;
	public static PrintStream P22;
	double[] dependentVariable;
	double[] inDependentVariable;
	ApplicationInfo appInfo;
	String missingParamDisp;
	
	
	public void calculationOfPDParameters(int profileNo)
	{
	
		// double dosingTime = 0; 
		
		ncaInst = NCA.creaetNCAInst();
		appInfo = ncaInst.getAppInfo();
		missingParamDisp = "Missing";
		double[] x = new double[ncaInst.Xp.length];
		double[] y = new double[ncaInst.Xp.length];
		x = ncaInst.Xp;
		y = ncaInst.Yp;
		double dosingTime = ncaInst.dosingTime[profileNo];
		
		int methodNo = ncaInst.method;
		
		try {
			P22 = new PrintStream(new FileOutputStream("PDtrace.txt"));
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}

		double area1, area2;
			
		int no_data = x.length;
		int j = 0;
		
		//System.out.println(" Data for the calculation : ");
		for (j = 0; j < no_data; j++)
			P22.println("x=" + x[j] + "y =" + y[j]);

		
		
		//System.out.println("Enter the intercept of the baseline: ");
		String line = null;
		//double baselineIntercept = 0;
		int noOfSortVar = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getSortVariablesListVector().size();
		
		try{
			
		}catch (Exception e) {
		
			baseLineIntercept = y[ncaInst.findVal(x, dosingTime)];
			
		}
	
		// remove this line
		baseLineIntercept = 65;
		
		
	
	
		
		
		dataPretreatmentForPD(x, y, dosingTime, baseLineIntercept);
	
		ncaInst.AUCValue=new double[inDependentVariable.length];
		ncaInst.AUMCValue=new double[inDependentVariable.length];
		
		if(methodNo == 0)
			ncaInst.linearlogTrapezoidal(inDependentVariable, dependentVariable);
		if(methodNo == 1)
			ncaInst.linearUplogDownTrapezoidal(inDependentVariable, dependentVariable);
		if(methodNo == 2)
			ncaInst.linearTrapezoidal(inDependentVariable, dependentVariable);
		if(methodNo == 3)
			ncaInst.linearTrapezoidal(inDependentVariable, dependentVariable);
		ncaInst.linearTrapezoidal(x, y);
		area1 = ncaInst.AUCall;
	
		int i = 0;

/*		System.out
				.println(" Data for the calculation of area above the baseline ");*/
		
		P22.println("BaseLine: " + baseLineIntercept);
		double[][] xAndY= detectPointsForAucAboveB(inDependentVariable,dependentVariable,baseLineIntercept);
		area2 = areaCalculation(xAndY,methodNo);
		calculateParamRelatedToB(area1,area2,inDependentVariable,dependentVariable,baseLineIntercept,xAndY, methodNo);
			

		// slope and rsq and adjusted Rsq calculatiuon for 2nd segment of the
		// curve.

		
	
		
		
		slope2= (-1)*(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNo]
				.getLambdaZ());
		rsqSlope2 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNo]
				.getrSquare();
		adjRsqSlope2 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNo]
				.getAdjRSquare();
		noOfPtsInSlope2 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNo]
				.getNoPtsLambdaZ();
		slope2Lower = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNo]
				.getLambdaZLower();
		slope2Upper = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNo]
				.getLambdaZUpper();
		/*
		 xForCorr=new double[noOfPtsInSlope2];
		 yForCorr=new double[noOfPtsInSlope2];
		
		for (int k = 0; k < noOfPtsInSlope2; k++) {
			xForCorr[k]=inDependentVariable[inDependentVariable.length-k-1];
			yForCorr[k]=dependentVariable[inDependentVariable.length-k-1];
			
		}*/
		
		corrXYSlope2 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNo]
				.getCorrelation(); 
		
		P22.println("slope2: "+ slope2);
		P22.println("rsqSlope2: "+ rsqSlope2);
		P22.println("adjRsqSlope2: "+ adjRsqSlope2);
		P22.println("noOfPtsInSlope2: "+ noOfPtsInSlope2);
		P22.println("Slope2_Lower: "+ slope2Lower);
		P22.println("Slope2_Upper: "+ slope2Upper);
		P22.println("corrXYSlope2: "+ corrXYSlope2);
	

		// calculation for threshold

		System.out.println("Enter the intercept of the threshold: ");
		String line1 = null;
		thresholdIntercept = 0;
	
		
		

		try{

		}catch (Exception e) {
		
			//thresholdIntercept = y[ncaInst.findVal(x, dosingTime)];
			
		}
	
		thresholdIntercept = 50;
		double[][] xAndY1= detectPointsForAucAboveT(inDependentVariable,dependentVariable,thresholdIntercept);
	
		area2 =areaCalculation(xAndY1,methodNo);
		calculateParamRelatedToT(area1, area2, inDependentVariable, thresholdIntercept,xAndY1,methodNo);
		int interpolationMethod=1;
		if(methodNo == 0|| methodNo == 1|| methodNo == 2)
			interpolationMethod = 1;
		else
			interpolationMethod = 2;
		calculateTOnsetOffset(dependentVariable,inDependentVariable,x,y,baseLineIntercept,thresholdIntercept,interpolationMethod,xAndY1);
		calculateTmaxAndTmin(dependentVariable,inDependentVariable,rMax,rMin);
		

		double Area_BT = 0;
		Area_BT = ((baseLineIntercept * (inDependentVariable[no_data - 1] - inDependentVariable[0])) - (thresholdIntercept * (inDependentVariable[no_data - 1] - inDependentVariable[0]))) - (aucAboveT - aucAboveB);
		System.out
				.println("Area between baseline and threshold and outside the response curve="
						+ Area_BT);
		storeParamValueInAppInfo();
	}
	
	
	
	
	
	
	
	private void storeParamValueInAppInfo() {

		ncaInst.parametersValue.add(ncaInst.round(tMin
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(rMin
				* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(tMax
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(rMax
				* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
				+ "");
		
		ncaInst.parametersValue.add(ncaInst.round(baseLineIntercept
				* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(aucAboveB
				* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(aucBelowB
				* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(aucNetB
				* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
				+ "");
		
		
		ncaInst.parametersValue.add(ncaInst.round(timeAboveB
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(timeBelowB
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(timePercentageBelowB
			, ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(thresholdIntercept
				* ncaInst.unitConvertAmount[1], ncaInst.roundTo)
				+ "");
		
		ncaInst.parametersValue.add(ncaInst.round(aucAboveT
				* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(aucBelowT
				* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(aucNetT
				* ncaInst.unitConvertAmount[2], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(timeAboveT
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "");
		
		
		
		ncaInst.parametersValue.add(ncaInst.round(timeBelowT
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(timePercentageBelowT
				, ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(tOnset
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(tOffset
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "");
		
		ncaInst.parametersValue.add(ncaInst.round(diffTOnsetTOffset
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(timeBetweenBT 
				* ncaInst.unitConvertAmount[0], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(slope1
				* ncaInst.unitConvertAmount[3], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(rsqSlope1
				, ncaInst.roundTo)
				+ "");
		
		
		ncaInst.parametersValue.add(ncaInst.round(adjRsqSlope1
				, ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(corrXYSlope1
				, ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(noOfPtsInSlope1
				, ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(slope1Lower
				, ncaInst.roundTo)
				+ "");
		
		ncaInst.parametersValue.add(ncaInst.round(slope1Upper
				, ncaInst.roundTo)
				+ "");
		
		ncaInst.parametersValue.add(ncaInst.round(slope2
				* ncaInst.unitConvertAmount[3], ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(rsqSlope2
				, ncaInst.roundTo)
				+ "");
		
		
		ncaInst.parametersValue.add(ncaInst.round(adjRsqSlope2
				, ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(corrXYSlope2
				, ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(noOfPtsInSlope2
				, ncaInst.roundTo)
				+ "");
		ncaInst.parametersValue.add(ncaInst.round(slope2Lower
				, ncaInst.roundTo)
				+ "");
		
		ncaInst.parametersValue.add(ncaInst.round(slope2Upper
				, ncaInst.roundTo)
				+ "");
		
		System.out.println();
	}
	private void calculateTOnsetOffset(double[] dependentVariable,
			double[] inDependentVariable,double[] x,double[] y, double baselineIntercept,
			double thresholdIntercept, int interpolationMethod, double[][] xAndY1) {
		
		for (int i = 0; i < xAndY1.length; i++) {
			if(xAndY1[i][1]>0 && xAndY1[i+1][1]==0)	
			{
				tOnset = xAndY1[i+1][0];
				break;
			}
		}
		
		for (int i = xAndY1.length -1; i>=0; i--) {
			if(xAndY1[i][1]>0 && xAndY1[i-1][1]==0)	
			{
				tOffset = xAndY1[i-1][0];
				break;
			}
		}
		
		/*if(y[0]<thresholdIntercept)
			tOnset = dependentVariable[0];
		else
		{
			if(interpolationMethod == 1)
				tOnset=nca.linearInterpolation(y, x, thresholdIntercept);
			else
				tOnset=nca.logInterpolation(y, x, thresholdIntercept);
			
		}
		P22.println("Tonset:" +tOnset);
		
		int count=0;
		for (int i = 0; i < x.length; i++) {
			if(x[i]>tOnset)
				count++;
		}
		double[] tempX = new double[count];
		double[] tempY = new double[count];
		count=0;
		for (int i = 0; i < x.length; i++) {
			
			if(x[i]>tOnset)
			{
				tempX[count]=x[i];
				tempY[count]=y[i];
				count++;
			}
		}
		if(interpolationMethod == 1)
			tOffset = nca.linearInterpolation(tempY, tempX, thresholdIntercept);
		else
			tOffset = nca.logInterpolation(tempY, tempX, thresholdIntercept);*/
		
		P22.println("Tonset:" +tOnset);
		P22.println("Toffset:" +tOffset);
		
		diffTOnsetTOffset = tOffset - tOnset;
		P22.println("diffTOnsetTOffset:" +diffTOnsetTOffset);
		
	}


	private double areaCalculation(double[][] xAndY, int methodNo) {
		double area2;
		double[] x2 = new double[xAndY.length];
		double[] y2 = new double[xAndY.length];
		for (int i = 0; i < xAndY.length; i++) {
			x2[i] = xAndY[i][0];
			y2[i] = xAndY[i][1];
			System.out.println("x=" + x2[i] + "y =" + y2[i]);

		}

		ncaInst.AUCValue=new double[x2.length];
		ncaInst.AUMCValue=new double[x2.length];
		if(methodNo == 0)
			ncaInst.linearlogTrapezoidal(x2, y2);
		if(methodNo == 1)
			ncaInst.linearUplogDownTrapezoidal(x2, y2);
		if(methodNo == 2)
			ncaInst.linearTrapezoidal(x2, y2);
		if(methodNo == 3)
			ncaInst.linearTrapezoidal(x2, y2);
		area2 = ncaInst.AUCall;
		return area2;
	}


	private void calculateTmaxAndTmin(double[] y, double[] x, double Rmax, double Rmin) {
		
		int no_data = y.length;

		for (int i = 0; i < no_data; i++) {
			if (y[i] == Rmax) {
				tMax = x[i];
			}
		}

		for (int i = 0; i < no_data; i++) {
			if (y[i] == Rmin) {
				tMin = x[i];
			}
		}
	
		System.out.println("Tmax=" + tMax);
		System.out.println("Tmin=" + tMin);
		P22.println("Tmax=" + tMax);
		P22.println("Tmin=" + tMin);

	}


	private double[][] detectPointsForAucAboveT(double[] x, double[] y,
			double threshold_intercept) {
		int i,j = 0;
		int no_data = x.length;
		double[] x1= new double[2*no_data];
		double[] y1= new double[2*no_data];
		double temp1;
		for (i = 0; i < no_data - 1; i++) {
			if ((y[i] >= threshold_intercept && y[i + 1] < threshold_intercept)
					|| (y[i] < threshold_intercept && y[i + 1] >= threshold_intercept)) {
				x1[j] = x[i];
				y1[j] = y[i] - threshold_intercept;
				if (y1[j] < 0)
					y1[j] = 0;
				System.out.println(" " + x1[j] + "  " + y1[j]);

				double[] tempX={x[i + 1], x[i]};
				double[] tempY={y[i + 1], y[i]};

				/*temp1 = x[i]
						+ ((x[i + 1] - x[i]) * (baseline_intercept - y[i]))
						/ (y[i + 1] - y[i]);*/
				if(y[i]>y[i+1])
					temp1=ncaInst.logInterpolation(tempY, tempX, threshold_intercept);
				else
					temp1=ncaInst.linearInterpolation(tempY, tempX, threshold_intercept);
				j++;
				x1[j] = temp1;
				y1[j] = 0;
				System.out.println(" " + x1[j] + "  " + y1[j]);

				j++;
			} else {
				x1[j] = x[i];
				y1[j] = y[i] - threshold_intercept;
				if (y1[j] < 0)
					y1[j] = 0;
				System.out.println(" " + x1[j] + "  " + y1[j]);
				j++;

			}
		}
		x1[j] = x[i];
		y1[j] = y[i] - threshold_intercept;
		if (y1[j] < 0)
			y1[j] = 0;
		double[][] xAndY1 = new double[j+1][2];

		for ( i = 0; i < xAndY1.length; i++) {
			xAndY1[i][0] = x1[i];
			xAndY1[i][1] = y1[i];
		}
		
		System.out.println(" " + x1[j] + "  " + y1[j]);
		return xAndY1;
	}


	private void calculateParamRelatedToT(double area1, double area2,
			double[] x, double threshold_intercept, double[][] xAndY1, int methodNo) {
		double AUEC_T,AUEC_Tot;
		int no_data=x.length;
		AUEC_Tot = area1;
		AUEC_T = threshold_intercept * (x[no_data - 1] - x[0]);
		aucAboveT = area2;
		//aucBelowT = AUEC_T - (AUEC_Tot - aucAboveT);
		aucBelowT = calculateAucBelow(xAndY1,threshold_intercept,methodNo);
		aucNetT = aucAboveT - aucBelowT;
		System.out.println("AUEC_T:  " + AUEC_T);
		System.out.println("AUEC_Above_T = " + aucAboveT);
		P22.println("AUC_Above_T:  " + aucAboveT);
		System.out.println("AUEC_Below_T = " + aucBelowT);
		P22.println("AUC_Below_T:  " + aucBelowT);
		System.out.println("AUEC_Net_T = " + aucNetT);
		P22.println("AUC_Net_T:  " + aucNetT);

		//double Time_Above_T = 0, Time_Below_T = 0;
		double[] xx2 = new double[xAndY1.length];
		double[] yy2 = new double[xAndY1.length];

		for (int i = 0; i < xAndY1.length; i++) {
			xx2[i] = xAndY1[i][0];
			yy2[i] = xAndY1[i][1];

		}
				
		int i,j;
		for (i = 0; i < xx2.length -1 ; i++) {
			if (yy2[i] > 0 && yy2[i + 1] > 0)
				timeAboveT = timeAboveT + (xx2[i + 1] - xx2[i]);
			if (yy2[i] == 0 && yy2[i + 1] > 0)
				timeAboveT = timeAboveT + (xx2[i + 1] - xx2[i]);
			if (yy2[i] > 0 && yy2[i + 1] == 0)
				timeAboveT = timeAboveT + (xx2[i + 1] - xx2[i]);
		}
		timeBelowT = (xx2[xx2.length-1] - xx2[0]) - timeAboveT;
		
		timePercentageBelowT = 100 * timeBelowT / (x[no_data - 1] - x[0]);

		System.out.println("Time_Above_T=" + timeAboveT);
		P22.println("Time_Above_T: " + timeAboveT);
		System.out.println("Time_Below_T=" + timeBelowT);
		P22.println("Time_Below_T: " + timeBelowT);
		timePercentageBelowB = 100 * timeBelowB / (x[no_data - 1] - x[0]);
		System.out.println("Time_%Below_T=" + timePercentageBelowT);
		P22.println("Time_%Below_T: " + timePercentageBelowT);

		
		timeBetweenBT= timeBelowB - timeBelowT;
		System.out.println("Time_Between_BT=" + timeBetweenBT);
		P22.println("Time_Between_BT: " + timeBetweenBT);

	}


	private double calculateAucBelow(double[][] xAndY1,
			double thresholdIntercept,int methodNo) {
		double aucBelow=0;
		double[] tempX=new double[xAndY1.length];
		double[] tempY=new double[xAndY1.length];
		for (int i = 0; i < xAndY1.length; i++) {
			if(xAndY1[i][1]>0)
			{
				tempX[i]=xAndY1[i][0];
				tempY[i]=0;
			}
			else
			{
				if(ncaInst.findVal(inDependentVariable,xAndY1[i][0])== -1)
				{
					tempX[i]=xAndY1[i][0];
					tempY[i]=0;
				}
				else
				{
					tempX[i]=xAndY1[i][0];
					tempY[i]=thresholdIntercept - dependentVariable[ncaInst.findVal(inDependentVariable,xAndY1[i][0])];
				}
			}
		}
		
		if(methodNo == 0)
			ncaInst.linearlogTrapezoidal(tempX, tempY);
		if(methodNo == 1)
			ncaInst.linearUplogDownTrapezoidal(tempX, tempY);
		if(methodNo == 2)
			ncaInst.linearTrapezoidal(tempX, tempY);
		if(methodNo == 3)
			ncaInst.linearTrapezoidal(tempX, tempY);
		aucBelow = ncaInst.AUCall;
		return aucBelow;
	}



	private void calculateParamRelatedToB(double area1, double area2, double[] x,double[] y, double baseline_intercept,double[][] xAndY, int methodNo) {
		double AUEC_Tot,AUEC_B;
		AUEC_Tot = area1;
		int no_data = x.length;
		AUEC_B = baseline_intercept * (x[no_data - 1] - x[0]);
		aucAboveB = area2;
		//aucBelowB = AUEC_B - (AUEC_Tot - aucAboveB);
		aucBelowB = calculateAucBelow(xAndY,baseline_intercept,methodNo);
		aucNetB = aucAboveB - aucBelowB;
		System.out.println("AUC_Tot = " + AUEC_Tot);
		System.out.println("AUC_B = " + AUEC_B);
		System.out.println("AUC_Above_B = " + aucAboveB);
		System.out.println("AUC_Below_B = " + aucBelowB);
		System.out.println("AUC_Net_B = " + aucNetB);
		
		P22.println("AUC_Above_B:  " + aucAboveB);
		P22.println("AUC_Below_B:  " + aucBelowB);
		P22.println("AUC_Net_B:  " + aucNetB);
		
		 timeAboveB = 0;
		 timeBelowB = 0;
		 double[] x2 = new double[xAndY.length];
			double[] y2 = new double[xAndY.length];
			for (int i = 0; i < xAndY.length; i++) {
				x2[i] = xAndY[i][0];
				y2[i] = xAndY[i][1];
				System.out.println("x=" + x2[i] + "y =" + y2[i]);

			}
		 
		for (int i = 0; i < x2.length -1; i++) {
			if (y2[i] > 0 && y2[i + 1] > 0)
				timeAboveB = timeAboveB + (x2[i + 1] - x2[i]);
			if (y2[i] == 0 && y2[i + 1] > 0)
				timeAboveB = timeAboveB + (x2[i + 1] - x2[i]);
			if (y2[i] > 0 && y2[i + 1] == 0)
				timeAboveB = timeAboveB + (x2[i + 1] - x2[i]);
		}
		timeBelowB = (x2[x2.length -1] - x2[0]) - timeAboveB;
		
		rMax = ncaInst.maxVal(y);
		rMin = ((NCA) ncaInst).minVal(y);
		double tFinal,tDose;
		tFinal=x[x.length-1];
		tDose = 0;

		System.out.println("Rmax=" + rMax);
		System.out.println("Rmin=" + rMin);
		System.out.println("Time_Above_B=" + timeAboveB);
		System.out.println("Time_Below_B=" + timeBelowB);
		timePercentageBelowB = 100*timeBelowB/(tFinal- tDose);
		System.out.println("Time_%Below_B=" + timePercentageBelowB);
		
		P22.println("Rmax: " + rMax);
		P22.println("Rmin: " + rMin);
		P22.println("Time_Above_B: " + timeAboveB);
		P22.println("Time_Below_B: " + timeBelowB);
		P22.println("Time_%Below_B: " + timePercentageBelowB);
		
	}


	private double[][] detectPointsForAucAboveB(double[] x, double[] y, double baseline_intercept) {
		int i=0,j = 0;
		int no_data=x.length;
		double[] x1= new double[2*no_data];
		double[] y1= new double[2*no_data];
		double temp1;
		for (i = 0; i < no_data - 1; i++) {
			if ((y[i] >= baseline_intercept && y[i + 1] < baseline_intercept)
					|| (y[i] < baseline_intercept && y[i + 1] >= baseline_intercept)) {
				x1[j] = x[i];
				y1[j] = y[i] - baseline_intercept;
				if (y1[j] < 0)
					y1[j] = 0;
				System.out.println(" " + x1[j] + "  " + y1[j]);
				double[] tempX={x[i + 1], x[i]};
				double[] tempY={y[i + 1], y[i]};

				
				if(y[i]>y[i+1])
					temp1=ncaInst.logInterpolation(tempY, tempX, baseline_intercept);
				else
					temp1=ncaInst.linearInterpolation(tempY, tempX, baseline_intercept);
				
				j++;
				x1[j] = temp1;
				y1[j] = 0;
				System.out.println(" " + x1[j] + "  " + y1[j]);

				j++;
			} else {
				x1[j] = x[i];
				y1[j] = y[i] - baseline_intercept;
				if (y1[j] < 0)
					y1[j] = 0;
				System.out.println(" " + x1[j] + "  " + y1[j]);
				j++;

			}
		}
		x1[j] = x[i];
		y1[j] = y[i] - baseline_intercept;
		if (y1[j] < 0)
			y1[j] = 0;

		System.out.println(" " + x1[j] + "  " + y1[j]);
		double[][] xAndY = new double[j+1][2];
		
		for ( i = 0; i < xAndY.length; i++) {
			xAndY[i][0] = x1[i];
			xAndY[i][1] = y1[i];
		}
		
		return xAndY;
	}


	public static double[] calculate_param(double[] x, double[] y, int noPts) {
		double[] logy = new double[noPts];
		double[] termX = new double[noPts];
		double[] param = new double[2];
		for (int i = 0; i < noPts; i++) {
			termX[i] = x[i];
			logy[i] = Math.log(y[i]);
		}
	
		simpleLinRegression(termX, logy);
		param[0] = Intercept;
		param[1] = Slope;
		return param;
	}

	public static double[] terminalRsq(double[] x, double[] y, int noPts) {

		
		double[] termX = new double[noPts];
		double[] termY = new double[noPts];
		double[] Rsq = new double[2];
		
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

		Rsq[0] = 1.0 - (SSE / SST);// rsq
		Rsq[1] = 1.0 - ((1.0 - Rsq[0]) * (noPts - 1)) / (noPts - 2);// adjusted
																// rsq
		return Rsq;

	}
	
	public static void simpleLinRegression(double[] x1, double[] y1) {
		int n = x1.length;
		double sumX = 0;
		double sumY = 0;
		double sumXY = 0;
		double sumX2 = 0;

		// System.out.println("data in simple LR"+x[0]);

		for (int i = 0; i < n; i++) {
			sumX = sumX + x1[i];
			sumY = sumY + y1[i];
			sumXY = sumXY + x1[i] * y1[i];
			sumX2 = sumX2 + x1[i] * x1[i];
		}

		Slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
		Intercept = (sumY - Slope * sumX) / n;
		// System.out.println("slope:"+Slope);

	}
	
	private void dataPretreatmentForPD(double[] x, double[] y, double dosingTime, double baseLine)
	{
		if(ncaInst.findVal(x, dosingTime)== -1)
		{
			inDependentVariable = new double[x.length+1];
			dependentVariable = new double[x.length+1];
			
			inDependentVariable[0] = dosingTime;
			dependentVariable[0] = baseLine;
			
			for (int i = 0; i < x.length; i++) {
				inDependentVariable[i+1] = x[i];
				dependentVariable[i+1] = y[i];
			}
			
		}
		else
		{

			inDependentVariable = new double[x.length];
			dependentVariable = new double[x.length];
								
			for (int i = 0; i < x.length; i++) {
				inDependentVariable[i] = x[i];
				dependentVariable[i] = y[i];
			}
						
		}
	}
}
