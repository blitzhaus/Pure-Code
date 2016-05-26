package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import Common.Comparator;
import Common.MyComparator;

public class ProfileInformation implements Serializable{

	private static final long serialVersionUID = -3880423607113315328L;
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
	 
	
	HashMap<String, String> statusCodeHM;
	public HashMap<String, String> getStatusCodeHM() {
		return statusCodeHM;
	}

	public void setStatusCodeHM(HashMap<String, String> statusCodeHM) {
		this.statusCodeHM = statusCodeHM;
	}
	
	@Override
	public boolean equals(Object obj) {
		 Comparator comp = MyComparator.createMyCompInst();
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfileInformation other = (ProfileInformation) obj;
		if (ConcForAllProfile == null) {
			if (other.ConcForAllProfile != null)
				return false;
		} else if (!Arrays.deepEquals(convert(ConcForAllProfile), convert(other.ConcForAllProfile)))
			return false;
		if (ConcForAllSparseSampling == null) {
			if (other.ConcForAllSparseSampling != null)
				return false;
		} else if (!Arrays.deepEquals(convert(ConcForAllSparseSampling),
				convert(other.ConcForAllSparseSampling)))
			return false;
		/*if (SE == null) {
			if (other.SE != null)
				return false;
		} else if (!comp.compareSE(SE,(other.SE)))
			return false;*/
		if (TimeForAllProfile == null) {
			if (other.TimeForAllProfile != null)
				return false;
		} else
		if (!Arrays.deepEquals(convert(TimeForAllProfile), convert(other.TimeForAllProfile)))
			return false;
		if (TimeForAllSparseSampling == null) {
			if (other.TimeForAllSparseSampling != null)
				return false;
		} else
		if (!Arrays.deepEquals(convert(TimeForAllSparseSampling),
				convert(other.TimeForAllSparseSampling)))
			return false;
		if (carryAlongData == null) {
			if (other.carryAlongData != null)
				return false;
		} else
		if (!Arrays.deepEquals(carryAlongData, other.carryAlongData))
			return false;
		if (concForAllUrineProfile == null) {
			if (other.concForAllUrineProfile != null)
				return false;
		} else
		if (!Arrays
				.deepEquals(convert(concForAllUrineProfile), convert(other.concForAllUrineProfile)))
			return false;
		if (dataSortVariables == null) {
			if (other.dataSortVariables != null)
				return false;
		} else
		if (!Arrays.deepEquals(dataSortVariables, other.dataSortVariables))
			return false;
		if (!Arrays.deepEquals(convert(no_distinct_obs_sparsesampling),
				convert(other.no_distinct_obs_sparsesampling)))
			return false;
		if (no_subject != other.no_subject)
			return false;
		if (numberOfSortVariable != other.numberOfSortVariable)
			return false;
		if (!Arrays.deepEquals(convert(routeOfAdminForDosingDefined),
				convert(other.routeOfAdminForDosingDefined)))
			return false;
		if (statusCodeHM == null) {
			if (other.statusCodeHM != null)
				return false;
		} else if (!comp.compareStrStr(statusCodeHM, other.statusCodeHM))
			return false;
		if (!Arrays.equals(subjectIdentifierData, other.subjectIdentifierData))
			return false;
		if (!Arrays.deepEquals(convert(subject_obs), convert(other.subject_obs)))
			return false;
		if (!Arrays.deepEquals(convert(summaryVariablesForDS), convert(other.summaryVariablesForDS)))
			return false;
		if (volumeForAllUrineProfile == null) {
			if (other.volumeForAllUrineProfile != null)
				return false;
		} else
		if (!Arrays.deepEquals(convert(volumeForAllUrineProfile),
				convert(other.volumeForAllUrineProfile)))
			return false;
		return true;
	}
	
	String[][] convert(double[][] one){
		String[][] dd = new String[one.length][one[0].length];
		for(int i=0;i<dd.length;i++){
			for(int j=0;j<dd[i].length;j++){
				dd[i][j] = one[i][j]+"";
			}
		}
		return dd;
	}
	
