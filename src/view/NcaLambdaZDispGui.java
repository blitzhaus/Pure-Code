package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class NcaLambdaZDispGui implements EventCodes{


	JInternalFrame LambdaZSelectorInternalFrame;
	JDesktopPane lambdaZInternalFrameDesktopPane;
	JInternalFrame lambdaZOptionsInternalFrame;
	JRadioButton logViewRadioButton;
	JRadioButton linearViewRadioButton;
	JComboBox lambdaZCalculationCombo;
	JLabel startTimeLable;
	JTextField startTimeForLambdaZTextField;
	//int ifLambdaZScreenIsCalledFirstTime = 0;
	//boolean isTimeRangeSelected = false;
	JTextField endTimeForLambdaZTextField;
	ArrayList<JInternalFrame> lambdaZNormalGraphsInternalFramesArrayList;
	ArrayList<JInternalFrame> lambdaZLogarithmicGraphsInternalFramesArrayList;
	ArrayList<Double> observedX = new ArrayList<Double>();
	ArrayList<Double> observedY = new ArrayList<Double>();
	ArrayList<String> profileNamesUsedForProfileNumberDetermination = new ArrayList<String>();
	JLabel endTimelable;
	JInternalFrame LambdaZplotInternalFrame;
	JDesktopPane lambdaZplotInternalFrameDesktopPane;
	JInternalFrame profileIF;
	JTable profileTable;
	JScrollPane profileTableScrollpane;
	boolean profileGraphGenerated;
	boolean entryIsWhileCreatingTheGUI = true;
	boolean isFromSlopeScreen;
	
	
	LambdaZDetails[] lambdaZdetInst;
	lambdazData lambdaZDataInst;
	NCAoutputPlots ncaOutplotsInst;
	
	protected void updateProfile() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ncaOutplotsInst = 	NCAoutputPlots
		.createNcaoutputPlotsInst();
		lambdaZdetInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getNcaInfo().getProcessingInputs()
				.getLambdazDataobj().getLambdaZDetails();
		lambdaZDataInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
		.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL()
		.get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex())
		.getNcaInfo()
		.getProcessingInputs()
		.getLambdazDataobj();
		
		LambdaZCalculationBeforeNCA lzbNCA = DisplayContents
				.createInstanceOflzBeforeNCA();
		
		/*ifLambdaZScreenIsCalledFirstTime = ifLambdaZScreenIsCalledFirstTime + 1;*/
		
		int selectedProfileNumber = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
		.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL()
		.get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex())
		.getNcaInfo()
		.getProcessingInputs()
		.getLambdazDataobj().getProfileSelected();
		
		lzbNCA.methodNumberForAllProfile[selectedProfileNumber] = lambdaZCalculationCombo
				.getSelectedIndex();

		try {
			lzbNCA
					.calculateLambdaZBeforeNCAUsingBestFit(
							selectedProfileNumber,
							lzbNCA.methodNumberForAllProfile[selectedProfileNumber],
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
									.getStartTime(),
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
									.getEndTime(),
							lzbNCA.infusionTimeForAllProfile[selectedProfileNumber]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lambdaZNormalGraphsInternalFramesArrayList = new ArrayList<JInternalFrame>();
		lambdaZLogarithmicGraphsInternalFramesArrayList = new ArrayList<JInternalFrame>();
		LambdaZCalculationBeforeNCA lamZBefNca = DisplayContents.createInstanceOflzBeforeNCA();
		double[] predictedY = lamZBefNca.predictedConc;
		
		predictedY = addZeroConcentrations(predictedY, selectedProfileNumber);
		
		NCAoutputPlots
				.createNcaoutputPlotsInst()
				.generateLambdaZplotForTheProfileClicked(
						observedX,
						observedY,
						predictedY,
						profileNamesUsedForProfileNumberDetermination
								.get(selectedProfileNumber), selectedProfileNumber);
		profileGraphGenerated = true;
		if (linearViewRadioButton.isSelected() == true) { 
			lambdaZNormalGraphsInternalFramesArrayList.get(0).moveToFront();

		} else if (logViewRadioButton.isSelected() == true) {	
			lambdaZLogarithmicGraphsInternalFramesArrayList.get(0)
					.moveToFront();  
		}

	}


	private double[] addZeroConcentrations(double[] predictedY, int selectedProfNumber) {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Hashtable<Integer, Boolean> hashTable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getNcaInfo().getProcessingInputs()
				.getLambdazDataobj().getLambdaZDetails()[selectedProfNumber].getInclusionExcusionPoints();
		
		predictedY = addPoints(hashTable, predictedY);
		
		return predictedY;
	}

	
	private double[] addPoints(Hashtable<Integer, Boolean> hashTable,
			double[] PredY) {
		ArrayList<Double> newPredY = new ArrayList<Double>();
		ArrayList<Double> predYY = new ArrayList<Double>();
		for(int i=0;i<PredY.length;i++){
			predYY.add(PredY[i]);
		}

		for (int i = 0; i < observedX.size(); i++) {
			if (hashTable.get(i) == true) {
				
			} else if (hashTable.get(i) == false) {
				predYY.add(i, 0.0);
			}
		}

		double[] newPredYY = new double[predYY.size()];
		for (int i = 0; i < newPredYY.length; i++) {
			newPredYY[i] = predYY.get(i);
		}

		return newPredYY;

	}
	

	private final class ProfileTableSelectionHandler implements TableModelListener {
	

		@Override
		public void tableChanged(TableModelEvent arg0) {

			int rowClicked = profileTable.getRowCount();
			int columnClicked = profileTable.getColumnCount();
			
			
			if(columnClicked == 2){
				
				if((Boolean)profileTable.getValueAt(rowClicked, 2) == true){
					profileTable.setValueAt(false, rowClicked, 2);
					
					//setting the start time into the text field
					for(int i=0;i<profileTable.getRowCount();i++){
						
							if((Boolean)profileTable.getValueAt(i, 2) == true){
								startTimeForLambdaZTextField.setText(observedX.get(i)+"");
								break;
							}
						
						
					}
					
					//setting the end time into the text field
					for(int i= profileTable.getRowCount()-1;i>=0;i--){
						if((Boolean)profileTable.getValueAt(i, 2) == true){
							endTimeForLambdaZTextField.setText(observedX.get(i)+"");
							break;
						}
					}
					
				} else{
					profileTable.setValueAt(true, rowClicked, 2);
					//setting the start time into the text field
					for(int i=0;i<profileTable.getRowCount();i++){
						
							if((Boolean)profileTable.getValueAt(i, 2) == true){
								startTimeForLambdaZTextField.setText(observedX.get(i)+"");
								break;
							}
						
						
					}
					
					//setting the end time into the text field
					for(int i= profileTable.getRowCount()-1;i>=0;i--){
						if((Boolean)profileTable.getValueAt(i, 2) == true){
							endTimeForLambdaZTextField.setText(observedX.get(i)+"");
							break;
						}
					}
				}
			}
			
		
			
		}
	}

	
	public NcaLambdaZDispGui(String dummy){
		
	}
	
	public static NcaLambdaZDispGui NCA_LANB_DISP = null;

	public static NcaLambdaZDispGui createLambDispGuiInstance()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NCA_LANB_DISP == null) {
			NCA_LANB_DISP = new NcaLambdaZDispGui("just object creation");
		}
		return NCA_LANB_DISP;
	}

	
	public void NcaLambdaZDispGuiCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		LambdaZSelectorInternalFrame();
	}
	
	private void LambdaZSelectorInternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {

		LambdaZSelectorInternalFrame = new JInternalFrame("slope Selector",
				false, false, false, false);
		LambdaZSelectorInternalFrame
				.setLocation(
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ NcaSetupAvailableComp
										.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		LambdaZSelectorInternalFrame
				.setSize(
						NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
								.getWidth()
								- NcaSetupAvailableComp
										.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getHeight());
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(LambdaZSelectorInternalFrame);
		lambdaZInternalFrameDesktopPane = new JDesktopPane();
		LambdaZSelectorInternalFrame
				.setContentPane(lambdaZInternalFrameDesktopPane);
		LambdaZSelectorInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		NcaTabbedPanes.createNcaTabbedPaneInstance().setupTabDesktopPane
				.add(LambdaZSelectorInternalFrame);
		lambdaZOptionsInternalFrame = new JInternalFrame("Options", false,
				false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(lambdaZOptionsInternalFrame);
		lambdaZOptionsInternalFrame.setLocation(0, 0);
		lambdaZOptionsInternalFrame.setSize(LambdaZSelectorInternalFrame
				.getWidth(), LambdaZSelectorInternalFrame.getHeight() / 8);
		lambdaZOptionsInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		lambdaZOptionsInternalFrame.setVisible(true);
		lambdaZInternalFrameDesktopPane.add(lambdaZOptionsInternalFrame);
		LambdaZSelectorInternalFrame.setVisible(true);
		lambdaZOptionsInternalFrame.setLayout(new FlowLayout());

		// determining the number of profiles in the data
		int numberOfTab = DetermineNumberOfSubject.createDetNoSubInst().no_subject;

		lambdaZOptionsInternalFrame.setSize(lambdaZOptionsInternalFrame
				.getSize());

		// creating the options
		NCAMainScreen.createNcaMainScreenInstance().titledBorder = BorderFactory
				.createTitledBorder(
						null,
						"View ",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION,
						NCAMainScreen.createNcaMainScreenInstance().componentLablesFont,
						Color.black);
		JPanel viewPanel = new JPanel();
		// viewPanel.setBorder(titledBorder);
		/*
		 * JLabel viewLable = new JLabel("View");
		 * viewLable.setFont(componentLablesFont); viewPanel.add(viewLable);
		 */
		logViewRadioButton = new JRadioButton("Log");
		logViewRadioButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		logViewRadioButton.setActionCommand(LOGVIEW);
		logViewRadioButton.setSelected(true);
		logViewRadioButton
				.setFont(NCAMainScreen.createNcaMainScreenInstance().componentLablesFont);
		viewPanel.add(logViewRadioButton);

		linearViewRadioButton = new JRadioButton("Lin");
		linearViewRadioButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		linearViewRadioButton.setActionCommand(LINEARVIEW);
		linearViewRadioButton.setFont(NCAMainScreen
				.createNcaMainScreenInstance().componentLablesFont);
		viewPanel.add(linearViewRadioButton);

		ButtonGroup viewtypeButtonGroup = new ButtonGroup();
		viewtypeButtonGroup.add(linearViewRadioButton);
		viewtypeButtonGroup.add(logViewRadioButton);

		lambdaZOptionsInternalFrame.add(viewPanel);

		NCAMainScreen.createNcaMainScreenInstance().titledBorder = BorderFactory
				.createTitledBorder(
						null,
						"Calculation method",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION,
						NCAMainScreen.createNcaMainScreenInstance().componentLablesFont,
						Color.black);
		JPanel lambdazoptionsPanel = new JPanel();
		// lambdazoptionsPanel.setBorder(titledBorder);

		JLabel calculationMethodLable = new JLabel("Calculation Method");
		lambdazoptionsPanel.add(calculationMethodLable);

		lambdaZCalculationCombo = new JComboBox();
		lambdaZCalculationCombo.setFont(NCAMainScreen
				.createNcaMainScreenInstance().componentOptionseFont);
		lambdaZCalculationCombo.setBackground(Color.white);

		lambdaZCalculationCombo.addItem("Best Fit-ARS");
		lambdaZCalculationCombo.addItem("Best Fit-TTT");
		lambdaZCalculationCombo.addItem("Best Fit-ARS+TTT");
		lambdaZCalculationCombo.addItem("Time Range");
		lambdazoptionsPanel.add(lambdaZCalculationCombo);
		lambdaZCalculationCombo.addActionListener(DDViewLayer
				.createViewLayerInstance());
		lambdaZCalculationCombo.setActionCommand(LAMBDAZMETHOD);

		lambdaZOptionsInternalFrame.add(lambdazoptionsPanel);
		LambdaZplotInternalFrame = new JInternalFrame("plot", false, false,
				false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(LambdaZplotInternalFrame);
		LambdaZplotInternalFrame
				.setBorder(DDViewLayer.createViewLayerInstance().b);
		lambdaZplotInternalFrameDesktopPane = new JDesktopPane();
		LambdaZplotInternalFrame
				.setContentPane(lambdaZplotInternalFrameDesktopPane);
		lambdaZInternalFrameDesktopPane.setBackground(LambdaZplotInternalFrame
				.getBackground());
		LambdaZplotInternalFrame.setLocation(
				lambdaZOptionsInternalFrame.getX(), lambdaZOptionsInternalFrame
						.getY()
						+ lambdaZOptionsInternalFrame.getHeight());
		LambdaZplotInternalFrame.setSize(
				lambdaZOptionsInternalFrame.getWidth(),
				LambdaZSelectorInternalFrame.getHeight()
						- lambdaZOptionsInternalFrame.getHeight());
		LambdaZplotInternalFrame.setVisible(true);
		lambdaZInternalFrameDesktopPane.add(LambdaZplotInternalFrame);

		// profile display internal frame
		profileIF = new JInternalFrame("Profile ", false, false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(profileIF);
		profileIF.setBorder(DDViewLayer.createViewLayerInstance().b);

		double width = LambdaZplotInternalFrame.getWidth() * (2.0 / 3);
		profileIF.setLocation(LambdaZplotInternalFrame.getX() + (int) width, 0);
		profileIF.setSize(LambdaZplotInternalFrame.getWidth() - (int) width,
				LambdaZplotInternalFrame.getHeight());
		profileIF.setVisible(true);
		lambdaZplotInternalFrameDesktopPane.add(profileIF);
		profileIF.moveToFront();

		// add a row to the jtable so that we can add rows for the respective
		// profile when clicked
		profileTable = new JTable(0, 3);
		profileTable.setRowSelectionAllowed(true);
		profileTable.setColumnSelectionAllowed(true);
		profileTable.setSelectionBackground(new Color(238, 238, 234));

		profileTable.setBackground(Color.white);
		profileTableScrollpane = new JScrollPane(profileTable);
		profileIF.getContentPane().add(profileTableScrollpane);

		Object data[][] = { { "", "", true }, { "", "", true },
				{ "", "", true } };
		Object col[] = { "", "", "" };
		DefaultTableModel model = new DefaultTableModel(data, col);
		profileTable = new JTable(model) {

			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				default:
					return Boolean.class;
				}
			}
		};
	}
}
