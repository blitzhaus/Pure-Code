package view;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Common.PETreeFunctions;




public class TableTransformationsButtonHandler {
	
	DefaultMutableTreeNode projectNode;
	
	public static TableTransformationsButtonHandler TT_BUTTONHAND_INST = null;
	public static TableTransformationsButtonHandler createTTButtonHandInst(){
		
		if(TT_BUTTONHAND_INST == null){
			TT_BUTTONHAND_INST = new TableTransformationsButtonHandler("just object creation");
		}
		return TT_BUTTONHAND_INST;
	}

	public TableTransformationsButtonHandler(String dummy){
		
	}

	public void handler() {
		
		nodeCreationInPE();
	}

	
	private DefaultMutableTreeNode getProjNode() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Iterator<Entry<String, DefaultMutableTreeNode>> it = appInfo.getpENodes().entrySet().iterator();
		
		while(it.hasNext()){
			
			Map.Entry pairs = (Map.Entry)it.next();
			if(pairs.getKey().equals(appInfo.getSelectedprojectPath())){
				return (DefaultMutableTreeNode) pairs.getValue(); 
			}
		}
		
		return null;
	}
	
	private DefaultMutableTreeNode getAnalysisNode() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String projName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getProjectName();
		String key = "Work Space,"+projName+","+"Table Transformations,";
		
		DefaultMutableTreeNode analysisNode = appInfo.getpENodes().get(key);
		
		return analysisNode;
		
	}
	
	
	private void nodeCreationInPE() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DefaultMutableTreeNode analysisNode = null;
		Enumeration en = DDViewLayer.createPEInstance().workSpace
				.breadthFirstEnumeration();

		boolean hasNCAnalysisNode = false;
		DefaultMutableTreeNode node = null;
		
		projectNode = getProjNode();
		for(int i=0;i<projectNode.getChildCount();i++){
			if(((DefaultMutableTreeNode)projectNode.getChildAt(i)).getUserObject().toString().equals("Table Transformations")){
				hasNCAnalysisNode = true;
				break;
			}
		}

		if (hasNCAnalysisNode == true) {
			analysisNode = getAnalysisNode();
			
			DefaultMutableTreeNode ttNode = new DefaultMutableTreeNode("TT-"+appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex()).getWorkBookName()+"-" + appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL()
							.get(
									appInfo
											.getProjectInfoAL()
											.get(appInfo.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).getSheetName());
					
			analysisNode.add(ttNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.reload();
		} else if (hasNCAnalysisNode == false) {

			analysisNode = new DefaultMutableTreeNode(
					"Table Transformations");
			projectNode.add(analysisNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.insertNodeInto(analysisNode,
					projectNode, projectNode.getChildCount() - 1);
			
			//insert this node into the project nodes
			appInfo.getpENodes().put(PETreeFunctions.createPETreeFuncInst().convertObjToStr(analysisNode), analysisNode);
	
			DefaultMutableTreeNode npsNode = new DefaultMutableTreeNode("TT-"+appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex()).getWorkBookName()+"-" + appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL()
							.get(
									appInfo
											.getProjectInfoAL()
											.get(appInfo.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).getSheetName());
			analysisNode.add(npsNode);

			model.reload();
		}

	}
	
	
	
}
