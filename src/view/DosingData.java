package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Arrays;


import Common.MyComparator;

import view.ApplicationInfo;


public class DosingData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5756003708026786090L;
	private String[][] dosingDSForNCA;
	private String[] dosingDSHeader;
	transient MyComparator comp = MyComparator.createMyCompInst();
	private String[][] bqlRulesTable;

	public String[][] getBqlRulesTable() {
		return bqlRulesTable;
	}

	public void setBqlRulesTable(String[][] bqlRulesTable) {
		this.bqlRulesTable = bqlRulesTable;
	}

	public String[] getDosingDSHeader() {
		return dosingDSHeader;
	}

	public void setDosingDSHeader(String[] dosingDSHeader) {
		this.dosingDSHeader = dosingDSHeader;
	}
	public String[][] getDosingDSForNCA() {
		return dosingDSForNCA;
	}

	public void setDosingDSForNCA(String[][] dosingDSForNCA) {
		this.dosingDSForNCA = dosingDSForNCA;
	}
	
	public void setDosingValueAt(int xIndex,int yIndex, String value){
		dosingDSForNCA[xIndex][yIndex] = value;
	}
	
	public void setBqlRulesTableValueAt(int xIndex, int yIndex, String value){
		bqlRulesTable[xIndex][yIndex] = value;
	}
	public String getDosingValueAt(int xIndex, int yIndex){
		return(dosingDSForNCA[xIndex][yIndex]);
	}

	public DosingData(){
		dosingDSForNCA = new String[1][1];
		bqlRulesTable = new String[1][1];
	}

	@Override
	public String toString() {
		String consoleS = "\tprinting dosing details\n";
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String s = "";
		s  = s + "\t//dosing table data"+ "\n";
		for(int i=0; i< dosingDSForNCA.length;i++){
			for(int j=0;j<dosingDSForNCA[i].length;j++){
				s =s + "\t\t"+dosingDSForNCA[i][j]+"   ";
			}
			s = s + "\n";
		}
		s  = s  + "\tt\number of columns = "+ dosingDSForNCA[0].length;
		//p.append(s);
		return s;
	}

	
	@Override
	public boolean equals(Object obj) {
		
	
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DosingData other = (DosingData) obj;
	
		if (!Arrays.deepEquals(bqlRulesTable, other.bqlRulesTable))
			return false;
		if (!Arrays.deepEquals(dosingDSForNCA, other.dosingDSForNCA))
			return false;
		if (!Arrays.deepEquals(dosingDSHeader, other.dosingDSHeader))
			return false;
		return true;
	}


}
