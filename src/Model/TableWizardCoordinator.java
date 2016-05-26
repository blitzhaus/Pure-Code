package Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.commons.math.stat.StatUtils;

import Common.ReadFile2dStrArray;
import Jama.Matrix;

import edu.umbc.cs.maple.utils.JamaUtils;

public class TableWizardCoordinator {
	
	
	private String[][] inputMatrix;
	private int rowCount = 0;
	private int colCount = 0;
	private String[] stringArray;
	private String[] descrArray;
	private TableWizardInputOptions tableWizardinputs;
	PrintStream P2;
	
	
	public TableWizardInputOptions getTableWizardinputs() {
		return tableWizardinputs;
	}
	
	public void setTableWizardinputs(TableWizardInputOptions tableWizardinputs) {
		this.tableWizardinputs = tableWizardinputs;
	}
	
	public String[][] getInputMatrix() {
		return inputMatrix;
	}
	
	public void setInputMatrix(String[][] inputMatrix) {
		this.inputMatrix = inputMatrix;
	}
	
	public int getRowCount() {
		return rowCount;
	}
	
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	public int getColCount() {
		return colCount;
	}
	
	public void setColCount(int colCount) {
		this.colCount = colCount;
	}
	
	public String[] getStringArray() {
		return stringArray;
	}
	
	public String[] getDescrArray() {
		return descrArray;
	}

	public void setDescrArray(String[] descrArray) {
		this.descrArray = descrArray;
	}

	public void setStringArray(String[] stringArray) {
		this.stringArray = stringArray;
	}

	public TableWizardCoordinator(PrintStream P2) {
		
		this.P2 = P2;
		ReadFile2dStrArray RF = new ReadFile2dStrArray();
		rowCount = RF.rowCount;
		colCount = RF.colCount;
		inputMatrix = new String[rowCount][colCount];
		stringArray = new String[rowCount];
		inputMatrix = RF.fileArray;
		stringArray = RF.stringArray;
		descrArray = RF.descrArray;
	
		printMatrix("Input data..", inputMatrix, inputMatrix.length,
				inputMatrix[0].length);
	}
	

	public TableWizardCoordinator(PrintStream P2, String[][] inputMatrix,
			int rCount, int cCount, String[] rowLabels, String[] colLabels) {

		this.P2 = P2;
		rowCount = rCount;
		colCount = cCount;
		this.inputMatrix = inputMatrix;
		this.stringArray = rowLabels;
		this.descrArray = colLabels;

		printMatrix("Input data..", inputMatrix, inputMatrix.length,
				inputMatrix[0].length);
	}
	
