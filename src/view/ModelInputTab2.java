package view;

import java.io.Serializable;

public class ModelInputTab2 implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1879450268484475394L;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelInputTab2 other = (ModelInputTab2) obj;
		if (convergenceCriteria == null) {
			if (other.convergenceCriteria != null)
				return false;
		} else if (!convergenceCriteria.equals(other.convergenceCriteria))
			return false;
		if (incForPartialDerivative == null) {
			if (other.incForPartialDerivative != null)
				return false;
		} else if (!incForPartialDerivative
				.equals(other.incForPartialDerivative))
			return false;
		if (initialParamBYGA != other.initialParamBYGA)
			return false;
		if (initialParamBYGS != other.initialParamBYGS)
			return false;
		if (initialParamByCS != other.initialParamByCS)
			return false;
		if (lambdaValue == null) {
			if (other.lambdaValue != null)
				return false;
		} else if (!lambdaValue.equals(other.lambdaValue))
			return false;
		if (meanSquareValue == null) {
			if (other.meanSquareValue != null)
				return false;
		} else if (!meanSquareValue.equals(other.meanSquareValue))
			return false;
		if (minimizationMethod != other.minimizationMethod)
			return false;
		if (muValue == null) {
			if (other.muValue != null)
				return false;
		} else if (!muValue.equals(other.muValue))
			return false;
		if (numberOfIterations == null) {
			if (other.numberOfIterations != null)
				return false;
		} else if (!numberOfIterations.equals(other.numberOfIterations))
			return false;
		if (numberOfPredictedValue == null) {
			if (other.numberOfPredictedValue != null)
				return false;
		} else if (!numberOfPredictedValue.equals(other.numberOfPredictedValue))
			return false;
		if (odeSolverMethod != other.odeSolverMethod)
			return false;
		if (paramBoundarySelection != other.paramBoundarySelection)
			return false;
		if (previouslyGenInitVal != other.previouslyGenInitVal)
			return false;
		if (propagateFinalEstimae != other.propagateFinalEstimae)
			return false;
		if (roundingOffValue == null) {
			if (other.roundingOffValue != null)
				return false;
		} else if (!roundingOffValue.equals(other.roundingOffValue))
			return false;
		if (sourceOfIntParamValue != other.sourceOfIntParamValue)
			return false;
		return true;
	}

	public ModelInputTab2() {
		sourceOfIntParamValue = 0;
		paramBoundarySelection = 0;
		minimizationMethod = 0;
		odeSolverMethod = 0;
		initialParamByCS = 0;
		initialParamBYGA = 0;
		initialParamBYGS = 0;
		initialParamBYNCA = 0;
		previouslyGenInitVal = 0;
		incForPartialDerivative = "0.0001";
		numberOfPredictedValue = "100";
		convergenceCriteria = "0.000001";
		numberOfIterations = "100";
		meanSquareValue = "0.0";
		lambdaValue = "0.01";
		muValue = ".2";
		roundingOffValue = "4";
		propagateFinalEstimae = false;
	}

	public int getInitialParamBYGS() {
		return initialParamBYGS;
	}

	public void setInitialParamBYGS(int initialParamBYGS) {
		this.initialParamBYGS = initialParamBYGS;
	}

	public boolean isPropagateFinalEstimae() {
		return propagateFinalEstimae;
	}

	public void setPropagateFinalEstimae(boolean propagateFinalEstimae) {
		this.propagateFinalEstimae = propagateFinalEstimae;
	}

	public int getPreviouslyGenInitVal() {
		return previouslyGenInitVal;
	}

	public void setPreviouslyGenInitVal(int previouslyGenInitVal) {
		this.previouslyGenInitVal = previouslyGenInitVal;
	}

	@Override
	public String toString() {
		return "ModelInputTab2 [convergenceCriteria=" + convergenceCriteria
				+ ", incForPartialDerivative=" + incForPartialDerivative
				+ ", initialParamBYGA=" + initialParamBYGA
				+ ", initialParamByCS=" + initialParamByCS + ", lambdaValue="
				+ lambdaValue + ", meanSquareValue=" + meanSquareValue
				+ ", minimizationMethod=" + minimizationMethod + ", muValue="
				+ muValue + ", numberOfIterations=" + numberOfIterations
				+ ", numberOfPredictedValue=" + numberOfPredictedValue
				+ ", odeSolverMethod=" + odeSolverMethod
				+ ", paramBoundarySelection=" + paramBoundarySelection
				+ ", roundingOffValue=" + roundingOffValue
				+ ", sourceOfIntParamValue=" + sourceOfIntParamValue + "]";
	}

	private int sourceOfIntParamValue;
	private int paramBoundarySelection;
	private int minimizationMethod;
	private int odeSolverMethod;
	private int initialParamByCS;
	private int initialParamBYGA;
	private int initialParamBYGS;
	private int initialParamBYNCA;
	
	public int getInitialParamBYNCA() {
		return initialParamBYNCA;
	}

	public void setInitialParamBYNCA(int initialParamBYNCA) {
		this.initialParamBYNCA = initialParamBYNCA;
	}

	private int previouslyGenInitVal;
	private boolean propagateFinalEstimae;

	public int getInitialParamByCS() {
		return initialParamByCS;
	}

	public void setInitialParamByCS(int initialParamByCS) {
		this.initialParamByCS = initialParamByCS;
	//	ApplicationInfo.createEventCoordinatorInst(DisplayContents.createAppInfoInstance().listOfObservers).sendNotifsOfWeightDosePanel();
	}

	public int getInitialParamBYGA() {
		return initialParamBYGA;
	}

	public void setInitialParamBYGA(int initialParamBYGA) {
		this.initialParamBYGA = initialParamBYGA;
	}

	private String incForPartialDerivative;
	private String numberOfPredictedValue;
	private String convergenceCriteria;
	private String numberOfIterations;
	private String meanSquareValue;

	private String lambdaValue;
	private String muValue;
	private String roundingOffValue;

	public int getSourceOfIntParamValue() {
		return sourceOfIntParamValue;
	}

	public void setSourceOfIntParamValue(int sourceOfIntParamValue) {
		this.sourceOfIntParamValue = sourceOfIntParamValue;
	}

	public int getParamBoundarySelection() {
		return paramBoundarySelection;
	}

	public void setParamBoundarySelection(int paramBoundarySelection) {
		this.paramBoundarySelection = paramBoundarySelection;
	}

	public int getMinimizationMethod() {
		return minimizationMethod;
	}

	public void setMinimizationMethod(int minimizationMethod) {
		this.minimizationMethod = minimizationMethod;
	}

	public int getOdeSolverMethod() {
		return odeSolverMethod;
	}

	public void setOdeSolverMethod(int odeSolverMethod) {
		this.odeSolverMethod = odeSolverMethod;
	}

	public String getIncForPartialDerivative() {
		return incForPartialDerivative;
	}

	public void setIncForPartialDerivative(String incForPartialDerivative) {
		this.incForPartialDerivative = incForPartialDerivative;
	}

	public String getNumberOfPredictedValue() {
		return numberOfPredictedValue;
	}

	public void setNumberOfPredictedValue(String numberOfPredictedValue) {
		this.numberOfPredictedValue = numberOfPredictedValue;
	}

	public String getConvergenceCriteria() {
		return convergenceCriteria;
	}

	public void setConvergenceCriteria(String convergenceCriteria) {
		this.convergenceCriteria = convergenceCriteria;
	}

	public String getNumberOfIterations() {
		return numberOfIterations;
	}

	public void setNumberOfIterations(String numberOfIterations) {
		this.numberOfIterations = numberOfIterations;
	}

	public String getMeanSquareValue() {
		return meanSquareValue;
	}

	public void setMeanSquareValue(String meanSquareValue) {
		this.meanSquareValue = meanSquareValue;
	}

	public String getLambdaValue() {
		return lambdaValue;
	}

	public void setLambdaValue(String lambdaValue) {
		this.lambdaValue = lambdaValue;
	}

	public String getMuValue() {
		return muValue;
	}

	public void setMuValue(String muValue) {
		this.muValue = muValue;
	}

	public String getRoundingOffValue() {
		return roundingOffValue;
	}

	public void setRoundingOffValue(String roundingOffValue) {
		this.roundingOffValue = roundingOffValue;
	}

}
