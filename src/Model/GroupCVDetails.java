package Model;

public class GroupCVDetails {
	
	String grpVarName;
	String crossVarName;
	String compKey;
	public String getGrpVarName() {
		return grpVarName;
	}
	public void setGrpVarName(String grpVarName) {
		this.grpVarName = grpVarName;
	}
	public String getCrossVarName() {
		return crossVarName;
	}
	public void setCrossVarName(String crossVarName) {
		this.crossVarName = crossVarName;
	}
	public String getCompKey() {
		return compKey;
	}
	public void setCompKey(String compKey) {
		this.compKey = compKey;
	}
	@Override
	public String toString() {
		return "GroupCVDetails [grpVarName=" + grpVarName + ", crossVarName="
				+ crossVarName + ", compKey=" + compKey + "]";
	}
	public GroupCVDetails(String grpVarName, String crossVarName, String compKey) {
		super();
		this.grpVarName = grpVarName;
		this.crossVarName = crossVarName;
		this.compKey = compKey;
	}
	
	

}
