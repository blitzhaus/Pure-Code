package Model;

import java.util.ArrayList;

import view.ApplicationInfo;

public class ListOfDataStructures {

	double[] infusionTime;
	double[] dose;
	double[] secondDose;

	public double[] getSecondDose() {
		return secondDose;
	}

	public void setSecondDose(double[] secondDose) {
		this.secondDose = secondDose;
	}

	double[] dosingTime;
	int modelNumber;

	int normalizationUnitIndex;
	int dataType;
	int rootOfAdministration;
	int ifParameterValueIsSupplied;
	int ifBoundaryIsUsed;
	double[] calcYval;
	/*
	 * String timeUnit; String concMassUnit; String volumeUnit; String
	 * normalizationUnit; String doseUnit;
	 */
	String[] defaultUnitForCA;
	String[] preferredUnitForCA;
	double[] unitConversionAmountForCA;
	int noOfFunc;

	int valueForParameterBoundary;
	double[] infusionDose = new double[1];

	boolean ifForInitialValue = false;

	boolean ifFromASCIIModel = false;

	double[] initial;

	double corrObsAndPredY;
	double weightedCorrObsAndPredY;
	double[] propFinalEstimate;
	
	boolean ifPropFinalEstimate;
	int initialValEstimateMethod;
	
	double[][] concForLinkModel;
	double[] paramForLinkModel;

	public double[] getInfusionTime() {
		return infusionTime;
	}

	public void setInfusionTime(double[] infusionTime) {
		this.infusionTime = infusionTime;
	}

	public double[] getDose() {
		return dose;
	}

	public void setDose(double[] dose) {
		this.dose = dose;
	}

	public double[] getDosingTime() {
		return dosingTime;
	}

	public void setDosingTime(double[] dosingTime) {
		this.dosingTime = dosingTime;
	}

	public int getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}

	public double[] getInfusionDose() {
		return infusionDose;
	}

	public void setInfusionDose(double[] infusionDose) {
		this.infusionDose = infusionDose;
	}

	public double[] getInitial() {
		return initial;
	}

	public void setInitial(double[] initial) {
		this.initial = initial;
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

	ApplicationInfo appInfo;
	public int getIfBoundaryIsUsed() {
		return ifBoundaryIsUsed;
	}

	public void setIfBoundaryIsUsed(int ifBoundaryIsUsed) {
		this.ifBoundaryIsUsed = ifBoundaryIsUsed;
	}

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

	

	static ListOfDataStructures listOfDataStructInst = null;

	public static ListOfDataStructures createListOfDataStructInstance() {
		if (listOfDataStructInst == null) {
			listOfDataStructInst = new ListOfDataStructures();
		}
		return listOfDataStructInst;
	}

	public ListOfDataStructures() {
		infusionTime = new double[1];
		dose = new double[1];
		secondDose = new double[1];
		dosingTime = new double[1];
		modelNumber = 1;
		normalizationUnitIndex = 0;
		dataType = 0;
		rootOfAdministration = 0;
		ifParameterValueIsSupplied = 0;
		ifBoundaryIsUsed = 0;
		calcYval = new double[1];

		defaultUnitForCA = new String[1];
		preferredUnitForCA = new String[1];
		unitConversionAmountForCA = new double[1];

		valueForParameterBoundary = 0;
		infusionDose = new double[1];
		ifForInitialValue = false;
		ifFromASCIIModel = false;
		initial = new double[1];
		verificationOfInputs = new ArrayList<String[]>();
		corrObsAndPredY = 0;
		weightedCorrObsAndPredY = 0;

	}

	public ApplicationInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(ApplicationInfo appInfo) {
		this.appInfo = appInfo;
	}

	


	
	int[] subjectObsNos;
	int noOfSubjects;

	ArrayList<String[]> verificationOfInputs;

}
