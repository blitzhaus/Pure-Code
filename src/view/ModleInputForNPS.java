package view;

import java.io.Serializable;

public class ModleInputForNPS implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5061202462435569183L;


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModleInputForNPS other = (ModleInputForNPS) obj;
		if (dosingTypeForNPS != other.dosingTypeForNPS)
			return false;
		if (ifDisplayAfterNthDose != other.ifDisplayAfterNthDose)
			return false;
		if (ifDisplayAtSteadyState != other.ifDisplayAtSteadyState)
			return false;
		if (loadingDose == null) {
			if (other.loadingDose != null)
				return false;
		} else if (!loadingDose.equals(other.loadingDose))
			return false;
		if (maintenanceDose == null) {
			if (other.maintenanceDose != null)
				return false;
		} else if (!maintenanceDose.equals(other.maintenanceDose))
			return false;
		if (methodForComputation != other.methodForComputation)
			return false;
		if (noOfOutputPoints == null) {
			if (other.noOfOutputPoints != null)
				return false;
		} else if (!noOfOutputPoints.equals(other.noOfOutputPoints))
			return false;
		if (numberOfDoseBeforeDisplay == null) {
			if (other.numberOfDoseBeforeDisplay != null)
				return false;
		} else if (!numberOfDoseBeforeDisplay
				.equals(other.numberOfDoseBeforeDisplay))
			return false;
		if (tauValueInNPS == null) {
			if (other.tauValueInNPS != null)
				return false;
		} else if (!tauValueInNPS.equals(other.tauValueInNPS))
			return false;
		return true;
	}
	private int dosingTypeForNPS;
	private String noOfOutputPoints;
	private int methodForComputation;
	private String loadingDose;
	private String maintenanceDose;
	private String tauValueInNPS;
	private boolean ifDisplayAtSteadyState;
	private boolean ifDisplayAfterNthDose;
	private String[][] dosingDataForNPS;
	private boolean ifTerminalPhaseDefined;
	private String startTime;
	private String endTime;
	private String[][] administeredDose;
	private String[][] terminatPhaseData;
	
	
	public boolean isIfTerminalPhaseDefined() {
		return ifTerminalPhaseDefined;
	}
	public void setIfTerminalPhaseDefined(boolean ifTerminalPhaseDefined) {
		this.ifTerminalPhaseDefined = ifTerminalPhaseDefined;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
	public String[][] getAdministeredDose() {
		return administeredDose;
	}
	public void setAdministeredDose(String[][] administeredDoseData) {
		this.administeredDose = administeredDoseData;
	}
	
	public void setAdministeredDosingValueAt(int xIndex,int yIndex, String value){
		this.administeredDose[xIndex][yIndex] = value;
	}
	
	
	public String getAdministeredDosingValueAt(int xIndex,int yIndex){
		 return administeredDose[xIndex][yIndex];
	}
	
	
	public String[][] getTerminatPhaseData() {
		return terminatPhaseData;
	}
	public void setTerminatPhaseData(String[][] terminatphaseData) {
		this.terminatPhaseData = terminatphaseData;
	}
	
	public void setTerminatPhaseValueAt(int xIndex,int yIndex, String value){
		this.terminatPhaseData[xIndex][yIndex] = value;
	}
	
	
	public String getTerminatPhaseValueAt(int xIndex,int yIndex){
		 return terminatPhaseData[xIndex][yIndex];
	}
	
	
	public String[][] getDosingDataForNPS() {
		return dosingDataForNPS;
	}
	public void setDosingDataForNPS(String[][] dosingDataForNPS) {
		this.dosingDataForNPS = dosingDataForNPS;
	}
	
	public void setDosingValueAt(int xIndex,int yIndex, String value){
		this.dosingDataForNPS[xIndex][yIndex] = value;
	}
	
	
	public String getDosingValueAt(int xIndex,int yIndex){
		 return dosingDataForNPS[xIndex][yIndex];
	}
	
	public boolean isIfDisplayAfterNthDose() {
		return ifDisplayAfterNthDose;
	}
	
	public ModleInputForNPS(){
		noOfOutputPoints = "50";
		ifDisplayAtSteadyState = true;
		dosingDataForNPS = new String[1][1];
		dosingTypeForNPS = 0;
		methodForComputation = 0;
		loadingDose = "0";
		maintenanceDose = "0";
		tauValueInNPS = "0";
		ifDisplayAtSteadyState = true;
		ifDisplayAfterNthDose = false;
		dosingDataForNPS = new String[1][1];
		administeredDose = new String[1][1];
		terminatPhaseData = new String[1][1];
		numberOfDoseBeforeDisplay = "1";
		ifTerminalPhaseDefined = false;
		startTime = "0";
		endTime = "0";
	}
	
	public void setIfDisplayAfterNthDose(boolean ifDisplayAfterNthDose) {
		this.ifDisplayAfterNthDose = ifDisplayAfterNthDose;
	}
	private String numberOfDoseBeforeDisplay;
	public int getDosingTypeForNPS() {
		return dosingTypeForNPS;
	}
	public void setDosingTypeForNPS(int dosingTypeForNPS) {
		this.dosingTypeForNPS = dosingTypeForNPS;
	}
	public String getNoOfOutputPoints() {
		return noOfOutputPoints;
	}
	
	public String toString() {
		return "ModleInputForNPS [dosingTypeForNPS=" + dosingTypeForNPS
				+ ", noOfOutputPoints=" + noOfOutputPoints
				+ ", methodForComputation=" + methodForComputation
				+ ", loadingDose=" + loadingDose + ", maintenanceDose="
				+ maintenanceDose + ", tauValueInNPS=" + tauValueInNPS
				+ ", ifDisplayAtSteadyState=" + ifDisplayAtSteadyState
				+ ", numberOfDoseBeforeDisplay=" + numberOfDoseBeforeDisplay
				+ "]";
	}
	public void setNoOfOutputPoints(String noOfOutputPoints) {
		this.noOfOutputPoints = noOfOutputPoints;
	}
	public int getMethodForComputation() {
		return methodForComputation;
	}
	public void setMethodForComputation(int methodForComputation) {
		this.methodForComputation = methodForComputation;
	}
	public String getLoadingDose() {
		return loadingDose;
	}
	public void setLoadingDose(String loadingDose) {
		this.loadingDose = loadingDose;
	}
	public String getMaintenanceDose() {
		return maintenanceDose;
	}
	public void setMaintenanceDose(String maintenanceDose) {
		this.maintenanceDose = maintenanceDose;
	}
	public String getTauValueInNPS() {
		return tauValueInNPS;
	}
	public void setTauValueInNPS(String tauValueInNPS) {
		this.tauValueInNPS = tauValueInNPS;
	}
	public boolean isIfDisplayAtSteadyState() {
		return ifDisplayAtSteadyState;
	}
	public void setIfDisplayAtSteadyState(boolean ifDisplayAtSteadyState) {
		this.ifDisplayAtSteadyState = ifDisplayAtSteadyState;
	}
	public String getNumberOfDoseBeforeDisplay() {
		return numberOfDoseBeforeDisplay;
	}
	public void setNumberOfDoseBeforeDisplay(String numberOfDoseBeforeDisplay) {
		this.numberOfDoseBeforeDisplay = numberOfDoseBeforeDisplay;
	}
}
