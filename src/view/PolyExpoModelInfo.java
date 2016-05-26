package view;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

import Model.WorkSheetOutputs;

public class PolyExpoModelInfo implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 5000174886901923420L;
	/**
	 * 
	 */
	
	private ProcessingInputsInfo ProcessingInputs;
	private WorkSheetOutputs WorkSheetOutputs;
	private ArrayList<ColumnProperties> ColumnPropertiesArrayList;
	private boolean isAnalysisExecuted;
	public void setAnalysisExecuted(boolean isAnalysisExecuted) {
		this.isAnalysisExecuted = isAnalysisExecuted;
	}
	public boolean isAnalysisExecuted() {
		return isAnalysisExecuted;
	}

	
	public WorkSheetOutputs getWorkSheetOutputs() {
		return WorkSheetOutputs;
	}

	public void setWorkSheetOutputs(WorkSheetOutputs workSheetOutputs2) {
		this.WorkSheetOutputs = workSheetOutputs2;
	}
	
	public ProcessingInputsInfo getProcessingInputs() {
		return ProcessingInputs;
	}
	public void setProcessingInputs(ProcessingInputsInfo processingInputs) {
		this.ProcessingInputs = processingInputs;
	}
	
	public PolyExpoModelInfo(){
		ProcessingInputs = new ProcessingInputsInfo();
		WorkSheetOutputs = new WorkSheetOutputs();
		ColumnPropertiesArrayList = new ArrayList<ColumnProperties>();
		isAnalysisExecuted = false;

	}
	
	
	
	public ArrayList<ColumnProperties> getColumnPropertiesArrayList() {
		return ColumnPropertiesArrayList;
	}


	public void setColumnPropertiesArrayList(
			ArrayList<ColumnProperties> columnPropertiesArrayList) {
		this.ColumnPropertiesArrayList = columnPropertiesArrayList;
	}

	@Override
	public String toString() {
		String consoleS = "InVitro";
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String s = "";
		for(int i=0;i< ColumnPropertiesArrayList.size();i++){
			s =s +ColumnPropertiesArrayList.get(i)+ "//The above details are for column number "+i+"\n\n";
		}
		
		s = s + ""+ProcessingInputs; 
		s = s + ""+ WorkSheetOutputs;
		
		return s;
	}



}
