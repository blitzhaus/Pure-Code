package Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import view.ApplicationInfo;
import view.ProcessingInputsInfo; //import org.math.plot.plots.ScatterPlot;

//import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing the use of log axes.
 * 
 */
public class GenerateLogGraphForDeConvo extends JInternalFrame {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2327179395206678700L;

	/*public GenerateLogGraphForDeConvo() {

	}*/

	private String timeUnit;

	private String concUnit;

	private String volumeUnit;
	ApplicationInfo appInfo;
	ProcessingInputsInfo procInputInst;

	public GenerateLogGraphForDeConvo(final String title, double[] xplot,
			double[] yPlot, double[] termX, double[] yCalc, double lambdaZ,
			String profile) throws RowsExceededException, WriteException,
			BiffException, IOException {

		super(title);

		final XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries observedData = new XYSeries("Observed Data");

		appInfo = DeConvolutionVariables.createDeConvoVarInstance().appInfo;
		procInputInst = DeConvolutionVariables.createDeConvoVarInstance()
				.copyProcessingInput();

		timeUnit = procInputInst.getParameterUnitsDataObj().getTimeUnit();

		concUnit = procInputInst.getParameterUnitsDataObj().getConcMassUnit();

		volumeUnit = procInputInst.getParameterUnitsDataObj().getVolumeUnit();

		for (int i = 0; i < xplot.length; i++) {
			if (yPlot[i] > 0)
				observedData.add(xplot[i], yPlot[i]);
			else
				continue;

		}
		dataset.addSeries(observedData);

		XYSeries predictedData = new XYSeries("Predicted Data");
		int xPlotLength = xplot.length;

		for (int i = 0; i < xplot.length; i++) {

		}

		if (lambdaZ != 0) {
			for (int i = 0; i < yCalc.length; i++) {

				if ((termX[i] != 0) && (yCalc[i] != 0))
					predictedData.add(termX[i], (yCalc[i]));
			}
			dataset.addSeries(predictedData);
		}

		final JFreeChart chart = ChartFactory.createXYLineChart(profile,
				procInputInst.getMappingDataObj().getxColumnName() + " ("
						+ timeUnit + ")", procInputInst.getMappingDataObj()
						.getYcolumnName()
						+ " (" + concUnit + "/" + volumeUnit + ")", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		// ChartFactory.createXYLineChart(title, xAxisLabel, yAxisLabel,
		// dataset, orientation, legend, tooltips, urls)
		final XYPlot plot = chart.getXYPlot();

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, false);
		renderer.setSeriesFillPaint(1, Color.blue);
		renderer.setSeriesShapesVisible(1, true);
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinesVisible(false);
		plot.setDomainGridlinesVisible(false);
		plot.setBackgroundPaint(Color.white);
		final NumberAxis domainAxis = new NumberAxis(procInputInst
				.getMappingDataObj().getxColumnName()
				+ " (" + timeUnit + ")");
		final NumberAxis rangeAxis = new LogarithmicAxis(procInputInst
				.getMappingDataObj().getYcolumnName()
				+ " (" + concUnit + "/" + volumeUnit + ")");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(rangeAxis);
		chart.setBackgroundPaint(Color.white);
		plot.setOutlinePaint(Color.black);
		chart.getTitle().setFont(new Font("Areal", Font.PLAIN, 12));

		DeConvolutionVariables.createDeConvoVarInstance().workSheetOutputInst
				.getPlotOutputs().getLogplots().add(chart);
		
	

	}

}
