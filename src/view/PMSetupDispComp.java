package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class PMSetupDispComp implements EventCodes {

	public static PMSetupDispComp PM_SETUP_DISP = null;

	public static PMSetupDispComp createPMSetupDisCompInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (PM_SETUP_DISP == null) {
			PM_SETUP_DISP = new PMSetupDispComp("just object creation");
		}
		return PM_SETUP_DISP;
	}

	private final class RelativeErrorRadioButtonHandler implements
			ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getMappingDataObj()
					.setScaleIsRelative(true);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getMappingDataObj()
					.setScaleIsAbsolute(false);

		}
	}

	private final class AbsoluteErrorRadioButtonHandler implements
			ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getMappingDataObj()
					.setScaleIsAbsolute(true);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getMappingDataObj()
					.setScaleIsRelative(false);

		}
	}

	JInternalFrame setupDisplayInternalFrame;
	JDesktopPane setupDisplayInternalFrameDesktoipPane;
	JInternalFrame plotMavenavailableVariablesInternalFrame;
	DefaultListModel plotMavenavailableVariablesListmodel;
	JList plotMavenavailableColumnsList;
	Vector<String> plotMavenavailableColumnsVector;
	JInternalFrame plotMavenMappingInternalFrame;
	private TitledBorder titledBorder;
	private Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	private Font componentLablesFont = new Font("Arial", Font.BOLD, 11);
	final ImageIcon moveToLeftButtonImage = new ImageIcon("left.png");
	final ImageIcon moveToRightButtonIcon = new ImageIcon("right.png");
	Icon toLeft = new ImageIcon("left.png");
	Icon toRight = new ImageIcon("right.png");
	JButton availableToxButton;
	JTextField plotMavenxvariableTextField;
	JButton availableToyButton;
	JTextField yvariableTextField;
	String upVariable;
	String downVariable;
	JList plotMavenSortVariablesList;
	JButton availableToSortButton;
	JButton availableToGroupButton;
	JList plotMavengroupVariablesList;
	boolean isAvailableToSort;
	boolean isAvailableToGroup;
	DefaultListModel plotMavenSortVariablesListModel;
	DefaultListModel plotMavenGroupVariablesListModel;
	JButton availableToUpVariablebutton;
	JButton availableToDownVariableButton;
	JTextField upVariableTextField;
	JTextField downVariablesTextField;
	JTextField plotMavenGroupVariableTextField;
	boolean isFromAvailableToUp = true;
	boolean isFromAvailableToDown = true;
	JRadioButton absoluteErrorBarsRadioButton;
	JRadioButton relativeErrorButtonRadioButton;
	String plotMavenXColumnName;
	int plotMavenyColumnCorrespondinOriginalIndex;
	int plotMavenxColumnCorrespondinOriginalIndex;
	String ycolumnSelected;
	Vector<String> plotMavenSortVariablesListVector;
	String groupVariable;

	public PMSetupDispComp(String dummy) throws RowsExceededException,
			WriteException, BiffException, IOException {

	}

	void cratePlotMavenSetupDisplayFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {
		setupDisplayInternalFrame = new JInternalFrame("Display", false, false,
				false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(setupDisplayInternalFrame);
		setupDisplayInternalFrame
				.setLocation(
						PMSetupAvailComp.createPMAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ PMSetupAvailComp.createPMAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						PMSetupAvailComp.createPMAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		setupDisplayInternalFrame
				.setSize(
						PlotMavenCreateUI.createPlotMavenUIInst().PlotMavenMainInternalFrame
								.getWidth()
								- PMSetupAvailComp.createPMAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						PMSetupAvailComp.createPMAvailCompInst().setupAvailableComponentsInternalFrame
								.getHeight());
		setupDisplayInternalFrame.setVisible(true);
		setupDisplayInternalFrame
				.setBorder(DDViewLayer.createViewLayerInstance().b);
		PlotMavenCreateUI.createPlotMavenUIInst().plotMavenSetupDesktopPane
				.add(setupDisplayInternalFrame);
		setupDisplayInternalFrame.moveToFront();
		setupDisplayInternalFrameDesktoipPane = new JDesktopPane();
		setupDisplayInternalFrame.getContentPane().add(
				setupDisplayInternalFrameDesktoipPane);
		createavailableVariablesInternalFrame();
		createMappinInternalFrame();

	}

	private void createMappinInternalFrame() {
		plotMavenMappingInternalFrame = new JInternalFrame(
				"Plot Maven Mapping", false,false,false,false);
		plotMavenMappingInternalFrame.setLocation(
				plotMavenavailableVariablesInternalFrame.getX()
						+ plotMavenavailableVariablesInternalFrame.getWidth(),
				plotMavenavailableVariablesInternalFrame.getY());
		plotMavenMappingInternalFrame.setSize(setupDisplayInternalFrame
				.getWidth()
				- plotMavenavailableVariablesInternalFrame.getWidth(),
				plotMavenavailableVariablesInternalFrame.getHeight());
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(plotMavenMappingInternalFrame);
		plotMavenMappingInternalFrame.setVisible(true);
		plotMavenMappingInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		plotMavenMappingInternalFrame.setBackground(Color.white);
		setupDisplayInternalFrameDesktoipPane
				.add(plotMavenMappingInternalFrame);
		createMappingUI();

	}

	private void createMappingUI() {
		JTabbedPane plotMavenMappingInternalFrameTabbedPane = new JTabbedPane();
		// JDesktopPane plotMavenxyTabDesktopPane = new JDesktopPane();
		JPanel plotMavenxyTabPanel = new JPanel();
		plotMavenMappingInternalFrameTabbedPane.addTab("XY Var",
				plotMavenxyTabPanel);
		// JDesktopPane plotMavenSortingTabDesktopPane = new JDesktopPane();
		JPanel plotMavenSortTabPanel = new JPanel();
		plotMavenMappingInternalFrameTabbedPane.addTab("Sorting",
				plotMavenSortTabPanel);
		// JDesktopPane plotMavenErrorBarsTabDesktopPane = new JDesktopPane();
		JPanel plotMavenErrorBarsPanel = new JPanel();
		plotMavenMappingInternalFrameTabbedPane.addTab("Error Bars",
				plotMavenErrorBarsPanel);
		plotMavenMappingInternalFrame.getContentPane().add(
				plotMavenMappingInternalFrameTabbedPane);
		// plotMavenxyTabPanel.setBackground(Color.white);
		plotMavenxyTabPanel.setLayout(new GridBagLayout());

		titledBorder = BorderFactory
				.createTitledBorder(null, "X Variable",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);

		plotMavenXYplanelCreation(plotMavenxyTabPanel);

		plotMavenSortTabPanelCreation(plotMavenSortTabPanel);

		// Error bars UI
		errorBarsCreation(plotMavenErrorBarsPanel);

		titledBorder = BorderFactory
				.createTitledBorder(null, "Scale",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		JPanel ErrorbarsScalePanel = new JPanel();
		ErrorbarsScalePanel.setBorder(titledBorder);
		ErrorbarsScalePanel.setLayout(new GridBagLayout());
		GridBagConstraints errorBarsScalePanelCon = new GridBagConstraints();
		errorBarsScalePanelCon.gridx = 0;
		errorBarsScalePanelCon.gridy = 2;
		errorBarsScalePanelCon.anchor = GridBagConstraints.LINE_START;
		errorBarsScalePanelCon.weightx = 1;
		errorBarsScalePanelCon.weighty = 1;
		errorBarsScalePanelCon.fill = GridBagConstraints.HORIZONTAL;

		absoluteErrorBarsRadioButton = new JRadioButton("Absolute");
		absoluteErrorBarsRadioButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		absoluteErrorBarsRadioButton
				.addActionListener(new AbsoluteErrorRadioButtonHandler());
		GridBagConstraints absoluteErrorBarsRadioButtonCon = new GridBagConstraints();
		absoluteErrorBarsRadioButtonCon.gridx = 0;
		absoluteErrorBarsRadioButtonCon.gridy = 0;
		absoluteErrorBarsRadioButtonCon.weightx = 1;
		ErrorbarsScalePanel.add(absoluteErrorBarsRadioButton,
				absoluteErrorBarsRadioButtonCon);

		relativeErrorButtonRadioButton = new JRadioButton("Relative");
		relativeErrorButtonRadioButton.setSelected(true);
		relativeErrorButtonRadioButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		relativeErrorButtonRadioButton
				.addActionListener(new RelativeErrorRadioButtonHandler());
		GridBagConstraints relativeErrorButtonRadioButtonCon = new GridBagConstraints();
		relativeErrorButtonRadioButtonCon.gridx = 1;
		relativeErrorButtonRadioButtonCon.gridy = 0;
		relativeErrorButtonRadioButtonCon.weightx = 1;
		ErrorbarsScalePanel.add(relativeErrorButtonRadioButton,
				relativeErrorButtonRadioButtonCon);

		plotMavenErrorBarsPanel
				.add(ErrorbarsScalePanel, errorBarsScalePanelCon);

		ButtonGroup relativeOrAbsolutebuttonGroup = new ButtonGroup();
		relativeOrAbsolutebuttonGroup.add(absoluteErrorBarsRadioButton);
		relativeOrAbsolutebuttonGroup.add(relativeErrorButtonRadioButton);
	}

	private void errorBarsCreation(JPanel plotMavenErrorBarsPanel) {
		plotMavenErrorBarsPanel.setLayout(new GridBagLayout());
		// plotMavenErrorBarsPanel.setBackground(Color.white);

		availableToUpVariablebutton = new JButton(moveToRightButtonIcon);
		availableToUpVariablebutton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		availableToUpVariablebutton.setActionCommand(PMAVAILTOUP);
		GridBagConstraints availableToUpVariablebuttonCon = new GridBagConstraints();
		availableToUpVariablebuttonCon.gridx = 0;
		availableToUpVariablebuttonCon.gridy = 0;
		availableToUpVariablebuttonCon.weightx = 1;

		titledBorder = BorderFactory
				.createTitledBorder(null, "Up Variable",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		JPanel upVariablePanel = new JPanel();
		upVariablePanel.setBorder(titledBorder);
		upVariablePanel.setLayout(new GridBagLayout());

		GridBagConstraints upVariablePanelCon = new GridBagConstraints();
		upVariablePanelCon.gridx = 0;
		upVariablePanelCon.gridy = 0;
		upVariablePanelCon.anchor = GridBagConstraints.LINE_START;
		upVariablePanelCon.weightx = 1;
		upVariablePanelCon.fill = GridBagConstraints.HORIZONTAL;
		upVariablePanelCon.weighty = 1;
		upVariablePanel.add(availableToUpVariablebutton,
				availableToUpVariablebuttonCon);
		plotMavenErrorBarsPanel.add(upVariablePanel, upVariablePanelCon);

		availableToDownVariableButton = new JButton(moveToRightButtonIcon);
		availableToDownVariableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		availableToDownVariableButton.setActionCommand(PMAVAILTODOWn);
		GridBagConstraints availableToDownVariableButtonCon = new GridBagConstraints();
		availableToDownVariableButtonCon.gridx = 0;
		availableToDownVariableButtonCon.gridy = 0;
		availableToDownVariableButtonCon.weightx = 1;

		upVariableTextField = new JTextField("Up Variable");
		upVariableTextField.setBackground(Color.white);
		upVariableTextField.addFocusListener(DDViewLayer
				.createViewLayerInstance());
		upVariableTextField.setColumns(15);
		upVariableTextField.setEditable(false);
		GridBagConstraints upVariableTextFieldCon = new GridBagConstraints();
		upVariableTextFieldCon.gridx = 1;
		upVariableTextFieldCon.gridy = 0;
		upVariableTextFieldCon.weightx = 1;
		upVariablePanel.add(upVariableTextField, upVariableTextFieldCon);

		downVariablesTextField = new JTextField("Down Variable");
		downVariablesTextField.setBackground(Color.white);
		downVariablesTextField.addFocusListener(DDViewLayer
				.createViewLayerInstance());
		downVariablesTextField.setColumns(15);
		downVariablesTextField.setEditable(false);
		GridBagConstraints downVariablesTextFieldCon = new GridBagConstraints();
		downVariablesTextFieldCon.gridx = 1;
		downVariablesTextFieldCon.gridy = 0;
		downVariablesTextFieldCon.weightx = 1;

		titledBorder = BorderFactory
				.createTitledBorder(null, "Down Variable",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		JPanel downVariablepanel = new JPanel();
		downVariablepanel.setBorder(titledBorder);
		downVariablepanel.setLayout(new GridBagLayout());
		GridBagConstraints downVariablePanelCon = new GridBagConstraints();
		downVariablePanelCon.gridx = 0;
		downVariablePanelCon.gridy = 1;
		downVariablePanelCon.anchor = GridBagConstraints.LINE_START;
		downVariablePanelCon.fill = GridBagConstraints.HORIZONTAL;
		downVariablePanelCon.weightx = 1;
		downVariablePanelCon.weighty = 1;
		downVariablepanel.add(availableToDownVariableButton,
				availableToDownVariableButtonCon);
		downVariablepanel
				.add(downVariablesTextField, downVariablesTextFieldCon);
		plotMavenErrorBarsPanel.add(downVariablepanel, downVariablePanelCon);

	}

	private void plotMavenSortTabPanelCreation(JPanel plotMavenSortTabPanel) {
		plotMavenSortTabPanel.setLayout(new GridBagLayout());

		availableToSortButton = new JButton(moveToRightButtonIcon);
		if (availableToSortButton.getIcon() == null) {
			throw new NullPointerException();

		}
		titledBorder = BorderFactory
				.createTitledBorder(null, "Sort Variables",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		JPanel sortvariablesPanel = new JPanel();
		sortvariablesPanel.setBorder(titledBorder);
		sortvariablesPanel.setLayout(new GridBagLayout());
		GridBagConstraints sortvariablesPanelCon = new GridBagConstraints();
		sortvariablesPanelCon.gridx = 0;
		sortvariablesPanelCon.gridy = 0;
		sortvariablesPanelCon.weightx = 1;
		sortvariablesPanelCon.weighty = 0.5;
		sortvariablesPanelCon.fill = GridBagConstraints.HORIZONTAL;
		sortvariablesPanelCon.anchor = GridBagConstraints.LINE_START;
		plotMavenSortTabPanel.add(sortvariablesPanel, sortvariablesPanelCon);

		availableToSortButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		availableToSortButton.setActionCommand(PMAVAILTOSORT);
		GridBagConstraints availableToSortButtonCon = new GridBagConstraints();
		availableToSortButtonCon.gridx = 0;
		availableToSortButtonCon.gridy = 0;
		availableToSortButtonCon.weightx = 0.5;
		// availableToSortButtonCon.anchor = GridBagConstraints.LINE_START;
		sortvariablesPanel.add(availableToSortButton, availableToSortButtonCon);

		titledBorder = BorderFactory
				.createTitledBorder(null, "Group Variable",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		JPanel groupVariablesPanel = new JPanel();
		groupVariablesPanel.setBorder(titledBorder);
		groupVariablesPanel.setLayout(new GridBagLayout());
		GridBagConstraints groupVariablesPanelCon = new GridBagConstraints();
		groupVariablesPanelCon.gridx = 0;
		groupVariablesPanelCon.gridy = 1;
		groupVariablesPanelCon.weightx = 1;
		groupVariablesPanelCon.anchor = GridBagConstraints.LINE_START;
		groupVariablesPanelCon.fill = GridBagConstraints.HORIZONTAL;
		groupVariablesPanelCon.weighty = 0.5;
		plotMavenSortTabPanel.add(groupVariablesPanel, groupVariablesPanelCon);

		availableToGroupButton = new JButton(moveToRightButtonIcon);
		availableToGroupButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		availableToGroupButton.setActionCommand(PMAVAILTOGROUP);
		// availableToGroupButton.setPreferredSize(new Dimension(65, 20));
		GridBagConstraints availableToGroupButtonCon = new GridBagConstraints();
		availableToGroupButtonCon.gridx = 0;
		availableToGroupButtonCon.gridy = 0;
		availableToGroupButtonCon.weightx = 0.5;
		// availableToGroupButtonCon.anchor = GridBagConstraints.LINE_START;
		groupVariablesPanel.add(availableToGroupButton,
				availableToGroupButtonCon);

		plotMavenSortVariablesListModel = new DefaultListModel();
		plotMavenSortVariablesList = new JList(plotMavenSortVariablesListModel);
		plotMavenSortVariablesList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		plotMavenSortVariablesList.setVisibleRowCount(5);
		plotMavenSortVariablesList.setVisible(true);
		JScrollPane plotMavenSortVariablesScrollPane = new JScrollPane(
				plotMavenSortVariablesList);
		GridBagConstraints plotMavenSortVariablesScrollPaneCon = new GridBagConstraints();
		plotMavenSortVariablesScrollPaneCon.gridx = 1;
		plotMavenSortVariablesScrollPaneCon.gridy = 0;
		plotMavenSortVariablesScrollPaneCon.weightx = 1;
		// plotMavenSortVariablesScrollPaneCon.anchor =
		// GridBagConstraints.LINE_END;
		plotMavenSortVariablesScrollPane.setPreferredSize(new Dimension(100,
				100));
		sortvariablesPanel.add(plotMavenSortVariablesScrollPane,
				plotMavenSortVariablesScrollPaneCon);

		plotMavenGroupVariableTextField = new JTextField(
				"Insert Group Variable");
		plotMavenGroupVariableTextField.addFocusListener(DDViewLayer
				.createViewLayerInstance());
		plotMavenGroupVariableTextField.setBackground(Color.white);
		plotMavenGroupVariableTextField.setEditable(false);
		plotMavenGroupVariableTextField.setColumns(15);
		GridBagConstraints plotMavenGroupVariableTextFieldCon = new GridBagConstraints();
		plotMavenGroupVariableTextFieldCon.gridx = 1;
		plotMavenGroupVariableTextFieldCon.gridy = 0;
		plotMavenGroupVariableTextFieldCon.weightx = 1;
		// plotMavenGroupVariableTextFieldCon.anchor =
		// GridBagConstraints.LINE_END;
		groupVariablesPanel.add(plotMavenGroupVariableTextField,
				plotMavenGroupVariableTextFieldCon);

	}

	private void plotMavenXYplanelCreation(JPanel plotMavenxyTabPanel) {
		JPanel xPanel = new JPanel();
		xPanel.setBorder(titledBorder);
		xPanel.setLayout(new GridBagLayout());
		GridBagConstraints xPanelCon = new GridBagConstraints();
		xPanelCon.gridx = 0;
		xPanelCon.gridy = 0;
		xPanelCon.weightx = 1;
		xPanelCon.weighty = 1;
		xPanelCon.anchor = GridBagConstraints.LINE_START;
		xPanelCon.fill = GridBagConstraints.HORIZONTAL;

		availableToxButton = new JButton(moveToRightButtonIcon);
		availableToxButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		availableToxButton.setActionCommand(PMAVAILTOX);
		availableToxButton.setBorder(null);
		availableToxButton.setBackground(null);

		// availableToxButton.setPreferredSize(new Dimension(60, 20));
		GridBagConstraints availableToxButtonCon = new GridBagConstraints();
		availableToxButtonCon.gridx = 0;
		availableToxButtonCon.gridy = 0;
		availableToxButtonCon.weightx = 1;
		xPanel.add(availableToxButton, availableToxButtonCon);

		plotMavenxvariableTextField = new JTextField("x Variable");
		plotMavenxvariableTextField.setBackground(Color.white);
		plotMavenxvariableTextField.addFocusListener(DDViewLayer
				.createViewLayerInstance());
		plotMavenxvariableTextField.setEditable(false);
		plotMavenxvariableTextField.setColumns(15);
		GridBagConstraints plotMavenXVariableTextFieldCon = new GridBagConstraints();
		plotMavenXVariableTextFieldCon.gridx = 1;
		plotMavenXVariableTextFieldCon.gridy = 0;
		plotMavenXVariableTextFieldCon.weightx = 1;
		xPanel.add(plotMavenxvariableTextField, plotMavenXVariableTextFieldCon);
		plotMavenxyTabPanel.add(xPanel, xPanelCon);

		titledBorder = BorderFactory
				.createTitledBorder(null, "Y Variable",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION, componentLablesFont,
						Color.black);
		JPanel yPanel = new JPanel();
		yPanel.setLayout(new GridBagLayout());
		yPanel.setBorder(titledBorder);
		GridBagConstraints yPanelCon = new GridBagConstraints();
		yPanelCon.gridx = 0;
		yPanelCon.gridy = 1;
		yPanelCon.weightx = 1;
		yPanelCon.weighty = 1;
		yPanelCon.fill = GridBagConstraints.HORIZONTAL;
		yPanelCon.anchor = GridBagConstraints.LINE_START;

		availableToyButton = new JButton(moveToRightButtonIcon);
		availableToyButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		availableToyButton.setActionCommand(PMAVAILTOY);
		// availableToyButton.setPreferredSize(new Dimension(60, 20));
		GridBagConstraints availableToYButtonCon = new GridBagConstraints();
		availableToYButtonCon.gridx = 0;
		availableToYButtonCon.gridy = 0;
		availableToYButtonCon.weightx = 1;
		yPanel.add(availableToyButton, availableToYButtonCon);

		yvariableTextField = new JTextField("Y Variable");
		yvariableTextField.setBackground(Color.white);
		yvariableTextField.setEditable(false);
		yvariableTextField
				.addFocusListener(DDViewLayer.createViewLayerInstance());
		yvariableTextField.setColumns(15);
		GridBagConstraints yVariableTextFieldCon = new GridBagConstraints();
		yVariableTextFieldCon.gridx = 1;
		yVariableTextFieldCon.gridy = 0;
		yVariableTextFieldCon.weightx = 1;
		yPanel.add(yvariableTextField, yVariableTextFieldCon);

		plotMavenxyTabPanel.add(yPanel, yPanelCon);

	}

	private void createavailableVariablesInternalFrame() {
		plotMavenavailableVariablesInternalFrame = new JInternalFrame(
				"variables", false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						plotMavenavailableVariablesInternalFrame);
		plotMavenavailableVariablesInternalFrame.setLocation(0, 0);
		plotMavenavailableVariablesInternalFrame.setSize(
				setupDisplayInternalFrame.getWidth() / 5,
				setupDisplayInternalFrame.getHeight());
		plotMavenavailableVariablesInternalFrame.setVisible(true);
		plotMavenavailableVariablesInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		setupDisplayInternalFrameDesktoipPane
				.add(plotMavenavailableVariablesInternalFrame);
		plotMavenavailableVariablesInternalFrame.moveToFront();
		fillAvailableVariables();

	}

	private void fillAvailableVariables() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		plotMavenavailableVariablesListmodel = new DefaultListModel();
		// plotMavenavailableVariablesListmodel.add
		plotMavenavailableColumnsList = new JList(
				plotMavenavailableVariablesListmodel);
		plotMavenavailableColumnsVector = new Vector<String>();
		if ((appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
				&& (DesStatResDispComp.createDesStatResDispInst().carryThisToOtherComponents == true)) {
			TableColumnModel tm = DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
					.getColumnModel();
			for (int i = 0; i < DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
					.getColumnCount(); i++) {
				TableColumn tc = tm.getColumn(i);
				plotMavenavailableVariablesListmodel.add(i, (String) tc
						.getHeaderValue());
				plotMavenavailableColumnsVector.add(i, (String) tc
						.getHeaderValue());
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getAvailableColumnsVector().add(
								(String) tc.getHeaderValue());
			}

		} else {

			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				plotMavenavailableVariablesListmodel.add(i, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(i).getColumnName());
				plotMavenavailableColumnsVector.add(i, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(i).getColumnName());
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getPlotInfo()
						.getProcessingInputs()
						.getMappingDataObj()
						.getAvailableColumnsVector()
						.add(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getColumnPropertiesArrayList().get(i)
										.getColumnName());
			}
		}
		plotMavenavailableColumnsList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		plotMavenavailableColumnsList.setVisible(true);
		plotMavenavailableColumnsList.setVisibleRowCount(5);
		JScrollPane columnsAvailableListScrollPane = new JScrollPane(
				plotMavenavailableColumnsList);
		columnsAvailableListScrollPane
				.setPreferredSize(new Dimension(100, 200));
		plotMavenavailableVariablesInternalFrame.getContentPane().add(
				columnsAvailableListScrollPane);
	}
}
