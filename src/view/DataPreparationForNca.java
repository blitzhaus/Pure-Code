package view;

import view.ApplicationInfo;
import view.DisplayContents;
import jxl.Cell;
import jxl.Sheet;

public class DataPreparationForNca {
	String[][] Dat = null;

	String[][] prepareData() {
		if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == true)) {
			Dat = multipleLevelSorting.createMultipleSortInst().dataSorted;
		} else {
			Dat = multipleLevelSorting.createMultipleSortInst().dataSorted;
		}

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().setNcaDat(Dat);

		return Dat;
	}

}
