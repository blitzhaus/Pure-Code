package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class NcaOptionsGui implements EventCodes {

	private final class NcaOptDoseUnitTextFieldHandler implements
			ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj().setDoseUnits(
							unitTextField.getText());
		}
	}

	JInternalFrame optionsInternalFrame;
	JTabbedPane moduleTabs;
	JDesktopPane optionsTabDesktopPane;
	JDesktopPane plotTabDesktopPane;
	JInternalFrame plotOptionsInternalFrame;
	JLabel viewLable;
	JComboBox viewsCombo;
	JInternalFrame modelSelectionInternalFrame;
	JComboBox modelOptions;
	public JTextField weightingValueTextField;
	JComboBox weightingOptions;
	JCheckBox sparseCheckBox;
	JTextArea titlesTextArea;
	JComboBox calculationMethodOptions;
	JLabel numberOfPartialAreaLable;
	JComboBox rootOfAdministration;
	int previousDataType;
	int presentDataType;
	boolean ifModelOptionsIsSelected;
	int columncount;
	JInternalFrame modelSettingsInternalFrame;
	JPanel modelSettingsPanel;
	//JCheckBox pageBreaksCheckBox;
	JCheckBox intermediateOutputCheckBox;
	JCheckBox excludeProfilesCheckBox;
	JCheckBox disableCurveStrippingCheckBox;
	JInternalFrame doseOptionsInternalFrame;
	JButton doseUnitButton;
	boolean isFromDoseUnitBuilding;
	boolean isFromPreferredUnitInternalFrame;
	UnitBuilder doseUnitBuilderFrame;
	int previousRootOfAdministration;
	JTextField unitTextField;
	JComboBox normalizationUnit;
	JInternalFrame fitSlopesInternalFrame;
	JCheckBox selectionCheckBoc;
	JCheckBox exclusionCheckBox;
	JButton fitFitSlopesButton;
	JInternalFrame regressionParametersInternalFrame;
	JTextField rsquareTextField;
	JTextField adjustedRSquareTextField;
	JTextField halfLifeTextField;
	JTextField numberOfTerminalPointsTextField;
	JInternalFrame PartialAreasoptionsInternalFrame;
	JComboBox numberOfPartialAreasCombo;

	public NcaOptionsGui(String dummy) {
		weightingValueTextField = new JTextField(2);
		calculationMethodOptions = new JComboBox();
		sparseCheckBox = new JCheckBox("Sparse");
		intermediateOutputCheckBox = new JCheckBox("Intermediate Output");
		excludeProfilesCheckBox = new JCheckBox(
				"Exclude Profiles With Insufficient Data");
		disableCurveStrippingCheckBox = new JCheckBox("Disable Curve Stripping");
		rootOfAdministration = new JComboBox();
		modelOptions = new JComboBox();
	}

	public static NcaOptionsGui NCA_OPT_GUI = null;

	public static NcaOptionsGui createNcaOptionsInstance()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NCA_OPT_GUI == null) {
			NCA_OPT_GUI = new NcaOptionsGui("just object creation");
		}
		return NCA_OPT_GUI;
	}

	public void NcaOptionsGuiCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {
		createOptionsInternalFrame();
	}

	private void createOptionsInternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {

		optionsInternalFrame = new JInternalFrame("options ", false, false,
				false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(optionsInternalFrame);
		optionsInternalFrame
				.setLocation(
						0,
						NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
								.getY()
								+ NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
										.getHeight());
		if (DDViewLayer.createViewLayerInstance().isToNCAFromPlotMaven == true) {
			optionsInternalFrame
					.setSize(
							NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
									.getWidth(),
							NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
									.getHeight() + 20);
		} else {
			optionsInternalFrame
					.setSize(
							NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
									.getWidth(),
							NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
									.getHeight() + 20);
		}

		optionsInternalFrame.setVisible(true);
		optionsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane
				.add(optionsInternalFrame);

		moduleTabs = new JTabbedPane();
		optionsTabDesktopPane = new JDesktopPane();
		moduleTabs.addTab("Options", optionsTabDesktopPane);
		plotTabDesktopPane = new JDesktopPane();
		moduleTabs.addTab("Plot", plotTabDesktopPane);
		plotOptionsInternalFrame = new JInternalFrame("Plot Options", false,false,false,false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(plotOptionsInternalFrame);
		plotOptionsInternalFrame.setLocation(0, 0);
		plotOptionsInternalFrame.setSize(optionsInternalFrame.getWidth(),
				optionsInternalFrame.getHeight());
		plotOptionsInternalFrame.setVisible(true);
		plotOptionsInternalFrame
				.setBorder(DDViewLayer.createViewLayerInstance().b);
		plotTabDesktopPane.add(plotOptionsInternalFrame);

		optionsInternalFrame.getContentPane().add(moduleTabs);
		// setFont(new Font("Serif", Font.PLAIN, 8));
		createPlotOptionsInternalFrame();
		createModelSelectionInternalFrame();
		createModelSettingsInternalFrame();
		createDoseOptionsInternalFrame();
		createFitSlopesInternalFrame();
		createRegressionParametersInternalFrame();
		createpartialArasinternalFrame();
		optionsInternalFrame.moveToFront();

	}

	private void createpartialArasinternalFrame() {
		Font componentOptionseFont = new Font("Arial", Font.BOLD, 10);
		Font componentLablesFont = new Font("Arial", Font.BOLD, 10);
		TitledBorder titledBorder = BorderFactory
				.createTitledBorder(null, "partial Areas",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);

		PartialAreasoptionsInternalFrame = new JInternalFrame("Partial Areas",
				false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						PartialAreasoptionsInternalFrame);
		PartialAreasoptionsInternalFrame.setBorder(null);
		PartialAreasoptionsInternalFrame.setLocation(doseOptionsInternalFrame
				.getX(), doseOptionsInternalFrame.getY()
				+ doseOptionsInternalFrame.getHeight());
		PartialAreasoptionsInternalFrame.setSize(doseOptionsInternalFrame
				.getWidth(), optionsInternalFrame.getHeight()
				- modelSettingsInternalFrame.getHeight()
				- doseOptionsInternalFrame.getHeight());
		PartialAreasoptionsInternalFrame.setVisible(false);
		optionsTabDesktopPane.add(PartialAreasoptionsInternalFrame);

		JPanel partialAreasoptionsPanel = new JPanel();
		partialAreasoptionsPanel.setBorder(titledBorder);
		partialAreasoptionsPanel.setLayout(new FlowLayout());
		partialAreasoptionsPanel.setVisible(true);

		JLabel numberOfPartialAreasLable = new JLabel(
				"Max number of Partial Areas");
		numberOfPartialAreasLable.setFont(componentLablesFont);
		partialAreasoptionsPanel.add(numberOfPartialAreasLable);

		numberOfPartialAreasCombo = new JComboBox();

		numberOfPartialAreasCombo.setFont(componentOptionseFont);

		for (int i = 1; i <= 127; i++) {
			numberOfPartialAreasCombo.addItem(i);

		}
		partialAreasoptionsPanel.add(numberOfPartialAreasCombo);
		numberOfPartialAreasCombo.addActionListener(DDViewLayer
				.createViewLayerInstance());
		numberOfPartialAreasCombo.setActionCommand(PARTIALAREACOUNT);
		PartialAreasoptionsInternalFrame.getContentPane().add(
				partialAreasoptionsPanel);

	}

	private void createRegressionParametersInternalFrame()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		regressionParametersInternalFrame = new JInternalFrame(
				"Regression Parameters", false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						regressionParametersInternalFrame);
		regressionParametersInternalFrame.setLocation(fitSlopesInternalFrame
				.getX(), doseOptionsInternalFrame.getY());
		regressionParametersInternalFrame.setSize(fitSlopesInternalFrame
				.getWidth(), fitSlopesInternalFrame.getHeight());
		regressionParametersInternalFrame.setVisible(false);
		regressionParametersInternalFrame.setBorder(null);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(null,
				"Regression Parameters", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, NCAMainScreen
						.createNcaMainScreenInstance().componentLablesFont,
				Color.black);

		JPanel regressionParametersPanel = new JPanel();
		regressionParametersPanel.setBorder(titledBorder);
		regressionParametersPanel.setLayout(new GridBagLayout());

		JLabel rsquareLable = new JLabel("R Sq");
		rsquareLable
				.setFont(NCAMainScreen.createNcaMainScreenInstance().componentLablesFont);
		GridBagConstraints rsquareLableCon = new GridBagConstraints();
		rsquareLableCon.gridx = 0;
		rsquareLableCon.gridy = 0;
		rsquareLableCon.weightx = 0.5;
		rsquareLableCon.weighty = 1;
		regressionParametersPanel.add(rsquareLable, rsquareLableCon);

		rsquareTextField = new JTextField();
		rsquareTextField.setColumns(5);
		rsquareTextField.setEditable(false);
		rsquareTextField
				.setFont(NCAMainScreen.createNcaMainScreenInstance().componentOptionseFont);
		rsquareTextField.setColumns(5);
		GridBagConstraints rsquareTextFieldCon = new GridBagConstraints();
		rsquareTextFieldCon.gridx = 1;
		rsquareTextFieldCon.gridy = 0;
		rsquareTextFieldCon.weightx = 0.5;
		rsquareTextFieldCon.fill = GridBagConstraints.HORIZONTAL;
		rsquareTextFieldCon.weighty = 1;
		regressionParametersPanel.add(rsquareTextField, rsquareTextFieldCon);

		JLabel adjustedRSquareLable = new JLabel("Adj R sq");
		adjustedRSquareLable.setFont(NCAMainScreen
				.createNcaMainScreenInstance().componentLablesFont);
		GridBagConstraints adjustedRSquareLableCon = new GridBagConstraints();
		adjustedRSquareLableCon.gridx = 0;
		adjustedRSquareLableCon.gridy = 1;
		adjustedRSquareLableCon.weightx = 0.5;
		adjustedRSquareLableCon.weighty = 1;
		regressionParametersPanel.add(adjustedRSquareLable,
				adjustedRSquareLableCon);

		adjustedRSquareTextField = new JTextField();
		adjustedRSquareTextField.setColumns(5);
		adjustedRSquareTextField.setEditable(false);
		adjustedRSquareTextField.setFont(NCAMainScreen
				.createNcaMainScreenInstance().componentOptionseFont);
		adjustedRSquareTextField.setColumns(5);
		GridBagConstraints adjustedRSquareTextFieldCon = new GridBagConstraints();
		adjustedRSquareTextFieldCon.gridx = 1;
		adjustedRSquareTextFieldCon.gridy = 1;
		adjustedRSquareTextFieldCon.weightx = 0.5;
		adjustedRSquareTextFieldCon.fill = GridBagConstraints.HORIZONTAL;
		adjustedRSquareTextFieldCon.weighty = 1;
		regressionParametersPanel.add(adjustedRSquareTextField,
				adjustedRSquareTextFieldCon);

		JLabel halfLifeLable = new JLabel("Half Life");
		halfLifeLable
				.setFont(NCAMainScreen.createNcaMainScreenInstance().componentLablesFont);
		GridBagConstraints halfLifeLableCon = new GridBagConstraints();
		halfLifeLableCon.gridx = 0;
		halfLifeLableCon.gridy = 2;
		halfLifeLableCon.weightx = 0.5;
		halfLifeLableCon.weighty = 1;
		regressionParametersPanel.add(halfLifeLable, halfLifeLableCon);

		halfLifeTextField = new JTextField();
		halfLifeTextField.setColumns(5);
		halfLifeTextField.setEditable(false);
		halfLifeTextField
				.setFont(NCAMainScreen.createNcaMainScreenInstance().componentOptionseFont);
		halfLifeTextField.setColumns(5);
		GridBagConstraints halfLifeTextFieldCon = new GridBagConstraints();
		halfLifeTextFieldCon.gridx = 1;
		halfLifeTextFieldCon.gridy = 2;
		halfLifeTextFieldCon.weightx = 1;
		halfLifeTextFieldCon.fill = GridBagConstraints.HORIZONTAL;
		halfLifeTextFieldCon.weighty = 1;
		regressionParametersPanel.add(halfLifeTextField, halfLifeTextFieldCon);

		JLabel numberOfTerminalpointsLable = new JLabel(
				"No. of terminal points");
		numberOfTerminalpointsLable.setFont(NCAMainScreen
				.createNcaMainScreenInstance().componentOptionseFont);
		GridBagConstraints numberOfTerminalpointsLableCon = new GridBagConstraints();
		numberOfTerminalpointsLableCon.gridx = 0;
		numberOfTerminalpointsLableCon.gridy = 3;
		numberOfTerminalpointsLableCon.weightx = 0.5;
		numberOfTerminalpointsLableCon.weighty = 1;
		regressionParametersPanel.add(numberOfTerminalpointsLable,
				numberOfTerminalpointsLableCon);

		numberOfTerminalPointsTextField = new JTextField();
		numberOfTerminalPointsTextField.setColumns(5);
		numberOfTerminalPointsTextField.setEditable(false);
		numberOfTerminalPointsTextField.setFont(NCAMainScreen
				.createNcaMainScreenInstance().componentOptionseFont);
		numberOfTerminalPointsTextField.setColumns(5);
		GridBagConstraints numberOfTerminalPointsTextFieldCon = new GridBagConstraints();
		numberOfTerminalPointsTextFieldCon.gridx = 1;
		numberOfTerminalPointsTextFieldCon.gridy = 3;
		numberOfTerminalPointsTextFieldCon.fill = GridBagConstraints.HORIZONTAL;
		numberOfTerminalPointsTextFieldCon.weightx = 0.5;
		numberOfTerminalPointsTextFieldCon.weighty = 1;
		regressionParametersPanel.add(numberOfTerminalPointsTextField,
				numberOfTerminalPointsTextFieldCon);

		regressionParametersInternalFrame.add(regressionParametersPanel);

		optionsTabDesktopPane.add(regressionParametersInternalFrame);
	}

	private void createFitSlopesInternalFrame() {
		fitSlopesInternalFrame = new JInternalFrame("Fit Slopes", false, false,
				false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(fitSlopesInternalFrame);
		fitSlopesInternalFrame.setLocation(modelSettingsInternalFrame.getX()
				+ modelSettingsInternalFrame.getWidth(),
				modelSettingsInternalFrame.getY());
		fitSlopesInternalFrame.setSize(optionsInternalFrame.getWidth()
				- modelSelectionInternalFrame.getWidth()
				- modelSettingsInternalFrame.getWidth(),
				modelSettingsInternalFrame.getHeight());
		fitSlopesInternalFrame.setVisible(true);
		GridBagLayout gridbagLayout = new GridBagLayout();
		fitSlopesInternalFrame.setLayout(gridbagLayout);
		fitSlopesInternalFrame.setBorder(null);
		createInternalGuiFitSlopes();
		optionsTabDesktopPane.add(fitSlopesInternalFrame);
		fitSlopesInternalFrame.moveToFront();
		optionsTabDesktopPane.setBackground(fitSlopesInternalFrame
				.getBackground());
	}

	private void createInternalGuiFitSlopes() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Font componentOptionseFont = new Font("Arial", Font.BOLD, 10);
		Font componentLablesFont = new Font("Arial", Font.BOLD, 10);
		TitledBorder titledBorder = BorderFactory
				.createTitledBorder(null, "Fit Slopes",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);

		JPanel fitSlopesPanel = new JPanel();
		fitSlopesPanel.setBorder(titledBorder);
		fitSlopesPanel.setLayout(new GridBagLayout());

		titledBorder = BorderFactory
				.createTitledBorder(null, "Clear",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		JPanel clearPanel = new JPanel();
		clearPanel.setBorder(titledBorder);
		clearPanel.setLayout(new FlowLayout());
		GridBagConstraints clearpanelCon = new GridBagConstraints();
		clearpanelCon.gridx = 0;
		clearpanelCon.gridy = 0;
		clearpanelCon.weighty = 1;

		selectionCheckBoc = new JCheckBox("Selection");
		selectionCheckBoc
				.addActionListener(DDViewLayer.createViewLayerInstance());
		selectionCheckBoc.setActionCommand(SELECTIONPROFILE);
		selectionCheckBoc.setFont(componentOptionseFont);
		clearPanel.add(selectionCheckBoc);
		selectionCheckBoc.setSelected(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().isClearSelection());

		exclusionCheckBox = new JCheckBox("Exclusion");
		exclusionCheckBox
				.addActionListener(DDViewLayer.createViewLayerInstance());
		exclusionCheckBox.setActionCommand(EXCLUDEPROFILE);
		exclusionCheckBox.setFont(componentOptionseFont);
		clearPanel.add(exclusionCheckBox);
		exclusionCheckBox.setSelected(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().isClearExclusion());

		fitSlopesPanel.add(clearPanel, clearpanelCon);

		fitFitSlopesButton = new JButton("Fit");
		fitFitSlopesButton.setFont(componentOptionseFont);
		fitFitSlopesButton.setPreferredSize(new Dimension(70, 20));
		fitFitSlopesButton
				.addMouseListener(DDViewLayer.createViewLayerInstance());
		GridBagConstraints fitFitSlopesButtonCon = new GridBagConstraints();
		fitFitSlopesButtonCon.gridx = 0;
		fitFitSlopesButtonCon.gridy = 1;
		fitFitSlopesButtonCon.weighty = 1;
		fitFitSlopesButtonCon.anchor = GridBagConstraints.LINE_END;
		fitSlopesPanel.add(fitFitSlopesButton, fitFitSlopesButtonCon);

		fitSlopesInternalFrame.getContentPane().add(fitSlopesPanel);

	}

	private void createDoseOptionsInternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {

		doseOptionsInternalFrame = new JInternalFrame("Dose Options", false,
				false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(doseOptionsInternalFrame);
		// need to modify
		doseOptionsInternalFrame.setLocation(modelSettingsInternalFrame.getX(),
				modelSettingsInternalFrame.getY()
						+ modelSettingsInternalFrame.getHeight());
		doseOptionsInternalFrame.setSize(modelSettingsInternalFrame.getWidth(),
				modelSettingsInternalFrame.getHeight());
		// doseOptionsInternalFrame.setSize(dataDisplayFrame.getWidth()/2,dataDisplayFrame.getHeight()/4);
		doseOptionsInternalFrame.setVisible(true);
		GridBagLayout gridbagLayout = new GridBagLayout();
		doseOptionsInternalFrame.setLayout(gridbagLayout);
		doseOptionsInternalFrame.setBorder(null);
		// doseOptionsInternalFrame.setBackground(Color.white);
		optionsTabDesktopPane.add(doseOptionsInternalFrame);
		createInternalGuiDoseOptions();
		doseOptionsInternalFrame.moveToFront();

	}

	private void createInternalGuiDoseOptions() throws RowsExceededException,
			WriteException, BiffException, IOException {

		Font componentOptionseFont = new Font("Arial", Font.BOLD, 10);
		Font componentLablesFont = new Font("Arial", Font.BOLD, 10);
		TitledBorder titledBorder = BorderFactory
				.createTitledBorder(null, "Dose Options",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);

		JPanel doseOptionsPanel = new JPanel();
		doseOptionsPanel.setBorder(titledBorder);
		doseOptionsPanel.setLayout(new GridBagLayout());

		JLabel typeofAdministrationLable = new JLabel("Type");
		typeofAdministrationLable.setFont(componentOptionseFont);
		GridBagConstraints typeofAdministrationLableCon = new GridBagConstraints();
		typeofAdministrationLableCon.gridx = 0;
		typeofAdministrationLableCon.gridy = 0;
		typeofAdministrationLableCon.weighty = 1;
		// typeofAdministrationLableCon.weightx = 0.1;
		typeofAdministrationLableCon.anchor = GridBagConstraints.LINE_START;
		doseOptionsPanel.add(typeofAdministrationLable,
				typeofAdministrationLableCon);

		rootOfAdministration.setBackground(Color.white);
		rootOfAdministration.setFont(componentOptionseFont);
		rootOfAdministration.addItem("Extrvascular");
		rootOfAdministration.addItem("IV Bolus");
		rootOfAdministration.addItem("IV Infusion");
		rootOfAdministration.addItem("User Defined Dosing");

		rootOfAdministration.setSelectedIndex(0);

		rootOfAdministration.addActionListener(DDViewLayer
				.createViewLayerInstance());
		rootOfAdministration.setActionCommand(ROOTOFADMIN);

		GridBagConstraints rootOfAdministrationCon = new GridBagConstraints();
		rootOfAdministrationCon.gridx = 1;
		rootOfAdministrationCon.gridy = 0;
		rootOfAdministrationCon.weighty = 1;
		rootOfAdministrationCon.anchor = GridBagConstraints.LINE_START;
		doseOptionsPanel.add(rootOfAdministration, rootOfAdministrationCon);

		doseUnitButton = new JButton("Unit");
		doseUnitButton.setFont(componentOptionseFont);
		doseUnitButton.setPreferredSize(new Dimension(70, 20));
		doseUnitButton.addActionListener(DDViewLayer.createViewLayerInstance());
		doseUnitButton.setActionCommand(UBDOSEUNIT);

		GridBagConstraints doseUnitButtonCon = new GridBagConstraints();
		doseUnitButtonCon.gridx = 0;
		doseUnitButtonCon.gridy = 1;
		doseUnitButtonCon.weighty = 1;
		doseUnitButtonCon.anchor = GridBagConstraints.LINE_START;
		doseOptionsPanel.add(doseUnitButton, doseUnitButtonCon);

		unitTextField = new JTextField();
		unitTextField.setFont(componentOptionseFont);
		unitTextField.setColumns(10);
		unitTextField.addActionListener(new NcaOptDoseUnitTextFieldHandler());

		GridBagConstraints unitTextFieldCon = new GridBagConstraints();
		unitTextFieldCon.gridx = 1;
		unitTextFieldCon.gridy = 1;
		unitTextFieldCon.weighty = 1;
		unitTextFieldCon.anchor = GridBagConstraints.LINE_START;
		unitTextFieldCon.fill = GridBagConstraints.HORIZONTAL;
		doseOptionsPanel.add(unitTextField, unitTextFieldCon);

		JLabel normalizationLable = new JLabel("Normalization");
		normalizationLable.setFont(componentOptionseFont);
		GridBagConstraints normalizationLableCon = new GridBagConstraints();
		normalizationLableCon.gridx = 0;
		normalizationLableCon.gridy = 2;
		normalizationLableCon.weighty = 1;
		// normalizationLableCon.weightx = 0.1;
		normalizationLableCon.anchor = GridBagConstraints.LINE_START;
		doseOptionsPanel.add(normalizationLable, normalizationLableCon);

		normalizationUnit = new JComboBox();
		normalizationUnit.setBackground(Color.white);
		normalizationUnit.setFont(componentOptionseFont);
		normalizationUnit.setPreferredSize(new Dimension(80, 25));
		normalizationUnit.addItem("");
		normalizationUnit.addItem("/Kg");
		normalizationUnit.addItem("/m*m");
		normalizationUnit.addActionListener(new NcaOptNormUnitHandler());

		GridBagConstraints normalizationUnitCon = new GridBagConstraints();
		normalizationUnitCon.gridx = 1;
		normalizationUnitCon.gridy = 2;
		normalizationUnitCon.weighty = 1;
		normalizationUnitCon.anchor = GridBagConstraints.LINE_START;
		doseOptionsPanel.add(normalizationUnit, normalizationUnitCon);

		doseOptionsInternalFrame.getContentPane().add(doseOptionsPanel);

	}

	private void createModelSettingsInternalFrame()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		modelSettingsInternalFrame = new JInternalFrame("Model Settings",
				false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(modelSettingsInternalFrame);

		// need to modify
		modelSettingsInternalFrame.setLocation(modelSelectionInternalFrame
				.getWidth()
				+ modelSelectionInternalFrame.getX(), 0);
		int height = (int) ((int) modelSelectionInternalFrame.getHeight() / 3);
		modelSettingsInternalFrame.setSize(modelSelectionInternalFrame
				.getWidth(), height);
		modelSettingsInternalFrame.setVisible(true);
		GridBagLayout gridbagLayout = new GridBagLayout();
		modelSettingsInternalFrame.setLayout(gridbagLayout);
		modelSettingsInternalFrame.setBorder(null);
		createInternalGuiModelSettings();
		optionsTabDesktopPane.add(modelSettingsInternalFrame);
		modelSettingsInternalFrame.moveToFront();

	}

	private void createInternalGuiModelSettings() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Font componentOptionseFont = new Font("Arial", Font.BOLD, 10);
		Font componentLablesFont = new Font("Arial", Font.BOLD, 10);
		TitledBorder titledBorder = BorderFactory
				.createTitledBorder(null, "Model Settings",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);

		modelSettingsPanel = new JPanel();
		modelSettingsPanel.setLayout(new GridBagLayout());
		modelSettingsPanel.setBorder(titledBorder);

/*		pageBreaksCheckBox = new JCheckBox("Page Breaks");
		pageBreaksCheckBox.setFont(componentOptionseFont);
		pageBreaksCheckBox.addActionListener(ViewLayer
				.createViewLayerInstance());
		pageBreaksCheckBox.setActionCommand(PAGEBREAK);
		GridBagConstraints pageBreaksCheckBoxCon = new GridBagConstraints();
		pageBreaksCheckBoxCon.gridx = 0;
		pageBreaksCheckBoxCon.gridy = 0;
		pageBreaksCheckBoxCon.weighty = 1;
		pageBreaksCheckBoxCon.anchor = GridBagConstraints.LINE_START;
		pageBreaksCheckBox.setSelected(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.isPageBreakSelected());
		modelSettingsPanel.add(pageBreaksCheckBox, pageBreaksCheckBoxCon);*/

		intermediateOutputCheckBox
				.addActionListener(new NcaOptIntOutputCheckBoxHandler());
		intermediateOutputCheckBox.setSelected(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.isIntermediateoutputSelected());
		intermediateOutputCheckBox.setFont(componentOptionseFont);
		GridBagConstraints immediateOutputCheckBoxCon = new GridBagConstraints();
		immediateOutputCheckBoxCon.gridx = 0;
		immediateOutputCheckBoxCon.gridy = 2;
		immediateOutputCheckBoxCon.weighty = 1;
		immediateOutputCheckBoxCon.anchor = GridBagConstraints.LINE_START;
		// displayFormatRadioGroup.add(customradioButton);
		// formatPanel.add(customradioButton,customRadioButtonCon);
		modelSettingsPanel.add(intermediateOutputCheckBox,
				immediateOutputCheckBoxCon);

		excludeProfilesCheckBox.setFont(componentOptionseFont);
		// excludeProfilesCheckBox.setBackground(Color.white);
		GridBagConstraints excludeProfilesCheckBoxCon = new GridBagConstraints();
		excludeProfilesCheckBoxCon.gridx = 0;
		excludeProfilesCheckBoxCon.gridy = 3;
		excludeProfilesCheckBoxCon.weighty = 1;
		excludeProfilesCheckBoxCon.anchor = GridBagConstraints.LINE_START;
		excludeProfilesCheckBox.setSelected(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().isExcluseProfiles());
		excludeProfilesCheckBox
				.addActionListener(new NcaOptExcludeProfHandler());

		modelSettingsPanel.add(excludeProfilesCheckBox,
				excludeProfilesCheckBoxCon);

		disableCurveStrippingCheckBox.setFont(componentOptionseFont);
		// disableCurveStrippingCheckBox.setBackground(Color.white);
		GridBagConstraints disableCurveStrippingCheckBoxCon = new GridBagConstraints();
		disableCurveStrippingCheckBoxCon.gridx = 0;
		disableCurveStrippingCheckBoxCon.gridy = 4;
		disableCurveStrippingCheckBoxCon.weighty = 1;
		disableCurveStrippingCheckBoxCon.anchor = GridBagConstraints.LINE_START;
		disableCurveStrippingCheckBox.setSelected(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.isDisableCurveStripping());
		modelSettingsPanel.add(disableCurveStrippingCheckBox,
				disableCurveStrippingCheckBoxCon);
		modelSettingsInternalFrame.getContentPane().add(modelSettingsPanel);

	}

	private void createModelSelectionInternalFrame()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		modelSelectionInternalFrame = new JInternalFrame("Model Selection",
				false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(modelSelectionInternalFrame);

		// need to modify
		modelSelectionInternalFrame.setLocation(0, 0);
		int width = (int) (optionsInternalFrame.getWidth() / 2.7);
		modelSelectionInternalFrame.setSize(width, optionsInternalFrame
				.getHeight() - 75); // 50 is subtracted because tabs are added
		// and it is to compensate the tabs height
		modelSelectionInternalFrame.setVisible(true);
		GridBagLayout gridbagLayout = new GridBagLayout();
		modelSelectionInternalFrame.setLayout(gridbagLayout);
		modelSelectionInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		createInternalGuiModelSelection();
		optionsTabDesktopPane.add(modelSelectionInternalFrame);
		modelSelectionInternalFrame.moveToFront();
	}

	private void createPlotOptionsInternalFrame() {
		plotOptionsInternalFrame.setLayout(new GridBagLayout());
		viewLable = new JLabel(" Plot View");
		viewLable.setEnabled(false);
		GridBagConstraints viewLableCon = new GridBagConstraints();
		viewLableCon.gridx = 0;
		viewLableCon.gridy = 0;
		viewLableCon.anchor = GridBagConstraints.LINE_START;
		plotOptionsInternalFrame.getContentPane().add(viewLable, viewLableCon);

		viewsCombo = new JComboBox();
		viewsCombo.setEnabled(false);
		viewsCombo.addMouseListener(DDViewLayer.createViewLayerInstance());
		viewsCombo.addItem("Lin");
		viewsCombo.addItem("Log");
		GridBagConstraints viewComboCon = new GridBagConstraints();
		viewComboCon.gridx = 1;
		viewComboCon.gridy = 0;
		viewComboCon.anchor = GridBagConstraints.LINE_START;
		plotOptionsInternalFrame.getContentPane().add(viewsCombo, viewComboCon);

	}

	void createInternalGuiModelSelection() throws RowsExceededException,
			WriteException, BiffException, IOException {

		Font componentOptionseFont = new Font("Arial", Font.BOLD, 10);
		Font componentLablesFont = new Font("Arial", Font.BOLD, 10);
		TitledBorder titledBorder = BorderFactory
				.createTitledBorder(null, "Model Type",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		JPanel modelTypePanel = new JPanel();
		modelTypePanel.setFont(componentOptionseFont);
		modelTypePanel.setPreferredSize(new Dimension(160, 50));
		modelTypePanel.setBorder(titledBorder);
		GridBagConstraints modelTypePanelCon = new GridBagConstraints();
		modelTypePanelCon.gridx = 0;
		modelTypePanelCon.gridy = 0;
		modelTypePanelCon.weighty = 1;
		modelTypePanelCon.weightx = 0.5;
		modelTypePanelCon.anchor = GridBagConstraints.LINE_START;

		modelOptions.setFont(componentOptionseFont);
		modelOptions.setBackground(Color.white);
		modelOptions.setPreferredSize(new Dimension(150, 20));
		modelOptions.addItem("Plasma Model(1-3)");
		modelOptions.addItem("Urine Model(4-6)");
		modelOptions.addItem("Drug Effect Model(7)");
		modelTypePanel.add(modelOptions);
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		modelOptions.setSelectedIndex(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getModelType());

		modelOptions.setSelectedItem("Plasma Model(1)");
		modelSelectionInternalFrame.getContentPane().add(modelTypePanel,
				modelTypePanelCon);
		modelOptions.addActionListener(DDViewLayer.createViewLayerInstance());
		modelOptions.setActionCommand(MODELTYPE);

		JPanel weightingAndSparseOptionsPanel = new JPanel();
		weightingAndSparseOptionsPanel.setLayout(new FlowLayout());
		GridBagConstraints weightingAndSparseOptionsPanelCon = new GridBagConstraints();
		weightingAndSparseOptionsPanelCon.gridx = 0;
		weightingAndSparseOptionsPanelCon.gridy = 1;
		weightingAndSparseOptionsPanelCon.weighty = 1;
		weightingAndSparseOptionsPanelCon.weightx = 0.5;
		weightingAndSparseOptionsPanelCon.anchor = GridBagConstraints.LINE_START;
		weightingAndSparseOptionsPanelCon.fill = GridBagConstraints.BOTH;

		modelSelectionInternalFrame.getContentPane().add(
				weightingAndSparseOptionsPanel,
				weightingAndSparseOptionsPanelCon);

		// weightingValueTextField = new JTextField(2);
		weightingValueTextField.setBackground(Color.white);
		//weightingValueTextField.setEditable(false);

		weightingAndSparseOptionsPanel.add(weightingValueTextField);

		JPanel weightingOptionsPanel = new JPanel();
		weightingOptionsPanel.setFont(componentOptionseFont);
		weightingOptionsPanel.setPreferredSize(new Dimension(110, 50));
		// weightingOptionsPanel.setBackground(Color.white);
		titledBorder = BorderFactory
				.createTitledBorder(null, "Weight Option",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		weightingOptionsPanel.setBorder(titledBorder);

		weightingOptions = new JComboBox();
		weightingOptions.setFont(componentOptionseFont);
		weightingOptions.setPreferredSize(new Dimension(100, 20));
		weightingOptions.setBackground(Color.white);
		weightingOptions.addItem("Uniform");
		weightingOptions.addItem("1/Y");
		weightingOptions.addItem("1/Y*Y");
		weightingOptionsPanel.add(weightingOptions);
		/*
		 * NcaOptWeightOptionsHandler ncaOptionsGui = new
		 * NcaOptWeightOptionsHandler(); ncaOptionsGui.
		 */
		weightingOptions.addActionListener(DDViewLayer.createViewLayerInstance());
		weightingOptions.setActionCommand(NCAWEIGHT);
		weightingOptions
				.setSelectedIndex(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getModelInputsObj()
						.getWeightingOption());
		weightingAndSparseOptionsPanel.add(weightingOptionsPanel);

		sparseCheckBox.setFont(componentOptionseFont);
		// sparseradioButton.setBackground(Color.white);
		sparseCheckBox.setSelected(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getisSparseSelected());
		sparseCheckBox.addActionListener(DDViewLayer.createViewLayerInstance());
		sparseCheckBox.setActionCommand(NCASPARSE);

		weightingAndSparseOptionsPanel.add(sparseCheckBox);

		JPanel titlesPanel = new JPanel();
		titlesPanel.setFont(componentOptionseFont);
		titledBorder = BorderFactory
				.createTitledBorder(null, "Titles",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		titlesPanel.setBorder(titledBorder);
		titlesPanel.setPreferredSize(new Dimension(270, 90));
		GridBagConstraints titlesPanelCon = new GridBagConstraints();
		titlesPanelCon.gridx = 0;
		titlesPanelCon.gridy = 2;
		// titlesPanelCon.weighty = 1;
		titlesPanelCon.anchor = GridBagConstraints.LINE_START;
		titlesPanelCon.fill = GridBagConstraints.HORIZONTAL;

		titlesTextArea = new JTextArea(2, 23);
		JScrollPane titlesTextAreaScrollPane = new JScrollPane(titlesTextArea);
		titlesPanel.add(titlesTextAreaScrollPane);
		modelSelectionInternalFrame.getContentPane().add(titlesPanel,
				titlesPanelCon);

		JPanel calculationMethodPanel = new JPanel();
		calculationMethodPanel.setPreferredSize(new Dimension(270, 50));
		// calculationMethodPanel.setBackground(Color.white);
		titledBorder = BorderFactory
				.createTitledBorder(null, "Calculation Method",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		calculationMethodPanel.setBorder(titledBorder);
		GridBagConstraints calculationMethodPanelCon = new GridBagConstraints();
		calculationMethodPanelCon.gridx = 0;
		calculationMethodPanelCon.gridy = 3;
		// calculationMethodPanelCon.weighty = 1;
		calculationMethodPanelCon.anchor = GridBagConstraints.LINE_START;

		calculationMethodOptions.setFont(componentOptionseFont);
		calculationMethodOptions.setBackground(Color.white);
		calculationMethodOptions.setPreferredSize(new Dimension(250, 20));
		calculationMethodOptions.addItem("Lin Log Trapezoidal(1)");
		calculationMethodOptions.addItem("Lin UP Log Down(2)");
		calculationMethodOptions
				.addItem("Lin Trapezoidal Lin Interpolation(3)");
		calculationMethodOptions
				.addItem("Lin Trapezoidal Lin/Log Interpolation(4)");
		calculationMethodPanel.add(calculationMethodOptions);
		modelSelectionInternalFrame.getContentPane().add(
				calculationMethodPanel, calculationMethodPanelCon);
		calculationMethodOptions.setSelectedIndex(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getCalculationMethod());
		calculationMethodOptions
				.addActionListener(DDViewLayer.createViewLayerInstance());
		calculationMethodOptions.setActionCommand(NCACALCMETHOD);

		numberOfPartialAreaLable = new JLabel("Number of Partial Area");
		numberOfPartialAreaLable.setVisible(false);
		GridBagConstraints numberOfPartialAreaLableCon = new GridBagConstraints();
		numberOfPartialAreaLableCon.gridx = 1;
		numberOfPartialAreaLableCon.gridy = 4;
		// numberOfPartialAreaLableCon.weighty = 1;
		// titleLableCon.weighty = 0.1;
		numberOfPartialAreaLableCon.anchor = GridBagConstraints.LINE_START;

		modelSelectionInternalFrame.getContentPane().add(
				numberOfPartialAreaLable, numberOfPartialAreaLableCon);

	}

}
