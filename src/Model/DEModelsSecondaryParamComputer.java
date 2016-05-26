package Model;

import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.StringUtils;

import view.ApplicationInfo;
import view.ProcessingInputsInfo;

public class DEModelsSecondaryParamComputer {

	public double[] secondaryParameterValue;

	String[] defaultUnit;
	String[] preferredUnit;
	String[] allDefaultUnit;
	String[] allPreferredUnit;
	PkPdInfo pkpdInst;

	int noOfSecParam;
	ApplicationInfo appInfo;
	int numberOfSortVariable;
	String[][] dataSortVariables;
	String[] secParamName;
	ProcessingInputsInfo procInputInst;
	

	public DEModelsSecondaryParamComputer() {}

	public void calculateSecondaryParameter(double[] param,int modelNumber, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		
		

		
		pkpdInst = PkPdInfo.createPKPDInstance();
		procInputInst = pkpdInst.copyProcessingInput();
		initialize();
		
		
		secondaryParameterValue = new double[noOfSecParam];
		defaultUnit = new String[noOfSecParam];
		preferredUnit = new String[noOfSecParam];
		PkParamEstimator pkpdMain = PkParamEstimator
				.createPkParamEstimateInstance();

		allDefaultUnit = procInputInst.getParameterUnitsDataObj()
				.getDefaultUnitArray();
		allPreferredUnit = procInputInst.getParameterUnitsDataObj()
				.getPreferredUnitsArray();

		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				"\r\n\r\n"
						+ StringUtils.rightPad(
								"*************************************************"
										+ "**********************************",
								80) + "\r\n\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst
				.getTextoutput()
				.add(
						StringUtils.rightPad("", 5)
								+ "\t"
								+ StringUtils
										.rightPad(
												"SUMMARY OF ESTIMATED SECONDARY PARAMETERS",
												45) + "\r\n\r\n");
		pkpdMain.pkpdInst.workSheetOutputInst.getTextoutput().add(
				StringUtils.rightPad("", 5)
						+ StringUtils.rightPad("PARAMETERS", 20) + "\t"
						+ StringUtils.rightPad("UNIT", 20) + "\t"
						+ StringUtils.rightPad("ESTIMATE", 20) + "\r\n");

		if (modelNumber == 1) {
			spCalculationForModel1(param, dose);
		} else if (modelNumber == 2) {
			spCalculationForModel2(param, dose);
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
			spCalculationForModel9(param, dose);
		} else if (modelNumber == 10) {
			spCalculationForModel10(param, dose);
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
			spCalculationForModel19(param, dose);
		} else if (modelNumber == 20) {
			spCalculationForModel20(param, dose);
		}

	}

	private void initialize() {
		appInfo = PkParamEstimator.createPkParamEstimateInstance().appInfoinst;

		noOfSecParam = procInputInst.getProfileAndParamInfoObj()
				.getNumberOfSecondaryParameters();

		numberOfSortVariable = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		dataSortVariables = procInputInst.getProfileAndParamInfoObj()
				.getDataSortVariables();
		secParamName = procInputInst.getProfileAndParamInfoObj()
				.getSecondaryParameterNameForCA();
	}

