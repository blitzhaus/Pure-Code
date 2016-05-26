package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.JinternalFrameFunctions;

public class NPSOpt {

	private final class NthDoseNPSTextDocumentHandler implements
			DocumentListener {
		@Override
		public void removeUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppIndo();
		}

		private void updateAppIndo() {
			
			
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst().copyProcessingInput();
					
			
			String previousNoOfDose = procInputInst.getModleInputForNPSObj().getNumberOfDoseBeforeDisplay(); 
			
			procInputInst.getModleInputForNPSObj()
					.setNumberOfDoseBeforeDisplay(
							noOfDoseInNPSTextField.getText());
			NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);
			
			if(procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 1)
			{
				if(previousNoOfDose.equals(noOfDoseInNPSTextField.getText()))
				{
					
				}else
				{
					int noOfDose;
					try
					{
						 noOfDose = Integer.parseInt(noOfDoseInNPSTextField.getText());
					}catch (Exception e) {
						noOfDose = 0;
					}
				
					
					if(noOfDose>1)
					{
						try {
							NPSDosingTableCreation.createNpsDosingGuiInst().createDosingInternalFrame();
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
					
				}
			}
			
			
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppIndo();
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppIndo();
		}
	}

	private final class DisplayAtNthDoseRadioActionHandler implements
			ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getModleInputForNPSObj()
					.setIfDisplayAfterNthDose(true);
			noOfDoseInNPSTextField.setEnabled(true);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getModleInputForNPSObj()
					.setIfDisplayAtSteadyState(false);

		}
	}

	private final class DisplayAtSteadyStateRadioActionHandler implements
			ActionListener {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		public void actionPerformed(ActionEvent arg0) {

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getModleInputForNPSObj()
					.setIfDisplayAfterNthDose(false);
			noOfDoseInNPSTextField.setEnabled(false);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getModleInputForNPSObj()
					.setIfDisplayAtSteadyState(true);

		}
	}

	private final class TauInTextFieldDocumentHandler implements
			DocumentListener {
		@Override
		public void removeUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppInfo();
		}

		private void updateAppInfo() {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			// TODO Auto-generated method stub
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getModleInputForNPSObj()
					.setTauValueInNPS(tauInNPSTextField.getText());
			
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getModleInputForNPSObj()
					.setNoOfOutputPoints(NoOfDataOutputPointsTextField.getText());
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppInfo();
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppInfo();
		}
	}

	private final class MaintainanceTextDocumentHandler implements
			DocumentListener {
		@Override
		public void removeUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppIndo();
		}

		private void updateAppIndo() {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst().copyProcessingInput();
			
			if(procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 0)
			{
				procInputInst.getModleInputForNPSObj()
				.setMaintenanceDose(maintenanceDoseTextField.getText());
			}else
			{
				procInputInst.getModleInputForNPSObj().setEndTime(maintenanceDoseTextField.getText());
			}
			
			NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);

		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppIndo();
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppIndo();
		}
	}

	private final class LoadingDoseTextDocumentHandler implements
			DocumentListener {
		@Override
		public void removeUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppInfo();

		}

		private void updateAppInfo() {
			
			ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst().copyProcessingInput();
			
			if(procInputInst.getModleInputForNPSObj().getDosingTypeForNPS() == 0)
			{
				procInputInst.getModleInputForNPSObj()
				.setLoadingDose(loadingDoseTextField.getText());
			}else
			{
				procInputInst.getModleInputForNPSObj().setStartTime(loadingDoseTextField.getText());
			}
			
			NPSHandlers.createNPSHandlersInst().setProcessingInput(procInputInst);
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppInfo();
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			updateAppInfo();
		}
	}

	private final class ComputationsMethodComboActionHandler implements
			ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getModleInputForNPSObj()
					.setMethodForComputation(
							methodForComputationsComboBox.getSelectedIndex());
		}
	}

	private final class NoDataOutputPointsFocusHandler implements FocusListener {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusGained(FocusEvent arg0) {

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getModleInputForNPSObj()
					.setNoOfOutputPoints(
							NoOfDataOutputPointsTextField.getText());

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getModleInputForNPSObj()
					.setLoadingDose(loadingDoseTextField.getText());

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getModleInputForNPSObj()
					.setMaintenanceDose(maintenanceDoseTextField.getText());

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getModleInputForNPSObj()
					.setTauValueInNPS(tauInNPSTextField.getText());

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNpsInfo()
					.getProcessingInputs().getModleInputForNPSObj()
					.setNumberOfDoseBeforeDisplay(
							noOfDoseInNPSTextField.getText());

		}
	}

	private final class DosingTypeComboHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			appInfo
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
							.getSelectedSheetIndex()).getNpsInfo().getProcessingInputs()
					.getModleInputForNPSObj()
					.setDosingTypeForNPS(
							dosingTypeComboBox.getSelectedIndex());
			
			if( dosingTypeComboBox.getSelectedIndex() == 1)
			{
				if(NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree.getRowCount() == 4)
				{
					
					DefaultTreeModel model = (DefaultTreeModel) NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree
					.getModel();
				
					DefaultMutableTreeNode mainComponent = new DefaultMutableTreeNode(
					"Maintenance Dosing");
					
					NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponents.add(mainComponent);
				/*//	NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponents.r
					NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree = new JTree(NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponents);
					NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree.repaint();*/
					
					model.reload();
				}
				
				loadingDoseLabel.setText("Output Start Time");
				maintenanceDoseLabel.setText("Output End Time");
				displayAtSteadyStateRadioButton.setVisible(false);
				tauInNPSTextField.setVisible(false);
				tauLabelInNPS.setVisible(false);
				loadingDoseTextField.setText("");
				maintenanceDoseTextField.setText("");
						
			
			}
			else
			{
				loadingDoseLabel.setText("Loading Dose");
				maintenanceDoseLabel.setText("mainTenance Dose");
				displayAtSteadyStateRadioButton.setVisible(true);
				displayAtSteadyStateRadioButton.setSelected(false);
				tauInNPSTextField.setVisible(true);
				tauLabelInNPS.setVisible(true);
				loadingDoseTextField.setText("");
				maintenanceDoseTextField.setText("");
				
				if(NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree.getRowCount() 
						/*NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponents.getChildCount()*/ >= 4)
				{
					DefaultTreeModel model = (DefaultTreeModel) NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree
					.getModel();
				
					//NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponents.getChildCount();
				
					if(NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponents.getChildCount()> 3)
					NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponents.remove(3);
				
					model.reload();
				}
			}
		}
	}

	public static NPSOpt NPS_OPT = null;

	public static NPSOpt createNPSOptInst() {

		if (NPS_OPT == null) {
			NPS_OPT = new NPSOpt("just object creation");
		}
		return NPS_OPT;
	}

	public NPSOpt(String dummy) {

	}

	JInternalFrame optionsInternalFrame;
	JTabbedPane moduleTabs;
	JDesktopPane optionsTabDesktopPane;
	JInternalFrame modelSelectionInternalFrame;
	JComboBox dosingTypeComboBox;
	JLabel NoOfDataOutputPoints;
	JTextField NoOfDataOutputPointsTextField;
	JComboBox methodForComputationsComboBox;
	JTextField loadingDoseTextField;
	JTextField tauInNPSTextField;
	JTextField maintenanceDoseTextField;
	JTextField noOfDoseInNPSTextField;
	JRadioButton displayAtSteadyStateRadioButton;
	JRadioButton displayAtNthdoseRadioButton;
	JLabel viewLable;
	JComboBox viewsCombo;
	JLabel maintenanceDoseLabel;
	JLabel loadingDoseLabel;
	JLabel tauLabelInNPS;

	void createOptionsInternalFrame() {
		// TODO Auto-generated method stub

		optionsInternalFrame = new JInternalFrame("options ", false, false,
				false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(optionsInternalFrame);
		optionsInternalFrame
				.setLocation(
						NPSMainPage.createNPSMainPageInst().npsMainInternalFrame
								.getX(),
						NPSMainPage.createNPSMainPageInst().npsMainInternalFrame
								.getY()
								+ NPSMainPage.createNPSMainPageInst().npsMainInternalFrame
										.getHeight());
		optionsInternalFrame.setSize(
				NPSMainPage.createNPSMainPageInst().npsMainInternalFrame
						.getWidth(),
				DDViewLayer.createMTInstance().mainTabbedFrame.getHeight() / 2);
		optionsInternalFrame.setVisible(true);
		optionsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane
				.add(optionsInternalFrame);
		moduleTabs = new JTabbedPane();
		optionsTabDesktopPane = new JDesktopPane();
		moduleTabs.addTab("Options", optionsTabDesktopPane);
		optionsInternalFrame.getContentPane().add(moduleTabs);
		createModelSelectionInternalFrame();
		optionsInternalFrame.moveToFront();
	}

	private void createModelSelectionInternalFrame() {

		modelSelectionInternalFrame = new JInternalFrame("Model Selection",
				false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(modelSelectionInternalFrame);

		modelSelectionInternalFrame.setLocation(0, 0);
		int width = (int) (optionsInternalFrame.getWidth());
		modelSelectionInternalFrame.setSize(width, optionsInternalFrame
				.getHeight());
		modelSelectionInternalFrame.setVisible(true);

		modelSelectionInternalFrame.setLayout(new GridLayout(2, 2));
		modelSelectionInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);

		createInternalGuiModelSelection();
		optionsTabDesktopPane.add(modelSelectionInternalFrame);
		modelSelectionInternalFrame.moveToFront();

	}

	private void createInternalGuiModelSelection() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Font componentOptionseFont = new Font("Arial", Font.BOLD, 10);
		Font componentLablesFont = new Font("Arial", Font.BOLD, 10);

		JPanel modelTypePanel = new JPanel();
		modelTypePanel.setFont(componentOptionseFont);

		modelTypePanel.setLayout(new GridLayout(3, 2));

		JLabel dosingTypeLabel = new JLabel("Dosing Type");
		modelTypePanel.add(dosingTypeLabel);

		dosingTypeComboBox = new JComboBox();
		dosingTypeComboBox.addItem("Regular");
		dosingTypeComboBox.addItem("Variable");

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getModleInputForNPSObj()
				.setDosingTypeForNPS(0);

		dosingTypeComboBox.addActionListener(new DosingTypeComboHandler());
		modelTypePanel.add(dosingTypeComboBox);

		NoOfDataOutputPoints = new JLabel("Number of Data Output Points");
		modelTypePanel.add(NoOfDataOutputPoints);

		NoOfDataOutputPointsTextField = new JTextField(1);
		NoOfDataOutputPointsTextField.setText("50");
		NoOfDataOutputPointsTextField.getDocument().addDocumentListener
				(new TauInTextFieldDocumentHandler());

		modelTypePanel.add(NoOfDataOutputPointsTextField);

		JLabel methodForComputationsLabel = new JLabel(
				"Method for Computations");
		modelTypePanel.add(methodForComputationsLabel);

		methodForComputationsComboBox = new JComboBox();
		methodForComputationsComboBox.addItem("Linear");
		methodForComputationsComboBox.addItem("Log Linear");

		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNpsInfo()
				.getProcessingInputs().getModleInputForNPSObj()
				.setMethodForComputation(0);

		methodForComputationsComboBox
				.addActionListener(new ComputationsMethodComboActionHandler());

		modelTypePanel.add(methodForComputationsComboBox);

		modelSelectionInternalFrame.add(modelTypePanel);

		JPanel modelTypePanel1 = new JPanel();
		modelTypePanel1.setFont(componentOptionseFont);

		modelTypePanel1.setLayout(new GridLayout(3, 2));

		loadingDoseLabel = new JLabel("Loading Dose");
		modelTypePanel1.add(loadingDoseLabel);

		loadingDoseTextField = new JTextField(1);
		loadingDoseTextField.getDocument().addDocumentListener(
				new LoadingDoseTextDocumentHandler());
		modelTypePanel1.add(loadingDoseTextField);

		maintenanceDoseLabel = new JLabel("Maintenance Dose");
		modelTypePanel1.add(maintenanceDoseLabel);

		maintenanceDoseTextField = new JTextField(1);
		maintenanceDoseTextField.getDocument().addDocumentListener(
				new MaintainanceTextDocumentHandler());
		modelTypePanel1.add(maintenanceDoseTextField);

		tauLabelInNPS = new JLabel("Tau");
		modelTypePanel1.add(tauLabelInNPS);

		tauInNPSTextField = new JTextField(10);
		tauInNPSTextField.getDocument().addDocumentListener(
				new TauInTextFieldDocumentHandler());
		modelTypePanel1.add(tauInNPSTextField);

		modelSelectionInternalFrame.add(modelTypePanel1);

		JPanel modelTypePanel2 = new JPanel();
		modelTypePanel2.setFont(componentOptionseFont);

		modelTypePanel2.setLayout(new GridLayout(3, 2));

		displayAtSteadyStateRadioButton = new JRadioButton(
				"Display at Steady State");
		displayAtSteadyStateRadioButton.setSelected(true);

		displayAtSteadyStateRadioButton
				.addActionListener(new DisplayAtSteadyStateRadioActionHandler());
		modelTypePanel2.add(displayAtSteadyStateRadioButton);

		JRadioButton displayAtSteadyStateRadioButton1 = new JRadioButton("");
		displayAtSteadyStateRadioButton1.setVisible(false);
		modelTypePanel2.add(displayAtSteadyStateRadioButton1);

		displayAtNthdoseRadioButton = new JRadioButton(
				"Number of dose before Display ");
		displayAtNthdoseRadioButton
				.addActionListener(new DisplayAtNthDoseRadioActionHandler());
		modelTypePanel2.add(displayAtNthdoseRadioButton);

		ButtonGroup bp = new ButtonGroup();
		bp.add(displayAtSteadyStateRadioButton);
		bp.add(displayAtNthdoseRadioButton);

		noOfDoseInNPSTextField = new JTextField(1);
		noOfDoseInNPSTextField.setEnabled(false);
		noOfDoseInNPSTextField.getDocument().addDocumentListener(
				new NthDoseNPSTextDocumentHandler());
		modelTypePanel2.add(noOfDoseInNPSTextField);

		modelSelectionInternalFrame.add(modelTypePanel2);

		JPanel plotTypePanel = new JPanel();
		plotTypePanel.setFont(componentOptionseFont);

		plotTypePanel.setLayout(new GridLayout(3, 2));

		JLabel view1 = new JLabel();
		view1.setVisible(false);
		plotTypePanel.add(view1);

		JLabel view2 = new JLabel();
		view2.setVisible(false);
		plotTypePanel.add(view2);

		viewLable = new JLabel(" Plot View");
		viewLable.setEnabled(false);
		plotTypePanel.add(viewLable);

		viewsCombo = new JComboBox();
		viewsCombo.setEnabled(false);
		viewsCombo.addActionListener(DDViewLayer.createViewLayerInstance());
		viewsCombo.addItem("Lin");
		viewsCombo.addItem("Log");
		plotTypePanel.add(viewsCombo);

		JLabel view3 = new JLabel();
		view3.setVisible(false);
		plotTypePanel.add(view3);

		JLabel view4 = new JLabel();
		view4.setVisible(false);
		plotTypePanel.add(view4);

		modelSelectionInternalFrame.add(plotTypePanel);

	}

}
