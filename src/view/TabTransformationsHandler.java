package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Common.ThousandsSeperator;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class TabTransformationsHandler {
	public static TabTransformationsHandler TABLE_TRANS = null;

	public static TabTransformationsHandler createTableTransformationInstance() {
		if (TABLE_TRANS == null) {
			TABLE_TRANS = new TabTransformationsHandler();
		}
		return TABLE_TRANS;
	}

	int xColIndex;
	int yColIndex;

	private final class TransformationTabModelSelecHandler implements
			TableModelListener {
		@Override
		public void tableChanged(TableModelEvent arg0) {

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			String[][] tempData = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex());

			int columnChanged = transformationTable.getSelectedColumn();
			int rowChanged = transformationTable.getSelectedRow();
			System.out.println("The row  is " + rowChanged
					+ " and the column changed is " + columnChanged);

			if (columnChanged == 1)
				xColIndex = rowChanged;
			else if (columnChanged == 2)
				yColIndex = rowChanged;

			String[][] data = extractDataFromAppInfo();

		}

	}

	JFrame dataTransFrame;
	JPanel dataTranspan;
	JPanel dataTranspan2;
	JPanel dataTranspan21;
	MyJTable transformationTable;
	JButton button;
	Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	JComboBox cbOperator = null;
	JComboBox cbTransformationType = null;
	JTextField tfNewColName;
	String tableName;
	DefaultMutableTreeNode node;
	String nodeName;
	String analysisType;
	

	String[] extractColumnData(String[][] data, int colIdx) {
		String[] colData = new String[data.length];

		for (int i = 0; i < colData.length; i++) {
			colData[i] = data[i][colIdx];
		}

		return colData;
	}

	private String[][] extractDataFromAppInfo() {
		String[][] data;
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getHasHeader() == true) {
			data = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getDataStructuresArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex());
			String[][] newData = new String[data.length - 1][data[0].length];
			for (int i = 0; i < newData.length; i++) {
				for (int j = 0; j < newData[i].length; j++) {
					newData[i][j] = data[i + 1][j];
				}
			}
			data = null;
			data = newData;

		} else {

			/*
			 * data =
			 * appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getSelectedWorkBookIndex
			 * ()).getDataStructuresArrayList().get(
			 * appInfo.getWorkBooksArrayList
			 * ().get(appInfo.getSelectedWorkBookIndex
			 * ()).getSelectedSheetIndex()); String[][] newData = new
			 * String[data.length - 1][data[0].length]; for (int i = 0; i <
			 * newData.length; i++) { for (int j = 0; j < newData[i].length;
			 * j++) { newData[i][j] = data[i + 1][j]; } } data = null; data =
			 * newData;
			 */

			data = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getDataStructuresArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex());
		}
		return data;
	}

	String detNewOperatedValue(String val1, String val2, int opType) {
		String valNew = null;

		switch (opType) {
		case 0:
			double value1 = Double.parseDouble(val1);
			double value2 = Double.parseDouble(val2);
			valNew = (value1 - value2) + "";
			break;
		case 1:
			value1 = Double.parseDouble(val1);
			value2 = Double.parseDouble(val2);
			valNew = (value1 + value2) + "";
			break;
		case 2:
			value1 = Double.parseDouble(val1);
			value2 = Double.parseDouble(val2);
			valNew = (value1 / value2) + "";
			break;
		case 3:
			value1 = Double.parseDouble(val1);
			value2 = Double.parseDouble(val2);
			valNew = (value1 * value2) + "";
			break;
		default:
			break;
		}

		return valNew;
	}

	String[][] appendColumns(String[][] ipData, String[] appColData) {
		int rCount = ipData.length;
		int cCount = ipData[0].length;
		int aRCount = appColData.length;
		int aCCount = 1;

		String[][] resData = new String[rCount][cCount + aCCount];

		for (int r = 0; r < rCount; r++) {
			for (int c = 0; c < cCount; c++) {
				resData[r][c] = ipData[r][c];
			}
			resData[r][cCount] = appColData[r];
		}

		return resData;
	}

	String[][] appendColumns(String[][] ipData, String[][] appColData) {
		int rCount = ipData.length;
		int cCount = ipData[0].length;
		int aRCount = appColData.length;
		int aCCount = appColData[0].length;

		String[][] resData = new String[rCount][cCount + aCCount];

		for (int r = 0; r < rCount; r++) {
			for (int c = 0; c < cCount; c++) {
				resData[r][c] = ipData[r][c];
			}
			int counter = cCount;
			for (int c1 = 0; c1 < aCCount; c1++) {
				resData[r][counter] = appColData[r][c1];
				counter++;
			}
		}

		return resData;
	}

	private String[][] appendRows(String[][] ipData, String[][] appRowData) {
		int rCount = ipData.length;
		int cCount = ipData[0].length;
		int aRCount = appRowData.length;
		int aCCount = appRowData[0].length;

		String[][] resData = new String[rCount + aRCount][cCount];

		for (int c = 0; c < aCCount; c++) {
			for (int r = 0; r < rCount; r++) {
				resData[r][c] = ipData[r][c];
			}
			int counter = rCount;
			for (int r1 = 0; r1 < aRCount; r1++) {
				resData[counter][c] = appRowData[r1][c];
				counter++;
			}
		}

		return resData;
	}

	private String[][] appendRows(String[][] ipData, String[] appRowData) {
		int rCount = ipData.length;
		int cCount = ipData[0].length;
		int aRCount = appRowData.length;
		int aCCount = appRowData.length;

		String[][] resData = new String[rCount + aRCount][cCount];

		for (int c = 0; c < aCCount; c++) {
			for (int r = 0; r < rCount; r++) {
				resData[r][c] = ipData[r][c];
			}
			int counter = rCount;
			for (int r1 = 0; r1 < aRCount; r1++) {
				resData[counter][c] = appRowData[c];
				counter++;
			}
		}

		return resData;
	}

	private String[] computeNewColumnData(int opType, String[] col1Data,
			String[] col2Data) {
		String[] derivedColData = new String[col1Data.length];
		for (int i = 0; i < col2Data.length; i++) {

			derivedColData[i] = detNewOperatedValue(col1Data[i], col2Data[i],
					opType);

		}
		return derivedColData;
	}

	void updateResTableWithData(String[][] newData, String[] headers) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		JTable tblImport = ImportedDataSheet.createImportedDataSheetInstance().importedDataTable;
		DefaultTableModel dtModel = (DefaultTableModel) tblImport.getModel();

		int rCount = dtModel.getRowCount();
		int cCount = dtModel.getColumnCount();

		for (int i = rCount; i > 0; i--) {
			dtModel.removeRow(i - 1);
		}

		for (int i = cCount; i < headers.length; i++) {
			dtModel.addColumn(headers[i]);
		}
		System.out.println(tblImport.getRowCount());
		for (int i = 0; i < newData.length; i++) {
			int rowCount = tblImport.getRowCount();
			dtModel.insertRow(rowCount, newData[i]);
		}

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getHasHeader() == true) {
			for (int j = 0; j < headers.length; j++) {
				TableColumnModel tcm = tblImport.getColumnModel();
				TableColumn tc = tcm.getColumn(j);
				tc.setHeaderValue(headers[j]);
			}
		} else {
			for (int j = 0; j < headers.length; j++) {
				TableColumnModel tcm = tblImport.getColumnModel();
				TableColumn tc = tcm.getColumn(j);
				tc.setHeaderValue("column " + (j + 1));
			}
		}

	}

	private void handleTableTransformation(int transType, int operatorId,
			String newColumnName) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		System.out.println();
		String[] newColumn;
		if (transType == 0) {
			String[][] data = extractDataFromAppInfo();
			String[] columnX = extractColumnData(data, xColIndex);
			String[] columnY = extractColumnData(data, yColIndex);

			String[] transColumn = computeNewColumnData(operatorId, columnX,
					columnY);

			String[][] appData = appendColumns(data, transColumn);

			String[][] dataWithHdr = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getDataStructuresArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex());

			String[] transColumnWithHdr = new String[transColumn.length + 1];

			transColumnWithHdr[0] = newColumnName;

			for (int i = 1; i < transColumnWithHdr.length; i++) {
				transColumnWithHdr[i] = transColumn[i - 1];
			}

			dataWithHdr = appendColumns(dataWithHdr, transColumnWithHdr);

			String[] headers = dataWithHdr[0];

			updateResTableWithData(appData, headers);

			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getHasHeader() == true) {

				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).setElementInDS(
						dataWithHdr,
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex());
				IMPSheetCoreFunctions.createIMPSheetCoreFuncInstance()
						.reflectInOriginalAppInfo(dataWithHdr);

			} else {
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).setElementInDS(
						appData,
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex());
				IMPSheetCoreFunctions.createIMPSheetCoreFuncInstance()
						.reflectInOriginalAppInfo(appData);

			}
		} else if (transType == 1) {
			// / Baseline Transformations - to be handled here.
		} else {
			// / Custom Transformations - to be handled here.
		}
	}

	public void createGUIForTableTransformation() {

		UIDefaults ui = UIManager.getLookAndFeel().getDefaults();
		UIManager.put("RadioButton.focus", ui.getColor("control"));
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String[][] data1 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex());

		String[] colName = new String[data1[0].length];
		TitledBorder titledBorder = BorderFactory.createTitledBorder(null,
				"Options", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
				componentOptionseFont, Color.black);

		dataTransFrame = new JFrame();
		dataTransFrame.setSize(500, 500);
		dataTransFrame.setVisible(true);

		dataTransFrame.setLocationRelativeTo(null);

		dataTransFrame.setLayout(new GridLayout(2, 1));
		dataTranspan = new JPanel();
		dataTranspan2 = new JPanel();

		dataTranspan.setVisible(true);

		// dataTranspan.setBorder(titledBorder);
		dataTranspan2.setBorder(titledBorder);

		dataTranspan2.setVisible(true);
		dataTranspan21 = new JPanel();
		JPanel dataTranspan22 = new JPanel();
		JPanel dataTranspan23 = new JPanel();
		JPanel dataTranspan24 = new JPanel();
		JPanel dataTranspan25 = new JPanel();
		/*
		 * dataTranspan21.setSize(dataTranspan2.WIDTH/3,dataTranspan2.HEIGHT);
		 * dataTranspan22.setSize(dataTranspan2.WIDTH/3,dataTranspan2.HEIGHT);
		 * dataTranspan23.setSize(dataTranspan2.WIDTH/3,dataTranspan2.HEIGHT);
		 */
		dataTranspan2.setLayout(new GridLayout(1, 5));

		dataTranspan21.setLayout(new GridLayout(7, 1));
		dataTranspan22.setLayout(new GridLayout(7, 1));
		dataTranspan21.add(new Label(""));
		dataTranspan21.add(new Label("Transformation Type"));
		cbTransformationType = new JComboBox();
		cbTransformationType.setSize(50, 20);
		cbTransformationType.setBackground(Color.white);
		cbTransformationType.addItem("Arithmetic");
		/*
		 * TransformationTypeComboBox.addItem("Baseline");
		 * TransformationTypeComboBox.addItem("Custom");
		 */
		dataTranspan21.add(cbTransformationType);
		dataTranspan21.add(new Label(""));
		dataTranspan21.add(new Label("Transformation"));

		cbOperator = new JComboBox();
		cbOperator.setBackground(Color.white);
		cbOperator.addItem("(x-y)");
		cbOperator.addItem("(x+y)");
		cbOperator.addItem("(x/y)");
		dataTranspan21.add(cbOperator);
		dataTranspan21.add(new Label(""));

		dataTranspan22.add(new Label(""));
		dataTranspan22.add(new Label("New Column Name"));
		tfNewColName = new JTextField(12);
		dataTranspan22.add(tfNewColName);
		dataTranspan22.add(new Label(""));
		dataTranspan22.add(new Label(""));
		button = new JButton("Append Column");
		dataTranspan22.add(button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// to take the name of transformation type
				// to take the name of arithmetic operator
				// to take the name of new column

				DDViewLayer.createViewLayerInstance().isTblTransInvoked = true;

				int operatorId = cbOperator.getSelectedIndex();
				int transType = cbTransformationType.getSelectedIndex();
				String newColumnName = tfNewColName.getText();

				handleTableTransformation(transType, operatorId, newColumnName);

				DDViewLayer.createViewLayerInstance().isTblTransInvoked = false;

			}
		});
		dataTranspan22.add(new Label(""));
		dataTranspan21.setVisible(true);
		dataTranspan22.setVisible(true);
		dataTranspan2.add(dataTranspan21);
		dataTranspan2.add(dataTranspan24);
		dataTranspan2.add(dataTranspan22);
		dataTranspan2.add(dataTranspan25);

		// dataTranspan2.add(dataTranspan23);
		/*
		 * dataTranspan2.add(dataTranspan22); dataTranspan2.add(dataTranspan23);
		 */
		// dataTranspan2.setLayout();

		appInfo = DisplayContents.createAppInfoInstance();
		for (int i = 0; i < data1[0].length; i++) {
			colName[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName();

		}

		Object data[][] = new Object[colName.length][3];
		Object col[] = { "Column Name", "X", "Y" };
		int[] cIdx = { 0 };

		for (int i = 0; i < colName.length; i++) {
			data[i][0] = colName[i];
			data[i][1] = new JRadioButton();
			data[i][2] = new JRadioButton();

			ButtonGroup group1 = new ButtonGroup();
			group1.add((JRadioButton) data[i][1]);
			group1.add((JRadioButton) data[i][2]);

		}

		DefaultTableModel model = new DefaultTableModel(data, col);

		transformationTable = new MyJTable(model, cIdx) {
			public void tableChanged(TableModelEvent e) {
				super.tableChanged(e);
				repaint();
			}
		};

		transformationTable.getColumn("X").setCellRenderer(
				new RadioButtonRenderer());
		transformationTable.getColumn("Y").setCellRenderer(
				new RadioButtonRenderer());
		transformationTable.getColumn("X").setCellEditor(
				new RadioButtonEditor(new JCheckBox()));
		transformationTable.getColumn("Y").setCellEditor(
				new RadioButtonEditor(new JCheckBox()));

		/*
		 * transformationTable .addFocusListener(new
		 * DiffEqunLibModelTableActionEventHandler());
		 */

		JScrollPane libraryModelScrollPane = new JScrollPane(
				transformationTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// dataTranspan2.add(new Label("Transformation Type"));
		transformationTable.getModel().addTableModelListener(
				new TransformationTabModelSelecHandler());

		dataTranspan.add(libraryModelScrollPane);
		dataTransFrame.getContentPane().add(dataTranspan);
		dataTransFrame.getContentPane().add(dataTranspan2);
		dataTransFrame.setVisible(true);

		dataTranspan21.setSize(dataTranspan21.getSize());
		dataTransFrame.repaint();
		dataTransFrame.validate();

	}

	void baseLineFunc() {
		
		
	}

	void availableVarListSelectionHandler() {


		TableTransformations.createTableTrasGUIInst().SortAndAvailableButton
				.setIcon(TableTransformations.createTableTrasGUIInst().toLeft);
		TableTransformations.createTableTrasGUIInst().xAndAvailableButton
				.setIcon(TableTransformations.createTableTrasGUIInst().toLeft);
		TableTransformations.createTableTrasGUIInst().YAndAvailableButton
				.setIcon(TableTransformations.createTableTrasGUIInst().toLeft);
		TableTransformations.createTableTrasGUIInst().isAvailableToSort = true;
		TableTransformations.createTableTrasGUIInst().isSortToAvailable = false;
		TableTransformations.createTableTrasGUIInst().isAvailableToX = true;
		TableTransformations.createTableTrasGUIInst().isXToAvailable = false;
		TableTransformations.createTableTrasGUIInst().isAvailableToY = true;
		TableTransformations.createTableTrasGUIInst().isYToAvailable = false;

		
	}

	void availableToSortButtonHandler() {


		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// storing the previous sort variables
		TableTransformations.createTableTrasGUIInst().previousSortVariables = new String[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size()];
		for (int i = 0; i < TableTransformations.createTableTrasGUIInst().previousSortVariables.length; i++) {
			TableTransformations.createTableTrasGUIInst().previousSortVariables[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i);
		}

		if (TableTransformations.createTableTrasGUIInst().isAvailableToSort == true) {

			int selectedIndex = TableTransformations.createTableTrasGUIInst().availableColumnsList
					.getSelectedIndex();
			
			String moving = (String) TableTransformations.createTableTrasGUIInst().availableColumnsList
					.getSelectedValue();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())

					.getTtInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getSortVariablesListVector()
					.add(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getTtInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size(),
							moving);
			TableTransformations.createTableTrasGUIInst().sortVariableListModel
					.add(
							TableTransformations.createTableTrasGUIInst().sortVariableListModel
									.getSize(), moving);
			TableTransformations.createTableTrasGUIInst().availableVariablesListmodel
					.remove(selectedIndex);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedIndex);

		}

		if (TableTransformations.createTableTrasGUIInst().isSortToAvailable == true) {

			String moving = (String) TableTransformations.createTableTrasGUIInst().sortVariablesList
					.getSelectedValue();
			int selectedIndex = TableTransformations.createTableTrasGUIInst().sortVariablesList
					.getSelectedIndex();
			TableTransformations.createTableTrasGUIInst().availableVariablesListmodel
					.add(
							TableTransformations.createTableTrasGUIInst().availableVariablesListmodel
									.getSize(), moving);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())

					.getTtInfo()
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

									.getTtInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().remove(selectedIndex);
			TableTransformations.createTableTrasGUIInst().sortVariableListModel
					.remove(selectedIndex);

		}
	
		
	}

	void availableToXButtonHandler() {


		if ((TableTransformations.createTableTrasGUIInst().isAvailableToX == true)) {

			availToXVarAction();
		}

		if (TableTransformations.createTableTrasGUIInst().isXToAvailable) {

			xToAvailAction();
		}
	
		
	}

	private void xToAvailAction() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		TableTransformations.createTableTrasGUIInst().previousXVariable = TableTransformations
				.createTableTrasGUIInst().xVariableTextField.getText();
		;
		String moving = TableTransformations.createTableTrasGUIInst().xVariableTextField
				.getText();
		TableTransformations.createTableTrasGUIInst().xVariableTextField
				.setText(null);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getTtInfo()
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
								.getTtInfo().getProcessingInputs()
								.getMappingDataObj()
								.getAvailableColumnsVector().size(), moving);
		TableTransformations.createTableTrasGUIInst().availableVariablesListmodel
				.add(
						TableTransformations.createTableTrasGUIInst().availableVariablesListmodel
								.getSize(), moving);

	}

	private void availToXVarAction() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		TableTransformations.createTableTrasGUIInst().previousXVariable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName();
		String moving = (String) TableTransformations.createTableTrasGUIInst().availableColumnsList
				.getSelectedValue();
		int selectedindex = TableTransformations.createTableTrasGUIInst().availableColumnsList
				.getSelectedIndex();

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().remove(selectedindex);

		// add the previous time column to the available columns
		// if there is a time column already present
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName() != "") {
			TableTransformations.createTableTrasGUIInst().availableVariablesListmodel
					.add(
							TableTransformations.createTableTrasGUIInst().availableVariablesListmodel
									.size(),
							TableTransformations.createTableTrasGUIInst().xVariableTextField
									.getText());
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getTtInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
							TableTransformations.createTableTrasGUIInst().xVariableTextField
									.getText());
		}

		TableTransformations.createTableTrasGUIInst().availableVariablesListmodel
				.remove(selectedindex);
		TableTransformations.createTableTrasGUIInst().xVariableTextField
				.setText(moving);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getMappingDataObj().setxColumnName(
						moving);

		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().size(); i++) {
			if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName())) {
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getTtInfo()
						.getProcessingInputs().getMappingDataObj()
						.setxColumnCorrespondinOriginalIndex(i);
			}
		}
	}

	public void sortVariableListSelectionHandler() {

		TableTransformations.createTableTrasGUIInst().SortAndAvailableButton
				.setIcon(TableTransformations.createTableTrasGUIInst().toRight);
		TableTransformations.createTableTrasGUIInst().isSortToAvailable = true;
		TableTransformations.createTableTrasGUIInst().isAvailableToSort = false;
		TableTransformations.createTableTrasGUIInst().isAvailableToY = true;
		TableTransformations.createTableTrasGUIInst().isYToAvailable = false;
		TableTransformations.createTableTrasGUIInst().isAvailableToX = true;
		TableTransformations.createTableTrasGUIInst().isXToAvailable = false;
	}
	
	void yVarTextFieldHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {
		TableTransformations.createTableTrasGUIInst().YAndAvailableButton
				.setIcon(TableTransformations.createTableTrasGUIInst().toRight);
		TableTransformations.createTableTrasGUIInst().isYToAvailable = true;
		TableTransformations.createTableTrasGUIInst().isAvailableToY = false;

	}

	void xVarTextFieldHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {
		// TODO Auto

		TableTransformations.createTableTrasGUIInst().xAndAvailableButton
				.setIcon(TableTransformations.createTableTrasGUIInst().toRight);
		TableTransformations.createTableTrasGUIInst().isXToAvailable = true;
		TableTransformations.createTableTrasGUIInst().isAvailableToX = false;

	}

	public void availableToYButtonHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (TableTransformations.createTableTrasGUIInst().isAvailableToY == true) {

			availToYvarAction();
		}

		if (TableTransformations.createTableTrasGUIInst().isYToAvailable == true) {

			YvarToAvaialAction();
		}

		// default and preffered units preperation based on the model type

	}

	private void YvarToAvaialAction() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = TableTransformations.createTableTrasGUIInst().yVariableTextField
				.getText();
		TableTransformations.createTableTrasGUIInst().availableVariablesListmodel
				.add(
						TableTransformations.createTableTrasGUIInst().availableVariablesListmodel
								.size(), moving);
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().add(moving);
		TableTransformations.createTableTrasGUIInst().yVariableTextField
				.setText("");
		TableTransformations.createTableTrasGUIInst().previousYColumnName = moving;

	}

	private void availToYvarAction() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = (String) TableTransformations.createTableTrasGUIInst().availableColumnsList
				.getSelectedValue();

		TableTransformations.createTableTrasGUIInst().previousYColumnName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName();

		int selectedIndex = TableTransformations.createTableTrasGUIInst().availableColumnsList
				.getSelectedIndex();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().remove(selectedIndex);
		TableTransformations.createTableTrasGUIInst().availableVariablesListmodel
				.remove(selectedIndex);

		// if the y text field does not contain an empty string
		// then move it to the available variables
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName() != "") {
			TableTransformations.createTableTrasGUIInst().availableVariablesListmodel
					.add(
							TableTransformations.createTableTrasGUIInst().availableVariablesListmodel
									.size(),
							TableTransformations.createTableTrasGUIInst().yVariableTextField
									.getText());
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getTtInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
							TableTransformations.createTableTrasGUIInst().yVariableTextField
									.getText());
			TableTransformations.createTableTrasGUIInst().previousYColumnName = TableTransformations.createTableTrasGUIInst().yVariableTextField.getText();
		}

		// set the y column name
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getMappingDataObj().setYcolumnName(
						moving);

		// asign the new ycolumn name to the ytext field
		TableTransformations.createTableTrasGUIInst().yVariableTextField
				.setText(moving);

		// setting y column corresponding original index

		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().size(); i++) {
			if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName())) {
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getTtInfo()
						.getProcessingInputs().getMappingDataObj()
						.setyColumnCorrespondinOriginalIndex(i);
			}
		}

	}

	public void includeAsInput() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String plotName = TableTransformations.createTableTrasGUIInst().resultsAvailTree
				.getSelectionPath().getLastPathComponent().toString();
		String[] pathSplits = TableTransformations.createTableTrasGUIInst().resultsAvailTree
				.getSelectionPath().toString().split(",");

		String[] plotNameSplits = plotName.split(" ");
		String[][] sheeetData = null;

		
		
		if (TableTransformations.createTableTrasGUIInst().resultsAvailTree
				.getSelectionPath().getParentPath().toString().contains(
						"Results")) {
			int chosenOption = JOptionPane.showConfirmDialog(TableTransformations.createTableTrasGUIInst().resultsDispIF,
					"Use as a input for a different analysis", "Conform",
					JOptionPane.YES_OPTION, 0);

			if (chosenOption == 0) {

				// get the selected node's corresponding table data
				sheeetData = getCorespondingSheetData(sheeetData, pathSplits);

				// add the data structure to array list of data structures in
				// ApplicationInfo
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().add(new WorkSheetsInfo());
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getDataStructuresArrayList().add(sheeetData);

				String sheetName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getSheetName();

				analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getAnalysisType();
				// set sheetName
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL().size() - 1)
						.setSheetName(
								sheetName + "_" + analysisType + "_"
										+ tableName);

				// set selected row as 1
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL().size() - 1)
						.setStartRow(1);

				// add a node to project explorer tree
				updateProjectExplorer();

				// set default column properties
				setDefaultColumnProperties();
			}
		} else {

		}

	}
	private String[][] getCorespondingSheetData(String[][] sheeetData,
			String[] pathSplits) {

		if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Transformed sheet]")) {
			sheeetData = copySelectedSheetData(TToutputGeneration
					.createttOutGenInst().outputTable);
			tableName = "Transformed Sheet";
		}
		return sheeetData;

	}

	private String[][] copySelectedSheetData(JTable table) {

		String[][] data = new String[table.getRowCount() + 1][table
				.getColumnCount()];
		TableColumnModel tm = table.getColumnModel();

		// include header as 1st row in data structure
		for (int j = 0; j < data[0].length; j++) {
			TableColumn tc = tm.getColumn(j);
			data[0][j] = tc.getHeaderValue().toString();
		}

		// copy the table data into the remaining rows of data.
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {

				try {
					data[i][j] = ThousandsSeperator.createThouSepInst()
							.removeComma(table.getValueAt(i - 1, j).toString());
				} catch (NullPointerException ne) {
					data[i][j] = "";
				}

			}
		}

		return data;
	}
	
	private void setDefaultColumnProperties() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL().size() - 1)
				.getColumnPropertiesArrayList().add(new ColumnProperties());

	}
	
	private void updateProjectExplorer() {
		boolean hasNode = checkIfNodePresent();
		performNodeCreationInPE(hasNode);

	}

	private void performNodeCreationInPE(boolean hasNode) {
		// the analysis node is present we just have to add another child to it.
		DefaultMutableTreeNode analysisNode;
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (hasNode == true) {

		} else if (hasNode == false) {

			analysisNode = DDViewLayer.createPEInstance().workSpace.getNextNode();

			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();

			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
					nodeName);
			analysisNode.add(newNode);

			model.reload();
		}

	}

	private boolean checkIfNodePresent() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String sheetName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getSheetName();
		Enumeration en = DDViewLayer.createPEInstance().workSpace
				.breadthFirstEnumeration();
		boolean hasNode = false;
		// iterate through the enumeration
		while (en.hasMoreElements()) {
			// get the node
			node = (DefaultMutableTreeNode) en.nextElement();

			// match the string with the user-object of the node
			nodeName = sheetName + "_" + analysisType + "_" + tableName;
			if ((node.getUserObject().toString()).equals(nodeName)) {
				hasNode = true;
				break;
			} else {
			}
		}

		return hasNode;
	}
	
	
	double[] getcorrespondingProfileConc(int subjectIndex) {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int subObs = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getProfileInfoObj().getSubject_obs()[subjectIndex];
		int startIndex = 0;

		int subObsTemp = 0;
		for (int i = 0; i < subjectIndex; i++) {
			subObsTemp = subObsTemp
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getTtInfo().getProcessingInputs()
							.getProfileInfoObj().getSubject_obs()[i];

			startIndex = subObsTemp;
		}

		double[] conc = new double[subObs];
		for (int i = 0; i < subObs; i++) {
			conc[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getConcForAllProfile()[startIndex++];
		}

		return conc;
	}

	double[] getCorrespondingProfileTime(int subjectIndex) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int subObs = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getProfileInfoObj().getSubject_obs()[subjectIndex];
		int startIndex = 0;

		int subObsTemp = 0;
		for (int i = 0; i < subjectIndex; i++) {
			subObsTemp = subObsTemp
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getTtInfo().getProcessingInputs()
							.getProfileInfoObj().getSubject_obs()[i];

			startIndex = subObsTemp;
		}

		double[] time = new double[subObs];
		for (int i = 0; i < subObs; i++) {
			time[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getTimeForAllProfile()[startIndex++];
		}

		return time;
	}

	public void availableCompHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		String[] pathSplits = TableTransformations.createTableTrasGUIInst().availCompTree
				.getSelectionPath().toString().split(",");

		if (TableTransformations.createTableTrasGUIInst().availCompTree
				.getMinSelectionRow() == 0) {
			PreviewSelectedSheet.createpreviewShtInstance().showPreview();
		} else if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Mapping]")) {

			TableTransformations.createTableTrasGUIInst().mappingIF
					.moveToFront();

		}

	}

	
}
