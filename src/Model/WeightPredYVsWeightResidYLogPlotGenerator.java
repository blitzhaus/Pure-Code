package Model;

import java.awt.Color;
import java.io.IOException;

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




public class WeightPredYVsWeightResidYLogPlotGenerator extends
		JInternalFrame {
	PkPdInfo pkPdInst;

	public WeightPredYVsWeightResidYLogPlotGenerator(
			final String title, double[] xplot, double[] yPlot, String profile) throws RowsExceededException, WriteException, BiffException, IOException {

		super(title);
		
		pkPdInst = PkPdInfo.createPKPDInstance();
		
		final XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries observedData = new XYSeries("Observed Data");

		for (int i = 0; i < xplot.length; i++) {
			if (yPlot[i] > 1)
				observedData.add(xplot[i], yPlot[i]);
			else
				continue;

		}
		dataset.addSeries(observedData);

		String timeUnit = pkPdInst.timeUnit;
		
		String concMassUnit = pkPdInst.concMassUnit;
		String volumeUnit = pkPdInst.volumeUnit;
		
		final JFreeChart chart = ChartFactory.createXYLineChart(profile,
				"Weighted Predicted "
						+ pkPdInst.yColumnName + "(" + concMassUnit
						+ "/" + volumeUnit + ")", "Weighted Residual "
						+ pkPdInst.yColumnName+ "(" + concMassUnit
						+ "/" + volumeUnit + ")", dataset,
				PlotOrientation.VERTICAL, true, true, false);

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
		final NumberAxis domainAxis = new NumberAxis("Weighted Predicted "
				+ pkPdInst.yColumnName + "(" + concMassUnit + "/"
				+ volumeUnit + ")");
		final NumberAxis rangeAxis = new LogarithmicAxis("Weighted Residual "
				+ pkPdInst.yColumnName + "(" + concMassUnit + "/"
				+ volumeUnit + ")");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(rangeAxis);
		chart.setBackgroundPaint(Color.white);
		plot.setOutlinePaint(Color.black);
		
		pkPdInst.getWorkSheetOutputInst().getPlotOutputs().getLogPlotForWeightedPredictedYVsWeightedResidualY().add(chart);
				

	}

	public void main(final String[] args) {

	}

	protected static void removeTitleBarForinternalFrame(JInternalFrame j) {
		javax.swing.plaf.InternalFrameUI ifu = j.getUI();
		((javax.swing.plaf.basic.BasicInternalFrameUI) ifu).setNorthPane(null);
	}

}
