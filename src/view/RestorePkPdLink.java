package view;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreePath;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Common.JinternalFrameFunctions;
import Common.LogEntry;
import Common.PETreeFunctions;

public class RestorePkPdLink {

	String[][] initialParameterValueDataForRestoring;
	String[][] linkInitialParameterValueDataForRestoring;
	int modelNumber;
	boolean ifSimulation;
	String[][] dosingDataForRestoring;

	public RestorePkPdLink(String[] pathSplits) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[] split = pathSplits[pathSplits.length - 1].split("-");
		String wbName = split[split.length - 2];
		split = split[split.length - 1].split("]");
		String sheetName = split[split.length - 1];

		DDViewLayer.createViewLayerInstance().isFromNca = false;
		DDViewLayer.createViewLayerInstance().isPlotMavenExecution = false;
		DDViewLayer.createViewLayerInstance().isFromPlotMavenMappingScreen = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		LogEntry.createLogEntryInstance().logEntry("Restored PK/PD Link Model");
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		DDViewLayer.createViewLayerInstance().isCARestore = true;
		DDViewLayer.createViewLayerInstance().isNcaRestore = false;
		DDViewLayer.createViewLayerInstance().isDSRestore = false;
		DDViewLayer.createViewLayerInstance().isNPSRestore = false;
		DDViewLayer.createViewLayerInstance().isSCARestore = false;
		DDViewLayer.createViewLayerInstance().isPlotMavenRestore = false;
		DDViewLayer.createViewLayerInstance().isPDRestore = true;

		DDViewLayer.createMTInstance().mainTabbedFrame
				.setTitle(" PkPdLink Analysis");

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

		DDViewLayer.createViewLayerInstance().isBeforeNCA = false;
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

			// set the selected work book
			setSelectedWorkBook(wbName, readPerst);

