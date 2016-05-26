package view;

import java.io.IOException;
import java.io.Serializable;

import javax.swing.JTable;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class SububAreasinputs implements Serializable{

	private static final long serialVersionUID = 8124526112782514364L;
	String[] subAreasTableHeaderValues;
	String[][] subAreasTableValues;
	
	
	public SububAreasinputs() throws RowsExceededException, WriteException, BiffException, IOException{
	
		//setSubAreasTableValues(NCAMainScreen.tableForPartialArea);
	}


	public String[] getSubAreasTableHeaderValues() {
		return subAreasTableHeaderValues;
	}


	public void setSubAreasTableHeaderValues(String[] subAreasTableHeaderValues) {
		this.subAreasTableHeaderValues = subAreasTableHeaderValues;
	}


	public String[][] getSubAreasTableValues() {
		return subAreasTableValues;
	}


	public void setSubAreasTableValues(JTable subAreasTableValues) throws RowsExceededException, WriteException, BiffException, IOException {/*
		
		NCAMainScreen ncaMS = MainLayoutPage.createNcaMainScreenInstance();
		this.subAreasTableValues = new String[ncaMS.getNumberOfRow()][ncaMS.getNumberOfColumn()];
		System.out.println("The sub areas are");
		for(int i=0;i<this.subAreasTableValues.length;i++){
			for(int j=0;j<this.subAreasTableValues[i].length;j++){
				try
				{
					this.subAreasTableValues[i][j] = (String)subAreasTableValues.getValueAt(i, j);
					System.out.print(this.subAreasTableValues[i][j]+", ");
				}catch (Exception e) {
					
				}
				
			}
			System.out.println();
		}
	*/}
}
