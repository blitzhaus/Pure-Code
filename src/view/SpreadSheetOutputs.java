package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


import view.ApplicationInfo;


public class SpreadSheetOutputs implements Serializable{

	private static final long serialVersionUID = 6717782629296888681L;
	private String[][] summaryTableData;
	private String[][] verticalParameters;
	private String[][] horizontalParameters;
	private String[][] verticalParametersForCA;
	private String[][] horizontalParametersForCA;
	private String[][] initialParameterForCA;
	private String[][] minimizationProcessForCA;
	private String[][] dosingValueForCA;
	private String[][] correlationMatrixForCA;
	private String[][] eigenValuesForCA;
	private String[][] conditionNumberForCA;
	private String[][] predictedValueForCA;
	private String[][] varienceCovarienceMatrixForCA;
	private String[][] summaryTableForCA;
	private String[][] diagnosticsForCA;
	private String[][] partialDerivativeForCA;
	private String[][] secondaryParametersForCA;
	private String[][] nonTransposedSPForCA;
	private String[][] userSettingsForCA;
	private String[][] historyForCA;

	
	
	public String[][] getHorizontalParameters() {
		return horizontalParameters;
	}

	public void setHorizontalParameters(String[][] horizontalParameters) {
		this.horizontalParameters = horizontalParameters;
	}

	public void storeHorizontalParametersTable(JTable horizontalParametersTable){
	
		String[][] data = storeTableDataToStructure(horizontalParametersTable);
		
		//setting the data[][] to the horizontalPrameters[][]
		setHorizontalParameters(data);
	}
	
	public String[][] getVerticalParameters() {
		return verticalParameters;
	}

	public void setVerticalParameters(String[][] verticalParameters) {
		this.verticalParameters = verticalParameters;
	}
	
	public void storeVerticalParameters(JTable verticalParametersTable){
		
		String[][] data = storeTableDataToStructure(verticalParametersTable);
		
		//set the data[][] to the verticalParameters[][]
		setVerticalParameters(data);
	}

	@Override
	public String toString() {
		String consoleS = "\tPrinting spread sheet outputs\n";
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String s  = "Output details stored \n\n";
		 s = s + "horizontal parameters table \n";
		 for(int i=0;i<horizontalParameters.length;i++){
			 for(int j=0;j<horizontalParameters[i].length;j++){
				 s = s + horizontalParameters[i][j]+", ";
			 }
			 s = s + "\n";
		 }
		s = s + "\n\n veritacal parameters\n";
		
		 for(int i=0;i<verticalParameters.length;i++){
			 for(int j=0;j<verticalParameters[i].length;j++){
				 s = s + verticalParameters[i][j]+", ";
			 }
			 s = s + "\n";
		 }
		 
		 s = s + "\n\n summary table\n";
		 for(int i=0;i<summaryTableData.length;i++){
			 for(int j=0;j<summaryTableData[i].length;j++){
				 s = s + summaryTableData[i][j]+", ";
			 }
			 s = s + "\n";
		 }
		 
	
		//p.append(s);
		return s;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpreadSheetOutputs other = (SpreadSheetOutputs) obj;
		if (!Arrays.deepEquals(conditionNumberForCA, other.conditionNumberForCA))
			return false;
		if (!Arrays
				.deepEquals(correlationMatrixForCA, other.correlationMatrixForCA))
			return false;
		if (!Arrays.deepEquals(diagnosticsForCA, other.diagnosticsForCA))
			return false;
		if (!Arrays.deepEquals(dosingValueForCA, other.dosingValueForCA))
			return false;
		if (!Arrays.deepEquals(eigenValuesForCA, other.eigenValuesForCA))
			return false;
		if (!Arrays.deepEquals(historyForCA, other.historyForCA))
			return false;
		if (!Arrays.deepEquals(horizontalParameters, other.horizontalParameters))
			return false;
		if (!Arrays.deepEquals(horizontalParametersForCA,
				other.horizontalParametersForCA))
			return false;
		if (!Arrays.deepEquals(initialParameterForCA, other.initialParameterForCA))
			return false;
		if (!Arrays.deepEquals(minimizationProcessForCA,
				other.minimizationProcessForCA))
			return false;
		if (!Arrays.deepEquals(nonTransposedSPForCA, other.nonTransposedSPForCA))
			return false;
		if (!Arrays
				.deepEquals(partialDerivativeForCA, other.partialDerivativeForCA))
			return false;
		if (!Arrays.deepEquals(predictedValueForCA, other.predictedValueForCA))
			return false;
		if (!Arrays.deepEquals(secondaryParametersForCA,
				other.secondaryParametersForCA))
			return false;
		if (!Arrays.deepEquals(summaryTableData, other.summaryTableData))
			return false;
		if (!Arrays.deepEquals(summaryTableForCA, other.summaryTableForCA))
			return false;
		if (!Arrays.deepEquals(userSettingsForCA, other.userSettingsForCA))
			return false;
		if (!Arrays.deepEquals(varienceCovarienceMatrixForCA,
				other.varienceCovarienceMatrixForCA))
			return false;
		if (!Arrays.deepEquals(verticalParameters, other.verticalParameters))
			return false;
		if (!Arrays.deepEquals(verticalParametersForCA,
				other.verticalParametersForCA))
			return false;
		return true;
	}

