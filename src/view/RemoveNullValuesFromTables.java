package view;

import java.io.IOException;

import javax.swing.JTable;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class RemoveNullValuesFromTables {

	
	public static RemoveNullValuesFromTables RM_NULL_FROM_TABLES = null;
	public static RemoveNullValuesFromTables createRmNullFromTabInst(){
		if(RM_NULL_FROM_TABLES == null){
			RM_NULL_FROM_TABLES = new RemoveNullValuesFromTables();
		}
		return RM_NULL_FROM_TABLES;
	}
	
	public RemoveNullValuesFromTables(){
		
	}
	
	public void removeNullValuesFromAllTables() throws RowsExceededException, WriteException, BiffException, IOException{
		
		removeNullFromVerticalDisplayofParameters(NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable);
		removeNullFromVerticalDisplayofParameters(NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable);
		removeNullFromVerticalDisplayofParameters(NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable);
		
	}
	
	
	private void removeNullFromVerticalDisplayofParameters(JTable table) throws RowsExceededException, WriteException, BiffException, IOException {
		
		for(int i=0;i<table.getRowCount();i++){
			for(int j=0;j<table.getColumnCount();j++){
				try{
					
					if(table.getValueAt(i, j).toString().equals("null")){
						table.setValueAt("", i, j);
					}
					
				} catch(Exception e){
					table.setValueAt("", i, j);
				}
			}
		}
		
		
		
	}
	
}

