package view;

import java.io.Serializable;

public class ModelInputTab1 implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8792963353677542878L;


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelInputTab1 other = (ModelInputTab1) obj;
		if (dataType != other.dataType)
			return false;
		if (doseUnit == null) {
			if (other.doseUnit != null)
				return false;
		} else if (!doseUnit.equals(other.doseUnit))
			return false;
		if (ifClearanceParam != other.ifClearanceParam)
			return false;
		if (ifLinkModel != other.ifLinkModel)
			return false;
		if (ifPrimaryModel != other.ifPrimaryModel)
			return false;
		if (isAlgebraicModel != other.isAlgebraicModel)
			return false;
		if (isDifferentialEquationModel != other.isDifferentialEquationModel)
			return false;
		if (isSimulation != other.isSimulation)
			return false;
		if (modelNumberForCA != other.modelNumberForCA)
			return false;
		if (modelNumberForLinkModel != other.modelNumberForLinkModel)
			return false;
		if (normalizationIndex != other.normalizationIndex)
			return false;
		if (numberOfDose != other.numberOfDose)
			return false;
		if (responseUnit == null) {
			if (other.responseUnit != null)
				return false;
		} else if (!responseUnit.equals(other.responseUnit))
			return false;
		if (weightingIndex != other.weightingIndex)
			return false;
		if (weightingScheme != other.weightingScheme)
			return false;
		return true;
	}

	private int modelNumberForCA;
	private int modelNumberForLinkModel;
	private boolean isAlgebraicModel;
	private boolean isSimulation;
	private boolean ifPrimaryModel;
	private boolean ifLinkModel;
	private String responseUnit; 
	private String pkUnit;
	private String simulationUnit;
	
	

	

	public String getSimulationUnit() {
		return simulationUnit;
	}

	public void setSimulationUnit(String simulationUnit) {
		this.simulationUnit = simulationUnit;
	}

	public String getPkUnit() {
		return pkUnit;
	}

	public void setPkUnit(String pkUnit) {
		this.pkUnit = pkUnit;
	}

	public String getResponseUnit() {
		return responseUnit;
	}

	public void setResponseUnit(String responseUnit) {
		this.responseUnit = responseUnit;
	}

	public boolean isIfPrimaryModel() {
		return ifPrimaryModel;
	}

	public void setIfPrimaryModel(boolean ifPrimaryModel) {
		this.ifPrimaryModel = ifPrimaryModel;
	}

	public boolean isIfLinkModel() {
		return ifLinkModel;
	}

	public void setIfLinkModel(boolean ifLinkModel) {
		this.ifLinkModel = ifLinkModel;
	}

	
	public ModelInputTab1() {
		modelNumberForCA = 0;
		modelNumberForLinkModel = 0;
		isAlgebraicModel = false;
		isDifferentialEquationModel = false;
		weightingIndex = 0;
		weightingScheme = 0;
		dataType = 0;
		doseUnit = null;
		normalizationIndex = 0;
		numberOfDose = 1;
		ifClearanceParam = false;
		isSimulation = false;
		ifPrimaryModel = false;
		ifLinkModel = false;
		responseUnit = "";
		simulationUnit = "";
			
	}

	public int getModelNumberForLinkModel() {
		return modelNumberForLinkModel;
	}

	public void setModelNumberForLinkModel(int modelNumberForLinkModel) {
		this.modelNumberForLinkModel = modelNumberForLinkModel;
	}

	public boolean isSimulation() {
		return isSimulation;
	}

	public void setSimulation(boolean isSimulation) {
		this.isSimulation = isSimulation;
	}

	public boolean isIfClearanceParam() {
		return ifClearanceParam;
	}

	public void setIfClearanceParam(boolean ifClearanceParam) {
		this.ifClearanceParam = ifClearanceParam;
	}

	private boolean isDifferentialEquationModel;

	public boolean isDifferentialEquationModel() {
		return isDifferentialEquationModel;
	}

	public void setDifferentialEquationModel(boolean isDifferentialEquationModel) {
		this.isDifferentialEquationModel = isDifferentialEquationModel;
	}

	private int weightingIndex;
	private int weightingScheme;
	private int dataType;
	private String doseUnit;
	private int normalizationIndex;
	private int numberOfDose;
	private boolean ifClearanceParam;

	public int getModelNumberForCA() {
		return modelNumberForCA;
	}

	public void setModelNumberForCA(int modelNumberForCA) {
		this.modelNumberForCA = modelNumberForCA;
	}

	public boolean isAlgebraicModel() {
		return isAlgebraicModel;
	}

	public void setAlgebraicModel(boolean isAlgebraicModel) {
		this.isAlgebraicModel = isAlgebraicModel;
	}

	public boolean getAlgebraicModel() {
		return isAlgebraicModel;
	}

	public int getWeightingIndex() {
		return weightingIndex;
	}

	public void setWeightingIndex(int weightingIndex) {
		this.weightingIndex = weightingIndex;
	}

	public int getWeightingScheme() {
		return weightingScheme;
	}

	public void setWeightingScheme(int weightingScheme) {
		this.weightingScheme = weightingScheme;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getDoseUnit() {
		return doseUnit;
	}

	public void setDoseUnit(String doseUnit) {
		this.doseUnit = doseUnit;
	}

	public int getNormalizationIndex() {
		return normalizationIndex;
	}

	public void setNormalizationIndex(int normalizationIndex) {
		this.normalizationIndex = normalizationIndex;
	}

	@Override
	public String toString() {
		return "\n ModelInputTab1 [modelNumberForCA=" + modelNumberForCA
				+ ", isAlgebraicModel=" + isAlgebraicModel
				+ ", weightingIndex=" + weightingIndex + ", weightingScheme="
				+ weightingScheme + ", dataType=" + dataType + ", doseUnit="
				+ doseUnit + ", normalizationIndex=" + normalizationIndex
				+ ", numberOfDose=" + numberOfDose + "]\n";
	}

	public int getNumberOfDose() {
		return numberOfDose;
	}

	public void setNumberOfDose(int numberOfDose) {
		this.numberOfDose = numberOfDose;
	}

}
