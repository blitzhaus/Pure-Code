package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
import view.DisplayContents;
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

	String timeUnit;
	String concMassUnit;
	String volumeUnit;

	public GenerateLogGraph(final String title, double[] xplot, double[] yPlot,
			double[] termX, double[] yCalc, int lambdaZ, String profile)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		super(title);

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		timeUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj().getTimeUnit();
		concMassUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj()
				.getConcMassUnit();
		volumeUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj()
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
				+ " (" + concMassUnit + "/" + volumeUnit + ")");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(rangeAxis);
		chart.setBackgroundPaint(Color.white);
		plot.setOutlinePaint(Color.black);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBackground(Color.white);
		chartPanel
				.setPreferredSize(new java.awt.Dimension(NcaResultDispComp
						.createNcaResDispCompInst().displayResultsInternalFrame
						.getWidth(), NcaResultDispComp
						.createNcaResDispCompInst().displayResultsInternalFrame
						.getHeight()));
		JInternalFrame f = new JInternalFrame("ajith", false, false, false,
				false);

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
		f.setContentPane(chartPanel);
		NcaResultDispComp.createNcaResDispCompInst().displayResultsinternalFrameDesktopPane
				.add(f);
		NcaResultDispComp.createNcaResDispCompInst().outputPlotsLogViewsArrayList
				.add(f);
		f.moveToFront();

	}

	public GenerateLogGraph(final String title, double[] xplot, double[] yPlot,
			double[] predictedY, String profile, int profileNumber) throws RowsExceededException,
			WriteException, BiffException, IOException {

		super(title);
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
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
		
		
/*		if(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNumber].getMethodSelected() == 3){
			ArrayList<Integer> indices = getCorrespondingxPlotIndices(xplot, profileNumber);
			if(indices.size() == 0){
				indices =  fillConsecutiveIndices(predictedY);
			}
			int m=0;
			for (int i = 0; i < predictedY.length; i++) {
				if ((predictedY[i] == 0) || (xplot[i] == 0)) {

				} else {
					predictedData.add(xplot[indices.get(i)], predictedY[i]);
				}
			}
		} else{

		}*/
		for (int i = 0; i < predictedY.length; i++) {
			if ((predictedY[i] == 0) || (xplot[i] == 0)) {

			} else {
				predictedData.add(xplot[i], predictedY[i]);
			}
		}

		dataset.addSeries(predictedData);
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
				+ " (" + concMassUnit + "/" + volumeUnit + ")");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(rangeAxis);
		chart.setBackgroundPaint(Color.white);
		plot.setOutlinePaint(Color.black);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBackground(Color.white);
		double width = NcaLambdaZDispGui.createLambDispGuiInstance().LambdaZplotInternalFrame
				.getWidth()
				* (2.0 / 3);
		chartPanel
				.setPreferredSize(new java.awt.Dimension(NcaLambdaZDispGui
						.createLambDispGuiInstance().LambdaZplotInternalFrame
						.getWidth() / 2, NcaLambdaZDispGui
						.createLambDispGuiInstance().LambdaZplotInternalFrame
						.getHeight() - 10));
		JInternalFrame f = new JInternalFrame("ajith", false, false, false,
				false);

		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(f);
		f.setLocation(0, 0);
		f.setBorder(DDViewLayer.createViewLayerInstance().b);
		f
				.setSize((int) width, NcaLambdaZDispGui
						.createLambDispGuiInstance().LambdaZplotInternalFrame
						.getHeight() - 10);
		f.setVisible(true);
		f.setContentPane(chartPanel);
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZplotInternalFrameDesktopPane
				.add(f);
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZLogarithmicGraphsInternalFramesArrayList
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
	public GenerateLogGraph(String string, double[] xplot, double[] yPlot,
			String profile) throws RowsExceededException, WriteException,
			BiffException, IOException {

		super(profile);
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		final XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries observedData = new XYSeries("Observed Data");

		for (int i = 0; i < xplot.length; i++) {
			if ((yPlot[i] != 0) && (xplot[i] != 0))
				observedData.add(xplot[i], yPlot[i]);
			else
				continue;

		}
		dataset.addSeries(observedData);

		
		

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
						+ " (" + concMassUnit + "/" + volumeUnit + ")",
				dataset, PlotOrientation.VERTICAL, true, true, false);
		chart.getTitle().setFont(new Font("Arial", Font.PLAIN, 12));
		final XYPlot plot = chart.getXYPlot();

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, true);
		renderer.setSeriesFillPaint(0, Color.black);
		renderer.setSeriesShapesVisible(0, true);

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
				+ " (" + concMassUnit + "/" + volumeUnit + ")");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(rangeAxis);
		chart.setBackgroundPaint(Color.white);
		plot.setOutlinePaint(Color.black);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBackground(Color.white);
		double width = NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaGraphInternalFrame
				.getWidth();
		chartPanel
				.setPreferredSize(new java.awt.Dimension(
						NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaGraphInternalFrame
								.getWidth(),
						NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaGraphInternalFrame
								.getHeight()));
		JInternalFrame f = new JInternalFrame("ajith", false, false, false,
				false);

		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(f);
		f.setLocation(0, 0);
		f.setBorder(DDViewLayer.createViewLayerInstance().b);
		f.setSize((int) width, NcaSubAreasDispGui
				.createNcaSubAreasDispGuiInst().partialAreaGraphInternalFrame
				.getHeight());
		f.setVisible(true);
		f.setContentPane(chartPanel);
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaGraphDesktopPane
				.removeAll();
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaGraphDesktopPane
				.add(f);

		f.moveToFront();

	}

	public static void main(final String[] args) {

	}

}

