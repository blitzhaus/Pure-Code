package Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.commons.math.stat.StatUtils;

import view.ApplicationInfo;
import view.DisplayContents;
import view.TableMavenInfo;
import Jama.Matrix;
import edu.umbc.cs.maple.utils.JamaUtils;

public class Template6Impl {

	private String[][] inputMatrix;
	private int rowCount = 0;
	private int colCount = 0;
	private String[] stringArray;
	private String[] descrArray;
	private TableWizardInputOptions tableWizardinputs;
	private TableWizardOpMetricOptions opMetListing;
	TableWizardCoordinator tempImpl;

	public Template6Impl(TableWizardCoordinator tempImpl) {

		this.tempImpl = tempImpl;
		rowCount = tempImpl.getRowCount();
		colCount = tempImpl.getColCount();
		inputMatrix = tempImpl.getInputMatrix();
		stringArray = tempImpl.getStringArray();
		descrArray = tempImpl.getDescrArray();

		tempImpl.printMatrix("Input data..", inputMatrix, inputMatrix.length,
				inputMatrix[0].length);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.tcs.tablemaven.Template#setTabWizIpOptions(java.util.ArrayList,
	 * java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, int)
	 */
	public void setTabWizIpOptions(ArrayList<String> groupVariables,
			ArrayList<String> idVariables, ArrayList<String> crossVariables,
			ArrayList<String> ordinaryVariables, int templateType) {

		tableWizardinputs = new TableWizardInputOptions(templateType,
				groupVariables, idVariables, crossVariables, ordinaryVariables);
	}

	public Template12Results[] prepareTemplate6ForGrpCvCombn(
			String grpVariable, String crossVariable) {
		// P2.println("*************************Group Variable Name.."
		// + grpVariable + "*************************");
		// P2.println("#########################Cross Variable Name."
		// + crossVariable + "#########################");

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		int columnIndexOfGrp = tempImpl.getColumnIndex(grpVariable);
		int columnIndexOfCrossVar = tempImpl.getColumnIndex(crossVariable);

		ArrayList<String> vec = new ArrayList<String>();

		vec.add(columnIndexOfGrp + "");
		vec.add(columnIndexOfCrossVar + "");

		ArrayList<String> idVariables = tableWizardinputs.getIdVariables();

		int[] columnIndices = tempImpl.getColumnIndices(idVariables);

		for (int i = 0; i < columnIndices.length; i++) {
			vec.add(columnIndices[i] + "");
		}

		ArrayList<String> numColIndices = new ArrayList<String>();

		for (int i = 0; i < columnIndices.length; i++) {
			numColIndices.add(columnIndices[i] + "");
		}

		/*
		 * Sort the data on the basis of group variable first followed by cross
		 * variable.
		 */
		String[][] sortedData = TMMultiLevelSort.sortData(inputMatrix, vec,
				numColIndices);

		String[][] columnToAppend = new String[inputMatrix.length][1];

		Vector ulForGVsCombn = new Vector();

		for (int i = 0; i < inputMatrix.length; i++) {

			String tempStr = new String();

			tempStr = inputMatrix[i][columnIndexOfGrp];
			tempStr += "#" + inputMatrix[i][columnIndexOfCrossVar];

			columnToAppend[i][0] = tempStr;

			if (ulForGVsCombn.contains(tempStr) != true)
				ulForGVsCombn.add(tempStr);
		}

		String[][] sortedAppendedData = tempImpl.appendColumns(sortedData,
				columnToAppend);

		Template12Results[] temp1Results = new Template12Results[ulForGVsCombn
				.size()];

		for (int i = 0; i < ulForGVsCombn.size(); i++) {

			String level = (String) ulForGVsCombn.get(i);

			temp1Results[i] = new Template12Results();
			temp1Results[i].setCompKey(level);
			String[][] levelData = tempImpl.pickDataForLevel(level,
					sortedAppendedData);

			// P2.println("level details.." + level);

			temp1Results[i].setLevelData(levelData);

			tempImpl.printMatrix("levelData for-->" + level, levelData,
					levelData.length, levelData[0].length);

			ArrayList<String> ordVariables = tableWizardinputs
					.getOrdinaryVariables();

			Hashtable<String, Hashtable<String, Double>> htOrdVarStatMetrics = new Hashtable<String, Hashtable<String, Double>>();

			for (int j = 0; j < ordVariables.size(); j++) {

				String ordVariableName = ordVariables.get(j);
				double[][] varData = tempImpl.pickColumnData(ordVariableName,
						levelData);

				// P2.println("ordVariableName.." + ordVariableName);

				double[] singVarData = tempImpl.convDblDimToSinDim(varData);

				Hashtable<String, Boolean> tmStatMetricOptions = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getTmInfo()
						.getTmOpMetOptions().getStatMetricsListing();

				Enumeration e = tmStatMetricOptions.keys();

				Hashtable<String, Double> htStatMetricValues = new Hashtable<String, Double>();

				while (e.hasMoreElements()) {
					String keyMetric = (String) e.nextElement();// statistical
																// metric name
					double statMetric = 0.0;

					if (tmStatMetricOptions.get(keyMetric).booleanValue() == true) {
						statMetric = computeStatMetric(keyMetric, singVarData);
					}

					htStatMetricValues.put(keyMetric, Double
							.valueOf(statMetric));
				}

				htOrdVarStatMetrics.put(ordVariableName, htStatMetricValues);
			}

			temp1Results[i].setVarStatMetrics(htOrdVarStatMetrics);
		}

		return temp1Results;

	}
	
