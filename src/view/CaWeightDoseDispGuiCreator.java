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

public class CaWeightDoseDispGuiCreator {

	JInternalFrame weightingInternalFrameForCA;
	JRadioButton algebraicEquationModelRadioButton;
	JRadioButton differentialEquationModelRadioButton;
	JRadioButton asciiModelRadioButton;
	
	JComboBox dataTypeComboBoxForCA;
	JComboBox normalizationUnitComboBoxForCA;
	JComboBox numberOfDoseComboBox;
	JComboBox weightingOptionsComboBox;
	JComboBox predictedWeightingOptionsComboBox;
	JComboBox availableLibraryModelComboBox;
	JComboBox availableDifferentialEquationModelComboBox;
	
	JRadioButton observedWeightingRadioButton;
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
	
	WeightDosePanelCreator weightDosePanelinst;
	
	
	public static CaWeightDoseDispGuiCreator PD_W_D_GUI = null;
	public static CaWeightDoseDispGuiCreator createCaWeightDoseInstance() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PD_W_D_GUI == null){
			PD_W_D_GUI = new CaWeightDoseDispGuiCreator();
		}
		return PD_W_D_GUI;
	}
	public void pdWeightDoseGuiCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createWeightDoseInternalFrame();
	}
	private void createWeightDoseInternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		
		
		weightingInternalFrameForCA = new JInternalFrame("Weighting", false,
				false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(weightingInternalFrameForCA);
		weightingInternalFrameForCA.setLocation(0, 0);
		weightingInternalFrameForCA.setSize(CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame
				.getWidth(), CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame.getHeight());
		
		weightingInternalFrameForCA.setVisible(true);
		GridBagLayout gridbagLayout = new GridBagLayout();
		weightingInternalFrameForCA.setLayout(new GridLayout(3, 2));
		weightingInternalFrameForCA.setBorder(DDViewLayer.createViewLayerInstance().b);
		
		weightDosePanelinst = new WeightDosePanelCreator();
		
		weightingInternalFrameForCA.setContentPane(weightDosePanelinst);
		//weightingInternalFrameForCA.add(mapOptionsWtDosePnl);
		CaOptionsGuiCreator.createPdOptionsInstance().weightDoseTabDesktopPane.add(weightingInternalFrameForCA);
	
	}
	

}
