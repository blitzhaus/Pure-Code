package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PDResponseUnitActionHandeler {
	
	public void responseUnitActionEvent()
	{
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		try {
			procInputInst.getModelInputTab1Obj().setResponseUnit(CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().responseUnitTextField.getText());
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
		
		try {
			ParameterAndUnitListLoader.createParamAndUnitListInstance().unitListForParameters();
			
			CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().paramUnitTableRebuilding(procInputInst);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
