package view;

import java.io.IOException;

import javax.swing.JTable;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class RemoveUnnecessaryValuesFromTables {

	
	public static RemoveUnnecessaryValuesFromTables RM_NULL_FROM_TABLES = null;
	public static RemoveUnnecessaryValuesFromTables createRmNullFromTabInst(){
		if(RM_NULL_FROM_TABLES == null){
			RM_NULL_FROM_TABLES = new RemoveUnnecessaryValuesFromTables();
		}
		return RM_NULL_FROM_TABLES;
	}
	
	public RemoveUnnecessaryValuesFromTables(){
		
	}
	
	public void removeNullValuesFromAllTables() throws RowsExceededException, WriteException, BiffException, IOException{
		
		removeNullValuesFromTables(NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable);
		removeNullValuesFromTables(NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable);
		removeNullValuesFromTables(NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable);
		
	}
	
	
	private void removeNullValuesFromTables(JTable table) throws RowsExceededException, WriteException, BiffException, IOException {
		
		for(int i=0;i<table.getRowCount();i++){
			for(int j=0;j<table.getColumnCount();j++){
		
					try{
						if((table.getValueAt(i, j).toString().equals("null")) ){
							table.setValueAt("", i, j);
							
						}
					} catch(NullPointerException e){
						table.setValueAt("", i, j);
					}
					
					
				
			}
		}
		
		
		
	}

	public void removeMissingValuesFromAllTables() throws RowsExceededException, WriteException, BiffException, IOException {
		removeMissingData(NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable);
		removeMissingData(NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable);
		removeMissingData(NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable);
		
	}
	
	void removeMissingData(JTable table){
		for(int i=0;i<table.getRowCount();i++){
			for(int j=0;j<table.getColumnCount();j++){
			
					try{
						if((table.getValueAt(i, j).toString().equals("Missing")) ){
							table.setValueAt("", i, j);
							
							table.setValueAt("", i, j-1);
						}
					} catch(ArrayIndexOutOfBoundsException e){
						table.setValueAt("", i, j);
					}
					
					
				
			}
		}
	}

	public void removeNegPiFromAllOutput() throws RowsExceededException, WriteException, BiffException, IOException {
		removeNegPIFrom(NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable);
		removeNegPIFrom(NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable);
		removeNegPIFrom(NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable);
		
	}
	
	void removeNegPIFrom(JTable table){
		for(int i=0;i<table.getRowCount();i++){
			for(int j=0;j<table.getColumnCount();j++){
			
					try{
						if((table.getValueAt(i, j).toString().equals("-3.14159265359")) ){
							table.setValueAt("", i, j);
							
							table.setValueAt("", i, j-1);
						}
					} catch(ArrayIndexOutOfBoundsException e){
						table.setValueAt("", i, j);
					}
					
					
				
			}
		}
	}
	
}

