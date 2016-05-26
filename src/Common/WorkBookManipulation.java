package Common;

import java.io.File;
import java.io.IOException;

import view.ApplicationInfo;
import view.DisplayContents;

import jxl.Cell;
import jxl.LabelCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WorkBookManipulation {

	public WorkBookManipulation() {

	}

	public WritableWorkbook createWorkBook(String path) throws IOException {
		WritableWorkbook wb = Workbook.createWorkbook(new File(path));
		return wb;
	}

	public void createWorkBook() throws IOException, BiffException,
			WriteException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		WritableWorkbook w = Workbook.createWorkbook(new File("ajith.xls"));

		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getDataStructuresArrayList().size(); i++) {

			// creating the work sheet
			WritableSheet s = w.createSheet(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(i).getSheetName(), i);

			// writing the contents of data structure to the work sheet
			String[][] temp = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(i);
			for (int m = 0; m < temp.length; m++) {
				for (int n = 0; n < temp[i].length; n++) {
					jxl.write.Label label = new jxl.write.Label(n, m,
							temp[m][n]);
					s.addCell(label);
				}
			}
		}
		w.write();
		w.close();
		Workbook ww = Workbook.getWorkbook(new File("ajith.xls"));
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).setFile1Workbook(ww);

	}

	public void replaceSheetInWorkBook() throws BiffException, WriteException,
			IOException {

		// we call the same method. Since the data structures array list is
		// already filled
		// with the latest final parameters it gets updated
		createWorkBook();

	}

	public WritableWorkbook addWorkSheet(WritableWorkbook w,
			String[][] dataFromFile2, String sheetname)
			throws RowsExceededException, WriteException, IOException {

		w.createSheet(sheetname, w.getNumberOfSheets());
		WritableSheet s = w.getSheet(w.getNumberOfSheets() - 1);
		for (int i = 0; i < dataFromFile2.length; i++) {
			for (int j = 0; j < dataFromFile2[i].length; j++) {
				jxl.write.Label label = new jxl.write.Label(j, i,
						dataFromFile2[i][j]);
				System.out.print(dataFromFile2[i][j] + ", ");
				s.addCell(label);
			}
			System.out.println();
		}

		return w;
	}

	public void createTemporaryExcelFile(String[][] dataFromFile,
			String filePath) throws IOException, RowsExceededException,
			WriteException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		WritableWorkbook wb = Workbook.createWorkbook(new File(
				"temporatWorkBook.xls"));
		wb.createSheet(filePath, 0);
		WritableSheet s = wb.getSheet(0);
		for (int i = 0; i < dataFromFile.length; i++) {
			for (int j = 0; j < dataFromFile[i].length; j++) {
				jxl.write.Label label = new jxl.write.Label(j, i,
						dataFromFile[i][j]);
				s.addCell(label);
			}
		}

		wb.write();
		wb.close();
	}

}
