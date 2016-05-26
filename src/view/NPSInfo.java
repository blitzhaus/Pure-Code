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


public class NPSInfo implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8826186476819540714L;
	@Override
	public boolean equals(Object obj) {
		Comparator comp = MyComparator.createMyCompInst();
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NPSInfo other = (NPSInfo) obj;
		if (ColumnPropertiesArrayList == null) {
			if (other.ColumnPropertiesArrayList != null)
				return false;
		} else if (!comp.compare(ColumnPropertiesArrayList,(other.ColumnPropertiesArrayList)))
			return false;
		if (ProcessingInputs == null) {
			if (other.ProcessingInputs != null)
				return false;
		} else if (!ProcessingInputs.equals(other.ProcessingInputs))
			return false;
		if (WorkSheetOutputs == null) {
			if (other.WorkSheetOutputs != null)
				return false;
		} else if (!WorkSheetOutputs.equals(other.WorkSheetOutputs))
			return false;
		if (isAnalysisExecuted != other.isAnalysisExecuted)
			return false;
		if (isDataChanged != other.isDataChanged)
			return false;
		return true;
	}

	private ProcessingInputsInfo ProcessingInputs;
	private WorkSheetOutputs WorkSheetOutputs;
	private ArrayList<ColumnProperties> ColumnPropertiesArrayList;
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
	
	public NPSInfo(){
		ProcessingInputs = new ProcessingInputsInfo();
		WorkSheetOutputs = new WorkSheetOutputs();
		ColumnPropertiesArrayList = new ArrayList<ColumnProperties>();
		isAnalysisExecuted = false;
		isDataChanged = false;

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
		String consoleS = "Non Parametric superposition";
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String s = "";
		for(int i=0;i< ColumnPropertiesArrayList.size();i++){
			s =s +ColumnPropertiesArrayList.get(i)+ "//The above details are for column number "+i+"\n\n";
		}
		
		s = s + ""+ProcessingInputs; 
		s = s + ""+WorkSheetOutputs;
		//p.append(s);
		
		return s;
	}


}
