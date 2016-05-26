package Model;

import java.util.ArrayList;

import view.ApplicationInfo;

public class SemiCompartmentalModelling {

	double Slope;
	double Intercept;
	int no_subject;
	int[] subject_obs;
	int methodNumberForSCA;
	ApplicationInfo appInfo;
	ArrayList<String[]> spreedSheetOutput;
	ArrayList<String> textOutput;
	WorkSheetOutputs wsOutputts;
	String massUnit;
	String volumeUnit;
	String timeUnit;

	public ApplicationInfo getAppInfo() {
		return appInfo;
	}

	public SemiCompartmentalModelling() {
		Slope = 0;
		Intercept = 0;
		no_subject = 1;
		subject_obs = new int[1];
		methodNumberForSCA = 0;
		wsOutputts = new WorkSheetOutputs();
		massUnit = "";
		volumeUnit = "";
		timeUnit = "";
		time = new double[1];
		plasmaConc = new double[1];
		ke0 = 0;
		sum = 0;
		noObs = 0;
		numberOfSortVariable = 0;
		totalSortVariables = new String[1][1];
		effectSiteConc = new double[1];
		textOutput = new ArrayList<String>();
		spreedSheetOutput = new ArrayList<String[]>();
	}

	public void setAppInfo(ApplicationInfo appInfo) {
		this.appInfo = appInfo;
	}

	double[] time;
	double[] plasmaConc;
	double[] allStartTime;
	double[] allConcentration;

	public static SemiCompartmentalModelling SCA_OBJECT = null;

	public static SemiCompartmentalModelling creaetSCAInst() {
		if (SCA_OBJECT == null) {
			SCA_OBJECT = new SemiCompartmentalModelling();
		}

		return SCA_OBJECT;
	}

	double ke0;
	int noObs;
	int sum;
	int numberOfSortVariable;
	String[][] totalSortVariables;
	double[] effectSiteConc;
	SemiCompartmentalOutputPlots plotInst;

	public void semiComparmentalComputation(ApplicationInfo appInfo) {

		setAppInfo(appInfo);
		plotInst = new SemiCompartmentalOutputPlots();

		capturingInputsFromAppInfo();

		addHeaderToTextOutput();

		sum = 0;
		spreedSheetOutput = new ArrayList<String[]>();
		for (int j = 0; j < no_subject; j++) {
			captureCurrentProfileData(appInfo, j);

			effectSiteConc = new double[time.length];

			if (methodNumberForSCA == 0) {

				calculateEffectSiteConcUsingM0();

			}

			if (methodNumberForSCA == 1) {

				calculateEffectSiteConcUsingM1();

			}

			if (methodNumberForSCA == 2) {

				calculateEffectSiteConcUsingM2();

			}

			storingOutputInResultStructures(j);

			sum = sum + subject_obs[j];
		}

		wsOutputts.getSpreadSheetOutputs().setVerticalParameters(
				retrievingResultsFromAL(spreedSheetOutput));

		wsOutputts.setTextoutput(textOutput);

		// appInfo.getWorkSheetObjectsAL().get(appInfo.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo().setWorkSheetOutputs(wsOutputts);

	}

	public WorkSheetOutputs getWsOutputts() {
		return wsOutputts;
	}

	public void setWsOutputts(WorkSheetOutputs wsOutputts) {
		this.wsOutputts = wsOutputts;
	}

	private String[][] retrievingResultsFromAL(ArrayList<String[]> arrayList) {
		String[][] results = new String[arrayList.size()][arrayList.get(0).length];

		for (int i = 0; i < arrayList.size(); i++) {

			results[i] = arrayList.get(i);

		}
		return results;
	}

