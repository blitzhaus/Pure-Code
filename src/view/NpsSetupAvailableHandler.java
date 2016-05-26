package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NpsSetupAvailableHandler {
	
	
	public static NpsSetupAvailableHandler NPS_SETUP_HANDLERS = null;
	public static NpsSetupAvailableHandler createNPSSetupHandlersInst() {
		if(NPS_SETUP_HANDLERS == null){
			NPS_SETUP_HANDLERS = new NpsSetupAvailableHandler();
		}
		
		return NPS_SETUP_HANDLERS;
	}

	
	void availaSetupCompTreeHandler() throws RowsExceededException, WriteException, BiffException, IOException {

		if (NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree
				.getSelectionPath() != null) {
			String plotName = NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree
					.getSelectionPath().getLastPathComponent().toString();
			String[] pathSplits = NPSSetupAvailComp
					.createNPSSetupAvailCompInst().availableComponentsTree
					.getSelectionPath().toString().split(",");
			String[] plotNameSplits = plotName.split(" ");

			if (NPSSetupAvailComp.createNPSSetupAvailCompInst().availableComponentsTree
					.getMinSelectionRow() == 0) {
				ImportedDataSheet.createImportedDataSheetInstance().importedDataSheetFrame
						.moveToFront();
				DDViewLayer.createFeaturesPanelInstance().PreviewLable
						.setVisible(true);
			}

			if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Mapping]")) {
				NPSSetupDispComp.createNpsSetDispInst().MappingInternalFrame
						.moveToFront();
				NPSOpt.createNPSOptInst().optionsInternalFrame.moveToFront();

			}
			
			if (pathSplits[pathSplits.length - 1].equalsIgnoreCase(" Dosing]")) {
				
				ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
				multipleLevelSorting m = multipleLevelSorting.createMultipleSortInst();// new
																						// multipleLevelSorting(appInfo);
				
					m.main(null);
				
				DataPreparationForNPS dataPrepNps = new DataPreparationForNPS();
				dataPrepNps.prepareData();
				DetermineNumberOfSubject.createDetNoSubInst()
						.determineNumberOfSubject(dataPrepNps.Data);

				// till the end of catch block is pertaining to lambdaZ
				DataPreparationForAllComponents.createDataPrepAllCompInst()
						.dataPreparationForAll(dataPrepNps.Data);
				
				
				NPSDosingTableCreation.createNpsDosingGuiInst().createDosingInternalFrame();
				
				try {
					NPSDosingTableCreation.createNpsDosingGuiInst().dosingInternalFrameForNPS
							.moveToFront();
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
				NPSOpt.createNPSOptInst().optionsInternalFrame.moveToFront();

			}

		}

	}

}
