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

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Common.JinternalFrameFunctions;
import Common.LogEntry;
import Common.PETreeFunctions;

public class RestoreWagNelson {

	boolean ifSimulation;

	public RestoreWagNelson(String[] pathSplits) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[] split = pathSplits[pathSplits.length - 1].split("-");
		String wbName = split[split.length - 2];
		split = split[split.length - 1].split("]");
		String sheetName = split[split.length - 1].trim();

		DDViewLayer.createViewLayerInstance().isFromNca = false;
		DDViewLayer.createViewLayerInstance().isPlotMavenExecution = false;
		DDViewLayer.createViewLayerInstance().isFromPlotMavenMappingScreen = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		LogEntry.createLogEntryInstance().logEntry("Restored WagNelson");
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		DDViewLayer.createViewLayerInstance().isWagNelsonRestore = true;
		DDViewLayer.createViewLayerInstance().isCARestore = false;
		DDViewLayer.createViewLayerInstance().isNcaRestore = false;
		DDViewLayer.createViewLayerInstance().isDSRestore = false;
		DDViewLayer.createViewLayerInstance().isNPSRestore = false;
		DDViewLayer.createViewLayerInstance().isSCARestore = false;
		DDViewLayer.createViewLayerInstance().isPlotMavenRestore = false;
		DDViewLayer.createViewLayerInstance().isPDRestore = true;

