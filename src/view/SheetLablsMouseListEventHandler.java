package view;

import java.awt.Color;
import java.awt.event.MouseEvent;

import jxl.Sheet;

public class SheetLablsMouseListEventHandler {

	
	DisplayContents dispContentsInst = null;
	public static SheetLablsMouseListEventHandler SHEETLABLEINST = null;
	public static SheetLablsMouseListEventHandler createSheetLableInstance(DisplayContents disContentsInst, int i){
		if(SHEETLABLEINST == null){
			SHEETLABLEINST = new SheetLablsMouseListEventHandler(disContentsInst,i);
		}
		return SHEETLABLEINST;
	}
	
	public SheetLablsMouseListEventHandler(DisplayContents dispContentsInst, int i){
		this.dispContentsInst = dispContentsInst;
		shtLblsMseClickEventHandler(i);
	}
	
	public void setData(int i){
		shtLblsMseClickEventHandler(i);
	}
	
	
	
	private void shtLblsMseClickEventHandler(int i) {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		try {

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.setSelectedSheetIndex(i);

			// show the selection of the user/ default selection of the has
			// header check box
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getHasHeader() == true) {
				dispContentsInst.hasHeaderRowCheck.setSelected(true);
			} else if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getHasHeader() == false) {
				dispContentsInst.hasHeaderRowCheck.setSelected(false);
			}

			// retrieve and display the missing value.
			dispContentsInst.missingValueText.setText(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getMissingValue()
					+ "");

			// retrieve and display the start row of the selected work sheet
			dispContentsInst.startRowText.setText(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getStartRow()
					+ "");

			for (int m = 0; m < dispContentsInst.dataTable.getRowCount(); m++) {
				for (int n = 0; n < dispContentsInst.dataTable.getColumnCount(); n++) {
					dispContentsInst.dataTable.setValueAt("", m, n);

				}
			}
			

			dispContentsInst.dataFrame.getContentPane().removeAll();
			dispContentsInst.displaySheetData(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getElementFromDS(i));
			dispContentsInst.dataFrame.moveToFront();
			dispContentsInst.dataFrame.setSize(dispContentsInst.dataFrame
					.getSize());
			// dataFrame.getContentPane().setBackground(Color.black);

		} catch (ArrayIndexOutOfBoundsException arrayIndexoutOfBounds) {
			String[] error = new String[2];
			error[0] = "Data Error";
			error[1] = "Data Sheet Contains Empty Rows Please delete them and Import again to avoid Unexpected behavioue";

		}
	}
}
