package view;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Common.DataManipulationFunctions;

public class BqlOutputGeneration {

	public static BqlOutputGeneration BQL_OUT_GEN = null;

	public static BqlOutputGeneration createBqloutGenInst() {
		if (BQL_OUT_GEN == null) {
			BQL_OUT_GEN = new BqlOutputGeneration("just object creation");
		}
		return BQL_OUT_GEN;
	}

	JTable transformedTable;

	public BqlOutputGeneration(String dummy) {

	}

	void generateOutput() {

		generateSpreadSheetOutput();
		addTransformedSheetToData();
	}

	private void addTransformedSheetToData() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[][] data = new String[transformedTable.getRowCount()][transformedTable.getColumnCount()];
		
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data[i].length;j++){
				data[i][j] = transformedTable.getValueAt(i, j)+"";
			}
			
		}
		
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo().getWorkSheetOutputs().getSpreadSheetOutputs().setVerticalParameters(data);
		
	}

	private void generateSpreadSheetOutput() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		DataPreparationForBQL dataPrepBql = new DataPreparationForBQL();

		String[][] data = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getBqlDat();
		transformedTable = new JTable(data.length, data[0].length + 1);

		TableColumnModel tcm = transformedTable.getColumnModel();
		for (int i = 0; i < transformedTable.getColumnCount(); i++) {
			TableColumn tc = tcm.getColumn(i);
			if (i == transformedTable.getColumnCount() - 1) {
				tc.setHeaderValue("Transformed Conc");
			} else {
				tc
						.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getBqlInfo().getProcessingInputs()
								.getBqlSortedColumnNames().get(i));
			}

		}
		int optDec =  appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
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
								.getSelectedSheetIndex()).getTtInfo().getProcessingInputs()
								.getMappingDataObj().getyColumnCorrespondinOriginalIndex())
								.getOptionalDecimals();
		String reqDec =  appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
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
								.getSelectedSheetIndex()).getTtInfo().getProcessingInputs()
								.getMappingDataObj().getyColumnCorrespondinOriginalIndex())
								.getRequiredDecimals();

		for (int i = 0; i < transformedTable.getRowCount(); i++) {
			for (int j = 0; j < transformedTable.getColumnCount(); j++) {

				if (j == transformedTable.getColumnCount() - 1) {

					String value;
					if (Double.parseDouble(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getBqlInfo().getWorkSheetOutputs()
							.getParameterValuesAL().get(i)) == -1) {
						value = "Missing";
					} else {
						value = performFormatting(
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
												.getBqlInfo()
												.getWorkSheetOutputs()
												.getParameterValuesAL().get(i)),
								optDec, reqDec);
					}
					transformedTable.setValueAt(value, i, j);
				} else {
					try {
						transformedTable
								.setValueAt(performFormatting(Double
										.parseDouble(data[i][j]), optDec,
										reqDec), i, j);
					} catch (Exception e) {
						transformedTable.setValueAt(data[i][j], i, j);
					}

				}

			}
		}

		JScrollPane transformedDataSP = new JScrollPane(transformedTable);
		BQLResDispComp.createBqlResDispInst().transformedSheetIF
				.getContentPane().add(transformedDataSP);
		BQLResDispComp.createBqlResDispInst().transformedSheetIF.moveToFront();

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

}
