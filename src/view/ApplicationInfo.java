package view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import Common.Comparator;
import Common.MyComparator;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

public class ApplicationInfo implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3487074172472076008L;
	private ArrayList<ProjectInfo> projectInfoAL;
	String selectedprojectPath;
	int selectedProjectIndex;
	HashMap<String, DefaultMutableTreeNode> pENodes;
	private boolean isCARetrievalFlag;
	public boolean isRetrievalFlag() {
		return isCARetrievalFlag;
	}

	public void setRetrievalFlag(boolean isRetrievalFlag) {
		this.isCARetrievalFlag = isRetrievalFlag;
	}
	
	ArrayList<DefaultMutableTreeNode> peSubTree = null;
	
	public ArrayList<DefaultMutableTreeNode> getPeSubTree() {
		return peSubTree;
	}

	public void setPeSubTree(ArrayList<DefaultMutableTreeNode> peSubTree) {
		this.peSubTree = peSubTree;
	}

	Hashtable<String, ArrayList<String>> peTreeDetails = null;
	
	public Hashtable<String, ArrayList<String>> getPeTreeDetails() {
		return peTreeDetails;
	}

	public void setPeTreeDetails(Hashtable<String, ArrayList<String>> peTreeDetails) {
		this.peTreeDetails = peTreeDetails;
	}
	
	
	synchronized public HashMap<String, DefaultMutableTreeNode> getpENodes() {
		return pENodes;
	}


	synchronized	public void setpENodes(HashMap<String, DefaultMutableTreeNode> pENodes) {
		this.pENodes = pENodes;
	}


	public int getSelectedProjectIndex() {
		return selectedProjectIndex;
	}
	
	public int getProjectIdByName(String projectName)
	{
		int projId = 0;
		
		for (int i = 0; i < projectInfoAL.size(); i++) {
			ProjectInfo projInfoTmp = (ProjectInfo) projectInfoAL.get(i);
			String projNameTmp = projInfoTmp.getProjectName();
			
			if (projectName.equals(projNameTmp)== true)
			{
				projId = i;
				break;
			}
		}
		
		return projId;
		
	}
	
	public int getWorkSheetIndexByName(String projectName, String wbName, String wsName)
	{
		int projIndex = getProjectIdByName(projectName);
		int wbIndex = getWorkBookIdByName(projectName, wbName);
		
		ProjectInfo projInfoTmp = (ProjectInfo) projectInfoAL.get(projIndex);
		String projNameTmp = projInfoTmp.getProjectName();

		ArrayList<WorkBookInfo> workBookInfoList = projInfoTmp
				.getWorkBooksArrayList();
		WorkBookInfo wbInfoTmp = workBookInfoList.get(wbIndex);
		
		int wsIndex = wbInfoTmp.getSheetIndexByName(wsName);

		return wsIndex;
	}
	
	public int getWorkBookIdByName(String projectName, String wbName)
	{
		int wbId = -1;
		
		int projIndex = getProjectIdByName(projectName);
		ProjectInfo projInfoTmp = (ProjectInfo) projectInfoAL.get(projIndex);
		String projNameTmp = projInfoTmp.getProjectName();

		ArrayList<WorkBookInfo> workBookInfoList = projInfoTmp
				.getWorkBooksArrayList();

		for (int j = 0; j < workBookInfoList.size(); j++) {
			WorkBookInfo wbInfoTmp = workBookInfoList.get(j);
			String workBookName = wbInfoTmp.getWorkBookName();
			if ((projectName.equals(projNameTmp) == true)
					&& (workBookName.equals(wbName) == true)) {
				wbId = j;
				return wbId;
			}

		}

		return wbId;
	}


	public void setSelectedProjectIndex(int selectedProjectIndex) {
		this.selectedProjectIndex = selectedProjectIndex;
	}


	public String getSelectedprojectPath() {
		return selectedprojectPath;
	}


	public void setSelectedprojectPath(String selectedprojectPath) {
		this.selectedprojectPath = selectedprojectPath;
	}


	public ArrayList<ProjectInfo> getProjectInfoAL() {
		return projectInfoAL;
	}


	public void setProjectInfoAL(ArrayList<ProjectInfo> projectInfoAL) {
		this.projectInfoAL = projectInfoAL;
	}


	public ApplicationInfo() {
		projectInfoAL = new ArrayList<ProjectInfo>();
		selectedprojectPath = new String("");
		selectedProjectIndex = 0;
		pENodes = new HashMap<String, DefaultMutableTreeNode>();
	}
	
	
	public ProjectInfo getselectedProjectInfo() {
	return null;	
	}


	public static PrintStream createFilePointer() {
		// TODO Auto-generated method stub
		return null;
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
		ApplicationInfo other = (ApplicationInfo) obj;
		if (isCARetrievalFlag != other.isCARetrievalFlag)
			return false;
		/*if (pENodes == null) {
			if (other.pENodes != null)
				return false;
		}*/ /*else if (!pENodes.equals(other.pENodes))
			return false;*/
		if (projectInfoAL == null) {
			if (other.projectInfoAL != null)
				return false;
		} else if (!comp.compareProject(projectInfoAL,(other.projectInfoAL)))
			return false;
		if (selectedProjectIndex != other.selectedProjectIndex)
			return false;
		if (selectedprojectPath == null) {
			if (other.selectedprojectPath != null)
				return false;
		} else if (!selectedprojectPath.equals(other.selectedprojectPath))
			return false;
		return true;
	}
	
	
	
	
	
}
