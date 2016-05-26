package view;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.jzy3d.plot3d.rendering.view.View;

import Common.PETreeFunctions;

public class NewProjectCreation {

	public static NewProjectCreation NEW_PROJ_INST = null;
	public static NewProjectCreation createNewProjectInst() {
		
		if(NEW_PROJ_INST == null){
			NEW_PROJ_INST = new NewProjectCreation("just object creation");
		}
		
		return NEW_PROJ_INST;
		
	}
	
	PETreeFunctions peTreeFuncInst = null;
	PEHandlers pehandlerInst = null;
	ApplicationInfo appInfo;
	
	public NewProjectCreation(String dummy){
		peTreeFuncInst = PETreeFunctions.createPETreeFuncInst();
		pehandlerInst = PEHandlers.createPEHanderInst();
	}
	
	

	void createNewProject() {
		
		DDViewLayer.createPEInstance().isProjectCreation = true;
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String projName = JOptionPane.showInputDialog("Please enter the project name");
		
		if(projName.equals("")){
			projName = ("Project "+(appInfo.getProjectInfoAL().size()+1));
			
		} 
		
		reflectInAppInfo(projName);
		reflectInPE(projName);
		pehandlerInst.storePresentAnalysis();
		
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).dataFolderAlreadyAdded = false;
		DDViewLayer.createPEInstance().isProjectCreation = false;
		
		
	}

	private void reflectInPE(String projName) {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		DefaultMutableTreeNode newProjNode = new DefaultMutableTreeNode(projName);
		((DefaultMutableTreeNode)DDViewLayer.createPEInstance().tree1.getModel().getRoot()).add(newProjNode);
		
		//adding the new project node to the hash map
		appInfo.getpENodes().put(peTreeFuncInst.convertObjToStr(newProjNode), newProjNode);
		
		((DefaultTreeModel)DDViewLayer.createPEInstance().tree1.getModel()).reload();
		
		
		//set the project in focus as the new project created
		appInfo.setSelectedprojectPath(peTreeFuncInst.convertObjToStr(newProjNode));		
	}

	private void reflectInAppInfo(String projName) {
		appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().add(new ProjectInfo());

		//set the selected project to the one just added i.e the last one in the array list of projects
		appInfo.setSelectedProjectIndex(appInfo.getProjectInfoAL().size()-1);
		
		
		// set the project name to the user input or the default
		appInfo.getProjectInfoAL().get(appInfo.getProjectInfoAL().size() - 1)
				.setProjectName(projName);

	}

}
