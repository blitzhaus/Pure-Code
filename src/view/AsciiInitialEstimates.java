package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AsciiInitialEstimates implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, HashSet<String>> funcArray;
	private ArrayList<String> indexedCopyOfPrarameters;
	
	public ArrayList<String> getIndexedCopyOfPrarameters() {
		return indexedCopyOfPrarameters;
	}
	public void setIndexedCopyOfPrarameters(
			ArrayList<String> indexedCopyOfPrarameters) {
		this.indexedCopyOfPrarameters = indexedCopyOfPrarameters;
	}
	public HashMap<String, HashSet<String>> getFuncArray() {
		return funcArray;
	}
	public void setFuncArray(HashMap<String, HashSet<String>> funcArray) {
		this.funcArray = funcArray;
	}
	public AsciiInitialEstimates(){
		funcArray = new HashMap<String, HashSet<String>>();
		indexedCopyOfPrarameters = new ArrayList<String>();
	}
}
