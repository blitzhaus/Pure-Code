package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Console;
import java.security.KeyStore;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import view.ApplicationInfo;
import view.DisplayContents;
import view.TableCellListener;

import Common.EventCodes;
import Common.JinternalFrameFunctions;
import Common.LogEntry;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Font;
import jxl.write.Number;
import jxl.write.WritableWorkbook;

public class ImportedDataSheet extends JInternalFrame implements EventCodes {

	private final class IMPDataTalbeFocusListHandler implements FocusListener {
		@Override
		public void focusLost(FocusEvent arg0) {

			// add.setEnabled(false);
		}

		@Override
		public void focusGained(FocusEvent arg0) {

			// MainLayoutPage.add.setEnabled(true);
		}
	}

	private final class IMPDataTableCellChangeHandler extends AbstractAction {
		TableCellListener tcl = new TableCellListener(importedDataTable,
				importedDataTableCellChangedAction);

		public void actionPerformed(ActionEvent e) {
			TableCellListener tcl = (TableCellListener) e.getSource();

			if (tcl.getOldValue().equals(tcl.getNewValue())) {
				
			} else {
				
				LogEntry.createLogEntryInstance().logEntry(
						"Display Contents:: Table Cell(" + (tcl.getRow() + 1)
								+ "," + (tcl.getColumn() + 1)
								+ ") changed from " + tcl.getOldValue()
								+ " to " + tcl.getNewValue());
			}

		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDesktopPane importedDtaSheetDesktopPane;
	public JInternalFrame dataDisplayFrame;
	JTable importedDataTable;
	JTableHeader importedDataTableHeader;
	AbstractTableModel t;
	public JInternalFrame columnsAvailableInternalFrame;
	public JInternalFrame columnPropertiesInternalFrame;
	int sheetIndex;
	Sheet workSheet;
	Workbook workBook;
	JLabel[] columnHeaderLables;
	int numberOfColumns;
	JButton unitBuilderButton;
	JButton dataTypeChangeButton;
	JTextField unitsTextField;
	JTextField dataTypeTextField;
	JTextField displayFormatTextField;
	TableColumnModel TableColumnModel;
	JList columnsAvailableList;
	AbstractAction importedDataTableCellChangedAction;
	JRadioButton numericRadioButton;
	JRadioButton optionalDecimalsRadioButton;
	JComboBox optionaldecimalCombo;
	JComboBox requiredDecimalsCombo;
	JCheckBox useThousandsSeperatorCheck;
	ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
	String[][] originalValues;
	DefaultListModel columnsAvailableListModel;
	boolean isColumnMoving;

	public static ImportedDataSheet importedDataSheetFrame = null;

	public static ImportedDataSheet createImportedDataSheetInstance() {
		if (importedDataSheetFrame == null) {

			importedDataSheetFrame = new ImportedDataSheet(DisplayContents
					.createAppInfoInstance().getProjectInfoAL().get(
							DisplayContents.createAppInfoInstance()
									.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(
							DisplayContents.createAppInfoInstance()
									.getProjectInfoAL().get(
											DisplayContents
													.createAppInfoInstance()
													.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getSelectedSheetIndex());
		}
		return importedDataSheetFrame;
	}

	public ImportedDataSheet(int sheetIndex) {

		this.sheetIndex = sheetIndex;

	}

	public void createImpDataSheetFrame() {
		DDViewLayer.createViewLayerInstance().isimportedDataTable = true;
		isColumnMoving = false;
		importedDtaSheetDesktopPane = new JDesktopPane();
		getContentPane().add(importedDtaSheetDesktopPane);
		int xIndex = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getElementFromDS(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getSelectedSheetIndex()).length;
		int yIndex = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getElementFromDS(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getSelectedSheetIndex())[0].length;
		originalValues = new String[xIndex][yIndex];
		for (int i = 0; i < xIndex; i++) {
			for (int j = 0; j < yIndex; j++) {
				originalValues[i][j] = appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getElementFromDS(
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
										.getSelectedSheetIndex())[i][j];
			}
		}

		createDataDisplayInternalFrame();
		createColumnsAvailableInternalFrame();
		createColumnpropertiesInternalFrame();

		IMPSheetCoreFunctions.createIMPSheetCoreFuncInstance()
				.fillDefaultValuesForColumnProperties();

		// since the listener is added while imported table has empty values now
		// in appInfo also there are empty values
		// so copy the original data to the respective data structure
		appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getDataStructuresArrayList()
				.set(
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
								.getSelectedSheetIndex(), originalValues);

		DDViewLayer.createViewLayerInstance().isimportedDataTable = false;
		ImportFile.createDispContentsInstance().finishAndOpenClicked = false;

	}

	private void createColumnpropertiesInternalFrame() {

		columnPropertiesInternalFrame = new JInternalFrame("column Properties",
				false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(columnPropertiesInternalFrame);
		columnPropertiesInternalFrame.setLocation(columnsAvailableInternalFrame
				.getWidth()
				+ columnsAvailableInternalFrame.getX(),
				columnsAvailableInternalFrame.getY());
		columnPropertiesInternalFrame.setSize(dataDisplayFrame.getWidth()
				- columnsAvailableInternalFrame.getWidth(),
				columnsAvailableInternalFrame.getHeight());
		columnPropertiesInternalFrame.setVisible(true);
		GridBagLayout gridbagLayout = new GridBagLayout();
		columnPropertiesInternalFrame.setLayout(gridbagLayout);
		columnPropertiesInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		columnPropertiesInternalFrame.setBackground(Color.white);
		createInternalGui();
		importedDtaSheetDesktopPane.add(columnPropertiesInternalFrame);
	}

	private void createInternalGui() {

		JLabel dataTypeLable = new JLabel("Data Type");
		GridBagConstraints dataTypeLableCon = new GridBagConstraints();
		dataTypeLableCon.gridx = 0;
		dataTypeLableCon.gridy = 0;
		dataTypeLableCon.weightx = 0.1;
		dataTypeLableCon.anchor = GridBagConstraints.LINE_START;
		columnPropertiesInternalFrame.getContentPane().add(dataTypeLable,
				dataTypeLableCon);

		dataTypeTextField = new JTextField();
		dataTypeTextField.setEditable(false);
		dataTypeTextField.setBackground(Color.white);
		dataTypeTextField.setColumns(10);
		GridBagConstraints dataTypeTextFieldCon = new GridBagConstraints();
		dataTypeTextFieldCon.gridx = 1;
		dataTypeTextFieldCon.gridy = 0;
		dataTypeLableCon.weightx = 0.1;
		dataTypeTextFieldCon.anchor = GridBagConstraints.LINE_START;
		columnPropertiesInternalFrame.getContentPane().add(dataTypeTextField,
				dataTypeTextFieldCon);

		dataTypeChangeButton = new JButton("Change");
		dataTypeChangeButton.setPreferredSize(new Dimension(90, 20));
		dataTypeChangeButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		dataTypeChangeButton.setActionCommand(DATATYPECHANGE);
		GridBagConstraints dataTypeChangeButtonCon = new GridBagConstraints();
		dataTypeChangeButtonCon.gridx = 2;
		dataTypeChangeButtonCon.gridy = 0;
		dataTypeChangeButtonCon.weightx = 0.5;
		dataTypeChangeButtonCon.anchor = GridBagConstraints.LINE_START;
		columnPropertiesInternalFrame.getContentPane().add(
				dataTypeChangeButton, dataTypeChangeButtonCon);

		JLabel unitLable = new JLabel("unit");
		GridBagConstraints unitLableCon = new GridBagConstraints();
		unitLableCon.gridx = 0;
		unitLableCon.gridy = 2;
		unitLableCon.weightx = 0.5;
		unitLableCon.anchor = GridBagConstraints.LINE_START;
		columnPropertiesInternalFrame.getContentPane().add(unitLable,
				unitLableCon);

		unitsTextField = new JTextField();
		unitsTextField.setColumns(10);

		GridBagConstraints unitsTextFieldCon = new GridBagConstraints();
		unitsTextFieldCon.gridx = 1;
		unitsTextFieldCon.gridy = 2;
		unitsTextFieldCon.weightx = 0.5;
		unitsTextFieldCon.anchor = GridBagConstraints.LINE_START;
		columnPropertiesInternalFrame.getContentPane().add(unitsTextField,
				unitsTextFieldCon);

		unitBuilderButton = new JButton("Change");
		unitBuilderButton
				.addActionListener(DDViewLayer.createViewLayerInstance());
		unitBuilderButton.setActionCommand(UNITBUILDERBUTTON);
		unitBuilderButton.setPreferredSize(new Dimension(90, 20));
		GridBagConstraints unitBuilderButtonCon = new GridBagConstraints();
		unitBuilderButtonCon.gridx = 2;
		unitBuilderButtonCon.gridy = 2;
		unitBuilderButtonCon.weightx = 0.5;
		unitBuilderButtonCon.anchor = GridBagConstraints.LINE_START;
		columnPropertiesInternalFrame.getContentPane().add(unitBuilderButton,
				unitBuilderButtonCon);

		JPanel formatPanel = new JPanel();
		formatPanel.setBackground(Color.white);
		GridBagConstraints formatPanelCon = new GridBagConstraints();
		formatPanelCon.gridx = 0;
		formatPanelCon.gridy = 3;
		formatPanelCon.weightx = 0.5;
		formatPanelCon.weighty = 0.5;
		formatPanelCon.gridheight = 4;
		formatPanelCon.gridwidth = 4;
		formatPanel.setBorder(DDViewLayer.createViewLayerInstance().b);

		numericRadioButton = new JRadioButton("Required Decimals");
		numericRadioButton.setSelected(true);
		numericRadioButton.addItemListener(DDViewLayer
				.createViewLayerInstance());
		numericRadioButton.setActionCommand(IMPSHEETNUMERICRADIO);
		numericRadioButton.setBackground(Color.white);
		GridBagConstraints requiredDecimalsLableCon = new GridBagConstraints();
		requiredDecimalsLableCon.gridx = 0;
		requiredDecimalsLableCon.gridy = 6;
		requiredDecimalsLableCon.weighty = 0.5;
		requiredDecimalsLableCon.anchor = GridBagConstraints.LINE_START;
		// formatPanel.add(requiredDecimalsLable,requiredDecimalsLableCon);
		columnPropertiesInternalFrame.getContentPane().add(numericRadioButton,
				requiredDecimalsLableCon);

		requiredDecimalsCombo = new JComboBox();
		
		requiredDecimalsCombo.setEnabled(false);
		requiredDecimalsCombo.addItem("None");
		for (int i = 1; i < 20; i++) {
			requiredDecimalsCombo.addItem(i);
		}

		requiredDecimalsCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				ImportedDataSheetHandlers.createImpSheetHandInstance().reqDecimalHandling = true;
				ImportedDataSheetHandlers.createImpSheetHandInstance()
						.implementRequiredDecimals();
				ImportedDataSheetHandlers.createImpSheetHandInstance().reqDecimalHandling = false;
			}
		});
		GridBagConstraints requiredDecimalsTextFieldCon = new GridBagConstraints();
		requiredDecimalsTextFieldCon.gridx = 1;
		requiredDecimalsTextFieldCon.gridy = 6;
		requiredDecimalsTextFieldCon.anchor = GridBagConstraints.LINE_START;
		columnPropertiesInternalFrame.getContentPane().add(
				requiredDecimalsCombo, requiredDecimalsTextFieldCon);

		optionalDecimalsRadioButton = new JRadioButton("Optional Decimals");
		optionalDecimalsRadioButton.addItemListener(DDViewLayer
				.createViewLayerInstance());
		optionalDecimalsRadioButton.setActionCommand(IMPSHEETOPTDECIMALRADIO);
		optionalDecimalsRadioButton.setBackground(Color.white);
		GridBagConstraints optionalDecimalsLableCon = new GridBagConstraints();
		optionalDecimalsLableCon.gridx = 0;
		optionalDecimalsLableCon.gridy = 7;
		optionalDecimalsLableCon.anchor = GridBagConstraints.LINE_START;
		columnPropertiesInternalFrame.getContentPane().add(
				optionalDecimalsRadioButton, optionalDecimalsLableCon);

		ButtonGroup decimalsGroup = new ButtonGroup();
		decimalsGroup.add(numericRadioButton);
		decimalsGroup.add(optionalDecimalsRadioButton);

		optionaldecimalCombo = new JComboBox();
		optionaldecimalCombo.setEnabled(false);
		optionaldecimalCombo.setEditable(false);
		optionaldecimalCombo.addItem("None");
		for (int i = 1; i < 20; i++) {
			optionaldecimalCombo.addItem(i);
		}

		optionaldecimalCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ImportedDataSheetHandlers.createImpSheetHandInstance().optDecimalHandling = true;
				ImportedDataSheetHandlers.createImpSheetHandInstance()
						.implementOptionalHandler();
				ImportedDataSheetHandlers.createImpSheetHandInstance().optDecimalHandling = false;
			}
		});

