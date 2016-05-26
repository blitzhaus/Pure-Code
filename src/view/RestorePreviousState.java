package view;

import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreePath;

public class RestorePreviousState {/*	

	public void restoringPreviousState(CAMainScreen caMainScreen, ApplicationInfo readPerst) {
	
		caMainScreen.appInfo.setSelectedSheetIndex(readPerst.getSelectedSheetIndex());
		caMainScreen.appInfo.setRetrievalFlag(readPerst.isRetrievalFlag());
	
		System.out.println("retrival flag=" + caMainScreen.appInfo.isRetrievalFlag());
		System.out
				.println("!!! sheet index:" + caMainScreen.appInfo.getSelectedSheetIndex());
	
		caMainScreen.resPrevState.restoreMappingPreviousState(caMainScreen, readPerst);
	
		System.out.println(" !!!! previous Sort V no:"
				+ caMainScreen.appInfo.getWorkSheetObjectsAL().get(
						caMainScreen.appInfo.getSelectedSheetIndex()).getPkInfo()
						.getProcessingInputs().getMappingDataObj()
						.getSortVariablesListVector().size());
		// after mapping the mapping is changed
	
		caMainScreen.appInfo.getWorkSheetObjectsAL().get(caMainScreen.appInfo.getSelectedSheetIndex())
				.getPkInfo().getProcessingInputs().getMappingDataObj()
				.setIfMappingScreenIsChanged(false);
	
		// after mapping is done we have to click on any one of the
		// dosing/lambdaz/sub areas so that all of them get created
		// then it would be possible to fill the values
		// the numeric 2 indicates the row for dosing screen
		caMainScreen.resPrevState.restoremodelOptionspreviousState(caMainScreen, readPerst);
		TreePath path = caMainScreen.availableComponentsTree.getPathForRow(1);
		caMainScreen.availableComponentsTree.setSelectionPath(path);
	
		// restoredosingoptionsPreviousState(readPerst);
	
		caMainScreen.resPrevState.restoreStrippingDoseDataPreviousState(readPerst);
		caMainScreen.resPrevState.restoreInitialParameterValuePreviousState(caMainScreen, readPerst);
	
		caMainScreen.resPrevState.restoreDosingDataPreviousState(caMainScreen, readPerst);
	
		caMainScreen.resPrevState.restoreUnitDataPreviousState(caMainScreen, readPerst);
	
		// appInfo.setRetrievalFlag(false);
	}

	void restoremodelOptionspreviousState(CAMainScreen caMainScreen, ApplicationInfo readPerst) {
	
		caMainScreen.algebraicEquationModelRadioButton.setSelected(readPerst
				.getWorkSheetObjectsAL().get(readPerst.getSelectedSheetIndex())
				.getPkInfo().getProcessingInputs().getModelInputTab1Obj()
				.getAlgebraicModel());
	
		if (readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getModelInputTab1Obj()
				.getAlgebraicModel() == true) {
	
			caMainScreen.availableLibraryModelComboBox.setEnabled(true);
			caMainScreen.availableLibraryModelComboBox.setSelectedIndex(readPerst
					.getWorkSheetObjectsAL().get(
							readPerst.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getModelInputTab1Obj()
					.getModelNumberForCA());
		}
		if (readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getModelInputTab1Obj()
				.getWeightingScheme() == 0) {
			caMainScreen.observedWeightingRadioButton.setSelected(true);
			caMainScreen.weightingOptionsComboBox.setEnabled(true);
			caMainScreen.weightingOptionsComboBox.setSelectedIndex(readPerst
					.getWorkSheetObjectsAL().get(
							readPerst.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getModelInputTab1Obj()
					.getModelNumberForCA());
		} else if (readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getModelInputTab1Obj()
				.getWeightingScheme() == 1) {
			caMainScreen.predictedWeightingRadioButton.setSelected(true);
			caMainScreen.predictedWeightingOptionsComboBox.setEnabled(true);
			caMainScreen.predictedWeightingOptionsComboBox.setSelectedIndex(readPerst
					.getWorkSheetObjectsAL().get(
							readPerst.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getModelInputTab1Obj()
					.getModelNumberForCA());
		}
	
		caMainScreen.dataTypeComboBoxForCA.setSelectedIndex(readPerst
				.getWorkSheetObjectsAL().get(readPerst.getSelectedSheetIndex())
				.getPkInfo().getProcessingInputs().getModelInputTab1Obj()
				.getDataType());
	
		caMainScreen.doseUnitTextFieldForCA.setText(readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getModelInputTab1Obj().getDoseUnit());
		caMainScreen.normalizationUnitComboBoxForCA.setSelectedIndex(readPerst
				.getWorkSheetObjectsAL().get(readPerst.getSelectedSheetIndex())
				.getPkInfo().getProcessingInputs().getModelInputTab1Obj()
				.getNormalizationIndex());
		caMainScreen.numberOfDoseComboBox
				.setSelectedIndex(readPerst.getWorkSheetObjectsAL().get(
						readPerst.getSelectedSheetIndex()).getPkInfo()
						.getProcessingInputs().getModelInputTab1Obj()
						.getNumberOfDose() - 1);
	
		caMainScreen.initialParameterValueComboBox.setSelectedIndex(readPerst
				.getWorkSheetObjectsAL().get(readPerst.getSelectedSheetIndex())
				.getPkInfo().getProcessingInputs().getModelInputTab2Obj()
				.getSourceOfIntParamValue());
	
		caMainScreen.parameterBoundariesComboBox.setSelectedIndex(readPerst
				.getWorkSheetObjectsAL().get(readPerst.getSelectedSheetIndex())
				.getPkInfo().getProcessingInputs().getModelInputTab2Obj()
				.getParamBoundarySelection());
	
		caMainScreen.minimizationMthodComboBox.setSelectedIndex(readPerst
				.getWorkSheetObjectsAL().get(readPerst.getSelectedSheetIndex())
				.getPkInfo().getProcessingInputs().getModelInputTab2Obj()
				.getMinimizationMethod());
		caMainScreen.odeSolverMethodComboBox.setSelectedIndex(readPerst
				.getWorkSheetObjectsAL().get(readPerst.getSelectedSheetIndex())
				.getPkInfo().getProcessingInputs().getModelInputTab2Obj()
				.getOdeSolverMethod());
		caMainScreen.incrementsForpartialDerivativesTextField.setText(readPerst
				.getWorkSheetObjectsAL().get(readPerst.getSelectedSheetIndex())
				.getPkInfo().getProcessingInputs().getModelInputTab2Obj()
				.getIncForPartialDerivative());
		caMainScreen.numberOfPredictedValuesTextField.setText(readPerst
				.getWorkSheetObjectsAL().get(readPerst.getSelectedSheetIndex())
				.getPkInfo().getProcessingInputs().getModelInputTab2Obj()
				.getNumberOfPredictedValue());
		caMainScreen.convergenceCriteriaTextField.setText(readPerst.getWorkSheetObjectsAL()
				.get(readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getModelInputTab2Obj()
				.getConvergenceCriteria());
		caMainScreen.numberOfIterationTextField.setText(readPerst.getWorkSheetObjectsAL()
				.get(readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getModelInputTab2Obj()
				.getNumberOfIterations());
		caMainScreen.meanSquareTextField.setText(readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getModelInputTab2Obj()
				.getMeanSquareValue());
	
		caMainScreen.lambdaValueTextField.setText(readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getModelInputTab2Obj().getLambdaValue());
		caMainScreen.muTextField.setText(readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getModelInputTab2Obj().getMuValue());
		caMainScreen.roundToTextField.setText(readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getModelInputTab2Obj()
				.getRoundingOffValue());
	
	}

	void restoreDosingDataPreviousState(CAMainScreen caMainScreen, ApplicationInfo readPerst) {
	
		System.out.println("!!!! in the dose reloding method:");
	
		int rowCount = caMainScreen.appInfo.getWorkSheetObjectsAL().get(
				caMainScreen.appInfo.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getDosingDataObj().getDosingDSForNCA().length;
		int columnCount = caMainScreen.appInfo.getWorkSheetObjectsAL().get(
				caMainScreen.appInfo.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getDosingDataObj().getDosingDSForNCA()[0].length;
	
		caMainScreen.dosingData = new String[rowCount][columnCount];
	
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
	
				String S = new String(readPerst.getWorkSheetObjectsAL().get(
						readPerst.getSelectedSheetIndex()).getPkInfo()
						.getProcessingInputs().getDosingDataObj()
						.getDosingValueAt(i, j));
				caMainScreen.dosingData[i][j] = S;
			}
		}
	
		String[] headers = { "#Dose", "Dose", "DosingTime" };
		int[] cIdx = { 0 };
	
		caMainScreen.tableForDosing = new MyJTable(caMainScreen.dosingData, headers, cIdx);
		new ReorderableJList(caMainScreen.tableForDosing);
		caMainScreen.DosingInternalFrame.add(caMainScreen.tableForDosing);
		System.out.println(" after entering value in dosing screen");
		for (int i = 0; i < caMainScreen.tableForDosing.getRowCount(); i++) {
			for (int j = 0; j < caMainScreen.tableForDosing.getColumnCount(); j++) {
	
				System.out.println("dose value "
						+ caMainScreen.tableForDosing.getValueAt(i, j) + "\t dosing data="
						+ caMainScreen.dosingData[i][j]);
	
			}
		}
	
		for (int i = 0; i < caMainScreen.tableForDosing.getRowCount(); i++)
			for (int j = 0; j < caMainScreen.tableForDosing.getColumnCount(); j++)
				caMainScreen.tableForDosing.isCellEditable(i, j);
		caMainScreen.tableForDosing.setVisible(true);
	
		
		 * tableForDosing.validate(); tableForDosing.repaint();
		 
		caMainScreen.tableForDosing.updateUI();
		caMainScreen.DosingInternalFrame.getContentPane().add(caMainScreen.tableForDosing);
		caMainScreen.DosingInternalFrame.repaint();
		caMainScreen.DosingInternalFrame.setSize(caMainScreen.DosingInternalFrame.getSize());
		System.out.println("!!!! end of reloding method:");
	}

	void restoreInitialParameterValuePreviousState(
			CAMainScreen caMainScreen, ApplicationInfo readPerst) {
		for (int i = 0; i < caMainScreen.tableForInitialParameterValueForCA.getRowCount(); i++)
			for (int j = 0; j < caMainScreen.tableForInitialParameterValueForCA
					.getColumnCount(); j++)
				caMainScreen.tableForInitialParameterValueForCA.setValueAt(readPerst
						.getWorkSheetObjectsAL().get(
								readPerst.getSelectedSheetIndex()).getPkInfo()
						.getProcessingInputs().getInitialParameterValueObj()
						.getInitialParameterValueAt(i, j), i, j);
	
	}

	void restoreUnitDataPreviousState(CAMainScreen caMainScreen, ApplicationInfo readPerst) {
		// tableForPreferredUnitForCA = new JTable(10,3);
		
		int rowCount = caMainScreen.parameterUnitsTableForCA.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			((DefaultTableModel) caMainScreen.parameterUnitsTableForCA
					.getModel()).removeRow(i - 1);
			System.out.println("removed row " + (i - 1));
		}

		for (int i = 0; i < caMainScreen.parameterUnitsTableForCA.getRowCount(); i++) {
			caMainScreen.parameterUnitsTableForCA.setValueAt(readPerst
					.getWorkSheetObjectsAL().get(
							readPerst.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getParameterUnitsDataObj()
					.getParameterbyGroupValueAt(i), i, 0);
	
			caMainScreen.parameterUnitsTableForCA.setValueAt(readPerst
					.getWorkSheetObjectsAL().get(
							readPerst.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getParameterUnitsDataObj()
					.getDefaultUnitValueAt(i), i, 1);
	
			caMainScreen.parameterUnitsTableForCA.setValueAt("", i, 2);
	
			caMainScreen.parameterUnitsTableForCA.setValueAt(readPerst
					.getWorkSheetObjectsAL().get(
							readPerst.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getParameterUnitsDataObj()
					.getPreferredUnit(i), i, 2);
	
			caMainScreen.appInfo.getWorkSheetObjectsAL()
					.get(caMainScreen.appInfo.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getParameterUnitsDataObj()
					.setPreferredUnit(
							i,
							readPerst.getWorkSheetObjectsAL().get(
									readPerst.getSelectedSheetIndex())
									.getPkInfo().getProcessingInputs()
									.getParameterUnitsDataObj()
									.getPreferredUnit(i));
	
		}
		caMainScreen.parameterUnitsTableForCA.repaint();
		caMainScreen.parameterUnitsInternalFrame.repaint();
	}

	void restoreStrippingDoseDataPreviousState(ApplicationInfo readPerst) {
	}

	void restoreMappingPreviousState(CAMainScreen caMainScreen, ApplicationInfo readPerst) {
	
		int size = caMainScreen.availableVariablesListmodel.size();
		for (int i = caMainScreen.availableVariablesListmodel.size() - 1; i >= 0; i--) {
			caMainScreen.availableVariablesListmodel.remove(i);
		}
	
		// setting the available columns
		for (int i = 0; i < readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getMappingDataObj()
				.getAvailableColumnsVector().size(); i++) {
			caMainScreen.availableVariablesListmodel.add(i, readPerst
					.getWorkSheetObjectsAL().get(
							readPerst.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().get(i));
	
		}
	
		// since sort variables are not populated while creating a new
		// ncaMainScreen
		// we need not remove anything, so
		// setting the sort variables
	
		caMainScreen.appInfo.getWorkSheetObjectsAL().get(caMainScreen.appInfo.getSelectedSheetIndex())
				.getPkInfo().getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().setSize(0);
	
		for (int i = 0; i < readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size(); i++) {
			caMainScreen.sortVariableListModel.add(i, readPerst.getWorkSheetObjectsAL().get(
					readPerst.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i));
	
			caMainScreen.appInfo.getWorkSheetObjectsAL()
					.get(caMainScreen.appInfo.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().add(
							readPerst.getWorkSheetObjectsAL().get(
									readPerst.getSelectedSheetIndex())
									.getPkInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().get(i));
	
			System.out.println("sort variable:"
					+ caMainScreen.appInfo.getWorkSheetObjectsAL().get(
							caMainScreen.appInfo.getSelectedSheetIndex()).getPkInfo()
							.getProcessingInputs().getMappingDataObj()
							.getSortVariablesListVector().get(i));
		}
	
		// setting the time column
	
		caMainScreen.xVariableTextField.setText(readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName());
	
		// setting the concentration column
	
		CAMainScreen.yVariableTextField.setText(readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName());
	
		// setting the end time column
		// restore this value only if the previous analysis is of model type
		// urine
		if (readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getModelInputsObj().getModelType() == 1) {
			caMainScreen.endTimeVariableTextField.setText(readPerst.getWorkSheetObjectsAL()
					.get(readPerst.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getMappingDataObj()
					.getEndTimeColumnName());
	
			caMainScreen.volumeVariableTextField.setText(readPerst.getWorkSheetObjectsAL()
					.get(readPerst.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getMappingDataObj()
					.getVolumeColumnName());
		}
	
		// setting the subject column name
		// restore this value only when if it was a sparse analysis
		if (readPerst.getWorkSheetObjectsAL().get(
				readPerst.getSelectedSheetIndex()).getPkInfo()
				.getProcessingInputs().getModelInputsObj()
				.getisSparseSelected() == true) {
	
			caMainScreen.subjectTextField.setText(readPerst.getWorkSheetObjectsAL().get(
					readPerst.getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSubjectColumnName());
		}
	
	}
*/}
