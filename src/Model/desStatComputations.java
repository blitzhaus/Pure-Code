package Model;

import java.util.ArrayList;

import view.ApplicationInfo;


public class desStatComputations {

	public static desStatComputations DES_STAT_COMP = null;

	public static desStatComputations createDesStatCompInst() {
		if (DES_STAT_COMP == null) {
			DES_STAT_COMP = new desStatComputations();
		}
		return DES_STAT_COMP;
	}

	String massUnit;
	String volumeUnit;

	int numberOfObs;
	int numberOfSummaryVariables;
	int numberOfWeightVariables;

	int numberOfSortVariables;

	String[] time;
	double[] conc;
	double[] weight;
	WorkSheetOutputs wsOutputts;

	public WorkSheetOutputs getWsOutputts() {
		return wsOutputts;
	}

	public void setWsOutputts(WorkSheetOutputs wsOutputts) {
		this.wsOutputts = wsOutputts;
	}

	ApplicationInfo appInfo;
	ArrayList<String[]> spreedSheetOutput;
	ArrayList<String> textOutput;

	public ApplicationInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(ApplicationInfo appInfo) {
		this.appInfo = appInfo;
	}

	public desStatComputations() {

		massUnit = "";
		volumeUnit = "";
		numberOfObs = 0;
		numberOfSummaryVariables = 0;
		numberOfWeightVariables = 0;
		numberOfSortVariables = 0;
		time = new String[1];
		conc = new double[1];
		weight = new double[1];
		wsOutputts = new WorkSheetOutputs();
		spreedSheetOutput = new ArrayList<String[]>();
		textOutput = new ArrayList<String>();
	}

	public void descriptiveStatisticsCalculation(ApplicationInfo appInfo) {

		setAppInfo(appInfo);

		initializeVariables();

		descStatUnitspreperation();
		headerForTextOutput();

		for (int noSvariable = 0; noSvariable < numberOfSummaryVariables; noSvariable++) {
			System.out.println("for " + noSvariable + "th summary variable");

			for (int ii = 0; ii < numberOfObs; ii++) {
				time[ii] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getProfileInfoObj().getIndependentVarForDS()[ii];
				conc[ii] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getProfileInfoObj().getSummaryVariablesForDS()[ii][noSvariable];

			}

			int j = 0;
			int noDistinctObs = 1;
			while (j < time.length - 1) {
				if (time[j] .equals( time[j + 1]))
					noDistinctObs++;

				j++;

			}

			

			double[][] descriptiveStat = new double[noDistinctObs][26];
			int count = 0;
			int countObs;

			double[] newConc = new double[time.length];

			int i = 0;
			while (count < time.length - 1) {
				countObs = 0;
				newConc[countObs] = conc[count];
				System.out.println("time=" + time[count] + " conc="
						+ newConc[countObs]);

				while ((count < time.length - 1)
						&& (time[count].equals(time[count + 1]))) {

					count++;
					countObs++;
					newConc[countObs] = conc[count];

					System.out.println("time=" + time[count] + " conc="
							+ newConc[countObs]);
				}

				countObs = countObs + 1;

				count++;
				System.out.println(" ");

				descriptiveStat = calculateDS(descriptiveStat, countObs,
						newConc, i);

				pupulateTextOutputResults(descriptiveStat, count, i);
				writingResultsToSpreedSheet(descriptiveStat, count, i);
				i++;

			}

		}

		wsOutputts.getSpreadSheetOutputs().setVerticalParameters(
				retrievingResultsFromAL(spreedSheetOutput));

		wsOutputts.setTextoutput(textOutput);

		// appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getDesStatsInfo().setWorkSheetOutputs(wsOutputts);

	}

	private String[][] retrievingResultsFromAL(ArrayList<String[]> arrayList) {
		String[][] results = new String[arrayList.size()][arrayList.get(0).length];

		for (int i = 0; i < arrayList.size(); i++) {

			results[i] = arrayList.get(i);

		}
		return results;
	}

