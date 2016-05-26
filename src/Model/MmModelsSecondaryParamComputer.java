package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;

//import view.ApplicationInfo;

public class MmModelsSecondaryParamComputer {

	public double[] secondaryParameterValue;

	String[] defaultUnit;
	String[] preferredUnit;
	String[] allDefaultUnit;
	String[] allPreferredUnit;
	PkPdInfo pkpdInst;

	int noOfSecParam;

	int numberOfSortVariable;
	String[][] dataSortVariables;
	String[] secParamName;

	public MmModelsSecondaryParamComputer() {

	}

	public void secondaryParamCalForMmModel(double[] param, int modelNumber,
			double dose, double[][] aInverse, double sd2)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		pkpdInst = PkPdInfo.createPKPDInstance();
		initialize();

		secondaryParameterValue = new double[noOfSecParam];
		defaultUnit = new String[noOfSecParam];
		preferredUnit = new String[noOfSecParam];

		allDefaultUnit = pkpdInst.defaultUnitForCA;
		allPreferredUnit = pkpdInst.preferredUnitForCA;

		if (pkpdInst.ifSimulation == true) {
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput()
					.add(
							"\r\n\r\n"
									+ StringUtils
											.rightPad(
													"*************************************************"
															+ "**********************************",
													80) + "\r\n\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 30)
									/* + "\t" */
									+ StringUtils
											.rightPad(
													"SUMMARY OF ESTIMATED SECONDARY PARAMETERS",
													45) + "\r\n\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput().add(
							StringUtils.rightPad("", 5)
									+ StringUtils.rightPad("PARAMETERS", 20)
									+ "\t" + StringUtils.rightPad("UNIT", 20)
									+ "\t"
									+ StringUtils.rightPad("ESTIMATE", 20)
									+ "\t");

			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput().add(
							StringUtils.rightPad("", 5)
									+ StringUtils.rightPad("", 20) + "\t"
									+ StringUtils.rightPad("", 20) + "\t"
									+ StringUtils.rightPad("", 20)  + "\r\n");

		} else {
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				"\r\n\r\n"
						+ StringUtils.rightPad(
								"*************************************************"
										+ "**********************************",
								80) + "\r\n\r\n");
		pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils.rightPad("", 5)
								+ "\t"
								+ StringUtils
										.rightPad(
												"SUMMARY OF ESTIMATED SECONDARY PARAMETERS",
												45) + "\r\n\r\n");
		pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5)
						+ StringUtils.rightPad("PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad("UNIT", 20) + "\t"
						+ StringUtils.rightPad("ESTIMATE", 20) + "\r\n");
		}

		if (modelNumber == 1) {
			spCalculationForModel1(param, dose);
		} else if (modelNumber == 3) {
			spCalculationForModel3(param, dose);
		} else if (modelNumber == 4) {
			spCalculationForModel4(param, dose);
		}else if (modelNumber == 5) {
			spCalculationForModel5(param, dose);
		}

	}

	private void initialize() {

		noOfSecParam = pkpdInst.noOfSecParam;
		numberOfSortVariable = pkpdInst.numberOfSortVar;
		dataSortVariables = pkpdInst.dataSortVariables;
		secParamName = pkpdInst.secParamName;
	}

	private void spCalculationForModel1(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel1();
		calculateSPForModel1(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel1(double[] param, double dose) {
		secondaryParameterValue[0] = (dose / param[0])
				* (dose / param[0] / 2 + param[2]) / param[1];

	}

	private void copyDefaultAndPreferredUnitForModel1() {

		defaultUnit[0] = allDefaultUnit[5];
		preferredUnit[0] = allPreferredUnit[5];

	}

	private void spCalculationForModel3(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel3();
		calculateSPForModel3(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel3(double[] param) {
		secondaryParameterValue[0] = Math.log(2) / param[1];

	}

	private void copyDefaultAndPreferredUnitForModel3() {

		defaultUnit[0] = allDefaultUnit[4];
		preferredUnit[0] = allPreferredUnit[4];

	}

	private void spCalculationForModel4(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel4();
		calculateSPForModel4(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel4(double[] param) {
		secondaryParameterValue[0] = Math.log(2) / param[1];
	}

	private void copyDefaultAndPreferredUnitForModel4() {

		defaultUnit[0] = allDefaultUnit[3];
		preferredUnit[0] = allPreferredUnit[3];

	}

	private void spCalculationForModel5(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel5();
		calculateSPForModel5(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel5(double[] param) {
		secondaryParameterValue[0] =  param[0] + param[2];
		double cl1 = param[3] /param[4];
		secondaryParameterValue[1] =  secondaryParameterValue[0] / cl1;
		
	}

	private void copyDefaultAndPreferredUnitForModel5() {

		defaultUnit[0] = allDefaultUnit[0];
		preferredUnit[0] = allDefaultUnit[0];
		
		defaultUnit[1] = "";
		preferredUnit[1] = "";
		
		defaultUnit[2] = "";
		preferredUnit[2] = "";

	}

	
	
	
	public void writingOutputInSpreedSheetAndTextArea(String[] defaultUnit,
			String[] preferredUnit) throws RowsExceededException,
			WriteException, BiffException, IOException {

		
		ProcessingInputsInfo procInputInst = PkPdInfo.createPKPDInstance().copyProcessingInput();

		boolean ifSimulation = procInputInst.getModelInputTab1Obj().isSimulation();

		if (ifSimulation == true) {
			printSecondaryParamForSimulation(defaultUnit, preferredUnit);
		} else {
			printSecondaryParamForEstimation(defaultUnit, preferredUnit);
		}

	}

	private void printSecondaryParamForSimulation(String[] defaultUnit,
			String[] preferredUnit) {
		DefaultToPreferredUnitConverter unitConversionInst = new DefaultToPreferredUnitConverter();

		SimulationForLibraryModels simulationInst = SimulationForLibraryModels
				.createSimulationInst();
		PkPdInfo pkpdInstance = PkPdInfo.createPKPDInstance();
		
		
		for (int i = 0; i < noOfSecParam; i++) {
			double convertAmount;
			try {
				convertAmount = unitConversionInst.unitConversion(
						defaultUnit[i], preferredUnit[i]);

			} catch (Exception e) {
				convertAmount = 1;
			}

			simulationInst.wsOutput.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(secParamName[i], 20)
							+ "\t"
							+ StringUtils.rightPad(preferredUnit[i], 20)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									secondaryParameterValue[i] * convertAmount,
								false), 20) + "\r\n");

		}

	}

	public void printSecondaryParamForEstimation(String[] defaultUnit,
			String[] preferredUnit) throws RowsExceededException,
			WriteException, BiffException, IOException {

		DefaultToPreferredUnitConverter unitConversionInst = new DefaultToPreferredUnitConverter();

		for (int i = 0; i < noOfSecParam; i++) {
			double convertAmount;
			try {
				convertAmount = unitConversionInst.unitConversion(
						defaultUnit[i], preferredUnit[i]);

			} catch (Exception e) {
				convertAmount = 1;
			}
			String[] s = new String[numberOfSortVariable + 6];
			for (int k = 0; k < numberOfSortVariable; k++) {
				s[k] = dataSortVariables[pkpdInst.getCurrentProfileNumber()][k];
			}
			s[numberOfSortVariable] = secParamName[i];
			s[numberOfSortVariable + 1] = preferredUnit[i];
			s[numberOfSortVariable + 2] = pkpdInst.formatting(
					secondaryParameterValue[i] * convertAmount,
					false)
					+ "";

			pkpdInst.getNonTransposedSPAL().add(s);
			pkpdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(secParamName[i], 20)
							+ "\t"
							+ StringUtils.rightPad(preferredUnit[i], 20)
							+ "\t"
							+ StringUtils.rightPad(pkpdInst.formatting(
									secondaryParameterValue[i] * convertAmount,
									false)
									+ "", 20) + "\r\n");

		}

		String[] s = new String[numberOfSortVariable + noOfSecParam];
		for (int k = 0; k < numberOfSortVariable; k++) {
			s[k] = dataSortVariables[pkpdInst.getCurrentProfileNumber()][k];
		}

		for (int i = 0; i < noOfSecParam; i++) {
			double convertAmount = unitConversionInst.unitConversion(
					defaultUnit[i], preferredUnit[i]);

			s[numberOfSortVariable + i] = pkpdInst.formatting(
					secondaryParameterValue[i] * convertAmount,
					false)
					+ "";
		}

		pkpdInst.getSecondaryParametersAL().add(s);
	}

}
