package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.MaskFormatter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.record.formula.atp.AnalysisToolPak;
import org.apache.xmlbeans.impl.jam.mutable.MField;
import org.dom4j.rule.RuleSet;

//import CaIpCalculations.ComputationsDispatcher;
import Common.EventCodes;
import Common.JinternalFrameFunctions;
import Common.TestException;
import Control.DDController;
import Control.DDControllerInterface;

import Model.DDModel;
import Model.ModelInterface;
import Model.ModelLayer;
import Model.NonParametricSuperposition;

public class DDViewLayer extends JFrame implements ObserverInterface,
		WindowStateListener, ActionListener, MouseListener,
		MouseMotionListener, TreeSelectionListener, PropertyChangeListener,
		EventCodes, DocumentListener, ListSelectionListener, FocusListener,
		TableModelListener, ItemListener {

	// for InVitro
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean isFromInVitro;
	boolean isBeforeInVitro;
	boolean isInVitroExecution;
	
	
	
	
	
	
	Dimension screenSize;
	protected static JDesktopPane desktop;
	final Border b = BorderFactory.createLineBorder(Color.BLACK);
	private boolean isDisplayContentsTable = false;
	private int numberOFclicksOnPlotLable = 0;
	boolean isimportedDataTable = false;
	boolean isBeforeNCA = true;
	boolean isFronClickOnProjectExplorer = false;
	NCAMainScreen ncaAnalysisObject;
	boolean isFromSemiCompartmentalModellingMainPage = false;
	boolean isFromNonParametricSuperPositionMainPage = false;
	boolean isFromTableMaven = false;
	boolean isFromPlotMavenMappingScreen = false;
	boolean isFromNca = false;
	boolean isPlotMavenExecution;
	boolean isNCAExecution;
	boolean isToNCAFromPlotMaven = false;
	boolean isFromCA = false;
	boolean isCAExecution;
	boolean isFromBQL;
	
	boolean isBeforeCA = true;

	boolean isNcaRestore;
	boolean isCARestore;
	boolean isPlotMavenRestore;
	boolean isTableMavenRestore;
	boolean isNPSRestore;
	boolean isSCARestore;
	boolean isDSRestore;
	boolean isPDRestore;
	boolean isMMRestore;
	boolean isIRMRestore;
	boolean isPKPDLinkRestore;
	boolean isForSparseSample;
	public int getNumberOFclicksOnPlotLable() {
		return numberOFclicksOnPlotLable;
	}

	public void setNumberOFclicksOnPlotLable(int numberOFclicksOnPlotLable) {
		this.numberOFclicksOnPlotLable = numberOFclicksOnPlotLable;
	}

	public boolean isDisplayContentsTable() {
		return isDisplayContentsTable;
	}

	public void setDisplayContentsTable(boolean isDisplayContentsTable) {
		this.isDisplayContentsTable = isDisplayContentsTable;
	}

	private static DDViewLayer m_mainLayOutPage = null;
	ModelInterface ddModel;
	DDControllerInterface ddControllerInterface;

	public static DDViewLayer createViewLayerInstance() {
		if (m_mainLayOutPage == null) {
			m_mainLayOutPage = new DDViewLayer();
		}

		return m_mainLayOutPage;
	}

	public void setModel(ModelInterface modelInst) {

		this.ddModel = modelInst;
		modelInst.registerObserver(this);
	}

	public void setController(DDController ddController) {

		this.ddControllerInterface = ddController;
	}

	protected boolean isTblTransInvoked;
	public boolean isTableTrans;
	public boolean istableTransRestore;
	public boolean isBqlRestore;
	public boolean isTMRestore;

	public DDViewLayer() {
		
		setLocation(0, 0);
		setTitle("Drug Development Platform - atc");
		Container content = this.getContentPane();
		content.setBackground(Color.white);
		desktop = new JDesktopPane();
		addWindowStateListener(this);
		desktop.setBackground(Color.white);
		content.add(desktop, BorderLayout.CENTER);
		//screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Rectangle gebounds = ge.getMaximumWindowBounds();

		double height = gebounds.getHeight();

		// setSize(screenSize);
		setSize(gebounds.width, gebounds.height);
		screenSize = getSize();

		setVisible(true);
		setResizable(false);
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// after persisting the analysis we have to remove the extra
				// final parameters sheet added
				// if descriptive stats analysis is performed

				// removing the final parameters from the work book and also
				// from the data structures

				File f = new File("ajith.xls");
				if (f.exists() == true)
					f.delete();
				
				File ff = new File("Printing Statements");
				if(ff.exists() == true){
					ff.delete();
				}
				
				try{
				
					 BufferedReader br = new BufferedReader(new FileReader(new File("Controller.txt")));
					    int numberOfExecutions = (int)br.read();
					    numberOfExecutions = numberOfExecutions - 49;
					    br.close();
					    
					    if(numberOfExecutions < 99){
					    	BufferedWriter brw = new BufferedWriter(new FileWriter(new File("Controller.txt")));
					    	brw.write((numberOfExecutions + 1+49));
					    	brw.close();
					    } else {
					    	
					    	JFrame fclose = new JFrame();
					    	fclose.setVisible(true);
					    	fclose.setSize(500,500);
					    	fclose.setLocationRelativeTo(null);
					    	JOptionPane.showMessageDialog(fclose,
									"Thanks for your feed back, you have reached the limit of executions. Please contact your administrator", "Error",
									JOptionPane.YES_OPTION);
					    	/*try {
								wait(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/
					    	fclose.dispose();
					    	System.exit(1);
					    }
				} catch(IOException e){
					
				}
			    
			   
				

			}

			@Override
			public void windowClosed(WindowEvent arg0) {

			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	
	public void instantiateViewRef() {
		createLogoPanelInstance().LogoPanelCreationCreation();
		createFeaturesPanelInstance().FeaturesPnaleCreation();
		createmenuBarInstance().MenuBarCreation();
		createComponentsPaneInstance().ComponentsPanelCreation();
		createPEInstance().ProjectExplorerCreation();
		createMTInstance().MainTabbedIFCreation();
		createLogFrameInstance().LogFrameCreation();
		createAAInstance().AvailableAnalysisFrameCration();
		// createStepsFrameInstance().StepsPanelCreation();
		JinternalFrameFunctions.createIFFunctionsInstance().fix(desktop);// function
																			// which
																			// makes
																			// the
																			// frames
																			// immovable

	}

	public DDViewLayer(String dummy) {

	}

	// singleton implementation of log panel creation object
	public static LogoPanelCreation LOGO_OBJ = null;

	public static LogoPanelCreation createLogoPanelInstance() {
		if (LOGO_OBJ == null) {
			LOGO_OBJ = new LogoPanelCreation("");
		}
		return LOGO_OBJ;
	}

	public static FeaturesPnaleCreation FEATURES_OBJ = null;

	public static FeaturesPnaleCreation createFeaturesPanelInstance() {
		if (FEATURES_OBJ == null) {
			FEATURES_OBJ = new FeaturesPnaleCreation("");
		}

		return FEATURES_OBJ;
	}

	public static MenuBarCreation MENU_OBJ = null;

	public static MenuBarCreation createmenuBarInstance() {
		if (MENU_OBJ == null) {
			MENU_OBJ = new MenuBarCreation("");
		}

		return MENU_OBJ;
	}

	public static ComponentsPanelCreation COMPONENTS_OBJ = null;

	public static ComponentsPanelCreation createComponentsPaneInstance() {
		if (COMPONENTS_OBJ == null) {
			COMPONENTS_OBJ = new ComponentsPanelCreation("");
		}

		return COMPONENTS_OBJ;
	}

	public static ProjectExplorerCreation PE_OBJ = null;

	public static ProjectExplorerCreation createPEInstance() {
		if (PE_OBJ == null) {
			PE_OBJ = new ProjectExplorerCreation("");
		}
		return PE_OBJ;
	}

	public static MainTabbedIFCreation MTabbedFRAME_OBJ = null;

	public static MainTabbedIFCreation createMTInstance() {
		if (MTabbedFRAME_OBJ == null) {
			MTabbedFRAME_OBJ = new MainTabbedIFCreation("");
		}
		return MTabbedFRAME_OBJ;
	}

	public static LogFrameCreation LOGFRMAE_OBJ = null;

	public static LogFrameCreation createLogFrameInstance() {
		if (LOGFRMAE_OBJ == null) {
			LOGFRMAE_OBJ = new LogFrameCreation("");
		}

		return LOGFRMAE_OBJ;
	}

	public static AvailableAnalysisFrame AAFRAME_OBJ = null;

	public static AvailableAnalysisFrame createAAInstance() {
		if (AAFRAME_OBJ == null) {
			AAFRAME_OBJ = new AvailableAnalysisFrame("");
		}
		return AAFRAME_OBJ;
	}

	public static StepsPanelCreation SF_OBJ = null;

	public static StepsPanelCreation createStepsFrameInstance() {
		if (SF_OBJ == null) {
			SF_OBJ = new StepsPanelCreation("");
		}
		return SF_OBJ;
	}

	@Override
	public void windowStateChanged(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			// Import file into the application
			if ((arg0.getActionCommand() == IMPORTPROJECT)
					|| arg0.getActionCommand() == OPEN
					|| arg0.getActionCommand() == IMPORTDATA) {

				// call to the file import in model
				ImportFile.createImportFileInstance().fileImport();
			} else if (arg0.getActionCommand() == CLOSE) {
				// call to project close handler.
				ProjectCloseHandler.createProjectCloseHandlerInst()
						.closeProject();
			} else if (arg0.getActionCommand() == SAVEAS) {
				// call to project saveAs handler.
				// ProjectSaveAsHandler.createProjectSaveAsHandlerInst().saveFile();
				ProjectSaveAsHandler.createProjectSaveAsHandlerInst()
						.saveProjectExplorerDetails();
			}
			else if(arg0.getActionCommand() == LOAD){
				//call to project load handler.
				ProjectLoadHandler.createProjectLoadHandlerInst().loadProject(); 
			}			
			// the next 2 conditions are relateed to the display contents func
			else if (arg0.getActionCommand() == FINISHANDOPEN) {
				DCNavigationPanelFunc.createDCNavigationHandlerInst()
						.finishAndOpenFunc(
								ImportFile.createDispContentsInstance());
			} else if (arg0.getActionCommand() == SHOWAAFRAME) {
				DDViewLayer.createAAInstance()
						.enableAvailableAnalysysInternalFrameFrame();
			} else if (arg0.getActionCommand() == HASHEADERROWCHECK) {

				HasRowCheckedEventHandler localObj = new HasRowCheckedEventHandler(
						ImportFile.createDispContentsInstance());
				localObj.hasHeaderRowCheckedEventHandler();
			}

			// the next 5 conditions are related to imported data sheet func
			else if (arg0.getActionCommand() == IMPCANCEL) {
				// cancel button in the navigation panel of the display contents
				DCNavigationPanelFunc.createDCNavigationHandlerInst()
						.cancelButtonFunc();

			} else if (arg0.getActionCommand() == DATATYPECHANGE) {
				ImportedDataSheetHandlers.createImpSheetHandInstance()
						.dataTypeChangeHand();
			}

			else if (arg0.getActionCommand() == UNITBUILDERBUTTON) {
				UnitBuilder.createUBInstance();
				UnitBuilder.createUBInstance().unitBuilderFrame
						.setVisible(true);
			}

			else if (arg0.getActionCommand() == IMPSHEETNUMERICRADIO) {
				
			} else if (arg0.getActionCommand() == IMPSHEETOPTDECIMALRADIO) {
				
				
			} else if (arg0.getActionCommand() == ADDROW) {
				NewSheetHandlers.createNewShetHandlersInst().addRow();
			} else if (arg0.getActionCommand() == ADDCOLUMN) {
				NewSheetHandlers.createNewShetHandlersInst().addColumn();
			} else if (arg0.getActionCommand() == NUMBEROFROWS) {
				NewSheetHandlers.createNewShetHandlersInst().setNumberOfRows();
			} else if (arg0.getActionCommand() == NUMBEROFCOLUMNS) {
				NewSheetHandlers.createNewShetHandlersInst()
						.setNumberOfColumns();
			} else if (arg0.getActionCommand() == NEWSHEETOK) {
				NewSheetHandlers.createNewShetHandlersInst().okButtonHandler();
			} else if (arg0.getActionCommand() == NEWSHEETCANCEL) {
				NewSheetHandlers.createNewShetHandlersInst()
						.cancelButtonHandler();
			} else if (arg0.getActionCommand() == BASELINEFUNC) {
				TabTransformationsHandler.createTableTransformationInstance()
						.baseLineFunc();
			} else if (arg0.getActionCommand() == TABTRANSAJITH) {
				
				TableTransformationsButtonHandler.createTTButtonHandInst().handler();
				TableTransformations.createTableTrasGUIInst().createTTGUI();
			} else if (arg0.getActionCommand() == TTAVAILAANDSORT) {
				TabTransformationsHandler.createTableTransformationInstance()
						.availableToSortButtonHandler();
			} else if (arg0.getActionCommand() == TTAVAILANDX) {
				TabTransformationsHandler.createTableTransformationInstance()
						.availableToXButtonHandler();
			} else if (arg0.getActionCommand() == TTAVAILANDY) {
				TabTransformationsHandler.createTableTransformationInstance()
						.availableToYButtonHandler();
			}

			// The next 9 conditions are related to the unit builder func
			else if (arg0.getActionCommand() == UBCANCEL) {
				UBCoreFunc.createUBCoreFuncInstance().cancelButtonHandler();
			} else if (arg0.getActionCommand() == UBMASSADD) {
				UBCoreFunc.createUBCoreFuncInstance().massMeasureAddHandler();
			} else if (arg0.getActionCommand() == UBDIVOPERATOR) {
				UBCoreFunc.createUBCoreFuncInstance().divisionOperatorHandler();
			} else if (arg0.getActionCommand() == UBMULOPERATOR) {
				UBCoreFunc.createUBCoreFuncInstance().mulOperatorHandler();
			} else if (arg0.getActionCommand() == UBEXPOPERATOR) {
				UBCoreFunc.createUBCoreFuncInstance().expOperatorHandler();
			} else if (arg0.getActionCommand() == UBVOLADD) {
				UBCoreFunc.createUBCoreFuncInstance().volumeAddButtonHandler();
			} else if (arg0.getActionCommand() == UBTIMEADD) {
				UBCoreFunc.createUBCoreFuncInstance().timeAddHandler();
			} else if (arg0.getActionCommand() == UBCLEAR) {
				UBCoreFunc.createUBCoreFuncInstance().clearUnitsHandler();
			} else if (arg0.getActionCommand() == UBOK) {
				UBCoreFunc.createUBCoreFuncInstance().okButtonHandler();
			}

			// The next 4 conditions are realted to the avaialble analysis
			// frame.
			else if (arg0.getActionCommand() == ENTERNCA) {
				new NCAButtonActionHandler();
			} else if (arg0.getActionCommand() == AVAILAANDSORT) {

				NcaMappingHandlers.createNCAMapHandInst()
						.sortAndAvailableButtonActnHandler();
			} else if (arg0.getActionCommand() == AVAILANDY) {
				NcaMappingHandlers.createNCAMapHandInst()
						.yAndAvailButtonActnHandler();
			} else if (arg0.getActionCommand() == AVAILANDX) {
				NcaMappingHandlers.createNCAMapHandInst()
						.xAndAvailButtonActnHandler();
			} else if (arg0.getActionCommand() == AVAILANDENDTIME) {
				NcaMappingHandlers.createNCAMapHandInst()
						.availToEndTimeButtonAction();
			} else if (arg0.getActionCommand() == AVAILANDSUBJECT) {
				NcaMappingHandlers.createNCAMapHandInst()
						.availToSubjectButtonHandler();
			} else if (arg0.getActionCommand() == AVAILANDVOLUME) {
				NcaMappingHandlers.createNCAMapHandInst()
						.availToVolumeButtonAction();
			} else if (arg0.getActionCommand() == EXCLUDEPROFILE) {
				NcaOptionsHandlers.createNcaOptHandlers()
						.excludeProfileHandler();
			} else if (arg0.getActionCommand() == SELECTIONPROFILE) {
				NcaOptionsHandlers.createNcaOptHandlers()
						.selectionCheckHandler();
			} else if (arg0.getActionCommand() == PAGEBREAK) {
				NcaOptionsHandlers.createNcaOptHandlers().pageBreakHandler();
			} else if ((arg0.getActionCommand() == LOGVIEW)
					|| (arg0.getActionCommand() == LINEARVIEW)) {
				NcaLambdaZHandlers.createNcaLamdHandlersInst()
						.linearLogViewHandler();
			} else if (arg0.getActionCommand() == LAMBDAZMETHOD) {
				NcaLambdaZHandlers.createNcaLamdHandlersInst()
						.lambdaZMethodComboHandler();
			} else if (arg0.getActionCommand() == PARTIALAREACOUNT) {
				NcaOptionsHandlers.createNcaOptHandlers()
						.partialAreasComboHandler();
			} else if (arg0.getActionCommand() == PREVIEW) {
				PreviewSelectedSheet.createpreviewShtInstance().showPreview();
			} else if (arg0.getActionCommand() == EXECUTE) {

				try {
					Execution.createExecuteInstance().executeAnalysis();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (arg0.getActionCommand() == ENTERPLOTMAVEN) {
				new PlotMavenButtonActionHandler();
			} else if (arg0.getActionCommand() == XYSCATTER) {
				PlotMavenHandlers.createPlotMavenHandlerInst()
						.xyScatterRadioHandler();
			} else if (arg0.getActionCommand() == PMAVAILTOX) {
				PlotMavenHandlers.createPlotMavenHandlerInst()
						.avialToXButtonHandler();
			} else if (arg0.getActionCommand() == PMAVAILTOY) {
				PlotMavenHandlers.createPlotMavenHandlerInst()
						.availToYButtonHandler();
			} else if (arg0.getActionCommand() == PMAVAILTOSORT) {
				PlotMavenHandlers.createPlotMavenHandlerInst()
						.availToSortButtonHandler();
			} else if (arg0.getActionCommand() == PMAVAILTOGROUP) {
				PlotMavenHandlers.createPlotMavenHandlerInst()
						.availToGroupButtonHandler();
			} else if (arg0.getActionCommand() == PMAVAILTOUP) {
				PlotMavenHandlers.createPlotMavenHandlerInst()
						.availToUpButtonHandler();
			} else if (arg0.getActionCommand() == PMAVAILTODOWn) {
				PlotMavenHandlers.createPlotMavenHandlerInst()
						.availToDownButtonHandler();
			} else if (arg0.getActionCommand() == PMLINEARX) {
				PlotMavenHandlers.createPlotMavenHandlerInst().linearX();
			} else if (arg0.getActionCommand() == PMLOGX) {
				PlotMavenHandlers.createPlotMavenHandlerInst().lox();
			} else if (arg0.getActionCommand() == ENTERDESSTATS) {
				new DescStatsMenuBarSelectionHandler();
			} else if (arg0.getActionCommand() == DSSORTANDAVAIL) {
				DSHandlers.createDesStatEventHandlerInst()
						.sortAndAvailButtonHanler();
			} else if (arg0.getActionCommand() == DSAVAILTOCARRY) {
				DSHandlers.createDesStatEventHandlerInst()
						.availToCarryButtonHandler();
			} else if (arg0.getActionCommand() == DSWEIGHTTOAVAIL) {
				DSHandlers.createDesStatEventHandlerInst()
						.weightToAvailButtonHandler();
			} else if (arg0.getActionCommand() == ENTERSCA) {
				new SCAbuttonHandler();
			} else if (arg0.getActionCommand() == SORTANDAVAIL) {
				ScaHandlers.createScaHandInst().sortAndAvailButttonHand();
			} else if (arg0.getActionCommand() == AVAILAANDCONC) {
				ScaHandlers.createScaHandInst().availaToConcHandler();
			} else if (arg0.getActionCommand() == EFFECTANDAVAIL) {
				ScaHandlers.createScaHandInst().effectAndAvailButtonHandler();
			} else if (arg0.getActionCommand() == TIMEANDAVAIL) {
				ScaHandlers.createScaHandInst().timeAndAvailButtonHandler();
			} else if (arg0.getActionCommand() == ENTERNPS) {
				new NPSButtonHandler();
			}else if(arg0.getActionCommand() == NCACALCMETHOD){
				NcaOptCalculationMthodSelectionHandler.createInst().handler();
			}
			else if (arg0.getActionCommand() == NPSSORTANDAVAIL) {
				NPSHandlers.createNPSHandlersInst().sortAndAvailButtonHandler();
			} else if (arg0.getActionCommand() == NPSAVAILTOCONC) {
				NPSHandlers.createNPSHandlersInst().availToConcButtonHandler();
			} else if (arg0.getActionCommand() == NPSAVAILTOTIME) {
				NPSHandlers.createNPSHandlersInst().timeAndAvailButtonHandler();
			} else if (arg0.getActionCommand() == ENTERBQL) {
				BQLMainScreen.createBQLMainScrInst().createUI();
			} else if (arg0.getActionCommand() == ROOTOFADMIN) {
				NcaOptRootOfAdminHandler.createNcaOptRootHandler()
						.rootOfAdminHandler();
			} else if (arg0.getActionCommand() == MODELTYPE) {
				NcaOptModelOptionsHandler.createNcaModelOptHandlerInst()
						.actionPerformed();
			} else if (arg0.getActionCommand() == NCASPARSE) {
				NcaOptSparseSelectionHandler.createNcaSparseSelectionhandInst()
						.sparseAction();
			} else if (arg0.getActionCommand() == ENTERTABLEMAVEN) {
				new TableMavenButtonHandler();
			} else if (arg0.getActionCommand() == ENTERCA) {
				CaLibModelOptionsGenerator libModelInst = new CaLibModelOptionsGenerator();

				libModelInst.providingOptionsForAllCAModels();

				// new CAbuttonHandler();

			}else if(arg0.getActionCommand() == NCAWEIGHT){
				NcaOptWeightOptionsHandler.createNcaWeightOptHandler().handler();
			}
			else if(arg0.getActionCommand() == UBDOSEUNIT){ 
				NcaOptDoseUnitButtonHandler.createInst().action();
			}
			
			else if(arg0.getActionCommand() == AVAILANDCARRYNCA){
				NcaMappingHandlers.createNCAMapHandInst().availToCarryAlongButtonAction();
			}
			
			
			else if (arg0.getActionCommand() == AVAILAANDSORTCA
					&& isCAExecution == true) {
					CaMapingHandler pdMapHandInst = CaMapingHandler.createCaMapHandInst();
					pdMapHandInst.availableVarToSortVarBtnEvent();

				//}
			}

			else if (arg0.getActionCommand() == AVAILANDYCA
					&& isCAExecution == true) {
					CaMapingHandler pdMapHandInst = CaMapingHandler.createCaMapHandInst();
					pdMapHandInst.yVarAndAvailableVarBtnEvent();
				
			} else if(arg0.getActionCommand() == AVAILANDFUNCCA && isCAExecution == true){
				CaMapingHandler.createCaMapHandInst().funcAndAvailBtnHandler();
			}
 
			else if (arg0.getActionCommand() == AVAILANDXCA
					&& isCAExecution == true) {
					CaMapingHandler pdMapHandInst = CaMapingHandler.createCaMapHandInst();
					pdMapHandInst.availableVarAndXVarBtnEvent();
			}else if (arg0.getActionCommand() == AVAILANDENDTIMECA
					&& isCAExecution == true) {
				
			} else if (arg0.getActionCommand() == AVAILANDVOLUMECA) {
			
			} else if (arg0.getActionCommand() == IPVCB) {
				CaEngineSetActionHandler engSetActInst = new CaEngineSetActionHandler();
				engSetActInst.actionListEventForInitParamValCB();
			} else if (arg0.getActionCommand() == PBCB) {
				CaEngineSetActionHandler engSetActInst = new CaEngineSetActionHandler();
				engSetActInst.actionListEventForParameBoundCB();
			} else if (arg0.getActionCommand() == MMCB) {
				CaEngineSetActionHandler engSetActInst = new CaEngineSetActionHandler();
				try {
					engSetActInst.minMethodCbActionhandler();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (arg0.getActionCommand() == ODESCB) {
				CaEngineSetActionHandler engSetActInst = new CaEngineSetActionHandler();
				engSetActInst.odeSolverMethodCbActionHandler();
			}

			else if (arg0.getActionCommand() == PEBCSRB) {
				CaEngineSetActionHandler engSetActInst = new CaEngineSetActionHandler();
				engSetActInst.actionListenerEventForInitParamCalByCS();
			} else if (arg0.getActionCommand() == PEBGARB) {
				CaEngineSetActionHandler engSetActInst = new CaEngineSetActionHandler();
				engSetActInst.actionListenerEventForInitParamCalueByGA();
			}else if (arg0.getActionCommand() == PEBGSRB) {
				CaEngineSetActionHandler engSetActInst = new CaEngineSetActionHandler();
				engSetActInst.actionListenerEventForInitParamCalueByGS();
			} else if(arg0.getActionCommand() == PEBNCA){
				CaEngineSetActionHandler engSetActInst = new CaEngineSetActionHandler();
				engSetActInst.actionListenerEventForInitParamCalByEqns();
			}

			else if (arg0.getActionCommand() == AVAILANDVOLUMECA
					&& isCAExecution == true) {/*
				CAMappingHandlers.createCAMapHandInst()
						.availableVarAndVolumeBtnEvent();
			*/}

			else if (arg0.getActionCommand() == AVAILANDVOLUMECA
					&& isCAExecution == true) {/*
				CAMappingHandlers.createCAMapHandInst()
						.availableVarAndSubjectBtnEvent();
			*/}

			else if (arg0.getActionCommand() == ALMCB) {
				
			} else if (arg0.getActionCommand() == ALMRB) {
				
			} else if (arg0.getActionCommand() == DEMRB) {/*
				DEMRbEventHandler inst = new DEMRbEventHandler();
				inst.actionEvent();
			*/} else if (arg0.getActionCommand() == DEMCB) {
				
			} else if (arg0.getActionCommand() == OWRB) {
	
					CaWeightDoseActionHandler inst = new CaWeightDoseActionHandler();
					inst.actionListenerEventForObservedWeightingRadioButton();
			//	}
}  else if (arg0.getActionCommand() == PWCB) {
				
					CaWeightDoseActionHandler inst = new CaWeightDoseActionHandler();
					inst.actionListenerEventForPredWeighOptionsCB();
				//}
			} else if (arg0.getActionCommand() == PWRB) {
				
					CaWeightDoseActionHandler inst = new CaWeightDoseActionHandler();
					inst.actionListenerEventForPrediWeightRB();
				//}

			}



	else if (arg0.getActionCommand() == OWCB) {
			
			CaWeightDoseActionHandler inst = new CaWeightDoseActionHandler();
			inst.actionListenerEventForWeightOptCB();
		//}
}
			
			else if (arg0.getActionCommand() == NUCB) {
			
					CaWeightDoseActionHandler inst = new CaWeightDoseActionHandler();
					inst.actionListenerEventForNormalizationUnitComboBoxForCA();
				//}
			} else if (arg0.getActionCommand() == BQLAVAILAANDSORT) {
				BQLMappingHandler.createBqlMappingHandInst()
						.sortAndAvailableHandler();
			} else if (arg0.getActionCommand() == BQLAVAILANDX) {
				BQLMappingHandler.createBqlMappingHandInst()
						.xAndAvailableButtonHandler();
			} else if (arg0.getActionCommand() == BQLAVAILANDY) {
				BQLMappingHandler.createBqlMappingHandInst()
						.yAndAvailableButtonHandler();
			} else if (arg0.getActionCommand() == BQLMOVETOSTATUS) {
				BQLMappingHandler.createBqlMappingHandInst()
						.availableToStatusCodeButtonHandler();
			} else if (arg0.getActionCommand() == BQLMOVETOCARRY) {
				BQLMappingHandler.createBqlMappingHandInst()
						.availableToCarryAlongButtonHandler();
			} else if (arg0.getActionCommand() == BQLLLOQCHECKBOX) {
				BQLRulesHandler.createRulesHandlerInst().lloqCheckHandler();
			} else if (arg0.getActionCommand() == DUB) {
				
					CaWeightDoseActionHandler inst = new CaWeightDoseActionHandler();
					inst.doseUnitButtonActionHandler();
				//}
			} else if (arg0.getActionCommand() == NDCB) {
				
					CaWeightDoseActionHandler inst = new CaWeightDoseActionHandler();
					inst.actionListenerEventForNumberOfDoseComboBox();
				//}
			} else if (arg0.getActionCommand() == PKUNIT) {
				LibraryModelActionHandler libModelActionHadInst = LibraryModelActionHandler
				.createLibModelActionHandInst();
				libModelActionHadInst.actionHandlerForPKUnitBtn();

			} 
			
			
			else if (arg0.getActionCommand() == SIMU) {
				LibraryModelActionHandler libModelActionHadInst = LibraryModelActionHandler
						.createLibModelActionHandInst();
				libModelActionHadInst.actionEventForSimulation();

			} else if (arg0.getActionCommand() == SIMUUNIT) {
				LibraryModelActionHandler libModelActionHadInst = LibraryModelActionHandler
						.createLibModelActionHandInst();
				libModelActionHadInst.actionHandlerForSimulationUnitBtn();

			}

			else if (arg0.getActionCommand() == CLER) {
				LibraryModelActionHandler libModelActionHadInst = new LibraryModelActionHandler();
				libModelActionHadInst.ActionEventForClearance();

			}

			else if (arg0.getActionCommand() == COPY) {
				CopyingTableValueforEditMenu.createCopyingTableValueInstance()
						.CopyingTableValueForEdit();

			} else if (arg0.getActionCommand() == PASTE) {
				PasteTableValueforEditMenu.createPasteTableValueInstance()
						.PasteTableValueForEdit();

			} else if (arg0.getActionCommand() == FILLDOWN) {
				FillDownTableValueforEditMenu
						.createFillDownTableValueInstance()
						.FillDownTableValueForEdit();
			} else if (arg0.getActionCommand() == TABTRANS) {
				// call to Table Transformations Handler.
				TabTransformationsHandler.createTableTransformationInstance()
						.createGUIForTableTransformation();
			}

			else if (arg0.getActionCommand() == SELECTALL) {
				SelectAllTableValueforEditMenu
						.createSelectAllTableValueInstance()
						.SelectAllTableValueForEdit();
			} else if (arg0.getActionCommand() == DELETE) {
				DeleteTableValueforEditMenu.createDeleteTableValueInstance()
						.DeleteTableValueForEdit();
			} else if (arg0.getActionCommand() == NEWSHEET) {
				NewSheetCreation.createNewSheetInst().createNewSheet();
			} else if(arg0.getActionCommand() == NEWPROJECT){
				NewProjectCreation.createNewProjectInst().createNewProject();
			} else if(arg0.getActionCommand() == FINDREPLACE){
				FindReplaceHandler.createFindReplaceHandInst().handler();
			}
			// for InVitro
			else if(arg0.getActionCommand() == HILL){
				InVitroModelWeightActionHandler inst = new InVitroModelWeightActionHandler();
				//inst.actionEventForHillModel();
			}else if(arg0.getActionCommand() == WEIBUL){
				InVitroModelWeightActionHandler inst = new InVitroModelWeightActionHandler();
				//inst.actionEventForWeibulModel();
			}else if(arg0.getActionCommand() == DOUBLEWEIBUL){
				InVitroModelWeightActionHandler inst = new InVitroModelWeightActionHandler();
				//inst.actionEventForDoubleWeibulModel();
			}else if(arg0.getActionCommand() == MAKOID){
				InVitroModelWeightActionHandler inst = new InVitroModelWeightActionHandler();
				//inst.actionEventForMakoidModel();
			}else if(arg0.getActionCommand() == INVITROWEIGHT){
				InVitroModelWeightActionHandler inst = new InVitroModelWeightActionHandler();
				inst.actionEventForDissolutionWeight();
			} else if(arg0.getActionCommand() == CAUSERIPCOMPUTE){/*
				
				ComputationsDispatcher compute = new ComputationsDispatcher();
				try {
					
					ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
					String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
							.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
									.getAnalysisType();
					compute.dispacher(analysisType);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			*/}
			
			
			
			
		} catch (RowsExceededException e) {
			TestException test = new TestException();
			test.throwCustomException(7);

		} catch (WriteException e) {
			TestException test = new TestException();
			test.throwCustomException(8);

		} catch (BiffException e) {
			TestException test = new TestException();
			test.throwCustomException(10);

		} catch (IOException e) {
			TestException test = new TestException();
			test.throwCustomException(9);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	// this for loop is to show the sheet specific data in the display
		// contents GUI
		if (ImportFile.createDispContentsInstance().f.isVisible() == true) {
		} else if (arg0.getSource() == ImportedDataSheet
				.createImportedDataSheetInstance().columnsAvailableList) {

			if (arg0.getClickCount() == 2)
				ImportedDataSheetHandlers.createImpSheetHandInstance()
						.renameColumn();
		} else if (arg0.getSource() == BQLSetupDispComp
				.createBQLSetupDispInst().rulesTable) {
			int columnClicked = BQLSetupDispComp.createBQLSetupDispInst().rulesTable
					.getSelectedColumn();
			int rowClicked = BQLSetupDispComp.createBQLSetupDispInst().rulesTable
					.getSelectedRow();
			if ((columnClicked != 0) && (columnClicked != 7)) {
				if (BQLOptComp.createBQLOptINst().lloqRB.isSelected() == true) {
					BQLSetupDispComp.createBQLSetupDispInst().rulesTable
							.setValueAt("LLOQ/2", rowClicked, columnClicked);
					;
				} else if (BQLOptComp.createBQLOptINst().missingRB.isSelected() == true) {
					BQLSetupDispComp.createBQLSetupDispInst().rulesTable
							.setValueAt("Missing", rowClicked, columnClicked);

				} else if (BQLOptComp.createBQLOptINst().zeroRB.isSelected() == true) {
					BQLSetupDispComp.createBQLSetupDispInst().rulesTable
							.setValueAt("0", rowClicked, columnClicked);
				}

			}
		}
		else if(arg0.getSource().equals(QcmainScreen.createQcMainScrInst().qcTable)){
			new QCHandlers(QcmainScreen.createQcMainScrInst().qcTable);
		}		
		else 
			try {
				if (arg0
						.getSource()
						.equals(
								NcaResultsAvailableComp
										.createNcaResAvailCompInst().availableOutputsTree)) {
					if (arg0.getClickCount() == 2) {
						NcaResultsAvailResTreeValueChangeHand
								.createNcaResTreeInst().includeAsInput();
					}
				} else if (arg0
						.getSource()
						.equals(
								DesStatResAvailComp.createDesStatResAvailInst().availableOutputsTree)) {
					if (arg0.getClickCount() == 2) {
						DSHandlers.createDesStatEventHandlerInst()
								.includeAsInput();
					}
				} else if (arg0
						.getSource()
						.equals(
								NPSResultsAvailComp.createNPSResAvailInst().availableOutputsTree)) {
					if (arg0.getClickCount() == 2) {
						NPSHandlers.createNPSHandlersInst().includeAsInput();
					}
				} else if (arg0
						.getSource()
						.equals(
								ScaResultsAvailComp.createSetResAvailInst().availableOutputsTree)) {
					if (arg0.getClickCount() == 2) {
						ScaHandlers.createScaHandInst().includeAsInput();
					}

				} else if (arg0
						.getSource()
						.equals(
								BQLResAvailComp.createBQLResAvailInst().resultsAvailableTree)) {
					if (arg0.getClickCount() == 2) {
						BqlHandlers.createBqlHandlerInst().includeAsInput();
					}
				} else if (arg0
						.getSource()
						.equals(
								TableTransformations.createTableTrasGUIInst().resultsAvailTree)) {
					if (arg0.getClickCount() == 2) {
						TabTransformationsHandler
								.createTableTransformationInstance()
								.includeAsInput();
					}
				} else if (arg0
						.getSource()
						.equals(
								NcaParameterUnitsDispGui
										.createNcaParUnitsDisInst().parameterUnitsTable)) {

					NcaParameterUnitsTableHandler
							.createNvaParUnitsTabHandInst()
							.preferredUnitsHandler();

				} else if(arg0.getSource().equals(NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable)){
					ncaParameterNamesHandler.createInstance().handler();
				}
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
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {

		try {
			if(DDViewLayer.createPEInstance().isProjectCreation == true){
				
			} else {

				ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

				if (arg0.getSource() == DDViewLayer.createPEInstance().tree1) {
					PEHandlers.createPEHanderInst()
							.projectExplorerSelctionHandler();
				} else if ((appInfo
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
										.getSelectedSheetIndex()).getAnalysisType()
						.equals("nca"))
						&& (arg0.getSource() == NcaSetupAvailableComp
								.createNcaSetupAvailCompInst().availableComponentsTree)) {
					NcaSetupAvailCompTreeValueChangeHandler
							.createNcaavailableCompHandlerInst()
							.availableCompHandler();
				} else if ((appInfo
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
										.getSelectedSheetIndex()).getAnalysisType()
						.equals("nca"))
						&& (arg0.getSource() == NcaResultsAvailableComp
								.createNcaResAvailCompInst().availableOutputsTree)) {
					NcaResultsAvailResTreeValueChangeHand.createNcaResTreeInst()
							.availableResults();
				} else if ((appInfo
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
										.getSelectedSheetIndex()).getAnalysisType()
						.equals("plotmaven"))
						&& (arg0.getSource() == PMSetupAvailComp
								.createPMAvailCompInst().plotMavenAvailableComponentsTree)) {
					PlotMavenHandlers.createPlotMavenHandlerInst()
							.availableCompTreeHandler();
				} else if ((appInfo
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
										.getSelectedSheetIndex()).getAnalysisType()
						.equals("plotmaven"))
						&& (arg0.getSource() == PMResultsAvailComp
								.createPmSetupAvailCompInst().resultsAvailableTree)) {
					PlotMavenHandlers.createPlotMavenHandlerInst()
							.resultsCompTreeHandler();
				} else if ((appInfo
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
										.getSelectedSheetIndex()).getAnalysisType()
						.equals("desstats"))
						&& (arg0.getSource() == DesStatSetupAvailComp
								.createDesStatAvailComp().availableComponentsTree)) {
					DSHandlers.createDesStatEventHandlerInst()
							.setupAvailableTreeSelectionHandler();
				} else if ((appInfo
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
										.getSelectedSheetIndex()).getAnalysisType()
						.equals("desstats"))
						&& (arg0.getSource() == DesStatResAvailComp
								.createDesStatResAvailInst().availableOutputsTree)) {
					DSHandlers.createDesStatEventHandlerInst()
							.resAvailOutputsTreeHandler();
				} else if ((appInfo
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
										.getSelectedSheetIndex()).getAnalysisType()
						.equals("sca"))
						&& (arg0.getSource() == ScaSetupAvailComp
								.createSetupAvailCompInst().availableComponentsTree)) {
					ScaHandlers.createScaHandInst().availableCompTreeHandler();
				} else if ((appInfo
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
										.getSelectedSheetIndex()).getAnalysisType()
						.equals("sca"))
						&& (arg0.getSource() == ScaResultsAvailComp
								.createSetResAvailInst().availableOutputsTree)) {
					ScaHandlers.createScaHandInst().availableOutputsTreeHandler();
				} else if ((appInfo
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
										.getSelectedSheetIndex()).getAnalysisType()
						.equals("nps"))
						&& (arg0.getSource() == NPSSetupAvailComp
								.createNPSSetupAvailCompInst().availableComponentsTree)) {
					/*NPSHandlers.createNPSHandlersInst()
							.availaSetupCompTreeHandler();*/
					
					NPSSetupAvailComp.createNPSSetupAvailCompInst().prepareInitialSetup();
				
				
				} else if ((appInfo
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
										.getSelectedSheetIndex()).getAnalysisType()
						.equals("nps"))
						&& (arg0.getSource() == NPSResultsAvailComp
								.createNPSResAvailInst().availableOutputsTree)) {
					NPSHandlers.createNPSHandlersInst()
							.availResultsTreeSelctionHandler();
				}  else if ((appInfo
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
										.getSelectedSheetIndex()).getAnalysisType()
						.equals("bql"))
						&& (arg0.getSource() == BQLSetupAvailComp
								.createBqlSetAvailInst().availableComponentsTree)) {
					BQLSetupAvailCompTreeHandlers.createBQLSetupAvailhandlerInst()
							.availCompHandler();
				} else if ((appInfo
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
										.getSelectedSheetIndex()).getAnalysisType()
						.equals("bql"))
						&& (arg0.getSource() == BQLResAvailComp
								.createBQLResAvailInst().resultsAvailableTree)) {
					BqlResultsAvailableCompTreeHandler.createBQLResAvailableInst()
							.moveToFronthandler();
				}else if ((arg0.getSource() == CaSetupAvailableCompCreator
					.createPdSetupAvailCompInst().availableComponentsTree)) {

				CaSetupDispCompGuiCreator.createPdMainScrSetupDispGui()
						.prepareInitialSetup();
			} else if ((arg0.getSource() == CaResultAvailCompDisplayer
					.createPdResAvailCompInst().availableOutputsTree)) {
				CaResultDispCompCreator.createCaResDispCompInst()
						.displayProcessResults();
			}
				 else if ((appInfo
						.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo
						.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType().equals("tt"))
						&& (arg0.getSource() == TableTransformations.createTableTrasGUIInst().availCompTree)) {
					TabTransformationsHandler.createTableTransformationInstance()
							.availableCompHandler();
				}else if((appInfo
						.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo
								.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType().equals("invitro"))&&(arg0.getSource() == InVitroSetupAvailableCompCreator
						.createInVitroSetupAvailCompInst().availableComponentsTree))
				{
					InVitroSetupDispCompGuiCreator.createInVitroMainScrSetupDispGui()
							.prepareInitialSetup();
				} else if(arg0.getSource() == CaIpUserSelectionGui.createCAIpUserSelecInst().availableComponentsTree){
					//write the handler here
					CaIpUserSelectionHandler caIpHandler = new CaIpUserSelectionHandler();
					caIpHandler.setupTreeSelectionHandler();
				}

			
			}
			
			
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
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {


		try {
			if(arg0.getDocument().equals(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nParamTF.getDocument())){
				boolean isNum = AsciiSelectionHandler.createAsciiCommHandInst().isNumber(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nParamTF.getText());
				if(isNum == true){
					AsciiSelectionHandler.createAsciiCommHandInst().nParamUpdater(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nParamTF, true);
					AsciiNonEditableAreaHandler.createAsciiNonEditHandnst().updateNonEditArea();
				} else {
					
				}
				
			} else if(arg0.getDocument().equals(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nSecParamTF.getDocument())){
				boolean isNum = AsciiSelectionHandler.createAsciiCommHandInst().isNumber(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nSecParamTF.getText());
				if(isNum == true){
					AsciiSelectionHandler.createAsciiCommHandInst().nParamUpdater(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nSecParamTF, false);
					AsciiNonEditableAreaHandler.createAsciiNonEditHandnst().updateNonEditArea();
				} else {
					
				}
				
			} else if(arg0.getDocument().equals(AsciiDosingNodeHandler.createAsciiDoseNodeHand().nConTF.getDocument())){

				boolean isNum = AsciiSelectionHandler.createAsciiCommHandInst().isNumber(AsciiDosingNodeHandler.createAsciiDoseNodeHand().nConTF.getText());
				if(isNum == true){
					AsciiDosingNodeHandler.createAsciiDoseNodeHand().nDoseTFHandler();
				} else {
					
				}
				
			
			} else if(arg0.getDocument().equals(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nDerTF.getDocument())){
				AsciiNonEditableAreaHandler.createAsciiNonEditHandnst().updateNonEditArea();
			} else if((arg0.getDocument().equals(AsciiDeclarationDispGui.createAsciiTempInst().asciiDeclareTA.getDocument()))
					||(arg0.getDocument().equals(AsciiAlgEqcDispGui.createAsciiFuncInst().asciiAlgEqnTA.getDocument()))
					||(arg0.getDocument().equals(AsciiDiffRqnDispGui.createAsciiDiffInst().asciiDiffEqnTA.getDocument()))
					||(arg0.getDocument().equals(AsciiInitializationDispGui.createAsciiStartInst().asciiInitializationTA.getDocument()))
					||(arg0.getDocument().equals(AsciiEqnSecParamDispGui.createAsciiSecInst().asciiEqnSecParamTA.getDocument()))){
				
				AsciiNonEditableAreaHandler.createAsciiNonEditHandnst().updateNonEditArea();
			}
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
	public void removeUpdate(DocumentEvent arg0) {


		try {
			if(arg0.getDocument().equals(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nParamTF.getDocument())){
				boolean isNum = AsciiSelectionHandler.createAsciiCommHandInst().isNumber(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nParamTF.getText());
				if(isNum == true){
					AsciiSelectionHandler.createAsciiCommHandInst().nParamUpdater(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nParamTF, true);
					
				} else {
					
				}
				AsciiNonEditableAreaHandler.createAsciiNonEditHandnst().updateNonEditArea();
				
			} else if(arg0.getDocument().equals(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nSecParamTF.getDocument())){
				boolean isNum = AsciiSelectionHandler.createAsciiCommHandInst().isNumber(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nSecParamTF.getText());
				if(isNum == true){
					AsciiSelectionHandler.createAsciiCommHandInst().nParamUpdater(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nSecParamTF, false);
					
				} else {
					
				}
				AsciiNonEditableAreaHandler.createAsciiNonEditHandnst().updateNonEditArea();
			} else if(arg0.getDocument().equals(AsciiDosingNodeHandler.createAsciiDoseNodeHand().nConTF.getDocument())){
				boolean isNum = AsciiSelectionHandler.createAsciiCommHandInst().isNumber(AsciiDosingNodeHandler.createAsciiDoseNodeHand().nConTF.getText());
				if(isNum == true){
					AsciiDosingNodeHandler.createAsciiDoseNodeHand().nDoseTFHandler();
				} else {
					
				}
				
			
			} else if(arg0.getDocument().equals(AsciiSelectionDispGUI.createAsciiCommandGuiInst().nDerTF.getDocument())){
				AsciiNonEditableAreaHandler.createAsciiNonEditHandnst().updateNonEditArea();
			} else if((arg0.getDocument().equals(AsciiDeclarationDispGui.createAsciiTempInst().asciiDeclareTA.getDocument()))
					||(arg0.getDocument().equals(AsciiAlgEqcDispGui.createAsciiFuncInst().asciiAlgEqnTA.getDocument()))
					||(arg0.getDocument().equals(AsciiDiffRqnDispGui.createAsciiDiffInst().asciiDiffEqnTA.getDocument()))
					||(arg0.getDocument().equals(AsciiInitializationDispGui.createAsciiStartInst().asciiInitializationTA.getDocument()))
					||(arg0.getDocument().equals(AsciiEqnSecParamDispGui.createAsciiSecInst().asciiEqnSecParamTA.getDocument()))){
				
				AsciiNonEditableAreaHandler.createAsciiNonEditHandnst().updateNonEditArea();
			}
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
	public void valueChanged(ListSelectionEvent arg0) {

		try {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("nca"))
					&& (arg0.getSource() == NcaMappingDispGui
							.createMappingInstance().availableColumnsList)) {

				NcaMappingHandlers.createNCAMapHandInst()
						.availableColumnListSelectionEvent();
			} else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("nca"))
					&& (arg0.getSource() == NcaMappingDispGui
							.createMappingInstance().sortVariablesList)) {
				NcaMappingHandlers.createNCAMapHandInst()
						.sortCOlumnsListSelectionEvent();
			} else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("plotmaven"))
					&& (arg0.getSource() == PMSetupDispComp
							.createPMSetupDisCompInst().plotMavenavailableColumnsList)) {
				PlotMavenHandlers.createPlotMavenHandlerInst()
						.availableColumnsListSelctionHandler();
			} else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("plotmaven"))
					&& (arg0.getSource() == PMSetupDispComp
							.createPMSetupDisCompInst().plotMavenSortVariablesList)) {
				PlotMavenHandlers.createPlotMavenHandlerInst()
						.sortVariablesListSelectionHandler();
			} else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("desstats"))
					&& (arg0.getSource() == DesStatSetupDespComp
							.createDesStatSetupDispInst().availableColumnsList)) {
				DSHandlers.createDesStatEventHandlerInst()
						.availableColumnListSelectionHandlr();
			} else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("desstats"))
					&& (arg0.getSource() == DesStatSetupDespComp
							.createDesStatSetupDispInst().sortVariablesList)) {
				DSHandlers.createDesStatEventHandlerInst()
						.sortVarListSlectionHandler();
			} else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("desstats"))
					&& (arg0.getSource() == DesStatSetupDespComp
							.createDesStatSetupDispInst().carryAlongList)) {
				DSHandlers.createDesStatEventHandlerInst()
						.caryAlongListSelectionHandler();
			} else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("sca"))
					&& (arg0.getSource() == ScaSetupDispComp
							.createSetupDispConpInst().availableColumnsList)) {
				ScaHandlers.createScaHandInst().availableColListHandler();
			} else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("sca"))
					&& (arg0.getSource() == ScaSetupDispComp
							.createSetupDispConpInst().sortVariablesList)) {
				ScaHandlers.createScaHandInst().sortVarListHandler();
			} else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("nps"))
					&& (arg0.getSource() == NPSSetupDispComp
							.createNpsSetDispInst().availableColumnsList)) {
				NPSHandlers.createNPSHandlersInst()
						.availableColumnsSelectionHandler();
			} else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("nps"))
					&& (arg0.getSource() == NPSSetupDispComp
							.createNpsSetDispInst().sortVariablesList)) {
				NPSHandlers.createNPSHandlersInst()
						.sortVarialbesListSelectionHandler();
			} else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("bql"))
					&& (arg0.getSource() == BQLSetupDispComp
							.createBQLSetupDispInst().sortVariablesList)) {
				BQLMappingHandler.createBqlMappingHandInst()
						.sortColumnListHandler();
			} else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("bql"))
					&& (arg0.getSource() == BQLSetupDispComp
							.createBQLSetupDispInst().availableColumnsList)) {
				BQLMappingHandler.createBqlMappingHandInst()
						.availableColumnListSelectionEvent();
			} else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("bql"))
					&& (arg0.getSource() == BQLSetupDispComp
							.createBQLSetupDispInst().carryAlongList)) {
				BQLMappingHandler.createBqlMappingHandInst()
						.carryAlongListSelectionHandler();
			} else if (arg0.getSource() == ImportFile
					.createDispContentsInstance().sheetLablesList) {
				SheetLablsMouseListEventHandler.createSheetLableInstance(
						ImportFile.createDispContentsInstance(),
						ImportFile.createDispContentsInstance().sheetLablesList
								.getSelectedIndex()).setData(
						ImportFile.createDispContentsInstance().sheetLablesList
								.getSelectedIndex());
			} else if (arg0.getSource() == CaMappingDispGuiCreator.createMappingInstance().caavailableColumnsList) {
				CaMapingHandler mapHandInst = CaMapingHandler.createCaMapHandInst();
				mapHandInst.settingVarForAvailableColList();
			} else if (arg0.getSource() == CaMappingDispGuiCreator.createMappingInstance().casortVariablesList) {
				CaMapingHandler mapHandInst = CaMapingHandler.createCaMapHandInst();
				mapHandInst.sortVarListEvent();
			}  else if(arg0.getSource() == AsciiDosingNodeHandler.createAsciiDoseNodeHand().doseProfilesList){
				AsciiDosingNodeHandler.createAsciiDoseNodeHand().asciiProfListHandler();
			}
			
			
			else if ((appInfo
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
									.getSelectedSheetIndex()).getAnalysisType()
					.equals("tt"))
					&& (arg0.getSource() == TableTransformations
							.createTableTrasGUIInst().availableColumnsList)) {
				TabTransformationsHandler.createTableTransformationInstance()
						.availableVarListSelectionHandler();
			} else if (arg0.getSource() == TableTransformations
					.createTableTrasGUIInst().sortVariablesList) {
				TabTransformationsHandler.createTableTransformationInstance()
						.sortVariableListSelectionHandler();
			}

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
	public void focusGained(FocusEvent arg0) {
		try {
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
			
			if (arg0.getSource() == NcaMappingDispGui.createMappingInstance().yVariableTextField) {

				System.out.println("yvar text field focus gained");
				NcaMappingHandlers.createNCAMapHandInst()
						.yVarTextFieldHandler();
			} else if (arg0.getSource() == NcaMappingDispGui
					.createMappingInstance().xVariableTextField) {
				NcaMappingHandlers.createNCAMapHandInst()
						.xVarTextFieldHandler();
			} else if (arg0.getSource() == NcaMappingDispGui
					.createMappingInstance().subjectTextField) {
				NcaMappingHandlers.createNCAMapHandInst()
						.subJectTextFieldAction();
			} else if (arg0.getSource() == NcaMappingDispGui
					.createMappingInstance().volumeVariableTextField) {
				NcaMappingHandlers.createNCAMapHandInst()
						.volumeTextFieldAction();
			} else if (arg0.getSource() == NcaMappingDispGui
					.createMappingInstance().endTimeVariableTextField) {
				NcaMappingHandlers.createNCAMapHandInst()
						.endTimeTextFieldAction();
			}

			else if (arg0.getSource() == PMSetupDispComp
					.createPMSetupDisCompInst().plotMavenGroupVariableTextField) {
				PlotMavenHandlers.createPlotMavenHandlerInst()
						.groupVarTextField();
			} else if (arg0.getSource() == PMSetupDispComp
					.createPMSetupDisCompInst().downVariablesTextField) {
				PMSetupDispComp.createPMSetupDisCompInst().isFromAvailableToDown = false;
			} else if (arg0.getSource() == PMSetupDispComp
					.createPMSetupDisCompInst().upVariableTextField) {
				PMSetupDispComp.createPMSetupDisCompInst().isFromAvailableToUp = false;
			} else if (arg0.getSource() == DesStatSetupDespComp
					.createDesStatSetupDispInst().weightVariableTextField) {
				DSHandlers.createDesStatEventHandlerInst()
						.weightTextFieldFocusHandler();
			} else if (arg0.getSource() == ScaSetupDispComp
					.createSetupDispConpInst().conbcentrationVariableTextField) {
				ScaHandlers.createScaHandInst().condVarTextFocusHandler();
			} else if (arg0.getSource() == ScaSetupDispComp
					.createSetupDispConpInst().timeVariableTextField) {
				ScaHandlers.createScaHandInst().timeVarTextFieldHandler();
			} else if (arg0.getSource() == ScaSetupDispComp
					.createSetupDispConpInst().effectVariableTextField) {
				ScaHandlers.createScaHandInst().effectVarHandler();
			} else if (arg0.getSource() == NPSOpt.createNPSOptInst().tauInNPSTextField) {
				NPSHandlers.createNPSHandlersInst().tauInNpsTextFocusHandler();
			} else if (arg0.getSource() == NPSSetupDispComp
					.createNpsSetDispInst().conbcentrationVariableTextField) {
				NPSHandlers.createNPSHandlersInst().concVarTextFocusHandler();
			} else if (arg0.getSource() == NPSSetupDispComp
					.createNpsSetDispInst().timeVariableTextField) {
				NPSHandlers.createNPSHandlersInst().timeVarTextFocusHandler();
			} else if (arg0.getSource() == BQLSetupDispComp
					.createBQLSetupDispInst().xVariableTextField) {
				BQLMappingHandler.createBqlMappingHandInst()
						.xVarTextFieldHandler();
			} else if (arg0.getSource() == BQLSetupDispComp
					.createBQLSetupDispInst().yVariableTextField) {
				BQLMappingHandler.createBqlMappingHandInst()
						.yVarTextFieldHandler();
			} else if (arg0.getSource() == BQLSetupDispComp
					.createBQLSetupDispInst().statusCodeTextField) {
				BQLMappingHandler.createBqlMappingHandInst()
						.statusCodeTextFieldHandler();
			} else if (arg0.getSource() == TableTransformations
					.createTableTrasGUIInst().xVariableTextField) {
				TabTransformationsHandler.createTableTransformationInstance()
						.xVarTextFieldHandler();
			} else if (arg0.getSource() == TableTransformations
					.createTableTrasGUIInst().yVariableTextField) {
				TabTransformationsHandler.createTableTransformationInstance()
						.yVarTextFieldHandler();
			}

			else if ((arg0.getSource() == CaMappingDispGuiCreator.createMappingInstance().yVariableTextField)) {
				CaMapingHandler mapHandlerInst = CaMapingHandler.createCaMapHandInst();
				mapHandlerInst.yVarTfEvent();
			} else if ( (arg0.getSource() == CaMappingDispGuiCreator.createMappingInstance().xVariableTextField)) {
				CaMapingHandler mapHandlerInst = CaMapingHandler.createCaMapHandInst();
				mapHandlerInst.xVarTfEvent();
			} else if ((analysisType.equals("pd")
					|| analysisType.equals("pkpdlink") || analysisType
					.equals("irm"))
					&& (arg0.getSource() == CaWeightDoseDispGuiCreator
							.createCaWeightDoseInstance().responseUnitTextField)) {
				PDResponseUnitActionHandeler responseUnitActInst = new PDResponseUnitActionHandeler();
				responseUnitActInst.responseUnitActionEvent();
			} else if(arg0.getSource() == CaMappingDispGuiCreator.createMappingInstance().caFuncTF){
				CaMapingHandler.createCaMapHandInst().isAvailableToFunc = false;
				CaMappingDispGuiCreator.createMappingInstance().caFuncButton.setIcon(CaMappingDispGuiCreator.createMappingInstance().toLeft);
			}
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

	@Override
	public void updateView(DDModel ddModel) throws RowsExceededException,
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



		if (appInfo
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
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("nca")) {

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
									.getSelectedSheetIndex()).getNcaInfo()
					.setWorkSheetOutputs(ddModel.getWsOutputs());

			NcaOutputGeneration ncaOutputGen = NcaOutputGeneration
					.createNcaOutGeneration();
			ncaOutputGen.ncaOutputGeneration();
		} else if (appInfo
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
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("desstats")) {

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
									.getSelectedSheetIndex()).getDesStatsInfo()
					.setWorkSheetOutputs(ddModel.getWsOutputs());

			DesStatsoutputGeneration desStatoutGen = DesStatsoutputGeneration
					.createDesStatOutInst();
			desStatoutGen.desStatOutGeneration();
		} else if (appInfo
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
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("nps")) {

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
									.getSelectedSheetIndex()).getNpsInfo()
					.setWorkSheetOutputs(ddModel.getWsOutputs());

			NonparametricoutputGeneration npsOutGen = NonparametricoutputGeneration
					.createNpsOutGenInst();
			npsOutGen.npsOutputGeneration();
		} else if (appInfo
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
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("sca")) {

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
									.getSelectedSheetIndex()).getScaInfo()
					.setWorkSheetOutputs(ddModel.getWsOutputs());

			ScaoutputGeneration scaOutGen = ScaoutputGeneration
					.createScaoutInst();
			scaOutGen.scaoutGeneration();

		} else if (appInfo
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
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("pk")) {
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
									.getSelectedSheetIndex()).getPkInfo()
					.setWorkSheetOutputs(ddModel.getWsOutputs());

			// output generation has to be done
			CaOutputDisplayer caoutGen = CaOutputDisplayer.createCaOutInst();
			caoutGen.caOutputGeneration();

		} else if (analysisType.equals("pd")) {
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
									.getSelectedSheetIndex()).getPdInfo()
					.setWorkSheetOutputs(ddModel.getWsOutputs());

			// output generation has to be done
			CaOutputDisplayer caoutGen = CaOutputDisplayer.createCaOutInst();
			caoutGen.caOutputGeneration();

		} else if (analysisType.equals("mm")) {
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
									.getSelectedSheetIndex()).getMmInfo()
					.setWorkSheetOutputs(ddModel.getWsOutputs());

			// output generation has to be done
			CaOutputDisplayer caoutGen = CaOutputDisplayer.createCaOutInst();
			caoutGen.caOutputGeneration();

		} else if (analysisType.equals("pkpdlink")) {
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
									.getSelectedSheetIndex()).getPkpdLinkInfo()
					.setWorkSheetOutputs(ddModel.getWsOutputs());

			// output generation has to be done
			CaOutputDisplayer caoutGen = CaOutputDisplayer.createCaOutInst();
			caoutGen.caOutputGeneration();

		} else if (analysisType.equals("irm")) {
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
									.getSelectedSheetIndex()).getIrInfo()
					.setWorkSheetOutputs(ddModel.getWsOutputs());

			// output generation has to be done
			CaOutputDisplayer caoutGen = CaOutputDisplayer.createCaOutInst();
			caoutGen.caOutputGeneration();

		
		}  else if (analysisType.equals("ascii")) {
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
							.getSelectedSheetIndex()).getAsciiInfo()
			.setWorkSheetOutputs(ddModel.getWsOutputs());

	// output generation has to be done
	CaOutputDisplayer caoutGen = CaOutputDisplayer.createCaOutInst();
	caoutGen.caOutputGeneration();


}else if (appInfo
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
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("tabmaven")) {

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
									.getSelectedSheetIndex()).getTmInfo()
					.setWorkSheetOutputs(ddModel.getWsOutputs());

			TableMavenOutputGeneration tableMavenOutGen = TableMavenOutputGeneration
					.createTableOutputInst();
			tableMavenOutGen.tableMavenOutputGeneration();

		} else if (appInfo
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
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("bql")) {
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
									.getSelectedSheetIndex()).getBqlInfo()
					.setWorkSheetOutputs(ddModel.getWsOutputs());

			BqlOutputGeneration bqlOutputGen = BqlOutputGeneration
					.createBqloutGenInst();
			bqlOutputGen.generateOutput();
		} else if (appInfo
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
								.getSelectedSheetIndex()).getAnalysisType()
				.equals("tt")) {
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
									.getSelectedSheetIndex()).getTtInfo()
					.setWorkSheetOutputs(ddModel.getWsOutputs());

			TToutputGeneration.createttOutGenInst().outputGeneration();

		} else if(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
				.getAnalysisType().equals("plotmaven")){
			
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getSelectedSheetIndex()).getPlotInfo().setWorkSheetOutputs(ddModel.getWsOutputs());
			
			PlotMavenoutputGeneration pmOutGen = PlotMavenoutputGeneration.createPMoutGen();
			pmOutGen.getnetateOutput();
			

		}

	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		if (arg0
				.getSource()
				.equals(
						ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
								.getModel())) {
			try {
				ImpDataTableValueChangeHandler.createImpTableHandlerInst()
						.valueChangeHandler();
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

		} else{


			try {
				if(arg0.getSource().equals(AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPrimParamTable.getModel())){
					
						AsciiParamTablesHandler.createAsciiParamHandInst().asciiPriParamTabHandler(AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPrimParamTable, true);
						AsciiNonEditableAreaHandler.createAsciiNonEditHandnst().updateNonEditArea();
						
						
				} else if(arg0.getSource().equals(AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamTable.getModel())){
					
						AsciiParamTablesHandler.createAsciiParamHandInst().asciiPriParamTabHandler(AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamTable, false);
						AsciiNonEditableAreaHandler.createAsciiNonEditHandnst().updateNonEditArea();
				} else if(arg0.getSource().equals(AsciiDosingNodeHandler.createAsciiDoseNodeHand().asciiDoseTable.getModel())){
					AsciiDosingNodeHandler.createAsciiDoseNodeHand().asciiDoseTableHandler();
				}
			
		 
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
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(ImportedDataSheet.createImportedDataSheetInstance().numericRadioButton)){


			ImportedDataSheetHandlers.createImpSheetHandInstance().reqDecimalHandling = true;
			ImportedDataSheetHandlers.createImpSheetHandInstance()
					.reqDecimalsFunc();
			ImportedDataSheetHandlers.createImpSheetHandInstance().reqDecimalHandling = false;

		
		}
		
		if(e.getSource().equals(ImportedDataSheet.createImportedDataSheetInstance().optionalDecimalsRadioButton)){

			ImportedDataSheetHandlers.createImpSheetHandInstance().optDecimalHandling = true;
			ImportedDataSheetHandlers.createImpSheetHandInstance()
					.optionalDecFunc();
			ImportedDataSheetHandlers.createImpSheetHandInstance().optDecimalHandling = false;
		
		}
		
	}

}
