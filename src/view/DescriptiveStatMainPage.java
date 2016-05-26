package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

public class DescriptiveStatMainPage extends JInternalFrame {

	JInternalFrame desStatsMainIF;
	boolean isFromPreferredUnitInternalFrame = false;
	boolean isAvailableToY;
	JTextField xVariableTextField;
	JTextField yVariableTextField;
	JTextField doseTextField;
	JInternalFrame slopeSelectorOptionsInternalFrame;
	JTextField timeOfDoseText;
	JTextField tauvalueText;
	JDesktopPane plotTabDesktopPane;
	JDesktopPane setupOptionsDesktopPane;
	JComboBox modelOptions;
	JComboBox weightingOptions;
	JComboBox calculationMethodOptions;
	JComboBox rootOfAdministration;
	JComboBox normalizationUnit;
	JButton fitFitSlopesButton;
	JRadioButton unitradioButton;
	JRadioButton sparseradioButton;
	JTextField unitTextField;
	JTextField textTextField;
	JDesktopPane ncaMainScreenDesktopPane;
	JInternalFrame ncaMainScreen;
	int ifSparseSampling;
	JLabel endTimeVariableLable;
	JLabel volumeLable;
	JLabel infusionTimeLable;
	JLabel subjectLable;
	JTextField subjectTextField;
	JTextField endTimeVariableTextField;
	JButton moveToEndTimeButton;
	JButton moveToVolumeButton;
	JTextField volumeVariableTextField;
	JTextField infusionTimeTextField;

	int availableoutputsTreeSelectedRow;
	int selectedImage;
	JTableHeader finalParametersHorizontalDisplayTableHeader;
	TableColumnModel finalParametersHorizontalDisplayTableModel;
	JTextField weightingValueTextField;
	boolean ifMappingScreenIsChanged = false;
	String[] previousSortVariables = new String[20];
	int previousSize;
	private ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

	public static DescriptiveStatMainPage DES_STAT_MAIN_PAGE = null;

	public static DescriptiveStatMainPage createDescStatMainPageInst() {
		if (DES_STAT_MAIN_PAGE == null) {
			DES_STAT_MAIN_PAGE = new DescriptiveStatMainPage(
					"just object creation");
		}
		return DES_STAT_MAIN_PAGE;
	}

	public DescriptiveStatMainPage(String dummy) {

	}

	void DescriptiveStatMainPage(int selectedSheetIndex) {

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.setSortVariablesListVector(new Vector<String>());
		desStatsConstructor();

		DesStatsUI.createDesStatsUI().createUI(selectedSheetIndex);
		DDViewLayer.createFeaturesPanelInstance().plotsLable.setVisible(true);

	}

	private void desStatsConstructor() {

		DDViewLayer.createMTInstance().mainTabbedFrame.setSize(DDViewLayer
				.createMTInstance().mainTabbedFrameOriginalWidth, DDViewLayer
				.createMTInstance().mainTabbedFrameoriginalHeight);
		desStatsMainIF = new JInternalFrame("Descriptive Stat Main Screen",
				false, false, false, false);

		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(desStatsMainIF);
		desStatsMainIF.setLocation(0, 0);

		desStatsMainIF.setSize(DDViewLayer.createMTInstance().mainTabbedFrame
				.getWidth(), (int)(DDViewLayer.createMTInstance().mainTabbedFrame
				.getHeight() / 2.1));

		desStatsMainIF.setVisible(true);
		desStatsMainIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane.add(desStatsMainIF);
		desStatsMainIF.moveToFront();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).setAnalysisType(
						"desstats");

	}

	private void mappingChangeDetection() {
		int a = 1;

		if (previousSize != appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size())
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getMappingDataObj()
					.setIfMappingScreenIsChanged(true);
		else {
			for (int i = 0; i < previousSize; i++) {
				for (int j = 0; j < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getSortVariablesListVector()
						.size(); j++) {
					String String1 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getDesStatsInfo().getProcessingInputs()
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
							.getDesStatsInfo().getProcessingInputs()
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
							.getDesStatsInfo().getProcessingInputs()
							.getMappingDataObj().setIfMappingScreenIsChanged(
									false);
				}

			}
		}

		// previousSortVariables=new String[sortVariablesListVector.size()];
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			String String2 = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i);
			previousSortVariables[i] = String2;
		}

		previousSize = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();
		// previousSortVariablesListVector=sortVariablesListVector;

	}

	public void restoringPreviousState(ApplicationInfo readPerst) {
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.setSelectedSheetIndex(
						readPerst.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex());

		restoreMappingPreviousState(readPerst);
		
		// after mapping the mapping is changed

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.setIfMappingScreenIsChanged(true);

		TreePath path = DesStatSetupAvailComp.createDesStatAvailComp().availableComponentsTree
				.getPathForRow(1);
		DesStatSetupAvailComp.createDesStatAvailComp().availableComponentsTree
				.setSelectionPath(path);

	}

	private void restoreMappingPreviousState(ApplicationInfo readPerst) {

		// first we remove the available columns populated from the column names
		// of imported data....this is in normal
		// proceeding to NCA but not here ...... here the case is restoring the
		// values
		// removing the values from the list displayed
		int size = DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
				.size();
		for (int i = DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
				.size() - 1; i >= 0; i--) {
			DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
					.remove(i);
		}

		// setting the available columns
		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().size(); i++) {
			DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
					.add(
							i,
							readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getDesStatsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().get(i));

		}

		// since sort variables are not populated while creating a new
		// ncaMainScreen
		// we need not remove anything, so
		// setting the sort variables
		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			DesStatSetupDespComp.createDesStatSetupDispInst().sortVariableListModel
					.add(
							i,
							readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getDesStatsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().get(i));

		}

		DesStatSetupDespComp.createDesStatSetupDispInst().weightVariableTextField
				.setText(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getWeightColumnName());

		// setting the subject column name
		// restore this value only when if it was a sparse analysis
		for (int i = 0; i < readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getCarryAlongVariablesListVector().size(); i++) {
			DesStatSetupDespComp.createDesStatSetupDispInst().carryAlongModel
					.add(
							i,
							readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															readPerst.getProjectInfoAL().get(readPerst.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getDesStatsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getCarryAlongVariablesListVector().get(i));

		}

	}

}