		DDViewLayer.createMTInstance().mainTabbedFrame
				.setTitle("Wagner Nelson DeConvolution");

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
			PersistStructure persistStructureObject = new PersistStructure();
			ApplicationInfo readPerst = (ApplicationInfo) persistStructureObject
					.readEncryptedFile("outjar.enc");

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
									.getSelectedSheetIndex())
					.getWagNelsonInfo().getProcessingInputs()
					.getModelInputTab1Obj().isSimulation();

			DisplayContents.setM_appInfo(readPerst);

			createInportedDataSheet(key, readPerst.getProjectInfoAL().get(
					readPerst.getSelectedProjectIndex())
					.getSelectedWorkBookIndex());

			WagNelsonMainScreenCreator wagNelsonMainScreen = WagNelsonMainScreenCreator
					.createWagNelsonMainScreenInstance();
			wagNelsonMainScreen.wagNelsonMainScreenCreation();

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
		ImportFile.createDispContentsInstance().finishAndOpenClicked = false;

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

	private void setAllTheGuiScreenObjectsToNull() {
		WagNelsonMainScreenCreator.WAGNELSON_MAIN_SCREEN = null;
		WagNelsonMainScreenGUICreator.WAGNELSON_MAIN_SCREEN_GUI = null;
		DeConvoTabbedPaneCreator.DECONVO_TABBLE_DISP = null;
		DeConvoSetupAvailableCompCreator.DECONVO_AVAIL_COMP = null;
		DeConvoSetupDispCompGuiCreator.SETUP_DISP_COMP_GUI = null;
		DeConvoMappingDispGuiCreator.DECONVO_MAP_DISP = null;
		DeConvoModelOptionsGui.DECONVO_OPT_GUI = null;
		DeConvoResultAvailCompDisplayer.DECONVO_RES_AVAIL_GUI = null;
		DeConvoResultDispCompCreator.DECONVO_RES_DISP = null;
		ImportedDataSheet.importedDataSheetFrame = null;
		CaMultipleLevelSortingProvider.mlpInstance = null;
		CaNoOfSubjectDeterminer.noOfSubjectInst = null;
		DataPreparationForAllComponentsForCA.datPrepForCAInst = null;
	}

	private void restoringPreviousState(ApplicationInfo appInfo, int key)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo tempAppInfo = appInfo;

		restoreMappingPreviousState(appInfo);
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		lambdazData ld = storeTheLambdaZObjectDetails(tempAppInfo);
		restoreModelOptionsPreviousState(appInfo);
		String[][] dosingData = procInputInst.getDeConvoSetupInfoInst()
				.getDose();

		// after mapping the mapping is changed
		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(true);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
		TreePath path = DeConvoSetupAvailableCompCreator
				.createDeConvoSetupAvailCompInst().availableComponentsTree
				.getPathForRow(2);
		DeConvoSetupAvailableCompCreator.createDeConvoSetupAvailCompInst().availableComponentsTree
				.setSelectionPath(path);
		restoreLambdaZPreviousState(appInfo, ld);
		restoreDosingDataPreviousState(dosingData);
		restoreSimulationOptions(appInfo);

		restoreSpreadSheetResults(appInfo);
		// restorePlots(appInfo);
		restoreTextOutput(appInfo);

		procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(false);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	private void restoreDosingDataPreviousState(String[][] dosingData)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		DeConvoDosingDispGuiCreator.createDeConvoDosingDispGui()
				.createInternalGUIForDosing();

		int rowCount = DeConvoDosingDispGuiCreator.createDeConvoDosingDispGui().tableForDosing
				.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			((DefaultTableModel) DeConvoDosingDispGuiCreator
					.createDeConvoDosingDispGui().tableForDosing.getModel())
					.removeRow(i - 1);

		}

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		procInputInst.getDeConvoSetupInfoInst().setDose(dosingData);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		for (int i = 0; i < dosingData.length; i++) {

			int colCount = dosingData[0].length;
			String[] s = new String[colCount];

			for (int j = 0; j < colCount; j++) {
				s[j] = dosingData[i][j];

			}

			((DefaultTableModel) DeConvoDosingDispGuiCreator
					.createDeConvoDosingDispGui().tableForDosing.getModel())
					.insertRow(DeConvoDosingDispGuiCreator
							.createDeConvoDosingDispGui().tableForDosing
							.getRowCount(), s);
		}

		JScrollPane initialValueScrollPane = new JScrollPane(
				DeConvoDosingDispGuiCreator.createDeConvoDosingDispGui().tableForDosing,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		initialValueScrollPane.setBackground(Color.white);
		initialValueScrollPane.setVisible(true);
		DeConvoDosingDispGuiCreator.createDeConvoDosingDispGui().dosingInternalFrame
				.getContentPane().removeAll();
		DeConvoDosingDispGuiCreator.createDeConvoDosingDispGui().dosingInternalFrame
				.add(initialValueScrollPane);

		DeConvoDosingDispGuiCreator.createDeConvoDosingDispGui().dosingInternalFrame
				.setSize(DeConvoDosingDispGuiCreator
						.createDeConvoDosingDispGui().dosingInternalFrame
						.getSize());

		DeConvoDosingDispGuiCreator.createDeConvoDosingDispGui().tableForDosing
				.setVisible(true);
		DeConvoDosingDispGuiCreator.createDeConvoDosingDispGui().dosingInternalFrame
				.repaint();
		DeConvoDosingDispGuiCreator.createDeConvoDosingDispGui().dosingInternalFrame
				.validate();
	}

	private lambdazData storeTheLambdaZObjectDetails(ApplicationInfo tempAppInfo) {

		lambdazData ld = (lambdazData) tempAppInfo
				.getProjectInfoAL()
				.get(tempAppInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						tempAppInfo.getProjectInfoAL().get(
								tempAppInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						tempAppInfo
								.getProjectInfoAL()
								.get(tempAppInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										tempAppInfo
												.getProjectInfoAL()
												.get(
														tempAppInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getProcessingInputs().getLambdazDataobj().clone();

		return ld;

	}

	private void restoreLambdaZPreviousState(ApplicationInfo readPerst,
			lambdazData ld) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

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
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getProcessingInputs().setLambdazDataobj(ld);

	}

	private void restoreSimulationOptions(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

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

	private void restoreTextOutput(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().completeTextOutputTextArea
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
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getWorkSheetOutputs().getTextoutput().size(); i++) {
			DeConvoResultDispCompCreator.createDeConvoResDispCompInst().completeTextOutputTextArea
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
							.getWagNelsonInfo().getWorkSheetOutputs()
							.getTextoutput().get(i));

		}
	}

	private void restoreSpreadSheetResults(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		DeConvoResultDispCompCreator.createDeConvoResDispCompInst()
				.createTablesToFillResult();
		restoreFinalParameterVerticalDisplayTable(readPerst);
		restoreFinalParameterHoriZontalDisplayTable(readPerst);
		restorePredictedValueTable(readPerst);
		restoreSummaryTable(readPerst);
		restoreExclusionTable(readPerst);
		restoreDosingTable(readPerst);
		restoreUserSettingsTable(readPerst);
		restoreHistoryTable(readPerst);

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
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getVerticalParametersForCA();

		// remove all the rows in the table first
		for (int i = DeConvoResultDispCompCreator
				.createDeConvoResDispCompInst().finalparametersVerticalDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().finalparametersVerticalDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().finalparametersVerticalDisplayTable
					.getModel())
					.insertRow(
							DeConvoResultDispCompCreator
									.createDeConvoResDispCompInst().finalparametersVerticalDisplayTable
									.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				DeConvoResultDispCompCreator.createDeConvoResDispCompInst().finalparametersVerticalDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().finalParametersInternalFrame
								.getWidth(),
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().finalParametersInternalFrame
								.getHeight()));
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().finalParametersInternalFrame
				.getContentPane().removeAll();
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().finalParametersInternalFrame
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
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getHorizontalParametersForCA();

		// remove all the rows in the table first
		for (int i = DeConvoResultDispCompCreator
				.createDeConvoResDispCompInst().finalParametersHorizontalDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().finalParametersHorizontalDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().finalParametersHorizontalDisplayTable
					.getModel())
					.insertRow(
							DeConvoResultDispCompCreator
									.createDeConvoResDispCompInst().finalParametersHorizontalDisplayTable
									.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				DeConvoResultDispCompCreator.createDeConvoResDispCompInst().finalParametersHorizontalDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().finalParamHorizontalDisplayInternalFrame
								.getWidth(),
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().finalParamHorizontalDisplayInternalFrame
								.getHeight()));
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().finalParamHorizontalDisplayInternalFrame
				.getContentPane().removeAll();
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().finalParamHorizontalDisplayInternalFrame
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
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getPredictedValueForCA();

		// remove all the rows in the table first
		for (int i = DeConvoResultDispCompCreator
				.createDeConvoResDispCompInst().predictedValueDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().predictedValueDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().predictedValueDisplayTable
					.getModel()).insertRow(DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().predictedValueDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				DeConvoResultDispCompCreator.createDeConvoResDispCompInst().predictedValueDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().predictedValueDisplayInternalFrame
								.getWidth(),
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().predictedValueDisplayInternalFrame
								.getHeight()));
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().predictedValueDisplayInternalFrame
				.getContentPane().removeAll();
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().predictedValueDisplayInternalFrame
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
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getSummaryTableForCA();

		// remove all the rows in the table first
		for (int i = DeConvoResultDispCompCreator
				.createDeConvoResDispCompInst().summaryTableDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().summaryTableDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().summaryTableDisplayTable
					.getModel()).insertRow(DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().summaryTableDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				DeConvoResultDispCompCreator.createDeConvoResDispCompInst().summaryTableDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().summaryTableDisplayInternalFrame
								.getWidth(),
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().summaryTableDisplayInternalFrame
								.getHeight()));
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().summaryTableDisplayInternalFrame
				.getContentPane().removeAll();
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().summaryTableDisplayInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);

	}

	private void restoreExclusionTable(ApplicationInfo appInfo)
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
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getExclusionForDeConvo();

		// remove all the rows in the table first
		for (int i = DeConvoResultDispCompCreator
				.createDeConvoResDispCompInst().exclusionDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().exclusionDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().exclusionDisplayTable
					.getModel()).insertRow(CaResultDispCompCreator
					.createCaResDispCompInst().diagnosticsDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				DeConvoResultDispCompCreator.createDeConvoResDispCompInst().exclusionDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(DeConvoResultDispCompCreator
						.createDeConvoResDispCompInst().exclusionInternalFrame
						.getWidth(), DeConvoResultDispCompCreator
						.createDeConvoResDispCompInst().exclusionInternalFrame
						.getHeight()));
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().exclusionInternalFrame
				.getContentPane().removeAll();
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().exclusionInternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
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
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getDosingValueForCA();

		// remove all the rows in the table first
		for (int i = DeConvoResultDispCompCreator
				.createDeConvoResDispCompInst().dosingDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().dosingDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().dosingDisplayTable
					.getModel()).insertRow(DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().dosingDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				DeConvoResultDispCompCreator.createDeConvoResDispCompInst().dosingDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().dosingDisplayInternalFrame
								.getWidth(),
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().dosingDisplayInternalFrame
								.getHeight()));
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().dosingDisplayInternalFrame
				.getContentPane().removeAll();
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().dosingDisplayInternalFrame
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
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getUserSettingsForCA();

		// remove all the rows in the table first
		for (int i = DeConvoResultDispCompCreator
				.createDeConvoResDispCompInst().userSettingsDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().userSettingsDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().userSettingsDisplayTable
					.getModel()).insertRow(DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().userSettingsDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				DeConvoResultDispCompCreator.createDeConvoResDispCompInst().userSettingsDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().userSettingsDisplayInternalFrame
								.getWidth(),
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().userSettingsDisplayInternalFrame
								.getHeight()));
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().userSettingsDisplayInternalFrame
				.getContentPane().removeAll();
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().userSettingsDisplayInternalFrame
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
								.getSelectedSheetIndex()).getWagNelsonInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getHistoryForCA();

		// remove all the rows in the table first
		for (int i = DeConvoResultDispCompCreator
				.createDeConvoResDispCompInst().historyDisplayTable
				.getRowCount() - 1; i >= 0; i--)
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().historyDisplayTable
					.getModel()).removeRow(i);

		// Insert the content rows
		for (int i = 1; i < data.length; i++) {
			String[] s = new String[data[i].length];
			for (int j = 0; j < data[i].length; j++) {
				s[j] = data[i][j];
			}
			((DefaultTableModel) DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().historyDisplayTable
					.getModel()).insertRow(DeConvoResultDispCompCreator
					.createDeConvoResDispCompInst().historyDisplayTable
					.getRowCount(), s);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				DeConvoResultDispCompCreator.createDeConvoResDispCompInst().historyDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().historyDisplayInternalFrame
								.getWidth(),
						DeConvoResultDispCompCreator
								.createDeConvoResDispCompInst().historyDisplayInternalFrame
								.getHeight()));
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().historyDisplayInternalFrame
				.getContentPane().removeAll();
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().historyDisplayInternalFrame
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
								.getSelectedSheetIndex()).getPdInfo()
				.getWorkSheetOutputs().getPlotOutputs()
				.getLinearPlotForPartialDerivative();
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
								.getSelectedSheetIndex()).getPdInfo()
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
								.getSelectedSheetIndex()).getPdInfo()
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
								.getSelectedSheetIndex()).getPdInfo()
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
								.getSelectedSheetIndex()).getPdInfo()
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
								.getSelectedSheetIndex()).getPdInfo()
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
								.getSelectedSheetIndex()).getPdInfo()
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
								.getSelectedSheetIndex()).getPdInfo()
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
								.getSelectedSheetIndex()).getPdInfo()
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
								.getSelectedSheetIndex()).getPdInfo()
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

	private void restoreModelOptionsPreviousState(ApplicationInfo readPerst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		DeConvoModelOptionsGui.createDeConvoOptionsInstance().weightingOptions
				.setSelectedIndex(procInputInst.getModelInputsObj()
						.getWeightingOption());
		DeConvoModelOptionsGui.createDeConvoOptionsInstance().titlesTextArea
				.setText(procInputInst.getModelInputsObj().getTitle());
		DeConvoModelOptionsGui.createDeConvoOptionsInstance().calculationMethodOptions
				.setSelectedIndex(procInputInst.getModelInputsObj()
						.getCalculationMethod());

		DeConvoModelOptionsGui.createDeConvoOptionsInstance().selectionCheckBoc
				.setSelected(procInputInst.getModelInputsObj()
						.isDisableCurveStripping());
		DeConvoModelOptionsGui.createDeConvoOptionsInstance().exclusionCheckBox
				.setSelected(procInputInst.getModelInputsObj()
						.isClearExclusion());

		DeConvoModelOptionsGui.createDeConvoOptionsInstance().unitTextField
				.setText(procInputInst.getModelInputsObj().getDoseUnits());

		DeConvoModelOptionsGui.createDeConvoOptionsInstance().normalizationUnit
				.setSelectedIndex(procInputInst.getModelInputsObj()
						.getNormalizationUnit());

	}

	private void restoreMappingPreviousState(ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		for (int i = DeConvoMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.size() - 1; i >= 0; i--) {
			DeConvoMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
					.remove(i);
		}

		for (int i = DeConvoMappingDispGuiCreator.createMappingInstance().sortVariableListModel
				.size() - 1; i >= 0; i--) {
			DeConvoMappingDispGuiCreator.createMappingInstance().sortVariableListModel
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
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		for (int i = 0; i < procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			DeConvoMappingDispGuiCreator.createMappingInstance().sortVariableListModel
					.add(i, procInputInst.getMappingDataObj()
							.getSortVariablesListVector().get(i));

			for (int j = 0; j < columnPropAL.size(); j++) {
				if (DeConvoMappingDispGuiCreator.createMappingInstance().sortVariableListModel
						.get(i) == columnPropAL.get(j))
					columnPropAL.remove(j);

			}

		}

		// setting the time column

		DeConvoMappingDispGuiCreator.createMappingInstance().xVariableTextField
				.setText(procInputInst.getMappingDataObj().getxColumnName());

		for (int j = 0; j < columnPropAL.size(); j++) {
			String temp1 = (String) DeConvoMappingDispGuiCreator
					.createMappingInstance().xVariableTextField.getText();
			String temp2 = (String) columnPropAL.get(j);
			if (temp1.equals(temp2))
				columnPropAL.remove(j);

		}

		// setting the concentration column

		DeConvoMappingDispGuiCreator.createMappingInstance().yVariableTextField
				.setText(procInputInst.getMappingDataObj().getYcolumnName());
		for (int j = 0; j < columnPropAL.size(); j++) {
			String temp1 = (String) DeConvoMappingDispGuiCreator
					.createMappingInstance().yVariableTextField.getText();
			String temp2 = (String) columnPropAL.get(j);
			if (temp1.equals(temp2))
				columnPropAL.remove(j);

		}

		// setting the available columns

		for (int i = 0; i < columnPropAL.size(); i++) {
			DeConvoMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
					.add(i, columnPropAL.get(i));

		}

	}

}
