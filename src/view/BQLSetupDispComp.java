package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Common.EventCodes;
import Common.JinternalFrameFunctions;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class BQLSetupDispComp implements EventCodes {

	private final class RulesTableModelListener implements TableModelListener {
		@Override
		public void tableChanged(TableModelEvent e) {

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

			int columnChanged = rulesTable.getSelectedColumn();
			int rowChanged = rulesTable.getSelectedRow();
			

			if ((columnChanged >= 0) && (rowChanged >= 0)) {

				if (columnChanged != 6) {
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getBqlInfo().getProcessingInputs()
							.getDosingDataObj().setBqlRulesTableValueAt(
									rowChanged,
									columnChanged,
									(String) rulesTable.getValueAt(rowChanged,
											columnChanged));
				}

			}

		}
	}

	public static BQLSetupDispComp BQL_SETUP_DISP = null;

	public static BQLSetupDispComp createBQLSetupDispInst() {
		if (BQL_SETUP_DISP == null) {
			BQL_SETUP_DISP = new BQLSetupDispComp("just object creation");
		}
		return BQL_SETUP_DISP;
	}

	public BQLSetupDispComp(String dummy) {
	}

	JInternalFrame bqlSetupDisplayIF;
	JDesktopPane bqlSetupDispDesktopPane;
	JInternalFrame bqlMappingIF;
	JDesktopPane mappingDesktopPane;
	JInternalFrame bqlRulesIF;
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
	JButton setAsStatusCode;
	JButton moveToVolumeButton;
	DefaultListModel carryAlongModel;
	JList carryAlongList;
	JScrollPane carryAlongScrollPane;
	JLabel statusCodeLable;
	JLabel volumeLable;
	JTextField statusCodeTextField;
	JTextField volumeVariableTextField;
	int previousSize;
	String[] previousSortVariables = new String[1];
	String previousYColumnName = "";

	String previousSubjectColumnName = "";
	String previousXVariable = "";

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
	boolean isAvailToStatus;
	boolean isStatusToAvail;
	JTable rulesTable;
	boolean isAvailableToY;
	JTextField lloqText;
	JCheckBox lloqCheckBox;

	void bqlSetupDispCompCreation() {
		bqlSetupDisplayIF = new JInternalFrame("setup disp", false, false,
				false, false);
		bqlSetupDisplayIF
				.setLocation(
						BQLSetupAvailComp.createBqlSetAvailInst().setupAvailableComponentsInternalFrame
								.getX()
								+ BQLSetupAvailComp.createBqlSetAvailInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						BQLSetupAvailComp.createBqlSetAvailInst().setupAvailableComponentsInternalFrame
								.getY());
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(bqlSetupDisplayIF);
		bqlSetupDisplayIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		bqlSetupDisplayIF.setVisible(true);
		bqlSetupDisplayIF
				.setSize(
						BQLMainScreen.createBQLMainScrInst().bqlMainScreenIF
								.getWidth()
								- BQLSetupAvailComp.createBqlSetAvailInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						BQLSetupAvailComp.createBqlSetAvailInst().setupAvailableComponentsInternalFrame
								.getHeight());
		BQLTabbedPane.createBqlTabInst().setupTabDesktopPane
				.add(bqlSetupDisplayIF);
		bqlSetupDisplayIF.moveToFront();
		bqlSetupDispDesktopPane = new JDesktopPane();
		bqlSetupDisplayIF.setContentPane(bqlSetupDispDesktopPane);

		createMappingScreen();
		createBqlRulesScreen();
		bqlMappingIF.moveToFront();
	}

	private void createBqlRulesScreen() {
		bqlRulesIF = new JInternalFrame("BQL Rules", false, false, false, false);
		bqlRulesIF.setLocation(0, 0);
		bqlRulesIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(bqlRulesIF);
		bqlRulesIF.setSize(bqlSetupDisplayIF.getWidth(), bqlSetupDisplayIF
				.getHeight());
		bqlRulesIF.setVisible(true);
		bqlRulesIF.setContentPane(new JDesktopPane());
		bqlSetupDispDesktopPane.add(bqlRulesIF);
		createRulesIFGUI();
		bqlRulesIF.moveToFront();

	}

	private void createRulesIFGUI() {

		JInternalFrame lloqIF = new JInternalFrame("LLOQ", false, false, false,
				false);
		lloqIF.setLocation(0, 0);
		lloqIF.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(lloqIF);
		lloqIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		lloqIF.setSize(bqlRulesIF.getWidth(), bqlRulesIF.getHeight() / 6);
		bqlRulesIF.getContentPane().add(lloqIF);
		lloqIF.setLayout(new FlowLayout());

		lloqCheckBox = new JCheckBox();
		lloqCheckBox.addActionListener(DDViewLayer.createViewLayerInstance());
		lloqCheckBox.setActionCommand(BQLLLOQCHECKBOX);
		lloqIF.getContentPane().add(lloqCheckBox);

		JLabel lloqLable = new JLabel("LLOQ");
		lloqIF.getContentPane().add(lloqLable);
		lloqIF.moveToFront();

		lloqText = new JTextField();
		lloqText.setColumns(10);
		lloqText.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getBqlInfo()
						.getProcessingInputs().setLloqValue(
								Double.parseDouble(lloqText.getText()));

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getBqlInfo()
						.getProcessingInputs().setLloqValue(
								Double.parseDouble(lloqText.getText()));

			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getBqlInfo()
						.getProcessingInputs().setLloqValue(
								Double.parseDouble(lloqText.getText()));

			}
		});
		lloqText.setEditable(false);
		lloqText.setEnabled(false);
		lloqIF.getContentPane().add(lloqText);

		ArrayList<String> headeNames = new ArrayList<String>();
		headeNames.add("Numeric Code");
		headeNames.add("Unconditional Substitution");
		headeNames.add("Before Tmax");
		headeNames.add("After Tmax");
		headeNames.add("First Consecutive After Tmax");
		headeNames.add("After First Consecutive After Tmax");
		headeNames.add("Use When < LLOQ");

		JInternalFrame tableIF = new JInternalFrame("table", false, false,
				false, false);
		tableIF.setLocation(lloqIF.getX(), lloqIF.getY() + lloqIF.getHeight());
		tableIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(tableIF);
		tableIF.setSize(bqlRulesIF.getWidth(), bqlRulesIF.getHeight()
				- lloqIF.getHeight());
		bqlRulesIF.getContentPane().add(tableIF);
		tableIF.setVisible(true);
		tableIF.moveToFront();
		rulesTable = new JTable(0, 6);
		Object data[][] = { { "", "", "", "", "", "" } };
		Object col[] = { "", "", "", "", "", "" };
		DefaultTableModel model = new DefaultTableModel(data, col);
		rulesTable = new JTable(model) {

			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;

				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				default:
					return Boolean.class;
				}
			}

		};

		rulesTable.setRowHeight(40);
		rulesTable.setRowSelectionAllowed(true);
		rulesTable.setColumnSelectionAllowed(true);
		rulesTable.setSelectionBackground(new Color(238, 238, 234));
		
		rulesTable.setBackground(Color.white);
		TableColumnModel tcm = rulesTable.getColumnModel();
		for (int i = 0; i < rulesTable.getColumnCount(); i++) {
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(headeNames.get(i));
			tc.setCellRenderer(new TableCellLongTextRenderer());
		}
		JScrollPane rulesTableSP = new JScrollPane(rulesTable);

		rulesTableSP.setPreferredSize(new Dimension(tableIF.getWidth(), tableIF
				.getHeight()));
		rulesTable.setFillsViewportHeight(true);
		rulesTable.setAutoscrolls(true);
		rulesTableSP.setVisible(true);
		rulesTable.addMouseListener(DDViewLayer.createViewLayerInstance());
		rulesTable.getModel().addTableModelListener(
				new RulesTableModelListener());
		rulesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		rulesTable.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				BQLOptComp.createBQLOptINst().lloqRB.setEnabled(true);
				BQLOptComp.createBQLOptINst().missingRB.setEnabled(true);
				BQLOptComp.createBQLOptINst().zeroRB.setEnabled(true);

			}
		});
		tableIF.getContentPane().add(rulesTableSP);

	}

	private void createMappingScreen() {
		bqlMappingIF = new JInternalFrame("Mapping", false, false, false, false);
		bqlMappingIF.setLocation(0, 0);
		bqlMappingIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		bqlMappingIF.setSize(bqlSetupDisplayIF.getWidth(), bqlSetupDisplayIF
				.getHeight());
		bqlMappingIF.setVisible(true);
		mappingDesktopPane = new JDesktopPane();
		bqlSetupDispDesktopPane.add(bqlMappingIF);

		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(bqlMappingIF);
		createMappingGUI();
		bqlMappingIF.moveToFront();

	}

	private void createMappingGUI() {
		availableColumnsCreation();
		mappingFieldsCreation();

	}

	private void mappingFieldsCreation() {

	}

	private void availableColumnsCreation() {

		// TODO Auto-generated method stub

		JDesktopPane mappinginternalFrameDesktopPane = new JDesktopPane();
		bqlMappingIF.setContentPane(mappinginternalFrameDesktopPane);
		GridBagLayout gridBagLayout = new GridBagLayout();

		JInternalFrame leftInternalFrame = new JInternalFrame("left", false,
				false, false, false);
		leftInternalFrame.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(leftInternalFrame);
		leftInternalFrame.setLocation(0, 0);
		leftInternalFrame.setSize(bqlMappingIF.getWidth() / 5, bqlMappingIF
				.getHeight());
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
		sortVariablesList = new JList(sortVariableListModel);
		// sortVariablesList.addMouseListener(ViewLayer.createViewLayerInstance());
		sortVariablesList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		sortVariablesList.setVisibleRowCount(3);
		sortVariablesList.setVisible(true);
		JScrollPane sortVarialbeListScrollPane = new JScrollPane(
				sortVariablesList);
		sortVarialbeListScrollPane.setPreferredSize(new Dimension(100, 150));
		GridBagConstraints sortVariableLIstScrollPaneCon = new GridBagConstraints();
		sortVariableLIstScrollPaneCon.gridx = 0;
		sortVariableLIstScrollPaneCon.gridy = 1;
		sortVariableLIstScrollPaneCon.weightx = 0.5;
		// sortVariableLIstScrollPaneCon.weighty = 0.5;
		sortVariableLIstScrollPaneCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(sortVarialbeListScrollPane,
				sortVariableLIstScrollPaneCon);

		JLabel xVariableLable = new JLabel("Time");
		GridBagConstraints xVariableLableCon = new GridBagConstraints();
		xVariableLableCon.gridx = 0;
		xVariableLableCon.gridy = 2;
		xVariableLableCon.weightx = 0.5;
		// xVariableLableCon.weighty = 0.5;
		xVariableLableCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(xVariableLable,
				xVariableLableCon);

		xVariableTextField = new JTextField();
		xVariableTextField.setText("");
		xVariableTextField.setBackground(Color.white);
		xVariableTextField.setEditable(false);
		xVariableTextField
				.addFocusListener(DDViewLayer.createViewLayerInstance());
		xVariableTextField.setColumns(8);
		xVariableTextField.setVisible(true);
		GridBagConstraints xVariableTextFieldCon = new GridBagConstraints();
		xVariableTextFieldCon.gridx = 0;
		xVariableTextFieldCon.gridy = 3;
		// xVariableTextFieldCon.weightx = 0.1;
		xVariableTextFieldCon.weighty = 0.5;
		xVariableTextFieldCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(xVariableTextField,
				xVariableTextFieldCon);

		JLabel yVariableLable = new JLabel("Concentration");
		GridBagConstraints yVairableLableCon = new GridBagConstraints();
		yVairableLableCon.gridx = 0;
		yVairableLableCon.gridy = 6;
		yVairableLableCon.weightx = 0.5;

		yVairableLableCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(yVariableLable,
				yVairableLableCon);

		yVariableTextField = new JTextField();
		yVariableTextField.setText("");
		yVariableTextField.setBackground(Color.white);
		yVariableTextField.setEditable(false);
		// yVariableTextField.addMouseListener(ViewLayer.createViewLayerInstance());
		yVariableTextField
				.addFocusListener(DDViewLayer.createViewLayerInstance());
		yVariableTextField.setColumns(8);
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
		leftarrowsInternalFrame.setBackground(Color.white);
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
		SortAndAvailableButton.setActionCommand(BQLAVAILAANDSORT);
		SortAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		SortAndAvailableButton.setVisible(true);
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
		xAndAvailableButton.setActionCommand(BQLAVAILANDX);
		xAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		GridBagConstraints setAsXVariableCon = new GridBagConstraints();
		setAsXVariableCon.gridx = 0;
		setAsXVariableCon.gridy = 2;
		setAsXVariableCon.weighty = 0.5;
		setAsXVariableCon.anchor = GridBagConstraints.LINE_START;
		leftarrowsInternalFrame.getContentPane().add(xAndAvailableButton,
				setAsXVariableCon);

		YAndAvailableButton = new JButton("", toLeft);
		YAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		YAndAvailableButton.setActionCommand(BQLAVAILANDY);
		YAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		GridBagConstraints setAsYVariableButtonCon = new GridBagConstraints();
		setAsYVariableButtonCon.gridy = 4;
		setAsYVariableButtonCon.gridx = 0;
		setAsYVariableButtonCon.weighty = 0.5;
		setAsYVariableButtonCon.anchor = GridBagConstraints.LINE_START;
		leftarrowsInternalFrame.getContentPane().add(YAndAvailableButton,
				setAsYVariableButtonCon);

		JInternalFrame middleInternalFrame = new JInternalFrame("middle",
				false, false, false, false);
		middleInternalFrame.setBackground(Color.white);
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

		JLabel availableColumnsLable = new JLabel("Variables");
		GridBagConstraints availableColumnsLableCon = new GridBagConstraints();
		availableColumnsLableCon.gridx = 0;
		availableColumnsLableCon.gridy = 0;
		middleInternalFrame.getContentPane().add(availableColumnsLable,
				availableColumnsLableCon);

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		availableVariablesListmodel = new DefaultListModel();
		availableColumnsList = new JList(availableVariablesListmodel);

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
					.getBqlInfo()
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

		// availableColumnsList.addMouseListener(ViewLayer.createViewLayerInstance());
		availableColumnsList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		availableColumnsList.setVisible(true);
		availableColumnsList.setVisibleRowCount(5);

		JScrollPane columnsAvailableListScrollPane = new JScrollPane(
				availableColumnsList);
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
		rightArrowsinternalFrame.setBackground(Color.white);
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
		moveToCarryAlongButton.setPreferredSize(new Dimension(60, 15));
		moveToCarryAlongButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		moveToCarryAlongButton.setActionCommand(BQLMOVETOCARRY);
		GridBagConstraints moveTocarryAlongButtonCon = new GridBagConstraints();
		moveTocarryAlongButtonCon.gridx = 0;
		moveTocarryAlongButtonCon.gridy = 1;
		moveTocarryAlongButtonCon.weighty = 0.5;
		moveTocarryAlongButtonCon.anchor = GridBagConstraints.LINE_END;
		rightArrowsinternalFrame.getContentPane().add(moveToCarryAlongButton,
				moveTocarryAlongButtonCon);

		setAsStatusCode = new JButton("", toRight);
		setAsStatusCode.setPreferredSize(new Dimension(60, 15));
		setAsStatusCode.addActionListener(DDViewLayer.createViewLayerInstance());
		setAsStatusCode.setActionCommand(BQLMOVETOSTATUS);
		setAsStatusCode.setVisible(true);

		GridBagConstraints setAsSubjectbuttonCon = new GridBagConstraints();
		setAsSubjectbuttonCon.gridx = 0;
		setAsSubjectbuttonCon.gridy = 2;
		setAsSubjectbuttonCon.weighty = 0.5;
		// setAsSubjectbuttonCon.anchor = GridBagConstraints.LINE_START;
		rightArrowsinternalFrame.getContentPane().add(setAsStatusCode,
				setAsSubjectbuttonCon);

		JInternalFrame rightInternalFrame = new JInternalFrame("right", false,
				false, false, false);
		rightInternalFrame.setBackground(Color.white);
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

		JLabel carryAlongLaable = new JLabel("Carry Along");
		GridBagConstraints carryAlongLableCon = new GridBagConstraints();
		carryAlongLableCon.gridx = 0;
		carryAlongLableCon.gridy = 0;
		carryAlongLableCon.weightx = 0.5;
		carryAlongLableCon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrame.getContentPane().add(carryAlongLaable,
				carryAlongLableCon);

		carryAlongModel = new DefaultListModel();
		carryAlongList = new JList(carryAlongModel);

		carryAlongScrollPane = new JScrollPane(carryAlongList);
		carryAlongList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		carryAlongScrollPane.setPreferredSize(new Dimension(70, 50));
		GridBagConstraints carryAlongScrollPaneCOn = new GridBagConstraints();
		carryAlongScrollPaneCOn.gridx = 0;
		carryAlongScrollPaneCOn.gridy = 1;
		carryAlongScrollPaneCOn.weightx = 0.5;
		// carryAlongScrollPaneCOn.weighty = 0.5;
		carryAlongScrollPaneCOn.anchor = GridBagConstraints.LINE_START;
		rightInternalFrame.getContentPane().add(carryAlongScrollPane,
				carryAlongScrollPaneCOn);

		statusCodeLable = new JLabel("Status Code");
		statusCodeLable.setVisible(true);
		GridBagConstraints statusCodeCon = new GridBagConstraints();
		statusCodeCon.gridx = 0;
		statusCodeCon.gridy = 4;

		// statusCodeCon.weighty = 0.5;
		statusCodeCon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrame.getContentPane().add(statusCodeLable, statusCodeCon);

		statusCodeTextField = new JTextField();
		statusCodeTextField.setColumns(8);
		statusCodeTextField.setVisible(true);
		statusCodeTextField.setBackground(Color.white);
		statusCodeTextField.setEditable(false);
		statusCodeTextField.addFocusListener(DDViewLayer
				.createViewLayerInstance());
		GridBagConstraints statusCodeTextFieldCon = new GridBagConstraints();
		statusCodeTextFieldCon.gridx = 0;
		statusCodeTextFieldCon.gridy = 5;
		// subjectTextFieldCon.weighty = 0.5;
		statusCodeTextFieldCon.weightx = 0.5;
		statusCodeTextFieldCon.anchor = GridBagConstraints.LINE_START;
		rightInternalFrame.getContentPane().add(statusCodeTextField,
				statusCodeTextFieldCon);

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

class TableCellLongTextRenderer extends JTextArea implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (column != 6) {
			this.setText((String) value);
			this.setWrapStyleWord(true);
			this.setLineWrap(true);
		}

		// set the JTextArea to the width of the table column
		setSize(table.getColumnModel().getColumn(column).getWidth(),
				getPreferredSize().height);
		if (table.getRowHeight(row) != getPreferredSize().height) {
			// set the height of the table row to the calculated height of the
			// JTextArea
			//table.setRowHeight(row, getPreferredSize().height);
		}

		return this;
	}

}
