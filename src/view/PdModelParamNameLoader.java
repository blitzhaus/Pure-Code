package view;

public class PdModelParamNameLoader {

	ParameterAndUnitListLoader paramAndUnitListInst;

	public void createParametersName(int libraryModelNo) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		paramAndUnitListInst = ParameterAndUnitListLoader
				.createParamAndUnitListInstance();
		
		parametersNameForPdModel(libraryModelNo);
	}

	private void parametersNameForPdModel(int libraryModelNo) {

		if (libraryModelNo == 1) {
			parameterNameOfPd1();
		} else if (libraryModelNo == 2) {
			parameterNameOfPd2();
		} else if (libraryModelNo == 3) {
			parameterNameOfPd3();
		} else if (libraryModelNo == 4) {
			parameterNameOfPd4();
		} else if (libraryModelNo == 5) {
			parameterNameOfPd5();
		} else if (libraryModelNo == 6) {
			parameterNameOfPd6();
		} else if (libraryModelNo == 7) {
			parameterNameOfPd7();
		} else if (libraryModelNo == 8) {
			parameterNameOfPd8();
		}
	}

	private void parameterNameOfPd8() {
		paramAndUnitListInst.numberOfParameter = 4;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;
		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PD/LM8.txt");

	}

	private void parameterNameOfPd7() {
		paramAndUnitListInst.numberOfParameter = 3;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PD/LM7.txt");

	}

	private void parameterNameOfPd6() {
		paramAndUnitListInst.numberOfParameter = 4;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PD/LM6.txt");

	}

	private void parameterNameOfPd5() {
		paramAndUnitListInst.numberOfParameter = 3;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PD/LM5.txt");

	}

	private void parameterNameOfPd4() {
		paramAndUnitListInst.numberOfParameter = 3;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PD/LM4.txt");

	}

	private void parameterNameOfPd3() {
		paramAndUnitListInst.numberOfParameter = 2;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;
		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PD/LM3.txt");

	}

	private void parameterNameOfPd2() {
		paramAndUnitListInst.numberOfParameter = 3;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PD/LM2.txt");

	}

	private void parameterNameOfPd1() {
		paramAndUnitListInst.numberOfParameter = 2;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PD/LM1.txt");

	}

}
