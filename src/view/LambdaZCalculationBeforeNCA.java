package view;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import view.ApplicationInfo;
import view.DisplayContents;

import Common.DataManipulationFunctions;
import Model.NCA;

public class LambdaZCalculationBeforeNCA {

	double[] TimeForLambdaZ;
	double[] ConcForLambdaZ;
	double[] distinctObsForLambdaZ;
	double[] midPointForlambdaZ;
	double[] excretionRateForlambdaZ;
	int[] no_distinct_obs_sparsesampling;
	double[] infusionTimeForAllProfile;
	int[] methodNumberForAllProfile;
	double lambdaZ;
	double rsq;
	double adjRsq;
	int noPtsLambdaZ;
	double correlation;
	double lambdaZLower;
	double lambdaZUpper;
	double[] timeForPredVal;
	double[] predictedConc;
	ArrayList<String> intermediateOutputForLambdaZ;

	public LambdaZCalculationBeforeNCA() {
		TimeForLambdaZ = new double[1];
		ConcForLambdaZ = new double[1];
		distinctObsForLambdaZ = new double[1];
		midPointForlambdaZ = new double[1];
		excretionRateForlambdaZ = new double[1];
		no_distinct_obs_sparsesampling = new int[1];
		infusionTimeForAllProfile = new double[1];
		methodNumberForAllProfile = new int[1];
		lambdaZ = 0;
		rsq = 0;
		adjRsq = 0;
		noPtsLambdaZ = 0;
		correlation = 0;
		lambdaZLower = 0;
		lambdaZUpper = 0;
		timeForPredVal = new double[1];
		predictedConc = new double[1];
		intermediateOutputForLambdaZ = new ArrayList<String>();
	}

	public void calculateLambdaZBeforeNCA(int profileNumber) {

	}

	
	NcaCoreLambdaZCalc lzCalcMObj;
	DetermineNumberOfSubject detNoSubInst;
	DataPreparationForAllComponents dataPrepInst;
	NcaLambdaZDispGui ncaLamDispGui;
	void calculateLambdaZBeforeNCAUsingBestFit(int profileNumber, int methodNo,
			double startTime, double endTime, double infusionTime)
			throws Exception {
		
		
		ncaLamDispGui = NcaLambdaZDispGui.createLambDispGuiInstance();
		dataPrepInst = DataPreparationForAllComponents.createDataPrepAllCompInst();
		detNoSubInst = DetermineNumberOfSubject.createDetNoSubInst();
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		
		
		getTimeConcForThisProf(profileNumber);
		
		if(checkForConsecutiveTimePoints() == false){
			performLambdaZCalculation( profileNumber, methodNo,
					startTime, endTime, infusionTime);
		} else{
			JOptionPane.showMessageDialog(NCAMainScreen
					.createNcaMainScreenInstance().ncaMainInternalFrame,
					"Sorry! cannot process because more than 2 identical times", "Conform", JOptionPane.YES_OPTION);
		}

	}

