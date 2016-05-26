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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class DesStatSetupDespComp implements EventCodes {

	public static DesStatSetupDespComp DES_STATSETUPDISP = null;

	public static DesStatSetupDespComp createDesStatSetupDispInst() {
		if (DES_STATSETUPDISP == null) {
			DES_STATSETUPDISP = new DesStatSetupDespComp();
		}
		return DES_STATSETUPDISP;
	}

	public DesStatSetupDespComp() {

	}

	void createSetupDisplayComponentsInternalFrame() {
		createMappinginternalFrame();

	}

	DefaultListModel carryAlongModel;
	JList carryAlongList;
	JInternalFrame MappingInternalFrame;
	JTextField weightVariableTextField;
	// JButton weightAndAvailableButton;
	JList availableColumnsList;
	JList sortVariablesList;
	JButton SortAndAvailableButton;
	DefaultListModel sortVariableListModel;
	JButton xAndAvailableButton;
	DefaultListModel availableVariablesListmodel;

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
	JScrollPane carryAlongScrollPane;
	boolean isAvailableToWeight;
	boolean isWeightToAvailable;
	Icon toLeft = new ImageIcon("left.png");
	Icon toRight = new ImageIcon("right.png");

	private void createMappinginternalFrame() {

		MappingInternalFrame = new JInternalFrame("Mapping ", false, false,
				false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(MappingInternalFrame);
		MappingInternalFrame.setBackground(Color.white);
		MappingInternalFrame
				.setLocation(
						DesStatSetupAvailComp.createDesStatAvailComp().setupAvailableComponentsInternalFrame
								.getX()
								+ DesStatSetupAvailComp
										.createDesStatAvailComp().setupAvailableComponentsInternalFrame
										.getWidth(),
						DesStatSetupAvailComp.createDesStatAvailComp().setupAvailableComponentsInternalFrame
								.getY());
		MappingInternalFrame
				.setSize(
						DescriptiveStatMainPage.createDescStatMainPageInst().desStatsMainIF
								.getWidth()
								- DesStatSetupAvailComp
										.createDesStatAvailComp().setupAvailableComponentsInternalFrame
										.getWidth(),
						DesStatSetupAvailComp.createDesStatAvailComp().setupAvailableComponentsInternalFrame
								.getHeight());
		MappingInternalFrame.setVisible(true);
		JDesktopPane mappinginternalFrameDesktopPane = new JDesktopPane();
		MappingInternalFrame.setContentPane(mappinginternalFrameDesktopPane);
		GridBagLayout gridBagLayout = new GridBagLayout();

		MappingInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		DSTabs.createDSTabInst().setupTabDesktopPane.add(MappingInternalFrame);

		JInternalFrame leftInternalFrame = new JInternalFrame("left", false,
				false, false, false);
		//leftInternalFrame.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(leftInternalFrame);
		leftInternalFrame.setLocation(0, 0);
		leftInternalFrame.setSize(MappingInternalFrame.getWidth() / 5,
				MappingInternalFrame.getHeight());
		leftInternalFrame.setVisible(true);
		leftInternalFrame.setLayout(gridBagLayout);
		leftInternalFrame.moveToFront();
		leftInternalFrame.setBorder(null);

		JLabel sortListLable = new JLabel("Sort Variables");
		GridBagConstraints sortVariableLableCon = new GridBagConstraints();
		sortVariableLableCon.gridx = 0;
		sortVariableLableCon.gridy = 0;
		sortVariableLableCon.weightx = 0.5;

		sortVariableLableCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(sortListLable,
				sortVariableLableCon);

		sortVariableListModel = new DefaultListModel();
		sortVariableListModel.size();
		sortVariablesList = new JList(sortVariableListModel);
		sortVariablesList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		sortVariablesList.setVisibleRowCount(3);
		sortVariablesList.setVisible(true);
		JScrollPane sortVarialbeListScrollPane = new JScrollPane(
				sortVariablesList);
		sortVarialbeListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sortVarialbeListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sortVarialbeListScrollPane.setPreferredSize(new Dimension(100, 120));
		GridBagConstraints sortVariableLIstScrollPaneCon = new GridBagConstraints();
		sortVariableLIstScrollPaneCon.gridx = 0;
		sortVariableLIstScrollPaneCon.gridy = 1;
		sortVariableLIstScrollPaneCon.weightx = 0.5;

		sortVariableLIstScrollPaneCon.anchor = GridBagConstraints.LINE_START;

		leftInternalFrame.getContentPane().add(sortVarialbeListScrollPane,
				sortVariableLIstScrollPaneCon);

		JInternalFrame leftarrowsInternalFrame = new JInternalFrame(
				"left arrows", false, false, false, false);
		//leftarrowsInternalFrame.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(leftarrowsInternalFrame);
		leftarrowsInternalFrame.setLocation(leftInternalFrame.getX()
				+ leftInternalFrame.getWidth(), leftInternalFrame.getY());
		leftarrowsInternalFrame.setSize(leftInternalFrame.getWidth(),
				leftInternalFrame.getHeight());
		leftarrowsInternalFrame.setVisible(true);
		leftarrowsInternalFrame.setLayout(gridBagLayout);
		leftarrowsInternalFrame.setBorder(null);

		SortAndAvailableButton = new JButton(toLeft);
		SortAndAvailableButton.setToolTipText("Move to Sort List");
		SortAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		SortAndAvailableButton.setActionCommand(DSSORTANDAVAIL);
		SortAndAvailableButton.setBorder(null);
		SortAndAvailableButton.setBackground(null);
		//SortAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		SortAndAvailableButton.setVisible(true);
		GridBagConstraints moveToSortListButtonCon = new GridBagConstraints();
		moveToSortListButtonCon.gridx = 0;
		moveToSortListButtonCon.gridy = 1;

		moveToSortListButtonCon.weighty = 0.5;

		leftarrowsInternalFrame.getContentPane().add(SortAndAvailableButton,
				moveToSortListButtonCon);
		JInternalFrame middleInternalFrame = new JInternalFrame("middle",
				false, false, false, false);
		//middleInternalFrame.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(middleInternalFrame);
		middleInternalFrame.setLocation(leftarrowsInternalFrame.getX()
				+ leftarrowsInternalFrame.getWidth(), leftInternalFrame.getY());
		middleInternalFrame.setSize(MappingInternalFrame.getWidth() / 4,
				MappingInternalFrame.getHeight());
		middleInternalFrame.setVisible(true);
		middleInternalFrame.setLayout(gridBagLayout);
		middleInternalFrame.moveToFront();
		middleInternalFrame.setBorder(null);

		JLabel availableColumnsLable = new JLabel("Variables");
		GridBagConstraints availableColumnsLableCon = new GridBagConstraints();
		availableColumnsLableCon.gridx = 0;
		availableColumnsLableCon.gridy = 0;
		middleInternalFrame.getContentPane().add(availableColumnsLable,
				availableColumnsLableCon);

		availableVariablesListmodel = new DefaultListModel();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		availableColumnsList = new JList(availableVariablesListmodel);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().setSize(0);
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getColumnPropertiesArrayList().size(); i++) {
			availableVariablesListmodel.add(i, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getColumnPropertiesArrayList().get(i).getColumnName());

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getDesStatsInfo()
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
									.getDesStatsInfo()
									.getColumnPropertiesArrayList().get(i)
									.getColumnName());

			

		}
		availableColumnsList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		availableColumnsList.setVisible(true);
		availableColumnsList.setVisibleRowCount(5);
		JScrollPane columnsAvailableListScrollPane = new JScrollPane(
				availableColumnsList);
		columnsAvailableListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		columnsAvailableListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		columnsAvailableListScrollPane
				.setPreferredSize(new Dimension(100, 200));
		GridBagConstraints columnsAvailableListScrollPaneCon = new GridBagConstraints();
		columnsAvailableListScrollPaneCon.gridx = 0;
		columnsAvailableListScrollPaneCon.gridy = 1;
		columnsAvailableListScrollPaneCon.gridheight = 3;

		middleInternalFrame.getContentPane().add(
				columnsAvailableListScrollPane,
				columnsAvailableListScrollPaneCon);

		JInternalFrame rightArrowsinternalFrame = new JInternalFrame(
				"right arrows", false, false, false, false);
		//rightArrowsinternalFrame.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(rightArrowsinternalFrame);
		rightArrowsinternalFrame.setLocation(middleInternalFrame.getX()
				+ middleInternalFrame.getWidth(), middleInternalFrame.getY());
		rightArrowsinternalFrame.setSize(MappingInternalFrame.getWidth() / 4,
				MappingInternalFrame.getHeight());
		rightArrowsinternalFrame.setVisible(true);
		rightArrowsinternalFrame.setBorder(null);
		rightArrowsinternalFrame.setLayout(gridBagLayout);

		moveToCarryAlongButton = new JButton(toRight);
		moveToCarryAlongButton.setBackground(null);
		moveToCarryAlongButton.setBorder(null);
		
		//moveToCarryAlongButton.setPreferredSize(new Dimension(60, 15));
		moveToCarryAlongButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		moveToCarryAlongButton.setActionCommand(DSAVAILTOCARRY);
		GridBagConstraints moveTocarryAlongButtonCon = new GridBagConstraints();
		moveTocarryAlongButtonCon.gridx = 0;
		moveTocarryAlongButtonCon.gridy = 0;
		moveTocarryAlongButtonCon.weighty = 0.5;
		moveTocarryAlongButtonCon.anchor = GridBagConstraints.LINE_END;
		rightArrowsinternalFrame.getContentPane().add(moveToCarryAlongButton,
				moveTocarryAlongButtonCon);

		JInternalFrame rightInternalFrame = new JInternalFrame("right", false,
				false, false, false);
		//rightInternalFrame.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(rightInternalFrame);
		rightInternalFrame.setLocation(rightArrowsinternalFrame.getX()
				+ rightArrowsinternalFrame.getWidth(), rightArrowsinternalFrame
				.getY());
		rightInternalFrame.setSize(MappingInternalFrame.getWidth() / 4,
				MappingInternalFrame.getHeight());
		rightInternalFrame.setVisible(true);
		rightInternalFrame.setLayout(gridBagLayout);
		rightInternalFrame.moveToFront();

		rightInternalFrame.setBorder(null);

		JLabel carryAlongLaable = new JLabel("Summary");
		GridBagConstraints carryAlongLableCon = new GridBagConstraints();
		carryAlongLableCon.gridx = 0;
		carryAlongLableCon.gridy = 0;
		carryAlongLableCon.weightx = 0.5;
		carryAlongLableCon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrame.getContentPane().add(carryAlongLaable,
				carryAlongLableCon);

		carryAlongModel = new DefaultListModel();
		carryAlongList = new JList(carryAlongModel);

		carryAlongList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		carryAlongList.setVisibleRowCount(3);
		carryAlongList.setVisible(true);

		carryAlongScrollPane = new JScrollPane(carryAlongList);
		carryAlongScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		carryAlongScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		carryAlongScrollPane.setPreferredSize(new Dimension(70, 50));
		GridBagConstraints carryAlongScrollPaneCon = new GridBagConstraints();
		carryAlongScrollPaneCon.gridx = 0;
		carryAlongScrollPaneCon.gridy = 1;
		carryAlongScrollPaneCon.weightx = 0.5;

		carryAlongScrollPaneCon.anchor = GridBagConstraints.LINE_START;

		rightInternalFrame.getContentPane().add(carryAlongScrollPane,
				carryAlongScrollPaneCon);

		mappinginternalFrameDesktopPane.add(leftInternalFrame);
		leftInternalFrame.moveToFront();
		mappinginternalFrameDesktopPane.add(leftarrowsInternalFrame);
		leftarrowsInternalFrame.moveToFront();

		mappinginternalFrameDesktopPane.add(middleInternalFrame);
		middleInternalFrame.moveToFront();
		mappinginternalFrameDesktopPane.add(rightArrowsinternalFrame);
		rightArrowsinternalFrame.moveToFront();
		mappinginternalFrameDesktopPane.add(rightInternalFrame);
		rightInternalFrame.moveToFront();

	}
}
