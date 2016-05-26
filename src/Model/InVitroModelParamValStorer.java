package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import view.ProcessingInputsInfo;

public class InVitroModelParamValStorer {
	


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

	public void parameterValuePrinting(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		pkPdInst = PkPdInfo.createPKPDInstance();

		procInputInst = pkPdInst.copyProcessingInput();

		initializition(parameter);

		paramDefaultUnit = new String[noOfParam];
		paramPreferredUnit = new String[noOfParam];
		printingOfModelParameters(parameter, initial, AInv, SD);
	}

	private void initializition(double[] parameter) {
		noOfParam = parameter.length;
		numberOfSortVariable = pkPdInst.numberOfSortVar;
		dataSortVariables = pkPdInst.dataSortVariables;
		paramName = pkPdInst.paramName;
	}

	public void printingOfModelParameters(double[] parameter, double[] initial,
			double[][] AInv, double SD) throws RowsExceededException,
			WriteException, BiffException, IOException {

		pkPdInst = PkPdInfo.createPKPDInstance();

		procInputInst = new ProcessingInputsInfo();

		procInputInst = pkPdInst.copyProcessingInput();

		paramDefaultUnit = procInputInst.getParameterUnitsDataObj()
				.getDefaultUnitArray();
		paramPreferredUnit = procInputInst.getParameterUnitsDataObj()
				.getPreferredUnitsArray();

		clearancaParam = pkPdInst.ifClearanceParam;

		String[] defaultUnit = new String[noOfParam];
		String[] preferredUnit = new String[noOfParam];

		for (int i = 0; i < preferredUnit.length; i++) {
			defaultUnit[i] = "";
			preferredUnit[i] = "";
		}
		
		writingOutputInSpreedSheetAndTextArea(parameter, initial, AInv, SD,
				defaultUnit, preferredUnit);
	}

	public void writingOutputInSpreedSheetAndTextArea(double[] parameter,
			double[] initial, double[][] AInv, double SD, String[] defaultUnit,
			String[] preferredUnit) throws RowsExceededException,
			WriteException, BiffException, IOException {

		procInputInst = PkPdInfo.createPKPDInstance().copyProcessingInput();

		writingOutputInSpreedSheetAndTextAreaForEstimation(parameter, initial,
				AInv, SD, defaultUnit, preferredUnit);

	}

	private void writingOutputInSpreedSheetAndTextAreaForEstimation(
			double[] parameter, double[] initial, double[][] AInv, double SD,
			String[] defaultUnit, String[] preferredUnit) {
		unitConversionInst = new DefaultToPreferredUnitConverter();
		PkPdInfo pkpdInstance = PkPdInfo.createPKPDInstance();
		
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



}
