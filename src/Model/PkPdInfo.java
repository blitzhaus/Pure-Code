package Model;

import java.util.*;

import Common.OptionalAndRequiredDecimals;
import Common.ThousandsSeperator;

import view.ApplicationInfo; //import view.DisplayContents;
import view.DisplayContents;
import view.ProcessingInputsInfo;

public class PkPdInfo {

	int nPar;
	int nFun;
	int nDer;
	int nCon;
	int nPop;

	String Default;
	int deltaDefined;
	double[] stdResidual;
	double[] sePredy;

	public double[][] getX() {
		return X;
	}

	public void setX(double[][] x) {
		X = x;
	}

	public double[][] getY() {
		return Y;
	}

	public void setY(double[][] y) {
		Y = y;
	}

	int simplexDefined;
	int wtScheme;
	int ifScaling;
	int ifRoundingOff;
	int roundTo;
	int ifPrintA;
	int ifPrintAinv;
	int ifPrintJac;
	int ifELS;
	int ifExtraData;
	int extraInInp;
	int ifWinExtraFile;
	int ifCompare;
	int ifPlot;
	int ifScatterPlot;
	int xLog;
	int yLog;
	int ifDiffXY;
	int xNum; // default XNUM= 0
	int yNum; // default YNUM= 1
	int wNum; // default WNUM= Ncol-1
	int methodNo;
	// addition of a new variable
	int methodNo1;
	int odeSolverChoice;
	double convergence;

	double wtExp;
	double crossOverProb;
	double mutationProb;
	double ifMinDefined;
	double xMin;
	double yMin;

	int max;
	int iter;
	int gridPts;
	int iterNoForProgBar = 0;
	int noOfPredval;

	String studyName;

	public double[] getParameter() {
		return parameter;
	}

	public void setParameter(double[] parameter) {
		this.parameter = parameter;
	}

	String caseStudy;
	// static public String[] parNames;
	// modification for parameter's unit
	String[] parUnit;
	String doseUnit;
	String timeUnit;
	String volumeUnit;
	String doseUnitConc;
	String concUnit;

	String concMassUnit;

	String normalizationUnit;

	String[] defaultUnitForCA;
	String[] preferredUnitForCA;
	double[] unitConversionAmountForCA;
	double[] infusionDose;

	public String getDoseUnit() {
		return doseUnit;
	}

	public void setDoseUnit(String doseUnit) {
		this.doseUnit = doseUnit;
	}

	String xColumnName;
	String yColumnName;

	String[] conNames;

	double[] initial;
	double[] delta;
	// addition of a new variable
	double[] initParameter;
	double[] parameter;
	double[] lowerBound;
	double[] upperBound;

	double[][] initSimplex;

	double[][] X; // to be fitted
	double[][] Y;

	double[][] extraData;

	int[] nObs;
	int[] nObsGiven;

	int[] row;
	int[] rowGiven;
	int[] col;

	double[] xf;
	double[] xp;
	double[] fn;

	double mu;
	double lambda;
	double multiplier;
	int exceptionForCurrentProfile;
	boolean ifCallingForParameterCalculation = false;
	int currentProfileNumber;

	String[] paramName;
	String[] secParamName;
	boolean ifSimulation;
	int noOfSecParam;
	int noOfDose;
	int modelNumber;
	boolean ifAlgebraicModel;
	boolean ifClearanceParam;
	private boolean isFirstIteration;
	public boolean isFirstIteration() {
		return isFirstIteration;
	}

	public void setFirstIteration(boolean isFirstIteration) {
		this.isFirstIteration = isFirstIteration;
	}

	int degreesOfFreedom;
	double[] aicValue;
	int aicCount;

	public int getDegreesOfFreedom() {
		return degreesOfFreedom;
	}

	public void setDegreesOfFreedom(int degreesOfFreedom) {
		this.degreesOfFreedom = degreesOfFreedom;
	}

	public int getCurrentProfileNumber() {
		return currentProfileNumber;
	}

