package view;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class InVitroSetupDispCompGuiCreator {

	public InVitroSetupDispCompGuiCreator() {

	}

	public static InVitroSetupDispCompGuiCreator SETUP_DISP_COMP_GUI = null;

	public static InVitroSetupDispCompGuiCreator createInVitroMainScrSetupDispGui()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (SETUP_DISP_COMP_GUI == null) {
			SETUP_DISP_COMP_GUI = new InVitroSetupDispCompGuiCreator();
		}
		return SETUP_DISP_COMP_GUI;
	}

	InVitroMappingDispGuiCreator inVitroMapDispGui;
	InVitroInitEstimateDispGuiCreator inVitroInitEstimateDispGui;
	InVitroParamUnitDispGuiCreator inVitroParamUnitDispInst;

	// mapping change detection
	String prevConcColName;
	String prevTimeColName;
	boolean timeColChanged;
	boolean concColChanged;
	boolean sortVarChanged;
	int previousSize;
	String[] previousSortVariables;

	public void inVitroSetupDispCompGuiCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {

		// This internal frame will contain many internal frames corresponding
		// to each of the components available
		initialization();
		inVitroMapDispGui = InVitroMappingDispGuiCreator
				.createMappingInstance();
		InVitroParamUnitDispGuiCreator.createInVitroParUnitsDisInst().inVitroParameterUnitsDispGuiCreation();

	}

	private void initialization() {
		prevConcColName = "";
		prevTimeColName = "";
		previousSize = 0;
		previousSortVariables = new String[1];
		timeColChanged = false;
		concColChanged = false;
		sortVarChanged = false;

	}

	public void prepareInitialSetup() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		if (InVitroSetupAvailableCompCreator.createInVitroSetupAvailCompInst().availableComponentsTree
				.getSelectionPath() != null) {

			String[] pathSplits = detectSetupPaths();

			mappingChangeDetection();

			if (procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true) {
				String message = decideMessage();
				CaMapingHandler.createCaMapHandInst().showMessage(message);

				boolean ifValidmapping = validationOfMapping();

				if (ifValidmapping == true)
					detectProfiles();
				else {
					message = "Invalid Mapping";
					CaMapingHandler.createCaMapHandInst().showMessage(message);
					procInputInst.getMappingDataObj()
							.setIfMappingScreenIsChanged(false);

				}

			}

			if (InVitroSetupAvailableCompCreator
					.createInVitroSetupAvailCompInst().availableComponentsTree
					.getMinSelectionRow() == 0) {
				ImportedDataSheet.importedDataSheetFrame.moveToFront();
				DDViewLayer.createFeaturesPanelInstance().PreviewLable
						.setVisible(true);
			}

			if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Mapping]")) {
				doMapping();
			}

			else if (pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Initial Estimate]")) {

				if ((procInputInst.getInVitroModelInputInst().getIfHillModel() == 1)
						|| (procInputInst.getInVitroModelInputInst()
								.getIfWeibulModel() == 1)
						|| (procInputInst.getInVitroModelInputInst()
								.getIfDoubleWeibulModel() == 1)
						|| (procInputInst.getInVitroModelInputInst()
								.getIfMakoidBanakarModel() == 1)) {
					provideInitialEstimates();

					InVitroInitEstimateDispGuiCreator.createInVitroInitEstGui().initialParamTableForInVitro
							.setFocusable(true);

					InVitroOptionsGuiCreator.createInVitroOptionsInstance().moduleTabs
							.setSelectedIndex(1);

				} else {

					String message = "Please choose a model";
					CaMapingHandler.createCaMapHandInst().showMessage(message);

				}
			}else if (pathSplits[pathSplits.length - 1]
			 					.equalsIgnoreCase(" Unit]")) {
				provideUnits();
				
			}

		}
	}

	private void provideUnits() throws RowsExceededException, WriteException, BiffException, IOException {
		// TODO Auto-generated method stub
		InVitroParamUnitDispGuiCreator.createInVitroParUnitsDisInst().parameterUnitsInternalFrame
		.moveToFront();
	}

	private boolean validationOfMapping() {
		boolean ifValidMapping = false;
		ProcessingInputsInfo procInputs = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		if (procInputs.getModelInputTab1Obj().isSimulation() == false) {
			if (procInputs.getMappingDataObj().getxColumnName().equals("")
					|| procInputs.getMappingDataObj().getYcolumnName().equals(
							"")) {
				ifValidMapping = false;
			} else {
				ifValidMapping = true;
			}

		} else {
			if (procInputs.getMappingDataObj().getxColumnName().equals("")) {
				ifValidMapping = false;
			} else {
				ifValidMapping = true;
			}

		}

		return ifValidMapping;
	}

	private void changeStateOfInitParamOptions(boolean state)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueComboBox
				.setEnabled(state);

		if (procInputInst.getModelInputTab2Obj().getSourceOfIntParamValue() == 0) {
			if (procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 0
					&& procInputInst.getModelInputTab2Obj()
							.getInitialParamBYGA() == 0
					&& procInputInst.getModelInputTab2Obj()
							.getInitialParamBYGS() == 0) {
				InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
						.setEnabled(true);

				InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
						.setEnabled(true);

				InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
						.setEnabled(true);
			} else {
				if (procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 1)
					InVitroEngSettingDispGuiCreator
							.createEngineSettingInstance().initialParameterValueByCS
							.setEnabled(state);
				if (procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 1)
					InVitroEngSettingDispGuiCreator
							.createEngineSettingInstance().initialParameterValueByGA
							.setEnabled(state);

				if (procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 1)
					InVitroEngSettingDispGuiCreator
							.createEngineSettingInstance().initialParameterValueByGS
							.setEnabled(state);
			}

		} else if (procInputInst.getModelInputTab2Obj()
				.getSourceOfIntParamValue() == 1) {
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setEnabled(false);

			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setEnabled(false);

			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
					.setEnabled(false);
		}

		if (procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 1)
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setEnabled(state);
		if (procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 1)
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setEnabled(state);

		if (procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 1)
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
					.setEnabled(state);
		if (procInputInst.getModelInputTab2Obj().getPreviouslyGenInitVal() == 1)
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().previouslyGenInitilParam
					.setEnabled(state);
		InVitroEngSettingDispGuiCreator.createEngineSettingInstance().parameterBoundariesComboBox
				.setEnabled(state);
		InVitroEngSettingDispGuiCreator.createEngineSettingInstance().propFinalEstimateCb
				.setEnabled(state);

	}

	private void disableInitParamOptions() throws RowsExceededException,
			WriteException, BiffException, IOException {
		InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueComboBox
				.setEnabled(false);
		InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
				.setEnabled(false);
		InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
				.setEnabled(false);
		InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
				.setEnabled(false);
		InVitroEngSettingDispGuiCreator.createEngineSettingInstance().previouslyGenInitilParam
				.setEnabled(false);
		InVitroEngSettingDispGuiCreator.createEngineSettingInstance().parameterBoundariesComboBox
				.setEnabled(false);
		InVitroEngSettingDispGuiCreator.createEngineSettingInstance().propFinalEstimateCb
				.setEnabled(false);

	}

	private void provideInitialEstimates() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		if (procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true) {

			performCommonFunctionality();
		}

		InVitroInitEstimateDispGuiCreator.createInVitroInitEstGui().paramInitValIFForInVitro
				.moveToFront();

	}

	public void performCommonFunctionality() throws RowsExceededException,
			WriteException, BiffException, IOException {

		// initial value screen
		initialValueScreenCreationBasedOnPrifiles();
		

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(false);
		timeColChanged = false;
		concColChanged = false;
		sortVarChanged = false;

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	private void initialValueScreenCreationBasedOnPrifiles()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		if (appInfo.isRetrievalFlag() == false)
			InVitroInitEstimateDispGuiCreator.createInVitroInitEstGui()
					.createInitialParameterValueTable();

		for (int i = 0; i < InVitroInitEstimateDispGuiCreator
				.createInVitroInitEstGui().initialParamTableForInVitro
				.getRowCount(); i++) {
			for (int j = 0; j <= noOfSortVariables; j++) {
				String tempStr = (String) (InVitroInitEstimateDispGuiCreator
						.createInVitroInitEstGui().initialParamTableForInVitro
						.getValueAt(i, j));

				procInputInst.getInitialParameterValueObj()
						.setInitialParameterValueAt(i, j, tempStr);
			}
		}

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
		InVitroInitEstimateDispGuiCreator.createInVitroInitEstGui().paramInitValIFForInVitro
				.moveToFront();

	}

	private String decideMessage() {
		String message = "";
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.equals("pk") || analysisType.equals("mm")) {
			if (timeColChanged == true)
				message = message + "Time variable changed";

			if (timeColChanged == true && concColChanged == true)
				message = message + ", Concentration variable changed";
			else if (timeColChanged == false && concColChanged == true)
				message = message + "Concentration variable changed";

			if (sortVarChanged == true) {
				if (timeColChanged == true || concColChanged == true)
					message = message + ", Sort variable changed";
				else
					message = message + "Sort variable changed";

			}

		} else if (analysisType.equals("pd")) {
			if (timeColChanged == true)
				message = message + "Concentration variable changed";

			if (timeColChanged == true && concColChanged == true)
				message = message + ", Effect variable changed";
			else if (timeColChanged == false && concColChanged == true)
				message = message + "Effect variable changed";

			if (sortVarChanged == true) {
				if (timeColChanged == true || concColChanged == true)
					message = message + ", Sort variable changed";
				else
					message = message + "Sort variable changed";

			}

		} else if (analysisType.equals("pkpdlink")
				|| analysisType.equals("irm")) {
			if (timeColChanged == true)
				message = message + "Time variable changed";

			if (timeColChanged == true && concColChanged == true)
				message = message + ", Effect variable changed";
			else if (timeColChanged == false && concColChanged == true)
				message = message + "Effect variable changed";

			if (sortVarChanged == true) {
				if (timeColChanged == true || concColChanged == true)
					message = message + ", Sort variable changed";
				else
					message = message + "Sort variable changed";

			}

		}

		return message;
	}

	private void detectProfiles() throws RowsExceededException, WriteException,
			BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		CaMultipleLevelSortingProvider.setAppInfo(appInfo);
		CaMultipleLevelSortingProvider m = CaMultipleLevelSortingProvider
				.createMultipleSortInstance();

		m.setAppInfo(appInfo);
		m.sortData(null);
		CaNoOfSubjectDeterminer determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();

		determineNoOfSubInst.determineNumberOfSubject(m.dataSorted);
	}

	private String[] detectSetupPaths() throws RowsExceededException,
			WriteException, BiffException, IOException {

		String plotName = InVitroSetupAvailableCompCreator
				.createInVitroSetupAvailCompInst().availableComponentsTree
				.getSelectionPath().getLastPathComponent().toString();

		String[] pathSplits = InVitroSetupAvailableCompCreator
				.createInVitroSetupAvailCompInst().availableComponentsTree
				.getSelectionPath().toString().split(",");

		return pathSplits;
	}

	public void mappingChangeDetection() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		int a = 1;
		int noOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		if (prevConcColName != procInputInst.getMappingDataObj()
				.getYcolumnName()) {
			procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(true);
			concColChanged = true;
		}

		if (prevTimeColName != procInputInst.getMappingDataObj()
				.getxColumnName()) {

			procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(true);
			timeColChanged = true;
		}

		if (previousSize != noOfSortVar) {
			procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(true);
			sortVarChanged = true;
		} else {
			for (int i = 0; i < previousSize; i++) {
				for (int j = 0; j < noOfSortVar; j++) {
					String String1 = procInputInst.getMappingDataObj()
							.getSortVariablesListVector().get(i);
					String String2 = previousSortVariables[i];
					if (String1.equals(String2) == true)
						a = a * 0;
					else
						a = a * 1;

				}

				if (a == 1) {
					procInputInst.getMappingDataObj()
							.setIfMappingScreenIsChanged(true);
					sortVarChanged = true;
					break;

				} else {
					a = 1;
					procInputInst.getMappingDataObj()
							.setIfMappingScreenIsChanged(false);
				}

			}
		}

		previousSortVariables = new String[procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size()];

		prevConcColName = procInputInst.getMappingDataObj().getYcolumnName();
		prevTimeColName = procInputInst.getMappingDataObj().getxColumnName();

		for (int i = 0; i < noOfSortVar; i++) {
			String String2 = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);
			previousSortVariables[i] = String2;
		}

		previousSize = noOfSortVar;
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	private void doMapping() throws RowsExceededException, WriteException,
			BiffException, IOException {
		InVitroMappingDispGuiCreator.createMappingInstance().mappingInternalFrame
				.moveToFront();
		InVitroOptionsGuiCreator.createInVitroOptionsInstance().optionsInternalFrame
				.moveToFront();
	}

}
