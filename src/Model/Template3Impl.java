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
import Jama.Matrix;
import edu.umbc.cs.maple.utils.JamaUtils;

public class Template3Impl {

	private String[][] inputMatrix;
	private int rowCount = 0;
	private int colCount = 0;
	private String[] stringArray;
	private String[] descrArray;
	private TableWizardInputOptions tableWizardinputs;
	private TableWizardOpMetricOptions opMetListing;
	TableWizardCoordinator tempImpl;

	public Template3Impl(TableWizardCoordinator tempImpl) {

		this.tempImpl = tempImpl;
		rowCount = tempImpl.getRowCount();
		colCount = tempImpl.getColCount();
		inputMatrix = tempImpl.getInputMatrix();
		stringArray = tempImpl.getStringArray();
		descrArray = tempImpl.getDescrArray();

		tempImpl.printMatrix("Input data..", inputMatrix, inputMatrix.length,
				inputMatrix[0].length);
	}

	public void initializeSetup() {
		ArrayList<String> groupVariables = new ArrayList<String>();
		ArrayList<String> idVariables = new ArrayList<String>();
		ArrayList<String> crossVariables = new ArrayList<String>();
		ArrayList<String> ordinaryVariables = new ArrayList<String>();

		groupVariables.add("Subject_ID");
		groupVariables.add("Formulation");
		groupVariables.add("Route");

		idVariables.add("Time");

		crossVariables.add(" ");

		ordinaryVariables.add("BP");
		ordinaryVariables.add("Conc");

		int templateType = 1;

		tableWizardinputs = new TableWizardInputOptions(templateType,
				groupVariables, idVariables, crossVariables, ordinaryVariables);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.tcs.tablemaven.Template#setTabWizOpOptions(org.tcs.tablemaven.
	 * TableWizardOpMetricOptions)
	 */
	public void setTabWizOpOptions(TableWizardOpMetricOptions opMetListing) {
		this.opMetListing = opMetListing;
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

	public Template12Results[] prepareTemplate() {
		ArrayList<String> grpVariables = tableWizardinputs.getGroupVariables();

		int[] columnIndices = tempImpl.getColumnIndices(grpVariables);

		ArrayList<String> vec = new ArrayList<String>();

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

		String[][] sortedData = TMMultiLevelSort.sortData(inputMatrix, vec,
				numColIndices);

		tempImpl.printMatrix("Template-3..", sortedData, sortedData.length,
				sortedData[0].length);

		String[][] columnToAppend = new String[inputMatrix.length][1];

		Vector ulForGVsCombn = new Vector();

		columnIndices = tempImpl.getColumnIndices(grpVariables);

		for (int i = 0; i < inputMatrix.length; i++) {

			String tmpStr = new String();

			for (int j = 0; j < columnIndices.length; j++) {
				tmpStr += "#" + inputMatrix[i][columnIndices[j]];
			}
			columnToAppend[i][0] = tmpStr;

			if (ulForGVsCombn.contains(tmpStr) != true)
				ulForGVsCombn.add(tmpStr);
		}

		String[][] sortedAppendedData = tempImpl.appendColumns(sortedData,
				columnToAppend);

		tempImpl.printMatrix("Sorted Appended Data", sortedAppendedData,
				sortedAppendedData.length, sortedAppendedData[0].length);

		Template12Results[] temp1Results = new Template12Results[ulForGVsCombn
				.size()];

		for (int i = 0; i < ulForGVsCombn.size(); i++) {

			String level = (String) ulForGVsCombn.get(i);

			temp1Results[i] = new Template12Results();
			temp1Results[i].setCompKey(level);
			String[][] levelData = tempImpl.pickDataForLevel(level,
					sortedAppendedData);

			tempImpl.printMatrix("levelData for-->" + level, levelData,
					levelData.length, levelData[0].length);

			temp1Results[i].setLevelData(levelData);

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			Hashtable<String, Boolean> htStatMetricListing = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTmInfo()
					.getTmOpMetOptions().getStatMetricsListing();
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

	public static PrintStream P2;

	public static void main(String[] args) {

		try {
			P2 = new PrintStream(new FileOutputStream("output.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TableWizardCoordinator ti = new TableWizardCoordinator(P2);// 
		Template3Impl ti3 = new Template3Impl(ti);

		/*
		 * Table Wizard Input Options
		 */

		ArrayList<String> groupVariables = new ArrayList<String>();
		ArrayList<String> idVariables = new ArrayList<String>();
		ArrayList<String> crossVariables = new ArrayList<String>();
		ArrayList<String> ordinaryVariables = new ArrayList<String>();

		groupVariables.add("Subject_ID");
		groupVariables.add("Formulation");
		groupVariables.add("Route");

		idVariables.add("Time");

		crossVariables.add(" ");

		ordinaryVariables.add("BP");
		ordinaryVariables.add("Conc");

		int templateType = 1;

		ti3.setTabWizIpOptions(groupVariables, idVariables, crossVariables,
				ordinaryVariables, templateType);

		/*
		 * Table Wizard Output Options
		 */

		Hashtable hTableOpOptions = new Hashtable();
		TableWizardOpMetricOptions opMetListing = new TableWizardOpMetricOptions(
				hTableOpOptions);
		ti3.setTabWizOpOptions(opMetListing);
		ti3.prepareTemplate();
	}

}
