package view;

import view.ApplicationInfo;
import view.DisplayContents;
import view.ReadFile2dStrArray;

public class SecondaryParameterList {

	int numberOfSecondaryParameters;
	String[] secondaryParameterNameForCA;
	boolean clearancaParam;

	static SecondaryParameterList secondaryParamListInst = null;

	public static SecondaryParameterList createSecParamAndUnitListInstance() {
		if (secondaryParamListInst == null) {
			secondaryParamListInst = new SecondaryParameterList();
		}
		return secondaryParamListInst;
	}

	public void createSecondaryParameterList(int libraryModelNo) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		clearancaParam = procInputInst.getModelInputTab1Obj()
				.isIfClearanceParam();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.equals("pk")) {
			if (procInputInst.getModelInputTab1Obj().getAlgebraicModel() == true) {
				if (clearancaParam == false)
					secondaryParametersNameForAEModel(libraryModelNo);
				else
					secondaryParametersNameForClAEModel(libraryModelNo);
			} else
				secondaryParametersNameForDEModel(libraryModelNo);
		} else if (analysisType.equals("pd")) {
			secondaryParametersNameForPDModel(libraryModelNo);
		} else if (analysisType.equals("mm")) {
			secondaryParametersNameForMMModel(libraryModelNo);
		} else if (analysisType.equals("pkpdlink")) {
			secondaryParametersNameForPDModel(libraryModelNo);
		}else if (analysisType.equals("ascii")) {
			secondaryParametersNameForAsciiModel();
		}

		procInputInst.getProfileAndParamInfoObj()
				.setNumberOfSecondaryParameters(numberOfSecondaryParameters);

		procInputInst.getProfileAndParamInfoObj()
				.setSecondaryParameterNameForCA(secondaryParameterNameForCA);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	private void secondaryParametersNameForAsciiModel() {
		
		
	}

	private void secondaryParametersNameForMMModel(int libraryModelNo) {

		if (libraryModelNo == 1) {
			setNumberOfSecondaryParameters(1);

			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/MMM/LM1.txt"));
		} else if (libraryModelNo == 3) {
			setNumberOfSecondaryParameters(1);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/MMM/LM3.txt"));
		} else if (libraryModelNo == 4) {
			setNumberOfSecondaryParameters(1);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/MMM/LM4.txt"));
		}else if (libraryModelNo == 5) {
			setNumberOfSecondaryParameters(3);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/MMM/LM5.txt"));
		}
	}

	private void secondaryParametersNameForPDModel(int libraryModelNo) {

		if (libraryModelNo == 2) {
			setNumberOfSecondaryParameters(1);

			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/PD/LM2.txt"));
		} else if (libraryModelNo == 4) {
			setNumberOfSecondaryParameters(1);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/PD/LM4.txt"));
		} else if (libraryModelNo == 6) {
			setNumberOfSecondaryParameters(1);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/PD/LM6.txt"));
		} else if (libraryModelNo == 8) {
			setNumberOfSecondaryParameters(1);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/PD/LM8.txt"));
		}
	}

	private void secondaryParametersNameForClAEModel(int libraryModelNo) {

		if (libraryModelNo == 1) {
			setNumberOfSecondaryParameters(7);

			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/ClAE/LM1.txt"));
		} else if (libraryModelNo == 2) {
			setNumberOfSecondaryParameters(7);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/ClAE/LM2.txt"));
		} else if (libraryModelNo == 3) {
			setNumberOfSecondaryParameters(6);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/ClAE/LM3.txt"));
		} else if (libraryModelNo == 4) {
			setNumberOfSecondaryParameters(6);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/ClAE/LM4.txt"));
		} else

		if (libraryModelNo == 5) {
			setNumberOfSecondaryParameters(5);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/ClAE/LM5.txt"));
		} else if (libraryModelNo == 6) {
			setNumberOfSecondaryParameters(5);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/ClAE/LM6.txt"));
		} else if (libraryModelNo == 7) {
			setNumberOfSecondaryParameters(15);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/ClAE/LM7.txt"));
		} else

		if (libraryModelNo == 9) {
			setNumberOfSecondaryParameters(15);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/ClAE/LM9.txt"));
		} else

		if (libraryModelNo == 11) {
			setNumberOfSecondaryParameters(14);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/ClAE/LM11.txt"));
		} else if (libraryModelNo == 12) {
			setNumberOfSecondaryParameters(14);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/ClAE/LM12.txt"));
		} else

		if (libraryModelNo == 15) {
			setNumberOfSecondaryParameters(2);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/ClAE/LM15.txt"));
		} else if (libraryModelNo == 16) {
			setNumberOfSecondaryParameters(10);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/ClAE/LM16.txt"));
		}

	}

	public SecondaryParameterList() {
		numberOfSecondaryParameters = 1;
		secondaryParameterNameForCA = new String[1];
	}

	private void secondaryParametersNameForDEModel(int libraryModelNo) {
		if (libraryModelNo == 1) {

			setNumberOfSecondaryParameters(7);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM1.txt"));

		} else

		if (libraryModelNo == 2) {
			setNumberOfSecondaryParameters(7);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM2.txt"));
		} else if (libraryModelNo == 3) {
			setNumberOfSecondaryParameters(3);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM3.txt"));
		} else if (libraryModelNo == 4) {
			setNumberOfSecondaryParameters(3);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM4.txt"));
		} else if (libraryModelNo == 5) {
			setNumberOfSecondaryParameters(3);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM5.txt"));
		} else if (libraryModelNo == 6) {
			setNumberOfSecondaryParameters(3);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM6.txt"));
		} else if (libraryModelNo == 7) {
			setNumberOfSecondaryParameters(3);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM7.txt"));
		} else if (libraryModelNo == 8) {
			setNumberOfSecondaryParameters(3);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM8.txt"));
		} else if (libraryModelNo == 9) {
			setNumberOfSecondaryParameters(9);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM9.txt"));
		} else if (libraryModelNo == 10) {
			setNumberOfSecondaryParameters(8);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM10.txt"));
		} else if (libraryModelNo == 11) {
			setNumberOfSecondaryParameters(8);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM11.txt"));
		} else if (libraryModelNo == 12) {
			setNumberOfSecondaryParameters(8);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM12.txt"));
		} else if (libraryModelNo == 13) {
			setNumberOfSecondaryParameters(8);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM13.txt"));
		} else if (libraryModelNo == 14) {
			setNumberOfSecondaryParameters(8);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM14.txt"));
		} else if (libraryModelNo == 15) {
			setNumberOfSecondaryParameters(8);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM15.txt"));
		} else if (libraryModelNo == 16) {
			setNumberOfSecondaryParameters(8);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM16.txt"));
		} else if (libraryModelNo == 17) {
			setNumberOfSecondaryParameters(12);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM17.txt"));
		} else if (libraryModelNo == 18) {
			setNumberOfSecondaryParameters(2);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM18.txt"));
		} else if (libraryModelNo == 19) {
			setNumberOfSecondaryParameters(2);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM19.txt"));
		} else if (libraryModelNo == 20) {
			setNumberOfSecondaryParameters(2);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/DE/LM20.txt"));
		}

	}

	private void secondaryParametersNameForAEModel(int libraryModelNo) {
		if (libraryModelNo == 1) {
			setNumberOfSecondaryParameters(7);

			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM1.txt"));
		} else if (libraryModelNo == 2) {
			setNumberOfSecondaryParameters(7);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM2.txt"));
		} else if (libraryModelNo == 3) {
			setNumberOfSecondaryParameters(6);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM3.txt"));
		} else if (libraryModelNo == 4) {
			setNumberOfSecondaryParameters(6);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM4.txt"));
		} else

		if (libraryModelNo == 5) {
			setNumberOfSecondaryParameters(5);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM5.txt"));
		} else if (libraryModelNo == 6) {
			setNumberOfSecondaryParameters(5);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM6.txt"));
		} else if (libraryModelNo == 7) {
			setNumberOfSecondaryParameters(15);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM7.txt"));
		} else if (libraryModelNo == 8) {
			setNumberOfSecondaryParameters(15);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM8.txt"));
		} else if (libraryModelNo == 9) {
			setNumberOfSecondaryParameters(15);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM9.txt"));
		} else if (libraryModelNo == 10) {
			setNumberOfSecondaryParameters(15);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM10.txt"));
		} else if (libraryModelNo == 11) {
			setNumberOfSecondaryParameters(14);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM11.txt"));
		} else if (libraryModelNo == 12) {
			setNumberOfSecondaryParameters(14);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM12.txt"));
		} else if (libraryModelNo == 13) {
			setNumberOfSecondaryParameters(14);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM13.txt"));
		} else if (libraryModelNo == 14) {
			setNumberOfSecondaryParameters(14);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM14.txt"));
		} else if (libraryModelNo == 15) {
			setNumberOfSecondaryParameters(2);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM15.txt"));
		} else if (libraryModelNo == 16) {
			setNumberOfSecondaryParameters(10);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM16.txt"));
		} else if (libraryModelNo == 17) {
			setNumberOfSecondaryParameters(10);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM17.txt"));
		} else if (libraryModelNo == 18) {
			setNumberOfSecondaryParameters(20);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM18.txt"));
		} else

		if (libraryModelNo == 19) {
			setNumberOfSecondaryParameters(20);
			setSecondaryParameterNameForCA(readFileForSingleDimArray("SecondaryParamsName/AE/LM19.txt"));
		}
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

		System.out.println("rowCount.." + rowCount);
		System.out.println("colCount.." + colCount);

		String[][] inputMatrix = new String[rowCount][colCount];
		String[] stringArray = new String[rowCount];
		inputMatrix = RF.fileArray;
		stringArray = RF.stringArray;

		return inputMatrix;
	}

	public int getNumberOfSecondaryParameters() {
		return numberOfSecondaryParameters;
	}

	public void setNumberOfSecondaryParameters(int numberOfSecondaryParameters) {
		this.numberOfSecondaryParameters = numberOfSecondaryParameters;
	}

	public String[] getSecondaryParameterNameForCA() {
		return secondaryParameterNameForCA;
	}

	public void setSecondaryParameterNameForCA(
			String[] secondaryParameterNameForCA) {
		this.secondaryParameterNameForCA = secondaryParameterNameForCA;
	}

}
