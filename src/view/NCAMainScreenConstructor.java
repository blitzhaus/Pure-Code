package view;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JTable;

import Common.WorkBookManipulation;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NCAMainScreenConstructor {

	public static NCAMainScreenConstructor NCA_MAIN_SCREENCONSTR = null;

	public static NCAMainScreenConstructor createNcaMainScrConstr() {
		if (NCA_MAIN_SCREENCONSTR == null) {
			NCA_MAIN_SCREENCONSTR = new NCAMainScreenConstructor();
		}
		return NCA_MAIN_SCREENCONSTR;
	}

	public NCAMainScreenConstructor() {

	}

	/*boolean checkIsToNcaFromDescStats() throws RowsExceededException,
			WriteException, BiffException, IOException {

		boolean entered = false;
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if ((appInfo
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
								.getSelectedSheetIndex()).isFromDescriptiveStatMainPage == true)
				&& (DesStatResDispComp.createDesStatResDispInst().carryThisToOtherComponents == true)) {

			entered = true;

			// if the descriptive statistics results are to be brought to NCA

			File f = new File("ajith.xls");
			if (f.exists() == false) {
				WorkBookManipulation w = new WorkBookManipulation();
				w.createWorkBook();
			} else {

			}

			Workbook w = Workbook.getWorkbook(new File("ajith.xls"));
			String[] sheetNames = w.getSheetNames();

			// new pre computations instance
			PrecomputaionsFromDSToNCA preComp = new PrecomputaionsFromDSToNCA();
			if (sheetNames[sheetNames.length - 1].equals("Final Parameters")) {
				// implies that the final parameters sheet is already present in
				// the work book
				// and also added to the list of data structures, so we have to
				// replace it rather than adding a new one

				// setting the selected shhet index to the final parameters DS
				// index
				// it is at the last index
				appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.setSelectedSheetIndex(
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
										.getDataStructuresArrayList().size() - 1);

				// replace the existing data structure
				preComp.replaceFinalparametersInDataStructuresAL();

				// replace the work shhet info object in the data structures AL
				appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.set(
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
										.getWorkSheetObjectsAL().size() - 1,
								new WorkSheetsInfo());

				// replace the column properties array list
				preComp.replaceColumnPropertiesAL();

				// set the sheet name
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
										.getSelectedSheetIndex()).setSheetName(
								"Final Parameters");

				// replace final parameters sheet in the workbook
				preComp.replaceFinalparametersSheet();

				// set column names of the columns from the data structure
				preComp.setColumnNameToAppInfo();

			} else {
				// implies that there is no final parameters sheet in the work
				// book
				// and we have to create a new one and add it, similarly we have
				// to
				// create the corresponding ds in the data structures array list

				preComp.addFinalParametersToDataStructuresAL();
				appInfo.getProjectInfoAL().get(
						appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().add(new WorkSheetsInfo());
				appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.setSelectedSheetIndex(
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
										.getDataStructuresArrayList().size() - 1);

				preComp.setColumnPropertiesAL();

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
										.getSelectedSheetIndex()).setSheetName(
								"Final Parameters");
				preComp.addFinalParametersSheetToFile1Workook();

				// set column names of the columns from the data structure
				preComp.setColumnNameToAppInfo();
			}

			NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
					.setSize(ViewLayer.createMTInstance().mainTabbedFrame
							.getWidth(),
							ViewLayer.createMTInstance().mainTabbedFrame
									.getHeight() / 2);

		}
		return entered;

	}*/

/*	boolean checkIsToNcaFromPlotMaven() throws RowsExceededException,
			WriteException, BiffException, IOException {
		boolean entered = false;
		if (ViewLayer.createViewLayerInstance().isToNCAFromPlotMaven == true) {
			NCAMainScreen.createNcaMainScreenInstance().ncaMainInternalFrame
					.setSize(
							ViewLayer.createMTInstance().mainTabbedFrame
									.getWidth(),
							(ViewLayer.createMTInstance().mainTabbedFrameoriginalHeight + ViewLayer
									.createLogFrameInstance().logFrameOriginalHeight) / 2);
			entered = true;

		}
		return entered;
	}*/

	void initiallizeVariables() throws RowsExceededException, WriteException,
			BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		// Initialize the sort variables inside the nca info of application info

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
				.getProcessingInputs().getMappingDataObj()
				.setSortVariablesListVector(new Vector<String>());
		NcaParameterNamesDispGui.createParameterNamesInstance().isRestoringParameterNames = false;
		NcaParameterUnitsDispGui.createNcaParUnitsDisInst().isRestoringParameterUnits = false;

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
				.getProcessingInputs().getParameterUnitsDataObj()
				.setPreferredUnitsArray(new String[16]);
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
				.getProcessingInputs().getParameterUnitsDataObj()
				.setDefaultUnitArray(new String[16]);

		NcaMappingDispGui.createMappingInstance().previousXVariable = new String(
				"");
		NcaMappingDispGui.createMappingInstance().previousYColumnName = new String(
				"");
		NcaMappingDispGui.createMappingInstance().previousEndTime = new String(
				"");
		NcaMappingDispGui.createMappingInstance().previousVolumeColumnName = new String(
				"");
		NcaMappingDispGui.createMappingInstance().previousSubjectColumnName = new String(
				"");
		NcaOptionsGui.createNcaOptionsInstance().previousDataType = 0;
		NCAMainScreen.createNcaMainScreenInstance().columncount = 0;
		NCAMainScreen.createNcaMainScreenInstance().numberOfTimeInsideTheLoop = 0;
		NCAMainScreen.createNcaMainScreenInstance().previousRootOfAdministration = 0;
		NcaOptionsGui.createNcaOptionsInstance().isFromDoseUnitBuilding = false;

		NcaLambdaZDispGui.createLambDispGuiInstance().entryIsWhileCreatingTheGUI = true;
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().ifMappingIsChangedInPartialArea = false;
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().partialAreaData = new String[127][2];
		NCAMainScreen.createNcaMainScreenInstance().numberOfTimeDisableCurveStrippingCalled = 0;
		NcaLambdaZDispGui.createLambDispGuiInstance().isFromSlopeScreen = false;
		NCAMainScreen.createNcaMainScreenInstance().ifMappingIsChangedInLambdaZ = false;
		/*
		 * NcaLambdaZDispGui.createLambDispGuiInstance().ifLambdaZScreenIsCalledFirstTime
		 * = 0;
		 */
		NcaOptionsGui.createNcaOptionsInstance().ifModelOptionsIsSelected = false;
		NcaDosingDispGui.createNcaDosingGuiInst().fromPositiveNumberOfProfile = false;
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().fromPositiveNumberOfProfileInPartialArea = false;
		NcaMappingDispGui.createMappingInstance().previousSortVariables = new String[20];
		NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing = new JTable(
				1, 1);
		NCAMainScreen.createNcaMainScreenInstance().previousSortVariablesForPA = new String[20];
		NCAMainScreen.createNcaMainScreenInstance().previousSortVariablesForLZ = new String[20];
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
				.getProcessingInputs().getMappingDataObj()
				.setIfMappingScreenIsChanged(false);
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().ifPartialAreaScreenIsCalledFirstTime = true;
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().ifFromNumberOfPartialAreasCombo = false;
		/*
		 * NcaLambdaZDispGui.createLambDispGuiInstance().isTimeRangeSelected =
		 * false;
		 */
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
				.getProcessingInputs().getParameterUnitsDataObj()
				.setFromPreferredUnitScreen(false);
		NcaDosingDispGui.createNcaDosingGuiInst().numberOfTimeDosingScreenCalled = 0;
		NCAMainScreen.createNcaMainScreenInstance().numberOfTimeUnitScreenCalled = 0;
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().numberOfTimePartialAreaScreenCalled = 0;
		NCAMainScreen.createNcaMainScreenInstance().isFromPartialAreaScreen = false;
		NcaSubAreasDispGui.createNcaSubAreasDispGuiInst().tableForPartialArea = new JTable(
				50, 14);
		NcaParameterUnitsDispGui.createNcaParUnitsDisInst().isFromPreferredUnitInternalFrame = false;
		NcaDosingDispGui.createNcaDosingGuiInst().numberOfTimeDosingScreenCalled = 0;
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
								.getSelectedSheetIndex())
				.setAnalysisType("nca");
		DDViewLayer.createViewLayerInstance().isTableTrans = false;
	}

}
