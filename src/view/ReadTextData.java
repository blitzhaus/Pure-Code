package view;

import java.io.File;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import Common.DataReader2;
import Common.WorkBookManipulation;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ReadTextData extends DCCoreFunctions implements ReadData{

	
	private String[][] dataFromFile;
	@Override
	public void readData(String fileExtension, String filePath,
			 int maxRow, int maxColumns,
			JInternalFrame sheetFrame) throws RowsExceededException,
			WriteException, IOException, BiffException {
		DataReader2 dr = new DataReader2(filePath, "String");
		
		dataFromFile = dr.new_StringFileArray;
		/*oriData = new Matrix(dataFromFile);
		System.out.println(oriData.getRowDimension()+"  rows");*/
		
		
		 //WritableWorkbook w = prepareWorkBook(dataFromFile, filePath);
		 WorkBookManipulation wm = new WorkBookManipulation();
		 wm.createTemporaryExcelFile(dataFromFile, filePath);
		 Workbook wb = Workbook.getWorkbook(new File("temporatWorkBook.xls"));
		 
		 for(int i=0;i<wb.getSheet(0).getRows();i++){
			 for(int j=0;j< wb.getSheet(0).getColumns();j++){
				 
			 }
			 
		 }
		 setWorkBookContentsToApplicationInfo(wb, ImportFile.createDispContentsInstance());
	}

	
}
