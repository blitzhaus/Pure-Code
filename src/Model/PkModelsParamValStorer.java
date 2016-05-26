package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import view.ProcessingInputsInfo;

public class PkModelsParamValStorer {

	double[] tValue = { 12.706, 4.303, 3.182, 2.776, 2.571, 2.447, 2.365,
			2.306, 2.262, 2.228, 2.201, 2.179, 2.160, 2.145, 2.131, 2.120,
			2.110, 2.101, 2.093, 2.086, 2.080, 2.074, 2.069, 2.064, 2.060, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	String[] paramDefaultUnit;
	String[] paramPreferredUnit;
	int noOfParam;

	int numberOfSortVariable;
	String[][] dataSortVariables;
	String[] paramName;
	boolean clearancaParam;

	PkPdInfo pkPdInst;
	ProcessingInputsInfo procInputInst;
	DefaultToPreferredUnitConverter unitConversionInst;

	public static PkModelsParamValStorer printParamInst = null;

	public static PkModelsParamValStorer createParamPrintInstance() {
		if (printParamInst == null) {
			printParamInst = new PkModelsParamValStorer();
		}
		return printParamInst;
	}

	public PkModelsParamValStorer() {

		paramDefaultUnit = new String[1];
		paramPreferredUnit = new String[1];

	}

	DeModelsParamValStorer deModelParamPrintInst;

	public void parameterValuePrinting(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		pkPdInst = PkPdInfo.createPKPDInstance();

		procInputInst = pkPdInst.copyProcessingInput();

		initializition();

		paramDefaultUnit = new String[noOfParam];
		paramPreferredUnit = new String[noOfParam];
		if (pkPdInst.ifAlgebraicModel == true) {
			printingOfPkModels(parameter, initial, AInv, SD);
		} else {
			deModelParamPrintInst = new DeModelsParamValStorer();
			deModelParamPrintInst.parameterValuePrinting(parameter, initial,
					AInv, SD);
		}

	}

	private void initializition() {
		noOfParam = pkPdInst.noOfParam;
		numberOfSortVariable = pkPdInst.numberOfSortVar;
		dataSortVariables = pkPdInst.dataSortVariables;
		paramName = pkPdInst.paramName;
	}

	public void printingOfPkModels(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		pkPdInst = PkPdInfo.createPKPDInstance();

		procInputInst = new ProcessingInputsInfo();

		procInputInst = pkPdInst.copyProcessingInput();

		int modelNumber = pkPdInst.modelNumber;

		paramDefaultUnit = procInputInst.getParameterUnitsDataObj()
				.getDefaultUnitArray();
		paramPreferredUnit = procInputInst.getParameterUnitsDataObj()
				.getPreferredUnitsArray();

		clearancaParam = pkPdInst.ifClearanceParam;

		if (modelNumber == 1) {
			printingOfLibraryModel1(parameter, initial, AInv, SD);
		} else if (modelNumber == 2) {
			printingOfLibraryModel2(parameter, initial, AInv, SD);
		} else if (modelNumber == 3) {
			printingOfLibraryModel3(parameter, initial, AInv, SD);
		} else if (modelNumber == 4) {
			printingOfLibraryModel4(parameter, initial, AInv, SD);
		} else if (modelNumber == 5) {
			printingOfLibraryModel5(parameter, initial, AInv, SD);
		} else if (modelNumber == 6) {
			printingOfLibraryModel6(parameter, initial, AInv, SD);
		} else if (modelNumber == 7) {
			printingOfLibraryModel7(parameter, initial, AInv, SD);
		} else if (modelNumber == 8) {
			printingOfLibraryModel8(parameter, initial, AInv, SD);
		} else if (modelNumber == 9) {
			printingOfLibraryModel9(parameter, initial, AInv, SD);
		} else if (modelNumber == 10) {
			printingOfLibraryModel10(parameter, initial, AInv, SD);
		} else if (modelNumber == 11) {
			printingOfLibraryModel11(parameter, initial, AInv, SD);
		} else if (modelNumber == 12) {
			printingOfLibraryModel12(parameter, initial, AInv, SD);
		} else if (modelNumber == 13) {
			printingOfLibraryModel13(parameter, initial, AInv, SD);
		} else if (modelNumber == 14) {
			printingOfLibraryModel14(parameter, initial, AInv, SD);
		} else if (modelNumber == 15) {
			printingOfLibraryModel15(parameter, initial, AInv, SD);
		} else if (modelNumber == 16) {
			printingOfLibraryModel16(parameter, initial, AInv, SD);
		} else if (modelNumber == 17) {
			printingOfLibraryModel17(parameter, initial, AInv, SD);
		} else if (modelNumber == 18) {
			printingOfLibraryModel18(parameter, initial, AInv, SD);
		} else if (modelNumber == 19) {
			printingOfLibraryModel19(parameter, initial, AInv, SD);
		}

	}

	public void headerForEstimatedParameter() throws RowsExceededException,
			WriteException, BiffException, IOException {

		PkPdInfo pkPdInst = PkPdInfo.createPKPDInstance();

		boolean ifSimulation = pkPdInst.ifSimulation;
		if (ifSimulation == true) {
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput().add(
							StringUtils.rightPad("", 40)
									/* + "\t" */
									+ StringUtils.rightPad(
											"ESTIMATED PARAMETERS VALUE", 30)
									+ "\r\n\r\n");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput().add(
							StringUtils.rightPad("", 5)
									+ StringUtils.rightPad("Parameter", 10)
									+ "\t" + StringUtils.rightPad("Unit", 15)
									+ "\t"
									+ StringUtils.rightPad("Estimate", 10)
									+ "\t" + StringUtils.rightPad("VIF", 10)
									+ "\t");
			SimulationForLibraryModels.createSimulationInst().wsOutput
					.getTextoutput().add(
							StringUtils.rightPad("", 5)
									+ StringUtils.rightPad("", 10) + "\t"
									+ StringUtils.rightPad("", 15) + "\t"
									+ StringUtils.rightPad("", 10) + "\t"
									+ StringUtils.rightPad("", 10) + "\t"
									+ "\r\n");

		} else {

			pkPdInst.workSheetOutputInst
					.getTextoutput()
					.add(
							StringUtils
									.rightPad(
											"*************************************************"
													+ "*****************************************************************",
											80)
									+ "\r\n\r\n");
			pkPdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 40)
					/* + "\t" */
					+ StringUtils.rightPad("ESTIMATED PARAMETERS VALUE", 30)
							+ "\r\n\r\n");
			pkPdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad("Parameter", 10) + "\t"
							+ StringUtils.rightPad("Unit", 15) + "\t"
							+ StringUtils.rightPad("Estimate", 10) + "\t"
							+ StringUtils.rightPad("SE", 10) + "\t"
							+ StringUtils.rightPad("CV%", 10) + "\t"
							+ StringUtils.rightPad("UNI_LOWER", 10) + "\t"
							+ StringUtils.rightPad("UNI_UPPER", 10) + "\r\n");
			pkPdInst.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5) + StringUtils.rightPad("", 10)
							+ "\t" + StringUtils.rightPad("", 15) + "\t"
							+ StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\t"
							+ StringUtils.rightPad("", 10) + "\r\n");
		}

	}

	private void printingOfLibraryModel1(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {
		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		if (clearancaParam == false) {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[0];
			preferredUnit[1] = paramPreferredUnit[0];
		} else {

			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[3];
			preferredUnit[1] = paramPreferredUnit[3];

		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);

	}

	private void printingOfLibraryModel2(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		if (clearancaParam == false) {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[0];
			preferredUnit[1] = paramPreferredUnit[0];
		} else {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[3];
			preferredUnit[1] = paramPreferredUnit[3];

		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);

	}

	private void printingOfLibraryModel3(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		if (clearancaParam == false) {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[0];
			preferredUnit[1] = paramPreferredUnit[0];

			defaultUnit[2] = paramDefaultUnit[0];
			preferredUnit[2] = paramPreferredUnit[0];
		} else {

			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[0];
			preferredUnit[1] = paramPreferredUnit[0];

			defaultUnit[2] = paramDefaultUnit[3];
			preferredUnit[2] = paramPreferredUnit[3];

		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);

	}

	private void printingOfLibraryModel4(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		if (clearancaParam == false) {

			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[0];
			preferredUnit[1] = paramPreferredUnit[0];

			defaultUnit[2] = paramDefaultUnit[0];
			preferredUnit[2] = paramPreferredUnit[0];

			defaultUnit[3] = paramDefaultUnit[7];
			preferredUnit[3] = paramPreferredUnit[7];
		} else {

			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[0];
			preferredUnit[1] = paramPreferredUnit[0];

			defaultUnit[2] = paramDefaultUnit[3];
			preferredUnit[2] = paramPreferredUnit[3];

			defaultUnit[3] = paramDefaultUnit[7];
			preferredUnit[3] = paramPreferredUnit[7];

		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);

	}

	private void printingOfLibraryModel5(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		if (clearancaParam == false) {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[0];
			preferredUnit[1] = paramPreferredUnit[0];
		} else {

			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[3];
			preferredUnit[1] = paramPreferredUnit[3];

		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);

	}

	private void printingOfLibraryModel6(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		if (clearancaParam == false) {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[0];
			preferredUnit[1] = paramPreferredUnit[0];

			defaultUnit[2] = paramDefaultUnit[7];
			preferredUnit[2] = paramPreferredUnit[7];
		} else {

			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[3];
			preferredUnit[1] = paramPreferredUnit[3];

			defaultUnit[2] = paramDefaultUnit[7];
			preferredUnit[2] = paramPreferredUnit[7];

		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);

	}

	private void printingOfLibraryModel7(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		if (clearancaParam == false) {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			for (int i = 1; i < noOfParam; i++) {
				defaultUnit[i] = paramDefaultUnit[0];
				preferredUnit[i] = paramPreferredUnit[0];
			}
		} else {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[0] = paramDefaultUnit[3];
			preferredUnit[0] = paramPreferredUnit[3];

			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[0] = paramDefaultUnit[3];
			preferredUnit[0] = paramPreferredUnit[3];
		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);

	}

	private void printingOfLibraryModel8(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		defaultUnit[0] = paramDefaultUnit[1];
		preferredUnit[0] = paramPreferredUnit[1];

		defaultUnit[1] = paramDefaultUnit[1];
		preferredUnit[1] = paramPreferredUnit[1];

		defaultUnit[2] = paramDefaultUnit[0];
		preferredUnit[2] = paramPreferredUnit[0];

		defaultUnit[3] = paramDefaultUnit[0];
		preferredUnit[3] = paramPreferredUnit[0];

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);

	}

	private void printingOfLibraryModel9(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		if (clearancaParam == false) {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			for (int i = 1; i < noOfParam; i++) {
				defaultUnit[i] = paramDefaultUnit[0];
				preferredUnit[i] = paramPreferredUnit[0];
			}
		} else {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[3];
			preferredUnit[1] = paramPreferredUnit[3];

			defaultUnit[2] = paramDefaultUnit[2];
			preferredUnit[2] = paramPreferredUnit[2];

			defaultUnit[3] = paramDefaultUnit[3];
			preferredUnit[3] = paramPreferredUnit[3];
		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);
	}

	private void printingOfLibraryModel10(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		defaultUnit[0] = paramDefaultUnit[2];
		preferredUnit[0] = paramPreferredUnit[2];

		for (int i = 1; i < noOfParam; i++) {
			defaultUnit[i] = paramDefaultUnit[0];
			preferredUnit[i] = paramPreferredUnit[0];
		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);
	}

	private void printingOfLibraryModel11(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		if (clearancaParam == false) {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			for (int i = 1; i < noOfParam; i++) {
				defaultUnit[i] = paramDefaultUnit[0];
				preferredUnit[i] = paramPreferredUnit[0];
			}
		} else {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[0] = paramDefaultUnit[0];
			preferredUnit[0] = paramPreferredUnit[0];

			defaultUnit[0] = paramDefaultUnit[3];
			preferredUnit[0] = paramPreferredUnit[3];

			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[0] = paramDefaultUnit[3];
			preferredUnit[0] = paramPreferredUnit[3];
		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);
	}

	private void printingOfLibraryModel12(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		if (clearancaParam == false) {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			for (int i = 1; i < noOfParam - 1; i++) {
				defaultUnit[i] = paramDefaultUnit[0];
				preferredUnit[i] = paramPreferredUnit[0];
			}

			defaultUnit[5] = paramDefaultUnit[7];
			preferredUnit[5] = paramPreferredUnit[7];
		} else {

			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[0] = paramDefaultUnit[0];
			preferredUnit[0] = paramPreferredUnit[0];

			defaultUnit[0] = paramDefaultUnit[3];
			preferredUnit[0] = paramPreferredUnit[3];

			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[0] = paramDefaultUnit[3];
			preferredUnit[0] = paramPreferredUnit[3];

			defaultUnit[0] = paramDefaultUnit[7];
			preferredUnit[0] = paramPreferredUnit[7];

		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);
	}

	private void printingOfLibraryModel13(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		defaultUnit[0] = paramDefaultUnit[1];
		preferredUnit[0] = paramPreferredUnit[1];

		defaultUnit[1] = paramDefaultUnit[1];
		preferredUnit[1] = paramPreferredUnit[1];

		for (int i = 2; i < noOfParam; i++) {
			defaultUnit[i] = paramDefaultUnit[0];
			preferredUnit[i] = paramPreferredUnit[0];
		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);
	}

	private void printingOfLibraryModel14(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		defaultUnit[0] = paramDefaultUnit[1];
		preferredUnit[0] = paramPreferredUnit[1];

		defaultUnit[1] = paramDefaultUnit[1];
		preferredUnit[1] = paramPreferredUnit[1];

		for (int i = 2; i < noOfParam - 1; i++) {
			defaultUnit[i] = paramDefaultUnit[0];
			preferredUnit[i] = paramPreferredUnit[0];
		}

		defaultUnit[5] = paramDefaultUnit[7];
		preferredUnit[5] = paramPreferredUnit[7];

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);
	}

	private void printingOfLibraryModel15(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		if (clearancaParam == false) {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[0];
			preferredUnit[1] = paramPreferredUnit[0];
		} else {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[3];
			preferredUnit[1] = paramPreferredUnit[3];

		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);
	}

	private void printingOfLibraryModel16(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		if (clearancaParam == false) {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			for (int i = 1; i < noOfParam; i++) {
				defaultUnit[i] = paramDefaultUnit[0];
				preferredUnit[i] = paramPreferredUnit[0];
			}
		} else {
			defaultUnit[0] = paramDefaultUnit[2];
			preferredUnit[0] = paramPreferredUnit[2];

			defaultUnit[1] = paramDefaultUnit[3];
			preferredUnit[1] = paramPreferredUnit[3];

			defaultUnit[2] = paramDefaultUnit[2];
			preferredUnit[2] = paramPreferredUnit[2];

			defaultUnit[3] = paramDefaultUnit[3];
			preferredUnit[3] = paramPreferredUnit[3];

		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);
	}

	private void printingOfLibraryModel17(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		defaultUnit[0] = paramDefaultUnit[1];
		preferredUnit[0] = paramPreferredUnit[1];

		defaultUnit[1] = paramDefaultUnit[1];
		preferredUnit[1] = paramPreferredUnit[1];

		defaultUnit[2] = paramDefaultUnit[0];
		preferredUnit[2] = paramPreferredUnit[0];

		defaultUnit[3] = paramDefaultUnit[0];
		preferredUnit[3] = paramPreferredUnit[0];

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);
	}

	private void printingOfLibraryModel18(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		for (int i = 0; i < 3; i++) {
			defaultUnit[i] = paramDefaultUnit[1];
			preferredUnit[i] = paramPreferredUnit[1];
		}

		for (int i = 3; i < noOfParam; i++) {
			defaultUnit[i] = paramDefaultUnit[0];
			preferredUnit[i] = paramPreferredUnit[0];
		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);
	}

	private void printingOfLibraryModel19(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		defaultUnit[0] = paramDefaultUnit[2];
		preferredUnit[0] = paramPreferredUnit[2];

		for (int i = 1; i < preferredUnit.length; i++) {
			defaultUnit[i] = paramDefaultUnit[0];
			preferredUnit[i] = paramPreferredUnit[0];
		}

		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);
	}

	public void writingOutputInSpreedSheetAndTextArea(double[] parameter,
			double[] initial, double[][] AInv, double SD, String[] defaultUnit,
			String[] preferredUnit) throws RowsExceededException,
			WriteException, BiffException, IOException {

		procInputInst = PkPdInfo.createPKPDInstance().copyProcessingInput();

		boolean ifSimulation = procInputInst.getModelInputTab1Obj()
				.isSimulation();

		if (ifSimulation == true) {
			writingOutputInSpreedSheetAndTextAreaForSimulation(parameter, AInv,
					defaultUnit, preferredUnit);
		} else {
			writingOutputInSpreedSheetAndTextAreaForEstimation(parameter,
					initial, AInv, SD, defaultUnit, preferredUnit);
		}

	}

	private void writingOutputInSpreedSheetAndTextAreaForEstimation(
			double[] parameter, double[] initial, double[][] AInv, double SD,
			String[] defaultUnit, String[] preferredUnit) {
		unitConversionInst = new DefaultToPreferredUnitConverter();
		PkPdInfo pkpdInstance = PkPdInfo.createPKPDInstance();
		/*
		 * CaParametersCalculation pkpdMainInst = CaParametersCalculation
		 * .createPKPDMainInstance();
		 */
		double[] SE = new double[noOfParam];
		double[] CV = new double[noOfParam];
		int count = 0;
		String[] strForHorizontalParam = new String[numberOfSortVariable + 3
				* noOfParam];

		for (int k = 0; k < numberOfSortVariable; k++) {
			strForHorizontalParam[k] = dataSortVariables[pkpdInstance
					.getCurrentProfileNumber()][k];
			count++;
		}

		for (int i = 0; i < noOfParam; i++) {

			double convertAmount = unitConversionInst.unitConversion(
					defaultUnit[i], preferredUnit[i]);
			SE[i] = SD * Math.sqrt(AInv[i][i]);
			CV[i] = Math.abs(SE[i] / parameter[i]) * 100;

			String[] s = new String[numberOfSortVariable + 5];
			for (int k = 0; k < numberOfSortVariable; k++) {
				s[k] = dataSortVariables[pkpdInstance.getCurrentProfileNumber()][k];
			}

			s[numberOfSortVariable] = paramName[i];

			s[numberOfSortVariable + 1] = preferredUnit[i];
			s[numberOfSortVariable + 2] = pkpdInstance.formatting(parameter[i]
					* convertAmount, false)
					+ "";
			s[numberOfSortVariable + 3] = pkpdInstance.formatting(SE[i], false)
					+ "";
			s[numberOfSortVariable + 4] = pkpdInstance.formatting(CV[i], false)
					+ "";

			strForHorizontalParam[count++] = pkpdInstance.formatting(
					parameter[i] * convertAmount, false)
					+ "";

			strForHorizontalParam[count++] = pkpdInstance.formatting(SE[i],
					false)
					+ "";

			strForHorizontalParam[count++] = pkpdInstance.formatting(CV[i],
					false)
					+ "";

			pkpdInstance.verticalParametersAL.add(s);

			// printing for final parameter value
			pkpdInstance.workSheetOutputInst.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(paramName[i], 10)
							+ "\t"
							+ StringUtils.rightPad(preferredUnit[i], 15)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									parameter[i] * convertAmount, false), 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									SE[i], false), 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									CV[i], false), 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									parameter[i]
											* convertAmount
											- tValue[pkpdInstance
													.getDegreesOfFreedom() - 1]
											* SE[i], false), 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									parameter[i]
											* convertAmount
											+ tValue[pkpdInstance
													.getDegreesOfFreedom() - 1]
											* SE[i], false), 10) + "\r\n");

		}

		pkpdInstance.horizontalParametersAL.add(strForHorizontalParam);

	}

	private void writingOutputInSpreedSheetAndTextAreaForSimulation(
			double[] parameter, double[][] aInv, String[] defaultUnit,
			String[] preferredUnit) {

		DefaultToPreferredUnitConverter unitConversionInst = new DefaultToPreferredUnitConverter();
		PkPdInfo pkpdInstance = PkPdInfo.createPKPDInstance();

		SimulationForLibraryModels simulationInst = SimulationForLibraryModels
				.createSimulationInst();

		for (int i = 0; i < noOfParam; i++) {

			double convertAmount = unitConversionInst.unitConversion(
					defaultUnit[i], preferredUnit[i]);

			// printing for final parameter value
			simulationInst.wsOutput.getTextoutput().add(
					StringUtils.rightPad("", 5)
							+ StringUtils.rightPad(paramName[i], 10)
							+ "\t"
							+ StringUtils.rightPad(preferredUnit[i], 15)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									parameter[i] * convertAmount, false), 10)
							+ "\t"
							+ StringUtils.rightPad(pkpdInstance.formatting(
									aInv[i][i], false), 10)

							+ "\r\n");

			String[] s = new String[numberOfSortVariable + 5];
			for (int k = 0; k < numberOfSortVariable; k++) {
				s[k] = simulationInst.pkpdInstance.dataSortVariables[simulationInst.pkpdInstance
						.getCurrentProfileNumber()][k];
			}

			s[numberOfSortVariable] = paramName[i];

			s[numberOfSortVariable + 1] = preferredUnit[i];
			s[numberOfSortVariable + 2] = pkpdInstance.formatting(parameter[i]
					* convertAmount, false)
					+ "";

			simulationInst.verticalParametersAL.add(s);

		}

	}

}