	public void printMatrix(String str, String[][] matrix, int rowDim,
			int colDim) {
	
		System.out.println("Input String:" + str);
		System.out.println("row Dimension:" + rowDim + "\n");
		System.out.println("col Dimension:" + colDim + "\n");
	
		if (str.length() != 0) {
			for (int r = 0; r < rowDim; r++) {
				for (int c = 0; c < colDim; c++) {
					System.out.println(matrix[r][c] + "\t");
				}
				System.out.println();
			}
			System.out.println("\n");
		}
	}
	
	
	public void printMatrix(String str, double[][] matrix, int rowDim,
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
	
	public void initializeSetup() {
		ArrayList<String> groupVariables = new ArrayList<String>();
		ArrayList<String> idVariables = new ArrayList<String>();
		ArrayList<String> crossVariables = new ArrayList<String>();
		ArrayList<String> ordinaryVariables = new ArrayList<String>();
	
		groupVariables.add("Subject_ID");
		groupVariables.add("Formulation");
	
		idVariables.add(" ");
	
		crossVariables.add(" ");
	
		ordinaryVariables.add("BP");
		ordinaryVariables.add("Conc");
	
		int templateType = 1;
	
		tableWizardinputs = new TableWizardInputOptions(templateType, groupVariables,
				idVariables, crossVariables, ordinaryVariables);
	}
	
	int getColumnIndex(String columnLabel) {
		int colIndex = 0;
	
		for (int i = 0; i < descrArray.length; i++) {
			if (descrArray[i].equals(columnLabel) == true) {
				colIndex = i;
				break;
			}
		}
	
		return colIndex;
	}
	
	int[] getColumnIndices(ArrayList<String> columnLabels) {
		int[] colIndices = new int[columnLabels.size()];
		int counter = 0;
	
		for (int i = 0; i < colIndices.length; i++) {
			String colLabel = columnLabels.get(i);
			colIndices[i] = getColumnIndex(colLabel);
		}
	
		return colIndices;
	}
	
	String[][] appendColumns(String[][] ipData, String[][] appColData) {
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
	
	String[][] pickDataForLevel(String level, String[][] data) {
		String[][] retData = new String[0][0];
	
		for (int i = 0; i < data.length; i++) {
	
			if (level.equals(data[i][data[i].length - 1]) == true) {
				String[][] rowData = new String[1][data[i].length - 1];
	
				for (int j = 0; j < rowData[0].length; j++) {
					rowData[0][j] = data[i][j];
				}
	
				if (retData.length == 0) {
					retData = rowData;
				} else {
					retData = appendRows(retData, rowData);
				}
			}
		}
	
		return retData;
	}
	
	double[][] pickColumnData(String colName, String[][] data) {
		int colIndex = 0;
		for (int i = 0; i < descrArray.length; i++) {
			String tempColName = descrArray[i];
			if (colName.equals(tempColName) == true) {
				colIndex = i;
				break;
			}
		}
	
		double[][] colData = new double[data.length][1];
	
		for (int i = 0; i < data.length; i++) {
			colData[i][0] = Double.parseDouble(data[i][colIndex]);
		}
	
		return colData;
	}
	
	double[] convDblDimToSinDim(double[][] dblData)
	{
		double[] singData = new double[dblData.length];
		
		for (int i = 0; i < dblData.length; i++) {
			singData[i] = dblData[i][0];
		}
		
		return singData;
	}
	
	public void prepareTemplate1() {
		ArrayList<String> grpVariables = tableWizardinputs.getGroupVariables();
	
		int[] columnIndices = getColumnIndices(grpVariables);
	
		Vector vec = new Vector();
	
		for (int i = 0; i < columnIndices.length; i++) {
			vec.add(columnIndices[i] + "");
		}
		String[][] sortedData = TMMultiLevelSort.sortData(inputMatrix, vec);
	
		String[][] columnToAppend = new String[inputMatrix.length][1];
	
		Vector ulForGVsCombn = new Vector();
	
		for (int i = 0; i < inputMatrix.length; i++) {
	
			String tmpStr = new String();
	
			for (int j = 0; j < columnIndices.length; j++) {
				tmpStr += inputMatrix[i][columnIndices[j]];
			}
			columnToAppend[i][0] = tmpStr;
	
			if (ulForGVsCombn.contains(tmpStr) != true)
				ulForGVsCombn.add(tmpStr);
		}
	
		String[][] sortedAppendedData = appendColumns(sortedData,
				columnToAppend);
	
		printMatrix("Sorted Appended Data", sortedAppendedData,
				sortedAppendedData.length, sortedAppendedData[0].length);
	
		for (int i = 0; i < ulForGVsCombn.size(); i++) {
	
			String level = (String) ulForGVsCombn.get(i);
			String[][] levelData = pickDataForLevel(level, sortedAppendedData);
	
			printMatrix("levelData for-->" + level, levelData,
					levelData.length, levelData[0].length);
			ArrayList<String> ordVariables = tableWizardinputs.getOrdinaryVariables();
			for (int j = 0; j < ordVariables.size(); j++) {
				
				String ordVariableName = ordVariables.get(j);
				double[][] varData = pickColumnData(ordVariableName,
						levelData);
				
				P2.println("ordVariableName.."+ordVariableName);
				
				double[] singVarData = convDblDimToSinDim(varData);
				
				double max = StatUtils.max(singVarData);
				double min = StatUtils.min(singVarData);
				double mean = StatUtils.mean(singVarData);
				double median = StatUtils.percentile(singVarData, 50);
				
				P2.println("maximum.."+max+" minimum:::"+min+" Mean:::"+mean+" Median:::"+median);
			}
	
		}
	
	}
	
	/*public static PrintStream P2;
	public static void main(String[] args) {
		
		try {
			P2 = new PrintStream(new FileOutputStream("output.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Template1Impl ti1 = new Template1Impl();
		ti1.initializeSetup();
		ti1.prepareTemplate1();
	}*/
	
	
}

