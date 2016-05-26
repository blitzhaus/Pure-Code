package view;

import java.awt.Component;
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

public class CaEngineSettingDispGuiCreator {
	


	JInternalFrame EngineSettingInternalFrame;
		
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
	JRadioButton useNcaCsEqns;
	
	JComboBox minimizationMthodComboBox;
	JComboBox initialParameterValueComboBox;
	JComboBox parameterBoundariesComboBox;


	JCheckBox propFinalEstimateCb;
	
	JLabel minimizationMthodLabel;
	JLabel numberOfPredictedValuesLabel;
	JLabel convergenceCriterialabel;
	JLabel noOfIterLabel;
	JLabel meanSquareLabel;
	ParamOptsEngSetingsPanelCreator paramOptEngSetInst;
	
	public static CaEngineSettingDispGuiCreator PD_ENG_SETGUI = null;
	public static CaEngineSettingDispGuiCreator createEngineSettingInstance() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PD_ENG_SETGUI == null){
			PD_ENG_SETGUI = new CaEngineSettingDispGuiCreator();
		}
		return PD_ENG_SETGUI;
	}
	public void pdEngSettingGuiCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createEngineSettingFrame();
	}
	private void createEngineSettingFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		
		
		EngineSettingInternalFrame = new JInternalFrame("Weighting", false,
				false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(EngineSettingInternalFrame);
		EngineSettingInternalFrame.setLocation(0, 0);
		EngineSettingInternalFrame.setSize(CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame
				.getWidth(), CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame.getHeight()-20);
		
		EngineSettingInternalFrame.setVisible(true);
		GridBagLayout gridbagLayout = new GridBagLayout();
		EngineSettingInternalFrame.setLayout(new GridLayout(3, 2));
		EngineSettingInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		
		paramOptEngSetInst = new ParamOptsEngSetingsPanelCreator();
		JScrollPane paramOptionsSc = new JScrollPane(paramOptEngSetInst,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		
		EngineSettingInternalFrame.setContentPane(paramOptionsSc);
		CaOptionsGuiCreator.createPdOptionsInstance().engineSettingTabDesktopPane.add(EngineSettingInternalFrame);
	
	}
	



}
