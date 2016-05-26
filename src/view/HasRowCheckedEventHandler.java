package view;

import Common.LogEntry;

public class HasRowCheckedEventHandler {

	DisplayContents dispContentsObj;

	public HasRowCheckedEventHandler(DisplayContents dispContentsObj) {
		this.dispContentsObj = dispContentsObj;
	}

	public void hasHeaderRowCheckedEventHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		LogEntry lgEntryObj = LogEntry.createLogEntryInstance();
		// log entry regarding the header row check box
		if (dispContentsObj.hasHeaderRowCheck.isSelected()) {

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.setHasHeader(true);

			lgEntryObj.logEntry("Header row is checked");

		} else {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).setHasHeader(
							false);
			lgEntryObj.logEntry("Header row is unchecked");
		}
	}

}
