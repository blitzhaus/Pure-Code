package view;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Arrays;

import view.ApplicationInfo;


public class SubAreasData implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 9006430381780722167L;


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubAreasData other = (SubAreasData) obj;
		if (!Arrays.deepEquals(partialAreaDSForNCA, other.partialAreaDSForNCA))
			return false;
		return true;
	}

	@Override
	public String toString() {
		PrintStream p = null;
		p = ApplicationInfo.createFilePointer();
		String consoles = "printing sub areas inputs";
		String S="\n\t //Sub areas table data\n";
		
		for(int i=0;i<partialAreaDSForNCA.length;i++){
			for(int j=0;j<partialAreaDSForNCA[i].length;j++){
				S=S+"\t\t"+partialAreaDSForNCA[i][j];
			}
			System.out.println();
			S = S + "\n";
		}
		
		//p.append(S+"\n\n\n");
		return S;
	}

	
	String[][] partialAreaDSForNCA;

	
	public String[][] getPartialAreaDSForNCA() {
		return partialAreaDSForNCA;
	}

	public void setPartialAreaDSForNCA(String[][] partialAreaDSForNCA) {
		this.partialAreaDSForNCA = partialAreaDSForNCA;
	}
	
	public void setPartialValueAt(int xIndex, int yIndex, String value){
		partialAreaDSForNCA[xIndex][yIndex] = value;
	}
	
	public String getpartialValueAt(int xIndex, int yIndex){
		return(partialAreaDSForNCA[xIndex][yIndex]);
	}
	
	public SubAreasData(){
		partialAreaDSForNCA = new String[1][1];
	}
}
