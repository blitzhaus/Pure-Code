package view;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger; 

import org.apache.commons.lang.StringUtils;

import Model.GroupCVDetails;
import Model.Template12Results;

public class T6ResultsLoader {

	String[][] data;
	String[] headers;
	
	PrintStream P2 = null;

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
	
	
	int ncr(int n, int r)
	{
		int nf=factorial(n); 
		int rf=factorial(r); 
		int nrf=factorial(n-r); 
		int npr=nf/nrf; 
		int ncr=npr/rf; 
		
		return ncr;
	}
	
	int npr(int n, int r)
	{
		int nf=factorial(n); 
		int rf=factorial(r); 
		int nrf=factorial(n-r); 
		int npr=nf/nrf; 		
		return npr;
	}

	public int factorial(int n) {
		int j, result;
		result = 1;
		for (j = n; j >= 1; j--)
			result = result * j;
		return result;
	}
	
	private String[][] ignoreFirstRow(String[][] sheetData)
	{
		String[][] sheetDataSet = new String[sheetData.length-1][sheetData[0].length];
		
		int index = 0;
		
		for (int i = 1; i < sheetDataSet.length; i++) {
			
			for (int j = 0; j < sheetDataSet[i].length; j++) {
				sheetDataSet[index][j] = sheetData[i][j];
			}
			
			index++;			
		}
		return sheetDataSet;
		
	}
	
	String[][] getSelectedSheetData()
	{
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		int projIndex = appInfo.getSelectedProjectIndex(); 
		int workBookIndex = appInfo.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getSelectedWorkBookIndex();
		int selSheetIndex = appInfo.getProjectInfoAL().get(projIndex)
				.getWorkBooksArrayList().get(workBookIndex)
				.getSelectedSheetIndex();
		
		String[][] sheetData = appInfo.getProjectInfoAL().get(projIndex).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getDataStructuresArrayList().get(selSheetIndex);
		
		return sheetData;
	}

	private ArrayList<String> pickUniqueLevelsOfGroupVariable()
	{
		ArrayList<String> strListing = new ArrayList<String>();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
	
		String[][] sheetData = getSelectedSheetData();
				
		sheetData = ignoreFirstRow(sheetData);
		
		
		ArrayList<String> grpVariables = getTMInfoInstance().getGroupVariables();
		
		String grVarName = grpVariables.get(0);
		
		String[][] groupData = pickColData(grVarName, sheetData);
		
		Vector vec = new Vector();
		
		for (int i = 0; i < groupData.length; i++) {
			if (vec.contains(groupData[i][0]) != true) {

				if (groupData[i][0] != null) {
					vec.add(groupData[i][0]);
					strListing.add(groupData[i][0]);
				}
			}
		}		
		
		return strListing;		
	}
	
	TableMavenInfo getTMInfoInstance()
	{
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		int projIndex = appInfo.getSelectedProjectIndex(); 
		int workBookIndex = appInfo.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getSelectedWorkBookIndex();
		int selSheetIndex = appInfo.getProjectInfoAL().get(projIndex)
				.getWorkBooksArrayList().get(workBookIndex)
				.getSelectedSheetIndex();
		
		TableMavenInfo tmInfo = appInfo.getProjectInfoAL().get(projIndex)
				.getWorkBooksArrayList().get(workBookIndex)
				.getWorkSheetObjectsAL().get(selSheetIndex).getTmInfo();
		
		return tmInfo;
	}
	
	
	private ArrayList<String> pickUniqueLevelsOfCrossVariable()
	{
		ArrayList<String> strListing = new ArrayList<String>();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[][] sheetData = getSelectedSheetData();
		
		sheetData = ignoreFirstRow(sheetData);
		
		ArrayList<String> grpVariables = getTMInfoInstance().getCrossVariables();
		
		String crossVarName = grpVariables.get(0);
		
		String[][] crossVarData = pickColData(crossVarName, sheetData);
		
		Vector vec = new Vector();
		
		for (int i = 0; i < crossVarData.length; i++) {
			if (vec.contains(crossVarData[i][0]) != true) {
				if (crossVarData[i][0] != null) {
					vec.add(crossVarData[i][0]);
					strListing.add(crossVarData[i][0]);
				}
			}
		}		
		
		return strListing;		
	}
	
	private Model.Template12Results pickAptResultsStruct(String compKey, Model.Template12Results[] temp6Results)
	{
		Model.Template12Results tempResultsStruct = null;
		for (int i = 0; i < temp6Results.length; i++) {
			Model.Template12Results tmpRes = temp6Results[i];
			
			if (tmpRes.getCompKey().equals(compKey)==true)
			{
				tempResultsStruct = tmpRes;
				break;
			}
			
		}
		
		return tempResultsStruct;
	}
	
