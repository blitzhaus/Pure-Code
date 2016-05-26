package Model;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JInternalFrame;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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




public class XVsWeightResidYLinearPlotGeneration extends JInternalFrame {
	
	PkPdInfo pkPdInst;
	
	public XVsWeightResidYLinearPlotGeneration(double[] xPlot,
			double[] yPlot, String profile) throws RowsExceededException, WriteException, BiffException, IOException {
		
		pkPdInst = PkPdInfo.createPKPDInstance();
		
		final XYDataset dataset = createDataset(xPlot, yPlot);
		final JFreeChart chart = createChart(dataset, profile);
		
		pkPdInst.getWorkSheetOutputInst().getPlotOutputs().getLinearPlotForXVsWeightedResidualY().add(chart);
				
	}

	private JFreeChart createChart(XYDataset dataset, String profile) {

			
		String timeUnit = pkPdInst.timeUnit;
		
		String concMassUnit = pkPdInst.concMassUnit;
		String volumeUnit = pkPdInst.volumeUnit;
		
		final JFreeChart chart = ChartFactory.createXYLineChart(profile, // chart
																			// title
				pkPdInst.xColumnName
						+ "(" + timeUnit + ")", // x axis label
				"Weighted Residual "
						+pkPdInst.yColumnName + "(" + concMassUnit
						+ "/" + volumeUnit + ")", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);
		chart.getTitle().setFont(new Font("Arial", Font.PLAIN, 12));

		chart.setBackgroundPaint(Color.white);
		final XYPlot plot = chart.getXYPlot();

		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinesVisible(false);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, true);
		renderer.setSeriesFillPaint(1, Color.blue);
		renderer.setSeriesShapesVisible(1, true);
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

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

	protected void removeTitleBarForinternalFrame(JInternalFrame j) {
		javax.swing.plaf.InternalFrameUI ifu = j.getUI();
		((javax.swing.plaf.basic.BasicInternalFrameUI) ifu).setNorthPane(null);
	}
}
