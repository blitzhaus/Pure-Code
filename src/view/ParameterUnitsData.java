package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Arrays;

import view.ApplicationInfo;


public class ParameterUnitsData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6872052387352359965L;
	@Override
	public String toString() {
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String consoleS = "\tPrinting Parameter units details\n";
		String s = "";
		s = s + "\n\t//ParameterUnitsData ";
		s = s + "\n"+ "\t\tdefaultUnitArray = ";
		for(int i=0;i<defaultUnitArray.length;i++){
			s = s + defaultUnitArray[i]+ " ,";
		}
		
		s = s + "\n"+ "\t\tisFromPreferredUnitScreen = " + isFromPreferredUnitScreen;
		
		s  = s + "\n" + "\t\tparameterByGroup = ";
		for(int i=0;i<parameterByGroup.length;i++){
			s  = s+ parameterByGroup[i]+" ," ;
		}
		
		s = s  + "\n" +"\t\tpreferredUnitsArray = ";
		for(int i=0;i<preferredUnitsArray.length;i++){
			s = s + preferredUnitsArray[i]+ ", ";
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
		ParameterUnitsData other = (ParameterUnitsData) obj;
		if (ConcMassUnit == null) {
			if (other.ConcMassUnit != null)
				return false;
		} else if (!ConcMassUnit.equals(other.ConcMassUnit))
			return false;
		if (!Arrays.equals(defaultUnitArray, other.defaultUnitArray))
			return false;
		if (isFromPreferredUnitScreen != other.isFromPreferredUnitScreen)
			return false;
		if (!Arrays.equals(parameterByGroup, other.parameterByGroup))
			return false;
		if (!Arrays.equals(preferredUnitsArray, other.preferredUnitsArray))
			return false;
		if (timeUnit == null) {
			if (other.timeUnit != null)
				return false;
		} else if (!timeUnit.equals(other.timeUnit))
			return false;
		if (volumeUnit == null) {
			if (other.volumeUnit != null)
				return false;
		} else if (!volumeUnit.equals(other.volumeUnit))
			return false;
		return true;
	}

	private String[] preferredUnitsArray;
	private boolean isFromPreferredUnitScreen;
	private String[] defaultUnitArray;
	private String[] parameterByGroup;
	public String getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	public String getConcMassUnit() {
		return ConcMassUnit;
	}

	public void setConcMassUnit(String concMassUnit) {
		ConcMassUnit = concMassUnit;
	}

	public String getVolumeUnit() {
		return volumeUnit;
	}

	public void setVolumeUnit(String volumeUnit) {
		this.volumeUnit = volumeUnit;
	}

	private String timeUnit;
	private String ConcMassUnit;
	private String volumeUnit;
	

	public String[] getParameterByGroup() {
		return parameterByGroup;
	}

	public void setParameterByGroup(String[] parameterByGroup) {
		this.parameterByGroup = parameterByGroup;
	}

	public String getParameterbyGroupValueAt(int index){
		return parameterByGroup[index];
	}
	
	public void setParameterByGroupValueAt(int index, String value){
		parameterByGroup[index] = value;
	}
	public String getDefaultUnitValueAt(int index){
		
		return(defaultUnitArray[index]);
	}
	
	public void setDefaultUnitValueAt(int index, String value){
		defaultUnitArray[index] = value;
		
	}
	public boolean isFromPreferredUnitScreen() {
		return isFromPreferredUnitScreen;
	}

	public void setFromPreferredUnitScreen(boolean isFromPreferredUnitScreen) {
		this.isFromPreferredUnitScreen = isFromPreferredUnitScreen;
	}

	public String[] getPreferredUnitsArray() {
		return preferredUnitsArray;
	}

	public void setPreferredUnitsArray(String[] preferredUnitsArray) {
		this.preferredUnitsArray = preferredUnitsArray;
	}
	
	public void setPreferredUnit(int index, String value){
		preferredUnitsArray[index] = value;
	}
	
	public String getPreferredUnit(int index){
		return preferredUnitsArray[index];
	}
	
	public ParameterUnitsData(){
		isFromPreferredUnitScreen=false;
		preferredUnitsArray = new String[16];
		defaultUnitArray = new String[16];
		parameterByGroup = new String[16];
		timeUnit = "";
		ConcMassUnit = "";
		volumeUnit = "";
	}

	public String[] getDefaultUnitArray() {
		return defaultUnitArray;
	}

	public void setDefaultUnitArray(String[] defaultUnitArray) {
		this.defaultUnitArray = defaultUnitArray;
	}
}
