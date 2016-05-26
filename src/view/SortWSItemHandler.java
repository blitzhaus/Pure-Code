package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

class ComboBoxTableModel extends AbstractTableModel {
	// Implementation of TableModel interface
	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	public Object getValueAt(int row, int column) {
		return data[row][column];
	}

	public Class getColumnClass(int column) {
		return (data[0][column]).getClass();
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public boolean isCellEditable(int row, int column) {
		return column == 1;
	}

	public void setValueAt(Object value, int row, int column) {
		if (isValidValue(value)) {
			data[row][column] = value;
			fireTableRowsUpdated(row, row);
		}
	}

	// Extra public methods
	public String[] getValidStates() {
		return validStates;
	}
	
	public void setFirstColumnData(String[] colData)
	{
		data = new Object[colData.length][2];
		for (int i = 0; i < colData.length; i++) {
			data[i][0] = colData[i];
			data[i][1] = "None";
		}
	}

	// Protected methods
	protected boolean isValidValue(Object value) {
		if (value instanceof String) {
			String sValue = (String) value;

			for (int i = 0; i < validStates.length; i++) {
				if (sValue.equals(validStates[i])) {
					return true;
				}
			}
		}

		return false;
	}

	int COLUMN_COUNT = 2;

	String[] validStates = { "Ascending",
			"Descending" };

	Object[][] data = new Object[][] {
			{ "Core Java Volume 1", validStates[0] },
			{ "Core Java Volume 2", validStates[0] },
			{ "Core Web Programming", validStates[0] },
			{ "Core Visual Basic 5", validStates[0] },
			{ "Core Java Foundation Classes", validStates[0] } };

	String[] columnNames = { "Column Name", "Sort Direction" };

}



public class SortWSItemHandler extends JFrame{
	JTable table;
	
	SelectButtonGroup[] btnGroup;
	
	public final static String OK   = "ok";
	public final static String CANCEL   = "cancel";
	public final static String CLEARSORT   = "clrsort";
	public final static String APPLY   = "apply";
	private static final String UPARROW = "uparrow";
	private static final String DOWNARROW = "downarrow";
	
	public JRadioButton[] radBtns;
	
