package view;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import Common.Iconizing;
import Common.JinternalFrameFunctions;
import Model.Template12Results;

public class T1ResultsLoader {

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

		Enumeration e1 = htStatMetricListing.keys();

		while (e1.hasMoreElements()) {
			String ordVarName = (String) e1.nextElement();

			Hashtable<String, Double> statMetricValuePairs = htStatMetricListing
					.get(ordVarName);

			if (firstTimeFlag == true) {
				String[][] colHeadersTmp = new String[statMetricValuePairs
						.size() + 1][tokenCount + 2];

				for (int i = 0; i < tokenCount; i++) {
					colHeadersTmp[0][i] = StringUtils
							.capitalize((String) grpVariables.get(i));
				}
				colHeadersTmp[0][tokenCount] = StringUtils.rightPad("", 30);
				colHeadersTmp[0][tokenCount + 1] = StringUtils.rightPad(
						ordVarName, 30);

				colHeaders = colHeadersTmp;
			} else {
				String[][] colHeadersTmp = new String[statMetricValuePairs
						.size() + 1][1];
				colHeadersTmp[0][0] = StringUtils.rightPad(ordVarName, 30);

				colHeaders = appendColumns(colHeaders, colHeadersTmp);
			}

			firstTimeFlag = false;

		}

		return convDblDimStrArrToSinDimArr(colHeaders);
	}

	public void template1Handling(
			Hashtable<String, Hashtable<String, Double>> htStatMetricListing) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Template12Results[] temp1Results = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getWorkSheetOutputs().getTemplate1Results();

		String compKey = temp1Results[0].getCompKey();

		data = new String[0][0];

		boolean firstOccurenceFlag = true;

		for (int i = 0; i < temp1Results.length; i++) {
			Template12Results t1ResForCompKey = temp1Results[i];
			String compositKey = t1ResForCompKey.getCompKey();

			htStatMetricListing = t1ResForCompKey.getVarStatMetrics();

			String[][] dataForCompKey = displayStatMetrics(compositKey,
					htStatMetricListing);

			if (firstOccurenceFlag == true) {
				data = dataForCompKey;
			} else {
				data = appendRows(data, dataForCompKey);
			}
			firstOccurenceFlag = false;
		}

		headers = genTblHdrForResultsTab(compKey, htStatMetricListing);

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

	String[][] displayStatMetrics(String compositeKey,
			Hashtable<String, Hashtable<String, Double>> htStatMetricListing) {

		StringTokenizer sTokenizer = new StringTokenizer(compositeKey, "#");
		int tokenCount = sTokenizer.countTokens();

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ArrayList<String> grpVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getGroupVariables();

		String[] compKeylist = new String[tokenCount];

		for (int i = 0; i < tokenCount; i++) {
			compKeylist[i] = sTokenizer.nextToken();
		}

		int sze = htStatMetricListing.size();

		System.out.println("no of ordinary variables" + sze);

		Enumeration e = htStatMetricListing.keys();
		boolean firstTimeFlag = true;

		int rCount = 0;

		while (e.hasMoreElements()) {
			String ordVarName = (String) e.nextElement();
			Hashtable<String, Double> statMetricValuePairs = htStatMetricListing
					.get(ordVarName);
			rCount = statMetricValuePairs.size();
		}

		String[][] dataToDisplay = new String[rCount][];

		Enumeration e1 = htStatMetricListing.keys();

		while (e1.hasMoreElements()) {
			String ordVarName = (String) e1.nextElement();

			Hashtable<String, Double> statMetricValuePairs = htStatMetricListing
					.get(ordVarName);

			String[][] tempDataToDisplay;
			if (firstTimeFlag == true) {
				tempDataToDisplay = new String[statMetricValuePairs.size() + 1][tokenCount + 2];

				for (int i = 0; i < tokenCount; i++) {
					tempDataToDisplay[0][i] = StringUtils
							.capitalize((String) grpVariables.get(i));
				}
				tempDataToDisplay[0][tokenCount] = StringUtils.rightPad("", 30);
				tempDataToDisplay[0][tokenCount + 1] = StringUtils.rightPad(
						ordVarName, 30);
			} else {
				tempDataToDisplay = new String[statMetricValuePairs.size() + 1][1];
				tempDataToDisplay[0][0] = StringUtils.rightPad(ordVarName, 30);
			}

			int counter = 0;

			Enumeration enumerator = statMetricValuePairs.keys();
			while (enumerator.hasMoreElements()) {
				String statMetricKey = (String) enumerator.nextElement();
				double metricValue = (Double) statMetricValuePairs.get(
						statMetricKey).doubleValue();

				if (firstTimeFlag == true) {
					for (int i = 0; i < tokenCount; i++) {
						tempDataToDisplay[counter][i] = StringUtils
								.capitalize((String) compKeylist[i]);
					}
					tempDataToDisplay[counter][tokenCount] = StringUtils
							.rightPad(statMetricKey, 30);
					tempDataToDisplay[counter][tokenCount + 1] = StringUtils
							.rightPad(round(metricValue, 4) + "", 40);
				} else {
					tempDataToDisplay[counter][0] = StringUtils.rightPad(round(
							metricValue, 4)
							+ "", 40);
				}

				counter++;
			}

			if (firstTimeFlag == true) {
				dataToDisplay = tempDataToDisplay;
			} else {
				dataToDisplay = appendColumns(dataToDisplay, tempDataToDisplay);
			}

			firstTimeFlag = false;
		}

		for (int i = 0; i < dataToDisplay.length; i++) {

			String rowConcatenated = StringUtils.join(dataToDisplay[i], "\t");
		}

		return dataToDisplay;
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
