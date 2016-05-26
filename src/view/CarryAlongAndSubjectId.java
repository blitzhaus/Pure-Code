package view;

import view.ApplicationInfo;
import view.DisplayContents;
import jxl.Cell;
import jxl.Sheet;

public class CarryAlongAndSubjectId {

	String[][] completeData;
	String[][] carryAlongData;
	String[] subjectIdentifierData;
	int numberOfSubjectIdVariable;
	int numberOfSortVariable;
	int numberOfcarryAlongVariable;

	public static CarryAlongAndSubjectId CarryAlong_AND_SUBID = null;

	public static CarryAlongAndSubjectId createCarryAlongAndSubIdInst() {
		if (CarryAlong_AND_SUBID == null) {
			CarryAlong_AND_SUBID = new CarryAlongAndSubjectId();
		}
		return CarryAlong_AND_SUBID;
	}

	public CarryAlongAndSubjectId() {

		completeData = new String[1][1];
		carryAlongData = new String[1][1];
		subjectIdentifierData = new String[1];
		numberOfSubjectIdVariable = 0;
		numberOfSortVariable = 0;
	}

	ApplicationInfo appInfo;

	public void prepareSubjectIdentifierData(String[][] sortedData,
			String[][] unsortedData) {

		appInfo = DisplayContents.createAppInfoInstance();
		numberOfSubjectIdVariable = 1;

		numberOfSortVariable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();
		int noOfColumnsForCompleteData = numberOfSortVariable
				+ numberOfSubjectIdVariable;
		String[] summaryData = new String[sortedData.length];

		subjectIdentifierData = new String[sortedData.length];

		int indexOfSubjectIdVariable = 0;

		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getColumnPropertiesArrayList().size(); i++) {

			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getSubjectColumnName()
					.equals(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo()
									.getColumnPropertiesArrayList().get(i)
									.getColumnName())) {
				indexOfSubjectIdVariable = i;
			}
		}

		String[][] data = copyDataFromDatStructAL();

		for (int i = 0; i <= sortedData.length; i++) {

			summaryData[i] = data[i][indexOfSubjectIdVariable];

		}

		int[] identifier = new int[sortedData.length];
		completeData = new String[sortedData.length][noOfColumnsForCompleteData];
		for (int i = 0; i < sortedData.length; i++) {
			for (int j = 0; j < numberOfSortVariable; j++) {

				completeData[i][j] = sortedData[i][j];

			}
		}

		int sortVariablesCount;
		for (int i = 0; i < sortedData.length; i++) {
			for (int j = 0; j < sortedData.length; j++) {
				sortVariablesCount = numberOfSortVariable;

				while (sortVariablesCount >= 1) {
					if (sortedData[i][sortVariablesCount - 1]
							.equals(unsortedData[j][sortVariablesCount - 1])) {

						sortVariablesCount--;
					} else
						break;

				}

				if (sortVariablesCount == 0 && identifier[j] != 1) {
					completeData[i][numberOfSortVariable] = summaryData[j];

					identifier[j] = 1;
					break;
				}

			}

		}

		for (int iii = 0; iii < completeData.length; iii++) {
			subjectIdentifierData[iii] = completeData[iii][numberOfSortVariable];
		}
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getProfileInfoObj()
				.setSubjectIdentifierData(subjectIdentifierData);
	}

	private String[][] copyDataFromDatStructAL() {
		String[][] data;
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getHasHeader() == true) {
			data = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getDataStructuresArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex());
			String[][] newData = new String[data.length - 1][data[0].length];
			for (int i = 0; i < newData.length; i++) {
				for (int j = 0; j < newData[i].length; j++) {
					newData[i][j] = data[i + 1][j];
				}
			}
			data = null;
			data = newData;

		} else {
			data = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getDataStructuresArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex());
		}
		return data;
	}

	public String[][] prepareCarryAlongData(String[][] sortedData,
			String[][] unSortedData) {

		appInfo = DisplayContents.createAppInfoInstance();
		numberOfSortVariable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();
		numberOfcarryAlongVariable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getCarryAlongVariablesListVector().size();
		int noOfColumnsForCompleteData = numberOfSortVariable
				+ numberOfcarryAlongVariable;

		String[][] summaryData = new String[sortedData.length][numberOfcarryAlongVariable];
		int indexOfcarryAlongVariable = 0;

		String[][] data = copyDataFromDatStructAL();

		for (int j = 0; j < numberOfcarryAlongVariable; j++) {
			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getColumnPropertiesArrayList().size(); i++) {
				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getNcaInfo()
						.getProcessingInputs()
						.getMappingDataObj()
						.getCarryAlongVariablesListVector()
						.get(j)
						.equals(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getNcaInfo()
										.getColumnPropertiesArrayList().get(i)
										.getColumnName())) {
					indexOfcarryAlongVariable = i;
				}
			}

			for (int i = 0; i < sortedData.length; i++) {

				summaryData[i][j] = data[i][indexOfcarryAlongVariable];

			}
		}

		int[] identifier = new int[sortedData.length];
		completeData = new String[sortedData.length][noOfColumnsForCompleteData];
		for (int i = 0; i < sortedData.length; i++) {
			for (int j = 0; j < numberOfSortVariable; j++) {

				completeData[i][j] = sortedData[i][j];

			}
		}

		int sortVariablesCount;

		for (int i = 0; i < sortedData.length; i++) {
			for (int j = 0; j < sortedData.length; j++) {
				sortVariablesCount = numberOfSortVariable;

				while (sortVariablesCount >= 1) {
					if (sortedData[i][sortVariablesCount - 1]
							.equals(unSortedData[j][sortVariablesCount - 1])) {

						sortVariablesCount--;
					} else
						break;

				}

				if (sortVariablesCount == 0 && identifier[j] != 1) {
					for (int k = 0; k < numberOfcarryAlongVariable; k++)
						completeData[i][numberOfSortVariable + k] = summaryData[j][k];

					identifier[j] = 1;

				}

			}

		}

		carryAlongData = new String[completeData.length][numberOfcarryAlongVariable];

		for (int iii = 0; iii < completeData.length; iii++) {
			for (int jjj = 0; jjj < numberOfcarryAlongVariable; jjj++)
				carryAlongData[iii][jjj] = completeData[iii][numberOfSortVariable
						+ jjj];
		}
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getProfileInfoObj().setCarryAlongData(
						carryAlongData);

		
		return carryAlongData;
	}

}
