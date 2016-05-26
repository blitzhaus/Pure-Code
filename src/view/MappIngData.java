package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;



import Common.Comparator;
import Common.MyComparator;

import view.ApplicationInfo;


/**
 * @author Ajith
 *
 */
public class MappIngData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3272150848054149943L;
	public static void main(String[] args) {
		MappIngData mpDat1 = new MappIngData();
		mpDat1.getAvailableColumnsVector().add("Ajith");
		mpDat1.getAvailableColumnsVector().add("");
		mpDat1.getAvailableColumnsVector().add("");
		
		mpDat1.setCarryAlongColumnName("Ajith");
		mpDat1.setDownVariable("Ramesh");
		
		
		MappIngData mpDat2 = new MappIngData();
		
		mpDat2.getAvailableColumnsVector().add("");
		mpDat2.getAvailableColumnsVector().add("");
		mpDat2.getAvailableColumnsVector().add("Ajith");
		
		
		
		mpDat2.setCarryAlongColumnName("Ajith");
		mpDat2.setDownVariable("Ramesh");
		System.out.println("trace ajith "+ mpDat1.equals(mpDat2));
		
	}



	@Override
	public boolean equals(Object obj) {
		
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MappIngData other = (MappIngData) obj;
		if (availableColumnsVector == null) {
			if (other.availableColumnsVector != null)
				return false;
		} else if(Arrays.deepEquals(convert(availableColumnsVector), convert(other.availableColumnsVector))!=true)//comp.compare(availableColumnsVector, other.availableColumnsVector)!=true
			{
			 return false;
		}
			
		if (carryAlongColumnName == null) {
			if (other.carryAlongColumnName != null)
				return false;
		} else if (!carryAlongColumnName.equals(other.carryAlongColumnName))
			return false;
		if (carryAlongVariablesListVector == null) {
			if (other.carryAlongVariablesListVector != null)
				return false;
		} else if (Arrays.deepEquals(convert(carryAlongVariablesListVector), convert(other.carryAlongVariablesListVector))!=true)
			return false;
		if (downVariable == null) {
			if (other.downVariable != null)
				return false;
		} else if (!downVariable.equals(other.downVariable))
			return false;
		if (downVariableCorrespondingIndex != other.downVariableCorrespondingIndex)
			return false;
		if (effectColumnName == null) {
			if (other.effectColumnName != null)
				return false;
		} else if (!effectColumnName.equals(other.effectColumnName))
			return false;
		if (effectCorrespondingOriginalIndex != other.effectCorrespondingOriginalIndex)
			return false;
		if (endTimeColumnCorrespondinOriginalIndex != other.endTimeColumnCorrespondinOriginalIndex)
			return false;
		if (endTimeColumnName == null) {
			if (other.endTimeColumnName != null)
				return false;
		} else if (!endTimeColumnName.equals(other.endTimeColumnName))
			return false;
		if (groupVariable == null) {
			if (other.groupVariable != null)
				return false;
		} else if (!groupVariable.equals(other.groupVariable))
			return false;
		if (groupVariableCorrespondingoriginalIndex != other.groupVariableCorrespondingoriginalIndex)
			return false;
		if (ifMappingScreenIsChanged != other.ifMappingScreenIsChanged)
			return false;
		if (ifSparseSampling != other.ifSparseSampling)
			return false;
		if (scaleIsAbsolute != other.scaleIsAbsolute)
			return false;
		if (scaleIsRelative != other.scaleIsRelative)
			return false;
		if (sortVariablesListVector == null) {
			if (other.sortVariablesListVector != null)
				return false;
		} else if (!Arrays.deepEquals(convert(sortVariablesListVector),convert(other.sortVariablesListVector)))//comp.compare(sortVariablesListVector, other.sortVariablesListVector)!=true
			return false;
		if (subjectColumnCorrespondinOriginalIndex != other.subjectColumnCorrespondinOriginalIndex)
			return false;
		if (subjectColumnName == null) {
			if (other.subjectColumnName != null)
				return false;
		} else if (!subjectColumnName.equals(other.subjectColumnName))
			return false;
		if (upVariable == null) {
			if (other.upVariable != null)
				return false;
		} else if (!upVariable.equals(other.upVariable))
			return false;
		if (upVariableCorrespondingOriginalIndex != other.upVariableCorrespondingOriginalIndex)
			return false;
		if (volumeColumnCorrespondinOriginalIndex != other.volumeColumnCorrespondinOriginalIndex)
			return false;
		if (volumeColumnName == null) {
			if (other.volumeColumnName != null)
				return false;
		} else if (!volumeColumnName.equals(other.volumeColumnName))
			return false;
		if (weightColumnCorrespondinOriginalIndex != other.weightColumnCorrespondinOriginalIndex)
			return false;
		if (weightColumnName == null) {
			if (other.weightColumnName != null)
				return false;
		} else if (!weightColumnName.equals(other.weightColumnName))
			return false;
		if (xColumnCorrespondinOriginalIndex != other.xColumnCorrespondinOriginalIndex)
			return false;
		if (xColumnName == null) {
			if (other.xColumnName != null)
				return false;
		} else if (!xColumnName.equals(other.xColumnName))
			return false;
		if (yColumnCorrespondinOriginalIndex != other.yColumnCorrespondinOriginalIndex)
			return false;
		if (ycolumnName == null) {
			if (other.ycolumnName != null)
				return false;
		} else if (!ycolumnName.equals(other.ycolumnName))
			return false;
		return true;
	}

	private String[] convert(Vector<String> carryAlongVariablesListVector2) {
		
		String[] objAr = new String[carryAlongVariablesListVector2.size()];
		for(int i=0;i<carryAlongVariablesListVector2.size();i++){
			objAr[i] = carryAlongVariablesListVector2.get(i);
		}
		return objAr;
	}

	@Override
	public String toString() {
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String consoleS = "\tprinting mapping data\n";
		String s = "";
		 s=s+"\t//MappIngData \n\t\tAvailableColumnsVector = ";  
				
		 for(int i=0;i<availableColumnsVector.size();i++){
			 	s = s +availableColumnsVector.get(i)+ " ,";
				}
		 s = s + "\n"+"\t\tcarryAlongVariablesListVector = ";
		 for(int i=0;i<carryAlongVariablesListVector.size();i++){
			 s = s+carryAlongVariablesListVector.get(i)+ " ,";
		 }
		 
		 s= s + "\n" + "\t\tendTimeColumnCorrespondinOriginalIndex = "+ endTimeColumnCorrespondinOriginalIndex;
		 s = s + "\n" + "\t\tendTimeColumnName=" + endTimeColumnName;
		 s = s + "\n" + "\t\tifMappingScreenIsChanged=" + ifMappingScreenIsChanged;
		 s = s + "\n" +  "\t\tifSparseSampling=" + ifSparseSampling;
		 s = s+ "\n" + "\t\tsortVariablesListVector = ";
		 for(int i=0;i<sortVariablesListVector.size();i++){
			 s = s + sortVariablesListVector.get(i)+" ,";
		 }
		 
		 s = s + "\n" + "\t\tsubjectColumnCorrespondinOriginalIndex="	+ subjectColumnCorrespondinOriginalIndex;
		 
		 s = s + "\n" + "\t\tsubjectColumnName=" + subjectColumnName;
		 
		 s = s + "\n" + "\t\tvolumeColumnCorrespondinOriginalIndex=" + volumeColumnCorrespondinOriginalIndex;
		 
		 s = s + "\n" +
		 		"\t\tvolumeColumnName="
				+ volumeColumnName + "\n\t\txColumnCorrespondinOriginalIndex="
				+ xColumnCorrespondinOriginalIndex + "\n\t\txColumnName="
				+ xColumnName + "\n\t\tyColumnCorrespondinOriginalIndex="
				+ yColumnCorrespondinOriginalIndex + "\n\t\tycolumnName="
				+ ycolumnName ;
		//p.append(s);
		return s;
	}

	private Vector<String> sortVariablesListVector;
	private  Vector<String> availableColumnsVector;
	private Vector<String> carryAlongVariablesListVector;
	private String xColumnName;
	private String ycolumnName;
	private String endTimeColumnName;
	private String volumeColumnName;
	private String subjectColumnName;
	private String carryAlongColumnName;

	private int funcColCorrespondngOrigIndex;
	private String funcColName;
	private int xColumnCorrespondinOriginalIndex;
	private int yColumnCorrespondinOriginalIndex;
	private int endTimeColumnCorrespondinOriginalIndex;
	private int volumeColumnCorrespondinOriginalIndex;
	private int subjectColumnCorrespondinOriginalIndex;
	private int weightColumnCorrespondinOriginalIndex;
	private int ifSparseSampling;
	private boolean ifMappingScreenIsChanged;
	private String upVariable;
	private int upVariableCorrespondingOriginalIndex;
	private String downVariable;
	private int downVariableCorrespondingIndex;
	private boolean scaleIsAbsolute;
	private boolean scaleIsRelative;
	private String groupVariable;
	private int groupVariableCorrespondingoriginalIndex;
	private HashSet<String> asciiFuncHS;
	


	public HashSet<String> getAsciiFuncHS() {
		return asciiFuncHS;
	}



	public void setAsciiFuncHS(HashSet<String> asciiFuncHS) {
		this.asciiFuncHS = asciiFuncHS;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public int getFuncColCorrespondngOrigIndex() {
		return funcColCorrespondngOrigIndex;
	}



	public void setFuncColCorrespondngOrigIndex(int funcColCorrespondngOrigIndex) {
		this.funcColCorrespondngOrigIndex = funcColCorrespondngOrigIndex;
	}



	public String getFuncColName() {
		return funcColName;
	}



	public void setFuncColName(String funcColName) {
		this.funcColName = funcColName;
	}


	
	
	public String getEffectColumnName() {
		return effectColumnName;
	}

	public void setEffectColumnName(String effectColumnName) {
		this.effectColumnName = effectColumnName;
	}

	public int getEffectCorrespondingOriginalIndex() {
		return effectCorrespondingOriginalIndex;
	}

	public void setEffectCorrespondingOriginalIndex(
			int effectCorrespondingOriginalIndex) {
		this.effectCorrespondingOriginalIndex = effectCorrespondingOriginalIndex;
	}

	private String effectColumnName;
	private int effectCorrespondingOriginalIndex;
	
	public String getGroupVariable() {
		return groupVariable;
	}

	public void setGroupVariable(String groupVariable) {
		this.groupVariable = groupVariable;
	}

	public int getGroupVariableCorrespondingoriginalIndex() {
		return groupVariableCorrespondingoriginalIndex;
	}

	public void setGroupVariableCorrespondingoriginalIndex(
			int groupVariableCorrespondingoriginalIndex) {
		this.groupVariableCorrespondingoriginalIndex = groupVariableCorrespondingoriginalIndex;
	}

	public String getUpVariable() {
		return upVariable;
	}

	public void setUpVariable(String upVariable) {
		this.upVariable = upVariable;
	}

	public int getUpVariableCorrespondingOriginalIndex() {
		return upVariableCorrespondingOriginalIndex;
	}

	public void setUpVariableCorrespondingOriginalIndex(
			int upVariableCorrespondingOriginalIndex) {
		this.upVariableCorrespondingOriginalIndex = upVariableCorrespondingOriginalIndex;
	}

	public String getDownVariable() {
		return downVariable;
	}

	public void setDownVariable(String downVariable) {
		this.downVariable = downVariable;
	}

	public int getDownVariableCorrespondingIndex() {
		return downVariableCorrespondingIndex;
	}

	public void setDownVariableCorrespondingIndex(int downVariableCorrespondingIndex) {
		this.downVariableCorrespondingIndex = downVariableCorrespondingIndex;
	}

	public boolean isScaleIsAbsolute() {
		return scaleIsAbsolute;
	}

	public void setScaleIsAbsolute(boolean scaleIsAbsolute) {
		this.scaleIsAbsolute = scaleIsAbsolute;
	}

	public boolean isScaleIsRelative() {
		return scaleIsRelative;
	}

	public void setScaleIsRelative(boolean scaleIsRelative) {
		this.scaleIsRelative = scaleIsRelative;
	}

	private String weightColumnName;
	public String getWeightColumnName() {
		return weightColumnName;
	}

	public void setWeightColumnName(String weightColumnName) {
		this.weightColumnName = weightColumnName;
	}

	public int getWeightColumnCorrespondinOriginalIndex() {
		return weightColumnCorrespondinOriginalIndex;
	}

	public void setWeightColumnCorrespondinOriginalIndex(
			int weightColumnCorrespondinOriginalIndex) {
		this.weightColumnCorrespondinOriginalIndex = weightColumnCorrespondinOriginalIndex;
	}


	public Vector<String> getCarryAlongVariablesListVector() {
		return carryAlongVariablesListVector;
	}

	public void setCarryAlongVariablesListVector(
			Vector<String> carryAlongVariablesListVector) {
		this.carryAlongVariablesListVector = carryAlongVariablesListVector;
	}

	public boolean getIfMappingScreenIsChanged() {
		return ifMappingScreenIsChanged;
	}

	public void setIfMappingScreenIsChanged(boolean ifMappingScreenIsChanged) {
		this.ifMappingScreenIsChanged = ifMappingScreenIsChanged;
	}

	public int getIfSparseSampling() {
		return ifSparseSampling;
	}

	public void setIfSparseSampling(int ifSparseSampling) {
		this.ifSparseSampling = ifSparseSampling;
	}

	public String getYcolumnName() {
		return ycolumnName;
	}

	public void setYcolumnName(String ycolumnName) {
		this.ycolumnName = ycolumnName;
	}

	public String getEndTimeColumnName() {
		return endTimeColumnName;
	}

	public void setEndTimeColumnName(String endTimeColumnName) {
		this.endTimeColumnName = endTimeColumnName;
	}

	public String getVolumeColumnName() {
		return volumeColumnName;
	}

	public void setVolumeColumnName(String volumeColumnName) {
		this.volumeColumnName = volumeColumnName;
	}

	public String getSubjectColumnName() {
		return subjectColumnName;
	}

	public void setSubjectColumnName(String subjectColumnName) {
		this.subjectColumnName = subjectColumnName;
	}

	public String getCarryAlongColumnName() {
		return carryAlongColumnName;
	}

	public void setCarryAlongColumnName(String carryAlongColumnName) {
		this.carryAlongColumnName = carryAlongColumnName;
	}

	public int getyColumnCorrespondinOriginalIndex() {
		return yColumnCorrespondinOriginalIndex;
	}

	public void setyColumnCorrespondinOriginalIndex(
			int yColumnCorrespondinOriginalIndex) {
		this.yColumnCorrespondinOriginalIndex = yColumnCorrespondinOriginalIndex;
	}

	public int getEndTimeColumnCorrespondinOriginalIndex() {
		return endTimeColumnCorrespondinOriginalIndex;
	}

	public void setEndTimeColumnCorrespondinOriginalIndex(
			int endTimeColumnCorrespondinOriginalIndex) {
		this.endTimeColumnCorrespondinOriginalIndex = endTimeColumnCorrespondinOriginalIndex;
	}

	public int getVolumeColumnCorrespondinOriginalIndex() {
		return volumeColumnCorrespondinOriginalIndex;
	}

	public void setVolumeColumnCorrespondinOriginalIndex(
			int volumeColumnCorrespondinOriginalIndex) {
		this.volumeColumnCorrespondinOriginalIndex = volumeColumnCorrespondinOriginalIndex;
	}

	public int getSubjectColumnCorrespondinOriginalIndex() {
		return subjectColumnCorrespondinOriginalIndex;
	}

	public void setSubjectColumnCorrespondinOriginalIndex(
			int subjectColumnCorrespondinOriginalIndex) {
		this.subjectColumnCorrespondinOriginalIndex = subjectColumnCorrespondinOriginalIndex;
	}

	public int getxColumnCorrespondinOriginalIndex() {
		return xColumnCorrespondinOriginalIndex;
	}

	public void setxColumnCorrespondinOriginalIndex(
			int xColumnCorrespondinOriginalIndex) {
		this.xColumnCorrespondinOriginalIndex = xColumnCorrespondinOriginalIndex;
	}

	public String getxColumnName() {
		return xColumnName;
	}

	public void setxColumnName(String xColumnName) {
		this.xColumnName = xColumnName;
	}

	public Vector<String> getSortVariablesListVector() {
		return sortVariablesListVector;
	}

	public void setSortVariablesListVector(Vector<String> sortVariablesListVector) {
		this.sortVariablesListVector = sortVariablesListVector;
	}
	public MappIngData(){
		sortVariablesListVector = new Vector<String>();
		availableColumnsVector = new Vector<String>();
		carryAlongVariablesListVector = new Vector<String>();
		ifMappingScreenIsChanged=false;
		endTimeColumnCorrespondinOriginalIndex = -1; // -1 indicates that the user has not supplied any value
		volumeColumnCorrespondinOriginalIndex = -1;
		subjectColumnCorrespondinOriginalIndex = -1;
		ycolumnName = "";
		xColumnName = "";
		endTimeColumnName = "";
		volumeColumnName = "";
		subjectColumnName = "";
		upVariable = "Up Variable";
		upVariableCorrespondingOriginalIndex = -1;
		downVariable = "Down Variable";
		downVariableCorrespondingIndex = -1;
		scaleIsAbsolute = false;
		scaleIsRelative = true;
		groupVariable = "Insert Group Variable";
		groupVariableCorrespondingoriginalIndex = -1;
		effectColumnName = "";
		effectCorrespondingOriginalIndex = -1;
		funcColCorrespondngOrigIndex = -1;
		asciiFuncHS = new HashSet<String>();
		
		
	}

	public Vector<String> getAvailableColumnsVector() {
		return availableColumnsVector;
	}

	public void setAvailableColumnsVector(Vector<String> availableColumnsVector) {
		this.availableColumnsVector = availableColumnsVector;
	}
}
