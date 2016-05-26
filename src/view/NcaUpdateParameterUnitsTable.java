package view;

import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaUpdateParameterUnitsTable {

	public NcaUpdateParameterUnitsTable() throws RowsExceededException,
			WriteException, BiffException, IOException {
		updatingParameterUnitsTable();
	}

	DefaultAndPrefferedUnit defAndPrefUnitInst;
	NcaParameterUnitsDispGui ncaParamUnitDispInst;
	ParameterUnitsData paramUnitsDataInst;
	
	
	private void updatingParameterUnitsTable() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ncaParamUnitDispInst = NcaParameterUnitsDispGui
		.createNcaParUnitsDisInst();
		paramUnitsDataInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getParameterUnitsDataObj();
		
	

		defAndPrefUnitInst = new DefaultAndPrefferedUnit();
		defAndPrefUnitInst.preparationOfparameterByGroup();
		NcaMappingDispGui.createMappingInstance().endTimeVariableTextField
				.setVisible(false);
/*		NcaMappingDispGui.createMappingInstance().endTimeVariableLable
				.setVisible(false);*/
		NcaMappingDispGui.createMappingInstance().moveToEndTimeButton
				.setVisible(false);
		NcaMappingDispGui.createMappingInstance().moveToVolumeButton
				.setVisible(false);
		NcaMappingDispGui.createMappingInstance().volumeVariableTextField
				.setVisible(false);
		/*NcaMappingDispGui.createMappingInstance().volumeLable.setVisible(false);*/

		NcaParameterUnitsDispGui.createNcaParUnitsDisInst().parameterUnitsInternalFrame
				.revalidate();
		NcaParameterUnitsDispGui.createNcaParUnitsDisInst().parameterUnitsInternalFrame
				.repaint();
		NcaMappingDispGui.createMappingInstance().MappingInternalFrame
				.moveToFront();

		// remove all the rows of parameter units
		for (int i = NcaParameterUnitsDispGui.createNcaParUnitsDisInst().parameterUnitsTable
				.getRowCount() - 1; i >= 0; i--) {
			((DefaultTableModel) NcaParameterUnitsDispGui
					.createNcaParUnitsDisInst().parameterUnitsTable.getModel())
					.removeRow(i);
		}

		/*
		 * ((DefaultTreeModel) availableComponentsTree.getModel()) .reload();
		 */
		defAndPrefUnitInst.preparationOfparameterByGroup();
		if (NcaOptionsGui.createNcaOptionsInstance().modelOptions
				.getSelectedIndex() == 1) {

			for (int i = 0; i < 9; i++) {
				String[] s = new String[3];
				String c = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getParameterbyGroupValueAt(i);
				s[0] = c;
				String u = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getDefaultUnitValueAt(i);
				s[1] = u;
				s[2] = u;
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.setPreferredUnit(i, u);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.setDefaultUnitValueAt(i, u);
				((DefaultTableModel) NcaParameterUnitsDispGui
						.createNcaParUnitsDisInst().parameterUnitsTable
						.getModel()).insertRow(NcaParameterUnitsDispGui
						.createNcaParUnitsDisInst().parameterUnitsTable
						.getRowCount(), s);
			}

		} else {

			for (int i = 0; i < 16; i++) {

				String[] s = new String[3];

				String c = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getParameterbyGroupValueAt(i);
				s[0] = c;

				String u = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.getDefaultUnitValueAt(i);
				s[1] = u;
				s[2] = u;
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.setPreferredUnit(i, u);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterUnitsDataObj()
						.setDefaultUnitValueAt(i, u);
				((DefaultTableModel) NcaParameterUnitsDispGui
						.createNcaParUnitsDisInst().parameterUnitsTable
						.getModel()).insertRow(NcaParameterUnitsDispGui
						.createNcaParUnitsDisInst().parameterUnitsTable
						.getRowCount(), s);

			}

		}
	}
}