	private void spCalculationForModel20(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel20();
		calculateSPForModel20(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel20(double[] param) {
		secondaryParameterValue[0] = param[2]
				* param[4];
		secondaryParameterValue[1] = param[2]
				* param[4] / param[5];
	}

	private void copyDefaultAndPreferredUnitForModel20() {

		defaultUnit[0] = allDefaultUnit[3];
		preferredUnit[0] = allPreferredUnit[3];

		defaultUnit[1] = allDefaultUnit[2];
		preferredUnit[1] = allPreferredUnit[2];
	}

	private void spCalculationForModel19(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel19();
		calculateSPForModel19(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel19(double[] param) {
		secondaryParameterValue[0] = param[2]
				* param[4];
		secondaryParameterValue[1] = param[2]
				* param[4] / param[5];
	}

	private void copyDefaultAndPreferredUnitForModel19() {

		defaultUnit[0] = allDefaultUnit[3];
		preferredUnit[0] = allPreferredUnit[3];

		defaultUnit[1] = allDefaultUnit[2];
		preferredUnit[1] = allPreferredUnit[2];
	}

	private void spCalculationForModel18(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel18();
		calculateSPForModel18(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel18(double[] param) {
		secondaryParameterValue[0] = param[2]
				* param[4];
		secondaryParameterValue[1] = param[2]
				* param[4] / param[5];
	}

	private void copyDefaultAndPreferredUnitForModel18() {

		defaultUnit[0] = allDefaultUnit[3];
		preferredUnit[0] = allPreferredUnit[3];

		defaultUnit[1] = allDefaultUnit[2];
		preferredUnit[1] = allPreferredUnit[2];

	}

	private void spCalculationForModel17(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel17();
		calculateSPForModel17(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel17(double[] param) {

		secondaryParameterValue[0] = param[0]
				/ param[1];
		secondaryParameterValue[1] = param[2]
				/ param[1];
		secondaryParameterValue[2] = param[2]
				/ param[3];
		secondaryParameterValue[3] = param[4]
				/ param[1];
		secondaryParameterValue[4] = param[4]
				/ param[5];
		secondaryParameterValue[5] = param[1];
		secondaryParameterValue[6] = 0;
		secondaryParameterValue[7] = 0;
		secondaryParameterValue[8] = 0;
		secondaryParameterValue[9] = Math.log(2) / secondaryParameterValue[6];
		secondaryParameterValue[10] = Math.log(2) / secondaryParameterValue[7];
		secondaryParameterValue[11] = Math.log(2) / secondaryParameterValue[8];
	}

	private void copyDefaultAndPreferredUnitForModel17() {

		for (int i = 0; i < 5; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		defaultUnit[5] = allDefaultUnit[2];
		preferredUnit[5] = allPreferredUnit[2];

		for (int i = 6; i < 9; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		for (int i = 9; i < 12; i++) {
			defaultUnit[i] = allDefaultUnit[6];
			preferredUnit[i] = allPreferredUnit[6];
		}

	}

	private void spCalculationForModel16(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel16();
		calculateSPForModel16(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel16(double[] param) {
		secondaryParameterValue[0] = param[0]
				* param[5];
		secondaryParameterValue[1] = param[5];
		secondaryParameterValue[2] = param[1]
				* param[5];
		secondaryParameterValue[3] = param[1]
				* param[5] / param[2];
		secondaryParameterValue[4] = param[3]
				* param[5];
		secondaryParameterValue[5] = param[3]
				* param[5] / param[4];
		secondaryParameterValue[6] = 0;
		secondaryParameterValue[7] = 0;
		secondaryParameterValue[8] = 0;
		secondaryParameterValue[9] = Math.log(2) / secondaryParameterValue[6];
		secondaryParameterValue[10] = Math.log(2) / secondaryParameterValue[7];
		secondaryParameterValue[11] = Math.log(2) / secondaryParameterValue[8];

	}

	private void copyDefaultAndPreferredUnitForModel16() {

		defaultUnit[0] = allDefaultUnit[3];
		preferredUnit[0] = allPreferredUnit[3];

		defaultUnit[1] = allDefaultUnit[2];
		preferredUnit[1] = allPreferredUnit[2];

		defaultUnit[2] = allDefaultUnit[3];
		preferredUnit[2] = allPreferredUnit[3];

		defaultUnit[3] = allDefaultUnit[2];
		preferredUnit[3] = allPreferredUnit[2];

		defaultUnit[4] = allDefaultUnit[3];
		preferredUnit[4] = allPreferredUnit[3];

		defaultUnit[5] = allDefaultUnit[2];
		preferredUnit[5] = allPreferredUnit[2];

		for (int i = 6; i < 9; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		for (int i = 9; i <= 11; i++) {
			defaultUnit[i] = allDefaultUnit[6];
			preferredUnit[i] = allPreferredUnit[6];
		}

	}

	private void spCalculationForModel15(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel15();
		calculateSPForModel15(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel15(double[] param) {

		secondaryParameterValue[0] = param[0]
				/ param[1];
		secondaryParameterValue[1] = param[2]
				/ param[1];
		secondaryParameterValue[2] = param[2]
				/ param[3];
		secondaryParameterValue[3] = param[4]
				/ param[1];
		secondaryParameterValue[4] = param[4]
				/ param[5];
		secondaryParameterValue[5] = param[1];
		secondaryParameterValue[6] = 0;
		secondaryParameterValue[7] = 0;
		secondaryParameterValue[8] = 0;
		secondaryParameterValue[9] = Math.log(2) / secondaryParameterValue[6];
		secondaryParameterValue[10] = Math.log(2) / secondaryParameterValue[7];
		secondaryParameterValue[11] = Math.log(2) / secondaryParameterValue[8];
	}

	private void copyDefaultAndPreferredUnitForModel15() {

		for (int i = 0; i < 5; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		defaultUnit[5] = allDefaultUnit[2];
		preferredUnit[5] = allPreferredUnit[2];

		for (int i = 6; i < 9; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		for (int i = 9; i <= 11; i++) {
			defaultUnit[i] = allDefaultUnit[6];
			preferredUnit[i] = allPreferredUnit[6];
		}

	}

	private void spCalculationForModel14(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel14();
		calculateSPForModel14(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel14(double[] param) {

		secondaryParameterValue[0] = param[0]
				* param[5];
		secondaryParameterValue[1] = param[5];
		secondaryParameterValue[2] = param[1]
				* param[5];
		secondaryParameterValue[3] = param[1]
				* param[5] / param[2];
		secondaryParameterValue[4] = param[3]
				* param[5];
		secondaryParameterValue[5] = param[3]
				* param[5] / param[4];
		secondaryParameterValue[6] = 0;
		secondaryParameterValue[7] = 0;
		secondaryParameterValue[8] = 0;
		secondaryParameterValue[9] = Math.log(2) / secondaryParameterValue[6];
		secondaryParameterValue[10] = Math.log(2) / secondaryParameterValue[7];
		secondaryParameterValue[11] = Math.log(2) / secondaryParameterValue[8];

	}

	private void copyDefaultAndPreferredUnitForModel14() {

		defaultUnit[0] = allDefaultUnit[3];
		preferredUnit[0] = allPreferredUnit[3];

		defaultUnit[1] = allDefaultUnit[2];
		preferredUnit[1] = allPreferredUnit[2];

		defaultUnit[2] = allDefaultUnit[3];
		preferredUnit[2] = allPreferredUnit[3];

		defaultUnit[3] = allDefaultUnit[2];
		preferredUnit[3] = allPreferredUnit[2];

		defaultUnit[4] = allDefaultUnit[3];
		preferredUnit[4] = allPreferredUnit[3];

		defaultUnit[5] = allDefaultUnit[2];
		preferredUnit[5] = allPreferredUnit[2];

		for (int i = 6; i < 9; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		for (int i = 9; i <= 11; i++) {
			defaultUnit[i] = allDefaultUnit[6];
			preferredUnit[i] = allPreferredUnit[6];
		}

	}

	private void spCalculationForModel13(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel13();
		calculateSPForModel13(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel13(double[] param) {
		secondaryParameterValue[0] = param[0]
				* param[1];
		secondaryParameterValue[1] = param[1];
		secondaryParameterValue[2] = param[1]
				* param[3];
		secondaryParameterValue[3] = param[3]
				/ param[4];
		secondaryParameterValue[4] = ((secondaryParameterValue[0]
				+ secondaryParameterValue[2] + secondaryParameterValue[3]) + Math
				.sqrt(Math
						.pow(
								(secondaryParameterValue[0]
										+ secondaryParameterValue[2] + secondaryParameterValue[3]),
								2)
						- secondaryParameterValue[0]
						* secondaryParameterValue[3])) / 2;
		secondaryParameterValue[5] = ((secondaryParameterValue[0]
				+ secondaryParameterValue[2] + secondaryParameterValue[3]) - Math
				.sqrt(Math
						.pow(
								(secondaryParameterValue[0]
										+ secondaryParameterValue[2] + secondaryParameterValue[3]),
								2)
						- secondaryParameterValue[0]
						* secondaryParameterValue[3])) / 2;
		secondaryParameterValue[6] = Math.log(2) / secondaryParameterValue[4];
		secondaryParameterValue[7] = Math.log(2) / secondaryParameterValue[5];

	}

	private void copyDefaultAndPreferredUnitForModel13() {

		defaultUnit[0] = allDefaultUnit[0];
		preferredUnit[0] = allPreferredUnit[0];

		defaultUnit[1] = allDefaultUnit[2];
		preferredUnit[1] = allPreferredUnit[2];

		for (int i = 2; i < 6; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		defaultUnit[6] = allDefaultUnit[6];
		preferredUnit[6] = allPreferredUnit[6];

		defaultUnit[7] = allDefaultUnit[6];
		preferredUnit[7] = allPreferredUnit[6];

	}

	private void spCalculationForModel12(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel12();
		calculateSPForModel12(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel12(double[] param) {

		secondaryParameterValue[0] = param[0]
				* param[1];
		secondaryParameterValue[1] = param[1];
		secondaryParameterValue[2] = param[1]
				* param[3];
		secondaryParameterValue[3] = param[1]
				* param[3] / param[4];
		secondaryParameterValue[4] = ((param[0]
				+ param[3] + param[4]) + Math
				.sqrt(Math.pow(
						(param[0]
								+ param[3] + param[4]), 2)
						- 4
						* param[0]
						* param[4])) / 2;
		secondaryParameterValue[5] = ((param[0]
				+ param[3] + param[4]) - Math
				.sqrt(Math.pow(
						(param[0]
								+ param[3] + param[4]), 2)
						- 4
						* param[0]
						* param[4])) / 2;
		secondaryParameterValue[6] = Math.log(2) / secondaryParameterValue[4];
		secondaryParameterValue[7] = Math.log(2) / secondaryParameterValue[5];

	}

	private void copyDefaultAndPreferredUnitForModel12() {

		defaultUnit[0] = allDefaultUnit[3];
		preferredUnit[0] = allPreferredUnit[3];

		defaultUnit[1] = allDefaultUnit[2];
		preferredUnit[1] = allPreferredUnit[2];

		defaultUnit[2] = allDefaultUnit[3];
		preferredUnit[2] = allPreferredUnit[3];

		defaultUnit[3] = allDefaultUnit[2];
		preferredUnit[3] = allPreferredUnit[2];

		defaultUnit[4] = allDefaultUnit[0];
		preferredUnit[4] = allPreferredUnit[0];

		defaultUnit[5] = allDefaultUnit[0];
		preferredUnit[5] = allPreferredUnit[0];

		defaultUnit[6] = allDefaultUnit[6];
		preferredUnit[6] = allPreferredUnit[6];

		defaultUnit[7] = allDefaultUnit[6];
		preferredUnit[7] = allPreferredUnit[6];

	}

	private void spCalculationForModel11(double[] param,double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel11();
		calculateSPForModel11(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel11(double[] param) {

		secondaryParameterValue[0] = param[0]
				* param[1];
		secondaryParameterValue[1] = param[1];
		secondaryParameterValue[2] = param[1]
				* param[3];
		secondaryParameterValue[3] = param[3]
				/ param[4];
		secondaryParameterValue[4] = ((secondaryParameterValue[0]
				+ secondaryParameterValue[2] + secondaryParameterValue[3]) + Math
				.sqrt(Math
						.pow(
								(secondaryParameterValue[0]
										+ secondaryParameterValue[2] + secondaryParameterValue[3]),
								2)
						- secondaryParameterValue[0]
						* secondaryParameterValue[3])) / 2;
		secondaryParameterValue[5] = ((secondaryParameterValue[0]
				+ secondaryParameterValue[2] + secondaryParameterValue[3]) - Math
				.sqrt(Math
						.pow(
								(secondaryParameterValue[0]
										+ secondaryParameterValue[2] + secondaryParameterValue[3]),
								2)
						- secondaryParameterValue[0]
						* secondaryParameterValue[3])) / 2;
		secondaryParameterValue[6] = Math.log(2) / secondaryParameterValue[4];
		secondaryParameterValue[7] = Math.log(2) / secondaryParameterValue[5];

	}

	private void copyDefaultAndPreferredUnitForModel11() {

		defaultUnit[0] = allDefaultUnit[0];
		preferredUnit[0] = allPreferredUnit[0];

		defaultUnit[1] = allDefaultUnit[2];
		preferredUnit[1] = allPreferredUnit[2];

		for (int i = 2; i < 6; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		defaultUnit[6] = allDefaultUnit[6];
		preferredUnit[6] = allPreferredUnit[6];

		defaultUnit[7] = allDefaultUnit[6];
		preferredUnit[7] = allPreferredUnit[6];

	}

	private void spCalculationForModel10(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel10();
		calculateSPForModel10(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel10(double[] param) {

		secondaryParameterValue[0] = param[0]
				* param[1];
		secondaryParameterValue[1] = param[1];
		secondaryParameterValue[2] = param[1]
				* param[3];
		secondaryParameterValue[3] = param[1]
				* param[3] / param[4];
		secondaryParameterValue[4] = ((param[0]
				+ param[3] + param[4]) + Math
				.sqrt(Math.pow(
						(param[0]
								+ param[3] + param[4]), 2)
						- 4
						* param[0]
						* param[4])) / 2;
		secondaryParameterValue[5] = ((param[0]
				+ param[3] + param[4]) - Math
				.sqrt(Math.pow(
						(param[0]
								+ param[3] + param[4]), 2)
						- 4
						* param[0]
						* param[4])) / 2;
		secondaryParameterValue[6] = Math.log(2) / secondaryParameterValue[4];
		secondaryParameterValue[7] = Math.log(2) / secondaryParameterValue[5];

	}

	private void copyDefaultAndPreferredUnitForModel10() {

		defaultUnit[0] = allDefaultUnit[3];
		preferredUnit[0] = allPreferredUnit[3];

		defaultUnit[1] = allDefaultUnit[2];
		preferredUnit[1] = allPreferredUnit[2];

		defaultUnit[2] = allDefaultUnit[3];
		preferredUnit[2] = allPreferredUnit[3];

		defaultUnit[3] = allDefaultUnit[2];
		preferredUnit[3] = allPreferredUnit[2];

		defaultUnit[4] = allDefaultUnit[0];
		preferredUnit[4] = allPreferredUnit[0];

		defaultUnit[5] = allDefaultUnit[0];
		preferredUnit[5] = allPreferredUnit[0];

		defaultUnit[6] = allDefaultUnit[6];
		preferredUnit[6] = allPreferredUnit[6];

		defaultUnit[7] = allDefaultUnit[6];
		preferredUnit[7] = allPreferredUnit[6];

	}

	private void spCalculationForModel9(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel9();
		calculateSPForModel9(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel9(double[] param) {

		secondaryParameterValue[0] = param[0]
				* param[1];
		secondaryParameterValue[1] = param[1];
		secondaryParameterValue[2] = param[1]
				* param[3];
		secondaryParameterValue[3] = param[3]
				/ param[4];
		secondaryParameterValue[4] = ((secondaryParameterValue[0]
				+ secondaryParameterValue[2] + secondaryParameterValue[3]) + Math
				.sqrt(Math
						.pow(
								(secondaryParameterValue[0]
										+ secondaryParameterValue[2] + secondaryParameterValue[3]),
								2)
						- secondaryParameterValue[0]
						* secondaryParameterValue[3])) / 2;
		secondaryParameterValue[5] = ((secondaryParameterValue[0]
				+ secondaryParameterValue[2] + secondaryParameterValue[3]) - Math
				.sqrt(Math
						.pow(
								(secondaryParameterValue[0]
										+ secondaryParameterValue[2] + secondaryParameterValue[3]),
								2)
						- secondaryParameterValue[0]
						* secondaryParameterValue[3])) / 2;
		secondaryParameterValue[6] = Math.log(2) / secondaryParameterValue[4];
		secondaryParameterValue[7] = Math.log(2) / secondaryParameterValue[5];

	}

	private void copyDefaultAndPreferredUnitForModel9() {

		defaultUnit[0] = allDefaultUnit[0];
		preferredUnit[0] = allPreferredUnit[0];

		defaultUnit[1] = allDefaultUnit[2];
		preferredUnit[1] = allPreferredUnit[2];

		for (int i = 2; i < 6; i++) {
			defaultUnit[i] = allDefaultUnit[0];
			preferredUnit[i] = allPreferredUnit[0];
		}

		defaultUnit[6] = allDefaultUnit[6];
		preferredUnit[6] = allPreferredUnit[6];

		defaultUnit[7] = allDefaultUnit[6];
		preferredUnit[7] = allPreferredUnit[6];
	}

	private void spCalculationForModel8(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel8();
		calculateSPForModel8(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel8(double[] param) {
		secondaryParameterValue[0] = param[0]
				/ param[1];
		secondaryParameterValue[1] = secondaryParameterValue[0];
		secondaryParameterValue[2] = Math.log(2) / secondaryParameterValue[1];
	}

	private void copyDefaultAndPreferredUnitForModel8() {
		defaultUnit[0] = allDefaultUnit[0];
		preferredUnit[0] = allPreferredUnit[0];

		defaultUnit[1] = allDefaultUnit[0];
		preferredUnit[1] = allPreferredUnit[0];

		defaultUnit[2] = allDefaultUnit[6];
		preferredUnit[2] = allPreferredUnit[6];
	}

	private void spCalculationForModel7(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel7();
		calculateSPForModel7(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel7(double[] param) {
		secondaryParameterValue[0] = param[0]
				* param[1];
		secondaryParameterValue[1] = param[0];
		secondaryParameterValue[2] = Math.log(2) / param[0];
	}

	private void copyDefaultAndPreferredUnitForModel7() {

		defaultUnit[0] = allDefaultUnit[3];
		preferredUnit[0] = allPreferredUnit[3];

		defaultUnit[1] = allDefaultUnit[0];
		preferredUnit[1] = allPreferredUnit[0];

		defaultUnit[2] = allDefaultUnit[6];
		preferredUnit[2] = allPreferredUnit[6];
	}

	private void spCalculationForModel6(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel6();
		calculateSPForModel6(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel6(double[] param) {
		secondaryParameterValue[0] = param[0]
				/ param[1];
		secondaryParameterValue[1] = secondaryParameterValue[0];
		secondaryParameterValue[2] = Math.log(2) / secondaryParameterValue[1];
	}

	private void copyDefaultAndPreferredUnitForModel6() {

		defaultUnit[0] = allDefaultUnit[0];
		preferredUnit[0] = allPreferredUnit[0];

		defaultUnit[1] = allDefaultUnit[0];
		preferredUnit[1] = allPreferredUnit[0];

		defaultUnit[2] = allDefaultUnit[6];
		preferredUnit[2] = allPreferredUnit[6];
	}

	private void spCalculationForModel5(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel5();
		calculateSPForModel5(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);

	}

	private void calculateSPForModel5(double[] param) {
		secondaryParameterValue[0] = param[0]
				* param[1];
		secondaryParameterValue[1] = param[0];
		secondaryParameterValue[2] = Math.log(2) / param[0];
	}

	private void copyDefaultAndPreferredUnitForModel5() {

		defaultUnit[0] = allDefaultUnit[3];
		preferredUnit[0] = allPreferredUnit[3];

		defaultUnit[1] = allDefaultUnit[0];
		preferredUnit[1] = allPreferredUnit[0];

		defaultUnit[2] = allDefaultUnit[6];
		preferredUnit[2] = allPreferredUnit[6];
		}

	private void spCalculationForModel4(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel4();
		calculateSPForModel4(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel4(double[] param) {

		secondaryParameterValue[0] = param[0]
				/ param[1];
		secondaryParameterValue[1] = secondaryParameterValue[0];
		secondaryParameterValue[2] = Math.log(2) / secondaryParameterValue[1];

	}

	private void copyDefaultAndPreferredUnitForModel4() {
		defaultUnit[0] = allDefaultUnit[0];
		preferredUnit[0] = allPreferredUnit[0];

		defaultUnit[1] = allDefaultUnit[0];
		preferredUnit[1] = allPreferredUnit[0];

		defaultUnit[2] = allDefaultUnit[6];
		preferredUnit[2] = allPreferredUnit[6];
}

	private void spCalculationForModel3(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel3();
		calculateSPForModel3(param);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel3(double[] param) {

		secondaryParameterValue[0] = param[0]
				* param[1];
		secondaryParameterValue[1] = param[0];
		secondaryParameterValue[2] = Math.log(2) / param[0];}

	private void copyDefaultAndPreferredUnitForModel3() {defaultUnit[0] = allDefaultUnit[3];
	preferredUnit[0] = allPreferredUnit[3];

	defaultUnit[1] = allDefaultUnit[0];
	preferredUnit[1] = allPreferredUnit[0];

	defaultUnit[2] = allDefaultUnit[6];
	preferredUnit[2] = allPreferredUnit[6];
}

	private void spCalculationForModel2(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		copyDefaultAndPreferredUnitForModel2();
		calculateSPForModel2(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel2(double[] param, double dose) {

		
		secondaryParameterValue[0] = dose
				/ (param[0] * param[1]) * param[0];

		secondaryParameterValue[1] = param[0]
				 *param[1];
		secondaryParameterValue[2] = dose / param[0];
		secondaryParameterValue[3] = Math.log(2)
				/ secondaryParameterValue[1];
		
		
		secondaryParameterValue[4] = dose
								/ (param[0] *	secondaryParameterValue[1] * secondaryParameterValue[1]);
		
		secondaryParameterValue[5] = secondaryParameterValue[4]
				/ secondaryParameterValue[0];
		secondaryParameterValue[6] = secondaryParameterValue[5]
				* param[1];

	
}

	private void copyDefaultAndPreferredUnitForModel2() {

		defaultUnit[0] = allDefaultUnit[4];
		preferredUnit[0] = allPreferredUnit[4];
		
		defaultUnit[1] = allDefaultUnit[0];
		preferredUnit[1] = allPreferredUnit[0];
		
		defaultUnit[2] = allDefaultUnit[8];
		preferredUnit[2] = allPreferredUnit[8];
		
		defaultUnit[3] = allDefaultUnit[6];
		preferredUnit[3] = allPreferredUnit[6];
		
		defaultUnit[4] = allDefaultUnit[5];
		preferredUnit[4] = allPreferredUnit[5];
		
		defaultUnit[5] = allDefaultUnit[9];
		preferredUnit[5] = allPreferredUnit[9];
		
		defaultUnit[6] = allDefaultUnit[2];
		preferredUnit[6] = allPreferredUnit[2];
		
	}

	private void spCalculationForModel1(double[] param, double dose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		copyDefaultAndPreferredUnitForModel1();
		calculateSPForModel1(param, dose);
		writingOutputInSpreedSheetAndTextArea(defaultUnit, preferredUnit);
	}

	private void calculateSPForModel1(double[] param, double dose) {

		
		secondaryParameterValue[0] = dose
				/ (param[0] * param[1]);

		secondaryParameterValue[1] = Math.log(2)
				/ param[1];
		secondaryParameterValue[2] = dose / param[0];
		secondaryParameterValue[3] = param[0]
				* param[1];
		secondaryParameterValue[4] = dose
								/ (param[0] * param[1] * param[1]);
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

	public void writingOutputInSpreedSheetAndTextArea(String[] defaultUnit,
			String[] preferredUnit) throws RowsExceededException,
			WriteException, BiffException, IOException {

		DefaultToPreferredUnitConverter unitConversionInst = new DefaultToPreferredUnitConverter();
		PkParamEstimator pkpdMainInst = PkParamEstimator
				.createPkParamEstimateInstance();

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
				s[k] = dataSortVariables[pkpdMainInst.pkpdInst.getCurrentProfileNumber()][k];
			}
			s[numberOfSortVariable] = secParamName[i];
			s[numberOfSortVariable + 1] = preferredUnit[i];
			s[numberOfSortVariable + 2] = pkpdInst.formatting(
					secondaryParameterValue[i] * convertAmount,
					false)
					+ "";

			pkpdMainInst.pkpdInst.getNonTransposedSPAL().add(s);
			pkpdMainInst.pkpdInst.workSheetOutputInst.getTextoutput().add(
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
			s[k] = dataSortVariables[pkpdMainInst.pkpdInst.getCurrentProfileNumber()][k];
		}

		for (int i = 0; i < noOfSecParam; i++) {
			double convertAmount = unitConversionInst.unitConversion(
					defaultUnit[i], preferredUnit[i]);

			s[numberOfSortVariable + i] = pkpdInst.formatting(
					secondaryParameterValue[i] * convertAmount,
					false)
					+ "";
		}

		pkpdMainInst.pkpdInst.getSecondaryParametersAL().add(s);
	}

}
