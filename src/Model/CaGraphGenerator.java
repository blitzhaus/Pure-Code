package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



public class CaGraphGenerator {
	
	

	XVsYAndPredYPlotGenerator caOutputPlotsInst;
	ObsdYVsWeightPredYPlotGenerator obsYVSWeigtPredYPlotInst;
	WeightPredYVsWeightResidYPlotGenerator weightPredYVSWeigtResdYPlot;
	XVsWeightResidYPlotGenerator xVsWeightResdYPlotInst;
	PartialDerivativePlotGenerator partDerivativeplotInst;

	
	public void plotGeneration(double[] time,
			double[] conc, double[] calcYval, String profile,
			double[] weightedPredictedY, double[] weightedResidualY) throws RowsExceededException, WriteException, BiffException, IOException
	{
		
		caOutputPlotsInst = new XVsYAndPredYPlotGenerator();
		obsYVSWeigtPredYPlotInst = new ObsdYVsWeightPredYPlotGenerator();
		weightPredYVSWeigtResdYPlot = new WeightPredYVsWeightResidYPlotGenerator();
		xVsWeightResdYPlotInst = new XVsWeightResidYPlotGenerator();

		caOutputPlotsInst.GenerateplottsForCAOutput(time, conc, time,
				calcYval, profile);
		obsYVSWeigtPredYPlotInst.Generateplotts(conc, weightedPredictedY,
				profile);
		weightPredYVSWeigtResdYPlot.Generateplotts(weightedPredictedY,
				weightedResidualY, profile);
		xVsWeightResdYPlotInst.Generateplotts(time, weightedResidualY, profile);
	}

	
	public void partialDetPlotGeneration(double xPlot[], double yPlot[][],String profile, int noOfParam) throws RowsExceededException, WriteException, BiffException, IOException
	{
		partDerivativeplotInst = new PartialDerivativePlotGenerator();

		partDerivativeplotInst.Generateplotts(xPlot, yPlot, profile,
				noOfParam);
	}
	
}
