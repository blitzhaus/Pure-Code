package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.ReadFile2dStrArray;

public class InVitroParamListLoader {

	String[] parameterNameForInVitro;

	int numberOfParameter;
	int numberOfFunction;
	int numberOfDeterminant;
	int normalizationUnitIndex;

	String[] parameterNameByGroupForInVitro;

	String[] defaultParameterUnitForInVitro;
	String[] preferredParameterUnitForInVitro;

	static InVitroParamListLoader paramListInst = null;

	public static InVitroParamListLoader createParamListInstance() {
		if (paramListInst == null) {
			paramListInst = new InVitroParamListLoader();
		}
		return paramListInst;
	}

	ProcessingInputsInfo procInputInst;

	public void createParameterList() {

		procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		parametersNameForInVitroModel();

		storeParamInfo(procInputInst);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	private void storeParamInfo(ProcessingInputsInfo procInputInst) {
		procInputInst.getProfileAndParamInfoObj().setParameterNameForCA(
				parameterNameForInVitro);

		procInputInst.getProfileAndParamInfoObj().setNumberOfParameter(
				numberOfParameter);

		procInputInst.getProfileAndParamInfoObj().setNumberOfFunction(
				numberOfFunction);

		procInputInst.getProfileAndParamInfoObj().setNumberOfDeterminant(
				numberOfDeterminant);
	}

	private void parametersNameForInVitroModel() {

		if (procInputInst.getInVitroModelInputInst().getIfHillModel() == 1) {
			parameterNameOfModel1();
		} else if (procInputInst.getInVitroModelInputInst().getIfWeibulModel() == 1) {
			parameterNameOfModel2();
		} else if (procInputInst.getInVitroModelInputInst()
				.getIfDoubleWeibulModel() == 1) {
			parameterNameOfModel3();
		} else if (procInputInst.getInVitroModelInputInst()
				.getIfMakoidBanakarModel() == 1) {
			parameterNameOfModel4();
		}

	}

	private void parameterNameOfModel4() {
		numberOfParameter = 5;
		numberOfFunction = 1;
		numberOfDeterminant = 0;

		parameterNameForInVitro = readFileForSingleDimArray("ParametersName/INVITRO/LM4.txt");

	}

	private void parameterNameOfModel3() {
		numberOfParameter = 8;
		numberOfFunction = 1;
		numberOfDeterminant = 0;

		parameterNameForInVitro = readFileForSingleDimArray("ParametersName/INVITRO/LM3.txt");

	}

	private void parameterNameOfModel2() {
		numberOfParameter = 5;
		numberOfFunction = 1;
		numberOfDeterminant = 0;

		parameterNameForInVitro = readFileForSingleDimArray("ParametersName/INVITRO/LM2.txt");

	}

	private void parameterNameOfModel1() {
		numberOfParameter = 5;
		numberOfFunction = 1;
		numberOfDeterminant = 0;
		parameterNameForInVitro = readFileForSingleDimArray("ParametersName/INVITRO/LM1.txt");

	}

	String[] readFileForSingleDimArray(String fileLocation) {

		String[][] temp = readFile(fileLocation);
		String[] paramName = new String[temp.length];
		for (int i = 0; i < temp.length; i++) {
			paramName[i] = temp[i][0];
		}

		return paramName;
	}

	private String[][] readFile(String fileLocation) {
		ReadFile2dStrArray RF = new ReadFile2dStrArray(fileLocation);
		int rowCount = RF.rowCount;
		int colCount = RF.colCount;
		String[][] inputMatrix = new String[rowCount][colCount];
		inputMatrix = RF.fileArray;
		return inputMatrix;
	}

	public InVitroParamListLoader() {
		parameterNameForInVitro = new String[1];
		numberOfParameter = 1;
		numberOfFunction = 1;
		numberOfDeterminant = 1;
	}

	public void unitListForParameters() throws RowsExceededException,
			WriteException, BiffException, IOException {

		unitPrepForInVitro();

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		procInputInst.getParameterUnitsDataObj().setParameterByGroup(
				parameterNameByGroupForInVitro);

		procInputInst.getParameterUnitsDataObj().setDefaultUnitArray(
				defaultParameterUnitForInVitro);
		procInputInst.getParameterUnitsDataObj().setPreferredUnitsArray(
				preferredParameterUnitForInVitro);

		procInputInst.getProfileAndParamInfoObj().setParameterNameByGroupForCA(
				parameterNameByGroupForInVitro);

		procInputInst.getProfileAndParamInfoObj().setDefaultParameterUnitForCA(
				defaultParameterUnitForInVitro);

		procInputInst.getProfileAndParamInfoObj()
				.setPreferredParameterUnitForCA(
						preferredParameterUnitForInVitro);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	private void unitPrepForInVitro() {
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		String timeUnit = procInputInst.getParameterUnitsDataObj()
				.getTimeUnit();

		String fracDissUnit = procInputInst.getParameterUnitsDataObj()
				.getTimeUnit();

		parameterNameByGroupForInVitro = readFileForSingleDimArray("ParameterUnit/parameterGroupingForInVitro.txt");
		defaultParameterUnitForInVitro = new String[parameterNameByGroupForInVitro.length];
		preferredParameterUnitForInVitro = new String[parameterNameByGroupForInVitro.length];

		defaultParameterUnitForInVitro[0] = timeUnit;
		defaultParameterUnitForInVitro[1] = "";
		defaultParameterUnitForInVitro[2] = fracDissUnit;
		defaultParameterUnitForInVitro[3] = fracDissUnit;

		defaultParameterUnitForInVitro[4] = timeUnit;
		defaultParameterUnitForInVitro[5] = "";
		defaultParameterUnitForInVitro[6] = timeUnit;

		for (int i = 0; i < defaultParameterUnitForInVitro.length; i++) {
			preferredParameterUnitForInVitro[i] = defaultParameterUnitForInVitro[i];
		}

	}

}
