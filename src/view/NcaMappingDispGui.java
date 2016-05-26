package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class NcaMappingDispGui implements EventCodes {

	JInternalFrame MappingInternalFrame;
	DefaultListModel sortVariableListModel;
	JList sortVariablesList;
	JTextField xVariableTextField;
	JTextField yVariableTextField;
	JLabel endTimeVariableLable;
	JTextField endTimeVariableTextField;
	JButton SortAndAvailableButton;
	Icon toLeft = new ImageIcon("left.png");
	Icon toRight = new ImageIcon("right.png");
	JButton xAndAvailableButton;
	JButton moveToEndTimeButton;
	JButton YAndAvailableButton;
	DefaultListModel availableVariablesListmodel;
	JList availableColumnsList;
	JButton moveToCarryAlongButton;
	JButton setAsSubjectButton;
	JButton moveToVolumeButton;
	DefaultListModel carryAlongModel;
	JList carryAlongList;
	JScrollPane carryAlongScrollPane;
	JLabel subjectLable;
	JLabel volumeLable;
	JTextField subjectTextField;
	JTextField volumeVariableTextField;
	int previousSize;
	String[] previousSortVariables;
	String previousYColumnName;
	String previousEndTime;
	String previousVolumeColumnName;
	String previousSubjectColumnName;
	String previousXVariable;

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

	boolean isAvailableToY;

	public NcaMappingDispGui(String dummy) {

	}

	public static NcaMappingDispGui NCA_MAP_DISP = null;

	public static NcaMappingDispGui createMappingInstance()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NCA_MAP_DISP == null) {
			NCA_MAP_DISP = new NcaMappingDispGui("just object creation");
		}
		return NCA_MAP_DISP;
	}

	public void NcaMappingDispGuiCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {
		createMappinginternalFrame();
	}

	private void createMappinginternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {

		// TODO Auto-generated method stub
		MappingInternalFrame = new JInternalFrame("Mapping ", false, false,
				false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(MappingInternalFrame);
	//	MappingInternalFrame.setBackground(Color.white);
		MappingInternalFrame
				.setLocation(
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ NcaSetupAvailableComp
										.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		MappingInternalFrame
				.setSize(
						NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
								.getWidth()
								- NcaSetupAvailableComp
										.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getHeight());
		MappingInternalFrame.setVisible(true);
		JDesktopPane mappinginternalFrameDesktopPane = new JDesktopPane();
		MappingInternalFrame.setContentPane(mappinginternalFrameDesktopPane);
		GridBagLayout gridBagLayout = new GridBagLayout();

		MappingInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		NcaTabbedPanes.createNcaTabbedPaneInstance().setupTabDesktopPane
				.add(MappingInternalFrame);

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

/*		JLabel sortListLable = new JLabel("Sort Variables");
		GridBagConstraints sortVariableLableCon = new GridBagConstraints();
		sortVariableLableCon.gridx = 0;
		sortVariableLableCon.gridy = 0;
		sortVariableLableCon.weightx = 0.5;

		sortVariableLableCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(sortListLable,
				sortVariableLableCon);*/

		sortVariableListModel = new DefaultListModel();
		sortVariablesList = new JList(sortVariableListModel);
		// sortVariablesList.addMouseListener(ViewLayer.createViewLayerInstance());
		sortVariablesList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		sortVariablesList.setVisibleRowCount(3);
		sortVariablesList.setVisible(true);
		JScrollPane sortVarialbeListScrollPane = new JScrollPane(
				sortVariablesList);
		sortVarialbeListScrollPane.setBorder(BorderFactory.createTitledBorder("Sort"));
		sortVarialbeListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sortVarialbeListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sortVarialbeListScrollPane.setPreferredSize(new Dimension(100, 75));
		GridBagConstraints sortVariableLIstScrollPaneCon = new GridBagConstraints();
		sortVariableLIstScrollPaneCon.gridx = 0;
		sortVariableLIstScrollPaneCon.gridy = 1;
		sortVariableLIstScrollPaneCon.weightx = 0.5;
		// sortVariableLIstScrollPaneCon.weighty = 0.5;
		sortVariableLIstScrollPaneCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(sortVarialbeListScrollPane,
				sortVariableLIstScrollPaneCon);

/*		JLabel xVariableLable = new JLabel("Time");
		GridBagConstraints xVariableLableCon = new GridBagConstraints();
		xVariableLableCon.gridx = 0;
		xVariableLableCon.gridy = 2;
		xVariableLableCon.weightx = 0.5;
		// xVariableLableCon.weighty = 0.5;
		xVariableLableCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(xVariableLable,
				xVariableLableCon);*/

		xVariableTextField = new JTextField();
		xVariableTextField.setText("");
		//xVariableTextField.setBackground(Color.white);
		xVariableTextField.setEditable(false);
		xVariableTextField
				.addFocusListener(DDViewLayer.createViewLayerInstance());
		xVariableTextField.setColumns(8);
		xVariableTextField.setVisible(true);
		xVariableTextField.setBorder(BorderFactory.createTitledBorder("Time"));
		GridBagConstraints xVariableTextFieldCon = new GridBagConstraints();
		xVariableTextFieldCon.gridx = 0;
		xVariableTextFieldCon.gridy = 3;
		// xVariableTextFieldCon.weightx = 0.1;
		xVariableTextFieldCon.weighty = 0.5;
		xVariableTextFieldCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(xVariableTextField,
				xVariableTextFieldCon);

		/*// for end time mapping
		endTimeVariableLable = new JLabel("End Time");
		endTimeVariableLable.setVisible(false);
		GridBagConstraints endTimeVariableLableCon = new GridBagConstraints();
		endTimeVariableLableCon.gridx = 0;
		endTimeVariableLableCon.gridy = 4;
		endTimeVariableLableCon.weightx = 0.5;
		// xVariableLableCon.weighty = 0.5;
		endTimeVariableLableCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(endTimeVariableLable,
				endTimeVariableLableCon);
*/
		endTimeVariableTextField = new JTextField();
		endTimeVariableTextField.setText("");
		//endTimeVariableTextField.setBackground(Color.white);
		endTimeVariableTextField.setEditable(false);
		endTimeVariableTextField.addFocusListener(DDViewLayer
				.createViewLayerInstance());
		endTimeVariableTextField.setBorder(BorderFactory.createTitledBorder("End Time"));
		endTimeVariableTextField.setColumns(8);
		endTimeVariableTextField.setVisible(false);
		GridBagConstraints endTimeVariableTextFieldCon = new GridBagConstraints();
		endTimeVariableTextFieldCon.gridx = 0;
		endTimeVariableTextFieldCon.gridy = 5;
		// xVariableTextFieldCon.weightx = 0.1;
		endTimeVariableTextFieldCon.weighty = 0.5;
		endTimeVariableTextFieldCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(endTimeVariableTextField,
				endTimeVariableTextFieldCon);

		/*JLabel yVariableLable = new JLabel("Concentration");
		GridBagConstraints yVairableLableCon = new GridBagConstraints();
		yVairableLableCon.gridx = 0;
		yVairableLableCon.gridy = 6;
		yVairableLableCon.weightx = 0.5;

		yVairableLableCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(yVariableLable,
				yVairableLableCon);*/

		yVariableTextField = new JTextField();
		yVariableTextField.setText("");
		//yVariableTextField.setBackground(Color.white);
		yVariableTextField.setEditable(false);
		// yVariableTextField.addMouseListener(ViewLayer.createViewLayerInstance());
		yVariableTextField
				.addFocusListener(DDViewLayer.createViewLayerInstance());
		yVariableTextField.setColumns(8);
		yVariableTextField.setBorder(BorderFactory.createTitledBorder("Conc"));
		GridBagConstraints yVariableTextFieldCon = new GridBagConstraints();
		yVariableTextFieldCon.gridx = 0;
		yVariableTextFieldCon.gridy = 7;
		yVariableTextFieldCon.weightx = 0.5;
		yVariableTextFieldCon.weighty = 0.5;
		yVariableTextFieldCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(yVariableTextField,
				yVariableTextFieldCon);

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

		SortAndAvailableButton = new JButton("", toLeft);
		SortAndAvailableButton.setToolTipText("Move to Sort List");
		SortAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		SortAndAvailableButton.setActionCommand(AVAILAANDSORT);
		//SortAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		SortAndAvailableButton.setVisible(true);
		SortAndAvailableButton.setBorder(null);
		SortAndAvailableButton.setBackground(null);
		GridBagConstraints moveToSortListButtonCon = new GridBagConstraints();
		moveToSortListButtonCon.gridx = 0;
		moveToSortListButtonCon.gridy = 1;
		// moveToSortListButtonCon.weightx = 0.5;
		moveToSortListButtonCon.weighty = 0.5;
		// moveToSortListButtonCon.anchor = GridBagConstraints.LINE_END;
		leftarrowsInternalFrame.getContentPane().add(SortAndAvailableButton,
				moveToSortListButtonCon);

		xAndAvailableButton = new JButton("", toLeft);
		xAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		xAndAvailableButton.setActionCommand(AVAILANDX);
		xAndAvailableButton.setBackground(null);
		xAndAvailableButton.setBorder(null);
		//xAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		GridBagConstraints setAsXVariableCon = new GridBagConstraints();
		setAsXVariableCon.gridx = 0;
		setAsXVariableCon.gridy = 2;
		setAsXVariableCon.weighty = 0.5;
		setAsXVariableCon.anchor = GridBagConstraints.LINE_START;
		leftarrowsInternalFrame.getContentPane().add(xAndAvailableButton,
				setAsXVariableCon);

		moveToEndTimeButton = new JButton("", toLeft);
		moveToEndTimeButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		moveToEndTimeButton.setActionCommand(AVAILANDENDTIME);
		moveToEndTimeButton.setBackground(null);
		moveToEndTimeButton.setBorder(null);
		//moveToEndTimeButton.setPreferredSize(new Dimension(60, 15));
		moveToEndTimeButton.setVisible(false);
		GridBagConstraints moveToEndTimeButtonCon = new GridBagConstraints();
		moveToEndTimeButtonCon.gridx = 0;
		moveToEndTimeButtonCon.gridy = 3;
		moveToEndTimeButtonCon.weighty = 0.5;
		moveToEndTimeButtonCon.anchor = GridBagConstraints.LINE_START;
		leftarrowsInternalFrame.getContentPane().add(moveToEndTimeButton,
				moveToEndTimeButtonCon);

		YAndAvailableButton = new JButton("", toLeft);
		YAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		YAndAvailableButton.setActionCommand(AVAILANDY);
		YAndAvailableButton.setBackground(null);
		YAndAvailableButton.setBorder(null);
		//YAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		GridBagConstraints setAsYVariableButtonCon = new GridBagConstraints();
		setAsYVariableButtonCon.gridy = 4;
		setAsYVariableButtonCon.gridx = 0;
		setAsYVariableButtonCon.weighty = 0.5;
		setAsYVariableButtonCon.anchor = GridBagConstraints.LINE_START;
		leftarrowsInternalFrame.getContentPane().add(YAndAvailableButton,
				setAsYVariableButtonCon);

		JInternalFrame middleInternalFrame = new JInternalFrame("middle",
				false, false, false, false);
		//middleInternalFrame.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(middleInternalFrame);
		middleInternalFrame.setLocation(leftarrowsInternalFrame.getX()
				+ leftarrowsInternalFrame.getWidth(), leftInternalFrame.getY());
		middleInternalFrame.setSize(leftInternalFrame.getWidth(),
				leftInternalFrame.getHeight());
		middleInternalFrame.setVisible(true);
		middleInternalFrame.setLayout(gridBagLayout);
		middleInternalFrame.moveToFront();
		middleInternalFrame.setBorder(null);

		/*JLabel availableColumnsLable = new JLabel("Variables");
		GridBagConstraints availableColumnsLableCon = new GridBagConstraints();
		availableColumnsLableCon.gridx = 0;
		availableColumnsLableCon.gridy = 0;
		middleInternalFrame.getContentPane().add(availableColumnsLable,
				availableColumnsLableCon);*/

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		availableVariablesListmodel = new DefaultListModel();
		availableColumnsList = new JList(availableVariablesListmodel);

		if ((appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
				&& (DesStatResDispComp.createDesStatResDispInst().carryThisToOtherComponents == true)) {
			TableColumnModel tm = DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
					.getColumnModel();
			for (int i = 0; i < DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
					.getColumnCount(); i++) {
				TableColumn tc = tm.getColumn(i);
				availableVariablesListmodel
						.add(i, (String) tc.getHeaderValue());
				appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getAvailableColumnsVector().add(i,
								(String) tc.getHeaderValue());

			}

		} else {
			for (int i = 0; i < appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				availableVariablesListmodel
						.add(
								i,
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getWorkBooksArrayList()
														.get(
																appInfo
																		.getProjectInfoAL()
																		.get(
																				appInfo
																						.getSelectedProjectIndex())
																		.getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getColumnPropertiesArrayList().get(i)
										.getColumnName());
				appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getNcaInfo()
						.getProcessingInputs()
						.getMappingDataObj()
						.getAvailableColumnsVector()
						.add(
								i,
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getWorkBooksArrayList()
														.get(
																appInfo
																		.getProjectInfoAL()
																		.get(
																				appInfo
																						.getSelectedProjectIndex())
																		.getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getColumnPropertiesArrayList().get(i)
										.getColumnName());

			}

		}

		// availableColumnsList.addMouseListener(ViewLayer.createViewLayerInstance());
		availableColumnsList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		availableColumnsList.setVisible(true);
		availableColumnsList.setVisibleRowCount(5);
		JScrollPane columnsAvailableListScrollPane = new JScrollPane(
				availableColumnsList);
		columnsAvailableListScrollPane
				.setPreferredSize(new Dimension(100, 200));
		columnsAvailableListScrollPane.setBorder(BorderFactory.createTitledBorder("Available"));
		columnsAvailableListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		columnsAvailableListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
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
		rightArrowsinternalFrame.setSize(leftarrowsInternalFrame.getWidth(),
				leftarrowsInternalFrame.getHeight());
		rightArrowsinternalFrame.setVisible(true);
		rightArrowsinternalFrame.setBorder(null);
		rightArrowsinternalFrame.setLayout(gridBagLayout);

		moveToCarryAlongButton = new JButton("", toRight);
		//moveToCarryAlongButton.setPreferredSize(new Dimension(60, 15));
		moveToCarryAlongButton.addActionListener(DDViewLayer.createViewLayerInstance());
		moveToCarryAlongButton.setBackground(null);
		moveToCarryAlongButton.setBorder(null);
		moveToCarryAlongButton.setActionCommand(AVAILANDCARRYNCA);
		GridBagConstraints moveTocarryAlongButtonCon = new GridBagConstraints();
		moveTocarryAlongButtonCon.gridx = 0;
		moveTocarryAlongButtonCon.gridy = 1;
		moveTocarryAlongButtonCon.weighty = 0.5;
		moveTocarryAlongButtonCon.anchor = GridBagConstraints.LINE_END;
		rightArrowsinternalFrame.getContentPane().add(moveToCarryAlongButton,
				moveTocarryAlongButtonCon);

		setAsSubjectButton = new JButton("", toRight);
		//setAsSubjectButton.setPreferredSize(new Dimension(60, 15));
		setAsSubjectButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		setAsSubjectButton.setActionCommand(AVAILANDSUBJECT);
		setAsSubjectButton.setVisible(false);
		setAsSubjectButton.setBorder(null);
		setAsSubjectButton.setBackground(null);
		GridBagConstraints setAsSubjectbuttonCon = new GridBagConstraints();
		setAsSubjectbuttonCon.gridx = 0;
		setAsSubjectbuttonCon.gridy = 2;
		setAsSubjectbuttonCon.weighty = 0.5;
		// setAsSubjectbuttonCon.anchor = GridBagConstraints.LINE_START;
		rightArrowsinternalFrame.getContentPane().add(setAsSubjectButton,
				setAsSubjectbuttonCon);

		moveToVolumeButton = new JButton("", toRight);
		//moveToVolumeButton.setPreferredSize(new Dimension(60, 15));
		moveToVolumeButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		moveToVolumeButton.setBorder(null);
		moveToVolumeButton.setBackground(null);
		moveToVolumeButton.setActionCommand(AVAILANDVOLUME);
		moveToVolumeButton.setVisible(false);
		GridBagConstraints moveToVolumeButtonCon = new GridBagConstraints();
		moveToVolumeButtonCon.gridx = 0;
		moveToVolumeButtonCon.gridy = 3;
		moveToVolumeButtonCon.weighty = 0.5;
		// setAsSubjectbuttonCon.anchor = GridBagConstraints.LINE_START;
		rightArrowsinternalFrame.getContentPane().add(moveToVolumeButton,
				moveToVolumeButtonCon);

		JInternalFrame rightInternalFrame = new JInternalFrame("right", false,
				false, false, false);
		//rightInternalFrame.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(rightInternalFrame);
		rightInternalFrame.setLocation(rightArrowsinternalFrame.getX()
				+ rightArrowsinternalFrame.getWidth(), rightArrowsinternalFrame
				.getY());
		rightInternalFrame.setSize(middleInternalFrame.getWidth(),
				middleInternalFrame.getHeight());
		rightInternalFrame.setVisible(true);
		rightInternalFrame.setLayout(gridBagLayout);
		rightInternalFrame.moveToFront();

		rightInternalFrame.setBorder(null);

		/*JLabel carryAlongLaable = new JLabel("Carry Along");
		GridBagConstraints carryAlongLableCon = new GridBagConstraints();
		carryAlongLableCon.gridx = 0;
		carryAlongLableCon.gridy = 0;
		carryAlongLableCon.weightx = 0.5;
		carryAlongLableCon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrame.getContentPane().add(carryAlongLaable,
				carryAlongLableCon);
*/
		carryAlongModel = new DefaultListModel();
		carryAlongList = new JList(carryAlongModel);

		carryAlongScrollPane = new JScrollPane(carryAlongList);
		carryAlongScrollPane.setBorder(BorderFactory.createTitledBorder("Carry Along"));
		carryAlongScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		carryAlongScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		carryAlongList.addMouseListener(DDViewLayer.createViewLayerInstance());
		carryAlongScrollPane.setPreferredSize(new Dimension(100, 50));
		GridBagConstraints carryAlongScrollPaneCOn = new GridBagConstraints();
		carryAlongScrollPaneCOn.gridx = 0;
		carryAlongScrollPaneCOn.gridy = 1;
		carryAlongScrollPaneCOn.weightx = 0.5;
		carryAlongScrollPaneCOn.weighty = 0.5;
		carryAlongScrollPaneCOn.anchor = GridBagConstraints.LINE_START;
		rightInternalFrame.getContentPane().add(carryAlongScrollPane,
				carryAlongScrollPaneCOn);

/*		subjectLable = new JLabel("Subject");
		subjectLable.setVisible(false);
		GridBagConstraints subjectLableCon = new GridBagConstraints();
		subjectLableCon.gridx = 0;
		;
		subjectLableCon.gridy = 2;

		// subjectLableCon.weighty = 0.5;
		subjectLableCon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrame.getContentPane().add(subjectLable, subjectLableCon);
*/
		subjectTextField = new JTextField();
		subjectTextField.setColumns(8);
		subjectTextField.setVisible(false);
		subjectTextField.setEditable(false);
		subjectTextField.setBorder(BorderFactory.createTitledBorder("Subject"));
		subjectTextField.addFocusListener(DDViewLayer.createViewLayerInstance());
		GridBagConstraints subjectTextFieldCon = new GridBagConstraints();
		subjectTextFieldCon.gridx = 0;
		subjectTextFieldCon.gridy = 3;
		subjectTextFieldCon.weighty = 0.5;
		subjectTextFieldCon.weightx = 0.5;
		subjectTextFieldCon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrame.getContentPane().add(subjectTextField,
				subjectTextFieldCon);

		/*volumeLable = new JLabel("Volume");
		volumeLable.setVisible(false);
		GridBagConstraints volumeLableCon = new GridBagConstraints();
		volumeLableCon.gridx = 0;
		;
		volumeLableCon.gridy = 4;

		// subjectLableCon.weighty = 0.5;
		volumeLableCon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrame.getContentPane().add(volumeLable, volumeLableCon);*/

		volumeVariableTextField = new JTextField();
		volumeVariableTextField.setBorder(BorderFactory.createTitledBorder("Volume"));
		volumeVariableTextField.setColumns(8);
		volumeVariableTextField.setVisible(false);
		volumeVariableTextField.setBackground(null);
		volumeVariableTextField.addFocusListener(DDViewLayer
				.createViewLayerInstance());
		GridBagConstraints volumeVariableTextFieldCon = new GridBagConstraints();
		volumeVariableTextFieldCon.gridx = 0;
		volumeVariableTextFieldCon.gridy = 5;
		volumeVariableTextFieldCon.weighty = 0.5;
		volumeVariableTextFieldCon.weightx = 0.5;
		volumeVariableTextFieldCon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrame.getContentPane().add(volumeVariableTextField,
				volumeVariableTextFieldCon);

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

	String mappingChangeDetection() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int a = 1;
		String s = new String("");
		
		if (previousSize != appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size()) {
			appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.setIfMappingScreenIsChanged(true);
			s = s + "Sort variables changed";

		} else {

			for (int i = 0; i < previousSize; i++) {
				for (int j = 0; j < appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size(); j++) {
					String String1 = appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL()
							.get(
									appInfo
											.getProjectInfoAL()
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.get(i);
					String String2 = previousSortVariables[i];
					if (String1.equals(String2) == true)
						a = a * 0;
					else
						a = a * 1;

				}

				if (a == 1) {
					appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL()
							.get(
									appInfo
											.getProjectInfoAL()
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().setIfMappingScreenIsChanged(
									true);
					s = s + "sort variables changed";
					break;

				} else {
					a = 1;
					appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL()
							.get(
									appInfo
											.getProjectInfoAL()
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getMappingDataObj().setIfMappingScreenIsChanged(
									false);
				}

			}

		}

		// setting the new sort variables to the previousSortVariables array
		for (int i = 0; i < appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			String String2 = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i);
			previousSortVariables[i] = String2;
		}

		previousSize = appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();
		s = checkIfXColumnChanged(s);

		previousXVariable = appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName();

		// checking if y column is changed
		s = checkIfYColumnChanged(s);

		previousYColumnName = appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName();

		// checking if subject column is changed
		if (appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getisSparseSelected() == true) {
			s = checkIfSubjectColumnChanged(s);
		}

		if (appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getModelType() == 1) {
			// checking if the end time is changed
			s = checkIfEndTimeChanged(s);

			// checking if volume column is changed
			s = checkIfVolumeColumnChanged(s);

			previousEndTime = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getEndTimeColumnName();

			previousVolumeColumnName = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getVolumeColumnName();
		}
		
		return s;
	}

	private String checkIfVolumeColumnChanged(String s) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (previousVolumeColumnName
				.equals(appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getVolumeColumnName())) {

		} else {
			appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.setIfMappingScreenIsChanged(true);
			s = s + "	, volume column changed";

		}

		return s;

	}

	private String checkIfEndTimeChanged(String s) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (previousEndTime
				.equals(appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getEndTimeColumnName())) {

		} else {
			appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.setIfMappingScreenIsChanged(true);
			s = s + "	, end time column changed";
			

		}
		return s;
	}

	private String checkIfSubjectColumnChanged(String s) {
System.out.println("Previous subject column name is "+previousSubjectColumnName);
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (previousSubjectColumnName
				.equals(appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSubjectColumnName())) {

		} else {
			appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.setIfMappingScreenIsChanged(true);
			s = s + "	, Subject column changed";
			

		}
		previousSubjectColumnName = appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSubjectColumnName();

		return s;

	}

	private String checkIfYColumnChanged(String s) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (previousYColumnName
				.equals(appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getYcolumnName())) {

		} else {
			appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.setIfMappingScreenIsChanged(true);
			s = s + "	, Concentration column changed";
			
		}
		return s;
	}

	private String checkIfXColumnChanged(String s) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// checking if the x column is changed
		if (previousXVariable
				.equals(appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(appInfo.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getxColumnName())) {

		} else {
			appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.setIfMappingScreenIsChanged(true);
			s = s + "	, Time column changed";
		}
		return s;
	}

}
