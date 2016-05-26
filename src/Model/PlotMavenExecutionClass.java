package Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JInternalFrame;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import view.ApplicationInfo;

public class PlotMavenExecutionClass {

	protected static double divisionalValueForXRequiredForMarkings;
	protected static double divisionalValueForYRequiredForMarkings;
	protected static int previousMaxScale;

	public PlotMavenExecutionClass() {
		
	}

	@SuppressWarnings("deprecation")
	public void generatePlots(double[] XPlot, double[] YPlot, String[] profile,
			int numberOfCanvases, boolean sameCanvas, double[] upperDeviation,
			double[] lowerDeviation, ApplicationInfo appInfo,
			WorkSheetOutputs wsOutput) throws RowsExceededException,
			WriteException, BiffException, IOException {

		// the below if loop is executed if there is no group variable present
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo()
				.getProcessingInputs().getMappingDataObj().getGroupVariable()
				.equals("Insert Group Variable") == true) {

			// PlotMavenMainScreen.createPlotMavenInstance().numberOfPlotNodes++;

			String profileForDisplay = "";

			for (int i = 0; i < profile.length; i++) {
				
				profileForDisplay = profile[i];
				}

			wsOutput.getPlotOutputs().getPlotMavenProfileList().add(profileForDisplay);

			PlotMavenPlots p = new PlotMavenPlots();

			// we have to pass the series name to the create data set.
			String seriesName;
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo()
					.getProcessingInputs().getMappingDataObj().getSortVariablesListVector()
					.size() == 0) {
				seriesName = "";
			} else
				seriesName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getPlotInfo()
						.getProcessingInputs().getMappingDataObj().getSortVariablesListVector()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).getPlotInfo()
								.getProcessingInputs().getMappingDataObj().getSortVariablesListVector()
								.size() - 1)
						+ " = " + profile[profile.length - 1];
			XYDataset dataset = p.createDataset(XPlot, YPlot, seriesName,
					upperDeviation, lowerDeviation, appInfo);
			JFreeChart chart = p.createChart(dataset, profileForDisplay, appInfo);

			removeUnnecessaryLegends(chart);

			XYPlot plot = chart.getXYPlot();

			for (int i = 1; i < plot.getSeriesCount(); i++) {

			}
			plot.setDomainGridlinesVisible(false);
			plot.setRangeGridlinesVisible(false);
			plot.setBackgroundPaint(Color.white);
			// plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0,
			// 5.0));
			plot.setDomainGridlinePaint(Color.white);
			plot.setRangeGridlinePaint(Color.white);

			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			renderer.setSeriesLinesVisible(0, true);
			renderer.setSeriesPaint(0, Color.black);

			plot.setRenderer(renderer);

			// change the auto tick unit selection to integer units only...
			NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			//add the chart to the output linear plots array list
			wsOutput.getPlotOutputs().getLinearPlots().add(chart);
			
			// Similarly now generate the log view
			plotMavenLogViewGeneration logp = new plotMavenLogViewGeneration();
			JFreeChart logChart = logp.plotMavenLogViewGeneration(
					XPlot, YPlot, seriesName, profileForDisplay,
					upperDeviation, lowerDeviation, appInfo);
			wsOutput.getPlotOutputs().getLogplots().add(logChart);
			

		} else {
			
			// if group variable is present and it is the 1st profile
			if (numberOfCanvases == 0) {
				
				//increase the number of plot nodes
				wsOutput.getPlotOutputs().setNumberOfPlotNodes(wsOutput.getPlotOutputs().getNumberOfPlotNodes() + 1);

				wsOutput.getPlotOutputs().setProfileForDisplay("");
				for (int i = 0; i <= profile.length - 2; i++) {// it is -2 since
					// the value of
					// the last sort
					// variable is
					// the value of
					// the group
					// variable.
					// The different values in the group variable determines the
					// number of series present in each canvas.
					// So its value has to go as a name with the corresponding
					// series in the legend.
					if (i == 0) {
						if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPlotInfo().getProcessingInputs()
								.getMappingDataObj()
								.getSortVariablesListVector().size() != 0)
							wsOutput
									.getPlotOutputs()
									.setProfileForDisplay(
											wsOutput.getPlotOutputs()
													.getProfileForDisplay()
													+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getWorkSheetObjectsAL()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																			.get(
																					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																			.getSelectedSheetIndex())
															.getPlotInfo()
															.getProcessingInputs()
															.getMappingDataObj()
															.getSortVariablesListVector()
															.get(i)
													+ " = "
													+ profile[i]);

					} else {
						if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPlotInfo().getProcessingInputs()
								.getMappingDataObj()
								.getSortVariablesListVector().size() != 0)
							wsOutput
									.getPlotOutputs()
									.setProfileForDisplay(
											wsOutput.getPlotOutputs()
													.getProfileForDisplay()
													+ ", "
													+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
															.getWorkSheetObjectsAL()
															.get(
																	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																			.get(
																					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																			.getSelectedSheetIndex())
															.getPlotInfo()
															.getProcessingInputs()
															.getMappingDataObj()
															.getSortVariablesListVector()
															.get(i)
													+ " = "
													+ profile[i]);

					}

				}
				wsOutput.getPlotOutputs().getPlotMavenProfileList().add(wsOutput.getPlotOutputs().getProfileForDisplay());
						
				DataForPlot dataForPlot = new DataForPlot();
				dataForPlot.profileCountforColor++;
				PlotMavenPlots p = new PlotMavenPlots();

				// we have to determine the series name
				String seriesName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex()).getPlotInfo().getProcessingInputs()
						.getMappingDataObj().getGroupVariable()
						+ " = " + profile[profile.length - 1];
				XYDataset dataset = p.createDataset(XPlot, YPlot, seriesName,
						upperDeviation, lowerDeviation, appInfo);
				JFreeChart chart = p.createChart(dataset, wsOutput.getPlotOutputs().getProfileForDisplay(), appInfo);

				removeUnnecessaryLegends(chart);

				XYPlot plot = chart.getXYPlot();
				plot.setBackgroundPaint(Color.white);
				// plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0,
				// 5.0));
				plot.setDomainGridlinePaint(Color.white);
				plot.setBackgroundPaint(Color.white);
				plot.setRangeGridlinePaint(Color.white);
				plot.setRangeGridlinesVisible(false);
				plot.setDomainGridlinesVisible(false);

				XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
				renderer.setSeriesLinesVisible(0, true);

				plot.setRenderer(renderer);

				// change the auto tick unit selection to integer units only...
				NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
				rangeAxis.setStandardTickUnits(NumberAxis
						.createIntegerTickUnits());
				
				//add the linear chart generated in the arraylist
				wsOutput.getPlotOutputs().getLinearPlots().add(chart);

				

				// Similarly now generate the log view chart and add it to output log array list
				plotMavenLogViewGeneration logp = new plotMavenLogViewGeneration();
				JFreeChart logChart = logp.plotMavenLogViewGeneration(
						XPlot, YPlot, seriesName, wsOutput.getPlotOutputs().getProfileForDisplay(),
						upperDeviation, lowerDeviation, appInfo);
				wsOutput.getPlotOutputs().getLogplots().add(logChart);
				

			} else {// this is not the first canvas

				// belongs to the same canvas i.e the same chart as in the last node of the arraylist
				if (sameCanvas == true) {

					
					JFreeChart currentChart = wsOutput.getPlotOutputs()
							.getLinearPlots().get(
									wsOutput.getPlotOutputs().getLinearPlots()
											.size() - 1);
					XYPlot currentPlot = currentChart.getXYPlot();
					XYSeriesCollection currentDataset = (XYSeriesCollection) currentPlot
							.getDataset();

					int numberOfSeriesPresntInCurrentDataSet = currentDataset
							.getSeriesCount();

					// we have to determine the series name
					String seriesName = null;
					if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getPlotInfo().getProcessingInputs()
							.getMappingDataObj().getGroupVariable().equals(
									"Insert Group Variable") == true) {
						seriesName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getPlotInfo()
								.getProcessingInputs()
								.getMappingDataObj()
								.getSortVariablesListVector()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getWorkSheetObjectsAL()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())
												.getPlotInfo()
												.getProcessingInputs()
												.getMappingDataObj()
												.getSortVariablesListVector()
												.size() - 1)
								+ " = " + profile[profile.length - 1];
					} else {
						seriesName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo().getProcessingInputs().getMappingDataObj().getGroupVariable()
								+ " = " + profile[profile.length - 1];
					}

					XYSeries series = new XYSeries("" + seriesName);

					for (int i = 0; i < XPlot.length; i++) {
						series.add(XPlot[i], YPlot[i]);
					}

					currentDataset.addSeries(series);

					PlotMavenPlots p = new PlotMavenPlots();
					String profileForDisplay = "";
					for (int i = 0; i <= profile.length - 2; i++) {// it is -2
						// since the
						// value of
						// the last
						// sort
						// variable
						// is the
						// value of
						// the group
						// variable.
						// The different values in the grop variable determines
						// the number of series present in each canvas.
						// So its value has to go as a name with the
						// corresponding series in the legend.
						if (i == 0) {
							if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).getPlotInfo().getProcessingInputs().getMappingDataObj().getSortVariablesListVector()
									.size() != 0)
								profileForDisplay = profileForDisplay
										+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).getPlotInfo().getProcessingInputs().getMappingDataObj().getSortVariablesListVector()
												.get(i) + " = " + profile[i];
						} else {
							if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).getPlotInfo().getProcessingInputs().getMappingDataObj().getSortVariablesListVector()
									.size() != 0)
								profileForDisplay = profileForDisplay
										+ ", "
										+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).getPlotInfo().getProcessingInputs().getMappingDataObj().getSortVariablesListVector()
												.get(i) + " = " + profile[i];
						}

					}

					JFreeChart chart = p.createChart(currentDataset,
							profileForDisplay, appInfo);// currentChart.getTitle().getID()
					XYPlot plot = chart.getXYPlot();
					plot.setDomainGridlinesVisible(false);

					plot.setRangeGridlinesVisible(false);
					plot.setBackgroundPaint(Color.white);

					// plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0,
					// 5.0, 5.0));
					plot.setDomainGridlinePaint(Color.white);
					plot.setRangeGridlinePaint(Color.white);

					XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
					renderer.setSeriesLinesVisible(0, true);
					for (int i = 0; i < plot.getSeriesCount(); i++) {
						renderer.setSeriesPaint(i, chooseNewColor(i));

					}

					plot.setRenderer(renderer);
					// plot.getRenderer().setseries

					// change the auto tick unit selection to integer units
					// only...
					NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
					rangeAxis.setStandardTickUnits(NumberAxis
							.createIntegerTickUnits());
					// OPTIONAL CUSTOMISATION COMPLETED.

					//add the updated linear chart to the same index of the array list i.e the last index
					wsOutput.getPlotOutputs().getLinearPlots().add(wsOutput.getPlotOutputs().getLinearPlots().size() - 1, chart);
					
					//get the current log chart
					JFreeChart currentLogChart = wsOutput.getPlotOutputs().getLogplots().get(wsOutput.getPlotOutputs().getLogplots().size() - 1);
					XYPlot logPlot = currentLogChart.getXYPlot();
					XYSeriesCollection currentLogDataSet = (XYSeriesCollection) logPlot
							.getDataset();

					// now we add the series by removing zero values.
					XYSeries logSeries = new XYSeries(seriesName);
					for (int i = 0; i < XPlot.length; i++) {
						if ((XPlot[i] != 0) && (YPlot[i] != 0)) {
							logSeries.add(XPlot[i], YPlot[i]);
						}
					}

					currentLogDataSet.addSeries(logSeries);
					JFreeChart logChart = plotMavenLogViewGeneration
							.GenerateChart(currentLogDataSet, profileForDisplay, appInfo);
					XYPlot logplot = chart.getXYPlot();
					logplot.setDomainGridlinesVisible(false);

					logplot.setRangeGridlinesVisible(false);
					logplot.setBackgroundPaint(Color.white);

					// plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0,
					// 5.0, 5.0));
					logplot.setDomainGridlinePaint(Color.white);
					logplot.setRangeGridlinePaint(Color.white);

					XYLineAndShapeRenderer logrenderer = new XYLineAndShapeRenderer();
					logrenderer.setSeriesLinesVisible(0, true);
					for (int i = 0; i < logplot.getSeriesCount(); i++) {
						logrenderer.setSeriesPaint(i, chooseNewColor(i));

					}

					logplot.setRenderer(logrenderer);
					// plot.getRenderer().setseries

					// change the auto tick unit selection to integer units
					// only...
					NumberAxis lograngeAxis = (NumberAxis) logplot
							.getRangeAxis();
					lograngeAxis.setStandardTickUnits(NumberAxis
							.createIntegerTickUnits());
					
					//add the same log chart to the same index i.e the last index in the array list
					wsOutput.getPlotOutputs().getLogplots().add(wsOutput.getPlotOutputs().getLogplots().size() - 1, logChart);
					
					

				} else { //if group variable is present and is a different canvas 
					
					
					
					String profileForDisplay = "";
					for (int i = 0; i <= profile.length - 2; i++) {
						if (i == 0) {
							profileForDisplay = profileForDisplay
									+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).getPlotInfo().getProcessingInputs()
											.getMappingDataObj().getSortVariablesListVector()
											.get(i) + " = " + profile[i];
						} else {
							profileForDisplay = profileForDisplay
									+ ", "
									+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).getPlotInfo().getProcessingInputs()
											.getMappingDataObj().getSortVariablesListVector()
											.get(i) + " = " + profile[i];
						}
					}
					wsOutput.getPlotOutputs().getPlotMavenProfileList().add(profileForDisplay);

					PlotMavenPlots p = new PlotMavenPlots();

					// we have to pass the series name to the create data set.
					String seriesName = null;
					if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getPlotInfo().getProcessingInputs()
									.getMappingDataObj().getGroupVariable().equals("Insert Group Variable") == true) {
						seriesName =appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo().getProcessingInputs()
								.getMappingDataObj().getSortVariablesListVector()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).getPlotInfo().getProcessingInputs()
												.getMappingDataObj().getSortVariablesListVector()
										.size() - 1)
								+ " = " + profile[profile.length - 1];
					} else {
						seriesName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getPlotInfo().getProcessingInputs()
								.getMappingDataObj().getGroupVariable()
								+ " = " + profile[profile.length - 1];
					}
					XYDataset dataset = p.createDataset(XPlot, YPlot,
							seriesName, upperDeviation, lowerDeviation, appInfo);
					JFreeChart chart = p
							.createChart(dataset, profileForDisplay, appInfo);

					// remove the unnecessary legends from the chart
					removeUnnecessaryLegends(chart);

					XYPlot plot = chart.getXYPlot();

					plot.setDomainGridlinesVisible(false);
					plot.setRangeGridlinesVisible(false);
					plot.setBackgroundPaint(Color.white);

					plot.setDomainGridlinePaint(Color.white);
					plot.setRangeGridlinePaint(Color.white);

					XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
					renderer.setSeriesLinesVisible(0, true);

					plot.setRenderer(renderer);

					// change the auto tick unit selection to integer units
					// only...
					NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
					rangeAxis.setStandardTickUnits(NumberAxis
							.createIntegerTickUnits());
					
					//add the generated chart to the array list
					wsOutput.getPlotOutputs().getLinearPlots().add(chart);

					// Similarly now generate the log view
					plotMavenLogViewGeneration logp = new plotMavenLogViewGeneration();
					
					JFreeChart logChart = logp.plotMavenLogViewGeneration(
							XPlot, YPlot, seriesName, profileForDisplay,
							upperDeviation, lowerDeviation, appInfo);
					
					// add this log generated plot to the array list
					wsOutput.getPlotOutputs().getLogplots().add(logChart);
					

				}
			}
		}

	}

	private void removeUnnecessaryLegends(JFreeChart chart) {
		// TODO Auto-generated method stub
		LegendItemCollection legendItemsOld = chart.getPlot().getLegendItems();

		for (int i = 0; i < legendItemsOld.getItemCount(); i++) {
			if (i == 0) {

			} else {
				chart.getXYPlot().getRenderer().setSeriesVisibleInLegend(false);
			}
		}

	}

	protected static Paint chooseNewColor(int i) {
		Color c;

		switch (i) {
		case 0:
			c = Color.blue;
			break;
		case 1:
			c = Color.red;
			break;
		case 2:
			c = Color.BLACK;
			break;
		case 3:
			c = Color.CYAN;
			break;
		case 4:
			c = Color.DARK_GRAY;
			break;
		case 5:
			c = Color.orange;
			break;
		case 6:
			c = Color.yellow;
			break;
		case 7:
			c = Color.lightGray;
			break;
		case 8:
			c = Color.green;
			break;
		case 9:
			c = Color.magenta;
			break;
		default:
			c = new Color(i * 2, 242 / i, 4);
		}

		return c;
	}

}
