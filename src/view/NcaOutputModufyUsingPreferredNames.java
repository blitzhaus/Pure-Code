package view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jurvis.swing.table.JvMappedObjectTableModel.ColumnMapper;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaOutputModufyUsingPreferredNames {

	public static NcaOutputModufyUsingPreferredNames NCA_OUT_PARAM_NAMES = null;
	public static NcaOutputModufyUsingPreferredNames createInst(){
		if(NCA_OUT_PARAM_NAMES == null){
			NCA_OUT_PARAM_NAMES = new NcaOutputModufyUsingPreferredNames("just object creation");
		}
		return NCA_OUT_PARAM_NAMES;
	}
	
	public NcaOutputModufyUsingPreferredNames(String dummyString) {
		// TODO Auto-generated constructor stub
	}
	
	

	
	
	void modifyOutputUsingPreferredParamNames() throws RowsExceededException, WriteException, BiffException, IOException {
		
		removeFromText();
		removeFromTables();
		
	}

	private void removeFromTables() throws RowsExceededException, WriteException, BiffException, IOException {
		removeFromVertTable();
		removeFromHorzTable();
		
	}

	private void removeFromHorzTable() throws RowsExceededException, WriteException, BiffException, IOException {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		HashMap<String, String[]> map = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getProcessingInputs().getParameterNamesObj().getParameterNamesMap();
		
		
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String[]> entry = (Map.Entry)it.next();
			String key = entry.getKey();
			String[] value = entry.getValue();
			if(value[1].equals("yes")){
				
				//if yes then we have to replace with the preferred name
				replaceHeaderWithPreferredName(key, value);
				
			} else{
				//since the parameter should not be included in the output we have to remove it
				removeColumn(key);
			}
		}
		
	}

	private void removeColumn(String key) throws RowsExceededException, WriteException, BiffException, IOException {
		
		int columns = NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable.getColumnCount();
		TableColumnModel tcm = NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable.getColumnModel();
		for(int i=0;i<columns;i++){
			TableColumn tc = tcm.getColumn(i);
			if(tc.getHeaderValue().toString().equals(key)){
				tcm.removeColumn(tc);
				columns = NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable.getColumnCount();
				i=0;
				continue;
				
			}
		}
		
	}

	private void replaceHeaderWithPreferredName(String key, String[] value) throws RowsExceededException, WriteException, BiffException, IOException {
		int columns = NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable.getColumnCount();
		TableColumnModel tcm = NcaResultDispComp.createNcaResDispCompInst().finalParametersHorizontalDisplayTable.getColumnModel();
		for(int i=0;i<columns;i++){
			TableColumn tc = tcm.getColumn(i);
			if(tc.getHeaderValue().toString().equals(key)){
				tc.setHeaderValue(value[0]);
				
			}
		}
		
	}

	private void removeFromVertTable() throws RowsExceededException, WriteException, BiffException, IOException {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		HashMap<String, String[]> map = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getProcessingInputs().getParameterNamesObj().getParameterNamesMap();
		
		
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String[]> entry = (Map.Entry)it.next();
			String key = entry.getKey();
			String[] value = entry.getValue();
			if(value[1].equals("yes")){
				
				//if yes then we have to replace with the preferred name
				replaceWithPreferredName(key, value);
				
			} else{
				//since the parameter should not be included in the output we have to remove it
				removeRow(key);
			}
		}
		
	}

	private void replaceWithPreferredName(String key, String[] value) throws RowsExceededException, WriteException, BiffException, IOException {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		int rows = NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable.getRowCount();
		int sortSize = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getProcessingInputs().getMappingDataObj().getSortVariablesListVector().size();
		for(int i=0;i<rows;i++){
			if(NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable.getValueAt(i, sortSize).toString().equals(key)){
				NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable.setValueAt(value[0], i, sortSize);
				
			}
		}
		
		
	}

	private void removeRow(String key) throws RowsExceededException, WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		int rows = NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable.getRowCount();
		int sortSize = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo().getProcessingInputs().getMappingDataObj().getSortVariablesListVector().size();
		for(int i=0;i<rows;i++){
			if(NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable.getValueAt(i, sortSize).toString().equals(key)){
				((DefaultTableModel)NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable.getModel()).removeRow(i);
				i = 0;
				rows = NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable.getRowCount();
				continue;
			}
		}
		
	}

	private void removeFromText() {
		// TODO Auto-generated method stub
		
	}
	
	
}