	private int no_subject;
	public int getNo_subject() {
		return no_subject;
	}
	public void setNo_subject(int noSubject) {
		no_subject = noSubject;
	}
	public int[] getSubject_obs() {
		return subject_obs;
	}
	public void setSubject_obs(int[] subjectObs) {
		subject_obs = subjectObs;
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
	private int[] subject_obs;
	private String[][] dataSortVariables;
	private int numberOfSortVariable;
	
	public double[] getTimeForAllProfile() {
		return TimeForAllProfile;
	}
	public void setTimeForAllProfile(double[] timeForAllProfile) {
		TimeForAllProfile = timeForAllProfile;
	}
	public double[] getConcForAllProfile() {
		return ConcForAllProfile;
	}
	public void setConcForAllProfile(double[] concForAllProfile) {
		ConcForAllProfile = concForAllProfile;
	}
	public double[] getTimeForAllSparseSampling() {
		return TimeForAllSparseSampling;
	}
	public void setTimeForAllSparseSampling(double[] timeForAllSparseSampling) {
		TimeForAllSparseSampling = timeForAllSparseSampling;
	}
	public double[] getConcForAllSparseSampling() {
		return ConcForAllSparseSampling;
	}
	public void setConcForAllSparseSampling(double[] concForAllSparseSampling) {
		ConcForAllSparseSampling = concForAllSparseSampling;
	}
	
	public int[] getNo_distinct_obs_sparsesampling() {
		return no_distinct_obs_sparsesampling;
	}
	public void setNo_distinct_obs_sparsesampling(int[] noDistinctObsSparsesampling) {
		no_distinct_obs_sparsesampling = noDistinctObsSparsesampling;
	}
	public ArrayList<StdErrorAndObsCount> getSE() {
		return SE;
	}
	public void setSE(ArrayList<StdErrorAndObsCount> sE) {
		SE = sE;
	}
	private double[]	TimeForAllProfile;
	private double[]	ConcForAllProfile;
	private double[]	TimeForAllSparseSampling;
	private double[]	ConcForAllSparseSampling;
	private int[] no_distinct_obs_sparsesampling;
	private ArrayList<StdErrorAndObsCount> SE;
	private double[][] 	summaryVariablesForDS;	
	private String[][] carryAlongData;
	private String[] subjectIdentifierData;
	private double[]	volumeForAllUrineProfile;
	private double[]	concForAllUrineProfile;
	private int[] routeOfAdminForDosingDefined;
	private String[] independentVarForDS;
	
	public String[] getIndependentVarForDS() {
		return independentVarForDS;
	}

	public void setIndependentVarForDS(String[] independentVarForDS) {
		this.independentVarForDS = independentVarForDS;
	}

	public double[] getVolumeForAllUrineProfile() {
		return volumeForAllUrineProfile;
	}
	public void setVolumeForAllUrineProfile(double[] volumeForAllUrineProfile) {
		this.volumeForAllUrineProfile = volumeForAllUrineProfile;
	}
	public double[] getConcForAllUrineProfile() {
		return concForAllUrineProfile;
	}
	public void setConcForAllUrineProfile(double[] concForAllUrineProfile) {
		this.concForAllUrineProfile = concForAllUrineProfile;
	}
	public String[][] getCarryAlongData() {
		return carryAlongData;
	}
	public void setCarryAlongData(String[][] carryAlongData) {
		this.carryAlongData = carryAlongData;
	}
	public String[] getSubjectIdentifierData() {
		return subjectIdentifierData;
	}
	public void setSubjectIdentifierData(String[] subjectIdentifierData) {
		this.subjectIdentifierData = subjectIdentifierData;
	}

	public double[][] getSummaryVariablesForDS() {
		return summaryVariablesForDS;
	}
	public void setSummaryVariablesForDS(double[][] summaryVariablesForDS) {
		this.summaryVariablesForDS = summaryVariablesForDS;
	}

	
	public ProfileInformation(){
		no_subject = 1;
		subject_obs = new int[1];
		dataSortVariables = new String[1][1];
		numberOfSortVariable = 0;
		TimeForAllProfile = new double[1];
		ConcForAllProfile = new double[1];
		TimeForAllSparseSampling = new double[1];
		statusCodeHM = new HashMap<String, String>();
		ConcForAllSparseSampling = new double[1];
		no_distinct_obs_sparsesampling = new int[1];
		SE = new ArrayList<StdErrorAndObsCount>() ;
		summaryVariablesForDS = new double[1][1];
		carryAlongData = new String[1][1];
		subjectIdentifierData = new String[1];
		volumeForAllUrineProfile = new double[1];
		concForAllUrineProfile = new double[1];
		routeOfAdminForDosingDefined = new int[1];
		independentVarForDS = new String[1];

	}
	
	public int[] getRouteOfAdminForDosingDefined() {
		return routeOfAdminForDosingDefined;
	}
	public void setRouteOfAdminForDosingDefined(int[] routeOfAdminForDosingDefined) {
		this.routeOfAdminForDosingDefined = routeOfAdminForDosingDefined;
	}
	
	public void setRouteOfAdminAt(int rowChanged, int routeOfAdminForDD) {
		routeOfAdminForDosingDefined[rowChanged] = routeOfAdminForDD;		
	}
	public int getRouteOfAdminAt(int rowChanged) {
		// TODO Auto-generated method stub
		return routeOfAdminForDosingDefined[rowChanged];
	}
	
}
