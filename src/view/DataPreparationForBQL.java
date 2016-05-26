package view;

public class DataPreparationForBQL {

	String[][] Dat = null;

	String[][] prepareData() {

		Dat = multipleLevelSorting.createMultipleSortInst().dataSorted;

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getBqlInfo()
				.getProcessingInputs().setBqlDat(Dat);
		return Dat;
	}

}
