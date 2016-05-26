package Model;

import java.util.ArrayList;
import java.util.Arrays;

import org.omg.CORBA.TRANSACTION_MODE;



import Jama.Matrix;
import view.ApplicationInfo;

public class TableTransformationsComputations {

	ApplicationInfo appInfo;
	WorkSheetOutputs wsoutputs;

	public ApplicationInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(ApplicationInfo appInfo) {
		this.appInfo = appInfo;
	}

	public TableTransformationsComputations(ApplicationInfo appInfo) {
		this.appInfo = appInfo;
		this.appInfo = appInfo;
		wsoutputs = new WorkSheetOutputs();
	}

	void performTransformations() {

		int transformationType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getTtTypeOfFunc();

		switch (transformationType) {
		case 0:
			funcTranformation();
			break;
		case 1:
			arithmeticTransformation();
			break;
		case 2:
			baseLinetransformation();
			break;

		}

	}

	private void baseLinetransformation() {

		ArrayList<Double> transformed = new ArrayList<Double>();
		int baseLineFuncChosen = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getTtSelectedBaseFuncIndex();
		switch (baseLineFuncChosen) {
		case 0:
			transformed = baseLineChange();
			break;

		case 1:
			transformed = percentageBaseLineChange();
			break;
		case 2:
			transformed = ratioFromBaseLine();
			break;
		}

		String[] transformedCol = new String[transformed.size()];
		for (int i = 0; i < transformed.size(); i++) {
			transformedCol[i] = transformed.get(i) + "";
		}

		setTheTransformedColumnInAppInfo(transformedCol);
	}

	private ArrayList<Double> ratioFromBaseLine() {

		ArrayList<Double> transformed = new ArrayList<Double>();
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().isTtIsLowestInProfile() == true) {

			int noSub = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj().getNo_subject();

			for (int i = 0; i < noSub; i++) {
				double[] time = getCorrespondingProfileTime(i);
				double[] conc = getcorrespondingProfileConc(i);

				int lowestTimePoint = determineLowestTimeoriginalIndex(time);
				double baseLineConc = conc[lowestTimePoint];

				for (int j = 0; j < time.length; j++) {
					transformed.add((conc[j]) / baseLineConc);
				}
			}

		} else {
			
			int noSub = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj().getNo_subject();

			
			for (int i = 0; i < noSub; i++) {
				double[] time = getCorrespondingProfileTime(i);
				double[] conc = getcorrespondingProfileConc(i);

				int lowestTimePoint = determineCustomTimeOriginalIndex(time);
				double baseLineConc = conc[lowestTimePoint];

				for (int j = 0; j < time.length; j++) {
					transformed.add((conc[j]) / baseLineConc);
				}
			}
		}

