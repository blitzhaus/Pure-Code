package view;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Common.WorkBookManipulation;

public class ReadSpreadSheetData extends DCCoreFunctions implements ReadData {

	
	@Override
	public void readData(String fileExtension, String filePath,
			 int maxRows, int maxColumns,
			JInternalFrame sheetFrame) throws RowsExceededException,
			WriteException, IOException, BiffException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Workbook w = null;
		if (fileExtension.equalsIgnoreCase(".xlsx")) {
			try{
				WorkBookManipulation wm = new WorkBookManipulation();
				WritableWorkbook wb = wm.createWorkBook("xslxWorkBook.xls");

				XSSFWorkbook workBook = new XSSFWorkbook(filePath);
				for (int i = 0; i < workBook.getNumberOfSheets(); i++) {

					XSSFSheet s = workBook.getSheetAt(i);
					Iterator rowIter = s.rowIterator();
					XSSFRow tempRow = (XSSFRow) rowIter.next();
					String data[][] = new String[s.getLastRowNum() + 1][tempRow
							.getLastCellNum()];

					rowIter = s.rowIterator();

					int rowIndex = 0;
					while (rowIter.hasNext()) {
						XSSFRow myRow = (XSSFRow) rowIter.next();
						Iterator celliterator = myRow.cellIterator();
						int j = 0;
						while (celliterator.hasNext()) {
							XSSFCell myCell = (XSSFCell) celliterator.next();
							data[rowIndex][j++] = myCell.toString();

						}
						rowIndex++;

					}

					wb = wm.addWorkSheet(wb, data, s.getSheetName());
			}
				wb.write();
				wb.close();
				filePath = "xslxWorkBook.xls";
			} catch (Exception e) {
				JOptionPane
						.showMessageDialog(
								ImportFile.createDispContentsInstance().dataFrame,
								"Problem reading the work sheet please ensure there are no empty rows and columns",
								"Conform", JOptionPane.YES_OPTION);
				ImportFile.DISP_CONTENTS = null;

			}

		}
		//String removedEmptyRowsFilePath  = determineIfExptyRowsOrColsExist(new File(filePath));
		w = Workbook.getWorkbook(new File(filePath));
		setWorkBookContentsToApplicationInfo(w, ImportFile
				.createDispContentsInstance());
	}

}
