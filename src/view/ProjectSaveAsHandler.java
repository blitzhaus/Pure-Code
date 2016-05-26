package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.codecompany.jeha.ExceptionHandler;

import com.itextpdf.text.DocumentException;

import Common.ExtensionFileFilter;
import Common.MyHandler;

public class ProjectSaveAsHandler {
	
	//Hashtable<String, ArrayList<String>> peTreeDetails = null;
	ArrayList<DefaultMutableTreeNode> peSubTree = null;
	
	public static ProjectSaveAsHandler PROJECT_SAVEAS = null;
	public static ProjectSaveAsHandler createProjectSaveAsHandlerInst(){
		if(PROJECT_SAVEAS == null){
			PROJECT_SAVEAS = new ProjectSaveAsHandler();
		}		
		return PROJECT_SAVEAS;
	}
	public ProjectSaveAsHandler(){
		//peTreeDetails = new Hashtable<String, ArrayList<String>>();
		peSubTree = new ArrayList<DefaultMutableTreeNode>();
	}
	
	
	
	/*void printProjectExplorerTreeDetails()
	{
		Enumeration keys = peTreeDetails.keys();
		while( keys.hasMoreElements() ) {
		  String key = (String) keys.nextElement();
		  System.out.println("Key...."+key);
		  ArrayList<String> values = (ArrayList<String>)peTreeDetails.get(key);
		  
			for (int i = 0; i < values.size(); i++) {
				System.out.println("  value of the key\t\t\t---->"+values.get(i));
			}
		}
	}*/
	
	
	
	public void saveProjectExplorerDetails() 
	{
		DefaultMutableTreeNode rootNode = DDViewLayer.createPEInstance().workSpace;
		JTree peTree = new JTree(rootNode);
		traverseProjectExplorerTree(DDViewLayer.createPEInstance().tree1);
		//printProjectExplorerTreeDetails();
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		//appInfo.setPeTreeDetails(peTreeDetails);
		
		appInfo.setPeSubTree(peSubTree);
		
		try {
			//so directly we store the appInfo without calling the store function
			PersistStructure persistStructureObject = new PersistStructure();
			persistStructureObject.writeMyObject(DisplayContents.createAppInfoInstance(),"sessions/store.ser", "sessions");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	void traverseProjectExplorerTree(JTree tree)
	{
		TreeModel model = tree.getModel();
		Object root = model.getRoot();
		walkProjectTree(model, root);
	}
	
	private Object[] extractPath(TreeNode tNode) {
		TreePath treePath = TreeUtilities.getPath(tNode);
		Object[] pathComps = treePath.getPath();
		return pathComps;
	}

	void walkProjectTree(TreeModel model, Object o)
	{
		int count = model.getChildCount(o);
		for (int i = 0; i < count; i++) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) model.getChild(o, i);
			
			if (child.isLeaf()!=true)
			{
				String parentName = child.getParent().toString();
				
				if (parentName.equals("Work Space")==true)
				{
					peSubTree.add((DefaultMutableTreeNode)child);
				}
				else
				{
					walkProjectTree(model, child);
				}
			}
			
			
		}	
	}

	
		
	@ExceptionHandler(handler = MyHandler.class)
	public String saveFile() {

		File selectedPfile = null;

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter textFileFilter = new ExtensionFileFilter("enc Files",
				new String[] { "ddproj" });
		chooser.setFileFilter(textFileFilter);
		chooser.showSaveDialog(null);
		selectedPfile = chooser.getSelectedFile();
		String path = selectedPfile.getPath();
		return path;

	}	
}

