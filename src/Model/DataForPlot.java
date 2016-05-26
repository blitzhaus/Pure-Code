package Model;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.sql.Time;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import view.ApplicationInfo;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DataForPlot {
	double[] start_time;
	double[] end_time;
	double[] concentration;
	double[] volume;
	int[] subject_obs;
	String[] subject_id;
	String[] previousSubject_id;
	int no_subject;
	double[] mid_point1;
	double[] excretion_rate1;
	int numberOfCanvases;
	int profileCountforColor = 0;
	double previousMaxX;
	double previousMaxY;
	Vector<Integer> scaleVector = new Vector<Integer>();
	int scaleVectorIndex = 0;

	WorkSheetOutputs wsOutputs;
	ApplicationInfo appInfo;

	public ApplicationInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(ApplicationInfo appInfo) {
		this.appInfo = appInfo;
	}

	public static DataForPlot DATA_PLOT = null;
	public static DataForPlot createDataForPlotInst(){
		if(DATA_PLOT == null){
			DATA_PLOT = new DataForPlot();
		}
		return DATA_PLOT;
	}
	
	public DataForPlot() {

	}

	void dataProcessingForPlot(ApplicationInfo appInfo)
			throws RowsExceededException, NumberFormatException,
			WriteException, BiffException, IOException {

		setAppInfo(appInfo);
		wsOutputs = new WorkSheetOutputs();
		
		int sum = 0;
		int k = 0;

		String[][] Dat = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().getPlotDat();
		int Nobs = Dat.length;
		String temp;
		int count = 0;
		double[] XPlot = null;
		double[] YPlot = null;
		double[] upperDeviation = null;
		double[] lowerDeviation = null;

		double[] XPlotSparse;
		double[] YPlotSparse;
		subject_obs = new int[Nobs];
		subject_obs[count] = 1;

		PlotMavenExecutionClass p = new PlotMavenExecutionClass();

		double[] allStartTime = new double[Nobs];
		double[] upVariableArray = new double[Nobs];
		double[] downVariableArray = new double[Nobs];
		double[] allEndTime = new double[Nobs];
		double[] allConcentration = new double[Nobs];
		double[] allVolume = new double[Nobs];
		String[] allSubjectIdentifier = new String[Nobs];
		String[] allPreviousIdentifier = new String[Nobs];
		int noOfColumns = Dat[0].length;

		int numberOfSortVariable;

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().getMappingDataObj()
				.getGroupVariableCorrespondingoriginalIndex() == -1) {
			numberOfSortVariable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();
		} else {
			numberOfSortVariable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size() + 1;
		}

		extractReuiredDataFromSortedData(Dat, Nobs, allStartTime,
				upVariableArray, downVariableArray, allConcentration);

		/*
		 * DetermineNumberOfSubject.createDetNoSubInst().determineNumberOfSubject
		 * ( Dat);
		 */

		subject_obs = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().getProfileInfoObj().getSubject_obs();

		no_subject = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().getProfileInfoObj().getNo_subject();

		sum = 0;

		boolean sameCanvas = true;
		int profileCount = 0;
		int jCount = 0;
		numberOfCanvases = -1;
		String[] previousProfileForScaleDetermination = new String[numberOfSortVariable];

		for (int j = 0; j < no_subject; j++) {
			XPlot = new double[subject_obs[j]];
			YPlot = new double[subject_obs[j]];

			String[][] totalSortVariables = new String[subject_obs[j]][numberOfSortVariable];
			for (int i = 0; i < subject_obs[j]; i++) {
				XPlot[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getTimeForAllProfile()[i + sum];
				YPlot[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getConcForAllProfile()[i + sum];
			}

			for (int i = 0; i < subject_obs[j]; i++) {
				for (int ii = 0; ii < numberOfSortVariable; ii++) {
					totalSortVariables[i][ii] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
							.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getProfileInfoObj().getDataSortVariables()[j][ii];

				}
			}
			numberOfCanvases++;
			int scale;
			if (numberOfCanvases == 0) {
				previousProfileForScaleDetermination = totalSortVariables[0];// profileCount
				scale = determineMaxCanvasScaleForYaxis(XPlot, YPlot,
						totalSortVariables[0], numberOfCanvases, sameCanvas);

			} else {

				for (int i = 0; i < previousProfileForScaleDetermination.length - 1; i++) {

					if (previousProfileForScaleDetermination[i]
							.equals(totalSortVariables[0][i])) {

					} else {
						sameCanvas = false;
						// System.out.println("now in a different canvas");
					}
				}
				jCount++;

				scale = determineMaxCanvasScaleForYaxis(XPlot, YPlot,
						totalSortVariables[0], numberOfCanvases, sameCanvas);
				sameCanvas = true;
				previousProfileForScaleDetermination = totalSortVariables[0];
			}
			sum = sum
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getProfileInfoObj().getSubject_obs()[j];
		}

		sum = 0;

		profileCount = 0;
		String[] previousProfile = new String[numberOfSortVariable];
		sameCanvas = true;
		jCount = 0;
		numberOfCanvases = -1;

		// now draw plots based on the scales determined above for each canvas
		for (int j = 0; j < no_subject; j++) {
			XPlot = new double[subject_obs[j]];
			YPlot = new double[subject_obs[j]];
			upperDeviation = new double[subject_obs[j]];
			lowerDeviation = new double[subject_obs[j]];

			String[][] totalSortVariables = new String[subject_obs[j]][numberOfSortVariable];
			for (int i = 0; i < subject_obs[j]; i++) {
				XPlot[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getTimeForAllProfile()[i + sum];
				YPlot[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getProfileInfoObj()
						.getConcForAllProfile()[i + sum];

				upperDeviation[i] = upVariableArray[i + sum];
				lowerDeviation[i] = downVariableArray[i + sum];

			}

			for (int i = 0; i < subject_obs[j]; i++) {
				for (int ii = 0; ii < numberOfSortVariable; ii++) {
					totalSortVariables[i][ii] = Dat[i + sum][ii];

				}

			}

			// calling the plot generation function and passing the required
			// arguments
			profileCountforColor++;

			numberOfCanvases++;
			if (numberOfCanvases == 0) {
				previousProfile = totalSortVariables[0];

				p.generatePlots(XPlot, YPlot, totalSortVariables[0],
						numberOfCanvases, sameCanvas, upperDeviation,
						lowerDeviation, appInfo, wsOutputs);
			} else {

				for (int i = 0; i < previousProfile.length - 1; i++) {

					if (previousProfile[i].equals(totalSortVariables[0][i])) {

					} else {
						sameCanvas = false;
					}
				}
				jCount++;

				p.generatePlots(XPlot, YPlot, totalSortVariables[0],
						numberOfCanvases, sameCanvas, upperDeviation,
						lowerDeviation, appInfo, wsOutputs);
				sameCanvas = true;
				previousProfile = totalSortVariables[0];
			}

			sum = sum
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getProfileInfoObj().getSubject_obs()[j];

		}
	}

	private void determineNoprofiles(int Nobs, String[][] dat, int count)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		int n = 0;
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().getMappingDataObj().getGroupVariable()
				.equals("Insert Group Variable") == true) {
			n = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();
		} else {
			n = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size() + 1;
		}
		for (int i = 0; i < Nobs - 2; i++) {
			while (n-- > 0) {
				if (dat[i][n].equals(dat[i + 1][n])) {

				} else {
					n++;
					break;
				}

			}

			if (n == 0) {
				subject_obs[count]++;
			} else {
				subject_obs[++count]++;
			}
		}

	}

	private void extractReuiredDataFromSortedData(String[][] Dat, int Nobs,
			double[] allStartTime, double[] upVariableArray,
			double[] downVariableArray, double[] allConcentration)
			throws RowsExceededException, NumberFormatException,
			WriteException, BiffException, IOException {

		for (int i = 0; i < Nobs; i++) {
			int j = 0;
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getMappingDataObj()
					.getGroupVariableCorrespondingoriginalIndex() == -1) {
				j = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size();
			} else {
				j = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size() + 1;
			}

			allStartTime[i] = Double.parseDouble(Dat[i][j]);

			// end_time[i]=0;
			allConcentration[i] = Double.parseDouble(Dat[i][j]);
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getMappingDataObj().getUpVariable()
					.equals("Up Variable") == true) {

			} else
				upVariableArray[i] = Double.parseDouble(Dat[i][j]);
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getMappingDataObj()
					.getDownVariable().equals("Down Variable")) {

			} else
				downVariableArray[i] = Double.parseDouble(Dat[i][j]);

		}
	}

	int determineMaxCanvasScaleForYaxis(double[] xPlot, double[] yPlot,
			String[] profile, int numberOfCanvases, boolean sameCanvas) {
		// TODO Auto-generated method stub

		// this is the first profile
		if (numberOfCanvases == 0) {
			int scale = calculateScale(xPlot, yPlot);
			scaleVector.add(scale);

		} else { // if it is not the first canvas then

			// if the profile got belongs to the same canvas then
			if (sameCanvas == true) {
				int scale = calculateScale(xPlot, yPlot);
				int previousScale = scaleVector.get(scaleVector.size() - 1);
				if (previousScale < scale) {
					scaleVector.remove(scaleVector.size() - 1);
					scaleVector.add(scale);
				}

			} else if (sameCanvas == false) {
				int scale = calculateScale(xPlot, yPlot);
				scaleVector.add(scale);
			}
		}

		return 0;

	}

	int calculateScale(double[] xPlot, double[] yPlot) {
		double[] dupX = new double[xPlot.length];
		for (int i = 0; i < xPlot.length; i++) {
			dupX[i] = xPlot[i];
		}

		double[] dupy = new double[yPlot.length];
		for (int i = 0; i < yPlot.length; i++) {
			dupy[i] = yPlot[i];
		}
		Arrays.sort(dupX);
		Arrays.sort(dupy);

		// determining the maximum x and y values in this profile
		double maxX = dupX[dupX.length - 1];
		double maxY = dupy[dupy.length - 1];

		// calculating the numner of digits in the max X.
		maxX = Math.floor(maxX) + 1;
		int previousMaxXDup = (int) maxX;
		String numberX = Integer.toString(previousMaxXDup);
		char[] numberCharX = numberX.toCharArray();
		int numberOfDigitsX = numberCharX.length;

		// calculating the number of digits in max Y
		maxY = Math.floor(maxY) + 1;
		int previousMaxYDup = (int) maxY;
		String numberY = Integer.toString(previousMaxYDup);
		char[] numberCharY = numberY.toCharArray();
		int numberOfDigitsY = numberCharY.length;

		// calculating the conversion scale
		int differenceDigits = numberOfDigitsY - numberOfDigitsX;
		int scale = (int) Math.pow(10, differenceDigits);
		return scale;
	}

	double round(double value, int decimalPlace) {
		double power_of_ten = 1;
		while (decimalPlace-- > 0) {
			power_of_ten *= 10.0;
		}
		return Math.round(value * power_of_ten) / power_of_ten;
	}

	void urine_dataprocessing(double[] start_time, double[] end_time,
			double[] conc, double[] volume) {
		int no_obs = 0;
		no_obs = start_time.length;
		mid_point1 = new double[no_obs];
		excretion_rate1 = new double[no_obs];

		for (int i = 0; i < no_obs; i++) {
			mid_point1[i] = (start_time[i] + end_time[i]) / 2;
			excretion_rate1[i] = conc[i] * volume[i]
					/ (end_time[i] - start_time[i]);
		}
	}
}
