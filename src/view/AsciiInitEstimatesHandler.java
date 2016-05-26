package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AsciiInitEstimatesHandler {
	
	private final int PARAMCOL = 1;
	private final int INITIALVALUE = 1;
	private final int lOWERBOND = 1;
	private final int UPPERBOUND = 1;
	public static AsciiInitEstimatesHandler ASCIIINITESTHAND = null;
	public static AsciiInitEstimatesHandler createAsciiInitEstInst() {
		
		if(ASCIIINITESTHAND == null){
			ASCIIINITESTHAND = new AsciiInitEstimatesHandler();
		}
		return ASCIIINITESTHAND;
	}
	
	//ProcessingInputsInfo processInputInfo;
	public AsciiInitEstimatesHandler(){
		//processInputInfo = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
	}

	void updateInitTable() throws RowsExceededException, WriteException, BiffException, IOException {
		
		removeAll();
		insertColumns();
		insertRows();
		
	}

	CaNoOfSubjectDeterminer determineNoOfSubInst;
	private void insertRows() throws RowsExceededException, WriteException, BiffException, IOException {
		// TODO Auto-generated method stub
		
		determineNoOfSubInst = CaNoOfSubjectDeterminer
		.createDetermineNoOfSubInstance();
		String[][] local = determineNoOfSubInst.dataSortVariables;

		int index = 0;
		if(CaMappingDispGuiCreator.createMappingInstance().caFuncTF.getText().equals("")){
			
			for(int i=0;i<local.length;i++){
				if(local.length == 1){// it means that there are no sort variables
					ArrayList<String> al = getPrimartParameters();
					ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
					// fill the sort variables along with the function column
					for(int k=0;k<appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
							appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
									.getAsciiInfo().getProcessingInputs().getAsciiParameters().getPriParameters().size();k++){
						
						String[] rowData = new String[4];
						rowData[0] =  al.get(k);
						rowData[1] = "";
						rowData[2] = "";
						rowData[3] = "";
						((DefaultTableModel)AsciiInitialEstGui.createAsciiinitGuiInst().asciiIntEstTable.getModel()).addRow(rowData);
					}
					
				} else {
					ArrayList<String> al = getPrimartParameters();
					ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
					// fill the sort variables along with the function column
					for(int k=0;k<appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
							appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
									.getAsciiInfo().getProcessingInputs().getAsciiParameters().getPriParameters().size();k++){
						
						String[] rowData = new String[local[0].length + 4];
						for(int j=0;j<local[0].length;j++){
							rowData[j] = local[i][j]; 
						}
						
					
						rowData[local[0].length] =  al.get(k);

						rowData[local[0].length +1] = "";
						rowData[local[0].length +2] = "";
						rowData[local[0].length +3] = "";
						((DefaultTableModel)AsciiInitialEstGui.createAsciiinitGuiInst().asciiIntEstTable.getModel()).addRow(rowData);
					}
				}
				

			}
			
			
		} else {
			for(int i=0;i<local.length;i++){
				//
				String funcVar = local[i][local[i].length-1];
				ArrayList<String> al = loadParametersIntoAL(funcVar);
				ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
				// fill the sort variables along with the function column
				for(int k=0;k<appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
								.getAsciiInfo().getProcessingInputs().getAsciiInitEstimates().getFuncArray().get(funcVar).size();k++){
					
					String[] rowData = new String[local[0].length + 4];
					for(int j=0;j<local[0].length;j++){
						rowData[j] = local[i][j]; 
					}
					
					// fill the parameter name
					if(index == (al.size())){
						index = 0;
					}
					rowData[local[0].length] =  al.get(index++);

					rowData[local[0].length +1] = "";
					rowData[local[0].length +2] = "";
					rowData[local[0].length +3] = "";
					((DefaultTableModel)AsciiInitialEstGui.createAsciiinitGuiInst().asciiIntEstTable.getModel()).addRow(rowData);
				}
			}
		}
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().setProcessingInputs(processInputInfo);*/
	}

	private ArrayList<String> getPrimartParameters() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ArrayList<String> al = new ArrayList<String>();
		HashMap<Integer, String> hm = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getAsciiInfo().getProcessingInputs().getAsciiParameters().getPriParameters();
		 for (Map.Entry entry : hm.entrySet()) {
			 
			 al.add(entry.getValue().toString());
			 }
		/* ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
					.getAsciiInfo().setProcessingInputs(processInputInfo);*/
		 return al;
		 
	}

	private ArrayList<String> loadParametersIntoAL(String funcVar) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ArrayList<String> al = new ArrayList<String>();
		al = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getAsciiInfo().getProcessingInputs().getAsciiInitEstimates().getIndexedCopyOfPrarameters();
		/*ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAsciiInfo().setProcessingInputs(processInputInfo);*/
		return al;
	}

	private void insertColumns() throws RowsExceededException, WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int numSort = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
				appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getAsciiInfo().getProcessingInputs().getMappingDataObj().getSortVariablesListVector().size();
		
		int totalColumns = numSort + PARAMCOL + INITIALVALUE + lOWERBOND + UPPERBOUND;
		
		if(!CaMappingDispGuiCreator.createMappingInstance().caFuncTF.getText().equals("")){
			totalColumns = totalColumns + 1;
			numSort = numSort + 1;
		}
		
		for(int i=0;i<totalColumns;i++){
			((DefaultTableModel)AsciiInitialEstGui.createAsciiinitGuiInst().asciiIntEstTable.getModel()).addColumn("");
			//AsciiInitialEstGui.createAsciiinitGuiInst().asciiIntEstTable.getColumnModel().addColumn(new TableColumn());
		}
		TableColumnModel tcm =  AsciiInitialEstGui.createAsciiinitGuiInst().asciiIntEstTable.getColumnModel();
		TableColumn tc;
		for(int i=0;i<numSort;i++){
			tc = tcm.getColumn(i);
			
			if((!CaMappingDispGuiCreator.createMappingInstance().caFuncTF.getText().equals(""))&&(i == (numSort - 1))){
				tc.setHeaderValue(CaMappingDispGuiCreator.createMappingInstance().caFuncTF.getText());
			} else {
				tc.setHeaderValue(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
								.getAsciiInfo().getProcessingInputs().getMappingDataObj().getSortVariablesListVector().get(i));
			}
			
		}
	
	
		tc = tcm.getColumn(numSort);
		tc.setHeaderValue("Parameter");
		
		tc = tcm.getColumn(numSort + PARAMCOL);
		tc.setHeaderValue("initial Value");
		
		tc = tcm.getColumn(numSort + PARAMCOL + INITIALVALUE);
		tc.setHeaderValue("Lower Bound");
		
		tc = tcm.getColumn(numSort + PARAMCOL + INITIALVALUE + lOWERBOND);
		tc.setHeaderValue("Upper Bound");
		
	}

	private void removeAll() {
		
		AsciiInitialEstGui.createAsciiinitGuiInst().asciiIntEstTable = null;
		AsciiInitialEstGui.createAsciiinitGuiInst().asciiInitEstIF.getContentPane().removeAll();
		AsciiInitialEstGui.createAsciiinitGuiInst().asciiInitEstIF.validate();
		
		AsciiInitialEstGui.createAsciiinitGuiInst().asciiIntEstTable = new JTable(0,0);
		JScrollPane sp = new JScrollPane(AsciiInitialEstGui.createAsciiinitGuiInst().asciiIntEstTable);
		
		AsciiInitialEstGui.createAsciiinitGuiInst().asciiInitEstIF.getContentPane().add(sp);
		
		AsciiInitialEstGui.createAsciiinitGuiInst().asciiInitEstIF.validate();
		
		
	}

}
