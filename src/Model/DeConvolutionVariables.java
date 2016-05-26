package Model;

import java.util.ArrayList;

import Common.OptionalAndRequiredDecimals;

import view.ApplicationInfo;
import view.DisplayContents;
import view.ProcessingInputsInfo;

public class DeConvolutionVariables {

	static DeConvolutionVariables deConvoVariablesInst = null;

	public static DeConvolutionVariables createDeConvoVarInstance() {
		if (deConvoVariablesInst == null) {
			deConvoVariablesInst = new DeConvolutionVariables();
		}
		return deConvoVariablesInst;
	}

	ApplicationInfo appInfo;

	double[] time;
	double[] conc;

	int noOfSortVar;
	int noOfProfiles;
	String[][] sortVarData;
	double[] dose;
	double[] dosingTime;

	int noOfExpTerm;
	double[] expValues;
	double[] parameter;
	int[] profileObsNos;
	int currentProfileNumber;

	String[] sortVariables;
	String analysisType;
	double[] fracAbsorption;
	double[] absRate;
	
	WorkSheetOutputs workSheetOutputInst;
	
	ArrayList<String[]> verticalParametersAL;
	ArrayList<String[]> horizontalParametersAL;
	ArrayList<String[]> dosingValueAL;
	ArrayList<String[]> predictedValueAL;
	ArrayList<String[]> summaryTableAL;
	ArrayList<String[]> exclusionAL;
	ArrayList<String[]> userSettingsAL;
	ArrayList<String[]> historyAL;
	
	int timeOptDec;
	String timeReqDec;
	int numberOfDose;
	int concOptDec;
	String concReqDec;
	
	
	double rsq;
	double adjRsq;
	double lambdaZ;
	int noOfPtsLambdaZ;

	double[] auc;
	double[] cumulativeAuc;
	double[] predConc;
	double[] residual;
	double[] weighting;
	double[] cumulAmtAbsV;
	double[] amtPeripheralV;
	
	double aucInf;
	double cumulAmtAbsVInf;
	
	boolean ifAucInfProvided;
	boolean ifObsAucInfIsUsed;
	boolean ifPredAucInfIsUsed;
	
	
	boolean ifCLastPred;
	boolean ifLambdaZProvided;
	
	int aucCalMethodNo;
	double intercept;
	
	String xColumnName;
	String yColumnName;
	


	public DeConvolutionVariables() {
		
		
		time = new double[1];
		conc = new double[1];
		noOfSortVar = 0;
		noOfProfiles = 1;
		sortVarData = new String[1][1];
		dose = new double[1];
		noOfExpTerm = 0;
		expValues = new double[1];
		parameter = new double[1];
		profileObsNos = new int[1];
		currentProfileNumber = 0;
		sortVariables = new String[1];
		fracAbsorption = new double[1];
		absRate = new double[1];
		xColumnName = "";
		yColumnName = "";
		workSheetOutputInst = new WorkSheetOutputs();
		
	}

	ProcessingInputsInfo copyProcessingInput() {

		ProcessingInputsInfo procInputInst = null;
		if (analysisType.equals("polyexpo")) {
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
									.getSelectedSheetIndex()).getPolyExpoInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("wagnelson")) {
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
									.getSelectedSheetIndex())
					.getWagNelsonInfo().getProcessingInputs();
		} else if (analysisType.equals("looriegel")) {
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
									.getSelectedSheetIndex())
					.getLooRiegelInfo().getProcessingInputs();
		}

		return procInputInst;
	}

	void setProcessingInput(ProcessingInputsInfo procInputInst) {

		if (analysisType.equals("polyexpo")) {
			appInfo
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
									.getSelectedSheetIndex()).getPolyExpoInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("wagnelson")) {
			appInfo
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
									.getSelectedSheetIndex())
					.getWagNelsonInfo().setProcessingInputs(procInputInst);
		} else if (analysisType.equals("looriegel")) {
			appInfo
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
									.getSelectedSheetIndex())
					.getLooRiegelInfo().setProcessingInputs(procInputInst);
		}
	}

public void removeMissingData(int profileNo) {
		

		
		int noOfNonMissingPoint = 0;
		
		for (int i = 0; i < time.length; i++) {
			if(Double.isNaN(conc[i]) == true)
			{
				conc[i] =  -3.14159265359;
			}
			
			if(conc[i] == -3.14159265359)
			{
				
			}else
			{
				noOfNonMissingPoint++;
			}
			
		}
		
			double[] tempTime = new double[time.length];
			double[] tempConc = new double[conc.length];
			
			for (int i = 0; i < tempConc.length; i++) {
				tempTime[i] = time[i];
				tempConc[i] = conc[i];
			}
			
			time = new double[noOfNonMissingPoint];
			conc = new double[noOfNonMissingPoint];
			
			int count = 0;
			for (int i = 0; i < tempTime.length; i++) {
				
				if(tempConc[i] == -3.14159265359)
				{
					
				}else
				{
					time[count] = tempTime[i];
					conc[count] = tempConc[i];
					count++;
				}
			}
			
		profileObsNos[profileNo] = noOfNonMissingPoint;
		
	
	}



