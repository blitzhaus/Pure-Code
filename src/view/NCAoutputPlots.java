package view;

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

public class NCAoutputPlots {
	Vector<Integer> scaleVector = new Vector<Integer>();
	double NcaOutputPlotsdivisionalValueForXRequiredForMarkings;
	double NcaOutputPlotsdivisionalValueForYRequiredForMarkings;
	boolean isFromNcaOutoutPlots;

	public static NCAoutputPlots NCA_OUT_PLOTS = null;

	public static NCAoutputPlots createNcaoutputPlotsInst() {
		if (NCA_OUT_PLOTS == null) {
			NCA_OUT_PLOTS = new NCAoutputPlots();
		}
		return NCA_OUT_PLOTS;
	}

	void GenerateplottsForNCAOutput(double XPlot[], double YPlot[],
			double[] termX, double[] Ycalc, int lambdaZ, String profile)
			throws RowsExceededException, WriteException, BiffException,
			IOException {// JDesktopPane displayResultsinternalFrameDesktopPane

		isFromNcaOutoutPlots = true;
		NcaOutputGeneration.createNcaOutGeneration().NcaOutputNumberOfplotnodes++;

		generateNormalGraphs(XPlot, YPlot, termX, Ycalc, profile);
		generateCorrepondingLogGraph(XPlot, YPlot, termX, Ycalc, lambdaZ,
				profile);
	}

	private void generateNormalGraphs(double[] xPlot, double[] yPlot,
			double[] termX, double[] ycalc, String profile)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		GenerateNormalGraph normalGraphInternalFrmae = new GenerateNormalGraph(
				xPlot, yPlot, termX, ycalc, profile);

	}

	void generateCorrepondingLogGraph(double[] xPlot, double[] yPlot,
			double[] termX, double[] ycalc, int lambdaZ, String profile)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		// GenerateLogGraph genLogGraphInst = new GenerateLogGraph();
		GenerateLogGraph logGraphIntenalFrame = new GenerateLogGraph(
				"Log View", xPlot, yPlot, termX, ycalc, lambdaZ, profile);
	}

	void generateLambdaZplotForTheProfileClicked(ArrayList<Double> observedX,
			ArrayList<Double> observedY, double[] predictedY, String profile, int profileNumber)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		// first convert the array lists into arrays
		double[] observedXArray = new double[observedX.size()];
		double[] observedYArray = new double[observedY.size()];
		for (int i = 0; i < observedX.size(); i++) {
			observedXArray[i] = observedX.get(i);
			observedYArray[i] = observedY.get(i);

		}

		generateNormalGraph(observedXArray, observedYArray, predictedY, profile, profileNumber);
		generateLogarithmicGraph(observedXArray, observedYArray, predictedY,
				profile, profileNumber);
	}

	void generateLogarithmicGraph(double[] observedXArray,
			double[] observedYArray, double[] predictedY, String profile, int profileNumber)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		GenerateLogGraph logGraphIntenalFrame = new GenerateLogGraph(
				"Log view", observedXArray, observedYArray, predictedY, profile, profileNumber);
		
	}

	void generateNormalGraph(double[] observedXArray, double[] observedYArray,
			double[] predictedY, String profile, int profileNumber) throws RowsExceededException,
			WriteException, BiffException, IOException {
		GenerateNormalGraph normalGraphInternalFrmae = new GenerateNormalGraph(
				observedXArray, observedYArray, predictedY, profile, profileNumber);
	}

	void generateSubAreasPlotForTheProfileClicked(ArrayList<Double> observedX,
			ArrayList<Double> observedY, String profile)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		// first convert the array lists into arrays
		double[] observedXArray = new double[observedX.size()];
		double[] observedYArray = new double[observedY.size()];
		for (int i = 0; i < observedX.size(); i++) {
			observedXArray[i] = observedX.get(i);
			observedYArray[i] = observedY.get(i);

		}

		generateLogarithmicSubAreaGraph(observedXArray, observedYArray, profile);

	}

	void generateLogarithmicSubAreaGraph(double[] observedXArray,
			double[] observedYArray, String profile)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		GenerateLogGraph logGraphIntenalFrame = new GenerateLogGraph(
				"Log view", observedXArray, observedYArray, profile);

	}

}
