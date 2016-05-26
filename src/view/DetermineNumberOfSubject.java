package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import view.ApplicationInfo;
import view.DisplayContents;

public class DetermineNumberOfSubject {

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

	public static DetermineNumberOfSubject getDET_NO_SUB() {
		return DET_NO_SUB;
	}

	public static void setDET_NO_SUB(DetermineNumberOfSubject dETNOSUB) {
		DET_NO_SUB = dETNOSUB;
	}

	int no_subject;
	int[] subject_obs;
	String[][] dataSortVariables;
	int numberOfSortVariable;

	public static DetermineNumberOfSubject DET_NO_SUB = null;

	public static DetermineNumberOfSubject createDetNoSubInst() {
		if (DET_NO_SUB == null) {
			DET_NO_SUB = new DetermineNumberOfSubject("just object creation");
		}
		return DET_NO_SUB;
	}

	public DetermineNumberOfSubject(String dummy) {
	}

	void determineNumberOfSubject(String[][] dataMatrix)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int sortVariableCount = 0;
		sortVariableCount = determineNoSortVariables(sortVariableCount);

		numberOfSortVariable = sortVariableCount;

		if (sortVariableCount == 0) {
			no_subject = 1;
			subject_obs = new int[1];
			subject_obs[0] = dataMatrix.length;
			numberOfSortVariable = 0;
			System.out.println();

		} else {

			int Nobs = dataMatrix.length;
			subject_obs = new int[Nobs];
			int sum = 0;

			// determine number of profiles
			determineNoProfiles(Nobs, sortVariableCount, dataMatrix);

			dataSortVariables = new String[no_subject][numberOfSortVariable];

			sum = 0;
			for (int j = 0; j < no_subject; j++) {

				for (int ii = 0; ii < numberOfSortVariable; ii++) {
					dataSortVariables[j][ii] = dataMatrix[sum][ii];

				}
				sum = sum + subject_obs[j];
			}
		}

		// setting all the class variables into processing inputs of appInfo
		settingIntoAppInfo();
		
		if((DDViewLayer.createViewLayerInstance().isNCAExecution == true)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)){
			
			setDefaultIncludeExculeMapAllProfiles();
		}
		
		
		

	}
	
	ProfileInformation profInfoInst;
	LambdaZDetails[] lamdaZdetailsInst;

	private void setDefaultIncludeExculeMapAllProfiles() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		profInfoInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getProfileInfoObj();
		lamdaZdetailsInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails();
		
		
		
		for(int i=0;i<no_subject; i++ ){
			
			
			int numberOfPoints = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj().getSubject_obs()[i];
			
			for(int j=0;j< numberOfPoints;j++){
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[i].getInclusionExcusionPoints().put(j, false);
			}
			
			
		}
		
		
		
	}

	private void settingIntoAppInfo() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setDataSortVariables(dataSortVariables);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getProfileInfoObj().setNo_subject(
							no_subject);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getProfileInfoObj().setSubject_obs(
							subject_obs);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setNumberOfSortVariable(numberOfSortVariable);

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == true)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)) {

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setDataSortVariables(dataSortVariables);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getProfileInfoObj().setNo_subject(
							no_subject);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getProfileInfoObj().setSubject_obs(
							subject_obs);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setNumberOfSortVariable(numberOfSortVariable);

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == true)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setDataSortVariables(dataSortVariables);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getProfileInfoObj().setNo_subject(
							no_subject);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getProfileInfoObj().setSubject_obs(
							subject_obs);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setNumberOfSortVariable(numberOfSortVariable);
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == true)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)) {

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setDataSortVariables(dataSortVariables);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj().setNo_subject(
							no_subject);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj().setSubject_obs(
							subject_obs);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setNumberOfSortVariable(numberOfSortVariable);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getLambdazDataobj()
					.setLambdaZDetails(no_subject);

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == true)) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setDataSortVariables(dataSortVariables);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getProfileInfoObj().setNo_subject(
							no_subject);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getProfileInfoObj().setSubject_obs(
							subject_obs);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setNumberOfSortVariable(numberOfSortVariable);
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == true)) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setDataSortVariables(dataSortVariables);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getProfileInfoObj().setNo_subject(
							no_subject);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getProfileInfoObj().setSubject_obs(
							subject_obs);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setNumberOfSortVariable(numberOfSortVariable);
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == false)
				&& (DDViewLayer.createViewLayerInstance().isFromTableMaven == false)
				&& (DDViewLayer.createViewLayerInstance().isTableTrans = true)) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setDataSortVariables(dataSortVariables);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj().setNo_subject(
							no_subject);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj().setSubject_obs(
							subject_obs);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj()
					.setNumberOfSortVariable(numberOfSortVariable);
		}

	}

	private void determineNoProfiles(int Nobs, int sortVariableCount,
			String[][] dataMatrix) {

		int count = 0;
		subject_obs[count] = 1;
		int numberOfSortLocal = sortVariableCount;
		for (int i = 0; i < Nobs - 1; i++) {

			while (numberOfSortLocal > 0) {

				if (dataMatrix[i][numberOfSortLocal - 1]
						.equals(dataMatrix[i + 1][numberOfSortLocal - 1])) {
					numberOfSortLocal--;
				} else {
					System.out.println("in the else loop");
					break;
				}
			}

			if (numberOfSortLocal == 0) {
				subject_obs[count]++;
			} else {
				count++;
				subject_obs[count]++;
			}
			numberOfSortLocal = sortVariableCount;

		}
		// subject_obs[count]=subject_obs[count]+1;
		no_subject = count + 1;

	}

	private int determineNoSortVariables(int sortVariableCount)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {
			sortVariableCount = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == true)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)) {
			sortVariableCount = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == true)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {
			sortVariableCount = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == true)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)) {
			sortVariableCount = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == true)) {

			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getMappingDataObj()
					.getGroupVariableCorrespondingoriginalIndex() == -1) {
				sortVariableCount = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size();
			} else {
				sortVariableCount = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() + 1;
			}

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == true)) {

			sortVariableCount = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getBqlInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == false)
				&& (DDViewLayer.createViewLayerInstance().isFromTableMaven == false)
				&& (DDViewLayer.createViewLayerInstance().isTableTrans = true)) {
			sortVariableCount = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();
		}

		return sortVariableCount;
	}
}