	private double[][] calculateDS(double[][] descriptiveStat, int countObs,
			double[] newConc, int i) {
		descriptiveStat[i][2] = countObs;
		descriptiveStat[i][3] = round(CalculateMean(newConc, countObs), 4);
		double variance = CalculateVarience(newConc, descriptiveStat[i][3],
				countObs);
		descriptiveStat[i][4] = round(Math.sqrt(variance), 4);
		descriptiveStat[i][5] = round(descriptiveStat[i][4]
				/ Math.sqrt(countObs), 4);
		descriptiveStat[i][6] = round(variance, 4);
		descriptiveStat[i][7] = round(minVal(newConc, countObs), 4);
		descriptiveStat[i][8] = round(CalculateMedian(newConc, countObs), 4);
		descriptiveStat[i][9] = round(maxVal(newConc, countObs), 4);
		descriptiveStat[i][10] = round(descriptiveStat[i][9]
				- descriptiveStat[i][7], 4);
		descriptiveStat[i][11] = round(
				100 * (descriptiveStat[i][4] / descriptiveStat[i][3]), 4);
		descriptiveStat[i][12] = round(
				CalculateGeometricMean(newConc, countObs), 4);
		descriptiveStat[i][13] = round(
				CalculateHarmonicMean(newConc, countObs), 4);

		descriptiveStat[i][14] = round(calculatePseudoSD(newConc, countObs), 4);
		descriptiveStat[i][15] = round(calculateMeanLog(newConc, countObs), 4);

		descriptiveStat[i][16] = round(calculateSDLog(newConc, countObs,
				descriptiveStat[i][3]), 4);

		descriptiveStat[i][17] = round(
				calculateCVGeometricMean(descriptiveStat[i][16]), 4);

		descriptiveStat[i][18] = round(-1, 4);
		descriptiveStat[i][19] = round(-1, 4);

		descriptiveStat[i][20] = round(-1, 4);
		descriptiveStat[i][21] = round(-1, 4);
		descriptiveStat[i][22] = round(-1, 4);

		descriptiveStat[i][23] = round(calculateSkewness(newConc, countObs), 4);

		descriptiveStat[i][24] = round(calculateKurtosis(newConc, countObs), 4);
		descriptiveStat[i][25] = round(-1, 4);
		return descriptiveStat;
	}

	private void pupulateTextOutputResults(double[][] descriptiveStat,
			int count, int i) {

		String s = "";
		for (int k = 0; k < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); k++) {
			s = s
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getDesStatsInfo().getProcessingInputs()
							.getProfileInfoObj().getDataSortVariables()[i][k]
					+ "\t";

		}

		s = s + Double.toString(descriptiveStat[i][2]) + "\t";
		s = s + Double.toString(descriptiveStat[i][3]) + "\t";
		s = s + Double.toString(descriptiveStat[i][4]) + "\t";
		s = s + Double.toString(descriptiveStat[i][5]) + "\t";
		s = s + Double.toString(descriptiveStat[i][6]) + "\t";
		s = s + Double.toString(descriptiveStat[i][7]) + "\t";
		s = s + Double.toString(descriptiveStat[i][8]) + "\t";
		s = s + Double.toString(descriptiveStat[i][9]) + "\t";
		s = s + Double.toString(descriptiveStat[i][10]) + "\t";
		s = s + Double.toString(descriptiveStat[i][11]) + "\t";
		s = s + Double.toString(descriptiveStat[i][12]) + "\t";
		s = s + Double.toString(descriptiveStat[i][13]) + "\t";

		s = s + Double.toString(descriptiveStat[i][14]) + "\t";
		s = s + Double.toString(descriptiveStat[i][15]) + "\t";
		s = s + Double.toString(descriptiveStat[i][16]) + "\t";
		s = s + Double.toString(descriptiveStat[i][17]) + "\t";

