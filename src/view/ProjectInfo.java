package view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import Common.Comparator;
import Common.MyComparator;

import jxl.Workbook;

public class ProjectInfo implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 2768796217925602485L;

	private static PrintStream P = null;
	
	boolean projectSaved = false;
	
	boolean dataFolderAlreadyAdded;
	
	public boolean isDataFolderAlreadyAdded() {
		return dataFolderAlreadyAdded;
	}

	public void setDataFolderAlreadyAdded(boolean dataFolderAlreadyAdded) {
		this.dataFolderAlreadyAdded = dataFolderAlreadyAdded;
	}


	Hashtable<String, ArrayList<String>> peTreeDetails = null;
	
	public Hashtable<String, ArrayList<String>> getPeTreeDetails() {
		return peTreeDetails;
	}

	public void setPeTreeDetails(Hashtable<String, ArrayList<String>> peTreeDetails) {
		this.peTreeDetails = peTreeDetails;
	}

	public static PrintStream createFilePointer() throws FileNotFoundException{
		
		if(P == null){
			 P = new PrintStream(new FileOutputStream("AppicationInfo.txt"));
		}
		
		return P;
	}
	
	
	private String projectName;
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	private boolean isCARetrievalFlag;
	public boolean isRetrievalFlag() {
		return isCARetrievalFlag;
	}

	public void setRetrievalFlag(boolean isRetrievalFlag) {
		this.isCARetrievalFlag = isRetrievalFlag;
	}
	
	
	private ArrayList<WorkBookInfo> workBooksArrayList;
	
	
	
	private int selectedWorkBookIndex;
	
	transient private Workbook file1Workbook;
	public Workbook getFile1Workbook() {
		return file1Workbook;
	}

	public void setFile1Workbook(Workbook ww) {
		this.file1Workbook = ww;
	}

	
	
	
	public int getSelectedWorkBookIndex() {
		return selectedWorkBookIndex;
	}

	public void setSelectedWorkBookIndex(int selectedWorkBookIndex) {
		this.selectedWorkBookIndex = selectedWorkBookIndex;
	}

	public ArrayList<WorkBookInfo> getWorkBooksArrayList() {
		return workBooksArrayList;
	}

	public void setWorkBooksArrayList(ArrayList<WorkBookInfo> workBooksArrayList) {
		this.workBooksArrayList = workBooksArrayList;
	}

	@Override
	public String toString() {
		PrintStream p = null;
		try {
			p = ProjectInfo.createFilePointer();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String ConsoleS = "Printing Application Info\n";
		String s = "";
		
		s = s + "//work sheet " + "\n";
		
	
		
		return ConsoleS;
	}

	public boolean isProjectSaved() {
		return projectSaved;
	}

	public void setProjectSaved(boolean projectSaved) {
		this.projectSaved = projectSaved;
	}

	public ProjectInfo(){
		workBooksArrayList = new ArrayList<WorkBookInfo>();
		selectedWorkBookIndex = 0;
		projectName = new String("Project 1");
		dataFolderAlreadyAdded = false;
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
		ProjectInfo other = (ProjectInfo) obj;
		if (dataFolderAlreadyAdded != other.dataFolderAlreadyAdded)
			return false;
		if (isCARetrievalFlag != other.isCARetrievalFlag)
			return false;
		/*if (peTreeDetails == null) {
			if (other.peTreeDetails != null)
				return false;
		} else if (!peTreeDetails.equals(other.peTreeDetails))
			return false;*/
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (projectSaved != other.projectSaved)
			return false;
		if (selectedWorkBookIndex != other.selectedWorkBookIndex)
			return false;
		if (workBooksArrayList == null) {
			if (other.workBooksArrayList != null)
				return false;
		} else if (!comp.compareWorkBooks(workBooksArrayList,(other.workBooksArrayList)))
			return false;
		return true;
	}
	
	

	
	

	

}
