package view;

import java.awt.Dimension;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.DataManipulationFunctions;
import Common.ThousandsSeperator;

public class DesStatsoutputGeneration {

	public static DesStatsoutputGeneration DES_STATOUT_GEN = null;

	public static DesStatsoutputGeneration createDesStatOutInst() {
		if (DES_STATOUT_GEN == null) {
			DES_STATOUT_GEN = new DesStatsoutputGeneration(
					"just object creation");
		}
		return DES_STATOUT_GEN;
	}

	JScrollPane finalParametersVerticalDisplayScrollPane;
	
	public DesStatsoutputGeneration(String dummy) {

	}

	void desStatOutGeneration() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		remoVePreviousResults();
		createTablesToFillData();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.setAnalysisExecuted(true);
		setoutputToView();
		MakeCopyOfOutputsIntoExportToPdfInfo();
		fixSortVarColumnFinalTable();
		
	}

	private void fixSortVarColumnFinalTable() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int noSort = appInfo
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
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() - 1;

		FixedColumnTable.createFixedColumnTableInst().FixedColumnTable(noSort, finalParametersVerticalDisplayScrollPane);
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
		int rows = DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
				.getRowCount();
		int columns = DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
				.getColumnCount();
		String[][] data = new String[rows + 1][columns];

		// add headers as ist row
		TableColumnModel tcm = DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
				.getColumnModel();

		for (int i = 0; i < columns; i++) {
			TableColumn tc = tcm.getColumn(i);
			data[0][i] = tc.getHeaderValue().toString();
		}

		// rest of the table data into the remaining rows
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.println("i = " + i + " and j = " + j);
				try {
					data[i][j] = DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
							.getValueAt(i - 1, j).toString();
				} catch (Exception e) {
					data[i][j] = "";
				}

			}
		}

		// set the data to appInfo
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
				.getProcessingInputs().getExpToPdf()
				.setVerticalParameters(data);
	}

	private void setoutputToView() {

		// setTextoutput();
		setSpreadSheetOutput();

	}

	private void setSpreadSheetOutput() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String plasmaColName =  appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
								.getProcessingInputs()
								.getMappingDataObj().getCarryAlongVariablesListVector()
								.get(0);
		
		int plasmaColoriginalindex = 0;
		for(int i=0;i< appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList().size();i++ ){
			if(plasmaColName.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList().get(i).getColumnName())){
				plasmaColoriginalindex = i;
				break;
			}
		}
		
		int timeorgIndex = determineTimeOriginalIndex();
		
		int concOptDec = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList()
								.get(plasmaColoriginalindex).getOptionalDecimals();
		
		String concReqDec = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList()
								.get(plasmaColoriginalindex).getRequiredDecimals();
		
		int timeoptDec = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList()
								.get(timeorgIndex).getOptionalDecimals();
		
		String timeReqDec = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList()
								.get(timeorgIndex).getRequiredDecimals();
		
		if((timeoptDec == 0)&&(timeReqDec.equals(""))){
			timeReqDec = "0.0000";
		}
		if((timeoptDec == 0)&&(timeReqDec.equals("0."))){
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
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getVerticalParameters();
		
		int noSort = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo().getProcessingInputs()
								.getMappingDataObj().getSortVariablesListVector().size();

		
		for (int i = 0; i < outputValues.length; i++) {
			String s[] = new String[outputValues[i].length];
			for (int j = 0; j < outputValues[i].length; j++) {
				try {
					
					if(j == (noSort-1)){
						s[j] = ThousandsSeperator.createThouSepInst().insertCommas(
								performFormatting(Double
										.parseDouble(outputValues[i][j]), timeoptDec,
										timeReqDec));
					} else {
						
						s[j] = ThousandsSeperator.createThouSepInst().insertCommas(
								performFormatting(Double
										.parseDouble(outputValues[i][j]), concOptDec,
										concReqDec));
					}
					
				} catch (Exception e) {
					s[j] = outputValues[i][j];
				}
			}
			int rowCount = DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
					.getRowCount();
			((DefaultTableModel) DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
					.getModel()).insertRow(rowCount, s);
		}

	}

	private int determineTimeOriginalIndex() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String lastSortColname = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo().getProcessingInputs()
								.getMappingDataObj().getSortVariablesListVector().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo().getProcessingInputs()
								.getMappingDataObj().getSortVariablesListVector().size()-1);
		
		int timeColOriginalIndex = 0;
		for(int i=0;i< appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList().size();i++ ){
			if(lastSortColname.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getColumnPropertiesArrayList().get(i).getColumnName())){
				timeColOriginalIndex = i;
				break;
			}
		}
	
		return timeColOriginalIndex;
	}

	String performFormatting(double value, int optDec, String reqDec) {
		String valueStr = new String();

		if (optDec == 0) {
			if (reqDec.equals("")) {
				valueStr = value + "";
			} else {
				DecimalFormat dec = new DecimalFormat(reqDec);
				dec.setRoundingMode(RoundingMode.DOWN);
				valueStr = dec.format(value);
			}
		} else if (reqDec.equals("")) {
			if (optDec != 0) {

				valueStr = DataManipulationFunctions.createDataManFuncInst()
						.truncateDouble(value, optDec)
						+ "";

			} else if (optDec == 0) {
				valueStr = value + "";
			}
		}
		return valueStr;
	}

	/*
	 * private void setTextoutput() { ApplicationInfo appInfo =
	 * DisplayContents.createAppInfoInstance(); ArrayList<String> textoutput =
	 * appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).
	 * getWorkSheetObjectsAL
	 * ().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getSelectedWorkBookIndex
	 * ()).getSelectedSheetIndex())
	 * .getDesStatsInfo().getWorkSheetOutputs().getTextoutput();
	 * 
	 * for(int i=0;i<textoutput.size();i++){
	 * DesStatResDispComp.createDesStatResDispInst
	 * ().completeTextOutputTextArea.append(textoutput.get(i)+"\n");
	 * 
	 * }
	 * 
	 * }
	 */

	void remoVePreviousResults() {

		// DesStatResDispComp.createDesStatResDispInst().completeTextOutputTextArea.setText("");

	}

	void createTablesToFillData() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DesStatResDispComp.createDesStatResDispInst().finalParametersinternalFrame
				.getContentPane().removeAll();
		DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable = new JTable(
				0, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 18);

		DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
				.setPreferredScrollableViewportSize(new Dimension(
						DesStatResDispComp.createDesStatResDispInst().displayResultsInternalFrame
								.getWidth(),
						DesStatResDispComp.createDesStatResDispInst().displayResultsInternalFrame
								.getHeight()));
		DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
				.setFillsViewportHeight(true);
		DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
				.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader finalParametersVerticalDisplayTableHeader = DesStatResDispComp
				.createDesStatResDispInst().finalparametersVerticalDisplayTable
				.getTableHeader();
		DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel = DesStatResDispComp
				.createDesStatResDispInst().finalparametersVerticalDisplayTable
				.getColumnModel();
		TableColumn tc = null;
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() - 1; i++) {
			tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
					.getColumn(i);
			tc.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i));
		}

		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() - 1);
		tc
				.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo()
						.getProcessingInputs()
						.getMappingDataObj()
						.getSortVariablesListVector()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getDesStatsInfo()
										.getProcessingInputs()
										.getMappingDataObj()
										.getSortVariablesListVector().size() - 1)
						+ "("
						+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getDesStatsInfo()
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
												.getDesStatsInfo()
												.getProcessingInputs()
												.getMappingDataObj()
												.getyColumnCorrespondinOriginalIndex())
								.getUnitBuilderDataObj().getTimeUnits()
						+ ")"
						+ "\t");

		String massPrefix = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getDesStatsInfo()
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
								.getDesStatsInfo().getProcessingInputs()
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
				.getDesStatsInfo()
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
								.getDesStatsInfo().getProcessingInputs()
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
				.getDesStatsInfo()
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
								.getDesStatsInfo().getProcessingInputs()
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
				.getDesStatsInfo()
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
								.getDesStatsInfo().getProcessingInputs()
								.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex())
				.getUnitBuilderDataObj().getVolumeUnitindex();

		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 0);
		tc.setHeaderValue("Nobs" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 1);
		tc.setHeaderValue("mean" + "(" + massPrefix + massUnit + "/"
				+ volumePrefix + volumeUnit + ")" + "\t");

		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 2);
		tc.setHeaderValue("SD" + "(" + massPrefix + massUnit + "/"
				+ volumePrefix + volumeUnit + ")" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 3);
		tc.setHeaderValue("SE" + "(" + massPrefix + massUnit + "/"
				+ volumePrefix + volumeUnit + ")" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 4);
		tc.setHeaderValue("Varience" + "(" + massPrefix + massUnit + "/"
				+ volumePrefix + volumeUnit + ")" + "*" + "(" + massPrefix
				+ massUnit + "/" + volumePrefix + volumeUnit + ")" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 5);
		tc.setHeaderValue("Min" + "(" + massPrefix + massUnit + "/"
				+ volumePrefix + volumeUnit + ")" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 6);
		tc.setHeaderValue("Median" + "(" + massPrefix + massUnit + "/"
				+ volumePrefix + volumeUnit + ")" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 7);
		tc.setHeaderValue("Max" + "(" + massPrefix + massUnit + "/"
				+ volumePrefix + volumeUnit + ")" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 8);
		tc.setHeaderValue("Range" + "(" + massPrefix + massUnit + "/"
				+ volumePrefix + volumeUnit + ")" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 9);
		tc.setHeaderValue("CV%" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 10);
		tc.setHeaderValue("Geometric Mean" + "(" + massPrefix + massUnit + "/"
				+ volumePrefix + volumeUnit + ")" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 11);
		tc.setHeaderValue("Harmonic Mean" + "(" + massPrefix + massUnit + "/"
				+ volumePrefix + volumeUnit + ")" + "\t");

		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 12);
		tc.setHeaderValue("Pseudo SD" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 13);
		tc.setHeaderValue("Mean Log" + "(" + massPrefix + massUnit + "/"
				+ volumePrefix + volumeUnit + ")" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 14);
		tc.setHeaderValue("SD Log" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 15);
		tc.setHeaderValue("CV% Geometric Mean" + "\t");

		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 16);
		tc.setHeaderValue("Skewness" + "\t");
		tc = DesStatResDispComp.createDesStatResDispInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size() + 17);
		tc.setHeaderValue("Kurtosis" + "\t");

		finalParametersVerticalDisplayScrollPane = new JScrollPane(
				DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable);
		
		finalParametersVerticalDisplayScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		finalParametersVerticalDisplayScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						DesStatResDispComp.createDesStatResDispInst().finalParametersinternalFrame
								.getWidth(),
						DesStatResDispComp.createDesStatResDispInst().finalParametersinternalFrame
								.getHeight()));
		DesStatResDispComp.createDesStatResDispInst().finalParametersinternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);

	}

}
