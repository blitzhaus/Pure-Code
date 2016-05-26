package Model;

import java.io.IOException;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class ProfileRelatedInfoPreparator {

	ListOfDataStructures dataStructInst;
	ApplicationInfo appInfo;
	PkPdInfo pkpdInst;
	ProcessingInputsInfo procInputInst;

	public void copyProfileRelatedInfo(String analysisType) {
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		appInfo = dataStructInst.getAppInfo();
		pkpdInst = PkPdInfo.createPKPDInstance();
		procInputInst = pkpdInst.copyProcessingInput();

		if (analysisType.equals("pd")) {
			copyProfileRelatedInfoForPd();
		} else if (analysisType.equals("pkpdlink")) {
			copyProfileRelatedInfoForLinkModel();
		} else if (analysisType.equals("mm")) {
			copyProfileRelatedInfoForMm();
		} else if (analysisType.equals("irm")) {
			copyProfileRelatedInfoForIrm();
		}else if (analysisType.equals("invitro")) {
			copyProfileRelatedInfoForInVitro();
		}else if (analysisType.equals("ascii")) {
			copyProfileRelatedInfoForAscii();
		}
	}

	
	private void copyProfileRelatedInfoForAscii() {

		pkpdInst.numberOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		dataStructInst.subjectObsNos = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo();

		dataStructInst.noOfSubjects = procInputInst.getProfileAndParamInfoObj()
				.getNoOfSubject();
		pkpdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();
		for (int i = 0; i < pkpdInst.numberOfSortVar; i++) {
			pkpdInst.sortVariables[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}
	}


	public void createSingleProfileData(String analysisType, int sum, int ii) throws IOException {
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();
		appInfo = dataStructInst.getAppInfo();
		pkpdInst = PkPdInfo.createPKPDInstance();

		if (analysisType.equals("pd")) {
			createSingleProfileDataForPd(sum, ii);
		} else if (analysisType.equals("pkpdlink")) {
			createSingleProfileDataForLinkModel(sum, ii);
		} else if (analysisType.equals("mm")) {
			createSingleProfileDataForMm(sum, ii);
		} else if (analysisType.equals("irm")) {
			createSingleProfileDataForIrm(sum, ii);
		}else if (analysisType.equals("invitro")) {
			createSingleProfileDataForInVitro(sum, ii);
		}else if (analysisType.equals("ascii")) {
			createSingleProfileDataForAscii(sum, ii);
		}
	}
	
	private void createSingleProfileDataForAscii(int sum, int ii) throws IOException {

		pkpdInst.setX(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);
		pkpdInst.setY(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);

		AsciiParameterEstimator.createAsciiParamEstimationInst().pkPdInst.degreesOfFreedom = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo()[ii]
				- procInputInst.getProfileAndParamInfoObj()
						.getNumberOfParameter();

		for (int i = 0; i < procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]; i++) {
			pkpdInst.X[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getTimeForAllProfile()[sum + i];
			pkpdInst.Y[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getConcForAllProfile()[sum + i];

		}

	}


	private void createSingleProfileDataForInVitro(int sum, int ii) {

		pkpdInst.setX(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);
		pkpdInst.setY(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);

		InVitroParameterEstimator.createInVitroParamCalInstance().pkPdInst.degreesOfFreedom = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo()[ii]
				- procInputInst.getProfileAndParamInfoObj()
						.getNumberOfParameter();

		for (int i = 0; i < procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]; i++) {
			pkpdInst.X[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getTimeForAllProfile()[sum + i];
			pkpdInst.Y[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getConcForAllProfile()[sum + i];

		}

	}


	private void createSingleProfileDataForIrm(int sum, int ii) {

		pkpdInst.setX(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);
		pkpdInst.setY(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);

		IrmParamEstimator.createIrmParamCalInstance().pkPdInst.degreesOfFreedom = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo()[ii]
				- procInputInst.getProfileAndParamInfoObj()
						.getNumberOfParameter();

		for (int i = 0; i < procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]; i++) {
			pkpdInst.X[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getTimeForAllProfile()[sum + i];
			pkpdInst.Y[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getConcForAllProfile()[sum + i];

		}

	}

	private void createSingleProfileDataForMm(int sum, int ii) {

		pkpdInst.setX(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);
		pkpdInst.setY(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);

		MmParamEstimator.createMmParamCalInstance().pkPdInst.degreesOfFreedom = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo()[ii]
				- procInputInst.getProfileAndParamInfoObj()
						.getNumberOfParameter();

		for (int i = 0; i < procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]; i++) {
			pkpdInst.X[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getTimeForAllProfile()[sum + i];
			pkpdInst.Y[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getConcForAllProfile()[sum + i];

		}

	}

	private void createSingleProfileDataForLinkModel(int sum, int ii) {

		pkpdInst.setX(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);
		pkpdInst.setY(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);

		PkPdLinkParamEstimator.createPkPdLinkmParamCalInstance().pkPdInst.degreesOfFreedom = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo()[ii]
				- procInputInst.getProfileAndParamInfoObj()
						.getNumberOfParameter();

		for (int i = 0; i < procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]; i++) {
			pkpdInst.X[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getTimeForAllProfile()[sum + i];
			pkpdInst.Y[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getConcForAllProfile()[sum + i];

		}
	}

	private void createSingleProfileDataForPd(int sum, int ii) {

		pkpdInst.setX(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);
		pkpdInst.setY(new double[procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]][1]);

		PdParamEstimator.createPdParamCalInstance().pkPdInst.degreesOfFreedom = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo()[ii]
				- procInputInst.getProfileAndParamInfoObj()
						.getNumberOfParameter();

		for (int i = 0; i < procInputInst.getProfileAndParamInfoObj()
				.getSubjectObsNo()[ii]; i++) {
			pkpdInst.X[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getTimeForAllProfile()[sum + i];
			pkpdInst.Y[i][0] = procInputInst.getProfileAndParamInfoObj()
					.getConcForAllProfile()[sum + i];

		}

	}

	private void copyProfileRelatedInfoForIrm() {

		pkpdInst.numberOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		dataStructInst.subjectObsNos = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo();

		dataStructInst.noOfSubjects = procInputInst.getProfileAndParamInfoObj()
				.getNoOfSubject();
		pkpdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();
		for (int i = 0; i < pkpdInst.numberOfSortVar; i++) {
			pkpdInst.sortVariables[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}
	}

	private void copyProfileRelatedInfoForMm() {

		pkpdInst.numberOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		dataStructInst.subjectObsNos = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo();

		dataStructInst.noOfSubjects = procInputInst.getProfileAndParamInfoObj()
				.getNoOfSubject();

		pkpdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();
		pkpdInst.noOfDose = procInputInst.getModelInputTab1Obj()
				.getNumberOfDose();

		for (int i = 0; i < pkpdInst.numberOfSortVar; i++) {
			pkpdInst.sortVariables[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}
	}

	private void copyProfileRelatedInfoForLinkModel() {

		pkpdInst.numberOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		dataStructInst.subjectObsNos = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo();

		dataStructInst.noOfSubjects = procInputInst.getProfileAndParamInfoObj()
				.getNoOfSubject();

		pkpdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();

		for (int i = 0; i < pkpdInst.numberOfSortVar; i++) {
			pkpdInst.sortVariables[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}
	}

	private void copyProfileRelatedInfoForPd() {

		pkpdInst.numberOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		dataStructInst.subjectObsNos = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo();

		dataStructInst.noOfSubjects = procInputInst.getProfileAndParamInfoObj()
				.getNoOfSubject();

		pkpdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();

		for (int i = 0; i < pkpdInst.numberOfSortVar; i++) {
			pkpdInst.sortVariables[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}
	}
	
	private void copyProfileRelatedInfoForInVitro() {

		pkpdInst.numberOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		dataStructInst.subjectObsNos = procInputInst
				.getProfileAndParamInfoObj().getSubjectObsNo();

		dataStructInst.noOfSubjects = procInputInst.getProfileAndParamInfoObj()
				.getNoOfSubject();

		pkpdInst.noOfParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfParameter();

		for (int i = 0; i < pkpdInst.numberOfSortVar; i++) {
			pkpdInst.sortVariables[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}
	}

	
	public void removeMissingData(int profileNo) {
		

		
		int noOfNonMissingPoint = 0;
		
		for (int i = 0; i < pkpdInst.X.length; i++) {
			if(Double.isNaN(pkpdInst.Y[i][0]) == true)
			{
				pkpdInst.Y[i][0] =  -3.14159265359;
			}
			
			if(pkpdInst.Y[i][0] == -3.14159265359)
			{
				
			}else
			{
				noOfNonMissingPoint++;
			}
			
		}
		
			double[] tempX = new double[pkpdInst.X.length];
			double[] tempY = new double[pkpdInst.Y.length];
			
			for (int i = 0; i < tempY.length; i++) {
				tempX[i] = pkpdInst.X[i][0];
				tempY[i] = pkpdInst.Y[i][0];
			}
			
			pkpdInst.X = new double[noOfNonMissingPoint][1];
			pkpdInst.Y = new double[noOfNonMissingPoint][1];
			
			int count = 0;
			for (int i = 0; i < tempX.length; i++) {
				
				if(tempY[i] == -3.14159265359)
				{
					
				}else
				{
					pkpdInst.X[count][0] = tempX[i];
					pkpdInst.Y[count][0] = tempY[i];
					count++;
				}
			}
			
		pkpdInst.row[0] = noOfNonMissingPoint;
		dataStructInst.subjectObsNos[profileNo] = noOfNonMissingPoint;
	
	}


	public String systemTime() {
		java.sql.Timestamp currentTime = new java.sql.Timestamp(
				new java.util.Date().getTime());
		String currenttime = String.valueOf(currentTime);
		String date = currenttime.substring(0, 10);
		String time = currenttime.substring(11, 19);
		// logArea.append("     " + date + "  " + time+"\n");
		return (date + "/" + time);
	}

}