	public void setCurrentProfileNumber(int currentProfileNumber) {
		this.currentProfileNumber = currentProfileNumber;
	}

	public boolean isIfCallingForParameterCalculation() {
		return ifCallingForParameterCalculation;
	}

	public void setIfCallingForParameterCalculation(
			boolean ifCallingForParameterCalculation) {
		this.ifCallingForParameterCalculation = ifCallingForParameterCalculation;
	}

	static PkPdInfo pkpdInst = null;

	public static PkPdInfo createPKPDInstance() {
		if (pkpdInst == null) {
			pkpdInst = new PkPdInfo();
		}
		return pkpdInst;
	}

	public PkPdInfo() {

		delta = new double[1];
		extraData = new double[1][1];
		row = new int[1];
		rowGiven = new int[1];
		xColumnIndex = 0;
		yColumnIndex = 0;
		workSheetOutputInst = new WorkSheetOutputs();
		aicValue = new double[5];
		aicCount = 0;
		stdResidual = new double[1];
		sePredy = new double[1];
		isFirstIteration = false;
	}

	public int maxNobs(int[] row_mat) {
		Arrays.sort(row_mat);
		int max = row_mat[nFun - 1];
		return max;
	}

	ArrayList<String[]> verticalParametersAL;
	ArrayList<String[]> horizontalParametersAL;
	ArrayList<String[]> initialParameterAL;
	ArrayList<String[]> minimizationProcessAL;
	ArrayList<String[]> dosingValueAL;
	ArrayList<String[]> correlationMatrixAL;
	ArrayList<String[]> eigenValuesAL;
	ArrayList<String[]> conditionNumberAL;
	ArrayList<String[]> predictedValueAL;
	ArrayList<String[]> varienceCovarienceMatrixAL;
	ArrayList<String[]> summaryTableAL;
	ArrayList<String[]> diagnosticsAL;
	ArrayList<String[]> partialDerivativeAL;
	ArrayList<String[]> secondaryParametersAL;
	ArrayList<String[]> nonTransposedSPAL;
	ArrayList<String[]> userSettingsAL;
	ArrayList<String[]> historyAL;

	public ArrayList<String[]> getVerticalParametersAL() {
		return verticalParametersAL;
	}

	public void setVerticalParametersAL(ArrayList<String[]> verticalParametersAL) {
		this.verticalParametersAL = verticalParametersAL;
	}

	public ArrayList<String[]> getHorizontalParametersAL() {
		return horizontalParametersAL;
	}

	public void setHorizontalParametersAL(
			ArrayList<String[]> horizontalParametersAL) {
		this.horizontalParametersAL = horizontalParametersAL;
	}

	public ArrayList<String[]> getInitialParameterAL() {
		return initialParameterAL;
	}

	public void setInitialParameterAL(ArrayList<String[]> initialParameterAL) {
		this.initialParameterAL = initialParameterAL;
	}

	public ArrayList<String[]> getMinimizationProcessAL() {
		return minimizationProcessAL;
	}

	public void setMinimizationProcessAL(
			ArrayList<String[]> minimizationProcessAL) {
		this.minimizationProcessAL = minimizationProcessAL;
	}

	public ArrayList<String[]> getDosingValueAL() {
		return dosingValueAL;
	}

	public void setDosingValueAL(ArrayList<String[]> dosingValueAL) {
		this.dosingValueAL = dosingValueAL;
	}

	public ArrayList<String[]> getCorrelationMatrixAL() {
		return correlationMatrixAL;
	}

	public void setCorrelationMatrixAL(ArrayList<String[]> correlationMatrixAL) {
		this.correlationMatrixAL = correlationMatrixAL;
	}

	public ArrayList<String[]> getEigenValuesAL() {
		return eigenValuesAL;
	}

	public void setEigenValuesAL(ArrayList<String[]> eigenValuesAL) {
		this.eigenValuesAL = eigenValuesAL;
	}

	public ArrayList<String[]> getConditionNumberAL() {
		return conditionNumberAL;
	}