	private int[] appendArrays(int[] arr1, int[] arr2)
	{
		int[] array = new int[arr1.length + arr2.length];
		
		for (int i = 0; i < arr1.length; i++) {
			array[i] = arr1[i];
		}
		
		int counter = 0;
		
		for (int i = arr1.length; i < array.length; i++) {
			array[i] = arr2[counter];
			counter++;
		}
		
		return array;
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

	public Template12Results[] prepareTemplate() {
		ArrayList<String> grpVariables = tableWizardinputs.getGroupVariables();

		int[] columnIndices = tempImpl.getColumnIndices(grpVariables);

		ArrayList<String> vec = new ArrayList<String>();

		for (int i = 0; i < columnIndices.length; i++) {
			vec.add(columnIndices[i] + "");
		}
		
		ArrayList<String> crossVariables = tableWizardinputs.getCrossVariables();

		columnIndices = tempImpl.getColumnIndices(crossVariables);

		for (int i = 0; i < columnIndices.length; i++) {
			vec.add(columnIndices[i] + "");
		}
		
		ArrayList<String> idVariables = tableWizardinputs.getIdVariables();

		columnIndices = tempImpl.getColumnIndices(idVariables);

		for (int i = 0; i < columnIndices.length; i++) {
			vec.add(columnIndices[i] + "");
		}
		
		ArrayList<String> numColIndices = new ArrayList<String>();

		for (int i = 0; i < columnIndices.length; i++) {
			numColIndices.add(columnIndices[i] + "");
		}
		
		String[][] sortedData = TMMultiLevelSort.sortData(inputMatrix, vec, numColIndices);
		
		tempImpl.printMatrix("Template-6..", sortedData, sortedData.length, sortedData[0].length);
		
		String[][] columnToAppend = new String[inputMatrix.length][1];

		Vector ulForGVsCombn = new Vector();
		
		int[] columnIndices1 = tempImpl.getColumnIndices(grpVariables);
		int[] columnIndices2 = tempImpl.getColumnIndices(crossVariables);
		
		int[] colIndices = appendArrays(columnIndices1, columnIndices2);

		for (int i = 0; i < inputMatrix.length; i++) {

			String tmpStr = new String();

			for (int j = 0; j < colIndices.length; j++) {
				tmpStr += "#"+inputMatrix[i][colIndices[j]];
			}
			columnToAppend[i][0] = tmpStr;

			if (ulForGVsCombn.contains(tmpStr) != true)
				ulForGVsCombn.add(tmpStr);
		}

		String[][] sortedAppendedData = tempImpl.appendColumns(sortedData,
				columnToAppend);

		tempImpl.printMatrix("Sorted Appended Data", sortedAppendedData,
				sortedAppendedData.length, sortedAppendedData[0].length);
		
		Template12Results[] temp1Results = new Template12Results[ulForGVsCombn.size()];

		for (int i = 0; i < ulForGVsCombn.size(); i++) {

			String level = (String) ulForGVsCombn.get(i);
			
			temp1Results[i] = new Template12Results();
			temp1Results[i].setCompKey(level);
			String[][] levelData = tempImpl.pickDataForLevel(level, sortedAppendedData);

			tempImpl.printMatrix("levelData for-->" + level, levelData,
					levelData.length, levelData[0].length);
			
			temp1Results[i].setLevelData(levelData);

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			Hashtable<String, Boolean> htStatMetricListing = getTMInfoInstance()
					.getTmOpMetOptions().getStatMetricsListing();
			ArrayList<String> ordVariables = tableWizardinputs.getOrdinaryVariables();
			
			Hashtable<String, Hashtable<String, Double>> htOrdVarStatMetrics = new Hashtable<String, Hashtable<String, Double>>();
			
			for (int j = 0; j < ordVariables.size(); j++) {

				String ordVariableName = ordVariables.get(j);
				double[][] varData = tempImpl.pickColumnData(ordVariableName, levelData);

				//P2.println("ordVariableName.." + ordVariableName);

				double[] singVarData = tempImpl.convDblDimToSinDim(varData);

				Hashtable<String, Boolean> tmStatMetricOptions = getTMInfoInstance()
						.getTmOpMetOptions().getStatMetricsListing();
				
				Enumeration e = tmStatMetricOptions.keys();
				
				Hashtable<String,Double> htStatMetricValues = new Hashtable<String,Double>();
				
				while(e.hasMoreElements())
				{
					String keyMetric = (String) e.nextElement();// statistical metric name
					double statMetric = 0.0;
					
					if (tmStatMetricOptions.get(keyMetric).booleanValue() == true)
					{
						statMetric = computeStatMetric(keyMetric, singVarData);
					}
					
					htStatMetricValues.put(keyMetric, Double.valueOf(statMetric));
				}
				
				htOrdVarStatMetrics.put(ordVariableName, htStatMetricValues);
			}
			temp1Results[i].setVarStatMetrics(htOrdVarStatMetrics);
		}
		
		return temp1Results;

	}

	private double computeStatMetric(String metricName, double[] data) {
		double metricValue = 0.0;

		if (metricName.equals("N")) {
			metricValue = DDUtils.N(data);
		} else if (metricName.equals("Nmiss")) {
			metricValue = DDUtils.NMiss(data);
		} else if (metricName.equals("Nobs")) {
			metricValue = DDUtils.NObs(data);
		} else if (metricName.equals("Mean")) {
			metricValue = DDUtils.mean(data);
		} else if (metricName.equals("SD")) {
			metricValue = DDUtils.stdDeviation(data);
		} else if (metricName.equals("Variance")) {
			metricValue = DDUtils.variance(data);
		} else if (metricName.equals("Min")) {
			metricValue = DDUtils.minimum(data);
		} else if (metricName.equals("Median")) {
			metricValue = DDUtils.median(data);
		} else if (metricName.equals("Max")) {
			metricValue = DDUtils.maximum(data);
		} else if (metricName.equals("Range")) {
			metricValue = DDUtils.range(data);
		} else if (metricName.equals("Percentiles")) {
			metricValue = DDUtils.percentile(data, 50); // change 50 to
														// appropriate' th
														// percentile.
		} else if (metricName.equals("CV%")) {
			metricValue = DDUtils.cvPercent(data);
		} else if (metricName.equals("CV%")) {
			metricValue = DDUtils.cvPercent(data);
		} else if (metricName.equals("Geometric Mean")) {
			metricValue = DDUtils.geomMean(data);
		} else if (metricName.equals("Harmonic Mean")) {
			metricValue = DDUtils.harmonicMean(data);
		} else if (metricName.equals("Mean of Logs")) {
			metricValue = DDUtils.meanOfLogs(data);
		} else if (metricName.equals("SD of Logs")) {
			metricValue = DDUtils.stdDevnOfLogs(data);
		} else if (metricName.equals("95% CI Lower Mean")) {
			metricValue = DDUtils.confIntLowerMean95PerCent(data);
		} else if (metricName.equals("95% CI Upper Mean")) {
			metricValue = DDUtils.confIntUpperMean95PerCent(data);
		} else if (metricName.equals("Lower 1 SD")) {
			metricValue = DDUtils.lower1SD(data);
		} else if (metricName.equals("95% CI Lower Var")) {
			metricValue = DDUtils.confIntLowerVariance95PerCent(data);
		} else if (metricName.equals("95% CI Upper Var")) {
			metricValue = DDUtils.confIntUpperVariance95PerCent(data);
		}

		return metricValue;
	}

	public static PrintStream P2;

	public static void main(String[] args) {

		try {
			P2 = new PrintStream(new FileOutputStream("output.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TableWizardCoordinator ti = new TableWizardCoordinator(P2);// 
		Template6Impl ti6 = new Template6Impl(ti);

		/*
		 * Table Wizard Input Options
		 */

		ArrayList<String> groupVariables = new ArrayList<String>();
		ArrayList<String> idVariables = new ArrayList<String>();
		ArrayList<String> crossVariables = new ArrayList<String>();
		ArrayList<String> ordinaryVariables = new ArrayList<String>();

		groupVariables.add("Subject_ID");
		idVariables.add("Time");
		crossVariables.add("Formulation");

		ordinaryVariables.add("BP");
		ordinaryVariables.add("Conc");

		int templateType = 1;

		ti6.setTabWizIpOptions(groupVariables, idVariables, crossVariables,
				ordinaryVariables, templateType);

		/*
		 * Table Wizard Output Options
		 */

		Hashtable hTableOpOptions = new Hashtable();
		TableWizardOpMetricOptions opMetListing = new TableWizardOpMetricOptions(
				hTableOpOptions);
		ti6.prepareTemplate();
	}

}
