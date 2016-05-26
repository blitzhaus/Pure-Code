package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DeConvoLambdaZCalculation {

	double[] TimeForLambdaZ;
	double[] ConcForLambdaZ;
	double[] distinctObsForLambdaZ;
	double[] midPointForlambdaZ;
	double[] excretionRateForlambdaZ;
	int[] no_distinct_obs_sparsesampling;
	double[] infusionTimeForAllProfile;
	int[] methodNumberForAllProfile;
	public double lambdaZ;
	public double rsq;
	public double adjRsq;
	public int noPtsLambdaZ;
	public double correlation;
	public double lambdaZLower;
	public double lambdaZUpper;
	public double[] timeForPredVal;
	public double[] predictedConc;
	ArrayList<String> intermediateOutputForLambdaZ;

	private static DeConvoLambdaZCalculation DCONVO_LAMBDAZ;

	public static DeConvoLambdaZCalculation createLambdaZInstance() {
		if (DCONVO_LAMBDAZ == null) {
			DCONVO_LAMBDAZ = new DeConvoLambdaZCalculation();
		}
		return DCONVO_LAMBDAZ;
	}

	public DeConvoLambdaZCalculation() {
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

	DeConvoCoreLambdaZCal lzCalcMObj;
	CaNoOfSubjectDeterminer detNoSubInst;
	DataPreparationForAllComponentsForCA dataPrepInst;
	DeConvoLambdaZDispGui deConvoLamDispGui;

	void calculateLambdaZUsingBestFit(int profileNumber, int methodNo,
			double startTime, double endTime, double infusionTime)
			throws Exception {

		deConvoLamDispGui = DeConvoLambdaZDispGui.createLambDispGuiInstance();
		dataPrepInst = DataPreparationForAllComponentsForCA
				.createDetaPrepForCAInstance();
		detNoSubInst = CaNoOfSubjectDeterminer.createDetermineNoOfSubInstance();

		getTimeConcForThisProf(profileNumber);

		if (checkForConsecutiveTimePoints() == false) {
			performLambdaZCalculation(profileNumber, methodNo, startTime,
					endTime, infusionTime);
		} else {
			JOptionPane
					.showMessageDialog(
							NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame,
							"Sorry! cannot process because more than 2 identical times",
							"Conform", JOptionPane.YES_OPTION);
		}

	}

	private void performLambdaZCalculation(int profileNumber, int methodNo,
			double startTime, double endTime, double infusionTime)
			throws Exception {

		lzCalcMObj = DeConvoCoreLambdaZCal.createCoreLamInst();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		setObservedXYZerothProfile();
		// lambda Z calculation
		lzCalcMObj.lambdaZCalculation(TimeForLambdaZ, ConcForLambdaZ,
				startTime, endTime, infusionTime, methodNo, profileNumber);

		// setting lambdaZ to appInfo
		procInputInst.getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setLambdaZ(lambdaZ);

		// setting rSquare value to appInfo
		procInputInst.getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setrSquare(rsq);

		// setting adjusted rsquare value to appinfo
		procInputInst.getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setAdjRSquare(adjRsq);

		// setting lambdaZ lower to appInfo
		procInputInst.getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setLambdaZLower(lambdaZLower);

		// setting lambdaZ upper to appInfo
		procInputInst.getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setLambdaZUpper(lambdaZUpper);

		// setting correlation to appInfo
		procInputInst.getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setCorrelation(correlation);

		// setting number of
		procInputInst.getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setNoPtsLambdaZ(noPtsLambdaZ);

		// setting predicted concentration to appinfo
		procInputInst.getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setPredictedConc(predictedConc);

		// setting times of this profile in appInfo
		procInputInst.getLambdazDataobj().getLambdaZDetails()[profileNumber]
				.setTimeForPredVal(timeForPredVal);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	void setObservedXYZerothProfile() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		int selectedProfileNumber = procInputInst.getLambdazDataobj().getProfileSelected();

		if (procInputInst.getModelInputsObj()
				.getisSparseSelected() == false) {// serial
			// data
			// in this case there are no observations to
			// skip
			for (int k = 0; k < CaNoOfSubjectDeterminer.createDetermineNoOfSubInstance().subjectObsNo[selectedProfileNumber]; k++) {
				DeConvoLambdaZDispGui.createLambDispGuiInstance().observedX
						.add(DataPreparationForAllComponentsForCA.createDetaPrepForCAInstance()
								.timeForAllProfile[k]);
				if (DataPreparationForAllComponentsForCA.createDetaPrepForCAInstance().concForAllProfile[k] >= 0)
					DeConvoLambdaZDispGui.createLambDispGuiInstance().observedY
							.add(DataPreparationForAllComponentsForCA.createDetaPrepForCAInstance()
									.concForAllProfile[k]);
				else
					DeConvoLambdaZDispGui.createLambDispGuiInstance().observedY
							.add(0.0);
			}
		} 

	}

	private void getTimeConcForThisProf(int profileNumber)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		int sum = 0;
		for (int i = 0; i < profileNumber; i++) {

			sum = sum
					+ CaNoOfSubjectDeterminer.createDetermineNoOfSubInstance().subjectObsNo[i];

		}

		// if it is a serial sampling

		TimeForLambdaZ = new double[CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance().subjectObsNo[profileNumber]];

		ConcForLambdaZ = new double[CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance().subjectObsNo[profileNumber]];

		for (int j = 0; j < CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance().subjectObsNo[profileNumber]; j++) {
			if (DeConvoLambdaZDispGui.createLambDispGuiInstance().lambdaZCalculationCombo
					.getSelectedIndex() == 3) {

				TimeForLambdaZ[j] = DataPreparationForAllComponentsForCA
						.createDetaPrepForCAInstance().timeForAllProfile[sum
						+ j];
				if (DataPreparationForAllComponentsForCA
						.createDetaPrepForCAInstance().concForAllProfile[sum
						+ j] >= 0) {
					ConcForLambdaZ[j] = DataPreparationForAllComponentsForCA
							.createDetaPrepForCAInstance().concForAllProfile[sum
							+ j];
				} else {
					ConcForLambdaZ[j] = 0;
				}

			} else {

				TimeForLambdaZ[j] = DataPreparationForAllComponentsForCA
						.createDetaPrepForCAInstance().timeForAllProfile[sum
						+ j];

				if (DataPreparationForAllComponentsForCA
						.createDetaPrepForCAInstance().concForAllProfile[sum
						+ j] >= 0) {
					ConcForLambdaZ[j] = DataPreparationForAllComponentsForCA
							.createDetaPrepForCAInstance().concForAllProfile[sum
							+ j];
				} else {
					ConcForLambdaZ[j] = 0;
				}

			}

		}
		sum = sum
				+ CaNoOfSubjectDeterminer.createDetermineNoOfSubInstance().subjectObsNo[profileNumber];

	}

	private boolean checkForConsecutiveTimePoints() {

		double[] allTimes = DataPreparationForAllComponentsForCA
				.createDetaPrepForCAInstance().timeForAllProfile;

		int i = 0;
		while (i < allTimes.length - 1) {
			if (allTimes[i] == allTimes[i + 1]) {
				return true;
			} else {

			}
			i++;
		}

		return false;
	}

	private boolean[] reverse(boolean[] pred) {
		int i = 0;
		int j = pred.length - 1;
		while (j > i) {
			boolean temp = pred[i];
			pred[i] = pred[j];
			pred[j] = temp;
			i++;
			j--;
		}
		return pred;
	}

	boolean calculationOfAllLambdaZUsingBestFit() throws Exception {
		infusionTimeForAllProfile = new double[CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance().noOfSubject];
		methodNumberForAllProfile = new int[CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance().noOfSubject];

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		if (procInputInst.getModelInputsObj().isDisableCurveStripping() == false) {

			// this is where we have to check for consecutive time points
			if (checkForConsecutiveTimePoints() == false) {

			} else {
				return true;
			}

			int sum = 0;
			
			for (int i = 0; i < CaNoOfSubjectDeterminer
					.createDetermineNoOfSubInstance().noOfSubject; i++) {

				getTimeConcForThisProfile(i, sum);
				calculate(i);

			}
		} else {
			for (int i = 0; i < CaNoOfSubjectDeterminer
					.createDetermineNoOfSubInstance().noOfSubject; i++) {
				procInputInst.getLambdazDataobj().getLambdaZDetails()[i]
						.setLambdaZ(0);
				procInputInst.getLambdazDataobj().getLambdaZDetails()[i]
						.setrSquare(0);
				procInputInst.getLambdazDataobj().getLambdaZDetails()[i]
						.setAdjRSquare(0);

			}
		}
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
		return false;

	}

	private void calculate(int i) throws Exception {
		NcaCoreLambdaZCalc lzCalcMObj = NcaCoreLambdaZCalc.createCoreLamInst();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		
		lzCalcMObj.lambdaZCalculation(TimeForLambdaZ, ConcForLambdaZ, 0, 0, 0,
				0);
		procInputInst.getLambdazDataobj().getLambdaZDetails()[i]
				.setLambdaZ(lambdaZ);
		procInputInst.getLambdazDataobj().getLambdaZDetails()[i]
				.setrSquare(rsq);
		procInputInst.getLambdazDataobj().getLambdaZDetails()[i]
				.setAdjRSquare(adjRsq);
		procInputInst.getLambdazDataobj().getLambdaZDetails()[i]
				.setLambdaZLower(lambdaZLower);
		procInputInst.getLambdazDataobj().getLambdaZDetails()[i]
				.setLambdaZUpper(lambdaZUpper);
		procInputInst.getLambdazDataobj().getLambdaZDetails()[i]
				.setCorrelation(correlation);
		procInputInst.getLambdazDataobj().getLambdaZDetails()[i]
				.setNoPtsLambdaZ(noPtsLambdaZ);
		procInputInst.getLambdazDataobj().getLambdaZDetails()[i]
				.setPredictedConc(predictedConc);
		procInputInst.getLambdazDataobj().getLambdaZDetails()[i]
				.setTimeForPredVal(timeForPredVal);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	private void getTimeConcForThisProfile(int i, int sum)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		TimeForLambdaZ = new double[CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance().subjectObsNo[i]];
		ConcForLambdaZ = new double[CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance().subjectObsNo[i]];

		for (int j = 0; j < CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance().subjectObsNo[i]; j++) {
			if (DeConvoLambdaZDispGui.createLambDispGuiInstance().lambdaZCalculationCombo
					.getSelectedIndex() == 3) {
				TimeForLambdaZ[j] = DataPreparationForAllComponentsForCA
						.createDetaPrepForCAInstance().timeForAllProfile[sum
						+ j];
				if (DataPreparationForAllComponentsForCA
						.createDetaPrepForCAInstance().concForAllProfile[sum
						+ j] >= 0) {
					ConcForLambdaZ[j] = DataPreparationForAllComponentsForCA
							.createDetaPrepForCAInstance().concForAllProfile[sum
							+ j];
				} else {
					ConcForLambdaZ[j] = 0;
				}

			} else {

				TimeForLambdaZ[j] = DataPreparationForAllComponentsForCA
						.createDetaPrepForCAInstance().timeForAllProfile[sum
						+ j];
				if (DataPreparationForAllComponentsForCA
						.createDetaPrepForCAInstance().concForAllProfile[sum
						+ j] >= 0) {
					ConcForLambdaZ[j] = DataPreparationForAllComponentsForCA
							.createDetaPrepForCAInstance().concForAllProfile[sum
							+ j];
				} else {
					ConcForLambdaZ[j] = 0;
				}
			}
		}
		sum = sum
				+ CaNoOfSubjectDeterminer.createDetermineNoOfSubInstance().subjectObsNo[i];

	}

}
