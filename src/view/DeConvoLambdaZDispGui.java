package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class DeConvoLambdaZDispGui implements EventCodes{
	
	


	JInternalFrame lambdaZSelectorInternalFrame;
	JDesktopPane lambdaZInternalFrameDesktopPane;
	JInternalFrame lambdaZOptionsInternalFrame;
	JRadioButton logViewRadioButton;
	JRadioButton linearViewRadioButton;
	JComboBox lambdaZCalculationCombo;
	JLabel startTimeLable;
	
	ArrayList<JInternalFrame> lambdaZNormalGraphsInternalFramesArrayList;
	ArrayList<JInternalFrame> lambdaZLogarithmicGraphsInternalFramesArrayList;
	ArrayList<Double> observedX = new ArrayList<Double>();
	ArrayList<Double> observedY = new ArrayList<Double>();
	ArrayList<String> profileNamesUsedForProfileNumberDetermination = new ArrayList<String>();
	JLabel endTimelable;
	JInternalFrame LambdaZplotInternalFrame;
	JDesktopPane lambdaZplotInternalFrameDesktopPane;
	JInternalFrame profileIF;
	
	JScrollPane profileTableScrollpane;
	boolean profileGraphGenerated;
	boolean entryIsWhileCreatingTheGUI = true;
	boolean isFromSlopeScreen;
	
	
	LambdaZDetails[] lambdaZdetInst;
	lambdazData lambdaZDataInst;
	DeConvoOutputPlots deConvoOutplotsInst;
	
	
	JRadioButton usePredAucInfRB;
	JRadioButton useObsAucInfRB;
	JRadioButton useGivenAucInfRB;
	JTextField userGivenAucInf;
	
	JRadioButton userGivenTimeRangeRB;
	JRadioButton userGivenLamZAndIntRB;
	JTextField userGivenLamZTF;
	JTextField userGivenInterceptTF;
	JTextField startTimeLamZTF;
	JTextField endTimeLamZTF;
	
	protected void updateProfile() throws RowsExceededException,
			WriteException, BiffException, IOException {

				
		deConvoOutplotsInst = 	DeConvoOutputPlots.createDeConvoOutputPlotsInst();
		
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		lambdaZdetInst = procInputInst
				.getLambdazDataobj().getLambdaZDetails();
		lambdaZDataInst = procInputInst
		.getLambdazDataobj();
		
		LambdaZCalculationBeforeNCA lzbNCA = DisplayContents
				.createInstanceOflzBeforeNCA();
		
		
		
		int selectedProfileNumber = procInputInst
		.getLambdazDataobj().getProfileSelected();
		
		lzbNCA.methodNumberForAllProfile[selectedProfileNumber] = lambdaZCalculationCombo
				.getSelectedIndex();

		try {
			lzbNCA
					.calculateLambdaZBeforeNCAUsingBestFit(
							selectedProfileNumber,
							lzbNCA.methodNumberForAllProfile[selectedProfileNumber],
							procInputInst
									.getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
									.getStartTime(),
							procInputInst
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
		
		DeConvoOutputPlots
				.createDeConvoOutputPlotsInst()
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
		
		
		
		return predictedY;
	}

	
	
	
	public DeConvoLambdaZDispGui() throws RowsExceededException, WriteException, BiffException, IOException{
		deConvoLambdaZDispGuiCreation();
	}
	
	public static DeConvoLambdaZDispGui DECONVO_LANB_DISP = null;

	public static DeConvoLambdaZDispGui createLambDispGuiInstance()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (DECONVO_LANB_DISP == null) {
			DECONVO_LANB_DISP = new DeConvoLambdaZDispGui();
		}
		return DECONVO_LANB_DISP;
	}

	
	public void deConvoLambdaZDispGuiCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		lambdaZSelectorInternalFrame();
	}
	
	private void lambdaZSelectorInternalFrame() throws RowsExceededException,
			WriteException, BiffException, IOException {

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
		
		lambdaZSelectorInternalFrame = new JInternalFrame("slope Selector",
				false, false, false, false);
		
		
		lambdaZSelectorInternalFrame
				.setLocation(
						DeConvoSetupAvailableCompCreator.createDeConvoSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ DeConvoSetupAvailableCompCreator
										.createDeConvoSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
										DeConvoSetupAvailableCompCreator.createDeConvoSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
	
		if(analysisType.equals("wagnelson"))
		{
			lambdaZSelectorInternalFrame
			.setSize(
					WagNelsonMainScreenCreator.createWagNelsonMainScreenInstance().wagNelsonMainInternalFrame
							.getWidth()
							- DeConvoSetupAvailableCompCreator
									.createDeConvoSetupAvailCompInst().setupAvailableComponentsInternalFrame
									.getWidth(),
									DeConvoSetupAvailableCompCreator.createDeConvoSetupAvailCompInst().setupAvailableComponentsInternalFrame
							.getHeight());
			
		}else if(analysisType.equals("looriegel"))
		{
			lambdaZSelectorInternalFrame
			.setSize(
					LooRiegelMainScreenCreator.createLooRiegelMainScreenInstance().looRiegelMainInternalFrame
							.getWidth()
							- DeConvoSetupAvailableCompCreator
									.createDeConvoSetupAvailCompInst().setupAvailableComponentsInternalFrame
									.getWidth(),
									DeConvoSetupAvailableCompCreator.createDeConvoSetupAvailCompInst().setupAvailableComponentsInternalFrame
							.getHeight());
		}
		
		
		
		
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(lambdaZSelectorInternalFrame);
		lambdaZInternalFrameDesktopPane = new JDesktopPane();
		lambdaZSelectorInternalFrame
				.setContentPane(lambdaZInternalFrameDesktopPane);
		lambdaZSelectorInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		DeConvoTabbedPaneCreator.createDeConvoTabbedPaneInstance().setupTabDesktopPane
				.add(lambdaZSelectorInternalFrame);
		lambdaZOptionsInternalFrame = new JInternalFrame("Options", false,
				false, false, false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(lambdaZOptionsInternalFrame);
		lambdaZOptionsInternalFrame.setLocation(0, 0);
		lambdaZOptionsInternalFrame.setSize(lambdaZSelectorInternalFrame
				.getWidth(), lambdaZSelectorInternalFrame.getHeight() / 8);
		lambdaZOptionsInternalFrame.setBorder(DDViewLayer
				.createViewLayerInstance().b);
		lambdaZOptionsInternalFrame.setVisible(true);
		lambdaZInternalFrameDesktopPane.add(lambdaZOptionsInternalFrame);
		lambdaZSelectorInternalFrame.setVisible(true);
		lambdaZOptionsInternalFrame.setLayout(new FlowLayout());

		// determining the number of profiles in the data
		int numberOfTab = DetermineNumberOfSubject.createDetNoSubInst().no_subject;

		lambdaZOptionsInternalFrame.setSize(lambdaZOptionsInternalFrame
				.getSize());

		// creating the options
		/*NCAMainScreen.createNcaMainScreenInstance().titledBorder = BorderFactory
				.createTitledBorder(
						null,
						"View ",
						TitledBorder.DEFAULT_JUSTIFICATION,
						TitledBorder.DEFAULT_POSITION,
						NCAMainScreen.createNcaMainScreenInstance().componentLablesFont,
						Color.black);
	*/	JPanel viewPanel = new JPanel();
		
		logViewRadioButton = new JRadioButton("Log");
		logViewRadioButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		logViewRadioButton.setActionCommand(LOGVIEWDECONVO);
		logViewRadioButton.setSelected(true);
		logViewRadioButton
				.setFont(NCAMainScreen.createNcaMainScreenInstance().componentLablesFont);
		viewPanel.add(logViewRadioButton);

		linearViewRadioButton = new JRadioButton("Lin");
		linearViewRadioButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		linearViewRadioButton.setActionCommand(LINVIEWDECONVO);
		linearViewRadioButton.setFont(NCAMainScreen
				.createNcaMainScreenInstance().componentLablesFont);
		viewPanel.add(linearViewRadioButton);

		ButtonGroup viewtypeButtonGroup = new ButtonGroup();
		viewtypeButtonGroup.add(linearViewRadioButton);
		viewtypeButtonGroup.add(logViewRadioButton);

		lambdaZOptionsInternalFrame.add(viewPanel);

		 TitledBorder titledBorder = BorderFactory
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
		lambdaZCalculationCombo.setActionCommand(LAMZMETHODDECONVO);

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
				lambdaZSelectorInternalFrame.getHeight()
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

		profileIF.setLayout(new GridLayout(2,1));
		// add lambdaZ and aucInf options
		
		
		
		
		
		Font componentLablesFont = new Font("Arial", Font.BOLD, 10);
		titledBorder = BorderFactory
		.createTitledBorder(null, "LambdaZ Options",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, componentLablesFont,
				Color.black);
		JPanel lambdaZOptionPanel = new JPanel();
		lambdaZOptionPanel.setLayout(new GridLayout(6,2));
		lambdaZOptionPanel.setBorder(titledBorder);
		
		userGivenTimeRangeRB = new  JRadioButton("User Time Range");
		userGivenTimeRangeRB.addActionListener(DDViewLayer.createViewLayerInstance());
		userGivenTimeRangeRB.setActionCommand(EventCodes.LAMZRANGERB);
		lambdaZOptionPanel.add(userGivenTimeRangeRB);
		
		lambdaZOptionPanel.add(new JLabel());
		
		
		JLabel startTimeLabel = new JLabel("Start Time");
		lambdaZOptionPanel.add(startTimeLabel);
		
		JLabel endTimeLabel = new JLabel("End Time");
		lambdaZOptionPanel.add(endTimeLabel);
		
		startTimeLamZTF = new JTextField();
		startTimeLamZTF.setEditable(false);
		startTimeLamZTF.addFocusListener(DDViewLayer.createViewLayerInstance());
		lambdaZOptionPanel.add(startTimeLamZTF);
		 
		endTimeLamZTF = new JTextField();
		endTimeLamZTF.setEditable(false);
		endTimeLamZTF.addFocusListener(DDViewLayer.createViewLayerInstance());
		
		lambdaZOptionPanel.add(endTimeLamZTF);
		
		
		userGivenLamZAndIntRB = new  JRadioButton("User Specified Lz and Intercept");
		userGivenLamZAndIntRB.addActionListener(DDViewLayer.createViewLayerInstance());
		userGivenLamZAndIntRB.setActionCommand(EventCodes.LAMZINTRB);
		lambdaZOptionPanel.add(userGivenLamZAndIntRB);
		
		lambdaZOptionPanel.add(new JLabel());
		
		JLabel lambdaZLabel = new JLabel("LambdaZ");
		lambdaZOptionPanel.add(lambdaZLabel);
		
		JLabel interceptLabel = new JLabel("Intercept");
		lambdaZOptionPanel.add(interceptLabel);
		
		userGivenLamZTF = new JTextField();
		userGivenLamZTF.setSize(10,5);
		userGivenLamZTF.setEditable(false);
		userGivenLamZTF.addFocusListener(DDViewLayer.createViewLayerInstance());
		lambdaZOptionPanel.add(userGivenLamZTF);
		 
		userGivenInterceptTF = new JTextField();
		userGivenInterceptTF.setEditable(false);
		userGivenInterceptTF.addFocusListener(DDViewLayer.createViewLayerInstance());
		lambdaZOptionPanel.add(userGivenInterceptTF);
		
		
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(userGivenLamZAndIntRB);
		bg.add(userGivenTimeRangeRB);
		
		
		profileIF.getContentPane().add(lambdaZOptionPanel);
		
			
		
		
		
		titledBorder = BorderFactory
		.createTitledBorder(null, "Area Under the Curve",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, componentLablesFont,
				Color.black);

		
		JPanel aucOptionsPanel = new JPanel();
		aucOptionsPanel.setLayout(new GridLayout(6, 2));
		 
		aucOptionsPanel.setBorder(titledBorder);
		
		
		
		
		usePredAucInfRB = new JRadioButton("Use AucInf_Pred");
		usePredAucInfRB.addActionListener(DDViewLayer.createViewLayerInstance());
		usePredAucInfRB.setActionCommand(EventCodes.PREDAUCINFRB);
		
		aucOptionsPanel.add(usePredAucInfRB);
		aucOptionsPanel.add(new JLabel());
		
		aucOptionsPanel.add(new JLabel());
		aucOptionsPanel.add(new JLabel());
		
		
		useObsAucInfRB = new JRadioButton("Use AucInf_Obs");
		useObsAucInfRB.addActionListener(DDViewLayer.createViewLayerInstance());
		useObsAucInfRB.setActionCommand(EventCodes.OBSAUCINFRB);
		
		aucOptionsPanel.add(useObsAucInfRB);
		aucOptionsPanel.add(new JLabel());
		
		aucOptionsPanel.add(new JLabel());
		aucOptionsPanel.add(new JLabel());
		
		useGivenAucInfRB = new JRadioButton("Provide AucInf");
		useGivenAucInfRB.addActionListener(DDViewLayer.createViewLayerInstance());
		useGivenAucInfRB.setActionCommand(EventCodes.GIVENAUCINFRB);
		
		aucOptionsPanel.add(useGivenAucInfRB);
		aucOptionsPanel.add(new JLabel());
		
		userGivenAucInf = new JTextField();
		userGivenAucInf.setEditable(false);
		userGivenAucInf
		.addFocusListener(DDViewLayer.createViewLayerInstance());
		
		aucOptionsPanel.add(userGivenAucInf);
		aucOptionsPanel.add(new JLabel());
		
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(useGivenAucInfRB);
		bg1.add(useObsAucInfRB);
		bg1.add(usePredAucInfRB);
		
		
		
		profileIF.getContentPane().add(aucOptionsPanel);
		
		
		
	}

	

}
