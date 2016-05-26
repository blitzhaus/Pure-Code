package view;

import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.Iconizing;
import Common.JinternalFrameFunctions;

public class MmMainScreenCreator {


	

	
	
	public static MmMainScreenCreator MM_MAIN_SCREEN = null;
	public static MmMainScreenCreator createMmMainScreenInstance() throws RowsExceededException, WriteException, BiffException, IOException{
		if(MM_MAIN_SCREEN == null){
			MM_MAIN_SCREEN = new MmMainScreenCreator();
		}
		return MM_MAIN_SCREEN;
	}


	JInternalFrame mmMainInternalFrame;
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
	protected static UnitBuilder doseUnitBuilderFrame;
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
	
	Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	Font componentOptionseFontForComboBox = new Font("Arial", Font.PLAIN, 11);
	Font componentLablesFont = new Font("Arial", Font.BOLD, 11);

	MmMainScreenGUICreator mmMainScreenInst;
	CaMappingDispGuiCreator mapCreationInst;
	
	
	
	public void MmMainScreenCreation() throws RowsExceededException, WriteException, IOException, BiffException {
		
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).setAnalysisType("mm");
		/*
		boolean isFromPlot = NCAMainScreenConstructor.createNcaMainScrConstr().checkIsToNcaFromPlotMaven();
		boolean isFromDescStats = NCAMainScreenConstructor.createNcaMainScrConstr().checkIsToNcaFromDescStats();*/
		
		//if((isFromPlot == false) &&(isFromDescStats == false)){
			/*Iconizing.createIconizingInstance().iconizeAvailableAnalysisInternalFrame();
			Iconizing.createIconizingInstance().iconizeLogInternalFrame();
			
		
			ViewLayer.createMTInstance().mainTabbedFrame.setSize(ViewLayer.createMTInstance().mainTabbedFrameOriginalWidth+ViewLayer.createAAInstance().availableAnalysisFrame.getWidth(),
					ViewLayer.createMTInstance().mainTabbedFrame.getHeight()+ViewLayer.createLogFrameInstance().logFrame.getHeight());
			ViewLayer.createPEInstance().projectExplorerFrame.setSize(ViewLayer.createPEInstance().projectExplorerFrame.getWidth(),
					ViewLayer.createMTInstance().mainTabbedFrame.getHeight());*/
		
		DDViewLayer.createMTInstance().mainTabbedFrame.setSize(DDViewLayer
				.createMTInstance().mainTabbedFrameOriginalWidth, DDViewLayer
				.createMTInstance().mainTabbedFrameoriginalHeight);
			mmMainInternalFrame = new JInternalFrame("PD Main Screen", false,
					false, false, false);
			
			JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(mmMainInternalFrame);
			mmMainInternalFrame.setLocation(0, 0);
			mmMainInternalFrame
			.setSize(DDViewLayer.createMTInstance().mainTabbedFrame.getWidth(), (int)(DDViewLayer.createMTInstance().mainTabbedFrame.getHeight()/2));

			
	//	}
			
		mmMainScreenInst = MmMainScreenGUICreator.createMmMainScreenGui();
		//mmMainScreenInst.createMmMainScreenGui();
		
		mapCreationInst = CaMappingDispGuiCreator.createMappingInstance();
		mapCreationInst.mappingInternalFrame.moveToFront();
					
		mmMainInternalFrame.setVisible(true);
		mmMainInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		DDViewLayer.createMTInstance().mainTabbedDesktopPane.add(mmMainInternalFrame);
		mmMainInternalFrame.moveToFront();
	}




}
