package view;

import java.io.IOException;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import view.ApplicationInfo;

import view.DisplayContents;


import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class StoreDosingTableHeader {

	public void storingTextOutput()
	{
		
	}
	
	public void storeDosingTableHeader() throws RowsExceededException, WriteException, BiffException, IOException
	{
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo
		.getProjectInfoAL()
		.get(appInfo.getSelectedProjectIndex())
		.getWorkBooksArrayList()
		.get(
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL()
		.get(
				appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(
												appInfo
														.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getSelectedSheetIndex()).getAnalysisType();
		String[] headers = null;
		TableColumnModel tm = null;
		if(analysisType.equals("pk"))
		{
			headers = new String[CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing.getColumnCount()];
			tm = CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing.getColumnModel();
		}else if (analysisType.equals("mm") || analysisType.equals("pkpdlink") || analysisType.equals("irm"))
		{
			headers = new String[CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
					.getColumnCount()];
			tm = CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing.getColumnModel();
		}
		
		for (int i = 0; i < headers.length; i++) {
			TableColumn tc= tm.getColumn(i);
			headers[i] =(String) tc.getHeaderValue();
		}
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())	.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
		.getPkInfo().getProcessingInputs().getDosingDataObj().setDosingDSHeader(headers);
	}
	
	
}
