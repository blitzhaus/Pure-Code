package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

import Common.Comparator;
import Common.MyComparator;
import Model.WorkSheetOutputs;

import view.ApplicationInfo;
import view.ColumnProperties;


public class ScaInfo implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = -1610845664795438462L;
	private ProcessingInputsInfo processingInputs;
	private WorkSheetOutputs workSheetOutputs;
	private ArrayList<ColumnProperties> columnPropertiesArrayList;
	private boolean isAnalysisExecuted;
	private boolean isDataChanged;
	public boolean isDataChanged() {
		return isDataChanged;
	}
	public void setDataChanged(boolean isDataChanged) {
		this.isDataChanged = isDataChanged;
	}
	public void setAnalysisExecuted(boolean isAnalysisExecuted) {
		this.isAnalysisExecuted = isAnalysisExecuted;
	}
	public boolean isAnalysisExecuted() {
		return isAnalysisExecuted;
	}
	
	
	
	public WorkSheetOutputs getWorkSheetOutputs() {
		return workSheetOutputs;
	}

	public void setWorkSheetOutputs(WorkSheetOutputs workSheetOutputs2) {
		this.workSheetOutputs = workSheetOutputs2;
	}
	
	public ProcessingInputsInfo getProcessingInputs() {
		return processingInputs;
	}
	public void setProcessingInputs(ProcessingInputsInfo processingInputs) {
		this.processingInputs = processingInputs;
	}
	
	public ScaInfo(){
		processingInputs = new ProcessingInputsInfo();
		workSheetOutputs = new WorkSheetOutputs();
		columnPropertiesArrayList = new ArrayList<ColumnProperties>();
		isAnalysisExecuted = false;
		isDataChanged = false;

	}
	
	
	@Override
	public boolean equals(Object obj) {
		Comparator comp = MyComparator.createMyCompInst();
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScaInfo other = (ScaInfo) obj;
		if (columnPropertiesArrayList == null) {
			if (other.columnPropertiesArrayList != null)
				return false;
		} else if (!comp.compare(columnPropertiesArrayList,(other.columnPropertiesArrayList)))
			return false;
		if (isAnalysisExecuted != other.isAnalysisExecuted)
			return false;
		if (isDataChanged != other.isDataChanged)
			return false;
		if (processingInputs == null) {
			if (other.processingInputs != null)
				return false;
		} else if (!processingInputs.equals(other.processingInputs))
			return false;
		if (workSheetOutputs == null) {
			if (other.workSheetOutputs != null)
				return false;
		} else if (!workSheetOutputs.equals(other.workSheetOutputs))
			return false;
		return true;
	}
	public ArrayList<ColumnProperties> getColumnPropertiesArrayList() {
		return columnPropertiesArrayList;
	}


	public void setColumnPropertiesArrayList(
			ArrayList<ColumnProperties> columnPropertiesArrayList) {
		this.columnPropertiesArrayList = columnPropertiesArrayList;
	}

	@Override
	public String toString() {
		String consoleS = "noncompartmental analysis";
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String s = "";
		for(int i=0;i< columnPropertiesArrayList.size();i++){
			s =s +columnPropertiesArrayList.get(i)+ "//The above details are for column number "+i+"\n\n";
		}
		
		s = s + ""+processingInputs; 
		s = s + ""+ workSheetOutputs;
		//p.append(s);
		
		return s;
	}


}
