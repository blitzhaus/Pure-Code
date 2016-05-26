package Model;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.sql.rowset.serial.SerialArray;
import javax.swing.JInternalFrame;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.Series;
import org.jfree.data.general.SeriesChangeEvent;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import view.ApplicationInfo;

public class plotMavenLogViewGeneration {

	XYSeriesCollection logDataSet;

	public JFreeChart plotMavenLogViewGeneration(double[] xPlot,
			double[] yPlot, String seriesName, String profileForDisplay,
			double[] upperDeviation, double[] lowerDeviation,
			ApplicationInfo appInfo) throws RowsExceededException,
			WriteException, BiffException, IOException {
		logDataSet = addSeriesToDataSet(xPlot, yPlot, seriesName,
				upperDeviation, lowerDeviation, appInfo);
		JFreeChart logChart = GenerateChart(logDataSet, profileForDisplay,
				appInfo);
		/*
		 * ChartPanel chartPanel = new ChartPanel(logChart);
		 * PlotMavenMainScreen.createPlotMavenInstance().currentLogFrame = new
		 * JInternalFrame( "Log View", true, true, true, true);
		 * PlotMavenMainScreen.createPlotMavenInstance().currentLogFrame
		 * .setLocation(0, 0);
		 * PlotMavenMainScreen.createPlotMavenInstance().currentLogFrame
		 * .setSize(
		 * PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame
		 * .getWidth(),
		 * PlotMavenCreateUI.createPlotMavenUIInst().logPlotsInternalFrame
		 * .getHeight() - 20);
		 * PlotMavenMainScreen.createPlotMavenInstance().currentLogFrame
		 * .setVisible(true);
		 * PlotMavenMainScreen.createPlotMavenInstance().currentLogFrame
		 * .setContentPane(chartPanel);
		 * PlotMavenCreateUI.createPlotMavenUIInst()
		 * .logPlotsInternalFrameDesktopPane
		 * .add(PlotMavenMainScreen.createPlotMavenInstance().currentLogFrame);
		 * PMResultsDispComp.createPMDispInst().logInternalFramesArrayList
		 * .add(PlotMavenMainScreen.createPlotMavenInstance().currentLogFrame);
		 */

		return logChart;

	}

	public static JFreeChart GenerateChart(XYSeriesCollection logDataSet,
			String profileForDisplay, ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		final JFreeChart logChart = ChartFactory.createXYLineChart(
				profileForDisplay, // chart title
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getxColumnName(), // x
				// axis
				// label
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getYcolumnName(), // y
				// axis
				// label
				logDataSet, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		logChart.setBackgroundPaint(Color.white);
		logChart.getTitle().setFont(new Font("Serif", Font.BOLD, 14));

		XYPlot plot = logChart.getXYPlot();
		plot.setRangeGridlinesVisible(false);
		plot.setDomainGridlinesVisible(false);
		plot.setBackgroundPaint(Color.white);
		final NumberAxis domainAxis = new NumberAxis(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName());
		final NumberAxis rangeAxis = new LogarithmicAxis(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName());
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(rangeAxis);
		logChart.setBackgroundPaint(Color.white);
		plot.setOutlinePaint(Color.black);
		plot.getDomainAxis().setLabelFont(new Font("Serif", Font.BOLD, 14));
		plot.getRangeAxis().setLabelFont(new Font("Serif", Font.BOLD, 14));

		XYLineAndShapeRenderer logrenderer = new XYLineAndShapeRenderer();
		logrenderer.setSeriesLinesVisible(0, true);
		for (int i = 0; i < plot.getSeriesCount(); i++)
			logrenderer.setSeriesPaint(i, PlotMavenExecutionClass
					.chooseNewColor(i));

		plot.setRenderer(logrenderer);
		// plot.getRenderer().setseries

		// change the auto tick unit selection to integer units only...
		NumberAxis lograngeAxis = (NumberAxis) plot.getRangeAxis();
		lograngeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		/*
		 * final StandardLegend legend = (StandardLegend) logChart.getLegend();
		 * legend.setDisplaySeriesShapes(true);
		 */
		// get a reference to the plot for further customisation...

		return logChart;

	}

	private XYSeriesCollection addSeriesToDataSet(double[] xPlot,
			double[] yPlot, String seriesName, double[] upperDeviation,
			double[] lowerDeviation, ApplicationInfo appInfo)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		XYSeries series = new XYSeries(seriesName);
		for (int i = 0; i < xPlot.length; i++) {
			if ((xPlot[i] != 0) && (yPlot[i] != 0)) {
				series.add(xPlot[i], yPlot[i]);
			}
		}
		logDataSet = new XYSeriesCollection();
		logDataSet.addSeries(series);

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().getMappingDataObj().getUpVariable()
				.equals("Up Variable") == true) {

		} else {
			for (int i = 0; i < xPlot.length; i++) {
				XYSeries errorseries = new XYSeries(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getUpVariable()
						+ i);
				if ((xPlot[i] != 0) && (yPlot[i] != 0))
					errorseries.add(xPlot[i], yPlot[i]);
				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.isScaleIsRelative() == true) {
					if ((xPlot[i] != 0) && (upperDeviation[i] + yPlot[i]) != 0)
						errorseries.add(xPlot[i],
								(upperDeviation[i] + yPlot[i]));
				} else {
					if ((xPlot[i] != 0) && (upperDeviation[i] + yPlot[i]) != 0)
						errorseries.add(xPlot[i], upperDeviation[i]);
				}

				logDataSet.addSeries(errorseries);
			}
		}

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().getMappingDataObj().getDownVariable()
				.equals("Down Variable")) {

		} else {
			for (int i = 0; i < xPlot.length; i++) {
				XYSeries errorseries = new XYSeries(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.getDownVariable()
						+ i + " ");
				if ((xPlot[i] != 0) && (yPlot[i]) != 0)
					errorseries.add(xPlot[i], yPlot[i]);
				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj()
						.isScaleIsAbsolute() == true) {
					if ((xPlot[i] != 0) && (lowerDeviation[i] + yPlot[i]) != 0)
						errorseries.add(xPlot[i], yPlot[i] - lowerDeviation[i]);
				} else {
					if ((xPlot[i] != 0) && (lowerDeviation[i] + yPlot[i]) != 0)
						errorseries.add(xPlot[i], lowerDeviation[i]);
				}

				logDataSet.addSeries(errorseries);
			}
		}

		return logDataSet;
	}

}