	private void performLambdaZCalculation(int profileNumber, int methodNo, double startTime, double endTime, double infusionTime) throws Exception {

		lzCalcMObj = NcaCoreLambdaZCalc.createCoreLamInst();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		if(methodNo == 3){
			removeExcludedPoints();
		}
		
	
		
		//lambda Z calculation
		lzCalcMObj.lambdaZCalculation(TimeForLambdaZ, ConcForLambdaZ,
				startTime, endTime, infusionTime, methodNo, profileNumber); 
		
		// setting lambdaZ to appInfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setLambdaZ(lambdaZ);
		
		//setting rSquare value to appInfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setrSquare(rsq);
		
		//setting adjusted rsquare value to appinfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setAdjRSquare(adjRsq);
		
		//setting lambdaZ lower to appInfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setLambdaZLower(lambdaZLower);
		
		//setting lambdaZ upper to appInfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setLambdaZUpper(lambdaZUpper);
		
		//	setting correlation to appInfo 
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setCorrelation(correlation);
		
		//setting number of 
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setNoPtsLambdaZ(noPtsLambdaZ);
		
		//setting predicted concentration to appinfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setPredictedConc(predictedConc);
		
		//setting times of this profile in appInfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setTimeForPredVal(timeForPredVal);

		if (lambdaZ == 0) {
			NcaOptionsGui.createNcaOptionsInstance().rsquareTextField
					.setText("");
			NcaOptionsGui.createNcaOptionsInstance().rsquareTextField
					.setCaretPosition(0);
			NcaOptionsGui.createNcaOptionsInstance().adjustedRSquareTextField
					.setText("");
			NcaOptionsGui.createNcaOptionsInstance().adjustedRSquareTextField
					.setCaretPosition(0);
			NcaOptionsGui.createNcaOptionsInstance().numberOfTerminalPointsTextField
					.setText("");
			NcaOptionsGui.createNcaOptionsInstance().numberOfTerminalPointsTextField
					.setCaretPosition(0);

			NcaOptionsGui.createNcaOptionsInstance().halfLifeTextField
					.setText("");
			NcaOptionsGui.createNcaOptionsInstance().halfLifeTextField
					.setCaretPosition(0);

		} else {
			NcaOptionsGui.createNcaOptionsInstance().rsquareTextField
					.setText(DataManipulationFunctions.createDataManFuncInst()
							.round(rsq, 4)
							+ "");
			NcaOptionsGui.createNcaOptionsInstance().rsquareTextField
					.setCaretPosition(0);
			NcaOptionsGui.createNcaOptionsInstance().adjustedRSquareTextField
					.setText(DataManipulationFunctions.createDataManFuncInst()
							.round(adjRsq, 4)
							+ "");
			NcaOptionsGui.createNcaOptionsInstance().adjustedRSquareTextField
					.setCaretPosition(0);
			NcaOptionsGui.createNcaOptionsInstance().numberOfTerminalPointsTextField
					.setText(noPtsLambdaZ + "");
			NcaOptionsGui.createNcaOptionsInstance().numberOfTerminalPointsTextField
					.setCaretPosition(0);

			NcaOptionsGui.createNcaOptionsInstance().halfLifeTextField
					.setText(DataManipulationFunctions.createDataManFuncInst()
							.round((Math.log(2)/ lambdaZ), 4)
							+ "");
			NcaOptionsGui.createNcaOptionsInstance().halfLifeTextField
					.setCaretPosition(0);

		}
		
		
		
	}



	private void getTimeConcForThisProf(int profileNumber) throws RowsExceededException, WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int sum = 0;
		for (int i = 0; i < profileNumber; i++) {
			if (NcaOptionsGui.createNcaOptionsInstance().sparseCheckBox
					.isSelected() == false)
				sum = sum
						+ DetermineNumberOfSubject.createDetNoSubInst().subject_obs[i];
			else
				sum = sum
						+ DataPreparationForAllComponents
								.createDataPrepAllCompInst().no_distinct_obs_sparsesampling[i];
		}

