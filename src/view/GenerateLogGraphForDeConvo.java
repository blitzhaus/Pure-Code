package view;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import Common.JinternalFrameFunctions;

public class GenerateLogGraphForDeConvo extends JInternalFrame {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2483729717816155288L;
	String timeUnit;
	String concMassUnit;
	String volumeUnit;

	public GenerateLogGraphForDeConvo(final String title, double[] xplot, double[] yPlot,
			double[] termX, double[] yCalc, int lambdaZ, String profile)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		super(title);

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput(); 
		 
		
		timeUnit = procInputInst.getParameterUnitsDataObj().getTimeUnit();
		concMassUnit = procInputInst.getParameterUnitsDataObj()
				.getConcMassUnit();
		volumeUnit = procInputInst.getParameterUnitsDataObj()
				.getVolumeUnit();
		final XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries observedData = new XYSeries("Observed Data");

		for (int i = 0; i < xplot.length; i++) {
			if (yPlot[i] != 0)
				observedData.add(xplot[i], yPlot[i]);
			else
				continue;

		}
		dataset.addSeries(observedData);
		XYSeries predictedData = new XYSeries("Predicted Data");
		int xPlotLength = xplot.length;
		if (lambdaZ != 0) {
			for (int i = 0; i < yCalc.length; i++) {
				if ((termX[i] != 0) && (yCalc[i] != 0))
					predictedData.add(termX[i], (yCalc[i]));
			}
			dataset.addSeries(predictedData);
		}

		final JFreeChart chart = ChartFactory.createXYLineChart(profile,
				procInputInst.getMappingDataObj()
						.getxColumnName()
						+ " (" + timeUnit + ")", procInputInst.getMappingDataObj()
						.getYcolumnName()
						+ " (" + concMassUnit + "/" + ")", dataset,
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
		final NumberAxis domainAxis = new NumberAxis(procInputInst.getMappingDataObj().getxColumnName()
				+ " (" + timeUnit + ")");
		final NumberAxis rangeAxis = new LogarithmicAxis(procInputInst.getMappingDataObj().getYcolumnName()
				+ " (" + concMassUnit + "/" + volumeUnit + ")");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(rangeAxis);
		chart.setBackgroundPaint(Color.white);
		plot.setOutlinePaint(Color.black);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBackground(Color.white);
		chartPanel
				.setPreferredSize(new java.awt.Dimension(DeConvoResultDispCompCreator
						.createDeConvoResDispCompInst().displayResultsInternalFrame
						.getWidth(), DeConvoResultDispCompCreator
						.createDeConvoResDispCompInst().displayResultsInternalFrame
						.getHeight()));
		JInternalFrame f = new JInternalFrame("", false, false, false,
				false);

		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(f);
		f.setLocation(0, 0);
		f
				.setSize(
						DeConvoResultDispCompCreator.createDeConvoResDispCompInst().displayResultsInternalFrame
								.getWidth(),
								DeConvoResultDispCompCreator.createDeConvoResDispCompInst().displayResultsInternalFrame
								.getHeight());
		f.setVisible(true);
		f.setContentPane(chartPanel);
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().displayResultsinternalFrameDesktopPane
				.add(f);
		DeConvoResultDispCompCreator.createDeConvoResDispCompInst().outputPlotsLogViewsArrayList
				.add(f);
		f.moveToFront();

	}

	public GenerateLogGraphForDeConvo(final String title, double[] xplot, double[] yPlot,
			double[] predictedY, String profile, int profileNumber) throws RowsExceededException,
			WriteException, BiffException, IOException {

		super(title);
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput(); 

		
		
		
		final XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries observedData = new XYSeries("Observed Data");
		for (int i = 0; i < xplot.length; i++) {
			if ((yPlot[i] > 0) && (xplot[i] != 0))
				observedData.add(xplot[i], yPlot[i]);
			else
				continue;
		}
		dataset.addSeries(observedData);
		XYSeries predictedData = new XYSeries("predicted Data");
		
		

		for (int i = 0; i < predictedY.length; i++) {
			if ((predictedY[i] == 0) || (xplot[i] == 0)) {

			} else {
				predictedData.add(xplot[i], predictedY[i]);
			}
		}

		dataset.addSeries(predictedData);
		final JFreeChart chart = ChartFactory.createXYLineChart(profile,
				procInputInst.getMappingDataObj()
						.getxColumnName()
						+ " (" + timeUnit + ")", procInputInst.getMappingDataObj()
						.getYcolumnName()
						+ " (" + concMassUnit + "/" + volumeUnit + ")",
				dataset, PlotOrientation.VERTICAL, true, true, false);
		chart.getTitle().setFont(new Font("Arial", Font.PLAIN, 12));
		final XYPlot plot = chart.getXYPlot();

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, false);
		renderer.setSeriesFillPaint(0, Color.black);
		renderer.setSeriesShapesVisible(0, true);

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinesVisible(false);
		plot.setDomainGridlinesVisible(false);
		plot.setBackgroundPaint(Color.white);
		final NumberAxis domainAxis = new NumberAxis(procInputInst.getMappingDataObj().getxColumnName()
				+ " (" + timeUnit + ")");
		final NumberAxis rangeAxis = new LogarithmicAxis(procInputInst.getMappingDataObj().getYcolumnName()
				+ " (" + concMassUnit + "/" + volumeUnit + ")");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(rangeAxis);
		chart.setBackgroundPaint(Color.white);
		plot.setOutlinePaint(Color.black);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBackground(Color.white);
		double width = DeConvoLambdaZDispGui.createLambDispGuiInstance().LambdaZplotInternalFrame
				.getWidth()
				* (2.0 / 3);
		chartPanel
				.setPreferredSize(new java.awt.Dimension(DeConvoLambdaZDispGui
						.createLambDispGuiInstance().LambdaZplotInternalFrame
						.getWidth() / 2, DeConvoLambdaZDispGui
						.createLambDispGuiInstance().LambdaZplotInternalFrame
						.getHeight() - 10));
		JInternalFrame f = new JInternalFrame("ajith", false, false, false,
				false);

		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(f);
		f.setLocation(0, 0);
		f.setBorder(DDViewLayer.createViewLayerInstance().b);
		f
				.setSize((int) width, DeConvoLambdaZDispGui
						.createLambDispGuiInstance().LambdaZplotInternalFrame
						.getHeight() - 10);
		f.setVisible(true);
		f.setContentPane(chartPanel);
		DeConvoLambdaZDispGui.createLambDispGuiInstance().lambdaZplotInternalFrameDesktopPane
				.add(f);
		DeConvoLambdaZDispGui.createLambDispGuiInstance().lambdaZLogarithmicGraphsInternalFramesArrayList
				.add(f);
		f.moveToFront();

	}
	private ArrayList<Integer> fillConsecutiveIndices(double[] predictedY) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i=0;i<predictedY.length;i++){
			al.add(i);
		}
		return al;
	}
	
	
	
	

}

