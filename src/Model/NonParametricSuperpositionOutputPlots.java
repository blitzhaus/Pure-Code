package Model;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class NonParametricSuperpositionOutputPlots {

	public void GenerateplottsForNPSOutput(double XPlot[], double YPlot[],
			String profile) {// JDesktopPane
								// displayResultsinternalFrameDesktopPane

		generateNormalGraphs(XPlot, YPlot, profile);
		generateCorrepondingLogGraph(XPlot, YPlot, profile);
	}

	private void generateNormalGraphs(double[] xPlot, double[] yPlot,
			String profile) {

		GenerateNormalGraphForNPS normalGraphInternalFrame = new GenerateNormalGraphForNPS(
				xPlot, yPlot, profile);

	}

	private void generateCorrepondingLogGraph(double[] xPlot, double[] yPlot,
			String profile) {

		GenerateLogGraphForNPS logGraphIntenalFrame = new GenerateLogGraphForNPS(
				"Log View", xPlot, yPlot, profile);
	}

}