	SortWSItemHandler()
	{
		getContentPane().setLayout(null);
		
		ComboBoxTableModel cbTblModel = new ComboBoxTableModel();
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		final String[] colNames = new String[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
		               				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
		            				.getColumnPropertiesArrayList().size()];
		for (int i = 0; i < colNames.length; i++) {
			String colName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName();
			
			colNames[i] = colName;
		}
		
		cbTblModel.setFirstColumnData(colNames);
		
			
		table = new JTable(cbTblModel);
		
		JPanel colMapperPanel = createColumnMapperPanel();
		
		 // Create the combo box editor
	    JComboBox comboBox = new JComboBox(cbTblModel.getValidStates());
	    comboBox.setEditable(true);
	    DefaultCellEditor editor = new DefaultCellEditor(comboBox);

	    // Assign the editor to the second column
	    TableColumnModel tcm = table.getColumnModel();
	    tcm.getColumn(1).setCellEditor(editor);

	    // Set column widths
	    tcm.getColumn(0).setPreferredWidth(200);
	    tcm.getColumn(1).setPreferredWidth(100);
	    
	    final JTable table1 = createColumnMapperTable();
	    
	  	JScrollPane scrollPane = new JScrollPane(table1,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 298, 258);
				
		getContentPane().add(scrollPane);
				
		Icon img1 = new ImageIcon("./downarrow.gif");
		JButton btnDownArrow = new JButton(img1);
		btnDownArrow.setBounds(343, 11, 50, 40);
		btnDownArrow.setActionCommand(DOWNARROW);
		getContentPane().add(btnDownArrow);
		
		Icon img2 = new ImageIcon("./uparrow.gif");
		JButton btnUpArrow = new JButton(img2);
		btnUpArrow.setActionCommand(UPARROW);
		
		btnUpArrow.setBounds(343, 66, 50, 40);
		getContentPane().add(btnUpArrow);
		
		JButton btnClrSort = new JButton("Clear_Sort");
		btnClrSort.setActionCommand(CLEARSORT);
		btnClrSort.setBounds(10, 292, 99, 23);
		getContentPane().add(btnClrSort);
		
		JButton btnOK = new JButton("OK");
		btnOK.setBounds(119, 292, 89, 23);
		
		btnOK.setActionCommand(OK);
		getContentPane().add(btnOK);
		
		JButton btncancel = new JButton("Cancel");
		btncancel.setActionCommand(CANCEL);
		btncancel.setBounds(218, 292, 89, 23);
		getContentPane().add(btncancel);
		
		JButton btnApply = new JButton("Apply");
		btnApply.setActionCommand(APPLY);
		btnApply.setBounds(314, 292, 89, 23);
		getContentPane().add(btnApply);
		
		ActionListener l = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (e.getActionCommand() == OK)
				{
					System.out.println("Ok clicked..");
					
					Vector ascVecs = new Vector();
					Vector descVecs = new Vector();
					
					//btnGroup
					
					for (int i = 0; i < btnGroup.length; i++) {
						int selectedIndex = btnGroup[i].getSelectedIndex();
						System.out.println(i+" th row selected index..."+selectedIndex);
						
						//colNames[i] = 0+"";
						
						System.out.println("Column Name.."+colNames[i]);
						
						if (selectedIndex == 1)
						{
							ascVecs.add(i+"");
							System.out.println("Ascending..");
						}
						else if (selectedIndex == 2)
						{
							System.out.println("Descending..");
							descVecs.add(i+"");
						}
					}
					
					String[][] sortedData = doMultilevelSort(ascVecs, descVecs);
					
					printMatrix("sortedData.", sortedData,
							sortedData.length, sortedData[0].length);
					
					dispose();
				}
				else if (e.getActionCommand() == CANCEL)
				{
					System.out.println("CANCEL clicked..");
					dispose();
				}
				else if (e.getActionCommand() == CLEARSORT)
				{
					System.out.println("CLEARSORT clicked..");
					for (int i = 0; i < btnGroup.length; i++) {
						btnGroup[i].setSelectedIndex(0);
					}
					
					table = createColMapTableForCS(btnGroup);
					
					JScrollPane scrollPane = new JScrollPane(table,
							ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
					scrollPane.setBounds(10, 11, 298, 258);
							
					getContentPane().add(scrollPane);
					
				}
				else if (e.getActionCommand() == APPLY)
				{
					System.out.println("Apply clicked..");
					
					Vector ascVecs = new Vector();
					Vector descVecs = new Vector();
					
					for (int i = 0; i < btnGroup.length; i++) {
						int selectedIndex = btnGroup[i].getSelectedIndex();
						System.out.println(i+" th row selected index..."+selectedIndex);
						
						if (selectedIndex == 1)
						{
							ascVecs.add(i+"");
							System.out.println("Ascending..");
						}
						else if (selectedIndex == 2)
						{
							System.out.println("Descending..");
							descVecs.add(i+"");
						}
					}
					
					String[][] sortedData = doMultilevelSort(ascVecs, descVecs);
					
					printMatrix("sortedData.", sortedData,
							sortedData.length, sortedData[0].length);
				}
				else if (e.getActionCommand() == UPARROW)
				{
					System.out.println("UPARROW clicked..");
					
					System.out.println("UPARROW clicked..");
					int selRow = table1.getSelectedRow();
					DefaultTableModel dtm = (DefaultTableModel)table1.getModel();
					dtm.moveRow(selRow, selRow, selRow-1);
				}
				else if (e.getActionCommand() == DOWNARROW)
				{
					System.out.println("DOWNARROW clicked..");
					int selRow = table1.getSelectedRow();
					DefaultTableModel dtm = (DefaultTableModel)table1.getModel();
					dtm.moveRow(selRow, selRow, selRow+1);
					
				}
			}

			private String[][] doMultilevelSort(Vector ascVecs, Vector descVecs) {
				String[][] data = getCurrentSelSheetData();
				MultiLevelSort mlSort = new MultiLevelSort();
				
				String[][] ascSortedData = mlSort.sortData(data, ascVecs);
			
				
				String[][] descSortedData = mlSort.sortDataDesc(ascSortedData, descVecs);
				
				return descSortedData;
			}			
		};
		
		btnOK.addActionListener(l);
		btncancel.addActionListener(l);
		btnApply.addActionListener(l);
		btnClrSort.addActionListener(l);
		btnUpArrow.addActionListener(l);
		btnDownArrow.addActionListener(l);
	}
	
	public void printMatrix(String str, String[][] matrix, int rowDim, int colDim)
	{/*
		

		for (int r = 0 ; r < rowDim; r++)
		{
			for (int c = 0 ; c < colDim; c++)
			{
				System.out.print(matrix[r][c]+"\t");
			}

			System.out.println();
		}

		System.out.println("\n");
	*/}
	
