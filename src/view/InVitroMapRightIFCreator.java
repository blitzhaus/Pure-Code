package view;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class InVitroMapRightIFCreator extends JPanel {

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws BiffException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	InVitroSortVarListPanelCreator srtVarPanel;
	InVitroIndVarTextPanelCreator TimeVarPanel;
	InVitroDepVarTextPanelCreator concVarPanel;
	InVitroCarryAlongVarListPanelCreator carryAlongVarPanel;

	public InVitroMapRightIFCreator() throws RowsExceededException,
			WriteException, BiffException, IOException {
		createMappingRightIF();
	}

	public void createMappingRightIF() throws RowsExceededException,
			WriteException, BiffException, IOException {

		setLayout(new GridLayout(2, 2));

		String dependentVarName = "";
		String independentVarName = "";

		dependentVarName = "Fdis";
		independentVarName = "Time";

		srtVarPanel = new InVitroSortVarListPanelCreator("Formulation");
		TimeVarPanel = new InVitroIndVarTextPanelCreator(independentVarName);
		concVarPanel = new InVitroDepVarTextPanelCreator(dependentVarName);
		carryAlongVarPanel = new InVitroCarryAlongVarListPanelCreator(
				"Carry Along");

		add(srtVarPanel);
		add(TimeVarPanel);
		add(concVarPanel);
		add(carryAlongVarPanel);

	}

}
