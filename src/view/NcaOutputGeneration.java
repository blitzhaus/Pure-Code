package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.AttributedString;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.apache.commons.lang.StringUtils;
import org.jfree.chart.JFreeChart;

import Common.DataManipulationFunctions;
import Common.SerializeTestCases;
import Common.SystemTime;
import Common.ThousandsSeperator;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public abstract class NcaOutputGeneration {

	int numberOfParameters = 0;
	int NcaOutputNumberOfplotnodes;
	protected int parameterValueIndex = 0;
	protected int seValueIndex = 0;
	protected String filePathForHorizontalHeaderValues;
	protected String filePathForProfNamesTOFillInHorzTable;
	protected ArrayList<String> headerParNamesList;
	JScrollPane finalParametersHorizontalDisplayScrollPane;

	abstract void fillHorizontalTable() throws RowsExceededException,
			WriteException, BiffException, IOException;

	abstract void fillVertTableAndTextWithRespectiveData(int j)
			throws RowsExceededException, WriteException, BiffException,
			IOException;

	public static NcaOutputGeneration NCA_OUT_GEN = null;

	public static NcaOutputGeneration createNcaOutGeneration()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NCA_OUT_GEN == null) {
			NCA_OUT_GEN = new FillTablesWithRespectiveData(
					"judt object creation");
		}
		return NCA_OUT_GEN;
	}

	public NcaOutputGeneration(String dummy) {

	}

	public NcaOutputGeneration() {

	}

	public void ncaOutputGeneration() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		remoVePreviousResults();
		populateAvailableResultsWithPlotNodes();
		createTablesToFillData();
		createSummaryDosingSubAreasTables();
		populateTablesWithResultValuesFromServer();
		
		setOutputAsRequired();
		
		MakeCopyOfOutputsIntoExportToPdfInfo();

		//SerializeTestCases.createSerTestCasesInst().serializeNcaObjects();
	}


	

	
	RemoveUnnecessaryValuesFromTables remUnnecessaryFromTablesInst;
	private void setOutputAsRequired() throws RowsExceededException, WriteException, BiffException, IOException {
		removeEmptyColumnsInsummaryTableSeperateLambZIndicator();
		fixSortVarColumnInHorzTable();
		setTextCursorToStartPosition();
		
		
		RemoveUnnecessaryValuesFromTables.createRmNullFromTabInst().removeNullValuesFromAllTables();
		RemoveUnnecessaryValuesFromTables.createRmNullFromTabInst().removeMissingValuesFromAllTables();
		RemoveUnnecessaryValuesFromTables.createRmNullFromTabInst().removeNegPiFromAllOutput();
		NcaOutputModufyUsingPreferredNames.createInst().modifyOutputUsingPreferredParamNames();
		
	}

	NcaResultDispComp ncaResDispInst;
	private void setTextCursorToStartPosition() throws RowsExceededException, WriteException, BiffException, IOException {
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea.setCaretPosition(0);
		
	}

	private void clearTextArea() throws RowsExceededException, WriteException, BiffException, IOException {
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea.setText("");
		
	}

	private void fixSortVarColumnInHorzTable() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int noSort = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getProcessingInputs()
								.getMappingDataObj().getSortVariablesListVector().size();
		
		
		FixedColumnTable.createFixedColumnTableInst().FixedColumnTable(noSort, finalParametersHorizontalDisplayScrollPane);
		
	}

	private void removeEmptyColumnsInsummaryTableSeperateLambZIndicator()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		TableColumnModel tm = NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
				.getColumnModel();
		// add a new column
		TableColumn tcc = new TableColumn();
		
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getisSparseSelected() == false) {

			tm.removeColumn(tm.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size() + 1));

			tm.removeColumn(tm.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size() + 2));
			
			tcc.setModelIndex(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size() + 1);
			

			tcc.setHeaderValue("Indicator");
			tm.addColumn(tcc);
			tm.moveColumn(tm.getColumnCount() - 1, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size() + 1);

			int timeColumnIndex = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();

			for (int i = 0; i < NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
					.getRowCount(); i++) {
				try{
					
					if (NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
							.getValueAt(i, timeColumnIndex).toString().contains("#")) {
						

						// remove the hash from the time column
						String hashRemovedValue = NcaResultDispComp
								.createNcaResDispCompInst().resultsSummaryTable
								.getValueAt(i, timeColumnIndex).toString().replace("#",
										"");

						NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
								.setValueAt(hashRemovedValue, i, timeColumnIndex);

						// insert the hash in the new column
						NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
								.setValueAt(
										"#",
										i,
										(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getWorkSheetObjectsAL()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())
												.getNcaInfo().getProcessingInputs()
												.getMappingDataObj()
												.getSortVariablesListVector().size() + 1));

					} else {
						NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
								.setValueAt(
										"",
										i,
										(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getWorkSheetObjectsAL()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())
												.getNcaInfo().getProcessingInputs()
												.getMappingDataObj()
												.getSortVariablesListVector().size() + 1));
					}
				
				} catch(Exception e){
					
				}
			}

		} else {
			
		}

	}

	private void MakeCopyOfOutputsIntoExportToPdfInfo()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		addTextToExportToPdf();
		addSpreadSheetsToExportToPdf();
		addPlotsToExportToPdf();

	}

	private void addSpreadSheetsToExportToPdf() throws RowsExceededException,
			WriteException, BiffException, IOException {
		addVerticalParameters();
		addHorzParameters();
		addSummaryTable();
		addDosingTable();
		addSubAreasTable();

	}

	private void addSubAreasTable() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int rows = NcaResultDispComp.createNcaResDispCompInst().resultsSubAreasTable
				.getRowCount();
		int columns = NcaResultDispComp.createNcaResDispCompInst().resultsSubAreasTable
				.getColumnCount();
		String[][] data = new String[rows + 1][columns];

		// add headers as ist row
		TableColumnModel tcm = NcaResultDispComp.createNcaResDispCompInst().resultsSubAreasTable
				.getColumnModel();

		for (int i = 0; i < data[0].length; i++) {
			TableColumn tc = tcm.getColumn(i);
			data[0][i] = tc.getHeaderValue().toString();
		}

		// rest of the table data into the remaining rows
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				try {
					data[i][j] = NcaResultDispComp.createNcaResDispCompInst().resultsSubAreasTable
							.getValueAt(i - 1, j).toString();
				} catch (Exception e) {
					data[i][j] = "";
				}

			}
		}

		// set the data to appInfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getExpToPdf().setSubAreasTable(data);

	}

	private void addDosingTable() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int rows = NcaResultDispComp.createNcaResDispCompInst().resultsDosingTable
				.getRowCount();
		int columns = NcaResultDispComp.createNcaResDispCompInst().resultsDosingTable
				.getColumnCount();
		String[][] data = new String[rows + 1][columns];

		// add headers as ist row
		TableColumnModel tcm = NcaResultDispComp.createNcaResDispCompInst().resultsDosingTable
				.getColumnModel();

		for (int i = 0; i < data[0].length; i++) {
			TableColumn tc = tcm.getColumn(i);
			data[0][i] = tc.getHeaderValue().toString();
		}

		// rest of the table data into the remaining rows
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = NcaResultDispComp.createNcaResDispCompInst().resultsDosingTable
						.getValueAt(i - 1, j).toString();
			}
		}

		// set the data to appInfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getExpToPdf().setDosingTable(data);

	}

	private void addSummaryTable() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int rows = NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
				.getRowCount();
		int columns = NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
				.getColumnCount();
		String[][] data = new String[rows + 1][columns];

		// add headers as ist row
		TableColumnModel tcm = NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
				.getColumnModel();

		for (int i = 0; i < data[0].length; i++) {
			TableColumn tc = tcm.getColumn(i);
			data[0][i] = tc.getHeaderValue().toString();
		}

		// rest of the table data into the remaining rows
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {

				try {
					data[i][j] = NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
							.getValueAt(i - 1, j).toString();
				} catch (Exception e) {
					data[i][j] = "";
				}

			}
		}

		// set the data to appInfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getExpToPdf().setSummaryTable(data);
	}

	private void addHorzParameters() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int rows = NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
				.getRowCount();
		int columns = NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
				.getColumnCount();
		String[][] data = new String[rows + 1][columns];

		// add headers as ist row
		TableColumnModel tcm = NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
				.getColumnModel();

		for (int i = 0; i < data[0].length; i++) {
			TableColumn tc = tcm.getColumn(i);
			data[0][i] = tc.getHeaderValue().toString();
		}

		// rest of the table data into the remaining rows
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
						.getValueAt(i - 1, j).toString();
			}
		}

		// set the data to appInfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getExpToPdf().setHorzParameters(data);
	}

	private void addVerticalParameters() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int rows = NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable
				.getRowCount();
		int columns = NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable
				.getColumnCount();
		String[][] data = new String[rows + 1][columns];

		// add headers as ist row
		TableColumnModel tcm = NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable
				.getColumnModel();

		for (int i = 0; i < columns; i++) {
			TableColumn tc = tcm.getColumn(i);
			data[0][i] = tc.getHeaderValue().toString();
		}

		// rest of the table data into the remaining rows
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				
				try {
					data[i][j] = NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable
							.getValueAt(i - 1, j).toString();
				} catch (Exception e) {
					data[i][j] = "";
				}

			}
		}

		// set the data to appInfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getExpToPdf()
				.setVerticalParameters(data);
	}

	private void addPlotsToExportToPdf() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ArrayList<JFreeChart> linear = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLinearPlots();
		ArrayList<JFreeChart> log = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLogplots();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getExpToPdf().setLinearPlots(linear);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getExpToPdf().setLogPlots(log);

	}

	private void addTextToExportToPdf() throws RowsExceededException, WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String[] textLines = NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea.getText().split("\\n");
		ArrayList<String> text = new ArrayList<String>();
		
		for(int i=0;i<textLines.length;i++){
			text.add(textLines[i]);
		}
			
			
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getExpToPdf().setText(text);

	}

	private void populateTablesWithResultValuesFromServer()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		NcaOutputGeneration ncaOutGen = new FillTablesWithRespectiveData(
				"just object creation");
		ncaOutGen.fillHorizontalTable();
		int numberOfProfiles = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject();
		parameterValueIndex = 0;
		NcaParameterFileDetermination.createNcaParDetInst()
				.fillTheUnitsHashMap();
		parameterValueIndex = 0;
		seValueIndex = 0;
		for (int i = 0; i < numberOfProfiles; i++) {

			ncaOutGen.fillVertTableAndTextWithRespectiveData(i);
		}
	}

	void remoVePreviousResults() throws RowsExceededException, WriteException,
			BiffException, IOException {

		DefaultTreeModel model = (DefaultTreeModel) NcaResultsAvailableComp
				.createNcaResAvailCompInst().availableOutputsTree.getModel();
		TreePath plotsPath = NcaResultsAvailableComp
				.createNcaResAvailCompInst().availableOutputsTree.getNextMatch(
				"plots", 0, Position.Bias.Forward);
		if (plotsPath == null) {
			
		} else {
			MutableTreeNode mNode = (MutableTreeNode) plotsPath
					.getLastPathComponent();
			model.removeNodeFromParent(mNode);
			for (int i = 0; i < NcaResultDispComp.createNcaResDispCompInst().outputPlotsArraylist
					.size(); i++) {
				NcaResultDispComp.createNcaResDispCompInst().outputPlotsArraylist
						.get(i).dispose();
			}

			for (int i = 0; i < NcaResultDispComp.createNcaResDispCompInst().outputPlotsLogViewsArrayList
					.size(); i++) {
				NcaResultDispComp.createNcaResDispCompInst().outputPlotsLogViewsArrayList
						.get(i).dispose();
			}

			
		}
		
		clearTextArea();
		
	}

	void printSettings(String tempS, Vector<String> textOutputVector,
			ApplicationInfo appInfo, int j, String timeUnit,
			String normalizationUnit, String singleDoseUnit) {
		tempS = "";
		tempS = tempS.format("%s", "Settings");
		AttributedString as = new AttributedString(tempS);
		as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, 1,
				5);

		textOutputVector.add(tempS);

		tempS = "";
		tempS = tempS.format("%s%s ", "Model:  ", appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getModelItem());
		textOutputVector.add(tempS);

		tempS = "";
		tempS = tempS.format("%s%s%s", "Route: ", appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getRootAdministrationItem(), " Administration");
		textOutputVector.add(tempS);

		tempS = "";
		tempS = tempS.format("%s %s", "Number of nonmissing observations: ",
				"0");
		textOutputVector.add(tempS);

		tempS = "";
		tempS = tempS
				.format(
						"%s %s %s",
						"Time of Dosing: ",
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo()
								.getProcessingInputs()
								.getDosingDataObj()
								.getDosingValueAt(
										j,
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getWorkSheetObjectsAL()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())
												.getNcaInfo()
												.getProcessingInputs()
												.getMappingDataObj()
												.getSortVariablesListVector()
												.size() + 1), timeUnit);
		textOutputVector.add(tempS);

		tempS = "";
		tempS = tempS
				.format(
						"%s%s%s",
						"Dose Administered: ",
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo()
								.getProcessingInputs()
								.getDosingDataObj()
								.getDosingValueAt(
										j,
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getWorkSheetObjectsAL()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())
												.getNcaInfo()
												.getProcessingInputs()
												.getMappingDataObj()
												.getSortVariablesListVector()
												.size()), singleDoseUnit + "/"
								+ normalizationUnit);
		textOutputVector.add(tempS);

		tempS = "";
		tempS = tempS
				.format("%s%s", "Calculation method:   ", appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getCalculationItem());
		textOutputVector.add(tempS);

	}

	void printDataTime(String tempS, Vector<String> textOutputVector) {

		String dateTime = SystemTime.createSystemTimeInstance().systemTime();
		String dT[] = dateTime.split("/");
		tempS = "";
		tempS = tempS.format("%130s %15s", "Date: ", dT[0]);
		textOutputVector.add(tempS);

		tempS = "";
		tempS = tempS.format("%130s %15s", "Time: ", dT[1]);
		textOutputVector.add(tempS);

	}

	@SuppressWarnings("static-access")
	void printProfileInfo(Vector<String> textOutputVector,
			ApplicationInfo appInfo, int j, int sum, String tempS) {

		// for text output, appending the profile information

		tempS = tempS.format("%-25s%s", "Imported Work Sheet: ", appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getSheetName());
		textOutputVector.add(tempS);

		tempS = "";
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {

			tempS = tempS
					+ tempS
							.format(
									"%s",
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector()
											.get(i)
											+ " = "
											+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getWorkSheetObjectsAL()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																	.get(
																			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																	.getSelectedSheetIndex())
													.getNcaInfo()
													.getProcessingInputs()
													.getProfileInfoObj()
													.getDataSortVariables()[j][i]);
			textOutputVector.add(tempS);
			tempS = "";

		}

	}

	ArrayList<String> fillListFromFile(String string) {

		ArrayList<String> tempList = new ArrayList<String>();
		try {
			FileInputStream fstream = new FileInputStream(string);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			try {
				while ((strLine = br.readLine()) != null) {
					// Print the content on the console
					tempList.add(strLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Close the input stream
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//tempList = checkForPreferredUnits(tempList);
		
		return tempList;
	}

	private ArrayList<String> checkForPreferredUnits(ArrayList<String> tempList) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Vector<String> preferred = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterNamesObj().getPrefferedParameternames();
		
		for(int i=0;i<preferred.size();i++){
			if(preferred.get(i).equals(tempList.get(i))){
				
			} else {
				tempList.set(i, preferred.get(i));
			}
		}
		
		return tempList;
	}

	boolean checkIfTauGreaterThanZeroForAllProfiles()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		boolean tauPresent = false;
		for (int i = 0; i < NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
				.getRowCount(); i++) {

			String cellContents = (String) NcaDosingDispGui
					.createNcaDosingGuiInst().tableForDosing
					.getModel()
					.getValueAt(
							i,
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() + 2);
			try {
				int integerContents = Integer.parseInt(cellContents);
				if (integerContents > 0) {
					tauPresent = true;
				}
			} catch (Exception e) {

				return false;

			}

		}

		return tauPresent;
	}

	boolean checkIfTauPresentForAtleastOneProfile()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		for (int i = 0; i < NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
				.getRowCount(); i++) {
			boolean tauPresent = false;
			String cellContents = (String) NcaDosingDispGui
					.createNcaDosingGuiInst().tableForDosing
					.getModel()
					.getValueAt(
							i,
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() + 2);
			try {
				int integerContents = Integer.parseInt(cellContents);
				if (integerContents > 0) {
					return true;
				}

			} catch (Exception e) {
				continue;
			}
		}
		return false;
	}

	boolean checkIfNoprofileHasTau() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		for (int i = 0; i < NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
				.getRowCount(); i++) {
			boolean tauPresent = false;
			String cellContents = (String) NcaDosingDispGui
					.createNcaDosingGuiInst().tableForDosing
					.getModel()
					.getValueAt(
							i,
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() + 2);
			try {
				int integerContents = Integer.parseInt(cellContents);
				if (integerContents > 0) {
					return false;
				}
			} catch (Exception e) {
				continue;
			}

		}
		return true;
	}

	void createTablesToFillData() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayInternalFrame
				.getContentPane().removeAll();
		NcaResultDispComp.createNcaResDispCompInst().finalParametersinternalFrame
				.getContentPane().removeAll();
		NcaResultDispComp.createNcaResDispCompInst().summaryTableIF
				.getContentPane().removeAll();

		// this array list is used to select the profile number in the available
		// components tree
		// for showing the corresponding graph within the lambda z screen.
		// profileNamesUsedForProfileNumberDetermination = new
		// ArrayList<String>();

		NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable = new JTable(
				0,
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size()
						+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo().getProcessingInputs()
								.getMappingDataObj()
								.getCarryAlongVariablesListVector().size() + 3);

		// NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable.setEditingColumn(false);
		NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable
				.setPreferredScrollableViewportSize(new Dimension(
						NcaResultDispComp.createNcaResDispCompInst().displayResultsInternalFrame
								.getWidth(),
						NcaResultDispComp.createNcaResDispCompInst().displayResultsInternalFrame
								.getHeight()));
		NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable
				.setFillsViewportHeight(true);
		NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable
				.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable
				.setRowSelectionAllowed(true);
		NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable
				.setCellSelectionEnabled(true);
		NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable
				.setColumnSelectionAllowed(true);
		NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable
				.setSelectionBackground(new Color(238, 238, 224));
		JTableHeader finalParametersVerticalDisplayTableHeader = NcaResultDispComp
				.createNcaResDispCompInst().finalparametersVerticalDisplayTable
				.getTableHeader();
		NcaResultDispComp.createNcaResDispCompInst().finalParametersVerticalDisplayTableModel = NcaResultDispComp
				.createNcaResDispCompInst().finalparametersVerticalDisplayTable
				.getColumnModel();
		TableColumn tc = null;
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			tc = NcaResultDispComp.createNcaResDispCompInst().finalParametersVerticalDisplayTableModel
					.getColumn(i);
			tc.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i));
		}
		tc = NcaResultDispComp.createNcaResDispCompInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() - 1 + 1);
		tc.setHeaderValue("Parameter");

		tc = NcaResultDispComp.createNcaResDispCompInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() - 1 + 2);
		tc.setHeaderValue("units");
		tc = NcaResultDispComp.createNcaResDispCompInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() - 1 + 3);
		tc.setHeaderValue("Estimate");

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable);
		finalParametersVerticalDisplayScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		finalParametersVerticalDisplayScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						NcaResultDispComp.createNcaResDispCompInst().finalParametersinternalFrame
								.getWidth(),
						NcaResultDispComp.createNcaResDispCompInst().finalParametersinternalFrame
								.getHeight()));
		NcaResultDispComp.createNcaResDispCompInst().finalParametersinternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getModelType() == 0) {

			if (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
					.getSelectedIndex() == 0) {// PO Data
				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getisSparseSelected() == true) {// sparse
					// data
					if (checkIfTauGreaterThanZeroForAllProfiles() == true) {
						System.out
								.println("within the checkIfTauGreaterThanZeroForAllProfiles()");
						numberOfParameters = 36;
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Sparse PO All have Tau.txt";
					} else if (checkIfTauPresentForAtleastOneProfile() == true) {
						System.out
								.println("within the checkIfTauPresentForAtleastOneProfile()");
						numberOfParameters = 46;
					} else if (checkIfNoprofileHasTau() == true) {
						System.out
								.println("within the checkIfNoprofileHasTau()");
						numberOfParameters = 37;
						NcaOutputGeneration.createNcaOutGeneration().filePathForProfNamesTOFillInHorzTable = "OP Plasma Sparse PO No tau.txt";
					}

					filePathForHorizontalHeaderValues = "OP Plasma PO Sparse atleast one has no tau.txt";
				} else {// serial data

					if (checkIfTauGreaterThanZeroForAllProfiles() == true) {
						System.out
								.println("within the checkIfTauGreaterThanZeroForAllProfiles()");
						numberOfParameters = 33;
						// = "OP Plasma PO Serial All have Tau.txt";
					} else if (checkIfTauPresentForAtleastOneProfile() == true) {
						System.out
								.println("within the checkIfTauPresentForAtleastOneProfile()");
						numberOfParameters = 43;
					} else if (checkIfNoprofileHasTau() == true) {
						System.out
								.println("within the checkIfNoprofileHasTau()");
						numberOfParameters = 34;
						// = "OP Plasma PO Serial No Tau.txt";
					}
					filePathForHorizontalHeaderValues = "OP Plasma PO Serial Atleast one profile has no tau.txt";

				}

			} else if (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
					.getSelectedIndex() == 1) {
				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getisSparseSelected() == true) {// sparse
					// data
					if (checkIfTauGreaterThanZeroForAllProfiles() == true) {
						System.out
								.println("within the checkIfTauGreaterThanZeroForAllProfiles()");
						numberOfParameters = 40;
						// = "OP Plasma IV Sparse All have TAU.txt";
					} else if (checkIfTauPresentForAtleastOneProfile() == true) {
						System.out
								.println("within the checkIfTauPresentForAtleastOneProfile()");
						numberOfParameters = 50;
					} else if (checkIfNoprofileHasTau() == true) {
						System.out
								.println("within the checkIfNoprofileHasTau()");
						numberOfParameters = 41;
						// = "OP Plasma Sparse IV no tau.txt";
					}

					filePathForHorizontalHeaderValues = "OP Plasma IV Sparse atleast one profile has no tau.txt";
				} else {// serial data

					if (checkIfTauGreaterThanZeroForAllProfiles() == true) {
						System.out
								.println("within the checkIfTauGreaterThanZeroForAllProfiles()");
						numberOfParameters = 37;
						// = "OP Plasma Serial IV all profiles has tau.txt";
					} else if (checkIfTauPresentForAtleastOneProfile() == true) {
						System.out
								.println("within the checkIfTauPresentForAtleastOneProfile()");
						numberOfParameters = 47;
					} else if (checkIfNoprofileHasTau() == true) {
						System.out
								.println("within the checkIfNoprofileHasTau()");
						numberOfParameters = 38;
						// = "OPSerialIvPlasmaSingleDoseParameters.txt";
					}

					filePathForHorizontalHeaderValues = "OPSerialIVPlasma atleast 1 profile has tau & atlst 1 prof has no tau.txt";
				}

			} else if (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
					.getSelectedIndex() == 2) {
				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getisSparseSelected() == true) {// sparse
					// data
					if (checkIfTauGreaterThanZeroForAllProfiles() == true) {
						System.out
								.println("within the checkIfTauGreaterThanZeroForAllProfiles()");
						numberOfParameters = 37;
						// = "OP Plasma Infusion Sparse all have tau.txt";
					} else if (checkIfTauPresentForAtleastOneProfile() == true) {
						System.out
								.println("within the checkIfTauPresentForAtleastOneProfile()");
						numberOfParameters = 47;
					} else if (checkIfNoprofileHasTau() == true) {
						System.out
								.println("within the checkIfNoprofileHasTau()");
						numberOfParameters = 38;
						// = "OP Plasma Infusion Sparse No Tau.txt";
					}

					filePathForHorizontalHeaderValues = "OP Plasma Infusion Sparse atleast one has tau.txt";
				} else {// serial data
					if (checkIfTauGreaterThanZeroForAllProfiles() == true) {
						System.out
								.println("within the checkIfTauGreaterThanZeroForAllProfiles()");
						numberOfParameters = 34;
						// = "OP Plasma Infusion Serial all have tau.txt";
					} else if (checkIfTauPresentForAtleastOneProfile() == true) {
						System.out
								.println("within the checkIfTauPresentForAtleastOneProfile()");
						numberOfParameters = 44;
					} else if (checkIfNoprofileHasTau() == true) {
						System.out
								.println("within the checkIfNoprofileHasTau()");
						numberOfParameters = 33;
						// = "OP Plasma Infusion Serial No tau.txt";
					}
				}

				filePathForHorizontalHeaderValues = "OP Plasma Infusion Serial atleast one has no tau.txt";

			} else if (NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
					.getSelectedIndex() == 3) {// dosing defined

				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getisSparseSelected() == true) {

					numberOfParameters = 51;
					filePathForHorizontalHeaderValues = "Plasma Sparse DD Parameters.txt";

				} else {

					numberOfParameters = 48;
					filePathForHorizontalHeaderValues = "Plasma Serial DD Parameters.txt";

				}
			}
		} else if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getModelType() == 1) {// number
			// of
			// parameters in
			// urine data are
			// independent of
			// the root of
			// administration
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.getisSparseSelected() == true) {
				numberOfParameters = 25;
				// = "OP Urine Sparse IVPOInfusion Parameters.txt";
				filePathForHorizontalHeaderValues = "OP Urine Sparse IVPOInfusion Parameters.txt";
			} else {
				numberOfParameters = 22;
				// = "OP Urine Serial IVPOnfusion Parameters.txt";
				filePathForHorizontalHeaderValues = "OP Urine Serial IVPOnfusion Parameters.txt";
			}

		}

		finalParametersHorizontalTableCreation(tc);

		summaryTableCreation(tc);

	}

	void summaryTableCreation(TableColumn tc) throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// summary table
		NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable = new JTable(
				0, 9 + appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size());
		TableColumnModel tableColumnModel = NcaResultDispComp
				.createNcaResDispCompInst().resultsSummaryTable
				.getColumnModel();
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			tc = tableColumnModel.getColumn(i);
			tc.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i));
		}
		NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
				.setRowSelectionAllowed(true);
		NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
				.setCellSelectionEnabled(true);
		NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
				.setColumnSelectionAllowed(true);
		NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
				.setFillsViewportHeight(true);
		NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
				.setSelectionBackground(new Color(238, 238, 234));
		NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
				.setPreferredScrollableViewportSize(new Dimension(
						NcaResultDispComp.createNcaResDispCompInst().displayResultsInternalFrame
								.getWidth(),
						NcaResultDispComp.createNcaResDispCompInst().displayResultsInternalFrame
								.getHeight()));// displayResultsInternalFrame.getWidth(),displayResultsInternalFrame.getHeight()

		JScrollPane resultsSummaryTableSP = new JScrollPane(NcaResultDispComp
				.createNcaResDispCompInst().resultsSummaryTable);
		resultsSummaryTableSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		resultsSummaryTableSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		resultsSummaryTableSP.setPreferredSize(new Dimension(NcaResultDispComp
				.createNcaResDispCompInst().summaryTableIF.getWidth(),
				NcaResultDispComp.createNcaResDispCompInst().summaryTableIF
						.getHeight()));// summaryTableIF.getWidth(),summaryTableIF.getHeight()
		NcaResultDispComp.createNcaResDispCompInst().summaryTableIF
				.getContentPane().setLayout(new GridLayout(1, 1));
		NcaResultDispComp.createNcaResDispCompInst().summaryTableIF
				.getContentPane().add(resultsSummaryTableSP);
		NcaResultDispComp.createNcaResDispCompInst().summaryTableIF
				.getContentPane().validate();

	}

	private void finalParametersHorizontalTableCreation(TableColumn tc)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable = new JTable(
				0, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size()+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).getNcaInfo()
								.getProcessingInputs().getMappingDataObj().getCarryAlongVariablesListVector().size()+ numberOfParameters);
		NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
				.setSelectionBackground(new Color(238, 238, 234));
		NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
				.setRowSelectionAllowed(true);
		NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
				.setCellSelectionEnabled(true);
		NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
				.setColumnSelectionAllowed(true);
		NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
				.setPreferredScrollableViewportSize(new Dimension(
						NcaResultDispComp.createNcaResDispCompInst().displayResultsInternalFrame
								.getWidth(),
						NcaResultDispComp.createNcaResDispCompInst().displayResultsInternalFrame
								.getHeight()));
		NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
				.setFillsViewportHeight(true);

		NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
				.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTableHeader = NcaResultDispComp
				.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
				.getTableHeader();
		NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTableModel = NcaResultDispComp
				.createNcaResDispCompInst().finalParametersHorizontalDisplayTable
				.getColumnModel();

		setTheHeaderNamesForHorizonParameterTable(tc);

		finalParametersHorizontalDisplayScrollPane = new JScrollPane(
				NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable);
		finalParametersHorizontalDisplayScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		finalParametersHorizontalDisplayScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		finalParametersHorizontalDisplayScrollPane
				.setPreferredSize(new Dimension(
						NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayInternalFrame
								.getWidth(),
						NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayInternalFrame
								.getHeight()));
		NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayInternalFrame
				.getContentPane().add(
						finalParametersHorizontalDisplayScrollPane);

		NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayInternalFrame
				.getContentPane().validate();
		NcaResultDispComp.createNcaResDispCompInst().finalParametersinternalFrame
				.getContentPane().validate();

	}

	private void setTheHeaderNamesForHorizonParameterTable(TableColumn tc)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() == 0) {
			fillParameterNamesAsHeader(tc);
		} else {
			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size(); i++) {
				tc = NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTableModel
						.getColumn(i);
				tc.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().get(i));

			}
			// fill the parameterNamesAsHeaders
			fillParameterNamesAsHeader(tc);
		}

	}

	private void fillParameterNamesAsHeader(TableColumn tc)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		headerParNamesList = new ArrayList<String>();
		headerParNamesList = fillListFromFile(filePathForHorizontalHeaderValues);
		int sortVar = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();
		int carryAlongSize = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getCarryAlongVariablesListVector().size();
		for (int i = 0; i < numberOfParameters; i++) {
			tc = NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTableModel
					.getColumn((sortVar + carryAlongSize + i));
			tc.setHeaderValue(headerParNamesList.get(i));
		}

	}

	void populateAvailableResultsWithPlotNodes() throws RowsExceededException,
			WriteException, BiffException, IOException {

		NcaOptionsGui.createNcaOptionsInstance().viewLable.setEnabled(true);
		NcaOptionsGui.createNcaOptionsInstance().viewsCombo.setEnabled(true);
		populateOutputTreeWithnodes();

	}
	
	NcaResultsAvailableComp ncaResAvailCompInst;

	private void populateOutputTreeWithnodes() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DefaultTreeModel model = (DefaultTreeModel) NcaResultsAvailableComp
				.createNcaResAvailCompInst().availableOutputsTree.getModel();

		DefaultMutableTreeNode plots = new DefaultMutableTreeNode("Plots");
		NcaResultsAvailableComp.createNcaResAvailCompInst().availableOutputs
				.add(plots);
		NcaOutputNumberOfplotnodes = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject();
		for (int i = 0; i < NcaOutputNumberOfplotnodes; i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"Plot " + (i + 1));
			plots.add(plotNode);
		}
		
