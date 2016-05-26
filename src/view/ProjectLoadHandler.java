package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.codecompany.jeha.ExceptionHandler;

import Common.ExtensionFileFilter;
import Common.MyHandler;

public class ProjectLoadHandler {
	
	public static ProjectLoadHandler PROJECT_LOAD = null;
	public static ProjectLoadHandler createProjectLoadHandlerInst(){
		if(PROJECT_LOAD == null){
			PROJECT_LOAD = new ProjectLoadHandler();
		}		
		return PROJECT_LOAD;
	}
	public ProjectLoadHandler(){		
	}
	
	private void createPESubTree(ArrayList<DefaultMutableTreeNode> treeNodes)
	{
	
		for (int i = 0; i < treeNodes.size(); i++) {
			DefaultMutableTreeNode mutTreeNode = (DefaultMutableTreeNode) treeNodes.get(i);
			
			if (mutTreeNode.isLeaf()!= true)
				DDViewLayer.createPEInstance().workSpace.add(mutTreeNode);
		}
		
		DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
		.createPEInstance().tree1.getModel();
		model.reload();				
	}
	
		
	@ExceptionHandler(handler = MyHandler.class)
	public String importFile() {

		File selectedPfile = null;

		JFileChooser importFile = new JFileChooser();
		importFile.setCurrentDirectory(new File("."));
		importFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter textFileFilter = new ExtensionFileFilter("enc Files",
				new String[] { "enc" });
		importFile.setFileFilter(textFileFilter);
		
		int returnVal = importFile.showOpenDialog(null);
		
		
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("You chose to open this file: " +
		    		   importFile.getSelectedFile().getName());
		    }
		//selectedPfile = importFile.getSelectedFile();
		//String path = selectedPfile.getPath();
		String path = importFile.getSelectedFile().getPath();
		return path;

	}
	
	void restoreGUIFromJar()
	{
	}
	
	void removeProject1Node(JTree tree)
	{
		TreeModel model = tree.getModel();
		Object root = model.getRoot();
		walkProjectTree(tree, model, root);
	}
	
void walkProjectTree(JTree tree, TreeModel model, Object root)
 {
		DefaultTreeModel defTreeModel = (DefaultTreeModel) tree.getModel();
		int count = model.getChildCount(root);
		for (int i = 0; i < count; i++) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) model
					.getChild(root, i);

			String parentName = child.getParent().toString();

				if (parentName.equals("Work Space") == true) {
					defTreeModel.removeNodeFromParent(child);
				} else {
					walkProjectTree(tree, model, child);
				}
		}

	}
	
	
	
	@ExceptionHandler(handler = MyHandler.class)
	public void loadProject() {
		String encFileName = importFile();
		System.out.println("Enc File Name..."+encFileName);
		
		ApplicationInfo readPerst = null;
		
		PersistStructure persistStructureObject = new PersistStructure();
		try {
			readPerst = (ApplicationInfo)persistStructureObject.readEncryptedFile(encFileName);
			DisplayContents.setM_appInfo(readPerst);
			
			JTree tree = DDViewLayer.createPEInstance().tree1;
			removeProject1Node(tree);
			
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			ArrayList<DefaultMutableTreeNode> peTreeDetails = readPerst.getPeSubTree();
			createPESubTree(peTreeDetails);
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		/*catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
		
	
}