	private String[][] getCurrentSelSheetData()
	{
		String[][] data;
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		if(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getHasHeader() == true){
			data = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getDataStructuresArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex());
			String[][] newData = new String[data.length-1][data[0].length];
			for(int i=0;i<newData.length;i++){
				for(int j=0;j<newData[i].length;j++){
					newData[i][j] = data[i+1][j];
				}
			}
			data = null;
			data = newData;
			
		} else {
			 data = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getDataStructuresArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex());
		}
		
		return data;
	}
	
	
	
	JTable createColMapTableForCS(SelectButtonGroup[] buttonGroup)
	{
		btnGroup = buttonGroup;

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String[] colNames = new String[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
		               				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
		            				.getColumnPropertiesArrayList().size()];
		
		for (int i = 0; i < colNames.length; i++) {
			String colName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName();
			
			colNames[i] = colName;
		}
		
		DefaultTableModel dm = new DefaultTableModel();
		Object[][] tableObj = new Object[colNames.length][4];
		String[] header = {"Column_Name", "None", "Ascending", "Descending"};
		
		for (int i = 0; i < tableObj.length; i++) {

			tableObj[i][0] = colNames[i];

			tableObj[i][1] = new JRadioButton();
			tableObj[i][2] = new JRadioButton();
			tableObj[i][3] = new JRadioButton();

			buttonGroup[i].add((JRadioButton)tableObj[i][1]);
			buttonGroup[i].add((JRadioButton)tableObj[i][2]);
			buttonGroup[i].add((JRadioButton)tableObj[i][3]);
		}
		
		dm.setDataVector(tableObj, header);
		JTable table = new JTable(dm){
		      public void tableChanged(TableModelEvent e) {
		          super.tableChanged(e);
		          repaint();
		        }
		      };
				
		table.getColumn("None").setCellRenderer(
	            new RadioButtonRenderer());
	   
		table.getColumn("Ascending").setCellRenderer(
            new RadioButtonRenderer());
   
		table.getColumn("Descending").setCellRenderer(
            new RadioButtonRenderer());
 	   
	    //this is how the selection of combo box is possible
		table.getColumn("None").setCellEditor(
	        new RadioButtonEditor(new JCheckBox()));
		table.getColumn("Ascending").setCellEditor(
	        new RadioButtonEditor(new JCheckBox()));
		table.getColumn("Descending").setCellEditor(
	        new RadioButtonEditor(new JCheckBox()));
		
		return table;
	}
	
	public JTable createColumnMapperTable()
	{
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String[] colNames = new String[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
		               				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
		            				.getColumnPropertiesArrayList().size()];
		
		for (int i = 0; i < colNames.length; i++) {
			String colName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName();
			
			colNames[i] = colName;
		}
		
		DefaultTableModel dm = new DefaultTableModel();
		Object[][] tableObj = new Object[colNames.length][4];
		String[] header = {"Column_Name", "None", "Ascending", "Descending"};
		
		btnGroup = new SelectButtonGroup[tableObj.length];
		
		for (int i = 0; i < tableObj.length; i++) {

			tableObj[i][0] = colNames[i];

			tableObj[i][1] = new JRadioButton();
			tableObj[i][2] = new JRadioButton();
			tableObj[i][3] = new JRadioButton();

			btnGroup[i] = new SelectButtonGroup();
			btnGroup[i].add((JRadioButton)tableObj[i][1]);
			btnGroup[i].add((JRadioButton)tableObj[i][2]);
			btnGroup[i].add((JRadioButton)tableObj[i][3]);
		}
		
		dm.setDataVector(tableObj, header);
		JTable table = new JTable(dm){
		      public void tableChanged(TableModelEvent e) {
		          super.tableChanged(e);
		          repaint();
		        }
		      };
		
		
		
		table.getColumn("None").setCellRenderer(
	            new RadioButtonRenderer());
	   
		table.getColumn("Ascending").setCellRenderer(
            new RadioButtonRenderer());
   
		table.getColumn("Descending").setCellRenderer(
            new RadioButtonRenderer());
 	   
	    //this is how the selection of combo box is possible
		table.getColumn("None").setCellEditor(
	        new RadioButtonEditor(new JCheckBox()));
		table.getColumn("Ascending").setCellEditor(
	        new RadioButtonEditor(new JCheckBox()));
		table.getColumn("Descending").setCellEditor(
	        new RadioButtonEditor(new JCheckBox()));
		
		return table;
				
	}

	
	
	public JPanel createColumnMapperPanel()
	{
		TitledBorder titledBorder2 = BorderFactory.createTitledBorder("Sort Worksheet:");
		JPanel nPanel2 = new JPanel();
		nPanel2.setBorder(titledBorder2);
		int gridCols = 4;
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String[] colNames = new String[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
		               				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
		            				.getColumnPropertiesArrayList().size()];
		
		String[] headers = colNames;
		int gridRows = colNames.length;
		nPanel2.setLayout(new GridLayout(gridRows,gridCols));
		JLabel l00 = new JLabel("");
		nPanel2.add(l00);
		JLabel l01 = new JLabel("None");
		nPanel2.add(l01);
		JLabel l02 = new JLabel("Ascending");
		nPanel2.add(l02);
		JLabel l03 = new JLabel("Descending");
		nPanel2.add(l03);
		
		
		radBtns = new JRadioButton[(gridRows)*(gridCols)];
		
		int counter = 0;

		for(int i = 0; i < gridRows; i++)
		{
			ButtonGroup group = new ButtonGroup();
			for(int j = 0; j < gridCols; j++)
			{
				if (j==0)
				{
					JLabel lbl = new JLabel(headers[i]);
					nPanel2.add(lbl);
				}
				else
				{
					radBtns[counter] = new JRadioButton();
					//radBtns[counter].addItemListener(l);
					group.add(radBtns[counter]);
					nPanel2.add(radBtns[counter]);
					counter++;
				}

			}
		}
		
		return nPanel2;
	}

	
	void handleSortWSMI()
	{
		
	}
	
	public static void main(String[] args) {
		SortWSItemHandler swsInst = new SortWSItemHandler();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		swsInst.setSize(width/3, height/2);
		// here's the part where i center the jframe on screen
		swsInst.setLocationRelativeTo(null);
		swsInst.setVisible(true);
		//swsInst.pack();
		
	}
}

