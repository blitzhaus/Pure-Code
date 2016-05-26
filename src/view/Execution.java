package view;

import java.awt.Cursor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;

import com.itextpdf.text.pdf.events.IndexEvents.Entry;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.CursorToolkitTwo;
import Common.LogEntry;
import Common.SerializeTestCases;

public class Execution {

	public static Execution EXECTUTE = null;

	public static Execution createExecuteInstance() {
		if (EXECTUTE == null) {
			EXECTUTE = new Execution();
		}
		return EXECTUTE;
	}

	void executeAnalysis() throws RowsExceededException, WriteException,
			BiffException, IOException, InterruptedException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// SerializeTestCases.createSerTestCasesInst().serializeAnalysis();

		if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {

			boolean isvalidInput = DSHandlers.createDesStatEventHandlerInst()
					.checkValidInputs();
			if (isvalidInput == true) {
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
						.setAnalysisType("desstats");

				preComputationsForDS();
				executeDescStats();
			} else {
				JOptionPane
						.showMessageDialog(
								DescriptiveStatMainPage
										.createDescStatMainPageInst().desStatsMainIF,
								"Please ensure the last sort variable is numeric data and all the required fields are populated",
								"Conform", JOptionPane.YES_OPTION);
			}

		} else

		if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == true)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)) {

			boolean isValidInput = ScaHandlers.createScaHandInst()
					.checkValidInput();
			if (isValidInput == true) {
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
						.setAnalysisType("sca");
				preComputationsForSCA();
				executeSCM();
			} else {
			}

		} else

		if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == true)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {

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
									.getSelectedSheetIndex()).setAnalysisType(
							"nps");
			boolean isValidInput = NPSHandlers.createNPSHandlersInst()
					.checkValidInputs();
			if (isValidInput == true) {
				preComputationsForNPS();
				executeNPS();
			} else {

				JOptionPane
						.showMessageDialog(
								NPSMainPage.createNPSMainPageInst().ncaMainScreen,
								"Please ensure the inputs are correct. Please provide all the inputs/ conform valid inputs",
								"Conform", JOptionPane.YES_OPTION);

			}

		}

		// to execute the nca code but not the plot related code
		if ((DDViewLayer.createViewLayerInstance().isNCAExecution == true)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)) {

			addCarryAlongColumns();
			addParameterNamesToAppInfo();
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
									.getSelectedSheetIndex()).setAnalysisType(
							"nca");
			if (appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.getisSparseSelected() == true) {
				DDViewLayer.createViewLayerInstance().isForSparseSample = true;

				multipleLevelSorting mlSortInstance = multipleLevelSorting
						.createMultipleSortInst();

				mlSortInstance.main(null);

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
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().setDatForSparse(
								mlSortInstance.dataSorted);

				DDViewLayer.createViewLayerInstance().isForSparseSample = false;

			}

			if (appInfo
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
									.getSelectedSheetIndex()).getNcaInfo()
					.isDataChanged() == true) {
				NcaSetupAvailCompTreeValueChangeHandler
						.createNcaavailableCompHandlerInst()
						.dosingNodeselected();
			} else {
				stopNCAEditing();
				performNCAExecution();
			}

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == true)) {

			boolean isInputValid = PlotMavenHandlers
					.createPlotMavenHandlerInst().validateInput();
			if (isInputValid == true) {
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
						.setAnalysisType("plotmaven");
				precomputationsForPM();
				executePlotMaven();
			} else {

				JOptionPane
						.showMessageDialog(
								PlotMavenCreateUI.createPlotMavenUIInst().PlotMavenMainInternalFrame,
								"Please ensure the inputs are correct. Please provide all the inputs/ conform valid inputs",
								"Conform", JOptionPane.YES_OPTION);

			}

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isCAExecution == true)) {
			stopCAEditing();
			performCAExecution();
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isInVitroExecution == true)) {
			stopInVitroEditing();
			performInVitroExecution();
		}

		else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == true)) {

			if (appInfo
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
									.getSelectedSheetIndex()).getBqlInfo()
					.isDataChanged() == true) {
				JOptionPane.showMessageDialog(BQLMainScreen
						.createBQLMainScrInst().bqlMainScreenIF,
						"Data Changed", "Conform", JOptionPane.YES_OPTION);
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
										.getSelectedSheetIndex()).getBqlInfo()
						.setDataChanged(false);

			}

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
									.getSelectedSheetIndex()).setAnalysisType(
							"bql");
			DDViewLayer.createViewLayerInstance().ddControllerInterface
					.performBQL(appInfo);

		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == false)
				&& (DDViewLayer.createViewLayerInstance().isFromTableMaven == true)) {

			if (appInfo
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
									.getSelectedSheetIndex()).getTmInfo()
					.isDataChanged() == true) {
				JOptionPane.showMessageDialog(TableMavenLaunchScreen
						.createTableMavenLauncherInst(),

				"Data Changed", "Conform", JOptionPane.YES_OPTION);
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
										.getSelectedSheetIndex()).getTmInfo()
						.setDataChanged(false);
			}

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
									.getSelectedSheetIndex()).setAnalysisType(
							"tabmaven");
			DDViewLayer.createViewLayerInstance().ddControllerInterface
					.performTableMaven(appInfo);
		} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo
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
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == false)
				&& (DDViewLayer.createViewLayerInstance().isFromTableMaven == false)
				&& (DDViewLayer.createViewLayerInstance().isTableTrans = true)) {
			boolean isInputValid = checkForValidInput();
			if (isInputValid == true) {
				ttprecomputations();

				if (checkForValidCustomTime() == true) {
					executeTabTrans();
				} else {
					JOptionPane
							.showMessageDialog(
									TableTransformations
											.createTableTrasGUIInst().mappingIF,
									"Please check the custom time point given. It may not be present in all the profiles",
									"Conform", JOptionPane.YES_OPTION);
				}

			} else {
				JOptionPane
						.showMessageDialog(
								TableTransformations.createTableTrasGUIInst().mappingIF,
								"Please ensure x & y column data are numeric / n value is a valid entry / custom time is present in each profile",
								"Conform", JOptionPane.YES_OPTION);
			}

		}

		else if (DDViewLayer.createViewLayerInstance().isDisplayContentsTable() == false) {
			stopDisplayDataEditing();

		} else if (DDViewLayer.createViewLayerInstance().isimportedDataTable == false) {
			stopImportDataEditing();

		}

	}

	private void performInVitroExecution() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		preComputationsForInVitro();

		DDViewLayer.createViewLayerInstance().ddControllerInterface
				.performInVitro(appInfo);

	}

	private void preComputationsForInVitro() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		CaNoOfSubjectDeterminer determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();
		DataPreparationForAllComponentsForCA datPrepForCAInst = DataPreparationForAllComponentsForCA
				.createDetaPrepForCAInstance();

		CaMultipleLevelSortingProvider mlSortInstance = CaMultipleLevelSortingProvider
				.createMultipleSortInstance();
		mlSortInstance.setAppInfo(appInfo);
		mlSortInstance.sortData(null);

		determineNoOfSubInst
				.determineNumberOfSubject(mlSortInstance.dataSorted);
		datPrepForCAInst.dataPreparationForAll(mlSortInstance.dataSorted);

			
		InVitroResultDispCompCreator.createInVitroResDispCompInst().createTablesToFillResult();
		
		
	}

	private void stopInVitroEditing() {
		// TODO Auto-generated method stub

	}

	private void addParameterNamesToAppInfo() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		HashMap<String, String[]> map = new HashMap<String, String[]>();
		int rows = NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable
				.getRowCount();
		for (int i = 0; i < rows; i++) {
			String[] value = new String[2];
			value[0] = NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable
					.getValueAt(i, 1).toString();
			value[1] = NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable
					.getValueAt(i, 2).toString();
			String key = NcaParameterNamesDispGui
					.createParameterNamesInstance().parameterNamesTable
					.getValueAt(i, 0).toString();
			map.put(key, value);

		}

		// setting the parameter names table in the form of a hash map into
		// appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterNamesObj()
				.setParameterNamesMap(map);

	}

	private void addCarryAlongColumns() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[][] sortedData = appInfo
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
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getNcaDat();

		String[][] unsortedData = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getElementFromDS(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getSelectedSheetIndex());

		CarryAlongAndSubjectId.createCarryAlongAndSubIdInst()
				.prepareCarryAlongData(sortedData, unsortedData);

	}

	private boolean checkForValidInput() {
		boolean valid = checkTimeConcValidDataTypes();
		boolean validOptions = areMiscelleniousOptionsValid();

		if ((valid == true) && (validOptions == true)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean checkForValidCustomTime() { // check for custom
		// transformations

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		boolean check = true;
		if (appInfo
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
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().isTtisCustom() == true) {

			try {
				double customTimePoint = Double
						.parseDouble(TableTransformations
								.createTableTrasGUIInst().customTimePointTF
								.getText());

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
										.getSelectedSheetIndex()).getTtInfo()
						.getProcessingInputs()
						.setTtCostomPoint(customTimePoint);

				check = checkIfCustomTimePresentInEachProfile(customTimePoint);

			} catch (Exception e) {
				return false;
			}
		}
		return check;
	}

	private boolean areMiscelleniousOptionsValid() {
		if ((TableTransformations.createTableTrasGUIInst().arithmeticFuncListCombo
				.getSelectedIndex() >= 6)
				&& (TableTransformations.createTableTrasGUIInst().arithmeticFuncListCombo
						.getSelectedIndex() <= 10)) {
			try {
				Double.parseDouble(TableTransformations
						.createTableTrasGUIInst().nValueTF.getText());
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	private boolean checkTimeConcValidDataTypes() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		boolean isTimeColumnEmpty = false;
		boolean isConcColumnEmpty = false;
		boolean isTimeNumeric = false;
		boolean isConcNumeric = false;

		if (appInfo
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
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName()
				.equals("")) {
			isTimeColumnEmpty = true;

		} else {
			// determine data type of time column
			if (appInfo
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
					.getTtInfo()
					.getColumnPropertiesArrayList()
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
									.getTtInfo().getProcessingInputs()
									.getMappingDataObj()
									.getxColumnCorrespondinOriginalIndex())
					.getDataType().equals("Numeric")) {
				isTimeNumeric = true;
			} else {

			}
		}

		if (appInfo
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
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName()
				.equals("")) {
			isConcColumnEmpty = true;
		} else {
			if (appInfo
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
					.getTtInfo()
					.getColumnPropertiesArrayList()
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
									.getTtInfo().getProcessingInputs()
									.getMappingDataObj()
									.getyColumnCorrespondinOriginalIndex())
					.getDataType().equals("Numeric")) {
				isConcNumeric = true;
			} else {

			}
		}

		if ((isTimeColumnEmpty == false) && (isConcColumnEmpty == false)
				&& (isTimeNumeric == true) && (isConcNumeric == true)) {
			return true;
		} else {
			return false;
		}

	}

	private void ttprecomputations() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if ((TableTransformations.createTableTrasGUIInst().arithmeticFuncListCombo
				.getSelectedIndex() >= 6)
				&& (TableTransformations.createTableTrasGUIInst().arithmeticFuncListCombo
						.getSelectedIndex() <= 10)) {
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
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().setTtnValue(
							Double.parseDouble(TableTransformations
									.createTableTrasGUIInst().nValueTF
									.getText()));

		}

		if (appInfo
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
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() == 0) {

		} else {

			DataPreparationForTT dataPrepTT = DataPreparationForTT
					.createDatPrepForTT();
			DataPreparationForAllComponents datPrepForAllComp = DataPreparationForAllComponents
					.createDataPrepAllCompInst();

			DetermineNumberOfSubject detNoOfSubj = DetermineNumberOfSubject
					.createDetNoSubInst();
			multipleLevelSorting.createMultipleSortInst().main(null);
			dataPrepTT.prepareData();
			detNoOfSubj.determineNumberOfSubject(dataPrepTT.Data);
			datPrepForAllComp.dataPreparationForAll(dataPrepTT.Data);

		}

	}

	private boolean checkIfCustomTimePresentInEachProfile(double timePoint) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int numberOfSub = appInfo
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
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject();

		for (int i = 0; i < numberOfSub; i++) {
			double[] time = TabTransformationsHandler
					.createTableTransformationInstance()
					.getCorrespondingProfileTime(i);
			boolean presentInProf = false;
			for (int j = 0; j < time.length; j++) {
				if (time[j] == timePoint) {
					presentInProf = true;
					break;
				} else {

				}
			}

			if (presentInProf == false) {
				return false;
			}

		}

		return true;
	}

	private void executeTabTrans() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DDViewLayer.createViewLayerInstance().ddControllerInterface
				.initiateAnalysis(appInfo);

	}

	private void precomputationsForPM() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if (appInfo
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
								.getSelectedSheetIndex()).getPlotInfo()
				.isDataChanged() == true) {
			JOptionPane.showMessageDialog(
					NPSMainPage.createNPSMainPageInst().ncaMainScreen,
					"Data Changed", "Conform", JOptionPane.YES_OPTION);
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
									.getSelectedSheetIndex()).getPlotInfo()
					.setDataChanged(false);
		}
		DataPreparationForNca dataPrepNca = new DataPreparationForNca();
		multipleLevelSorting.createMultipleSortInst().main(null);
		PlotMavenMainScreen.createPlotMavenInstance()
				.createTheResultTabComponents();
		PlotMavenMainScreen.createPlotMavenInstance().removePreviousResults();
		dataPrepNca.prepareData();
		DetermineNumberOfSubject.createDetNoSubInst().determineNumberOfSubject(
				dataPrepNca.Dat);
		DataPreparationForAllComponents.createDataPrepAllCompInst()
				.dataPreparationForAll(dataPrepNca.Dat);
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
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().setPlotDat(dataPrepNca.Dat);

		LogEntry.createLogEntryInstance().logEntry("Plot Maven Execution");

	}

	private void performCAExecution() throws RowsExceededException,
			WriteException, BiffException, IOException {
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
		preComputationsForCA();
		if (analysisType.equals("pk")) {
			CursorToolkitTwo.startWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
			DDViewLayer.createViewLayerInstance().ddControllerInterface
					.performCA(appInfo);
			CursorToolkitTwo.stopWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());

		} else if (analysisType.equals("pd")) {
			CursorToolkitTwo.startWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
			DDViewLayer.createViewLayerInstance().ddControllerInterface
					.performPD(appInfo);
			CursorToolkitTwo.stopWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
		} else if (analysisType.equals("mm")) {
			CursorToolkitTwo.startWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
			DDViewLayer.createViewLayerInstance().ddControllerInterface
					.performMM(appInfo);
			CursorToolkitTwo.stopWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());

		} else if (analysisType.equals("pkpdlink")) {
			CursorToolkitTwo.startWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
			DDViewLayer.createViewLayerInstance().ddControllerInterface
					.performPkPdLink(appInfo);
			CursorToolkitTwo.stopWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());

		} else if (analysisType.equals("irm")) {
			CursorToolkitTwo.startWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
			DDViewLayer.createViewLayerInstance().ddControllerInterface
					.performIRM(appInfo);
			CursorToolkitTwo.stopWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());

		} else if (analysisType.equals("ascii")) {
			
			CursorToolkitTwo.startWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
			setInitEstDataStructures();
			setAsciiModelInfo();// for storing ascii model information in appinfo
			DDViewLayer.createViewLayerInstance().ddControllerInterface
					.performAscii(appInfo);
			CursorToolkitTwo.stopWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
			
		}

	}

	private void setAsciiModelInfo() {
		/*ProcessingInputsInfo procInputInst = CaMapingHandler
		.createCaMapHandInst().copyProcessingInput();*/
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		//set the number of primary parameters
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getProfileAndParamInfoObj()
		.setNumberOfParameter(			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters().getPriParameters().size());
		
		//set primary parameter names
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getProfileAndParamInfoObj().setParameterNameForCA(getPrimParamNames());
		
		//set number of sec parameters
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getProfileAndParamInfoObj().setNumberOfSecondaryParameters(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL().get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters().getSecParameters().size());
		
		
		//set sec parameter names
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getProfileAndParamInfoObj().setSecondaryParameterNameForCA(getSeconParamNames());
		
		//set the number of functions
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getProfileAndParamInfoObj().setNumberOfFunction(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL().get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiCommands().getnFunc());
		
		//number of determinants/ derivatives
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getProfileAndParamInfoObj().setNumberOfDeterminant(			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL().get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiCommands().getnDer());
		
		System.out.println();
	}

	 String[] getSeconParamNames() {

		/*ProcessingInputsInfo procInputInst = CaMapingHandler
		.createCaMapHandInst().copyProcessingInput();*/
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[] s = new String[			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters().getSecParameters().size()];
		HashMap<Integer, String> hm = 			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters().getSecParameters();
		int i=0;
		 for (Map.Entry entry : hm.entrySet()) {
			 
			 //System.out.println("key,val: " + entry.getKey() + "," + entry.getValue());
			 s[i++] = entry.getValue().toString();
			 }
		return s;
	
	}

	 String[] getPrimParamNames() {
/*		ProcessingInputsInfo procInputInst = CaMapingHandler
		.createCaMapHandInst().copyProcessingInput();*/
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[] s = new String[			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters().getPriParameters().size()];
		HashMap<Integer, String> hm = 			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters().getPriParameters();
		int i=0;
		 for (Map.Entry entry : hm.entrySet()) {
			 
			 //System.out.println("key,val: " + entry.getKey() + "," + entry.getValue());
			 s[i++] = entry.getValue().toString();
			 }
		return s;
	}

	private void setInitEstDataStructures() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
	
		String[][] local = getInitialEstimatesTableData();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getInitialParameterValueObj()
				.setInitialParameterValueForCA(local);
		
	

	}

	private String[][] getInitialEstimatesTableData() {
		int rows = AsciiInitialEstGui.createAsciiinitGuiInst().asciiIntEstTable
				.getRowCount();
		int columns = AsciiInitialEstGui.createAsciiinitGuiInst().asciiIntEstTable
				.getColumnCount();
		String[][] temp = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				try {
					temp[i][j] = AsciiInitialEstGui.createAsciiinitGuiInst().asciiIntEstTable
							.getValueAt(i, j).toString();
				} catch (NullPointerException exp) {
					temp[i][j] = "";
				}
			}
		}
		return temp;

	}

	private void preComputationsForCA() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		CaNoOfSubjectDeterminer determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();
		DataPreparationForAllComponentsForCA datPrepForCAInst = DataPreparationForAllComponentsForCA
				.createDetaPrepForCAInstance();

		CaMultipleLevelSortingProvider mlSortInstance = CaMultipleLevelSortingProvider
				.createMultipleSortInstance();
		mlSortInstance.setAppInfo(appInfo);
		mlSortInstance.sortData(null);

		determineNoOfSubInst
				.determineNumberOfSubject(mlSortInstance.dataSorted);
		datPrepForCAInst.dataPreparationForAll(mlSortInstance.dataSorted);

		SecondaryParameterList secParamListInst = SecondaryParameterList
				.createSecParamAndUnitListInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		secParamListInst.createSecondaryParameterList(procInputInst
				.getModelInputTab1Obj().getModelNumberForCA());

		
			CaResultDispCompCreator.createCaResDispCompInst()
					.createTablesToFillResult();
		

	}

	private void preComputationsForNPS() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (appInfo
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
								.getSelectedSheetIndex()).getNpsInfo()
				.isDataChanged() == true) {
			JOptionPane.showMessageDialog(
					NPSMainPage.createNPSMainPageInst().ncaMainScreen,
					"Data Changed", "Conform", JOptionPane.YES_OPTION);
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
									.getSelectedSheetIndex()).getNpsInfo()
					.setDataChanged(false);
		}

		DataPreparationForNPS dataPrepNps = DataPreparationForNPS
				.createDatPrepForNPS();
		DataPreparationForAllComponents datPrepForAllComp = DataPreparationForAllComponents
				.createDataPrepAllCompInst();

		DetermineNumberOfSubject detNoOfSubj = DetermineNumberOfSubject
				.createDetNoSubInst();
		multipleLevelSorting.createMultipleSortInst().main(null);
		dataPrepNps.prepareData();
		detNoOfSubj.determineNumberOfSubject(dataPrepNps.Data);
		datPrepForAllComp.dataPreparationForAll(dataPrepNps.Data);
	}

	private void preComputationsForSCA() throws RowsExceededException,
			WriteException, BiffException, IOException {

		new DataPreparationForSCA();

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if (appInfo
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
								.getSelectedSheetIndex()).getScaInfo()
				.isDataChanged() == true) {
			JOptionPane.showMessageDialog(
					SCAMainPage.createScainstance().scaMainInternalFrame,
					"Data Changed", "Conform", JOptionPane.YES_OPTION);
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
									.getSelectedSheetIndex()).getScaInfo()
					.setDataChanged(false);
		}
		multipleLevelSorting.createMultipleSortInst().main(null);
		DataPreparationForSCA.createDatPrepForSCA().prepareData();
		DetermineNumberOfSubject.createDetNoSubInst().determineNumberOfSubject(
				DataPreparationForSCA.createDatPrepForSCA().Data);
		DataPreparationForAllComponents.createDataPrepAllCompInst()
				.dataPreparationForAll(
						DataPreparationForSCA.createDatPrepForSCA().Data);

	}

	private void preComputationsForDS() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if (appInfo
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
								.getSelectedSheetIndex()).getDesStatsInfo()
				.isDataChanged() == true) {
			JOptionPane.showMessageDialog(DescriptiveStatMainPage
					.createDescStatMainPageInst().desStatsMainIF,
					"Data Changed", "Conform", JOptionPane.YES_OPTION);

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
									.getSelectedSheetIndex()).getDesStatsInfo()
					.setDataChanged(false);
		}

		multipleLevelSorting.createMultipleSortInst().main(null);
		DataPreparationForDS.createDatPrepForDS().prepareData();
		DetermineNumberOfSubject.createDetNoSubInst().determineNumberOfSubject(
				DataPreparationForDS.createDatPrepForDS().Data);
		DataPreparationForAllComponents.createDataPrepAllCompInst()
				.dataPreparationForAll(
						DataPreparationForDS.createDatPrepForDS().Data);
	}

	private void executePlotMaven() throws RowsExceededException,
			WriteException, BiffException, IOException {
		CursorToolkitTwo.startWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
		DDViewLayer.createViewLayerInstance().ddControllerInterface
				.initiateAnalysis(DisplayContents.createAppInfoInstance());
		CursorToolkitTwo.stopWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
	}

	public static void performNCAExecution() throws RowsExceededException,
			WriteException, BiffException, IOException, InterruptedException {

		CursorToolkitTwo.startWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
		DDViewLayer.createViewLayerInstance().setEnabled(false);

		DDViewLayer.createViewLayerInstance().ddControllerInterface
				.initiateAnalysis(DisplayContents.createAppInfoInstance());

		DDViewLayer.createViewLayerInstance().setEnabled(true);
		CursorToolkitTwo.stopWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
	}

	private void executeNPS() throws RowsExceededException, WriteException,
			BiffException, IOException {
		CursorToolkitTwo.startWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
		DDViewLayer.createViewLayerInstance().ddControllerInterface
				.initiateAnalysis(DisplayContents.createAppInfoInstance());
		CursorToolkitTwo.stopWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
	}

	private void executeSCM() throws RowsExceededException, WriteException,
			BiffException, IOException {
		CursorToolkitTwo.startWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
		DDViewLayer.createViewLayerInstance().ddControllerInterface
				.initiateAnalysis(DisplayContents.createAppInfoInstance());
		CursorToolkitTwo.stopWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());

	}

	private void executeDescStats() throws RowsExceededException,
			WriteException, BiffException, IOException {
		CursorToolkitTwo.startWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
		DDViewLayer.createViewLayerInstance().ddControllerInterface
				.initiateAnalysis(DisplayContents.createAppInfoInstance());
		CursorToolkitTwo.stopWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
		
	}

	private void stopCAEditing() throws RowsExceededException, WriteException,
			BiffException, IOException {
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

		if (analysisType.equals("pk")) {

			if (CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
					.isEditing())
				CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
						.getCellEditor().stopCellEditing();

			if (CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
					.isEditing())
				CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
						.getCellEditor().stopCellEditing();

			if (CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
					.isEditing())
				CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
						.getCellEditor().stopCellEditing();

		} else if (analysisType.equals("pd")) {
			if (CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
					.isEditing())
				CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
						.getCellEditor().stopCellEditing();
			if (CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
					.isEditing())
				CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
						.getCellEditor().stopCellEditing();

		} else if (analysisType.equals("mm")) {
			if (CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
					.isEditing())
				CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
						.getCellEditor().stopCellEditing();

			if (CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
					.isEditing())
				CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
						.getCellEditor().stopCellEditing();

			if (CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
					.isEditing())
				CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
						.getCellEditor().stopCellEditing();

		} else if (analysisType.equals("pkpdlink")) {
			if (CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
					.isEditing())
				CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
						.getCellEditor().stopCellEditing();

			if (CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().tableForLinkInitialParameterValueForCA
					.isEditing())
				CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().tableForLinkInitialParameterValueForCA
						.getCellEditor().stopCellEditing();

			if (CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
					.isEditing())
				CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
						.getCellEditor().stopCellEditing();

			if (CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
					.isEditing())
				CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
						.getCellEditor().stopCellEditing();

		} else if (analysisType.equals("irm")) {
			if (CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
					.isEditing())
				CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
						.getCellEditor().stopCellEditing();

			if (CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
					.isEditing())
				CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
						.getCellEditor().stopCellEditing();

			if (CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
					.isEditing())
				CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
						.getCellEditor().stopCellEditing();

		}

	}

	private void stopDisplayDataEditing() throws RowsExceededException,
			WriteException, BiffException, IOException {

		if (ImportFile.createDispContentsInstance().dataTable.isEditing())
			ImportFile.createDispContentsInstance().dataTable.getCellEditor()
					.stopCellEditing();

		/* ImportedDataSheet.createImportedDataSheetInstance().importedDataTable */

	}

	private void stopImportDataEditing() {

		if (ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
				.isEditing())
			ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
					.getCellEditor().stopCellEditing();

		/* ImportedDataSheet.createImportedDataSheetInstance().importedDataTable */

	}

	private void stopNCAEditing() throws RowsExceededException, WriteException,
			BiffException, IOException {
		NcaDosingDispGui NcaDosingDispGuiInst = NcaDosingDispGui
				.createNcaDosingGuiInst();
		NcaSubAreasDispGui NcaSubAreasDispGuiInst = NcaSubAreasDispGui
				.createNcaSubAreasDispGuiInst();
		NcaParameterNamesDispGui NcaParameterNamesDispGuiInst = NcaParameterNamesDispGui
				.createParameterNamesInstance();

		if (NcaDosingDispGuiInst.tableForDosing.isEditing())
			NcaDosingDispGuiInst.tableForDosing.getCellEditor()
					.stopCellEditing();
		if (NcaSubAreasDispGuiInst.tableForPartialArea.isEditing())
			NcaDosingDispGuiInst.tableForDosing.getCellEditor()
					.stopCellEditing();
		if (NcaParameterNamesDispGuiInst.parameterNamesTable.isEditing())
			NcaDosingDispGuiInst.tableForDosing.getCellEditor()
					.stopCellEditing();
	}
}
