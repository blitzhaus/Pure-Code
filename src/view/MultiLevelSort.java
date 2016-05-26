package view;


import java.util.*;
import java.io.*;
import java.sql.*;

import org.apache.commons.collections.comparators.ComparatorChain;

import Jama.*;


class Sorter implements Comparator
 {
	int colIndex = 0;

	Sorter(int cIdx, int numCols) {
		colIndex = cIdx;
	}

	public int compare(Object obj1, Object obj2) {
		String[] arrayOne = (String[]) obj1;
		String[] arrayTwo = (String[]) obj2;

		return arrayOne[colIndex].compareTo(arrayTwo[colIndex]);
	}

}

class NumberSorter implements Comparator
 {
	int colIndex = 0;

	NumberSorter(int cIdx, int numCols) {
		colIndex = cIdx;
	}

	public int compare(Object obj1, Object obj2) {
		String[] arrayOne = (String[]) obj1;
		String[] arrayTwo = (String[]) obj2;

		double num1 = Double.parseDouble(arrayOne[colIndex]);
		double num2 = Double.parseDouble(arrayTwo[colIndex]);

		if (num1 > num2)
			return 1;
		else if (num1 < num2)
			return -1;
		else
			return 0;
	}

}

class NumberSorterDesc implements Comparator
 {
	int colIndex = 0;

	NumberSorterDesc(int cIdx, int numCols) {
		colIndex = cIdx;
	}

	public int compare(Object obj1, Object obj2) {
		String[] arrayOne = (String[]) obj1;
		String[] arrayTwo = (String[]) obj2;

		double num1 = Double.parseDouble(arrayOne[colIndex]);
		double num2 = Double.parseDouble(arrayTwo[colIndex]);

		if (num1 < num2)
			return 1;
		else if (num1 > num2)
			return -1;
		else
			return 0;
	}

}

public class MultiLevelSort
 {
	static public String[][] inputMatrix;
	static public int rowCount = 0;
	static public int colCount = 0;

	public void FUNCTION_ENTER(String func_name) {
		try {
			P2.println("Entering\t" + func_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void FUNCTION_EXIT(String func_name) {
		try {
			P2.println("Leaving\t" + func_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void printMatrix(String str, String[][] matrix, int rowDim,
			int colDim) {

		P2.println("Input String:" + str);
		P2.println("row Dimension:" + rowDim + "\n");
		P2.println("col Dimension:" + colDim + "\n");

		if (str.length() != 0) {
			for (int r = 0; r < rowDim; r++) {
				for (int c = 0; c < colDim; c++) {
					P2.print(matrix[r][c] + "\t");
				}
				P2.println();
			}
			P2.println("\n");
		}
	}

	public MultiLevelSort() {
	}

	public MultiLevelSort(Vector v) {
	}
	
	private Vector<String> identifyColTypes(Vector colIndices)
	{	
		Vector<String> colTypes = new Vector<String>();
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		for (int i = 0; i < colIndices.size(); i++) {
			String colIdxStr = (String) colIndices.get(i);
			int colIndex = Integer.parseInt(colIdxStr);

			String dataTypeStr = appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(colIndex).getDataType();
			
			colTypes.add(dataTypeStr);
		}
		return colTypes;		
	}

	public String[][] sortData(String[][] data, Vector colIndices) {
		ComparatorChain chainOfComparators = new ComparatorChain();
		int cCount = data[0].length;

		Vector<String> colTypes = identifyColTypes(colIndices);
				
		if (colIndices.size() == 0) {
			String[][] data1 = new String[data.length][1];

			for (int i = 0; i < data1.length; i++) {
				data1[i][0] = 1.0 + "";
			}

			data = appendColumns(data1, data);
			
			int idx = Integer.parseInt((String) colIndices.get(0));
			
			String columnDataType = colTypes.get(0);
			
			if (columnDataType.equals("Numeric")== true)
				chainOfComparators.addComparator(new NumberSorter(idx,
						data[0].length));
			else
				chainOfComparators.addComparator(new Sorter(0, data[0].length));

			Arrays.sort(data, chainOfComparators);
		} else {
			for (int i = 0; i < colIndices.size(); i++) {
				int idx = Integer.parseInt((String) colIndices.get(i));

				//if (idx == cCount - 1 || idx == cCount - 2)
				String columnDataType = colTypes.get(i);
				
				if (columnDataType.equals("Numeric")== true)
				{
					chainOfComparators.addComparator(new NumberSorter(idx,
							data[0].length));
				}
				else
				{
					chainOfComparators.addComparator(new Sorter(idx,
							data[0].length));
				}
			}
			Arrays.sort(data, chainOfComparators);
		}

		return data;
	}

	String[][] sortDataDesc(String[][] data, Vector colIndices) {
		ComparatorChain chainOfComparators = new ComparatorChain();
		int cCount = data[0].length;

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (colIndices.size() == 0) {
			String[][] data1 = new String[data.length][1];

			for (int i = 0; i < data1.length; i++) {
				data1[i][0] = 1.0 + "";
			}

			data = appendColumns(data1, data);
			chainOfComparators.addComparator(new Sorter(0, data[0].length));

			Arrays.sort(data, chainOfComparators);
		} else {
			for (int i = 0; i < colIndices.size(); i++) {
				int idx = Integer.parseInt((String) colIndices.get(i));

				if (idx == cCount - 1 || idx == cCount - 2)
					chainOfComparators.addComparator(new NumberSorterDesc(idx,
							data[0].length));
				else
					chainOfComparators.addComparator(new Sorter(idx,
							data[0].length));
			}
			Arrays.sort(data, chainOfComparators);
		}

		return data;
	}

	private String[][] appendColumns(String[][] ipData, String[][] appColData) {
		int rCount = ipData.length;
		int cCount = ipData[0].length;
		int aRCount = appColData.length;
		int aCCount = appColData[0].length;

		String[][] resData = new String[rCount][cCount + aCCount];

		for (int r = 0; r < rCount; r++) {
			for (int c = 0; c < cCount; c++) {
				resData[r][c] = ipData[r][c];
			}
			int counter = cCount;
			for (int c1 = 0; c1 < aCCount; c1++) {
				resData[r][counter] = appColData[r][c1];
				counter++;
			}
		}

		return resData;
	}

	private String[][] appendRows(String[][] ipData, String[][] appRowData) {
		int rCount = ipData.length;
		int cCount = ipData[0].length;
		int aRCount = appRowData.length;
		int aCCount = appRowData[0].length;

		String[][] resData = new String[rCount + aRCount][cCount];

		for (int c = 0; c < aCCount; c++) {
			for (int r = 0; r < rCount; r++) {
				resData[r][c] = ipData[r][c];
			}
			int counter = rCount;
			for (int r1 = 0; r1 < aRCount; r1++) {
				resData[counter][c] = appRowData[r1][c];
				counter++;
			}
		}

		return resData;
	}

	public static PrintStream P2;

	public static void main(String[] aArguments) {
	}

}
