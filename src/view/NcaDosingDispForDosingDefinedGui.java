package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaDosingDispForDosingDefinedGui {

	int routeOfAdminForDD;

	NcaDosingDispGui ncaDosingDispInst = null;
	
	void dosingTableCreationForDosingDefined() throws RowsExceededException,
			WriteException, BiffException, IOException {
		
		
		
		ncaDosingDispInst = NcaDosingDispGui.createNcaDosingGuiInst();

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.getSelectedIndex() == 1) {
			NcaDosingDispGui.createNcaDosingGuiInst().numberOfColumnForDosing = 2 + appInfo
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
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();

		} else {
			NcaDosingDispGui.createNcaDosingGuiInst().numberOfColumnForDosing = 7 + appInfo
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
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().size();

		}

		int numberOfSortVar = appInfo
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
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();

		String[][] sortVarData = appInfo
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
				.getProcessingInputs().getProfileInfoObj()
				.getDataSortVariables();

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
				.getProcessingInputs().getDosingDataObj().setDosingDSForNCA(
						new String[DetermineNumberOfSubject.createDetNoSubInst().no_subject][numberOfSortVar + 4]);

		for (int i = 0; i < DetermineNumberOfSubject.createDetNoSubInst().no_subject; i++) {
			for (int j = 0; j < numberOfSortVar; j++) {
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
						.getProcessingInputs().getDosingDataObj()
						.setDosingValueAt(i, j, sortVarData[i][j]);

			}

		}

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
				.getProcessingInputs().getProfileInfoObj()
				.setRouteOfAdminForDosingDefined(new int[DetermineNumberOfSubject.createDetNoSubInst().no_subject]);
		NcaDosingDispGui.createNcaDosingGuiInst().routeOfAdminFOrDosingD = new int[DetermineNumberOfSubject.createDetNoSubInst().no_subject];

		String[] header = new String[numberOfSortVar + 7];

		for (int i = 0; i < numberOfSortVar; i++) {
			header[i] = appInfo
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
					.getProcessingInputs().getMappingDataObj()
					.getSortVariablesListVector().get(i);

		}

		header[numberOfSortVar] = "Dose";
		header[numberOfSortVar + 1] = "Time of Dose";
		header[numberOfSortVar + 2] = "Tau";
		header[numberOfSortVar + 3] = "Length of Infusion";
		header[numberOfSortVar + 4] = "Extravascular";
		header[numberOfSortVar + 5] = "IV bolus";
		header[numberOfSortVar + 6] = "Infusion";

		UIDefaults ui = UIManager.getLookAndFeel().getDefaults();
		UIManager.put("RadioButton.focus", ui.getColor("control"));

		DefaultTableModel dm = new DefaultTableModel();

		Object[][] tableObj = new Object[DetermineNumberOfSubject.createDetNoSubInst().no_subject][header.length];

		Object[] item = { "Extravascular", "IV bolus", "Infusion" };

		for (int i = 0; i < tableObj.length; i++) {
			for (int j = 0; j < numberOfSortVar; j++) {
				tableObj[i][j] = sortVarData[i][j];

			}

			tableObj[i][numberOfSortVar + 4] = new JRadioButton();
			tableObj[i][numberOfSortVar + 5] = new JRadioButton();
			tableObj[i][numberOfSortVar + 6] = new JRadioButton();

			ButtonGroup group1 = new ButtonGroup();
			group1.add((JRadioButton) tableObj[i][numberOfSortVar + 4]);
			group1.add((JRadioButton) tableObj[i][numberOfSortVar + 5]);
			group1.add((JRadioButton) tableObj[i][numberOfSortVar + 6]);

		}

		dm.setDataVector(tableObj, header);

		NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing = new JTable(
				dm);

		/*
		 * for (int i = 0; i < tableObj.length; i++) {
		 */
		/*
		 * ButtonGroup group1 = new ButtonGroup(); group1.add((JRadioButton)
		 * dm.getValueAt(0, 4)); group1.add((JRadioButton) dm.getValueAt(0, 5));
		 * group1.add((JRadioButton) dm.getValueAt(0, 6));
		 */

		// }

		NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing.getColumn(
				"Extravascular").setCellRenderer(new RadioButtonRenderer());

		NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing.getColumn(
				"IV bolus").setCellRenderer(new RadioButtonRenderer());

		NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing.getColumn(
				"Infusion").setCellRenderer(new RadioButtonRenderer());

		// this is how the selection of combo box is possible
		NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing.getColumn(
				"Extravascular").setCellEditor(
				new RadioButtonEditor(new JCheckBox()));
		NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing.getColumn(
				"IV bolus").setCellEditor(
				new RadioButtonEditor(new JCheckBox()));
		NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing.getColumn(
				"Infusion").setCellEditor(
				new RadioButtonEditor(new JCheckBox()));

	}

	public void actionEventForDosingDefinedRouteAdmin(int rowChanged,
			int columnChanged) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		int noOfSortVar = appInfo
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
				.getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size();

		if (columnChanged == noOfSortVar + 4)
			routeOfAdminForDD = 0;

		if (columnChanged == noOfSortVar + 5)
			routeOfAdminForDD = 1;
		if (columnChanged == noOfSortVar + 6)
			routeOfAdminForDD = 2;

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
				.getProcessingInputs().getProfileInfoObj().setRouteOfAdminAt(
						rowChanged, routeOfAdminForDD);
	}

}

class RadioButtonRenderer1 implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value == null)
			return null;
		return (Component) value;
	}
}

class RadioButtonEditor1 extends DefaultCellEditor implements ItemListener {
	private JRadioButton button;

	public RadioButtonEditor1(JCheckBox checkBox) {
		super(checkBox);
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (value == null)
			return null;
		button = (JRadioButton) value;
		button.addItemListener(this);
		return (Component) value;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub

	}
}