	public String[][] getSummaryTableData() {
		return summaryTableData;
	}

	public void setSummaryTableData(String[][] summaryTableData) {
		this.summaryTableData = summaryTableData;
	}
	
	public SpreadSheetOutputs(){
		summaryTableData = new String[1][1];
		verticalParameters = new String[1][1];
		horizontalParameters = new String[1][1];
		verticalParametersForCA = new String[1][1];
		horizontalParametersForCA = new String[1][1];
		initialParameterForCA = new String[1][1];
		minimizationProcessForCA = new String[1][1];
		dosingValueForCA = new String[1][1];
		correlationMatrixForCA = new String[1][1];
		eigenValuesForCA = new String[1][1];
		conditionNumberForCA = new String[1][1];
		predictedValueForCA = new String[1][1];
		varienceCovarienceMatrixForCA = new String[1][1];
		summaryTableForCA = new String[1][1];
		diagnosticsForCA = new String[1][1];
		partialDerivativeForCA = new String[1][1];
		secondaryParametersForCA = new String[1][1];
		nonTransposedSPForCA = new String[1][1];
		userSettingsForCA = new String[1][1];
		historyForCA = new String[1][1];
		
	}
	
	public String getsummaryTableValueAt(int xIndex, int yIndex){
		
		return summaryTableData[xIndex][yIndex];
	}
	
	
	public String[][] getVerticalParametersForCA() {
		return verticalParametersForCA;
	}

	public void setVerticalParametersForCA(String[][] verticalParametersForCA) {
		this.verticalParametersForCA = verticalParametersForCA;
	}

	public String[][] getHorizontalParametersForCA() {
		return horizontalParametersForCA;
	}

	public void setHorizontalParametersForCA(
			String[][] horizontalParametersForCA) {
		this.horizontalParametersForCA = horizontalParametersForCA;
	}

	public String[][] getInitialParameterForCA() {
		return initialParameterForCA;
	}

	public void setInitialParameterForCA(String[][] initialParameterForCA) {
		this.initialParameterForCA = initialParameterForCA;
	}

	public String[][] getMinimizationProcessForCA() {
		return minimizationProcessForCA;
	}

	public void setMinimizationProcessForCA(String[][] minimizationProcessForCA) {
		this.minimizationProcessForCA = minimizationProcessForCA;
	}

	public String[][] getDosingValueForCA() {
		return dosingValueForCA;
	}

	public void setDosingValueForCA(String[][] dosingValueForCA) {
		this.dosingValueForCA = dosingValueForCA;
	}

