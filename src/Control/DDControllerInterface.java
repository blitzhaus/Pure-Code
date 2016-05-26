package Control;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import view.ApplicationInfo;

public interface DDControllerInterface {

	void initiateAnalysis(ApplicationInfo appInfo) throws RowsExceededException, WriteException, BiffException, IOException;
	void performCA(ApplicationInfo appInfo);
	
	void performNCA(ApplicationInfo appInfo);
	void performNPS(ApplicationInfo appInfo) throws RowsExceededException, WriteException, BiffException, IOException;
	void performDS(ApplicationInfo appInfo) throws RowsExceededException, WriteException, BiffException, IOException;
	void performSCA(ApplicationInfo appInfo) throws RowsExceededException, WriteException, BiffException, IOException;
	void performPlotMaven(ApplicationInfo appInfo) throws RowsExceededException, NumberFormatException, WriteException, BiffException, IOException;
	void performTableMaven(ApplicationInfo appInfo) throws RowsExceededException, WriteException, BiffException, IOException;
	void performBQL(ApplicationInfo appInfo) throws RowsExceededException, WriteException, BiffException, IOException;
	void performPD(ApplicationInfo appInfo);
	void performMM(ApplicationInfo appInfo);
	void performPkPdLink(ApplicationInfo appInfo);
	void performIRM(ApplicationInfo appInfo);
	void performTT(ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException;
	void performAscii(ApplicationInfo appInfo) throws IOException, RowsExceededException, WriteException, BiffException;
	void performInVitro(ApplicationInfo appInfo);
}
