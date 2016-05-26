package view;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import Common.JinternalFrameFunctions;

public class BQLResAvailComp {

	JInternalFrame resultsAvailableComponentIF;
	JTree resultsAvailableTree;

	
	public static BQLResAvailComp BQL_RES_AVAIL = null;
	public static BQLResAvailComp createBQLResAvailInst(){
		if(BQL_RES_AVAIL == null){
			BQL_RES_AVAIL = new BQLResAvailComp("just object creation");
		}
		return BQL_RES_AVAIL;
	}
	
	public BQLResAvailComp(String dummy){

	}
	
	void bqlResAvailUICreation(){

		resultsAvailableComponentIF = new JInternalFrame(
				"Results Available", false, false, false, false);
		resultsAvailableComponentIF.setLocation(0, 0);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(resultsAvailableComponentIF);
		resultsAvailableComponentIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		int height = (int) (BQLMainScreen.createBQLMainScrInst().bqlMainScreenIF.getHeight()-20);
		resultsAvailableComponentIF.setSize(BQLMainScreen.createBQLMainScrInst().bqlMainScreenIF
				.getWidth() / 4, height);
		BQLTabbedPane.createBqlTabInst().resultsTabDesktopPane.add(resultsAvailableComponentIF);
		resultsAvailableComponentIF.moveToFront();
		resultsAvailableComponentIF.setVisible(true);
	
		resultsAvailableCompTreeCreation();
	}

	private void resultsAvailableCompTreeCreation() {

		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		DefaultMutableTreeNode resultsAvailableComponents = new DefaultMutableTreeNode(
				"Output");
				
		DefaultMutableTreeNode transformedSheet = new DefaultMutableTreeNode(
		"Transformed Sheet");
		
		resultsAvailableComponents.add(transformedSheet);


		resultsAvailableTree = new JTree(resultsAvailableComponents);
		resultsAvailableTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		//availableComponentsTree.addMouseListener(ViewLayer.createViewLayerInstance());
		resultsAvailableTree.addTreeSelectionListener(DDViewLayer.createViewLayerInstance());
		resultsAvailableTree.addMouseListener(DDViewLayer.createViewLayerInstance());
		
		JScrollPane availableComponentsScrollPane = new JScrollPane(
				resultsAvailableTree);
		resultsAvailableComponentIF.getContentPane().add(
				availableComponentsScrollPane);
		resultsAvailableComponentIF.moveToFront();

		
		
	}
}
