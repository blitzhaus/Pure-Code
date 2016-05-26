package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PolyExpoSetupDispCompGuiCreator {

	public PolyExpoSetupDispCompGuiCreator() {

	}

	public static PolyExpoSetupDispCompGuiCreator SETUP_DISP_COMP_GUI = null;

	public static PolyExpoSetupDispCompGuiCreator createPolyExpoMainScrSetupDispGui()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (SETUP_DISP_COMP_GUI == null) {
			SETUP_DISP_COMP_GUI = new PolyExpoSetupDispCompGuiCreator();
		}
		return SETUP_DISP_COMP_GUI;
	}

	PolyExpoMappingDispGuiCreator polyExpoMapDispGui;
	PolyExpoInitEstimateDispGuiCreator polyExpoInitEstimateDispGui;

	// mapping change detection
	String prevConcColName;
	String prevTimeColName;
	boolean timeColChanged;
	boolean concColChanged;
	boolean sortVarChanged;
	int previousSize;
	String[] previousSortVariables;

	public void polyExpoSetupDispCompGuiCreation()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		// This internal frame will contain many internal frames corresponding
		// to each of the components available
		initialization();
		polyExpoMapDispGui = PolyExpoMappingDispGuiCreator
				.createMappingInstance();

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
		if (PolyExpoSetupAvailableCompCreator
				.createPolyExpoSetupAvailCompInst().availableComponentsTree
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

			if (PolyExpoSetupAvailableCompCreator
					.createPolyExpoSetupAvailCompInst().availableComponentsTree
					.getMinSelectionRow() == 0) {
				ImportedDataSheet.importedDataSheetFrame.moveToFront();
				DDViewLayer.createFeaturesPanelInstance().PreviewLable
						.setVisible(true);
			}

			if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Mapping]")) {
				doMapping();
			}

			else if (pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Exponential Terms]")) {

				provideInitialEstimates();

				PolyExpoInitEstimateDispGuiCreator.createPolyExpoInitEstGui().initialParamTableForPolyExpo
						.setFocusable(true);

			} else if (pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Dose]")) {
				provideDose();

			/*	PolyExpoDosingDispGuiCreator.createPolyExpoDosingDispGui().tableForDosing
						.setFocusable(true);*/
				PolyExpoDosingDispGuiCreator.createPolyExpoDosingDispGui().dosingInternalFrame
						.moveToFront();
				PolyExpoDosingDispGuiCreator.createPolyExpoDosingDispGui().dosingInternalFrame
						.setSize(PolyExpoDosingDispGuiCreator
								.createPolyExpoDosingDispGui().dosingInternalFrame
								.getSize());

			}

		}
	}

	private void provideDose() throws RowsExceededException, WriteException,
			BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		if (procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true) {

			performCommonFunctionality();

		}

		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(false);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
		if (appInfo.isRetrievalFlag() == true) {
		}

		PolyExpoDosingDispGuiCreator.createPolyExpoDosingDispGui().dosingInternalFrame
				.moveToFront();
		PolyExpoDosingDispGuiCreator.createPolyExpoDosingDispGui().dosingInternalFrame
				.setSize(PolyExpoDosingDispGuiCreator
						.createPolyExpoDosingDispGui().dosingInternalFrame
						.getSize());
		// previousModelNumber = PKPDMain.modelNumber;
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

	private void provideInitialEstimates() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		if (procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true) {

			performCommonFunctionality();
		}

		PolyExpoInitEstimateDispGuiCreator.createPolyExpoInitEstGui().paramInitValIFForPolyExpo
				.moveToFront();

	}

	public void performCommonFunctionality() throws RowsExceededException,
			WriteException, BiffException, IOException {

		// initial value screen
		initialValueScreenCreationBasedOnPrifiles();
		dosingScreenCreationBasedOnProfiles();

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(false);
		timeColChanged = false;
		concColChanged = false;
		sortVarChanged = false;

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	void dosingScreenCreationBasedOnProfiles() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (appInfo.isRetrievalFlag() == false)
			PolyExpoDosingDispGuiCreator.createPolyExpoDosingDispGui()
					.createInternalGUIForDosing();

		PolyExpoDosingDispGuiCreator.createPolyExpoDosingDispGui().dosingInternalFrame
				.moveToFront();

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
			PolyExpoInitEstimateDispGuiCreator.createPolyExpoInitEstGui()
					.createInitialParameterValueTable();

		for (int i = 0; i < PolyExpoInitEstimateDispGuiCreator
				.createPolyExpoInitEstGui().initialParamTableForPolyExpo
				.getRowCount(); i++) {
			for (int j = 0; j <= noOfSortVariables; j++) {
				String tempStr = (String) (PolyExpoInitEstimateDispGuiCreator
						.createPolyExpoInitEstGui().initialParamTableForPolyExpo
						.getValueAt(i, j));

				procInputInst.getDeConvoSetupInfoInst().setExpValueAt(i, j,
						tempStr);
			}
		}

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
		PolyExpoInitEstimateDispGuiCreator.createPolyExpoInitEstGui().paramInitValIFForPolyExpo
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

		String plotName = PolyExpoSetupAvailableCompCreator
				.createPolyExpoSetupAvailCompInst().availableComponentsTree
				.getSelectionPath().getLastPathComponent().toString();

		String[] pathSplits = PolyExpoSetupAvailableCompCreator
				.createPolyExpoSetupAvailCompInst().availableComponentsTree
				.getSelectionPath().toString().split(",");

		return pathSplits;
	}

	public void mappingChangeDetection() {

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
		PolyExpoMappingDispGuiCreator.createMappingInstance().mappingInternalFrame
				.moveToFront();
		PolyExpoOptionsGuiCreator.createPolyExpoOptionsInstance().optionsInternalFrame
				.moveToFront();
	}

}
