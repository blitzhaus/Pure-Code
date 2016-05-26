package view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public class ProfileAndParamInfo implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -1865392179213158021L;

	String[] convert(double[] array){
		String[] strArray = new String[array.length];
		for(int i=0;i<array.length;i++){
			strArray[i] = array[i]+"";
		}
		
		return strArray;
	}
	 
	 String[] convert(int[] array){
			String[] strArray = new String[array.length];
			for(int i=0;i<array.length;i++){
				strArray[i] = array[i]+"";
			}
			
			return strArray;
		}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfileAndParamInfo other = (ProfileAndParamInfo) obj;
		if (!Arrays.equals(ParameterNameForLinkModel,
				other.ParameterNameForLinkModel))
			return false;
		if (!Arrays.deepEquals(convert(concForAllProfile), convert(other.concForAllProfile)))
			return false;
		if (!Arrays.deepEquals(convert(concForOneProfile), convert(other.concForOneProfile)))
			return false;
		if (!Arrays.deepEquals(convert(concentrationForOneProfile),
				convert(other.concentrationForOneProfile)))
			return false;
		if (!Arrays.deepEquals(dataSortVariables, other.dataSortVariables))
			return false;
		if (!Arrays.equals(defaultParameterUnitForCA,
				other.defaultParameterUnitForCA))
			return false;
		if (!Arrays.deepEquals(convert(endTimeForOneProfile), convert(other.endTimeForOneProfile)))
			return false;
		if (!Arrays.deepEquals(convert(excretionRateForOneProfile),
				convert(other.excretionRateForOneProfile)))
			return false;
		if (!Arrays.deepEquals(convert(midPointForOneProfile), convert(other.midPointForOneProfile)))
			return false;
		if (noOfSubject != other.noOfSubject)
			return false;
		if (normalizationUnit == null) {
			if (other.normalizationUnit != null)
				return false;
		} else if (!normalizationUnit.equals(other.normalizationUnit))
			return false;
		if (normalizationUnitIndex != other.normalizationUnitIndex)
			return false;
		if (numberOfDeterminant != other.numberOfDeterminant)
			return false;
		if (numberOfFunction != other.numberOfFunction)
			return false;
		if (numberOfLinkParam != other.numberOfLinkParam)
			return false;
		if (numberOfParameter != other.numberOfParameter)
			return false;
		if (numberOfSecondaryParameters != other.numberOfSecondaryParameters)
			return false;
		if (numberOfSortVariable != other.numberOfSortVariable)
			return false;
		if (!Arrays.equals(parameterNameByGroupForCA,
				other.parameterNameByGroupForCA))
			return false;
		if (!Arrays.equals(parameterNameForCA, other.parameterNameForCA))
			return false;
		if (!Arrays.equals(preferredParameterUnitForCA,
				other.preferredParameterUnitForCA))
			return false;
		if (!Arrays.equals(secondaryParameterNameForCA,
				other.secondaryParameterNameForCA))
			return false;
		if (!Arrays
				.deepEquals(convert(startTimeForOneProfile), convert(other.startTimeForOneProfile)))
			return false;
		if (!Arrays.deepEquals(convert(subjectObsNo), convert(other.subjectObsNo)))
			return false;
		if (!Arrays.deepEquals(convert(timeForAllProfile), convert(other.timeForAllProfile)))
			return false;
		if (!Arrays.deepEquals(convert(timeForOneProfile), convert(other.timeForOneProfile)))
			return false;
		if (!Arrays.deepEquals(convert(volumeForOneProfile), convert(other.volumeForOneProfile)))
			return false;
		return true;
	}

	int noOfSubject;
	int[] subjectObsNo;
	String[][] dataSortVariables;
	int numberOfSortVariable;
	
	double[] startTimeForOneProfile;
	double[] endTimeForOneProfile;
	double[] volumeForOneProfile;
	double[] concentrationForOneProfile;
	double[] timeForOneProfile;
	double[] concForOneProfile;
	double[] timeForAllProfile;
	double[] concForAllProfile;


	double[] midPointForOneProfile;
	double[] excretionRateForOneProfile;
	
	String[] parameterNameForCA;
	String[] parameterNameByGroupForCA;

	String[] defaultParameterUnitForCA;
	String[] preferredParameterUnitForCA;
	int numberOfParameter;
	int numberOfFunction;
	int numberOfDeterminant;
	int normalizationUnitIndex;
	String normalizationUnit;
	
	int numberOfSecondaryParameters;
	String[] secondaryParameterNameForCA;
	
	String[] ParameterNameForLinkModel;
	int numberOfLinkParam;

	public String[] getParameterNameForLinkModel() {
		return ParameterNameForLinkModel;
	}

	public void setParameterNameForLinkModel(String[] parameterNameForLinkModel) {
		ParameterNameForLinkModel = parameterNameForLinkModel;
	}

	public int getNumberOfLinkParam() {
		return numberOfLinkParam;
	}

	public void setNumberOfLinkParam(int numberOfLinkParam) {
		this.numberOfLinkParam = numberOfLinkParam;
	}

	public int getNoOfSubject() {
		return noOfSubject;
	}

	public void setNoOfSubject(int noOfSubject) {
		this.noOfSubject = noOfSubject;
	}

	public int[] getSubjectObsNo() {
		return subjectObsNo;
	}

	public void setSubjectObsNo(int[] subjectObsNo) {
		this.subjectObsNo = subjectObsNo;
	}

	public String[][] getDataSortVariables() {
		return dataSortVariables;
	}

	public void setDataSortVariables(String[][] dataSortVariables) {
		this.dataSortVariables = dataSortVariables;
	}

	public int getNumberOfSortVariable() {
		return numberOfSortVariable;
	}

	public void setNumberOfSortVariable(int numberOfSortVariable) {
		this.numberOfSortVariable = numberOfSortVariable;
	}

	public double[] getStartTimeForOneProfile() {
		return startTimeForOneProfile;
	}

	public void setStartTimeForOneProfile(double[] startTimeForOneProfile) {
		this.startTimeForOneProfile = startTimeForOneProfile;
	}

	public double[] getEndTimeForOneProfile() {
		return endTimeForOneProfile;
	}

	public void setEndTimeForOneProfile(double[] endTimeForOneProfile) {
		this.endTimeForOneProfile = endTimeForOneProfile;
	}

	public double[] getVolumeForOneProfile() {
		return volumeForOneProfile;
	}

	public void setVolumeForOneProfile(double[] volumeForOneProfile) {
		this.volumeForOneProfile = volumeForOneProfile;
	}

	public double[] getConcentrationForOneProfile() {
		return concentrationForOneProfile;
	}

	public void setConcentrationForOneProfile(double[] concentrationForOneProfile) {
		this.concentrationForOneProfile = concentrationForOneProfile;
	}

	public double[] getTimeForOneProfile() {
		return timeForOneProfile;
	}

	public void setTimeForOneProfile(double[] timeForOneProfile) {
		this.timeForOneProfile = timeForOneProfile;
	}

	public double[] getConcForOneProfile() {
		return concForOneProfile;
	}

	public void setConcForOneProfile(double[] concForOneProfile) {
		this.concForOneProfile = concForOneProfile;
	}

	public double[] getTimeForAllProfile() {
		return timeForAllProfile;
	}

	public void setTimeForAllProfile(double[] timeForAllProfile) {
		this.timeForAllProfile = timeForAllProfile;
	}

	public double[] getConcForAllProfile() {
		return concForAllProfile;
	}

	public void setConcForAllProfile(double[] concForAllProfile) {
		this.concForAllProfile = concForAllProfile;
	}

	public double[] getMidPointForOneProfile() {
		return midPointForOneProfile;
	}

	public void setMidPointForOneProfile(double[] midPointForOneProfile) {
		this.midPointForOneProfile = midPointForOneProfile;
	}

	public double[] getExcretionRateForOneProfile() {
		return excretionRateForOneProfile;
	}

	public void setExcretionRateForOneProfile(double[] excretionRateForOneProfile) {
		this.excretionRateForOneProfile = excretionRateForOneProfile;
	}

	
	public String[] getParameterNameForCA() {
		return parameterNameForCA;
	}

	public void setParameterNameForCA(String[] parameterNameForCA) {
		this.parameterNameForCA = parameterNameForCA;
	}

	public String[] getParameterNameByGroupForCA() {
		return parameterNameByGroupForCA;
	}

	public void setParameterNameByGroupForCA(String[] parameterNameByGroupForCA) {
		this.parameterNameByGroupForCA = parameterNameByGroupForCA;
	}

	public String[] getDefaultParameterUnitForCA() {
		return defaultParameterUnitForCA;
	}

	public void setDefaultParameterUnitForCA(String[] defaultParameterUnitForCA) {
		this.defaultParameterUnitForCA = defaultParameterUnitForCA;
	}

	public String[] getPreferredParameterUnitForCA() {
		return preferredParameterUnitForCA;
	}

	public void setPreferredParameterUnitForCA(String[] preferredParameterUnitForCA) {
		this.preferredParameterUnitForCA = preferredParameterUnitForCA;
	}

	public int getNumberOfParameter() {
		return numberOfParameter;
	}

	public void setNumberOfParameter(int numberOfParameter) {
		this.numberOfParameter = numberOfParameter;
	}

	public int getNumberOfFunction() {
		return numberOfFunction;
	}

	public void setNumberOfFunction(int numberOfFunction) {
		this.numberOfFunction = numberOfFunction;
	}

	public int getNumberOfDeterminant() {
		return numberOfDeterminant;
	}

	public void setNumberOfDeterminant(int numberOfDeterminant) {
		this.numberOfDeterminant = numberOfDeterminant;
	}

	public int getNormalizationUnitIndex() {
		return normalizationUnitIndex;
	}

	public void setNormalizationUnitIndex(int normalizationUnitIndex) {
		this.normalizationUnitIndex = normalizationUnitIndex;
	}

	public String getNormalizationUnit() {
		return normalizationUnit;
	}

	public void setNormalizationUnit(String normalizationUnit) {
		this.normalizationUnit = normalizationUnit;
	}

	public int getNumberOfSecondaryParameters() {
		return numberOfSecondaryParameters;
	}

	public void setNumberOfSecondaryParameters(int numberOfSecondaryParameters) {
		this.numberOfSecondaryParameters = numberOfSecondaryParameters;
	}

	public String[] getSecondaryParameterNameForCA() {
		return secondaryParameterNameForCA;
	}

	public void setSecondaryParameterNameForCA(String[] secondaryParameterNameForCA) {
		this.secondaryParameterNameForCA = secondaryParameterNameForCA;
	}

	public ProfileAndParamInfo() {
		
		noOfSubject = 1;
		subjectObsNo = new int[1];
		dataSortVariables = new String[1][1];
		numberOfSortVariable = 0;
		startTimeForOneProfile = new double[1];
		endTimeForOneProfile = new double[1];
		volumeForOneProfile = new double[1];
		concentrationForOneProfile = new double[1];
		timeForOneProfile = new double[1];
		concForOneProfile = new double[1];
		timeForAllProfile = new double[1];
		
		concForAllProfile = new double[1];
		midPointForOneProfile = new double[1];
		excretionRateForOneProfile = new double[1];
		parameterNameForCA = new String[1];
		parameterNameByGroupForCA = new String[1];
		defaultParameterUnitForCA = new String[1];
		preferredParameterUnitForCA = new String[1];
		numberOfParameter = 1;
		numberOfFunction = 1;
		numberOfDeterminant = 0;
		normalizationUnitIndex = 0;
		normalizationUnit = "";
		numberOfSecondaryParameters = 1;
		secondaryParameterNameForCA = new String[1];
	}
	

	

}
