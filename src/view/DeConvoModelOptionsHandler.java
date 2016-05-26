package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DeConvoModelOptionsHandler {

	public static DeConvoModelOptionsHandler DECONVO_MOD_HAND = null;

	public static DeConvoModelOptionsHandler createDeConvoMapHandInst() {
		if (DECONVO_MOD_HAND == null) {
			DECONVO_MOD_HAND = new DeConvoModelOptionsHandler();
		}
		return DECONVO_MOD_HAND;
	}

	ProcessingInputsInfo procInputInst;

	void weightOptionsCbActionEvent() throws RowsExceededException, WriteException, BiffException, IOException {
		procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		procInputInst
				.getModelInputsObj()
				.setWeightingOption(
						DeConvoModelOptionsGui.createDeConvoOptionsInstance().weightingOptions
								.getSelectedIndex());

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}
	
	void methodOptionsCbActionEvent() throws RowsExceededException, WriteException, BiffException, IOException {
		procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		procInputInst
				.getModelInputsObj()
				.setCalculationMethod(
						DeConvoModelOptionsGui.createDeConvoOptionsInstance().calculationMethodOptions
								.getSelectedIndex());

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}
	
	
	void doseUnitButtonActionEvent() throws RowsExceededException, WriteException, BiffException, IOException {
				
		UnitBuilder.createUBInstance().unitBuilderFrame.setVisible(true);
	}
	
	
	void normalizationCbActionEvent() throws RowsExceededException, WriteException, BiffException, IOException {
		procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		procInputInst
				.getModelInputsObj()
				.setCalculationMethod(
						DeConvoModelOptionsGui.createDeConvoOptionsInstance().normalizationUnit
								.getSelectedIndex());

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

}
