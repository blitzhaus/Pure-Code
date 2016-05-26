package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;


import Common.EventCodes;
import Common.ImageLoader;
import Common.JinternalFrameFunctions;
import Common.ReadFile2dStrArray;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CaLibModelDispGuiCreator {

	MyJTable libraryModelTable;
	MyJTable diffEqunLibraryModelTable;

	int libraeyModelSelected;

	/*
	 * JRadioButton algebraicEquationModelRadioButton; JRadioButton
	 * differentialEquationModelRadioButton; JRadioButton asciiModelRadioButton;
	 * 
	 * JComboBox availableLibraryModelComboBox; JComboBox
	 * availableDifferentialEquationModelComboBox;
	 */

	JInternalFrame libraryModelInternalFrameForCA;
	JInternalFrame differentialEqunModelIF;
	JInternalFrame combinedLibraryModelIF;
	JInternalFrame diagramAndFormulaInternalFrameForCA;
	JInternalFrame diagramAndFormulaInternalFrameForDiffModel;
	JInternalFrame diagramInternalFrameForDiffModel;
	JInternalFrame formulaInternalFrameForDiffModel;
	JInternalFrame diagramInternalFrame;
	JInternalFrame formulaInternalFrame;

	JTabbedPane libraryModelTP;

	JDesktopPane algebraicModelDP;
	JDesktopPane differentialEquationModelDP;
	JDesktopPane libraryModelDesktopPane;
	//JDesktopPane diagramDesktopPane;
	//JDesktopPane formulaDesktopPane;
	/*JDesktopPane diagramDesktopPaneForDiffModel;
	JDesktopPane formulaDesktopPaneForDiffModel;*/

	JPanel libraryModelDiagramPanelForDiiffModel;
	JPanel libraryModelFormulaPanelForDiiffModel;
	JPanel libraryModelDiagramPanel;
	JPanel libraryModelFormulaPanel;

	JButton imageButton;
	JButton formulaImageButton;
	JButton imageButtonForDiiffModel;
	JCheckBox clearanceCb;
	JCheckBox simulationCb;
	
	JTextField pkUnitTf;
	JButton pkUnitButton;

	JTextField simulationUnitTf;
	JButton simulationUnitButton;

	int modelNumber;
	int previousLibraryModel = 0;
	int previousLibraryModelForLink = 0;

	boolean ifLibraryModelIsChanged;
	boolean ifSecondLibraryModelIsChanged;
	boolean ifFromPkUnit;
	boolean ifFromSimulationUnit = false;

	boolean calculateInitialValue;
	Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);

	String analysisType;
	ApplicationInfo appInfo;
	
	DefaultTableModel firstSetOfLibModel;

	public static CaLibModelDispGuiCreator CA_LIB_MOD_GUI = null;

	public static CaLibModelDispGuiCreator createCalibraryModelInstance()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (CA_LIB_MOD_GUI == null) {
			CA_LIB_MOD_GUI = new CaLibModelDispGuiCreator();
		}
		return CA_LIB_MOD_GUI;
	}

	public void caLibModelGuiCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {
		createLibModelInternalFrame();
	}

	public void createLibModelInternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		createLibraryModelInternalFrameForCA();
		createDiagramAndFormulaInternalFrameForCA();

		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.equals("pk") || analysisType.equals("pkpdlink") || analysisType.equals("irm")) {
			createDiffEqunModelIF();
		}

	}

	final class LibModelTableActionEventHandler implements FocusListener {
		@Override
		public void focusGained(FocusEvent arg0) {
			libraeyModelSelected = libraryModelTable.getSelectedRow();

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			String analysisType = appInfo
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
									.getSelectedSheetIndex()).getAnalysisType();

			ProcessingInputsInfo procInputInst = CaMapingHandler
					.createCaMapHandInst().copyProcessingInput();

			if (analysisType.equals("pk")) {
							
				
				procInputInst.getModelInputTab1Obj().setAlgebraicModel(true);

				procInputInst.getModelInputTab1Obj().setModelNumberForCA(
						libraeyModelSelected + 1);

			} else if (analysisType.equals("pd")) {

				procInputInst.getModelInputTab1Obj().setModelNumberForCA(
						libraeyModelSelected + 1);
			} else if (analysisType.equals("mm")) {
				procInputInst.getModelInputTab1Obj().setModelNumberForCA(
						libraeyModelSelected + 1);
			} else if (analysisType.equals("pkpdlink")) {

				procInputInst.getModelInputTab1Obj()
						.setModelNumberForLinkModel(libraeyModelSelected + 1);
			} else if (analysisType.equals("irm")) {

				procInputInst.getModelInputTab1Obj()
						.setModelNumberForLinkModel(libraeyModelSelected + 1);
			}

			CaMapingHandler.createCaMapHandInst().setProcessingInput(
					procInputInst);

			

			

			addImageToPanel(libraeyModelSelected + 1);

			libraryModelTable.validate();
			libraryModelInternalFrameForCA.validate();
			libraryModelInternalFrameForCA
					.setSize(libraryModelInternalFrameForCA.getSize());
			try {
				actionListenerEventForAvailableLibraryModelComboBox();
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}

		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	final class DiffEqunLibModelTableActionEventHandler implements
			FocusListener {
		@Override
		public void focusGained(FocusEvent arg0) {
			libraeyModelSelected = diffEqunLibraryModelTable.getSelectedRow();

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			String analysisType = appInfo
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
									.getSelectedSheetIndex()).getAnalysisType();

			ProcessingInputsInfo procInputInst = CaMapingHandler
					.createCaMapHandInst().copyProcessingInput();

			if (analysisType.equals("pk")) {
				
				
				
				procInputInst.getModelInputTab1Obj().setAlgebraicModel(false);

				procInputInst.getModelInputTab1Obj().setModelNumberForCA(
						libraeyModelSelected + 1);

			} else if (analysisType.equals("pd")) {
				procInputInst.getModelInputTab1Obj().setModelNumberForCA(
						libraeyModelSelected + 1);
			} else if (analysisType.equals("mm")) {
				procInputInst.getModelInputTab1Obj().setModelNumberForCA(
						libraeyModelSelected + 1);
			} else if (analysisType.equals("pkpdlink")) {
				procInputInst.getModelInputTab1Obj().setIfLinkModel(true);
				procInputInst.getModelInputTab1Obj().setModelNumberForCA(
						libraeyModelSelected + 1);
			} else if (analysisType.equals("irm")) {
				procInputInst.getModelInputTab1Obj().setIfLinkModel(true);
				procInputInst.getModelInputTab1Obj().setModelNumberForCA(
						libraeyModelSelected + 1);
			}
			CaMapingHandler.createCaMapHandInst().setProcessingInput(
					procInputInst);

			

			addImageToSecondLibModelPanel(libraeyModelSelected + 1);
			diffEqunLibraryModelTable.validate();
			differentialEqunModelIF.validate();
			differentialEqunModelIF.setSize(differentialEqunModelIF.getSize());

			try {
				actionListenerEventForAvailableDifferentaiEquationModelComboBox();
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	
	private void addImageToSecondLibModelPanel(int i) {

		addImageToSecondDiagramPanel(i);
	//	addImageToSecondFormulaPanel(i);
	}

	private void addImageToPanel(int modelNumber) {
		addImageToDiagramPanel(modelNumber);
		//addImageToFormulaPanel(modelNumber);
	}

	private void createLibraryModelInternalFrameForCA() throws RowsExceededException,
			WriteException, BiffException, IOException {
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		libraryModelInternalFrameForCA = new JInternalFrame("Library Model",
				false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(libraryModelInternalFrameForCA);
		//libraryModelInternalFrameForCA.setBorder(ViewLayer.createViewLayerInstance().b);
		libraryModelInternalFrameForCA.setLocation(0, 0);
		libraryModelInternalFrameForCA
				.setSize(
						CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame
								.getWidth(), (int) (CaOptionsGuiCreator
								.createPdOptionsInstance().optionsInternalFrame
								.getHeight()/.7));

		libraryModelInternalFrameForCA.setVisible(true);

		libraryModelInternalFrameForCA.setLayout(new GridLayout(2, 2));
		libraryModelInternalFrameForCA.setBorder(viewLayerInst.b);
		createInternalGuiForLibraryModel();
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();
		
		
		if(analysisType.equals("pk"))
		{
			createLibraryModelTab();
			algebraicModelDP .add(libraryModelInternalFrameForCA);
		}else if(analysisType.equals("pd") || analysisType.equals("mm") || analysisType.equals("pkpdlink")
				|| analysisType.equals("irm"))
		{
			CaOptionsGuiCreator.createPdOptionsInstance().libraryModelTabDesktopPane
			.add(libraryModelInternalFrameForCA);
		}

		
		
		
		// caMainScreen.libraryModelInternalFrameForCA.pack();
		libraryModelInternalFrameForCA.moveToFront();

		// createDiffEqunModelIF();

	}

	private void createLibraryModelTab() throws RowsExceededException, WriteException,
			BiffException, IOException {

		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		combinedLibraryModelIF = new JInternalFrame("Library Model", false,
				false, false, false);

		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(combinedLibraryModelIF);

		combinedLibraryModelIF.setLocation(0, 0);
		combinedLibraryModelIF.setSize(
				CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame
						.getWidth(), (int) (CaOptionsGuiCreator
						.createPdOptionsInstance().optionsInternalFrame
						.getHeight() / 1.3));

		combinedLibraryModelIF.setVisible(true);
		combinedLibraryModelIF.setBorder(viewLayerInst.b);

		libraryModelTP = new JTabbedPane();

		algebraicModelDP = new JDesktopPane();
		libraryModelTP.addTab("Algebraic Model", algebraicModelDP);

		differentialEquationModelDP = new JDesktopPane();
		libraryModelTP.addTab("Differential Equation Model",
				differentialEquationModelDP);

		CaOptionsGuiCreator.createPdOptionsInstance().libraryModelTabDesktopPane.add(combinedLibraryModelIF);
		combinedLibraryModelIF.getContentPane().add(libraryModelTP);

	}

	private void createDiagramAndFormulaInternalFrameForCA()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		diagramAndFormulaInternalFrameForCA = new JInternalFrame(
				"Diagram And Formula", false, false, false, false);

		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						diagramAndFormulaInternalFrameForCA);

		diagramAndFormulaInternalFrameForCA.setLocation(CaOptionsGuiCreator
				.createPdOptionsInstance().optionsInternalFrame.getWidth() / 2,
				0);
		diagramAndFormulaInternalFrameForCA
				.setSize(
						CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame
								.getWidth() / 2, (int) (CaOptionsGuiCreator
								.createPdOptionsInstance().optionsInternalFrame
								.getHeight() / 1.2));

		diagramAndFormulaInternalFrameForCA.setVisible(true);

		diagramAndFormulaInternalFrameForCA.setLayout(new GridLayout(1, 1));
		diagramAndFormulaInternalFrameForCA.setBorder(viewLayerInst.b);

		libraryModelInternalFrameForCA.add(diagramAndFormulaInternalFrameForCA);
		
		
		JPanel dummyPanel1 = new JPanel();
		
		JPanel dummyPanel2 = new JPanel();
		
		libraryModelInternalFrameForCA.add(dummyPanel1);
		libraryModelInternalFrameForCA.add(dummyPanel2
				);
		// diagramAndFormulaInternalFrameForCA.pack();
		diagramAndFormulaInternalFrameForCA.moveToFront();

		/*JTabbedPane diagramAndFormulaTabbedPane = new JTabbedPane();
		diagramAndFormulaInternalFrameForCA.add(diagramAndFormulaTabbedPane);

		diagramDesktopPane = new JDesktopPane();
		formulaDesktopPane = new JDesktopPane();
		diagramAndFormulaTabbedPane.addTab("Diagram", diagramDesktopPane);
		diagramAndFormulaTabbedPane.addTab("Formula", formulaDesktopPane);*/

		createDiagramAndFormulaInternalGUI();
	}

	private void createDiagramAndFormulaInternalFrameForDiffModel()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		diagramAndFormulaInternalFrameForDiffModel = new JInternalFrame(
				"Diagram And Formula", false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						diagramAndFormulaInternalFrameForDiffModel);

		/*diagramAndFormulaInternalFrameForDiffModel.setLocation(PdOptionsGui
				.createPdOptionsInstance().optionsInternalFrame.getWidth() / 2,
				0);*/
		diagramAndFormulaInternalFrameForDiffModel
				.setSize(
						CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame
								.getWidth() / 2, (int) (CaOptionsGuiCreator
								.createPdOptionsInstance().optionsInternalFrame
								.getHeight() / 1.2));

		diagramAndFormulaInternalFrameForDiffModel.setVisible(true);

		diagramAndFormulaInternalFrameForDiffModel.setLayout(new GridLayout(1,
				1));
		diagramAndFormulaInternalFrameForDiffModel.setBorder(viewLayerInst.b);

		/*
		 * differentialEquationModelDP
		 * .add(diagramAndFormulaInternalFrameForDiffModel);
		 */

		differentialEqunModelIF.getContentPane()
				.add(diagramAndFormulaInternalFrameForDiffModel);
		
		JPanel dummyPanel1 = new JPanel();
		JPanel dummyPanel2 = new JPanel();
		
		differentialEqunModelIF.getContentPane().add(dummyPanel1);
		differentialEqunModelIF.getContentPane().add(dummyPanel2);

		diagramAndFormulaInternalFrameForDiffModel.moveToFront();

		/*JTabbedPane diagramAndFormulaTabbedPane = new JTabbedPane();
		diagramAndFormulaInternalFrameForDiffModel
				.add(diagramAndFormulaTabbedPane);
.
		diagramDesktopPaneForDiffModel = new JDesktopPane();
		formulaDesktopPaneForDiffModel = new JDesktopPane();
		diagramAndFormulaTabbedPane.addTab("Diagram",
				diagramDesktopPaneForDiffModel);
		diagramAndFormulaTabbedPane.addTab("Formula",
				formulaDesktopPaneForDiffModel);
	*/
		createDiagramAndFormulaForDiffInternalGUI();
	}

	private void createDiagramAndFormulaForDiffInternalGUI()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		/*
		 * TitledBorder titledBorder = BorderFactory.createTitledBorder(null,
		 * "Diagram", TitledBorder.CENTER, TitledBorder.CENTER,
		 * PdMainScreen.createPdMainScreenInstance().componentLablesFont,
		 * Color.black);
		 */
		libraryModelDiagramPanelForDiiffModel = new JPanel();
		// libraryModelDiagramPanelForDiiffModel.setBorder(titledBorder);

		// libraryModelDiagramPanelForDiiffModel.setLayout(new GridLayout(3,
		// 3));
		libraryModelDiagramPanelForDiiffModel.setLayout(new BorderLayout());
		GridBagConstraints liModelDiaPanelGbc = new GridBagConstraints();
		liModelDiaPanelGbc.gridwidth = 4;
		liModelDiaPanelGbc.insets = new Insets(0, 0, 5, 5);
		liModelDiaPanelGbc.fill = GridBagConstraints.BOTH;
		liModelDiaPanelGbc.gridx = 0;
		liModelDiaPanelGbc.gridy = 0;

		// caMainScreen.libraryModelDiagramPanel.add(caMainScreen.imageButton);
		diagramInternalFrameForDiffModel = new JInternalFrame("Diagram", false,
				false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						diagramInternalFrameForDiffModel);

		diagramInternalFrameForDiffModel.setLocation(0, 0);
		diagramInternalFrameForDiffModel
				.setSize(
						(int) (CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame
								.getWidth() / 2.1), (int) (CaOptionsGuiCreator
								.createPdOptionsInstance().optionsInternalFrame
								.getHeight() / 1.6));
		diagramInternalFrameForDiffModel.setLayout(new GridBagLayout());
		diagramInternalFrameForDiffModel.setBorder(viewLayerInst.b);
		diagramInternalFrameForDiffModel.setVisible(true);

		/*JScrollPane libModelSP = new JScrollPane(
				libraryModelDiagramPanelForDiiffModel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);*/

		diagramInternalFrameForDiffModel.getContentPane().add(libraryModelDiagramPanelForDiiffModel,
				liModelDiaPanelGbc);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints clrPanelGbc = new GridBagConstraints();
		clrPanelGbc.gridwidth = 4;
		clrPanelGbc.insets = new Insets(0, 0, 5, 5);
		clrPanelGbc.fill = GridBagConstraints.BOTH;
		clrPanelGbc.gridx = 0;
		clrPanelGbc.gridy = 1;
		
		diagramInternalFrameForDiffModel.getContentPane().add(panel,
				clrPanelGbc);
		//diagramDesktopPaneForDiffModel.add(diagramInternalFrameForDiffModel);
		
		
		diagramAndFormulaInternalFrameForDiffModel.getContentPane().add(diagramInternalFrameForDiffModel);

		/*
		 * TitledBorder titledBorder = BorderFactory.createTitledBorder(null,
		 * "Formula", TitledBorder.CENTER, TitledBorder.CENTER,
		 * PdMainScreen.createPdMainScreenInstance().componentLablesFont,
		 * Color.black);
		 */
		libraryModelFormulaPanelForDiiffModel = new JPanel();
		// libraryModelFormulaPanelForDiiffModel.setBorder(titledBorder);

	//	libraryModelFormulaPanelForDiiffModel.setLayout(new GridLayout(3, 3));
		libraryModelFormulaPanelForDiiffModel.setLayout(new BorderLayout());

		GridBagConstraints liModelFormulaPanelGbc = new GridBagConstraints();
		liModelFormulaPanelGbc.gridwidth = 4;
		liModelFormulaPanelGbc.insets = new Insets(0, 0, 5, 5);
		liModelFormulaPanelGbc.fill = GridBagConstraints.BOTH;
		liModelFormulaPanelGbc.gridx = 0;
		liModelFormulaPanelGbc.gridy = 0;

		// caMainScreen.libraryModelFormulaPanel.add(caMainScreen.imageButton);
		
		
		
		
		
		formulaInternalFrameForDiffModel = new JInternalFrame("Formula", false,
				false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(
						formulaInternalFrameForDiffModel);

		formulaInternalFrameForDiffModel.setLocation(0, 0);
		formulaInternalFrameForDiffModel
				.setSize(
						(int) (CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame
								.getWidth() / 2.1), (int) (CaOptionsGuiCreator
								.createPdOptionsInstance().optionsInternalFrame
								.getHeight() / 1.6));
		formulaInternalFrameForDiffModel.setLayout(new GridLayout(1, 1));
		formulaInternalFrameForDiffModel.setBorder(viewLayerInst.b);
		formulaInternalFrameForDiffModel.setVisible(true);

		/*
		 * JScrollPane formulaSP = new JScrollPane(
		 * libraryModelFormulaPanelForDiiffModel,
		 * JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		 * JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 */
		formulaInternalFrameForDiffModel.getContentPane().add(
				libraryModelFormulaPanelForDiiffModel, liModelFormulaPanelGbc);
		
		
		
		//formulaDesktopPaneForDiffModel.add(formulaInternalFrameForDiffModel);

	}

	private void createDiffEqunModelIF() throws RowsExceededException, WriteException,
			BiffException, IOException {
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		differentialEqunModelIF = new JInternalFrame("Diff Equn Model", false,
				false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(differentialEqunModelIF);

		differentialEqunModelIF.setLocation(0, 0);
		differentialEqunModelIF.setSize(
				CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame
						.getWidth(), (int) (CaOptionsGuiCreator
						.createPdOptionsInstance().optionsInternalFrame
						.getHeight() / .7));

		differentialEqunModelIF.setVisible(true);
		differentialEqunModelIF.setBorder(viewLayerInst.b);
		differentialEqunModelIF.setLayout(new GridLayout(2, 2));
		/*
		 * differentialEquationModelDP .add(differentialEqunModelIF);
		 */

		if(analysisType.equals("pk"))
		{
			differentialEquationModelDP.add(differentialEqunModelIF);
		} else if(analysisType.equals("pkpdlink") || analysisType.equals("irm"))
		{
			CaOptionsGuiCreator.createPdOptionsInstance().linkModelTabDesktopPane
			.add(differentialEqunModelIF);
		}
		
		
		differentialEqunModelIF.moveToFront();

		createInternalGUIForSecondModel();

		createDiagramAndFormulaInternalFrameForDiffModel();

	}

	public void createInternalGUIForSecondModel() {

		UIDefaults ui = UIManager.getLookAndFeel().getDefaults();
		UIManager.put("RadioButton.focus", ui.getColor("control"));

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.equals("pk")) {
			fileData = readFile("DiffModelTableDisplay.txt");

		} else
		if (analysisType.equals("pkpdlink")) {
			fileData = readFile("PDModelTableDisplay.txt");

		} else if (analysisType.equals("irm")) {
			fileData = readFile("IRModelTableDisplay.txt");

		}

		Object data[][] = new Object[fileData.length - 1][fileData[0].length + 1];
		Object col[] = new Object[fileData[0].length + 1];
		int[] cIdx = new int[fileData[0].length];
		col[0] = "Selected";
		for (int j = 1; j < data[0].length; j++) {
			col[j] = fileData[0][j - 1];
			cIdx[j - 1] = j;
		}

		for (int i = 1; i < fileData.length; i++) {
			data[i - 1][0] = new JRadioButton("");

			for (int j = 1; j < data[0].length; j++) {
				data[i - 1][j] = fileData[i][j - 1];
			}

		}

		DefaultTableModel model = new DefaultTableModel(data, col);

		diffEqunLibraryModelTable = new MyJTable(model, cIdx) {
			public void tableChanged(TableModelEvent e) {
				super.tableChanged(e);
				repaint();
			}
		};

		ButtonGroup group = new ButtonGroup();

		
		
		if(analysisType.equals("pk"))
		{
			for (int i = 0; i < 19; i++) {
				group.add((JRadioButton) firstSetOfLibModel.getValueAt(i, 0));
			}
			
			for (int i = 0; i < data.length; i++) {
				group.add((JRadioButton) model.getValueAt(i, 0));
			}

		}else
		{
			for (int i = 0; i < data.length; i++) {
				group.add((JRadioButton) model.getValueAt(i, 0));
			}

		}
		
		
		
		
		diffEqunLibraryModelTable.getColumn("Selected").setCellRenderer(
				new RadioButtonRenderer());
		diffEqunLibraryModelTable.getColumn("Selected").setCellEditor(
				new RadioButtonEditor(new JCheckBox()));

		diffEqunLibraryModelTable
				.addFocusListener(new DiffEqunLibModelTableActionEventHandler());

		JScrollPane libraryModelScrollPane = new JScrollPane(
				diffEqunLibraryModelTable,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		differentialEqunModelIF.getContentPane().add(libraryModelScrollPane);

	}

	String[][] fileData;

	void createInternalGuiForLibraryModel() throws RowsExceededException, WriteException, BiffException, IOException {

		UIDefaults ui = UIManager.getLookAndFeel().getDefaults();
		UIManager.put("RadioButton.focus", ui.getColor("control"));

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();


		if (analysisType.equals("pk")) {
			fileData = readFile("PKModelTableDisplay.txt");

		} else
		if (analysisType.equals("pd")) {
			fileData = readFile("PDModelTableDisplay.txt");

		} else if (analysisType.equals("mm")) {
			fileData = readFile("MMModelTableDisplay.txt");

		} else if (analysisType.equals("pkpdlink")) {
			fileData = readFile("PKModelTableDisplay.txt");

		} else if (analysisType.equals("irm")) {
			fileData = readFile("PKModelTableDisplay.txt");

		}

		Object data[][] = new Object[fileData.length - 1][fileData[0].length + 1];
		Object col[] = new Object[fileData[0].length + 1];
		int[] cIdx = new int[fileData[0].length];
		col[0] = "Selected";
		for (int j = 1; j < data[0].length; j++) {
			col[j] = fileData[0][j - 1];
			cIdx[j - 1] = j;
		}

		for (int i = 1; i < fileData.length; i++) {
			data[i - 1][0] = new JRadioButton("");

			for (int j = 1; j < data[0].length; j++) {
				data[i - 1][j] = fileData[i][j - 1];
			}

		}

		firstSetOfLibModel = new DefaultTableModel(data, col);

		libraryModelTable = new MyJTable(firstSetOfLibModel, cIdx) {
			public void tableChanged(TableModelEvent e) {
				super.tableChanged(e);
				repaint();
			}
		};

		ButtonGroup group = new ButtonGroup();

		for (int i = 0; i < data.length; i++) {
			group.add((JRadioButton) firstSetOfLibModel.getValueAt(i, 0));
		}

		libraryModelTable.getColumn("Selected").setCellRenderer(
				new RadioButtonRenderer());
		libraryModelTable.getColumn("Selected").setCellEditor(
				new RadioButtonEditor(new JCheckBox()));

		libraryModelTable
				.addFocusListener(new LibModelTableActionEventHandler());
	/*	JInternalFrame libraryModelIf = new JInternalFrame();
		libraryModelIf.setSize(
				PdOptionsGui.createPdOptionsInstance().optionsInternalFrame
				.getWidth(), (int) (PdOptionsGui
				.createPdOptionsInstance().optionsInternalFrame
				.getHeight()/1.6));
		
		libraryModelIf.setLocation(0, 0);
		*/
		
/*
		JInternalFrame libraryModelIf = new JInternalFrame("Library Model",
				false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(libraryModelIf);
		//libraryModelInternalFrameForCA.setBorder(ViewLayer.createViewLayerInstance().b);
		libraryModelIf.setLocation(0, 0);
		libraryModelIf
				.setSize(
						PdOptionsGui.createPdOptionsInstance().optionsInternalFrame
								.getWidth(), (int) (PdOptionsGui
								.createPdOptionsInstance().optionsInternalFrame
								.getHeight()/1.6));

		libraryModelIf.setVisible(true);

		libraryModelIf.setLayout(new GridLayout(1, 2));
		libraryModelIf.setBorder(ViewLayer.createViewLayerInstance().b);
		*/
	
		JScrollPane libraryModelScrollPane = new JScrollPane(libraryModelTable,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//libraryModelIf.getContentPane().add(libraryModelScrollPane);
		
	/*	libraryModelScrollPane.setPreferredSize(new Dimension(
				PdOptionsGui.createPdOptionsInstance().optionsInternalFrame
				.getWidth(), (int) (PdOptionsGui
				.createPdOptionsInstance().optionsInternalFrame
				.getHeight()/1.6)));*/
		/*libraryModelScrollPane.setPreferredSize(
				PdOptionsGui.createPdOptionsInstance().optionsInternalFrame
				.getWidth(), (int) (PdOptionsGui
				.createPdOptionsInstance().optionsInternalFrame
				.getHeight()/1.6));*/
		
		libraryModelInternalFrameForCA.getContentPane().add(
				libraryModelScrollPane);

	}

	void createDiagramAndFormulaInternalGUI() throws RowsExceededException,
			WriteException, BiffException, IOException {

		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo
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
						.getSelectedSheetIndex()).getAnalysisType();

		/*
		 * TitledBorder titledBorder = BorderFactory.createTitledBorder(null,
		 * "Diagram", TitledBorder.CENTER, TitledBorder.CENTER,
		 * componentLablesFont, Color.black);
		 */

		libraryModelDiagramPanel = new JPanel();
		// caMainScreen.libraryModelDiagramPanel.setBorder(titledBorder);

		// caMainScreen.libraryModelDiagramPanel.setLayout(new GridLayout(1,
		// 1));
		libraryModelDiagramPanel.setLayout(new BorderLayout());

		// caMainScreen.libraryModelDiagramPanel.setLayout(new GridLayout(2,1));
		GridBagConstraints liModelDiaPanelGbc = new GridBagConstraints();
		liModelDiaPanelGbc.gridwidth = 4;
		liModelDiaPanelGbc.insets = new Insets(0, 0, 5, 5);
		liModelDiaPanelGbc.fill = GridBagConstraints.BOTH;
		liModelDiaPanelGbc.gridx = 0;
		liModelDiaPanelGbc.gridy = 0;
		// liModelDiaPanelGbc.ipadx = 100;

		// caMainScreen.libraryModelDiagramPanel.add(caMainScreen.imageButton);
		diagramInternalFrame = new JInternalFrame("Diagram", false, false,
				false, false);

		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(diagramInternalFrame);

		diagramInternalFrame.setLocation(0, 0);
		diagramInternalFrame
				.setSize(
						(int) (CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame
								.getWidth() / 2.1), (int) (CaOptionsGuiCreator
								.createPdOptionsInstance().optionsInternalFrame
								.getHeight() / 1.6));
		// caMainScreen.diagramInternalFrame.setLayout(new GridLayout(2, 1));
		diagramInternalFrame.setLayout(new GridBagLayout());
		diagramInternalFrame.setBorder(viewLayerInst.b);
		diagramInternalFrame.setVisible(true);

		JScrollPane libModelSP = new JScrollPane(libraryModelDiagramPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		diagramInternalFrame.getContentPane().add(libraryModelDiagramPanel,
				liModelDiaPanelGbc);

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints clrPanelGbc = new GridBagConstraints();
		clrPanelGbc.gridwidth = 4;
		clrPanelGbc.insets = new Insets(0, 0, 5, 5);
		clrPanelGbc.fill = GridBagConstraints.BOTH;
		clrPanelGbc.gridx = 0;
		clrPanelGbc.gridy = 1;

		clearanceCb = new JCheckBox("Clearance");
		clearanceCb.setFont(componentOptionseFont);
		
		if(analysisType.equals("pd") || analysisType.equals("pkpdlink") ||
				analysisType.equals("mm") ||analysisType.equals("irm") )
		{
			clearanceCb.setVisible(false);
		}
		

		clearanceCb.addActionListener(DDViewLayer.createViewLayerInstance());
		clearanceCb.setActionCommand(EventCodes.CLER);
		GridBagConstraints clearanceCbGbc = new GridBagConstraints();
		/*
		 * clearanceCbGbc.gridwidth = 4; clearanceCbGbc.insets = new Insets(0,
		 * 0, 5, 5); clearanceCbGbc.fill = GridBagConstraints.BOTH;
		 */
		clearanceCbGbc.gridx = 1;
		clearanceCbGbc.gridy = 0;
		// clearanceCbGbc.ipadx = 100
		// caMainScreen.diagramInternalFrame.getContentPane().add(caMainScreen.clearanceCb,clearanceCbGbc);

		panel.add(clearanceCb, clearanceCbGbc);

		simulationCb = new JCheckBox("Simulation");
		//simulationCb.setEnabled(false);
		simulationCb.setFont(componentOptionseFont);
		
		simulationCb.addActionListener(DDViewLayer.createViewLayerInstance());
		simulationCb.setActionCommand(EventCodes.SIMU);
		GridBagConstraints simulationCbGbc = new GridBagConstraints();
		// simulationCbGbc.gridwidth = 4;
		// simulationCbGbc.insets = new Insets(0, 0, 5, 5);
		// simulationCbGbc.fill = GridBagConstraints.BOTH;
		simulationCbGbc.gridx = 0;
		simulationCbGbc.gridy = 0;
		// clearanceCbGbc.ipadx = 100
		// caMainScreen.diagramInternalFrame.getContentPane().add(caMainScreen.clearanceCb,simulationCbGbc);

		panel.add(simulationCb, simulationCbGbc);
		diagramInternalFrame.getContentPane().add(panel, clrPanelGbc);
		
	
		simulationUnitButton = new JButton("Y Units");
		simulationUnitButton.setVisible(false);
		simulationUnitButton.setFont(componentOptionseFont);
		simulationUnitButton.addActionListener(DDViewLayer.createViewLayerInstance());
		simulationUnitButton.setActionCommand(EventCodes.SIMUUNIT);
		GridBagConstraints simuUnitBtnGbc = new GridBagConstraints();
		// simulationCbGbc.gridwidth = 4;
		// simulationCbGbc.insets = new Insets(0, 0, 5, 5);
		// simulationCbGbc.fill = GridBagConstraints.BOTH;
		simuUnitBtnGbc.gridx = 0;
		simuUnitBtnGbc.gridy = 1;
		simuUnitBtnGbc.ipadx = 20;
		

		panel.add(simulationUnitButton, simuUnitBtnGbc);
		
		
		simulationUnitTf = new JTextField();
		simulationUnitTf.setVisible(false);
		simulationUnitTf.setFont(componentOptionseFont);
		simulationUnitTf.addActionListener(DDViewLayer.createViewLayerInstance());
		simulationUnitTf.setColumns(10);
		simulationUnitTf.setEditable(false);
		GridBagConstraints simuUnitTfGbc = new GridBagConstraints();
		// simulationCbGbc.gridwidth = 4;
		// simulationCbGbc.insets = new Insets(0, 0, 5, 5);
		// simulationCbGbc.fill = GridBagConstraints.BOTH;
		simuUnitTfGbc.gridx = 1;
		simuUnitTfGbc.gridy = 1;
		simuUnitTfGbc.ipadx = 20;
		// caMainScreen.diagramInternalFrame.getContentPane().add(caMainScreen.clearanceCb,simulationCbGbc);

		panel.add(simulationUnitTf, simuUnitTfGbc);
		
		
		

		
		
		if(analysisType.equals("pkpdlink") || analysisType.equals("irm"))
		{
			pkUnitButton = new JButton("PkUnit");
			pkUnitButton.setFont(componentOptionseFont);
			pkUnitButton.addActionListener(DDViewLayer.createViewLayerInstance());
			pkUnitButton.setActionCommand(EventCodes.PKUNIT);
			GridBagConstraints pkUnitBtnGbc = new GridBagConstraints();
			// simulationCbGbc.gridwidth = 4;
			// simulationCbGbc.insets = new Insets(0, 0, 5, 5);
			// simulationCbGbc.fill = GridBagConstraints.BOTH;
			pkUnitBtnGbc.gridx = 0;
			pkUnitBtnGbc.gridy = 2;
			pkUnitBtnGbc.ipadx = 18;

			panel.add(pkUnitButton, pkUnitBtnGbc);
			
			
			pkUnitTf = new JTextField();
			pkUnitTf.setFont(componentOptionseFont);
			pkUnitTf.addActionListener(DDViewLayer.createViewLayerInstance());
			pkUnitTf.setColumns(10);
			pkUnitTf.setEditable(false);
			GridBagConstraints pkUnitTfGbc = new GridBagConstraints();
			// simulationCbGbc.gridwidth = 4;
			// simulationCbGbc.insets = new Insets(0, 0, 5, 5);
			// simulationCbGbc.fill = GridBagConstraints.BOTH;
			pkUnitTfGbc.gridx = 1;
			pkUnitTfGbc.gridy = 2;
			pkUnitTfGbc.ipadx = 20;

			panel.add(pkUnitTf, pkUnitTfGbc);
			
			
		}
			
			
			
		

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		procInputInst.getModelInputTab1Obj().setIfClearanceParam(false);
		procInputInst.getModelInputTab1Obj().setSimulation(false);
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		//diagramDesktopPane.add(diagramInternalFrame);

		
		libraryModelFormulaPanel = new JPanel();
		// libraryModelFormulaPanel.setBorder(titledBorder);

		// libraryModelFormulaPanel.setLayout(new GridLayout(3, 3));
		libraryModelFormulaPanel.setLayout(new BorderLayout());

		GridBagConstraints liModelFormulaPanelGbc = new GridBagConstraints();
		liModelFormulaPanelGbc.gridwidth = 4;
		liModelFormulaPanelGbc.insets = new Insets(0, 0, 5, 5);
		liModelFormulaPanelGbc.fill = GridBagConstraints.BOTH;
		liModelFormulaPanelGbc.gridx = 0;
		liModelFormulaPanelGbc.gridy = 0;

		// caMainScreen.libraryModelFormulaPanel.add(caMainScreen.imageButton);
		formulaInternalFrame = new JInternalFrame("Formula", false, false,
				false, false);

		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(formulaInternalFrame);

		formulaInternalFrame.setLocation(0, 0);
		formulaInternalFrame
				.setSize(
						(int) (CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame
								.getWidth() / 2.1), (int) (CaOptionsGuiCreator
								.createPdOptionsInstance().optionsInternalFrame
								.getHeight() / 1.6));
		formulaInternalFrame.setLayout(new GridLayout(1, 1));
		formulaInternalFrame.setBorder(viewLayerInst.b);
		formulaInternalFrame.setVisible(true);

		/*
		 * JScrollPane formulaSP = new JScrollPane( libraryModelFormulaPanel,
		 * JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		 * JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 */
		
		
		/*formulaInternalFrame.getContentPane().add(libraryModelFormulaPanel,
				liModelFormulaPanelGbc);
		formulaDesktopPane.add(formulaInternalFrame);*/
		
	
		JScrollPane diagramSP = new JScrollPane(diagramInternalFrame , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		formulaInternalFrame.getContentPane().add(diagramSP);
		
		diagramAndFormulaInternalFrameForCA.add(formulaInternalFrame);
		
		//diagramDesktopPane.add(formulaInternalFrame);
		
		
	}

	private void addImageToFormulaPanel(int modelNumber) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		if(modelNumber>=1)

		if (analysisType.equals("pk")) {
			addImageToAEMFormulaPanel(modelNumber);
			libraryModelFormulaPanel.removeAll();
			libraryModelFormulaPanel.add(formulaImageButton);
			formulaInternalFrame.repaint();
		} else if (analysisType.equals("pd")) {

		} else if (analysisType.equals("mm")) {

		} else if (analysisType.equals("pkpdlink")) {
			addImageToAEMFormulaPanel(modelNumber);
			libraryModelFormulaPanel.removeAll();
			//libraryModelFormulaPanel.add(imageButton);
			formulaInternalFrame.repaint();

		} else if (analysisType.equals("irm")) {
			addImageToAEMFormulaPanel(modelNumber);
			libraryModelFormulaPanel.removeAll();
			//libraryModelFormulaPanel.add(imageButton);
			formulaInternalFrame.repaint();
		}

	}
	
	private void addImageToSecondFormulaPanel(int modelNumber) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		if (analysisType.equals("pk")) {
			addImageToDEMFormulaPanel(modelNumber);
			libraryModelFormulaPanelForDiiffModel.removeAll();
			libraryModelFormulaPanelForDiiffModel.add(imageButtonForDiiffModel,BorderLayout.CENTER);
			formulaInternalFrameForDiffModel.repaint();

		} else if (analysisType.equals("pd")) {

		} else if (analysisType.equals("mm")) {

		} else if (analysisType.equals("pkpdlink")) {
			addImageToSecondFormulaPanelForLinkModel(modelNumber);
			libraryModelFormulaPanelForDiiffModel.removeAll();
			libraryModelFormulaPanelForDiiffModel.add(imageButtonForDiiffModel,BorderLayout.CENTER);
			formulaInternalFrameForDiffModel.repaint();
			formulaInternalFrameForDiffModel.validate();

		} else if (analysisType.equals("irm")) {
			addImageToSecondFormulaPanelForIrm(modelNumber);
			libraryModelFormulaPanelForDiiffModel.removeAll();
			libraryModelFormulaPanelForDiiffModel.add(imageButtonForDiiffModel,BorderLayout.CENTER);
			formulaInternalFrameForDiffModel.repaint();
			formulaInternalFrameForDiffModel.validate();
		}

	}


	private void addImageToSecondFormulaPanelForLinkModel(int modelNumber2) {
		if (modelNumber == 1) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula1.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 2) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula2.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 3) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula3.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 4) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula4.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 5) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula5.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 6) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula6.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 7) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula7.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 8) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		}
		
	}

	private void addImageToSecondFormulaPanelForIrm(int modelNumber2) {
		if (modelNumber == 1) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula1.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 2) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula2.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 3) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula3.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 4) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula4.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} 
		
	}

	private void addImageToDEMFormulaPanel(int modelNumber) {
		if (modelNumber == 1) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula1.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 2) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula2.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 3) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula3.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 4) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula4.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 5) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula5.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 6) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula6.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 7) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula7.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 8) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		}

		else if (modelNumber == 9) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 10) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 11) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 12) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 13) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 14) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 15) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 16) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 17) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 18) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 19) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 20) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		}
	}

	private void addImageToAEMFormulaPanel(int modelNumber) {
		if (modelNumber == 1) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula1.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 2) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula2.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 3) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula3.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 4) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula4.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 5) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula5.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 6) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula6.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 7) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula7.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 8) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		}

		else if (modelNumber == 9) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 10) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 11) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 12) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 13) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 14) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 15) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 16) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 17) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 18) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 19) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "aFormula8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			formulaImageButton = new JButton("", TATAlogoIcon);

		}
	}

	void addImageToSecondDiagramPanel(int modelNumber) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		if (modelNumber >= 1) {
			if (analysisType.equals("pk")) {
				addImageToDEMPanel(modelNumber);
				libraryModelDiagramPanelForDiiffModel.removeAll();
				libraryModelDiagramPanelForDiiffModel
						.add(imageButtonForDiiffModel, BorderLayout.CENTER);
				diagramInternalFrameForDiffModel.repaint();
				libraryModelDiagramPanelForDiiffModel.validate();

			} else if (analysisType.equals("pd")) {
				
			} else if (analysisType.equals("mm")) {

			} else if (analysisType.equals("pkpdlink")) {
				addImageToSecondPanelForLinkModel(modelNumber);
				libraryModelDiagramPanelForDiiffModel.removeAll();
				libraryModelDiagramPanelForDiiffModel
				.add(imageButtonForDiiffModel);
				diagramInternalFrameForDiffModel.repaint();
				libraryModelDiagramPanelForDiiffModel.validate();
			} else if (analysisType.equals("irm")) {
				
				addImageToSecondPanelForIrm(modelNumber);
				libraryModelDiagramPanelForDiiffModel.removeAll();
				libraryModelDiagramPanelForDiiffModel
				.add(imageButtonForDiiffModel);
				diagramInternalFrameForDiffModel.repaint();
				libraryModelDiagramPanelForDiiffModel.validate();
			}

		}

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	private void addImageToSecondPanelForIrm(int modelNumber) {
		if (modelNumber == 1) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "irModel1.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 2) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "irModel2.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 3) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "irModel3.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 4) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "irModel4.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		}
	}

	private void addImageToSecondPanelForLinkModel(int modelNumber) {
		if (modelNumber == 1) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel1.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 2) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel2.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 3) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel3.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 4) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel4.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 5) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel5.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 6) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel6.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 7) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel7.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 8) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} 
	}

	void addImageToDiagramPanel(int modelNumber) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		if (modelNumber >= 1) {
			if (analysisType.equals("pk")) {
				addImageToAEMPanel(modelNumber);
				libraryModelDiagramPanel.removeAll();
				libraryModelDiagramPanel.add(imageButton);
				diagramInternalFrame.repaint();

			} else if (analysisType.equals("pd")) {

				addImageToPdPanel(modelNumber);
				libraryModelDiagramPanel.removeAll();
				libraryModelDiagramPanel.add(imageButton);
				diagramInternalFrame.repaint();
			} else if (analysisType.equals("mm")) {

			} else if (analysisType.equals("pkpdlink")) {
				addImageToAEMPanel(modelNumber);
				libraryModelDiagramPanel.removeAll();
				libraryModelDiagramPanel.add(imageButton);
				diagramInternalFrame.repaint();

			} else if (analysisType.equals("irm")) {
				addImageToAEMPanel(modelNumber);
				libraryModelDiagramPanel.removeAll();
				libraryModelDiagramPanel.add(imageButton);
				diagramInternalFrame.repaint();
			}

		}

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
	}

	private void addImageToPdPanel(int modelNumber) {
		if (modelNumber == 1) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel1.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 2) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel2.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 3) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel3.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 4) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel4.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 5) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel5.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 6) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel6.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 7) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel7.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 8) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "pdmodel8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		}
	}

	private void addImageToDEMPanel(int modelNumber) {
		if (modelNumber == 1) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm1.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 2) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm2.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 3) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm3.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 4) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm4.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 5) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm5.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 6) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm6.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 7) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm7.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 8) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 9) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm9.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 10) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm10.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 11) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm11.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 12) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm12.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 13) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm13.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 14) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm14.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 15) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm15.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 16) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm16.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 17) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm17.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 18) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm18.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 19) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm19.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 19) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "dlm20.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButtonForDiiffModel = new JButton("", TATAlogoIcon);

		}
	}

	private void addImageToAEMPanel(int modelNumber) {
		if (modelNumber == 1) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm1.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 2) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm2.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 3) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm3.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 4) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm4.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 5) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm5.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 6) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm6.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 7) {
			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm7.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);
		} else if (modelNumber == 8) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm8.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 9) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm9.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 10) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm10.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 11) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm11.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 12) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm12.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 13) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm13.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 14) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm14.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 15) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm15.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 16) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm16.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 17) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm17.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 18) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm18.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		} else if (modelNumber == 19) {

			Image TATAlogoImage = ImageLoader.createImageLoaderInstance()
					.getImage(DDViewLayer.class, "lm19.jpg");
			Icon TATAlogoIcon = new ImageIcon(TATAlogoImage);
			imageButton = new JButton("", TATAlogoIcon);

		}
	}

	private JComboBox loadLibModelsForCb(JComboBox cb, String fileLocation) {
		ReadFile2dStrArray RF = new ReadFile2dStrArray(fileLocation);
		int rowCount = RF.rowCount;
		int colCount = RF.colCount;
		String[][] inputMatrix = new String[rowCount][colCount];
		String[] stringArray = new String[rowCount];
		inputMatrix = RF.fileArray;
		stringArray = RF.stringArray;

		for (int i = 0; i < stringArray.length; i++) {
			cb.addItem(inputMatrix[i][0]);
		}

		return cb;
	}

	private JInternalFrame createModelOptionInternalFrame()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		DDViewLayer viewLayerInst = DDViewLayer.createViewLayerInstance();

		JInternalFrame modelOptionsInternalFrame = new JInternalFrame(
				"Weighting", true, true, true, true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(modelOptionsInternalFrame);

		modelOptionsInternalFrame.setLocation(0, 0);
		modelOptionsInternalFrame.setVisible(true);
		modelOptionsInternalFrame.setLayout(new GridLayout(1, 1));
		modelOptionsInternalFrame.setBorder(viewLayerInst.b);

		modelOptionsInternalFrame
				.setSize(
						(int) (CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame
								.getWidth() / 2.3), (int) (CaOptionsGuiCreator
								.createPdOptionsInstance().optionsInternalFrame
								.getHeight() / 2.5));
		return modelOptionsInternalFrame;
	}

	/*
	 * public void actionListenerEventForDifferentialEquationModelRadioButton()
	 * {
	 * 
	 * ParameterAndCorrespondingUnitList paramAndUnitListInst =
	 * ParameterAndCorrespondingUnitList .createParamAndUnitListInstance();
	 * 
	 * if(PdWeightDoseDispGui.createPdWeightDoseInstance().
	 * differentialEquationModelRadioButton.isSelected() == true) {
	 * 
	 * PdWeightDoseDispGui.createPdWeightDoseInstance().
	 * availableDifferentialEquationModelComboBox .setEnabled(true);
	 * PdWeightDoseDispGui
	 * .createPdWeightDoseInstance().availableLibraryModelComboBox
	 * .setEnabled(false);
	 * PDEngineSettingDispGui.createEngineSettingInstance().odeSolverMethodComboBox
	 * .setEnabled(true);
	 * PDEngineSettingDispGui.createEngineSettingInstance().odeSolverMethodLabel
	 * .setEnabled(true);PDEngineSettingDispGui.createEngineSettingInstance().
	 * initialParameterValueByCS.setEnabled(false);
	 * appInfo.getWorkSheetObjectsAL()
	 * .get(appInfo.getSelectedSheetIndex()).getCaInfo()
	 * .getProcessingInputs().getModelInputTab1Obj()
	 * .setDifferentialEquationModel(true);
	 * 
	 * appInfo.getWorkSheetObjectsAL()
	 * .get(appInfo.getSelectedSheetIndex()).getCaInfo()
	 * .getProcessingInputs().getModelInputTab1Obj() .setAlgebraicModel(false);
	 * 
	 * paramAndUnitListInst.createParameterAndCorrespondingUnitList();
	 * 
	 * dosingDispScreen .createInternalGUIForDosing(caMainScreen);
	 * PdInitialEstimateDispGui
	 * .createPdInitEstGui().createInitialParameterValueTable(); } else {
	 * PdWeightDoseDispGui
	 * .createPdWeightDoseInstance().availableDifferentialEquationModelComboBox
	 * .setEnabled(false); appInfo.getWorkSheetObjectsAL()
	 * .get(appInfo.getSelectedSheetIndex()).getCaInfo()
	 * .getProcessingInputs().getModelInputTab1Obj()
	 * .setDifferentialEquationModel(false);
	 * 
	 * } }
	 */

	private void actionListenerEventForAlgebraicEquationModelRadioButton()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ParameterAndUnitListLoader paramAndUnitListInst = ParameterAndUnitListLoader
				.createParamAndUnitListInstance();

		CaEngineSettingDispGuiCreator engSetInst = CaEngineSettingDispGuiCreator
				.createEngineSettingInstance();
		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		if (CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().algebraicEquationModelRadioButton
				.isSelected() == true) {
			CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().availableLibraryModelComboBox
					.setEnabled(true);
			engSetInst.odeSolverMethodComboBox.setEnabled(false);
			engSetInst.odeSolverMethodLabel.setEnabled(false);
			engSetInst.initialParameterValueByCS.setEnabled(true);
			CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().availableDifferentialEquationModelComboBox
					.setEnabled(false);
			if (analysisType.equals("pk"))
				;
			{

				procInputInst.getModelInputTab1Obj().setAlgebraicModel(true);
				procInputInst.getModelInputTab1Obj()
						.setDifferentialEquationModel(false);
			}
			CaMapingHandler.createCaMapHandInst().setProcessingInput(
					procInputInst);

			paramAndUnitListInst.createParameterAndCorrespondingUnitList();

			procInputInst = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();

			CaInitEstimateDispGuiCreator.createCaInitEstGui()
					.createInitialParameterValueTable();
		} else {
			CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().availableLibraryModelComboBox
					.setEnabled(false);
			procInputInst.getModelInputTab1Obj().setAlgebraicModel(false);
			procInputInst = CaMapingHandler.createCaMapHandInst()
					.copyProcessingInput();

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeIntegation.LMDS#
	 * actionListenerEventForAvailableDifferentaiEquationModelComboBox
	 * (Integation.CAMainScreen, Integation.ApplicationInfo)
	 */
	private void actionListenerEventForAvailableDifferentaiEquationModelComboBox()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
		.copyProcessingInput();
		int modelNumber = procInputInst.getModelInputTab1Obj()
		.getModelNumberForCA();
		ParameterAndUnitListLoader paramAndUnitListInst = ParameterAndUnitListLoader
				.createParamAndUnitListInstance();
		
		if (previousLibraryModel != modelNumber) {
			calculateInitialValue = true;
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setSelected(false);
			procInputInst.getModelInputTab2Obj().setInitialParamByCS(0);

			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setSelected(false);
			procInputInst.getModelInputTab2Obj().setInitialParamBYGA(0);

		}
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		/*
		 * appInfo.getWorkSheetObjectsAL().get(appInfo.getSelectedSheetIndex())
		 * .getCaInfo().getProcessingInputs().getModelInputTab1Obj()
		 * .setModelNumberForCA(
		 * PdWeightDoseDispGui.createPdWeightDoseInstance()
		 * .availableDifferentialEquationModelComboBox .getSelectedIndex() + 1);
		 */
		paramAndUnitListInst.createParameterAndCorrespondingUnitList();

		/*
		 * int rowCount =
		 * PdParameterUnitsDispGui.createPdParUnitsDisInst().parameterUnitsTable
		 * .getRowCount(); for (int i = rowCount; i > 0; i--) {
		 * ((DefaultTableModel)
		 * PdParameterUnitsDispGui.createPdParUnitsDisInst().parameterUnitsTable
		 * .getModel()).removeRow(i - 1); }
		 * 
		 * if (appInfo.isRetrievalFlag() == false)
		 * paramAndUnitListInst.unitListForParameters();
		 * 
		 * for (int i = 0; i < 10; i++) { String[] s = new String[3]; String c =
		 * appInfo.getWorkSheetObjectsAL().get(
		 * appInfo.getSelectedSheetIndex()).getCaInfo()
		 * .getProcessingInputs().getParameterUnitsDataObj()
		 * .getParameterbyGroupValueAt(i); s[0] = c; String u =
		 * appInfo.getWorkSheetObjectsAL().get(
		 * appInfo.getSelectedSheetIndex()).getCaInfo()
		 * .getProcessingInputs().getParameterUnitsDataObj()
		 * .getDefaultUnitValueAt(i); String u1 =
		 * appInfo.getWorkSheetObjectsAL().get(
		 * appInfo.getSelectedSheetIndex()).getCaInfo()
		 * .getProcessingInputs().getParameterUnitsDataObj()
		 * .getPreferredUnit(i); s[1] = u;
		 * 
		 * s[2] = u1; appInfo.getWorkSheetObjectsAL()
		 * .get(appInfo.getSelectedSheetIndex()).getCaInfo()
		 * .getProcessingInputs().getParameterUnitsDataObj()
		 * .setPreferredUnit(i, u); appInfo.getWorkSheetObjectsAL()
		 * .get(appInfo.getSelectedSheetIndex()).getCaInfo()
		 * .getProcessingInputs().getParameterUnitsDataObj()
		 * .setDefaultUnitValueAt(i, u); ((DefaultTableModel)
		 * PdParameterUnitsDispGui.createPdParUnitsDisInst().parameterUnitsTable
		 * .getModel()).insertRow(
		 * PdParameterUnitsDispGui.createPdParUnitsDisInst
		 * ().parameterUnitsTable.getRowCount(), s);
		 * 
		 * 
		 * PdParameterUnitsDispGui.createPdParUnitsDisInst().parameterUnitsTable.
		 * isCellEditable(i, 0);
		 * PdParameterUnitsDispGui.createPdParUnitsDisInst(
		 * ).parameterUnitsTable.isCellEditable(i, 1); }
		 */

		if (previousLibraryModel != modelNumber) {
			ifLibraryModelIsChanged = true;
			/*
			 * dosingDispScreen .createInternalGUIForDosing(caMainScreen);
			 * DosingInternalFrame.repaint();
			 */
		} else {
			ifLibraryModelIsChanged = false;
		}

		if (ifLibraryModelIsChanged == true) {

			/*PdInitialEstimateDispGui.createPdInitEstGui().parameterInitialValueInternalFrameForCA
					.repaint();
			PdInitialEstimateDispGui.createPdInitEstGui().parameterInitialValueInternalFrameForCA
					.remove(PdInitialEstimateDispGui.createPdInitEstGui().initialValueScrollPane);
			PdInitialEstimateDispGui.createPdInitEstGui()
					.createInitialParameterValueTable();*/
			
			CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().linkInitParamValueForCAIF
			.remove(CaLinkParamInitEstimateGuiCreator
					.createLinkInitEstGui().initialValueScrollPane);
			CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().numberOfColumnsForInitialValue = 0;
			CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui()
			.createInitialParameterValueTable();

		}
		previousLibraryModel = modelNumber;

		ifLibraryModelIsChanged = false;

	}

	private void actionListenerEventForAvailableLibraryModelComboBox()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		ParameterAndUnitListLoader paramAndUnitListInst = ParameterAndUnitListLoader
				.createParamAndUnitListInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		int modelNumber = 0;
		if (analysisType.equals("pk") || analysisType.equals("pd")
				|| analysisType.equals("mm")) {
			modelNumber = procInputInst.getModelInputTab1Obj()
					.getModelNumberForCA();
		} else if (analysisType.equals("pkpdlink")
				|| analysisType.equals("irm")) {
			modelNumber = procInputInst.getModelInputTab1Obj()
					.getModelNumberForLinkModel();
		}

		if (previousLibraryModelForLink != modelNumber) {
			calculateInitialValue = true;
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setSelected(false);
			procInputInst.getModelInputTab2Obj().setInitialParamByCS(0);

			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setSelected(false);
			procInputInst.getModelInputTab2Obj().setInitialParamBYGA(0);

		}
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		if (previousLibraryModelForLink != modelNumber) {
			ifSecondLibraryModelIsChanged = true;

			if (analysisType.equals("pk") || analysisType.equals("pkpdlink") || analysisType.equals("mm")
					|| analysisType.equals("irm"))
				CaDosingDispGuiCreator.createCaDosingDispGui()
						.createInternalGUIForDosing();
			CaDosingDispGuiCreator.createCaDosingDispGui().DosingInternalFrame
					.repaint();
		} else {
			ifSecondLibraryModelIsChanged = false;
		}

		if (ifSecondLibraryModelIsChanged == true) {

			if (analysisType.equals("pk") || analysisType.equals("pd")
					|| analysisType.equals("mm")) {
				/*
				 * PdInitialEstimateDispGui.createPdInitEstGui().parameterInitialValueInternalFrameForCA
				 * .repaint();
				 */
				CaInitEstimateDispGuiCreator.createCaInitEstGui().parameterInitialValueInternalFrameForCA
						.remove(CaInitEstimateDispGuiCreator.createCaInitEstGui().initialValueScrollPane);
				CaInitEstimateDispGuiCreator.createCaInitEstGui().numberOfColumnsForInitialValue = 0;
				CaInitEstimateDispGuiCreator.createCaInitEstGui()
						.createInitialParameterValueTable();

			} else if (analysisType.equals("pkpdlink")
					|| analysisType.equals("irm")) {
				/*PdLinkParamInitEstimateGui.createLinkInitEstGui().linkParameterInitialValueInternalFrameForCA
						.remove(PdLinkParamInitEstimateGui
								.createLinkInitEstGui().initialValueScrollPane);
				PdLinkParamInitEstimateGui.createLinkInitEstGui().numberOfColumnsForInitialValue = 0;
				PdLinkParamInitEstimateGui.createLinkInitEstGui()
						.createInitialParameterValueTable();*/
				
				
				CaInitEstimateDispGuiCreator.createCaInitEstGui().parameterInitialValueInternalFrameForCA
				.remove(CaInitEstimateDispGuiCreator.createCaInitEstGui().initialValueScrollPane);
				CaInitEstimateDispGuiCreator.createCaInitEstGui().numberOfColumnsForInitialValue = 0;
				CaInitEstimateDispGuiCreator.createCaInitEstGui()
				.createInitialParameterValueTable();

			}
		}

		if (analysisType.equals("pk") || analysisType.equals("pd")
				|| analysisType.equals("mm")) {
			previousLibraryModelForLink = procInputInst.getModelInputTab1Obj()
					.getModelNumberForCA();
		} else if (analysisType.equals("pkpdlink")
				|| analysisType.equals("irm")) {
			previousLibraryModelForLink = procInputInst.getModelInputTab1Obj()
					.getModelNumberForLinkModel();
		}

		ifSecondLibraryModelIsChanged = false;

	}

	private String[][] readFile(String fileLocation) {
		ReadFile2dStrArray RF = new ReadFile2dStrArray(fileLocation);
		int rowCount = RF.rowCount;
		int colCount = RF.colCount;

		System.out.println("rowCount.." + rowCount);
		System.out.println("colCount.." + colCount);

		String[][] inputMatrix = new String[rowCount][colCount];
		String[] stringArray = new String[rowCount];
		inputMatrix = RF.fileArray;
		stringArray = RF.stringArray;

		return inputMatrix;
	}

}
