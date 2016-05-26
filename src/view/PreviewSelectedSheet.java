package view;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;

import org.jzy3d.plot3d.rendering.view.View;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class PreviewSelectedSheet {

	public static PreviewSelectedSheet PREV_SHEET = null;

	public static PreviewSelectedSheet createpreviewShtInstance() {
		if (PREV_SHEET == null) {
			PREV_SHEET = new PreviewSelectedSheet();
		}
		return PREV_SHEET;
	}

	public PreviewSelectedSheet() {

	}

	public void showPreview() throws RowsExceededException, WriteException,
			BiffException, IOException {

		if (DDViewLayer.createFeaturesPanelInstance().PreviewLable.isVisible() == true) {

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
					&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {

				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToBack();
				DesStatSetupAvailComp.createDesStatAvailComp().availableComponentsTree
						.setSelectionRow(1);

			} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == true)
					&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
					&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)) {

				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToBack();
				ScaSetupAvailComp.createSetupAvailCompInst().availableComponentsTree
						.setSelectionRow(1);

			} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == true)
					&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToBack();
				NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree
						.setSelectionRow(1);

			}

			if ((DDViewLayer.createViewLayerInstance().isNCAExecution == true)
					&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)) {

				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToBack();
				NcaSetupAvailableComp.createNcaSetupAvailCompInst().availableComponentsTree
						.setSelectionRow(1);

			} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == true)) {
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToBack();
				PMSetupAvailComp.createPMAvailCompInst().plotMavenAvailableComponentsTree
						.setSelectionRow(1);

			} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isCAExecution == true)) {

				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToBack();
				// CAMainScreen.createCAMainScreen()..availableComponentsTree.setSelectionRow(1);

			} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isFromBQL == true)) {

				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToBack();
				BQLSetupAvailComp.createBqlSetAvailInst().availableComponentsTree
						.setSelectionRow(1);

			} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isFromBQL == false)
					&& (DDViewLayer.createViewLayerInstance().isFromTableMaven == true)) {
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.moveToBack();
				TMSetupAvailComp.createTmSetupAvailCompInst().tree.setSelectionRow(1);
				
			} else if((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == false)
				&& (DDViewLayer.createViewLayerInstance().isFromTableMaven == false)
				&& (DDViewLayer.createViewLayerInstance().isTableTrans = true)){
				
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.moveToBack();
				TableTransformations.createTableTrasGUIInst().availCompTree.setSelectionRow(1);
			}

			DDViewLayer.createFeaturesPanelInstance().PreviewLable
					.setVisible(false);

		} else {

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
					&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {

				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToFront();

			} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == true)
					&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
					&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)) {

				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToFront();

			} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == true)
					&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)) {

			}
			ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
					.moveToFront();

			if ((DDViewLayer.createViewLayerInstance().isNCAExecution == true)
					&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)) {

				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToFront();

			} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == true)) {
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToFront();
			} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isCAExecution == true)) {
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToFront();
			} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isFromBQL == true)) {
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToFront();
			} else if ((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
					&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
					&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
					&& (DDViewLayer.createViewLayerInstance().isFromBQL == false)
					&& (DDViewLayer.createViewLayerInstance().isFromTableMaven == true)) {
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToFront();
			} else if((DDViewLayer.createViewLayerInstance().isNCAExecution == false)
				&& (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isPlotMavenExecution == false)
				&& (DDViewLayer.createViewLayerInstance().isFromNonParametricSuperPositionMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromSemiCompartmentalModellingMainPage == false)
				&& (DDViewLayer.createViewLayerInstance().isFromBQL == false)
				&& (DDViewLayer.createViewLayerInstance().isFromTableMaven == false)
				&& (DDViewLayer.createViewLayerInstance().isTableTrans = true)){
				
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
				.moveToFront();
			}

			DDViewLayer.createFeaturesPanelInstance().PreviewLable
					.setVisible(true);

		}
	}
}