;
		NcaResultsAvailableComp.createNcaResAvailCompInst().isModelReload = true;
		model.reload();
		NcaResultsAvailableComp.createNcaResAvailCompInst().isModelReload = false;

	}

	double round(double value, int decimalPlace) {
		double power_of_ten = 1;
		while (decimalPlace-- > 0) {
			power_of_ten *= 10.0;
		}
		return Math.round(value * power_of_ten) / power_of_ten;
	}

	public void createSummaryDosingSubAreasTables()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		// removing the previous results
		NcaResultDispComp.createNcaResDispCompInst().dosingTableIF
				.getContentPane().removeAll();
		NcaResultDispComp.createNcaResDispCompInst().SubAreasIF
				.getContentPane().removeAll();

		// fill the dosing table
		NcaResultDispComp.createNcaResDispCompInst().resultsDosingTable = new JTable(
				0, NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.getColumnCount());
		NcaResultDispComp.createNcaResDispCompInst().resultsDosingTable
				.setEnabled(false);
		NcaResultDispComp.createNcaResDispCompInst().resultsDosingTable
				.setRowSelectionAllowed(true);
		NcaResultDispComp.createNcaResDispCompInst().resultsDosingTable
				.setCellSelectionEnabled(true);
		NcaResultDispComp.createNcaResDispCompInst().resultsDosingTable
				.setColumnSelectionAllowed(true);
		for (int i = 0; i < NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
				.getRowCount(); i++) {
			String[] s = new String[NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
					.getColumnCount()];
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			int rootOfAdmin = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.getRootOfAdmistration();

			int lastColumn = NcaResultDispComp.createNcaResDispCompInst().resultsDosingTable
					.getColumnCount() - 1;
			int lastButOneColumn = NcaResultDispComp.createNcaResDispCompInst().resultsDosingTable
					.getColumnCount() - 2;
			int nextToLastButOneColumn = NcaResultDispComp
					.createNcaResDispCompInst().resultsDosingTable
					.getColumnCount() - 3;

			for (int j = 0; j < s.length; j++) {
				if (NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.getValueAt(i, j) != null) {
					if ((rootOfAdmin == 3)
							&& ((j == lastColumn) || (j == lastButOneColumn) || j == nextToLastButOneColumn)) {

						int userDefinedRoot = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo().getProcessingInputs()
								.getProfileInfoObj()
								.getRouteOfAdminForDosingDefined()[i];
						if ((userDefinedRoot == 0)
								&& (j == nextToLastButOneColumn)) {
							s[j] = "true";
						} else if ((userDefinedRoot == 1)
								&& (j == lastButOneColumn)) {
							s[j] = "true";
						} else if ((userDefinedRoot == 2) && (j == lastColumn)) {
							s[j] = "true";
						}
					} else {
						s[j] = (String) (NcaDosingDispGui
								.createNcaDosingGuiInst().tableForDosing
								.getValueAt(i, j) + "");
					}

				} else {
					s[j] = "";
				}

			}
			((DefaultTableModel) NcaResultDispComp.createNcaResDispCompInst().resultsDosingTable
					.getModel()).insertRow(NcaResultDispComp
					.createNcaResDispCompInst().resultsDosingTable
					.getRowCount(), s);
		}

		TableColumnModel tableForDosingtm = NcaDosingDispGui
				.createNcaDosingGuiInst().tableForDosing.getColumnModel();
		TableColumnModel resultsDosingTabletm = NcaResultDispComp
				.createNcaResDispCompInst().resultsDosingTable.getColumnModel();
		TableColumn tableForDosingtc = new TableColumn();
		TableColumn resultsDosingtc = new TableColumn();
		for (int i = 0; i < NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
				.getColumnCount(); i++) {
			tableForDosingtc = tableForDosingtm.getColumn(i);
			resultsDosingtc = resultsDosingTabletm.getColumn(i);
			resultsDosingtc.setHeaderValue(tableForDosingtc.getHeaderValue());
		}

		JScrollPane resultsDosingTableSP = new JScrollPane(NcaResultDispComp
				.createNcaResDispCompInst().resultsDosingTable);
		resultsDosingTableSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		resultsDosingTableSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		NcaResultDispComp.createNcaResDispCompInst().dosingTableIF
				.getContentPane().add(resultsDosingTableSP);

		// fill the sub areas table
		int columnCount = NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().tableForPartialArea
				.getColumnCount() + 1;
		NcaResultDispComp.createNcaResDispCompInst().resultsSubAreasTable = new JTable(
				0, columnCount);
		NcaResultDispComp.createNcaResDispCompInst().resultsSubAreasTable
				.setEnabled(true);
		NcaResultDispComp.createNcaResDispCompInst().resultsSubAreasTable
				.setRowSelectionAllowed(true);
		NcaResultDispComp.createNcaResDispCompInst().resultsSubAreasTable
				.setCellSelectionEnabled(true);
		NcaResultDispComp.createNcaResDispCompInst().resultsSubAreasTable
				.setColumnSelectionAllowed(true);
		for (int i = 0; i < NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().tableForPartialArea
				.getRowCount(); i++) {
			String[] s = new String[columnCount];
			for (int j = 0; j < s.length - 1; j++) {
				if (NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().tableForPartialArea
						.getValueAt(i, j) != null) {
					s[j] = (String) (NcaSubAreasDispGui
							.createNcaSubAreasDispGuiInst().tableForPartialArea
							.getValueAt(i, j) + "");
				} else {
					s[j] = "";
				}

			}

			// s[s.length-1] = NCA.partialAreaValue[i]+"";
			((DefaultTableModel) NcaResultDispComp.createNcaResDispCompInst().resultsSubAreasTable
					.getModel()).insertRow(NcaResultDispComp
					.createNcaResDispCompInst().resultsSubAreasTable
					.getRowCount(), s);

		}

		TableColumnModel tableForsubtm = NcaSubAreasDispGui
				.createNcaSubAreasDispGuiInst().tableForPartialArea
				.getColumnModel();
		TableColumnModel resultssubTabletm = NcaResultDispComp
				.createNcaResDispCompInst().resultsSubAreasTable
				.getColumnModel();
		TableColumn tableForsubtc = new TableColumn();
		TableColumn resultssubtc = new TableColumn();
		for (int i = 0; i < NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().tableForPartialArea
				.getColumnCount(); i++) {
			tableForsubtc = tableForsubtm.getColumn(i);
			resultssubtc = resultssubTabletm.getColumn(i);
			resultssubtc.setHeaderValue(tableForsubtc.getHeaderValue());
		}

		resultssubtc = resultssubTabletm.getColumn(NcaSubAreasDispGui
				.createNcaSubAreasDispGuiInst().tableForPartialArea
				.getColumnCount());
		resultssubtc.setHeaderValue("Partial Area");

		JScrollPane resultsSubAreasSP = new JScrollPane(NcaResultDispComp
				.createNcaResDispCompInst().resultsSubAreasTable,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		NcaResultDispComp.createNcaResDispCompInst().SubAreasIF
				.getContentPane().add(resultsSubAreasSP);
	}

	void appendSummaryTableToTextAndTables(double[] Xp, double[] Yp,
			String time_unit, String conc_mass_unit, String volume_unit,
			String[] Ycalc, String[] termX, double[] AUCValue,
			double[] AUMCValue, int weightingIndex, int currentProfileNumber)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append("Summary Table" + "\r\n");
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append("------------------");
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append("\r\n");
		TableColumnModel tm = NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
				.getColumnModel();
		TableColumn tc;
		tc = tm.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size());
		tc.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName()
				+ "(" + time_unit + ")");

		tc = tm.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 1);
		tc.setHeaderValue("N");

		tc = tm.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 2);
		tc.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName());
		// NCAMainScreen.completeTextOutputTextArea.append("\t"+appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo().getProcessingInputs()
		// .getMappingDataObj().getYcolumnName());

		tc = tm.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 3);
		tc.setHeaderValue("SEM");

		tc = tm.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 4);
		tc.setHeaderValue("Predicted");
		// NCAMainScreen.completeTextOutputTextArea.append("\tPredicted ("+conc_mass_unit+"/"+volume_unit+")");

		tc = tm.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 5);
		tc.setHeaderValue("Residual(" + conc_mass_unit + "/" + volume_unit
				+ ")");

		tc = tm.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 6);
		tc.setHeaderValue("AUC");

		tc = tm.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 7);
		tc.setHeaderValue("AUMC");

		tc = tm.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 8);
		tc.setHeaderValue("Weight");

		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append(StringUtils.rightPad(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getxColumnName(), 6)
						+ "\t"
						+ StringUtils
								.rightPad(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getWorkSheetObjectsAL()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())
												.getNcaInfo()
												.getProcessingInputs()
												.getMappingDataObj()
												.getYcolumnName(), 15)
						+ "\t"
						+ StringUtils.rightPad("Predicted", 15)
						+ "\t"
						+ StringUtils.rightPad("Weight", 15) + "\r\n");
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append(StringUtils.rightPad("(" + time_unit + ")", 6)
						+ "\t"
						+ StringUtils.rightPad("(" + conc_mass_unit + "/"
								+ volume_unit + ")", 15)
						+ "\t"
						+ StringUtils.rightPad("(" + conc_mass_unit + "/"
								+ volume_unit + ")", 15) + "\t"
						+ StringUtils.rightPad("", 15) + "\r\n");

		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append("------------------------------------------------------------------------------------");
		NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
				.append("\r\n\r\n");
		String[] summary = new String[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 9];
		for (int index = 0; index < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); index++) {
			summary[index] = DetermineNumberOfSubject.createDetNoSubInst().dataSortVariables[currentProfileNumber][index];

		}

		// The values of the summary table
		int concOptDec = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getColumnPropertiesArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getProcessingInputs().getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex()).getOptionalDecimals();
		
		String concReqDec = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getColumnPropertiesArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getProcessingInputs().getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex()).getRequiredDecimals();
		
		
		int timeoptdec =  appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getColumnPropertiesArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getProcessingInputs().getMappingDataObj()
								.getxColumnCorrespondinOriginalIndex()).getOptionalDecimals();
		
		String timeReqDec =  appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getColumnPropertiesArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getProcessingInputs().getMappingDataObj()
								.getxColumnCorrespondinOriginalIndex()).getRequiredDecimals();
		
	
		if((timeoptdec == 0)&&(timeReqDec.equals(""))){
			timeReqDec = "0.0000";
		}
		if((timeoptdec == 0)&&(timeReqDec.equals("0."))){
			timeReqDec = "0.0000";
		}
		
		if((concOptDec == 0)&&(concReqDec.equals(""))){
			concReqDec = "0.0000";
		}
		
		if((concOptDec == 0)&&(concReqDec.equals("0."))){
			concReqDec = "0.0000";
		}
		String[] xpStr = performxPFormatting(Xp, timeoptdec, timeReqDec);
		String[] YpStr = performYpFormatting(Yp, concOptDec, concReqDec);
		Ycalc = performYCalcFormatting(Ycalc, concOptDec, concReqDec);
		String[] aucStr = performAucValueFormatting(AUCValue, concOptDec, concReqDec);
		String[] aumcStr = performAumcValueFormatting(AUMCValue, concOptDec, concReqDec);
		String weightIndexStr = performFormatting(weightingIndex, concOptDec,
				concReqDec);
		
		ArrayList<double[]> seAL = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getWorkSheetOutputs().getStdError();
		if(Xp.length > 0){
			for (int index = 0; index < Xp.length; index++) {

				
				summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() + 0] = xpStr[index]
						+ "";

				NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
						.append(StringUtils.rightPad(xpStr[index], 6) + "\t");

				summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() + 2] = ThousandsSeperator
						.createThouSepInst().insertCommas(YpStr[index])
						+ "";

				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getisSparseSelected() == true) {
					
					
					if( seAL.get(seValueIndex)[1] == 0){
						summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).getNcaInfo()
								.getProcessingInputs().getMappingDataObj()
								.getSortVariablesListVector().size() + 1] =  "";

						// it is sparse sampling and we have to include the standard
						// deviation
						summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).getNcaInfo()
								.getProcessingInputs().getMappingDataObj()
								.getSortVariablesListVector().size() + 3] = "";
					} else {
						summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).getNcaInfo()
								.getProcessingInputs().getMappingDataObj()
								.getSortVariablesListVector().size() + 1] = seAL.get(seValueIndex)[1]
								
								+ "";

						// it is sparse sampling and we have to include the standard
						// deviation
						summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).getNcaInfo()
								.getProcessingInputs().getMappingDataObj()
								.getSortVariablesListVector().size() + 3] = seAL.get(seValueIndex)[0]
								+ "";
					}

					
					seValueIndex++;

				} else {

					summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).getNcaInfo()
							.getProcessingInputs().getMappingDataObj()
							.getSortVariablesListVector().size() + 1] = "";
					NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
							.getColumnModel()
							.getColumn(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector().size() + 1)
							.setHeaderValue("");

					NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
							.getColumnModel()
							.getColumn(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector().size() + 1)
							.setMaxWidth(0);
					summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).getNcaInfo()
							.getProcessingInputs().getMappingDataObj()
							.getSortVariablesListVector().size() + 3] = "";
					NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
							.getColumnModel()
							.getColumn(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector().size() + 3)
							.setHeaderValue("");
					NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
							.getColumnModel()
							.getColumn(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
											.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getMappingDataObj()
											.getSortVariablesListVector().size() + 3)
							.setMaxWidth(0);
				}

				// NCAMainScreen.completeTextOutputTextArea.append("\t"+(Yp[index],2));
				NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
						.append(StringUtils.rightPad(YpStr[index], 15) + "\t");
				boolean yCalcPresent = false;

				// if there is any predicted value for this x then print it.
				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.isDisableCurveStripping() == false) {
					for (int jindex = 0; jindex < termX.length; jindex++) {
						if (Xp[index] == Double.parseDouble(termX[jindex])) {
							summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() + 0] = summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() + 0]
									+ "#" + "";
							summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() + 4] = ThousandsSeperator
									.createThouSepInst()
									.insertCommas(Ycalc[jindex])
									+ "";
							summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size() + 5] = formatRecidual(
									Double.parseDouble(YpStr[index])
											- Double.parseDouble(Ycalc[jindex]),
									concOptDec, concReqDec)
									+ "";
							NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
									.append(StringUtils.rightPad(Ycalc[jindex], 15)
											+ "\t");
							yCalcPresent = true;
						}
					}
				}
				summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() + 6] = aucStr[index]
						+ "";
				summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() + 7] = aumcStr[index]
						+ "";
				if (yCalcPresent == false) {
					// NCAMainScreen.completeTextOutputTextArea.append("\t\t\t");

				} else if (yCalcPresent == true) {

					summary[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).getNcaInfo()
							.getProcessingInputs().getMappingDataObj()
							.getSortVariablesListVector().size() + 8] = ThousandsSeperator
							.createThouSepInst()
							.insertCommas(
									performFormatting(
											Math
													.pow(
															Double
																	.parseDouble(xpStr[index]),
															(-1)
																	* (Double
																			.parseDouble(weightIndexStr))),
											concOptDec, concReqDec))
							+ "";
					NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
							.append(StringUtils
									.rightPad(
											performFormatting(
													(1 / (Math
															.pow(
																	Double
																			.parseDouble(xpStr[index]),
																	(-1)
																			* (Double
																					.parseDouble(weightIndexStr))))),
													concOptDec, concReqDec), 15)
									+ "\t");
				}

				((DefaultTableModel) NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
						.getModel()).insertRow(NcaResultDispComp
						.createNcaResDispCompInst().resultsSummaryTable
						.getRowCount(), summary);
				NcaResultDispComp.createNcaResDispCompInst().completeTextOutputTextArea
						.append("\r\n");
				NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
						.validate();
				NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
						.revalidate();
				NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
						.repaint();

			} 
		} else {
			((DefaultTableModel) NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable
					.getModel()).insertRow(NcaResultDispComp
					.createNcaResDispCompInst().resultsSummaryTable
					.getRowCount(), summary);
		}

	}

	String formatRecidual(double d, int optDec, String reqDec) {
		String recidual = new String();
		if (optDec == 0) {
			if (reqDec.equals("")) {
				recidual = d + "";
			} else {
				DecimalFormat dec = new DecimalFormat(reqDec);
				dec.setRoundingMode(RoundingMode.DOWN);
				recidual = dec.format(d);
			}
		} else if (reqDec.equals("")) {
			if (optDec != 0) {

				recidual = DataManipulationFunctions.createDataManFuncInst()
						.truncateDouble(d, optDec)
						+ "";

			} else if (optDec == 0) {
				recidual = d + "";
			}
		}
		return (ThousandsSeperator.createThouSepInst().insertCommas(recidual));
	}

	String performFormatting(double weightingIndex, int optDec, String reqDec) {
		String weightIndexStr = new String();

		if (optDec == 0) {
			if (reqDec.equals("")) {
				weightIndexStr = weightingIndex + "";
			} else {
				DecimalFormat dec = new DecimalFormat(reqDec);
				dec.setRoundingMode(RoundingMode.DOWN);
				weightIndexStr = dec.format(weightingIndex);
			}
		} else if (reqDec.equals("")) {
			if (optDec != 0) {

				weightIndexStr = DataManipulationFunctions
						.createDataManFuncInst().truncateDouble(weightingIndex,
								optDec)
						+ "";

			} else if (optDec == 0) {
				weightIndexStr = weightingIndex + "";
			}
		}

		return weightIndexStr;
	}

	String[] performAumcValueFormatting(double[] aUMCValue, int optDec,
			String reqDec) {
		String[] aumcStr = new String[aUMCValue.length];

		if (optDec == 0) {
			if (reqDec.equals("")) {
				for (int i = 0; i < aUMCValue.length; i++) {
					aumcStr[i] = aUMCValue[i] + "";
				}
			} else {
				DecimalFormat dec = new DecimalFormat(reqDec);
				dec.setRoundingMode(RoundingMode.DOWN);
				for (int i = 0; i < aUMCValue.length; i++) {
					aumcStr[i] = dec.format(aUMCValue[i]);
				}
			}
		} else if (reqDec.equals("")) {
			if (optDec != 0) {
				for (int i = 0; i < aUMCValue.length; i++) {
					aumcStr[i] = DataManipulationFunctions
							.createDataManFuncInst().truncateDouble(
									aUMCValue[i], optDec)
							+ "";
				}

			} else if (optDec == 0) {
				for (int i = 0; i < aUMCValue.length; i++) {
					aumcStr[i] = aUMCValue[i] + "";
				}
			}
		}
		aumcStr = thousandsSeperator(aumcStr);
		return aumcStr;
	}

	String[] performAucValueFormatting(double[] aUCValue, int optDec,
			String reqDec) {

		String[] aucStr = new String[aUCValue.length];
		if (optDec == 0) {
			if (reqDec.equals("")) {
				for (int i = 0; i < aUCValue.length; i++) {
					aucStr[i] = aUCValue[i] + "";
				}
			} else {
				DecimalFormat dec = new DecimalFormat(reqDec);
				dec.setRoundingMode(RoundingMode.DOWN);
				for (int i = 0; i < aUCValue.length; i++) {
					aucStr[i] = dec.format(aUCValue[i]);
				}
			}
		} else if (reqDec.equals("")) {
			if (optDec != 0) {
				for (int i = 0; i < aUCValue.length; i++) {
					aucStr[i] = DataManipulationFunctions
							.createDataManFuncInst().truncateDouble(
									aUCValue[i], optDec)
							+ "";
				}

			} else if (optDec == 0) {
				for (int i = 0; i < aUCValue.length; i++) {
					aucStr[i] = aUCValue[i] + "";
				}
			}
		}
		aucStr = thousandsSeperator(aucStr);
		return aucStr;
	}

	String[] performYCalcFormatting(String[] ycalc, int optDec, String reqDec) {
		double[] temp = null;
		if (optDec == 0) {
			if (reqDec.equals("")) {
				for (int i = 0; i < ycalc.length; i++) {
					ycalc[i] = ycalc[i];
				}
			} else {
				DecimalFormat dec = new DecimalFormat(reqDec);
				dec.setRoundingMode(RoundingMode.DOWN);
				for (int i = 0; i < ycalc.length; i++) {
					ycalc[i] = dec.format(Double.parseDouble(ycalc[i]));
				}
			}
		} else if (reqDec.equals("")) {
			temp = new double[ycalc.length];
			if (optDec != 0) {
				for (int i = 0; i < ycalc.length; i++) {
					temp[i] = DataManipulationFunctions.createDataManFuncInst()
							.truncateDouble(Double.parseDouble(ycalc[i]),
									optDec);
				}

			} else if (optDec == 0) {
				for (int i = 0; i < ycalc.length; i++)
					temp[i] = Double.parseDouble(ycalc[i]);
			}
			for (int i = 0; i < temp.length; i++) {
				ycalc[i] = temp[i] + "";
			}
		}

		ycalc = thousandsSeperator(ycalc);
		return ycalc;
	}

	String[] performYpFormatting(double[] yp, int optDec, String reqDec) {

		String[] ypStr = new String[yp.length];
		if (optDec == 0) {
			if (reqDec.equals("")) {
				for (int i = 0; i < yp.length; i++) {
					ypStr[i] = yp[i] + "";
				}
			} else {
				DecimalFormat dec = new DecimalFormat(reqDec);
				dec.setRoundingMode(RoundingMode.DOWN);
				for (int i = 0; i < yp.length; i++) {
					ypStr[i] = dec.format(yp[i]);
				}
			}
		} else if (reqDec.equals("")) {
			if (optDec != 0) {
				for (int i = 0; i < yp.length; i++) {
					ypStr[i] = DataManipulationFunctions
							.createDataManFuncInst().truncateDouble(yp[i],
									optDec)
							+ "";
				}

			} else if (optDec == 0) {
				for (int i = 0; i < yp.length; i++) {
					ypStr[i] = yp[i] + "";
				}
			}
		}

		return ypStr;
	}

	String[] performxPFormatting(double[] xp, int optDec, String reqDec) {

		String[] xpStr = new String[xp.length];
		
		
		if (optDec == 0) {
			if (reqDec.equals("")) {
				for (int i = 0; i < xp.length; i++) {
					xpStr[i] = xp[i] + "";
				}
			} else {
				DecimalFormat dec = new DecimalFormat(reqDec);
				dec.setRoundingMode(RoundingMode.DOWN);
				for (int i = 0; i < xp.length; i++) {
					xpStr[i] = dec.format(xp[i]);
				}
			}
		} else if (reqDec.equals("")) {
			if (optDec != 0) {
				for (int i = 0; i < xp.length; i++) {
					xpStr[i] = DataManipulationFunctions
							.createDataManFuncInst().truncateDouble(xp[i],
									optDec)
							+ "";
				}

			} else if (optDec == 0) {

				for (int i = 0; i < xp.length; i++) {
					xpStr[i] = xp[i] + "";
				}

			}
		}

		return xpStr;
	}

	private String[] thousandsSeperator(String[] xpStr) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.isUseThousandSeperatorClicked() == true) {
			for (int i = 0; i < xpStr.length; i++) {
				xpStr[i] = ThousandsSeperator.createThouSepInst().insertCommas(
						xpStr[i]);
			}
		}

		return xpStr;
	}

}
