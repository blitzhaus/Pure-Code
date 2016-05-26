package Model;

import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Jama.Matrix;
import Model.PkParamEstimator;
import Model.PkPdInfo;

import org.apache.commons.lang.StringUtils;

import view.ApplicationInfo;
import view.ParameterAndUnitListLoader;
import view.ProcessingInputsInfo;

public class SecondaryParamComputer {

	double[] tValue = { 12.706, 4.303, 3.182, 2.776, 2.571, 2.447, 2.365,
			2.306, 2.262, 2.228, 2.201, 2.179, 2.160, 2.145, 2.131, 2.120,
			2.110, 2.101, 2.093, 2.086, 2.080, 2.074, 2.069, 2.064, 2.060, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	double[] secondaryParameterValue;
	String[] defaultUnit;
	String[] preferredUnit;
	String[] allDefaultUnit;
	String[] allPreferredUnit;
	PkPdInfo pkpdInstance;

	int noOfSecParam;
	ApplicationInfo appInfo;
	int numberOfSortVariable;
	String[][] dataSortVariables;
	String[] secParamName;
	double[][] aInv;
	double SD;
	boolean clearancaParam;
	ProcessingInputsInfo procInputInst;

	static SecondaryParamComputer secondaryParamCal = null;

	public static SecondaryParamComputer createSecParamCalInstance() {
		if (secondaryParamCal == null) {
			secondaryParamCal = new SecondaryParamComputer();
		}
		return secondaryParamCal;
	}

	public SecondaryParamComputer() {

	}
	
	PkModelSecondaryParamComputer pkSecondaryParamCalInst;
	PDModelsSecondaryParamComputer pdModelInst;
	MmModelsSecondaryParamComputer mmModelInst;
	PkPdLinkModelsSecondaryParamComputer pkpdLindModelInst;
	AsciiModelSecondaryParamComputer asciiSecondaryParamCalInst;
	

	public void calculateSecondaryParameter(double[] param, int modelNumber,
			double dose, double[][] aInverse, double sd)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		
		pkpdInstance = PkPdInfo.createPKPDInstance();
		procInputInst = pkpdInstance.copyProcessingInput();
		
		if (pkpdInstance.analysisType.equals("pk")) {
			pkSecondaryParamCalInst = new PkModelSecondaryParamComputer();
			
			pkSecondaryParamCalInst.secondaryParamCalForPKModel(param, modelNumber, dose, aInverse, sd);
		} else if (pkpdInstance.analysisType.equals("pd")) {
			pdModelInst = new PDModelsSecondaryParamComputer();
			pdModelInst.secondaryParamCalForPdModel(param, modelNumber, dose,
					aInverse, sd);

		} else if (pkpdInstance.analysisType.equals("mm")) {
			mmModelInst = new MmModelsSecondaryParamComputer();
			mmModelInst.secondaryParamCalForMmModel(param, modelNumber, dose,
					aInverse, sd);
		} else if (pkpdInstance.analysisType.equals("irm")) {
			
		}else if (pkpdInstance.analysisType.equals("pkpdlink")) {
			pkpdLindModelInst = new PkPdLinkModelsSecondaryParamComputer();
			pkpdLindModelInst.secondaryParamCalForPkPdLinkModel(param, modelNumber, dose,
					aInverse, sd);

		}else if (pkpdInstance.analysisType.equals("ascii")) {
			asciiSecondaryParamCalInst = new AsciiModelSecondaryParamComputer();
			
			asciiSecondaryParamCalInst.secondaryParamCalForAsciiModel(param, pkpdInstance.currentProfileNumber, aInverse, sd);
		}
			

	}

	private void secondaryParamCalForPKModel(double[] param, int modelNumber,
			double dose, double[][] aInverse, double sd2)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		PkParamEstimator caParamCalInst = PkParamEstimator
				.createPkParamEstimateInstance();
		double lengthOfInfusion = caParamCalInst.infusionTime[0]
				- caParamCalInst.dosingTime[0];

