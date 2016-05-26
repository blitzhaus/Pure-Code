package view;

import java.awt.Font;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import Model.WorkSheetOutputs;



public class TableMavenInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8877922927412956513L;
	int templateType;
	ArrayList<String> groupVariables;
	ArrayList<String> idVariables;
	ArrayList<String> crossVariables;
	ArrayList<String> ordinaryVariables;
	TableWizardOpMetricOptions tmOpMetOptions;
	private boolean isDataChanged;
	
	int confInterval = 0;
	int numOfStdDeviations = 0;
	
	

	public int getConfInterval() {
		return confInterval;
	}

	public void setConfInterval(int confInterval) {
		this.confInterval = confInterval;
	}

	public int getNumOfStdDeviations() {
		return numOfStdDeviations;
	}

	public void setNumOfStdDeviations(int numOfStdDeviations) {
		this.numOfStdDeviations = numOfStdDeviations;
	}

	public boolean isDataChanged() {
		return isDataChanged;
	}

	public void setDataChanged(boolean isDataChanged) {
		this.isDataChanged = isDataChanged;
	}

	private WorkSheetOutputs WorkSheetOutputs;

	public WorkSheetOutputs getWorkSheetOutputs() {
		return WorkSheetOutputs;
	}

	public void setWorkSheetOutputs(WorkSheetOutputs workSheetOutputs2) {
		WorkSheetOutputs = workSheetOutputs2;
	}

	String fontColor = null;
	String textAlignment = null;

	public String getTextAlignment() {
		return textAlignment;
	}

	public void setTextAlignment(String textAlignment) {
		this.textAlignment = textAlignment;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	Font sysLevelFont;

	public Font getSysLevelFont() {
		return sysLevelFont;
	}

	public void setSysLevelFont(Font sysLevelFont) {
		this.sysLevelFont = sysLevelFont;
	}

	public TableWizardOpMetricOptions getTmOpMetOptions() {
		return tmOpMetOptions;
	}

	public void setTmOpMetOptions(TableWizardOpMetricOptions tmOpMetOptions) {
		this.tmOpMetOptions = tmOpMetOptions;
	}

	public int getTemplateType() {
		return templateType;
	}

	public void setTemplateType(int templateType) {
		this.templateType = templateType;
	}

	public ArrayList<String> getGroupVariables() {
		return groupVariables;
	}

	public void setGroupVariables(ArrayList<String> groupVariables) {
		this.groupVariables = groupVariables;
	}

	public ArrayList<String> getIdVariables() {
		return idVariables;
	}

	public void setIdVariables(ArrayList<String> idVariables) {
		this.idVariables = idVariables;
	}

	public ArrayList<String> getCrossVariables() {
		return crossVariables;
	}

	public void setCrossVariables(ArrayList<String> crossVariables) {
		this.crossVariables = crossVariables;
	}

	public ArrayList<String> getOrdinaryVariables() {
		return ordinaryVariables;
	}

	public void setOrdinaryVariables(ArrayList<String> ordinaryVariables) {
		this.ordinaryVariables = ordinaryVariables;
	}

	public TableMavenInfo(int templateType, ArrayList<String> groupVariables,
			ArrayList<String> idVariables, ArrayList<String> crossVariables,
			ArrayList<String> ordinaryVariables) {

		this.templateType = templateType;
		this.groupVariables = groupVariables;
		this.idVariables = idVariables;
		this.crossVariables = crossVariables;
		this.ordinaryVariables = ordinaryVariables;
		isDataChanged = false;
	}

	public TableMavenInfo() {

		templateType = 1;
		groupVariables = new ArrayList<String>();
		idVariables = new ArrayList<String>();
		crossVariables = new ArrayList<String>();
		ordinaryVariables = new ArrayList<String>();
	}

	String[] convert(ArrayList<String> al){
		String[] strAl = new String[al.size()];
		for(int i=0;i<strAl.length;i++){
			strAl[i] = al.get(i);
		}
		return strAl;
	}
	

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableMavenInfo other = (TableMavenInfo) obj;
		if (WorkSheetOutputs == null) {
			if (other.WorkSheetOutputs != null)
				return false;
		} else if (!WorkSheetOutputs.equals(other.WorkSheetOutputs))
			return false;
		if (crossVariables == null) {
			if (other.crossVariables != null)
				return false;
		} else if (!Arrays.deepEquals(convert(crossVariables),convert(other.crossVariables)))
			return false;
		if (fontColor == null) {
			if (other.fontColor != null)
				return false;
		} else if (!fontColor.equals(other.fontColor))
			return false;
		if (groupVariables == null) {
			if (other.groupVariables != null)
				return false;
		} else if (!Arrays.deepEquals(convert(groupVariables),convert(other.groupVariables)))
			return false;
		if (idVariables == null) {
			if (other.idVariables != null)
				return false;
		} else if (!Arrays.deepEquals(convert(idVariables),convert(other.idVariables)))
			return false;
		if (isDataChanged != other.isDataChanged)
			return false;
		if (ordinaryVariables == null) {
			if (other.ordinaryVariables != null)
				return false;
		} else if (!Arrays.deepEquals(convert(ordinaryVariables),convert(other.ordinaryVariables)))
			return false;
		if (sysLevelFont == null) {
			if (other.sysLevelFont != null)
				return false;
		} else if (!sysLevelFont.equals(other.sysLevelFont))
			return false;
		if (templateType != other.templateType)
			return false;
		if (textAlignment == null) {
			if (other.textAlignment != null)
				return false;
		} else if (!textAlignment.equals(other.textAlignment))
			return false;
		if (tmOpMetOptions == null) {
			if (other.tmOpMetOptions != null)
				return false;
		} else if (!tmOpMetOptions.equals(other.tmOpMetOptions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TableWizardInputs [crossVariables=" + crossVariables
				+ ", groupVariables=" + groupVariables + ", idVariables="
				+ idVariables + ", ordinaryVariables=" + ordinaryVariables
				+ ", templateType=" + templateType + "]";
	}
}
