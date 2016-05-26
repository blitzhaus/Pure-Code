package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class InVitroModelWeightActionHandler {

	public void actionEventForDissolutionWeight() throws RowsExceededException, WriteException, BiffException, IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		procInputInst
				.getInVitroModelInputInst()
				.setWeightingIndex(
						InVitroModelWeightDispGuiCreator
								.createInVitroModelWeightInstance().weightingOptionsComboBox
								.getSelectedIndex());
		
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

}
