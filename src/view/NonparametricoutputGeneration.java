package view;

import java.awt.Dimension;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.DataManipulationFunctions;
import Model.NonParametricSuperposition;

public class NonparametricoutputGeneration {

	public static NonparametricoutputGeneration NPS_OUT_GEN = null;

	public static NonparametricoutputGeneration createNpsOutGenInst() {
		if (NPS_OUT_GEN == null) {
			NPS_OUT_GEN = new NonparametricoutputGeneration(
					"just output generation");
		}
		return NPS_OUT_GEN;
	}

	public NonparametricoutputGeneration(String dummy) {
	}

	public void npsOutputGeneration() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		createTablesToFillData();
		remoVePreviousResults();
		populateAvailableResultsWithPlots();
		NPSMainPage.createNPSMainPageInst().changeOptionsFrame();
		setToView();
		MakeCopyOfOutputsIntoExportToPdfInfo();

	}

	private void MakeCopyOfOutputsIntoExportToPdfInfo()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		addSpreadSheetsToExportToPdf();

	}

	private void addSpreadSheetsToExportToPdf() throws RowsExceededException,
			WriteException, BiffException, IOException {
		addVerticalParameters();

	}

	private void addVerticalParameters() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int rows = NPSResultsDispComp.createNPSResDispCompInst().finalparametersVerticalDisplayTable
				.getRowCount();
		int columns = NPSResultsDispComp.createNPSResDispCompInst().finalparametersVerticalDisplayTable
				.getColumnCount();
		String[][] data = new String[rows + 1][columns];

		// add headers as ist row
		TableColumnModel tcm = NPSResultsDispComp.createNPSResDispCompInst().finalparametersVerticalDisplayTable
				.getColumnModel();

		for (int i = 0; i < columns; i++) {
			TableColumn tc = tcm.getColumn(i);
			data[0][i] = tc.getHeaderValue().toString();
		}

		// rest of the table data into the remaining rows
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				
				try {
					data[i][j] = NPSResultsDispComp.createNPSResDispCompInst().finalparametersVerticalDisplayTable
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
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getExpToPdf()
				.setVerticalParameters(data);
	}

	private void setToView() {
		setToTextOutput();
		setToSpreadSheets();

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

	private void setToTextOutput() {
	}

	private void setToSpreadSheets() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int concOptDec =  appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo().getProcessingInputs()
								.getMappingDataObj().getyColumnCorrespondinOriginalIndex())
								.getOptionalDecimals();
		String concReqDec =  appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo().getProcessingInputs()
								.getMappingDataObj().getyColumnCorrespondinOriginalIndex())
								.getRequiredDecimals();
		

		int timeoptdec = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo().getProcessingInputs()
								.getMappingDataObj().getxColumnCorrespondinOriginalIndex())
								.getOptionalDecimals();
		
		String timeReqDec = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo().getProcessingInputs()
								.getMappingDataObj().getxColumnCorrespondinOriginalIndex())
								.getRequiredDecimals();
		
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
		
		String[][] outputValues = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getVerticalParameters();

		int noSort = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo().getProcessingInputs().getMappingDataObj()
								.getSortVariablesListVector().size();
		for (int i = 0; i < outputValues.length; i++) {
			String s[] = new String[outputValues[i].length];
			for (int j = 0; j < outputValues[i].length; j++) {
				try {
					
					if(j==noSort){
						s[j] = performFormatting(Double
								.parseDouble(outputValues[i][j]), timeoptdec, timeReqDec);
					} else{
						s[j] = performFormatting(Double
								.parseDouble(outputValues[i][j]), concOptDec, concReqDec);
					}
				
				} catch (Exception e) {
					s[j] = outputValues[i][j];
				}

			}
			int rowCount = NPSResultsDispComp.createNPSResDispCompInst().finalparametersVerticalDisplayTable
					.getRowCount();
			((DefaultTableModel) NPSResultsDispComp.createNPSResDispCompInst().finalparametersVerticalDisplayTable
					.getModel()).insertRow(rowCount, s);
		}
	}

	void populateAvailableResultsWithPlots() {

		DefaultTreeModel model = (DefaultTreeModel) NPSResultsAvailComp
				.createNPSResAvailInst().availableOutputsTree.getModel();

		DefaultMutableTreeNode plots = new DefaultMutableTreeNode("plots");
		NPSResultsAvailComp.createNPSResAvailInst().availableOutputs.add(plots);
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DefaultMutableTreeNode plotsTimeVSConc = new DefaultMutableTreeNode(
				"Time vs Conc");
		plots.add(plotsTimeVSConc);
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo()
				.getWorkSheetOutputs().getPlotOutputs().getLinearPlots().size(); i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			plotsTimeVSConc.add(plotNode);
		}

		model.reload();

	}

	void remoVePreviousResults() {
		
		DefaultTreeModel model = (DefaultTreeModel) NPSResultsAvailComp
				.createNPSResAvailInst().availableOutputsTree.getModel();
		TreePath plotsPath = NPSResultsAvailComp.createNPSResAvailInst().availableOutputsTree
				.getNextMatch("plots", 0, Position.Bias.Forward);
		if (plotsPath == null) {
		} else {
			MutableTreeNode mNode = (MutableTreeNode) plotsPath
					.getLastPathComponent();
			model.removeNodeFromParent(mNode);
		}
	}

	void createTablesToFillData() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NPSResultsDispComp.createNPSResDispCompInst().finalParametersinternalFrame
				.getContentPane().removeAll();
		NPSResultsDispComp.createNPSResDispCompInst().finalparametersVerticalDisplayTable = new JTable(
				0, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNpsInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() + 2);
		NPSResultsDispComp.createNPSResDispCompInst().finalparametersVerticalDisplayTable
				.setPreferredScrollableViewportSize(new Dimension(
						NPSResultsDispComp.createNPSResDispCompInst().displayResultsInternalFrame
								.getWidth(),
						NPSResultsDispComp.createNPSResDispCompInst().displayResultsInternalFrame
								.getHeight()));
		NPSResultsDispComp.createNPSResDispCompInst().finalparametersVerticalDisplayTable
				.setFillsViewportHeight(true);
		NPSResultsDispComp.createNPSResDispCompInst().finalparametersVerticalDisplayTable
				.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader finalParametersVerticalDisplayTableHeader = NPSResultsDispComp
				.createNPSResDispCompInst().finalparametersVerticalDisplayTable
				.getTableHeader();
		NPSMainPage.createNPSMainPageInst().finalParametersVerticalDisplayTableModel = NPSResultsDispComp
				.createNPSResDispCompInst().finalparametersVerticalDisplayTable
				.getColumnModel();
		TableColumn tc = null;
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			tc = NPSMainPage.createNPSMainPageInst().finalParametersVerticalDisplayTableModel
					.getColumn(i);
			tc.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i));
		}

		tc = NPSMainPage.createNPSMainPageInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNpsInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size());

		tc
				.setHeaderValue("time"
						+ "("
						+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getColumnPropertiesArrayList()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getWorkSheetObjectsAL()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())
												.getNpsInfo()
												.getProcessingInputs()
												.getMappingDataObj()
												.getxColumnCorrespondinOriginalIndex())
								.getUnitBuilderDataObj().getTimeUnits() + ")"
						+ "\t");

		String massPrefix = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNpsInfo().getProcessingInputs()
								.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex())
				.getUnitBuilderDataObj().getMasPrefixIndex();
		String massUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNpsInfo().getProcessingInputs()
								.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex())
				.getUnitBuilderDataObj().getMassunitIndex();
		String volumePrefix = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNpsInfo().getProcessingInputs()
								.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex())
				.getUnitBuilderDataObj().getVolumePrefixIndex();

		String volumeUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNpsInfo().getProcessingInputs()
								.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex())
				.getUnitBuilderDataObj().getVolumeUnitindex();

		tc = NPSMainPage.createNPSMainPageInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNpsInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() + 1);
		tc.setHeaderValue("Predicted Conc" + "(" + massPrefix + massUnit + "/"
				+ volumePrefix + volumeUnit + ")" + "\t");

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				NPSResultsDispComp.createNPSResDispCompInst().finalparametersVerticalDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						NPSResultsDispComp.createNPSResDispCompInst().finalParametersinternalFrame
								.getWidth(),
						NPSResultsDispComp.createNPSResDispCompInst().finalParametersinternalFrame
								.getHeight()));
		NPSResultsDispComp.createNPSResDispCompInst().finalParametersinternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);

	}

}
