package Model;

import java.io.IOException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class XVsWeightResidYPlotGenerator {
	XVsWeightResidYLinearPlotGeneration  normalGraphgeneInst;
	XVsWeightResidYLogPlotGenerator logGraphGeneInst;
	
	public void Generateplotts(double XPlot[], double YPlot[],String profile) throws RowsExceededException, WriteException, BiffException, IOException{//JDesktopPane displayResultsinternalFrameDesktopPane 
			
		generateNormalGraphs(XPlot,YPlot,profile);
		generateCorrepondingLogGraph(XPlot,YPlot,profile);
	}

	private void generateNormalGraphs(double[] xPlot, double[] yPlot, String profile) throws RowsExceededException, WriteException, BiffException, IOException {
		
		normalGraphgeneInst = new XVsWeightResidYLinearPlotGeneration(xPlot,yPlot,profile);
		
	}


	private void generateCorrepondingLogGraph(double[] xPlot, double[] yPlot, String profile) throws RowsExceededException, WriteException, BiffException, IOException {
		
		logGraphGeneInst = new XVsWeightResidYLogPlotGenerator("Log View",xPlot,yPlot,profile);
	}

}
