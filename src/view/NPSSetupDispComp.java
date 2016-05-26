package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class NPSSetupDispComp implements EventCodes {

	public static NPSSetupDispComp NPS_SETUP_DISP_COMP = null;

	public static NPSSetupDispComp createNpsSetDispInst() {
		if (NPS_SETUP_DISP_COMP == null) {
			NPS_SETUP_DISP_COMP = new NPSSetupDispComp("just object creation");
		}
		return NPS_SETUP_DISP_COMP;
	}

	public NPSSetupDispComp(String dummy) {

	}

	JInternalFrame MappingInternalFrame;
	JInternalFrame leftInternalFrameForNPS;
	DefaultListModel sortVariableListModel;
	JList availableColumnsList;
	JList sortVariablesList;
	JButton SortAndAvailableButton;
	JTextField timeVariableTextField;
	JInternalFrame leftarrowsInternalFrameForNPS;
	JButton timeAndAvailableButton;
	JInternalFrame middleInternalFrameForNPS;
	DefaultListModel availableVariablesListmodel;
	JInternalFrame rightArrowsinternalFrameForNPS;
	JButton moveToConcentrationButton;
	JInternalFrame rightInternalFrameForNPS;
	JTextField conbcentrationVariableTextField;
	JDesktopPane mappinginternalFrameDesktopPane;
	JButton xAndAvailableButton;

	JButton moveToCarryAlongButton;
	JButton setAsSubjectButton;
	JButton YAndAvailableButton;
	boolean isSortToAvailable;
	boolean isAvailableToSort;
	boolean isXToAvailable;
	boolean isAvailableToX;
	boolean isYToAvailable;
	boolean isAvailableToEndTime;
	boolean isEndTimeToAvailable;
	boolean isAvailableToVolume;
	boolean isVolumeToAvailable;
	boolean isAvailableToCarryAlong;
	boolean isCarryAlongToAvailable;
	boolean isAvailableToSubject;
	boolean isSubjectToAvailable;
	boolean isTimeToAvailable;
	boolean isAvailableToTime;

	boolean isAvailableToConcentration;
	boolean isConcentrationToAvailable;
	boolean isAvailableToY;
	String concentrationColumnName;
	String timeColumnName;
	Icon toLeft = new ImageIcon("left.png");
	Icon toRight = new ImageIcon("right.png");

	void createSetupDisplayComponentsInternalFrame() {
		createMappinginternalFrame();

	}

	private void createMappinginternalFrame() {

		mappingInternalFrameCreation();
		leftInternalFrameCreation();
		leftArrowsInternalFrame();
		middleInternalFrame();
		rightArrowsInternalFrame();
		rightInternalFrame();

		mappinginternalFrameDesktopPane.add(leftInternalFrameForNPS);
		leftInternalFrameForNPS.moveToFront();
		mappinginternalFrameDesktopPane.add(leftarrowsInternalFrameForNPS);
		leftarrowsInternalFrameForNPS.moveToFront();

		mappinginternalFrameDesktopPane.add(middleInternalFrameForNPS);
		middleInternalFrameForNPS.moveToFront();
		mappinginternalFrameDesktopPane.add(rightArrowsinternalFrameForNPS);
		rightArrowsinternalFrameForNPS.moveToFront();
		mappinginternalFrameDesktopPane.add(rightInternalFrameForNPS);
		rightInternalFrameForNPS.moveToFront();

	}

	private void rightInternalFrame() {

		rightInternalFrameForNPS = new JInternalFrame("right", false, false,
				false, false);
		rightInternalFrameForNPS.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(rightInternalFrameForNPS);
		rightInternalFrameForNPS.setLocation(rightArrowsinternalFrameForNPS
				.getX()
				+ rightArrowsinternalFrameForNPS.getWidth(),
				rightArrowsinternalFrameForNPS.getY());
		rightInternalFrameForNPS.setSize(middleInternalFrameForNPS.getWidth(),
				middleInternalFrameForNPS.getHeight());
		rightInternalFrameForNPS.setVisible(true);
		rightInternalFrameForNPS.setLayout(new GridBagLayout());
		rightInternalFrameForNPS.moveToFront();

		rightInternalFrameForNPS.setBorder(null);

		JLabel conbcentrationLableForNPS = new JLabel("Concentration");
		GridBagConstraints conbcentrationLableForNPSCon = new GridBagConstraints();
		conbcentrationLableForNPSCon.gridx = 0;
		conbcentrationLableForNPSCon.gridy = 0;

		conbcentrationLableForNPSCon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrameForNPS.getContentPane().add(
				conbcentrationLableForNPS, conbcentrationLableForNPSCon);

		conbcentrationVariableTextField = new JTextField();
		conbcentrationVariableTextField.setText("");
		conbcentrationVariableTextField.setBackground(Color.white);
		conbcentrationVariableTextField.setEditable(false);
		conbcentrationVariableTextField.addFocusListener(DDViewLayer
				.createViewLayerInstance());
		conbcentrationVariableTextField.setColumns(8);
		conbcentrationVariableTextField.setVisible(true);
		GridBagConstraints conbcentrationVariableTextFieldForNPSCon = new GridBagConstraints();
		conbcentrationVariableTextFieldForNPSCon.gridx = 0;
		conbcentrationVariableTextFieldForNPSCon.gridy = 1;
		conbcentrationVariableTextFieldForNPSCon.weighty = 0.5;
		conbcentrationVariableTextFieldForNPSCon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrameForNPS.getContentPane().add(
				conbcentrationVariableTextField,
				conbcentrationVariableTextFieldForNPSCon);

	}

	private void rightArrowsInternalFrame() {

		rightArrowsinternalFrameForNPS = new JInternalFrame("right arrows",
				false, false, false, false);
		rightArrowsinternalFrameForNPS.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(rightArrowsinternalFrameForNPS);
		rightArrowsinternalFrameForNPS.setLocation(middleInternalFrameForNPS
				.getX()
				+ middleInternalFrameForNPS.getWidth(),
				middleInternalFrameForNPS.getY());
		rightArrowsinternalFrameForNPS.setSize(leftarrowsInternalFrameForNPS
				.getWidth(), leftarrowsInternalFrameForNPS.getHeight());
		rightArrowsinternalFrameForNPS.setVisible(true);
		rightArrowsinternalFrameForNPS.setBorder(null);
		rightArrowsinternalFrameForNPS.setLayout(new GridBagLayout());

		moveToConcentrationButton = new JButton("", toRight);
		moveToConcentrationButton.setPreferredSize(new Dimension(60, 15));
		moveToConcentrationButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		moveToConcentrationButton.setActionCommand(NPSAVAILTOCONC);
		GridBagConstraints moveToConcentrationButtonForNPSCon = new GridBagConstraints();
		moveToConcentrationButtonForNPSCon.gridx = 0;
		moveToConcentrationButtonForNPSCon.gridy = 1;
		moveToConcentrationButtonForNPSCon.weighty = 0.5;
		moveToConcentrationButtonForNPSCon.anchor = GridBagConstraints.LINE_END;
		rightArrowsinternalFrameForNPS.getContentPane().add(
				moveToConcentrationButton, moveToConcentrationButtonForNPSCon);

	}

	private void middleInternalFrame() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		middleInternalFrameForNPS = new JInternalFrame("middle", false, false,
				false, false);
		middleInternalFrameForNPS.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(middleInternalFrameForNPS);
		middleInternalFrameForNPS.setLocation(leftarrowsInternalFrameForNPS
				.getX()
				+ leftarrowsInternalFrameForNPS.getWidth(),
				leftInternalFrameForNPS.getY());
		middleInternalFrameForNPS.setSize(leftInternalFrameForNPS.getWidth(),
				leftInternalFrameForNPS.getHeight());
		middleInternalFrameForNPS.setVisible(true);
		middleInternalFrameForNPS.setLayout(new GridBagLayout());
		middleInternalFrameForNPS.moveToFront();
		middleInternalFrameForNPS.setBorder(null);

		JLabel availableColumnsLableForNPS = new JLabel("Variables");
		GridBagConstraints availableColumnsLableForNPSCon = new GridBagConstraints();
		availableColumnsLableForNPSCon.gridx = 0;
		availableColumnsLableForNPSCon.gridy = 0;
		middleInternalFrameForNPS.getContentPane().add(
				availableColumnsLableForNPS, availableColumnsLableForNPSCon);

		availableVariablesListmodel = new DefaultListModel();
		availableColumnsList = new JList(availableVariablesListmodel);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().setSize(0);
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().size(); i++) {
			availableVariablesListmodel.add(i, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
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
					.getNpsInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
							i,
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

		availableColumnsList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		availableColumnsList.setVisible(true);
		availableColumnsList.setVisibleRowCount(5);
		JScrollPane columnsAvailableListScrollPaneForNPS = new JScrollPane(
				availableColumnsList);
		columnsAvailableListScrollPaneForNPS.setPreferredSize(new Dimension(
				100, 200));
		GridBagConstraints columnsAvailableListScrollPaneForNPSCon = new GridBagConstraints();
		columnsAvailableListScrollPaneForNPSCon.gridx = 0;
		columnsAvailableListScrollPaneForNPSCon.gridy = 1;
		columnsAvailableListScrollPaneForNPSCon.gridheight = 3;

		middleInternalFrameForNPS.getContentPane().add(
				columnsAvailableListScrollPaneForNPS,
				columnsAvailableListScrollPaneForNPSCon);

	}

	private void leftArrowsInternalFrame() {
		leftarrowsInternalFrameForNPS = new JInternalFrame("left arrows",
				false, false, false, false);
		leftarrowsInternalFrameForNPS.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(leftarrowsInternalFrameForNPS);
		leftarrowsInternalFrameForNPS.setLocation(leftInternalFrameForNPS
				.getX()
				+ leftInternalFrameForNPS.getWidth(), leftInternalFrameForNPS
				.getY());
		leftarrowsInternalFrameForNPS.setSize(leftInternalFrameForNPS
				.getWidth(), leftInternalFrameForNPS.getHeight());
		leftarrowsInternalFrameForNPS.setVisible(true);
		leftarrowsInternalFrameForNPS.setLayout(new GridBagLayout());
		leftarrowsInternalFrameForNPS.setBorder(null);

		SortAndAvailableButton = new JButton("", toLeft);
		SortAndAvailableButton.setToolTipText("Move to Sort List");
		SortAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		SortAndAvailableButton.setActionCommand(NPSSORTANDAVAIL);
		SortAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		SortAndAvailableButton.setVisible(true);
		GridBagConstraints moveToSortListButtonForNPSCon = new GridBagConstraints();
		moveToSortListButtonForNPSCon.gridx = 0;
		moveToSortListButtonForNPSCon.gridy = 1;

		moveToSortListButtonForNPSCon.weighty = 0.5;

		leftarrowsInternalFrameForNPS.getContentPane().add(
				SortAndAvailableButton, moveToSortListButtonForNPSCon);

		timeAndAvailableButton = new JButton("", toLeft);
		timeAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		timeAndAvailableButton.setActionCommand(NPSAVAILTOTIME);
		timeAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		GridBagConstraints setAsTimeVariableForNPSCon = new GridBagConstraints();
		setAsTimeVariableForNPSCon.gridx = 0;
		setAsTimeVariableForNPSCon.gridy = 2;
		setAsTimeVariableForNPSCon.weighty = 0.5;
		setAsTimeVariableForNPSCon.anchor = GridBagConstraints.LINE_START;
		leftarrowsInternalFrameForNPS.getContentPane().add(
				timeAndAvailableButton, setAsTimeVariableForNPSCon);

	}

	private void leftInternalFrameCreation() {
		leftInternalFrameForNPS = new JInternalFrame("left", false, false,
				false, false);
		leftInternalFrameForNPS.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(leftInternalFrameForNPS);
		leftInternalFrameForNPS.setLocation(0, 0);
		leftInternalFrameForNPS.setSize(MappingInternalFrame.getWidth() / 5,
				MappingInternalFrame.getHeight());
		leftInternalFrameForNPS.setVisible(true);
		leftInternalFrameForNPS.setLayout(new GridBagLayout());
		leftInternalFrameForNPS.moveToFront();
		leftInternalFrameForNPS.setBorder(null);

		JLabel sortListLableForNPS = new JLabel("Sort Variables");
		GridBagConstraints sortVariableLableForNPSCon = new GridBagConstraints();
		sortVariableLableForNPSCon.gridx = 0;
		sortVariableLableForNPSCon.gridy = 0;
		sortVariableLableForNPSCon.weightx = 0.5;

		sortVariableLableForNPSCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrameForNPS.getContentPane().add(sortListLableForNPS,
				sortVariableLableForNPSCon);

		sortVariableListModel = new DefaultListModel();
		sortVariablesList = new JList(sortVariableListModel);
		sortVariablesList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		sortVariablesList.setVisibleRowCount(3);
		sortVariablesList.setVisible(true);
		JScrollPane sortVarialbeListScrollPaneForNPS = new JScrollPane(
				sortVariablesList);
		sortVarialbeListScrollPaneForNPS.setPreferredSize(new Dimension(100,
				150));
		GridBagConstraints sortVariableLIstScrollPaneForNPSCon = new GridBagConstraints();

		sortVariableLIstScrollPaneForNPSCon.gridx = 0;
		sortVariableLIstScrollPaneForNPSCon.gridy = 1;
		sortVariableLIstScrollPaneForNPSCon.weightx = 0.5;

		sortVariableLIstScrollPaneForNPSCon.anchor = GridBagConstraints.LINE_START;

		leftInternalFrameForNPS.getContentPane().add(
				sortVarialbeListScrollPaneForNPS,
				sortVariableLIstScrollPaneForNPSCon);

		JLabel timeVariableLableForNPS = new JLabel("Time");
		GridBagConstraints timeVariableLableForNPSCon = new GridBagConstraints();
		timeVariableLableForNPSCon.gridx = 0;
		timeVariableLableForNPSCon.gridy = 2;

		timeVariableLableForNPSCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrameForNPS.getContentPane().add(timeVariableLableForNPS,
				timeVariableLableForNPSCon);

		timeVariableTextField = new JTextField();
		timeVariableTextField.setText("");
		timeVariableTextField.setBackground(Color.white);
		timeVariableTextField.setEditable(false);
		timeVariableTextField.addFocusListener(DDViewLayer
				.createViewLayerInstance());
		timeVariableTextField.setColumns(8);
		timeVariableTextField.setVisible(true);
		GridBagConstraints timeVariableTextFieldForNPSCon = new GridBagConstraints();
		timeVariableTextFieldForNPSCon.gridx = 0;
		timeVariableTextFieldForNPSCon.gridy = 3;

		timeVariableTextFieldForNPSCon.weighty = 0.5;
		timeVariableTextFieldForNPSCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrameForNPS.getContentPane().add(timeVariableTextField,
				timeVariableTextFieldForNPSCon);

	}

	private void mappingInternalFrameCreation() {
		MappingInternalFrame = new JInternalFrame("Mapping ", false, false,
				false, false);
		MappingInternalFrame.setBackground(Color.white);
		MappingInternalFrame
				.setLocation(
						NPSSetupAvailComp.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ NPSSetupAvailComp
										.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						NPSSetupAvailComp.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		MappingInternalFrame
				.setSize(
						NPSMainPage.createNPSMainPageInst().npsMainInternalFrame
								.getWidth()
								- NPSSetupAvailComp
										.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						NPSSetupAvailComp.createNPSSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getHeight());
		MappingInternalFrame.setVisible(true);
		mappinginternalFrameDesktopPane = new JDesktopPane();
		MappingInternalFrame.setContentPane(mappinginternalFrameDesktopPane);
		GridBagLayout gridBagLayout = new GridBagLayout();

		MappingInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		NPSTabs.createNPSTabsInst().setupTabDesktopPane
				.add(MappingInternalFrame);

	}
}
