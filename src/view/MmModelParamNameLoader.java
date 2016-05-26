package view;

public class MmModelParamNameLoader {


	ParameterAndUnitListLoader paramAndUnitListInst;

	public void createParametersName(int libraryModelNo) {
		paramAndUnitListInst = ParameterAndUnitListLoader
				.createParamAndUnitListInstance();
		parametersNameForMmModel(libraryModelNo);
	}

	private void parametersNameForMmModel(int libraryModelNo) {

		if (libraryModelNo == 1) {
			parameterNameOfMm1();
		} else if (libraryModelNo == 2) {
			parameterNameOfMm2();
		} else if (libraryModelNo == 3) {
			parameterNameOfMm3();
		} else if (libraryModelNo == 4) {
			parameterNameOfMm4();
		}else if (libraryModelNo == 5) {
			parameterNameOfMm5();
		}

	}


	private void parameterNameOfMm5() {
		paramAndUnitListInst.numberOfParameter = 5;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 2;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/MMM/LM5.txt");

	}

	private void parameterNameOfMm4() {
		paramAndUnitListInst.numberOfParameter = 5;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 1;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/MMM/LM4.txt");

	}

	private void parameterNameOfMm3() {
		paramAndUnitListInst.numberOfParameter = 4;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 1;
		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/MMM/LM3.txt");

	}

	private void parameterNameOfMm2() {
		paramAndUnitListInst.numberOfParameter = 3;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 1;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/MMM/LM2.txt");

	}

	private void parameterNameOfMm1() {
		paramAndUnitListInst.numberOfParameter = 3;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 1;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/MMM/LM1.txt");

	}



}
