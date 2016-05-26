package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class InVitroModelInputHandler {

	public static InVitroModelInputHandler INVITRO_MODEL_HAND = null;

	public static InVitroModelInputHandler createInVitroModelHandInst() {
		if (INVITRO_MODEL_HAND == null) {
			INVITRO_MODEL_HAND = new InVitroModelInputHandler();
		}
		return INVITRO_MODEL_HAND;
	}

	ProcessingInputsInfo procInputInst;

	void hillModelActionEvent() throws RowsExceededException, WriteException, BiffException, IOException {
		procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		if(InVitroModelWeightDispGuiCreator.createInVitroModelWeightInstance().hillModelRadioButton.isSelected() == true)
		{
			procInputInst.getInVitroModelInputInst().setIfHillModel(1);	
		}else
		{
			procInputInst.getInVitroModelInputInst().setIfHillModel(0);
		}
		
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	
	void weibulModelActionEvent() throws RowsExceededException, WriteException, BiffException, IOException {
		procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		if(InVitroModelWeightDispGuiCreator.createInVitroModelWeightInstance().weibulModelRadioButton.isSelected() == true)
		{
			procInputInst.getInVitroModelInputInst().setIfWeibulModel(1);	
		}else
		{
			procInputInst.getInVitroModelInputInst().setIfWeibulModel(0);
		}
		
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}
	
	void doubleWeibulModelActionEvent() throws RowsExceededException, WriteException, BiffException, IOException {
		procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		if(InVitroModelWeightDispGuiCreator.createInVitroModelWeightInstance().doubleWeibulModelRadioButton.isSelected() == true)
		{
			procInputInst.getInVitroModelInputInst().setIfDoubleWeibulModel(1);	
		}else
		{
			procInputInst.getInVitroModelInputInst().setIfDoubleWeibulModel(0);
		}
		
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}
	
	void makoidBanakarModelActionEvent() throws RowsExceededException, WriteException, BiffException, IOException {
		procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		if(InVitroModelWeightDispGuiCreator.createInVitroModelWeightInstance().makoiBanakarModelRadioButton.isSelected() == true)
		{
			procInputInst.getInVitroModelInputInst().setIfMakoidBanakarModel(1);	
		}else
		{
			procInputInst.getInVitroModelInputInst().setIfMakoidBanakarModel(0);
		}
		
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

}
