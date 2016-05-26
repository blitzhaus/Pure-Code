package Model;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class SemiCompartmentalOutputPlots {

	public void GenerateplottsForSCAOutput(double XPlot[], double YPlot[],
			String profile) {// JDesktopPane
								// displayResultsinternalFrameDesktopPane

		generateNormalGraphs(XPlot, YPlot, profile);
		generateCorrepondingLogGraph(XPlot, YPlot, profile);
	}

	private void generateNormalGraphs(double[] xPlot, double[] yPlot,
			String profile) {

		GenerateNormalGraphForSCA normalGraphInternalFrame = new GenerateNormalGraphForSCA(
				xPlot, yPlot, profile);

	}

	private void generateCorrepondingLogGraph(double[] xPlot, double[] yPlot,
			String profile) {

		GenerateLogGraphForSCA logGraphIntenalFrame = new GenerateLogGraphForSCA(
				"Log View", xPlot, yPlot, profile);
	}

}
