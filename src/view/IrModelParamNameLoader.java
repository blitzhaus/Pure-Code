package view;

public class IrModelParamNameLoader {



	ParameterAndUnitListLoader paramAndUnitListInst;

	public void createParametersName(int libraryModelNo) {
		paramAndUnitListInst = ParameterAndUnitListLoader
				.createParamAndUnitListInstance();
		parametersNameForIrModel(libraryModelNo);
	}

	private void parametersNameForIrModel(int libraryModelNo) {

		if (libraryModelNo == 1) {
			parameterNameOfIr1();
		} else if (libraryModelNo == 2) {
			parameterNameOfIr2();
		} else if (libraryModelNo == 3) {
			parameterNameOfIr3();
		} else if (libraryModelNo == 4) {
			parameterNameOfIr4();
		}

	}


	private void parameterNameOfIr4() {
		paramAndUnitListInst.numberOfParameter = 4;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 1;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/IRM/LM4.txt");

	}

	private void parameterNameOfIr3() {
		paramAndUnitListInst.numberOfParameter = 4;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 1;
		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/IRM/LM3.txt");

	}

	private void parameterNameOfIr2() {
		paramAndUnitListInst.numberOfParameter = 3;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 1;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/IRM/LM2.txt");

	}

	private void parameterNameOfIr1() {
		paramAndUnitListInst.numberOfParameter = 3;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 1;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/IRM/LM1.txt");

	}


}
