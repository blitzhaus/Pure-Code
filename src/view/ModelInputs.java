package view;

import java.io.Serializable;

public class ModelInputs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8011340622685960472L;

	@Override
	public String toString() {
		return "\n\t//ModelInputs" +"\n"+
				" \t\tcalculationItem = " + calculationItem+"\n"
				+ "\t\tcalculationMethod = " + calculationMethod+"\n"
				+ "\t\tclearExclusion = " +clearExclusion + "\n"+"\t\tclearSelection = "
				+ clearSelection + "\n"+"\t\tdisableCurveStripping = "
				+ disableCurveStripping +"\n"+ "\t\texcluseProfiles = "
				+ excluseProfiles + "\n"+"\t\tisIntermediateoutputSelected = "
				+ isIntermediateoutputSelected + "\n"+"\t\tisPageBreakSelected = "
				+ isPageBreakSelected + "\n"+"\t\tisSparseSelected = "
				+ isSparseSelected +"\n" +"\t\tmodelItem = " + modelItem
				+ "\n\t\tmodelType = " + modelType+"\n" + "\t\tnormalizationUnit = "
				+ normalizationUnit +"\n"+ "\t\trootAdministrationItem = "
				+ rootAdministrationItem + "\n"+"\t\trootOfAdmistration = "
				+ rootOfAdmistration + "\n"+"\t\ttitle = " + title
				+ "\n\t\tweightingOption = " + weightingOption + "\n";
	}
	private int modelType;
	private String modelItem;
	private int weightingOption;
	private int calculationMethod;
	private String calculationItem;
	private int rootOfAdmistration;
	private String title;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelInputs other = (ModelInputs) obj;
		if (Ke0Value == null) {
			if (other.Ke0Value != null)
				return false;
		} else if (!Ke0Value.equals(other.Ke0Value))
			return false;
		if (calculationItem == null) {
			if (other.calculationItem != null)
				return false;
		} else if (!calculationItem.equals(other.calculationItem))
			return false;
		if (calculationMethod != other.calculationMethod)
			return false;
		if (clearExclusion != other.clearExclusion)
			return false;
		if (clearSelection != other.clearSelection)
			return false;
		if (disableCurveStripping != other.disableCurveStripping)
			return false;
		if (doseUnits == null) {
			if (other.doseUnits != null)
				return false;
		} else if (!doseUnits.equals(other.doseUnits))
			return false;
		if (dosingDisplayUnits == null) {
			if (other.dosingDisplayUnits != null)
				return false;
		} else if (!dosingDisplayUnits.equals(other.dosingDisplayUnits))
			return false;
		if (excluseProfiles != other.excluseProfiles)
			return false;
		if (isIntermediateoutputSelected != other.isIntermediateoutputSelected)
			return false;
		if (isPageBreakSelected != other.isPageBreakSelected)
			return false;
		if (isSparseSelected != other.isSparseSelected)
			return false;
		if (methodNumberForSCA != other.methodNumberForSCA)
			return false;
		if (modelItem == null) {
			if (other.modelItem != null)
				return false;
		} else if (!modelItem.equals(other.modelItem))
			return false;
		if (modelType != other.modelType)
			return false;
		if (normalizationUnit != other.normalizationUnit)
			return false;
		if (rootAdministrationItem == null) {
			if (other.rootAdministrationItem != null)
				return false;
		} else if (!rootAdministrationItem.equals(other.rootAdministrationItem))
			return false;
		if (rootOfAdmistration != other.rootOfAdmistration)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (weightingOption != other.weightingOption)
			return false;
		return true;
	}
	private boolean isPageBreakSelected;
	private boolean isIntermediateoutputSelected;
	private boolean excluseProfiles;
	private boolean disableCurveStripping;
	private boolean clearExclusion;
	private boolean clearSelection;
	private String doseUnits;
	
	
	
	private String Ke0Value;
	private int methodNumberForSCA;
	
	public String getKe0Value() {
		return Ke0Value;
	}

	public void setKe0Value(String ke0Value) {
		Ke0Value = ke0Value;
	}

	public int getMethodNumberForSCA() {
		return methodNumberForSCA;
	}

	public void setMethodNumberForSCA(int methodNumberForSCA) {
		this.methodNumberForSCA = methodNumberForSCA;
	}
	public String getDoseUnits() {
		return doseUnits;
	}

	public void setDoseUnits(String doseUnits) {
		this.doseUnits = doseUnits;
	}

	public boolean isClearExclusion() {
		return clearExclusion;
	}

	public void setClearExclusion(boolean clearExclusion) {
		this.clearExclusion = clearExclusion;
	}

	public boolean isClearSelection() {
		return clearSelection;
	}

	public void setClearSelection(boolean clearSelection) {
		this.clearSelection = clearSelection;
	}

	public boolean isDisableCurveStripping() {
		return disableCurveStripping;
	}

	public void setDisableCurveStripping(boolean disableCurveStripping) {
		this.disableCurveStripping = disableCurveStripping;
	}

	public boolean isExcluseProfiles() {
		return excluseProfiles;
	}

	public void setExcluseProfiles(boolean excluseProfiles) {
		this.excluseProfiles = excluseProfiles;
	}

	public boolean isIntermediateoutputSelected() {
		return isIntermediateoutputSelected;
	}

	public void setIntermediateoutputSelected(boolean isIntermediateoutputSelected) {
		this.isIntermediateoutputSelected = isIntermediateoutputSelected;
	}

	public boolean isPageBreakSelected() {
		return isPageBreakSelected;
	}

	public void setPageBreakSelected(boolean isPageBreakSelected) {
		this.isPageBreakSelected = isPageBreakSelected;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	private String rootAdministrationItem;
	private int normalizationUnit;
	private boolean isSparseSelected;
	private String dosingDisplayUnits;
	
	public boolean getisSparseSelected() {
		return isSparseSelected;
	}

	public void setisSparseSelected(boolean isSparseSelected) {
		this.isSparseSelected = isSparseSelected;
	}


	public String getDosingDisplayUnits() {
		return dosingDisplayUnits;
	}

	public void setDosingDisplayUnits(String dosingDisplayUnits) {
		this.dosingDisplayUnits = dosingDisplayUnits;
	}

	public int getNormalizationUnit() {
		return normalizationUnit;
	}

	public void setNormalizationUnit(int normalizationUnit) {
		this.normalizationUnit = normalizationUnit;
	}

	public int getRootOfAdmistration() {
		return rootOfAdmistration;
	}

	public void setRootOfAdmistration(int rootOfAdmistration) {
		this.rootOfAdmistration = rootOfAdmistration;
	}

	public String getRootAdministrationItem() {
		return rootAdministrationItem;
	}

	public void setRootAdministrationItem(String rootAdministrationItem) {
		this.rootAdministrationItem = rootAdministrationItem;
	}

	public String getCalculationItem() {
		return calculationItem;
	}

	public void setCalculationItem(String calculationItem) {
		this.calculationItem = calculationItem;
	}

	public int getCalculationMethod() {
		return calculationMethod;
	}

	public void setCalculationMethod(int calculationMethod) {
		this.calculationMethod = calculationMethod;
	}

	public int getWeightingOption() {
		return weightingOption;
	}

	public void setWeightingOption(int weightingOption) {
		this.weightingOption = weightingOption;
	}

	public String getModelItem() {
		return modelItem;
	}

	public void setModelItem(String modelItem) {
		this.modelItem = modelItem;
	}

	public int getModelType() {
		return modelType;
	}

	public void setModelType(int modelType) {
		this.modelType = modelType;
	}
	public ModelInputs(){
		modelType = 0;
		weightingOption = 0;
		rootOfAdmistration = 0;
		normalizationUnit = 0;
		isSparseSelected = false;
		isPageBreakSelected = false;
		isIntermediateoutputSelected = false;
		excluseProfiles = false;
		disableCurveStripping = false;
		clearSelection = false;
		clearExclusion = false;
		title = "";
		dosingDisplayUnits = "";
		modelItem = "Plasma Model";
		calculationItem = "Lin Log Trapizoidal";
		rootAdministrationItem = "Extravascular";
		doseUnits = "";
	}
	
}
