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

public class PolyExpoResultDispCompCreator {

	public PolyExpoResultDispCompCreator() {

	}

	public static PolyExpoResultDispCompCreator POLYEXPO_RES_DISP = null;

	public static PolyExpoResultDispCompCreator createPolyExpoResDispCompInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (POLYEXPO_RES_DISP == null) {
			POLYEXPO_RES_DISP = new PolyExpoResultDispCompCreator();
		}
		return POLYEXPO_RES_DISP;
	}

	JInternalFrame displayResultsInternalFrame;
	JDesktopPane displayResultsinternalFrameDesktopPane;

	JTable finalparametersVerticalDisplayTable;
	JInternalFrame finalParametersHorizontalDisplayInternalFrame;

	JInternalFrame textCompleteOutputInternalFrame;
	JTextArea completeTextOutputTextArea;

	int selectedImage;


	JInternalFrame plotOutputInternalFrame;

	ArrayList<JInternalFrame> xVsObsYAndPredYLogPlotAL;
	ArrayList<JInternalFrame> xVsObsYAndPredYLinearPlotAL;

	ArrayList<JInternalFrame> fracAbsLinarPlotAL;
	ArrayList<JInternalFrame> fracAbsLogPlotAL;

	ArrayList<JInternalFrame> cumuAbsLinearPlotAL;
	ArrayList<JInternalFrame> cumuAbsLogPlotAl;

	

	JInternalFrame predictedValueDisplayInternalFrame;

	JInternalFrame summaryTableDisplayInternalFrame;

	JInternalFrame userSettingsDisplayInternalFrame;
	JInternalFrame historyDisplayInternalFrame;

	JTable predictedValueDisplayTable;

	JTable summaryTableDisplayTable;

	JTable userSettingsDisplayTable;
	JTable historyDisplayTable;

	TableColumnModel predictedValueDisplayTableModel;

	TableColumnModel summaryTableDisplayTableModel;

	TableColumnModel userSettingsDisplayTableModel;
	TableColumnModel historyDisplayTableModel;

	String analysisType;

	public void polyExpoResultDispCompCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {

		createDisplayResultInternalFrame();
	}

	public void removingExistingRowsOfOutputTables() {

		removeAllRowsOfTables(summaryTableDisplayTable);
		removeAllRowsOfTables(predictedValueDisplayTable);

		removeAllRowsOfTables(historyDisplayTable);
		removeAllRowsOfTables(userSettingsDisplayTable);

	}

	public void removeAllRowsOfTables(JTable table) {
		int rowCount = table.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			((DefaultTableModel) table.getModel()).removeRow(i - 1);
		}
	}

	public void initializationOfPlotArrays() throws RowsExceededException,
			WriteException, BiffException, IOException {

		xVsObsYAndPredYLogPlotAL = new ArrayList<JInternalFrame>();
		xVsObsYAndPredYLinearPlotAL = new ArrayList<JInternalFrame>();
		fracAbsLinarPlotAL = new ArrayList<JInternalFrame>();
		fracAbsLogPlotAL = new ArrayList<JInternalFrame>();
		cumuAbsLinearPlotAL = new ArrayList<JInternalFrame>();
		cumuAbsLogPlotAl = new ArrayList<JInternalFrame>();
		
	}

	public void createDisplayResultInternalFrame()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		displayResultsInternalFrame = new JInternalFrame("Display", true, true,
				true, true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(displayResultsInternalFrame);

		displayResultsInternalFrame.setBorder(viewLayerInst.b);
		displayResultsInternalFrame
				.setLocation(
						0 + PolyExpoResultAvailCompDisplayer
								.createPolyExpoResAvailCompInst().resultsTabAvailableoutputInternalFrame
								.getWidth(), 0);

		int widthComp = 0;
		widthComp = PolyExpoMainScreenCreator
				.createPolyExpoMainScreenInstance().polyExpoMainInternalFrame
				.getWidth();

		displayResultsInternalFrame
				.setSize(
						widthComp
								- PolyExpoResultAvailCompDisplayer
										.createPolyExpoResAvailCompInst().resultsTabAvailableoutputInternalFrame
										.getWidth(),
						PolyExpoResultAvailCompDisplayer
								.createPolyExpoResAvailCompInst().resultsTabAvailableoutputInternalFrame
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

		PolyExpoTabbedPanesCreator.createPolyExpoTabbedPaneInstance().resultsTabDesktopPane
				.add(displayResultsInternalFrame);

		predictedValueDisplayInternalFrame = createIntframeWithSettings(
				predictedValueDisplayInternalFrame, "Predicted Value Display");
		displayResultsinternalFrameDesktopPane
				.add(predictedValueDisplayInternalFrame);

		summaryTableDisplayInternalFrame = createIntframeWithSettings(
				summaryTableDisplayInternalFrame, "Summary Table Display");
		displayResultsinternalFrameDesktopPane
				.add(summaryTableDisplayInternalFrame);

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

		if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Predicted_Value]")) {
			mainLayoutPageExportSettings();
			predictedValueDisplayInternalFrame.moveToFront();
			predictedValueDisplayInternalFrame
					.setSize(predictedValueDisplayInternalFrame.getSize());

		}else if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Summary]")) {
			mainLayoutPageExportSettings();
			summaryTableDisplayInternalFrame.moveToFront();
			summaryTableDisplayInternalFrame
					.setSize(summaryTableDisplayInternalFrame.getSize());

		} else if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Settings]")) {
			mainLayoutPageExportSettings();
			userSettingsDisplayInternalFrame.moveToFront();
			userSettingsDisplayInternalFrame
					.setSize(userSettingsDisplayInternalFrame.getSize());

		}else if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" History]")) {
			mainLayoutPageExportSettings();
			historyDisplayInternalFrame.moveToFront();
			historyDisplayInternalFrame
					.setSize(historyDisplayInternalFrame.getSize());

		}  else if (pathSplits[pathSplits.length - 1]
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

	private void createPredictedValueDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) throws RowsExceededException,
			WriteException, BiffException, IOException {
		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		String[][] tempStr = null;

		tempStr = PolyExpoResultAvailCompDisplayer
				.createPolyExpoResAvailCompInst()
				.readFile(
						"PredictedValueTableColName/PredictedValueTableColNameForPolyExpo.txt");
		int colNo = noOfSortVariables + tempStr.length;

		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		for (int i = 0; i < 2; i++) {
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

		int colNo = 0;
		String[][] tempStr = null;

		colNo = noOfSortVariables + 4;
		tempStr = InVitroResultAvailCompDisplayer
				.createInVitroResAvailCompInst()
				.readFile(
						"SymmaryTableColName/SummaryTableColNameForPolyExpoDeConvo.txt");

		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		for (int i = 0; i < 4; i++) {
			colName[i + noOfSortVariables] = tempStr[i][0];
		}
		tableHeaderInst.setSummaryTableTH(colName);

		summaryTableDisplayTable = new JTable(0, colNo);
		createResultsTable(summaryTableDisplayInternalFrame,
				summaryTableDisplayTable, summaryTableDisplayTableModel, rowNo,
				colNo, colName);

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
		String[][] tempStr = InVitroResultAvailCompDisplayer
				.createInVitroResAvailCompInst().readFile(
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
		DefaultTreeModel model = (DefaultTreeModel) PolyExpoResultAvailCompDisplayer
				.createPolyExpoResAvailCompInst().availableOutputsTree
				.getModel();

		DefaultMutableTreeNode plots = new DefaultMutableTreeNode("plots");
		PolyExpoResultAvailCompDisplayer.createPolyExpoResAvailCompInst().availableOutputs
				.add(plots);

		DefaultMutableTreeNode XvsObservedAndPredictedY = new DefaultMutableTreeNode(
				"X vs Observed and Predicted Y");
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

		DefaultMutableTreeNode ObservedYvsWeightedPredictedY = new DefaultMutableTreeNode(
				"Time vs Frac Absorption");
		plots.add(ObservedYvsWeightedPredictedY);
		DefaultMutableTreeNode linPlot1 = new DefaultMutableTreeNode("Lin Plot");

		ObservedYvsWeightedPredictedY.add(linPlot1);
		for (int i = 0; i < noOfProfiles; i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			linPlot1.add(plotNode);
		}

		DefaultMutableTreeNode logPlot1 = new DefaultMutableTreeNode("Log Plot");

		ObservedYvsWeightedPredictedY.add(logPlot1);
		for (int i = 0; i < noOfProfiles; i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			logPlot1.add(plotNode);
		}

		DefaultMutableTreeNode WeightedPredictedYvsWeightedResidualY = new DefaultMutableTreeNode(
				"Time vs Cumulative Absorption");
		plots.add(WeightedPredictedYvsWeightedResidualY);
		DefaultMutableTreeNode linPlot2 = new DefaultMutableTreeNode("Lin Plot");

		WeightedPredictedYvsWeightedResidualY.add(linPlot2);

		for (int i = 0; i < noOfProfiles; i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			linPlot2.add(plotNode);
		}
		DefaultMutableTreeNode logPlot2 = new DefaultMutableTreeNode("Log Plot");

		WeightedPredictedYvsWeightedResidualY.add(logPlot2);

		for (int i = 0; i < noOfProfiles; i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			logPlot2.add(plotNode);
		}

		
		model.reload();
	}

	public void removePreviousResults() throws RowsExceededException,
			WriteException, BiffException, IOException {

		completeTextOutputTextArea.setText("");

		
		DefaultTreeModel model = (DefaultTreeModel) PolyExpoResultAvailCompDisplayer
				.createPolyExpoResAvailCompInst().availableOutputsTree
				.getModel();

		TreePath plotsPath = PolyExpoResultAvailCompDisplayer
				.createPolyExpoResAvailCompInst().availableOutputsTree
				.getNextMatch("plots", 0, Bias.Forward);
		if (plotsPath == null) {

		} else {
			MutableTreeNode mNode = (MutableTreeNode) plotsPath
					.getLastPathComponent();
			model.removeNodeFromParent(mNode);
			for (int i = 0; i < xVsObsYAndPredYLogPlotAL.size(); i++) {
				xVsObsYAndPredYLogPlotAL.get(i).dispose();
			}

			for (int i = 0; i < xVsObsYAndPredYLinearPlotAL.size(); i++) {
				xVsObsYAndPredYLinearPlotAL.get(i).dispose();
			}

			for (int i = 0; i < fracAbsLinarPlotAL.size(); i++) {
				fracAbsLinarPlotAL.get(i).dispose();
			}

			for (int i = 0; i < fracAbsLogPlotAL.size(); i++) {
				fracAbsLogPlotAL.get(i).dispose();
			}

			for (int i = 0; i < cumuAbsLinearPlotAL.size(); i++) {
				cumuAbsLinearPlotAL.get(i).dispose();
			}

			for (int i = 0; i < cumuAbsLogPlotAl.size(); i++) {
				cumuAbsLogPlotAl.get(i).dispose();
			}

			
			

		}
	}

	public void createTablesToFillResult() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		ResultDisplayTablesHeader tableHeaderInst = new ResultDisplayTablesHeader();

		createPredictedValueDisplayTable(tableHeaderInst, procInputInst);
		createSummaryDisplayTable(tableHeaderInst, procInputInst);
		createUserSettingsDisplayTable(tableHeaderInst, procInputInst);
		createHistoryDisplayTable(tableHeaderInst, procInputInst);

		procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		procInputInst.setResultDisplayTabllsHeaderObj(tableHeaderInst);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	public void displayProcessResults() throws RowsExceededException,
			WriteException, BiffException, IOException {
		if (PolyExpoResultAvailCompDisplayer.createPolyExpoResAvailCompInst().availableOutputsTree
				.getSelectionPath() != null) {

			String plotName = PolyExpoResultAvailCompDisplayer
					.createPolyExpoResAvailCompInst().availableOutputsTree
					.getSelectionPath().getLastPathComponent().toString();

			String[] pathSplits = PolyExpoResultAvailCompDisplayer
					.createPolyExpoResAvailCompInst().availableOutputsTree
					.getSelectionPath().toString().split(",");

			String[] plotNameSplits = plotName.split(" ");

			if ((pathSplits.length - 3) >= 0
					&& pathSplits[pathSplits.length - 3]
							.equalsIgnoreCase(" X vs Observed and Predicted Y")) {

				displayResultPlots(pathSplits, plotNameSplits,xVsObsYAndPredYLinearPlotAL
						, xVsObsYAndPredYLogPlotAL);
			}

			if ((pathSplits.length - 3) >= 0
					&& pathSplits[pathSplits.length - 3]
							.equalsIgnoreCase(" Time vs Frac Absorption")) {
				displayResultPlots(pathSplits, plotNameSplits,
						fracAbsLinarPlotAL,
						fracAbsLogPlotAL);
			}

			if ((pathSplits.length - 3) >= 0
					&& pathSplits[pathSplits.length - 3]
							.equalsIgnoreCase(" Time vs Cumulative Absorption")) {
				displayResultPlots(pathSplits, plotNameSplits,
						cumuAbsLinearPlotAL,
						cumuAbsLogPlotAl);
			}

			

			determineInternalFrameForResultDisplay(pathSplits);

		}
	}

}
