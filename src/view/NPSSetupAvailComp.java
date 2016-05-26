package view;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.JinternalFrameFunctions;

public class NPSSetupAvailComp {

	public static NPSSetupAvailComp NPS_SETUP_AVAILCOMP = null;

	public static NPSSetupAvailComp createNPSSetupAvailCompInst() {

		if (NPS_SETUP_AVAILCOMP == null) {
			NPS_SETUP_AVAILCOMP = new NPSSetupAvailComp("just object creation");
		}
		return NPS_SETUP_AVAILCOMP;
	}

	public NPSSetupAvailComp(String dummy) {

	}

	JInternalFrame setupAvailableComponentsInternalFrame;
	JTree availableComponentsTree;
	DefaultMutableTreeNode availableComponents;

	String prevConcColName;
	String prevTimeColName;
	int previousSize;
	String[] previousSortVariables;
	boolean concColChanged = false;
	boolean timeColChanged = false;
	boolean sortVarChanged = false;

	
	void createSetupAvailableComponentsinternalFrame(int selectedSheetIndex) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		setupAvailableComponentsInternalFrame = new JInternalFrame(
				"Available Options", false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						setupAvailableComponentsInternalFrame);
		setupAvailableComponentsInternalFrame.setLocation(0, 0);
		setupAvailableComponentsInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		int height = (int) (NPSMainPage.createNPSMainPageInst().npsMainInternalFrame
				.getHeight());
		setupAvailableComponentsInternalFrame.setSize(NPSMainPage
				.createNPSMainPageInst().npsMainInternalFrame.getWidth() / 4,
				height);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.setSelectedSheetIndex(selectedSheetIndex);
		setupAvailableComponentsInternalFrame.setVisible(true);
		NPSTabs.createNPSTabsInst().setupTabDesktopPane
				.add(setupAvailableComponentsInternalFrame);
		setupAvailableComponentsInternalFrame.moveToFront();