		GridBagConstraints optionalDecimaltextCon = new GridBagConstraints();
		optionalDecimaltextCon.gridx = 1;
		optionalDecimaltextCon.gridy = 7;
		optionaldecimalCombo.setVisible(true);
		optionalDecimaltextCon.anchor = GridBagConstraints.LINE_START;
		columnPropertiesInternalFrame.getContentPane().add(
				optionaldecimalCombo, optionalDecimaltextCon);

		useThousandsSeperatorCheck = new JCheckBox("Use Thousands Seperator");
		useThousandsSeperatorCheck.setBackground(Color.white);
		useThousandsSeperatorCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ImportedDataSheetHandlers.createImpSheetHandInstance()
						.thousandsSepHandler();
			}
		});
		GridBagConstraints useThresholdseperatorCheckCon = new GridBagConstraints();
		useThresholdseperatorCheckCon.gridx = 0;
		useThresholdseperatorCheckCon.gridy = 8;
		useThresholdseperatorCheckCon.weighty = 0.5;
		useThresholdseperatorCheckCon.anchor = GridBagConstraints.LINE_START;
		columnPropertiesInternalFrame.getContentPane().add(
				useThousandsSeperatorCheck, useThresholdseperatorCheckCon);

	}

	private void createColumnsAvailableInternalFrame() {

		columnsAvailableInternalFrame = new JInternalFrame("Column Properties",
				false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(columnsAvailableInternalFrame);
		columnsAvailableInternalFrame.setBackground(Color.white);
		columnsAvailableInternalFrame.setLocation(0, dataDisplayFrame
				.getHeight());
		columnsAvailableInternalFrame.setSize(dataDisplayFrame.getWidth() / 4,
				dataDisplayFrame.getHeight() + 20);
		columnsAvailableInternalFrame.setVisible(true);
		columnsAvailableInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		columnsAvailableInternalFrame.moveToFront();
		IMPSheetCoreFunctions.createIMPSheetCoreFuncInstance().displayColumns();
		importedDtaSheetDesktopPane.add(columnsAvailableInternalFrame);
	}

	void fillColumnsAvailableList() {
		appInfo = DisplayContents.createAppInfoInstance();
		String[][] data = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getElementFromDS(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getSelectedSheetIndex());
		appInfo = DisplayContents.createAppInfoInstance();
		columnsAvailableListModel.removeAllElements();
		for (int i = 0; i < data[0].length; i++) {
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
									.getSelectedSheetIndex()).getHasHeader() == true) {
				String c = data[0][i];
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(sheetIndex)
						.getColumnPropertiesArrayList().get(i)
						.setColumnNames(c);
			} else {
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(sheetIndex)
						.getColumnPropertiesArrayList().get(i).setColumnNames(
								"column" + (i + 1));
			}

			columnsAvailableListModel.add(columnsAvailableListModel.getSize(),
					appInfo.getProjectInfoAL().get(
							appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(sheetIndex)
							.getColumnPropertiesArrayList().get(i)
							.getColumnName());
		}

	}

	private void createDataDisplayInternalFrame() {

		dataDisplayFrame = new JInternalFrame("", false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(dataDisplayFrame);
		GridBagLayout gridBagLayout = new GridBagLayout();
		dataDisplayFrame.getContentPane().setLayout(gridBagLayout);
		dataDisplayFrame.setLocation(0, 0);
		dataDisplayFrame.setBackground(Color.white);
		dataDisplayFrame.getContentPane().setBackground(Color.white);
		dataDisplayFrame.setSize(DDViewLayer.createMTInstance().mainTabbedFrame
				.getWidth(), DDViewLayer.createMTInstance().mainTabbedFrame
				.getHeight() / 2 - 20);
		dataDisplayFrame.setVisible(true);
		dataDisplayFrame.moveToFront();
		dataDisplayFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		displayData();
		importedDtaSheetDesktopPane.add(dataDisplayFrame);
		dataDisplayFrame
				.setTitle(appInfo
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
						.getSheetName());
		// fix(importedDtaSheetDesktopPane);
	}

	private void displayData() {

		final ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		// workSheet = workBook.getSheet(sheetIndex);
		String[][] data1 = appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex()).getElementFromDS(
				sheetIndex);

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
								.getSelectedSheetIndex()).getHasHeader() == true) {
			if (DDViewLayer.createViewLayerInstance().isFronClickOnProjectExplorer == true) {
				importedDataTable = new JTable(
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
								.getCopyOfOriginalDataStructures().get(sheetIndex).length
								- appInfo
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
										.getStartRow(), data1[0].length);

			} else {

				importedDataTable = new JTable(
						ImportFile.createDispContentsInstance().dataTable
								.getRowCount()
								- appInfo
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
										.getStartRow(), ImportFile
								.createDispContentsInstance().dataTable
								.getColumnCount());
			}

		} else {

			if (DDViewLayer.createViewLayerInstance().isFronClickOnProjectExplorer == true) {

				importedDataTable = new JTable(
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
								.getElementFromDS(sheetIndex).length
								- appInfo
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
										.getStartRow() + 1, data1[0].length);
			} else {

				importedDataTable = new JTable(
						ImportFile.createDispContentsInstance().dataTable
								.getRowCount()
								- appInfo
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
										.getStartRow() + 1, ImportFile
								.createDispContentsInstance().dataTable
								.getColumnCount());
			}

		}

		/*
		 * for(int i=0;i<importedDataTable.getRowCount();i++){ for(int
		 * j=0;j<importedDataTable.getColumnCount();j++){
		 * importedDataTable.setValueAt("", i, j); } }
		 */
		importedDataTable.setCellSelectionEnabled(true);
		new ReorderableJList(importedDataTable);
		importedDataTable.setColumnSelectionAllowed(false);
		importedDataTable.setRowSelectionAllowed(true);
		importedDataTable.setCellSelectionEnabled(true);
		importedDataTable.setColumnSelectionAllowed(true);
		importedDataTable.getModel().addTableModelListener(
				DDViewLayer.createViewLayerInstance());
		importedDataTable.setBackground(Color.white);
		importedDataTable.getColumnModel().addColumnModelListener(
				new ColumnSelectionChnageHandler(appInfo));
		importedDataTable.getSelectionModel().addListSelectionListener(
				new IMPDataTableListSelectionHandler(appInfo));
		importedDataTable.addFocusListener(new IMPDataTalbeFocusListHandler());
		int width = (int) ((int) getWidth() / 1.2);
		importedDataTable.setPreferredScrollableViewportSize(new Dimension(600,
				300));
		t = (AbstractTableModel) importedDataTable.getModel();
		// t.fireTableDataChanged();
		importedDataTable.setFillsViewportHeight(true);
		importedDataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		GridBagConstraints tableScrollPaneCon = new GridBagConstraints();
		tableScrollPaneCon = new GridBagConstraints();
		tableScrollPaneCon.gridx = 0;
		tableScrollPaneCon.gridy = 0;
		tableScrollPaneCon.weighty = 0.5;
		tableScrollPaneCon.weightx = 0.5;
		tableScrollPaneCon.gridheight = 5;
		tableScrollPaneCon.gridwidth = 4;
		tableScrollPaneCon.fill = GridBagConstraints.BOTH;
		int counting = 0;
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
								.getSelectedSheetIndex()).getHasHeader() == true) {
			determineColDataTypeWithHeader(appInfo);
		} else {
			determineColDataTypeNoHeader(appInfo);
		}
		importedDataTable.setBackground(Color.white);
		JScrollPane dataTableScrollPane = new JScrollPane(importedDataTable);
		dataTableScrollPane.setBackground(Color.white);
		dataDisplayFrame.getContentPane().add(dataTableScrollPane,
				tableScrollPaneCon);
		importedDataTableHeader = importedDataTable.getTableHeader();

		// fill the header with the first row of the sheet if it has a header
		TableColumnModel = importedDataTableHeader.getColumnModel();
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
								.getSelectedSheetIndex()).getHasHeader() == true) {
			String[][] data = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getElementFromDS(sheetIndex);
			for (int i = 0; i < data[0].length; i++) {
				TableColumn tc = TableColumnModel.getColumn(i);
				String c = data[0][i];
				tc.setHeaderValue(c);
			}
		} else {
			String[][] data = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getElementFromDS(sheetIndex);
			for (int i = 0; i < data[0].length; i++) {
				TableColumn tc = TableColumnModel.getColumn(i);
				tc.setHeaderValue("column " + (i + 1));
			}
		}
		importedDataTableCellChangedAction = new IMPDataTableCellChangeHandler();
	}

	private void determineColDataTypeNoHeader(final ApplicationInfo appInfo) {
		if (DDViewLayer.createViewLayerInstance().isFronClickOnProjectExplorer == false) {

			String c = null;

			// just fill the data do not bother about the data type
			for (int i = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(sheetIndex).getStartRow() - 1; i < importedDataTable
					.getRowCount(); i++) {
				for (int j = 0; j < importedDataTable.getColumnCount(); j++) {
					c = ImportFile.createDispContentsInstance().dataTable
							.getValueAt(i, j).toString();
					importedDataTable.setValueAt(c, i, j);

				}

			}

			// create a arraylist of columnProperties objects so that we can
			// fill the properties
			ArrayList<ColumnProperties> localColumnPropertiesAL = new ArrayList<ColumnProperties>();
			for (int m = 0; m < importedDataTable.getColumnCount(); m++) {
				ColumnProperties clmPropObj = new ColumnProperties();
				localColumnPropertiesAL.add(clmPropObj);
			}
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
					.setColumnPropertiesArrayList(localColumnPropertiesAL);

			// create a new unit builder data object also so that null point
			// exception does not arise
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
				UnitBuilderData localUnitBuilderData = new UnitBuilderData();
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
						.getColumnPropertiesArrayList().get(i)
						.setUnitBuilderDataObj(localUnitBuilderData);
			}

			// now bother about the data type.
			for (int j = 0; j < importedDataTable.getColumnCount(); j++) {
				for (int i = appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(sheetIndex).getStartRow() - 1; i < importedDataTable
						.getRowCount(); i++) {
					c = ImportFile.createDispContentsInstance().dataTable
							.getValueAt(i, j).toString();
					// determining the data type of the column
					try {
						Double.parseDouble(c);
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
								.getWorkSheetObjectsAL().get(sheetIndex)
								.getColumnPropertiesArrayList().get(j)
								.setDataType("Numeric");
					} catch (Exception e) {
						try {
							Integer.parseInt(c);
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
									.getWorkSheetObjectsAL().get(sheetIndex)
									.getColumnPropertiesArrayList().get(j)
									.setDataType("Numeric");

						} catch (Exception e1) {
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
									.getWorkSheetObjectsAL().get(sheetIndex)
									.getColumnPropertiesArrayList().get(j)
									.setDataType("Text");
							break;
						}

					}

				}
			}

		} else if (DDViewLayer.createViewLayerInstance().isFronClickOnProjectExplorer == true) {
			String c = null;
			int rowIndex = 0;
			String[][] data = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getElementFromDS(sheetIndex);
			for (int i = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(sheetIndex).getStartRow() - 1; i < data.length; i++) {
				int columnIndex = 0;
				for (int j = 0; j < data[0].length; j++) {
					c = data[i][j];

					// determining the data type of the column
					try {
						Double.parseDouble(c);
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
								.getWorkSheetObjectsAL().get(sheetIndex)
								.getColumnPropertiesArrayList().get(j)
								.setDataType("Numeric");
					} catch (Exception e) {
						try {
							Integer.parseInt(c);
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
									.getWorkSheetObjectsAL().get(sheetIndex)
									.getColumnPropertiesArrayList().get(j)
									.setDataType("Numeric");

						} catch (Exception e1) {
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
									.getWorkSheetObjectsAL().get(sheetIndex)
									.getColumnPropertiesArrayList().get(j)
									.setDataType("Text");
						}

					}
					
					if(c.equals("-3.14159265359")){
						importedDataTable.setValueAt("", i, j);
					} else{
						importedDataTable.setValueAt(c, i, j);
					}
					
				}
				rowIndex++;
			}
		}
	}

	private void determineColDataTypeWithHeader(final ApplicationInfo appInfo) {
		if (DDViewLayer.createViewLayerInstance().isFronClickOnProjectExplorer == false) {
			String c = null;
			

			// just fill the data do not bother about the data type
			for (int i = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(sheetIndex).getStartRow() - 1; i < importedDataTable
					.getRowCount(); i++) {
				for (int j = 0; j < importedDataTable.getColumnCount(); j++) {
					c = ImportFile.createDispContentsInstance().dataTable
							.getValueAt(i + 1, j).toString();
					importedDataTable.setValueAt(c, i, j);

				}

			}

			// create a arraylist of columnProperties objects so that we can
			// fill the properties
			ArrayList<ColumnProperties> localColumnPropertiesAL = new ArrayList<ColumnProperties>();
			for (int m = 0; m < importedDataTable.getColumnCount(); m++) {
				ColumnProperties clmPropObj = new ColumnProperties();
				localColumnPropertiesAL.add(clmPropObj);
			}
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
					.setColumnPropertiesArrayList(localColumnPropertiesAL);

			// create a new unit builder data object also so that null point
			// exception does not arise
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
				UnitBuilderData localUnitBuilderData = new UnitBuilderData();
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
						.getColumnPropertiesArrayList().get(i)
						.setUnitBuilderDataObj(localUnitBuilderData);
			}

			// now bother about the data type.
			for (int j = 0; j < importedDataTable.getColumnCount(); j++) {

				for (int i = appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(sheetIndex).getStartRow() - 1; i < importedDataTable
						.getRowCount(); i++) {
					c = (String) ImportFile.createDispContentsInstance().dataTable
							.getValueAt(i + 1, j);
					// determining the data type of the column
					try {

						
						Double.parseDouble(c);
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
								.getWorkSheetObjectsAL().get(sheetIndex)
								.getColumnPropertiesArrayList().get(j)
								.setDataType("Numeric");

					} catch (Exception e) {
						try {

							Integer.parseInt(c);
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
									.getWorkSheetObjectsAL().get(sheetIndex)
									.getColumnPropertiesArrayList().get(j)
									.setDataType("Numeric");

						} catch (Exception e1) {

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
									.getWorkSheetObjectsAL().get(sheetIndex)
									.getColumnPropertiesArrayList().get(j)
									.setDataType("Text");
							break;
						}

					}

				}
			}

			// This loop is just for printing
			for (int i = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(sheetIndex).getStartRow() - 1; i < importedDataTable
					.getRowCount(); i++) {

			}

		} else if (DDViewLayer.createViewLayerInstance().isFronClickOnProjectExplorer == true) {

			String[][] data = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getElementFromDS(sheetIndex);
			String c = null;

			// just fill the data do not bother about the data type
			for (int i = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(sheetIndex).getStartRow() - 1; i < importedDataTable
					.getRowCount(); i++) {
				for (int j = 0; j < importedDataTable.getColumnCount(); j++) {
					c = data[i + 1][j].toString();

					if(c.equals("-3.14159265359")){
						importedDataTable.setValueAt("", i, j);
					} else {
						importedDataTable.setValueAt(c, i, j);
					}
					

				}
				
			}

			// create a arraylist of columnProperties objects so that we can
			// fill the properties
			ArrayList<ColumnProperties> localColumnPropertiesAL = new ArrayList<ColumnProperties>();
			for (int m = 0; m < importedDataTable.getColumnCount(); m++) {
				ColumnProperties clmPropObj = new ColumnProperties();
				localColumnPropertiesAL.add(clmPropObj);
			}
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
					.setColumnPropertiesArrayList(localColumnPropertiesAL);

			// create a new unit builder data object also so that null point
			// exception does not arise
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
				UnitBuilderData localUnitBuilderData = new UnitBuilderData();
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
						.getColumnPropertiesArrayList().get(i)
						.setUnitBuilderDataObj(localUnitBuilderData);
			}

			// now bother about the data type.
			for (int j = 0; j < importedDataTable.getColumnCount(); j++) {
				for (int i = appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(sheetIndex).getStartRow() - 1; i < importedDataTable
						.getRowCount(); i++) {
					c = data[i + 1][j].toString();

					// determining the data type of the column
					try {
						Double.parseDouble(c);
						
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
								.getWorkSheetObjectsAL().get(sheetIndex)
								.getColumnPropertiesArrayList().get(j)
								.setDataType("Numeric");
					} catch (Exception e) {
						try {
							Integer.parseInt(c);
							System.out.println("Integer");
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
									.getWorkSheetObjectsAL().get(sheetIndex)
									.getColumnPropertiesArrayList().get(j)
									.setDataType("Numeric");

						} catch (Exception e1) {
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
									.getWorkSheetObjectsAL().get(sheetIndex)
									.getColumnPropertiesArrayList().get(j)
									.setDataType("Text");
							System.out.println("text ");
							break;
						}

					}

				}
			}
		}
	}

	public static void main(String args[]) {

	}
}
