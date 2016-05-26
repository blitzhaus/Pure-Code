package view;

public class PkPdLinkModelParamNameLoader {


	ParameterAndUnitListLoader paramAndUnitListInst;

	public void createParametersName(int libraryModelNo) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		paramAndUnitListInst = ParameterAndUnitListLoader
				.createParamAndUnitListInstance();
		
		parametersNameForPkPdLinkModel(libraryModelNo);
	}

	private void parametersNameForPkPdLinkModel(int libraryModelNo) {

		if (libraryModelNo == 1) {
			parameterNameOfPkPd1();
		} else if (libraryModelNo == 2) {
			parameterNameOfPkPd2();
		} else if (libraryModelNo == 3) {
			parameterNameOfPkPd3();
		} else if (libraryModelNo == 4) {
			parameterNameOfPkPd4();
		} else if (libraryModelNo == 5) {
			parameterNameOfPkPd5();
		} else if (libraryModelNo == 6) {
			parameterNameOfPkPd6();
		} else if (libraryModelNo == 7) {
			parameterNameOfPkPd7();
		} else if (libraryModelNo == 8) {
			parameterNameOfPkPd8();
		}
	}

	private void parameterNameOfPkPd8() {
		paramAndUnitListInst.numberOfParameter = 5;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;
		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PKPDLINK/LM8.txt");

	}

	private void parameterNameOfPkPd7() {
		paramAndUnitListInst.numberOfParameter = 4;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PKPDLINK/LM7.txt");

	}

	private void parameterNameOfPkPd6() {
		paramAndUnitListInst.numberOfParameter = 5;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PKPDLINK/LM6.txt");

	}

	private void parameterNameOfPkPd5() {
		paramAndUnitListInst.numberOfParameter = 4;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PKPDLINK/LM5.txt");

	}

	private void parameterNameOfPkPd4() {
		paramAndUnitListInst.numberOfParameter = 4;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PKPDLINK/LM4.txt");

	}

	private void parameterNameOfPkPd3() {
		paramAndUnitListInst.numberOfParameter = 3;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;
		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PKPDLINK/LM3.txt");

	}

	private void parameterNameOfPkPd2() {
		paramAndUnitListInst.numberOfParameter = 4;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PKPDLINK/LM2.txt");

	}

	private void parameterNameOfPkPd1() {
		paramAndUnitListInst.numberOfParameter = 3;
		paramAndUnitListInst.numberOfFunction = 1;
		paramAndUnitListInst.numberOfDeterminant = 0;

		paramAndUnitListInst.parameterNameForCA = paramAndUnitListInst
				.readFileForSingleDimArray("ParametersName/PKPDLINK/LM1.txt");

	}



}
