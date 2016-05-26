package view;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;	
import Common.JinternalFrameFunctions;
public class DesStatResAvailComp {

	public static DesStatResAvailComp DES_STAT_RES_AVAIL = null;
	public static DesStatResAvailComp createDesStatResAvailInst(){
		if(DES_STAT_RES_AVAIL == null){
			DES_STAT_RES_AVAIL = new DesStatResAvailComp();
		}
		return DES_STAT_RES_AVAIL;
	}
	
	public DesStatResAvailComp(){
		
	}
	
	JInternalFrame ResultsTabAvailableoutputInternalFrame;
	DefaultMutableTreeNode availableOutputs;
	JTree availableOutputsTree;
	
	void createResultsTabAvailableOutputsInternalFrame() {
		ResultsTabAvailableoutputInternalFrame = new JInternalFrame("Output",
				false,false,false,false);
		ResultsTabAvailableoutputInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(ResultsTabAvailableoutputInternalFrame);
		ResultsTabAvailableoutputInternalFrame.setLocation(0, 0);
		int height = (int) (DDViewLayer.createMTInstance().mainTabbedFrame.getHeight() / 2);
		ResultsTabAvailableoutputInternalFrame.setSize(
				DDViewLayer.createMTInstance().mainTabbedFrame.getWidth() / 4, height);
		ResultsTabAvailableoutputInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		ResultsTabAvailableoutputInternalFrame.setVisible(true);
		DSTabs.createDSTabInst().resultsTabDesktopPane.add(ResultsTabAvailableoutputInternalFrame);
		ResultsTabAvailableoutputInternalFrame.moveToFront();
		availableOutputs = new DefaultMutableTreeNode("output");
		DefaultMutableTreeNode Tables = new DefaultMutableTreeNode("Sheets");
		availableOutputs.add(Tables);
		DefaultMutableTreeNode finalParametersNode = new DefaultMutableTreeNode(
				"Final Parameters");
		Tables.add(finalParametersNode);

		availableOutputsTree = new JTree(availableOutputs);

		availableOutputsTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		availableOutputsTree.addTreeSelectionListener(DDViewLayer.createViewLayerInstance());
		availableOutputsTree.addMouseListener(DDViewLayer.createViewLayerInstance());
		JScrollPane availableComponentsScrollPane = new JScrollPane(
				availableOutputsTree);
		ResultsTabAvailableoutputInternalFrame.getContentPane().add(
				availableComponentsScrollPane);

	}
}
