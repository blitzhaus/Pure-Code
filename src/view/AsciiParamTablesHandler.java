package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AsciiParamTablesHandler {

	public static AsciiParamTablesHandler ASCIIPARAMHANDINST = null;
	public static AsciiParamTablesHandler createAsciiParamHandInst(){
		if(ASCIIPARAMHANDINST == null){
			ASCIIPARAMHANDINST = new AsciiParamTablesHandler();
		}
		return ASCIIPARAMHANDINST;
	}

	//private AsciiModelInfo asciiModInfoInst;
	
	
	public AsciiParamTablesHandler(){

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		/*asciiModInfoInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo();*/
	
	}

	public void asciiPriParamTabHandler(JTable table, boolean isPrimaryParam) throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int rowChanged = table
				.getSelectedRow();
		int colChanged = table
				.getSelectedColumn();
		if((rowChanged >=0) && (colChanged >= 0)){
			String value =  table
			.getValueAt(rowChanged, colChanged).toString();
			if(isPrimaryParam == true){
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters()
				.getPriParameters().put(rowChanged, value);
			} else {
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters()
				.getSecParameters().put(rowChanged, value);
			}
		}
		AsciiInitialEstGui.createAsciiinitGuiInst().isInitEstimateUpdateReq = true;
/*		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).setAsciiInfo(asciiModInfoInst);*/
		System.out.println();
	}

	public void resetFuncTableColumns() throws RowsExceededException, WriteException, BiffException, IOException {
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncIF.getContentPane().removeAll();
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncIF.getContentPane().removeAll();

		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable = setFuncTableCol(AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable);
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiParamFuncTableSP = new JScrollPane(AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable);
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiParamFuncTableSP.setBorder(BorderFactory.createTitledBorder("Function Table"));
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiParamFuncTableSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiParamFuncTableSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncIF.getContentPane().add(AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiParamFuncTableSP);
		
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncTalbe = 	setFuncTableCol(AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncTalbe);
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiParamFuncTableSP = new JScrollPane(AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncTalbe);
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiParamFuncTableSP.setBorder(BorderFactory.createTitledBorder("Function Table"));
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiParamFuncTableSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiParamFuncTableSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncIF.getContentPane().add(AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiParamFuncTableSP);
		
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncIF.repaint();
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncIF.validate();
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable.repaint();
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable.validate();
		
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncIF.repaint();
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncIF.validate();
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncTalbe.repaint();
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncTalbe.validate();
	}

	private JTable setFuncTableCol(JTable table) throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		TableColumnModel tcm = table.getColumnModel();
		boolean hasRows = false;
		int rows = 0;
		if ((rows = table.getRowCount()) != 0) {
			hasRows = true;

		}
		HashSet<String> local = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs()
				.getMappingDataObj().getAsciiFuncHS();
		table = null;
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model) {

			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				return Boolean.class;
			}
		};
		
		table.getSelectionModel().addListSelectionListener(new AsciiPrimaryParamFuncSelectionHandler());
		Iterator<String> it = local.iterator();
		while (it.hasNext()) {
			((DefaultTableModel) table.getModel()).addColumn(it.next());
		}

		if (hasRows == true) {
			table = insertRows(rows, table);
		}

		return table;

	}

	JTable insertRows(int rows, JTable table) {
		// TODO Auto-generated method stub
		while(rows-- >0){
			Object[] rowData = new Object[table.getColumnCount()];
			for(int i=0;i<rowData.length;i++){
				rowData[i] = false;
			}
			((DefaultTableModel)table.getModel()).insertRow(table.getRowCount(), rowData);
		}
		
		return table;
	}


	
}