	public void setConditionNumberAL(ArrayList<String[]> conditionNumberAL) {
		this.conditionNumberAL = conditionNumberAL;
	}

	public ArrayList<String[]> getPredictedValueAL() {
		return predictedValueAL;
	}

	public void setPredictedValueAL(ArrayList<String[]> predictedValueAL) {
		this.predictedValueAL = predictedValueAL;
	}

	public ArrayList<String[]> getVarienceCovarienceMatrixAL() {
		return varienceCovarienceMatrixAL;
	}

	public void setVarienceCovarienceMatrixAL(
			ArrayList<String[]> varienceCovarienceMatrixAL) {
		this.varienceCovarienceMatrixAL = varienceCovarienceMatrixAL;
	}

	public ArrayList<String[]> getSummaryTableAL() {
		return summaryTableAL;
	}

	public void setSummaryTableAL(ArrayList<String[]> summaryTableAL) {
		this.summaryTableAL = summaryTableAL;
	}

	public ArrayList<String[]> getDiagnosticsAL() {
		return diagnosticsAL;
	}

	public void setDiagnosticsAL(ArrayList<String[]> diagnosticsAL) {
		this.diagnosticsAL = diagnosticsAL;
	}

	public ArrayList<String[]> getPartialDerivativeAL() {
		return partialDerivativeAL;
	}

	public void setPartialDerivativeAL(ArrayList<String[]> partialDerivativeAL) {
		this.partialDerivativeAL = partialDerivativeAL;
	}

	public ArrayList<String[]> getSecondaryParametersAL() {
		return secondaryParametersAL;
	}

	public void setSecondaryParametersAL(
			ArrayList<String[]> secondaryParametersAL) {
		this.secondaryParametersAL = secondaryParametersAL;
	}

	public ArrayList<String[]> getNonTransposedSPAL() {
		return nonTransposedSPAL;
	}

	public void setNonTransposedSPAL(ArrayList<String[]> nonTransposedSPAL) {
		this.nonTransposedSPAL = nonTransposedSPAL;
	}

	public ArrayList<String[]> getUserSettingsAL() {
		return userSettingsAL;
	}

	public void setUserSettingsAL(ArrayList<String[]> userSettingsAL) {
		this.userSettingsAL = userSettingsAL;
	}

	public ArrayList<String[]> getHistoryAL() {
		return historyAL;
	}

	public void setHistoryAL(ArrayList<String[]> historyAL) {
		this.historyAL = historyAL;
	}

	int requiredIter;

	public int getRequiredIter() {
		return requiredIter;
	}

	public void setRequiredIter(int requiredIter) {
		this.requiredIter = requiredIter;
	}

	WorkSheetOutputs workSheetOutputInst;

	public WorkSheetOutputs getWorkSheetOutputInst() {
		return workSheetOutputInst;
	}

	public void setWorkSheetOutputInst(WorkSheetOutputs workSheetOutputInst) {
		this.workSheetOutputInst = workSheetOutputInst;
	}

	double pdIncrement;

	public double getPdIncrement() {
		return pdIncrement;
	}

	public void setPdIncrement(double pdIncrement) {
		this.pdIncrement = pdIncrement;
	}

	String[][] dataSortVariables;
	int numberOfSortVar;
	int noOfParam;

	String analysisType;
	String[] sortVariables;

	int xColumnIndex;
	int yColumnIndex;
	int timeOptDec;
	String timeReqDec;
	int numberOfDose;
	int concOptDec;
	String concReqDec;

