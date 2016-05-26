package view;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import Common.JinternalFrameFunctions;

public class ScaResultsAvailComp {

	public static ScaResultsAvailComp SCA_RES_AVAIL_CIMP = null;
	public static ScaResultsAvailComp createSetResAvailInst() {
		if(SCA_RES_AVAIL_CIMP == null){
			SCA_RES_AVAIL_CIMP = new ScaResultsAvailComp("just object creation");
		}
		return SCA_RES_AVAIL_CIMP;
	}
	
	public ScaResultsAvailComp(String dummy)
	{
		
	}
	
	JInternalFrame ResultsTabAvailableoutputInternalFrame;
	DefaultMutableTreeNode availableOutputs;
	JTree availableOutputsTree;
	int selectedImage;
	
	void createResultsTabAvailableOutputsInternalFrame() {
		ResultsTabAvailableoutputInternalFrame = new JInternalFrame("Output",true,true,true,true);
		ResultsTabAvailableoutputInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(ResultsTabAvailableoutputInternalFrame);
		ResultsTabAvailableoutputInternalFrame.setLocation(0, 0);
		int height = (int) (DDViewLayer.createMTInstance().mainTabbedFrame.getHeight()/2);
		ResultsTabAvailableoutputInternalFrame.setSize(DDViewLayer.createMTInstance().mainTabbedFrame.getWidth()/4,height);
		ResultsTabAvailableoutputInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		ResultsTabAvailableoutputInternalFrame.setVisible(true);
		ScaTabs.createScaTabsInst().resultsTabDesktopPane.add(ResultsTabAvailableoutputInternalFrame);
		ResultsTabAvailableoutputInternalFrame.moveToFront();
		availableOutputs = new DefaultMutableTreeNode("output");
		/*DefaultMutableTreeNode textOutput = new DefaultMutableTreeNode("Text output");
		availableOutputs.add(textOutput);
		DefaultMutableTreeNode completeOutput = new DefaultMutableTreeNode("Complete output");
		textOutput.add(completeOutput);
		*/
		DefaultMutableTreeNode Tables = new DefaultMutableTreeNode("Sheets");
		availableOutputs.add(Tables);
		DefaultMutableTreeNode finalParametersNode = new DefaultMutableTreeNode("Final Output");
		Tables.add(finalParametersNode);
		
		availableOutputsTree = new JTree(availableOutputs);
		
		availableOutputsTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		availableOutputsTree.addTreeSelectionListener(DDViewLayer.createViewLayerInstance());
		availableOutputsTree.addMouseListener(DDViewLayer.createViewLayerInstance());
		JScrollPane availableComponentsScrollPane = new JScrollPane(availableOutputsTree);
		availableComponentsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		availableComponentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ResultsTabAvailableoutputInternalFrame.getContentPane().add(availableComponentsScrollPane);
		
	}

}
