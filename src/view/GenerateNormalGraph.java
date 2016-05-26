package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.math.plot.utils.Array;
import org.w3c.dom.css.ViewCSS;

import view.ApplicationInfo;
import view.DisplayContents;

import Common.JinternalFrameFunctions;

public class GenerateNormalGraph extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenerateNormalGraph(double[] xPlot, double[] yPlot, double[] termX,
			double[] ycalc, String profile) throws RowsExceededException,
			WriteException, BiffException, IOException {

		final XYDataset dataset = createDataset(xPlot, yPlot, termX, ycalc);
		final JFreeChart chart = createChart(dataset, profile);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel
				.setPreferredSize(new java.awt.Dimension(NcaResultDispComp
						.createNcaResDispCompInst().displayResultsInternalFrame
						.getWidth(), NcaResultDispComp
						.createNcaResDispCompInst().displayResultsInternalFrame
						.getHeight()));
		chartPanel.setBackground(Color.white);
		JInternalFrame f = new JInternalFrame("normal graph", true, true, true,
				true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(f);
		f.setLocation(0, 0);
		f
				.setSize(
						NcaResultDispComp.createNcaResDispCompInst().displayResultsInternalFrame
								.getWidth(),
						NcaResultDispComp.createNcaResDispCompInst().displayResultsInternalFrame
								.getHeight());
		f.setVisible(true);
		f.moveToFront();
		NcaResultDispComp.createNcaResDispCompInst().displayResultsinternalFrameDesktopPane
				.add(f);
		NcaResultDispComp.createNcaResDispCompInst().outputPlotsArraylist
				.add(f);
		f.setContentPane(chartPanel);
	}

	public GenerateNormalGraph(double[] xPlot, double[] yPlot,
			double[] predictedY, String profile, int profileNumber) throws RowsExceededException,
			WriteException, BiffException, IOException {

		final XYDataset dataset = createDataset(xPlot, yPlot, predictedY, profileNumber);
		final JFreeChart chart = createChart(dataset, profile);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel
				.setPreferredSize(new java.awt.Dimension(NcaLambdaZDispGui
						.createLambDispGuiInstance().LambdaZplotInternalFrame
						.getWidth(), NcaLambdaZDispGui
						.createLambDispGuiInstance().LambdaZplotInternalFrame
						.getHeight() - 10));
		chartPanel.setBackground(Color.white);
		JInternalFrame f = new JInternalFrame("normal graph", true, true, true,
				true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(f);

		f.setLocation(0, 0);
		double width = NcaLambdaZDispGui.createLambDispGuiInstance().LambdaZplotInternalFrame
				.getWidth()
				* (2.0 / 3);
		f
				.setSize((int) width, NcaLambdaZDispGui
						.createLambDispGuiInstance().LambdaZplotInternalFrame
						.getHeight() - 10);
		f.setVisible(true);
		f.moveToFront();
		f.setBorder(DDViewLayer.createViewLayerInstance().b);
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZplotInternalFrameDesktopPane
				.add(f);
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZNormalGraphsInternalFramesArrayList
				.add(f);
		f.setContentPane(chartPanel);

	}

	private JFreeChart createChart(XYDataset dataset, String profile) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		final JFreeChart chart = ChartFactory
				.createXYLineChart(
						profile, // chart title
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo().getProcessingInputs()
								.getMappingDataObj().getxColumnName(), // x axis
																		// label
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getWorkBooksArrayList()
												.get(
														appInfo
																.getProjectInfoAL()
																.get(
																		appInfo
																				.getSelectedProjectIndex())
																.getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo().getProcessingInputs()
								.getMappingDataObj().getYcolumnName(), // y axis
																		// label
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
		renderer.setSeriesLinesVisible(0, false);
		renderer.setSeriesLinesVisible(1, true);
		renderer.setSeriesFillPaint(0, Color.black);
		renderer.setSeriesShapesVisible(0, true);
		renderer.setSeriesShapesVisible(1, true);
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		// change the auto tick unit selection to integer units only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart;

	}

	private XYDataset createDataset(double[] xPlot, double[] yPlot,
			double[] termX, double[] ycalc) {

		final XYSeries series1 = new XYSeries("Observed");

		for (int i = 0; i < xPlot.length; i++) {
			series1.add(xPlot[i], yPlot[i]);
		}

		// similarly we need to have another series for predicted y values.
		final XYSeries series2 = new XYSeries("Predicted");

		for (int i = 0; i < termX.length; i++) {
			series2.add(termX[i], ycalc[i]);

		}

		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);

		return dataset;
	}

	private XYDataset createDataset(double[] xPlot, double[] yPlot,
			double[] predictedY, int profileNumber) {

		final XYSeries series1 = new XYSeries("Observed");

		for (int i = 0; i < xPlot.length; i++) {
			if(yPlot[i] > 0){
				series1.add(xPlot[i], yPlot[i]);
			}
			
		}
		final XYSeries series2 = new XYSeries("Predicted");
		

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
/*		if(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNumber].getMethodSelected() == 3){
			
			ArrayList<Integer> indices = getCorrespondingxPlotIndices(xPlot, profileNumber);
			if(indices.size() == 0){
				indices  =  fillConsecutiveIndices(predictedY);
			}
			int m=0;
			for (int i = 0; i < predictedY.length; i++) {
				if (predictedY[i] == 0) {

				} else {
					series2.add(xPlot[indices.get(i)], predictedY[i]);

				}


			}
		} else{

		}*/
		for (int i = 0; i < predictedY.length; i++) {
			if (predictedY[i] == 0) {

			} else {
				series2.add(xPlot[i], predictedY[i]);

			}


		}


		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);

		return dataset;
	}

	private ArrayList<Integer> fillConsecutiveIndices(double[] predictedY) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i=0;i<predictedY.length;i++){
			al.add(i);
		}
		return al;
	}

	private ArrayList<Integer> getCorrespondingxPlotIndices(double[] xPlot, int profileNumber) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Hashtable<Integer, Boolean> hashTable;
		
		hashTable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNumber].getInclusionExcusionPoints();
		
		ArrayList<Integer> indices = new ArrayList<Integer>();
		Iterator<Entry<Integer, Boolean>> it = hashTable.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Boolean> map = (Map.Entry<Integer, Boolean>)it.next();
			if(map.getValue() == true){
				indices.add(map.getKey());
			}
		}
		
		return indices;
	}

}