	private void storingOutputInResultStructures(int j) {
		
		
		
		String profile = "";

		for (int k = 0; k < numberOfSortVariable; k++) {

			profile = profile
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getScaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.get(k)
					+ "="
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getScaInfo().getProcessingInputs()
							.getProfileInfoObj().getDataSortVariables()[j][k]
					+ ",";

		}

		String strForTextOutput = "";
		
		for (int i = 0; i < subject_obs[j]; i++) {

			for (int k = 0; k < numberOfSortVariable; k++) {

				strForTextOutput = strForTextOutput
						+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getScaInfo().getProcessingInputs()
								.getProfileInfoObj().getDataSortVariables()[j][k]
						+ "\t";
			}

			String[] s = new String[numberOfSortVariable + 3];
			for (int k = 0; k < numberOfSortVariable; k++) {
				s[k] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getScaInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getDataSortVariables()[j][k];
			}

			strForTextOutput = strForTextOutput
					+ Double.toString(round(time[i], 4)) + "\t";
			strForTextOutput = strForTextOutput
					+ Double.toString(round(plasmaConc[i], 4)) + "\t";
			strForTextOutput = strForTextOutput
					+ Double.toString(round(effectSiteConc[i], 4)) + "\t";
			strForTextOutput = strForTextOutput + "\r\n\r\n";
			textOutput.add(strForTextOutput);
			s[numberOfSortVariable] = Double.toString(round(time[i], 4));
			s[numberOfSortVariable + 1] = Double.toString(round(plasmaConc[i],
					4));
			s[numberOfSortVariable + 2] = Double.toString(round(
					effectSiteConc[i], 4));
			spreedSheetOutput.add(s);

		}

		plotInst.GenerateplottsForSCAOutput(time, plasmaConc, profile);
		plotInst.GenerateplottsForSCAOutput(time, effectSiteConc, profile);

	}

	private void calculateEffectSiteConcUsingM2() {
		double lambdaJ;
		effectSiteConc[0] = 0;
		double[] X = new double[2];
		double[] Y = new double[2];

		double Cmax = maxVal(plasmaConc);
		double Tmax = time[findVal(plasmaConc, Cmax)];
		if (time[0] == 0) {
			X[0] = time[0];
			X[1] = time[1];
			Y[0] = plasmaConc[0];
			Y[1] = plasmaConc[1];
			effectSiteConc[0] = 0;
			lambdaJ = (Y[1] - Y[0]) / (X[1] - X[0]);
			effectSiteConc[1] = effectSiteConc[0]
					* Math.exp((-ke0) * (time[1] - time[0]))
					+ (plasmaConc[0] - lambdaJ / ke0)
					* (1 - Math.exp((-ke0) * (time[1] - time[0]))) + lambdaJ
					* (time[1] - time[0]);

			for (int i = 2; i < time.length; i++) {
				if (time[i] <= Tmax) {
					lambdaJ = (plasmaConc[i] - plasmaConc[i - 1])
							/ (time[i] - time[i - 1]);
					effectSiteConc[i] = effectSiteConc[i - 1]
							* Math.exp((-ke0) * (time[i] - time[i - 1]))
							+ (plasmaConc[i - 1] - lambdaJ / ke0)
							* (1 - Math.exp((-ke0) * (time[i] - time[i - 1])))
							+ lambdaJ * (time[i] - time[i - 1]);
				} else {
					lambdaJ = (-Math.log(plasmaConc[i]) + Math
							.log(plasmaConc[i - 1]))
							/ (time[i] - time[i - 1]);
					effectSiteConc[i] = effectSiteConc[i - 1]
							* Math.exp((-ke0) * (time[i] - time[i - 1]))
							+ plasmaConc[i - 1]
							* (ke0 / (ke0 - lambdaJ))
							* (Math.exp((-lambdaJ) * (time[i] - time[i - 1])) - Math
									.exp((-ke0) * (time[i] - time[i - 1])));
				}
			}

		} else {
			X[0] = 0;
			X[1] = time[0];
			Y[0] = 0;
			Y[1] = plasmaConc[0];

			lambdaJ = (Y[1] - Y[0]) / (X[1] - X[0]);
			effectSiteConc[0] = (0 - lambdaJ / ke0)
					* (1 - Math.exp((-ke0) * (time[0] - 0))) + lambdaJ
					* (time[0] - 0);

			for (int i = 1; i < time.length; i++) {
				if (time[i] <= Tmax) {
					lambdaJ = (plasmaConc[i] - plasmaConc[i - 1])
							/ (time[i] - time[i - 1]);
					effectSiteConc[i] = effectSiteConc[i - 1]
							* Math.exp((-ke0) * (time[i] - time[i - 1]))
							+ (plasmaConc[i - 1] - lambdaJ / ke0)
							* (1 - Math.exp((-ke0) * (time[i] - time[i - 1])))
							+ lambdaJ * (time[i] - time[i - 1]);
				}
				{
					lambdaJ = (-Math.log(plasmaConc[i]) + Math
							.log(plasmaConc[i - 1]))
							/ (time[i] - time[i - 1]);
					effectSiteConc[i] = effectSiteConc[i - 1]
							* Math.exp((-ke0) * (time[i] - time[i - 1]))
							+ plasmaConc[i - 1]
							* (ke0 / (ke0 - lambdaJ))
							* (Math.exp((-lambdaJ) * (time[i] - time[i - 1])) - Math
									.exp((-ke0) * (time[i] - time[i - 1])));
				}
			}

		}
	}

