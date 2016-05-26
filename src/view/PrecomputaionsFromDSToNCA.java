package view;

import java.io.IOException;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import view.ApplicationInfo;
import view.DisplayContents;

import Common.WorkBookManipulation;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PrecomputaionsFromDSToNCA {

	public PrecomputaionsFromDSToNCA(){
		
	} 
	
	public void addFinalParametersSheetToFile1Workook() throws IOException, RowsExceededException, WriteException, BiffException{
		
		WorkBookManipulation wm = new WorkBookManipulation();
		wm.createWorkBook();
	}

	public void addFinalParametersToDataStructuresAL() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[][] finalparametersDS = createTemporaryDS();
		
		//add the data structure to the data structures array list
		//appInfo.addElementToDS(finalparametersDS);
		
	}

	private String[][] createTemporaryDS() {

		// The +1 is for the header as the data structure stores the header as
		// well
		String[][] finalparametersDS = new String[DesStatResDispComp
				.createDesStatResDispInst().finalparametersVerticalDisplayTable
				.getRowCount() + 1][DesStatResDispComp
				.createDesStatResDispInst().finalparametersVerticalDisplayTable
				.getColumnCount()];

		// fill the headers
		TableColumnModel tm = DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
				.getColumnModel();
		for (int i = 0; i < finalparametersDS[0].length; i++) {
			TableColumn tc = tm.getColumn(i);
			String headerValue = (String) tc.getHeaderValue();
			finalparametersDS[0][i] = headerValue;
		}

		// fill the values
		for (int i = 1; i < finalparametersDS.length; i++) {
			for (int j = 0; j < finalparametersDS[i].length; j++) {
				finalparametersDS[i][j] = (String) DesStatResDispComp
						.createDesStatResDispInst().finalparametersVerticalDisplayTable
						.getValueAt(i - 1, j);
			}
		}
		return finalparametersDS;
	}

	public void setColumnNameToAppInfo() {
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[][] tempData = appInfo.getElementFromDS(appInfo.getSelectedSheetIndex());
		
		if(appInfo.getWorkSheetObjectsAL().get(appInfo.getSelectedSheetIndex()).getHasHeader() == true){
			for(int i=0;i<appInfo.getWorkSheetObjectsAL().get(appInfo.getSelectedSheetIndex()).getColumnPropertiesArrayList().size();i++){
				appInfo.getWorkSheetObjectsAL().get(appInfo.getSelectedSheetIndex()).getColumnPropertiesArrayList()
				.get(i).setColumnNames(tempData[0][i]);
			}
		} else {
			for(int i=0;i<appInfo.getWorkSheetObjectsAL().get(appInfo.getSelectedSheetIndex()).getColumnPropertiesArrayList().size();i++){
				appInfo.getWorkSheetObjectsAL().get(appInfo.getSelectedSheetIndex()).getColumnPropertiesArrayList()
				.get(i).setColumnNames("Column "+(i+1));
			}
		}
		*/
		
	}

	public void setColumnPropertiesAL() {/*
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if(appInfo.getWorkSheetObjectsAL().get(appInfo.getSelectedSheetIndex()).getHasHeader() == true){
			for(int i=0;i<appInfo.getElementFromDS(appInfo.getSelectedSheetIndex())[0].length;i++){
				appInfo.getWorkSheetObjectsAL().get(appInfo.getSelectedSheetIndex()).getColumnPropertiesArrayList().add(new ColumnProperties());
			}
		}
		
		
		
		
	*/}

	public void replaceFinalparametersInDataStructuresAL() {/*
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[][] finalParametersDS = createTemporaryDS();
		
		//replace the ds created in the data structures array list
		appInfo.setElementInDS(finalParametersDS, appInfo.getSelectedSheetIndex());
		
	*/}

	public void replaceColumnPropertiesAL() {/*
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if(appInfo.getWorkSheetObjectsAL().get(appInfo.getSelectedSheetIndex()).getHasHeader() == true){
			for(int i=0;i<appInfo.getElementFromDS(appInfo.getSelectedSheetIndex())[0].length;i++){
				appInfo.getWorkSheetObjectsAL().get(appInfo.getSelectedSheetIndex()).getColumnPropertiesArrayList()
				.set(i, new ColumnProperties());
			}
		}
		
	*/}

	public void replaceFinalparametersSheet() throws BiffException, WriteException, IOException {
		
		WorkBookManipulation w = new WorkBookManipulation();
		w.replaceSheetInWorkBook();
	}
}
