package view;

import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Common.ThousandsSeperator;

public class DSHandlers {

	public static DSHandlers DS_EVENT_HANDLER = null;

	public static DSHandlers createDesStatEventHandlerInst() {
		if (DS_EVENT_HANDLER == null) {
			DS_EVENT_HANDLER = new DSHandlers();
		}
		return DS_EVENT_HANDLER;
	}

	String tableName;
	DefaultMutableTreeNode node;
	String nodeName;
	String analysisType;

	void setupAvailableTreeSelectionHandler() {

		if (DesStatSetupAvailComp.createDesStatAvailComp().availableComponentsTree
				.getSelectionPath() != null) {

			String plotName = DesStatSetupAvailComp.createDesStatAvailComp().availableComponentsTree
					.getSelectionPath().getLastPathComponent().toString();

			String[] pathSplits = DesStatSetupAvailComp
					.createDesStatAvailComp().availableComponentsTree
					.getSelectionPath().toString().split(",");

			String[] plotNameSplits = plotName.split(" ");

			if (DesStatSetupAvailComp.createDesStatAvailComp().availableComponentsTree
					.getMinSelectionRow() == 0) {
				ImportedDataSheet.importedDataSheetFrame.moveToFront();
				DDViewLayer.createFeaturesPanelInstance().PreviewLable
						.setVisible(true);
			}

			if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Mapping]")) {
				DesStatSetupDespComp.createDesStatSetupDispInst().MappingInternalFrame
						.moveToFront();
				DesStatOptComp.createDesStatOptInst().optionsInternalFrame
						.moveToFront();

			}

		}

	}

	void availableColumnListSelectionHandlr() {

		DesStatSetupDespComp.createDesStatSetupDispInst().SortAndAvailableButton
				.setIcon(DesStatSetupDespComp.createDesStatSetupDispInst().toLeft);
		DesStatSetupDespComp.createDesStatSetupDispInst().moveToCarryAlongButton
				.setIcon(DesStatSetupDespComp.createDesStatSetupDispInst().toRight);
		DesStatSetupDespComp.createDesStatSetupDispInst().isAvailableToSort = true;
		DesStatSetupDespComp.createDesStatSetupDispInst().isSortToAvailable = false;
		DesStatSetupDespComp.createDesStatSetupDispInst().isAvailableToWeight = true;
		DesStatSetupDespComp.createDesStatSetupDispInst().isWeightToAvailable = false;
		DesStatSetupDespComp.createDesStatSetupDispInst().isAvailableToCarryAlong = true;
		DesStatSetupDespComp.createDesStatSetupDispInst().isCarryAlongToAvailable = false;

	}

	void sortAndAvailButtonHanler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (DesStatSetupDespComp.createDesStatSetupDispInst().isAvailableToSort == true) {
			
			int selectedIndex = DesStatSetupDespComp
					.createDesStatSetupDispInst().availableColumnsList
					.getSelectedIndex();
			
			String moving = (String) DesStatSetupDespComp
					.createDesStatSetupDispInst().availableColumnsList
					.getSelectedValue();
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getDesStatsInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getSortVariablesListVector()
					.add(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getDesStatsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size(),
							moving);
			DesStatSetupDespComp.createDesStatSetupDispInst().sortVariableListModel
					.add(
							DesStatSetupDespComp.createDesStatSetupDispInst().sortVariableListModel
									.getSize(), moving);
			DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
					.remove(selectedIndex);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedIndex);

			

		}
		if (DesStatSetupDespComp.createDesStatSetupDispInst().isSortToAvailable == true) {

			String moving = (String) DesStatSetupDespComp
					.createDesStatSetupDispInst().sortVariablesList
					.getSelectedValue();
			int selectedIndex = DesStatSetupDespComp
					.createDesStatSetupDispInst().sortVariablesList
					.getSelectedIndex();
			DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
					.add(
							DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
									.getSize(), moving);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getDesStatsInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getDesStatsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().remove(selectedIndex);

			DesStatSetupDespComp.createDesStatSetupDispInst().sortVariableListModel
					.remove(selectedIndex);

		}

	}

	public void sortVarListSlectionHandler() {

		DesStatSetupDespComp.createDesStatSetupDispInst().SortAndAvailableButton
				.setIcon(DesStatSetupDespComp.createDesStatSetupDispInst().toRight);
		DesStatSetupDespComp.createDesStatSetupDispInst().isSortToAvailable = true;
		DesStatSetupDespComp.createDesStatSetupDispInst().isAvailableToSort = false;

	}

	public void availToCarryButtonHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (DesStatSetupDespComp.createDesStatSetupDispInst().isAvailableToCarryAlong == true) {
			String moving = (String) DesStatSetupDespComp
					.createDesStatSetupDispInst().availableColumnsList
					.getSelectedValue();

			int selectedIndex = DesStatSetupDespComp
					.createDesStatSetupDispInst().availableColumnsList
					.getSelectedIndex();
			int originalColumnIndex = 0;
			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getColumnPropertiesArrayList()
						.get(i).getColumnName())) {
					originalColumnIndex = i;
					break;
				}
			}

			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getColumnPropertiesArrayList().get(originalColumnIndex)
					.getDataType().equals("Numeric")) {
				// add the selected column to the carry along vector in appInfo
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo()
						.getProcessingInputs()
						.getMappingDataObj()
						.getCarryAlongVariablesListVector()
						.add(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getDesStatsInfo()
										.getProcessingInputs()
										.getMappingDataObj()
										.getCarryAlongVariablesListVector()
										.size(), moving);

				// set the units of the added column to appInfo from the column
				// properties
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo()
						.getProcessingInputs()
						.getParameterUnitsDataObj()
						.setConcMassUnit(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
										.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL()
										.get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
														.get(
																appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
														.getSelectedSheetIndex())
										.getDesStatsInfo()
										.getColumnPropertiesArrayList().get(
												selectedIndex)
										.getUnitBuilderDataObj()
										.getMasPrefixIndex()
										+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getWorkSheetObjectsAL()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())
												.getDesStatsInfo()
												.getColumnPropertiesArrayList()
												.get(selectedIndex)
												.getUnitBuilderDataObj()
												.getMassunitIndex());

				// carryAlongVariablesListVector.add(carryAlongVariablesListVector.size(),
				// moving);
				DesStatSetupDespComp.createDesStatSetupDispInst().carryAlongModel
						.add(
								DesStatSetupDespComp
										.createDesStatSetupDispInst().carryAlongModel
										.getSize(), moving);
				DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
						.remove(selectedIndex);

				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().getAvailableColumnsVector()
						.remove(selectedIndex);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getMappingDataObj().setCarryAlongColumnName(moving);

				for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getColumnPropertiesArrayList()
						.size(); i++) {
					if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getDesStatsInfo().getColumnPropertiesArrayList()
							.get(i).getColumnName())) {
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getDesStatsInfo().getProcessingInputs()
								.getMappingDataObj()
								.setyColumnCorrespondinOriginalIndex(i);
					}
				}
			} else {
				JOptionPane
						.showMessageDialog(
								DescriptiveStatMainPage
										.createDescStatMainPageInst().desStatsMainIF,
								"Please ensure the data of the column selected is numeric",
								"Conform", JOptionPane.YES_OPTION);
			}

		}

		if (DesStatSetupDespComp.createDesStatSetupDispInst().isCarryAlongToAvailable == true) {
			String moving = (String) DesStatSetupDespComp
					.createDesStatSetupDispInst().carryAlongList
					.getSelectedValue();
			int selectedIndex = DesStatSetupDespComp
					.createDesStatSetupDispInst().carryAlongList
					.getSelectedIndex();
			DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
					.add(
							DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
									.getSize(), moving);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getDesStatsInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getDesStatsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getCarryAlongVariablesListVector().remove(selectedIndex);

			DesStatSetupDespComp.createDesStatSetupDispInst().carryAlongModel
					.remove(selectedIndex);

			// if there are no columns in the carry along vetor then remove the
			// units also
			// from appInfo
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getCarryAlongVariablesListVector().size() == 0) {

				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getProcessingInputs()
						.getParameterUnitsDataObj().setConcMassUnit(
								new String(""));

			}

		}

	}

	public void caryAlongListSelectionHandler() {

		DesStatSetupDespComp.createDesStatSetupDispInst().moveToCarryAlongButton
				.setIcon(DesStatSetupDespComp.createDesStatSetupDispInst().toLeft);
		DesStatSetupDespComp.createDesStatSetupDispInst().isCarryAlongToAvailable = true;
		DesStatSetupDespComp.createDesStatSetupDispInst().isAvailableToCarryAlong = false;

	}

	public void weightTextFieldFocusHandler() {

		/*
		 * DesStatSetupDespComp.createDesStatSetupDispInst().weightAndAvailableButton
		 * .setText("-->");
		 */
		DesStatSetupDespComp.createDesStatSetupDispInst().isWeightToAvailable = true;
		DesStatSetupDespComp.createDesStatSetupDispInst().isAvailableToWeight = false;

	}

	public void weightToAvailButtonHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if ((DesStatSetupDespComp.createDesStatSetupDispInst().isAvailableToWeight == true)) {
			String moving = (String) DesStatSetupDespComp
					.createDesStatSetupDispInst().availableColumnsList
					.getSelectedValue();
			int selectedindex = DesStatSetupDespComp
					.createDesStatSetupDispInst().availableColumnsList
					.getSelectedIndex();
			
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedindex);

			DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
					.remove(selectedindex);
			DesStatSetupDespComp.createDesStatSetupDispInst().weightVariableTextField
					.setText(moving);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getMappingDataObj()
					.setWeightColumnName(moving);

			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getColumnPropertiesArrayList()
						.get(i).getColumnName())) {
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getDesStatsInfo().getProcessingInputs()
							.getMappingDataObj()
							.setWeightColumnCorrespondinOriginalIndex(i);
				}
			}

		}

		if (DesStatSetupDespComp.createDesStatSetupDispInst().isWeightToAvailable) {
			String moving = DesStatSetupDespComp.createDesStatSetupDispInst().weightVariableTextField
					.getText();
			DesStatSetupDespComp.createDesStatSetupDispInst().weightVariableTextField
					.setText(null);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getDesStatsInfo()
					.getProcessingInputs()
					.getMappingDataObj()
					.getAvailableColumnsVector()
					.add(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getDesStatsInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);

			DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
					.add(
							DesStatSetupDespComp.createDesStatSetupDispInst().availableVariablesListmodel
									.getSize(), moving);
		}

	}

	public void resAvailOutputsTreeHandler() {

		if (DesStatResAvailComp.createDesStatResAvailInst().availableOutputsTree
				.getSelectionPath() != null) {

			String plotName = DesStatResAvailComp.createDesStatResAvailInst().availableOutputsTree
					.getSelectionPath().getLastPathComponent().toString();

			String[] pathSplits = DesStatResAvailComp
					.createDesStatResAvailInst().availableOutputsTree
					.getSelectionPath().toString().split(",");

			String[] plotNameSplits = plotName.split(" ");

			if (pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Final Parametes]")) {
				DDViewLayer.createmenuBarInstance().exportTextoutput
						.setEnabled(false);
				DDViewLayer.createmenuBarInstance().exportImage.setEnabled(false);
				DDViewLayer.createmenuBarInstance().exportTable.setEnabled(true);

				DesStatResDispComp.createDesStatResDispInst().finalParametersinternalFrame
						.moveToFront();
				DesStatResDispComp.createDesStatResDispInst().carryThisToOtherComponents = true;
			} else {
				DesStatResDispComp.createDesStatResDispInst().carryThisToOtherComponents = false;
			}

			if (pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Complete output]")) {
				DDViewLayer.createmenuBarInstance().exportTextoutput
						.setEnabled(true);
				DDViewLayer.createmenuBarInstance().exportImage.setEnabled(false);
				DDViewLayer.createmenuBarInstance().exportTable.setEnabled(false);

				DesStatResDispComp.createDesStatResDispInst().textCompleteOutputInternalFrame
						.moveToFront();
			}

		}

	}

	boolean checkValidInputs() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		int sortVarListSize = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();
		
		int summaryVarSize = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getDesStatsInfo()
				.getProcessingInputs().getMappingDataObj().getCarryAlongVariablesListVector().size();

		if ((sortVarListSize == 0) || (summaryVarSize == 0)) {
			return false;
		} else {
			String lastEntryName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(sortVarListSize - 1);

			int originalIndex = 0;
			int noColumns = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getColumnPropertiesArrayList().size();
			for (int i = 0; i < noColumns; i++) {

				if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getDesStatsInfo().getColumnPropertiesArrayList()
						.get(i).getColumnName().equals(lastEntryName)) {
					originalIndex = i;
					break;
				}
			}
