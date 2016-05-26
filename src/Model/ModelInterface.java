package Model;

import java.io.IOException;
import java.util.ArrayList;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import view.ObserverInterface;


public interface ModelInterface {

	
	void registerObserver(ObserverInterface observer);
	
	void removeObserver(ObserverInterface observer);
	
	void setResults(WorkSheetOutputs wsOutputs) throws RowsExceededException, WriteException, BiffException, IOException;
	
	void notifyObserver() throws RowsExceededException, WriteException, BiffException, IOException;
	
}
