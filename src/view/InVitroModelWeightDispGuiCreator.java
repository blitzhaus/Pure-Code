package view;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Common.JinternalFrameFunctions;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class InVitroModelWeightDispGuiCreator {


	JInternalFrame modelWeightInternalFrame;
	JRadioButton hillModelRadioButton;
	JRadioButton weibulModelRadioButton;
	JRadioButton doubleWeibulModelRadioButton;
	JRadioButton makoiBanakarModelRadioButton;
	
	
	JComboBox dataTypeComboBoxForCA;
	JComboBox normalizationUnitComboBoxForCA;
	JComboBox numberOfDoseComboBox;
	JComboBox weightingOptionsComboBox;
	JComboBox predictedWeightingOptionsComboBox;
	JComboBox availableLibraryModelComboBox;
	JComboBox availableDifferentialEquationModelComboBox;
	
	JLabel weightingLabel;
	JRadioButton predictedWeightingRadioButton;
	
	JTextField doseUnitTextFieldForCA;
	
	JButton doseUnitButtonForCA;
	JLabel dataTypeLabel;
	JLabel normalizationUnitLabel;
	JLabel noOfDoseLabel;
	boolean ifFromDoseUnitOfCA;
	JLabel responseUnitLabel;
	JTextField responseUnitTextField;
	int previousNumberOfDoseForCA = 1;
	int numberOfDoseForCA = 1;
	boolean isFromRowRemoval;
	
	InVitroModelWeightPanelCreator modelWeightPanelinst;
	
	
	public static InVitroModelWeightDispGuiCreator INVITRO_M_W_GUI = null;
	public static InVitroModelWeightDispGuiCreator createInVitroModelWeightInstance() throws RowsExceededException, WriteException, BiffException, IOException{
		if(INVITRO_M_W_GUI == null){
			INVITRO_M_W_GUI = new InVitroModelWeightDispGuiCreator();
		}
		return INVITRO_M_W_GUI;
	}
	public void invitroModelWeightGuiCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createModelWeightInternalFrame();
	}
	private void createModelWeightInternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		
		
		modelWeightInternalFrame = new JInternalFrame("Weighting", false,
				false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(modelWeightInternalFrame);
		modelWeightInternalFrame.setLocation(0, 0);
		modelWeightInternalFrame.setSize(InVitroOptionsGuiCreator.createInVitroOptionsInstance().optionsInternalFrame
				.getWidth(), InVitroOptionsGuiCreator.createInVitroOptionsInstance().optionsInternalFrame.getHeight());
		
		modelWeightInternalFrame.setVisible(true);
		
		modelWeightInternalFrame.setLayout(new GridLayout(3, 2));
		modelWeightInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		
		modelWeightPanelinst = new InVitroModelWeightPanelCreator();
		
		modelWeightInternalFrame.setContentPane(modelWeightPanelinst);
	
		InVitroOptionsGuiCreator.createInVitroOptionsInstance().modelWeightTabDesktopPane.add(modelWeightInternalFrame);
	
	}
	



}
