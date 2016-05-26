package Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.commons.math.stat.StatUtils;

import view.ApplicationInfo;
import view.DisplayContents;
import view.TableMavenInfo;

//import view.TableWizardOpMetricOptions;
import Jama.Matrix;

import edu.umbc.cs.maple.utils.JamaUtils;

public class Template1Impl {

	
	private String[][] inputMatrix;
	private int rowCount = 0;
	private int colCount = 0;
	private String[] stringArray;
	private String[] descrArray;
	private TableWizardInputOptions tableWizardinputs;
	private view.TableWizardOpMetricOptions opMetListing;

	TableWizardCoordinator tempImpl;

	public Template1Impl(TableWizardCoordinator tempImpl) {

		this.tempImpl = tempImpl;
		rowCount = tempImpl.getRowCount();
		colCount = tempImpl.getColCount();
		inputMatrix = tempImpl.getInputMatrix();
		// stringArray = tempImpl.getStringArray();
		descrArray = tempImpl.getDescrArray();

		// tempImpl.printMatrix("Input data..", inputMatrix, inputMatrix.length,
		// inputMatrix[0].length);
	}

	
	public void setTabWizIpOptions(ArrayList<String> groupVariables,
			ArrayList<String> idVariables, ArrayList<String> crossVariables,
			ArrayList<String> ordinaryVariables, int templateType) {

		tableWizardinputs = new TableWizardInputOptions(templateType,
				groupVariables, idVariables, crossVariables, ordinaryVariables);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.tcs.tablemaven.Template#prepareTemplate()
	 */
	public Template12Results[] prepareTemplate() {
		ArrayList<String> grpVariables = tableWizardinputs.getGroupVariables();

		int[] columnIndices = tempImpl.getColumnIndices(grpVariables);

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

			String level = (String) ulForGVsCombn.get(i);// level stand for one
															// composite key.

			temp1Results[i] = new Template12Results();

			temp1Results[i].setCompKey(level);

			String[][] levelData = tempImpl.pickDataForLevel(level,
					sortedAppendedData);

			temp1Results[i].setLevelData(levelData);

			tempImpl.printMatrix("levelData for-->" + level, levelData,
					levelData.length, levelData[0].length);

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
	}

	

	

}
