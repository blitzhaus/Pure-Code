package view;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.text.View;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.JinternalFrameFunctions;

public class PMResultsAvailComp {

	
	public static PMResultsAvailComp PM_RES_AVAIL_COMP = null;
	public static PMResultsAvailComp createPmSetupAvailCompInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PM_RES_AVAIL_COMP == null){
			PM_RES_AVAIL_COMP = new PMResultsAvailComp("just object creation");
		}
		return PM_RES_AVAIL_COMP;
	}
	
	JInternalFrame ResultsAvailableInternalFrame;
	DefaultMutableTreeNode OutputMainNode;
	JTree resultsAvailableTree;
	int selectedFrame;
	
	public PMResultsAvailComp(String dummy) throws RowsExceededException, WriteException, BiffException, IOException{
		
	}
	
	void createPlotMavenResultsAvailableInternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		ResultsAvailableInternalFrame = new JInternalFrame("ResultsAvailable",true,true,true,true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(ResultsAvailableInternalFrame);
		ResultsAvailableInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		ResultsAvailableInternalFrame.setLocation(0, 0);
		ResultsAvailableInternalFrame.setSize(PMSetupAvailComp.createPMAvailCompInst().setupAvailableComponentsInternalFrame.getWidth(), 
				PMSetupAvailComp.createPMAvailCompInst().setupAvailableComponentsInternalFrame.getHeight());
		
		ResultsAvailableInternalFrame.setVisible(true);
		PlotMavenCreateUI.createPlotMavenUIInst().plotMavenResultDesktopPane.add(ResultsAvailableInternalFrame);
		ResultsAvailableInternalFrame.moveToFront();
		ResultsAvailableInternalFrame.setBackground(Color.white);
		
		OutputMainNode = new DefaultMutableTreeNode("output");
		resultsAvailableTree = new JTree(OutputMainNode);
		resultsAvailableTree.addTreeSelectionListener(DDViewLayer.createViewLayerInstance());
		
		resultsAvailableTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		JScrollPane resultsAvailableScrollPane = new JScrollPane(resultsAvailableTree);
		ResultsAvailableInternalFrame.getContentPane().add(resultsAvailableScrollPane);
		
		
		
	}
}
