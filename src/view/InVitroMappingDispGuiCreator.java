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
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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

public class InVitroMappingDispGuiCreator implements EventCodes {

	JInternalFrame mappingInternalFrame;
	JList inVitroAvailableColumnsList;
	JList inVitroSortVariablesList;
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

	public static InVitroMappingDispGuiCreator INVITRO_MAP_DISP = null;

	public static InVitroMappingDispGuiCreator createMappingInstance()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (INVITRO_MAP_DISP == null) {
			INVITRO_MAP_DISP = new InVitroMappingDispGuiCreator();
		}
		return INVITRO_MAP_DISP;
	}

	public InVitroMappingDispGuiCreator() throws RowsExceededException,
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
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		width = InVitroMainScreenCreator.createInVitroMainScreenInstance().inVitroMainInternalFrame
				.getWidth()
				- InVitroSetupAvailableCompCreator
						.createInVitroSetupAvailCompInst().setupAvailableComponentsInternalFrame
						.getWidth();
		height = InVitroSetupAvailableCompCreator
				.createInVitroSetupAvailCompInst().setupAvailableComponentsInternalFrame
				.getHeight();

		mappingInternalFrame
				.setLocation(
						InVitroSetupAvailableCompCreator
								.createInVitroSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ InVitroSetupAvailableCompCreator
										.createInVitroSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						InVitroSetupAvailableCompCreator
								.createInVitroSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		mappingInternalFrame.setSize(width, height);
		mappingInternalFrame.setVisible(true);
		JDesktopPane mappinginternalFrameDesktopPane = new JDesktopPane();
		mappingInternalFrame.setContentPane(mappinginternalFrameDesktopPane);
		mappingInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		createMappingPanel();

		InVitroTabbedPanesCreator.createInVitroTabbedPaneInstance().setupTabDesktopPane
				.add(mappingInternalFrame);
	}

	String analysisType;

	public void createMappingPanel() {

		toLeft = new ImageIcon("left.png");
		toRight = new ImageIcon("right.png");
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();
		createAvailableColumnsIF();
		createTimeConButtonsIF();
		createTimeConIF();
		createSortCarryButtonsIF();
		createSortCarryIF();
		mappingInternalFrame.getContentPane().setBackground(
				inVitroSortCarryIF.getBackground());
	}

	JInternalFrame inVitroSortCarryIF;

	private void createSortCarryIF() {

		inVitroSortCarryIF = new JInternalFrame(
				"carry along and sort list internal frame", false, false,
				false, false);
		inVitroSortCarryIF.setLocation(inVitroSortCarryButIF.getX()
				+ inVitroSortCarryButIF.getWidth(), inVitroSortCarryButIF
				.getY());
		inVitroSortCarryIF.setSize(inVitroSortCarryButIF.getWidth() * 2,
				inVitroSortCarryButIF.getHeight());
		inVitroSortCarryIF.setVisible(true);
		inVitroSortCarryIF.setBorder(null);
		inVitroSortCarryIF.setLayout(new GridBagLayout());
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(inVitroSortCarryIF);

		sortVariableListModel = new DefaultListModel();
		inVitroSortVariablesList = new JList(sortVariableListModel);
		inVitroSortVariablesList.setVisibleRowCount(5);
		inVitroSortVariablesList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());

		JScrollPane scrlPane = new JScrollPane(inVitroSortVariablesList);
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
		inVitroSortCarryIF.getContentPane().add(scrlPane, sortLstCon);
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
		inVitroSortCarryIF.getContentPane().add(scrollPane, carryLstCon);
		mappingInternalFrame.getContentPane().add(inVitroSortCarryIF);
	}

	JInternalFrame inVitroSortCarryButIF;

	private void createSortCarryButtonsIF() {

		inVitroSortCarryButIF = new JInternalFrame(
				"sort carry along internal frame", false, false, false, false);
		inVitroSortCarryButIF.setLocation(inVitroTimeConIF.getX()
				+ inVitroTimeConIF.getWidth(), inVitroTimeConIF.getY());
		inVitroSortCarryButIF.setSize(inVitroTimeConIF.getWidth(),
				inVitroTimeConIF.getHeight());
		inVitroSortCarryButIF.setVisible(true);
		inVitroSortCarryButIF.setBorder(null);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(inVitroSortCarryButIF);
		inVitroSortCarryButIF.setLayout(new GridBagLayout());

		SortAndAvailableButton = new JButton("", toRight);
		SortAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		SortAndAvailableButton.setActionCommand(AVAILAANDSORTINVITRO);
		SortAndAvailableButton.setBackground(null);
		SortAndAvailableButton.setBorder(null);
		GridBagConstraints sortCon = new GridBagConstraints();
		sortCon.gridx = 0;
		sortCon.gridy = 0;
		sortCon.weighty = 0.5;
		sortCon.anchor = GridBagConstraints.LINE_END;
		inVitroSortCarryButIF.getContentPane().add(SortAndAvailableButton,
				sortCon);

		moveToCarryAlongButton = new JButton("", toRight);
		moveToCarryAlongButton.setBorder(null);
		moveToCarryAlongButton.setBackground(null);
		GridBagConstraints carryCon = new GridBagConstraints();
		carryCon.gridx = 0;
		carryCon.gridy = 1;
		carryCon.weighty = 0.5;
		carryCon.anchor = GridBagConstraints.LINE_END;
		inVitroSortCarryButIF.getContentPane().add(moveToCarryAlongButton,
				carryCon);
		mappingInternalFrame.getContentPane().add(inVitroSortCarryButIF);
	}

	JInternalFrame inVitroTimeConIF;
	JTextField caFuncTF;

	private void createTimeConIF() {

		String indepVar = "";
		String depVar = "";

		depVar = "Fdis";
		indepVar = "Time";

		inVitroTimeConIF = new JInternalFrame("Time conc internal frame",
				false, false, false, false);
		inVitroTimeConIF.setLocation(InVitroTimeConcButtonsIF.getX()
				+ InVitroTimeConcButtonsIF.getWidth(), InVitroTimeConcButtonsIF
				.getY());
		inVitroTimeConIF.setSize(InVitroTimeConcButtonsIF.getWidth(),
				InVitroTimeConcButtonsIF.getHeight());
		inVitroTimeConIF.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(inVitroTimeConIF);
		inVitroTimeConIF.setBorder(null);
		inVitroTimeConIF.setLayout(new GridBagLayout());

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
		inVitroTimeConIF.getContentPane().add(xVariableTextField, xVarCon);
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
		inVitroTimeConIF.getContentPane().add(yVariableTextField, yVarCon);

		mappingInternalFrame.getContentPane().add(inVitroTimeConIF);
	}

	JInternalFrame InVitroTimeConcButtonsIF;
	JButton caFuncButton;

	private void createTimeConButtonsIF() {
		InVitroTimeConcButtonsIF = new JInternalFrame("time and conc buttons",
				false, false, false, false);
		InVitroTimeConcButtonsIF.setLocation(inVitroAvailColIF.getX()
				+ inVitroAvailColIF.getWidth(), inVitroAvailColIF.getY());
		InVitroTimeConcButtonsIF.setSize(inVitroAvailColIF.getWidth() / 2,
				inVitroAvailColIF.getHeight());
		InVitroTimeConcButtonsIF.setVisible(true);
		InVitroTimeConcButtonsIF.setLayout(new GridBagLayout());
		InVitroTimeConcButtonsIF.setBorder(null);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(InVitroTimeConcButtonsIF);
		xAndAvailableButton = new JButton("", toRight);
		xAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		xAndAvailableButton.setActionCommand(AVAILANDXINVITRO);
		xAndAvailableButton.setBackground(null);
		xAndAvailableButton.setBorder(null);
		GridBagConstraints caTimeConstraints = new GridBagConstraints();
		caTimeConstraints.gridx = 0;
		caTimeConstraints.gridy = 0;
		caTimeConstraints.weighty = 0.5;
		caTimeConstraints.anchor = GridBagConstraints.LINE_END;
		InVitroTimeConcButtonsIF.getContentPane().add(xAndAvailableButton,
				caTimeConstraints);

		YAndAvailableButton = new JButton("", toRight);
		YAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		YAndAvailableButton.setActionCommand(AVAILANDYINVITRO);
		YAndAvailableButton.setBackground(null);
		YAndAvailableButton.setBorder(null);
		GridBagConstraints caConcCon = new GridBagConstraints();
		caConcCon.gridx = 0;
		caConcCon.gridy = 1;
		caConcCon.weighty = 0.5;
		caConcCon.anchor = GridBagConstraints.LINE_END;
		InVitroTimeConcButtonsIF.getContentPane().add(YAndAvailableButton,
				caConcCon);

		mappingInternalFrame.getContentPane().add(InVitroTimeConcButtonsIF);

	}

	JInternalFrame inVitroAvailColIF;

	private void createAvailableColumnsIF() {

		inVitroAvailColIF = new JInternalFrame("Available columns", false,
				false, false, false);
		inVitroAvailColIF.setLocation(0, 0);
		inVitroAvailColIF.setSize((mappingInternalFrame.getWidth() / 7) * 2,
				mappingInternalFrame.getHeight());
		inVitroAvailColIF.setVisible(true);
		inVitroAvailColIF.setBorder(null);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(inVitroAvailColIF);
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
		inVitroAvailableColumnsList = new JList(availableVariablesListmodel);
		inVitroAvailableColumnsList.setBorder(new CompoundBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"),
						"Available Variables", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(0, 0, 0)),
				new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		inVitroAvailableColumnsList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		JScrollPane scrlPane = new JScrollPane(inVitroAvailableColumnsList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		inVitroAvailColIF.getContentPane().add(scrlPane);
		mappingInternalFrame.getContentPane().add(inVitroAvailColIF);
	}

}
