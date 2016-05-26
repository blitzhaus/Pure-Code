package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

import Common.Comparator;
import Common.MyComparator;
import Model.WorkSheetOutputs;

public class TableTransformationsInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3202201874081953261L;
	private ProcessingInputsInfo ProcessingInputs;
	private WorkSheetOutputs WorkSheetOutputs;
	private ArrayList<ColumnProperties> ColumnPropertiesArrayList;
	private boolean isAnalysisExecuted;
	private boolean dataChanged;
	
	
	public boolean isDataChanged() {
		return dataChanged;
	}
	public void setDataChanged(boolean dataChanged) {
		this.dataChanged = dataChanged;
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

	public void setWorkSheetOutputs(WorkSheetOutputs wsOutputts) {
		this.WorkSheetOutputs = wsOutputts;
	}
	
	public ProcessingInputsInfo getProcessingInputs() {
		return ProcessingInputs;
	}
	public void setProcessingInputs(ProcessingInputsInfo processingInputs) {
		this.ProcessingInputs = processingInputs;
	}
	
	public TableTransformationsInfo(){
		ProcessingInputs = new ProcessingInputsInfo();
		WorkSheetOutputs = new WorkSheetOutputs();
		ColumnPropertiesArrayList = new ArrayList<ColumnProperties>();
		isAnalysisExecuted = false;
		dataChanged = false;

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
		TableTransformationsInfo other = (TableTransformationsInfo) obj;
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
		if (dataChanged != other.dataChanged)
			return false;
		if (isAnalysisExecuted != other.isAnalysisExecuted)
			return false;
		return true;
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
		String consoleS = "Table transformations";
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String s = "";
		for(int i=0;i< ColumnPropertiesArrayList.size();i++){
			s =s +ColumnPropertiesArrayList.get(i)+ "//The above details are for column number "+i+"\n\n";
		}
		
		s = s + ""+ProcessingInputs; 
		s = s + ""+ WorkSheetOutputs;
		
		//p.append(s);
		
		return s;
	}

}