	ProcessingInputsInfo copyProcessingInput() {

		ApplicationInfo appInfo = null;

		
		ListOfDataStructures dataStructInst = ListOfDataStructures
				.createListOfDataStructInstance();
		appInfo = dataStructInst.appInfo;
		

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

		ProcessingInputsInfo procInputInst = null;
		if (analysisType.equals("pk")) {
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
									.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("pd")) {
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
									.getSelectedSheetIndex()).getPdInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("mm")) {
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
									.getSelectedSheetIndex()).getMmInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("pkpdlink")) {
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
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("irm")) {
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
									.getSelectedSheetIndex()).getIrInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("ascii")) {
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
									.getSelectedSheetIndex()).getAsciiInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("invitro")) {
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
									.getSelectedSheetIndex()).getInVitroInfo()
					.getProcessingInputs();
		}
		return procInputInst;
	}

	public void setProgressBarIterNo(int iterNoForProgBar) {

		ApplicationInfo appInfo = null;

		if (analysisType.equals("pk")) {
			PkParamEstimator caParamCalInst = PkParamEstimator
					.createPkParamEstimateInstance();
			appInfo = caParamCalInst.appInfoinst;
		} else {
			ListOfDataStructures dataStructInst = ListOfDataStructures
					.createListOfDataStructInstance();
			appInfo = dataStructInst.appInfo;
		}
		if (analysisType.equals("pk")) {
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
									.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getProgressBarInfoObj()
					.setCurrentIterNo(iterNoForProgBar);
		} else if (analysisType.equals("pd")) {
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
									.getSelectedSheetIndex()).getPdInfo()
					.getProcessingInputs().getProgressBarInfoObj()
					.setCurrentIterNo(iterNoForProgBar);
		} else if (analysisType.equals("mm")) {
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
									.getSelectedSheetIndex()).getMmInfo()
					.getProcessingInputs().getProgressBarInfoObj()
					.setCurrentIterNo(iterNoForProgBar);
		} else if (analysisType.equals("pkpdlink")) {
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
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getProcessingInputs().getProgressBarInfoObj()
					.setCurrentIterNo(iterNoForProgBar);
		} else if (analysisType.equals("irm")) {
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
									.getSelectedSheetIndex()).getIrInfo()
					.getProcessingInputs().getProgressBarInfoObj()
					.setCurrentIterNo(iterNoForProgBar);
		} else if (analysisType.equals("ascii")) {
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
									.getSelectedSheetIndex()).getAsciiInfo()
					.getProcessingInputs().getProgressBarInfoObj()
					.setCurrentIterNo(iterNoForProgBar);
		}

	}

	void setProcessingInput(ProcessingInputsInfo procInputInst) {

		ApplicationInfo appInfo = null;

		if (analysisType.equals("pk")) {
			PkParamEstimator caParamCalInst = PkParamEstimator
					.createPkParamEstimateInstance();
			appInfo = caParamCalInst.appInfoinst;
		} else {
			ListOfDataStructures dataStructInst = ListOfDataStructures
					.createListOfDataStructInstance();
			appInfo = dataStructInst.appInfo;
		}

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

		if (analysisType.equals("pk")) {
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
									.getSelectedSheetIndex()).getPkInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("pd")) {
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
									.getSelectedSheetIndex()).getPdInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("mm")) {
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
									.getSelectedSheetIndex()).getMmInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("pkpdlink")) {
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
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("irm")) {
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
									.getSelectedSheetIndex()).getIrInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("ascii")) {
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
									.getSelectedSheetIndex()).getAsciiInfo()
					.setProcessingInputs(procInputInst);
		}else if (analysisType.equals("invitro")) {
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
							.getSelectedSheetIndex()).getInVitroInfo()
			.setProcessingInputs(procInputInst);
}
	}

	void determineOptReqDecOfAnalysis() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if (analysisType.equals("pk")) {
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
					.getPkInfo()
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
									.getPkInfo().getProcessingInputs()
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
					.getPkInfo()
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
									.getPkInfo().getProcessingInputs()
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
					.getPkInfo()
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
									.getPkInfo().getProcessingInputs()
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
					.getPkInfo()
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
									.getPkInfo().getProcessingInputs()
									.getMappingDataObj()
									.getyColumnCorrespondinOriginalIndex())
					.getRequiredDecimals();

		} else if (analysisType.equals("pd")) {
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
					.getPdInfo()
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
									.getPdInfo().getProcessingInputs()
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
					.getPdInfo()
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
									.getPdInfo().getProcessingInputs()
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
					.getPdInfo()
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
									.getPdInfo().getProcessingInputs()
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
					.getPdInfo()
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
									.getPdInfo().getProcessingInputs()
									.getMappingDataObj()
									.getyColumnCorrespondinOriginalIndex())
					.getRequiredDecimals();

		} else if (analysisType.equals("mm")) {
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
					.getMmInfo()
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
									.getMmInfo().getProcessingInputs()
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
					.getMmInfo()
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
									.getMmInfo().getProcessingInputs()
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
					.getMmInfo()
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
									.getMmInfo().getProcessingInputs()
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
					.getMmInfo()
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
									.getMmInfo().getProcessingInputs()
									.getMappingDataObj()
									.getyColumnCorrespondinOriginalIndex())
					.getRequiredDecimals();

		} else if (analysisType.equals("irm")) {
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
					.getIrInfo()
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
									.getIrInfo().getProcessingInputs()
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
					.getIrInfo()
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
									.getIrInfo().getProcessingInputs()
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
					.getIrInfo()
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
									.getIrInfo().getProcessingInputs()
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
					.getIrInfo()
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
									.getIrInfo().getProcessingInputs()
									.getMappingDataObj()
									.getyColumnCorrespondinOriginalIndex())
					.getRequiredDecimals();

		} else if (analysisType.equals("pkpdlink")) {
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
					.getPkpdLinkInfo()
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
									.getPkpdLinkInfo().getProcessingInputs()
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
					.getPkpdLinkInfo()
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
									.getPkpdLinkInfo().getProcessingInputs()
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
					.getPkpdLinkInfo()
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
									.getPkpdLinkInfo().getProcessingInputs()
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
					.getPkpdLinkInfo()
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
									.getPkpdLinkInfo().getProcessingInputs()
									.getMappingDataObj()
									.getyColumnCorrespondinOriginalIndex())
					.getRequiredDecimals();

		} else if (analysisType.equals("ascii")) {
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
					.getAsciiInfo()
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
									.getAsciiInfo().getProcessingInputs()
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
					.getAsciiInfo()
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
									.getAsciiInfo().getProcessingInputs()
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
					.getAsciiInfo()
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
									.getAsciiInfo().getProcessingInputs()
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
					.getAsciiInfo()
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
									.getAsciiInfo().getProcessingInputs()
									.getMappingDataObj()
									.getyColumnCorrespondinOriginalIndex())
					.getRequiredDecimals();

		}else if (analysisType.equals("invitro")) {
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
			.getInVitroInfo()
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
							.getInVitroInfo().getProcessingInputs()
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
			.getInVitroInfo()
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
							.getInVitroInfo().getProcessingInputs()
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
			.getInVitroInfo()
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
							.getInVitroInfo().getProcessingInputs()
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
			.getInVitroInfo()
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
							.getInVitroInfo().getProcessingInputs()
							.getMappingDataObj()
							.getyColumnCorrespondinOriginalIndex())
			.getRequiredDecimals();

}

	}

	// Rounding up
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

	public String convertToScientificNumber(double value) {
		String str;

		str = value + "";
		String tempStr = "";
		if (str.contains("E")) {
			String[] split = str.split("E");
			str = split[0] + "";
			String[] split1 = str.split(".");
			char[] temp = str.toCharArray();

			tempStr = temp[0] + "" + temp[1] + "" + temp[2] + temp[3] + "E"
					+ split[1];
		} else {
			tempStr = formatting(value, false);
		}

		System.out.println();
		return tempStr;

	}

	public void read_info() {
	}

	public double func_diff(double[] par, double[] con, double x, int fn_no,
			double[][] Extra_DATA, int rowIdx, int[] row) {
		double[] fn = new double[nFun];
		return fn[fn_no];
	}

	public double weighting(double f, int fn_no) {
		double wt = 0;
		return wt;
	}

	public void read_initial() {
	}

}
