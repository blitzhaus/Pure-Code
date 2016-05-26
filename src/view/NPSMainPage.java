package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.InternalFrameUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.jfree.date.SpreadsheetDate;

import view.ApplicationInfo;
import view.DisplayContents;
import Common.JinternalFrameFunctions;
import jxl.Cell;

public class NPSMainPage extends JInternalFrame {
	JInternalFrame npsMainInternalFrame;
	JTextField xVariableTextField;
	JTextField yVariableTextField;
	JTextField doseTextField;
	JInternalFrame slopeSelectorOptionsInternalFrame;
	JTextField timeOfDoseText;
	JTextField tauvalueText;
	JDesktopPane plotTabDesktopPane;
	JDesktopPane setupOptionsDesktopPane;
	JComboBox modelOptions;
	JComboBox calculationMethodOptions;
	JComboBox rootOfAdministration;
	JDesktopPane ncaMainScreenDesktopPane;
	JInternalFrame ncaMainScreen;
	UnitBuilder doseUnitBuilderFrame;
	AbstractTableModel t;
	AbstractTableModel tt;
	AbstractTableModel tForDosing;
	int numberOfRow;
	int numberOfColumn;

	JTable finalParametersHorizontalDisplayTable;
	TableColumnModel finalParametersVerticalDisplayTableModel;
	int NcaOutputNumberOfplotnodes;
	private int availableoutputsTreeSelectedRow;
	JTableHeader finalParametersHorizontalDisplayTableHeader;
	TableColumnModel finalParametersHorizontalDisplayTableModel;
	JInternalFrame plotOptionsInternalFrame;
	boolean ifMappingScreenIsChanged = false;
	String[] previousSortVariables = new String[20];
	int previousSize;
	JComboBox viewTypeCombo;
	DefaultListModel carryAlongModel;
	JList carryAlongList;
	int concentrationColumnCorrespondinOriginalIndex;
	int timeColumnCorrespondinOriginalIndex;
	ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

	public static NPSMainPage NPS_MAIN_PAGE = null;

	public static NPSMainPage createNPSMainPageInst() {
		if (NPS_MAIN_PAGE == null) {
			NPS_MAIN_PAGE = new NPSMainPage("just object creation");
		}
		return NPS_MAIN_PAGE;
	}

	public NPSMainPage(String dummy) {

	}

	NPSTabs npsTabs;
	NPSSetupAvailComp npsSetupAvailComp;
	NPSSetupDispComp npsSetupDispComp;
	NPSOpt npsOpt;
	NPSResultsAvailComp npsResAvailComp;
	NPSResultsDispComp npsResDispComp;

	void nPSMainPage(int selectedSheetIndex) {

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.setAnalysisType("nps");
		DDViewLayer.createMTInstance().mainTabbedFrame.setSize(DDViewLayer
				.createMTInstance().mainTabbedFrameOriginalWidth, DDViewLayer
				.createMTInstance().mainTabbedFrameoriginalHeight);
		npsMainInternalFrame = new JInternalFrame(
				"NonParametric Superposition Main Screen", false, false, false,
				false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(npsMainInternalFrame);
		npsMainInternalFrame.setLocation(0, 0);
		npsMainInternalFrame.setSize(
				DDViewLayer.createMTInstance().mainTabbedFrame.getWidth(),
				DDViewLayer.createMTInstance().mainTabbedFrame.getHeight() / 2);

		npsMainInternalFrame.setVisible(true);
		npsMainInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane
				.add(npsMainInternalFrame);
		npsMainInternalFrame.moveToFront();

		// tabs creation
		npsTabs = NPSTabs.createNPSTabsInst();
		npsTabs.cteateTabbedPanes();

		// setup available components creation
		npsSetupAvailComp = NPSSetupAvailComp.createNPSSetupAvailCompInst();
		npsSetupAvailComp
				.createSetupAvailableComponentsinternalFrame(selectedSheetIndex);

		// setup display components creation
		npsSetupDispComp = NPSSetupDispComp.createNpsSetDispInst();
		npsSetupDispComp.createSetupDisplayComponentsInternalFrame();

		// NPS options creation
		npsOpt = NPSOpt.createNPSOptInst();
		npsOpt.createOptionsInternalFrame();
		npsSetupDispComp.MappingInternalFrame.moveToFront();
		npsOpt.optionsInternalFrame.moveToFront();

		// NPS results available component creation
		npsResAvailComp = NPSResultsAvailComp.createNPSResAvailInst();
		npsResAvailComp.createResultsTabAvailableOutputsInternalFrame();

		npsResDispComp = NPSResultsDispComp.createNPSResDispCompInst();
		npsResDispComp.createDisplayResultInternalFrame();

	}

	private void mappingChangeDetection() {
		int a = 1;

		System.out.println(" In mapping change detection");
		System.out.println(" size before mapping"
				+ previousSize
				+ "size"
				+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNpsInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size());

		if (previousSize != appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size())
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getMappingDataObj()
					.setIfMappingScreenIsChanged(true);
		else {
			for (int i = 0; i < previousSize; i++) {
				for (int j = 0; j < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNpsInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size(); j++) {
					String String1 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNpsInfo().getProcessingInputs()
							.getMappingDataObj().getSortVariablesListVector()
							.get(i);
					String String2 = previousSortVariables[i];
					if (String1.equals(String2) == true)
						a = a * 0;
					else
						a = a * 1;

				}

				if (a == 1) {
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNpsInfo().getProcessingInputs()
							.getMappingDataObj().setIfMappingScreenIsChanged(
									true);
					break;

				} else {
					a = 1;
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNpsInfo().getProcessingInputs()
							.getMappingDataObj().setIfMappingScreenIsChanged(
									false);
				}

			}
		}

		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			String String2 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i);
			previousSortVariables[i] = String2;
		}

		previousSize = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();

	}

	void changeOptionsFrame() {

		NPSOpt.createNPSOptInst().viewLable.setEnabled(true);
		NPSOpt.createNPSOptInst().viewsCombo.setEnabled(true);

	}

}
