
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codecompany.jeha.ExceptionHandler;
import org.codecompany.jeha.core.HandlerUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

import jxl.BooleanCell;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.*;
import Common.DataReader2;
import Common.LogEntry;
import Common.MyHandler;
import Common.TestException;
import Common.WorkBookManipulation;

import Jama.Matrix;

public class DisplayContents extends JFrame {
	
	public static ApplicationInfo m_appInfo = null;
	public static ApplicationInfo getM_appInfo() {
		return m_appInfo;
	}
	public static void setM_appInfo(ApplicationInfo mAppInfo) {
		m_appInfo = mAppInfo;
	}
	public static ApplicationInfo createAppInfoInstance()
	{
		if (m_appInfo == null)
		{
			m_appInfo = new ApplicationInfo();
		}
		return m_appInfo;
	}
	

	private static LambdaZCalculationBeforeNCA m_lZBeforeNCA;
	public static LambdaZCalculationBeforeNCA createInstanceOflzBeforeNCA(){
		if(m_lZBeforeNCA == null){
			m_lZBeforeNCA = new LambdaZCalculationBeforeNCA();
		}
		return m_lZBeforeNCA;
	}
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame f;
	Matrix oriData;
	String[][] dataFromFileString;
	JTableHeader displayContentsDataTableHeader;
	JTable dataTable;
	boolean  finishAndOpenClicked = false;
	Container c;
	AbstractTableModel t;
	JInternalFrame sheetsFrame;
	JScrollPane sheetsScrollPane;
	Boolean excel;
	JList sheetLablesList;
	int sheetCountIndex;
	int maxRows;
	int maxColumns;
	JScrollPane dataTableScrollPane;
	String fileName;
	AbstractAction DataDisplayTableCellChangedaction;
	JTextField missingValueText;
	int startRowValue; 
	JDesktopPane mainDesktopPane;
	JInternalFrame dataFrame;
	JInternalFrame filesFrame;
	JCheckBox hasHeaderRowCheck;
	private JButton cancelButton;
	JTextField startRowText;
	JButton finishAndOpen;
	DefaultListModel sheetLablesListModel;

	public DisplayContents(){
	
	}
	public static void main(String args[]){
	
	}
	

	
	public  void createFrame(){
		DDViewLayer.createViewLayerInstance().setDisplayContentsTable(true);
		f = new JFrame("Work Book Contents");
		f.setSize(700,540);
		f.setBackground(Color.white);
		f.getContentPane().setBackground(Color.white);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setResizable(false);
		new DCInternalFramesCreation(ImportFile.createDispContentsInstance());
		new DCCreateNavigationPanel(ImportFile.createDispContentsInstance());
		
		
	}
	
	protected void removeTitleBarForinternalFrame(JInternalFrame j){
		javax.swing.plaf.InternalFrameUI ifu= j.getUI();
		  ((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);
	}

	@ExceptionHandler(handler = MyHandler.class)
	void getdata(String fileExtension, String filePath) throws BiffException, IOException, RowsExceededException, WriteException{
		
		
		try{
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			if((fileExtension.equalsIgnoreCase(".txt"))||(fileExtension.equalsIgnoreCase(".csv"))
					||(fileExtension.equalsIgnoreCase(".dat"))||(fileExtension.equalsIgnoreCase(".bat"))
					||(fileExtension.equalsIgnoreCase(".dta"))||(fileExtension.equalsIgnoreCase(".prn"))){
			
					//create and add the new workbookInfo to the arrayList
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().add(new WorkBookInfo());
					
					//set the index of selected work book to the current importing work book
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).setSelectedWorkBookIndex(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().size() - 1);
					
					ReadTextData readTData = new ReadTextData();
					readTData.readData(fileExtension, filePath,  maxRows, maxColumns, sheetsFrame);
					
					
					displaySheetData(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(0));//default work sheet display
					
					//setting the selection of the first sheet by default
					

			} else if (fileExtension.equalsIgnoreCase(".xls")
					|| (fileExtension.equalsIgnoreCase(".xlsx"))
					|| (fileExtension.equalsIgnoreCase(".ods"))) {

				//create and add the new workbookInfo to the arrayList
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().add(new WorkBookInfo());
				
				
				//set the index of selected work book to the current importing work book
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).setSelectedWorkBookIndex(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().size() - 1);
				
				ReadSpreadSheetData readSSData = new ReadSpreadSheetData();
				readSSData.readData(fileExtension, filePath, maxRows,
						maxColumns, sheetsFrame);
				displaySheetData(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(0));// default work
																// sheet display
				// setting the selection of the first sheet by default
				sheetLablesList.setSelectedIndex(0);

			} else{
				TestException test = new TestException();
				test.throwCustomException(4);
			}
		}
		
