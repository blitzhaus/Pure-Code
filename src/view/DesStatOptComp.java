package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Common.JinternalFrameFunctions;

public class DesStatOptComp {

	public static DesStatOptComp DES_STAT_OPT = null;
	public static DesStatOptComp createDesStatOptInst(){
		if(DES_STAT_OPT == null){
			DES_STAT_OPT = new DesStatOptComp();
		}
		return DES_STAT_OPT;
	}
	
	public DesStatOptComp(){
		
	}
	
	JInternalFrame optionsInternalFrame;
	JTabbedPane moduleTabs;
	JDesktopPane optionsTabDesktopPane;
	JInternalFrame modelSelectionInternalFrame;
	JRadioButton includePercentileRadioButtonForDS;
	JCheckBox confidenceIntervalCheckBox;
	JTextField confidenceIntervalTextField;

	
	void createOptionsInternalFrame() {

		optionsInternalFrame = new JInternalFrame("options ", false, false,
				false, false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(optionsInternalFrame);
		optionsInternalFrame.setLocation(DescriptiveStatMainPage.createDescStatMainPageInst().desStatsMainIF.getX(),
				DescriptiveStatMainPage.createDescStatMainPageInst().desStatsMainIF.getY() +
				DescriptiveStatMainPage.createDescStatMainPageInst().desStatsMainIF.getHeight());

		optionsInternalFrame.setSize(DescriptiveStatMainPage.createDescStatMainPageInst().desStatsMainIF.getWidth(),
				DDViewLayer.createMTInstance().mainTabbedFrame.getHeight() / 2);

		optionsInternalFrame.setVisible(true);
		optionsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane.add(optionsInternalFrame);
		moduleTabs = new JTabbedPane();
		optionsTabDesktopPane = new JDesktopPane();
		moduleTabs.addTab("Options", optionsTabDesktopPane);
		optionsInternalFrame.getContentPane().add(moduleTabs);
		//setFont(new Font("Serif", Font.PLAIN, 8));
		createModelSelectionInternalFrame();
		optionsInternalFrame.moveToFront();
	}
	
	private void createModelSelectionInternalFrame() {

		modelSelectionInternalFrame = new JInternalFrame("Model Selection",
				false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(modelSelectionInternalFrame);

		// need to modify
		modelSelectionInternalFrame.setLocation(0, 0);
		int width = (int) (optionsInternalFrame.getWidth() / 2.7);
		modelSelectionInternalFrame.setSize(optionsInternalFrame.getWidth(),
				optionsInternalFrame.getHeight()); // 50 is subtracted because
													// tabs are added and it is
													// to compensate the tabs
													// height
		// modelSelectionInternalFrame.setSize(380,300);
		modelSelectionInternalFrame.setVisible(true);
		GridBagLayout gridbagLayout = new GridBagLayout();
		modelSelectionInternalFrame.setLayout(gridbagLayout);
		modelSelectionInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		// modelSelectionInternalFrame.setBackground(Color.white);
		createInternalGuiModelSelection();
		optionsTabDesktopPane.add(modelSelectionInternalFrame);
		modelSelectionInternalFrame.moveToFront();
	}
	
	private void createInternalGuiModelSelection() {

		Font componentOptionseFont = new Font("Arial", Font.BOLD, 10);
		Font componentLablesFont = new Font("Arial", Font.BOLD, 10);

		JPanel modelTypePanel = new JPanel();
		modelTypePanel.setFont(componentOptionseFont);
		modelTypePanel.setPreferredSize(new Dimension(160, 50));

		GridBagConstraints modelTypePanelCon = new GridBagConstraints();
		modelTypePanelCon.gridx = 0;
		modelTypePanelCon.gridy = 0;

		modelTypePanelCon.weightx = 0.5;
		modelTypePanelCon.anchor = GridBagConstraints.LINE_START;

		includePercentileRadioButtonForDS = new JRadioButton(
				"Include Percentile");
		includePercentileRadioButtonForDS.setEnabled(false);
		includePercentileRadioButtonForDS.setFont(componentOptionseFont);
		includePercentileRadioButtonForDS.setBackground(Color.white);
		includePercentileRadioButtonForDS.addActionListener(DDViewLayer.createViewLayerInstance());
		modelTypePanel.add(includePercentileRadioButtonForDS);

		modelSelectionInternalFrame.getContentPane().add(modelTypePanel,
				modelTypePanelCon);

		JPanel weightingAndSparseOptionsPanel = new JPanel();
		weightingAndSparseOptionsPanel.setLayout(new FlowLayout());
		GridBagConstraints weightingAndSparseOptionsPanelCon = new GridBagConstraints();
		weightingAndSparseOptionsPanelCon.gridx = 0;
		weightingAndSparseOptionsPanelCon.gridy = 1;
		weightingAndSparseOptionsPanelCon.anchor = GridBagConstraints.LINE_START;
		modelSelectionInternalFrame.getContentPane().add(
				weightingAndSparseOptionsPanel,
				weightingAndSparseOptionsPanelCon);

		confidenceIntervalCheckBox = new JCheckBox("Confidence Interval");
		confidenceIntervalCheckBox.setEnabled(false);
		confidenceIntervalCheckBox.setFont(componentOptionseFont);
		weightingAndSparseOptionsPanel.add(confidenceIntervalCheckBox);

		confidenceIntervalTextField = new JTextField(20);
		confidenceIntervalTextField.setEnabled(false);

		confidenceIntervalTextField.addFocusListener(DDViewLayer.createViewLayerInstance());

		confidenceIntervalTextField.setFont(componentOptionseFont);
		weightingAndSparseOptionsPanel.add(confidenceIntervalTextField);

		JPanel weightingOptionsPanel = new JPanel();
		weightingOptionsPanel.setFont(componentOptionseFont);
		weightingOptionsPanel.setPreferredSize(new Dimension(110, 150));

		JCheckBox numberOfSDCheckBox = new JCheckBox("Number Of SD");
		numberOfSDCheckBox.setEnabled(false);
		numberOfSDCheckBox.setFont(componentOptionseFont);
		weightingAndSparseOptionsPanel.add(numberOfSDCheckBox);

		JTextField numberOfSDTextField = new JTextField(20);
		numberOfSDTextField.setEnabled(false);

		numberOfSDTextField.addFocusListener(DDViewLayer.createViewLayerInstance());
		weightingAndSparseOptionsPanel.add(numberOfSDTextField);

	}
}