		return transformed;
	}

	private ArrayList<Double> percentageBaseLineChange() {

		ArrayList<Double> transformed = new ArrayList<Double>();
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().isTtIsLowestInProfile() == true) {

			int noSub = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj().getNo_subject();

			for (int i = 0; i < noSub; i++) {
				double[] time = getCorrespondingProfileTime(i);
				double[] conc = getcorrespondingProfileConc(i);

				int lowestTimePoint = determineLowestTimeoriginalIndex(time);
				double baseLineConc = conc[lowestTimePoint];

				for (int j = 0; j < time.length; j++) {
					transformed
							.add(((conc[j] - baseLineConc) / baseLineConc) * 100);
				}
			}

		} else {
			
			int noSub = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj().getNo_subject();

			
			for (int i = 0; i < noSub; i++) {
				double[] time = getCorrespondingProfileTime(i);
				double[] conc = getcorrespondingProfileConc(i);

				int lowestTimePoint = determineCustomTimeOriginalIndex(time);
				double baseLineConc = conc[lowestTimePoint];

				for (int j = 0; j < time.length; j++) {
					transformed.add(((conc[j] - baseLineConc) / baseLineConc) * 100);
				}
			}
		}

		return transformed;
	}

	private ArrayList<Double> baseLineChange() {

		ArrayList<Double> transformed = new ArrayList<Double>();
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().isTtIsLowestInProfile() == true) {

			int noSub = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj().getNo_subject();

			for (int i = 0; i < noSub; i++) {
				double[] time = getCorrespondingProfileTime(i);
				double[] conc = getcorrespondingProfileConc(i);

				int lowestTimePoint = determineLowestTimeoriginalIndex(time);
				double baseLineConc = conc[lowestTimePoint];

				for (int j = 0; j < time.length; j++) {
					transformed.add((conc[j] - baseLineConc) / baseLineConc);
				}
			}

		} else {
			
			int noSub = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj().getNo_subject();

			
			for (int i = 0; i < noSub; i++) {
				double[] time = getCorrespondingProfileTime(i);
				double[] conc = getcorrespondingProfileConc(i);

				int lowestTimePoint = determineCustomTimeOriginalIndex(time);
				double baseLineConc = conc[lowestTimePoint];

				for (int j = 0; j < time.length; j++) {
					transformed.add((conc[j] - baseLineConc) / baseLineConc);
				}
			}
		}

		return transformed;
	}

	private int determineCustomTimeOriginalIndex(double[] time) {
		
		int lowestTimeindex = 0;
		double customTime = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getTtCostomPoint();
		
		for (int i = 0; i < time.length; i++) {
		if (time[i] == customTime) {
			lowestTimeindex = i;
			break;
		}

	}
		
	return lowestTimeindex;
	}

	private int determineLowestTimeoriginalIndex(double[] time) {
		double[] timeTemp = new double[time.length];
		for (int i = 0; i < time.length; i++) {
			timeTemp[i] = time[i];
		}
		Arrays.sort(timeTemp);
		int lowestTimeindex = 0;
		for (int i = 0; i < time.length; i++) {
			if (time[i] == timeTemp[0]) {
				lowestTimeindex = i;
				break;
			}

		}

		return lowestTimeindex;
	}

	private double[] getcorrespondingProfileConc(int subjectIndex) {
		int subObs = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getProfileInfoObj().getSubject_obs()[subjectIndex];
		int startIndex = 0;

		int subObsTemp = 0;
		for (int i = 0; i < subjectIndex; i++) {
			subObsTemp = subObsTemp
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getTtInfo().getProcessingInputs()
							.getProfileInfoObj().getSubject_obs()[i];

			startIndex = subObsTemp;
		}

		double[] conc = new double[subObs];
		for (int i = 0; i < subObs; i++) {
			conc[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getConcForAllProfile()[startIndex++];
		}

		return conc;
	}

	private double[] getCorrespondingProfileTime(int subjectIndex) {
		int subObs = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getProfileInfoObj().getSubject_obs()[subjectIndex];
		int startIndex = 0;

		int subObsTemp = 0;
		for (int i = 0; i < subjectIndex; i++) {
			subObsTemp = subObsTemp
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getTtInfo().getProcessingInputs()
							.getProfileInfoObj().getSubject_obs()[i];

			startIndex = subObsTemp;
		}

		double[] time = new double[subObs];
		for (int i = 0; i < subObs; i++) {
			time[i] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getTtInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getTimeForAllProfile()[startIndex++];
		}

		return time;
	}

	private void arithmeticTransformation() {

		int arithmeticFuncSelected = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getTtSlectedArithmeticFuncIndex();

		int xColindex = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()

				.getProcessingInputs().getMappingDataObj()
				.getxColumnCorrespondinOriginalIndex();

		int yColIndex = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()

				.getProcessingInputs().getMappingDataObj()
				.getyColumnCorrespondinOriginalIndex();

		String[][] data = getData();
		String[] xColData = new String[data.length];
		String[] yColData = new String[data.length];

		for (int i = 0; i < xColData.length; i++) {
			xColData[i] = data[i][xColindex];
			yColData[i] = data[i][yColIndex];
		}

		double n = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getTtnValue();

		String[] transformedCol = new String[data.length];

		switch (arithmeticFuncSelected) {
		case 0:
			transformedCol = addColumns(xColData, yColData);
			break;

		case 1:
			transformedCol = subtractCol(xColData, yColData);
			break;
		case 2:
			transformedCol = product(xColData, yColData);
			break;
		case 3:
			transformedCol = divide(xColData, yColData);
			break;

		case 4:
			transformedCol = relativeDiff(xColData, yColData);
			break;

		case 5:
			transformedCol = percentage(xColData, yColData);
			break;

		case 6:
			transformedCol = xPlusn(xColData, n);
			break;
		case 7:
			transformedCol = xMinusn(xColData, n);
			break;
		case 8:
			transformedCol = product(xColData, n);
			break;
		case 9:
			transformedCol = divide(xColData, n);
			break;
		case 10:
			transformedCol = xPowern(xColData, n);
			break;
		}

		setTheTransformedColumnInAppInfo(transformedCol);

	}

	private String[] xPowern(String[] xColData, double n) {
		String[] transformed = new String[xColData.length];
		for (int i = 0; i < xColData.length; i++) {

			transformed[i] = Math.pow(Double.parseDouble(xColData[i]), n) + "";
		}
		return transformed;
	}

	private String[] divide(String[] xColData, double n) {
		String[] transformed = new String[xColData.length];
		for (int i = 0; i < xColData.length; i++) {

			transformed[i] = (Double.parseDouble(xColData[i]) / n) + "";
		}
		return transformed;
	}

	private String[] product(String[] xColData, double n) {
		String[] transformed = new String[xColData.length];
		for (int i = 0; i < xColData.length; i++) {
			transformed[i] = (Double.parseDouble(xColData[i]) * n) + "";
		}
		return transformed;
	}

	private String[] xMinusn(String[] xColData, double n) {
		String[] transformed = new String[xColData.length];
		for (int i = 0; i < xColData.length; i++) {
			transformed[i] = (Double.parseDouble(xColData[i]) - n) + "";
		}
		return transformed;
	}

	private String[] xPlusn(String[] xColData, double n) {
		String[] transformed = new String[xColData.length];
		for (int i = 0; i < xColData.length; i++) {
			transformed[i] = (Double.parseDouble(xColData[i]) + n) + "";
		}
		return transformed;
	}

	private String[] percentage(String[] xColData, String[] yColData) {
		String[] transformed = addColumns(xColData, yColData);
		transformed = divide(transformed, yColData);
		for (int i = 0; i < transformed.length; i++) {
			transformed[i] = (Double.parseDouble(transformed[i]) * 100) + "";
		}
		return transformed;
	}

	private String[] relativeDiff(String[] xColData, String[] yColData) {
		String[] transformed = new String[xColData.length];
		for (int i = 0; i < xColData.length; i++) {
			if (Double.parseDouble(yColData[i]) == 0) {
				transformed[i] = "NAN";
			} else {
				transformed[i] = ((Double.parseDouble(xColData[i]) - Double
						.parseDouble(yColData[i])) / Double
						.parseDouble(yColData[i]))
						+ "";
			}

		}
		return transformed;
	}

	private String[] divide(String[] xColData, String[] yColData) {
		String[] transformed = new String[xColData.length];
		for (int i = 0; i < xColData.length; i++) {
			if (Double.parseDouble(yColData[i]) == 0) {
				transformed[i] = "NAN";
			} else {
				transformed[i] = Double.parseDouble(xColData[i])
						/ Double.parseDouble(yColData[i]) + "";
			}

		}
		return transformed;
	}

	private String[] product(String[] xColData, String[] yColData) {
		String[] transformed = new String[xColData.length];
		for (int i = 0; i < xColData.length; i++) {
			transformed[i] = Double.parseDouble(xColData[i])
					* Double.parseDouble(yColData[i]) + "";
		}
		return transformed;
	}

	private String[] subtractCol(String[] xColData, String[] yColData) {
		String[] transformed = new String[xColData.length];
		for (int i = 0; i < xColData.length; i++) {
			transformed[i] = Double.parseDouble(xColData[i])
					- Double.parseDouble(yColData[i]) + "";
		}
		return transformed;
	}

	private String[] addColumns(String[] xColData, String[] yColData) {

		String[] transformed = new String[xColData.length];
		for (int i = 0; i < xColData.length; i++) {
			transformed[i] = Double.parseDouble(xColData[i])
					+ Double.parseDouble(yColData[i]) + "";
		}
		return transformed;
	}

	private void funcTranformation() {

		int functionSelected = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().getTtSelectedFuncIndex();

		int index = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()

				.getProcessingInputs().getMappingDataObj()
				.getxColumnCorrespondinOriginalIndex();

		String[][] data = getData();

		String[] xColumn = new String[data.length];

		for (int i = 0; i < data.length; i++) {
			xColumn[i] = data[i][index];
		}

		String[] transformedCol = new String[xColumn.length];

		switch (functionSelected) {
		case 0:
			transformedCol = reciprocal(xColumn);
			break;
		case 1:
			transformedCol = absoluteValue(xColumn);
			break;
		case 2:
			transformedCol = ePowerX(xColumn);
			break;
		case 3:
			transformedCol = naturalLog(xColumn);
			break;
		case 4:
			transformedCol = log10X(xColumn);
			break;
		case 5:
			transformedCol = squareRoot(xColumn);
			break;

		}

		setTheTransformedColumnInAppInfo(transformedCol);
	}

	private String[][] getData() {
		String[][] data;
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getHasHeader() == true) {
			int rows = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).length - 1;
			int columns = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex())[0].length;
			data = new String[rows][columns];
			for (int i = 1; i <= rows; i++) {
				for (int j = 0; j < columns; j++) {
					data[i - 1][j] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getElementFromDS(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())[i][j];
				}
			}

		} else {
			data = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex());
		}
		return data;
	}

	private void setTheTransformedColumnInAppInfo(String[] transformedCol) {

		wsoutputs.setTtTransformedColumn(transformedCol);
	}

	private String[] squareRoot(String[] xColumn) {
		String[] sqrootX = new String[xColumn.length];
		for (int i = 0; i < sqrootX.length; i++) {
			sqrootX[i] = Math.sqrt(Double.parseDouble(xColumn[i])) + "";
		}
		return sqrootX;
	}

	private String[] log10X(String[] xColumn) {
		String[] log10X = new String[xColumn.length];
		for (int i = 0; i < log10X.length; i++) {
			log10X[i] = Math.log10(Double.parseDouble(xColumn[i])) + "";
		}
		return log10X;

	}

	private String[] naturalLog(String[] xColumn) {
		String[] natularLogx = new String[xColumn.length];
		for (int i = 0; i < natularLogx.length; i++) {
			natularLogx[i] = Math.log(Double.parseDouble(xColumn[i])) + "";
		}

		return natularLogx;

	}

	private String[] ePowerX(String[] xColumn) {
		String[] ePowerX = new String[xColumn.length];
		for (int i = 0; i < ePowerX.length; i++) {
			ePowerX[i] = Math.pow(Math.E, Double.parseDouble(xColumn[i])) + "";
		}
		return ePowerX;

	}

	private String[] absoluteValue(String[] xColumn) {
		String[] absValue = new String[xColumn.length];
		for (int i = 0; i < absValue.length; i++) {
			absValue[i] = Math.abs(Double.parseDouble(xColumn[i])) + "";
		}

		return absValue;
	}

	private String[] reciprocal(String[] xColumn) {
		String[] reciprocalX = new String[xColumn.length];
		for (int i = 0; i < xColumn.length; i++) {
			System.out.println(xColumn[i]);
			if ((xColumn[i]) == 0 + "") {
				reciprocalX[i] = "NAN";
			} else {
				reciprocalX[i] = 1 / (Double.parseDouble(xColumn[i])) + "";
			}

		}

		return reciprocalX;
	}

}
