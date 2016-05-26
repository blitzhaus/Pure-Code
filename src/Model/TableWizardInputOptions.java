package Model;

import java.util.ArrayList;

public class TableWizardInputOptions {
int templateType;
ArrayList<String> groupVariables;
ArrayList<String> idVariables;
ArrayList<String> crossVariables;
ArrayList<String> ordinaryVariables;
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
public TableWizardInputOptions(int templateType, ArrayList<String> groupVariables,
		ArrayList<String> idVariables, ArrayList<String> crossVariables,
		ArrayList<String> ordinaryVariables) {
	super();
	this.templateType = templateType;
	this.groupVariables = groupVariables;
	this.idVariables = idVariables;
	this.crossVariables = crossVariables;
	this.ordinaryVariables = ordinaryVariables;
}
@Override
public String toString() {
	return "TableWizardInputs [crossVariables=" + crossVariables
			+ ", groupVariables=" + groupVariables + ", idVariables="
			+ idVariables + ", ordinaryVariables=" + ordinaryVariables
			+ ", templateType=" + templateType + "]";
}
}
