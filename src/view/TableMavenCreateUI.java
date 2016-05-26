package view;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;

import Common.Iconizing;
import Common.JinternalFrameFunctions;
import Model.Template12Results;

public class TableMavenCreateUI {

	public static TableMavenCreateUI TABLE_MAVEN_UI = null;

	public static TableMavenCreateUI createTableMavenUIInst() {
		if (TABLE_MAVEN_UI == null) {
			TABLE_MAVEN_UI = new TableMavenCreateUI("just object creation");
		}
		return TABLE_MAVEN_UI;
	}

	public static TMMappingPanel m_categoryPanel = null;

	public static TMMappingPanel createTMMappingPanel() {
		if (m_categoryPanel == null) {
			m_categoryPanel = new TMMappingPanel();
		}
		return m_categoryPanel;
	}

	public static TMOptionsPanel m_optionsPanel = null;

	public static TMOptionsPanel createTMOptionsPanel() {
		if (m_optionsPanel == null) {
			m_optionsPanel = new TMOptionsPanel();
		}
		return m_optionsPanel;
	}

	public static FontStylePanel m_fontStylePanel = null;

	public static FontStylePanel createFontStylePanel() {
		if (m_fontStylePanel == null) {
			m_fontStylePanel = new FontStylePanel();
		}
		return m_fontStylePanel;
	}

	public static TMStatsPanel m_tmStatsPanel = null;

	public static TMStatsPanel createTMStatsPanel() {
		if (m_tmStatsPanel == null) {
			m_tmStatsPanel = new TMStatsPanel();
		}
		return m_tmStatsPanel;
	}

	public TableMavenCreateUI(String dummy) {

	}

	JTabbedPane tmTabbedPane;
	JDesktopPane tmSetupDP;
	JTable tmResultsTable;
	JTextArea tmVerificationTA;
	JInternalFrame tableMavenMainInternalFrame;
	JInternalFrame setupMainIF;
	JDesktopPane setupMainIFDP;

	TMSetupAvailComp tmSetupAvailComp;
	TMSetupDispComp tmSetupDispComp;
	TMOptions tmOptions;
	TMResultAvailComp tmResAvailComp;
	TMResultDispComp tmResDispComp;
	
	private boolean restoreMode = false;
	
	public boolean isRestoreMode() {
		return restoreMode;
	}

	public void setRestoreMode(boolean restoreMode) {
		this.restoreMode = restoreMode;
	}