		//if it is a serial sampling
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getNcaInfo().getProcessingInputs().getModelInputsObj().getisSparseSelected() == false) {
			
			
			TimeForLambdaZ = new double[DetermineNumberOfSubject
					.createDetNoSubInst().subject_obs[profileNumber]];
			
			ConcForLambdaZ = new double[DetermineNumberOfSubject
					.createDetNoSubInst().subject_obs[profileNumber]];
			
			
			
			for (int j = 0; j < DetermineNumberOfSubject.createDetNoSubInst().subject_obs[profileNumber]; j++) {
				if (NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZCalculationCombo
						.getSelectedIndex() == 3) {
					if ((Boolean) NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				/*17702230292	*/		.getValueAt(j, 2) == false) {
						TimeForLambdaZ[j] = 0;
						ConcForLambdaZ[j] = 0;
					} else {
						TimeForLambdaZ[j] = DataPreparationForAllComponents
								.createDataPrepAllCompInst().TimeForAllProfile[sum
								+ j];
						if(DataPreparationForAllComponents
								.createDataPrepAllCompInst().ConcForAllProfile[sum
								+ j]>=0){
							ConcForLambdaZ[j] = DataPreparationForAllComponents
							.createDataPrepAllCompInst().ConcForAllProfile[sum
							+ j];
						} else{
							ConcForLambdaZ[j] = 0;
						}
						
					}

				} else {

					TimeForLambdaZ[j] = DataPreparationForAllComponents
							.createDataPrepAllCompInst().TimeForAllProfile[sum
							+ j];
					
					if(DataPreparationForAllComponents
							.createDataPrepAllCompInst().ConcForAllProfile[sum
							+ j] >= 0){
						ConcForLambdaZ[j] = DataPreparationForAllComponents
						.createDataPrepAllCompInst().ConcForAllProfile[sum
						+ j];
					} else {
						ConcForLambdaZ[j] = 0;
					}
					

				}

			}
			sum = sum
					+ DetermineNumberOfSubject.createDetNoSubInst().subject_obs[profileNumber];
		} else { //it is a sparse sampling
			TimeForLambdaZ = new double[DataPreparationForAllComponents
					.createDataPrepAllCompInst().no_distinct_obs_sparsesampling[profileNumber]];
			ConcForLambdaZ = new double[DataPreparationForAllComponents
					.createDataPrepAllCompInst().no_distinct_obs_sparsesampling[profileNumber]];
			for (int j = 0; j < DataPreparationForAllComponents
					.createDataPrepAllCompInst().no_distinct_obs_sparsesampling[profileNumber]; j++) {
				if (NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZCalculationCombo
						.getSelectedIndex() == 3) {
					if ((Boolean) NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
							.getValueAt(j, 2) == false) {
						TimeForLambdaZ[j] = 0;
						ConcForLambdaZ[j] = 0;
					} else {
						TimeForLambdaZ[j] = DataPreparationForAllComponents
								.createDataPrepAllCompInst().TimeForAllProfile[sum
								+ j];
						if( DataPreparationForAllComponents
								.createDataPrepAllCompInst().ConcForAllProfile[sum
								+ j] >= 0){
							ConcForLambdaZ[j] = DataPreparationForAllComponents
							.createDataPrepAllCompInst().ConcForAllProfile[sum
							+ j];
						} else{
							ConcForLambdaZ[j] = 0;
						}
						
					}

				} else {

					TimeForLambdaZ[j] = DataPreparationForAllComponents
							.createDataPrepAllCompInst().TimeForAllProfile[sum
							+ j];
					if(DataPreparationForAllComponents
							.createDataPrepAllCompInst().ConcForAllProfile[sum
							+ j] >= 0){
						ConcForLambdaZ[j] = DataPreparationForAllComponents
						.createDataPrepAllCompInst().ConcForAllProfile[sum
						+ j];
					} else {
						ConcForLambdaZ[j] = 0;
					}
					

				}

			}
			sum = sum
					+ DataPreparationForAllComponents
							.createDataPrepAllCompInst().no_distinct_obs_sparsesampling[profileNumber];

		}
		
	}

	private boolean checkForConsecutiveTimePoints() {
		
		double[] allTimes = DataPreparationForAllComponents
		.createDataPrepAllCompInst().TimeForAllProfile;
		
		int i=0;
		while(i < allTimes.length -1){
			if(allTimes[i] == allTimes[i+1]){
				return true;
			} else{
				
			}
			i++;
		}
		
		return false;
	}

	private void removeExcludedPoints() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		int profileSelected = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex()).getNcaInfo()
		.getProcessingInputs().getLambdazDataobj().getProfileSelected();
		
		Hashtable<Integer, Boolean> hashTable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex()).getNcaInfo()
		.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileSelected].getInclusionExcusionPoints();
		
		TimeForLambdaZ = excludePoints(hashTable, TimeForLambdaZ);
		ConcForLambdaZ = excludePoints(hashTable, ConcForLambdaZ);
		
		
		
		
	}
	private boolean[] reverse(boolean[] pred) {
		int i=0;
		int j= pred.length-1;
		while(j>i){
			boolean temp = pred[i];
			pred[i] = pred[j];
			pred[j] = temp;
			i++;
			j--;
		}
		return pred;
	}

	
	@SuppressWarnings("unchecked")
	private double[] excludePoints(Hashtable<Integer, Boolean> hashTable, double[] TimeOrConcArray) {
		ArrayList<Double> onlyIncludedTimes = new ArrayList<Double>();
		
		boolean[] presence = new boolean[hashTable.entrySet().size()];
		Iterator it = hashTable.entrySet().iterator();
		int m=0;
		while(it.hasNext()){
			Map.Entry<Integer, Boolean> map = (Map.Entry<Integer, Boolean>)it.next();
			presence[m++] = map.getValue();
		}
		
		presence = reverse(presence);
		for(int k=0;k<presence.length;k++){
			if(presence[k] == true){
				onlyIncludedTimes.add(TimeOrConcArray[k]);
			}
		}
		
	
		
		TimeOrConcArray = new double[onlyIncludedTimes.size()];
		for(int i=0;i<onlyIncludedTimes.size();i++){
			TimeOrConcArray[i] = onlyIncludedTimes.get(i);
		}
		
		return TimeOrConcArray;
		
		
	}

	boolean calculationOfAllLambdaZUsingBestFit() throws Exception {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		
		infusionTimeForAllProfile = new double[DetermineNumberOfSubject
				.createDetNoSubInst().no_subject];
		methodNumberForAllProfile = new int[DetermineNumberOfSubject
				.createDetNoSubInst().no_subject];

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.isDisableCurveStripping() == false) {

			//this is where we have to check for consecutive time points 
			if(checkForConsecutiveTimePoints() == false){
				
			} else {
				return true;
			}

			int sum = 0;
			for (int i = 0; i < DetermineNumberOfSubject.createDetNoSubInst().no_subject; i++) {

				getTimeConcForThisProfile(i,sum);
				calculate(i);
				System.out.println();

			}
		} else {
			for (int i = 0; i < DetermineNumberOfSubject.createDetNoSubInst().no_subject; i++) {
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getLambdazDataobj()
						.getLambdaZDetails()[i].setLambdaZ(0);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getLambdazDataobj()
						.getLambdaZDetails()[i].setrSquare(0);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getLambdazDataobj()
						.getLambdaZDetails()[i].setAdjRSquare(0);
			}
		}
		return false;

	}

	private void calculate(int i) throws Exception {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NcaCoreLambdaZCalc lzCalcMObj = NcaCoreLambdaZCalc.createCoreLamInst();
		lzCalcMObj.lambdaZCalculation(TimeForLambdaZ, ConcForLambdaZ,
				0, 0, 0, 0, i);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj()
				.getLambdaZDetails()[i].setLambdaZ(lambdaZ);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj()
				.getLambdaZDetails()[i].setrSquare(rsq);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj()
				.getLambdaZDetails()[i].setAdjRSquare(adjRsq);

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj()
				.getLambdaZDetails()[i].setLambdaZLower(lambdaZLower);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj()
				.getLambdaZDetails()[i].setLambdaZUpper(lambdaZUpper);

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj()
				.getLambdaZDetails()[i].setCorrelation(correlation);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj()
				.getLambdaZDetails()[i].setNoPtsLambdaZ(noPtsLambdaZ);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj()
				.getLambdaZDetails()[i].setPredictedConc(predictedConc);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj()
				.getLambdaZDetails()[i]
				.setTimeForPredVal(timeForPredVal);
		
	}

	private void getTimeConcForThisProfile(int i, int sum) throws RowsExceededException, WriteException, BiffException, IOException {
		if (NcaOptionsGui.createNcaOptionsInstance().sparseCheckBox
				.isSelected() == false) {
			TimeForLambdaZ = new double[DetermineNumberOfSubject
					.createDetNoSubInst().subject_obs[i]];
			ConcForLambdaZ = new double[DetermineNumberOfSubject
					.createDetNoSubInst().subject_obs[i]];
			for (int j = 0; j < DetermineNumberOfSubject
					.createDetNoSubInst().subject_obs[i]; j++) {
				if (NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZCalculationCombo
						.getSelectedIndex() == 3) {
					if ((Boolean) NcaLambdaZDispGui
							.createLambDispGuiInstance().profileTable
							.getValueAt(j, 2) == false) {
						TimeForLambdaZ[j] = 0;
						ConcForLambdaZ[j] = 0;
					} else {
						TimeForLambdaZ[j] = DataPreparationForAllComponents
								.createDataPrepAllCompInst().TimeForAllProfile[sum
								+ j];
						if(DataPreparationForAllComponents
								.createDataPrepAllCompInst().ConcForAllProfile[sum
								+ j] >= 0){
							ConcForLambdaZ[j] = DataPreparationForAllComponents
							.createDataPrepAllCompInst().ConcForAllProfile[sum
							+ j];
						} else{
							ConcForLambdaZ[j] = 0;
						}
						
					}

				} else {

					TimeForLambdaZ[j] = DataPreparationForAllComponents
							.createDataPrepAllCompInst().TimeForAllProfile[sum
							+ j];
					if(DataPreparationForAllComponents
							.createDataPrepAllCompInst().ConcForAllProfile[sum
							+ j] >=0){
						ConcForLambdaZ[j] = DataPreparationForAllComponents
						.createDataPrepAllCompInst().ConcForAllProfile[sum
						+ j];
					} else {
						ConcForLambdaZ[j] = 0;
					}
					

				}

			}
			sum = sum
					+ DetermineNumberOfSubject.createDetNoSubInst().subject_obs[i];
		} else {
			TimeForLambdaZ = new double[DataPreparationForAllComponents
					.createDataPrepAllCompInst().no_distinct_obs_sparsesampling[i]];
			ConcForLambdaZ = new double[DataPreparationForAllComponents
					.createDataPrepAllCompInst().no_distinct_obs_sparsesampling[i]];
			for (int j = 0; j < DataPreparationForAllComponents
					.createDataPrepAllCompInst().no_distinct_obs_sparsesampling[i]; j++) {
				if (NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZCalculationCombo
						.getSelectedIndex() == 3) {
					if ((Boolean) NcaLambdaZDispGui
							.createLambDispGuiInstance().profileTable
							.getValueAt(j, 2) == false) {
						TimeForLambdaZ[j] = 0;
						ConcForLambdaZ[j] = 0;
					} else {
						TimeForLambdaZ[j] = DataPreparationForAllComponents
								.createDataPrepAllCompInst().TimeForAllProfile[sum
								+ j];
						if(DataPreparationForAllComponents
								.createDataPrepAllCompInst().ConcForAllProfile[sum
								+ j] >= 0){
							ConcForLambdaZ[j] = DataPreparationForAllComponents
							.createDataPrepAllCompInst().ConcForAllProfile[sum
							+ j];
						} else{
							ConcForLambdaZ[j] = 0;
						}
						
					}

				} else {

					TimeForLambdaZ[j] = DataPreparationForAllComponents
							.createDataPrepAllCompInst().TimeForAllProfile[sum
							+ j];
					ConcForLambdaZ[j] = DataPreparationForAllComponents
							.createDataPrepAllCompInst().ConcForAllProfile[sum
							+ j];

				}

			}
			sum = sum
					+ DataPreparationForAllComponents
							.createDataPrepAllCompInst().no_distinct_obs_sparsesampling[i];

		}
		
	}

}
