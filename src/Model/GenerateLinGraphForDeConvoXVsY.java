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

import view.ProcessingInputsInfo;




public class GenerateLinGraphForDeConvoXVsY extends JInternalFrame {
	DeConvolutionVariables deConvoVarInst;
	ProcessingInputsInfo procInputInst;
	
	public GenerateLinGraphForDeConvoXVsY(double[] xPlot, double[] yPlot,
			double[] termX, double[] ycalc, String profile) throws RowsExceededException, WriteException, BiffException, IOException {
		
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		procInputInst = deConvoVarInst.copyProcessingInput();
		final XYDataset dataset = createDataset(xPlot, yPlot, termX, ycalc);
		final JFreeChart chart = createChart(dataset, profile);
		
		deConvoVarInst.getWorkSheetOutputInst().getPlotOutputs().getLinearPlots().add(chart);
		
	}

	private JFreeChart createChart(XYDataset dataset, String profile) {

		
		String timeUnit = "";
		
		String concMassUnit ="";
		String volumeUnit = "";
		
		
		
		final JFreeChart chart = ChartFactory.createXYLineChart(profile, // chart
																			// title
				deConvoVarInst.xColumnName
						+ " (" + timeUnit + ")", // x axis label
				deConvoVarInst.yColumnName
						+ " ("
						+ concMassUnit
						+ "/"
						+ volumeUnit + ")", // x axis label
				// y axis label
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

	private XYDataset createDataset(double[] xPlot, double[] yPlot,
			double[] termX, double[] ycalc) {

		final XYSeries series1 = new XYSeries("Observed");

		for (int i = 0; i < xPlot.length; i++) {
			series1.add(xPlot[i], yPlot[i]);
		}

		final XYSeries series2 = new XYSeries("Predicted");
		for (int i = 0; i < termX.length; i++) {
			series2.add(termX[i], ycalc[i]);
		}

		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);

		return dataset;
	}

	protected void removeTitleBarForinternalFrame(JInternalFrame j) {
		javax.swing.plaf.InternalFrameUI ifu = j.getUI();
		((javax.swing.plaf.basic.BasicInternalFrameUI) ifu).setNorthPane(null);
	}
}