	public void tableMavenCreateUI() {

		// Main internal frame which sits in the main desktop pane
		// to hold all the table maven related internal frames
		tableMavenMainInternalFrame = new JInternalFrame("Table Maven", false,
				false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(tableMavenMainInternalFrame);
		tableMavenMainInternalFrame.setLocation(0, 0);

/*		if (ViewLayer.createMTInstance().mainTabbedFrame.getWidth() == ViewLayer
				.createMTInstance().mainTabbedFrameOriginalWidth) {
			Iconizing.createIconizingInstance()
					.iconizeAvailableAnalysisInternalFrame();
		}
		if (ViewLayer.createMTInstance().mainTabbedFrame.getHeight() == ViewLayer
				.createMTInstance().mainTabbedFrameoriginalHeight) {
			Iconizing.createIconizingInstance().iconizeLogInternalFrame();
		}*/

		tableMavenMainInternalFrame
				.setSize(DDViewLayer.createMTInstance().mainTabbedFrame
						.getWidth(),
						(int) (DDViewLayer.createMTInstance().mainTabbedFrame
								.getHeight() * 0.5));
		tableMavenMainInternalFrame.setVisible(true);
		tableMavenMainInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane
				.add(tableMavenMainInternalFrame);

		// tabbed frame creation
		tmTabbedPane = new JTabbedPane();
		tmSetupDP = new JDesktopPane();

		tmVerificationTA = new JTextArea();
		tmResultsTable = new JTable();

		DefaultTableModel dm = (DefaultTableModel) tmResultsTable.getModel();

		dm
				.setDataVector(new Object[][] { { "Group 1", "A" },
						{ "Group 1", "B" }, { "Group 1", "C" },
						{ "Group 2", "a" }, { "Group 2", "b" } }, new Object[] {
						"String", "JRadioButton" });

		tmTabbedPane.add("Setup", tmSetupDP);
		tmTabbedPane.add("Results", null);
		//tmTabbedPane.add("Verification", tmVerificationTA);
		tmTabbedPane.setLocation(0, 0);
		tmTabbedPane.setSize(tableMavenMainInternalFrame.getWidth(),
				tableMavenMainInternalFrame.getHeight());
		tableMavenMainInternalFrame.getContentPane().add(tmTabbedPane);
		tableMavenMainInternalFrame.moveToFront();

		// setup available components cration
		tmSetupAvailComp = TMSetupAvailComp.createTmSetupAvailCompInst();
		setupMainIF = new JInternalFrame("setupMainIF", false, false, false,
				false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(setupMainIF);
		setupMainIF.setLocation(0, 0);
		setupMainIF.setVisible(true);
		setupMainIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		setupMainIF.setSize(tableMavenMainInternalFrame.getWidth(),
				tableMavenMainInternalFrame.getHeight());
		setupMainIFDP = new JDesktopPane();
		setupMainIF.setContentPane(setupMainIFDP);

		tmSetupDP.add(setupMainIF);
		tmSetupAvailComp.createSetupAvailCompUI();

		// setup display components creation
		tmSetupDispComp = TMSetupDispComp.createTmSetupDispCompInst();
		
		if (DDViewLayer.createViewLayerInstance().isTMRestore == true)
		{
			tmSetupDispComp.setRestoreMode(true);
		}
		
		tmSetupDispComp.createSetupDispCompUI();

		// tm options creation
		tmOptions = TMOptions.createTMptionsInst();
		
		if (DDViewLayer.createViewLayerInstance().isTMRestore == true)
		{
			tmOptions.setRestoreMode(true);
		}
		tmOptions.createOptionsUI();

		// results available component creation
		tmResAvailComp = TMResultAvailComp.createResAvailCompInst();
		tmResAvailComp.resultsAvailCompUI();

		// results display component creation
		tmResDispComp = TMResultDispComp.createResDispInst();
		tmResDispComp.resultsDispCompUI();
	}

	public String[] convDblDimStrArrToSinDimArr(String[][] ipArray) {
		String[] opArray = new String[ipArray[0].length];

		for (int i = 0; i < opArray.length; i++) {
			opArray[i] = ipArray[0][i];
		}

		return opArray;
	}

	void rebuildResultsTab() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		int templateType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo()
				.getTemplateType();

		Hashtable<String, Hashtable<String, Double>> htStatMetricListing = null;

		if (templateType == 0) {
			T1ResultsLoader t1ResLoader = new T1ResultsLoader();
			t1ResLoader.template1Handling(htStatMetricListing);

			updateResTableWithData(t1ResLoader.getData(), t1ResLoader
					.getHeaders());
			updateTabPaneWithResultsTable();
		} else if (templateType == 1) {
			T2ResultsLoader t1ResLoader = new T2ResultsLoader();
			t1ResLoader.template2Handling(htStatMetricListing);

			updateResTableWithData(t1ResLoader.getData(), t1ResLoader
					.getHeaders());
			updateTabPaneWithResultsTable();
		} else if (templateType == 2) {
			T3ResultsLoader t1ResLoader = new T3ResultsLoader();
			t1ResLoader.template3Handling(htStatMetricListing);

			updateResTableWithData(t1ResLoader.getData(), t1ResLoader
					.getHeaders());
			updateTabPaneWithResultsTable();
		} else if (templateType == 3) {
			T6ResultsLoader t1ResLoader = new T6ResultsLoader();
			t1ResLoader.template6Handling(htStatMetricListing);

			updateResTableWithData(t1ResLoader.getData(), t1ResLoader
					.getHeaders());
			updateTabPaneWithResultsTable();
		}

	}

	void updateTabPaneWithResultsTable() {
		int resIndex = tmTabbedPane.indexOfTab("Results");
		tmTabbedPane.remove(resIndex);
		tmTabbedPane.insertTab("Results", null, new JScrollPane(tmResultsTable,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS),
				"Tabulated Results of tableMaven", resIndex);
	}

	void updateResTableWithData(String[][] newData, String[] headers) {
		DefaultTableModel dtModel = (DefaultTableModel) tmResultsTable
				.getModel();

		int rCount = dtModel.getRowCount();
		int cCount = dtModel.getColumnCount();

		for (int i = rCount; i > 0; i--) {
			dtModel.removeRow(i - 1);
		}

		for (int i = cCount; i < headers.length; i++) {
			dtModel.addColumn(headers[i]);
		}
		System.out.println(tmResultsTable.getRowCount());
		for (int i = 0; i < newData.length; i++) {
			int rowCount = tmResultsTable.getRowCount();
			dtModel.insertRow(rowCount, newData[i]);
		}

		for (int j = 0; j < headers.length; j++) {
			TableColumnModel tcm = tmResultsTable.getColumnModel();
			TableColumn tc = tcm.getColumn(j);
			tc.setHeaderValue(headers[j]);
		}
	}
}