		s = s + Double.toString(descriptiveStat[i][23]) + "\t";
		s = s + Double.toString(descriptiveStat[i][24]) + "\t";
		s = s + "\r\n\r\n";
		textOutput.add(s);

	}

	private void writingResultsToSpreedSheet(double[][] descriptiveStat,
			int count, int i) {

		String[] s = new String[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 18];
		for (int k = 0; k < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); k++) {
			s[k] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getDataSortVariables()[i][k];
		}

		// for text output, appending the profile information

		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 0] = Double
				.toString(descriptiveStat[i][2]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 1] = Double
				.toString(descriptiveStat[i][3]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 2] = Double
				.toString(descriptiveStat[i][4]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 3] = Double
				.toString(descriptiveStat[i][5]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 4] = Double
				.toString(descriptiveStat[i][6]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 5] = Double
				.toString(descriptiveStat[i][7]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 6] = Double
				.toString(descriptiveStat[i][8]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 7] = Double
				.toString(descriptiveStat[i][9]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 8] = Double
				.toString(descriptiveStat[i][10]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 9] = Double
				.toString(descriptiveStat[i][11]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 10] = Double
				.toString(descriptiveStat[i][12]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 11] = Double
				.toString(descriptiveStat[i][13]);

		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 12] = Double
				.toString(descriptiveStat[i][14]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 13] = Double
				.toString(descriptiveStat[i][15]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 14] = Double
				.toString(descriptiveStat[i][16]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 15] = Double
				.toString(descriptiveStat[i][17]);

		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 16] = Double
				.toString(descriptiveStat[i][23]);
		s[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 17] = Double
				.toString(descriptiveStat[i][24]);

		spreedSheetOutput.add(s);

	}

	private void initializeVariables() {

		numberOfObs = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getProfileInfoObj()
				.getSummaryVariablesForDS().length;

		numberOfSummaryVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getCarryAlongVariablesListVector().size();
		numberOfWeightVariables = 0;

		numberOfSortVariables = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();

		time = new String[numberOfObs];
		conc = new double[numberOfObs];
		weight = new double[numberOfObs];
	}

	private void headerForTextOutput() {

		String s = "";

		for (int k = 0; k < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); k++) {
			s = s
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getDesStatsInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.get(k) + "\t";
		}

		s = s + "Nobs" + "\t";
		s = s + "mean" + "(" + massUnit + "/" + volumeUnit + ")" + "\t";
		s = s + "SD" + "(" + massUnit + "/" + volumeUnit + ")" + "\t";
		s = s + "SE" + "(" + massUnit + "/" + volumeUnit + ")" + "\t";
		s = s + "Varience" + "(" + massUnit + "/" + volumeUnit + ")" + "*"
				+ "(" + massUnit + "/" + volumeUnit + ")" + "\t";
		s = s + "Min" + "(" + massUnit + "/" + volumeUnit + ")" + "\t";
		s = s + "Median" + "(" + massUnit + "/" + volumeUnit + ")" + "\t";
		s = s + "Max" + "(" + massUnit + "/" + volumeUnit + ")" + "\t";
		s = s + "Range" + "\t";
		s = s + "CV%" + "\t";
		s = s + "Geometric Mean" + "(" + massUnit + "/" + volumeUnit + ")"
				+ "\t";
		s = s + "Harmonic Mean" + "(" + massUnit + "/" + volumeUnit + ")"
				+ "\t";

		s = s + "Pseudo SD" + "(" + massUnit + "/" + volumeUnit + ")" + "\t";
		s = s + "Mean Log" + "\t";
		s = s + "SD Log" + "\t";
		s = s + "CV% Geometric Mean" + "\t";

		s = s + "Skewness" + "\t";
		s = s + "Kurtosis" + "\t";

		s = s + "\r\n\r\n";

		textOutput.add(s);

	}

	private void descStatUnitspreperation() {

		massUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getParameterUnitsDataObj()
				.getConcMassUnit();

		volumeUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getParameterUnitsDataObj()
				.getVolumeUnit();

	}

	private double CalculateMean(double[] Y, int noObs) {
		double mean;
		double sum = 0;
		for (int i = 0; i < noObs; i++) {
			sum = sum + Y[i];
		}
		mean = sum / noObs;
		return mean;
	}

	private double CalculateVarience(double[] Y, double mean, int noObs) {
		double varience;
		double squreSum = 0;
		for (int i = 0; i < noObs; i++) {
			squreSum = squreSum + (Y[i] - mean) * (Y[i] - mean);
		}
		varience = squreSum / (noObs - 1);
		return varience;

	}

	private double minVal(double[] mat, int noObs) {
		double min = mat[0];
		for (int i = 1; i < noObs; i++) {
			if (mat[i] < min) {
				min = mat[i];
			}
		}
		return min;
	}

	private double maxVal(double[] mat, int noObs) {
		double max = mat[0];
		for (int i = 1; i < noObs; i++) {
			if (mat[i] > max) {
				max = mat[i];
			}
		}
		return max;
	}

	private double CalculateGeometricMean(double[] conc, int noObs) {
		double product = 1;
		double geometricMean = 0;
		double powerIndex = 1.0;
		powerIndex = powerIndex / noObs;
		System.out.println("@@@@ power index=" + powerIndex);

		for (int i = 0; i < noObs; i++)
			product = product * conc[i];

		geometricMean = Math.pow(product, powerIndex);
		return geometricMean;
	}

	private double CalculateMedian(double[] conc, int noObs) {
		double median = 0;
		double temp;

		for (int i = 0; i < noObs - 1; i++)
			for (int j = 0; j < noObs - i - 1; j++) {
				if (conc[j] > conc[j + 1]) {
					temp = conc[j + 1];
					conc[j + 1] = conc[j];
					conc[j] = temp;
				}
			}
		if (noObs % 2 != 0)
			median = conc[(noObs - 1) / 2];
		else
			median = (conc[(noObs - 2) / 2] + conc[noObs / 2]) / 2;
		return median;

	}

	private double CalculateHarmonicMean(double[] conc, int noObs) {
		double harmonicmean;
		double harmonicSum = 0;
		for (int i = 0; i < noObs; i++)
			harmonicSum = harmonicSum + 1 / conc[i];
		harmonicmean = harmonicSum / noObs;

		return (1 / harmonicmean);
	}

	private double calculatePseudoSD(double[] conc, int noObs) {
		double[] hMean = new double[noObs];
		double hSum = 0;
		double mean = 0;
		double pseudoSD = 0;
		for (int i = 0; i < noObs; i++) {
			hSum = 0;
			for (int j = 0; j < noObs; j++) {
				hSum = hSum + (1.0 / conc[i]);
			}
			hSum = hSum - (2.0 / conc[i]);
			hMean[i] = (noObs - 1) / hSum;
			mean = mean + hMean[i];
		}
		mean = mean / noObs;
		for (int i = 0; i < noObs; i++) {
			pseudoSD = pseudoSD + (hMean[i] - mean) * (hMean[i] - mean);
		}
		return (Math.sqrt((noObs - 1) * pseudoSD));
	}

	private double calculateMeanLog(double[] conc, int noObs) {
		double meanLog = 0;
		double logSum = 0;
		for (int i = 0; i < noObs; i++) {
			if (conc[i] > 0)
				logSum = logSum + Math.log(conc[i]);
		}
		meanLog = logSum / noObs;
		return meanLog;
	}

	private double calculateSDLog(double[] conc, int noObs, double mean) {
		double varience;

		double squreSum = 0;
		for (int i = 0; i < noObs; i++) {
			squreSum = squreSum + (Math.log(conc[i]) - mean)
					* (Math.log(conc[i]) - mean);
		}
		varience = squreSum / (noObs - 1);
		return (Math.sqrt(varience));
	}

	private double calculateCVGeometricMean(double logSD) {
		double cvGeometricMean = 0;
		cvGeometricMean = Math.sqrt((Math.exp(logSD) - 1)) * 100;

		return cvGeometricMean;
	}

	private double calculateSkewness(double[] conc, int noObs) {
		double skewness = 0;
		double mean;
		double sum = 0;
		double squareSum = 0;
		double qubicSum = 0;
		for (int i = 0; i < noObs; i++) {
			sum = sum + conc[i];
		}

		mean = sum / noObs;

		for (int i = 0; i < noObs; i++) {
			squareSum = squareSum + (conc[i] - mean) * (conc[i] - mean);
			qubicSum = qubicSum + Math.pow((conc[i] - mean), 3);
		}

		skewness = (qubicSum / noObs) / (Math.pow(squareSum / noObs, 1.5));

		return skewness;
	}

	private double calculateKurtosis(double[] conc, int noObs) {
		double kurtosis = 0;
		double mean;
		double sum = 0;
		double squareSum = 0;
		double quadraticSum = 0;
		for (int i = 0; i < noObs; i++) {
			sum = sum + conc[i];
		}

		mean = sum / noObs;

		for (int i = 0; i < noObs; i++) {
			squareSum = squareSum + (conc[i] - mean) * (conc[i] - mean);
			quadraticSum = quadraticSum + Math.pow((conc[i] - mean), 4);
		}

		kurtosis = (quadraticSum / noObs) / (Math.pow(squareSum / noObs, 1.5));

		return kurtosis;
	}

	private double round(double value, int decimalPlace) {
		double power_of_ten = 1;
		while (decimalPlace-- > 0) {
			power_of_ten *= 10.0;
		}
		return Math.round(value * power_of_ten) / power_of_ten;
	}

}
