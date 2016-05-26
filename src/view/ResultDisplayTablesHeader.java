package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ResultDisplayTablesHeader implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4112328865847609003L;
	String[] verticalParametersTH;
	String[] horizontalParametersTH;
	String[] initialParameterTH;
	String[] minimizationProcessTH;
	String[] dosingValueTH;
	String[] correlationMatrixTH;
	String[] eigenValuesTH;
	String[] conditionNumberTH;
	String[] predictedValueTH;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultDisplayTablesHeader other = (ResultDisplayTablesHeader) obj;
		if (!Arrays.equals(conditionNumberTH, other.conditionNumberTH))
			return false;
		if (!Arrays.equals(correlationMatrixTH, other.correlationMatrixTH))
			return false;
		if (!Arrays.equals(diagnosticsTH, other.diagnosticsTH))
			return false;
		if (!Arrays.equals(dosingValueTH, other.dosingValueTH))
			return false;
		if (!Arrays.equals(eigenValuesTH, other.eigenValuesTH))
			return false;
		if (!Arrays.equals(historyTH, other.historyTH))
			return false;
		if (!Arrays
				.equals(horizontalParametersTH, other.horizontalParametersTH))
			return false;
		if (!Arrays.equals(initialParameterTH, other.initialParameterTH))
			return false;
		if (!Arrays.equals(minimizationProcessTH, other.minimizationProcessTH))
			return false;
		if (!Arrays.equals(nonTransposedSPTH, other.nonTransposedSPTH))
			return false;
		if (!Arrays.equals(partialDerivativeTH, other.partialDerivativeTH))
			return false;
		if (!Arrays.equals(predictedValueTH, other.predictedValueTH))
			return false;
		if (!Arrays.equals(secondaryParametersTH, other.secondaryParametersTH))
			return false;
		if (!Arrays.equals(summaryTableTH, other.summaryTableTH))
			return false;
		if (!Arrays.equals(userSettingsTH, other.userSettingsTH))
			return false;
		if (!Arrays.equals(varienceCovarienceMatrixTH,
				other.varienceCovarienceMatrixTH))
			return false;
		if (!Arrays.equals(verticalParametersTH, other.verticalParametersTH))
			return false;
		return true;
	}
	String[] varienceCovarienceMatrixTH;
	String[] summaryTableTH;
	String[] diagnosticsTH;
	String[] partialDerivativeTH;
	String[] secondaryParametersTH;
	String[] nonTransposedSPTH;
	String[] userSettingsTH;
	String[] historyTH;
	public ResultDisplayTablesHeader() {
		
		verticalParametersTH = new String[1];
		horizontalParametersTH = new String[1];
		initialParameterTH = new String[1];
		minimizationProcessTH = new String[1];
		dosingValueTH = new String[1];
		correlationMatrixTH = new String[1];
		eigenValuesTH = new String[1];
		conditionNumberTH = new String[1];
		predictedValueTH = new String[1];
		varienceCovarienceMatrixTH = new String[1];
		summaryTableTH = new String[1];
		diagnosticsTH = new String[1];
		partialDerivativeTH = new String[1];
		secondaryParametersTH = new String[1];
		nonTransposedSPTH = new String[1];
		userSettingsTH = new String[1];
		historyTH = new String[1];
	}
	public String[] getVerticalParametersTH() {
		return verticalParametersTH;
	}
	public void setVerticalParametersTH(String[] verticalParametersTH) {
		this.verticalParametersTH = verticalParametersTH;
	}
	public String[] getHorizontalParametersTH() {
		return horizontalParametersTH;
	}
	public void setHorizontalParametersTH(String[] horizontalParametersTH) {
		this.horizontalParametersTH = horizontalParametersTH;
	}
	public String[] getInitialParameterTH() {
		return initialParameterTH;
	}
	public void setInitialParameterTH(String[] initialParameterTH) {
		this.initialParameterTH = initialParameterTH;
	}
	public String[] getMinimizationProcessTH() {
		return minimizationProcessTH;
	}
	public void setMinimizationProcessTH(String[] minimizationProcessTH) {
		this.minimizationProcessTH = minimizationProcessTH;
	}
	public String[] getDosingValueTH() {
		return dosingValueTH;
	}
	public void setDosingValueTH(String[] dosingValueTH) {
		this.dosingValueTH = dosingValueTH;
	}
	public String[] getCorrelationMatrixTH() {
		return correlationMatrixTH;
	}
	public void setCorrelationMatrixTH(String[] correlationMatrixTH) {
		this.correlationMatrixTH = correlationMatrixTH;
	}
	public String[] getEigenValuesTH() {
		return eigenValuesTH;
	}
	public void setEigenValuesTH(String[] eigenValuesTH) {
		this.eigenValuesTH = eigenValuesTH;
	}
	public String[] getConditionNumberTH() {
		return conditionNumberTH;
	}
	public void setConditionNumberTH(String[] conditionNumberTH) {
		this.conditionNumberTH = conditionNumberTH;
	}
	public String[] getPredictedValueTH() {
		return predictedValueTH;
	}
	public void setPredictedValueTH(String[] predictedValueTH) {
		this.predictedValueTH = predictedValueTH;
	}
	public String[] getVarienceCovarienceMatrixTH() {
		return varienceCovarienceMatrixTH;
	}
	public void setVarienceCovarienceMatrixTH(String[] varienceCovarienceMatrixTH) {
		this.varienceCovarienceMatrixTH = varienceCovarienceMatrixTH;
	}
	public String[] getSummaryTableTH() {
		return summaryTableTH;
	}
	public void setSummaryTableTH(String[] summaryTableTH) {
		this.summaryTableTH = summaryTableTH;
	}
	public String[] getDiagnosticsTH() {
		return diagnosticsTH;
	}
	public void setDiagnosticsTH(String[] diagnosticsTH) {
		this.diagnosticsTH = diagnosticsTH;
	}
	public String[] getPartialDerivativeTH() {
		return partialDerivativeTH;
	}
	public void setPartialDerivativeTH(String[] partialDerivativeTH) {
		this.partialDerivativeTH = partialDerivativeTH;
	}
	public String[] getSecondaryParametersTH() {
		return secondaryParametersTH;
	}
	public void setSecondaryParametersTH(String[] secondaryParametersTH) {
		this.secondaryParametersTH = secondaryParametersTH;
	}
	public String[] getNonTransposedSPTH() {
		return nonTransposedSPTH;
	}
	public void setNonTransposedSPTH(String[] nonTransposedSPTH) {
		this.nonTransposedSPTH = nonTransposedSPTH;
	}
	public String[] getUserSettingsTH() {
		return userSettingsTH;
	}
	public void setUserSettingsTH(String[] userSettingsTH) {
		this.userSettingsTH = userSettingsTH;
	}
	public String[] getHistoryTH() {
		return historyTH;
	}
	public void setHistoryTH(String[] historyTH) {
		this.historyTH = historyTH;
	}
	

}
