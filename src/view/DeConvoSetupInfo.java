package view;

import java.io.Serializable;

public class DeConvoSetupInfo implements Serializable {

	public DeConvoSetupInfo() {
		dose = new String[1][1];
		expValues = new String[1][1];
		parameters = new String[1][1];
		
	}
	
	
	public void setDosingValueAt(int xIndex,int yIndex, String value){
		dose[xIndex][yIndex] = value;
	}
	
	public String getDosingValueAt(int xIndex, int yIndex){
		return(dose[xIndex][yIndex]);
	}
	
	public String[][] getDose() {
		return dose;
	}
	public void setDose(String[][] dose) {
		this.dose = dose;
	}
	
	
	public void setExpValueAt(int xIndex,int yIndex, String value){
		expValues[xIndex][yIndex] = value;
	}
	
	public String getExpValueAt(int xIndex, int yIndex){
		return(expValues[xIndex][yIndex]);
	}
	
	public String[][] getExpValues() {
		return expValues;
	}
	public void setExpValues(String[][] expValues) {
		this.expValues = expValues;
	}
	
	
	public void setParameterValueAt(int xIndex,int yIndex, String value){
		parameters[xIndex][yIndex] = value;
	}
	
	public String getParameterValueAt(int xIndex, int yIndex){
		return(parameters[xIndex][yIndex]);
	}
	
	
	public String[][] getParameters() {
		return parameters;
	}
	public void setParameters(String[][] parameters) {
		this.parameters = parameters;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 4243690495984723469L;
	
	private String[][] dose;
	private String[][] expValues;
	private String[][] parameters;
	
	

}
