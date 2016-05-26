package view;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import jxl.read.biff.File;

import org.jfree.chart.ChartUtilities;

import view.ApplicationInfo;



public class StoreResults {

	private BufferedWriter bout;
	private FileOutputStream fout;
	private DataOutputStream dout;
	public StoreResults(){
		
	}

	public void storeAnalysisResults(ApplicationInfo applicationInfo) throws FileNotFoundException {
		
		storeTextoutput(applicationInfo);
		storeSpreadSheets(applicationInfo);
		storePlots();
	}

	private void storePlots() throws FileNotFoundException {
		
/*		OutputStream out = new BufferedOutputStream(new FileOutputStream("fileName"));
		ChartUtilities.saveChartAsJPEG(out, chart, chart.width(), chart.height());*/
	}

	private void storeSpreadSheets(ApplicationInfo applicationInfo) {
/*		storeFinalParametersVerticalDisplay();
		storeFinalParametersHorizontalDisplay();
		storeSummaryTable();
		storeDosingTable();
		storeSubAreasTable();*/
		
		/*if(applicationInfo.getWorkSheetObjectsAL().get(applicationInfo.getSelectedSheetIndex())
				.getNcaInfo().getProcessingInputs().getModelInputsObj().getisSparseSelected() == true){
			storeIndividualsbyTimeTable();
		}*/
		
		
	}

	private void storeTextoutput(ApplicationInfo applicationInfo) {/*
		String s  = NCAMainScreen.completeTextOutputTextArea.getText();
		writeToFile(s, applicationInfo.getWorkSheetObjectsAL().get(applicationInfo.getSelectedSheetIndex())
				.getSheetName());
		
		
	*/}
	void writeToFile (String str, String outputFile)
	{
		try
		{
			fout = new FileOutputStream (outputFile);
			dout = new DataOutputStream (fout);
			bout = new BufferedWriter(new OutputStreamWriter(dout));
			bout.write(str);
		}
		catch (IOException e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	void  closeFile ()
	{
		try
		{
			bout.close();
			dout.close();
			fout.close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
}
