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


public class GenerateLinGraphForDeConvoCumuAbs extends
		JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8413762451994735027L;
	DeConvolutionVariables deConvoVarInst;
	ProcessingInputsInfo procInputInst;

	public GenerateLinGraphForDeConvoCumuAbs(double[] xPlot,
			double[] yPlot, String profile) throws RowsExceededException, WriteException, BiffException, IOException {
		
		deConvoVarInst = DeConvolutionVariables.createDeConvoVarInstance();
		procInputInst = deConvoVarInst.copyProcessingInput();
		
		final XYDataset dataset = createDataset(xPlot, yPlot);
		final JFreeChart chart = createChart(dataset, profile);
		
		deConvoVarInst.getWorkSheetOutputInst().getPlotOutputs().getLinearPlotsForObsevedYVsWeightedPredictedY().add(chart);
				
	}

	private JFreeChart createChart(XYDataset dataset, String profile) {
						
		String concMassUnit = "";
		String volumeUnit = "";
		
		final JFreeChart chart = ChartFactory.createXYLineChart(profile, // chart
																			// title
				"Observed "
						+ procInputInst.getMappingDataObj().getxColumnName()+ "(" + concMassUnit
						+ "/" + volumeUnit + ")", // x axis label
				"Cumu_Abs" , // y axis label
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
