package view;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;

public class InVitroEngSettingDispGuiCreator {
	

	


	JInternalFrame engineSettingInternalFrame;
		
	JTextField meanSquareTextField;
	JTextField incrementsForpartialDerivativesTextField;
	JTextField numberOfPredictedValuesTextField;
	// public static JTextField multiplierForLambdaTextField;
	JTextField lambdaValueTextField;
	JTextField muTextField;

	JLabel populationSizeLabel;
	JLabel crossoverProbabilityLabel;
	JLabel mutationProbabilityLabel;
	JLabel gridPointsLabel;
	JLabel odeSolverMethodLabel;
	JLabel roundToLabel;
	JLabel muLable;
	JLabel lambdaValueLabel;
	JLabel incrementsForpartialDerivativesLabel;
	JLabel multiplierForLambdaLabel;
	JTextField populationSizeTextField;
	JTextField crossoverProbabilityTextField;
	JTextField mutationProbabilityTextField;
	JTextField gridPointsTextField;
	//JTextField roundToTextField;
	JTextField numberOfIterationTextField;
	JTextField convergenceCriteriaTextField;
	
	
	JComboBox odeSolverMethodComboBox;
	JRadioButton initialParameterValueByCS;
	JRadioButton initialParameterValueByGA;
	JRadioButton previouslyGenInitilParam;
	JRadioButton initialParameterValueByGS;
	
	JComboBox minimizationMthodComboBox;
	JComboBox initialParameterValueComboBox;
	JComboBox parameterBoundariesComboBox;


	JCheckBox propFinalEstimateCb;
	
	JLabel minimizationMthodLabel;
	JLabel numberOfPredictedValuesLabel;
	JLabel convergenceCriterialabel;
	JLabel noOfIterLabel;
	JLabel meanSquareLabel;
	
	InVitroParamOptsEngSetingsPanelCreator paramOptEngSetInst;
	
	public static InVitroEngSettingDispGuiCreator INVITRO_ENG_SETGUI = null;
	public static InVitroEngSettingDispGuiCreator createEngineSettingInstance() throws RowsExceededException, WriteException, BiffException, IOException{
		if(INVITRO_ENG_SETGUI == null){
			INVITRO_ENG_SETGUI = new InVitroEngSettingDispGuiCreator();
		}
		return INVITRO_ENG_SETGUI;
	}
	public void inVitroEngSettingGuiCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createEngineSettingFrame();
	}
	private void createEngineSettingFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		
		
		engineSettingInternalFrame = new JInternalFrame("Weighting", false,
				false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(engineSettingInternalFrame);
		engineSettingInternalFrame.setLocation(0, 0);
		engineSettingInternalFrame.setSize(InVitroOptionsGuiCreator.createInVitroOptionsInstance().optionsInternalFrame
				.getWidth(), InVitroOptionsGuiCreator.createInVitroOptionsInstance().optionsInternalFrame.getHeight()-20);
		
		engineSettingInternalFrame.setVisible(true);
		
		engineSettingInternalFrame.setLayout(new GridLayout(3, 2));
		engineSettingInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		
		paramOptEngSetInst = new InVitroParamOptsEngSetingsPanelCreator();
		JScrollPane paramOptionsSc = new JScrollPane(paramOptEngSetInst,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		
		engineSettingInternalFrame.setContentPane(paramOptionsSc);
		InVitroOptionsGuiCreator.createInVitroOptionsInstance().engineSettingTabDesktopPane.add(engineSettingInternalFrame);
	
	}
	





}
