package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;

import view.ApplicationInfo;


public class UnitBuilderData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4641414431702823420L;

	@Override
	public String toString() {
		
		String consoleS = "printing unit builder properties\n";
		
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String s = "" +"\n\t\t\t{"+"\n"+
				"\t\t\t" + "displayUnits = "+displayUnits +"\n" 
				+ "\t\t\tmasPrefixIndex=" + masPrefixIndex + ", massunitIndex="+
				 massunitIndex + "\n\t\t\t operatorIndex=" + operatorIndex+"\n"
				+ "\t\t\ttimeUnits=" + timeUnits + ", unitsJustForDisplay="+
				 unitsJustForDisplay + "\n\t\t\tvolumePrefixIndex="
				+ volumePrefixIndex + "\n\t\t\tvolumeUnitindex=" + volumeUnitindex+"\n"
				+"\n\t\t\t}";
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
		UnitBuilderData other = (UnitBuilderData) obj;
		if (displayUnits == null) {
			if (other.displayUnits != null)
				return false;
		} else if (!displayUnits.equals(other.displayUnits))
			return false;
		if (masPrefixIndex == null) {
			if (other.masPrefixIndex != null)
				return false;
		} else if (!masPrefixIndex.equals(other.masPrefixIndex))
			return false;
		if (massunitIndex == null) {
			if (other.massunitIndex != null)
				return false;
		} else if (!massunitIndex.equals(other.massunitIndex))
			return false;
		if (operatorIndex == null) {
			if (other.operatorIndex != null)
				return false;
		} else if (!operatorIndex.equals(other.operatorIndex))
			return false;
		if (timeUnits == null) {
			if (other.timeUnits != null)
				return false;
		} else if (!timeUnits.equals(other.timeUnits))
			return false;
		if (unitsJustForDisplay == null) {
			if (other.unitsJustForDisplay != null)
				return false;
		} else if (!unitsJustForDisplay.equals(other.unitsJustForDisplay))
			return false;
		if (volumePrefixIndex == null) {
			if (other.volumePrefixIndex != null)
				return false;
		} else if (!volumePrefixIndex.equals(other.volumePrefixIndex))
			return false;
		if (volumeUnitindex == null) {
			if (other.volumeUnitindex != null)
				return false;
		} else if (!volumeUnitindex.equals(other.volumeUnitindex))
			return false;
		return true;
	}
	private String massunitIndex;
	private String volumePrefixIndex;
	private String timeUnits;
	private String volumeUnitindex;
	private String operatorIndex;
	private String unitsJustForDisplay;
	private String displayUnits;
	private String masPrefixIndex;//index is enough as we have the prefix array in the above class
	
	public UnitBuilderData(){
		massunitIndex = new String();
		volumePrefixIndex= new String() ;
		timeUnits = new String();
		volumeUnitindex = new String();
		operatorIndex = new String();
		unitsJustForDisplay = new String();
		displayUnits = new String();
		masPrefixIndex = new String();
		
	}
	public String getMassunitIndex() {
		return massunitIndex;
	}
	public void setMassunitIndex(String massunitIndex) {
		this.massunitIndex = massunitIndex;
	}
	public String getVolumePrefixIndex() {
		return volumePrefixIndex;
	}
	public void setVolumePrefixIndex(String volumePrefixIndex) {
		this.volumePrefixIndex = volumePrefixIndex;
	}
	public String getTimeUnits() {
		return timeUnits;
	}
	public void setTimeUnits(String timeUnits) {
		this.timeUnits = timeUnits;
	}
	public String getVolumeUnitindex() {
		return volumeUnitindex;
	}
	public void setVolumeUnitindex(String volumeUnitindex) {
		this.volumeUnitindex = volumeUnitindex;
	}
	public String getOperatorIndex() {
		return operatorIndex;
	}
	public void setOperatorIndex(String operatorIndex) {
		this.operatorIndex = operatorIndex;
	}
	public String getUnitsJustForDisplay() {
		return unitsJustForDisplay;
	}
	public void setUnitsJustForDisplay(String unitsJustForDisplay) {
		this.unitsJustForDisplay = unitsJustForDisplay;
	}
	public String getDisplayUnits() {
		return displayUnits;
	}
	public void setDisplayUnits(String displayUnits) {
		this.displayUnits = displayUnits;
	}
	public String getMasPrefixIndex() {
		return masPrefixIndex;
	}
	public void setMasPrefixIndex(String masPrefixIndex) {
		this.masPrefixIndex = masPrefixIndex;
	}
} 