			int key = 0;
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
					key = i;
					break;
				}
			}

			readPerst.getProjectInfoAL().get(
					readPerst.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(
							readPerst.getProjectInfoAL().get(
									readPerst.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.setSelectedSheetIndex(key);

			ifSimulation = readPerst
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
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.getProcessingInputs().getModelInputTab1Obj()
					.isSimulation();
			DisplayContents.setM_appInfo(readPerst);

			createInportedDataSheet(key, readPerst.getProjectInfoAL().get(
					readPerst.getSelectedProjectIndex())
					.getSelectedWorkBookIndex());

			PkPdLinkMainScreenCreator pkpdlinkdMainScreen = PkPdLinkMainScreenCreator
					.createPkPdLinkMainScreenInstance();
			pkpdlinkdMainScreen.PkPdLinkMainScreenCreation();

			// the selected index is set to the sheet which corresponds
			// to the analysis clicked

			// DisplayContents.setM_appInfo(readPerst);

			// set the workbook to the stored workBook
			Workbook restoringWB = null;

			appInfo = DisplayContents.createAppInfoInstance();
			// appInfo.setFile1Workbook(restoringWB);

			restoringPreviousState(appInfo, key);
		} catch (IOException e1) {

		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		ImportFile.createDispContentsInstance().finishAndOpenClicked = false;

	}

	private void setAllTheGuiScreenObjectsToNull() {
		PkPdLinkMainScreenCreator.PKPDLINK_MAIN_SCREEN = null;
		PkPdLinkMainScreenGUICreator.PKPDLINK_MAIN_SCREEN_GUI = null;
		CaTabbedPanesCreator.PD_TABBLE_DISP = null;
		CaSetupAvailableCompCreator.PD_AVAIL_COMP = null;
		CaSetupDispCompGuiCreator.SETUP_DISP_COMP_GUI = null;
		CaMappingDispGuiCreator.PD_MAP_DISP = null;
		CaInitEstimateDispGuiCreator.PD_INIT_EST_INST = null;
		CaLinkParamInitEstimateGuiCreator.CALink_INIT_EST_INST = null;
		CaDosingDispGuiCreator.PD_DOSE_INST = null;
		CaParamUnitsDispGuiCreator.CA_PAR_UNITS_DISP = null;
		CaOptionsGuiCreator.PD_OPT_GUI = null;
		CaLibModelDispGuiCreator.CA_LIB_MOD_GUI = null;
		CaWeightDoseDispGuiCreator.PD_W_D_GUI = null;
		CaEngineSettingDispGuiCreator.PD_ENG_SETGUI = null;
		CaResultAvailCompDisplayer.PD_RES_AVAIL_GUI = null;
		CaResultDispCompCreator.CA_RES_DISP = null;
		ImportedDataSheet.importedDataSheetFrame = null;
		CaMultipleLevelSortingProvider.mlpInstance = null;
		CaNoOfSubjectDeterminer.noOfSubjectInst = null;
		DataPreparationForAllComponentsForCA.datPrepForCAInst = null;
		ParameterAndUnitListLoader.paramAndUnitListInst = null;

	}

	private void restoringPreviousState(ApplicationInfo readPerst, int key)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo tempAppInfo = readPerst;

		restoreMappingPreviousState(readPerst);
		ProcessingInputsInfo procInputinst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		String[] preferredUnitData = procInputinst.getParameterUnitsDataObj()
				.getPreferredUnitsArray();

		String[] defaultUnitData = procInputinst.getParameterUnitsDataObj()
				.getDefaultUnitArray();

		initialParameterValueDataForRestoring = procInputinst
				.getInitialParameterValueObj().getInitialParameterValueForCA();
		linkInitialParameterValueDataForRestoring = procInputinst
				.getInitialParameterValueObj()
				.getInitialParameterValueForLinkModel();

		dosingDataForRestoring = procInputinst.getDosingDataObj()
				.getDosingDSForNCA();
		modelNumber = procInputinst.getModelInputTab1Obj()
				.getModelNumberForCA();
		int linkModelNumber = procInputinst.getModelInputTab1Obj()
				.getModelNumberForLinkModel();

		restoreModelOptionsPreviousState(readPerst);

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		// after mapping the mapping is changed
		procInputinst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		procInputinst.getMappingDataObj().setIfMappingScreenIsChanged(true);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputinst);

		TreePath path = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().availableComponentsTree
				.getPathForRow(1);
		CaSetupAvailableCompCreator.createPdSetupAvailCompInst().availableComponentsTree
				.setSelectionPath(path);
		restoreLibModelTableDisp(readPerst, modelNumber, linkModelNumber);
		restoreSimulationOptions(readPerst);
		restoreInitialParameterValuePreviousState(
				initialParameterValueDataForRestoring,
				linkInitialParameterValueDataForRestoring);
		restoreDosingDataPreviousState(dosingDataForRestoring);
		restoreUnitDataPreviousState(readPerst, defaultUnitData,
				preferredUnitData);

		restoreSpreadSheetResults(readPerst);
		restorePlots(readPerst);
		restoreTextOutput(readPerst);

		procInputinst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		procInputinst.getMappingDataObj().setIfMappingScreenIsChanged(false);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputinst);
	}

	private void restoreSimulationOptions(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		if (ifSimulation == true) {

			CaLibModelDispGuiCreator.createCalibraryModelInstance().simulationCb
					.setSelected(true);
			CaLibModelDispGuiCreator.createCalibraryModelInstance().simulationUnitButton
					.setVisible(true);
			CaLibModelDispGuiCreator.createCalibraryModelInstance().simulationUnitTf
					.setVisible(true);
			CaLibModelDispGuiCreator.createCalibraryModelInstance().simulationUnitTf
					.setText(procInputInst.getModelInputTab1Obj()
							.getSimulationUnit());
			procInputInst.getModelInputTab1Obj().setSimulation(true);
		}
	}

	private void restoreDosingDataPreviousState(String[][] dosingData)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		CaDosingDispGuiCreator.createCaDosingDispGui().createInternalGUIForDosing();

		restoreNoOfDoseCb();
		int rowCount = CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
				.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			((DefaultTableModel) CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
					.getModel()).removeRow(i - 1);
			System.out.println("removed row " + (i - 1));
		}

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		procInputInst.getDosingDataObj().setDosingDSForNCA(dosingData);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		for (int i = 0; i < dosingData.length; i++) {

			int colCount = dosingData[0].length;
			String[] s = new String[colCount];

			for (int j = 0; j < colCount; j++) {
				s[j] = dosingData[i][j];

			}

			((DefaultTableModel) CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
					.getModel()).insertRow(CaDosingDispGuiCreator
					.createCaDosingDispGui().tableForDosing.getRowCount(), s);
		}

		JScrollPane initialValueScrollPane = new JScrollPane(CaDosingDispGuiCreator
				.createCaDosingDispGui().tableForDosing,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		initialValueScrollPane.setBackground(Color.white);
		initialValueScrollPane.setVisible(true);
		CaDosingDispGuiCreator.createCaDosingDispGui().DosingInternalFrame
				.getContentPane().removeAll();
		CaDosingDispGuiCreator.createCaDosingDispGui().DosingInternalFrame
				.add(initialValueScrollPane);

		CaDosingDispGuiCreator.createCaDosingDispGui().DosingInternalFrame
				.setSize(CaDosingDispGuiCreator.createCaDosingDispGui().DosingInternalFrame
						.getSize());

		CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing.setVisible(true);
		CaDosingDispGuiCreator.createCaDosingDispGui().DosingInternalFrame.repaint();
		CaDosingDispGuiCreator.createCaDosingDispGui().DosingInternalFrame.validate();
	}

	private void restoreNoOfDoseCb() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().numberOfDoseComboBox
				.setSelectedIndex(procInputInst.getModelInputTab1Obj()
						.getNumberOfDose() - 1);

	}

	private void restoreLibModelTableDisp(ApplicationInfo readPerst,
			int modelNumber, int linkModelNumber) throws RowsExceededException,
			WriteException, BiffException, IOException {

		if (modelNumber > 0) {
			((JRadioButton) CaLibModelDispGuiCreator
					.createCalibraryModelInstance().diffEqunLibraryModelTable
					.getValueAt(modelNumber - 1, 0)).setSelected(true);
			CaLibModelDispGuiCreator.createCalibraryModelInstance()
					.addImageToSecondDiagramPanel(modelNumber);
		}

		if (linkModelNumber > 0) {
			((JRadioButton) CaLibModelDispGuiCreator
					.createCalibraryModelInstance().libraryModelTable
					.getValueAt(linkModelNumber - 1, 0)).setSelected(true);
			CaLibModelDispGuiCreator.createCalibraryModelInstance()
					.addImageToDiagramPanel(modelNumber);
		}

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		procInputInst.getModelInputTab1Obj().setModelNumberForCA(modelNumber);
		procInputInst.getModelInputTab1Obj().setModelNumberForLinkModel(
				linkModelNumber);
		CaLibModelDispGuiCreator.createCalibraryModelInstance().clearanceCb
				.setSelected(procInputInst.getModelInputTab1Obj()
						.isIfClearanceParam());

		CaLibModelDispGuiCreator.createCalibraryModelInstance().pkUnitTf
				.setText(procInputInst.getParameterUnitsDataObj()
						.getConcMassUnit()
						+ "/"
						+ procInputInst.getParameterUnitsDataObj()
								.getVolumeUnit());

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	private void restoreTextOutput(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		CaResultDispCompCreator.createCaResDispCompInst().completeTextOutputTextArea
				.setText("");
		for (int i = 0; i < appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getTextoutput().size(); i++) {
			CaResultDispCompCreator.createCaResDispCompInst().completeTextOutputTextArea
					.append(appInfo
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
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPkpdLinkInfo().getWorkSheetOutputs()
							.getTextoutput().get(i));

		}
	}

	private void restoreSpreadSheetResults(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		CaResultDispCompCreator.createCaResDispCompInst().createTablesToFillResult();
		restoreFinalParameterVerticalDisplayTable(readPerst);
		restoreFinalParameterHoriZontalDisplayTable(readPerst);
		restoreInitialParameterTable(readPerst);
		restoreDosingTable(readPerst);
		restoreMinimizationProcessTable(readPerst);
		restoreCorrelationMatrixTable(readPerst);
		restoreEigenValuesTable(readPerst);
		restoreConditionNumberTable(readPerst);
		restorePredictedValueTable(readPerst);
		restoreVarienceCovarienceMatrixTable(readPerst);
		restoreSummaryTable(readPerst);
		restoreDiagnosticsTable(readPerst);
		restorePartialDerivativeTable(readPerst);
		restoreSecondaryParametersTable(readPerst);
		restoreNonTransposedSPTable(readPerst);
		restoreUserSettingsTable(readPerst);
		restoreHistoryTable(readPerst);

	}

	private void restoreDosingTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getDosingValueForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().dosingDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().dosingDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().dosingDisplayTable
					.getModel())
					.insertRow(
							CaResultDispCompCreator.createCaResDispCompInst().dosingDisplayTable
									.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().dosingDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().dosingDisplayInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().dosingDisplayInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().dosingDisplayInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().dosingDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);

	}

	private void restoreFinalParameterVerticalDisplayTable(
			ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getVerticalParametersForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().finalparametersVerticalDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().finalparametersVerticalDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().finalparametersVerticalDisplayTable
					.getModel())
					.insertRow(
							CaResultDispCompCreator.createCaResDispCompInst().finalparametersVerticalDisplayTable
									.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().finalparametersVerticalDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().finalParametersinternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().finalParametersinternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().finalParametersinternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().finalParametersinternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	private void restoreFinalParameterHoriZontalDisplayTable(
			ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getHorizontalParametersForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().finalParametersHorizontalDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().finalParametersHorizontalDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().finalParametersHorizontalDisplayTable
					.getModel())
					.insertRow(
							CaResultDispCompCreator.createCaResDispCompInst().finalParametersHorizontalDisplayTable
									.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().finalParametersHorizontalDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().finalParamHorizontalDisplayInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().finalParamHorizontalDisplayInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().finalParamHorizontalDisplayInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().finalParamHorizontalDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	private void restoreInitialParameterTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getInitialParameterForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().initialParameterDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().initialParameterDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().initialParameterDisplayTable
					.getModel()).insertRow(CaResultDispCompCreator
					.createCaResDispCompInst().initialParameterDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().initialParameterDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().initialParametersinternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().initialParametersinternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().initialParametersinternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().initialParametersinternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	private void restoreMinimizationProcessTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getMinimizationProcessForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().minimizationProcessDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().minimizationProcessDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().minimizationProcessDisplayTable
					.getModel()).insertRow(CaResultDispCompCreator
					.createCaResDispCompInst().minimizationProcessDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().minimizationProcessDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().minimizationProcessInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().minimizationProcessInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().minimizationProcessInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().minimizationProcessInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);

	}

	private void restoreCorrelationMatrixTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getCorrelationMatrixForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().correlationMatrixDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().correlationMatrixDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().correlationMatrixDisplayTable
					.getModel()).insertRow(CaResultDispCompCreator
					.createCaResDispCompInst().correlationMatrixDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().correlationMatrixDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().correlationMatrixDisplayInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().correlationMatrixDisplayInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().correlationMatrixDisplayInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().correlationMatrixDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	private void restoreEigenValuesTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getEigenValuesForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().eigenValuesDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().eigenValuesDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().eigenValuesDisplayTable
					.getModel()).insertRow(CaResultDispCompCreator
					.createCaResDispCompInst().eigenValuesDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().eigenValuesDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().eigenValuesDisplayInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().eigenValuesDisplayInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().eigenValuesDisplayInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().eigenValuesDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);

	}

	private void restoreConditionNumberTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getConditionNumberForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().conditionNumberDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().conditionNumberDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().conditionNumberDisplayTable
					.getModel()).insertRow(CaResultDispCompCreator
					.createCaResDispCompInst().conditionNumberDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().conditionNumberDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().conditionNumberDisplayInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().conditionNumberDisplayInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().conditionNumberDisplayInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().conditionNumberDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	private void restorePredictedValueTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getPredictedValueForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().predictedValueDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().predictedValueDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().predictedValueDisplayTable
					.getModel()).insertRow(CaResultDispCompCreator
					.createCaResDispCompInst().predictedValueDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().predictedValueDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().predictedValueDisplayInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().predictedValueDisplayInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().predictedValueDisplayInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().predictedValueDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	private void restoreVarienceCovarienceMatrixTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getVarienceCovarienceMatrixForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().varienceCovarienceMatrixDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().varienceCovarienceMatrixDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().varienceCovarienceMatrixDisplayTable
					.getModel())
					.insertRow(
							CaResultDispCompCreator.createCaResDispCompInst().varienceCovarienceMatrixDisplayTable
									.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().varienceCovarienceMatrixDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().varienceCovarienceMatrixDisplayInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().varienceCovarienceMatrixDisplayInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().varienceCovarienceMatrixDisplayInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().varienceCovarienceMatrixDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	private void restoreSummaryTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getSummaryTableForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().summaryTableDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().summaryTableDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().summaryTableDisplayTable
					.getModel()).insertRow(CaResultDispCompCreator
					.createCaResDispCompInst().summaryTableDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().summaryTableDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().summaryTableDisplayInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().summaryTableDisplayInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().summaryTableDisplayInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().summaryTableDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);

	}

	private void restoreDiagnosticsTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getDiagnosticsForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().diagnosticsDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().diagnosticsDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().diagnosticsDisplayTable
					.getModel()).insertRow(CaResultDispCompCreator
					.createCaResDispCompInst().diagnosticsDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().diagnosticsDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().diagnosticsDisplayInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().diagnosticsDisplayInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().diagnosticsDisplayInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().diagnosticsDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	private void restorePartialDerivativeTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getPartialDerivativeForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().partialDerivativeDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().partialDerivativeDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().partialDerivativeDisplayTable
					.getModel()).insertRow(CaResultDispCompCreator
					.createCaResDispCompInst().partialDerivativeDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().partialDerivativeDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().partialDerivativeDisplayInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().partialDerivativeDisplayInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().partialDerivativeDisplayInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().partialDerivativeDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	private void restoreSecondaryParametersTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		SecondaryParameterList secondaryParamListInst = SecondaryParameterList
				.createSecParamAndUnitListInstance();
		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getSecondaryParametersForCA();

		// Fill the header

		CaResultDispCompCreator.createCaResDispCompInst().secondaryParameterDisplayInternalFrame
				.getContentPane().removeAll();
		secondaryParamListInst.createSecondaryParameterList(modelNumber);

		CaResultDispCompCreator.createCaResDispCompInst().secondaryParameterDisplayTable = new JTable(
				0,
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
										.getSelectedSheetIndex()).getPkInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size()
						+ secondaryParamListInst
								.getNumberOfSecondaryParameters());
		CaResultDispCompCreator.createCaResDispCompInst().secondaryParameterDisplayTable
				.setPreferredScrollableViewportSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().secondaryParameterDisplayTable
				.setFillsViewportHeight(true);
		CaResultDispCompCreator.createCaResDispCompInst().secondaryParameterDisplayTable
				.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().secondaryParameterDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().secondaryParameterDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().secondaryParameterDisplayTable
					.getModel()).insertRow(CaResultDispCompCreator
					.createCaResDispCompInst().secondaryParameterDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().secondaryParameterDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().secondaryParameterDisplayInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().secondaryParameterDisplayInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().secondaryParameterDisplayInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().secondaryParameterDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	private void restoreNonTransposedSPTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getNonTransposedSPForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().nonTransposedSecondaryParameterDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().nonTransposedSecondaryParameterDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().nonTransposedSecondaryParameterDisplayTable
					.getModel())
					.insertRow(
							CaResultDispCompCreator.createCaResDispCompInst().nonTransposedSecondaryParameterDisplayTable
									.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().nonTransposedSecondaryParameterDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().nonTransSecParamDispInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().nonTransSecParamDispInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().nonTransSecParamDispInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().nonTransSecParamDispInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	private void restoreUserSettingsTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getUserSettingsForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().userSettingsDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().userSettingsDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().userSettingsDisplayTable
					.getModel()).insertRow(CaResultDispCompCreator
					.createCaResDispCompInst().userSettingsDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().userSettingsDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().userSettingsDisplayInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().userSettingsDisplayInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().userSettingsDisplayInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().userSettingsDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	private void restoreHistoryTable(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		String[][] data = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getHistoryForCA();

		// remove all the rows in the table first
		for (int i = CaResultDispCompCreator.createCaResDispCompInst().historyDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().historyDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) CaResultDispCompCreator.createCaResDispCompInst().historyDisplayTable
					.getModel()).insertRow(CaResultDispCompCreator
					.createCaResDispCompInst().historyDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				CaResultDispCompCreator.createCaResDispCompInst().historyDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						CaResultDispCompCreator.createCaResDispCompInst().historyDisplayInternalFrame
								.getWidth(),
						CaResultDispCompCreator.createCaResDispCompInst().historyDisplayInternalFrame
								.getHeight()));
		CaResultDispCompCreator.createCaResDispCompInst().historyDisplayInternalFrame
				.getContentPane().removeAll();
		CaResultDispCompCreator.createCaResDispCompInst().historyDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	private void restorePlots(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		restoreLinearPlots(readPerst);
		restoreLogPlots(readPerst);
		CaResultDispCompCreator.createCaResDispCompInst()
				.populateAvailableResultsWithPlots();
	}

	private void restoreLogPlots(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		restoreLogPlotForObsevedYVsWeightedPredictedY(readPerst);
		restoreLogPlotForPartialDerivative(readPerst);
		restoreLogPlotForWeightedPredictedYVsWeightedResidualY(readPerst);
		restoreLogPlotForXVsWeightedResidualY(readPerst);
		restoreLogPlotForXVsYAndPredictedY(readPerst);
	}

	private void restoreLinearPlots(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		restoreLinearPlotForObsevedYVsWeightedPredictedY(readPerst);
		restoreLinearPlotForPartialDerivative(readPerst);
		restoreLinearPlotForWeightedPredictedYVsWeightedResidualY(readPerst);
		restoreLinearPlotForXVsWeightedResidualY(readPerst);
		restoreLinearPlotForXVsYAndPredictedY(readPerst);
	}

	private void restoreLinearPlotForPartialDerivative(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		CaResultDispCompCreator.createCaResDispCompInst().partialDerivativeLinearPlotAL = new ArrayList<JInternalFrame>();
		ArrayList<JFreeChart> chartArraylist = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative();
		for (int i = 0; i < chartArraylist.size(); i++) {
			JFreeChart chart = chartArraylist.get(i);
			ChartPanel chartPanel = new ChartPanel(chart);

			JScrollPane plotDispSP = new JScrollPane(chartPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JInternalFrame f = new JInternalFrame();
			// f.setContentPane(chartPanel);
			JinternalFrameFunctions.createIFFunctionsInstance()
					.removeTitleBarForinternalFrame(f);
			f.setVisible(true);
			f
					.setSize(
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getWidth(),
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getHeight());
			f.setContentPane(plotDispSP);
			f.setBorder(DDViewLayer.createViewLayerInstance().b);
			CaResultDispCompCreator.createCaResDispCompInst().displayResultsinternalFrameDesktopPane
					.add(f);
			CaResultDispCompCreator.createCaResDispCompInst().partialDerivativeLinearPlotAL
					.add(f);
		}

	}

	private void restoreLinearPlotForWeightedPredictedYVsWeightedResidualY(
			ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {
		CaResultDispCompCreator.createCaResDispCompInst().weightPredYVsWeightResdYLinearPlotAL = new ArrayList<JInternalFrame>();
		ArrayList<JFreeChart> chartArraylist = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForWeightedPredictedYVsWeightedResidualY();
		for (int i = 0; i < chartArraylist.size(); i++) {
			JFreeChart chart = chartArraylist.get(i);
			ChartPanel chartPanel = new ChartPanel(chart);
			JScrollPane plotDispSP = new JScrollPane(chartPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JInternalFrame f = new JInternalFrame();
			JinternalFrameFunctions.createIFFunctionsInstance()
					.removeTitleBarForinternalFrame(f);
			f.setVisible(true);
			f
					.setSize(
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getWidth(),
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getHeight());
			f.setContentPane(plotDispSP);
			f.setBorder(DDViewLayer.createViewLayerInstance().b);
			CaResultDispCompCreator.createCaResDispCompInst().displayResultsinternalFrameDesktopPane
					.add(f);
			CaResultDispCompCreator.createCaResDispCompInst().weightPredYVsWeightResdYLinearPlotAL
					.add(f);
		}
	}

	private void restoreLinearPlotForXVsWeightedResidualY(
			ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		CaResultDispCompCreator.createCaResDispCompInst().xVsWeightedResidualYLinearPlotAL = new ArrayList<JInternalFrame>();
		ArrayList<JFreeChart> chartArraylist = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForXVsWeightedResidualY();
		for (int i = 0; i < chartArraylist.size(); i++) {
			JFreeChart chart = chartArraylist.get(i);
			ChartPanel chartPanel = new ChartPanel(chart);
			JScrollPane plotDispSP = new JScrollPane(chartPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JInternalFrame f = new JInternalFrame();
			JinternalFrameFunctions.createIFFunctionsInstance()
					.removeTitleBarForinternalFrame(f);
			f.setVisible(true);
			f
					.setSize(
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getWidth(),
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getHeight());
			f.setContentPane(plotDispSP);
			f.setBorder(DDViewLayer.createViewLayerInstance().b);
			CaResultDispCompCreator.createCaResDispCompInst().displayResultsinternalFrameDesktopPane
					.add(f);
			CaResultDispCompCreator.createCaResDispCompInst().xVsWeightedResidualYLinearPlotAL
					.add(f);
		}

	}

	private void restoreLinearPlotForXVsYAndPredictedY(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		CaResultDispCompCreator.createCaResDispCompInst().xVsObsYAndPredYLogPlotAL = new ArrayList<JInternalFrame>();
		ArrayList<JFreeChart> chartArraylist = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLinearPlots();
		for (int i = 0; i < chartArraylist.size(); i++) {
			JFreeChart chart = chartArraylist.get(i);
			ChartPanel chartPanel = new ChartPanel(chart);
			JScrollPane plotDispSP = new JScrollPane(chartPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JInternalFrame f = new JInternalFrame();
			JinternalFrameFunctions.createIFFunctionsInstance()
					.removeTitleBarForinternalFrame(f);
			f.setVisible(true);
			f
					.setSize(
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getWidth(),
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getHeight());
			f.setContentPane(plotDispSP);
			f.setBorder(DDViewLayer.createViewLayerInstance().b);
			CaResultDispCompCreator.createCaResDispCompInst().displayResultsinternalFrameDesktopPane
					.add(f);
			CaResultDispCompCreator.createCaResDispCompInst().xVsObsYAndPredYLogPlotAL
					.add(f);
		}

	}

	private void restoreLinearPlotForObsevedYVsWeightedPredictedY(
			ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		CaResultDispCompCreator.createCaResDispCompInst().obsYVsWeightPredYLinarPlotAL = new ArrayList<JInternalFrame>();
		ArrayList<JFreeChart> chartArraylist = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForObsevedYVsWeightedPredictedY();
		for (int i = 0; i < chartArraylist.size(); i++) {
			JFreeChart chart = chartArraylist.get(i);
			ChartPanel chartPanel = new ChartPanel(chart);
			JScrollPane plotDispSP = new JScrollPane(chartPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JInternalFrame f = new JInternalFrame();
			JinternalFrameFunctions.createIFFunctionsInstance()
					.removeTitleBarForinternalFrame(f);
			f.setVisible(true);
			f
					.setSize(
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getWidth(),
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getHeight());
			f.setContentPane(plotDispSP);
			f.setBorder(DDViewLayer.createViewLayerInstance().b);
			CaResultDispCompCreator.createCaResDispCompInst().displayResultsinternalFrameDesktopPane
					.add(f);
			CaResultDispCompCreator.createCaResDispCompInst().obsYVsWeightPredYLinarPlotAL
					.add(f);
		}

	}

	private void restoreLogPlotForObsevedYVsWeightedPredictedY(
			ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		CaResultDispCompCreator.createCaResDispCompInst().obsYVsWeightPredYLogPlotAL = new ArrayList<JInternalFrame>();
		ArrayList<JFreeChart> chartArraylist = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForObsevedYVsWeightedPredictedY();
		for (int i = 0; i < chartArraylist.size(); i++) {
			JFreeChart chart = chartArraylist.get(i);
			ChartPanel chartPanel = new ChartPanel(chart);
			JScrollPane plotDispSP = new JScrollPane(chartPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JInternalFrame f = new JInternalFrame();
			JinternalFrameFunctions.createIFFunctionsInstance()
					.removeTitleBarForinternalFrame(f);
			f.setVisible(true);
			f
					.setSize(
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getWidth(),
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getHeight());
			f.setContentPane(plotDispSP);
			f.setBorder(DDViewLayer.createViewLayerInstance().b);
			CaResultDispCompCreator.createCaResDispCompInst().displayResultsinternalFrameDesktopPane
					.add(f);
			CaResultDispCompCreator.createCaResDispCompInst().obsYVsWeightPredYLogPlotAL
					.add(f);
		}

	}

	private void restoreLogPlotForPartialDerivative(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		CaResultDispCompCreator.createCaResDispCompInst().partialDerivativeLogPlotAL = new ArrayList<JInternalFrame>();
		ArrayList<JFreeChart> chartArraylist = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForPartialDerivative();
		for (int i = 0; i < chartArraylist.size(); i++) {
			JFreeChart chart = chartArraylist.get(i);
			ChartPanel chartPanel = new ChartPanel(chart);
			JScrollPane plotDispSP = new JScrollPane(chartPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JInternalFrame f = new JInternalFrame();
			JinternalFrameFunctions.createIFFunctionsInstance()
					.removeTitleBarForinternalFrame(f);
			f.setVisible(true);
			f
					.setSize(
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getWidth(),
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getHeight());
			f.setContentPane(plotDispSP);
			f.setBorder(DDViewLayer.createViewLayerInstance().b);
			CaResultDispCompCreator.createCaResDispCompInst().displayResultsinternalFrameDesktopPane
					.add(f);
			CaResultDispCompCreator.createCaResDispCompInst().partialDerivativeLogPlotAL
					.add(f);
		}

	}

	private void restoreLogPlotForWeightedPredictedYVsWeightedResidualY(
			ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {

		CaResultDispCompCreator.createCaResDispCompInst().weightPredYVsWeightResdYLogPlotAl = new ArrayList<JInternalFrame>();
		ArrayList<JFreeChart> chartArraylist = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForWeightedPredictedYVsWeightedResidualY();
		for (int i = 0; i < chartArraylist.size(); i++) {
			JFreeChart chart = chartArraylist.get(i);
			ChartPanel chartPanel = new ChartPanel(chart);
			JScrollPane plotDispSP = new JScrollPane(chartPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JInternalFrame f = new JInternalFrame();
			JinternalFrameFunctions.createIFFunctionsInstance()
					.removeTitleBarForinternalFrame(f);
			f.setVisible(true);
			f
					.setSize(
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getWidth(),
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getHeight());
			f.setContentPane(plotDispSP);
			f.setBorder(DDViewLayer.createViewLayerInstance().b);
			CaResultDispCompCreator.createCaResDispCompInst().displayResultsinternalFrameDesktopPane
					.add(f);
			CaResultDispCompCreator.createCaResDispCompInst().weightPredYVsWeightResdYLogPlotAl
					.add(f);
		}

	}

	private void restoreLogPlotForXVsWeightedResidualY(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		CaResultDispCompCreator.createCaResDispCompInst().xVsWeightedResidualYLogPlotAL = new ArrayList<JInternalFrame>();
		ArrayList<JFreeChart> chartArraylist = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLogPlotForXVsWeightedResidualY();
		for (int i = 0; i < chartArraylist.size(); i++) {
			JFreeChart chart = chartArraylist.get(i);
			ChartPanel chartPanel = new ChartPanel(chart);
			JScrollPane plotDispSP = new JScrollPane(chartPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JInternalFrame f = new JInternalFrame();
			JinternalFrameFunctions.createIFFunctionsInstance()
					.removeTitleBarForinternalFrame(f);
			f.setVisible(true);
			f
					.setSize(
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getWidth(),
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getHeight());
			f.setContentPane(plotDispSP);
			f.setBorder(DDViewLayer.createViewLayerInstance().b);
			CaResultDispCompCreator.createCaResDispCompInst().displayResultsinternalFrameDesktopPane
					.add(f);
			CaResultDispCompCreator.createCaResDispCompInst().xVsWeightedResidualYLogPlotAL
					.add(f);
		}

	}

	private void restoreLogPlotForXVsYAndPredictedY(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		CaResultDispCompCreator.createCaResDispCompInst().xVsObsYAndPredYLinearPlotAL = new ArrayList<JInternalFrame>();
		ArrayList<JFreeChart> chartArraylist = appInfo
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
								.getSelectedSheetIndex()).getPkpdLinkInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogplots();
		for (int i = 0; i < chartArraylist.size(); i++) {
			JFreeChart chart = chartArraylist.get(i);
			ChartPanel chartPanel = new ChartPanel(chart);
			JScrollPane plotDispSP = new JScrollPane(chartPanel,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JInternalFrame f = new JInternalFrame();
			JinternalFrameFunctions.createIFFunctionsInstance()
					.removeTitleBarForinternalFrame(f);
			f.setVisible(true);
			f
					.setSize(
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getWidth(),
							CaResultDispCompCreator.createCaResDispCompInst().displayResultsInternalFrame
									.getHeight());
			f.setContentPane(plotDispSP);
			f.setBorder(DDViewLayer.createViewLayerInstance().b);
			CaResultDispCompCreator.createCaResDispCompInst().displayResultsinternalFrameDesktopPane
					.add(f);
			CaResultDispCompCreator.createCaResDispCompInst().xVsObsYAndPredYLinearPlotAL
					.add(f);
		}

	}

	private void restoreUnitDataPreviousState(ApplicationInfo readPerst,
			String[] defaultUnit, String[] preferredUnit)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		int rowCount = CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
				.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			((DefaultTableModel) CaParamUnitsDispGuiCreator
					.createCaParUnitsDisInst().parameterUnitsTable.getModel())
					.removeRow(i - 1);

		}

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		procInputInst.getParameterUnitsDataObj().setPreferredUnitsArray(
				preferredUnit);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		for (int i = 0; i < preferredUnit.length; i++) {

			int colCount = 3;
			String[] s = new String[colCount];

			s[0] = procInputInst.getParameterUnitsDataObj()
					.getParameterbyGroupValueAt(i);
			s[1] = defaultUnit[i];
			s[2] = preferredUnit[i];

			((DefaultTableModel) CaParamUnitsDispGuiCreator
					.createCaParUnitsDisInst().parameterUnitsTable.getModel())
					.insertRow(CaParamUnitsDispGuiCreator
							.createCaParUnitsDisInst().parameterUnitsTable
							.getRowCount(), s);
		}

		JScrollPane initialValueScrollPane = new JScrollPane(
				CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		initialValueScrollPane.setBackground(Color.white);

		initialValueScrollPane.setVisible(true);
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsInternalFrame
				.getContentPane().removeAll();
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsInternalFrame
				.add(initialValueScrollPane);

		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsInternalFrame
				.setSize(CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsInternalFrame
						.getSize());

		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
				.setVisible(true);
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
				.repaint();
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsInternalFrame
				.repaint();
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsInternalFrame
				.validate();
	}

	private void restoreModelOptionsPreviousState(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		restoreWeightingOptions(readPerst);
		restoreDataType(readPerst);
		restoreResponseUnit(readPerst);
		restoreDoseUnitTf(readPerst);
		restoreNormalizationUnit(readPerst);

		restoreParamValueAndBouds(readPerst);
		restoreMinMethodCb(readPerst);

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		String incForParDer = procInputInst.getModelInputTab2Obj()
				.getIncForPartialDerivative();

		String noOfPredval = procInputInst.getModelInputTab2Obj()
				.getNumberOfPredictedValue();

		String convergence = procInputInst.getModelInputTab2Obj()
				.getConvergenceCriteria();
		String noOfIt = procInputInst.getModelInputTab2Obj()
				.getNumberOfIterations();

		String meanSquare = procInputInst.getModelInputTab2Obj()
				.getMeanSquareValue();

		String lambda = procInputInst.getModelInputTab2Obj().getLambdaValue();

		String mu = procInputInst.getModelInputTab2Obj().getMuValue();

		restoreIncForParDerivateTf(incForParDer);
		restoreNoOfPredValueTf(noOfPredval);
		restoreConvergencCriterionTf(convergence);
		restoreNoOfIterTf(noOfIt);

		restoreMeanSquareTf(meanSquare);
		restoreLambdaValTf(lambda);
		restoreMuValTf(mu);

		restoreInitParamCalMethod(readPerst);

	}

	private void restoreResponseUnit(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().responseUnitTextField
				.setText(procInputInst.getModelInputTab1Obj().getResponseUnit());

	}

	private void restoreNormalizationUnit(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().normalizationUnitComboBoxForCA
				.setSelectedIndex(procInputInst.getModelInputTab1Obj()
						.getNormalizationIndex());

	}

	private void restoreDoseUnitTf(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().doseUnitTextFieldForCA
				.setText(procInputInst.getModelInputTab1Obj().getDoseUnit());

	}

	private void restoreInitParamCalMethod(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		if (procInputInst.getModelInputTab2Obj().getSourceOfIntParamValue() == 1) {
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setEnabled(false);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setEnabled(false);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().previouslyGenInitilParam
					.setEnabled(false);
		} else if (procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 1) {
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setSelected(true);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setEnabled(false);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().previouslyGenInitilParam
					.setEnabled(false);

		} else if (procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 1) {

			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setEnabled(false);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setSelected(true);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().previouslyGenInitilParam
					.setEnabled(false);

		} else if (procInputInst.getModelInputTab2Obj()
				.getPreviouslyGenInitVal() == 1) {

			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setEnabled(false);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setEnabled(false);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().previouslyGenInitilParam
					.setSelected(true);

		}

	}

	private void restoreMuValTf(String mu) throws RowsExceededException,
			WriteException, BiffException, IOException {
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().muTextField
				.setText(mu);
	}

	private void restoreLambdaValTf(String lambda)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().lambdaValueTextField
				.setText(lambda);

	}

	private void restoreMeanSquareTf(String meanSquare)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().meanSquareTextField
				.setText(meanSquare);

	}

	private void restoreNoOfIterTf(String noOfIt) throws RowsExceededException,
			WriteException, BiffException, IOException {
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().numberOfIterationTextField
				.setText(noOfIt);

	}

	private void restoreConvergencCriterionTf(String convergence)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().convergenceCriteriaTextField
				.setText(convergence);

	}

	private void restoreNoOfPredValueTf(String noOfPredval)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().numberOfPredictedValuesTextField
				.setText(noOfPredval);

	}

	private void restoreIncForParDerivateTf(String incForPadDeri)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().incrementsForpartialDerivativesTextField
				.setText(incForPadDeri);

	}

	private void restoreMinMethodCb(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		CaEngineSettingDispGuiCreator.createEngineSettingInstance().minimizationMthodComboBox
				.setSelectedIndex(procInputInst.getModelInputTab2Obj()
						.getMinimizationMethod());

	}

	private void restoreParamValueAndBouds(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueComboBox
				.setSelectedIndex(procInputInst.getModelInputTab2Obj()
						.getSourceOfIntParamValue());

		CaEngineSettingDispGuiCreator.createEngineSettingInstance().parameterBoundariesComboBox
				.setSelectedIndex(procInputInst.getModelInputTab2Obj()
						.getParamBoundarySelection());
	}

	private void restoreDataType(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().dataTypeComboBoxForCA
				.setSelectedIndex(procInputInst.getModelInputTab1Obj()
						.getDataType());
	}

	private void restoreWeightingOptions(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		if (procInputInst.getModelInputTab1Obj().getWeightingScheme() == 0) {
			CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().observedWeightingRadioButton
					.setSelected(true);
			CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().weightingOptionsComboBox
					.setEnabled(true);
			CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().weightingOptionsComboBox
					.setSelectedIndex(procInputInst.getModelInputTab1Obj()
							.getWeightingIndex());
			CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().predictedWeightingOptionsComboBox
					.setEnabled(false);
		} else if (procInputInst.getModelInputTab1Obj().getWeightingScheme() == 1) {
			CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().predictedWeightingRadioButton
					.setSelected(true);
			CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().predictedWeightingOptionsComboBox
					.setEnabled(true);
			CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().predictedWeightingOptionsComboBox
					.setSelectedIndex(procInputInst.getModelInputTab1Obj()
							.getWeightingIndex());
			CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().weightingOptionsComboBox
					.setEnabled(false);
		}

	}

	private void restoreInitialParameterValuePreviousState(
			String[][] linkParamData, String[][] data)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		CaInitEstimateDispGuiCreator.createCaInitEstGui()
				.createInitialParameterValueTable();
		int rowCount = CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
				.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			((DefaultTableModel) CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
					.getModel()).removeRow(i - 1);
			System.out.println("removed row " + (i - 1));
		}

		rowCount = CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().tableForLinkInitialParameterValueForCA
				.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			((DefaultTableModel) CaLinkParamInitEstimateGuiCreator
					.createLinkInitEstGui().tableForLinkInitialParameterValueForCA
					.getModel()).removeRow(i - 1);
			System.out.println("removed row " + (i - 1));
		}

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		procInputInst.getInitialParameterValueObj()
				.setInitialParameterValueForCA(linkParamData);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		for (int i = 0; i < linkParamData.length; i++) {

			int colCount = linkParamData[0].length;
			String[] s = new String[colCount];

			for (int j = 0; j < colCount; j++) {
				s[j] = linkParamData[i][j];

			}

			((DefaultTableModel) CaLinkParamInitEstimateGuiCreator
					.createLinkInitEstGui().tableForLinkInitialParameterValueForCA
					.getModel())
					.insertRow(
							CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().tableForLinkInitialParameterValueForCA
									.getRowCount(), s);
		}

		procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		procInputInst.getInitialParameterValueObj()
				.setInitialParameterValueForLinkModel(data);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		for (int i = 0; i < data.length; i++) {

			int colCount = data[0].length;
			String[] s = new String[colCount];

			for (int j = 0; j < colCount; j++) {
				s[j] = data[i][j];

			}

			((DefaultTableModel) CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
					.getModel()).insertRow(CaInitEstimateDispGuiCreator
					.createCaInitEstGui().tableForInitialParameterValueForCA
					.getRowCount(), s);
		}

		JScrollPane initialValueScrollPane = new JScrollPane(
				CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		initialValueScrollPane.setBackground(Color.white);
		initialValueScrollPane.setVisible(true);
		CaInitEstimateDispGuiCreator.createCaInitEstGui().parameterInitialValueInternalFrameForCA
				.getContentPane().removeAll();
		CaInitEstimateDispGuiCreator.createCaInitEstGui().parameterInitialValueInternalFrameForCA
				.add(initialValueScrollPane);

		CaInitEstimateDispGuiCreator.createCaInitEstGui().parameterInitialValueInternalFrameForCA
				.setSize(CaInitEstimateDispGuiCreator.createCaInitEstGui().parameterInitialValueInternalFrameForCA
						.getSize());

		CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
				.setVisible(true);
		CaInitEstimateDispGuiCreator.createCaInitEstGui().parameterInitialValueInternalFrameForCA
				.repaint();
		CaInitEstimateDispGuiCreator.createCaInitEstGui().parameterInitialValueInternalFrameForCA
				.validate();

	}

	private void restoreMappingPreviousState(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		for (int i = CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.size() - 1; i >= 0; i--) {
			CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
					.remove(i);
		}

		for (int i = CaMappingDispGuiCreator.createMappingInstance().sortVariableListModel
				.size() - 1; i >= 0; i--) {
			CaMappingDispGuiCreator.createMappingInstance().sortVariableListModel
					.remove(i);
		}

		ArrayList<String> columnPropAL = new ArrayList<String>();

		for (int i = 0; i < appInfo
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
				.getColumnPropertiesArrayList().size(); i++) {
			columnPropAL
					.add(appInfo
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
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getColumnPropertiesArrayList().get(i)
							.getColumnName());
		}

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		for (int i = 0; i < procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			CaMappingDispGuiCreator.createMappingInstance().sortVariableListModel.add(
					i, procInputInst.getMappingDataObj()
							.getSortVariablesListVector().get(i));

			for (int j = 0; j < columnPropAL.size(); j++) {
				if (CaMappingDispGuiCreator.createMappingInstance().sortVariableListModel
						.get(i) == columnPropAL.get(j))
					columnPropAL.remove(j);

			}

		}

		// setting the time column

		CaMappingDispGuiCreator.createMappingInstance().xVariableTextField
				.setText(procInputInst.getMappingDataObj().getxColumnName());

		for (int j = 0; j < columnPropAL.size(); j++) {
			String temp1 = (String) CaMappingDispGuiCreator.createMappingInstance().xVariableTextField
					.getText();
			String temp2 = (String) columnPropAL.get(j);
			if (temp1.equals(temp2))
				columnPropAL.remove(j);

		}

		// setting the concentration column

		CaMappingDispGuiCreator.createMappingInstance().yVariableTextField
				.setText(procInputInst.getMappingDataObj().getYcolumnName());
		for (int j = 0; j < columnPropAL.size(); j++) {
			String temp1 = (String) CaMappingDispGuiCreator.createMappingInstance().yVariableTextField
					.getText();
			String temp2 = (String) columnPropAL.get(j);
			if (temp1.equals(temp2))
				columnPropAL.remove(j);

		}

		// setting the available columns

		for (int i = 0; i < columnPropAL.size(); i++) {
			CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
					.add(i, columnPropAL.get(i));

		}

	}

}
