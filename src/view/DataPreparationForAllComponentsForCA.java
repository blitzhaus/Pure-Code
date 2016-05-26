package view;

import view.ApplicationInfo;
import view.DisplayContents;

public class DataPreparationForAllComponentsForCA {

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
	int[] noOfdistinctObsForSparsesSmpling;

	CaNoOfSubjectDeterminer noOfSubjectInst;
	
	static DataPreparationForAllComponentsForCA datPrepForCAInst=null;
	public static DataPreparationForAllComponentsForCA createDetaPrepForCAInstance() 
	{
		if (datPrepForCAInst == null)
		{
			datPrepForCAInst = new DataPreparationForAllComponentsForCA();
		}
		return datPrepForCAInst;
	}
	
	
	public void dataPreparationForAll(String[][] dataMatrix) {
		
		noOfSubjectInst = CaNoOfSubjectDeterminer.createDetermineNoOfSubInstance();
		timeForAllProfile = new double[dataMatrix.length];
		concForAllProfile = new double[dataMatrix.length];

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procinputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();

		int sum = 0;
		
		for (int j = 0; j < noOfSubjectInst.noOfSubject; j++) {
			if (procinputInst.getMappingDataObj()
					.getSortVariablesListVector().size() != 0) {
				String[][] totalSortVariables = new String[1][noOfSubjectInst.numberOfSortVariable];
				for (int ii = 0; ii < noOfSubjectInst.numberOfSortVariable; ii++) {
					totalSortVariables[0][ii] = dataMatrix[sum][ii];
				}
			}

			if (procinputInst.getModelInputTab1Obj().getDataType() == 1) {
				startTimeForOneProfile = new double[noOfSubjectInst.subjectObsNo[j]];
				endTimeForOneProfile = new double[noOfSubjectInst.subjectObsNo[j]];
				concentrationForOneProfile = new double[noOfSubjectInst.subjectObsNo[j]];
				volumeForOneProfile = new double[noOfSubjectInst.subjectObsNo[j]];

				for (int i = 0; i < noOfSubjectInst.subjectObsNo[j]; i++) {

					startTimeForOneProfile[i] = Double
							.parseDouble(dataMatrix[i + sum][noOfSubjectInst.numberOfSortVariable]);
					endTimeForOneProfile[i] = Double
							.parseDouble(dataMatrix[i + sum][noOfSubjectInst.numberOfSortVariable + 1]);
					concentrationForOneProfile[i] = Double
							.parseDouble(dataMatrix[i + sum][noOfSubjectInst.numberOfSortVariable + 2]);
					volumeForOneProfile[i] = Double
							.parseDouble(dataMatrix[i + sum][noOfSubjectInst.numberOfSortVariable + 3]);
					System.out.println();
				}
				sum = sum + noOfSubjectInst.subjectObsNo[j];

				urineDataProcessing(startTimeForOneProfile,
						endTimeForOneProfile, concentrationForOneProfile,
						volumeForOneProfile);

				for (int i = 0; i < startTimeForOneProfile.length; i++)
					timeForOneProfile = new double[noOfSubjectInst.subjectObsNo[j]];
				concForOneProfile = new double[noOfSubjectInst.subjectObsNo[j]];
				timeForOneProfile = midPointForOneProfile;
				concForOneProfile = excretionRateForOneProfile;
			}

			if (procinputInst.getModelInputTab1Obj().getDataType() == 0 && procinputInst.getModelInputTab1Obj().isSimulation() == false ) {
				timeForOneProfile = new double[noOfSubjectInst.subjectObsNo[j]];
				concForOneProfile = new double[noOfSubjectInst.subjectObsNo[j]];
				for (int i = 0; i < noOfSubjectInst.subjectObsNo[j]; i++) {
					timeForOneProfile[i] = Double
							.parseDouble(dataMatrix[i + sum][noOfSubjectInst.numberOfSortVariable]);
					concForOneProfile[i] = Double
							.parseDouble(dataMatrix[i + sum][noOfSubjectInst.numberOfSortVariable + 1]);
				}
				sum = sum + noOfSubjectInst.subjectObsNo[j];
				
				
				for (int i = 0; i < noOfSubjectInst.subjectObsNo[j]; i++) {
					timeForAllProfile[sum
							- noOfSubjectInst.subjectObsNo[j] + i] = timeForOneProfile[i];
					concForAllProfile[sum
							- noOfSubjectInst.subjectObsNo[j] + i] = concForOneProfile[i];
				}
			}
			else
			{

				timeForOneProfile = new double[noOfSubjectInst.subjectObsNo[j]];
				
				for (int i = 0; i < noOfSubjectInst.subjectObsNo[j]; i++) {
					timeForOneProfile[i] = Double
							.parseDouble(dataMatrix[i + sum][noOfSubjectInst.numberOfSortVariable]);
					
				}
				sum = sum + noOfSubjectInst.subjectObsNo[j];
				
				for (int i = 0; i < noOfSubjectInst.subjectObsNo[j]; i++) {
					timeForAllProfile[sum
							- noOfSubjectInst.subjectObsNo[j] + i] = timeForOneProfile[i];
					
				}

			
				
			}

			
		}
		
		updatingInfoToAppInfo(appInfo);


	}


	private void updatingInfoToAppInfo(ApplicationInfo appInfo) {
		
		ProcessingInputsInfo procinputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		procinputInst.getProfileAndParamInfoObj().setStartTimeForOneProfile(startTimeForOneProfile);
		
		procinputInst.getProfileAndParamInfoObj().setEndTimeForOneProfile(endTimeForOneProfile);
		
		procinputInst.getProfileAndParamInfoObj().setVolumeForOneProfile(volumeForOneProfile);
		
		procinputInst.getProfileAndParamInfoObj().setConcentrationForOneProfile(concentrationForOneProfile);		
		
		procinputInst.getProfileAndParamInfoObj().setTimeForOneProfile(timeForOneProfile);
		
		procinputInst.getProfileAndParamInfoObj().setConcForOneProfile(concForOneProfile);
		
		procinputInst.getProfileAndParamInfoObj().setTimeForAllProfile(timeForAllProfile);
		
		procinputInst.getProfileAndParamInfoObj().setConcForAllProfile(concForAllProfile);

		procinputInst.getProfileAndParamInfoObj().setMidPointForOneProfile(midPointForOneProfile);

		procinputInst.getProfileAndParamInfoObj().setExcretionRateForOneProfile(excretionRateForOneProfile);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procinputInst);
	
	}

	public void urineDataProcessing(double[] startTime,
			double[] endTime, double[] conc, double[] volume) {
		int noObs = 0;
		noObs = startTime.length;
		midPointForOneProfile = new double[noObs];
		excretionRateForOneProfile = new double[noObs];

		for (int i = 0; i < noObs; i++) {
			midPointForOneProfile[i] = (startTime[i] + endTime[i]) / 2;
			excretionRateForOneProfile[i] = conc[i] * volume[i]
					/ (endTime[i] - startTime[i]);
		}
	}

	public DataPreparationForAllComponentsForCA() {
		
		
	}
}
