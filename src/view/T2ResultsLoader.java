package view;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import Model.Template12Results;

public class T2ResultsLoader {

	String[][] data;
	String[] headers;

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

	public String[] convDblDimStrArrToSinDimArr(String[][] ipArray) {
		String[] opArray = new String[ipArray[0].length];

		for (int i = 0; i < opArray.length; i++) {
			opArray[i] = ipArray[0][i];
		}

		return opArray;
	}

	public String[] genTblHdrForResultsTab(String compKey,
			Hashtable<String, Hashtable<String, Double>> htStatMetricListing) {
		String[][] colHeaders = null;

		boolean firstTimeFlag = true;

		StringTokenizer sTokenizer = new StringTokenizer(compKey, "#");
		int tokenCount = sTokenizer.countTokens();

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ArrayList<String> grpVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getGroupVariables();

		ArrayList<String> idVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getIdVariables();

		ArrayList<String> ordVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getOrdinaryVariables();

		colHeaders = new String[1][grpVariables.size() + idVariables.size()
				+ ordVariables.size()];

		int indexer = 0;
		for (int i = 0; i < grpVariables.size(); i++) {
			colHeaders[0][i] = grpVariables.get(i);
			indexer++;
		}

		for (int i = 0; i < idVariables.size(); i++) {
			colHeaders[0][indexer] = idVariables.get(i);
			indexer++;
		}

		for (int i = 0; i < ordVariables.size(); i++) {
			colHeaders[0][indexer] = ordVariables.get(i);
			indexer++;
		}

		return convDblDimStrArrToSinDimArr(colHeaders);
	}

	public void template2Handling(
			Hashtable<String, Hashtable<String, Double>> htStatMetricListing) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Template12Results[] temp2Results = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getWorkSheetOutputs().getTemplate2Results();

		String compKey = temp2Results[0].getCompKey();

		data = new String[0][0];

		boolean firstOccurenceFlag = true;

		for (int i = 0; i < temp2Results.length; i++) {
			Template12Results t1ResForCompKey = temp2Results[i];
			String compositKey = t1ResForCompKey.getCompKey();

			String[][] dataForCompKey = t1ResForCompKey.getLevelData();

			if (firstOccurenceFlag == true) {
				data = dataForCompKey;
			} else {
				data = appendRows(data, dataForCompKey);
			}
			firstOccurenceFlag = false;
		}

		data = pickTemplate2Data(data);

		headers = genTblHdrForResultsTab(compKey, htStatMetricListing);
	}

	private String[][] pickTemplate2Data(String[][] data) {
		String[][] template2Data = new String[data.length][0];

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ArrayList<String> grpVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getGroupVariables();

		ArrayList<String> idVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getIdVariables();

		ArrayList<String> ordVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getOrdinaryVariables();

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

	String[][] pickColData(String colName, String[][] data) {
		int colIndex = 0;

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ArrayList<ColumnProperties> colProps = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList();

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

	public double round(double value, int decimalPlace) {
		double power_of_ten = 1;
		while (decimalPlace-- > 0) {
			power_of_ten *= 10.0;
		}
		return Math.round(value * power_of_ten) / power_of_ten;
	}

}
