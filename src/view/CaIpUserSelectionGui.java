package view;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import Common.CursorToolkitTwo;
import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class CaIpUserSelectionGui implements EventCodes{

	public static CaIpUserSelectionGui CAIPUSERSELEC = null;
	public static CaIpUserSelectionGui createCAIpUserSelecInst(){
		if(CAIPUSERSELEC == null){
			CAIPUSERSELEC = new CaIpUserSelectionGui();
		}
		return CAIPUSERSELEC;
	}
	
	public CaIpUserSelectionGui(){
		setNewGUI();
	}

	private JDialog caUserIpDialog;
	private JInternalFrame caUserIpIF;
	private void setNewGUI() {
		CursorToolkitTwo.startWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
		caUserIpDialog = new JDialog(DDViewLayer.createViewLayerInstance(), "Initial Parameters");
		caUserIpDialog.setLocationRelativeTo(null);
		caUserIpDialog.setVisible(true);

		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Rectangle gebounds = ge.getMaximumWindowBounds();
		caUserIpDialog.setSize(gebounds.width/2, gebounds.height/2);
		caUserIpDialog.setContentPane(new JDesktopPane());
		createCaUserIPIF();
		createButtonsIF();
	}

	JInternalFrame buttonsIF;
	private void createButtonsIF() {
		// TODO Auto-generated method stub
		buttonsIF = new JInternalFrame("", false,false,false,false);
		buttonsIF.setLocation(caUserIpIF.getX(), caUserIpIF.getY()+caUserIpIF.getHeight());
		buttonsIF.setSize(caUserIpIF.getWidth(), 100);
		buttonsIF.setVisible(true);
		caUserIpDialog.getContentPane().add(buttonsIF);
		createButtons();
	}

	JButton compute;
	private void createButtons() {
		// TODO Auto-generated method stub
		compute = new JButton("Compute");
		compute.setVisible(true);
		compute.setActionCommand(CAUSERIPCOMPUTE);
		compute.addActionListener(DDViewLayer.createViewLayerInstance());
		buttonsIF.getContentPane().add(compute);
		
	}

	private void createCaUserIPIF() {
		caUserIpIF = new JInternalFrame("", false,false,false,false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(caUserIpIF);
		caUserIpIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		caUserIpIF.setLocation(0, 0);
		caUserIpIF.setSize(caUserIpDialog.getWidth(), caUserIpDialog.getHeight()-100);
		caUserIpIF.setVisible(true);
		caUserIpDialog.getContentPane().add(caUserIpIF);
		createSetupTab();
		createResultsTab();
		CursorToolkitTwo.stopWaitCursor(DDViewLayer.createViewLayerInstance().getRootPane());
		
	}

	private void createResultsTab() {
		// TODO Auto-generated method stub
		
	}
	
	JDesktopPane setupDesktopPane;
	JDesktopPane resultsDesktopPane;

	private void createSetupTab() {
		// TODO Auto-generated method stub
		JTabbedPane dialogTabs = new JTabbedPane();
		setupDesktopPane = new JDesktopPane();
		resultsDesktopPane = new JDesktopPane();
		dialogTabs.addTab("Setup", setupDesktopPane);
		dialogTabs.addTab("Result", resultsDesktopPane);
		dialogTabs.setVisible(true);
		caUserIpIF.getContentPane().add(dialogTabs);
		createSetupInternalFrames();
	}

	private void createSetupInternalFrames() {
		setAvailCompCreation();
		setDispCompCreation();
	}

	JInternalFrame setupDispCompIF;
	private void setDispCompCreation() {
		setupDispCompIF = new JInternalFrame("", false,false,false,false);
		setupDispCompIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(setupDispCompIF);
		setupDispCompIF.setLocation(0+setupAvailCompIF.getWidth(), 0);
		setupDispCompIF.setSize(caUserIpIF.getWidth() - setupAvailCompIF.getWidth(), caUserIpIF.getHeight());
		setupDispCompIF.setVisible(true);
		setupDispCompIF.setContentPane(new JDesktopPane());
		setupDesktopPane.add(setupDispCompIF);
		createInternalFrames();
	}
	
	public JInternalFrame matrixIF;
	public JInternalFrame routeIF;
	public JInternalFrame compartmentsIF;
	public JTable matrixTable;
	public JTable routeTable;
	public JTable compTable;
	private void createInternalFrames() {
		// TODO Auto-generated method stub
		matrixIF = new JInternalFrame("matrix IF", false,false,false,false);
		matrixIF.setLocation(0,0);
		matrixIF.setSize(setupDispCompIF.getWidth(),setupDispCompIF.getHeight());
		matrixIF.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(matrixIF);
		matrixIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		setupDispCompIF.getContentPane().add(matrixIF);
		String[] header = {"Function", "Plasma", "Urine"};
		matrixTable = createTable(header);
		JScrollPane matrixTableSP = new JScrollPane(matrixTable);
		matrixIF.getContentPane().add(matrixTableSP);
		
		routeIF = new JInternalFrame("Route IF", false,false,false,false);
		routeIF.setLocation(0,0);
		routeIF.setSize(setupDispCompIF.getWidth(),setupDispCompIF.getHeight());
		routeIF.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(routeIF);
		routeIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		setupDispCompIF.getContentPane().add(routeIF);
		String[] Routeheader = {"Function", "IV Bolus", "Extravascular", "IV Infusion","IV Bolus+Infusion", "Extravascular + Infusion"
				,"IV Bolus + Extravascular"};
		routeTable = createTable(Routeheader);
		JScrollPane routeSP = new JScrollPane(routeTable);
		routeIF.getContentPane().add(routeSP);
		
		
		
		compartmentsIF = new JInternalFrame("Compartments IF", false,false,false,false);
		compartmentsIF.setLocation(0,0);
		compartmentsIF.setSize(setupDispCompIF.getWidth(),setupDispCompIF.getHeight());
		compartmentsIF.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(compartmentsIF);
		compartmentsIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		//compartmentsIF.setcom
		setupDispCompIF.getContentPane().add(compartmentsIF);
		String[] compartHeader = {"Function", "Compartment 1", "Compartment 2", "Compartment 3"};
		compTable = createTable(compartHeader);
		JScrollPane compSp = new JScrollPane(compTable);
		compartmentsIF.getContentPane().add(compSp);
		
		
		
	}

	private JTable createTable(String[] header) {
		TestButtonGroup butGroup = new TestButtonGroup();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		HashMap<String, HashSet<String>> hm = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getSelectedSheetIndex()).getAsciiInfo().getProcessingInputs().getAsciiInitEstimates().getFuncArray();
		
		int columns = header.length;
		JTable matrixTable = butGroup.createTable(hm, header, columns);
		
		
		
		return matrixTable;
	}

	JTree availableComponentsTree;
	private void createTree() {
		DefaultMutableTreeNode selection = new DefaultMutableTreeNode("Selection");
		DefaultMutableTreeNode matrix = new DefaultMutableTreeNode("Matrix");
		selection.add(matrix);
		DefaultMutableTreeNode route = new DefaultMutableTreeNode("Administration Route");
		selection.add(route);
		DefaultMutableTreeNode compartment = new DefaultMutableTreeNode("Comparment");
		selection.add(compartment);
		
		availableComponentsTree = new JTree(selection);
		availableComponentsTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		// availableComponentsTree.addMouseListener(ViewLayer.createViewLayerInstance());
		availableComponentsTree.addTreeSelectionListener(DDViewLayer
				.createViewLayerInstance());
		JScrollPane availableComponentsScrollPane = new JScrollPane(
				availableComponentsTree);
		availableComponentsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		availableComponentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setupAvailCompIF.getContentPane().add(availableComponentsScrollPane);
		
		
	}

	JInternalFrame setupAvailCompIF;
	private void setAvailCompCreation() {
		// TODO Auto-generated method stub
		setupAvailCompIF = new JInternalFrame("", false,false,false,false);
		setupAvailCompIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(setupAvailCompIF);
		setupAvailCompIF.setLocation(0, 0);
		setupAvailCompIF.setSize(caUserIpIF.getWidth()/4, caUserIpIF.getHeight());
		setupAvailCompIF.setVisible(true);
		createTree();
		setupDesktopPane.add(setupAvailCompIF);
	}
}
