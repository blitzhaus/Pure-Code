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
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import view.ApplicationInfo;
//import org.math.plot.plots.ScatterPlot;

import Common.JinternalFrameFunctions;


//import org.jfree.ui.RefineryUtilities;

/**
 * A demo showing the use of log axes.
 *
 */
public class GenerateLogGraph extends JInternalFrame {

	/**
	 * Creates a new demo.
	 * 
	 * @param title
	 *            the frame title.
	 * @param profile
	 * @throws IOException
	 * @throws BiffException
	 * @throws WriteException
	 * @throws RowsExceededException
	 * @throws IOException
	 */

	ApplicationInfo appInfo = NCA.creaetNCAInst().getAppInfo();

	public GenerateLogGraph() {

	}

	private String timeUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
			.get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getNcaInfo()
			.getProcessingInputs().getParameterUnitsDataObj().getTimeUnit();

	private String concUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
			.get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getNcaInfo()
			.getProcessingInputs().getParameterUnitsDataObj().getConcMassUnit();

	private String volumeUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
			.get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex()).getNcaInfo()
			.getProcessingInputs().getParameterUnitsDataObj().getVolumeUnit();

	GenerateLogGraph(final String title, double[] xplot, double[] yPlot,
			double[] termX, double[] yCalc, int lambdaZ, String profile)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		super(title);

		final XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries observedData = new XYSeries("Observed Data");

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
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getxColumnName()
						+ " (" + timeUnit + ")", appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getYcolumnName()
						+ " (" + concUnit + "/" + volumeUnit + ")", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		// ChartFactory.createXYLineChart(title, xAxisLabel, yAxisLabel,
		// dataset, orientation, legend, tooltips, urls)
		final XYPlot plot = chart.getXYPlot();

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, true);
		renderer.setSeriesFillPaint(1, Color.blue);
		renderer.setSeriesShapesVisible(1, true);
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinesVisible(false);
		plot.setDomainGridlinesVisible(false);
		plot.setBackgroundPaint(Color.white);
		final NumberAxis domainAxis = new NumberAxis(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName()
				+ " (" + timeUnit + ")");
		final NumberAxis rangeAxis = new LogarithmicAxis(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName()
				+ " (" + concUnit + "/" + volumeUnit + ")");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(rangeAxis);
		chart.setBackgroundPaint(Color.white);
		plot.setOutlinePaint(Color.black);
		chart.getTitle().setFont(new Font("Areal", Font.PLAIN, 12));

		NCA.creaetNCAInst().wsOutputts.getPlotOutputs().getLogplots()
				.add(chart);

	}

}

