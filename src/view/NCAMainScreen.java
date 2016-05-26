package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
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
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.naming.NoInitialContextException;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.InternalFrameUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.resources.JFreeChartResources;
import org.jfree.data.statistics.Regression;
import org.jfree.date.SpreadsheetDate;

import view.ApplicationInfo;
import view.DisplayContents;
import view.WorkSheetsInfo;

import Common.DataReader2;
import Common.Iconizing;
import Common.JinternalFrameFunctions;
import Common.WorkBookManipulation;


import jxl.Cell;
import jxl.BooleanCell;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.biff.XCTRecord;
import jxl.read.biff.BiffException;

import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import jxl.write.biff.RowsExceededException;
import jxl.*;

public class NCAMainScreen extends JInternalFrame
		{
	
	
	public static NCAMainScreen NCA_MAIN_SCREEN = null;
	public static NCAMainScreen createNcaMainScreenInstance() throws RowsExceededException, WriteException, BiffException, IOException{
		if(NCA_MAIN_SCREEN == null){
			NCA_MAIN_SCREEN = new NCAMainScreen("just object creation");
		}
		return NCA_MAIN_SCREEN;
	}


	JInternalFrame ncaMainInternalFrame;
	JTextField doseTextField;
	JTextField timeOfDoseText;
	JTextField tauvalueText;
	JDesktopPane setupOptionsDesktopPane;
	private JComboBox normalizationUnit;
	JButton fitFitSlopesButton;
	JRadioButton unitradioButton;
	JTextField unitTextField;
	JInternalFrame modelSettingsInternalFrame;
	JInternalFrame doseOptionsInternalFrame;
	JInternalFrame fitSlopesInternalFrame;
	JDesktopPane ncaMainScreenDesktopPane;
	JInternalFrame ncaMainScreen;
	//protected static UnitBuilder doseUnitBuilderFrame;
	private boolean isaLambdaZPlot;
	private boolean isDisplayPartialAreaScrollPane;
	JLabel infusionTimeLable;
	JTextField infusionTimeTextField;
	JCheckBox pageBreaksCheckBox;
	private JCheckBox intermediateOutputCheckBox;
	JCheckBox excludeProfilesCheckBox;
	private JCheckBox disableCurveStrippingCheckBox;
	// protected static JTextField numberOfPartialAreaTextField;
	

	AbstractTableModel tt;

	
	//dummy flags for table entry restriction
	boolean tblPartialArea;
	boolean tblDosing;
	boolean tblParNames;


	JScrollPane partialAreaScrollPane;
	JCheckBox useInternalWorkSheetcheck;
	// protected static JRadioButton partialAreaRadioButton;
	
	JInternalFrame preferredUnitSelectionInternalFrame;
	JButton preferredUnitBuilderButton;
	JButton resetUnitBuilderButton;
	JTable tableForPreferredUnit;
	int triedToChange;

	JTableHeader tableForDosingHeader;
	
	int numberOfTimeUnitScreenCalled;
	double[][] doingInputArray;
	
	boolean isFromPartialAreaScreen = false;
	

	
	
	int availableoutputsTreeSelectedRow;
	
	JButton doseUnitButton;

	JLabel startTimeForLambdaZLabel;
	JLabel endTimeForLambdaZLabel;
	JButton computeLambdaZButton;
	JDesktopPane lambdaZMethodSelectionDesktopPane;
	int numberOfPartialArea;
	JPanel modelSettingsPanel;
	
	JPanel partialAreasoptionsPanel;
	

	// DefaultTableModel model;
	JInternalFrame dummyDosingInternalFrame;
	int numberOfTimesDosingScreenChanged;
	

	
	String[] previousSortVariablesForPA;
	String[] previousSortVariablesForLZ;
	
	int previousSizeForPartialArea;

	
	

	int numberOfTimeDisableCurveStrippingCalled = 0;
	
	int previousSizeForLambdaZ;
	boolean ifMappingIsChangedInLambdaZ = false;
	
	

	// protected static JComboBox viewTypeCombo;
	
	
	
	TitledBorder titledBorder;
	Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	Font componentLablesFont = new Font("Arial", Font.BOLD, 11);
	JPanel startTimePanel;
	JPanel endTimePanel;
	
	

	
	
	JTextField adjustedRSquareTextField;
	
	
	
	
	

	int previousRootOfAdministration = 0;
	int numberOfTimeInsideTheLoop = 0;
	int columncount = 0;
	
	
	JCheckBox selectionCheckBoc;
	JCheckBox exclusionCheckBox;
	
	ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
	
	JInternalFrame displayDescriptiveStatsSheet;
	



	public NCAMainScreen(String dummy){
		ncaMainInternalFrame = new JInternalFrame("",false,false,false,false);
	}
	
	public void isncaMainScreenCreation(int selectedSheetIndex) throws RowsExceededException,
			WriteException, IOException, BiffException {
		ncaMainInternalFrame.setLocation(0, 0);
		ncaMainInternalFrame.setSize(
				DDViewLayer.createMTInstance().mainTabbedFrame.getWidth(),
				DDViewLayer.createMTInstance().mainTabbedFrame.getHeight() / 2);
		NCAMainScreenGUICreation.createNcaMainScreenGui(selectedSheetIndex);
		NcaMappingDispGui.createMappingInstance().MappingInternalFrame
				.moveToFront();
		NCAMainScreenConstructor.createNcaMainScrConstr()
				.initiallizeVariables();
		JinternalFrameFunctions
				.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame);

		ncaMainInternalFrame.setVisible(true);
		ncaMainInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane
				.add(ncaMainInternalFrame);
		ncaMainInternalFrame.moveToFront();
	}

}

class JTableButtonRenderer implements TableCellRenderer {
	private TableCellRenderer __defaultRenderer;

	public JTableButtonRenderer(TableCellRenderer renderer) {
		__defaultRenderer = renderer;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof Component)
			return (Component) value;
		return __defaultRenderer.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
	}
}

class SelectionListener implements ListSelectionListener {
	JTable table;

	SelectionListener(JTable table) {

		System.out.println("The selected row in the partial aras table is "
				+ table.getSelectedRow());
	}

	public void valueChanged(ListSelectionEvent e) {
	}
}
