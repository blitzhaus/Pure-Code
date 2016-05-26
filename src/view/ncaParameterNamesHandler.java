package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ncaParameterNamesHandler {

	public static ncaParameterNamesHandler NCAPARAMHAND = null;

	public static ncaParameterNamesHandler createInstance() {
		
		if(NCAPARAMHAND == null){
			NCAPARAMHAND = new ncaParameterNamesHandler("just object creation");
		} 
		
		return NCAPARAMHAND;
	}
	
	public ncaParameterNamesHandler(String string) {
		// TODO Auto-generated constructor stub
	}

	void handler() throws RowsExceededException, WriteException, BiffException, IOException {
		
		int rowSelected = NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable.getSelectedRow();
		int columnSlected  = NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable.getSelectedColumn();
		if(columnSlected == 2){
			if(NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable.getValueAt(rowSelected, columnSlected).toString().equals("yes")){
				NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable.setValueAt("no", rowSelected, columnSlected);
			} else{
				NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable.setValueAt("yes", rowSelected, columnSlected);
			}
		}
		
	}
	
	

}
