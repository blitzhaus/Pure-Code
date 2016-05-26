package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.NumericShaper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.JFreeChartEntity;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.resources.JFreeChartResources;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.HorizontalAlignment;

import view.ApplicationInfo;
import view.DisplayContents;
import view.WorkSheetsInfo;

import Common.EventCodes;
import Common.JinternalFrameFunctions;
import Common.WorkBookManipulation;

public class PlotMavenMainScreen extends JInternalFrame implements EventCodes {

	int numberOfTimesPlacedinGroupVariable = 0;
	boolean isItPlotExecution;
	int numberOfPlotNodes;
	ArrayList<String> profilesList;;
	JInternalFrame currentFrame;
	JInternalFrame currentLogFrame;
	int logFrameCount;
	int frameCount;
	private Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	private Font componentLablesFont = new Font("Arial", Font.BOLD, 11);
	private TitledBorder titledBorder;
	DefaultMutableTreeNode plotMavenPlotsAvailable;

	public static PlotMavenMainScreen PLOT_MAVEN_MAIN_SCR = null;

	public static PlotMavenMainScreen createPlotMavenInstance()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (PLOT_MAVEN_MAIN_SCR == null) {
			PLOT_MAVEN_MAIN_SCR = new PlotMavenMainScreen(
					"just object creation");
		}
		return PLOT_MAVEN_MAIN_SCR;
	}

	public void PlotMavenMainScreen(int selectedSheetIndex)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		plotMavenConstructor();
		PlotMavenCreateUI.createPlotMavenUIInst().plotMavenCreateUI(
				selectedSheetIndex);
		initializeVariables();
	}

	public PlotMavenMainScreen(String dummy) {
		// TODO Auto-generated constructor stub
	}

	private void initializeVariables() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		profilesList = new ArrayList<String>();
		PMSetupDispComp.createPMSetupDisCompInst().plotMavenSortVariablesListVector = new Vector<String>();
		PMResultsDispComp.createPMDispInst().internalFramesArrayList = new ArrayList<JInternalFrame>();
		PMResultsDispComp.createPMDispInst().logInternalFramesArrayList = new ArrayList<JInternalFrame>();
		numberOfPlotNodes = 0;

		DDViewLayer.createMTInstance().mainTabbedFrame
				.setTitle("Plot Maven Main Screen");
		JinternalFrameFunctions
				.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						PlotMavenCreateUI.createPlotMavenUIInst().PlotMavenMainInternalFrame);
		DDViewLayer.createViewLayerInstance().isFromPlotMavenMappingScreen = true;
		isItPlotExecution = true;
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).setAnalysisType(
						"plotmaven");

	}

	private void plotMavenConstructor() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		// setting the temporary column properties array list to the plot maven
		// related code.
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getPlotInfo()
				.setColumnPropertiesArrayList(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getColumnPropertiesArrayList());
	}

	public static void main(String args[]) {

	}

	public void createUI() throws RowsExceededException, WriteException,
			BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		PlotMavenCreateUI.createPlotMavenUIInst().plotMavenCreateUI(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex());
	}

	void createTheResultTabComponents() throws RowsExceededException,
			WriteException, BiffException, IOException {

		TreePath plotsPath = PMResultsAvailComp.createPmSetupAvailCompInst().resultsAvailableTree
				.getNextMatch("plots", 0, Position.Bias.Forward);
		if (plotsPath == null) {
			plotMavenPlotsAvailable = new DefaultMutableTreeNode("plots");
			PMResultsAvailComp.createPmSetupAvailCompInst().OutputMainNode
					.add(plotMavenPlotsAvailable);
			((DefaultTreeModel) PMResultsAvailComp.createPmSetupAvailCompInst().resultsAvailableTree
					.getModel()).reload();
		}
		profilesList = new ArrayList<String>();
		numberOfPlotNodes = 0;
		PMResultsDispComp.createPMDispInst().internalFramesArrayList = new ArrayList<JInternalFrame>();
		PMResultsDispComp.createPMDispInst().logInternalFramesArrayList = new ArrayList<JInternalFrame>();

	}

	void populatingResults() throws RowsExceededException, WriteException,
			BiffException, IOException {
		System.out.println("The number of plot nodes = " + numberOfPlotNodes);
		System.out.println("The profiles are");

		for (int i = 0; i < numberOfPlotNodes; i++) {

			DefaultMutableTreeNode plot = new DefaultMutableTreeNode(
					profilesList.get(i));
			System.out.println(profilesList.get(i));
			plotMavenPlotsAvailable.add(plot);
		}

		((DefaultTreeModel) PMResultsAvailComp.createPmSetupAvailCompInst().resultsAvailableTree
				.getModel()).reload();

		
		TreePath plotsPath = PMResultsAvailComp.createPmSetupAvailCompInst().resultsAvailableTree
				.getNextMatch("plots", 0, Position.Bias.Forward);
		MutableTreeNode mNode = (MutableTreeNode) plotsPath
				.getLastPathComponent();
		

		PMResultsDispComp.createPMDispInst().internalFramesArrayList.get(0)
				.moveToFront();
		PMResultsDispComp.createPMDispInst().logInternalFramesArrayList.get(0)
				.moveToFront();
	}

	void removePreviousResults() throws RowsExceededException, WriteException,
			BiffException, IOException {
		DefaultTreeModel model = (DefaultTreeModel) PMResultsAvailComp
				.createPmSetupAvailCompInst().resultsAvailableTree.getModel();
		
		TreePath plotsPath = PMResultsAvailComp.createPmSetupAvailCompInst().resultsAvailableTree
				.getNextMatch("plots", 0, Position.Bias.Forward);
		if (plotsPath == null) {
			
		} else {
			MutableTreeNode mNode = (MutableTreeNode) plotsPath
					.getLastPathComponent();
			
			
			if (mNode.isLeaf() == false) {

				plotMavenPlotsAvailable.removeAllChildren();
				System.out.println("removing");
				mNode.removeFromParent();

				
				plotMavenPlotsAvailable = new DefaultMutableTreeNode("plots");
				PMResultsAvailComp.createPmSetupAvailCompInst().OutputMainNode
						.add(plotMavenPlotsAvailable);
				((DefaultTreeModel) PMResultsAvailComp
						.createPmSetupAvailCompInst().resultsAvailableTree
						.getModel()).reload();
				for (int i = 0; i < PMResultsDispComp.createPMDispInst().internalFramesArrayList
						.size(); i++) {
					
					PMResultsDispComp.createPMDispInst().internalFramesArrayList
							.get(i).dispose();
				}

				for (int i = 0; i < PMResultsDispComp.createPMDispInst().logInternalFramesArrayList
						.size(); i++) {
					PMResultsDispComp.createPMDispInst().logInternalFramesArrayList
							.get(i).dispose();
				}
			}

			((DefaultTreeModel) PMResultsAvailComp.createPmSetupAvailCompInst().resultsAvailableTree
					.getModel()).reload();
			
		}

	}

}
