public package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import view.ApplicationInfo;
import view.DisplayContents;
import Common.ReadFile2dStrArray;

public class ParameterAndUnitListLoader {
	String[] parameterNameForCA;
	String[] linkParameterNameForCA;
	String[] parameterNameByGroupForCA;

	String[] defaultParameterUnitForCA;
	String[] preferredParameterUnitForCA;
	
	int numberOfLinkParameter;
	int numberOfFunctionLinkParam;
	int numberOfDeterminantLinkParam;
	int numberOfParameter;
	int numberOfFunction;
	int numberOfDeterminant;
	int normalizationUnitIndex;
	String normalizationUnit;
	boolean clearancaParam;

	static ParameterAndUnitListLoader paramAndUnitListInst = null;

	public static ParameterAndUnitListLoader createParamAndUnitListInstance() {
		if (paramAndUnitListInst == null) {
			paramAndUnitListInst = new ParameterAndUnitListLoader();
		}
		return paramAndUnitListInst;
	}

	public void createParameterAndCorrespondingUnitList() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		
		if (analysisType.equals("pk")) {
			int libraryModelNo = procInputInst.getModelInputTab1Obj()
					.getModelNumberForCA();
			clearancaParam = procInputInst.getModelInputTab1Obj()
					.isIfClearanceParam();

			if (procInputInst.getModelInputTab1Obj()
					.getAlgebraicModel() == true)
				parametersNameForAEModel(libraryModelNo);
			else
				parametersNameForDEModel(libraryModelNo);

			storeParamInfo(procInputInst);
			CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		} else if (analysisType.equals("pd")) {
			
			int libraryModelNo = procInputInst.getModelInputTab1Obj()
					.getModelNumberForCA();
			PdModelParamNameLoader pdModelParamNameInst = new PdModelParamNameLoader();
			pdModelParamNameInst.createParametersName(libraryModelNo);

			storeParamInfo(procInputInst);

			CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		} else if (analysisType.equals("mm")) {
			
			int libraryModelNo = procInputInst.getModelInputTab1Obj()
					.getModelNumberForCA();
			MmModelParamNameLoader mmModelParamNameInst = new MmModelParamNameLoader();
			mmModelParamNameInst.createParametersName(libraryModelNo);

			storeParamInfo(procInputInst);

			CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		} else if (analysisType.equals("pkpdlink")) {

			int libraryModelNo = procInputInst.getModelInputTab1Obj()
					.getModelNumberForLinkModel();
			clearancaParam = false;

			parametersNameForAEModel(libraryModelNo);
			
			storeParamInfoForlinkModel(procInputInst);

			libraryModelNo = procInputInst.getModelInputTab1Obj()
					.getModelNumberForCA();
			PkPdLinkModelParamNameLoader pdModelParamNameInst = new PkPdLinkModelParamNameLoader();
			pdModelParamNameInst.createParametersName(libraryModelNo);

			storeParamInfo(procInputInst);

			CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		} else if (analysisType.equals("irm")) {

			int libraryModelNo = procInputInst.getModelInputTab1Obj()
					.getModelNumberForLinkModel();
			clearancaParam = false;

			parametersNameForAEModel(libraryModelNo);
			
			storeParamInfoForlinkModel(procInputInst);

			libraryModelNo = procInputInst.getModelInputTab1Obj()
					.getModelNumberForCA();
			IrModelParamNameLoader irModelParamNameInst = new IrModelParamNameLoader();
			irModelParamNameInst.createParametersName(libraryModelNo);

			storeParamInfo(procInputInst);

			CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		}

	}

	private void storeParamInfo(ProcessingInputsInfo procInputInst) {
		procInputInst.getProfileAndParamInfoObj().setParameterNameForCA(
				parameterNameForCA);

		procInputInst.getProfileAndParamInfoObj().setNumberOfParameter(
				numberOfParameter);

		procInputInst.getProfileAndParamInfoObj().setNumberOfFunction(
				numberOfFunction);

		procInputInst.getProfileAndParamInfoObj().setNumberOfDeterminant(
				numberOfDeterminant);
	}

	private void storeParamInfoForlinkModel(ProcessingInputsInfo procInputInst) {
		procInputInst.getProfileAndParamInfoObj().setParameterNameForLinkModel(
				parameterNameForCA);

		procInputInst.getProfileAndParamInfoObj().setNumberOfLinkParam(
				numberOfParameter);

		procInputInst.getProfileAndParamInfoObj().setNumberOfFunction(
				numberOfFunction);

		procInputInst.getProfileAndParamInfoObj().setNumberOfDeterminant(
				numberOfDeterminant);
	}

	private void parametersNameForAEModel(int libraryModelNo) {

		if (libraryModelNo == 1) {
			parameterNameOfAM1();
		}
		if (libraryModelNo == 2) {
			parameterNameOfAM2();
		}
		if (libraryModelNo == 3) {
			parameterNameOfAM3();
		}
		if (libraryModelNo == 4) {
			parameterNameOfAM4();
		}

		if (libraryModelNo == 5) {
			parameterNameOfAM5();
		}
		if (libraryModelNo == 6) {
			parameterNameOfAM6();
		}
		if (libraryModelNo == 7) {
			parameterNameOfAM7();
		}
		if (libraryModelNo == 8) {
			parameterNameOfAM8();
		}
		if (libraryModelNo == 9) {
			parameterNameOfAM9();
		}
		if (libraryModelNo == 10) {
			parameterNameOfAM10();
		}
		if (libraryModelNo == 11) {
			parameterNameOfAM11();
		}
		if (libraryModelNo == 12) {
			parameterNameOfAM12();
		}
		if (libraryModelNo == 13) {
			parameterNameOfAM13();
		}
		if (libraryModelNo == 14) {
			parameterNameOfAM14();
		}
		if (libraryModelNo == 15) {
			parameterNameOfAM15();
		}
		if (libraryModelNo == 16) {
			parameterNameOfAM16();
		}
		if (libraryModelNo == 17) {
			parameterNameOfAM17();
		}
		if (libraryModelNo == 18) {
			parameterNameOfAM18();
		}

		if (libraryModelNo == 19) {
			parameterNameOfAM19();
		}

	}

	private void parameterNameOfAM19() {
		numberOfParameter = 6;
		numberOfFunction = 1;
		numberOfDeterminant = 0;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM19.txt");
	}

	private void parameterNameOfAM18() {
		numberOfParameter = 6;
		numberOfFunction = 1;
		numberOfDeterminant = 0;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM18.txt");
	}

	private void parameterNameOfAM17() {
		numberOfParameter = 4;
		numberOfFunction = 1;
		numberOfDeterminant = 0;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM17.txt");
	}

	private void parameterNameOfAM16() {
		numberOfParameter = 4;
		numberOfFunction = 1;
		numberOfDeterminant = 0;
		if (clearancaParam == false)
			parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM16.txt");
		else
			parameterNameForCA = readFileForSingleDimArray("ParametersName/ClAE/LM16.txt");
	}

	private void parameterNameOfAM15() {
		numberOfParameter = 2;
		numberOfFunction = 1;
		numberOfDeterminant = 0;

		if (clearancaParam == false)
			parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM15.txt");
		else
			parameterNameForCA = readFileForSingleDimArray("ParametersName/ClAE/LM15.txt");

	}

	private void parameterNameOfAM14() {
		numberOfParameter = 6;
		numberOfFunction = 1;
		numberOfDeterminant = 0;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM14.txt");

	}

	private void parameterNameOfAM13() {
		numberOfParameter = 5;
		numberOfFunction = 1;
		numberOfDeterminant = 0;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM13.txt");

	}

	private void parameterNameOfAM12() {
		numberOfParameter = 6;
		numberOfFunction = 1;
		numberOfDeterminant = 0;

		if (clearancaParam == false)
			parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM12.txt");
		else
			parameterNameForCA = readFileForSingleDimArray("ParametersName/ClAE/LM12.txt");

	}

	private void parameterNameOfAM11() {
		numberOfParameter = 5;
		numberOfFunction = 1;
		numberOfDeterminant = 0;

		if (clearancaParam == false)
			parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM11.txt");
		else
			parameterNameForCA = readFileForSingleDimArray("ParametersName/ClAE/LM11.txt");

	}

	private void parameterNameOfAM10() {
		numberOfParameter = 4;
		numberOfFunction = 1;
		numberOfDeterminant = 0;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM10.txt");

	}

	private void parameterNameOfAM9() {
		numberOfParameter = 4;
		numberOfFunction = 1;
		numberOfDeterminant = 0;

		if (clearancaParam == false)
			parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM9.txt");
		else
			parameterNameForCA = readFileForSingleDimArray("ParametersName/ClAE/LM9.txt");

	}

	private void parameterNameOfAM8() {
		numberOfParameter = 4;
		numberOfFunction = 1;
		numberOfDeterminant = 0;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM8.txt");

	}

	private void parameterNameOfAM7() {
		numberOfParameter = 4;
		numberOfFunction = 1;
		numberOfDeterminant = 0;

		if (clearancaParam == false)
			parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM7.txt");
		else
			parameterNameForCA = readFileForSingleDimArray("ParametersName/ClAE/LM7.txt");

	}

	private void parameterNameOfAM6() {
		numberOfParameter = 3;
		numberOfFunction = 1;
		numberOfDeterminant = 0;

		if (clearancaParam == false)
			parameterNameForCA =  readFileForSingleDimArray("ParametersName/AE/LM6.txt");
		else
			parameterNameForCA = readFileForSingleDimArray("ParametersName/ClAE/LM6.txt");

	}

	private void parameterNameOfAM5() {
		numberOfParameter = 2;
		numberOfFunction = 1;
		numberOfDeterminant = 0;
		if (clearancaParam == false)
			parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM5.txt");
		else
			parameterNameForCA = readFileForSingleDimArray("ParametersName/ClAE/LM5.txt");

	}

	private void parameterNameOfAM4() {
		numberOfParameter = 4;
		numberOfFunction = 1;
		numberOfDeterminant = 0;

		if (clearancaParam == false)
			parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM4.txt");
		else
			parameterNameForCA = readFileForSingleDimArray("ParametersName/ClAE/LM4.txt");

	}

	private void parameterNameOfAM3() {
		numberOfParameter = 3;
		numberOfFunction = 1;
		numberOfDeterminant = 0;

		if (clearancaParam == false)
			parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM3.txt");
		else
			parameterNameForCA = readFileForSingleDimArray("ParametersName/ClAE/LM3.txt");

	}

	private void parameterNameOfAM2() {
		numberOfParameter = 2;
		numberOfFunction = 1;
		numberOfDeterminant = 1;

		if (clearancaParam == false)
			parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM2.txt");
		else
			parameterNameForCA = readFileForSingleDimArray("ParametersName/ClAE/LM2.txt");

	}

	private void parameterNameOfAM1() {
		numberOfParameter = 2;
		numberOfFunction = 1;
		numberOfDeterminant = 1;

		if (clearancaParam == false)
			parameterNameForCA = readFileForSingleDimArray("ParametersName/AE/LM1.txt");
		else
			parameterNameForCA = readFileForSingleDimArray("ParametersName/ClAE/LM1.txt");

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

	public void parametersNameForDEModel(int modelNumber) {
		if (modelNumber == 1) {
			parameterNameOfDM1();
		}else
		if (modelNumber == 2) {
			parameterNameOfDM2();
		}else
		if (modelNumber == 3) {
			parameterNameOfDM3();
		}else
		if (modelNumber == 4) {
			parameterNameOfDM4();
		}else
		if (modelNumber == 5) {
			parameterNameOfDM5();
		}else
		if (modelNumber == 6) {
			parameterNameOfDM6();
		}else
		if (modelNumber == 7) {
			parameterNameOfDM7();
		}else
		if (modelNumber == 8) {
			parameterNameOfDM8();
		}else
		if (modelNumber == 9) {
			parameterNameOfDM9();
		}else
		if (modelNumber == 10) {
			parameterNameOfDM10();
		}else
		if (modelNumber == 11) {
			parameterNameOfDM11();
		}else
		if (modelNumber == 12) {
			parameterNameOfDM12();
		}else
		if (modelNumber == 13) {
			parameterNameOfDM13();
		}else
		if (modelNumber == 14) {
			parameterNameOfDM14();
		}else
		if (modelNumber == 15) {
			parameterNameOfDM15();
		}else
		if (modelNumber == 16) {
			parameterNameOfDM16();
		}else
		if (modelNumber == 17) {
			parameterNameOfDM17();
		}else
		if (modelNumber == 18) {
			parameterNameOfDM18();
		}else
		if (modelNumber == 19) {
			parameterNameOfDM19();
		}else
		if (modelNumber == 20) {
			parameterNameOfDM20();
		}else
		if (modelNumber == 21) {
			parameterNameOfDM21();
		}else
		if (modelNumber == 22) {
			parameterNameOfDM22();
		}else
		if (modelNumber == 23) {
			parameterNameOfDM23();
		}else
		if (modelNumber == 24) {
			parameterNameOfDM24();
		}else
			if (modelNumber == 25) {
				parameterNameOfDM25();
			}else
			if (modelNumber == 26) {
				parameterNameOfDM26();
			}else
			if (modelNumber == 27) {
				parameterNameOfDM27();
			}else
			if (modelNumber == 28) {
				parameterNameOfDM28();
			}
	}

	private void parameterNameOfDM28() {
		numberOfParameter = 5;
		numberOfFunction = 2;
		numberOfDeterminant = 2;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM28.txt");

	}

	private void parameterNameOfDM27() {
		numberOfParameter = 5;
		numberOfFunction = 2;
		numberOfDeterminant = 2;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM27.txt");
	}

	private void parameterNameOfDM26() {
		numberOfParameter = 3;
		numberOfFunction = 1;
		numberOfDeterminant = 1;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM26.txt");
	}

	private void parameterNameOfDM25() {
		numberOfParameter = 3;
		numberOfFunction  = 1;
		numberOfDeterminant = 1;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM25.txt");
	}
	
	
	private void parameterNameOfDM24() {
		numberOfParameter = 4;
		numberOfFunction = 2;
		numberOfDeterminant = 2;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM24.txt");

	}

	private void parameterNameOfDM23() {
		numberOfParameter = 5;
		numberOfFunction = 2;
		numberOfDeterminant = 2;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM23.txt");
	}

	private void parameterNameOfDM22() {
		numberOfParameter = 2;
		numberOfFunction = 1;
		numberOfDeterminant = 1;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM22.txt");
	}

	private void parameterNameOfDM21() {
		numberOfParameter = 2;
		numberOfFunction = 1;
		numberOfDeterminant = 1;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM21.txt");
	}

	private void parameterNameOfDM20() {
		numberOfParameter = 6;
		numberOfFunction = 3;
		numberOfDeterminant = 3;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM20.txt");

	}

	private void parameterNameOfDM19() {
		numberOfParameter = 6;
		numberOfFunction = 3;
		numberOfDeterminant = 3;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM19.txt");
	}

	private void parameterNameOfDM18() {
		numberOfParameter = 6;
		numberOfFunction = 3;
		numberOfDeterminant = 3;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM18.txt");
	}

	private void parameterNameOfDM17() {
		numberOfParameter = 6;
		numberOfFunction = 3;
		numberOfDeterminant = 3;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM17.txt");
	}

	private void parameterNameOfDM16() {
		numberOfParameter = 4;
		numberOfFunction = 2;
		numberOfDeterminant = 2;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM16.txt");
	}

	private void parameterNameOfDM15() {
		numberOfParameter = 4;
		numberOfFunction = 2;
		numberOfDeterminant = 2;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM15.txt");
	}

	private void parameterNameOfDM14() {
		numberOfParameter = 6;
		numberOfFunction = 2;
		numberOfDeterminant = 2;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM14.txt");
	}

	private void parameterNameOfDM13() {
		numberOfParameter = 6;
		numberOfFunction = 2;
		numberOfDeterminant = 2;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM13.txt");
	}

	private void parameterNameOfDM12() {
		numberOfParameter = 5;
		numberOfFunction = 2;
		numberOfDeterminant = 2;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM12.txt");
	}

	private void parameterNameOfDM11() {
		numberOfParameter = 5;
		numberOfFunction = 2;
		numberOfDeterminant = 2;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM11.txt");
	}

	private void parameterNameOfDM10() {
		numberOfParameter = 4;
		numberOfFunction = 2;
		numberOfDeterminant = 2;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM10.txt");
	}

	private void parameterNameOfDM9() {
		numberOfParameter = 4;
		numberOfFunction = 2;
		numberOfDeterminant = 2;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM9.txt");
	}

	private void parameterNameOfDM8() {
		numberOfParameter = 2;
		numberOfFunction = 1;
		numberOfDeterminant = 2;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM8.txt");
	}

	private void parameterNameOfDM7() {
		numberOfParameter = 2;
		numberOfFunction = 1;
		numberOfDeterminant = 1;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM7.txt");
	}

	private void parameterNameOfDM6() {
		numberOfParameter = 4;
		numberOfFunction = 1;
		numberOfDeterminant = 1;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM6.txt");
	}

	private void parameterNameOfDM5() {
		numberOfParameter = 4;
		numberOfFunction = 1;
		numberOfDeterminant = 1;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM5.txt");
	}

	private void parameterNameOfDM4() {
		numberOfParameter = 3;
		numberOfFunction = 1;
		numberOfDeterminant = 1;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM4.txt");
	}

	private void parameterNameOfDM3() {
		numberOfParameter = 3;
		numberOfFunction = 1;
		numberOfDeterminant = 1;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM3.txt");
	}

	private void parameterNameOfDM2() {
		numberOfParameter = 2;
		numberOfFunction = 1;
		numberOfDeterminant = 1;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM2.txt");
	}

	private void parameterNameOfDM1() {
		numberOfParameter = 2;
		numberOfFunction = 1;
		numberOfDeterminant = 1;
		parameterNameForCA = readFileForSingleDimArray("ParametersName/DE/LM1.txt");
	}

	public void unitListForParameters() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType().equals(
						"pk")) {
			unitPrepForPk();
		} else if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType().equals("pd")) {
			unitPrepForPd();
		} else if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType().equals("mm")) {
			unitPrepForMm();
		} else if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType().equals(
				"pkpdlink")) {
			unitPrepForPkPdLink();
		} else if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType()
				.equals("irm")) {
			unitPrepForIrm();
		}

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		procInputInst.getParameterUnitsDataObj().setParameterByGroup(
				parameterNameByGroupForCA);

		procInputInst.getParameterUnitsDataObj().setDefaultUnitArray(
				defaultParameterUnitForCA);
		procInputInst.getParameterUnitsDataObj().setPreferredUnitsArray(
				preferredParameterUnitForCA);

		procInputInst.getProfileAndParamInfoObj().setParameterNameByGroupForCA(
				parameterNameByGroupForCA);

		procInputInst.getProfileAndParamInfoObj().setDefaultParameterUnitForCA(
				defaultParameterUnitForCA);

		procInputInst.getProfileAndParamInfoObj()
				.setPreferredParameterUnitForCA(preferredParameterUnitForCA);

		procInputInst.getProfileAndParamInfoObj().setNormalizationUnit(
				normalizationUnit);

		procInputInst.getProfileAndParamInfoObj().setNormalizationUnitIndex(
				normalizationUnitIndex);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType().equals(
						"pk")) {
			
			
			procInputInst = CaMapingHandler.createCaMapHandInst()
			.copyProcessingInput();
			CaParamUnitsDispGuiCreator.createCaParUnitsDisInst()
			.paramUnitTableRebuilding(procInputInst);
		} else

		{

			procInputInst = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();
			CaParamUnitsDispGuiCreator.createCaParUnitsDisInst()
					.paramUnitTableRebuilding(procInputInst);
		}
		

	}

	private void unitPrepForIrm() throws RowsExceededException, WriteException, BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		String timeUnit = procInputInst.getParameterUnitsDataObj()
				.getTimeUnit();

		String concMassUnit = procInputInst.getParameterUnitsDataObj()
				.getConcMassUnit();
		String volumeUnit = procInputInst.getParameterUnitsDataObj()
				.getVolumeUnit();
		
		String responseUnit = CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().responseUnitTextField.getText();


		parameterNameByGroupForCA = readFileForSingleDimArray("ParameterUnit/parameterGroupingForIrm.txt");
		defaultParameterUnitForCA = new String[parameterNameByGroupForCA.length];
		preferredParameterUnitForCA = new String[parameterNameByGroupForCA.length];

		defaultParameterUnitForCA[0] = "1/" + timeUnit;
		defaultParameterUnitForCA[1] = concMassUnit + "/" + volumeUnit;
		defaultParameterUnitForCA[2] = responseUnit;
		
		for (int i = 0; i < defaultParameterUnitForCA.length; i++) {
			preferredParameterUnitForCA[i] = defaultParameterUnitForCA[i];
		}
	}

	

	private void unitPrepForMm() {

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		String timeUnit = procInputInst.getParameterUnitsDataObj()
				.getTimeUnit();

		String concMassUnit = procInputInst.getParameterUnitsDataObj()
				.getConcMassUnit();
		String volumeUnit = procInputInst.getParameterUnitsDataObj()
				.getVolumeUnit();
		
		parameterNameByGroupForCA = readFileForSingleDimArray("ParameterUnit/parameterGroupingForMm.txt");
		defaultParameterUnitForCA = new String[parameterNameByGroupForCA.length];
		preferredParameterUnitForCA = new String[parameterNameByGroupForCA.length];

		defaultParameterUnitForCA[0] = volumeUnit;
		defaultParameterUnitForCA[1] = "1/" + timeUnit;
		defaultParameterUnitForCA[2] = concMassUnit + "/"
		+ volumeUnit;
		defaultParameterUnitForCA[3] = "1/" + timeUnit;
		defaultParameterUnitForCA[4] = timeUnit;
		defaultParameterUnitForCA[5] = timeUnit + "*" + concMassUnit + "/"
		+ volumeUnit;
		defaultParameterUnitForCA[6] = timeUnit;
		

		

		for (int i = 0; i < defaultParameterUnitForCA.length; i++) {
			preferredParameterUnitForCA[i] = defaultParameterUnitForCA[i];
		}

	}

	private void unitPrepForPk() {

		normalizationUnitDetection();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		String timeUnit = procInputInst.getParameterUnitsDataObj()
				.getTimeUnit();

		String concMassUnit = procInputInst.getParameterUnitsDataObj()
				.getConcMassUnit();
		String volumeUnit = procInputInst.getParameterUnitsDataObj()
				.getVolumeUnit();

		parameterNameByGroupForCA = readFileForSingleDimArray("ParameterUnit/parameterGrouping.txt");
		defaultParameterUnitForCA = new String[parameterNameByGroupForCA.length];
		preferredParameterUnitForCA = new String[parameterNameByGroupForCA.length];

		defaultParameterUnitForCA[0] = "1/" + timeUnit;
		defaultParameterUnitForCA[1] = concMassUnit + "/" + volumeUnit;

		
		if (normalizationUnitIndex != 0) {

			defaultParameterUnitForCA[2] = volumeUnit + "/" + normalizationUnit;
		} else {
			defaultParameterUnitForCA[2] = volumeUnit;
		}

		if (normalizationUnitIndex != 0) {

			defaultParameterUnitForCA[3] = volumeUnit + "/" + timeUnit + "/"
					+ normalizationUnit;
		} else {
			defaultParameterUnitForCA[3] = volumeUnit + "/" + timeUnit;
		}

		defaultParameterUnitForCA[4] = timeUnit + "*" + concMassUnit + "/"
				+ volumeUnit;
		defaultParameterUnitForCA[5] = timeUnit + "*" + timeUnit + "*"
				+ concMassUnit + "/" + volumeUnit;
		defaultParameterUnitForCA[6] = timeUnit;
		defaultParameterUnitForCA[7] = timeUnit;
		defaultParameterUnitForCA[8] = concMassUnit + "/" + volumeUnit;
		defaultParameterUnitForCA[9] = timeUnit;

			for (int i = 0; i < defaultParameterUnitForCA.length; i++) {
				preferredParameterUnitForCA[i] = defaultParameterUnitForCA[i];
			}
		

	}

	private void unitPrepForPd() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		String concMassUnit = procInputInst.getParameterUnitsDataObj()
				.getConcMassUnit();
		String volumeUnit = procInputInst.getParameterUnitsDataObj()
				.getVolumeUnit();
		String responseUnit = CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().responseUnitTextField.getText();
		
		parameterNameByGroupForCA = readFileForSingleDimArray("ParameterUnit/parameterGroupingForPd.txt");
		defaultParameterUnitForCA = new String[parameterNameByGroupForCA.length];
		preferredParameterUnitForCA = new String[parameterNameByGroupForCA.length];

		defaultParameterUnitForCA[0] = responseUnit;
		defaultParameterUnitForCA[1] = concMassUnit + "/" + volumeUnit;

		

		for (int i = 0; i < defaultParameterUnitForCA.length; i++) {
			preferredParameterUnitForCA[i] = defaultParameterUnitForCA[i];
		}

	}

	
	private void unitPrepForPkPdLink() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		String timeUnit = procInputInst.getParameterUnitsDataObj()
				.getTimeUnit();

		String concMassUnit = procInputInst.getParameterUnitsDataObj()
				.getConcMassUnit();
		String volumeUnit = procInputInst.getParameterUnitsDataObj()
				.getVolumeUnit();
		String responseUnit = CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().responseUnitTextField
				.getText();

		parameterNameByGroupForCA = readFileForSingleDimArray("ParameterUnit/parameterGroupingForPkPdlink.txt");
		defaultParameterUnitForCA = new String[parameterNameByGroupForCA.length];
		preferredParameterUnitForCA = new String[parameterNameByGroupForCA.length];

		defaultParameterUnitForCA[0] = responseUnit;
		defaultParameterUnitForCA[1] = concMassUnit + "/" + volumeUnit;
		defaultParameterUnitForCA[2] = "1" + "/" + timeUnit;

		for (int i = 0; i < defaultParameterUnitForCA.length; i++) {
			preferredParameterUnitForCA[i] = defaultParameterUnitForCA[i];
		}

	}

	private void normalizationUnitDetection() {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		normalizationUnitIndex = procInputInst.getModelInputTab1Obj()
				.getNormalizationIndex();

		if (normalizationUnitIndex == 0)
			normalizationUnit = "";
		else if (normalizationUnitIndex == 1)
			normalizationUnit = "Kg";
		else if (normalizationUnitIndex == 2)
			normalizationUnit = "m*m";
	}

	public ParameterAndUnitListLoader() {
		parameterNameForCA = new String[1];
		parameterNameByGroupForCA = new String[1];
		defaultParameterUnitForCA = new String[1];
		preferredParameterUnitForCA = new String[1];
		numberOfParameter = 1;
		numberOfFunction = 1;
		numberOfDeterminant = 1;
	}

	
}