		catch(Exception e){
			//HandlerUtil.handle(e);
			ImportFile.DISP_CONTENTS = null;
			e.printStackTrace();
			f.dispose();
		}

		
		
		
	}
	

	private WritableWorkbook prepareWorkBook(String[][] dataFromFile2, String filePath) throws IOException, RowsExceededException, WriteException {
		
		WritableWorkbook w = Workbook.createWorkbook(new File(filePath));
		WorkBookManipulation wm = new WorkBookManipulation();
		w = wm.addWorkSheet(w, dataFromFile2, filePath);
		return w;
	}
	
	
	void displaySheetData(String[][] data) {

		dataTable = new JTable(data.length, data[0].length);

		dataTable.setRowSelectionAllowed(true);
		dataTable.setColumnSelectionAllowed(true);
		dataTable.setCellSelectionEnabled(true);
		dataTable.setBackground(Color.white);

		// dataTable.getModel().addTableModelListener(this);
		/*new ReorderableJList(dataTable);
		new JvUndoableTable(dataTable.getModel());*/
		int width = (int) ((int) getWidth() / 1.2);
		dataTable.setPreferredScrollableViewportSize(new Dimension(600, 300));
		t = (AbstractTableModel) dataTable.getModel();
		// t.fireTableDataChanged();
		dataTable.setFillsViewportHeight(true);
		dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// getting the data from the data structure
		// System.out.println("Work sheet is !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+file1Workbook.getSheet(sheetIndex).getName());
		for (int i = 0; i < data.length; i++) {

			for (int j = 0; j < data[i].length; j++) {

				// Cell c = file1Workbook.getSheet(sheetIndex).getCell(j, i);
				System.out.print(data[i][j] + "\t");
				dataTable.setValueAt(data[i][j], i, j);
			}
			System.out.println();
		}
		dataTableScrollPane = new JScrollPane(dataTable);
		dataTableScrollPane.setPreferredSize(new Dimension(
				dataFrame.getWidth(), dataFrame.getHeight()));
		// dataTableScrollPane.setBackground(Color.white);
		dataFrame.getContentPane().add(dataTableScrollPane);
		dataTableScrollPane.setVisible(true);

		displayContentsDataTableHeader = dataTable.getTableHeader();

		DataDisplayTableCellChangedaction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				dataDispTableCellChangeAction(e);

			}

			private void dataDispTableCellChangeAction(ActionEvent e) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				TableCellListener tcl = (TableCellListener) e.getSource();

				if (tcl.getOldValue().equals(tcl.getNewValue())) {

				} else {

					LogEntry.createLogEntryInstance().logEntry(
							"Display Contents:: Table Cell("
									+ (tcl.getRow() + 1) + ","
									+ (tcl.getColumn() + 1) + ") changed from "
									+ tcl.getOldValue() + " to "
									+ tcl.getNewValue());
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())[tcl
							.getRow()][tcl.getColumn()] = tcl.getNewValue()
							.toString();
				}
			}

			TableCellListener tcl = new TableCellListener(dataTable,
					DataDisplayTableCellChangedaction);
		};

	}

		// TODO Auto-generated method stub
	void fillFilesFrame(String filePath, int importedFileCount) {
		int fileNamePos;
		
		if (System.getProperty("os.name").startsWith("Windows")) {
	        // includes: Windows 2000,  Windows 95, Windows 98, Windows NT, Windows Vista, Windows XP
			fileNamePos = filePath.lastIndexOf("\\");
			System.out.println("This is a windows version");
	    } else {
	        // everything else
	    	System.out.println("this is a unix child");
	    	fileNamePos = filePath.lastIndexOf("/");
	    } 
		fileName = filePath.substring(fileNamePos+1);
		//JLabel fileNameLable = new JLabel(fileName);
		JTextField fileNameText = new JTextField();
		fileNameText.setText(fileName);
		fileNameText.setEditable(false);
		fileNameText.setBackground(Color.white);
		fileNameText.setVisible(true);
		//fileNameLable.setVisible(true);
		GridBagConstraints fileNameTextCon = new GridBagConstraints();
		fileNameTextCon.gridx = 0;
		fileNameTextCon.gridy = importedFileCount;
		fileNameTextCon.weighty = 0.5;
		System.out.println("The imported count is  "+importedFileCount);
		fileNameTextCon.anchor = GridBagConstraints.LINE_START;
		filesFrame.getContentPane().add(fileNameText, fileNameTextCon);
	}
	private void displayData() {
		// TODO Auto-generated method stub
		
		JTable dataTable = new JTable(oriData.getRowDimension(),oriData.getColumnDimension());
		dataTable.setBackground(Color.white);
		

		//dataTable.getModel().addTableModelListener(this);
		
		int width =  (int) ((int) getWidth()/1.2);
		dataTable.setPreferredScrollableViewportSize(new Dimension(600,300));
		t = (AbstractTableModel) dataTable.getModel();
		t.fireTableDataChanged();
		dataTable.setFillsViewportHeight(true);
		

		dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
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
		//for getting the data from the selected columns
		for(int i = 0; i < oriData.getColumnDimension();i++){
			
				for(int j=0;j<oriData.getRowDimension();j++){
					double v = oriData.get(j,i);
					dataTable.setValueAt(v, j, counting);
				}
				counting++;
			
		}
		
		JScrollPane dataTableScrollPane = new JScrollPane(dataTable);
		dataTableScrollPane.setBackground(Color.white);
		dataFrame.getContentPane().add(dataTableScrollPane,tableScrollPaneCon);
		
	}



}
