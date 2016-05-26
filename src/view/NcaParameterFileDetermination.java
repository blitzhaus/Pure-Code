package view;

import java.io.IOException;
import java.util.HashMap;

import Common.DataReader2;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaParameterFileDetermination {

	public static NcaParameterFileDetermination NCA_PAR_DET = null;

	public static NcaParameterFileDetermination createNcaParDetInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NCA_PAR_DET == null) {
			NCA_PAR_DET = new NcaParameterFileDetermination(
					"just object creation");
		}
		return NCA_PAR_DET;
	}

	public NcaParameterFileDetermination(String dummy) {

	}

	void ncaParameterFileDetermination() throws RowsExceededException,
			WriteException, BiffException, IOException {
		fillTheHashMap();
		parFileDetermination();
	}

	HashMap<String, String> parameterFileNamesHM;
	HashMap<String, String> parameterUnitsHM;
	String fileName;

	// the hash map contains the combination and
	// the file name.
	void fillTheHashMap() {
		parameterFileNamesHM = new HashMap<String, String>();
		DataReader2 dd2 = new DataReader2(
				"ParameterFileNames & Combination.txt", "String");
		String[][] combinations = dd2.new_StringFileArray;
		for (int i = 0; i < combinations.length; i++) {
			parameterFileNamesHM.put(combinations[i][0], combinations[i][1]);
		}
	}

	void parFileDetermination() throws RowsExceededException, WriteException,
			BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String combCode = null;

		// model type
		int modelType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getModelType();
		if (modelType == 0) {
			combCode = "0";
		} else if (modelType == 1) {
			combCode = "1";
		} else if (modelType == 2) {
			combCode = "2";
		}

		// root of administration
		int rootOfAdmin = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getRootOfAdmistration();
		if (rootOfAdmin == 0) {
			combCode += "0";
		} else if (rootOfAdmin == 1) {
			combCode += "1";
		} else if (rootOfAdmin == 2) {
			combCode += "2";
		}

		// check if serial or sparse
		boolean isSparse = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getisSparseSelected();
		if (isSparse == true) {
			combCode += "1";
		} else if (isSparse == false) {
			combCode += "0";
		}

		// determine the tau related combination digit.
		combCode += determineNoMultipleDoseProfiles();

		// determine the file name
		fileName = parameterFileNamesHM.get(combCode);

		// fill units hash map
		fillTheUnitsHashMap();

	}

	void fillTheUnitsHashMap() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		parameterUnitsHM = new HashMap<String, String>();
		String[] parameterGroups = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj()
				.getParameterByGroup();

		String[] parameterUnits = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj()
				.getPreferredUnitsArray();

		// split the unit groups & store them in parameter units hash map
		for (int i = 0; i < parameterGroups.length; i++) {
			String group = parameterGroups[i];
			String[] individualParameters = group.split(",");
			for (int j = 0; j < individualParameters.length; j++) {
				parameterUnitsHM
						.put(individualParameters[j], parameterUnits[i]);
			}
		}
	}

	private String determineNoMultipleDoseProfiles()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		// serial data
		String numberOfProf = "0";
		if (NcaOutputGeneration.createNcaOutGeneration()
				.checkIfTauGreaterThanZeroForAllProfiles() == true) {
			numberOfProf = "2";

		} else if (NcaOutputGeneration.createNcaOutGeneration()
				.checkIfTauPresentForAtleastOneProfile() == true) {
			numberOfProf = "1";

		} else if (NcaOutputGeneration.createNcaOutGeneration()
				.checkIfNoprofileHasTau() == true) {
			numberOfProf = "0";
		}

		return numberOfProf;
	}

}
