package Model;

import java.io.IOException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PartialDerivativePlotGenerator {
	PartialDetLinearPlotGenerator  normalGraphGeneInst;
	PartialDevtLogPlotGenerator logGraphGeneInst;
	
	public void Generateplotts(double XPlot[], double YPlot[][],String profile, int numberOfParameter) throws RowsExceededException, WriteException, BiffException, IOException{//JDesktopPane displayResultsinternalFrameDesktopPane 
		
	/*	isFromNcaOutoutPlots = true;
		CAMainScreen.NcaOutputNumberOfplotnodes++;*/
		
		generateNormalGraphs(XPlot,YPlot,profile,numberOfParameter);
		generateCorrepondingLogGraph(XPlot,YPlot,profile,numberOfParameter);
	}



	private void generateNormalGraphs(double[] xPlot, double[][] yPlot, String profile, int numberOfParameter) throws RowsExceededException, WriteException, BiffException, IOException {
		
		normalGraphGeneInst = new PartialDetLinearPlotGenerator(xPlot,yPlot,profile,numberOfParameter);
		
	}



	private void generateCorrepondingLogGraph(double[] xPlot, double[][] yPlot, String profile,int numberOfParameter) throws RowsExceededException, WriteException, BiffException, IOException {
		
		logGraphGeneInst = new PartialDevtLogPlotGenerator("Log View",xPlot,yPlot,profile,numberOfParameter);
	}



}
