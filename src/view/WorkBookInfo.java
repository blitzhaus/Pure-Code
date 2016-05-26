package view;

import java.io.Serializable;
import java.util.ArrayList;

import Common.Comparator;
import Common.MyComparator;

public class WorkBookInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7158910210911777140L;
	private ArrayList<String[][]> copyOfOriginalDataStructures;
	public ArrayList<String[][]> getCopyOfOriginalDataStructures() {
		return copyOfOriginalDataStructures;
	}

	public void setCopyOfOriginalDataStructures(
			ArrayList<String[][]> copyOfOriginalDataStructures) {
		this.copyOfOriginalDataStructures = copyOfOriginalDataStructures;
	}

	private String workBookName;
	
	public String getWorkBookName() {
		return workBookName;
	}

	

	String[] convert(ArrayList<String> al){
		String[] stral = new String[al.size()];
		for(int i=0;i<stral.length;i++){
			stral[i] = al.get(i);
		}
		return stral;
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
		WorkBookInfo other = (WorkBookInfo) obj;
		/*if (copyOfOriginalDataStructures == null) {
			if (other.copyOfOriginalDataStructures != null)
				return false;
		} else if (!copyOfOriginalDataStructures
				.equals(other.copyOfOriginalDataStructures))
			return false;
		if (dataStructuresArrayList == null) {
			if (other.dataStructuresArrayList != null)
				return false;
		} else if (!dataStructuresArrayList
				.equals(other.dataStructuresArrayList))
			return false;*/
		if (selectedSheetIndex != other.selectedSheetIndex)
			return false;
		if (workBookName == null) {
			if (other.workBookName != null)
				return false;
		} else if (!workBookName.equals(other.workBookName))
			return false;
		if (workSheetObjectsAL == null) {
			if (other.workSheetObjectsAL != null)
				return false;
		} else if (!comp.compareWorkSheetInfo(workSheetObjectsAL,(other.workSheetObjectsAL)))
			return false;
		return true;
	}

	public void setWorkBookName(String workBookName) {
		this.workBookName = workBookName;
	}

	private int selectedSheetIndex;
	private ArrayList<String[][]> dataStructuresArrayList;


	public ArrayList<String[][]> getDataStructuresArrayList() {
		return dataStructuresArrayList;
	}

	public void addElementToDS(String[][] data){
		dataStructuresArrayList.add(data);
	}
	
	public String[][] getElementFromDS(int index){
		return dataStructuresArrayList.get(index);
	}
	
	public void setElementInDS(String[][] data, int index) {
		dataStructuresArrayList.remove(index);
		dataStructuresArrayList.add(index,
				new String[data.length][data[0].length]);
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				dataStructuresArrayList.get(index)[i][j] = data[i][j];
			}
		}

	}
	
	private ArrayList<WorkSheetsInfo> workSheetObjectsAL;
	
	

	public void setDataStructuresArrayList(
			ArrayList<String[][]> dataStructuresArrayList) {
		this.dataStructuresArrayList = dataStructuresArrayList;
	}

	public int getSelectedSheetIndex() {
		return selectedSheetIndex;
	}

	public void setSelectedSheetIndex(int selectedSheetIndex) {
		this.selectedSheetIndex = selectedSheetIndex;
	}

	public WorkBookInfo(){
		workSheetObjectsAL = new ArrayList<WorkSheetsInfo>();
		selectedSheetIndex = 0;
		dataStructuresArrayList = new ArrayList<String[][]>();
		copyOfOriginalDataStructures = new ArrayList<String[][]>();
		workBookName = new String("");
	}
	
	public ArrayList<WorkSheetsInfo> getWorkSheetObjectsAL() {
		return workSheetObjectsAL;
	}

	public void setWorkSheetObjectsAL(ArrayList<WorkSheetsInfo> workSheetObjectsAL) {
		this.workSheetObjectsAL = workSheetObjectsAL;
	}
	
	public String[][] getSelectedSheetDataByName(String sheetName)
	{
		String[][] data = null;
		int selSheetindex = 0;
		
		for (int i = 0; i < workSheetObjectsAL.size(); i++) {
			WorkSheetsInfo wsInfo = workSheetObjectsAL.get(i);
			String wsSheetName = wsInfo.getSheetName();
			
			if (sheetName.equals(wsSheetName)==true)
			{
				selSheetindex = i;
				break;
			}
		}
		
		data = getDataStructuresArrayList().get(selSheetindex);
		
		return data;
	}
	
	public int getSheetIndexByName(String sheetName)
	{
		int selSheetindex = 0;
		
		for (int i = 0; i < workSheetObjectsAL.size(); i++) {
			WorkSheetsInfo wsInfo = workSheetObjectsAL.get(i);
			String wsSheetName = wsInfo.getSheetName();
			
			if (sheetName.equals(wsSheetName)==true)
			{
				selSheetindex = i;
				break;
			}
		}
				
		return selSheetindex;
	}

	
}
