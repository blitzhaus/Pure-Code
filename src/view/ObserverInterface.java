package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Model.DDModel;


public interface ObserverInterface {
	
	void updateView(DDModel ddModel) throws RowsExceededException, WriteException, BiffException, IOException;
	
}
