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

public class InVitroResultDispCompCreator {



	public InVitroResultDispCompCreator() {

	}

	public static InVitroResultDispCompCreator INVITRO_RES_DISP = null;

	public static InVitroResultDispCompCreator createInVitroResDispCompInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (INVITRO_RES_DISP == null) {
			INVITRO_RES_DISP = new InVitroResultDispCompCreator();
		}
		return INVITRO_RES_DISP;
	}

	JInternalFrame displayResultsInternalFrame;
	JDesktopPane displayResultsinternalFrameDesktopPane;

	JTable finalparametersVerticalDisplayTable;
	JInternalFrame finalParametersHorizontalDisplayInternalFrame;

	JInternalFrame textCompleteOutputInternalFrame;
	JTextArea completeTextOutputTextArea;

	int selectedImage;
	JTable finalParametersHorizontalDisplayTable;
	TableColumnModel finalParametersVerticalDisplayTableModel;
	JTableHeader finalParametersHorizontalDisplayTableHeader;
	TableColumnModel finalParametersHorizontalDisplayTableModel;
	JTable resultsSubAreasTable;
	JTable resultsSummaryTable;
	JTable resultsDosingTable;

	JInternalFrame plotOutputInternalFrame;

	ArrayList<JInternalFrame> xVsObsYAndPredYLogPlotAL;
	ArrayList<JInternalFrame> xVsObsYAndPredYLinearPlotAL;

	ArrayList<JInternalFrame> obsYVsWeightPredYLinarPlotAL;
	ArrayList<JInternalFrame> obsYVsWeightPredYLogPlotAL;

	ArrayList<JInternalFrame> weightPredYVsWeightResdYLinearPlotAL;
	ArrayList<JInternalFrame> weightPredYVsWeightResdYLogPlotAl;

	ArrayList<JInternalFrame> xVsWeightedResidualYLinearPlotAL;
	ArrayList<JInternalFrame> xVsWeightedResidualYLogPlotAL;

	ArrayList<JInternalFrame> partialDerivativeLinearPlotAL;
	ArrayList<JInternalFrame> partialDerivativeLogPlotAL;

	JInternalFrame finalParametersinternalFrame;
	JInternalFrame finalParamHorizontalDisplayInternalFrame;
	JInternalFrame initialParametersinternalFrame;
	JInternalFrame minimizationProcessInternalFrame;
	JInternalFrame correlationMatrixDisplayInternalFrame;
	JInternalFrame eigenValuesDisplayInternalFrame;
	JInternalFrame conditionNumberDisplayInternalFrame;
	JInternalFrame predictedValueDisplayInternalFrame;
	JInternalFrame varienceCovarienceMatrixDisplayInternalFrame;
	JInternalFrame summaryTableDisplayInternalFrame;
	JInternalFrame diagnosticsDisplayInternalFrame;
	JInternalFrame partialDerivativeDisplayInternalFrame;
	JInternalFrame userSettingsDisplayInternalFrame;
	JInternalFrame historyDisplayInternalFrame;

	JTable initialParameterDisplayTable;
	JTable minimizationProcessDisplayTable;
	JTable correlationMatrixDisplayTable;
	JTable eigenValuesDisplayTable;
	JTable conditionNumberDisplayTable;
	JTable predictedValueDisplayTable;
	JTable varienceCovarienceMatrixDisplayTable;
	JTable summaryTableDisplayTable;
	JTable diagnosticsDisplayTable;
	JTable partialDerivativeDisplayTable;
	JTable userSettingsDisplayTable;
	JTable historyDisplayTable;

	TableColumnModel initialParameterDisplayTableModel;
	TableColumnModel minimizationProcessDisplayTableModel;
	TableColumnModel correlationMatrixDisplayTableModel;
	TableColumnModel eigenValuesDisplayTableModel;
	TableColumnModel conditionNumberDisplayTableModel;
	TableColumnModel predictedValueDisplayTableModel;
	TableColumnModel varienceCovarienceMatrixDisplayTableModel;
	TableColumnModel summaryTableDisplayTableModel;
	TableColumnModel diagnosticsDisplayTableModel;
	TableColumnModel partialDerivativeDisplayTableModel;
	TableColumnModel userSettingsDisplayTableModel;
	TableColumnModel historyDisplayTableModel;

	String analysisType;

	public void inVitroResultDispCompCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {

		createDisplayResultInternalFrame();
	}

	public void removingExistingRowsOfOutputTables() {

		removeAllRowsOfTables(initialParameterDisplayTable);
		removeAllRowsOfTables(minimizationProcessDisplayTable);
		removeAllRowsOfTables(finalParametersHorizontalDisplayTable);
		removeAllRowsOfTables(finalparametersVerticalDisplayTable);
		removeAllRowsOfTables(conditionNumberDisplayTable);
		removeAllRowsOfTables(eigenValuesDisplayTable);
		removeAllRowsOfTables(summaryTableDisplayTable);
		removeAllRowsOfTables(predictedValueDisplayTable);
		removeAllRowsOfTables(partialDerivativeDisplayTable);
		removeAllRowsOfTables(historyDisplayTable);
		removeAllRowsOfTables(userSettingsDisplayTable);
		removeAllRowsOfTables(varienceCovarienceMatrixDisplayTable);
		removeAllRowsOfTables(correlationMatrixDisplayTable);
		removeAllRowsOfTables(diagnosticsDisplayTable);
		
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
		obsYVsWeightPredYLinarPlotAL = new ArrayList<JInternalFrame>();
		obsYVsWeightPredYLogPlotAL = new ArrayList<JInternalFrame>();
		weightPredYVsWeightResdYLinearPlotAL = new ArrayList<JInternalFrame>();
		weightPredYVsWeightResdYLogPlotAl = new ArrayList<JInternalFrame>();
		xVsWeightedResidualYLinearPlotAL = new ArrayList<JInternalFrame>();
		xVsWeightedResidualYLogPlotAL = new ArrayList<JInternalFrame>();
		partialDerivativeLinearPlotAL = new ArrayList<JInternalFrame>();
		partialDerivativeLogPlotAL = new ArrayList<JInternalFrame>();

	}

	public void createDisplayResultInternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		displayResultsInternalFrame = new JInternalFrame("Display", true, true,
				true, true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(displayResultsInternalFrame);

		displayResultsInternalFrame.setBorder(viewLayerInst.b);
		displayResultsInternalFrame
				.setLocation(
						0 + InVitroResultAvailCompDisplayer.createInVitroResAvailCompInst().resultsTabAvailableoutputInternalFrame
								.getWidth(), 0);

		int widthComp = 0;
		widthComp = InVitroMainScreenCreator.createInVitroMainScreenInstance().inVitroMainInternalFrame
					.getWidth();
		

		displayResultsInternalFrame
				.setSize(
						widthComp
								- InVitroResultAvailCompDisplayer
										.createInVitroResAvailCompInst().resultsTabAvailableoutputInternalFrame
										.getWidth(),
						InVitroResultAvailCompDisplayer.createInVitroResAvailCompInst().resultsTabAvailableoutputInternalFrame
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

	public void createInternalFrameForTableOutputDisplay() throws RowsExceededException,
			WriteException, BiffException, IOException {

		InVitroTabbedPanesCreator.createInVitroTabbedPaneInstance().resultsTabDesktopPane
				.add(displayResultsInternalFrame);

		finalParametersinternalFrame = createIntframeWithSettings(
				finalParametersinternalFrame, "Final Parameters");
		displayResultsinternalFrameDesktopPane
				.add(finalParametersinternalFrame);

		finalParamHorizontalDisplayInternalFrame = createIntframeWithSettings(
				finalParamHorizontalDisplayInternalFrame,
				"Final Parameter Horizantal Display");
		displayResultsinternalFrameDesktopPane
				.add(finalParamHorizontalDisplayInternalFrame);

		initialParametersinternalFrame = createIntframeWithSettings(
				initialParametersinternalFrame, "Initial Parameters");
		displayResultsinternalFrameDesktopPane
				.add(initialParametersinternalFrame);

		minimizationProcessInternalFrame = createIntframeWithSettings(
				minimizationProcessInternalFrame, "Minimization Process");
		displayResultsinternalFrameDesktopPane
				.add(minimizationProcessInternalFrame);
		minimizationProcessInternalFrame.moveToFront();

		correlationMatrixDisplayInternalFrame = createIntframeWithSettings(
				correlationMatrixDisplayInternalFrame,
				"Correlation Matrix Display");
		displayResultsinternalFrameDesktopPane
				.add(correlationMatrixDisplayInternalFrame);

		eigenValuesDisplayInternalFrame = createIntframeWithSettings(
				eigenValuesDisplayInternalFrame, "Eigen Values Display");
		displayResultsinternalFrameDesktopPane
				.add(eigenValuesDisplayInternalFrame);

		conditionNumberDisplayInternalFrame = createIntframeWithSettings(
				conditionNumberDisplayInternalFrame,
				"Conditional Number Display");
		displayResultsinternalFrameDesktopPane
				.add(conditionNumberDisplayInternalFrame);

		predictedValueDisplayInternalFrame = createIntframeWithSettings(
				predictedValueDisplayInternalFrame, "Predicted Value Display");
		displayResultsinternalFrameDesktopPane
				.add(predictedValueDisplayInternalFrame);

		varienceCovarienceMatrixDisplayInternalFrame = createIntframeWithSettings(
				varienceCovarienceMatrixDisplayInternalFrame,
				"VarCovar Matrix Display");
		displayResultsinternalFrameDesktopPane
				.add(varienceCovarienceMatrixDisplayInternalFrame);

		summaryTableDisplayInternalFrame = createIntframeWithSettings(
				summaryTableDisplayInternalFrame, "Summary Table Display");
		displayResultsinternalFrameDesktopPane
				.add(summaryTableDisplayInternalFrame);

		diagnosticsDisplayInternalFrame = createIntframeWithSettings(
				diagnosticsDisplayInternalFrame, "Diagnostics Display");
		displayResultsinternalFrameDesktopPane
				.add(diagnosticsDisplayInternalFrame);

		partialDerivativeDisplayInternalFrame = createIntframeWithSettings(
				partialDerivativeDisplayInternalFrame,
				"Partial Derivatives Display");
		displayResultsinternalFrameDesktopPane
				.add(partialDerivativeDisplayInternalFrame);

		
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
				.equalsIgnoreCase(" Initial_Parameter]")) {
			mainLayoutPageExportSettings();
			initialParametersinternalFrame.moveToFront();
			initialParametersinternalFrame
					.setSize(initialParametersinternalFrame.getSize());
		}else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Minimization_Process]")) {
			mainLayoutPageExportSettings();
			minimizationProcessInternalFrame.moveToFront();
			minimizationProcessInternalFrame
					.setSize(minimizationProcessInternalFrame.getSize());
		}else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Final_Parameters]")) {
			mainLayoutPageExportSettings();
			finalParamHorizontalDisplayInternalFrame.moveToFront();
			finalParamHorizontalDisplayInternalFrame
					.setSize(finalParamHorizontalDisplayInternalFrame.getSize());

		}else if (pathSplits[pathSplits.length - 1]
				.equals(" Non_Transposed_Final_Parameters]")) {
			mainLayoutPageExportSettings();
			finalParametersinternalFrame.moveToFront();
			finalParametersinternalFrame.setSize(finalParametersinternalFrame
					.getSize());

		}else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Correlation_Matrix]")) {
			mainLayoutPageExportSettings();
			correlationMatrixDisplayInternalFrame.moveToFront();
			correlationMatrixDisplayInternalFrame
					.setSize(correlationMatrixDisplayInternalFrame.getSize());

		}else if(pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Eigen_Values]")) {
			mainLayoutPageExportSettings();
			eigenValuesDisplayInternalFrame.moveToFront();
			eigenValuesDisplayInternalFrame
					.setSize(eigenValuesDisplayInternalFrame.getSize());

		}else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Condition_Number]")) {
			mainLayoutPageExportSettings();
			conditionNumberDisplayInternalFrame.moveToFront();
			conditionNumberDisplayInternalFrame
					.setSize(conditionNumberDisplayInternalFrame.getSize());

		}else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Predicted_Value]")) {
			mainLayoutPageExportSettings();
			predictedValueDisplayInternalFrame.moveToFront();
			predictedValueDisplayInternalFrame
					.setSize(predictedValueDisplayInternalFrame.getSize());

		}else if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" History]")) {
			mainLayoutPageExportSettings();
			historyDisplayInternalFrame.moveToFront();
			historyDisplayInternalFrame.setSize(historyDisplayInternalFrame
					.getSize());
		}else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" User_Settings]")) {
			mainLayoutPageExportSettings();
			userSettingsDisplayInternalFrame.moveToFront();
			userSettingsDisplayInternalFrame
					.setSize(userSettingsDisplayInternalFrame.getSize());

		}else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Partial_Derivative]")) {
			mainLayoutPageExportSettings();
			partialDerivativeDisplayInternalFrame.moveToFront();
			partialDerivativeDisplayInternalFrame
					.setSize(partialDerivativeDisplayInternalFrame.getSize());

		}else if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Diagnostics]")) {
			mainLayoutPageExportSettings();
			diagnosticsDisplayInternalFrame.moveToFront();
			diagnosticsDisplayInternalFrame
					.setSize(diagnosticsDisplayInternalFrame.getSize());

		}else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Summary_Table]")) {
			mainLayoutPageExportSettings();
			summaryTableDisplayInternalFrame.moveToFront();
			summaryTableDisplayInternalFrame
					.setSize(summaryTableDisplayInternalFrame.getSize());
		}else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Variance_CoVariance_Matrix]")) {
			mainLayoutPageExportSettings();
			varienceCovarienceMatrixDisplayInternalFrame.moveToFront();
			varienceCovarienceMatrixDisplayInternalFrame
					.setSize(varienceCovarienceMatrixDisplayInternalFrame
							.getSize());

		}else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Complete output]")) {
			DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

			viewLayerInst.createmenuBarInstance().exportTextoutput
					.setEnabled(true);
			viewLayerInst.createmenuBarInstance().exportImage.setEnabled(false);
			viewLayerInst.createmenuBarInstance().exportTable.setEnabled(false);
			
			textCompleteOutputInternalFrame.moveToFront();
			textCompleteOutputInternalFrame
					.setSize(textCompleteOutputInternalFrame.getSize());
			
			completeTextOutputTextArea
			.setCaretPosition(0);
			
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
		int colNo = noOfSortVariables + 5;
		String[] colName = new String[colNo];
		String[][] tempStr = InVitroResultAvailCompDisplayer.createInVitroResAvailCompInst()
				.readFile("finalParamTableColName.txt");
		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}
		for (int i = 0; i < 5; i++) {
			colName[i + noOfSortVariables] = tempStr[i][0];
		}

		tableHeaderInst.setVerticalParametersTH(colName);

		finalparametersVerticalDisplayTable = new JTable(0, colNo);
		createResultsTable(finalParametersinternalFrame,
				finalparametersVerticalDisplayTable,
				finalParametersVerticalDisplayTableModel, rowNo, colNo, colName);
	}

	private void createHorizontalFinalParamTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) {

		InVitroParamAndUnitListLoader paramListInst = InVitroParamAndUnitListLoader
				.createParamListInstance();
		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + 3
				* paramListInst.numberOfParameter;
		String[] colName = new String[colNo];
		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		for (int i = 0; i < paramListInst.numberOfParameter; i++) {

			colName[noOfSortVariables + 3 * i] = paramListInst
					.parameterNameForInVitro[i];
			colName[noOfSortVariables + 3 * i + 1] = paramListInst
					.parameterNameForInVitro[i]
					+ "_Std Error";
			colName[noOfSortVariables + 3 * i + 2] = paramListInst
					.parameterNameForInVitro[i]
					+ "CV%";
		}
		tableHeaderInst.setHorizontalParametersTH(colName);

		finalParametersHorizontalDisplayTable = new JTable(0, colNo);
		createResultsTable(finalParamHorizontalDisplayInternalFrame,
				finalParametersHorizontalDisplayTable,
				finalParametersHorizontalDisplayTableModel, rowNo, colNo,
				colName);

	}

	private void createInitialParamTable(ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) throws RowsExceededException,
			WriteException, BiffException, IOException {

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + 4;
		String[] colName = new String[colNo];
		String[][] tempStr = InVitroResultAvailCompDisplayer.createInVitroResAvailCompInst()
				.readFile("InitialParamTableColName.txt");

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		for (int i = 0; i < 4; i++) {
			colName[i + noOfSortVariables] = tempStr[i][0];
		}
		tableHeaderInst.setInitialParameterTH(colName);

		initialParameterDisplayTable = new JTable(0, colNo);
		createResultsTable(initialParametersinternalFrame,
				initialParameterDisplayTable,
				initialParameterDisplayTableModel, rowNo, colNo, colName);

	}

	private void createMinimizationProcessTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) {

		InVitroParamAndUnitListLoader paramListInst = InVitroParamAndUnitListLoader
		.createParamListInstance();

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + 2
				+ paramListInst.numberOfParameter;
		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}
		colName[noOfSortVariables] = "Iteration";
		colName[noOfSortVariables + 1] = "WeightedSumSquare";

		for (int i = 0; i < paramListInst.numberOfParameter; i++) {
			colName[noOfSortVariables + 2 + i] = paramListInst
					.parameterNameForInVitro[i];
		}
		tableHeaderInst.setMinimizationProcessTH(colName);

		minimizationProcessDisplayTable = new JTable(0, colNo);
		createResultsTable(minimizationProcessInternalFrame,
				minimizationProcessDisplayTable,
				minimizationProcessDisplayTableModel, rowNo, colNo, colName);
	}

	

	private void createCorrelationMatrixDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) {

		InVitroParamAndUnitListLoader paramListInst = InVitroParamAndUnitListLoader
		.createParamListInstance();
		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + 1
				+ paramListInst.numberOfParameter;
		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}
		colName[noOfSortVariables] = "Parameter";
		for (int i = 0; i < paramListInst.numberOfParameter; i++) {
			colName[noOfSortVariables + i + 1] = paramListInst
					.parameterNameForInVitro[i];
		}
		tableHeaderInst.setCorrelationMatrixTH(colName);

		correlationMatrixDisplayTable = new JTable(0, colNo);
		createResultsTable(correlationMatrixDisplayInternalFrame,
				correlationMatrixDisplayTable,
				correlationMatrixDisplayTableModel, rowNo, colNo, colName);

	}

	private void createEigenValuesDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) {

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + 2;
		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		colName[noOfSortVariables] = "Number";
		colName[noOfSortVariables + 1] = "Value";
		tableHeaderInst.setEigenValuesTH(colName);

		eigenValuesDisplayTable = new JTable(0, colNo);
		createResultsTable(eigenValuesDisplayInternalFrame,
				eigenValuesDisplayTable, eigenValuesDisplayTableModel, rowNo,
				colNo, colName);
	}

	private void createConditionNumberDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) {

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + 3;
		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		colName[noOfSortVariables] = "Iteration";
		colName[noOfSortVariables + 1] = "Rank";
		colName[noOfSortVariables + 2] = "Condition Number";
		tableHeaderInst.setConditionNumberTH(colName);

		conditionNumberDisplayTable = new JTable(0, colNo);
		createResultsTable(conditionNumberDisplayInternalFrame,
				conditionNumberDisplayTable, conditionNumberDisplayTableModel,
				rowNo, colNo, colName);
	}

	private void createPredictedValueDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) throws RowsExceededException,
			WriteException, BiffException, IOException {
		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		int colNo = noOfSortVariables + 2;
		String[][] tempStr = null;

		

			tempStr = InVitroResultAvailCompDisplayer
					.createInVitroResAvailCompInst()
					.readFile(
							"PredictedValueTableColName/PredictedValueTableColNameForInVitro.txt");
		

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

	private void createVarCovarMatrixDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) {
		
		InVitroParamAndUnitListLoader paramListInst = InVitroParamAndUnitListLoader
		.createParamListInstance();

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + 1
				+ paramListInst.numberOfParameter;
		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}
		colName[noOfSortVariables] = "Parameter";
		for (int i = 0; i < paramListInst.numberOfParameter; i++) {
			colName[i + 1 + noOfSortVariables] = paramListInst
					.parameterNameForInVitro[i];
		}
		tableHeaderInst.setVarienceCovarienceMatrixTH(colName);

		varienceCovarienceMatrixDisplayTable = new JTable(0, colNo);
		createResultsTable(varienceCovarienceMatrixDisplayInternalFrame,
				varienceCovarienceMatrixDisplayTable,
				varienceCovarienceMatrixDisplayTableModel, rowNo, colNo,
				colName);

	}

	private void createSummaryDisplayTable(ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) throws RowsExceededException,
			WriteException, BiffException, IOException {

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		int colNo = 0;
		String[][] tempStr = null;

		
			colNo = noOfSortVariables + 7;
			tempStr = InVitroResultAvailCompDisplayer.createInVitroResAvailCompInst()
					.readFile(
							"SymmaryTableColName/SummaryTableColNameForinVitro.txt");
		

		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		for (int i = 0; i < 7; i++) {
			colName[i + noOfSortVariables] = tempStr[i][0];
		}
		tableHeaderInst.setSummaryTableTH(colName);

		summaryTableDisplayTable = new JTable(0, colNo);
		createResultsTable(summaryTableDisplayInternalFrame,
				summaryTableDisplayTable, summaryTableDisplayTableModel, rowNo,
				colNo, colName);

	}

	private void createDiagnosticsDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) {

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + 2;
		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		colName[noOfSortVariables] = "Item";
		colName[noOfSortVariables + 1] = "Value";
		tableHeaderInst.setDiagnosticsTH(colName);

		diagnosticsDisplayTable = new JTable(0, colNo);
		createResultsTable(diagnosticsDisplayInternalFrame,
				diagnosticsDisplayTable, diagnosticsDisplayTableModel, rowNo,
				colNo, colName);

	}

	private void createPartialDerivativeDisplayTable(
			ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) {
		
		InVitroParamAndUnitListLoader paramListInst = InVitroParamAndUnitListLoader
		.createParamListInstance();

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + 1
				+ paramListInst.numberOfParameter;
		String[] colName = new String[colNo];

		for (int i = 0; i < noOfSortVariables; i++) {
			colName[i] = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		colName[noOfSortVariables] = "Time";
		for (int i = 0; i < paramListInst.numberOfParameter; i++) {
			colName[i + noOfSortVariables + 1] = paramListInst
					.parameterNameForInVitro[i];

		}

		tableHeaderInst.setPartialDerivativeTH(colName);

		partialDerivativeDisplayTable = new JTable(0, colNo);
		createResultsTable(partialDerivativeDisplayInternalFrame,
				partialDerivativeDisplayTable,
				partialDerivativeDisplayTableModel, rowNo, colNo, colName);

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

	private void createHistoryDisplayTable(ResultDisplayTablesHeader tableHeaderInst,
			ProcessingInputsInfo procInputInst) throws RowsExceededException,
			WriteException, BiffException, IOException {

		int rowNo = 0;
		int noOfSortVariables = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();
		int colNo = noOfSortVariables + 7;
		String[] colName = new String[colNo];
		String[][] tempStr = InVitroResultAvailCompDisplayer.createInVitroResAvailCompInst()
				.readFile("HistoryTableColName.txt");

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

		CaNoOfSubjectDeterminer determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		int noOfProfiles = 0;
		

		noOfProfiles = procInputInst.getProfileAndParamInfoObj()
				.getNoOfSubject();
		DefaultTreeModel model = (DefaultTreeModel) InVitroResultAvailCompDisplayer
				.createInVitroResAvailCompInst().availableOutputsTree.getModel();

		DefaultMutableTreeNode plots = new DefaultMutableTreeNode("plots");
		InVitroResultAvailCompDisplayer.createInVitroResAvailCompInst().availableOutputs
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
				"Observed vs Weighted Predicted Y");
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
				"Weighted Predicted Y vs Weighted Residual Y");
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

		DefaultMutableTreeNode XvsWeightedResidualY = new DefaultMutableTreeNode(
				"X vs Weighted Residual Y");
		plots.add(XvsWeightedResidualY);
		DefaultMutableTreeNode linPlot3 = new DefaultMutableTreeNode("Lin Plot");

		XvsWeightedResidualY.add(linPlot3);
		for (int i = 0; i < noOfProfiles; i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			linPlot3.add(plotNode);
		}

		DefaultMutableTreeNode logPlot3 = new DefaultMutableTreeNode("Log Plot");

		XvsWeightedResidualY.add(logPlot3);
		for (int i = 0; i < noOfProfiles; i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			logPlot3.add(plotNode);
		}

		DefaultMutableTreeNode partialDerivativeNode = new DefaultMutableTreeNode(
				"Partial Derivative Plot");
		plots.add(partialDerivativeNode);
		DefaultMutableTreeNode linPlot4 = new DefaultMutableTreeNode("Lin Plot");

		partialDerivativeNode.add(linPlot4);
		for (int i = 0; i < noOfProfiles; i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			linPlot4.add(plotNode);
		}
		DefaultMutableTreeNode logPlot4 = new DefaultMutableTreeNode("Log Plot");

		partialDerivativeNode.add(logPlot4);
		for (int i = 0; i < noOfProfiles; i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			logPlot4.add(plotNode);
		}
		model.reload();
	}

	public void removePreviousResults() throws RowsExceededException,
			WriteException, BiffException, IOException {

		completeTextOutputTextArea.setText("");

		((DefaultTableModel) userSettingsDisplayTable.getModel())
				.getDataVector().removeAllElements();

		while (userSettingsDisplayTable.getRowCount() != 0) {
			((DefaultTableModel) userSettingsDisplayTable.getModel())
					.removeRow(0);
		}

		DefaultTreeModel model = (DefaultTreeModel) InVitroResultAvailCompDisplayer
				.createInVitroResAvailCompInst().availableOutputsTree.getModel();

		TreePath plotsPath = InVitroResultAvailCompDisplayer.createInVitroResAvailCompInst().availableOutputsTree
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

			for (int i = 0; i < obsYVsWeightPredYLinarPlotAL
					.size(); i++) {
				obsYVsWeightPredYLinarPlotAL.get(i)
						.dispose();
			}

			for (int i = 0; i < obsYVsWeightPredYLogPlotAL
					.size(); i++) {
				obsYVsWeightPredYLogPlotAL
						.get(i).dispose();
			}

			for (int i = 0; i < weightPredYVsWeightResdYLinearPlotAL
					.size(); i++) {
				weightPredYVsWeightResdYLinearPlotAL
						.get(i).dispose();
			}

			for (int i = 0; i < weightPredYVsWeightResdYLogPlotAl
					.size(); i++) {
				weightPredYVsWeightResdYLogPlotAl
						.get(i).dispose();
			}

			for (int i = 0; i < xVsWeightedResidualYLinearPlotAL
					.size(); i++) {
				xVsWeightedResidualYLinearPlotAL.get(i).dispose();
			}

			for (int i = 0; i < xVsWeightedResidualYLogPlotAL
					.size(); i++) {
				xVsWeightedResidualYLogPlotAL.get(i)
						.dispose();
			}

			for (int i = 0; i < partialDerivativeLinearPlotAL.size(); i++) {
				partialDerivativeLinearPlotAL.get(i).dispose();
			}

			for (int i = 0; i < partialDerivativeLogPlotAL
					.size(); i++) {
				partialDerivativeLogPlotAL.get(i)
						.dispose();
			}

		
		}
	}

	public void createTablesToFillResult() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		
		ResultDisplayTablesHeader tableHeaderInst = new ResultDisplayTablesHeader();

		createVerticalFinalParamTable(tableHeaderInst, procInputInst);
		createHorizontalFinalParamTable(tableHeaderInst, procInputInst);
		createInitialParamTable(tableHeaderInst, procInputInst);
		createMinimizationProcessTable(tableHeaderInst, procInputInst);
		createCorrelationMatrixDisplayTable(tableHeaderInst, procInputInst);
		createEigenValuesDisplayTable(tableHeaderInst, procInputInst);
		createConditionNumberDisplayTable(tableHeaderInst, procInputInst);
		createPredictedValueDisplayTable(tableHeaderInst, procInputInst);
		createVarCovarMatrixDisplayTable(tableHeaderInst, procInputInst);
		createSummaryDisplayTable(tableHeaderInst, procInputInst);
		createDiagnosticsDisplayTable(tableHeaderInst, procInputInst);
		createPartialDerivativeDisplayTable(tableHeaderInst, procInputInst);
		createUserSettingsDisplayTable(tableHeaderInst, procInputInst);
		createHistoryDisplayTable(tableHeaderInst, procInputInst);

		procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		procInputInst.setResultDisplayTabllsHeaderObj(tableHeaderInst);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	
	}

	public void displayProcessResults() throws RowsExceededException, WriteException,
			BiffException, IOException {
		if (InVitroResultAvailCompDisplayer.createInVitroResAvailCompInst().availableOutputsTree
				.getSelectionPath() != null) {
			
			String plotName = InVitroResultAvailCompDisplayer.createInVitroResAvailCompInst().availableOutputsTree
					.getSelectionPath().getLastPathComponent().toString();
			
			String[] pathSplits = InVitroResultAvailCompDisplayer
					.createInVitroResAvailCompInst().availableOutputsTree
					.getSelectionPath().toString().split(",");
			
			String[] plotNameSplits = plotName.split(" ");

			if ((pathSplits.length - 3) >= 0
					&& pathSplits[pathSplits.length - 3]
							.equalsIgnoreCase(" X vs Observed and Predicted Y")) {

				displayResultPlots(pathSplits, plotNameSplits,
						xVsObsYAndPredYLogPlotAL,
						xVsObsYAndPredYLinearPlotAL);
			}

			if ((pathSplits.length - 3) >= 0
					&& pathSplits[pathSplits.length - 3]
							.equalsIgnoreCase(" Observed vs Weighted Predicted Y")) {
				displayResultPlots(pathSplits, plotNameSplits,
						obsYVsWeightPredYLinarPlotAL,
						obsYVsWeightPredYLogPlotAL);
			}

			if ((pathSplits.length - 3) >= 0
					&& pathSplits[pathSplits.length - 3]
							.equalsIgnoreCase(" Weighted Predicted Y vs Weighted Residual Y")) {
				displayResultPlots(
						pathSplits,
						plotNameSplits,
						weightPredYVsWeightResdYLinearPlotAL,
						weightPredYVsWeightResdYLogPlotAl);
			}

			if ((pathSplits.length - 3) >= 0
					&& pathSplits[pathSplits.length - 3]
							.equalsIgnoreCase(" X vs Weighted Residual Y")) {
				displayResultPlots(pathSplits, plotNameSplits,
						xVsWeightedResidualYLinearPlotAL,
						xVsWeightedResidualYLogPlotAL);
			}

			if ((pathSplits.length - 3) >= 0
					&& pathSplits[pathSplits.length - 3]
							.equalsIgnoreCase(" Partial Derivative Plot")) {
				displayResultPlots(pathSplits, plotNameSplits,
						partialDerivativeLinearPlotAL,
						partialDerivativeLogPlotAL);
			}

			determineInternalFrameForResultDisplay(pathSplits);

		}
	}


}
