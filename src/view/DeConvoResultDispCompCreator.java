package view;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.Position.Bias;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;

public class DeConvoResultDispCompCreator {

	public DeConvoResultDispCompCreator() {

	}

	public static DeConvoResultDispCompCreator DECONVO_RES_DISP = null;

	public static DeConvoResultDispCompCreator createDeConvoResDispCompInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (DECONVO_RES_DISP == null) {
			DECONVO_RES_DISP = new DeConvoResultDispCompCreator();
		}
		return DECONVO_RES_DISP;
	}

	JInternalFrame displayResultsInternalFrame;
	JDesktopPane displayResultsinternalFrameDesktopPane;

	JInternalFrame textCompleteOutputInternalFrame;
	JTextArea completeTextOutputTextArea;

	int selectedImage;

	JTableHeader finalParametersHorizontalDisplayTableHeader;

	JTable resultsSubAreasTable;
	JTable resultsSummaryTable;
	JTable resultsDosingTable;

	JInternalFrame plotOutputInternalFrame;

	ArrayList<JInternalFrame> outputPlotsArraylist;
	ArrayList<JInternalFrame> outputPlotsLogViewsArrayList;

	ArrayList<JInternalFrame> fracAbsPlotsArraylist;
	ArrayList<JInternalFrame> fracAbsPlotsLogViewsArrayList;

	JInternalFrame finalParametersInternalFrame;
	JInternalFrame finalParamHorizontalDisplayInternalFrame;
	JInternalFrame dosingDisplayInternalFrame;
	JInternalFrame predictedValueDisplayInternalFrame;
	JInternalFrame exclusionInternalFrame;
	JInternalFrame summaryTableDisplayInternalFrame;
	JInternalFrame userSettingsDisplayInternalFrame;
	JInternalFrame historyDisplayInternalFrame;

	JTable finalparametersVerticalDisplayTable;
	JTable finalParametersHorizontalDisplayTable;
	JTable predictedValueDisplayTable;
	JTable summaryTableDisplayTable;
	JTable dosingDisplayTable;
	JTable exclusionDisplayTable;
	JTable userSettingsDisplayTable;
	JTable historyDisplayTable;

	TableColumnModel finalParametersVerticalDisplayTableModel;
	TableColumnModel finalParametersHorizontalDisplayTableModel;
	TableColumnModel predictedValueDisplayTableModel;
	TableColumnModel summaryTableDisplayTableModel;
	TableColumnModel dosingDisplayTableModel;
	TableColumnModel exclusionDisplayTableModel;
	TableColumnModel userSettingsDisplayTableModel;
	TableColumnModel historyDisplayTableModel;

	String analysisType;

	public void deConvoResultDispCompCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {

		createDisplayResultInternalFrame();
	}

	public void removingExistingRowsOfOutputTables() {

		removeAllRowsOfTables(finalParametersHorizontalDisplayTable);
		removeAllRowsOfTables(finalparametersVerticalDisplayTable);
		removeAllRowsOfTables(summaryTableDisplayTable);
		removeAllRowsOfTables(predictedValueDisplayTable);
		removeAllRowsOfTables(historyDisplayTable);
		removeAllRowsOfTables(userSettingsDisplayTable);
		removeAllRowsOfTables(exclusionDisplayTable);
		removeAllRowsOfTables(dosingDisplayTable);

	}

	public void removeAllRowsOfTables(JTable table) {
		int rowCount = table.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			((DefaultTableModel) table.getModel()).removeRow(i - 1);
		}
	}

	public void initializationOfPlotArrays() throws RowsExceededException,
			WriteException, BiffException, IOException {

		outputPlotsArraylist = new ArrayList<JInternalFrame>();
		outputPlotsLogViewsArrayList = new ArrayList<JInternalFrame>();

	}

	public void createDisplayResultInternalFrame()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		analysisType = appInfo
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

		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		displayResultsInternalFrame = new JInternalFrame("Display", true, true,
				true, true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(displayResultsInternalFrame);

		displayResultsInternalFrame.setBorder(viewLayerInst.b);
		displayResultsInternalFrame
				.setLocation(
						0 + DeConvoResultAvailCompDisplayer
								.createDeConvoResAvailCompInst().resultsTabAvailableoutputInternalFrame
								.getWidth(), 0);

		int widthComp = 0;

		if (analysisType.equals("wagnelson")) {
			widthComp = WagNelsonMainScreenCreator
					.createWagNelsonMainScreenInstance().wagNelsonMainInternalFrame
					.getWidth();
		} else if (analysisType.equals("looriegel")) {
			widthComp = LooRiegelMainScreenCreator
					.createLooRiegelMainScreenInstance().looRiegelMainInternalFrame
					.getWidth();
		}

		displayResultsInternalFrame
				.setSize(
						widthComp
								- DeConvoResultAvailCompDisplayer
										.createDeConvoResAvailCompInst().resultsTabAvailableoutputInternalFrame
										.getWidth(),
						DeConvoResultAvailCompDisplayer
								.createDeConvoResAvailCompInst().resultsTabAvailableoutputInternalFrame
								.getHeight());

		displayResultsInternalFrame.setVisible(true);
		displayResultsinternalFrameDesktopPane = new JDesktopPane();
		displayResultsInternalFrame
				.setContentPane(displayResultsinternalFrameDesktopPane);
		displayResultsInternalFrame.moveToFront();
		displayResultsInternalFrame.setBorder(viewLayerInst.b);
		createInternalFrameForTableOutputDisplay();
		createCompleteOutputInternalFrame();
		createPlotOutputInternalFrame();

	}

	public void createInternalFrameForTableOutputDisplay()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		DeConvoTabbedPaneCreator.createDeConvoTabbedPaneInstance().resultsTabDesktopPane
				.add(displayResultsInternalFrame);

		finalParametersInternalFrame = createIntframeWithSettings(
				finalParametersInternalFrame, "Final Parameters");
		displayResultsinternalFrameDesktopPane
				.add(finalParametersInternalFrame);

		finalParamHorizontalDisplayInternalFrame = createIntframeWithSettings(
				finalParamHorizontalDisplayInternalFrame,
				"Final Parameter Horizantal Display");
		displayResultsinternalFrameDesktopPane
				.add(finalParamHorizontalDisplayInternalFrame);

		predictedValueDisplayInternalFrame = createIntframeWithSettings(
				predictedValueDisplayInternalFrame, "Predicted Value Display");
		displayResultsinternalFrameDesktopPane
				.add(predictedValueDisplayInternalFrame);

		summaryTableDisplayInternalFrame = createIntframeWithSettings(
				summaryTableDisplayInternalFrame, "Summary Table Display");
		displayResultsinternalFrameDesktopPane
				.add(summaryTableDisplayInternalFrame);

		dosingDisplayInternalFrame = createIntframeWithSettings(
				dosingDisplayInternalFrame, "Dosing Table Display");
		displayResultsinternalFrameDesktopPane.add(dosingDisplayInternalFrame);

		exclusionInternalFrame = createIntframeWithSettings(
				exclusionInternalFrame, "Exclusion Display");
		displayResultsinternalFrameDesktopPane.add(exclusionInternalFrame);

		userSettingsDisplayInternalFrame = createIntframeWithSettings(
				userSettingsDisplayInternalFrame, "User Settings Display");
		displayResultsinternalFrameDesktopPane
				.add(userSettingsDisplayInternalFrame);

		historyDisplayInternalFrame = createIntframeWithSettings(
				historyDisplayInternalFrame, "History Display");
		displayResultsinternalFrameDesktopPane.add(historyDisplayInternalFrame);

	}

	private JInternalFrame createIntframeWithSettings(JInternalFrame iFrame,
			String title) {
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();
		JinternalFrameFunctions jIFfunctionsInst = JinternalFrameFunctions
				.createIFFunctionsInstance();
		iFrame = new JInternalFrame(title, true, true, true, true);
		iFrame.setBorder(viewLayerInst.b);
		jIFfunctionsInst.removeTitleBarForinternalFrame(iFrame);
		iFrame.setLocation(0, 0);
		iFrame.setSize(displayResultsInternalFrame.getWidth(),
				displayResultsInternalFrame.getHeight() - 20);
		iFrame.setVisible(true);
		iFrame.setSize(iFrame.getSize());

		return iFrame;
	}

	private void createCompleteOutputInternalFrame() {
		textCompleteOutputInternalFrame = createIntframeWithSettings(
				textCompleteOutputInternalFrame, "Text Output Display");
		completeTextOutputTextArea = new JTextArea();
		JScrollPane completeTextAreaScrollPane = new JScrollPane(
				completeTextOutputTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		textCompleteOutputInternalFrame.getContentPane().add(
				completeTextAreaScrollPane);

		displayResultsinternalFrameDesktopPane
				.add(textCompleteOutputInternalFrame);
		textCompleteOutputInternalFrame.moveToFront();

	}

	private void createPlotOutputInternalFrame() {
		plotOutputInternalFrame = createIntframeWithSettings(
				plotOutputInternalFrame, "Plot Display");

		displayResultsinternalFrameDesktopPane.add(plotOutputInternalFrame);
		plotOutputInternalFrame.moveToFront();

	}

	void displayResultPlots(String[] pathSplits, String[] plotNameSplits,
			ArrayList<JInternalFrame> linInternalFrameListing,
			ArrayList<JInternalFrame> logInternalFrameListing) {

		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		viewLayerInst.createmenuBarInstance().exportImage.setEnabled(true);
		viewLayerInst.createmenuBarInstance().exportTextoutput
				.setEnabled(false);
		viewLayerInst.createmenuBarInstance().exportTable.setEnabled(false);
		int plotNumber = Integer
				.parseInt(plotNameSplits[plotNameSplits.length - 1]);
		selectedImage = plotNumber - 1;
		plotNumber = (plotNumber - 1);

		if (pathSplits[pathSplits.length - 2].equalsIgnoreCase(" Lin Plot")) {
			linInternalFrameListing.get(plotNumber).moveToFront();
			linInternalFrameListing.get(plotNumber).setSize(
					linInternalFrameListing.get(plotNumber).getSize());

		} else if (pathSplits[pathSplits.length - 2]
				.equalsIgnoreCase(" Log Plot")) {
			logInternalFrameListing.get(plotNumber).moveToFront();
			logInternalFrameListing.get(plotNumber).setSize(
					logInternalFrameListing.get(plotNumber).getSize());

		}
	}

	public void determineInternalFrameForResultDisplay(String[] pathSplits) {

		if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Final_Parameters]")) {
			mainLayoutPageExportSettings();
			finalParamHorizontalDisplayInternalFrame.moveToFront();
			finalParamHorizontalDisplayInternalFrame
					.setSize(finalParamHorizontalDisplayInternalFrame.getSize());

		} else if (pathSplits[pathSplits.length - 1]
				.equals(" Non_Transposed_Final_Parameters]")) {
			mainLayoutPageExportSettings();
			finalParametersInternalFrame.moveToFront();
			finalParametersInternalFrame.setSize(finalParametersInternalFrame
					.getSize());

		} else if ((pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Wagner_Nelson]"))
				|| (pathSplits[pathSplits.length - 1]
						.equalsIgnoreCase(" Loo-Riegelman]"))) {
			mainLayoutPageExportSettings();
			predictedValueDisplayInternalFrame.moveToFront();
			predictedValueDisplayInternalFrame
					.setSize(predictedValueDisplayInternalFrame.getSize());

		} else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" History]")) {
			mainLayoutPageExportSettings();
			historyDisplayInternalFrame.moveToFront();
			historyDisplayInternalFrame.setSize(historyDisplayInternalFrame
					.getSize());
		} else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" User_Settings]")) {
			mainLayoutPageExportSettings();
			userSettingsDisplayInternalFrame.moveToFront();
			userSettingsDisplayInternalFrame
					.setSize(userSettingsDisplayInternalFrame.getSize());

		} else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Dosing_Used]")) {
			mainLayoutPageExportSettings();
			dosingDisplayInternalFrame.moveToFront();
			dosingDisplayInternalFrame.setSize(dosingDisplayInternalFrame
					.getSize());

		} else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Exclusion]")) {
			mainLayoutPageExportSettings();
			exclusionInternalFrame.moveToFront();
			exclusionInternalFrame.setSize(exclusionInternalFrame.getSize());

		} else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Summary]")) {
			mainLayoutPageExportSettings();
			summaryTableDisplayInternalFrame.moveToFront();
			summaryTableDisplayInternalFrame
					.setSize(summaryTableDisplayInternalFrame.getSize());
		} else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Complete output]")) {
			DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

			viewLayerInst.createmenuBarInstance().exportTextoutput
					.setEnabled(true);
			viewLayerInst.createmenuBarInstance().exportImage.setEnabled(false);
			viewLayerInst.createmenuBarInstance().exportTable.setEnabled(false);

			textCompleteOutputInternalFrame.moveToFront();
			textCompleteOutputInternalFrame
					.setSize(textCompleteOutputInternalFrame.getSize());

			completeTextOutputTextArea.setCaretPosition(0);

		}
	}

	private void mainLayoutPageExportSettings() {
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		viewLayerInst.createmenuBarInstance().exportTextoutput
				.setEnabled(false);
		viewLayerInst.createmenuBarInstance().exportImage.setEnabled(false);
		viewLayerInst.createmenuBarInstance().exportTable.setEnabled(true);
	}

	private void createVerticalFinalParamTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) throws RowsExceededException,
			WriteException, BiffException, IOException {

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + 2;
		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		colName[noOfSortVariables] = "Parameter";
		colName[noOfSortVariables + 1] = "Estimate";

		tableHeaderInst.setVerticalParametersTH(colName);

		finalparametersVerticalDisplayTable = new JTable(0, colNo);
		createResultsTable(finalParametersInternalFrame,
				finalparametersVerticalDisplayTable,
				finalParametersVerticalDisplayTableModel, rowNo, colNo, colName);
	}

	private void createHorizontalFinalParamTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) {

		DeConvoParamListLoader paramListInst = DeConvoParamListLoader
				.createParamListInstance();
		paramListInst.createParameterList();

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + paramListInst.numberOfParameter;
		String[] colName = new String[colNo];
		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		for (int i = 0; i < paramListInst.numberOfParameter; i++) {

			colName[noOfSortVariables + i] = paramListInst.parameterNameForInDeConvo[i];

		}
		tableHeaderInst.setHorizontalParametersTH(colName);

		finalParametersHorizontalDisplayTable = new JTable(0, colNo);
		createResultsTable(finalParamHorizontalDisplayInternalFrame,
				finalParametersHorizontalDisplayTable,
				finalParametersHorizontalDisplayTableModel, rowNo, colNo,
				colName);

	}

	private void createPredictedValueDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) throws RowsExceededException,
			WriteException, BiffException, IOException {
		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		int colNo = 0;
		String[][] tempStr = null;

		if (analysisType.equals("wagnelson")) {
			colNo = noOfSortVariables + 5;
			tempStr = DeConvoResultAvailCompDisplayer
					.createDeConvoResAvailCompInst()
					.readFile(
							"PredictedValueTableColName/PredictedValueTableColNameForWagNelson.txt");

		} else if (analysisType.equals("looriegel")) {
			colNo = noOfSortVariables + 6;
			tempStr = DeConvoResultAvailCompDisplayer
					.createDeConvoResAvailCompInst()
					.readFile(
							"PredictedValueTableColName/PredictedValueTableColNameForLooRiegel.txt");
		}

		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		for (int i = 0; i < tempStr.length; i++) {
			colName[i + noOfSortVariables] = tempStr[i][0];
		}
		tableHeaderInst.setPredictedValueTH(colName);

		predictedValueDisplayTable = new JTable(0, colNo);
		createResultsTable(predictedValueDisplayInternalFrame,
				predictedValueDisplayTable, predictedValueDisplayTableModel,
				rowNo, colNo, colName);

	}

	private void createSummaryDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) throws RowsExceededException,
			WriteException, BiffException, IOException {

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		String[][] tempStr = null;

		tempStr = DeConvoResultAvailCompDisplayer
				.createDeConvoResAvailCompInst()
				.readFile(
						"SymmaryTableColName/SummaryTableColNameForDeConvo.txt");

		int colNo = noOfSortVariables + tempStr.length;
		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		for (int i = 0; i < tempStr.length; i++) {
			colName[i + noOfSortVariables] = tempStr[i][0];
		}
		tableHeaderInst.setSummaryTableTH(colName);

		summaryTableDisplayTable = new JTable(0, colNo);
		createResultsTable(summaryTableDisplayInternalFrame,
				summaryTableDisplayTable, summaryTableDisplayTableModel, rowNo,
				colNo, colName);

	}

	private void createDosingDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) {

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + 1;
		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		colName[noOfSortVariables] = "Time of Last Dose";

		dosingDisplayTable = new JTable(0, colNo);
		createResultsTable(dosingDisplayInternalFrame, dosingDisplayTable,
				dosingDisplayTableModel, rowNo, colNo, colName);

	}

	private void createExclusionDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) {

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables;
		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		tableHeaderInst.setPartialDerivativeTH(colName);

		exclusionDisplayTable = new JTable(0, colNo);
		createResultsTable(exclusionInternalFrame, exclusionDisplayTable,
				exclusionDisplayTableModel, rowNo, colNo, colName);

	}

	private void createUserSettingsDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) {

		int rowNo = 0;
		int colNo = 1;
		String[] colName = { "User_Settings" };
		tableHeaderInst.setUserSettingsTH(colName);

		userSettingsDisplayTable = new JTable(0, colNo);
		createResultsTable(userSettingsDisplayInternalFrame,
				userSettingsDisplayTable, userSettingsDisplayTableModel, rowNo,
				colNo, colName);
	}

	private void createHistoryDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) throws RowsExceededException,
			WriteException, BiffException, IOException {

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + 7;
		String[] colName = new String[colNo];
		String[][] tempStr = DeConvoResultAvailCompDisplayer
				.createDeConvoResAvailCompInst().readFile(
						"HistoryTableColName.txt");

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		for (int i = 0; i < 7; i++) {
			colName[i + noOfSortVariables] = tempStr[i][0];
		}
		tableHeaderInst.setHistoryTH(colName);

		historyDisplayTable = new JTable(0, colNo);
		createResultsTable(historyDisplayInternalFrame, historyDisplayTable,
				historyDisplayTableModel, rowNo, colNo, colName);

	}

	private void createResultsTable(JInternalFrame intFrame, JTable tableName,
			TableColumnModel tableColModel, int rowNo, int colNo,
			String[] colName) {

		intFrame.getContentPane().removeAll();
		// tableName = new JTable(0, colNo);
		tableName.setPreferredScrollableViewportSize(new Dimension(
				displayResultsInternalFrame.getWidth(),
				displayResultsInternalFrame.getHeight()));
		tableName.setFillsViewportHeight(true);
		tableName.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableColModel = tableName.getColumnModel();
		TableColumn tc = null;
		for (int i = 0; i < colNo; i++) {
			tc = tableColModel.getColumn(i);
			tc.setHeaderValue(colName[i]);
		}

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				tableName);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(intFrame.getWidth(), intFrame
						.getHeight()));
		intFrame.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	public void populateAvailableResultsWithPlots()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		int noOfProfiles = 0;

		noOfProfiles = procInputInst.getProfileAndParamInfoObj()
				.getNoOfSubject();
		DefaultTreeModel model = (DefaultTreeModel) DeConvoResultAvailCompDisplayer
				.createDeConvoResAvailCompInst().availableOutputsTree
				.getModel();

		DefaultMutableTreeNode plots = new DefaultMutableTreeNode("plots");
		DeConvoResultAvailCompDisplayer.createDeConvoResAvailCompInst().availableOutputs
				.add(plots);

		DefaultMutableTreeNode XvsObservedAndPredictedY = new DefaultMutableTreeNode(
				"Time vs Observed and Predicted Conc");
		plots.add(XvsObservedAndPredictedY);

		DefaultMutableTreeNode linPlot = new DefaultMutableTreeNode("Lin Plot");
		XvsObservedAndPredictedY.add(linPlot);
		for (int i = 0; i < noOfProfiles; i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			linPlot.add(plotNode);
		}

		DefaultMutableTreeNode logPlot = new DefaultMutableTreeNode("Log Plot");
		XvsObservedAndPredictedY.add(logPlot);
		for (int i = 0; i < noOfProfiles; i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			logPlot.add(plotNode);
		}

		DefaultMutableTreeNode timeVsFracAbs = new DefaultMutableTreeNode(
				"Time vs Fraction Absorption");
		plots.add(timeVsFracAbs);
		DefaultMutableTreeNode linPlot1 = new DefaultMutableTreeNode("Lin Plot");

		timeVsFracAbs.add(linPlot1);
		for (int i = 0; i < noOfProfiles; i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			linPlot1.add(plotNode);
		}

		DefaultMutableTreeNode logPlot1 = new DefaultMutableTreeNode("Log Plot");

		timeVsFracAbs.add(logPlot1);
		for (int i = 0; i < noOfProfiles; i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			logPlot1.add(plotNode);
		}

		model.reload();
	}

	public void removePreviousResults() throws RowsExceededException,
			WriteException, BiffException, IOException {

		completeTextOutputTextArea.setText("");

		/*
		 * ((DefaultTableModel) userSettingsDisplayTable.getModel())
		 * .getDataVector().removeAllElements();
		 * 
		 * while (userSettingsDisplayTable.getRowCount() != 0) {
		 * ((DefaultTableModel) userSettingsDisplayTable.getModel())
		 * .removeRow(0); }
		 */

		DefaultTreeModel model = (DefaultTreeModel) DeConvoResultAvailCompDisplayer
				.createDeConvoResAvailCompInst().availableOutputsTree
				.getModel();

		TreePath plotsPath = DeConvoResultAvailCompDisplayer
				.createDeConvoResAvailCompInst().availableOutputsTree
				.getNextMatch("plots", 0, Bias.Forward);
		if (plotsPath == null) {

		} else {
			MutableTreeNode mNode = (MutableTreeNode) plotsPath
					.getLastPathComponent();
			model.removeNodeFromParent(mNode);
			for (int i = 0; i < outputPlotsArraylist.size(); i++) {
				outputPlotsArraylist.get(i).dispose();
			}

			for (int i = 0; i < outputPlotsLogViewsArrayList.size(); i++) {
				outputPlotsLogViewsArrayList.get(i).dispose();
			}

		}
	}

	public void createTablesToFillResult() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		ResultDisplayTablesHeader tableHeaderInst = new ResultDisplayTablesHeader();

		createVerticalFinalParamTable(tableHeaderInst, procInputInst);
		createHorizontalFinalParamTable(tableHeaderInst, procInputInst);
		createPredictedValueDisplayTable(tableHeaderInst, procInputInst);
		createSummaryDisplayTable(tableHeaderInst, procInputInst);
		createDosingDisplayTable(tableHeaderInst, procInputInst);
		createExclusionDisplayTable(tableHeaderInst, procInputInst);
		createUserSettingsDisplayTable(tableHeaderInst, procInputInst);
		createHistoryDisplayTable(tableHeaderInst, procInputInst);

		procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		procInputInst.setResultDisplayTabllsHeaderObj(tableHeaderInst);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	public void displayProcessResults() throws RowsExceededException,
			WriteException, BiffException, IOException {
		if (DeConvoResultAvailCompDisplayer.createDeConvoResAvailCompInst().availableOutputsTree
				.getSelectionPath() != null) {

			String plotName = DeConvoResultAvailCompDisplayer
					.createDeConvoResAvailCompInst().availableOutputsTree
					.getSelectionPath().getLastPathComponent().toString();

			String[] pathSplits = DeConvoResultAvailCompDisplayer
					.createDeConvoResAvailCompInst().availableOutputsTree
					.getSelectionPath().toString().split(",");

			String[] plotNameSplits = plotName.split(" ");

			if ((pathSplits.length - 3) >= 0
					&& pathSplits[pathSplits.length - 3]
							.equalsIgnoreCase(" Time vs Observed and Predicted Conc")) {

				displayResultPlots(pathSplits, plotNameSplits,
						outputPlotsArraylist, outputPlotsLogViewsArrayList);
			}

			if ((pathSplits.length - 3) >= 0
					&& pathSplits[pathSplits.length - 3]
							.equalsIgnoreCase(" Time vs Fraction Absorption")) {

				displayResultPlots(pathSplits, plotNameSplits,
						fracAbsPlotsArraylist, fracAbsPlotsLogViewsArrayList);
			}

			determineInternalFrameForResultDisplay(pathSplits);

		}
	}

}
