package view;

import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class InVitroOutputDisplayer {

	public static InVitroOutputDisplayer INVITRO_OUT_GEN = null;

	public static InVitroOutputDisplayer createInVitroOutInst() {
		if (INVITRO_OUT_GEN == null) {
			INVITRO_OUT_GEN = new InVitroOutputDisplayer();
		}
		return INVITRO_OUT_GEN;
	}

	
	InVitroResultDispCompCreator inVitroResDispCreatorInst;
	PlotsOutputDisplayer plotRetrievingInst;
	

	public void inVitroOutputGeneration() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

	

		inVitroResDispCreatorInst = InVitroResultDispCompCreator
				.createInVitroResDispCompInst();

		inVitroResDispCreatorInst.initializationOfPlotArrays();
		inVitroResDispCreatorInst.removePreviousResults();

		inVitroResDispCreatorInst.removingExistingRowsOfOutputTables();

		inVitroResDispCreatorInst.populateAvailableResultsWithPlots();

		/*plotRetrievingInst = new PlotsOutputDisplayer();
		plotRetrievingInst.setPlotsToGUI();
*/
		
		displayingResultsInTablesForinVitro();

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
								.getSelectedSheetIndex()).getInVitroInfo()
				.getWorkSheetOutputs().getTextoutput().size(); i++) {
			inVitroResDispCreatorInst.completeTextOutputTextArea
					.append(appInfo
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
							.getInVitroInfo().getWorkSheetOutputs()
							.getTextoutput().get(i));
		}
		inVitroResDispCreatorInst.completeTextOutputTextArea.setCaretPosition(0);
	

		
		makeResultsUneditable();
		
	}

	private void displayingResultsInTablesForinVitro() throws RowsExceededException, WriteException, BiffException, IOException {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().finalParametersHorizontalDisplayTable,
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
										.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getHorizontalParametersForCA());

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().finalparametersVerticalDisplayTable,
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
										.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getVerticalParametersForCA());

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().minimizationProcessDisplayTable,
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
														.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getMinimizationProcessForCA());

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().initialParameterDisplayTable,
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
										.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getInitialParameterForCA());

		

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().conditionNumberDisplayTable,
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
										.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getConditionNumberForCA());

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().correlationMatrixDisplayTable,
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
										.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getCorrelationMatrixForCA());

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().varienceCovarienceMatrixDisplayTable,
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
										.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getVarienceCovarienceMatrixForCA());

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().summaryTableDisplayTable,
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
										.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getSummaryTableForCA());

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().predictedValueDisplayTable,
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
										.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getPredictedValueForCA());

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().partialDerivativeDisplayTable,
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
										.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getPartialDerivativeForCA());

		

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().userSettingsDisplayTable,
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
										.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getUserSettingsForCA());

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().diagnosticsDisplayTable,
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
										.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getDiagnosticsForCA());

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().historyDisplayTable,
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
										.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getHistoryForCA());

		fillTablesWithResults(
				InVitroResultDispCompCreator
				.createInVitroResDispCompInst().eigenValuesDisplayTable,
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
										.getSelectedSheetIndex()).getInVitroInfo()
						.getWorkSheetOutputs().getSpreadSheetOutputs()
						.getEigenValuesForCA());

		
	}
	
	private void fillTablesWithResults(JTable table, String[][] data) {

		for (int i = 1; i < data.length; i++) {

			((DefaultTableModel) table.getModel()).insertRow(table
					.getRowCount(), data[i]);

		}
	}

	public void makeResultsUneditable() throws RowsExceededException,
			WriteException, BiffException, IOException {

		InVitroResultDispCompCreator inVitroResDispCreatorInst = InVitroResultDispCompCreator
				.createInVitroResDispCompInst();

		inVitroResDispCreatorInst.completeTextOutputTextArea.setEditable(false);
		inVitroResDispCreatorInst.finalparametersVerticalDisplayTable
				.setEnabled(false);
		/*
		 * caResDispCreatorInst.finalParametersHorizontalDisplayTable
		 * .setEnabled(false); caResDispCreatorInst.initialParameterDisplayTable
		 * .setEnabled(false);
		 * caResDispCreatorInst.minimizationProcessDisplayTable
		 * .setEnabled(false); caResDispCreatorInst.dosingDisplayTable
		 * .setEnabled(false);
		 * caResDispCreatorInst.correlationMatrixDisplayTable
		 * .setEnabled(false); caResDispCreatorInst.eigenValuesDisplayTable
		 * .setEnabled(false); caResDispCreatorInst.conditionNumberDisplayTable
		 * .setEnabled(false);
		 */
		inVitroResDispCreatorInst.predictedValueDisplayTable.setEnabled(false);
		/*
		 * caResDispCreatorInst.varienceCovarienceMatrixDisplayTable
		 * .setEnabled(false);
		 */

		inVitroResDispCreatorInst.summaryTableDisplayTable.setEnabled(false);
		/*
		 * caResDispCreatorInst.diagnosticsDisplayTable .setEnabled(false);
		 * caResDispCreatorInst.partialDerivativeDisplayTable
		 * .setEnabled(false);
		 * caResDispCreatorInst.secondaryParameterDisplayTable
		 * .setEnabled(false);
		 * caResDispCreatorInst.nonTransposedSecondaryParameterDisplayTable
		 * .setEnabled(false); caResDispCreatorInst.historyDisplayTable
		 * .setEnabled(false);
		 */

	}
}