		availableComponents = new DefaultMutableTreeNode(
				appInfo
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
										.getSelectedSheetIndex())
						.getSheetName());
		DefaultMutableTreeNode mainComponent = new DefaultMutableTreeNode(
				"Mapping");
		availableComponents.add(mainComponent);
		DefaultMutableTreeNode administeredDoseComponent = new DefaultMutableTreeNode(
				"Administered Dose");
		availableComponents.add(administeredDoseComponent);

		DefaultMutableTreeNode terminalPhaseComponent = new DefaultMutableTreeNode(
				"Terminal Phase");
		availableComponents.add(terminalPhaseComponent);

		

		availableComponentsTree = new JTree(availableComponents);
		availableComponentsTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		availableComponentsTree.addTreeSelectionListener(DDViewLayer
				.createViewLayerInstance());
		JScrollPane availableComponentsScrollPane = new JScrollPane(
				availableComponentsTree);
		setupAvailableComponentsInternalFrame.getContentPane().add(
				availableComponentsScrollPane);

	}

	void prepareInitialSetup() throws RowsExceededException, WriteException,
			BiffException, IOException {

		if (availableComponentsTree
				.getSelectionPath() != null) {

			if (availableComponentsTree
					.getMinSelectionRow() == 0) {
				
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.moveToFront();
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame.setVisible(true);
				
				DDViewLayer.createFeaturesPanelInstance().PreviewLable
						.setVisible(true);
			} else {
				String[] pathSplits = detectSetupPaths();

				detectProfiles();

				mappingChangeDetection();

				if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Mapping]")) {
					doMapping();
				}
				if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Administered Dose]")) {

					provideAdministeredDose();
				}

				if (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Terminal Phase]")) {
					provideTerminalPhase();

				}

				if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Maintenance Dosing]")) {
					provideDosing();
				}
			}

		}

	}

	private void provideDosing() throws RowsExceededException, HeadlessException, WriteException, BiffException, IOException {
		
		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();

		if (procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true) {

			String message = "Sort Variable changed";
			showMessage(message);

			performCommonFunctionality();

		}

		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(false);

		NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);
		
		NPSDosingTableCreation.createNpsDosingGuiInst().dosingInternalFrameForNPS
				.moveToFront();
		
	}

	private void provideTerminalPhase() throws RowsExceededException, HeadlessException, WriteException, BiffException, IOException {
		
		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();

		if (procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true) {

			String message = "Sort Variable changed";
			showMessage(message);

			performCommonFunctionality();

		}

		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(false);

		NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);
		
		NpsTermialPhaseTableCreation.createNpsTerminalPhaseGuiInst().terminalPhaseIFForNPS
				.moveToFront();
		
	}

	private void performCommonFunctionality() throws RowsExceededException, WriteException, BiffException, IOException {
		NpsAdministeredDoseTableCreation.createNpsAdministeredDoseGuiInst().createInternalGUIForAdministeredDosing();
		NpsTermialPhaseTableCreation.createNpsTerminalPhaseGuiInst().createInternalGUIForTerminatPhase();
		
		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
		.copyProcessingInput();
		
		if(procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 1)
		{
			NPSDosingTableCreation.createNpsDosingGuiInst().createDosingInternalFrame();
		}
		
		timeColChanged = false;
		concColChanged = false;
		sortVarChanged = false;
	}

	private void provideAdministeredDose() throws RowsExceededException, HeadlessException, WriteException, BiffException, IOException {
		
		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();

		if (procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true) {

			String message = "Sort Variable changed";
			showMessage(message);

			performCommonFunctionality();

		}

		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(false);

		NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);
		
		NpsAdministeredDoseTableCreation.createNpsAdministeredDoseGuiInst().administeredDoseIFForNPS
		.moveToFront();
		
	}

	private void doMapping() throws RowsExceededException, WriteException,
			BiffException, IOException {
		NPSSetupDispComp.createNpsSetDispInst().MappingInternalFrame
				.moveToFront();
		NPSOpt.createNPSOptInst().optionsInternalFrame.moveToFront();
	}

	private void mappingChangeDetection() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();
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
		NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);

	}

	private void detectProfiles() throws RowsExceededException, WriteException,
			BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		multipleLevelSorting m = multipleLevelSorting.createMultipleSortInst();

		m.main(null);
		DetermineNumberOfSubject determineNoOfSubInst = DetermineNumberOfSubject
				.createDetNoSubInst();

		determineNoOfSubInst.determineNumberOfSubject(m.dataSorted);
	}

	private String[] detectSetupPaths() throws RowsExceededException,
			WriteException, BiffException, IOException {

		String plotName = availableComponentsTree
				.getSelectionPath().getLastPathComponent().toString();

		String[] pathSplits = availableComponentsTree
				.getSelectionPath().toString().split(",");

		return pathSplits;
	}

	void showMessage(String message) throws RowsExceededException,
			HeadlessException, WriteException, BiffException, IOException {

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

		if (analysisType.equals("nps")) {
			JOptionPane.showMessageDialog(NPSMainPage.createNPSMainPageInst().npsMainInternalFrame
					, message,
					"Conform", JOptionPane.YES_OPTION);
		} else if (analysisType.equals("pd")) {
			JOptionPane.showMessageDialog(PdMainScreenCreator
					.createPdMainScreenInstance().pdMainInternalFrame, message,
					"Conform", JOptionPane.YES_OPTION);
		} else if (analysisType.equals("mm")) {
			JOptionPane.showMessageDialog(MmMainScreenCreator
					.createMmMainScreenInstance().mmMainInternalFrame, message,
					"Conform", JOptionPane.YES_OPTION);
		} else if (analysisType.equals("pkpdlink")) {
			JOptionPane.showMessageDialog(PkPdLinkMainScreenCreator
					.createPkPdLinkMainScreenInstance().pkpdLinkMainInternalFrame,
					message, "Conform", JOptionPane.YES_OPTION);
		} else if (analysisType.equals("irm")) {
			JOptionPane.showMessageDialog(IrmMainScreenCreator
					.createIrmMainScreenInstance().irmMainInternalFrame,
					message, "Conform", JOptionPane.YES_OPTION);
		}

	}

}