void determineOptReqDecOfAnalysis() {
	ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

	if (analysisType.equals("polyexpo")) {
		timeOptDec = appInfo
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
								.getSelectedSheetIndex())
				.getPolyExpoInfo()
				.getColumnPropertiesArrayList()
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
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPolyExpoInfo().getProcessingInputs()
								.getMappingDataObj()
								.getxColumnCorrespondinOriginalIndex())
				.getOptionalDecimals();
		timeReqDec = appInfo
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
								.getSelectedSheetIndex())
				.getPolyExpoInfo()
				.getColumnPropertiesArrayList()
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
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPolyExpoInfo().getProcessingInputs()
								.getMappingDataObj()
								.getxColumnCorrespondinOriginalIndex())
				.getRequiredDecimals();

		concOptDec = appInfo
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
								.getSelectedSheetIndex())
				.getPolyExpoInfo()
				.getColumnPropertiesArrayList()
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
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPolyExpoInfo().getProcessingInputs()
								.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex())
				.getOptionalDecimals();
		concReqDec = appInfo
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
								.getSelectedSheetIndex())
				.getPolyExpoInfo()
				.getColumnPropertiesArrayList()
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
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPolyExpoInfo().getProcessingInputs()
								.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex())
				.getRequiredDecimals();

	} else if (analysisType.equals("wagnelson")) {
		timeOptDec = appInfo
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
								.getSelectedSheetIndex())
				.getWagNelsonInfo()
				.getColumnPropertiesArrayList()
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
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getWagNelsonInfo().getProcessingInputs()
								.getMappingDataObj()
								.getxColumnCorrespondinOriginalIndex())
				.getOptionalDecimals();
		timeReqDec = appInfo
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
								.getSelectedSheetIndex())
				.getWagNelsonInfo()
				.getColumnPropertiesArrayList()
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
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getWagNelsonInfo().getProcessingInputs()
								.getMappingDataObj()
								.getxColumnCorrespondinOriginalIndex())
				.getRequiredDecimals();

		concOptDec = appInfo
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
								.getSelectedSheetIndex())
				.getWagNelsonInfo()
				.getColumnPropertiesArrayList()
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
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getWagNelsonInfo().getProcessingInputs()
								.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex())
				.getOptionalDecimals();
		concReqDec = appInfo
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
								.getSelectedSheetIndex())
				.getWagNelsonInfo()
				.getColumnPropertiesArrayList()
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
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getWagNelsonInfo().getProcessingInputs()
								.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex())
				.getRequiredDecimals();

	} else if (analysisType.equals("looriegel")) {
		timeOptDec = appInfo
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
								.getSelectedSheetIndex())
				.getLooRiegelInfo()
				.getColumnPropertiesArrayList()
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
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getLooRiegelInfo().getProcessingInputs()
								.getMappingDataObj()
								.getxColumnCorrespondinOriginalIndex())
				.getOptionalDecimals();
		timeReqDec = appInfo
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
								.getSelectedSheetIndex())
				.getLooRiegelInfo()
				.getColumnPropertiesArrayList()
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
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getLooRiegelInfo().getProcessingInputs()
								.getMappingDataObj()
								.getxColumnCorrespondinOriginalIndex())
				.getRequiredDecimals();

		concOptDec = appInfo
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
								.getSelectedSheetIndex())
				.getLooRiegelInfo()
				.getColumnPropertiesArrayList()
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
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getLooRiegelInfo().getProcessingInputs()
								.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex())
				.getOptionalDecimals();
		concReqDec = appInfo
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
								.getSelectedSheetIndex())
				.getLooRiegelInfo()
				.getColumnPropertiesArrayList()
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
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getLooRiegelInfo().getProcessingInputs()
								.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex())
				.getRequiredDecimals();
	}

	} 


public String formatting(double value, boolean isTimeRelatedValue) {
	
	String tempStr = "";
	
	OptionalAndRequiredDecimals optAndRedDecimalInst = new OptionalAndRequiredDecimals();

	determineOptReqDecOfAnalysis();

	if ((timeOptDec == 0) && (timeReqDec.equals(""))) {
		timeReqDec = "0.0000";
	}
	if ((timeOptDec == 0) && (timeReqDec.equals("0."))) {
		timeReqDec = "0.0000";
	}

	if ((concOptDec == 0) && (concReqDec.equals(""))) {
		concReqDec = "0.0000";
	}

	if ((concOptDec == 0) && (concReqDec.equals("0."))) {
		concReqDec = "0.0000";
	}

	if (isTimeRelatedValue == true) {
		tempStr = optAndRedDecimalInst.performFormatting(value, timeOptDec,
				timeReqDec);
	} else {
		tempStr = optAndRedDecimalInst.performFormatting(value, concOptDec,
				concReqDec);
	}

	

	return tempStr;

}



public WorkSheetOutputs getWorkSheetOutputInst() {
	return workSheetOutputInst;
}

public void setWorkSheetOutputInst(WorkSheetOutputs workSheetOutputInst) {
	this.workSheetOutputInst = workSheetOutputInst;
}

	
}
