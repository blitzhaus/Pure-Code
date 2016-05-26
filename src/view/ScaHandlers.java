package view;

import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import Common.ThousandsSeperator;

public class ScaHandlers {

	public static ScaHandlers SCA_HAND = null;

	public static ScaHandlers createScaHandInst() {
		if (SCA_HAND == null) {
			SCA_HAND = new ScaHandlers("just object creation");
		}
		return SCA_HAND;
	}

	String tableName;
	DefaultMutableTreeNode node;
	String nodeName;
	String analysisType;

	public ScaHandlers(String dummy) {

	}

	void availableCompTreeHandler() {

		if (ScaSetupAvailComp.createSetupAvailCompInst().availableComponentsTree
				.getSelectionPath() != null) {
			System.out
					.println("The selected path is "
							+ ScaSetupAvailComp.createSetupAvailCompInst().availableComponentsTree
									.getSelectionPath());
			String plotName = ScaSetupAvailComp.createSetupAvailCompInst().availableComponentsTree
					.getSelectionPath().getLastPathComponent().toString();
			System.out
					.println("The plot path in  string format is "
							+ ScaSetupAvailComp.createSetupAvailCompInst().availableComponentsTree
									.getSelectionPath().toString());
			String[] pathSplits = ScaSetupAvailComp.createSetupAvailCompInst().availableComponentsTree
					.getSelectionPath().toString().split(",");
			System.out.println("The last split in the path is "
					+ pathSplits[pathSplits.length - 1]);
			String[] plotNameSplits = plotName.split(" ");

			if (ScaSetupAvailComp.createSetupAvailCompInst().availableComponentsTree
					.getMinSelectionRow() == 0) {
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToFront();
				DDViewLayer.createFeaturesPanelInstance().PreviewLable
						.setVisible(true);
			}

			if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Mapping]")) {
				ScaSetupDispComp.createSetupDispConpInst().mappingInternalFrame
						.moveToFront();
				ScaOptions.createScaOptInst().optionsInternalFrame
						.moveToFront();

			}

		}

	}

	void availableColListHandler() {

		ScaSetupDispComp.createSetupDispConpInst().SortAndAvailableButton
				.setIcon(ScaSetupDispComp.createSetupDispConpInst().toRight);
		ScaSetupDispComp.createSetupDispConpInst().timeAndAvailableButton
				.setIcon(ScaSetupDispComp.createSetupDispConpInst().toRight);

		ScaSetupDispComp.createSetupDispConpInst().moveToConcentrationButton
				.setIcon(ScaSetupDispComp.createSetupDispConpInst().toRight);
		ScaSetupDispComp.createSetupDispConpInst().moveToEffectButton
				.setIcon(ScaSetupDispComp.createSetupDispConpInst().toRight);
		ScaSetupDispComp.createSetupDispConpInst().isAvailableToSort = true;
		ScaSetupDispComp.createSetupDispConpInst().isSortToAvailable = false;
		ScaSetupDispComp.createSetupDispConpInst().isAvailableToTime = true;
		ScaSetupDispComp.createSetupDispConpInst().isTimeToAvailable = false;

		ScaSetupDispComp.createSetupDispConpInst().isAvailableToConcentration = true;
		ScaSetupDispComp.createSetupDispConpInst().isConcentrationToAvailable = false;
		ScaSetupDispComp.createSetupDispConpInst().isAvailableToEffect = true;
		ScaSetupDispComp.createSetupDispConpInst().isEffectToAvailable = false;

	}

	void sortAndAvailButttonHand() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		if (ScaSetupDispComp.createSetupDispConpInst().isAvailableToSort == true) {
			int selectedIndex = ScaSetupDispComp.createSetupDispConpInst().availableColumnsList
					.getSelectedIndex();
			String moving = (String) ScaSetupDispComp.createSetupDispConpInst().availableColumnsList
					.getSelectedValue();

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getScaInfo()
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
									.getScaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getSortVariablesListVector().size(),
							moving);
			ScaSetupDispComp.createSetupDispConpInst().sortVariableListModel
					.add(
							ScaSetupDispComp.createSetupDispConpInst().sortVariableListModel
									.getSize(), moving);
			ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
					.remove(selectedIndex);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedIndex);
		}
		if (ScaSetupDispComp.createSetupDispConpInst().isSortToAvailable == true) {

			String moving = (String) ScaSetupDispComp.createSetupDispConpInst().sortVariablesList
					.getSelectedValue();
			int selectedIndex = ScaSetupDispComp.createSetupDispConpInst().sortVariablesList
					.getSelectedIndex();
			ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
					.add(
							ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
									.getSize(), moving);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getScaInfo()
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
									.getScaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);

			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().remove(selectedIndex);
			ScaSetupDispComp.createSetupDispConpInst().sortVariableListModel
					.remove(selectedIndex);

		}

	}

	void sortVarListHandler() {

		ScaSetupDispComp.createSetupDispConpInst().SortAndAvailableButton
				.setIcon(ScaSetupDispComp.createSetupDispConpInst().toLeft);
		ScaSetupDispComp.createSetupDispConpInst().isSortToAvailable = true;
		ScaSetupDispComp.createSetupDispConpInst().isAvailableToSort = false;

	}

	void condVarTextFocusHandler() {

		ScaSetupDispComp.createSetupDispConpInst().moveToConcentrationButton
				.setIcon(ScaSetupDispComp.createSetupDispConpInst().toLeft);
		ScaSetupDispComp.createSetupDispConpInst().isConcentrationToAvailable = true;
		ScaSetupDispComp.createSetupDispConpInst().isAvailableToConcentration = false;

	}

	void availaToConcHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if ((ScaSetupDispComp.createSetupDispConpInst().isAvailableToConcentration == true)) {
			String moving = (String) ScaSetupDispComp.createSetupDispConpInst().availableColumnsList
					.getSelectedValue();
			int selectedindex = ScaSetupDispComp.createSetupDispConpInst().availableColumnsList
					.getSelectedIndex();

			// remove the column fromt the avaiclable columns vector in appInfo
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedindex);

			// remove the column from the available column Jlist
			ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
					.remove(selectedindex);

			// set the conc column to the conc text field
			ScaSetupDispComp.createSetupDispConpInst().conbcentrationVariableTextField
					.setText(moving);

			// set the conc column name in appInfo
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getMappingDataObj().setYcolumnName(
							moving);
			ScaSetupDispComp.createSetupDispConpInst().concentrationColumnName = moving;

			// set the conc units to appInfo from the column properties
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getScaInfo()
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
									.getScaInfo()
									.getColumnPropertiesArrayList().get(
											selectedindex)
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
											.getScaInfo()
											.getColumnPropertiesArrayList()
											.get(selectedindex)
											.getUnitBuilderDataObj()
											.getMassunitIndex());

			// set the original column index of the conc column in appInfo
			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(i).getColumnName())) {
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getScaInfo().getProcessingInputs()
							.getMappingDataObj()
							.setyColumnCorrespondinOriginalIndex(i);
				}
			}
		}

		if (ScaSetupDispComp.createSetupDispConpInst().isConcentrationToAvailable == true) {
			String moving = ScaSetupDispComp.createSetupDispConpInst().conbcentrationVariableTextField
					.getText();
			ScaSetupDispComp.createSetupDispConpInst().conbcentrationVariableTextField
					.setText(null);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getScaInfo()
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
									.getScaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);
			ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
					.add(
							ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
									.getSize(), moving);

			// clear the conc units from appInfo
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getParameterUnitsDataObj()
					.setConcMassUnit(new String(""));
		}

	}

	void effectVarHandler() {

		ScaSetupDispComp.createSetupDispConpInst().moveToEffectButton
				.setIcon(ScaSetupDispComp.createSetupDispConpInst().toLeft);
		ScaSetupDispComp.createSetupDispConpInst().isEffectToAvailable = true;
		ScaSetupDispComp.createSetupDispConpInst().isAvailableToEffect = false;

	}

	void effectAndAvailButtonHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if ((ScaSetupDispComp.createSetupDispConpInst().isAvailableToEffect == true)) {
			String moving = (String) ScaSetupDispComp.createSetupDispConpInst().availableColumnsList
					.getSelectedValue();
			int selectedindex = ScaSetupDispComp.createSetupDispConpInst().availableColumnsList
					.getSelectedIndex();
			System.out.println("The selected Index is ============="
					+ selectedindex);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedindex);
			ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
					.remove(selectedindex);
			ScaSetupDispComp.createSetupDispConpInst().effectVariableTextField
					.setText(moving);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getMappingDataObj()
					.setEffectColumnName(moving);

			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(i).getColumnName())) {
					ScaSetupDispComp.createSetupDispConpInst().effectColumnCorrespondinOriginalIndex = i;
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getScaInfo().getProcessingInputs()
							.getMappingDataObj()
							.setEffectCorrespondingOriginalIndex(i);
				}
			}
		}

		if (ScaSetupDispComp.createSetupDispConpInst().isEffectToAvailable) {
			String moving = ScaSetupDispComp.createSetupDispConpInst().effectVariableTextField
					.getText();
			ScaSetupDispComp.createSetupDispConpInst().effectVariableTextField
					.setText(null);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getScaInfo()
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
									.getScaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);
			ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
					.add(
							ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
									.getSize(), moving);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getMappingDataObj()
					.setEffectColumnName("");
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getMappingDataObj()
					.setEffectCorrespondingOriginalIndex(-1);
		}

	}

	void timeVarTextFieldHandler() {

		ScaSetupDispComp.createSetupDispConpInst().timeAndAvailableButton
				.setIcon(ScaSetupDispComp.createSetupDispConpInst().toLeft);
		ScaSetupDispComp.createSetupDispConpInst().isTimeToAvailable = true;
		ScaSetupDispComp.createSetupDispConpInst().isAvailableToTime = false;

	}

	void timeAndAvailButtonHandler() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if ((ScaSetupDispComp.createSetupDispConpInst().isAvailableToTime == true)) {
			String moving = (String) ScaSetupDispComp.createSetupDispConpInst().availableColumnsList
					.getSelectedValue();
			int selectedindex = ScaSetupDispComp.createSetupDispConpInst().availableColumnsList
					.getSelectedIndex();

			// remove the time column from the available columns vector in
			// appInfo
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getMappingDataObj()
					.getAvailableColumnsVector().remove(selectedindex);

			// remove the column from the available columns Jlist
			ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
					.remove(selectedindex);

			// set the time column to the time variable text field
			ScaSetupDispComp.createSetupDispConpInst().timeVariableTextField
					.setText(moving);

			// set the time column name to appInfo
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getMappingDataObj().setxColumnName(
							moving);

			// set the time units to appinfo from the column properties
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getScaInfo()
					.getProcessingInputs()
					.getParameterUnitsDataObj()
					.setTimeUnit(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getScaInfo()
									.getColumnPropertiesArrayList().get(
											selectedindex)
									.getUnitBuilderDataObj().getTimeUnits());

			// determine the original column index of time column and set it to
			// appInfo
			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(i).getColumnName())) {
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getScaInfo().getProcessingInputs()
							.getMappingDataObj()
							.setxColumnCorrespondinOriginalIndex(i);
				}
			}

		}

		if (ScaSetupDispComp.createSetupDispConpInst().isTimeToAvailable) {
			String moving = ScaSetupDispComp.createSetupDispConpInst().timeVariableTextField
					.getText();
			ScaSetupDispComp.createSetupDispConpInst().timeVariableTextField
					.setText(null);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getScaInfo()
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
									.getScaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getAvailableColumnsVector().size(), moving);
			ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
					.add(
							ScaSetupDispComp.createSetupDispConpInst().availableVariablesListmodel
									.getSize(), moving);

			// clear the time units from appInfo
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getScaInfo()
					.getProcessingInputs().getParameterUnitsDataObj()
					.setTimeUnit(new String(""));
		}

	}

	void availableOutputsTreeHandler() {

		if (ScaResultsAvailComp.createSetResAvailInst().availableOutputsTree
				.getSelectionPath() != null) {
			String plotName = ScaResultsAvailComp.createSetResAvailInst().availableOutputsTree
					.getSelectionPath().getLastPathComponent().toString();
			String[] pathSplits = ScaResultsAvailComp.createSetResAvailInst().availableOutputsTree
					.getSelectionPath().toString().split(",");
			System.out.println("The last split in the path is "
					+ pathSplits[pathSplits.length - 1]);
			String[] plotNameSplits = plotName.split(" ");
			if (pathSplits[pathSplits.length - 2]
					.equalsIgnoreCase(" Time vs Conc")) {

				DDViewLayer.createmenuBarInstance().exportImage.setEnabled(true);
				DDViewLayer.createmenuBarInstance().exportTextoutput
						.setEnabled(false);
				DDViewLayer.createmenuBarInstance().exportTable.setEnabled(false);
				int plotNumber = Integer
						.parseInt(plotNameSplits[plotNameSplits.length - 1]);
				ScaResultsAvailComp.createSetResAvailInst().selectedImage = plotNumber - 1;
				plotNumber = 2 * (plotNumber - 1);

				if (ScaOptions.createScaOptInst().viewsCombo.getSelectedIndex() == 0) {
					showPlot("linear", plotNumber);
				} else if (ScaOptions.createScaOptInst().viewsCombo
						.getSelectedIndex() == 1) {
					showPlot("log", plotNumber);
				}
			}

			if (pathSplits[pathSplits.length - 2]
					.equalsIgnoreCase(" Time vs Effect Site Conc")) {

				System.out.println(" inside Time VS Effect site  Conc ");
				DDViewLayer.createmenuBarInstance().exportImage.setEnabled(true);
				DDViewLayer.createmenuBarInstance().exportTextoutput
						.setEnabled(false);
				DDViewLayer.createmenuBarInstance().exportTable.setEnabled(false);

				int plotNumber = Integer
						.parseInt(plotNameSplits[plotNameSplits.length - 1]);
				ScaResultsAvailComp.createSetResAvailInst().selectedImage = plotNumber - 1;
				plotNumber = 2 * (plotNumber - 1) + 1;
				System.out.println("The plot number selected is " + plotNumber);
				if (ScaOptions.createScaOptInst().viewsCombo.getSelectedIndex() == 0)
					showPlot("linear", plotNumber);
				else if (ScaOptions.createScaOptInst().viewsCombo
						.getSelectedIndex() == 1) {
					showPlot("log", plotNumber);
				}
			}

			if (pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Final Output]")) {
				DDViewLayer.createmenuBarInstance().exportTextoutput
						.setEnabled(false);
				DDViewLayer.createmenuBarInstance().exportImage.setEnabled(false);
				DDViewLayer.createmenuBarInstance().exportTable.setEnabled(true);
				System.out.println("Viewing final parameters");
				ScaResultsDispComp.createScaResDispCompInst().finalParametersinternalFrame
						.moveToFront();
			}

			if (pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Complete output]")) {
				DDViewLayer.createmenuBarInstance().exportTextoutput
						.setEnabled(true);
				DDViewLayer.createmenuBarInstance().exportImage.setEnabled(false);
				DDViewLayer.createmenuBarInstance().exportTable.setEnabled(false);
				System.out.println("Viewing the text output");
				ScaResultsDispComp.createScaResDispCompInst().textCompleteOutputInternalFrame
						.moveToFront();
			}

		}

	}

	private void showPlot(String axis, int plotNumber) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		JFreeChart chart = null;
		if (axis.equals("linear")) {
			chart = appInfo
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
					.getWorkSheetOutputs().getPlotOutputs().getLinearPlots()
					.get(plotNumber);
		} else if (axis.equals("log")) {
			chart = appInfo
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
					.getWorkSheetOutputs().getPlotOutputs().getLogplots().get(
							plotNumber);
		}

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setSize(
				ScaResultsDispComp.createScaResDispCompInst().plotsFrame
						.getWidth(), ScaResultsDispComp
						.createScaResDispCompInst().plotsFrame.getHeight());
		ScaResultsDispComp.createScaResDispCompInst().plotsFrame
				.setContentPane(chartPanel);
		ScaResultsDispComp.createScaResDispCompInst().plotsFrame.moveToFront();

	}

	boolean checkValidInput() {
		boolean checkTimeConcValid = checkTimeConcValid();
		boolean effectValid = checkEffectValid();
		boolean isKe0Valid = checkKe0Valid();

		if ((checkTimeConcValid == true) && (effectValid == true)&&(isKe0Valid == true)) {
			return true;
		} else {
			return false;
		}

	}

	private boolean checkKe0Valid() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		try {
			double ke0 = Double
					.parseDouble(appInfo
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
							.getScaInfo().getProcessingInputs()
							.getModelInputsObj().getKe0Value());
			if (ke0 <= 0) {

				JOptionPane.showMessageDialog(NPSMainPage
						.createNPSMainPageInst().ncaMainScreen,
						"Please enter a ke0 value greater than zero",
						"Conform", JOptionPane.YES_OPTION);
				return false;

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					NPSMainPage.createNPSMainPageInst().ncaMainScreen,
					"Please enter valid ke0 value", "Conform",
					JOptionPane.YES_OPTION);
			return false;
		}

		return true;
	}

	private boolean checkEffectValid() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
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
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getMappingDataObj()
				.getEffectColumnName().equals("")) {
			return true;
		} else {
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
									.getSelectedSheetIndex())
					.getScaInfo()
					.getColumnPropertiesArrayList()
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
									.getScaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getEffectCorrespondingOriginalIndex())
					.getDataType().equals("Numeric")) {
				return true;
			} else {
				JOptionPane.showMessageDialog(NPSMainPage
						.createNPSMainPageInst().ncaMainScreen,
						"Please ensure Effect data is numeric", "Conform",
						JOptionPane.YES_OPTION);
				return false;
			}
		}
	}

	private boolean checkTimeConcValid() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		boolean isTimeColumnEmpty = false;
		boolean isConcColumnEmpty = false;
		boolean isTimeNumeric = false;
		boolean isConcNumeric = false;

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
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getMappingDataObj().getxColumnName()
				.equals("")) {
			isTimeColumnEmpty = true;

		} else {
			// determine data type of time column
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
									.getSelectedSheetIndex())
					.getScaInfo()
					.getColumnPropertiesArrayList()
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
									.getScaInfo().getProcessingInputs()
									.getMappingDataObj()
									.getxColumnCorrespondinOriginalIndex())
					.getDataType().equals("Numeric")) {
				isTimeNumeric = true;
			} else {

			}
		}

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
								.getSelectedSheetIndex()).getScaInfo()
				.getProcessingInputs().getMappingDataObj().getYcolumnName()
				.equals("")) {
			isConcColumnEmpty = true;
		} else {
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
									.getSelectedSheetIndex())
					.getScaInfo()
					.getColumnPropertiesArrayList()
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
									.getScaInfo().getProcessingInputs()
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
			JOptionPane.showMessageDialog(
					NPSMainPage.createNPSMainPageInst().ncaMainScreen,
					"Please ensure Time and Concentrations are valid",
					"Conform", JOptionPane.YES_OPTION);
			return false;
		}

	}

	void includeAsInput() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String plotName = ScaResultsAvailComp.createSetResAvailInst().availableOutputsTree
				.getSelectionPath().getLastPathComponent().toString();
		String[] pathSplits = ScaResultsAvailComp.createSetResAvailInst().availableOutputsTree
				.getSelectionPath().toString().split(",");

		String[] plotNameSplits = plotName.split(" ");
		String[][] sheeetData = null;

		if (ScaResultsAvailComp.createSetResAvailInst().availableOutputsTree
				.getSelectionPath().getParentPath().toString().contains(
						"Sheets")) {
			int chosenOption = JOptionPane.showConfirmDialog(ScaResultsDispComp
					.createScaResDispCompInst().displayResultsInternalFrame,
					"Use as a input for a different analysis", "Conform",
					JOptionPane.YES_OPTION, 0);

			if (chosenOption == 0) {

				// get the selected node's corresponding table data
				sheeetData = getCorespondingSheetData(sheeetData, pathSplits);

				// add the data structure to array list of data structures in
				// ApplicationInfo
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().add(new WorkSheetsInfo());
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getDataStructuresArrayList().add(sheeetData);

				String sheetName = appInfo
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
										.getSelectedSheetIndex())
						.getSheetName();

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
										.getSelectedSheetIndex())
						.getAnalysisType();
				// set sheetName
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
										.getWorkSheetObjectsAL().size() - 1)
						.setSheetName(
								sheetName + "_" + analysisType + "_"
										+ tableName);

				// set selected row as 1
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

			String workBookSelected = appInfo.getProjectInfoAL().get(
					appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getWorkBookName();

			Enumeration en = DDViewLayer.createPEInstance().workSpace
					.breadthFirstEnumeration();

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
		String sheetName = appInfo
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
				.equalsIgnoreCase(" Final Output]")) {
			sheeetData = copySelectedSheetData(ScaResultsDispComp
					.createScaResDispCompInst().finalparametersVerticalDisplayTable);
			tableName = "Final Output";
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
