package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JInternalFrame.JDesktopIcon;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class TableTransformations implements EventCodes {

	public static TableTransformations TAB_TRANS = null;

	public static TableTransformations createTableTrasGUIInst() {
		if (TAB_TRANS == null) {
			TAB_TRANS = new TableTransformations("dummy string");
		}
		return TAB_TRANS;
	}

	public TableTransformations(String dummy) {

	}

	JInternalFrame ttMainIF;
	JTabbedPane ttTabbedPane;
	JDesktopPane ttSetupDP;
	JDesktopPane ttResultsDP;
	JInternalFrame setupAvailIF;
	JInternalFrame mappingIF;
	DefaultListModel sortVariableListModel;
	JList sortVariablesList;
	JTextField xVariableTextField;
	JTextField yVariableTextField;
	JButton SortAndAvailableButton;
	Icon toLeft = new ImageIcon("left.png");
	Icon toRight = new ImageIcon("right.png");
	JButton xAndAvailableButton;
	JButton YAndAvailableButton;
	DefaultListModel availableVariablesListmodel;
	JList availableColumnsList;
	boolean isAvailableToSort;
	public boolean isSortToAvailable;
	public boolean isAvailableToX;
	public boolean isXToAvailable;
	public boolean isAvailableToY;
	public boolean isYToAvailable;
	public String[] previousSortVariables;
	public String previousXVariable;
	public String previousYColumnName;
	JInternalFrame ttOptionsIF;
	JRadioButton funcRB;
	JRadioButton baseLineRB;
	JRadioButton arithmeticRB;
	JComboBox baseLineCombo;
	JComboBox funcCombo;
	JComboBox arithmeticFuncListCombo;
	JInternalFrame variousFuncIF;
	JDesktopPane ttoptionsDP;
	JTextField nValueTF;
	JPanel nValPanel;
	JInternalFrame resultsAvailIF;
	JInternalFrame resultsDispIF;
	JTree resultsAvailTree;
	private JRadioButton lowestPerProfRB;
	private JRadioButton customRB;
	JTextField customTimePointTF;
	JTree availCompTree;
	DefaultMutableTreeNode projectNode;
	
	void createTTGUI() {

		setColProperties();
		initializeVariables();

		// setting the analysis type
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).setAnalysisType("tt");

		ttMainIF = new JInternalFrame("", false, false, false, false);
		ttMainIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(ttMainIF);
		ttMainIF.setLocation(0, 0);
		ttMainIF.setSize(DDViewLayer.createMTInstance().mainTabbedFrame
				.getWidth(), DDViewLayer.createMTInstance().mainTabbedFrame
				.getHeight() / 2);
		ttMainIF.setVisible(true);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane.add(ttMainIF);
		ttMainIF.moveToFront();

		// hide the table and plot maven feature buttons
		DDViewLayer.createFeaturesPanelInstance().plotsLable.setVisible(false);
		DDViewLayer.createFeaturesPanelInstance().tableLabel.setVisible(false);
		DDViewLayer.createFeaturesPanelInstance().avaiAnalButton
				.setVisible(false);

		// enable the execution button
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);

		createTabs();
		createSetupAvailIF();
		createSetupDispIF();
		createResultsAvail();
		createResultsDispIF();
		createOptionsIF();
		storeDataInAppInfo();
		//createPENode();

	}


	private void setColProperties() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getTtInfo()
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

	private void createResultsDispIF() {
		resultsDispIF = new JInternalFrame("", false, false, false, false);
		resultsDispIF.setLocation(resultsAvailIF.getX()
				+ resultsAvailIF.getWidth(), resultsAvailIF.getY());
		resultsDispIF.setSize(mappingIF.getWidth(), mappingIF.getHeight());
		resultsDispIF.moveToFront();
		resultsDispIF.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(resultsDispIF);
		resultsDispIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		ttResultsDP.add(resultsDispIF);

	}

	private void createResultsAvail() {
		resultsAvailIF = new JInternalFrame("", false, false, false, false);
		resultsAvailIF.setLocation(0, 0);
		resultsAvailIF.setSize(setupAvailIF.getWidth(), setupAvailIF
				.getHeight());
		resultsAvailIF.moveToFront();
		resultsAvailIF.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(resultsAvailIF);
		resultsAvailIF.setBorder(DDViewLayer.createViewLayerInstance().b);

		DefaultMutableTreeNode results = new DefaultMutableTreeNode("Results");
		resultsAvailTree = new JTree(results);
		
		resultsAvailTree.addTreeSelectionListener(DDViewLayer.createViewLayerInstance());
		resultsAvailTree.addMouseListener(DDViewLayer.createViewLayerInstance());

		DefaultMutableTreeNode transformetdSheetNode = new DefaultMutableTreeNode(
				"Transformed sheet");
		results.add(transformetdSheetNode);
		JScrollPane resAvailTreeSp = new JScrollPane(resultsAvailTree);
		resAvailTreeSp
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		resAvailTreeSp
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		resultsAvailIF.getContentPane().add(resAvailTreeSp);
		ttResultsDP.add(resultsAvailIF);
		for(int i=0;i<resultsAvailTree.getRowCount();i++){
			resultsAvailTree.expandRow(i);
		}

	}

	private void initializeVariables() {
		// ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.setFocusable(false);
		DDViewLayer.createFeaturesPanelInstance().executeLable.setVisible(true);
		DDViewLayer.createFeaturesPanelInstance().executeLable.setEnabled(true);
		DDViewLayer.createViewLayerInstance().isFronClickOnProjectExplorer = false;

		DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage = false;
		DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage = false;
		DDViewLayer.createViewLayerInstance().isFromPlotMavenMappingScreen = false;
		DDViewLayer.createViewLayerInstance().isFromNca = false;
		DDViewLayer.createViewLayerInstance().isPlotMavenExecution = false;
		DDViewLayer.createViewLayerInstance().isNCAExecution = false;
		DDViewLayer.createViewLayerInstance().isToNCAFromPlotMaven = false;
		DDViewLayer.createViewLayerInstance().isFromCA = false;
		DDViewLayer.createViewLayerInstance().isCAExecution = false;
		DDViewLayer.createViewLayerInstance().isFromBQL = false;
		DDViewLayer.createViewLayerInstance().isTableTrans = true;

	}

	private void storeDataInAppInfo() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String[][] data = new String[appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex()).length][appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())[0].length];

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getElementFromDS(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())[i][j];
			}
		}

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().setTtData(data);

	}

	private void createTabs() {

		ttTabbedPane = new JTabbedPane();
		ttSetupDP = new JDesktopPane();
		ttTabbedPane.addTab("Setup", ttSetupDP);

		ttResultsDP = new JDesktopPane();
		ttTabbedPane.addTab("Result", ttResultsDP);

		ttMainIF.getContentPane().add(ttTabbedPane);
	}

	private void createOptionsIF() {

		ttOptionsIF = new JInternalFrame("", false, false, false, false);
		ttOptionsIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(ttOptionsIF);
		ttOptionsIF.setLocation(0, ttMainIF.getY() + ttMainIF.getHeight());
		ttOptionsIF.setSize(ttMainIF.getWidth(), ttMainIF.getHeight() + 20);
		ttOptionsIF.setVisible(true);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane.add(ttOptionsIF);
		ttOptionsIF.moveToFront();

		ttoptionsDP = new JDesktopPane();
		ttOptionsIF.setContentPane(ttoptionsDP);

		createVarFuncIF();

		createnewColNameIF();
		ttoptionsDP.setBackground(variousFuncIF.getBackground());
		setupAvailIF.setBackground(variousFuncIF.getBackground());

		nValPanel = new JPanel();
		nValPanel.setBorder(BorderFactory.createTitledBorder("n Value"));
		GridBagConstraints nValpanelCon = new GridBagConstraints();
		nValpanelCon.gridx = 1;
		nValpanelCon.gridy = 1;
		nValpanelCon.anchor = GridBagConstraints.LINE_START;
		nValPanel.setEnabled(false);

		variousFuncIF.getContentPane().add(nValPanel, nValpanelCon);

		nValueTF = new JTextField();
		nValueTF.setColumns(5);
		nValPanel.add(nValueTF);
		nValueTF.setEnabled(false);
		ButtonGroup bg = new ButtonGroup();
		bg.add(arithmeticRB);
		bg.add(baseLineRB);
		bg.add(funcRB);
	}

	private void createnewColNameIF() {
		JInternalFrame newColNameIF = new JInternalFrame("", false, false,
				false, false);
		newColNameIF.setLocation(variousFuncIF.getX()
				+ variousFuncIF.getWidth(), variousFuncIF.getY());
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(newColNameIF);
		newColNameIF.setSize(variousFuncIF.getWidth(), variousFuncIF
				.getHeight());
		newColNameIF.setVisible(true);
		newColNameIF.moveToFront();
		newColNameIF.setBorder(null);
		newColNameIF.setLayout(new GridBagLayout());
		ttoptionsDP.add(newColNameIF);

		JPanel newColPanel = new JPanel();
		// newColPanel.setPreferredSize(new Dimension(newColNameIF.getWidth()/2,
		// (newColNameIF.getHeight()/2.5));
		/*newColPanel.setSize((int) (newColNameIF.getWidth() / 1.5),
				(int) (newColNameIF.getHeight() / 3));*/
		newColPanel.setBorder(BorderFactory
				.createTitledBorder("New Column Name"));
		GridBagConstraints newColPanelCon = new GridBagConstraints();
		newColPanelCon.gridx = 0;
		newColPanelCon.gridy = 0;
		// newColPanelCon.weightx = 0.5;
		newColPanelCon.anchor = GridBagConstraints.PAGE_START;

		JTextField newColTF = new JTextField();
		newColTF.setColumns(10);
		newColPanel.add(newColTF);
		newColTF.setEnabled(false);
		newColNameIF.getContentPane().add(newColPanel, newColPanelCon);
		
		lowestPerProfRB = new JRadioButton("Lowest Per Profile");
		lowestPerProfRB.setEnabled(false);
		lowestPerProfRB.setSelected(true);
		lowestPerProfRB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				customTimePointTF.setEnabled(false);
				ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().setTtIsLowestInProfile(true);
				
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().setTtisCustom(false);
				
			}
		});
		GridBagConstraints lowestPerProfRBCon = new  GridBagConstraints();
		lowestPerProfRBCon.gridx = 0;
		lowestPerProfRBCon.gridy = 1;
		
		
		newColNameIF.getContentPane().add(lowestPerProfRB,lowestPerProfRBCon);
		
		JPanel customPanel = new JPanel();
		GridBagConstraints customPanelCon = new GridBagConstraints();
		customPanelCon.gridx = 0;
		customPanelCon.gridy = 2;
		customPanelCon.anchor =  GridBagConstraints.LINE_START;
		
		
		customRB = new JRadioButton("Custom");
		customRB.setEnabled(false);
		customRB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				customTimePointTF.setEnabled(true);
				ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().setTtIsLowestInProfile(false);
				
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTtInfo()
				.getProcessingInputs().setTtisCustom(true);
				
				
			}
		});
		GridBagConstraints customRBCon = new GridBagConstraints();
		/*customRBCon.gridx = 0;
		customRBCon.gridy = 2;
		*/
		customPanel.add(customRB);
		
		customTimePointTF = new JTextField();
		customTimePointTF.setEnabled(false);
		customTimePointTF.setColumns(5);
		
		/*customTimePointTFCon.gridx = 1;
		customTimePointTFCon.gridy = 2;
		customTimePointTFCon.anchor = GridBagConstraints.LINE_START;*/
		customPanel.add(customTimePointTF);
		
		newColNameIF.getContentPane().add(customPanel, customPanelCon);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(lowestPerProfRB);
		bg.add(customRB);

	}

	private void createVarFuncIF() {
		variousFuncIF = new JInternalFrame("", false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(variousFuncIF);
		variousFuncIF.setLocation(0, 0);
		variousFuncIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		variousFuncIF.setSize((int) (ttOptionsIF.getWidth() / 2.5),
				(int) (ttOptionsIF.getHeight() / 2.5));
		variousFuncIF.setVisible(true);
		variousFuncIF.setLayout(new GridBagLayout());

		ttoptionsDP.add(variousFuncIF);

		createArithmeticListPanel();
		createBaselinePanel();
		createFuncPanel();
	}

	private void createFuncPanel() {

		JPanel funcPanel = new JPanel();
		funcPanel.setBorder(BorderFactory.createTitledBorder("Functions"));

		funcPanel.setLayout(new GridBagLayout());

		funcRB = new JRadioButton("Functions");
		funcRB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				baseLineCombo.setEnabled(false);
				arithmeticFuncListCombo.setEnabled(false);
				funcCombo.setEnabled(true);
				nValueTF.setEnabled(false);
				nValPanel.setEnabled(false);
				sortVariablesList.setEnabled(false);
				SortAndAvailableButton.setEnabled(false);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getTtInfo()
						.getProcessingInputs().setTtTypeOfFunc(0);
				
				lowestPerProfRB.setEnabled(false);
				customRB.setEnabled(false);
				customTimePointTF.setEnabled(false);;
			}
		});
		GridBagConstraints funcRBCon = new GridBagConstraints();
		funcRBCon.gridx = 0;
		funcRBCon.gridy = 0;

		funcPanel.add(funcRB, funcRBCon);

		funcCombo = new JComboBox();
		funcCombo.setEnabled(false);
		funcCombo.setPreferredSize(new Dimension(200, 25));

		funcCombo.addItem("1/X");
		funcCombo.addItem("Absolute value(X)");
		funcCombo.addItem("e^X");
		funcCombo.addItem("LN(X)");
		funcCombo.addItem("Log10(X)");
		funcCombo.addItem("Square root(X)");

		funcCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getTtInfo()
						.getProcessingInputs().setTtSelectedFuncIndex(
								funcCombo.getSelectedIndex());

			}
		});

		GridBagConstraints funcComboCon = new GridBagConstraints();
		funcComboCon.gridx = 1;
		funcComboCon.gridy = 0;
		funcPanel.add(funcCombo, funcComboCon);

		GridBagConstraints funcBagConstraints = new GridBagConstraints();
		funcBagConstraints.gridx = 0;
		funcBagConstraints.gridy = 0;
		// funcBagConstraints.weightx = 0.1;
		funcBagConstraints.weighty = 0.5;
		funcBagConstraints.anchor = GridBagConstraints.LINE_START;
		variousFuncIF.getContentPane().add(funcPanel, funcBagConstraints);

	}

	private void createBaselinePanel() {
		JPanel baseLinepanel = new JPanel();
		baseLinepanel.setBorder(BorderFactory.createTitledBorder("Base Line"));

		baseLinepanel.setLayout(new GridBagLayout());

		baseLineRB = new JRadioButton("Base Line");
		baseLineRB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				arithmeticFuncListCombo.setEnabled(false);
				funcCombo.setEnabled(false);
				baseLineCombo.setEnabled(true);
				nValueTF.setEnabled(false);
				nValPanel.setEnabled(false);
				sortVariablesList.setEnabled(true);
				SortAndAvailableButton.setEnabled(true);

				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getTtInfo()
						.getProcessingInputs().setTtTypeOfFunc(2);
				
				lowestPerProfRB.setEnabled(true);
				customRB.setEnabled(true);
			}
		});
		GridBagConstraints baseLineRBCon = new GridBagConstraints();
		baseLineRBCon.gridx = 0;
		baseLineRBCon.gridy = 0;

		baseLinepanel.add(baseLineRB, baseLineRBCon);

		baseLineCombo = new JComboBox();
		baseLineCombo.setEnabled(false);
		baseLineCombo.setPreferredSize(new Dimension(200, 25));

		baseLineCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getTtInfo()
						.getProcessingInputs().setTtSelectedBaseFuncIndex(
								baseLineCombo.getSelectedIndex());

			}
		});

		baseLineCombo.addItem("Change from base line");
		baseLineCombo.addItem("% Change from base line");
		baseLineCombo.addItem("Ratio from base line");

		GridBagConstraints baseLineComboCon = new GridBagConstraints();
		baseLineComboCon.gridx = 1;
		baseLineComboCon.gridy = 0;
		baseLinepanel.add(baseLineCombo, baseLineComboCon);

		GridBagConstraints baseLinePanelCon = new GridBagConstraints();
		baseLinePanelCon.gridx = 0;
		baseLinePanelCon.gridy = 2;
		// baseLinePanelCon.weightx = 0.5;
		baseLinePanelCon.weighty = 0.5;
		baseLinePanelCon.anchor = GridBagConstraints.LINE_START;
		
		
		
		variousFuncIF.getContentPane().add(baseLinepanel, baseLinePanelCon);

	}

	private void createArithmeticListPanel() {
		JPanel arithmeticPanel = new JPanel();
		arithmeticPanel.setBorder(BorderFactory
				.createTitledBorder("Arithmetic"));

		arithmeticPanel.setLayout(new GridBagLayout());

		arithmeticRB = new JRadioButton("Arithmetic");
		arithmeticRB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				baseLineCombo.setEnabled(false);
				funcCombo.setEnabled(false);
				arithmeticFuncListCombo.setEnabled(true);
				arithmeticFuncListCombo.setSelectedIndex(0);
				sortVariablesList.setEnabled(false);
				SortAndAvailableButton.setEnabled(false);

				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getTtInfo()
						.getProcessingInputs().setTtTypeOfFunc(1);
				
				lowestPerProfRB.setEnabled(false);
				customRB.setEnabled(false);
				customTimePointTF.setEnabled(false);
			}
		});
		arithmeticRB.setSelected(true);
		GridBagConstraints arithmeticRBCon = new GridBagConstraints();
		arithmeticRBCon.gridx = 0;
		arithmeticRBCon.gridy = 0;
		arithmeticPanel.add(arithmeticRB, arithmeticRBCon);

		arithmeticFuncListCombo = new JComboBox();
		arithmeticFuncListCombo.addItem("X+Y");
		arithmeticFuncListCombo.addItem("X-Y");
		arithmeticFuncListCombo.addItem("X*Y");
		arithmeticFuncListCombo.addItem("X/Y");
		arithmeticFuncListCombo.addItem("(X-Y)/Y");
		arithmeticFuncListCombo.addItem("(X+Y)/Y*100");
		arithmeticFuncListCombo.addItem("X+n");
		arithmeticFuncListCombo.addItem("X-n");
		arithmeticFuncListCombo.addItem("X*n");
		arithmeticFuncListCombo.addItem("X/n");
		arithmeticFuncListCombo.addItem("X^n");
		arithmeticFuncListCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if ((arithmeticFuncListCombo.getSelectedIndex() >= 6)
						&& (arithmeticFuncListCombo.getSelectedIndex() <= 10)) {
					nValueTF.setEnabled(true);
					nValPanel.setEnabled(true);
				} else {
					nValueTF.setEnabled(false);
					nValPanel.setEnabled(false);
				}

				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getTtInfo()
						.getProcessingInputs().setTtSlectedArithmeticFuncIndex(
								arithmeticFuncListCombo.getSelectedIndex());

			}
		});

		arithmeticFuncListCombo.setPreferredSize(new Dimension(200, 25));

		GridBagConstraints arithListCon = new GridBagConstraints();
		arithListCon.gridx = 1;
		arithListCon.gridy = 0;
		arithmeticPanel.add(arithmeticFuncListCombo, arithListCon);

		GridBagConstraints arithmeticPanelCon = new GridBagConstraints();
		arithmeticPanelCon.gridx = 0;
		arithmeticPanelCon.gridy = 1;
		// arithmeticPanelCon.weightx = 0.5;
		arithmeticPanelCon.weighty = 0.5;
		arithmeticPanelCon.anchor = GridBagConstraints.LINE_START;

		variousFuncIF.add(arithmeticPanel, arithmeticPanelCon);

	}

	private void createSetupDispIF() {

		createMappIngScreen();

	}

	private void createMappIngScreen() {
		mappingIF = new JInternalFrame("", false, false, false, false);
		mappingIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(mappingIF);
		mappingIF.setLocation(setupAvailIF.getX() + setupAvailIF.getWidth(),
				setupAvailIF.getY());
		mappingIF.setSize((ttMainIF.getWidth() - setupAvailIF.getWidth()),
				setupAvailIF.getHeight());
		mappingIF.setVisible(true);
		ttSetupDP.add(mappingIF);
		mappingIF.moveToFront();
		createMappingGUI();

	}

	private void createMappingGUI() {
		JDesktopPane mappinginternalFrameDesktopPane = new JDesktopPane();
		mappingIF.setContentPane(mappinginternalFrameDesktopPane);
		mappinginternalFrameDesktopPane.setBackground(Color.white);
		GridBagLayout gridBagLayout = new GridBagLayout();

		mappingIF.setBorder(DDViewLayer.createViewLayerInstance().b);

		JInternalFrame leftInternalFrame = new JInternalFrame("left", false,
				false, false, false);
		leftInternalFrame.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(leftInternalFrame);
		leftInternalFrame.setLocation(0, 0);
		leftInternalFrame.setSize(mappingIF.getWidth() / 5, mappingIF
				.getHeight());
		leftInternalFrame.setVisible(true);
		leftInternalFrame.setLayout(gridBagLayout);
		leftInternalFrame.moveToFront();
		leftInternalFrame.setBorder(null);

		JLabel sortListLable = new JLabel("Sort Variables");
		GridBagConstraints sortVariableLableCon = new GridBagConstraints();
		sortVariableLableCon.gridx = 0;
		sortVariableLableCon.gridy = 0;
		sortVariableLableCon.weightx = 0.5;

		sortVariableLableCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(sortListLable,
				sortVariableLableCon);

		sortVariableListModel = new DefaultListModel();
		sortVariablesList = new JList(sortVariableListModel);
		// sortVariablesList.addMouseListener(ViewLayer.createViewLayerInstance());
		sortVariablesList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		sortVariablesList.setVisibleRowCount(3);
		sortVariablesList.setVisible(true);
		sortVariablesList.setEnabled(false);
		JScrollPane sortVarialbeListScrollPane = new JScrollPane(
				sortVariablesList);
		sortVarialbeListScrollPane.setPreferredSize(new Dimension(100, 150));
		GridBagConstraints sortVariableLIstScrollPaneCon = new GridBagConstraints();
		sortVariableLIstScrollPaneCon.gridx = 0;
		sortVariableLIstScrollPaneCon.gridy = 1;
		sortVariableLIstScrollPaneCon.weightx = 0.5;
		// sortVariableLIstScrollPaneCon.weighty = 0.5;
		sortVariableLIstScrollPaneCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(sortVarialbeListScrollPane,
				sortVariableLIstScrollPaneCon);

		JLabel xVariableLable = new JLabel("Time");
		GridBagConstraints xVariableLableCon = new GridBagConstraints();
		xVariableLableCon.gridx = 0;
		xVariableLableCon.gridy = 2;
		xVariableLableCon.weightx = 0.5;
		// xVariableLableCon.weighty = 0.5;
		xVariableLableCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(xVariableLable,
				xVariableLableCon);

		xVariableTextField = new JTextField();
		xVariableTextField.setText("");
		xVariableTextField.setBackground(Color.white);
		xVariableTextField.setEditable(false);
		xVariableTextField
				.addFocusListener(DDViewLayer.createViewLayerInstance());
		xVariableTextField.setColumns(8);
		xVariableTextField.setVisible(true);
		GridBagConstraints xVariableTextFieldCon = new GridBagConstraints();
		xVariableTextFieldCon.gridx = 0;
		xVariableTextFieldCon.gridy = 3;
		// xVariableTextFieldCon.weightx = 0.1;
		xVariableTextFieldCon.weighty = 0.5;
		xVariableTextFieldCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(xVariableTextField,
				xVariableTextFieldCon);

		JLabel yVariableLable = new JLabel("Concentration");
		GridBagConstraints yVairableLableCon = new GridBagConstraints();
		yVairableLableCon.gridx = 0;
		yVairableLableCon.gridy = 6;
		yVairableLableCon.weightx = 0.5;

		yVairableLableCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(yVariableLable,
				yVairableLableCon);

		yVariableTextField = new JTextField();
		yVariableTextField.setText("");
		yVariableTextField.setBackground(Color.white);
		yVariableTextField.setEditable(false);
		// yVariableTextField.addMouseListener(ViewLayer.createViewLayerInstance());
		yVariableTextField
				.addFocusListener(DDViewLayer.createViewLayerInstance());
		yVariableTextField.setColumns(8);
		GridBagConstraints yVariableTextFieldCon = new GridBagConstraints();
		yVariableTextFieldCon.gridx = 0;
		yVariableTextFieldCon.gridy = 7;
		yVariableTextFieldCon.weightx = 0.5;
		yVariableTextFieldCon.weighty = 0.5;
		yVariableTextFieldCon.anchor = GridBagConstraints.LINE_START;
		leftInternalFrame.getContentPane().add(yVariableTextField,
				yVariableTextFieldCon);
		mappinginternalFrameDesktopPane.add(leftInternalFrame);
		leftInternalFrame.moveToFront();

		JInternalFrame leftarrowsInternalFrame = new JInternalFrame(
				"left arrows", false, false, false, false);
		leftarrowsInternalFrame.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(leftarrowsInternalFrame);
		leftarrowsInternalFrame.setLocation(leftInternalFrame.getX()
				+ leftInternalFrame.getWidth(), leftInternalFrame.getY());
		leftarrowsInternalFrame.setSize(leftInternalFrame.getWidth(),
				leftInternalFrame.getHeight());
		leftarrowsInternalFrame.setVisible(true);
		leftarrowsInternalFrame.setLayout(gridBagLayout);
		leftarrowsInternalFrame.setBorder(null);

		SortAndAvailableButton = new JButton("", toLeft);
		SortAndAvailableButton.setToolTipText("Move to Sort List");
		SortAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		SortAndAvailableButton.setActionCommand(TTAVAILAANDSORT);
		SortAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		SortAndAvailableButton.setVisible(true);
		SortAndAvailableButton.setEnabled(false);
		GridBagConstraints moveToSortListButtonCon = new GridBagConstraints();
		moveToSortListButtonCon.gridx = 0;
		moveToSortListButtonCon.gridy = 1;
		// moveToSortListButtonCon.weightx = 0.5;
		moveToSortListButtonCon.weighty = 0.5;
		// moveToSortListButtonCon.anchor = GridBagConstraints.LINE_END;
		leftarrowsInternalFrame.getContentPane().add(SortAndAvailableButton,
				moveToSortListButtonCon);

		xAndAvailableButton = new JButton("", toLeft);
		xAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		xAndAvailableButton.setActionCommand(TTAVAILANDX);
		xAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		GridBagConstraints setAsXVariableCon = new GridBagConstraints();
		setAsXVariableCon.gridx = 0;
		setAsXVariableCon.gridy = 2;
		setAsXVariableCon.weighty = 0.5;
		setAsXVariableCon.anchor = GridBagConstraints.LINE_START;
		leftarrowsInternalFrame.getContentPane().add(xAndAvailableButton,
				setAsXVariableCon);

		YAndAvailableButton = new JButton("", toLeft);
		YAndAvailableButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		YAndAvailableButton.setActionCommand(TTAVAILANDY);
		YAndAvailableButton.setPreferredSize(new Dimension(60, 15));
		GridBagConstraints setAsYVariableButtonCon = new GridBagConstraints();
		setAsYVariableButtonCon.gridy = 4;
		setAsYVariableButtonCon.gridx = 0;
		setAsYVariableButtonCon.weighty = 0.5;
		setAsYVariableButtonCon.anchor = GridBagConstraints.LINE_START;
		leftarrowsInternalFrame.getContentPane().add(YAndAvailableButton,
				setAsYVariableButtonCon);
		mappinginternalFrameDesktopPane.add(leftarrowsInternalFrame);
		leftarrowsInternalFrame.moveToFront();

		JInternalFrame middleInternalFrame = new JInternalFrame("middle",
				false, false, false, false);
		middleInternalFrame.setBackground(Color.white);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(middleInternalFrame);
		middleInternalFrame.setLocation(leftarrowsInternalFrame.getX()
				+ leftarrowsInternalFrame.getWidth(), leftInternalFrame.getY());
		middleInternalFrame.setSize(leftInternalFrame.getWidth(),
				leftInternalFrame.getHeight());
		middleInternalFrame.setVisible(true);
		middleInternalFrame.setLayout(gridBagLayout);
		middleInternalFrame.moveToFront();
		middleInternalFrame.setBorder(null);

		JLabel availableColumnsLable = new JLabel("Variables");
		GridBagConstraints availableColumnsLableCon = new GridBagConstraints();
		availableColumnsLableCon.gridx = 0;
		availableColumnsLableCon.gridy = 0;
		middleInternalFrame.getContentPane().add(availableColumnsLable,
				availableColumnsLableCon);

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		availableVariablesListmodel = new DefaultListModel();
		availableColumnsList = new JList(availableVariablesListmodel);

		if ((appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
				&& (DesStatResDispComp.createDesStatResDispInst().carryThisToOtherComponents == true)) {
			TableColumnModel tm = DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
					.getColumnModel();
			for (int i = 0; i < DesStatResDispComp.createDesStatResDispInst().finalparametersVerticalDisplayTable
					.getColumnCount(); i++) {
				TableColumn tc = tm.getColumn(i);
				availableVariablesListmodel
						.add(i, (String) tc.getHeaderValue());
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getTtInfo()
						.getProcessingInputs().getMappingDataObj()
						.getAvailableColumnsVector().add(i,
								(String) tc.getHeaderValue());

			}

		} else {
			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				availableVariablesListmodel.add(i, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(i).getColumnName());
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getTtInfo()
						.getProcessingInputs()
						.getMappingDataObj()
						.getAvailableColumnsVector()
						.add(
								i,
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getColumnPropertiesArrayList().get(i)
										.getColumnName());

			}

		}

		// availableColumnsList.addMouseListener(ViewLayer.createViewLayerInstance());
		availableColumnsList.addListSelectionListener(DDViewLayer
				.createViewLayerInstance());
		availableColumnsList.setVisible(true);
		availableColumnsList.setVisibleRowCount(5);
		JScrollPane columnsAvailableListScrollPane = new JScrollPane(
				availableColumnsList);
		columnsAvailableListScrollPane
				.setPreferredSize(new Dimension(100, 200));
		GridBagConstraints columnsAvailableListScrollPaneCon = new GridBagConstraints();
		columnsAvailableListScrollPaneCon.gridx = 0;
		columnsAvailableListScrollPaneCon.gridy = 1;
		columnsAvailableListScrollPaneCon.gridheight = 3;
		middleInternalFrame.getContentPane().add(
				columnsAvailableListScrollPane,
				columnsAvailableListScrollPaneCon);

		mappinginternalFrameDesktopPane.add(middleInternalFrame);
		middleInternalFrame.moveToFront();
	}

	private void createSetupAvailIF() {

		setupAvailIF = new JInternalFrame("", false, false, false, false);
		setupAvailIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(setupAvailIF);
		setupAvailIF.setLocation(0, 0);
		setupAvailIF.setSize(ttMainIF.getWidth() / 4, ttMainIF.getHeight()-30);
		setupAvailIF.setVisible(true);
		setupAvailIF.moveToFront();
		ttSetupDP.add(setupAvailIF);

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		DefaultMutableTreeNode sheetNode = new DefaultMutableTreeNode(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getSheetName());
		availCompTree = new JTree(sheetNode);
		availCompTree.setEditable(true);
		availCompTree.addTreeSelectionListener(DDViewLayer.createViewLayerInstance());
		availCompTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		DefaultMutableTreeNode mappingNode = new DefaultMutableTreeNode(
				"Mapping");
		sheetNode.add(mappingNode);
		JScrollPane availCompTreeSP = new JScrollPane(availCompTree);
		availCompTreeSP
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		availCompTreeSP
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setupAvailIF.getContentPane().add(availCompTreeSP);
		for(int i=0;i<availCompTree.getRowCount();i++){
			availCompTree.expandRow(i);
		}
	
	}

}
