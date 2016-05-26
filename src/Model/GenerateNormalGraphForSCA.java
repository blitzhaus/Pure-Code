package Model;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JInternalFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import view.ApplicationInfo;

public class GenerateNormalGraphForSCA extends JInternalFrame {

	SemiCompartmentalModelling scaInst;
	ApplicationInfo appInfo;

	public GenerateNormalGraphForSCA(double[] xPlot, double[] yPlot,
			String profile) {

		scaInst = SemiCompartmentalModelling.creaetSCAInst();
		appInfo = scaInst.getAppInfo();

		final XYDataset dataset = createDataset(xPlot, yPlot);
		final JFreeChart chart = createChart(dataset, profile);

		scaInst.wsOutputts.getPlotOutputs().getLinearPlots().add(chart);
	}

	private JFreeChart createChart(XYDataset dataset, String profile) {

		final JFreeChart chart = ChartFactory.createXYLineChart(profile, // chart
																			// title
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getScaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getxColumnName(), // x axis label
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getScaInfo()
						.getProcessingInputs().getMappingDataObj()
						.getYcolumnName(), // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);
		chart.getTitle().setFont(new Font("Arial", Font.PLAIN, 12));

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);

		// final StandardLegend legend = (StandardLegend) chart.getLegend();
		// legend.setDisplaySeriesShapes(true);

		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();

		// plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinesVisible(false);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, true);
		renderer.setSeriesFillPaint(1, Color.blue);
		renderer.setSeriesShapesVisible(1, true);
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		// change the auto tick unit selection to integer units only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart;

	}

	private XYDataset createDataset(double[] xPlot, double[] yPlot) {

		final XYSeries series1 = new XYSeries("Observed");

		for (int i = 0; i < xPlot.length; i++) {
			series1.add(xPlot[i], yPlot[i]);
		}

		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);

		return dataset;
	}
}
