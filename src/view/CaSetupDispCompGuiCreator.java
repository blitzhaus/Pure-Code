package view;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.record.formula.atp.AnalysisToolPak;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CaSetupDispCompGuiCreator {

	public CaSetupDispCompGuiCreator() {

	}

	public static CaSetupDispCompGuiCreator SETUP_DISP_COMP_GUI = null;

	public static CaSetupDispCompGuiCreator createPdMainScrSetupDispGui()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (SETUP_DISP_COMP_GUI == null) {
			SETUP_DISP_COMP_GUI = new CaSetupDispCompGuiCreator();
		}
		return SETUP_DISP_COMP_GUI;
	}

	CaMappingDispGuiCreator pdMapDispGui;
	CaInitEstimateDispGuiCreator pdInitEstimateDispGui;
	CaDosingDispGuiCreator pdDosingDispGui;
	CaLinkParamInitEstimateGuiCreator linkParamInitEstimateDispGui;

	CaParamUnitsDispGuiCreator pdParameterUnitsDispGui;
	
	AsciiSelectionDispGUI asciiCommandDispGuiInst;

	// mapping change detection
	String prevConcColName;
	String prevTimeColName;
	String prevFuncColName;
	boolean timeColChanged;
	boolean concColChanged;
	boolean funcColChanged;
	boolean sortVarChanged;
	int previousSize;
	String[] previousSortVariables;

	public void pdSetupDispCompGuiCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {

		// This internal frame will contain many internal frames corresponding
		// to each of the components available
		initialization();
		CaMappingDispGuiCreator.createMappingInstance();
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst();
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().caParameterUnitsDispGuiCreation();
		
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
		
		if(analysisType.equals("ascii"))
		{
			asciiDispComponentsCreation();

		
		}
		
		
		

	}

	AsciiNonEditableArea asciiNonEditInst;
	AsciiPrimParametersDispGUI asciiParamDispGui;
	AsciiSecParameterDispGui asciiSecParamDispGui;
	AsciiDeclarationDispGui asciiTempDispGui;
	AsciiAlgEqcDispGui asciiFuncDispGui;
	AsciiInitializationDispGui asciiStartDispGui;
	AsciiDiffRqnDispGui asciiDiffDispGui;
	AsciiEqnSecParamDispGui asciiSecDispGui;
	AsciiInitialEstGui asciiInitEstGui;
	private void asciiDispComponentsCreation() throws RowsExceededException, WriteException, BiffException, IOException {
		
		
		asciiInitEstGui = AsciiInitialEstGui.createAsciiinitGuiInst();
		asciiInitEstGui.createInitEstimatesScreen();
		
		asciiCommandDispGuiInst = AsciiSelectionDispGUI.createAsciiCommandGuiInst();
		asciiCommandDispGuiInst.asciiSelectionGuiCreation();
		asciiNonEditInst = AsciiNonEditableArea.createAsciiNonEditInst();
		asciiParamDispGui = AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst(); 
		asciiSecParamDispGui = AsciiSecParameterDispGui.createAsciiSecParamGuiInst();
		asciiTempDispGui = AsciiDeclarationDispGui.createAsciiTempInst();
		asciiFuncDispGui = AsciiAlgEqcDispGui.createAsciiFuncInst();
		asciiStartDispGui = AsciiInitializationDispGui.createAsciiStartInst();
		asciiDiffDispGui = AsciiDiffRqnDispGui.createAsciiDiffInst();
		asciiSecDispGui = AsciiEqnSecParamDispGui.createAsciiSecInst();
		
	}

	private void initialization() {
		prevConcColName = "";
		prevFuncColName = "";
		prevTimeColName = "";
		previousSize = 0;
		previousSortVariables = new String[1];
		timeColChanged = false;
		concColChanged = false;
		sortVarChanged = false;

	}

	public void prepareInitialSetup() throws RowsExceededException, WriteException,
			BiffException, IOException {

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

		if (CaSetupAvailableCompCreator.createPdSetupAvailCompInst().availableComponentsTree
				.getSelectionPath() != null) {

			String[] pathSplits = detectSetupPaths();

			mappingChangeDetection();
			ProcessingInputsInfo procInputInst = CaMapingHandler
					.createCaMapHandInst().copyProcessingInput();
			if (procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true) {
				String message = decideMessage();
				CaMapingHandler.createCaMapHandInst().showMessage(message);
				
				boolean ifValidmapping = validationOfMapping();
				
				if(ifValidmapping == true){
					detectProfiles();
					if(analysisType.equals("ascii")){
						fillFuncHasSet();
						
						
					}
				}
					
				else 
				{
					message = "Invalid Mapping";
					CaMapingHandler.createCaMapHandInst().showMessage(message);
					procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(false);
					
				}

			}

			if (CaSetupAvailableCompCreator.createPdSetupAvailCompInst().availableComponentsTree
					.getMinSelectionRow() == 0) {
				ImportedDataSheet.importedDataSheetFrame.moveToFront();
				DDViewLayer.createFeaturesPanelInstance().PreviewLable
						.setVisible(true);
			}

			if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Mapping]")) {
				doMapping();
			}
			else if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Dosing]")) {

				if(analysisType.equals("ascii")){
					if(procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true){
						performAsciiCommonFunctionality();
						
					}
					AsciiDosingNodeHandler.createAsciiDoseNodeHand().asciiDoseIF.moveToFront();
					AsciiDosingNodeHandler.createAsciiDoseNodeHand().asciiProfIF.moveToFront();
					

					
				} else {
					if (((analysisType.equals("pk") || analysisType.equals("mm")) && procInputInst.getModelInputTab1Obj().getModelNumberForCA() > 0) ||
							((analysisType.equals("pkpdlink") || analysisType.equals("irm")) && procInputInst.getModelInputTab1Obj().getModelNumberForLinkModel() > 0)) {
						doDosing();
						CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
								.setFocusable(true);

						CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
								.setFocusable(false);
						if (analysisType.equals("pk") || analysisType.equals("mm")
								|| analysisType.equals("pd")) {
							CaOptionsGuiCreator.createPdOptionsInstance().moduleTabs
									.setSelectedIndex(1);
						} else if (analysisType.equals("pkpdlink")
								|| analysisType.equals("irm")) {
							CaOptionsGuiCreator.createPdOptionsInstance().moduleTabs
									.setSelectedIndex(2);
						}

						CaDosingDispGuiCreator.createCaDosingDispGui().DosingInternalFrame
								.moveToFront();

					} else {
						String message = "Please choose a library model";
						CaMapingHandler.createCaMapHandInst().showMessage(message);

					}

				}
			}

			else if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Unit]")) {
				provideUnits();

			}
			
			else if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Ascii Format]")) {
				
				
			} else if(pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Selection]")){
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.moveToFront();
				AsciiNonEditableArea.createAsciiNonEditInst().asciiNonEditAreaIF.moveToFront();
				
			} else if(pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Primary Parameters]")){
				
				if(procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true){
					performAsciiCommonFunctionality();
					/*if(funcColChanged == true){
						AsciiParamTablesHandler.createAsciiParamHandInst().resetFuncTableColumns();
					}*/
				}
				AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiParametersIF.moveToFront();
				AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncIF.moveToFront();
				if(CaMappingDispGuiCreator.createMappingInstance().caFuncTF.getText().toString().equals("")){
					AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable.setVisible(false);
				}
				AsciiNonEditableArea.createAsciiNonEditInst().asciiNonEditAreaIF.moveToFront();
			} else if(pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Secondary Parameters]")){
				
				AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamIF.moveToFront();
				AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncIF.moveToFront();
				if(CaMappingDispGuiCreator.createMappingInstance().caFuncTF.getText().toString().equals("")){
					AsciiSecParameterDispGui.createAsciiSecParamGuiInst().asciiSecParamFuncTalbe.setVisible(false);
				}
				AsciiNonEditableArea.createAsciiNonEditInst().asciiNonEditAreaIF.moveToFront();
				 
			} else if(pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Declaration]")){
				AsciiDeclarationDispGui.createAsciiTempInst().asciiDecIF.moveToFront();
				AsciiNonEditableArea.createAsciiNonEditInst().asciiNonEditAreaIF.moveToFront();
			} else if(pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Algebraic_Equations]")){
				AsciiAlgEqcDispGui.createAsciiFuncInst().asciiAlgEqnIF.moveToFront();
				AsciiNonEditableArea.createAsciiNonEditInst().asciiNonEditAreaIF.moveToFront();
			} else if(pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Initialization]")){
				AsciiInitializationDispGui.createAsciiStartInst().asciiInitializationIF.moveToFront();
				AsciiNonEditableArea.createAsciiNonEditInst().asciiNonEditAreaIF.moveToFront();
			} else if(pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Differential_equations]")){
				AsciiDiffRqnDispGui.createAsciiDiffInst().asciiDiffIF.moveToFront();
				AsciiNonEditableArea.createAsciiNonEditInst().asciiNonEditAreaIF.moveToFront();
			} else if(pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Equations_sec_param]")){
				AsciiEqnSecParamDispGui.createAsciiSecInst().asciiEqnSecParamIF.moveToFront();
				AsciiNonEditableArea.createAsciiNonEditInst().asciiNonEditAreaIF.moveToFront();
			}

			else if (pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Initial Estimate]")) {

				int tempModelNo;

				if(!analysisType.equals("ascii")){
					if (analysisType.equals("pkpdlink")
							|| analysisType.equals("irm")) {
						tempModelNo = procInputInst.getModelInputTab1Obj()
								.getModelNumberForLinkModel();
					} else {
						tempModelNo = procInputInst.getModelInputTab1Obj()
								.getModelNumberForCA();
					}

					if (tempModelNo > 0) {
						provideInitialEstimates();

						if (analysisType.equals("pd")) {

						} else {
							CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
									.setFocusable(false);
						}

						CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
								.setFocusable(true);

						if (analysisType.equals("pk") || analysisType.equals("mm")
								|| analysisType.equals("pd")) {
							CaOptionsGuiCreator.createPdOptionsInstance().moduleTabs
									.setSelectedIndex(2);
						} else if (analysisType.equals("pkpdlink")
								|| analysisType.equals("irm")) {
							CaOptionsGuiCreator.createPdOptionsInstance().moduleTabs
									.setSelectedIndex(3);
							disableInitParamOptions();

						}

					} 
				} else if(analysisType.equals("ascii")){
					if((procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true) || (
							AsciiInitialEstGui.createAsciiinitGuiInst().isInitEstimateUpdateReq == true)){
						AsciiInitEstimatesHandler.createAsciiInitEstInst().updateInitTable();
						AsciiInitialEstGui.createAsciiinitGuiInst().isInitEstimateUpdateReq = false;
					}
					/*AsciiInitialEstGui.createAsciiinitGuiInst().asciiInitEstIF.repaint();
					AsciiInitialEstGui.createAsciiinitGuiInst().asciiInitEstIF.validate();*/
					AsciiInitialEstGui.createAsciiinitGuiInst().asciiInitEstIF.moveToFront();
					
				} else {

					String message = "Please choose a library model";
					CaMapingHandler.createCaMapHandInst().showMessage(message);

				}
			}

			else if (pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Link Estimate]")) {

				if (procInputInst.getModelInputTab1Obj().getModelNumberForCA() > 0) {
					provideLinkInitialEstimates();
					CaDosingDispGuiCreator.createCaDosingDispGui().tableForDosing
							.setFocusable(false);
					CaInitEstimateDispGuiCreator.createCaInitEstGui().tableForInitialParameterValueForCA
							.setFocusable(false);
					CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().tableForLinkInitialParameterValueForCA
							.setFocusable(true);
					CaOptionsGuiCreator.createPdOptionsInstance().moduleTabs
							.setSelectedIndex(3);
					changeStateOfInitParamOptions(true);

				} else {

					String message = "Please choose a Link library model";
					CaMapingHandler.createCaMapHandInst().showMessage(message);
					
				}

			}

		}
	}

	private void performAsciiCommonFunctionality() throws RowsExceededException, WriteException, BiffException, IOException {
		ProcessingInputsInfo procInputInst = CaMapingHandler
		.createCaMapHandInst().copyProcessingInput();
		
		//do dosing
		AsciiDosingNodeHandler.createAsciiDoseNodeHand().doseNodeClickHandler();
		
		//do primary parameters
		if(funcColChanged == true){
			AsciiParamTablesHandler.createAsciiParamHandInst().resetFuncTableColumns();
		}
		
		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(false);
		
	}

	private void fillFuncHasSet() throws RowsExceededException, WriteException, BiffException, IOException {
		ProcessingInputsInfo procInputs = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		if(!CaMappingDispGuiCreator.createMappingInstance().caFuncTF.getText().toString().equals("")){
			procInputs.getMappingDataObj().setAsciiFuncHS(new HashSet<String>());
			
			String[][] local = CaMultipleLevelSortingProvider.createMultipleSortInstance().dataSorted;
			
			
			
			for(int i=0;i<local.length;i++){
				
					procInputs.getMappingDataObj().getAsciiFuncHS().add(local[i][procInputs.getMappingDataObj().getSortVariablesListVector().size()]);
					
					//add a hash set of strings to an array so as to store the parameters related to 
					//a specific function
					procInputs.getAsciiInitEstimates().getFuncArray().put(local[i][local[i].length-1],new HashSet<String>());
					
			}
			AsciiParamTablesHandler.createAsciiParamHandInst().resetFuncTableColumns();
			AsciiSelectionDispGUI.createAsciiCommandGuiInst().nFuncTF.setText(procInputs.getMappingDataObj().getAsciiFuncHS().size()+"");
			procInputs.getAsciiCommands().setnFunc(procInputs.getMappingDataObj().getAsciiFuncHS().size());
			funcColChanged = false;
		
		} else if(CaMappingDispGuiCreator.createMappingInstance().caFuncTF.getText().toString().equals("")){
			procInputs.getMappingDataObj().setAsciiFuncHS(new HashSet<String>());
			procInputs.getAsciiCommands().setnFunc(1);
			
		}
		
		
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputs);
		System.out.println();
		System.out.println();
		
	}

	

	private boolean validationOfMapping() {
		boolean ifValidMapping = false;
		ProcessingInputsInfo procInputs = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		if(procInputs.getModelInputTab1Obj().isSimulation() == false )
		{
			if(procInputs.getMappingDataObj().getxColumnName().equals("") 
					||  procInputs.getMappingDataObj().getYcolumnName().equals(""))
			{
				ifValidMapping = false;
			}else
			{
				ifValidMapping = true;
			}
			
		}else
		{
			if(procInputs.getMappingDataObj().getxColumnName().equals("") )
			{
				ifValidMapping = false;
			}else
			{
				ifValidMapping = true;
			}
			
		}
		
		
		return ifValidMapping;
	}

	private void changeStateOfInitParamOptions(boolean state)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueComboBox
				.setEnabled(state);
	
		if(procInputInst.getModelInputTab2Obj().getSourceOfIntParamValue() == 0)
		{
			if(procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 0
					&& procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 0
					&& procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 0)
			{
				CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
				.setEnabled(true);
		
				CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
				.setEnabled(true);
			
				CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
				.setEnabled(true);
			}else
			{
				if(procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 1)
					CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setEnabled(state);
				if(procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 1)
					CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
				.setEnabled(state);
		
				if(procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 1)
					CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
					.setEnabled(state);
			}
				
		}else if(procInputInst.getModelInputTab2Obj().getSourceOfIntParamValue() == 1)
		{
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setEnabled(false);
			
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setEnabled(false);
				
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
					.setEnabled(false);
		}
		
		if(procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 1)
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
				.setEnabled(state);
		if(procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 1)
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
				.setEnabled(state);
		
		if(procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 1)
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
				.setEnabled(state);
		if(procInputInst.getModelInputTab2Obj().getPreviouslyGenInitVal() == 1)
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().previouslyGenInitilParam
				.setEnabled(state);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().parameterBoundariesComboBox
				.setEnabled(state);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().propFinalEstimateCb
				.setEnabled(state);

	}

	private void disableInitParamOptions() throws RowsExceededException,
			WriteException, BiffException, IOException {
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueComboBox
				.setEnabled(false);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
				.setEnabled(false);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
				.setEnabled(false);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
				.setEnabled(false);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().previouslyGenInitilParam
				.setEnabled(false);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().parameterBoundariesComboBox
				.setEnabled(false);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().propFinalEstimateCb
				.setEnabled(false);

	}

	private void provideLinkInitialEstimates() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		if (procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true) {

			
			performCommonFunctionality();
		}
		
		
		CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().linkInitParamValueForCAIF
				.moveToFront();

	}

	private void provideInitialEstimates() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		if (procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true) {
			
			performCommonFunctionality();
		}
		// performCommonFunctionality();

		if (appInfo.isRetrievalFlag() == true) {
		}
		CaInitEstimateDispGuiCreator.createCaInitEstGui().parameterInitialValueInternalFrameForCA
				.moveToFront();
		
	}

	private void provideUnits() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ParameterAndUnitListLoader paramAndUnitListInst = ParameterAndUnitListLoader
				.createParamAndUnitListInstance();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsInternalFrame
				.moveToFront();

	}

	public void performCommonFunctionality() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// create the dosing screen
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

		if (analysisType.equals("pd")) {

		} else {
			dosingScreenCreationBasedOnProfiles();
		}

		// initial value screen
		initialValueScreenCreationBasedOnPrifiles();

		if (analysisType.equals("pkpdlink") || analysisType.equals("irm")) {
			linkInitialValueScreenCreationBasedOnPrifiles();
		}

		// create parameters unit screen
		//parameterUnitScreenCreationBasedOnProfiles();

		// stripping dose screen creation
		// strippingDoseScreenCreationBasedOnProfiles();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(false);
		timeColChanged = false;
		concColChanged = false;
		sortVarChanged = false;

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	private void linkInitialValueScreenCreationBasedOnPrifiles()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		if (appInfo.isRetrievalFlag() == false)
			CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui()
					.createInitialParameterValueTable();
		CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().linkInitParamValueForCAIF
				.moveToFront();
	}

	private void initialValueScreenCreationBasedOnPrifiles()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		if (appInfo.isRetrievalFlag() == false)
			CaInitEstimateDispGuiCreator.createCaInitEstGui()
					.createInitialParameterValueTable();
		CaInitEstimateDispGuiCreator.createCaInitEstGui().parameterInitialValueInternalFrameForCA
				.moveToFront();

	}

	private void dosingScreenCreationBasedOnProfiles()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true
				|| CaLibModelDispGuiCreator.createCalibraryModelInstance().modelNumber != CaLibModelDispGuiCreator
						.createCalibraryModelInstance().previousLibraryModel) {

			if (appInfo.isRetrievalFlag() == false)
				CaDosingDispGuiCreator.createCaDosingDispGui()
						.createInternalGUIForDosing();
		}
		CaDosingDispGuiCreator.createCaDosingDispGui().DosingInternalFrame
				.moveToFront();
		CaLibModelDispGuiCreator.createCalibraryModelInstance().previousLibraryModel = CaLibModelDispGuiCreator
				.createCalibraryModelInstance().modelNumber;

	}

	private void parameterUnitScreenCreationBasedOnProfiles()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ParameterAndUnitListLoader paramAndUnitListInst = ParameterAndUnitListLoader
				.createParamAndUnitListInstance();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		paramAndUnitListInst.createParamAndUnitListInstance()
				.unitListForParameters();
		// ParameterAndCorrespondingUnitList.unitListForParameters();
		int rowCount = CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
				.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			((DefaultTableModel) CaParamUnitsDispGuiCreator
					.createCaParUnitsDisInst().parameterUnitsTable.getModel())
					.removeRow(i - 1);

		}

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		for (int i = 0; i < procInputInst.getParameterUnitsDataObj()
				.getParameterByGroup().length; i++) {
			String[] s = new String[3];
			String c = procInputInst.getParameterUnitsDataObj()
					.getParameterbyGroupValueAt(i);
			s[0] = c;
			String u = procInputInst.getParameterUnitsDataObj()
					.getDefaultUnitValueAt(i);

			String u1;
			u1 = procInputInst.getParameterUnitsDataObj().getPreferredUnit(i);
			s[1] = u;

			s[2] = u1;

			((DefaultTableModel) CaParamUnitsDispGuiCreator
					.createCaParUnitsDisInst().parameterUnitsTable.getModel())
					.insertRow(CaParamUnitsDispGuiCreator
							.createCaParUnitsDisInst().parameterUnitsTable
							.getRowCount(), s);

			CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
					.isCellEditable(i, 0);
			CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsTable
					.isCellEditable(i, 1);
		}
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst().parameterUnitsInternalFrame
				.moveToFront();

	}

	private void doDosing() throws RowsExceededException, HeadlessException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		if (procInputInst.getMappingDataObj().getIfMappingScreenIsChanged() == true) {
			
			performCommonFunctionality();

		}

		procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(false);

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);
		if (appInfo.isRetrievalFlag() == true) {
		}

		CaDosingDispGuiCreator.createCaDosingDispGui().DosingInternalFrame
				.setSize(CaDosingDispGuiCreator.createCaDosingDispGui().DosingInternalFrame
						.getSize());
		CaDosingDispGuiCreator.createCaDosingDispGui().DosingInternalFrame
				.moveToFront();
		// previousModelNumber = PKPDMain.modelNumber;
	}

	private String decideMessage() {
		String message = "";
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

		if (analysisType.equals("pk") || analysisType.equals("mm")) {
			if (timeColChanged == true)
				message = message + "Time variable changed";

			if (timeColChanged == true && concColChanged == true)
				message = message + ", Concentration variable changed";
			else if (timeColChanged == false && concColChanged == true)
				message = message + "Concentration variable changed";

			if (sortVarChanged == true) {
				if (timeColChanged == true || concColChanged == true)
					message = message + ", Sort variable changed";
				else
					message = message + "Sort variable changed";

			}

		} else if (analysisType.equals("pd")) {
			if (timeColChanged == true)
				message = message + "Concentration variable changed";

			if (timeColChanged == true && concColChanged == true)
				message = message + ", Effect variable changed";
			else if (timeColChanged == false && concColChanged == true)
				message = message + "Effect variable changed";

			if (sortVarChanged == true) {
				if (timeColChanged == true || concColChanged == true)
					message = message + ", Sort variable changed";
				else
					message = message + "Sort variable changed";

			}

		} else if (analysisType.equals("pkpdlink")
				|| analysisType.equals("irm")) {
			if (timeColChanged == true)
				message = message + "Time variable changed";

			if (timeColChanged == true && concColChanged == true)
				message = message + ", Effect variable changed";
			else if (timeColChanged == false && concColChanged == true)
				message = message + "Effect variable changed";

			if (sortVarChanged == true) {
				if (timeColChanged == true || concColChanged == true)
					message = message + ", Sort variable changed";
				else
					message = message + "Sort variable changed";

			}

		} else if(analysisType.equals("ascii")){
			if (timeColChanged == true)
				message = message + "Time variable changed";
			if (timeColChanged == true && concColChanged == true)
				message = message + ", Effect variable changed";
			else if (timeColChanged == false && concColChanged == true)
				message = message + "Effect variable changed";

			if (sortVarChanged == true) {
				if (timeColChanged == true || concColChanged == true)
					message = message + ", Sort variable changed";
				else
					message = message + "Sort variable changed";

			}
			if(funcColChanged == true){
				if (timeColChanged == true || concColChanged == true)
					message = message + ", Function variable changed";
				else
					message = message + "Function variable changed";
			}
		}

		return message;
	}

	private void detectProfiles() throws RowsExceededException, WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		CaMultipleLevelSortingProvider.setAppInfo(appInfo);
		CaMultipleLevelSortingProvider m = CaMultipleLevelSortingProvider
				.createMultipleSortInstance();

		m.setAppInfo(appInfo);
		m.sortData(null);
		CaNoOfSubjectDeterminer determineNoOfSubInst = CaNoOfSubjectDeterminer
				.createDetermineNoOfSubInstance();

		determineNoOfSubInst.determineNumberOfSubject(m.dataSorted);
		
		DataPreparationForAllComponentsForCA datPrepForCAInst = DataPreparationForAllComponentsForCA
		.createDetaPrepForCAInstance();


		datPrepForCAInst.dataPreparationForAll(m.dataSorted);
		
	}

	private String[] detectSetupPaths() throws RowsExceededException,
			WriteException, BiffException, IOException {

		String plotName = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().availableComponentsTree
				.getSelectionPath().getLastPathComponent().toString();

		String[] pathSplits = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().availableComponentsTree
				.getSelectionPath().toString().split(",");

		return pathSplits;
	}

	public void mappingChangeDetection() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		int a = 1;
	
		//if function is mapped then detect whether function column is present in the sort variable list 
		//and if present then remove it because it will be added more than once
		removeFuncIfPresentasSort();
		int noOfSortVar = procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size();

		if (prevConcColName != procInputInst.getMappingDataObj()
				.getYcolumnName()) {
			procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(true);
			concColChanged = true;
		}

		if (prevTimeColName != procInputInst.getMappingDataObj()
				.getxColumnName()) {

			procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(true);
			timeColChanged = true;
		}

		if (previousSize != noOfSortVar) {
			procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(true);
			sortVarChanged = true;
		} else {
			for (int i = 0; i < previousSize; i++) {
				for (int j = 0; j < noOfSortVar; j++) {
					String String1 = procInputInst.getMappingDataObj()
							.getSortVariablesListVector().get(i);
					String String2 = previousSortVariables[i];
					if (String1.equals(String2) == true)
						a = a * 0;
					else
						a = a * 1;

				}

				if (a == 1) {
					procInputInst.getMappingDataObj()
							.setIfMappingScreenIsChanged(true);
					sortVarChanged = true;
					break;

				} else {
					a = 1;
					procInputInst.getMappingDataObj()
							.setIfMappingScreenIsChanged(false);
				}

			}
		}

		if(prevFuncColName != procInputInst.getMappingDataObj().getFuncColName()){
			procInputInst.getMappingDataObj().setIfMappingScreenIsChanged(true);
			funcColChanged = true;
		}
		previousSortVariables = new String[procInputInst.getMappingDataObj()
				.getSortVariablesListVector().size()];

		prevConcColName = procInputInst.getMappingDataObj().getYcolumnName();
		prevTimeColName = procInputInst.getMappingDataObj().getxColumnName();
		prevFuncColName = procInputInst.getMappingDataObj().getFuncColName();

		for (int i = 0; i < noOfSortVar; i++) {
			String String2 = procInputInst.getMappingDataObj()
					.getSortVariablesListVector().get(i);
			previousSortVariables[i] = String2;
		}

		previousSize = noOfSortVar;
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	private void removeFuncIfPresentasSort() {
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
		.copyProcessingInput();
		String funcCol = procInputInst.getMappingDataObj().getFuncColName();
		
		for(int i=0;i< procInputInst.getMappingDataObj().getSortVariablesListVector().size();i++){
			if(procInputInst.getMappingDataObj().getSortVariablesListVector().get(i).equals(funcCol))
			{
				procInputInst.getMappingDataObj().getSortVariablesListVector().remove(i);
			
			}
		}
			
		
	}

	private void doMapping() throws RowsExceededException, WriteException,
			BiffException, IOException {
		CaMappingDispGuiCreator.createMappingInstance().mappingInternalFrame
				.moveToFront();
		CaOptionsGuiCreator.createPdOptionsInstance().optionsInternalFrame
				.moveToFront();
	}

}