	private void calculateEffectSiteConcUsingM1() {
		double lambdaJ;
		effectSiteConc[0] = 0;
		double[] X = new double[2];
		double[] Y = new double[2];
		if (time[0] == 0) {
			X[0] = time[0];
			X[1] = time[1];
			Y[0] = plasmaConc[0];
			Y[1] = plasmaConc[1];
			effectSiteConc[0] = 0;
			lambdaJ = (Y[1] - Y[0]) / (X[1] - X[0]);
			effectSiteConc[1] = effectSiteConc[0]
					* Math.exp((-ke0) * (time[1] - time[0]))
					+ (plasmaConc[0] - lambdaJ / ke0)
					* (1 - Math.exp((-ke0) * (time[1] - time[0]))) + lambdaJ
					* (time[1] - time[0]);

			for (int i = 2; i < time.length; i++) {
				lambdaJ = (plasmaConc[i] - plasmaConc[i - 1])
						/ (time[i] - time[i - 1]);
				effectSiteConc[i] = effectSiteConc[i - 1]
						* Math.exp((-ke0) * (time[i] - time[i - 1]))
						+ (plasmaConc[i - 1] - lambdaJ / ke0)
						* (1 - Math.exp((-ke0) * (time[i] - time[i - 1])))
						+ lambdaJ * (time[i] - time[i - 1]);
			}

		} else {
			X[0] = 0;
			X[1] = time[0];
			Y[0] = 0;
			Y[1] = plasmaConc[0];

			lambdaJ = (Y[1] - Y[0]) / (X[1] - X[0]);
			effectSiteConc[0] = (0 - lambdaJ / ke0)
					* (1 - Math.exp((-ke0) * (time[0] - 0))) + lambdaJ
					* (time[0] - 0);

			for (int i = 1; i < time.length; i++) {
				lambdaJ = (plasmaConc[i] - plasmaConc[i - 1])
						/ (time[i] - time[i - 1]);
				effectSiteConc[i] = effectSiteConc[i - 1]
						* Math.exp((-ke0) * (time[i] - time[i - 1]))
						+ (plasmaConc[i - 1] - lambdaJ / ke0)
						* (1 - Math.exp((-ke0) * (time[i] - time[i - 1])))
						+ lambdaJ * (time[i] - time[i - 1]);
			}

		}
	}

	private void calculateEffectSiteConcUsingM0() {
		double lambdaJ;
		double[] X = new double[2];
		double[] Y = new double[2];
		if (time[0] == 0) {
			X[0] = time[0];
			X[1] = time[1];
			Y[0] = plasmaConc[0];
			Y[1] = plasmaConc[1];

			lambdaJ = (Y[1] - Y[0]) / (X[1] - X[0]);
			effectSiteConc[0] = 0;
			effectSiteConc[1] = effectSiteConc[0]
					* Math.exp((-ke0) * time[1])
					+ plasmaConc[0]
					* (ke0 / (ke0 - lambdaJ))
					* (Math.exp((-lambdaJ) * (time[1] - time[0])) - Math
							.exp((-ke0) * (time[1] - time[0])));

			for (int i = 2; i < time.length; i++) {
				lambdaJ = (-Math.log(plasmaConc[i]) + Math
						.log(plasmaConc[i - 1]))
						/ (time[i] - time[i - 1]);
				effectSiteConc[i] = effectSiteConc[i - 1]
						* Math.exp((-ke0) * (time[i] - time[i - 1]))
						+ plasmaConc[i - 1]
						* (ke0 / (ke0 - lambdaJ))
						* (Math.exp((-lambdaJ) * (time[i] - time[i - 1])) - Math
								.exp((-ke0) * (time[i] - time[i - 1])));
			}

		} else {

			X[0] = 0;
			X[1] = time[0];
			Y[0] = 0;
			Y[1] = plasmaConc[0];
			lambdaJ = (Y[1] - Y[0]) / (X[1] - X[0]);
			effectSiteConc[0] = (0 - lambdaJ / ke0)
					* (1 - Math.exp((-ke0) * (time[0] - 0))) + lambdaJ
					* (time[0] - 0);

			for (int i = 1; i < time.length; i++) {
				lambdaJ = (-Math.log(plasmaConc[i]) + Math
						.log(plasmaConc[i - 1]))
						/ (time[i] - time[i - 1]);
				effectSiteConc[i] = effectSiteConc[i - 1]
						* Math.exp((-ke0) * (time[i] - time[i - 1]))
						+ plasmaConc[i - 1]
						* (ke0 / (ke0 - lambdaJ))
						* (Math.exp((-lambdaJ) * (time[i] - time[i - 1])) - Math
								.exp((-ke0) * (time[i] - time[i - 1])));
			}

		}
	}

