package Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import view.ColumnProperties;
import view.ProjectInfo;
import view.WorkBookInfo;
import view.WorkSheetsInfo;

public class MyComparator extends Comparator {
	
public static MyComparator MY_COMP_INST = null;
public static MyComparator createMyCompInst(){
	if(MY_COMP_INST == null){
		MY_COMP_INST = new MyComparator();
	}
	return MY_COMP_INST;
}
	
	public MyComparator(){
		
	}

	@Override
	public boolean compareProject(ArrayList<ProjectInfo>al1, ArrayList<ProjectInfo>al2){
		for(int i=0;i<al1.size();i++){
			if(al1.get(i).equals(al2.get(i))){
				
			} else{
				return false;
			}
		}
		return true;
	}


	@Override
	public boolean compare(ArrayList<ColumnProperties> cp1, ArrayList<ColumnProperties> cp2){
		
		try{
			for(int i=0;i<cp1.size();i++){
				if(cp1.get(i).equals(cp2.get(i))){
					
				} else{
					return false;
				}
			}
			return true;
		} catch(IndexOutOfBoundsException e){
			return false;
		}
		
		
	}
	
	@Override
	public boolean compare(Vector<String> v1, Vector<String> v2){
		if(v1.size() != v2.size()){
			return false;
		} else{
			for(int i=0;i<v1.size();i++){
				if(v1.get(i).equals(v2.get(i))){
					
				} else{
					return false;
				}
			}
			return true;
		}
	}
	
	@Override
	public boolean compareWorkBooks(ArrayList<WorkBookInfo> al1, ArrayList<WorkBookInfo> al2){
		if(al1.size()!= al2.size()){
			return false;
		} else{
			for(int i=0;i<al1.size();i++){
				if(!al1.get(i).equals(al2.get(i))){
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	@Override
	public boolean compareIntBool(Hashtable<Integer, Boolean> inclusionExcusionPoints,
			Hashtable<Integer, Boolean> inclusionExcusionPoints2){
		
		Iterator<Entry<Integer, Boolean>> it = inclusionExcusionPoints.entrySet().iterator();
		while(it.hasNext()){
			 Map.Entry entry = (Map.Entry) it.next();
			 int i = (Integer) entry.getKey();
			 
			 if(inclusionExcusionPoints2.get(i).equals(entry.getValue())){
				 
			 } else{
				 return false;
			 }
		}
		return true;
		
	}
	
	
	@Override
	public boolean compareStrBool(Hashtable<String, Boolean> inclusionExcusionPoints,
			Hashtable<String, Boolean> inclusionExcusionPoints2){
		
		Iterator<Entry<String, Boolean>> it = inclusionExcusionPoints.entrySet().iterator();
		while(it.hasNext()){
			 Map.Entry entry = (Map.Entry) it.next();
			 String i = (String) entry.getKey();
			 
			 if(inclusionExcusionPoints2.get(i).equals(entry.getValue())){
				 
			 } else{
				 return false;
			 }
		}
		return true;
		
	}
	
	@Override
	public boolean compareWorkSheetInfo(ArrayList<WorkSheetsInfo> al1, ArrayList<WorkSheetsInfo> al2){
	
		if(al1.size() != al2.size()){
			return false;
		}
		else{
			for(int i=0;i<al1.size();i++){
				//if(compare(al1.get(i), al2.get(i)) == false){
				if(!al1.get(i).equals(al2.get(i))){
					return false;
				}
			}
		}
		
		return true;
	}
	
	@Override
	public boolean compareStrStr(HashMap<String, String> statusCodeHM,
			HashMap<String, String> statusCodeHM2) {
		
		
		Iterator<Entry<String, String>> it = statusCodeHM.entrySet().iterator();
		while(it.hasNext()){
			 Map.Entry entry = (Map.Entry) it.next();
			 String i = (String) entry.getKey();
			 
			 if(statusCodeHM2.get(i).equals(entry.getValue())){
				 
			 } else{
				 return false;
			 }
		}
		return true;
	}
	
	@Override
	public boolean compareStrHash(Hashtable<String, Hashtable<String, Double>> hash1, Hashtable<String, Hashtable<String, Double>> hash2){
		
		Iterator<Entry<String, Hashtable<String, Double>>> it = hash1.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			if(hash1.get(key) == hash2.get(key)){
				
			} else{
				Iterator<Entry<String, Double>> itInner = hash1.get(key).entrySet().iterator();
				while(itInner.hasNext()){
					Map.Entry entryInner = (Map.Entry) itInner.next();
					String keyInner = (String)entryInner.getKey();
					if(hash1.get(key).get(keyInner).equals(hash2.get(key).get(keyInner))){
						
					} else{
						return false;
					}
				}
				return true;
			}
			
		}
		return true;
		
	}
	
	
	public boolean compareasciiPrimaryPar(HashMap<Integer, String> al1,
			HashMap<Integer, String> al2) {
		
		Iterator<Entry<Integer, String>> it = al1.entrySet().iterator();
		while(it.hasNext()){
			 Map.Entry entry = (Map.Entry) it.next();
			 String i = (String) entry.getKey();
			 
			 if(al2.get(i).equals(entry.getValue())){
				 
			 } else{
				 return false;
			 }
		}
		return true;
	}
	
	@Override
	public boolean compareStrDouble(HashMap<String, Double> asciiTempVarHM, HashMap<String, Double> asciiTempVarHM2){
		
		
		Iterator<Entry<String, Double>> it = asciiTempVarHM.entrySet().iterator();
		while(it.hasNext()){
			 Map.Entry entry = (Map.Entry) it.next();
			 String i = (String) entry.getKey();
			 
			 if(asciiTempVarHM2.get(i).equals(entry.getValue())){
				 
			 } else{
				 return false;
			 }
		}
		return true;
	}
	
}
