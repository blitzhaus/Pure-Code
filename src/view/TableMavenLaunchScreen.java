package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;

import javax.swing.JInternalFrame;

import javax.swing.JPanel;

import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Common.MenuToolBarEnableDisableFeatures;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class TableMavenLaunchScreen extends JInternalFrame {

	private JPanel contentPane;
	public JPanel topPanel;
	private static TableMavenInfo m_tblWizardInputs;

	int nodeType = 0;

	JPanel[] templateGraphPanels;

	private String[][] inputMatrix;
	private int rowCount = 0;
	private int colCount = 0;
	private String[] stringArray;
	private String[] descrArray;

	public String[][] getInputMatrix() {
		return inputMatrix;
	}

	public void setInputMatrix(String[][] inputMatrix) {
		this.inputMatrix = inputMatrix;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getColCount() {
		return colCount;
	}

	public void setColCount(int colCount) {
		this.colCount = colCount;
	}

	public String[] getStringArray() {
		return stringArray;
	}

	public void setStringArray(String[] stringArray) {
		this.stringArray = stringArray;
	}

	public String[] getDescrArray() {
		return descrArray;
	}

	public void setDescrArray(String[] descrArray) {
		this.descrArray = descrArray;
	}

	void tableMavenLaunchScreen() {

		tableMavenConstructor();
		TableMavenCreateUI.createTableMavenUIInst().tableMavenCreateUI();
		initializeVariables();
		addGraphPanelsToLayeredPane();
		nodeCreationInPE();
		MenuToolBarEnableDisableFeatures.createMenuToolEnabDisabInst().disableEnableMenuToolBarFeatures();
	}
	
	DefaultMutableTreeNode projectNode;

	private void nodeCreationInPE() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DefaultMutableTreeNode analysisNode = null;
		Enumeration en = DDViewLayer.createPEInstance().workSpace
				.breadthFirstEnumeration();
		boolean hasNCAnalysisNode = false;
		DefaultMutableTreeNode node = null;

		projectNode = getProjNode();
		for(int i=0;i<projectNode.getChildCount();i++){
			if(((DefaultMutableTreeNode)projectNode.getChildAt(i)).getUserObject().toString().equals("TableMaven")){
				hasNCAnalysisNode = true;
				break;
			}
		}
		

		if (hasNCAnalysisNode == true) {
			analysisNode = getAnalysisNode();
			
			DefaultMutableTreeNode scaNode = new DefaultMutableTreeNode("TM-"
					+ appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex()).getWorkBookName()+"-" + appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL()
							.get(
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
											.getSelectedSheetIndex()).getSheetName());
			analysisNode.add(scaNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.reload();
		} else if (hasNCAnalysisNode == false) {

			analysisNode = new DefaultMutableTreeNode(
					"TableMaven");
			projectNode.add(analysisNode);
			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();
			model.insertNodeInto(analysisNode,
					projectNode, projectNode.getChildCount() - 1);
			
			DefaultMutableTreeNode ncaNode = new DefaultMutableTreeNode("TM-"
					+ (appInfo
							.getProjectInfoAL()
							.get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(
											appInfo.getSelectedProjectIndex())
											.getSelectedWorkBookIndex()).getWorkBookName()+"-" + appInfo
									.getProjectInfoAL()
									.get(appInfo.getSelectedProjectIndex())
									.getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(
													appInfo.getSelectedProjectIndex())
													.getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
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
													.getSelectedSheetIndex()).getSheetName()));
			analysisNode.add(ncaNode);

			model.reload();
		}
	}
	
	private DefaultMutableTreeNode getProjNode() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Iterator<Entry<String, DefaultMutableTreeNode>> it = appInfo.getpENodes().entrySet().iterator();
		
		while(it.hasNext()){
			
			Map.Entry pairs = (Map.Entry)it.next();
			if(pairs.getKey().equals(appInfo.getSelectedprojectPath())){
				return (DefaultMutableTreeNode) pairs.getValue(); 
			}
		}
		
		return null;
	}

	
	
	private DefaultMutableTreeNode getAnalysisNode() {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		String projName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getProjectName();
		String key = "Work Space,"+projName+","+"TableMaven";
		
		DefaultMutableTreeNode analysisNode = appInfo.getpENodes().get(key);
		
		return analysisNode;
		
	}

	private void initializeVariables() {

		DDViewLayer.createViewLayerInstance().isFromTableMaven = true;
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		DDViewLayer.createViewLayerInstance().isPlotMavenExecution = false;
		DDViewLayer.createViewLayerInstance().isFromCA = false;
		DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage = false;
		DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage = false;
		DDViewLayer.createViewLayerInstance().isFromBQL = false;
		DDViewLayer.createViewLayerInstance().isTableTrans = false;
		
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).setAnalysisType(
						"tablemaven");
		
		
	}

	private void tableMavenConstructor() {
		DDViewLayer.createFeaturesPanelInstance().tableLabel.setVisible(false);

	}

	ImageIcon createImageIcon(int tempType) {
		ImageIcon imgIcon = null;
		switch (tempType) {
		case 1:
			imgIcon = new ImageIcon("template1.jpeg");
			break;
		case 2:
			imgIcon = new ImageIcon("template2.jpeg");
			break;
		case 3:
			imgIcon = new ImageIcon("template3.jpeg");
			break;
		case 4:
			imgIcon = new ImageIcon("template6.jpeg");
			break;
		/*
		case 5:
			imgIcon = new ImageIcon("template5.jpeg");
			break;
		case 6:
			imgIcon = new ImageIcon("template6.jpeg");
			break;
		case 7:
			imgIcon = new ImageIcon("template7.jpeg");
			break;
		case 8:
			imgIcon = new ImageIcon("template8.jpeg");
			break;
		*/
		}

		return imgIcon;
	}

	void addGraphPanelsToLayeredPane() {
		templateGraphPanels = new JPanel[4];

		for (int i = 0; i < 4; i++) {
			final ImageIcon imageIcon = createImageIcon(i + 1);

			JTextArea textArea = new JTextArea() {
				Image image = imageIcon.getImage();
				Image grayImage = GrayFilter.createDisabledImage(image);
				{
					setOpaque(false);
				} // instance initializer

				public void paintComponent(Graphics g) {
					g.drawImage(grayImage, 0, 0, 275, 275, Color.BLUE, this);
					super.paintComponent(g);
				}
			};

			templateGraphPanels[i] = new JPanel();
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension scrDim = toolkit.getScreenSize();

			int width = (int) scrDim.getWidth();
			int height = (int) scrDim.getHeight();

			int leftNavWidth = (int) (scrDim.getWidth() * 0.3);
			int leftNavHeight = (int) (scrDim.getHeight() * 0.5);

			int leftnavX = 0;
			int leftnavY = 0;

			int mapPanelWidth = width - leftNavWidth;
			int mapPanelHeight = leftNavHeight;

			int mapPanelX = leftNavWidth + 1;
			int mapPanelY = 0;

			// templateGraphPanels[i].setBounds(0, 0, mapPanelWidth,
			// mapPanelHeight);

			/*
			 * layeredPaneOfGraphPanels.add(templateGraphPanels[i], new
			 * Integer(i));
			 */
			templateGraphPanels[i].repaint();
			System.out.println("I am in of for loop"+i);
			
			System.out.println("I am at the end of for loop");
		}
		
		System.out.println("I am out of for loop");

		/* layeredPaneOfGraphPanels.repaint(); */

		// getContentPane().add(layeredPaneOfGraphPanels);

	}

	public static TableMavenInfo createTableMavenIPInstance()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (m_tblWizardInputs == null) {
			m_tblWizardInputs = new TableMavenInfo();
		}
		return m_tblWizardInputs;
	}

	public void enableGraphPanels(int nodeType, int templateType) {
		TMSetupDispComp.createTmSetupDispCompInst().tmMappingPanel
				.setVisible(false);
		for (int i = 0; i < templateGraphPanels.length; i++) {
			templateGraphPanels[i].setVisible(false);
		}

		if (nodeType == 1) {
			TMSetupDispComp.createTmSetupDispCompInst().tmMappingPanel
					.setVisible(true);
		} else {
			templateGraphPanels[templateType - 1].setVisible(true);
			templateGraphPanels[templateType - 1].repaint();
		}
	}

	public void updateView(int nodeType, int templateType) {
		enableGraphPanels(nodeType, templateType);
	}

	public static TableMavenLaunchScreen TABLE_LAUNCHER = null;

	public static TableMavenLaunchScreen createTableMavenLauncherInst() {
		if (TABLE_LAUNCHER == null) {
			TABLE_LAUNCHER = new TableMavenLaunchScreen("just object creation");
		}
		return TABLE_LAUNCHER;

	}

	public TableMavenLaunchScreen(String dummy) {

	}

}
