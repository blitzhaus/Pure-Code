package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaParameterUnitsTableHandler {

	public static NcaParameterUnitsTableHandler NCA_PAR_UNIT_TABLE_HANDLER = null;
	public static NcaParameterUnitsTableHandler createNvaParUnitsTabHandInst(){
		if(NCA_PAR_UNIT_TABLE_HANDLER == null){
			NCA_PAR_UNIT_TABLE_HANDLER = new NcaParameterUnitsTableHandler();
		}
		return NCA_PAR_UNIT_TABLE_HANDLER;
	}
	
	
	
	public NcaParameterUnitsTableHandler(){
		
	}
	
	public void preferredUnitsHandler() throws RowsExceededException, WriteException, BiffException, IOException{
		
		if(NcaParameterUnitsDispGui.createNcaParUnitsDisInst().parameterUnitsTable.getSelectedColumn() == 2){
			
			NcaParameterUnitsDispGui.createNcaParUnitsDisInst().isRestoringParameterUnits = true;
			NcaOptionsGui.createNcaOptionsInstance().isFromDoseUnitBuilding = false;
			NcaOptionsGui.createNcaOptionsInstance().isFromPreferredUnitInternalFrame = true;
			UnitBuilder.createUBInstance().setVisible(true);
		}
		
	}
}
