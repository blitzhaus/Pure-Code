package view;

import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public interface ReadData {
	void readData(String fileExtension, String filePath
			,int maxRow, int maxColumns, JInternalFrame sheetFrame) throws RowsExceededException, WriteException, IOException, BiffException;
}
