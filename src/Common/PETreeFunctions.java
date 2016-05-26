package Common;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;

import view.ApplicationInfo;
import view.DisplayContents;
import view.DDViewLayer;

public class PETreeFunctions {

	public static PETreeFunctions PE_TREE_FUNC_INST = null;
	public static PETreeFunctions createPETreeFuncInst(){
		if(PE_TREE_FUNC_INST == null){
			PE_TREE_FUNC_INST = new PETreeFunctions("just object creation");
		}
		return PE_TREE_FUNC_INST;
	}
	
	public PETreeFunctions(String dummy){
		
	}
	
	
	 public void setProjIndexAndSelectedProjPath(int projIndex, String projName) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		//set the selected project index
		appInfo.setSelectedProjectIndex(projIndex);
		
		//set the selected project path
		Iterator<Entry<String, DefaultMutableTreeNode>> it = appInfo.getpENodes().entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			if(pairs.getKey().toString().equals("Work Space,"+projName+",")){
				
				String key = pairs.getKey().toString();
				DefaultMutableTreeNode projNode = (DefaultMutableTreeNode)appInfo.getpENodes().get(key);
				
				appInfo.setSelectedprojectPath(convertObjToStr(projNode));
			}
		}
		
	}
	
	public String convertObjToStr(DefaultMutableTreeNode workSpace2) {
		
		String s = new String("");
		
		for(int i=0;i<workSpace2.getUserObjectPath().length;i++){
			s = s + workSpace2.getUserObjectPath()[i].toString()+",";
		}
		
		return s;
	}
	
	
	public int getProjIndex(String projName) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		for(int i=0;i<appInfo.getProjectInfoAL().size();i++){
			if(appInfo.getProjectInfoAL().get(i).getProjectName().equals(projName)){
				return i;
			}
		}
		return 0;
	}

	public String getProjName(String[] pathSplits) {
		
		String s = pathSplits[1];
		s = s.substring(1, s.length());
		return s;
	}
	
	
	
}
