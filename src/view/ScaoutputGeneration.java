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
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.jfree.chart.JFreeChart;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.DataManipulationFunctions;

public class ScaoutputGeneration {

	public static ScaoutputGeneration SCA_OUT_GEN = null;

	public static ScaoutputGeneration createScaoutInst() {
		if (SCA_OUT_GEN == null) {
			SCA_OUT_GEN = new ScaoutputGeneration("just object creation");
		}
		return SCA_OUT_GEN;
	}

	public ScaoutputGeneration(String dummy) {

	}

	void scaoutGeneration() throws RowsExceededException, WriteException,
			BiffException, IOException {

		remoVePreviousResults();
		populateAvailableResultsWithPlots();
		createTablesToFillData();
		setOutputToView();
		MakeCopyOfOutputsIntoExportToPdfInfo();
	}

	private void setOutputToView() {
		setSpreadSheets();
		setTextoutput();

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
		int rows = ScaResultsDispComp.createScaResDispCompInst().finalparametersVerticalDisplayTable
				.getRowCount();
		int columns = ScaResultsDispComp.createScaResDispCompInst().finalparametersVerticalDisplayTable
				.getColumnCount();
		String[][] data = new String[rows + 1][columns];

		// add headers as ist row
		TableColumnModel tcm = ScaResultsDispComp.createScaResDispCompInst().finalparametersVerticalDisplayTable
				.getColumnModel();

		for (int i = 0; i < columns; i++) {
			TableColumn tc = tcm.getColumn(i);
			data[0][i] = tc.getHeaderValue().toString();
		}

		// rest of the table data into the remaining rows
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				
				try {
					data[i][j] = ScaResultsDispComp.createScaResDispCompInst().finalparametersVerticalDisplayTable
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
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getExpToPdf()
				.setVerticalParameters(data);
	}

	private void setTextoutput() {
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

	private void setSpreadSheets() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		int concOptDec = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo().getColumnPropertiesArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo().getProcessingInputs().getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex()).getOptionalDecimals();
		
		String concReqDec = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo().getColumnPropertiesArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo().getProcessingInputs().getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex()).getRequiredDecimals();
		

		
		
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getWorkSheetOutputs().getSpreadSheetOutputs()
				.getVerticalParameters().length; i++) {
			String[] s = new String[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getWorkSheetOutputs().getSpreadSheetOutputs()
					.getVerticalParameters()[i].length];

			int timeoptdec =  appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo().getColumnPropertiesArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo().getProcessingInputs().getMappingDataObj()
									.getxColumnCorrespondinOriginalIndex()).getOptionalDecimals();
			
			String timeReqDec =  appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo().getColumnPropertiesArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo().getProcessingInputs().getMappingDataObj()
									.getxColumnCorrespondinOriginalIndex()).getRequiredDecimals();
			
		int noSort =  appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo().getProcessingInputs()
								.getMappingDataObj().getSortVariablesListVector().size();
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
			
			
			for (int j = 0; j < s.length; j++) {
				try {
					
					if(j==noSort){
						s[j] = performFormatting(
								Double
										.parseDouble(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getWorkSheetObjectsAL()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())
												.getScaInfo().getWorkSheetOutputs()
												.getSpreadSheetOutputs()
												.getVerticalParameters()[i][j]),
								timeoptdec, timeReqDec);
					} else {
						s[j] = performFormatting(
								Double
										.parseDouble(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getWorkSheetObjectsAL()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())
												.getScaInfo().getWorkSheetOutputs()
												.getSpreadSheetOutputs()
												.getVerticalParameters()[i][j]),
								concOptDec, concReqDec);
					}
			
				} catch (Exception w) {
					s[j] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getScaInfo().getWorkSheetOutputs()
							.getSpreadSheetOutputs().getVerticalParameters()[i][j];
				}

			}

			int rowCount = ScaResultsDispComp.createScaResDispCompInst().finalparametersVerticalDisplayTable
					.getRowCount();
			((DefaultTableModel) ScaResultsDispComp.createScaResDispCompInst().finalparametersVerticalDisplayTable
					.getModel()).insertRow(rowCount, s);
		}

	}

	void createTablesToFillData() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ScaResultsDispComp.createScaResDispCompInst().finalParametersinternalFrame
				.getContentPane().removeAll();
		ScaResultsDispComp.createScaResDispCompInst().finalparametersVerticalDisplayTable = new JTable(
				0, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getScaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() + 3);
		ScaResultsDispComp.createScaResDispCompInst().finalparametersVerticalDisplayTable
				.setPreferredScrollableViewportSize(new Dimension(
						ScaResultsDispComp.createScaResDispCompInst().displayResultsInternalFrame
								.getWidth(),
						ScaResultsDispComp.createScaResDispCompInst().displayResultsInternalFrame
								.getHeight()));
		ScaResultsDispComp.createScaResDispCompInst().finalparametersVerticalDisplayTable
				.setFillsViewportHeight(true);
		ScaResultsDispComp.createScaResDispCompInst().finalparametersVerticalDisplayTable
				.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader finalParametersVerticalDisplayTableHeader = ScaResultsDispComp
				.createScaResDispCompInst().finalparametersVerticalDisplayTable
				.getTableHeader();
		ScaResultsDispComp.createScaResDispCompInst().finalParametersVerticalDisplayTableModel = ScaResultsDispComp
				.createScaResDispCompInst().finalparametersVerticalDisplayTable
				.getColumnModel();
		TableColumn tc = null;
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			tc = ScaResultsDispComp.createScaResDispCompInst().finalParametersVerticalDisplayTableModel
					.getColumn(i);
			tc.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i));
		}

		ColumnProperties obj1 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
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
								.getScaInfo().getProcessingInputs()
								.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex());

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
								.getScaInfo().getProcessingInputs()
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
								.getScaInfo().getProcessingInputs()
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
								.getScaInfo().getProcessingInputs()
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
								.getScaInfo().getProcessingInputs()
								.getMappingDataObj()
								.getyColumnCorrespondinOriginalIndex())
				.getUnitBuilderDataObj().getVolumeUnitindex();

		tc = ScaResultsDispComp.createScaResDispCompInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getScaInfo()
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
												.getScaInfo()
												.getProcessingInputs()
												.getMappingDataObj()
												.getxColumnCorrespondinOriginalIndex())
								.getUnitBuilderDataObj().getTimeUnits() + ")"
						+ "\t");

		tc = ScaResultsDispComp.createScaResDispCompInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getScaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() + 1);
		tc.setHeaderValue("Conc" + "(" + massPrefix + massUnit + "/"
				+ volumePrefix + volumeUnit + ")" + "\t");
		tc = ScaResultsDispComp.createScaResDispCompInst().finalParametersVerticalDisplayTableModel
				.getColumn(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getScaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() + 2);
		tc.setHeaderValue("Effect-Site Conc" + "(" + massPrefix + massUnit
				+ "/" + volumePrefix + volumeUnit + ")" + "\t");

		JScrollPane finalParametersVerticalDisplayScrollPane = new JScrollPane(
				ScaResultsDispComp.createScaResDispCompInst().finalparametersVerticalDisplayTable);
		finalParametersVerticalDisplayScrollPane
				.setPreferredSize(new Dimension(
						ScaResultsDispComp.createScaResDispCompInst().finalParametersinternalFrame
								.getWidth(),
						ScaResultsDispComp.createScaResDispCompInst().finalParametersinternalFrame
								.getHeight()));
		ScaResultsDispComp.createScaResDispCompInst().finalParametersinternalFrame
				.getContentPane().add(finalParametersVerticalDisplayScrollPane);
	}

	void remoVePreviousResults() {

		DefaultTreeModel model = (DefaultTreeModel) ScaResultsAvailComp
				.createSetResAvailInst().availableOutputsTree.getModel();
		TreePath plotsPath = ScaResultsAvailComp.createSetResAvailInst().availableOutputsTree
				.getNextMatch("plots", 0, Position.Bias.Forward);
		if (plotsPath == null) {

		} else {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			MutableTreeNode mNode = (MutableTreeNode) plotsPath
					.getLastPathComponent();
			model.removeNodeFromParent(mNode);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getWorkSheetOutputs().getPlotOutputs().setLinearPlots(
							new ArrayList<JFreeChart>());

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getWorkSheetOutputs().getPlotOutputs().setLogplots(
							new ArrayList<JFreeChart>());

		}
	}

	void populateAvailableResultsWithPlots() {

		DefaultTreeModel model = (DefaultTreeModel) ScaResultsAvailComp
				.createSetResAvailInst().availableOutputsTree.getModel();

		DefaultMutableTreeNode plots = new DefaultMutableTreeNode("plots");
		ScaResultsAvailComp.createSetResAvailInst().availableOutputs.add(plots);
		DefaultMutableTreeNode plotsTimeVSConc = new DefaultMutableTreeNode(
				"Time vs Conc");
		plots.add(plotsTimeVSConc);
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject(); i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			plotsTimeVSConc.add(plotNode);
		}
		DefaultMutableTreeNode plotsTimeVSEffectSiteConc = new DefaultMutableTreeNode(
				"Time vs Effect Site Conc");
		plots.add(plotsTimeVSEffectSiteConc);
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject(); i++) {
			DefaultMutableTreeNode plotNode = new DefaultMutableTreeNode(
					"plot " + (i + 1));
			plotsTimeVSEffectSiteConc.add(plotNode);
		}
		ScaOptions.createScaOptInst().viewsCombo.setEnabled(true);
		model.reload();
	}

}