		if (clearancaParam == true) {
			ClearanceSecondaryParamComputer inst = new ClearanceSecondaryParamComputer();
			inst.secondaryParamCalForClearanceParam(dose, modelNumber,
					lengthOfInfusion, pkpdInstance.parameter);
		} else {

			if (modelNumber == 1) {
				spCalculationForModel1(param, dose, aInv, SD);

			} else if (modelNumber == 2) {
				spCalculationForModel2(param, dose, lengthOfInfusion);
			} else if (modelNumber == 3) {
				spCalculationForModel3(param, dose);
			} else if (modelNumber == 4) {
				spCalculationForModel4(param, dose);
			} else if (modelNumber == 5) {
				spCalculationForModel5(param, dose);
			} else if (modelNumber == 6) {
				spCalculationForModel6(param, dose);
			} else if (modelNumber == 7) {
				spCalculationForModel7(param, dose);
			} else if (modelNumber == 8) {
				spCalculationForModel8(param, dose);
			} else if (modelNumber == 9) {
				spCalculationForModel9(param, dose, lengthOfInfusion);
			} else if (modelNumber == 10) {
				spCalculationForModel10(param, dose, lengthOfInfusion);
			} else if (modelNumber == 11) {
				spCalculationForModel11(param, dose);

			} else if (modelNumber == 12) {
				spCalculationForModel12(param, dose);
			} else if (modelNumber == 13) {
				spCalculationForModel13(param, dose);
			} else if (modelNumber == 14) {
				spCalculationForModel14(param, dose);
			} else if (modelNumber == 15) {
				spCalculationForModel15(param, dose);
			} else if (modelNumber == 16) {
				spCalculationForModel16(param, dose);
			} else if (modelNumber == 17) {
				spCalculationForModel17(param, dose);
			} else if (modelNumber == 18) {
				spCalculationForModel18(param, dose);
			} else if (modelNumber == 19) {
				spCalculationForModel19(param, dose, lengthOfInfusion);
			}
		}

	}

	private void headerForSecondarParameters() {

		

		if (pkpdInstance.ifSimulation == true) {
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
									+ StringUtils.rightPad("", 20) + "\t"
									+ StringUtils.rightPad("", 20) + "\t"
									+ StringUtils.rightPad("", 20) + "\r\n");

		} else {
			
			pkpdInstance.workSheetOutputInst
					.getTextoutput()
					.add(
							"\r\n\r\n"
									+ StringUtils
											.rightPad(
													"*************************************************"
															+ "**********************************",
													80) + "\r\n\r\n");
			pkpdInstance.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils.rightPad("", 30)
									/* + "\t" */
									+ StringUtils
											.rightPad(
													"SUMMARY OF ESTIMATED SECONDARY PARAMETERS",
													45) + "\r\n\r\n");
			pkpdInstance.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad("PARAMETERS", 20) + "\t"
							+ StringUtils.rightPad("UNIT", 20) + "\t"
							+ StringUtils.rightPad("ESTIMATE", 20) + "\t"
							+ StringUtils.rightPad("STDERROR", 20) + "\t"
							+ StringUtils.rightPad("CV%", 20) + "\r\n");

			pkpdInstance.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5) + StringUtils.rightPad("", 20)
							+ "\t" + StringUtils.rightPad("", 20) + "\t"
							+ StringUtils.rightPad("", 20) + "\t"
							+ StringUtils.rightPad("", 20) + "\t"
							+ StringUtils.rightPad("", 20) + "\r\n");

		}

	}

	private void spCalculationForModel19(double[] param, double dose,
			double lengthOfInfusion) throws RowsExceededException,
			WriteException, BiffException, IOException {
		copyDefaultAndPreferredUnitForModel19();
		calculateSPForModel19(param, dose, lengthOfInfusion);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel19(double[] param, double dose,
			double lengthOfInfusion) {

		double ti = lengthOfInfusion;

		double k0 = dose / ti;
		double a1 = k0 * (param[1] - param[3]) * (param[2] - param[3]);
		a1 = a1
				/ (param[0] * param[3] * (param[5] - param[3]) * (param[4] - param[3]));
		double b1 = k0 * (param[1] - param[4]) * (param[2] - param[4]);
		b1 = b1
				/ (param[0] * param[4] * (param[5] - param[4]) * (param[3] - param[4]));
		double c1 = k0 * (param[1] - param[5]) * (param[2] - param[5]);
		c1 = c1
				/ (param[0] * param[5] * (param[3] - param[5]) * (param[4] - param[5]));

		secondaryParameterValue[0] = a1 * (1.0 - Math.exp((-param[3]) * ti))
				+ b1 * (1.0 - Math.exp((-param[4]) * ti)) + c1
				* (1.0 - Math.exp((-param[5]) * ti));

		secondaryParameterValue[1] = param[3] * param[4] * param[5]
				/ (param[1] * param[2]);

		double e1 = param[3] * param[4] + param[3] * param[5] + param[4]
				* param[5];
		double e2 = param[1] * (param[3] + param[4] + param[5]);

		double e3 = e1 - e2 - secondaryParameterValue[1] * param[2] + param[1]
				* param[1];

		secondaryParameterValue[2] = e3 / (param[2] - param[1]);

		secondaryParameterValue[3] = (param[3] + param[4] + param[5])
				- (secondaryParameterValue[1] + secondaryParameterValue[2]
						+ param[1] + param[2]);

		secondaryParameterValue[4] = dose * (param[1] - param[3])
				* (param[3] - param[2])
				/ (param[0] * (param[5] - param[3]) * (param[3] - param[4]));
		secondaryParameterValue[5] = dose * (param[1] - param[4])
				* (param[2] - param[4])
				/ (param[0] * (param[5] - param[4]) * (param[3] - param[4]));
		secondaryParameterValue[6] = dose * (param[1] - param[5])
				* (param[2] - param[5])
				/ (param[0] * (param[5] - param[3]) * (param[5] - param[4]));

		secondaryParameterValue[7] = Math.log(2) / secondaryParameterValue[1];

		secondaryParameterValue[8] = Math.log(2) / param[3];

		secondaryParameterValue[9] = Math.log(2) / param[4];

		secondaryParameterValue[10] = Math.log(2) / param[5];

		secondaryParameterValue[11] = (secondaryParameterValue[4] / param[3])
				+ (secondaryParameterValue[5] / param[4])
				+ (secondaryParameterValue[6] / param[5]);

		secondaryParameterValue[12] = dose / secondaryParameterValue[11];

		double aumcb = (secondaryParameterValue[4] / (param[3] * param[3])
				+ secondaryParameterValue[5] / (param[4] * param[4]) + secondaryParameterValue[6]
				/ (param[5] * param[5]));

		secondaryParameterValue[13] = aumcb + ti * secondaryParameterValue[11]
				/ 2.0;
		secondaryParameterValue[14] = (secondaryParameterValue[13] / secondaryParameterValue[11])
				- ti / 2;

		secondaryParameterValue[15] = dose
				* ((secondaryParameterValue[13] / secondaryParameterValue[11]) - ti / 2.0)
				/ secondaryParameterValue[11];

		secondaryParameterValue[16] = secondaryParameterValue[2] * param[0];

		secondaryParameterValue[17] = secondaryParameterValue[16] / param[1];

		secondaryParameterValue[18] = secondaryParameterValue[3] * param[0];

		secondaryParameterValue[19] = secondaryParameterValue[18] / param[2];

	}

	private void copyDefaultAndPreferredUnitForModel19() {

		defaultUnit[0] = allDefaultUnit[8];
		preferredUnit[0] = allPreferredUnit[8];

		for (int i = 1; i < 4; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		for (int i = 4; i < 7; i++) {
			defaultUnit[i] = allDefaultUnit[1];
			preferredUnit[i] = allPreferredUnit[1];
		}

		for (int i = 7; i < 11; i++) {
			defaultUnit[i] = allDefaultUnit[6];
			preferredUnit[i] = allPreferredUnit[6];
		}

		defaultUnit[11] = allDefaultUnit[4];
		preferredUnit[11] = allPreferredUnit[4];

		defaultUnit[12] = allDefaultUnit[3];
		preferredUnit[12] = allPreferredUnit[3];

		defaultUnit[13] = allDefaultUnit[5];
		preferredUnit[13] = allPreferredUnit[5];

		defaultUnit[14] = allDefaultUnit[9];
		preferredUnit[14] = allPreferredUnit[9];

		defaultUnit[15] = allDefaultUnit[2];
		preferredUnit[15] = allPreferredUnit[2];

		defaultUnit[16] = allDefaultUnit[2];
		preferredUnit[16] = allPreferredUnit[2];

		defaultUnit[17] = allDefaultUnit[3];
		preferredUnit[17] = allPreferredUnit[3];

		defaultUnit[18] = allDefaultUnit[2];
		preferredUnit[18] = allPreferredUnit[2];

		defaultUnit[19] = allDefaultUnit[3];
		preferredUnit[19] = allPreferredUnit[3];

	}

	private void spCalculationForModel18(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel18();
		calculateSPForModel18(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel18(double[] param, double dose) {
		secondaryParameterValue[0] = (param[0] + param[1] + param[2]);

		secondaryParameterValue[1] = dose / (param[0] + param[1] + param[2]);

		double e1 = 1;
		double e2 = (param[5] * param[1] + param[5] * param[0] + param[4]
				* param[2] + param[4] * param[0] + param[3] * param[2] + param[3]
				* param[1]);
		e2 = (-1) * e2 / secondaryParameterValue[0];
		double e3 = 1
				* (param[3] * param[5] * param[1] + param[5] * param[4]
						* param[0] + param[3] * param[4] * param[2])
				/ secondaryParameterValue[0];

		secondaryParameterValue[2] = 0.5 * ((-e2) + Math.sqrt(e2 * e2 - 4 * e3));

		secondaryParameterValue[3] = .5 * ((-e2) - Math.sqrt(e2 * e2 - 4 * e3));
		secondaryParameterValue[4] = param[3] * param[4] * param[5]
				/ (secondaryParameterValue[2] * secondaryParameterValue[3]);

		e1 = param[3] * param[4] + param[3] * param[5] + param[4] * param[5];
		e2 = secondaryParameterValue[2] * (param[3] + param[4] + param[5]);
		e3 = e1 - e2 - secondaryParameterValue[4] * secondaryParameterValue[3]
				+ secondaryParameterValue[2] * secondaryParameterValue[2];

		secondaryParameterValue[5] = e3
				/ (secondaryParameterValue[3] - secondaryParameterValue[2]);

		secondaryParameterValue[6] = (param[3] + param[4] + param[5])
				- (secondaryParameterValue[4] + secondaryParameterValue[5]
						+ secondaryParameterValue[2] + secondaryParameterValue[3]);

		secondaryParameterValue[7] = Math.log(2) / secondaryParameterValue[4];

		secondaryParameterValue[8] = Math.log(2) / param[3];

		secondaryParameterValue[9] = Math.log(2) / param[4];

		secondaryParameterValue[10] = Math.log(2) / param[5];

		secondaryParameterValue[11] = ((param[0] / param[3])
				+ (param[1] / param[4]) + (param[2] / param[5]));

		secondaryParameterValue[12] = dose / secondaryParameterValue[11];

		secondaryParameterValue[13] = (param[0] / (param[3] * param[3])
				+ param[1] / (param[4] * param[4]) + param[2]
				/ (param[5] * param[5]));
		secondaryParameterValue[14] = secondaryParameterValue[13]
				/ secondaryParameterValue[11];

		secondaryParameterValue[15] = secondaryParameterValue[12]
				* secondaryParameterValue[14];


		secondaryParameterValue[17] = secondaryParameterValue[5]
					* secondaryParameterValue[1];

			secondaryParameterValue[16] = secondaryParameterValue[17]
					/ secondaryParameterValue[2];

			secondaryParameterValue[19] = secondaryParameterValue[6]
					* secondaryParameterValue[1];

			secondaryParameterValue[18] = secondaryParameterValue[19]
					/ secondaryParameterValue[3];

	}

	private void copyDefaultAndPreferredUnitForModel18() {

		defaultUnit[0] = allDefaultUnit[8];
		preferredUnit[0] = allPreferredUnit[8];

		defaultUnit[1] = allDefaultUnit[2];
		preferredUnit[1] = allPreferredUnit[2];

		for (int i = 2; i < 7; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		for (int i = 7; i < 11; i++) {
			defaultUnit[i] = allDefaultUnit[6];
			preferredUnit[i] = allPreferredUnit[6];
		}

		defaultUnit[11] = allDefaultUnit[4];
		preferredUnit[11] = allPreferredUnit[4];

		defaultUnit[12] = allDefaultUnit[3];
		preferredUnit[12] = allPreferredUnit[3];

		defaultUnit[13] = allDefaultUnit[5];
		preferredUnit[13] = allPreferredUnit[5];

		defaultUnit[14] = allDefaultUnit[9];
		preferredUnit[14] = allPreferredUnit[9];

		defaultUnit[15] = allDefaultUnit[2];
		preferredUnit[15] = allPreferredUnit[2];

		defaultUnit[16] = allDefaultUnit[2];
		preferredUnit[16] = allPreferredUnit[2];

		defaultUnit[17] = allDefaultUnit[3];
		preferredUnit[17] = allPreferredUnit[3];

		defaultUnit[18] = allDefaultUnit[2];
		preferredUnit[18] = allPreferredUnit[2];

		defaultUnit[19] = allDefaultUnit[3];
		preferredUnit[19] = allPreferredUnit[3];

	}

	private void spCalculationForModel17(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel17();
		calculateSPForModel17(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel17(double[] param, double dose) {

		secondaryParameterValue[2] = (param[0] * param[3] + param[1] * param[2])
				/ (param[0] + param[1]);

		secondaryParameterValue[0] = param[2] * param[3]
				/ secondaryParameterValue[2];

		secondaryParameterValue[1] = param[2] + param[3]
				- secondaryParameterValue[2] - secondaryParameterValue[0];

		secondaryParameterValue[3] = Math.log(2) / secondaryParameterValue[0];
		secondaryParameterValue[4] = Math.log(2) / param[2];

		secondaryParameterValue[5] = Math.log(2) / param[3];

		secondaryParameterValue[6] = dose / (param[0] + param[1]);

		secondaryParameterValue[7] = dose
				/ (param[0] / param[2] + param[1] / param[3]);

		secondaryParameterValue[8] = secondaryParameterValue[6]
				* secondaryParameterValue[1] / secondaryParameterValue[2];

		secondaryParameterValue[9] = secondaryParameterValue[6]
				* secondaryParameterValue[1];

	}

	private void copyDefaultAndPreferredUnitForModel17() {

		for (int i = 0; i < 3; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		for (int i = 3; i < 6; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		defaultUnit[6] = allDefaultUnit[2];
		preferredUnit[6] = allPreferredUnit[2];

		defaultUnit[7] = allDefaultUnit[3];
		preferredUnit[7] = allPreferredUnit[3];

		defaultUnit[8] = allDefaultUnit[2];
		preferredUnit[8] = allPreferredUnit[2];

		defaultUnit[9] = allDefaultUnit[3];
		preferredUnit[9] = allPreferredUnit[3];

	}

	private void spCalculationForModel16(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel16();
		calculateSPForModel16(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel16(double[] param, double dose) {

		secondaryParameterValue[0] = Math.log(2) / param[1];

		secondaryParameterValue[2] = (1 / 2.0)
				* (param[1] + param[2] + param[3] - Math.sqrt(Math.pow(
						(param[1] + param[2] + param[3]), 2)
						- 4 * param[3] * param[1]));

		secondaryParameterValue[1] = param[1] * param[3]
				/ secondaryParameterValue[2];

		secondaryParameterValue[3] = Math.log(2) / secondaryParameterValue[1];
		secondaryParameterValue[4] = Math.log(2) / secondaryParameterValue[2];

		secondaryParameterValue[5] = (dose / param[0])
				* (secondaryParameterValue[1] - param[3])
				/ (secondaryParameterValue[1] - secondaryParameterValue[2]);

		secondaryParameterValue[6] = (dose / param[0])
				* (secondaryParameterValue[2] - param[3])
				/ (secondaryParameterValue[2] - secondaryParameterValue[1]);

		secondaryParameterValue[7] = param[0] * param[1];

		secondaryParameterValue[8] = param[0] * param[2] / param[3];

		secondaryParameterValue[9] = param[2] * param[0];

	}

	private void copyDefaultAndPreferredUnitForModel16() {

		defaultUnit[0] = allDefaultUnit[6];
		preferredUnit[0] = allPreferredUnit[6];

		defaultUnit[1] = allDefaultUnit[0];
		preferredUnit[1] = allPreferredUnit[0];

		defaultUnit[2] = allDefaultUnit[0];
		preferredUnit[2] = allPreferredUnit[0];

		defaultUnit[3] = allDefaultUnit[6];
		preferredUnit[3] = allPreferredUnit[6];

		defaultUnit[4] = allDefaultUnit[6];
		preferredUnit[4] = allPreferredUnit[6];

		defaultUnit[5] = allDefaultUnit[1];
		preferredUnit[5] = allPreferredUnit[1];

		defaultUnit[6] = allDefaultUnit[1];
		preferredUnit[6] = allPreferredUnit[1];

		defaultUnit[7] = allDefaultUnit[3];
		preferredUnit[7] = allPreferredUnit[3];

		defaultUnit[8] = allDefaultUnit[2];
		preferredUnit[8] = allPreferredUnit[2];

		defaultUnit[9] = allDefaultUnit[3];
		preferredUnit[9] = allPreferredUnit[3];

	}

	private void spCalculationForModel15(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel15();
		calculateSPForModel15(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel15(double[] param, double dose) {

		secondaryParameterValue[0] = param[0] * param[1];

		secondaryParameterValue[1] = Math.log(2) / param[1];

	}

	private void copyDefaultAndPreferredUnitForModel15() {

		defaultUnit[0] = allDefaultUnit[3];
		preferredUnit[0] = allPreferredUnit[3];

		defaultUnit[1] = allDefaultUnit[6];
		preferredUnit[1] = allPreferredUnit[6];

	}

	private void spCalculationForModel14(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel14();
		calculateSPForModel14(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel14(double[] param, double dose) {double temp = (param[0] * (param[2] - param[3]) + param[1]
	                                                                                                 						* (param[2] - param[4]));

	secondaryParameterValue[2] =  (param[0] * param[4] * param[2] + param[1] * param[3]
					* param[2] - (param[0] + param[1]) * param[3]
					* param[4]) / temp;
	secondaryParameterValue[0] = param[3] * param[4]
	                              				/ secondaryParameterValue[2];
	secondaryParameterValue[1] = param[3]+param[4]-secondaryParameterValue[0]-secondaryParameterValue[2];

	

	double v = param[2] *dose /temp;
	secondaryParameterValue[3] = dose / v / secondaryParameterValue[0];
	secondaryParameterValue[4] = Math.log(2) / param[2];

	secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[0];

	secondaryParameterValue[6] = Math.log(2) / param[3];

	secondaryParameterValue[7] = Math.log(2) / param[4];

	secondaryParameterValue[8] = v;

	secondaryParameterValue[9] = dose / secondaryParameterValue[3];

	secondaryParameterValue[11] = secondaryParameterValue[1] * v;

	secondaryParameterValue[10] = secondaryParameterValue[11]
			/ secondaryParameterValue[2];
	}

	private void copyDefaultAndPreferredUnitForModel14() {

		for (int i = 0; i < 3; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		defaultUnit[3] = allDefaultUnit[4];
		preferredUnit[3] = allPreferredUnit[4];

		for (int i = 4; i < 8; i++) {
			defaultUnit[i] = allDefaultUnit[6];
			preferredUnit[i] = allPreferredUnit[6];
		}

		defaultUnit[8] = allDefaultUnit[2];
		preferredUnit[8] = allPreferredUnit[2];

		defaultUnit[9] = allDefaultUnit[3];
		preferredUnit[9] = allPreferredUnit[3];

		defaultUnit[10] = allDefaultUnit[2];
		preferredUnit[10] = allPreferredUnit[2];

		defaultUnit[11] = allDefaultUnit[3];
		preferredUnit[11] = allPreferredUnit[3];

		defaultUnit[12] = allDefaultUnit[7];
		preferredUnit[12] = allPreferredUnit[7];

		defaultUnit[13] = allDefaultUnit[8];
		preferredUnit[13] = allPreferredUnit[8];

	}

	private void spCalculationForModel1(double[] param, double dose,
			double[][] aInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		copyDefaultAndPreferredUnitForModel1();
		calculateSPForModel1(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel1(double[] param, double dose) {

		secondaryParameterValue[0] = dose / (param[0] * param[1]);

		secondaryParameterValue[1] = Math.log(2) / param[1];
		secondaryParameterValue[2] = dose / param[0];
		secondaryParameterValue[3] = param[0] * param[1];
		secondaryParameterValue[4] = dose / (param[0] * param[1] * param[1]);
		secondaryParameterValue[5] = secondaryParameterValue[4]
				/ secondaryParameterValue[0];
		secondaryParameterValue[6] = secondaryParameterValue[5]
				* secondaryParameterValue[3];

	}

	private void copyDefaultAndPreferredUnitForModel1() {

		defaultUnit[0] = allDefaultUnit[4];
		preferredUnit[0] = allPreferredUnit[4];

		defaultUnit[1] = allDefaultUnit[6];
		preferredUnit[1] = allPreferredUnit[6];

		defaultUnit[2] = allDefaultUnit[8];
		preferredUnit[2] = allPreferredUnit[8];

		defaultUnit[3] = allDefaultUnit[3];
		preferredUnit[3] = allPreferredUnit[3];

		defaultUnit[4] = allDefaultUnit[5];
		preferredUnit[4] = allPreferredUnit[5];

		defaultUnit[5] = allDefaultUnit[9];
		preferredUnit[5] = allPreferredUnit[9];

		defaultUnit[6] = allDefaultUnit[2];
		preferredUnit[6] = allPreferredUnit[2];

	}

	private void spCalculationForModel2(double[] param, double dose,
			double lengthOfInfusion) throws RowsExceededException,
			WriteException, BiffException, IOException {
		copyDefaultAndPreferredUnitForModel2();
		calculateSPForModel2(param, dose, lengthOfInfusion);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel2(double[] param, double dose,
			double lengthOfInfusion) {

		secondaryParameterValue[0] = dose / (param[0] * param[1]);

		secondaryParameterValue[1] = Math.log(2) / param[1];
		// nedd to check the formula

		double coef = dose / (lengthOfInfusion * param[0] * param[1]);
		secondaryParameterValue[2] = coef
				* (1 - Math.exp((-param[1]) * lengthOfInfusion));

		secondaryParameterValue[3] = param[0] * param[1];

		double aumcIv = dose / (param[0] * param[1] * param[1]);

		secondaryParameterValue[4] = aumcIv + lengthOfInfusion
				* secondaryParameterValue[0] / 2;
		secondaryParameterValue[5] = secondaryParameterValue[4]
				/ secondaryParameterValue[0] - lengthOfInfusion / 2;
		secondaryParameterValue[6] = secondaryParameterValue[5]
				* secondaryParameterValue[3];

	}

	private void copyDefaultAndPreferredUnitForModel2() {

		defaultUnit[0] = allDefaultUnit[4];
		preferredUnit[0] = allPreferredUnit[4];

		defaultUnit[1] = allDefaultUnit[6];
		preferredUnit[1] = allPreferredUnit[6];

		defaultUnit[2] = allDefaultUnit[8];
		preferredUnit[2] = allPreferredUnit[8];

		defaultUnit[3] = allDefaultUnit[3];
		preferredUnit[3] = allPreferredUnit[3];

		defaultUnit[4] = allDefaultUnit[5];
		preferredUnit[4] = allPreferredUnit[5];

		defaultUnit[5] = allDefaultUnit[9];
		preferredUnit[5] = allPreferredUnit[9];

		defaultUnit[6] = allDefaultUnit[2];
		preferredUnit[6] = allPreferredUnit[2];
	}

	private void spCalculationForModel3(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel3();
		calculateSPForModel3(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel3(double[] param, double dose) {

		secondaryParameterValue[0] = dose / (param[0] * param[2]);

		secondaryParameterValue[1] = Math.log(2) / param[1];
		secondaryParameterValue[2] = Math.log(2) / param[2];
		secondaryParameterValue[3] = param[0] * param[2];
		secondaryParameterValue[4] = Math.log(param[1] / param[2])
				/ (param[1] - param[2]);

		secondaryParameterValue[5] = dose
				* Math.exp((-param[2] * secondaryParameterValue[4])) / param[0];

	}

	private void copyDefaultAndPreferredUnitForModel3() {

		defaultUnit[0] = allDefaultUnit[4];
		preferredUnit[0] = allPreferredUnit[4];

		defaultUnit[1] = allDefaultUnit[6];
		preferredUnit[1] = allPreferredUnit[6];

		defaultUnit[2] = allDefaultUnit[6];
		preferredUnit[2] = allPreferredUnit[6];

		defaultUnit[3] = allDefaultUnit[3];
		preferredUnit[3] = allPreferredUnit[3];

		defaultUnit[4] = allDefaultUnit[7];
		preferredUnit[4] = allPreferredUnit[7];

		defaultUnit[5] = allDefaultUnit[8];
		preferredUnit[5] = allPreferredUnit[8];

	}

	private void spCalculationForModel4(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel4();
		calculateSPForModel4(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel4(double[] param, double dose) {

		secondaryParameterValue[0] = dose / (param[0] * param[2]);

		secondaryParameterValue[1] = Math.log(2) / param[1];
		secondaryParameterValue[2] = Math.log(2) / param[2];
		secondaryParameterValue[3] = param[0] * param[2];
		secondaryParameterValue[4] = Math.log(param[1] / param[2])
				/ (param[1] - param[2]) + param[3];

		secondaryParameterValue[5] = dose
				* Math
						.exp((-param[2] * (secondaryParameterValue[4] - param[3])))
				/ param[0];

	}

	private void copyDefaultAndPreferredUnitForModel4() {

		defaultUnit[0] = allDefaultUnit[4];
		preferredUnit[0] = allPreferredUnit[4];

		defaultUnit[1] = allDefaultUnit[6];
		preferredUnit[1] = allPreferredUnit[6];

		defaultUnit[2] = allDefaultUnit[6];
		preferredUnit[2] = allPreferredUnit[6];

		defaultUnit[3] = allDefaultUnit[3];
		preferredUnit[3] = allPreferredUnit[3];

		defaultUnit[4] = allDefaultUnit[7];
		preferredUnit[4] = allPreferredUnit[7];

		defaultUnit[5] = allDefaultUnit[8];
		preferredUnit[5] = allPreferredUnit[8];

	}

	private void spCalculationForModel5(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel5();
		calculateSPForModel5(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel5(double[] param, double dose) {

		secondaryParameterValue[0] = dose / (param[0] * param[1]);

		secondaryParameterValue[1] = Math.log(2) / param[1];
		secondaryParameterValue[2] = param[0] * param[1];
		secondaryParameterValue[3] = 1 / param[1];

		secondaryParameterValue[4] = Math.exp(-1) * (dose / param[0]);

	}

	private void copyDefaultAndPreferredUnitForModel5() {

		defaultUnit[0] = allDefaultUnit[4];
		preferredUnit[0] = allPreferredUnit[4];

		defaultUnit[1] = allDefaultUnit[6];
		preferredUnit[1] = allPreferredUnit[6];

		defaultUnit[2] = allDefaultUnit[3];
		preferredUnit[2] = allPreferredUnit[3];

		defaultUnit[3] = allDefaultUnit[7];
		preferredUnit[3] = allPreferredUnit[7];

		defaultUnit[4] = allDefaultUnit[8];
		preferredUnit[4] = allPreferredUnit[8];

	}

	private void spCalculationForModel6(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel6();
		calculateSPForModel6(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel6(double[] param, double dose) {

		secondaryParameterValue[0] = dose / (param[0] * param[1]);

		secondaryParameterValue[1] = Math.log(2) / param[1];
		secondaryParameterValue[2] = param[0] * param[1];
		secondaryParameterValue[3] = 1 / param[1] + param[2];

		secondaryParameterValue[4] = Math.exp(-1) * (dose / param[0]);

	}

	private void copyDefaultAndPreferredUnitForModel6() {

		defaultUnit[0] = allDefaultUnit[4];
		preferredUnit[0] = allPreferredUnit[4];

		defaultUnit[1] = allDefaultUnit[6];
		preferredUnit[1] = allPreferredUnit[6];

		defaultUnit[2] = allDefaultUnit[3];
		preferredUnit[2] = allPreferredUnit[3];

		defaultUnit[3] = allDefaultUnit[7];
		preferredUnit[3] = allPreferredUnit[7];

		defaultUnit[4] = allDefaultUnit[8];
		preferredUnit[4] = allPreferredUnit[8];

	}

	private void spCalculationForModel7(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel7();
		calculateSPForModel7(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel7(double[] param, double dose) {

		secondaryParameterValue[1] = Math.log(2) / param[1];

		double temp = Math.pow((param[1] + param[2] + param[3]), 2);
		double temp1 = Math.sqrt(temp - 4 * param[1] * param[3]);

		double temp2 = param[1] + param[2] + param[3] - temp1;

		secondaryParameterValue[3] = (1 / 2.0) * (temp2);
		secondaryParameterValue[2] = param[1] * param[3]
				/ secondaryParameterValue[3];

		double a = (dose / param[0]) * (secondaryParameterValue[2] - param[3])
				/ (secondaryParameterValue[2] - secondaryParameterValue[3]);

		double b = (-1) * (dose / param[0])
				* (secondaryParameterValue[3] - param[3])
				/ (secondaryParameterValue[2] - secondaryParameterValue[3]);

		secondaryParameterValue[0] = (a / secondaryParameterValue[2])
				+ (b / secondaryParameterValue[3]);

		secondaryParameterValue[4] = Math.log(2) / secondaryParameterValue[2];
		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		secondaryParameterValue[6] = a;
		secondaryParameterValue[7] = b;

		secondaryParameterValue[8] = dose / param[0];
		secondaryParameterValue[9] = dose / secondaryParameterValue[0];

		secondaryParameterValue[10] = secondaryParameterValue[6]
				/ Math.pow(secondaryParameterValue[2], 2)
				+ secondaryParameterValue[7]
				/ Math.pow(secondaryParameterValue[3], 2);
		secondaryParameterValue[11] = secondaryParameterValue[10]
				/ secondaryParameterValue[0];

		secondaryParameterValue[12] = secondaryParameterValue[9]
				* secondaryParameterValue[11];

		secondaryParameterValue[14] = param[0] * param[2];

		secondaryParameterValue[13] = secondaryParameterValue[14] / param[3];

	}

	private void copyDefaultAndPreferredUnitForModel7() {

		defaultUnit[0] = allDefaultUnit[4];
		preferredUnit[0] = allPreferredUnit[4];

		defaultUnit[1] = allDefaultUnit[6];
		preferredUnit[1] = allPreferredUnit[6];

		defaultUnit[2] = allDefaultUnit[0];
		preferredUnit[2] = allPreferredUnit[0];

		defaultUnit[3] = allDefaultUnit[0];
		preferredUnit[3] = allPreferredUnit[0];

		defaultUnit[4] = allDefaultUnit[6];
		preferredUnit[4] = allPreferredUnit[6];

		defaultUnit[5] = allDefaultUnit[6];
		preferredUnit[5] = allPreferredUnit[6];

		defaultUnit[6] = allDefaultUnit[1];
		preferredUnit[6] = allPreferredUnit[1];

		defaultUnit[7] = allDefaultUnit[1];
		preferredUnit[7] = allPreferredUnit[1];

		defaultUnit[8] = allDefaultUnit[8];
		preferredUnit[8] = allPreferredUnit[8];

		defaultUnit[9] = allDefaultUnit[3];
		preferredUnit[9] = allPreferredUnit[3];

		defaultUnit[10] = allDefaultUnit[5];
		preferredUnit[10] = allPreferredUnit[5];

		defaultUnit[11] = allDefaultUnit[9];
		preferredUnit[11] = allPreferredUnit[9];

		defaultUnit[12] = allDefaultUnit[2];
		preferredUnit[12] = allPreferredUnit[2];

		defaultUnit[13] = allDefaultUnit[2];
		preferredUnit[13] = allPreferredUnit[2];

		defaultUnit[14] = allDefaultUnit[3];
		preferredUnit[14] = allPreferredUnit[3];

	}

	private void spCalculationForModel8(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel8();
		calculateSPForModel8(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel8(double[] param, double dose) {

		secondaryParameterValue[0] = (param[0] / param[2])
				+ (param[1] / param[3]);

		secondaryParameterValue[2] = Math.log(2) / param[2];

		secondaryParameterValue[3] = Math.log(2) / param[3];

		secondaryParameterValue[6] = (param[0] * param[3] + param[1] * param[2])
				/ (param[0] + param[1]);

		secondaryParameterValue[4] = param[2] * param[3]
				/ secondaryParameterValue[6];
		secondaryParameterValue[5] = param[2] + param[3]
				- secondaryParameterValue[6] - secondaryParameterValue[4];

		secondaryParameterValue[1] = Math.log(2) / secondaryParameterValue[4];
		secondaryParameterValue[8] = dose / (param[0] + param[1]);

		secondaryParameterValue[7] = dose / secondaryParameterValue[8];

		secondaryParameterValue[9] = dose / secondaryParameterValue[0];

		secondaryParameterValue[10] = param[0] / Math.pow(param[2], 2)
				+ param[1] / Math.pow(param[3], 2);

		secondaryParameterValue[11] = secondaryParameterValue[10]
				/ secondaryParameterValue[0];

		secondaryParameterValue[12] = secondaryParameterValue[9]
				* secondaryParameterValue[11];

		secondaryParameterValue[14] = secondaryParameterValue[8]
				* secondaryParameterValue[5];

		secondaryParameterValue[13] = secondaryParameterValue[14]
				/ secondaryParameterValue[6];

	}

	private void copyDefaultAndPreferredUnitForModel8() {

		defaultUnit[0] = allDefaultUnit[4];
		preferredUnit[0] = allPreferredUnit[4];

		for (int i = 1; i < 4; i++) {
			defaultUnit[i] = allDefaultUnit[6];
			preferredUnit[i] = allPreferredUnit[6];
		}

		for (int i = 4; i < 7; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		defaultUnit[7] = allDefaultUnit[8];
		preferredUnit[7] = allPreferredUnit[8];

		defaultUnit[8] = allDefaultUnit[2];
		preferredUnit[8] = allPreferredUnit[2];

		defaultUnit[9] = allDefaultUnit[3];
		preferredUnit[9] = allPreferredUnit[3];

		defaultUnit[10] = allDefaultUnit[5];
		preferredUnit[10] = allPreferredUnit[5];

		defaultUnit[11] = allDefaultUnit[9];
		preferredUnit[11] = allPreferredUnit[9];

		defaultUnit[12] = allDefaultUnit[2];
		preferredUnit[12] = allPreferredUnit[2];

		defaultUnit[13] = allDefaultUnit[2];
		preferredUnit[13] = allPreferredUnit[2];

		defaultUnit[14] = allDefaultUnit[3];
		preferredUnit[14] = allPreferredUnit[3];
	}

	private void spCalculationForModel9(double[] param, double dose,
			double lengthOfInfusion) throws RowsExceededException,
			WriteException, BiffException, IOException {
		copyDefaultAndPreferredUnitForModel9();
		calculateSPForModel9(param, dose, lengthOfInfusion);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel9(double[] param, double dose,
			double lengthOfInfusion) {
		secondaryParameterValue[0] = dose / (param[0] * param[1]);

		secondaryParameterValue[1] = Math.log(2) / param[1];

		secondaryParameterValue[3] = (1 / 2.0)
				* (param[1] + param[2] + param[3] - Math.sqrt(Math.pow(
						(param[1] + param[2] + param[3]), 2)

						- 4 * param[1] * param[3]));

		secondaryParameterValue[2] = (param[1] * param[3])
				/ secondaryParameterValue[3];

		secondaryParameterValue[4] = Math.log(2) / secondaryParameterValue[2];

		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		double a1 = (dose / lengthOfInfusion / param[0])
				* (param[3] - secondaryParameterValue[2])
				/ (secondaryParameterValue[2] - secondaryParameterValue[3])
				/ secondaryParameterValue[2];

		double b1 = (-1) * (dose / lengthOfInfusion / param[0])
				* (param[3] - secondaryParameterValue[3])
				/ (secondaryParameterValue[2] - secondaryParameterValue[3])
				/ secondaryParameterValue[3];

		double a = (-1.0) * a1 * lengthOfInfusion * secondaryParameterValue[2];

		double b = (-1.0) * b1 * lengthOfInfusion * secondaryParameterValue[3];
		secondaryParameterValue[6] = a;

		secondaryParameterValue[7] = b;
		secondaryParameterValue[8] = a1
				* (Math.exp((-secondaryParameterValue[2]) * lengthOfInfusion) - 1.0)
				+ b1
				* (Math.exp((-secondaryParameterValue[3]) * lengthOfInfusion) - 1.0);

		secondaryParameterValue[9] = dose / secondaryParameterValue[0];

		double aumcb = (a / (secondaryParameterValue[2] * secondaryParameterValue[2]))
				+ (b / (secondaryParameterValue[3] * secondaryParameterValue[3]));

		secondaryParameterValue[10] = aumcb + lengthOfInfusion
				* secondaryParameterValue[0] / 2.0;

		secondaryParameterValue[11] = secondaryParameterValue[10]
				/ secondaryParameterValue[0] - lengthOfInfusion / 2.0;

		secondaryParameterValue[12] = dose
				* ((secondaryParameterValue[10] / secondaryParameterValue[0]) - lengthOfInfusion / 2)
				/ secondaryParameterValue[0];

		secondaryParameterValue[14] = param[0] * param[2];

		secondaryParameterValue[13] = param[0] * (param[2] / param[3]);
	}

	private void copyDefaultAndPreferredUnitForModel9() {

		defaultUnit[0] = allDefaultUnit[4];
		preferredUnit[0] = allPreferredUnit[4];

		defaultUnit[1] = allDefaultUnit[6];
		preferredUnit[1] = allPreferredUnit[6];

		defaultUnit[2] = allDefaultUnit[0];
		preferredUnit[2] = allPreferredUnit[0];

		defaultUnit[3] = allDefaultUnit[0];
		preferredUnit[3] = allPreferredUnit[0];

		defaultUnit[4] = allDefaultUnit[6];
		preferredUnit[4] = allPreferredUnit[6];

		defaultUnit[5] = allDefaultUnit[6];
		preferredUnit[5] = allPreferredUnit[6];

		defaultUnit[6] = allDefaultUnit[1];
		preferredUnit[6] = allPreferredUnit[1];

		defaultUnit[7] = allDefaultUnit[1];
		preferredUnit[7] = allPreferredUnit[1];

		defaultUnit[8] = allDefaultUnit[8];
		preferredUnit[8] = allPreferredUnit[8];

		defaultUnit[9] = allDefaultUnit[3];
		preferredUnit[9] = allPreferredUnit[3];

		defaultUnit[10] = allDefaultUnit[5];
		preferredUnit[10] = allPreferredUnit[5];

		defaultUnit[11] = allDefaultUnit[9];
		preferredUnit[11] = allPreferredUnit[9];

		defaultUnit[12] = allDefaultUnit[2];
		preferredUnit[12] = allPreferredUnit[2];

		defaultUnit[13] = allDefaultUnit[2];
		preferredUnit[13] = allPreferredUnit[2];

		defaultUnit[14] = allDefaultUnit[3];
		preferredUnit[14] = allPreferredUnit[3];

	}

	private void spCalculationForModel10(double[] param, double dose,
			double lengthOfInfusion) throws RowsExceededException,
			WriteException, BiffException, IOException {
		copyDefaultAndPreferredUnitForModel10();
		calculateSPForModel10(param, dose, lengthOfInfusion);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel10(double[] param, double dose,
			double lengthOfInfusion) {

		secondaryParameterValue[0] = (param[2] * param[3]) / param[1];

		secondaryParameterValue[1] = param[2] + param[3] - param[1]
				- secondaryParameterValue[0];

		secondaryParameterValue[2] = Math.log(2) / secondaryParameterValue[0];

		secondaryParameterValue[3] = dose
				/ (param[0] * secondaryParameterValue[0]);

		secondaryParameterValue[4] = Math.log(2) / param[2];

		secondaryParameterValue[5] = Math.log(2) / param[3];

		double a = dose * (param[2] - param[1])
				/ (param[0] * (param[2] - param[3]));
		double b = dose * (param[1] - param[3])
				/ (param[0] * (param[2] - param[3]));
		secondaryParameterValue[6] = a;
		secondaryParameterValue[7] = b;

		double k0 = dose / lengthOfInfusion;
		double aa = k0 * (param[1] - param[2])
				/ (param[0] * param[2] * (param[3] - param[2]));
		double bb = k0 * (param[1] - param[3])
				/ (param[0] * param[3] * (param[2] - param[3]));

		secondaryParameterValue[8] = aa
				* (1 - Math.exp((-param[2]) * lengthOfInfusion)) + bb
				* (1 - Math.exp((-param[3]) * lengthOfInfusion));
		secondaryParameterValue[9] = dose / secondaryParameterValue[3];

		double aumcb = (a / (param[2] * param[2]) + b / (param[3] * param[3]));
		secondaryParameterValue[10] = aumcb + lengthOfInfusion
				* secondaryParameterValue[3] / 2.0;

		secondaryParameterValue[11] = (secondaryParameterValue[10] / secondaryParameterValue[3])
				- lengthOfInfusion / 2.0;

		secondaryParameterValue[12] = dose
				* ((secondaryParameterValue[10] / secondaryParameterValue[3]) - lengthOfInfusion / 2)
				/ secondaryParameterValue[3];

		secondaryParameterValue[14] = secondaryParameterValue[1] * param[0];

		secondaryParameterValue[13] = secondaryParameterValue[14] / param[1];

	}

	private void copyDefaultAndPreferredUnitForModel10() {

		defaultUnit[0] = allDefaultUnit[0];
		preferredUnit[0] = allPreferredUnit[0];

		defaultUnit[1] = allDefaultUnit[0];
		preferredUnit[1] = allPreferredUnit[0];

		defaultUnit[2] = allDefaultUnit[6];
		preferredUnit[2] = allPreferredUnit[6];

		defaultUnit[3] = allDefaultUnit[4];
		preferredUnit[3] = allPreferredUnit[4];

		defaultUnit[4] = allDefaultUnit[6];
		preferredUnit[4] = allPreferredUnit[6];

		defaultUnit[5] = allDefaultUnit[6];
		preferredUnit[5] = allPreferredUnit[6];

		defaultUnit[6] = allDefaultUnit[1];
		preferredUnit[6] = allPreferredUnit[1];

		defaultUnit[7] = allDefaultUnit[1];
		preferredUnit[7] = allPreferredUnit[1];

		defaultUnit[8] = allDefaultUnit[8];
		preferredUnit[8] = allPreferredUnit[8];

		defaultUnit[9] = allDefaultUnit[3];
		preferredUnit[9] = allPreferredUnit[3];

		defaultUnit[10] = allDefaultUnit[5];
		preferredUnit[10] = allPreferredUnit[5];

		defaultUnit[11] = allDefaultUnit[9];
		preferredUnit[11] = allPreferredUnit[9];

		defaultUnit[12] = allDefaultUnit[2];
		preferredUnit[12] = allPreferredUnit[2];

		defaultUnit[13] = allDefaultUnit[2];
		preferredUnit[13] = allPreferredUnit[2];

		defaultUnit[14] = allDefaultUnit[3];
		preferredUnit[14] = allPreferredUnit[3];
	}

	private void spCalculationForModel11(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel11();
		calculateSPForModel11(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel11(double[] param, double dose) {

		secondaryParameterValue[0] = dose / (param[0] * param[2]);

		secondaryParameterValue[1] = Math.log(2) / param[1];

		secondaryParameterValue[2] = Math.log(2) / param[2];

		secondaryParameterValue[4] = (1 / 2.0)
				* (param[2] + param[3] + param[4] - Math.sqrt(Math.pow(
						(param[2] + param[3] + param[4]), 2)

						- 4 * param[2] * param[4]));

		secondaryParameterValue[3] = param[2] * param[4]
				/ secondaryParameterValue[4];

		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		secondaryParameterValue[6] = Math.log(2) / secondaryParameterValue[4];

		secondaryParameterValue[7] = (dose / param[0]) * param[1]
				* (param[4] - secondaryParameterValue[3])
				/ (secondaryParameterValue[3] - secondaryParameterValue[4])
				/ (secondaryParameterValue[3] - param[1]);

		secondaryParameterValue[8] = (-1) * (dose / param[0]) * param[1]
				* (param[4] - secondaryParameterValue[4])
				/ (secondaryParameterValue[3] - secondaryParameterValue[4])
				/ (secondaryParameterValue[4] - param[1]);

		secondaryParameterValue[9] = dose / secondaryParameterValue[0];

		secondaryParameterValue[11] = param[3] * param[0];

		secondaryParameterValue[10] = secondaryParameterValue[11] / param[4];
	}

	private void copyDefaultAndPreferredUnitForModel11() {

		defaultUnit[0] = allDefaultUnit[4];
		preferredUnit[0] = allPreferredUnit[4];

		defaultUnit[1] = allDefaultUnit[6];
		preferredUnit[1] = allPreferredUnit[6];

		defaultUnit[2] = allDefaultUnit[6];
		preferredUnit[2] = allPreferredUnit[6];

		defaultUnit[3] = allDefaultUnit[0];
		preferredUnit[3] = allPreferredUnit[0];

		defaultUnit[4] = allDefaultUnit[0];
		preferredUnit[4] = allPreferredUnit[0];

		defaultUnit[5] = allDefaultUnit[6];
		preferredUnit[5] = allPreferredUnit[6];

		defaultUnit[6] = allDefaultUnit[6];
		preferredUnit[6] = allPreferredUnit[6];

		defaultUnit[7] = allDefaultUnit[1];
		preferredUnit[7] = allPreferredUnit[1];

		defaultUnit[8] = allDefaultUnit[1];
		preferredUnit[8] = allPreferredUnit[1];

		defaultUnit[9] = allDefaultUnit[3];
		preferredUnit[9] = allPreferredUnit[3];

		defaultUnit[10] = allDefaultUnit[2];
		preferredUnit[10] = allPreferredUnit[2];

		defaultUnit[11] = allDefaultUnit[3];
		preferredUnit[11] = allPreferredUnit[3];

		defaultUnit[12] = allDefaultUnit[7];
		preferredUnit[12] = allPreferredUnit[7];

		defaultUnit[13] = allDefaultUnit[8];
		preferredUnit[13] = allPreferredUnit[8];
	}

	private void spCalculationForModel12(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel12();
		calculateSPForModel12(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel12(double[] param, double dose) {

		secondaryParameterValue[0] = dose / (param[0] * param[2]);

		secondaryParameterValue[1] = Math.log(2) / param[1];

		secondaryParameterValue[2] = Math.log(2) / param[2];

		secondaryParameterValue[4] = (1 / 2.0)
				* (param[2] + param[3] + param[4] - Math.sqrt(Math.pow(
						(param[2] + param[3] + param[4]), 2)

						- 4 * param[2] * param[4]));

		secondaryParameterValue[3] = param[2] * param[4]
				/ secondaryParameterValue[4];

		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		secondaryParameterValue[6] = Math.log(2) / secondaryParameterValue[4];

		secondaryParameterValue[7] = (dose / param[0]) * param[1]
				* (param[4] - secondaryParameterValue[3])
				/ (secondaryParameterValue[3] - secondaryParameterValue[4])
				/ (secondaryParameterValue[3] - param[1]);
		secondaryParameterValue[8] = (-1) * (dose / param[0]) * param[1]
				* (param[4] - secondaryParameterValue[4])
				/ (secondaryParameterValue[3] - secondaryParameterValue[4])
				/ (secondaryParameterValue[4] - param[1]);

		secondaryParameterValue[9] = dose / secondaryParameterValue[0];

		secondaryParameterValue[10] = param[3] * param[0] / param[4];

		secondaryParameterValue[11] = param[3] * param[0];

	}

	private void copyDefaultAndPreferredUnitForModel12() {

		defaultUnit[0] = allDefaultUnit[4];
		preferredUnit[0] = allPreferredUnit[4];

		defaultUnit[1] = allDefaultUnit[6];
		preferredUnit[1] = allPreferredUnit[6];

		defaultUnit[2] = allDefaultUnit[6];
		preferredUnit[2] = allPreferredUnit[6];

		defaultUnit[3] = allDefaultUnit[0];
		preferredUnit[3] = allPreferredUnit[0];

		defaultUnit[4] = allDefaultUnit[0];
		preferredUnit[4] = allPreferredUnit[0];

		defaultUnit[5] = allDefaultUnit[6];
		preferredUnit[5] = allPreferredUnit[6];

		defaultUnit[6] = allDefaultUnit[6];
		preferredUnit[6] = allPreferredUnit[6];

		defaultUnit[7] = allDefaultUnit[1];
		preferredUnit[7] = allPreferredUnit[1];

		defaultUnit[8] = allDefaultUnit[1];
		preferredUnit[8] = allPreferredUnit[1];

		defaultUnit[9] = allDefaultUnit[3];
		preferredUnit[9] = allPreferredUnit[3];

		defaultUnit[10] = allDefaultUnit[2];
		preferredUnit[10] = allPreferredUnit[2];

		defaultUnit[11] = allDefaultUnit[3];
		preferredUnit[11] = allPreferredUnit[3];

		defaultUnit[12] = allDefaultUnit[7];
		preferredUnit[12] = allPreferredUnit[7];

		defaultUnit[13] = allDefaultUnit[8];
		preferredUnit[13] = allPreferredUnit[8];

	}

	private void spCalculationForModel13(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel13();
		calculateSPForModel13(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel13(double[] param, double dose) {

		secondaryParameterValue[2] = (param[0] * param[4] + param[1] * param[3])
				/ (param[0] + param[1]);

		secondaryParameterValue[1] = param[3] + param[4] - param[2]
				- secondaryParameterValue[2];

		secondaryParameterValue[0] = param[3] * param[4]
				/ secondaryParameterValue[2];

		secondaryParameterValue[4] = Math.log(2) / param[3];

		secondaryParameterValue[3] = param[2] * param[4]
				/ secondaryParameterValue[0];

		secondaryParameterValue[5] = Math.log(2) / secondaryParameterValue[3];

		secondaryParameterValue[6] = Math.log(2) / secondaryParameterValue[4];

		secondaryParameterValue[7] = (dose * (secondaryParameterValue[3] - param[4]))
				/ (param[0] * (secondaryParameterValue[3] - secondaryParameterValue[4]));

		secondaryParameterValue[8] = (dose * (secondaryParameterValue[4] - param[4]))
				/ (param[0] * (secondaryParameterValue[4] - secondaryParameterValue[3]));

		secondaryParameterValue[9] = dose / secondaryParameterValue[0];

		secondaryParameterValue[10] = param[3] * param[0] / param[4];

		secondaryParameterValue[11] = param[3] * param[0];

	}

	private void copyDefaultAndPreferredUnitForModel13() {

		for (int i = 0; i < 3; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		defaultUnit[3] = allDefaultUnit[4];
		preferredUnit[3] = allPreferredUnit[4];

		for (int i = 4; i < 8; i++) {
			defaultUnit[i] = allDefaultUnit[6];
			preferredUnit[i] = allPreferredUnit[6];
		}

		defaultUnit[8] = allDefaultUnit[2];
		preferredUnit[8] = allPreferredUnit[2];

		defaultUnit[9] = allDefaultUnit[3];
		preferredUnit[9] = allPreferredUnit[3];

		defaultUnit[10] = allDefaultUnit[2];
		preferredUnit[10] = allPreferredUnit[2];

		defaultUnit[11] = allDefaultUnit[3];
		preferredUnit[11] = allPreferredUnit[3];

		defaultUnit[12] = allDefaultUnit[7];
		preferredUnit[12] = allPreferredUnit[7];

		defaultUnit[13] = allDefaultUnit[8];
		preferredUnit[13] = allPreferredUnit[8];
	}

	private void writingOutputInSpreedSheetAndTextArea(String[] defaultUnit,
			String[] preferredUnit) throws RowsExceededException,
			WriteException, BiffException, IOException {

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
			
			String[] s = new String[simulationInst.numberOfSortVariables + 5];
			for (int k = 0; k < simulationInst.numberOfSortVariables; k++) {
				s[k] = simulationInst.pkpdInstance.dataSortVariables[simulationInst.pkpdInstance
						.getCurrentProfileNumber()][k];
			}
			s[numberOfSortVariable] = secParamName[i];
			s[numberOfSortVariable + 1] = preferredUnit[i];
			s[numberOfSortVariable + 2] = simulationInst.pkpdInstance.formatting(
					secondaryParameterValue[i] * convertAmount,
					false);
			
			simulationInst.nonTransposedSPAL.add(s);


		}

	}

	private void printSecondaryParamForEstimation(String[] defaultUnit,
			String[] preferredUnit) throws RowsExceededException,
			WriteException, BiffException, IOException {

		DefaultToPreferredUnitConverter unitConversionInst = new DefaultToPreferredUnitConverter();
		/*
		 * CaParametersCalculation pkpdMainInst = CaParametersCalculation
		 * .createPKPDMainInstance();
		 */

		PkPdInfo pkPdInst = PkPdInfo.createPKPDInstance();
		double[] stdError = calculateStdErrorofSP();
		double[] CV = new double[noOfSecParam];

		int count = 0;
		String[] strForHorizontalParam = new String[pkPdInst.numberOfSortVar
				+ 3 * pkPdInst.noOfSecParam];

		for (int k = 0; k < pkPdInst.numberOfSortVar; k++) {
			strForHorizontalParam[k] = pkPdInst.dataSortVariables[pkPdInst
					.getCurrentProfileNumber()][k];
			count++;
		}

		for (int i = 0; i < pkPdInst.noOfSecParam; i++) {
			double convertAmount;
			try {
				convertAmount = unitConversionInst.unitConversion(
						defaultUnit[i], preferredUnit[i]);

			} catch (Exception e) {
				convertAmount = 1;
			}
			CV[i] = Math.abs(stdError[i] / secondaryParameterValue[i]) * 100;

			String[] s = new String[pkPdInst.numberOfSortVar + 5];
			for (int k = 0; k < pkPdInst.numberOfSortVar; k++) {
				s[k] = pkPdInst.dataSortVariables[pkPdInst
						.getCurrentProfileNumber()][k];
			}
			s[pkPdInst.numberOfSortVar] = pkPdInst.secParamName[i];
			s[pkPdInst.numberOfSortVar + 1] = preferredUnit[i];
			s[pkPdInst.numberOfSortVar + 2] = pkPdInst.formatting(
					secondaryParameterValue[i] * convertAmount,
					false);

			s[pkPdInst.numberOfSortVar + 3] = pkPdInst.formatting(stdError[i],
					false);

			s[pkPdInst.numberOfSortVar + 4] = pkPdInst.formatting(CV[i],
					false);

			strForHorizontalParam[count++] = pkPdInst.formatting(
					secondaryParameterValue[i] * convertAmount,
					false);

			strForHorizontalParam[count++] = pkPdInst.formatting(stdError[i],
					false);

			strForHorizontalParam[count++] = pkPdInst.formatting(CV[i],
					false);

			pkPdInst.getNonTransposedSPAL().add(s);

			pkPdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(secParamName[i], 20)
							+ "\t"
							+ StringUtils.rightPad(preferredUnit[i], 20)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									secondaryParameterValue[i] * convertAmount,
									false), 20)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									stdError[i], false), 20)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									CV[i], false), 20) + "\r\n");

		}

		pkPdInst.secondaryParametersAL.add(strForHorizontalParam);
		System.out.println();
	}

	private double[] calculateStdErrorofSP() throws RowsExceededException,
			WriteException, BiffException, IOException {
		PkPdInfo pkPdInst = PkPdInfo.createPKPDInstance();

		double[][] parDerivative = new double[pkpdInstance.parameter.length][1];

		double dose = PkParamEstimator.createPkParamEstimateInstance().dose[0];
		double[] param = new double[pkpdInstance.parameter.length];

		double[] stdError = new double[noOfSecParam];

		StandardErrorCalculationOfSp inst = new StandardErrorCalculationOfSp();

		for (int j = 0; j < pkPdInst.noOfSecParam; j++) {

			for (int i = 0; i < parDerivative.length; i++) {
				param = pkPdInst.parameter;

				double[] value = inst.chooseLibraryModel(param);

				param[i] = pkPdInst.parameter[i] + pkPdInst.getPdIncrement();

				double[] incValue = inst.chooseLibraryModel(param);

				parDerivative[i][0] = (incValue[j] - value[j])
						/ pkPdInst.getPdIncrement();

			}

			Matrix AInv = new Matrix(aInv);
			Matrix G = new Matrix(parDerivative);
			Matrix GT = G.transpose();
			Matrix GTAinv = GT.times(AInv);
			Matrix GTaInvG = GTAinv.times(G);

			double[][] value = GTaInvG.getArray();

			double val = value[0][0] * SD * SD;

			val = Math.sqrt(val);
			stdError[j] = val;

		}
		System.out.println();
		return stdError;
	}
}
