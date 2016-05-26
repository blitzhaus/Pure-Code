package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.tree.TreePath;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaOptSparseSelectionHandler {

	public static NcaOptSparseSelectionHandler NCA_SPARSE_ACTION = null;

	public static NcaOptSparseSelectionHandler createNcaSparseSelectionhandInst() {
		if (NCA_SPARSE_ACTION == null) {
			NCA_SPARSE_ACTION = new NcaOptSparseSelectionHandler();
		}

		return NCA_SPARSE_ACTION;
	}

	public void sparseAction() throws RowsExceededException, WriteException,
			BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		TreePath path;
		try {
			path = NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
					.getPathForRow(1);
			NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
					.setSelectionPath(path);
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (NcaOptionsGui.createNcaOptionsInstance().sparseCheckBox
				.isSelected() == true) {

			try {
/*				NcaMappingDispGui.createMappingInstance().subjectLable
						.setVisible(true);*/

				NcaMappingDispGui.createMappingInstance().subjectTextField
						.setVisible(true);
				NcaMappingDispGui.createMappingInstance().setAsSubjectButton
						.setVisible(true);
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
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.setisSparseSelected(true);

		} else {
			try {
/*				NcaMappingDispGui.createMappingInstance().subjectLable
						.setVisible(false);*/
				NcaMappingDispGui.createMappingInstance().subjectTextField
						.setVisible(false);
				NcaMappingDispGui.createMappingInstance().setAsSubjectButton
						.setVisible(false);

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
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).getNcaInfo()
					.getProcessingInputs().getModelInputsObj()
					.setisSparseSelected(false);
		}
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getMappingDataObj()
				.setIfMappingScreenIsChanged(true);
		
	}
}