/*
			if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getDesStatsInfo()
					.getColumnPropertiesArrayList().get(originalIndex)
					.getDataType().equals("Numeric")) {
				return true;
			} else {
				return false;
			}*/
		}
		return true;
	}

	void includeAsInput() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String plotName = DesStatResAvailComp.createDesStatResAvailInst().availableOutputsTree
				.getSelectionPath().getLastPathComponent().toString();
		String[] pathSplits = DesStatResAvailComp.createDesStatResAvailInst().availableOutputsTree
				.getSelectionPath().toString().split(",");

		String[] plotNameSplits = plotName.split(" ");
		String[][] sheeetData = null;

		if (DesStatResAvailComp.createDesStatResAvailInst().availableOutputsTree
				.getSelectionPath().getParentPath().toString().contains(
						"Sheets")) {
			int chosenOption = JOptionPane.showConfirmDialog(DesStatResDispComp
					.createDesStatResDispInst().displayResultsInternalFrame,
					"Use as a input for a different analysis", "Conform",
					JOptionPane.YES_OPTION, 0);

			if (chosenOption == 0) {

				// get the selected node's corresponding table data
				sheeetData = getCorespondingSheetData(sheeetData, pathSplits);

				// add the data structure to array list of data structures in
				// ApplicationInfo
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().add(new WorkSheetsInfo());
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getDataStructuresArrayList().add(sheeetData);

				String sheetName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getSheetName();

				analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getAnalysisType();
				// set sheetName
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL().size() - 1)
						.setSheetName(
								sheetName + "_" + analysisType + "_"
										+ tableName);

				// set selected row as 1
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getWorkSheetObjectsAL().size() - 1)
						.setStartRow(1);

				// add a node to project explorer tree
				updateProjectExplorer();

				// set default column properties
				setDefaultColumnProperties();
			}
		} else {

		}

	}

	private void setDefaultColumnProperties() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL().size() - 1)
				.getColumnPropertiesArrayList().add(new ColumnProperties());

	}

	private void updateProjectExplorer() {
		boolean hasNode = checkIfNodePresent();
		performNodeCreationInPE(hasNode);

	}

	private void performNodeCreationInPE(boolean hasNode) {
		// the analysis node is present we just have to add another child to it.
		DefaultMutableTreeNode analysisNode = null;
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (hasNode == true) {

		} else if (hasNode == false) {
			
			String workBookSelected = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkBookName();
			
			Enumeration en = DDViewLayer.createPEInstance().workSpace.breadthFirstEnumeration();
			
			analysisNode = getAnalysisNode(workBookSelected, analysisNode, en);
			
			
			

			DefaultTreeModel model = (DefaultTreeModel) DDViewLayer
					.createPEInstance().tree1.getModel();

			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
					nodeName);
			analysisNode.add(newNode);

			model.reload();
		}

	}
	
	private DefaultMutableTreeNode getAnalysisNode(String workBookSelected,
			DefaultMutableTreeNode analysisNode, Enumeration en) {

		while (en.hasMoreElements()) {

			DefaultMutableTreeNode next = (DefaultMutableTreeNode) en
					.nextElement();

			if (next.toString().equals("Data")) {
				for (int i = 0; i < next.getChildCount(); i++) {

					String child = next.getChildAt(i).toString();
					if (workBookSelected.equals(child)) {
						analysisNode = (DefaultMutableTreeNode) next
								.getChildAt(i);
						return analysisNode;
					}
				}
			}
		}
		return analysisNode;

	}
	

	private boolean checkIfNodePresent() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String sheetName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getSheetName();
		Enumeration en = DDViewLayer.createPEInstance().workSpace
				.breadthFirstEnumeration();
		boolean hasNode = false;
		// iterate through the enumeration
		while (en.hasMoreElements()) {
			// get the node
			node = (DefaultMutableTreeNode) en.nextElement();

			// match the string with the user-object of the node
			nodeName = sheetName + "_" + analysisType + "_" + tableName;
			if ((node.getUserObject().toString()).equals(nodeName)) {
				hasNode = true;
				break;
			} else {
			}
		}

		return hasNode;
	}

	private String[][] getCorespondingSheetData(String[][] sheeetData,
			String[] pathSplits) {

		if (pathSplits[pathSplits.length - 1]
				.equalsIgnoreCase(" Final Parameters]")) {
			sheeetData = copySelectedSheetData(DesStatResDispComp
					.createDesStatResDispInst().finalparametersVerticalDisplayTable);
			tableName = "Final Parameters";
		}
		return sheeetData;

	}

	private String[][] copySelectedSheetData(JTable table) {

		String[][] data = new String[table.getRowCount() + 1][table
				.getColumnCount()];
		TableColumnModel tm = table.getColumnModel();

		// include header as 1st row in data structure
		for (int j = 0; j < data[0].length; j++) {
			TableColumn tc = tm.getColumn(j);
			data[0][j] = tc.getHeaderValue().toString();
		}

		// copy the table data into the remaining rows of data.
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {

				try {
					data[i][j] = ThousandsSeperator.createThouSepInst()
							.removeComma(table.getValueAt(i - 1, j).toString());
				} catch (NullPointerException ne) {
					data[i][j] = "";
				}

			}
		}

		return data;
	}
}