	public String[][] getCorrelationMatrixForCA() {
		return correlationMatrixForCA;
	}

	public void setCorrelationMatrixForCA(String[][] correlationMatrixForCA) {
		this.correlationMatrixForCA = correlationMatrixForCA;
	}

	public String[][] getEigenValuesForCA() {
		return eigenValuesForCA;
	}

	public void setEigenValuesForCA(String[][] eigenValuesForCA) {
		this.eigenValuesForCA = eigenValuesForCA;
	}

	public String[][] getConditionNumberForCA() {
		return conditionNumberForCA;
	}

	public void setConditionNumberForCA(String[][] conditionNumberForCA) {
		this.conditionNumberForCA = conditionNumberForCA;
	}

	public String[][] getPredictedValueForCA() {
		return predictedValueForCA;
	}

	public void setPredictedValueForCA(String[][] predictedValueForCA) {
		this.predictedValueForCA = predictedValueForCA;
	}

	public String[][] getVarienceCovarienceMatrixForCA() {
		return varienceCovarienceMatrixForCA;
	}

	public void setVarienceCovarienceMatrixForCA(
			String[][] varienceCovarienceMatrixForCA) {
		this.varienceCovarienceMatrixForCA = varienceCovarienceMatrixForCA;
	}

	public String[][] getSummaryTableForCA() {
		return summaryTableForCA;
	}

	public void setSummaryTableForCA(String[][] summaryTableForCA) {
		this.summaryTableForCA = summaryTableForCA;
	}

	public String[][] getDiagnosticsForCA() {
		return diagnosticsForCA;
	}

	public void setDiagnosticsForCA(String[][] diagnosticsForCA) {
		this.diagnosticsForCA = diagnosticsForCA;
	}

	public String[][] getPartialDerivativeForCA() {
		return partialDerivativeForCA;
	}

	public void setPartialDerivativeForCA(String[][] partialDerivativeForCA) {
		this.partialDerivativeForCA = partialDerivativeForCA;
	}

	public String[][] getSecondaryParametersForCA() {
		return secondaryParametersForCA;
	}

	public void setSecondaryParametersForCA(String[][] secondaryParametersForCA) {
		this.secondaryParametersForCA = secondaryParametersForCA;
	}

	public String[][] getNonTransposedSPForCA() {
		return nonTransposedSPForCA;
	}

	public void setNonTransposedSPForCA(String[][] nonTransposedSPForCA) {
		this.nonTransposedSPForCA = nonTransposedSPForCA;
	}

	public String[][] getUserSettingsForCA() {
		return userSettingsForCA;
	}

	public void setUserSettingsForCA(String[][] userSettingsForCA) {
		this.userSettingsForCA = userSettingsForCA;
	}

	public String[][] getHistoryForCA() {
		return historyForCA;
	}

	public void setHistoryForCA(String[][] historyForCA) {
		this.historyForCA = historyForCA;
	}
	
	public void setSummaryTableValueAt(int xIndex, int yIndex, String value){
		summaryTableData[xIndex][yIndex] = value;
	}
	
	public void storeSummaryTable(JTable summaryTable){
	String[][] data	= storeTableDataToStructure(summaryTable);
		
		//set the data[][] to the summaryTableData[][]
		setSummaryTableData(data);
	}

	private String[][] storeTableDataToStructure(JTable summaryTable) {
		//+1 is for storing the header of the vertical parameters table
		String[][] data = new String[summaryTable.getRowCount()+1][summaryTable.getColumnCount()];
		TableColumnModel tm = summaryTable.getColumnModel();
		for(int i=0;i<summaryTable.getColumnCount();i++){
			TableColumn tc = tm.getColumn(i);
			data[0][i] = (String)tc.getHeaderValue();
			
		}
		
		for(int i=1;i<data.length;i++){
			for(int j=0;j<data[i].length;j++){
				data[i][j] = (String)summaryTable.getValueAt(i-1, j);
			}
		}
		return data;
	}
	


}
