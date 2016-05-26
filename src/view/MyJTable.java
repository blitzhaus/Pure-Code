package view;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MyJTable extends JTable{
	int[] colIndices;
	
	public MyJTable(DefaultTableModel dtm, int[] cIdx)
	{
		super(dtm);
		colIndices = cIdx; 
	}
	MyJTable(String[][] data, String[] headers, int[] cIdx)
	{
		super(data, headers);
		colIndices = cIdx; 
	}
	
	MyJTable(int rowCnt, int colCnt, int[] cIdx)
	{
		super(rowCnt,colCnt);
		colIndices = cIdx; 
	}
	
	public boolean isCellEditable(int row, int col)
	{
		boolean retVal = true;
		for (int i = 0; i < colIndices.length; i++) {
			if (col == colIndices[i])
				retVal = false;
		}
		
		return retVal;					
	}

}