	public void printMatrix(String str, String[][] matrix, int rowDim, int colDim)
	{
		P2.println("Input String:"+str);
		
		P2.println("row Dimension:"+rowDim+"\n");
		P2.println("col Dimension:"+colDim+"\n");

		

		for (int r = 0 ; r < rowDim; r++)
		{
			for (int c = 0 ; c < colDim; c++)
			{
				P2.print(matrix[r][c]+"\t");
			}

			P2.println();
		}

		P2.println("\n");
	}
	
	public String[][] printBorder(String str, String[][] matrix, int rowDim, int colDim)
	{
		String[][] retMatrix = new String[1][matrix[0].length];
		
			for (int c = 0 ; c < colDim; c++)
			{
				retMatrix[0][c] = "***********************************";
			}

			return retMatrix;		
	}

	
	private String[][] trimPrecedingColumns(String[][] data)
	{
		
		String[][] trimmedData = null;
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		ArrayList<String> idVariables = getTMInfoInstance().getIdVariables();
		
		System.out.println("idVariables size"+idVariables.size());
		
		int noOfColsToTrim =  idVariables.size() + 1;
		
		for (int i = 0; i < data[0].length; i++) {
			
			if (i < noOfColsToTrim)
			{
				continue;
			}
			else
			{
				String[][] colData = pickColumnDataByIndex(i, data);
				
				if (trimmedData == null)
				{
					trimmedData = colData;
				}
				else
				{
					trimmedData = appendColumns(trimmedData, colData);
				}
			}
			
		}
			
		return trimmedData;
		
	}
	
	
	public void template6Handling(
			Hashtable<String, Hashtable<String, Double>> htStatMetricListing) {

		String FileName = "op_trace.txt";
		try {
			P2 = new PrintStream(new FileOutputStream(FileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Template12Results[] temp6Results = getTMInfoInstance().getWorkSheetOutputs().getTemplate6Results();

		ArrayList<String> grpVarLevelsUnique = pickUniqueLevelsOfGroupVariable();
		ArrayList<String> crossVarLevelsUnique = pickUniqueLevelsOfCrossVariable();

		boolean foRowFlag = true;
		String[][] actualDataRow = new String[0][0];

		for (int i = 0; i < grpVarLevelsUnique.size(); i++) {

			String grpLevelcurrent = grpVarLevelsUnique.get(i);
			
			P2.println("grpLevelcurrent...."+grpLevelcurrent);

			boolean foColFlag = true;

			String[][] actualDataColumn = new String[0][0];

			for (int j = 0; j < crossVarLevelsUnique.size(); j++) {

				String crossVarLevelcurrent = crossVarLevelsUnique.get(j);
				
				P2.println("crossVarLevelcurrent..."+crossVarLevelcurrent);

				String compKey = "#" + grpLevelcurrent + "#"
						+ crossVarLevelcurrent;

				Model.Template12Results t1ResForCompKey = pickAptResultsStruct(
						compKey, temp6Results);

				String compositKey = t1ResForCompKey.getCompKey();

				htStatMetricListing = t1ResForCompKey.getVarStatMetrics();
				String[][] dataForCompKey = t1ResForCompKey.getLevelData();
				dataForCompKey = pickTemplate6Data(dataForCompKey);
				String[][] statDataForCompKey = displayStatMetrics(compositKey,
						htStatMetricListing);

				if (foColFlag == true) {
					
					//printMatrix("data for comp key..", dataForCompKey,
							//dataForCompKey.length, dataForCompKey[0].length);
					//printMatrix("statDataForCompKey..", statDataForCompKey,
							//statDataForCompKey.length,
							//[0].length);
					String[][] currLvlActStatData = appendRows(
							dataForCompKey, statDataForCompKey);
					
					actualDataColumn = currLvlActStatData;
				} else {
					
				
					String[][] currLvlActStatData = appendRows(
							dataForCompKey, statDataForCompKey);
					
					currLvlActStatData = trimPrecedingColumns(currLvlActStatData);
					
					actualDataColumn = appendColumns(actualDataColumn,
							currLvlActStatData);
				}
				foColFlag = false;
				
				//printMatrix("actualDataColumn..", actualDataColumn, actualDataColumn.length, actualDataColumn[0].length);
			}

			if (foRowFlag == true) {
				actualDataRow = actualDataColumn;
			} else {

				actualDataRow = appendRows(actualDataRow, actualDataColumn);
			}
			foRowFlag = false;
			
			//printMatrix("actualDataRow..", actualDataRow, actualDataRow.length, actualDataRow[0].length);
		}
		
		data = actualDataRow;
		
		printMatrix("data..", data, data.length, data[0].length);
		
		String[][] headersData = genTblHdrForResultsTab();
		
		String[][] headersBorder = printBorder("border", headersData, headersData.length, headersData[0].length);
		
		headersData = appendRows(headersData, headersBorder);
		
		data = appendRows(headersData, data);
		
		String[][] border = printBorder("border", data, data.length, data[0].length);
		
		data = appendRows(border, data);
		
		data = appendRows(data, border);
		
		headers = data[data.length-1];
	}
	
	private String[][] genTblHdrForResultsTab() {
		// 
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		ArrayList<String> grpVariables = getTMInfoInstance().getGroupVariables();
		
		ArrayList<String> idVariables = getTMInfoInstance().getIdVariables();
		
		ArrayList<String> crossVariables = getTMInfoInstance().getCrossVariables();
		
		ArrayList<String> ordVariables = getTMInfoInstance().getOrdinaryVariables();
		
		boolean foHeaderFlag = true;
		String[][] actualHeaderData = new String[0][0];
		
		ArrayList<String> crossVarLevelsUnique = pickUniqueLevelsOfCrossVariable();
		
		
		for (int i = 0; i < crossVarLevelsUnique.size(); i++) {
			String[][] hdrComp = new String[2][ordVariables.size()];
			
			hdrComp[0][0] = crossVarLevelsUnique.get(i);
			
			for (int j = 0; j < ordVariables.size(); j++) {
				hdrComp[1][j] = ordVariables.get(j);
			}
			
			if (foHeaderFlag == true)
			{
				actualHeaderData = hdrComp;
			}
			else
			{
				actualHeaderData = appendColumns(actualHeaderData, hdrComp);
			}
			
			printMatrix("actualHeaderData.." + i + ".." + hdrComp[0][0],
					actualHeaderData, actualHeaderData.length,
					actualHeaderData[0].length);
			
			foHeaderFlag = false;
		}
		
		String[][] topHeader = new String[1][actualHeaderData[0].length];
		topHeader[0][0] = crossVariables.get(0);
		
		topHeader = appendRows(topHeader, actualHeaderData);
		
		String[][] leftTopHeader = new String[3][grpVariables.size()+idVariables.size()];
		
		for (int i = 0; i < grpVariables.size(); i++) {
			leftTopHeader[2][i] = grpVariables.get(i);
		}
		
		int indexer = grpVariables.size();
		
		for (int i = 0; i < idVariables.size(); i++) {
			leftTopHeader[2][indexer] = idVariables.get(i);
			indexer++;
		}
		
		String[][] headersData = appendColumns(leftTopHeader, topHeader);
		
		printMatrix("headersData..",
				headersData, headersData.length,
				headersData[0].length);
		
		return headersData;
	}

	String[][] displayStatMetrics(String compositeKey, Hashtable<String, Hashtable<String,Double>> htStatMetricListing)
	{
		
		StringTokenizer sTokenizer = new StringTokenizer(compositeKey, "#");
		int tokenCount = sTokenizer.countTokens();
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		ArrayList<String> grpVariables = getTMInfoInstance().getGroupVariables();
		
		String[] compKeylist = new String[tokenCount];
		
		for (int i = 0; i < tokenCount; i++) {
			compKeylist[i] = sTokenizer.nextToken();
		}
		
		int sze = htStatMetricListing.size();
		
		System.out.println("no of ordinary variables"+sze);
		
		Enumeration e = htStatMetricListing.keys();
		boolean firstTimeFlag = true;
		
		int rCount = 0;
		
		while(e.hasMoreElements())
		{
			String ordVarName = (String) e.nextElement();
			Hashtable<String,Double> statMetricValuePairs = htStatMetricListing.get(ordVarName);
			rCount = statMetricValuePairs.size();			
		}
		
		String[][] dataToDisplay = new String[rCount][];
		
		Enumeration e1 = htStatMetricListing.keys();
		
		while(e1.hasMoreElements())
		{
			String ordVarName = (String) e1.nextElement();
			
			Hashtable<String,Double> statMetricValuePairs = htStatMetricListing.get(ordVarName);
						
			String[][] tempDataToDisplay;
			if (firstTimeFlag == true)
			{
				tempDataToDisplay = new String[statMetricValuePairs.size()+1][tokenCount+1];
			}
			else
			{
				tempDataToDisplay = new String[statMetricValuePairs.size()+1][1];
			}
			
			int counter = 0;
			
			Enumeration enumerator = statMetricValuePairs.keys();
			while(enumerator.hasMoreElements())
			{
				String statMetricKey = (String) enumerator.nextElement();
				double metricValue = (Double) statMetricValuePairs.get(statMetricKey).doubleValue();
				
				if (firstTimeFlag == true)
				{
					//for (int i = 0; i < tokenCount; i++) {
						tempDataToDisplay[counter][0] = StringUtils.capitalize((String)compKeylist[0]);
					//}
					tempDataToDisplay[counter][tokenCount-1] = StringUtils.rightPad(statMetricKey,  30);
					tempDataToDisplay[counter][tokenCount] = StringUtils.rightPad(round(metricValue,4)+"", 40);
				}
				else
				{
					tempDataToDisplay[counter][0] = StringUtils.rightPad(round(metricValue,4)+"",  40);
				}
				
				counter++;
			}
			
			if (firstTimeFlag == true)
			{
				dataToDisplay = tempDataToDisplay;
			}
			else
			{
				dataToDisplay = appendColumns(dataToDisplay, tempDataToDisplay);
			}
			
			firstTimeFlag = false;
		}
		
		for (int i = 0; i < dataToDisplay.length; i++) {
			
			String rowConcatenated = StringUtils.join(dataToDisplay[i], "\t");
		}
		
		return dataToDisplay;
	}
	
	public String[] convDblDimStrArrToSinDimArr(String[][] ipArray)
	{
		String[] opArray = new String[ipArray[0].length];
		
		for (int i = 0; i < opArray.length; i++) {
			opArray[i] = ipArray[0][i];
		}
		
		return opArray;
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

	private String[][] pickTemplate6Data(String[][] data) {

		String[][] template2Data = new String[data.length][0];

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ArrayList<String> grpVariables = getTMInfoInstance().getGroupVariables();

		ArrayList<String> idVariables = getTMInfoInstance().getIdVariables();

		ArrayList<String> ordVariables = getTMInfoInstance().getOrdinaryVariables();

		for (int i = 0; i < grpVariables.size(); i++) {
			String[][] grpVarData = pickColData(grpVariables.get(i), data);

			if (template2Data[0].length == 0) {
				template2Data = grpVarData;
			} else {
				template2Data = appendColumns(template2Data, grpVarData);
			}
		}

		for (int i = 0; i < idVariables.size(); i++) {
			String[][] idVarData = pickColData(idVariables.get(i), data);
			template2Data = appendColumns(template2Data, idVarData);
		}

		for (int i = 0; i < ordVariables.size(); i++) {
			String[][] ordVarData = pickColData(ordVariables.get(i), data);
			template2Data = appendColumns(template2Data, ordVarData);
		}

		return template2Data;
	}
	
	

	
			
	WorkSheetsInfo getWorkSheetsInfo() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		int projIndex = appInfo.getSelectedProjectIndex();
		int workBookIndex = appInfo.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getSelectedWorkBookIndex();
		int selSheetIndex = appInfo.getProjectInfoAL().get(projIndex)
				.getWorkBooksArrayList().get(workBookIndex)
				.getSelectedSheetIndex();

		WorkSheetsInfo wsInfo = appInfo.getProjectInfoAL().get(projIndex)
				.getWorkBooksArrayList().get(workBookIndex)
				.getWorkSheetObjectsAL().get(selSheetIndex);

		return wsInfo;
	}

	String[][] pickColData(String colName, String[][] data) {
		int colIndex = 0;

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ArrayList<ColumnProperties> colProps = getWorkSheetsInfo().getColumnPropertiesArrayList();

		String[] descrArray = new String[colProps.size()];

		for (int i = 0; i < descrArray.length; i++) {
			ColumnProperties colProp = colProps.get(i);
			descrArray[i] = colProp.getColumnName();
		}
		for (int i = 0; i < descrArray.length; i++) {
			String tempColName = descrArray[i];
			if (colName.equals(tempColName) == true) {
				colIndex = i;
				break;
			}
		}

		String[][] colData = new String[data.length][1];

		for (int i = 0; i < data.length; i++) {
			colData[i][0] = data[i][colIndex];
		}

		return colData;
	}
	
	
	private String[][] pickColumnDataByIndex(int colIndex, String[][] data) {

		String[][] colData = new String[data.length][1];

		for (int i = 0; i < data.length; i++) {
			colData[i][0] = data[i][colIndex];
		}

		return colData;
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
	
	public double round(double value, int decimalPlace)
	{
	    double power_of_ten = 1;
	    while (decimalPlace-- > 0)
	    {
	      power_of_ten *= 10.0;
	    }
	    return Math.round(value * power_of_ten)/ power_of_ten;
	}

}
