package view;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AsciiSelectionHandler {

	public static AsciiSelectionHandler ASCIICOMHAND = null;
	public static AsciiSelectionHandler createAsciiCommHandInst(){
		if(ASCIICOMHAND == null){
			ASCIICOMHAND = new AsciiSelectionHandler();
		}
		return ASCIICOMHAND;
	}
	
	//AsciiModelInfo asciiModInfoInst;
	public AsciiSelectionHandler(){
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		asciiModInfoInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAsciiInfo();*/
	}


	public void nParamUpdater(JTextField paramTF, boolean isPrimaryParam) throws RowsExceededException, NumberFormatException, WriteException, BiffException, IOException {
		int numParam;
		if(paramTF.getText().equals("")){
			numParam = 0;
		} else {
			numParam = Integer.parseInt(paramTF.getText());
		}
		storeInAppInfo(numParam, isPrimaryParam);
		updateParamTable(numParam, isPrimaryParam);
		updateFuncTable(numParam, isPrimaryParam);
	}


	private void updateFuncTable(int numParam, boolean isPrimaryParam) throws RowsExceededException, WriteException, BiffException, IOException {
		int funcTablerows = 0;
		int paramTableRows = 0;
		
		if(isPrimaryParam == true){
			funcTablerows = AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable.getRowCount();
			paramTableRows = AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPrimParamTable.getRowCount();
			
		} else if(isPrimaryParam == false){
			funcTablerows = AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncTalbe.getRowCount();
			paramTableRows = AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamTable.getRowCount();
			
		}
		
		
		updateFuncTableRows(funcTablerows,paramTableRows,isPrimaryParam);
		
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncIF.repaint();
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncIF.validate();
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable.repaint();
		AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable.validate();
		
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncIF.repaint();
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncIF.validate();
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncTalbe.repaint();
		AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncTalbe.validate();
		
	}


	private void updateFuncTableRows(int funcTableRows, int paramTableRows,
			boolean isPrimaryParam) throws RowsExceededException, WriteException, BiffException, IOException {
		if(funcTableRows == paramTableRows){
			
		} else if(funcTableRows < paramTableRows){
			if(isPrimaryParam == true){
				AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable = AsciiParamTablesHandler.createAsciiParamHandInst().insertRows((paramTableRows - funcTableRows), AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable);
			} else if(isPrimaryParam == false){
				AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncTalbe = AsciiParamTablesHandler.createAsciiParamHandInst().insertRows((paramTableRows - funcTableRows), AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncTalbe);
			}
			
		} else if(funcTableRows > paramTableRows){
			removeRowsFromFuncTable((funcTableRows - paramTableRows), isPrimaryParam);
		}
		
		
	}


private void removeRowsFromFuncTable(int rows, boolean isPrimaryParam) throws RowsExceededException, WriteException, BiffException, IOException {

	JTable table;
	if(isPrimaryParam == true){
		table = AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable; 
	} else {
		table = AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncTalbe;
	}
	table.removeRowSelectionInterval(0,
			table.getRowCount()-1);
	while(rows-- > 0){
		
		((DefaultTableModel)table.getModel()).
		removeRow(table.getRowCount()-1);
	}
	

		
	}

	private void updateParamTable(int numParam, boolean isPrimaryParam) throws RowsExceededException, WriteException, BiffException, IOException {
		int rows = getParamTableRows(isPrimaryParam);
		if(numParam == rows){
		} else if(numParam < rows){
			removeRows((rows - numParam), isPrimaryParam);
		} else if(numParam > rows){
			insertRows((numParam - rows), isPrimaryParam);
		}
		
	}

	private void insertRows(int rows, boolean isPrimaryParam) throws RowsExceededException, WriteException, BiffException, IOException {
		JTable table;
		int numParam; 
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if(isPrimaryParam == true){
			table = AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPrimParamTable;
			numParam = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters()
			.getPriParameters().size();
		} else {
			table = AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamTable;
			numParam = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters()
			.getSecParameters().size();
		}
		for(int i=0;i<rows;i++){
			((DefaultTableModel)table.getModel()).addRow(new Object[]{""});
		}
		setAppInfoParam(table, numParam, isPrimaryParam);

	}


	private void setAppInfoParam(JTable table, int numParam, boolean isPrimaryParam) throws RowsExceededException, WriteException, BiffException, IOException {
		int numRows = table.getRowCount();
		int num = numParam;
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if(numParam == numRows){
		} else if(numParam > numRows){
			num = numRows;
		} else if(numParam < numRows) {
			num = numParam;
		}
		for(int i=0;i< num;i++){
			if(isPrimaryParam == true){
				table.setValueAt(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters()
						.getPriParameters().get(i), i, 0);
			} else {
				table.setValueAt(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiParameters()
						.getSecParameters().get(i), i, 0);
			}
		}
		
	}


	private void removeRows(int rows, boolean isPrimaryParam) throws RowsExceededException, WriteException, BiffException, IOException {
		JTable table;
		if(isPrimaryParam == true){
			table = AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPrimParamTable; 
		} else {
			table = AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamTable;
		}
		table.removeRowSelectionInterval(0,
				table.getRowCount()-1);
		while(rows-- > 0){
			
			((DefaultTableModel)table.getModel()).
			removeRow(table.getRowCount()-1);
		}
		
	}


	private int getParamTableRows(boolean isPrimaryParam) throws RowsExceededException, WriteException, BiffException, IOException {
		// TODO Auto-generated method stub
		if(isPrimaryParam == true){
			return AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPrimParamTable.getRowCount();
		} else {
			return AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamTable.getRowCount();
		}
		
	}


	private void storeInAppInfo(int numParam, boolean isPrimaryParam) {
		// TODO Auto-generated method stub
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if(isPrimaryParam == true){
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiCommands().setNparam(numParam);
		} else {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiCommands().setnSecParam(numParam);
		}
		
	}


	public boolean isNumber(String text) {
		try{
			if(text.equals("")){
				return false;
			}
			Integer.parseInt(text);	
		} catch(Exception e){
			return false;
		}
		return true;
	}
}