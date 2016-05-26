package Model;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class XVsYAndPredYPlotGenerator {
	XvsYAndPredYLogGraphGenerator logGraphGeneInst;
	XVsYAndPredYLinearGraphGenerator normalGraphGeneInst;
	
	
	public void GenerateplottsForCAOutput(double XPlot[],
			double YPlot[], double[] termX, double[] Ycalc, String profile) throws RowsExceededException, WriteException, BiffException, IOException {

		generateNormalGraphs(XPlot, YPlot, termX, Ycalc, profile);
		generateCorrepondingLogGraph(XPlot, YPlot, termX, Ycalc, profile);
	}

	private void generateNormalGraphs(double[] xPlot, double[] yPlot,
			double[] termX, double[] ycalc, String profile) throws RowsExceededException, WriteException, BiffException, IOException {

		normalGraphGeneInst = new XVsYAndPredYLinearGraphGenerator(
				xPlot, yPlot, termX, ycalc, profile);

	}

	private void generateCorrepondingLogGraph(double[] xPlot,
			double[] yPlot, double[] termX, double[] ycalc, String profile) throws RowsExceededException, WriteException, BiffException, IOException {

		logGraphGeneInst = new XvsYAndPredYLogGraphGenerator(
				"Log View", xPlot, yPlot, termX, ycalc, profile);
	}

}
