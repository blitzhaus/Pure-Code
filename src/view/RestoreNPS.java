package view;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.TreePath;

import Common.Iconizing;
import Common.JinternalFrameFunctions;
import Common.LogEntry;
import Common.PETreeFunctions;

import view.ApplicationInfo;
import view.DisplayContents;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class RestoreNPS {

	public RestoreNPS(String[] pathSplits) throws RowsExceededException,
			WriteException, BiffException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[] split = pathSplits[pathSplits.length - 1].split("-");
		String wbName = split[split.length - 2];
		split = split[split.length - 1].split("]");
		String sheetName = split[split.length - 1];

		DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage = true;
		DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage = false;
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
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		DDViewLayer.createViewLayerInstance().isNcaRestore = false;
		DDViewLayer.createViewLayerInstance().isDSRestore = false;
		DDViewLayer.createViewLayerInstance().isPlotMavenRestore = false;
		DDViewLayer.createViewLayerInstance().isTableMavenRestore = false;
		DDViewLayer.createViewLayerInstance().isNPSRestore = true;
		DDViewLayer.createViewLayerInstance().isSCARestore = false;
		DDViewLayer.createViewLayerInstance().isCARestore = false;
		DDViewLayer.createViewLayerInstance().istableTransRestore = false;

		DDViewLayer.createMTInstance().mainTabbedFrame
				.setTitle("NonParametric Superposition");
		// ViewLayer.createViewLayerInstance().viewsCombo.setEnabled(true);
		/*
		 * if(ViewLayer.createAAInstance().availableAnalysisFrame.isVisible() ==
		 * true){
		 * Iconizing.createIconizingInstance().iconizeAvailableAnalysisInternalFrame
		 * (); Iconizing.createIconizingInstance().iconizeLogInternalFrame(); }
		 */
		LogEntry.createLogEntryInstance().logEntry(
				"Restored Non Parametric Superposition");

		try {

			/*PersistStructure persistStructureObject = new PersistStructure();
			ApplicationInfo readPerst = (ApplicationInfo) persistStructureObject
					.readApplicationInfo("outjar.jar");*/
			
			PersistStructure persistStructureObject = new PersistStructure();
			ApplicationInfo readPerst = (ApplicationInfo)persistStructureObject.readEncryptedFile("outjar.enc");

			setAllTheGuiScreenObjectsToNull();

			// set the selected project
			PETreeFunctions.createPETreeFuncInst()
					.setProjIndexAndSelectedProjPath(
							PETreeFunctions.createPETreeFuncInst()
									.getProjIndex(
											PETreeFunctions
													.createPETreeFuncInst()
													.getProjName(pathSplits)),
							PETreeFunctions.createPETreeFuncInst().getProjName(
									pathSplits));

			// set the selected workbook
			setSelectedWorkBook(wbName, readPerst);

			int selsectedSheetIndex = 0;
			for (int i = 0; i < appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().size(); i++) {
				if (sheetName.equals(appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(i).getSheetName())) {
					selsectedSheetIndex = i;
					break;
				}
			}

			// set the selected sheet index
			readPerst.getProjectInfoAL().get(
					readPerst.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(
							readPerst.getProjectInfoAL().get(
									readPerst.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.setSelectedSheetIndex(selsectedSheetIndex);
			createInportedDataSheet(selsectedSheetIndex, readPerst
					.getProjectInfoAL()
					.get(readPerst.getSelectedProjectIndex())
					.getSelectedWorkBookIndex());
			NPSMainPage.createNPSMainPageInst()
					.nPSMainPage(selsectedSheetIndex);

			DisplayContents.setM_appInfo(readPerst);

			// set the workbook to the stored workBook
			Workbook restoringWB = null;
			/*
			 * try { restoringWB = Workbook.getWorkbook(new File("ajith.xls"));
			 * } catch (BiffException e) { e.printStackTrace(); }
			 */
			appInfo = DisplayContents.createAppInfoInstance();
			// appInfo.setFile1Workbook(restoringWB);
			restoringPreviousState(appInfo);
			NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree
					.setSelectionRow(0);
			DDViewLayer.createFeaturesPanelInstance().PreviewLable.doClick();
			ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
					.moveToBack();
			NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree
					.setSelectionRow(1);
		} catch (IOException e1) {

		} /*
			catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/

	}

	private void setSelectedWorkBook(String wbName, ApplicationInfo readPerst) {

		int selectedWorkBookIndex = 0;

		for (int i = 0; i < readPerst.getProjectInfoAL().get(
				readPerst.getSelectedProjectIndex()).getWorkBooksArrayList()
				.size(); i++) {

			if (readPerst.getProjectInfoAL().get(
					readPerst.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(i).getWorkBookName().equals(
							wbName)) {
				selectedWorkBookIndex = i;
				readPerst.getProjectInfoAL().get(
						readPerst.getSelectedProjectIndex())
						.setSelectedWorkBookIndex(selectedWorkBookIndex);
				break;
			}
		}

	}

	private void createInportedDataSheet(int selectedSheetIndex,
			int selectedWorkBookIndex) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.setSelectedWorkBookIndex(selectedWorkBookIndex);

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.setSelectedSheetIndex(selectedSheetIndex);
		ImportedDataSheet.createImportedDataSheetInstance();
		ImportedDataSheet.createImportedDataSheetInstance()
				.createImpDataSheetFrame();
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setTitle("Imported Data main screen");
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setLocation(0, 0);
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setBorder(DDViewLayer.createViewLayerInstance().b);
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setBackground(Color.white);
		JinternalFrameFunctions
				.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame);
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setSize(DDViewLayer.createMTInstance().mainTabbedDesktopPane
						.getWidth(),
						DDViewLayer.createMTInstance().mainTabbedDesktopPane
								.getHeight());
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.setVisible(true);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane
				.add(ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame);
		// fix(mainTabbedDesktopPane);

	}

	private void setAllTheGuiScreenObjectsToNull() {

		NPSMainPage.NPS_MAIN_PAGE = null;
		NPSTabs.NPS_TABS = null;
		NpsAdministeredDoseTableCreation.NPS_ADMIN_DOSE_GUI = null;
		NpsTermialPhaseTableCreation.NPS_TERMINAl_PHASE_GUI = null;
		NPSDosingTableCreation.NPS_DOSE_GUI = null;
		NPSSetupAvailComp.NPS_SETUP_AVAILCOMP = null;
		NPSSetupDispComp.NPS_SETUP_DISP_COMP = null;
		NPSResultsAvailComp.NPS_RES_AVAIL = null;
		NPSResultsDispComp.NPS_RES_DISP_COMP = null;
		NPSOpt.NPS_OPT = null;
		ImportedDataSheet.importedDataSheetFrame = null;

	}

	private void restoringPreviousState(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		restoreMappingScreen(readPerst);
		restoreModelOptions(readPerst);

		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();

		String[][] administeredDose = procInputInst.getModleInputForNPSObj()
				.getAdministeredDose();

		String[][] terminalPhaseData = procInputInst.getModleInputForNPSObj()
				.getTerminatPhaseData();

		String[][] dosingData = procInputInst.getModleInputForNPSObj()
				.getDosingDataForNPS();

		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(true);
		NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);

		TreePath path = NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree
				.getPathForRow(2);
		NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree
				.setSelectionPath(path);

		restoreAdministerDoseData(administeredDose);
		restoreTerminalPhaseData(terminalPhaseData);

		if (procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 1)
			restoreDosingData(dosingData);

		restoreResults(readPerst);

		procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();
		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(false);
		NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);

	}

	private void restoreDosingData(String[][] dosingData)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		// NPSDosingTableCreation.createNpsDosingGuiInst().createDosingInternalFrame();
		int rowCount = NPSDosingTableCreation.createNpsDosingGuiInst().dosingTableForNPS
				.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			((DefaultTableModel) NPSDosingTableCreation
					.createNpsDosingGuiInst().dosingTableForNPS.getModel())
					.removeRow(i - 1);
			System.out.println("removed row " + (i - 1));
		}

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();

		procInputInst.getModleInputForNPSObj().setDosingDataForNPS(dosingData);

		NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);

		for (int i = 0; i < dosingData.length; i++) {

			int colCount = dosingData[0].length;
			String[] s = new String[colCount];

			for (int j = 0; j < colCount; j++) {
				s[j] = dosingData[i][j];

			}

			((DefaultTableModel) NPSDosingTableCreation
					.createNpsDosingGuiInst().dosingTableForNPS.getModel())
					.insertRow(
							NPSDosingTableCreation.createNpsDosingGuiInst().dosingTableForNPS
									.getRowCount(), s);
		}
	}

	private void restoreTerminalPhaseData(String[][] terminalPhaseData)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		/*
		 * NpsTermialPhaseTableCreation
		 * .createNpsTerminalPhaseGuiInst().createInternalGUIForTerminatPhase();
		 */
		int rowCount = NpsTermialPhaseTableCreation
				.createNpsTerminalPhaseGuiInst().terminalPhaseTable
				.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			((DefaultTableModel) NpsTermialPhaseTableCreation
					.createNpsTerminalPhaseGuiInst().terminalPhaseTable
					.getModel()).removeRow(i - 1);
			System.out.println("removed row " + (i - 1));
		}

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();

		procInputInst.getModleInputForNPSObj().setTerminatPhaseData(
				terminalPhaseData);

		NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);

		for (int i = 0; i < terminalPhaseData.length; i++) {

			int colCount = terminalPhaseData[0].length;
			String[] s = new String[colCount];

			for (int j = 0; j < colCount; j++) {
				s[j] = terminalPhaseData[i][j];

			}

			((DefaultTableModel) NpsTermialPhaseTableCreation
					.createNpsTerminalPhaseGuiInst().terminalPhaseTable
					.getModel()).insertRow(NpsTermialPhaseTableCreation
					.createNpsTerminalPhaseGuiInst().terminalPhaseTable
					.getRowCount(), s);
		}
	}

	private void restoreAdministerDoseData(String[][] administeredDose)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		/*
		 * NpsAdministeredDoseTableCreation.createNpsAdministeredDoseGuiInst().
		 * createInternalGUIForAdministeredDosing();
		 */
		int rowCount = NpsAdministeredDoseTableCreation
				.createNpsAdministeredDoseGuiInst().administeredDoseForNPS
				.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			((DefaultTableModel) NpsAdministeredDoseTableCreation
					.createNpsAdministeredDoseGuiInst().administeredDoseForNPS
					.getModel()).removeRow(i - 1);
			System.out.println("removed row " + (i - 1));
		}

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();

		procInputInst.getModleInputForNPSObj().setAdministeredDose(
				administeredDose);

		NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);

		for (int i = 0; i < administeredDose.length; i++) {

			int colCount = administeredDose[0].length;
			String[] s = new String[colCount];

			for (int j = 0; j < colCount; j++) {
				s[j] = administeredDose[i][j];

			}

			((DefaultTableModel) NpsAdministeredDoseTableCreation
					.createNpsAdministeredDoseGuiInst().administeredDoseForNPS
					.getModel()).insertRow(NpsAdministeredDoseTableCreation
					.createNpsAdministeredDoseGuiInst().administeredDoseForNPS
					.getRowCount(), s);
		}
	}

	private void restoreResults(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		NonparametricoutputGeneration.createNpsOutGenInst()
				.npsOutputGeneration();

	}

	private void restoreModelOptions(ApplicationInfo readPerst) {

		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst()
				.copyProcessingInput();

		NPSOpt.createNPSOptInst().dosingTypeComboBox
				.setSelectedIndex(procInputInst.getModleInputForNPSObj()
						.getDosingTypeForNPS());

		NPSOpt.createNPSOptInst().NoOfDataOutputPointsTextField
				.setText(procInputInst.getModleInputForNPSObj()
						.getNoOfOutputPoints());

		NPSOpt.createNPSOptInst().methodForComputationsComboBox
				.setSelectedIndex(procInputInst.getModleInputForNPSObj()
						.getMethodForComputation());

		
		if (procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 0) {
			if (procInputInst.getModleInputForNPSObj().isIfDisplayAtSteadyState() == true) {
				NPSOpt.createNPSOptInst().displayAtSteadyStateRadioButton
						.setSelected(true);

			} else {
				NPSOpt.createNPSOptInst().displayAtSteadyStateRadioButton
						.setSelected(false);

			}

		}else
		{
			NPSOpt.createNPSOptInst().displayAtSteadyStateRadioButton.setVisible(false);
		}
		
		
		
		if (procInputInst.getModleInputForNPSObj().isIfDisplayAfterNthDose() == true) {
			NPSOpt.createNPSOptInst().displayAtNthdoseRadioButton
					.setSelected(true);

		} else {
			NPSOpt.createNPSOptInst().displayAtNthdoseRadioButton
					.setSelected(false);

		}

		
		
		NPSOpt.createNPSOptInst().noOfDoseInNPSTextField.setText(procInputInst
				.getModleInputForNPSObj().getNumberOfDoseBeforeDisplay());

		if (procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 0) {
			NPSOpt.createNPSOptInst().loadingDoseTextField
					.setText(procInputInst.getModleInputForNPSObj()
							.getLoadingDose());

			NPSOpt.createNPSOptInst().maintenanceDoseTextField
					.setText(procInputInst.getModleInputForNPSObj()
							.getMaintenanceDose());

			NPSOpt.createNPSOptInst().tauInNPSTextField.setText(procInputInst
					.getModleInputForNPSObj().getTauValueInNPS());
		} else {
			NPSOpt.createNPSOptInst().loadingDoseTextField
					.setText(procInputInst.getModleInputForNPSObj()
							.getStartTime());

			NPSOpt.createNPSOptInst().maintenanceDoseTextField
					.setText(procInputInst.getModleInputForNPSObj()
							.getEndTime());
		}

	}

	private void restoreMappingScreen(ApplicationInfo readPerst) {

		int size = NPSSetupDispComp.createNpsSetDispInst().availableVariablesListmodel
				.size();
		for (int i = NPSSetupDispComp.createNpsSetDispInst().availableVariablesListmodel
				.size() - 1; i >= 0; i--) {
			NPSSetupDispComp.createNpsSetDispInst().availableVariablesListmodel
					.remove(i);
		}

		// setting the available columns
		for (int i = 0; i < readPerst
				.getProjectInfoAL()
				.get(readPerst.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						readPerst.getProjectInfoAL().get(
								readPerst.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						readPerst
								.getProjectInfoAL()
								.get(readPerst.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										readPerst
												.getProjectInfoAL()
												.get(
														readPerst
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().size(); i++) {
			NPSSetupDispComp.createNpsSetDispInst().availableVariablesListmodel
					.add(
							i,
							readPerst
									.getProjectInfoAL()
									.get(readPerst.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											readPerst
													.getProjectInfoAL()
													.get(
															readPerst
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											readPerst
													.getProjectInfoAL()
													.get(
															readPerst
																	.getSelectedProjectIndex())
													.getWorkBooksArrayList()
													.get(
															readPerst
																	.getProjectInfoAL()
																	.get(
																			readPerst
																					.getSelectedProjectIndex())
																	.getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNpsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().get(i));

		}

		NPSSetupDispComp.createNpsSetDispInst().timeVariableTextField
				.setText(readPerst
						.getProjectInfoAL()
						.get(readPerst.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								readPerst.getProjectInfoAL().get(
										readPerst.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								readPerst
										.getProjectInfoAL()
										.get(
												readPerst
														.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												readPerst
														.getProjectInfoAL()
														.get(
																readPerst
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNpsInfo()
						.getProcessingInputs().getMappingDataObj()
						.getxColumnName());

		// setting the concentration column

		NPSSetupDispComp.createNpsSetDispInst().conbcentrationVariableTextField
				.setText(readPerst
						.getProjectInfoAL()
						.get(readPerst.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								readPerst.getProjectInfoAL().get(
										readPerst.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								readPerst
										.getProjectInfoAL()
										.get(
												readPerst
														.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												readPerst
														.getProjectInfoAL()
														.get(
																readPerst
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNpsInfo()
						.getProcessingInputs().getMappingDataObj()
						.getYcolumnName());

		// since sort variables are not populated while creating a new
		// ncaMainScreen
		// we need not remove anything, so
		// setting the sort variables
		for (int i = 0; i < readPerst
				.getProjectInfoAL()
				.get(readPerst.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						readPerst.getProjectInfoAL().get(
								readPerst.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						readPerst
								.getProjectInfoAL()
								.get(readPerst.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										readPerst
												.getProjectInfoAL()
												.get(
														readPerst
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			NPSSetupDispComp.createNpsSetDispInst().sortVariableListModel
					.add(
							i,
							readPerst
									.getProjectInfoAL()
									.get(readPerst.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											readPerst
													.getProjectInfoAL()
													.get(
															readPerst
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											readPerst
													.getProjectInfoAL()
													.get(
															readPerst
																	.getSelectedProjectIndex())
													.getWorkBooksArrayList()
													.get(
															readPerst
																	.getProjectInfoAL()
																	.get(
																			readPerst
																					.getSelectedProjectIndex())
																	.getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNpsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().get(i));

		}

	}

}
