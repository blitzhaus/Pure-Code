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
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class PolyExpoMappingDispGuiCreator implements EventCodes {

	JInternalFrame mappingInternalFrame;
	JList polyExpoAvailableColumnsList;
	JList polyExpoSortVariablesList;
	JList carryAlongList;
	JButton SortAndAvailableButton;
	JButton moveToCarryAlongButton;
	JButton setAsSubjectButton;
	JButton YAndAvailableButton;
	JButton xAndAvailableButton;
	DefaultListModel sortVariableListModel;
	DefaultListModel availableVariablesListmodel;
	DefaultListModel carryAlongVariableListmodel;
	JTextField xVariableTextField;
	JTextField yVariableTextField;
	JTextField subjectTextField;
	JTextField endTimeVariableTextField;
	JButton moveToEndTimeButton;
	Icon toLeft;
	Icon toRight;

	public static PolyExpoMappingDispGuiCreator POLYEXPO_MAP_DISP = null;

	public static PolyExpoMappingDispGuiCreator createMappingInstance()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (POLYEXPO_MAP_DISP == null) {
			POLYEXPO_MAP_DISP = new PolyExpoMappingDispGuiCreator();
		}
		return POLYEXPO_MAP_DISP;
	}

	public PolyExpoMappingDispGuiCreator() throws RowsExceededException,
			WriteException, BiffException, IOException {
		createMappinginternalFrame();
	}

	private void createMappinginternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {

		// TODO Auto-generated method stub
		mappingInternalFrame = new JInternalFrame("Mapping ", false, false,
				false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(mappingInternalFrame);
		mappingInternalFrame.setBackground(Color.white);

		DDViewLayer.createMTInstance().mainTabbedFrame.setSize(DDViewLayer
				.createMTInstance().mainTabbedFrameOriginalWidth, DDViewLayer
				.createMTInstance().mainTabbedFrameoriginalHeight);
		int width = 0;
		int height = 0;
		width = PolyExpoMainScreenCreator.createPolyExpoMainScreenInstance().polyExpoMainInternalFrame
				.getWidth()
				- PolyExpoSetupAvailableCompCreator
						.createPolyExpoSetupAvailCompInst().setupAvailableComponentsInternalFrame
						.getWidth();
		height = PolyExpoSetupAvailableCompCreator
				.createPolyExpoSetupAvailCompInst().setupAvailableComponentsInternalFrame
				.getHeight();

		mappingInternalFrame
				.setLocation(
						PolyExpoSetupAvailableCompCreator
								.createPolyExpoSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ PolyExpoSetupAvailableCompCreator
										.createPolyExpoSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
										PolyExpoSetupAvailableCompCreator
								.createPolyExpoSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		mappingInternalFrame.setSize(width, height);
		mappingInternalFrame.setVisible(true);
		JDesktopPane mappinginternalFrameDesktopPane = new JDesktopPane();
		mappingInternalFrame.setContentPane(mappinginternalFrameDesktopPane);
		mappingInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		createMappingPanel();

		PolyExpoTabbedPanesCreator.createPolyExpoTabbedPaneInstance().setupTabDesktopPane
				.add(mappingInternalFrame);
	}



	public void createMappingPanel() {

		toLeft = new ImageIcon("left.png");
		toRight = new ImageIcon("right.png");
		createAvailableColumnsIF();
		createTimeConButtonsIF();
		createTimeConIF();
		createSortCarryButtonsIF();
		createSortCarryIF();
		mappingInternalFrame.getContentPane().setBackground(
				polyExpoSortCarryIF.getBackground());
	}

	JInternalFrame polyExpoSortCarryIF;

	private void createSortCarryIF() {

		polyExpoSortCarryIF = new JInternalFrame(
				"carry along and sort list internal frame", false, false,
				false, false);
		polyExpoSortCarryIF.setLocation(polyExpoSortCarryButIF.getX()
				+ polyExpoSortCarryButIF.getWidth(), polyExpoSortCarryButIF
				.getY());
		polyExpoSortCarryIF.setSize(polyExpoSortCarryButIF.getWidth() * 2,
				polyExpoSortCarryButIF.getHeight());
		polyExpoSortCarryIF.setVisible(true);
		polyExpoSortCarryIF.setBorder(null);
		polyExpoSortCarryIF.setLayout(new GridBagLayout());
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(polyExpoSortCarryIF);

		sortVariableListModel = new DefaultListModel();
		polyExpoSortVariablesList = new JList(sortVariableListModel);
		polyExpoSortVariablesList.setVisibleRowCount(5);
		polyExpoSortVariablesList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());

		JScrollPane scrlPane = new JScrollPane(polyExpoSortVariablesList);
		scrlPane.setBorder(BorderFactory.createTitledBorder("Sort Columns"));
		scrlPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrlPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrlPane.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints sortLstCon = new GridBagConstraints();
		sortLstCon.gridx = 0;
		sortLstCon.gridy = 0;
		sortLstCon.weightx = 0.5;
		sortLstCon.anchor = GridBagConstraints.LINE_START;
		polyExpoSortCarryIF.getContentPane().add(scrlPane, sortLstCon);
		carryAlongVariableListmodel = new DefaultListModel();
		carryAlongList = new JList(carryAlongVariableListmodel);
		// carryAlongList.setVisibleRowCount(5);
		JScrollPane scrollPane = new JScrollPane(carryAlongList);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Carry Columns"));
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(150, 100));
		GridBagConstraints carryLstCon = new GridBagConstraints();
		carryLstCon.gridx = 0;
		carryLstCon.gridy = 1;
		carryLstCon.weightx = 0.5;
		carryLstCon.anchor = GridBagConstraints.LINE_START;
		polyExpoSortCarryIF.getContentPane().add(scrollPane, carryLstCon);
		mappingInternalFrame.getContentPane().add(polyExpoSortCarryIF);
	}

	JInternalFrame polyExpoSortCarryButIF;

	private void createSortCarryButtonsIF() {

		polyExpoSortCarryButIF = new JInternalFrame(
				"sort carry along internal frame", false, false, false, false);
		polyExpoSortCarryButIF.setLocation(polyExpoTimeConIF.getX()
				+ polyExpoTimeConIF.getWidth(), polyExpoTimeConIF.getY());
		polyExpoSortCarryButIF.setSize(polyExpoTimeConIF.getWidth(),
				polyExpoTimeConIF.getHeight());
		polyExpoSortCarryButIF.setVisible(true);
		polyExpoSortCarryButIF.setBorder(null);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(polyExpoSortCarryButIF);
		polyExpoSortCarryButIF.setLayout(new GridBagLayout());

		SortAndAvailableButton = new JButton("", toRight);
		SortAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		SortAndAvailableButton.setActionCommand(AVAILAANDSORTPOLYEXPO);
		SortAndAvailableButton.setBackground(null);
		SortAndAvailableButton.setBorder(null);
		GridBagConstraints sortCon = new GridBagConstraints();
		sortCon.gridx = 0;
		sortCon.gridy = 0;
		sortCon.weighty = 0.5;
		sortCon.anchor = GridBagConstraints.LINE_END;
		polyExpoSortCarryButIF.getContentPane().add(SortAndAvailableButton,
				sortCon);

		moveToCarryAlongButton = new JButton("", toRight);
		moveToCarryAlongButton.setBorder(null);
		moveToCarryAlongButton.setBackground(null);
		GridBagConstraints carryCon = new GridBagConstraints();
		carryCon.gridx = 0;
		carryCon.gridy = 1;
		carryCon.weighty = 0.5;
		carryCon.anchor = GridBagConstraints.LINE_END;
		polyExpoSortCarryButIF.getContentPane().add(moveToCarryAlongButton,
				carryCon);
		mappingInternalFrame.getContentPane().add(polyExpoSortCarryButIF);
	}

	JInternalFrame polyExpoTimeConIF;


	private void createTimeConIF() {

		String indepVar = "";
		String depVar = "";

		depVar = "Concentration";
		indepVar = "Time";

		polyExpoTimeConIF = new JInternalFrame("Time conc internal frame",
				false, false, false, false);
		polyExpoTimeConIF.setLocation(PolyExpoTimeConcButtonsIF.getX()
				+ PolyExpoTimeConcButtonsIF.getWidth(), PolyExpoTimeConcButtonsIF
				.getY());
		polyExpoTimeConIF.setSize(PolyExpoTimeConcButtonsIF.getWidth(),
				PolyExpoTimeConcButtonsIF.getHeight());
		polyExpoTimeConIF.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(polyExpoTimeConIF);
		polyExpoTimeConIF.setBorder(null);
		polyExpoTimeConIF.setLayout(new GridBagLayout());

		xVariableTextField = new JTextField();
		xVariableTextField
				.setBorder(BorderFactory.createTitledBorder(indepVar));
		xVariableTextField.setEditable(false);
		xVariableTextField
				.addFocusListener(DDViewLayer.createViewLayerInstance());
		xVariableTextField.setColumns(8);

		GridBagConstraints xVarCon = new GridBagConstraints();
		xVarCon.gridx = 0;
		xVarCon.gridy = 0;
		xVarCon.weighty = 0.5;
		xVarCon.anchor = GridBagConstraints.LINE_START;
		polyExpoTimeConIF.getContentPane().add(xVariableTextField, xVarCon);
		yVariableTextField = new JTextField();
		yVariableTextField.setBorder(BorderFactory.createTitledBorder(depVar));
		yVariableTextField.setColumns(8);
		yVariableTextField.setEditable(false);
		yVariableTextField
				.addFocusListener(DDViewLayer.createViewLayerInstance());
		GridBagConstraints yVarCon = new GridBagConstraints();
		yVarCon.gridx = 0;
		yVarCon.gridy = 1;
		yVarCon.weighty = 0.5;
		yVarCon.weightx = 0.5;
		yVarCon.anchor = GridBagConstraints.LINE_START;
		polyExpoTimeConIF.getContentPane().add(yVariableTextField, yVarCon);

		mappingInternalFrame.getContentPane().add(polyExpoTimeConIF);
	}

	JInternalFrame PolyExpoTimeConcButtonsIF;


	private void createTimeConButtonsIF() {
		PolyExpoTimeConcButtonsIF = new JInternalFrame("time and conc buttons",
				false, false, false, false);
		PolyExpoTimeConcButtonsIF.setLocation(polyExpoAvailColIF.getX()
				+ polyExpoAvailColIF.getWidth(), polyExpoAvailColIF.getY());
		PolyExpoTimeConcButtonsIF.setSize(polyExpoAvailColIF.getWidth() / 2,
				polyExpoAvailColIF.getHeight());
		PolyExpoTimeConcButtonsIF.setVisible(true);
		PolyExpoTimeConcButtonsIF.setLayout(new GridBagLayout());
		PolyExpoTimeConcButtonsIF.setBorder(null);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(PolyExpoTimeConcButtonsIF);
		xAndAvailableButton = new JButton("", toRight);
		xAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		xAndAvailableButton.setActionCommand(AVAILANDXPOLYEXPO);
		xAndAvailableButton.setBackground(null);
		xAndAvailableButton.setBorder(null);
		GridBagConstraints caTimeConstraints = new GridBagConstraints();
		caTimeConstraints.gridx = 0;
		caTimeConstraints.gridy = 0;
		caTimeConstraints.weighty = 0.5;
		caTimeConstraints.anchor = GridBagConstraints.LINE_END;
		PolyExpoTimeConcButtonsIF.getContentPane().add(xAndAvailableButton,
				caTimeConstraints);

		YAndAvailableButton = new JButton("", toRight);
		YAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		YAndAvailableButton.setActionCommand(AVAILANDYPOLYEXPO);
		YAndAvailableButton.setBackground(null);
		YAndAvailableButton.setBorder(null);
		GridBagConstraints caConcCon = new GridBagConstraints();
		caConcCon.gridx = 0;
		caConcCon.gridy = 1;
		caConcCon.weighty = 0.5;
		caConcCon.anchor = GridBagConstraints.LINE_END;
		PolyExpoTimeConcButtonsIF.getContentPane().add(YAndAvailableButton,
				caConcCon);

		mappingInternalFrame.getContentPane().add(PolyExpoTimeConcButtonsIF);

	}

	JInternalFrame polyExpoAvailColIF;

	private void createAvailableColumnsIF() {

		polyExpoAvailColIF = new JInternalFrame("Available columns", false,
				false, false, false);
		polyExpoAvailColIF.setLocation(0, 0);
		polyExpoAvailColIF.setSize((mappingInternalFrame.getWidth() / 7) * 2,
				mappingInternalFrame.getHeight());
		polyExpoAvailColIF.setVisible(true);
		polyExpoAvailColIF.setBorder(null);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(polyExpoAvailColIF);
		availableVariablesListmodel = new DefaultListModel();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		procInputInst.getMappingDataObj().getAvailableColumnsVector()
				.setSize(0);
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
			procInputInst
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
		polyExpoAvailableColumnsList = new JList(availableVariablesListmodel);
		polyExpoAvailableColumnsList.setBorder(new CompoundBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"),
						"Available Variables", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(0, 0, 0)),
				new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		polyExpoAvailableColumnsList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		JScrollPane scrlPane = new JScrollPane(polyExpoAvailableColumnsList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		polyExpoAvailColIF.getContentPane().add(scrlPane);
		mappingInternalFrame.getContentPane().add(polyExpoAvailColIF);
	}

}