	private void captureCurrentProfileData(ApplicationInfo appInfo, int j) {
		time = new double[subject_obs[j]];
		plasmaConc = new double[subject_obs[j]];

		for (int i = 0; i < subject_obs[j]; i++) {
			time[i] = allStartTime[i + sum];
			plasmaConc[i] = allConcentration[i + sum];
		}

		System.out.println("" + j + " th sort variables");
		totalSortVariables = new String[subject_obs[j]][numberOfSortVariable];
		for (int i = 0; i < subject_obs[j]; i++) {
			for (int ii = 0; ii < numberOfSortVariable; ii++) {
				totalSortVariables[i][ii] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getScaInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getDataSortVariables()[j][ii];

			}

		}
	}

	private void addHeaderToTextOutput() {
		String strForTextOutput = "";
		for (int k = 0; k < numberOfSortVariable; k++) {
			strForTextOutput = strForTextOutput
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getScaInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.get(k) + "\t";
		}

		strForTextOutput = strForTextOutput + "time" + "(" + timeUnit + ")"
				+ "\t";

		strForTextOutput = strForTextOutput + "Conc" + "(" + massUnit + "/"
				+ volumeUnit + ")" + "\t";
		strForTextOutput = strForTextOutput + "Effect-Site Conc" + "("
				+ massUnit + "/" + volumeUnit + ")" + "\t";

		strForTextOutput = strForTextOutput + "\r\n\r\n";
		textOutput.add(strForTextOutput);
	}

	private void capturingInputsFromAppInfo() {
		try {
			String S = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getModelInputsObj().getKe0Value();
			Double.parseDouble(S);
			ke0 = Double.parseDouble(S);
		} catch (Exception e) {

			ke0 = 1;
		}

		methodNumberForSCA = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getMethodNumberForSCA();

		noObs = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getProfileInfoObj()
				.getConcForAllProfile().length;

		allStartTime = new double[noObs];
		allConcentration = new double[noObs];

		numberOfSortVariable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();
		subject_obs = new int[noObs];

		for (int i = 0; i < noObs; i++) {

			allStartTime[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getTimeForAllProfile()[i];
			allConcentration[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getConcForAllProfile()[i];
		}

		no_subject = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject();

		subject_obs = new int[no_subject];
		subject_obs = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getProfileInfoObj().getSubject_obs();

		timeUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getParameterUnitsDataObj().getTimeUnit();

		massUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getParameterUnitsDataObj()
				.getConcMassUnit();

		volumeUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getParameterUnitsDataObj()
				.getVolumeUnit();

	}

	public double maxVal(double[] mat) {
		double max = mat[0];
		for (int i = 1; i < mat.length; i++) {
			if (mat[i] > max) {
				max = mat[i];
			}
		}
		return max;
	}

	public int findVal(double[] mat, double val) {

		for (int i = 0; i < mat.length; i++) {
			if (mat[i] == val)
				return i;
		}
		return -1;
	}

	public double round(double value, int decimalPlace) {
		double power_of_ten = 1;
		while (decimalPlace-- > 0) {
			power_of_ten *= 10.0;
		}
		return Math.round(value * power_of_ten) / power_of_ten;
	}

}
