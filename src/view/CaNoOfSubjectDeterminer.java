package view;

import view.ApplicationInfo;
import view.DisplayContents;

public class CaNoOfSubjectDeterminer {

	int noOfSubject;
	int[] subjectObsNo;
	String[][] dataSortVariables;
	int numberOfSortVariable;
	static CaNoOfSubjectDeterminer noOfSubjectInst=null;
	public static CaNoOfSubjectDeterminer createDetermineNoOfSubInstance() 
	{
		if (noOfSubjectInst == null)
		{
			noOfSubjectInst = new CaNoOfSubjectDeterminer();
		}
		return noOfSubjectInst;
	}
	
	public void determineNumberOfSubject(String[][] dataMatrix) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		numberOfSortVariable = procInputInst.getMappingDataObj()
		.getSortVariablesListVector().size();
		if (numberOfSortVariable == 0) {
			noOfSubject = 1;
			subjectObsNo = new int[1];
			subjectObsNo[0] = dataMatrix.length;
			numberOfSortVariable = 0;
		}
		else {
			determineProfileNosWithSortVar(dataMatrix);
		}
		updatingInfoToAppInfo();
	}

	private void updatingInfoToAppInfo() {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		procInputInst.getProfileAndParamInfoObj().setNoOfSubject(noOfSubject);
		procInputInst.getProfileAndParamInfoObj().setSubjectObsNo(subjectObsNo);
		procInputInst.getProfileAndParamInfoObj().setNumberOfSortVariable(numberOfSortVariable);
		procInputInst.getProfileAndParamInfoObj().setDataSortVariables(dataSortVariables);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}
	public CaNoOfSubjectDeterminer() {
		noOfSubject = 1;
		subjectObsNo = new int[1];
		dataSortVariables = new String[1][1];
		numberOfSortVariable = 0;
	}

private void determineNoProfiles(int Nobs, int sortVariableCount, String[][] dataMatrix) {
		int count = 0;
		subjectObsNo[count] = 1;
		int numberOfSortLocal = sortVariableCount;
		for (int i = 0; i < Nobs - 1; i++) {
			while(numberOfSortLocal > 0){
				if(dataMatrix[i][numberOfSortLocal - 1].equals(dataMatrix[i+1][numberOfSortLocal - 1])){
					numberOfSortLocal--;
				} else {
					break;
				}
			}
			if(numberOfSortLocal == 0){
				subjectObsNo[count]++;
			} else {
				count++;
				subjectObsNo[count]++;
			}
			numberOfSortLocal = sortVariableCount;
		}
		noOfSubject = count + 1;
	}

	private void determineProfileNosWithSortVar(String[][] dataMatrix) {
		int Nobs = dataMatrix.length;
		subjectObsNo = new int[Nobs];
		int count = 0;
		int sum = 0;
		subjectObsNo[count] = 1;
		determineNoProfiles(Nobs, numberOfSortVariable, dataMatrix);
		dataSortVariables = new String[noOfSubject][numberOfSortVariable];
		sum = 0;
		for (int j = 0; j < noOfSubject; j++) {
			for (int ii = 0; ii < numberOfSortVariable; ii++) {
				dataSortVariables[j][ii] = dataMatrix[sum][ii];
			}
			sum = sum + subjectObsNo[j];
		}
	}
}