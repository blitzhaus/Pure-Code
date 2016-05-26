package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.bouncycastle.asn1.x509.DisplayText;

import com.itextpdf.awt.geom.Dimension;

import Common.DataManipulationFunctions;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaSetupAvailCompTreeValueChangeHandler {
	
	boolean lambdaZMethodSelectionProgramatically = false;
	int methodSelectedProgrameatically;
	boolean mappingChanged;

	private final class ProfileTableListSelectionListener implements
			ListSelectionListener {
		private final ArrayList<Double> observedX;
		boolean removeSlectionalreadyEntered;
		boolean insertSelectionAlreadyEntered;
		
		private ProfileTableListSelectionListener(ArrayList<Double> observedX) {
			this.observedX = observedX;
			removeSlectionalreadyEntered = false;
			insertSelectionAlreadyEntered = false;
		}

		NcaLambdaZDispGui ncaLamDispGui;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			
			
			
		if(arg0.getValueIsAdjusting() == false){
				ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
				int selectedProfileNumber = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getLambdazDataobj().getProfileSelected();
				
				
				int rowClicked;
				
				
				try {
					rowClicked = NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
							.getSelectedRow();

						
						int columnClicked = NcaLambdaZDispGui
								.createLambDispGuiInstance().profileTable
								.getSelectedColumn();
						
					
						
						
						if (columnClicked == 2) {

							
							// the selection is true
							if (((Boolean) NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
									.getValueAt(rowClicked, 2) == true)) {

								int i = rowClicked;
								
								includeThisPointInAppInfo(selectedProfileNumber, i);
								/*NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
								.setValueAt(false, i, 2);*/
								removeSlectionalreadyEntered = true;
								
								
								
							} else if(((Boolean) NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
									.getValueAt(rowClicked, 2) == false)){
								
								int i = rowClicked;
								
								excludeThisPointInAppInfo(selectedProfileNumber, i);
								
						
								
							}
							ncaLamDispGui = NcaLambdaZDispGui.createLambDispGuiInstance();
							NcaLambdaZDispGui.createLambDispGuiInstance().profileTable.setColumnSelectionInterval(0, 0);
							NcaLambdaZDispGui.createLambDispGuiInstance().LambdaZplotInternalFrame.setFocusable(true);
							
							NcaLambdaZDispGui.createLambDispGuiInstance().profileTable.getSelectionModel().clearSelection();
						}
						
						// setting the start time into the text field
						for (int i = 0; i < NcaLambdaZDispGui
								.createLambDispGuiInstance().profileTable.getRowCount(); i++) {

							if ((Boolean) NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
									.getValueAt(i, 2) == true) {
								/*NcaLambdaZDispGui.createLambDispGuiInstance().startTimeForLambdaZTextField
										.setText(observedX.get(i) + "");*/
								
								//set in appInfo
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
										.setStartTime(observedX.get(i));
								
								break;
							}

						}

						// setting the end time into the text field
						for (int i = NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
								.getRowCount() - 1; i >= 0; i--) {
							if ((Boolean) NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
									.getValueAt(i, 2) == true) {
								/*NcaLambdaZDispGui.createLambDispGuiInstance().endTimeForLambdaZTextField
										.setText(observedX.get(i) + "");*/
								
								//set in appInfo
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
										.getNcaInfo().getProcessingInputs()
										.getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
										.setEndTime(observedX.get(i));
								
								break;
							}
						}
						
						//update the graph and set the related regression values
						NcaLambdaZDispGui.createLambDispGuiInstance().updateProfile();
						
						NcaLambdaZDispGui.createLambDispGuiInstance().profileTable.validate();
						NcaLambdaZDispGui.createLambDispGuiInstance().profileTable.repaint();
						
						
						


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
		private void includeThisPointInAppInfo(int selectedProfileNumber, int rowSelected) {
			// TODO Auto-generated method stub
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			Hashtable<Integer, Boolean> hashTable;
					
			hashTable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber].getInclusionExcusionPoints();
			
			hashTable.put(rowSelected, true);
			
		}

		private void excludeThisPointInAppInfo(int selectedProfileNumber, int rowSelected) throws RowsExceededException, WriteException, BiffException, IOException {
			
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			Hashtable<Integer, Boolean> hashTable;
					
			hashTable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber].getInclusionExcusionPoints();
			
			
			hashTable.put(rowSelected, false);
			
		}
	}

	public static NcaSetupAvailCompTreeValueChangeHandler NCA_AVAIL_COMP_TREE_HAND = null;

	public static NcaSetupAvailCompTreeValueChangeHandler createNcaavailableCompHandlerInst() {
		if (NCA_AVAIL_COMP_TREE_HAND == null) {
			NCA_AVAIL_COMP_TREE_HAND = new NcaSetupAvailCompTreeValueChangeHandler();

		}
		return NCA_AVAIL_COMP_TREE_HAND;
	}

	
	
	
	void availableCompHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {
		boolean permittedInside = true;
		if (NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
				.getSelectionPath() != null) {
			NcaLambdaZDispGui.createLambDispGuiInstance().entryIsWhileCreatingTheGUI = false;


			String[] pathSplits = NcaSetupAvailableComp
					.createNcaSetupAvailCompInst().availableComponentsTree
					.getSelectionPath().toString().split(",");

			// create the lambda z graph and move it to front. Also populate
			// the profiles regression parameters.
			createLambZProfGraphAndPopulateRegressionDetails(pathSplits);
			
			
			if (NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
					.getMinSelectionRow() == 0) {
				showImportedSheet();
			} else if (pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Mapping]")) {
				mappingNodeSelected();
				NcaOptionsGui.createNcaOptionsInstance().PartialAreasoptionsInternalFrame.setVisible(false);
			} else if ((pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Dosing]"))) {
				dosingNodeselected();
				NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.setFocusable(true);
				NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().tableForPartialArea
						.setFocusable(false);
				NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable
						.setFocusable(false);
				NcaOptionsGui.createNcaOptionsInstance().PartialAreasoptionsInternalFrame.setVisible(false);
			} else if ((pathSplits[pathSplits.length - 1]
					.equals(" Lambda Z Selector]"))
					&& (permittedInside == true)) {
				lambdaZNodeSelected();
				NcaOptionsGui.createNcaOptionsInstance().PartialAreasoptionsInternalFrame.setVisible(false);
			} else if ((pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Specify Sub Areas]"))
					&& (permittedInside == true)) {
				subAreasNodeSeleted();

				NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.setFocusable(false);
				NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().tableForPartialArea
						.setFocusable(true);
				NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable
						.setFocusable(false);
				NcaOptionsGui.createNcaOptionsInstance().PartialAreasoptionsInternalFrame.setVisible(true);
			} else if ((pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Parameter Units]"))) {
				parameterUnitsNodeSelected();
				NcaOptionsGui.createNcaOptionsInstance().PartialAreasoptionsInternalFrame.setVisible(false);
			} else if ((pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Parameter Names]"))) {
				parameterNamesNodeSelected();

				NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.setFocusable(false);
				NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().tableForPartialArea
						.setFocusable(false);
				NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesTable
						.setFocusable(true);
				NcaOptionsGui.createNcaOptionsInstance().PartialAreasoptionsInternalFrame.setVisible(false);

			}
		}
	}

	private void parameterNamesNodeSelected() throws RowsExceededException,
			WriteException, BiffException, IOException {

		if (NcaParameterNamesDispGui.createParameterNamesInstance().isRestoringParameterNames == true) {

		} else {
			new NcaUpdateParameterNamesTable();
		}

		NcaParameterNamesDispGui.createParameterNamesInstance().parameterNamesInternalFrame
				.moveToFront();
		NcaOptionsGui.createNcaOptionsInstance().regressionParametersInternalFrame
				.setVisible(false);

	}
	
	NcaUpdateParameterUnitsTable ncaParamUnitsTable;

	private void parameterUnitsNodeSelected() throws RowsExceededException,
			WriteException, BiffException, IOException {

		if (NcaParameterUnitsDispGui.createNcaParUnitsDisInst().isRestoringParameterUnits == true) {

		} else {
			new NcaUpdateParameterUnitsTable();
		}

		NcaParameterUnitsDispGui.createNcaParUnitsDisInst().parameterUnitsInternalFrame
				.moveToFront();
		NcaOptionsGui.createNcaOptionsInstance().regressionParametersInternalFrame
				.setVisible(false);

	}

	
	NcaUpdateParameterNamesTable ncaUpdateParamNames;
	private boolean hasNoConsecutiveTimes;
	
	boolean performCommonFunctionality() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		multipleLevelSorting m = multipleLevelSorting.createMultipleSortInst();// new
		// multipleLevelSorting(appInfo);
		m.main(null);
		DataPreparationForNca dataPrepNca = new DataPreparationForNca();
		dataPrepNca.prepareData();
		DetermineNumberOfSubject.createDetNoSubInst().determineNumberOfSubject(
				dataPrepNca.Dat);
		

		// till the end of catch block is pertaining to lambdaZ
		DataPreparationForAllComponents.createDataPrepAllCompInst()
				.dataPreparationForAll(dataPrepNca.Dat);
		

		hasNoConsecutiveTimes = false; 
		
				try {
					LambdaZCalculationBeforeNCA lzbNCA = DisplayContents
							.createInstanceOflzBeforeNCA();
					hasNoConsecutiveTimes = lzbNCA.calculationOfAllLambdaZUsingBestFit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

				// create the lambda z screen
				if(hasNoConsecutiveTimes == true){
								
				} else {
					
					// create the dosing screen
					dosingScreenCreationBasedOnProfiles();
					lambdaZScreenCreationBasedOnprofiles();
				}
				
				// create the sub areas screen
				subAreasScreenCreationBasedOnprofiles();
				
				//update of parameter units
				new NcaUpdateParameterUnitsTable();
				
				//update of parameter names
				new NcaUpdateParameterNamesTable();	
				
			
		return hasNoConsecutiveTimes;


		

	}
	
	NcaSubAreasDispGui ncaSubAdreasDispGui;

	private void subAreasScreenCreationBasedOnprofiles()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ncaSubAdreasDispGui = NcaSubAreasDispGui.createNcaSubAreasDispGuiInst();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().fromAreaScreen = true;
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().numberOfTimePartialAreaScreenCalled++;
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().ifFromNumberOfPartialAreasCombo = false;
		if (NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().numberOfTimePartialAreaScreenCalled == 1)
			NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().ifPartialAreaScreenIsCalledFirstTime = true;
		else
			NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().ifPartialAreaScreenIsCalledFirstTime = false;

		// NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().PartialAreasoptionsInternalFrame.setVisible(true);

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() == 0) {
			if (NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().numberOfTimePartialAreaScreenCalled == 1
					|| NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().fromPositiveNumberOfProfileInPartialArea == true) {
				NcaSubAreasDispGui.createNcaSubAreasDispGuiInst()
						.createSubAreasInternalFrame();
				NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaInternalFrame
						.moveToFront();
				NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().tableForPartialArea
						.setVisible(true);
				NcaOptionsGui.createNcaOptionsInstance().optionsInternalFrame
						.moveToFront();
				NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().fromPositiveNumberOfProfileInPartialArea = false;

			} else {
				NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaInternalFrame
						.moveToFront();
				NcaOptionsGui.createNcaOptionsInstance().optionsInternalFrame
						.moveToFront();

			}
		} else {
			NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().fromPositiveNumberOfProfileInPartialArea = true;

			
			NcaSubAreasDispGui.createNcaSubAreasDispGuiInst()
					.createSubAreasInternalFrame();
			for (int i = 0; i < NcaSubAreasDispGui
					.createNcaSubAreasDispGuiInst().tableForPartialArea
					.getRowCount(); i++) {

				NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaData[i][0] = (String) NcaSubAreasDispGui
						.createNcaSubAreasDispGuiInst().tableForPartialArea
						.getValueAt(
								i,
								NcaSubAreasDispGui
										.createNcaSubAreasDispGuiInst().numberOfColumn - 2);
				NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaData[i][1] = (String) NcaSubAreasDispGui
						.createNcaSubAreasDispGuiInst().tableForPartialArea
						.getValueAt(
								i,
								NcaSubAreasDispGui
										.createNcaSubAreasDispGuiInst().numberOfColumn - 1);

			}

		}
	}

	private void lambdaZScreenCreationBasedOnprofiles()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		/*NcaLambdaZDispGui.createLambDispGuiInstance().ifLambdaZScreenIsCalledFirstTime++;*/
		NcaLambdaZDispGui.createLambDispGuiInstance().isFromSlopeScreen = true;

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		// determining the name of each profile and setting it to
		// the node under the lambda Z elector node
		NcaSetupAvailableComp.createNcaSetupAvailCompInst().lambdaZSelectorNodeselector
				.removeAllChildren();
		NcaLambdaZDispGui.createLambDispGuiInstance().profileNamesUsedForProfileNumberDetermination = new ArrayList<String>();
		for (int ii = 0; ii < DetermineNumberOfSubject.createDetNoSubInst().no_subject; ii++) {

			String S = "";

			for (int i = 0; i < DetermineNumberOfSubject.createDetNoSubInst().numberOfSortVariable; i++)
				S = S
						+ " "
						+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getNcaInfo().getProcessingInputs()
								.getMappingDataObj()
								.getSortVariablesListVector().get(i)
						+ "="
						+ DetermineNumberOfSubject.createDetNoSubInst().dataSortVariables[ii][i];
			DefaultMutableTreeNode profile = new DefaultMutableTreeNode(S);
			NcaLambdaZDispGui.createLambDispGuiInstance().profileNamesUsedForProfileNumberDetermination
					.add(S);

			NcaSetupAvailableComp.createNcaSetupAvailCompInst().lambdaZSelectorNodeselector
					.add(profile);
		}
		((DefaultTreeModel) NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
				.getModel()).reload();

		NcaLambdaZDispGui.createLambDispGuiInstance().LambdaZSelectorInternalFrame
				.moveToFront();

	}
	NcaDosingDispGui ncadosingDispInst;
	DefaultAndPrefferedUnit defAndPrefUnitInst;
	public void dosingScreenCreationBasedOnProfiles()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		
		ncadosingDispInst = NcaDosingDispGui.createNcaDosingGuiInst();
		// PartialAreasoptionsInternalFrame.setVisible(false);
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NcaDosingDispGui.createNcaDosingGuiInst().numberOfTimeDosingScreenCalled++;
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() == 0) {
			if (NcaDosingDispGui.createNcaDosingGuiInst().numberOfTimeDosingScreenCalled == 1
					|| NcaDosingDispGui.createNcaDosingGuiInst().fromPositiveNumberOfProfile == true) {

				NcaDosingDispGui.createNcaDosingGuiInst()
						.createDosingInternalFrame();
				NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
						.moveToFront();
				NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.setVisible(true);
				NcaOptionsGui.createNcaOptionsInstance().optionsInternalFrame
						.moveToFront();
				NcaDosingDispGui.createNcaDosingGuiInst().fromPositiveNumberOfProfile = false;

			} else {
				NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
						.moveToFront();
				NcaOptionsGui.createNcaOptionsInstance().optionsInternalFrame
						.moveToFront();
			}
		} else {
			NcaDosingDispGui.createNcaDosingGuiInst().fromPositiveNumberOfProfile = true;
			NcaDosingDispGui.createNcaDosingGuiInst()
					.createDosingInternalFrame();
			NcaOptionsGui.createNcaOptionsInstance().optionsInternalFrame
					.moveToFront();
			NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
					.moveToFront();
		}
		defAndPrefUnitInst = new DefaultAndPrefferedUnit();
		defAndPrefUnitInst.preparationOfparameterByGroup();
	}

	public void subAreasNodeSeleted() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// check for mapping change
		String s = NcaMappingDispGui.createMappingInstance()
				.mappingChangeDetection();

		mappingChanged = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getIfMappingScreenIsChanged();

		boolean dataChanged = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.isDataChanged();
		// if the mapping is changed
		if ((mappingChanged == true) && (dataChanged == true)) {
			hasNoConsecutiveTimes = false;
			s = s + ",Data Changed";
			resetEveryThing(s);
			NcaOptionsGui.createNcaOptionsInstance().regressionParametersInternalFrame
			.setVisible(false);
	NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaInternalFrame
			.moveToFront();
	NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaGraphInternalFrame
			.moveToFront();

		} else if ((mappingChanged == true) && (dataChanged == false)) {
			hasNoConsecutiveTimes = false;
			resetEveryThing(s);
			NcaOptionsGui.createNcaOptionsInstance().regressionParametersInternalFrame
			.setVisible(false);
	NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaInternalFrame
			.moveToFront();
	NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaGraphInternalFrame
			.moveToFront();
		} else if ((mappingChanged == false) && (dataChanged == true)) {
			hasNoConsecutiveTimes = false;
			s = s + ", Data Changed";
			resetEveryThing(s);
			NcaOptionsGui.createNcaOptionsInstance().regressionParametersInternalFrame
			.setVisible(false);
	NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaInternalFrame
			.moveToFront();
	NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaGraphInternalFrame
			.moveToFront();
		} else if ((mappingChanged == false) && (dataChanged == false)) {
			
			boolean isValidMapping = checkForMappingValidation();
			if((isValidMapping == true)&&(hasNoConsecutiveTimes == false)){
				NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaInternalFrame
				.moveToFront();
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaGraphInternalFrame
				.moveToFront();
		NcaOptionsGui.createNcaOptionsInstance().optionsInternalFrame
				.moveToFront();
		NcaOptionsGui.createNcaOptionsInstance().regressionParametersInternalFrame
		.setVisible(false);
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaInternalFrame
		.moveToFront();
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaGraphInternalFrame
		.moveToFront();
			} else if(hasNoConsecutiveTimes == true){
				// pop up a message that mapping is invalid
				JOptionPane.showMessageDialog(NCAMainScreen
						.createNcaMainScreenInstance().ncaMainInternalFrame,
						"Two consecutive time points are same, please verify", "Conform", JOptionPane.YES_OPTION);
				NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
						.setSelectionRow(1);
			} else{
				// pop up a message that mapping is invalid
				JOptionPane.showMessageDialog(NCAMainScreen
						.createNcaMainScreenInstance().ncaMainInternalFrame,
						"Invalid Mapping Data", "Conform", JOptionPane.YES_OPTION);
				NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
						.setSelectionRow(1);
				
				
			
			}
		}

		

	}

	
	NcaLambdaZDispGui ncaLamDisGui;
	public void lambdaZNodeSelected() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ncaLamDisGui = NcaLambdaZDispGui.createLambDispGuiInstance();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NcaOptionsGui.createNcaOptionsInstance().regressionParametersInternalFrame
				.setVisible(false);

		// checking for the mapping change
		String s = NcaMappingDispGui.createMappingInstance()
				.mappingChangeDetection();

		mappingChanged = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getIfMappingScreenIsChanged();

		boolean dataChanged = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.isDataChanged();

		// if the mapping is changed
		if ((mappingChanged == true) && (dataChanged == true)) {
			hasNoConsecutiveTimes = false;
			s = s + ", Data Changed";
			resetEveryThing(s);
			NcaLambdaZDispGui.createLambDispGuiInstance().LambdaZSelectorInternalFrame
			.moveToFront();
		} else if ((mappingChanged == true) && (dataChanged == false)){			
			hasNoConsecutiveTimes = false;
			resetEveryThing(s);
			NcaLambdaZDispGui.createLambDispGuiInstance().LambdaZSelectorInternalFrame
			.moveToFront();
		} else if ((mappingChanged == false) && (dataChanged == true)) {
			hasNoConsecutiveTimes = false;
			s = s + ", Data Changed";
			resetEveryThing(s);
			NcaLambdaZDispGui.createLambDispGuiInstance().LambdaZSelectorInternalFrame
			.moveToFront();
		} else if ((mappingChanged == false) && (dataChanged == false)) {

			boolean isValidMappingData = checkForMappingValidation();
			if((isValidMappingData == true)&&(hasNoConsecutiveTimes == false)){
				NcaLambdaZDispGui.createLambDispGuiInstance().LambdaZSelectorInternalFrame
				.moveToFront();
		NcaOptionsGui.createNcaOptionsInstance().optionsInternalFrame
				.moveToFront();
			} else if(hasNoConsecutiveTimes == true){ 


				// pop up a message that mapping is invalid
				JOptionPane.showMessageDialog(NCAMainScreen
						.createNcaMainScreenInstance().ncaMainInternalFrame,
						"Two consecutive time points are same, please verify", "Conform", JOptionPane.YES_OPTION);
				NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
						.setSelectionRow(1);
				
				
			
			
			}else{

				// pop up a message that mapping is invalid
				JOptionPane.showMessageDialog(NCAMainScreen
						.createNcaMainScreenInstance().ncaMainInternalFrame,
						"Invalid Mapping Data", "Conform", JOptionPane.YES_OPTION);
				NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
						.setSelectionRow(1);
				
				
			
			}
			
			

		}
		

	}

	public void dosingNodeselected() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// checking for the mapping change
		String s = NcaMappingDispGui.createMappingInstance()
				.mappingChangeDetection();
		mappingChanged = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getIfMappingScreenIsChanged();

		boolean dataChanged = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.isDataChanged();

		
		// if dosing defined is selected.
		
			// if the mapping is changed
		if ((mappingChanged == true) && (dataChanged == false)) {
			hasNoConsecutiveTimes = false;
			resetEveryThing(s);
			NcaOptionsGui.createNcaOptionsInstance().regressionParametersInternalFrame
			.setVisible(false);
			//this condition is executed only in the case of dosing defined
			createDosingDefinedTable();
	NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
			.moveToFront();

		} else if ((mappingChanged == true) && (dataChanged == true)) {

			hasNoConsecutiveTimes = false;
			s = s + ", Data Changed";
			resetEveryThing(s);
			NcaOptionsGui.createNcaOptionsInstance().regressionParametersInternalFrame
			.setVisible(false);
			//this condition is executed only in the case of dosing defined
			createDosingDefinedTable();
	NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
			.moveToFront();

		} else if ((mappingChanged == false) && (dataChanged == true)) {

			hasNoConsecutiveTimes = false;
			s = s + "Data Changed";
			resetEveryThing(s);
			NcaOptionsGui.createNcaOptionsInstance().regressionParametersInternalFrame
			.setVisible(false);
			//this condition is executed only in the case of dosing defined
			createDosingDefinedTable();
	NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
			.moveToFront();
		} else if ((mappingChanged == false) && (dataChanged == false)) {

			// this is user interface validation
			boolean isValidMappingData = checkForMappingValidation();
			if((isValidMappingData == true)&&(hasNoConsecutiveTimes == false)){
				
				//this condition is executed only in the case of dosing defined, 
				//checking is done within the function 
				createDosingDefinedTable();
				NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
				.moveToFront();
		NcaOptionsGui.createNcaOptionsInstance().optionsInternalFrame
				.moveToFront();
		NcaOptionsGui.createNcaOptionsInstance().regressionParametersInternalFrame
		.setVisible(false);
NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
		.moveToFront();
		
		
			}else if(hasNoConsecutiveTimes == true){

				// pop up a message that mapping is invalid
				JOptionPane.showMessageDialog(NCAMainScreen
						.createNcaMainScreenInstance().ncaMainInternalFrame,
						"Two consecutive time points are same, please verify", "Conform", JOptionPane.YES_OPTION);
				NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
						.setSelectionRow(1);
				
				
			
			}
			
			else{
				// pop up a message that mapping is invalid
				JOptionPane.showMessageDialog(NCAMainScreen
						.createNcaMainScreenInstance().ncaMainInternalFrame,
						"Invalid Mapping Data", "Conform", JOptionPane.YES_OPTION);
				NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
						.setSelectionRow(1);
				
				
			}
			

		}
		
		

	}
	
	public void createDosingDefinedTable() throws RowsExceededException, WriteException, BiffException, IOException{
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if(NcaOptionsGui.createNcaOptionsInstance().previousRootOfAdministration != 3
				&& NcaOptionsGui.createNcaOptionsInstance().rootOfAdministration
						.getSelectedIndex() == 3){
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getProfileInfoObj()
					.getNo_subject() != 0) {

				NcaDosingDispForDosingDefinedGui inst = new NcaDosingDispForDosingDefinedGui();
				inst.dosingTableCreationForDosingDefined();

				NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.setSelectionBackground(new Color(238, 238, 224));
				NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.setShowHorizontalLines(true); // Configure some of
				// JTable's
				// parameters
				NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.setRowSelectionAllowed(true);
				NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.setColumnSelectionAllowed(true);
				new ReorderableJList(NcaDosingDispGui
						.createNcaDosingGuiInst().tableForDosing);
				new JvUndoableTable(NcaDosingDispGui
						.createNcaDosingGuiInst().tableForDosing.getModel());
				// NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing.getSelectionModel().addListSelectionListener(new
				// DosingTabListSelecHandler());
				NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.getModel().addTableModelListener(
								new DosingTabModelSelecHandler());

				// int width = (int) ((int) getWidth() / 1.2);
				/*NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.setPreferredScrollableViewportSize(new Dimension(
								500, 400));*/
				NcaDosingDispGui.createNcaDosingGuiInst().tForDosing = (AbstractTableModel) NcaDosingDispGui
						.createNcaDosingGuiInst().tableForDosing.getModel();
				// tForDosing.fireTableDataChanged();
				NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.setFillsViewportHeight(true);

				NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

				GridBagConstraints tableForDosingTableScrollPaneCon = new GridBagConstraints();
				tableForDosingTableScrollPaneCon = new GridBagConstraints();
				tableForDosingTableScrollPaneCon.gridx = 0;
				tableForDosingTableScrollPaneCon.gridy = 0;
				tableForDosingTableScrollPaneCon.weighty = 0.5;
				tableForDosingTableScrollPaneCon.weightx = 0.5;
				tableForDosingTableScrollPaneCon.gridheight = 5;
				tableForDosingTableScrollPaneCon.gridwidth = 4;
				tableForDosingTableScrollPaneCon.fill = GridBagConstraints.BOTH;

				NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing
						.setVisible(true);
				JScrollPane tableForDosingScrollPane = new JScrollPane(
						NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing);
				tableForDosingScrollPane.setBackground(Color.white);
				tableForDosingScrollPane.setVisible(true);
				NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
						.getContentPane().removeAll();
				NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
						.add(tableForDosingScrollPane,
								tableForDosingTableScrollPaneCon);
				/*NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
						.moveToFront();*/
				NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
						.setSize(NcaDosingDispGui.createNcaDosingGuiInst().DosingInternalFrame
								.getSize());
			}

		}
	}

	public void resetEveryThing(String s) throws RowsExceededException,
			HeadlessException, WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		// this is user interface validation
		boolean isValidMappingData = checkForMappingValidation();

		// if valid input is given only ten proceed
		if ((isValidMappingData == true)) {
			JOptionPane.showMessageDialog(NCAMainScreen
					.createNcaMainScreenInstance().ncaMainInternalFrame, s,
					"Conform", JOptionPane.YES_OPTION);

			performCommonFunctionality();

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getMappingDataObj()
					.setIfMappingScreenIsChanged(false);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.setDataChanged(false);

		} else {// pop up a message that mapping is invalid
			JOptionPane.showMessageDialog(NCAMainScreen
					.createNcaMainScreenInstance().ncaMainInternalFrame,
					"Invalid Mapping Data, please verify and provide valid inputs", "Conform", JOptionPane.YES_OPTION);
			
			NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree.setSelectionRow(0);
			/*ViewLayer.createFeaturesPanelInstance().PreviewLable.doClick();
			NcaSetupDispCompGui.createNcaMainScrSetupDispGui().ncaMapDispGui.MappingInternalFrame.validate();*/
		} if(hasNoConsecutiveTimes == true){
			// pop up a message that mapping is invalid
			JOptionPane.showMessageDialog(NCAMainScreen
					.createNcaMainScreenInstance().ncaMainInternalFrame,
					"Two consecutive time points are same, please verify", "Conform", JOptionPane.YES_OPTION);
			
			NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree.setSelectionRow(0);
			/*ViewLayer.createFeaturesPanelInstance().PreviewLable.doClick();
			NcaSetupDispCompGui.createNcaMainScrSetupDispGui().ncaMapDispGui.MappingInternalFrame.validate();*/
		
		}

	}

	private boolean checkForMappingValidation() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		int modelType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj().getModelType();

		boolean isSparse = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getModelInputsObj()
				.getisSparseSelected();

		boolean isValidMappingData = false;

		if ((modelType == 0) && (isSparse == false)) {// plasma & serial
			isValidMappingData = checkTimeAndConcColumnDataTypes();
		} else if ((modelType == 0) && (isSparse == true)) { // plasma, sparse
			isValidMappingData = checkTimeConcSubjectDataTypes();
		} else if ((modelType == 1) && (isSparse == false)) { // urine & serial
			isValidMappingData = checkTimeConcEndTimeVol();
		} else if ((modelType == 1) && (isSparse == true)) { // urine & sparse
			isValidMappingData = checkTimeConcEndTimeVolSub();
		}

		return isValidMappingData;

	}

	private boolean checkTimeConcEndTimeVolSub() {
		boolean isValidTimeConcEndTimeVol = checkTimeConcEndTimeVol();
		boolean isValidSubject = checkTimeConcSubjectDataTypes();

		if ((isValidTimeConcEndTimeVol == true) && (isValidSubject == true)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean checkTimeConcEndTimeVol() {
		boolean isValidTimeConc = checkTimeAndConcColumnDataTypes();
		boolean isValidEndTime;
		boolean isValidVolume;
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getVolumeColumnName().equals("")) {
			isValidVolume = false;
		} else {
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getColumnPropertiesArrayList()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getVolumeColumnCorrespondinOriginalIndex())
					.getDataType().equals("Numeric")) {
				isValidVolume = true;
			} else {
				isValidVolume = false;
			}
		}

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getEndTimeColumnName().equals("")) {
			isValidEndTime = false;
		} else {
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getColumnPropertiesArrayList()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo()
									.getProcessingInputs()
									.getMappingDataObj()
									.getEndTimeColumnCorrespondinOriginalIndex())
					.getDataType().equals("Numeric")) {
				isValidEndTime = true;
			} else {
				isValidEndTime = false;
			}
		}

		if ((isValidTimeConc == true) && (isValidEndTime == true)
				&& (isValidVolume == true)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean checkTimeConcSubjectDataTypes() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		boolean isValidTimeConc = checkTimeAndConcColumnDataTypes();
		boolean isValidSubject = false;

		
		
		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSubjectColumnName().equals("")) {
			
			
			isValidSubject = false;
		} else {
			
			isValidSubject = true;
		}

		if ((isValidTimeConc == true) && (isValidSubject == true)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean checkTimeAndConcColumnDataTypes() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		boolean isTimeColumnEmpty = false;
		boolean isConcColumnEmpty = false;
		boolean isTimeNumeric = false;
		boolean isConcNumeric = false;

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName()
				.equals("")) {
			isTimeColumnEmpty = true;

		} else {
			// determine data type of time column
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getColumnPropertiesArrayList()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getxColumnCorrespondinOriginalIndex())
					.getDataType().equals("Numeric")) {
				isTimeNumeric = true;
			} else {

			}
		}

		if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName()
				.equals("")) {
			isConcColumnEmpty = true;
		} else {
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getNcaInfo()
					.getColumnPropertiesArrayList()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getyColumnCorrespondinOriginalIndex())
					.getDataType().equals("Numeric")) {
				isConcNumeric = true;
			} else {

			}
		}

		if ((isTimeColumnEmpty == false) && (isConcColumnEmpty == false)
				&& (isTimeNumeric == true) && (isConcNumeric == true)) {
			return true;
		} else {
			return false;
		}

	}

	private void mappingNodeSelected() throws RowsExceededException,
			WriteException, BiffException, IOException {

		NcaMappingDispGui.createMappingInstance().MappingInternalFrame
				.moveToFront();
		NcaOptionsGui.createNcaOptionsInstance().optionsInternalFrame
				.moveToFront();
		// partialAreasoptionsPanel.setVisible(false);
		// PartialAreasoptionsInternalFrame.setVisible(false);
		NcaOptionsGui.createNcaOptionsInstance().regressionParametersInternalFrame
				.setVisible(false);

	}

	private void showImportedSheet() {

		
		ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.moveToFront();

		DDViewLayer.createFeaturesPanelInstance().PreviewLable.setVisible(true);

	}

	public void createLambZProfGraphAndPopulateRegressionDetails(
			String[] pathSplits) throws RowsExceededException, WriteException,
			BiffException, IOException {

		NcaLambdaZDispGui.createLambDispGuiInstance().observedX.clear();
		NcaLambdaZDispGui.createLambDispGuiInstance().observedY.clear();

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		// the below two sentences are the definitions for the
		// array lists which hold the lambdaz
		// plots for the profile clicked
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZNormalGraphsInternalFramesArrayList = new ArrayList<JInternalFrame>();
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZLogarithmicGraphsInternalFramesArrayList = new ArrayList<JInternalFrame>();
		
		// for loop to traverse through the profiles and select the required node
		for (int i = 0; i < NcaLambdaZDispGui.createLambDispGuiInstance().profileNamesUsedForProfileNumberDetermination
				.size(); i++) {

			if ((" "
					+ NcaLambdaZDispGui.createLambDispGuiInstance().profileNamesUsedForProfileNumberDetermination
							.get(i) + "]")
					.equals((pathSplits[pathSplits.length - 1]))) {
				
				int selectedProfileNumber = i;
			
				
				setProfileNumberSelected(i);
				
				
				lambdaZMethodSelectionProgramatically = true;
				setLambdaZmethodSelected(i);
				lambdaZMethodSelectionProgramatically = false;
				
				
				if (selectedProfileNumber == 0) {

					NcaLambdaZDispGui.createLambDispGuiInstance().observedX
							.clear();
					NcaLambdaZDispGui.createLambDispGuiInstance().observedY
							.clear();
					NcaSubAreasDispGui.createNcaSubAreasDispGuiInst()
							.setObservedXYZerothProfile();

				} else {

					NcaLambdaZDispGui.createLambDispGuiInstance().observedX
							.clear();
					NcaLambdaZDispGui.createLambDispGuiInstance().observedY
							.clear();
					NcaSubAreasDispGui.createNcaSubAreasDispGuiInst()
							.setObservedXYOtherProfiles();
				}
				
				

				
				if(methodSelectedProgrameatically == 3){
					//it is a time range so we have to restore the previous time range selected by the user
					Hashtable<Integer , Boolean > hashTable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
							.get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).getNcaInfo()
							.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber].getInclusionExcusionPoints();
					
					
					for(int j=0;j<NcaLambdaZDispGui.createLambDispGuiInstance().profileTable.getRowCount();j++){
						
						
						NcaLambdaZDispGui.createLambDispGuiInstance().profileTable.setValueAt((Boolean)hashTable.get(j), j, 2);
					}
					
					
					
				}
				
				
				
				NcaOptionsGui.createNcaOptionsInstance().halfLifeTextField
						.setText("");
				NcaOptionsGui.createNcaOptionsInstance().numberOfTerminalPointsTextField
						.setText("");

				
				try {
					
					populateTable(selectedProfileNumber);
					
					LambdaZCalculationBeforeNCA lambdaZCalcBefNca = DisplayContents
							.createInstanceOflzBeforeNCA();

					LambdaZCalculationBeforeNCA lzbNCA = DisplayContents
							.createInstanceOflzBeforeNCA();

					
					lzbNCA
							.calculateLambdaZBeforeNCAUsingBestFit(
									selectedProfileNumber,
									methodSelectedProgrameatically,
									appInfo
											.getProjectInfoAL()
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getWorkBooksArrayList()
															.get(
																	appInfo
																			.getProjectInfoAL()
																			.get(
																					appInfo
																							.getSelectedProjectIndex())
																			.getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getLambdazDataobj()
											.getLambdaZDetails()[selectedProfileNumber]
											.getStartTime(),
									appInfo
											.getProjectInfoAL()
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getWorkSheetObjectsAL()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getWorkBooksArrayList()
															.get(
																	appInfo
																			.getProjectInfoAL()
																			.get(
																					appInfo
																							.getSelectedProjectIndex())
																			.getSelectedWorkBookIndex())
															.getSelectedSheetIndex())
											.getNcaInfo().getProcessingInputs()
											.getLambdazDataobj()
											.getLambdaZDetails()[selectedProfileNumber]
											.getEndTime(),
									lambdaZCalcBefNca.infusionTimeForAllProfile[selectedProfileNumber]);
					
					setRSquareToTextField(i);

					setAdjustedRSquare(i);
				} catch (Exception e) {

					e.printStackTrace();
					
					
					JOptionPane.showMessageDialog(NcaLambdaZDispGui
							.createLambDispGuiInstance().LambdaZSelectorInternalFrame,
							"Error in Lambda Z calcultaion", "Error",
							JOptionPane.YES_OPTION);
					NcaMappingDispGui.createMappingInstance().MappingInternalFrame
							.moveToFront();
					TreePath path = NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
							.getPathForRow(1);
					NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
							.setSelectionPath(path);
					break;
				}

				
				double[] predictedY = getPredY(i);
				if(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[i].getMethodSelected() == 3){
					predictedY = includeZeroesIfRequired(predictedY, i);
				}
				
				NCAoutputPlots
						.createNcaoutputPlotsInst()
						.generateLambdaZplotForTheProfileClicked(
								NcaLambdaZDispGui.createLambDispGuiInstance().observedX,
								NcaLambdaZDispGui.createLambDispGuiInstance().observedY,
								predictedY,
								NcaLambdaZDispGui.createLambDispGuiInstance().profileNamesUsedForProfileNumberDetermination
										.get(i), i);
				
				//populateTable(selectedProfileNumber);
				
				if(NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZCalculationCombo.getSelectedIndex() == 3){
					
					NcaLambdaZDispGui.createLambDispGuiInstance().profileIF.moveToFront();
				}

				NcaLambdaZDispGui.createLambDispGuiInstance().profileGraphGenerated = true;
				NcaLambdaZDispGui.createLambDispGuiInstance().LambdaZSelectorInternalFrame
						.moveToFront();
				
				NcaOptionsGui.createNcaOptionsInstance().optionsInternalFrame.repaint();

				setLogOrLinearView();

			} else {

			}

		}

	}
	
	private boolean[] reverse(boolean[] pred) {
		int i=0;
		int j= pred.length-1;
		while(j>i){
			boolean temp = pred[i];
			pred[i] = pred[j];
			pred[j] = temp;
			i++;
			j--;
		}
		return pred;
	}

	private double[] includeZeroesIfRequired(double[] predictedY, int profileNumber) {
		
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			Hashtable<Integer, Boolean> hashTable;
			
			hashTable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[profileNumber].getInclusionExcusionPoints();
			
			double[] pred = new double[ hashTable.entrySet().size()];
			
			boolean[] presence = new boolean[hashTable.entrySet().size()];
			Iterator it = hashTable.entrySet().iterator();
			int m=0;
			while(it.hasNext()){
				Map.Entry<Integer, Boolean> map = (Map.Entry<Integer, Boolean>)it.next();
				presence[m++] = map.getValue();
			}
			
			presence = reverse(presence);
			int y=0;
		for(int i=0;i<presence.length;i++){
			if(presence[i] == false){
				pred[i] = 0;
				
			} else{
				pred[i] = predictedY[y++];
				
			}

		}
				
				
		//pred = reverseZeroes(pred);
		return pred;
	}




	private double[] reverseZeroes(double[] pred) {
		
		ArrayList<Double> al = new ArrayList<Double>();
		int i = pred.length-1;
		int zeroesCount = 0;
		while(i > 0){
			if(pred[i] == 0 ){
				zeroesCount++;
			} else{
				break;
			}
			i--;
		}
		
		for(int j=0;j<zeroesCount;j++){
			al.add(0.0);
			
		}
		
		for(int j=0;j<=(pred.length-1-zeroesCount);j++){
			al.add(pred[j]);
		}
		double[] ajith = new double[al.size()];
		for(int j=0;j<al.size();j++){
			ajith[j] = al.get(j); 
			
		}
		return ajith;
	}//samrat.himvanth@




	private double[] reverse(double[] pred) {
		int i=0;
		int j= pred.length-1;
		while(j>i){
			pred[i] = pred[i]+pred[j];
			pred[j] = pred[i] - pred[j];
			pred[i] = pred[i] - pred[j];
			i++;
			j--;
		}
		return pred;
	}




	private double[] getPredY(int i) {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		double[] predY = appInfo
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
		.getProcessingInputs().getLambdazDataobj()
		.getLambdaZDetails()[i].getPredictedConc();
		
		return predY;
	}

	private void setLogOrLinearView() throws RowsExceededException, WriteException, BiffException, IOException {

		if (NcaLambdaZDispGui.createLambDispGuiInstance().linearViewRadioButton
				.isSelected() == true) {
			NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZNormalGraphsInternalFramesArrayList
					.get(0).moveToFront();

		} else if (NcaLambdaZDispGui.createLambDispGuiInstance().logViewRadioButton
				.isSelected() == true) {
			NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZLogarithmicGraphsInternalFramesArrayList
					.get(0).moveToFront();
		}
		NcaOptionsGui.createNcaOptionsInstance().regressionParametersInternalFrame
				.setVisible(true);
		
	}

	private void populateTable(
			int selectedProfileNumber) throws RowsExceededException, WriteException, BiffException, IOException {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		populateProfileTable(
				NcaLambdaZDispGui.createLambDispGuiInstance().observedX,
				NcaLambdaZDispGui.createLambDispGuiInstance().observedY,
				
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
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(
												appInfo
														.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getNcaInfo().getProcessingInputs()
						.getMappingDataObj().getxColumnName(),
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
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(
												appInfo
														.getSelectedProjectIndex())
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getNcaInfo().getProcessingInputs()
						.getMappingDataObj().getYcolumnName(), selectedProfileNumber);
		
	}

	private void setLambdaZmethodSelected(int profileSelected) throws RowsExceededException, WriteException, BiffException, IOException {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		methodSelectedProgrameatically = appInfo
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
		.getWorkSheetObjectsAL()
		.get(
				appInfo
						.getProjectInfoAL()
						.get(
								appInfo
										.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(
												appInfo
														.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getSelectedSheetIndex())
		.getNcaInfo().getProcessingInputs()
		.getLambdazDataobj().getLambdaZDetails()[profileSelected]
		.getMethodSelected();
		
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZCalculationCombo
		.setSelectedIndex(methodSelectedProgrameatically);
		
		
		
	}

	private void setAdjustedRSquare(int profileSelected) throws RowsExceededException, WriteException, BiffException, IOException {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		NcaOptionsGui.createNcaOptionsInstance().adjustedRSquareTextField
		.setText(DataManipulationFunctions.createDataManFuncInst().round(appInfo
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
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(
										appInfo
												.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getNcaInfo().getProcessingInputs()
				.getLambdazDataobj().getLambdaZDetails()[profileSelected]
				.getAdjRSquare(), 4)
				+ "");
		
	}

	private void setRSquareToTextField(int profileSelected) throws RowsExceededException, WriteException, BiffException, IOException {	
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		
		
		NcaOptionsGui.createNcaOptionsInstance().rsquareTextField
		.setText(DataManipulationFunctions.createDataManFuncInst().round(appInfo
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
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(
										appInfo
												.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getNcaInfo().getProcessingInputs()
				.getLambdazDataobj().getLambdaZDetails()[profileSelected]
				.getrSquare(), 4)
				+ "");
	
		}
	

	private void setProfileNumberSelected(int profileSelected) {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().setProfileSelected(profileSelected);
		
	}

	void populateProfileTable(final ArrayList<Double> observedX,
			final ArrayList<Double> observedY,
			String getxColumnName, String ycolumnName, int selectedProfileNumber)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		Object col[] = { getxColumnName, ycolumnName, "Select" };
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		Hashtable<Integer, Boolean> hashTable = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber].getInclusionExcusionPoints();
		
		// populate the new profile
		Object[][] s = new Object[observedX.size()][3];
		for (int i = 0; i < observedX.size(); i++) {

			s[i][0] = observedX.get(i);
			if(observedY.get(i) == -3.14159265359 ){
				s[i][1] = "";
			} else
				s[i][1] = observedY.get(i);

			/*if(predictedY[i]!=0){
				s[i][2] = true;
			} else {
				s[i][2] = false;
			}*/
			
			s[i][2] = (Boolean)hashTable.get(i);
			

		}
		DefaultTableModel model = new DefaultTableModel(s, col);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable = new JTable(
				model) {

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

/*		//remove rows for which the concentrations are zeroes
		for(int i=0;i<model.getRowCount();i++){
			if(Double.parseDouble(model.getValueAt(i, 1).toString()) == 0){
				model.removeRow(i);
			}
		}*/
/*		NcaOptionsGui.createNcaOptionsInstance().halfLifeTextField.setText("");
		NcaOptionsGui.createNcaOptionsInstance().numberOfTerminalPointsTextField
				.setText("");
		NcaOptionsGui.createNcaOptionsInstance().rsquareTextField.setText("");
		NcaOptionsGui.createNcaOptionsInstance().adjustedRSquareTextField
				.setText("");*/
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.setRowSelectionAllowed(true);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.setColumnSelectionAllowed(true);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.setCellSelectionEnabled(true);
		TableColumnModel tm = NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.getColumnModel();
		tm.getColumn(2).setWidth(15);


		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.getSelectionModel().addListSelectionListener(
						new ProfileTableListSelectionListener(observedX));

		NcaLambdaZDispGui.createLambDispGuiInstance().profileTableScrollpane
				.removeAll();
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTableScrollpane = null;
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTableScrollpane = new JScrollPane(
				NcaLambdaZDispGui.createLambDispGuiInstance().profileTable);

	/*	for (int i = 0; i < observedX.size(); i++) {
			if (predictedY[i] == 0) {
				NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
						.setSelectionBackground(Color.white);
			} else {
				NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
						.setSelectionBackground(new Color(238, 238, 234));
			}
		}
*/
		NcaLambdaZDispGui.createLambDispGuiInstance().profileIF
				.getContentPane().removeAll();
		NcaLambdaZDispGui.createLambDispGuiInstance().profileIF
				.getContentPane()
				.add(
						NcaLambdaZDispGui.createLambDispGuiInstance().profileTableScrollpane);

		NcaLambdaZDispGui.createLambDispGuiInstance().profileIF.repaint();
		NcaLambdaZDispGui.createLambDispGuiInstance().profileIF.validate();

	}
}
