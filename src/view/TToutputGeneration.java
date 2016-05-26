package view;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import Common.DataManipulationFunctions;

public class TToutputGeneration {

	public static TToutputGeneration TT_OUT_GEN = null;
	public static TToutputGeneration createttOutGenInst() {
		if(TT_OUT_GEN == null){
			TT_OUT_GEN = new TToutputGeneration("just object creation");
		}
		return TT_OUT_GEN;
		
	}
	
	JTable outputTable;

	public TToutputGeneration(String dummy){
		
	}

	void outputGeneration() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		removeAllPreviousComponents();
		
		int rows;
		if(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getHasHeader() == true){
			rows = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getTtData().length -1;
		} else{
			rows = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getTtData().length;
		}
		
		int columns = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getTtData()[0].length + 1;
		
		
		
		String[][] data = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getTtData();
		String[] transformedCol = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getWorkSheetOutputs().getTtTransformedColumn();
		
		outputTable = new JTable(rows, columns);
		
		
		if(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getHasHeader() == true){
			for(int i=0;i<outputTable.getColumnCount();i++){
				if(i == (outputTable.getColumnCount()-1)){
					outputTable.getColumnModel().getColumn(i).setHeaderValue("Transformed X");
				} else{
					outputTable.getColumnModel().getColumn(i).setHeaderValue(data[0][i]);
				}
			}
			
			for(int i=1;i<=rows;i++){
				for(int j=0;j<columns-1;j++){
					outputTable.setValueAt(data[i][j], i-1, j);
				}
			}
			
		} else{

			for(int i=0;i<outputTable.getColumnCount();i++){
				if(i == (outputTable.getColumnCount()-1)){
					outputTable.getColumnModel().getColumn(i).setHeaderValue("Transformed X");
				} else{
					outputTable.getColumnModel().getColumn(i).setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getColumnPropertiesArrayList().get(i).getColumnName());
				}
			}
			for(int i=1;i<rows;i++){
				for(int j=0;j<columns-1;j++){
					outputTable.setValueAt(data[i][j], i-1, j);
				}
			}
		
		}
		
		
		
		String[] transformed = checkForReqDecimalsAndOptionalDecimals(transformedCol);
		
		for(int i=0;i<transformed.length;i++){
			outputTable.setValueAt(transformed[i]+"", i, (columns - 1));
		}
		
		JScrollPane resTabSP = new JScrollPane(outputTable);
		resTabSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		resTabSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		TableTransformations.createTableTrasGUIInst().resultsDispIF.getContentPane().add(resTabSP);
		
		/*outputTable.repaint();
		outputTable.validate();*/
		//TableTransformations.createTableTrasGUIInst().resultsDispIF.repaint();
		TableTransformations.createTableTrasGUIInst().resultsDispIF.validate();
		
		TableTransformations.createTableTrasGUIInst().resultsAvailTree.setSelectionRow(1);
		
	}
	
	private void removeAllPreviousComponents() {
		TableTransformations.createTableTrasGUIInst().resultsDispIF.getContentPane().removeAll();
		
	}

	private String[] checkForReqDecimalsAndOptionalDecimals(
			String[] transformedCol) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		int optDec = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
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
		String reqDec = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
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

		String[] xpStr = new String[transformedCol.length];
		if (optDec == 0) {
			if (reqDec.equals("")) {
				for (int i = 0; i < transformedCol.length; i++) {
					xpStr[i] = transformedCol[i] + "";
				}
			} else {
				DecimalFormat dec = new DecimalFormat(reqDec);
				dec.setRoundingMode(RoundingMode.DOWN);
				for (int i = 0; i < transformedCol.length; i++) {
					
					if(transformedCol[i].equals("NAN")){
						xpStr[i] = transformedCol[i];
					} else{
						xpStr[i] = dec.format(Double.parseDouble(transformedCol[i]));
					}
					
				}
			}
		} else if (reqDec.equals("")) {
			if (optDec != 0) {
				for (int i = 0; i < transformedCol.length; i++) {
					
					if(transformedCol[i].equals("NAN")){
						xpStr[i] = transformedCol[i];
					} else{
						xpStr[i] = DataManipulationFunctions
						.createDataManFuncInst().truncateDouble(Double.parseDouble(transformedCol[i]),
								optDec)
						+ "";
					}
					
				}

			} else if (optDec == 0) {

				for (int i = 0; i < transformedCol.length; i++) {
					xpStr[i] = transformedCol[i] + "";
				}

			}
		}

		return xpStr;
	
	}

}
