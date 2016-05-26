package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NcaLambdaZHandlers {

	public static NcaLambdaZHandlers NCA_LAMB_HAND = null;

	public static NcaLambdaZHandlers createNcaLamdHandlersInst() {
		if (NCA_LAMB_HAND == null) {
			NCA_LAMB_HAND = new NcaLambdaZHandlers();
		}
		return NCA_LAMB_HAND;
	}
	
	ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
	
	int selectedProfileNumber = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
	.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
	.getWorkSheetObjectsAL()
	.get(
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getSelectedSheetIndex())
	.getNcaInfo()
	.getProcessingInputs()
	.getLambdazDataobj().getProfileSelected();

	void linearLogViewHandler() throws RowsExceededException, WriteException,
			BiffException, IOException {

		if ((NcaLambdaZDispGui.createLambDispGuiInstance().linearViewRadioButton
				.isSelected() == true)
				&& (NcaLambdaZDispGui.createLambDispGuiInstance().profileGraphGenerated == true)) {

			NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZNormalGraphsInternalFramesArrayList
					.get(0).moveToFront();

		} else if ((NcaLambdaZDispGui.createLambDispGuiInstance().logViewRadioButton
				.isSelected() == true)
				&& (NcaLambdaZDispGui.createLambDispGuiInstance().profileGraphGenerated == true)) {

			NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZLogarithmicGraphsInternalFramesArrayList
					.get(0).moveToFront();
		}
	}

	NcaLambdaZDispGui ncaLambDispGui;
	void lambdaZMethodComboHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {
		
		ncaLambDispGui = NcaLambdaZDispGui.createLambDispGuiInstance();
		//set the method selected
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		selectedProfileNumber = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
		.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL()
		.get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex())
		.getNcaInfo()
		.getProcessingInputs()
		.getLambdazDataobj().getProfileSelected();
		
		if(NcaSetupAvailCompTreeValueChangeHandler.createNcaavailableCompHandlerInst().lambdaZMethodSelectionProgramatically == true){
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
			.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL()
			.get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex())
			.getNcaInfo()
			.getProcessingInputs()
			.getLambdazDataobj()
			.getLambdaZDetailsOf(
					selectedProfileNumber)
			.setMethodSelected(NcaSetupAvailCompTreeValueChangeHandler.createNcaavailableCompHandlerInst().methodSelectedProgrameatically);
		} else{
		
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
			.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL()
			.get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex())
			.getNcaInfo()
			.getProcessingInputs()
			.getLambdazDataobj()
			.getLambdaZDetailsOf(
					selectedProfileNumber)
			.setMethodSelected(
					NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZCalculationCombo
							.getSelectedIndex());
		}
		
		
		int methodSelected = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getNcaInfo()
				.getProcessingInputs()
				.getLambdazDataobj()
				.getLambdaZDetailsOf(
						selectedProfileNumber)
				.getMethodSelected();
		
		
		if(NcaSetupAvailCompTreeValueChangeHandler.createNcaavailableCompHandlerInst().lambdaZMethodSelectionProgramatically == true){
			if(methodSelected == 3){
				NcaLambdaZDispGui.createLambDispGuiInstance().profileIF.setVisible(true);
			} else{
				NcaLambdaZDispGui.createLambDispGuiInstance().profileIF.setVisible(false);
			}
			
		} else{
			if (methodSelected == 3) { //time range selected
				timeRangeLambdaOption();
			} else if ((methodSelected == 1) ||(methodSelected == 2)) { 
				bestFitTTTOrBestFitTTTARS();
			}else if (methodSelected == 0) {
				selectedBestFitWhileGuiCreation();
			}
		}

	}

	private void selectedBestFitWhileGuiCreation()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		LambdaZCalculationBeforeNCA lmbdaZCalcBefNca = DisplayContents
		.createInstanceOflzBeforeNCA();
		NcaCoreLambdaZCalc lmbdaZCalcMethod = NcaCoreLambdaZCalc.createCoreLamInst();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZNormalGraphsInternalFramesArrayList = new ArrayList<JInternalFrame>();
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZLogarithmicGraphsInternalFramesArrayList = new ArrayList<JInternalFrame>();
		
		clearTheHashTableForTimeRange();
		
		
		//setting the selected method in appInfo
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getNcaInfo()
				.getProcessingInputs()
				.getLambdazDataobj()
				.getLambdaZDetailsOf(
						selectedProfileNumber)
				.setMethodSelected(
						NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZCalculationCombo
								.getSelectedIndex());
		
		
		
		int methodSelected = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getNcaInfo()
				.getProcessingInputs()
				.getLambdazDataobj()
				.getLambdaZDetailsOf(
						selectedProfileNumber)
				.getMethodSelected();
		
		
		
		lmbdaZCalcBefNca.methodNumberForAllProfile[selectedProfileNumber] = methodSelected;
		
		NcaLambdaZDispGui.createLambDispGuiInstance().profileIF.setVisible(false);
		
		try {
			LambdaZCalculationBeforeNCA lzbNCA = DisplayContents
					.createInstanceOflzBeforeNCA();
			
			//call to the lambdaZcalculation
			lzbNCA
					.calculateLambdaZBeforeNCAUsingBestFit(
							//profile number
							selectedProfileNumber,
							
							//method number
							lmbdaZCalcBefNca.methodNumberForAllProfile[selectedProfileNumber],
							
									//start time
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
									.getStartTime(),
									
									//end time
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
									.getEndTime(),
									
									//infusion time
							lmbdaZCalcBefNca.infusionTimeForAllProfile[selectedProfileNumber]);
		} catch (Exception e) {

			e.printStackTrace();
		}

		double[] predictedY = DisplayContents.createInstanceOflzBeforeNCA().predictedConc;
		NcaLambdaZDispGui.createLambDispGuiInstance().observedX.clear();
		NcaLambdaZDispGui.createLambDispGuiInstance().observedY.clear();
		for (int i = 0; i < DisplayContents.createInstanceOflzBeforeNCA().TimeForLambdaZ.length; i++) {
			NcaLambdaZDispGui.createLambDispGuiInstance().observedX
					.add(DisplayContents.createInstanceOflzBeforeNCA().TimeForLambdaZ[i]);
			NcaLambdaZDispGui.createLambDispGuiInstance().observedY
					.add(DisplayContents.createInstanceOflzBeforeNCA().ConcForLambdaZ[i]);
		}

		NCAoutputPlots
				.createNcaoutputPlotsInst()
				.generateLambdaZplotForTheProfileClicked(
						NcaLambdaZDispGui.createLambDispGuiInstance().observedX,
						NcaLambdaZDispGui.createLambDispGuiInstance().observedY,
						predictedY,
						NcaLambdaZDispGui.createLambDispGuiInstance().profileNamesUsedForProfileNumberDetermination
								.get(selectedProfileNumber), selectedProfileNumber);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileGraphGenerated = true;
		if (NcaLambdaZDispGui.createLambDispGuiInstance().linearViewRadioButton
				.isSelected() == true) {
			NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZNormalGraphsInternalFramesArrayList
					.get(0).moveToFront();

		} else if (NcaLambdaZDispGui.createLambDispGuiInstance().logViewRadioButton
				.isSelected() == true) {
			NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZLogarithmicGraphsInternalFramesArrayList
					.get(0).moveToFront();
		}

	}

	private void selectedBestFitAndPreviousSelectionTimeRange()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		LambdaZCalculationBeforeNCA lmbdaZCalcBefNca = DisplayContents
				.createInstanceOflzBeforeNCA();
		NcaCoreLambdaZCalc lmbdaZCalcMethod = NcaCoreLambdaZCalc.createCoreLamInst();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.getTableHeader().setVisible(false);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.setEnabled(false);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.setVisible(false);
		//NcaLambdaZDispGui.createLambDispGuiInstance().isTimeRangeSelected = false;
		NcaLambdaZDispGui.createLambDispGuiInstance().endTimelable
				.setVisible(false);
		NcaLambdaZDispGui.createLambDispGuiInstance().startTimeLable
				.setVisible(false);
		NcaLambdaZDispGui.createLambDispGuiInstance().startTimeForLambdaZTextField
				.setVisible(false);
		NcaLambdaZDispGui.createLambDispGuiInstance().endTimeForLambdaZTextField
				.setVisible(false);
		NcaLambdaZDispGui.createLambDispGuiInstance().endTimeForLambdaZTextField
				.setText("");
		NcaLambdaZDispGui.createLambDispGuiInstance().startTimeForLambdaZTextField
				.setText("");
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZNormalGraphsInternalFramesArrayList = new ArrayList<JInternalFrame>();
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZLogarithmicGraphsInternalFramesArrayList = new ArrayList<JInternalFrame>();
		/*NcaLambdaZDispGui.createLambDispGuiInstance().ifLambdaZScreenIsCalledFirstTime = NcaLambdaZDispGui
				.createLambDispGuiInstance().ifLambdaZScreenIsCalledFirstTime + 1;*/
		lmbdaZCalcBefNca.methodNumberForAllProfile[selectedProfileNumber] = NcaLambdaZDispGui
				.createLambDispGuiInstance().lambdaZCalculationCombo
				.getSelectedIndex();
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
				.setMethodSelected(NcaLambdaZDispGui
						.createLambDispGuiInstance().lambdaZCalculationCombo
						.getSelectedIndex());
		
		try {
			LambdaZCalculationBeforeNCA lzbNCA = DisplayContents
					.createInstanceOflzBeforeNCA();
			lzbNCA
					.calculateLambdaZBeforeNCAUsingBestFit(
							selectedProfileNumber,
							lmbdaZCalcBefNca.methodNumberForAllProfile[selectedProfileNumber],
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
									.getStartTime(),
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
									.getEndTime(),
							lmbdaZCalcBefNca.infusionTimeForAllProfile[selectedProfileNumber]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double[] predictedY = lmbdaZCalcMethod.predictedValue;
		NCAoutputPlots
				.createNcaoutputPlotsInst()
				.generateLambdaZplotForTheProfileClicked(
						NcaLambdaZDispGui.createLambDispGuiInstance().observedX,
						NcaLambdaZDispGui.createLambDispGuiInstance().observedY,
						predictedY,
						NcaLambdaZDispGui.createLambDispGuiInstance().profileNamesUsedForProfileNumberDetermination
								.get(selectedProfileNumber), selectedProfileNumber);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileGraphGenerated = true;
		if (NcaLambdaZDispGui.createLambDispGuiInstance().linearViewRadioButton
				.isSelected() == true) {
			NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZNormalGraphsInternalFramesArrayList
					.get(0).moveToFront();

		} else if (NcaLambdaZDispGui.createLambDispGuiInstance().logViewRadioButton
				.isSelected() == true) {
			NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZLogarithmicGraphsInternalFramesArrayList
					.get(0).moveToFront();
		}
		NcaLambdaZDispGui.createLambDispGuiInstance().entryIsWhileCreatingTheGUI = false;

	}

	private void bestFitTTTOrBestFitTTTARS() throws RowsExceededException,
			WriteException, BiffException, IOException {
		LambdaZCalculationBeforeNCA lmbdaZCalcBefNca = DisplayContents
				.createInstanceOflzBeforeNCA();
		NcaCoreLambdaZCalc lmbdaZCalcMethod = NcaCoreLambdaZCalc.createCoreLamInst();

		clearTheHashTableForTimeRange();
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.setEnabled(false);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.setVisible(false);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.getTableHeader().setVisible(false);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileIF.setVisible(false);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileIF.moveToBack();
		
		
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZNormalGraphsInternalFramesArrayList = new ArrayList<JInternalFrame>();
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZLogarithmicGraphsInternalFramesArrayList = new ArrayList<JInternalFrame>();

		
		lmbdaZCalcBefNca.methodNumberForAllProfile[selectedProfileNumber] = NcaLambdaZDispGui
				.createLambDispGuiInstance().lambdaZCalculationCombo
				.getSelectedIndex();
		
		
		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getNcaInfo()
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
				.setMethodSelected(NcaLambdaZDispGui
						.createLambDispGuiInstance().lambdaZCalculationCombo
						.getSelectedIndex());
		
		
		
		
		
		
		try {
			LambdaZCalculationBeforeNCA lzbNCA = DisplayContents
					.createInstanceOflzBeforeNCA();
			lzbNCA
					.calculateLambdaZBeforeNCAUsingBestFit(
							selectedProfileNumber,
							lmbdaZCalcBefNca.methodNumberForAllProfile[selectedProfileNumber],
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
									.getStartTime(),
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
													.get(
															appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo().getProcessingInputs()
									.getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
									.getEndTime(),
							lmbdaZCalcBefNca.infusionTimeForAllProfile[selectedProfileNumber]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double[] predictedY = lmbdaZCalcMethod.predictedValue;
		NCAoutputPlots
				.createNcaoutputPlotsInst()
				.generateLambdaZplotForTheProfileClicked(
						NcaLambdaZDispGui.createLambDispGuiInstance().observedX,
						NcaLambdaZDispGui.createLambDispGuiInstance().observedY,
						predictedY,
						NcaLambdaZDispGui.createLambDispGuiInstance().profileNamesUsedForProfileNumberDetermination
								.get(selectedProfileNumber), selectedProfileNumber);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileGraphGenerated = true;
		if (NcaLambdaZDispGui.createLambDispGuiInstance().linearViewRadioButton
				.isSelected() == true) {
			NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZNormalGraphsInternalFramesArrayList
					.get(0).moveToFront();

		} else if (NcaLambdaZDispGui.createLambDispGuiInstance().logViewRadioButton
				.isSelected() == true) {
			NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZLogarithmicGraphsInternalFramesArrayList
					.get(0).moveToFront();
		}
		NcaLambdaZDispGui.createLambDispGuiInstance().entryIsWhileCreatingTheGUI = false;
	}

	private void clearTheHashTableForTimeRange() {
		
		int size = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
		.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL()
		.get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex())
		.getNcaInfo().getProcessingInputs()
		.getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber].getInclusionExcusionPoints().size();
		
		for(int i=0;i<size;i++){
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
			.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
			.getWorkSheetObjectsAL()
			.get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
							.get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getSelectedSheetIndex())
			.getNcaInfo().getProcessingInputs()
			.getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber].getInclusionExcusionPoints().put(i, false);
		}
		
	}

	private void timeRangeLambdaOption() throws RowsExceededException,
			WriteException, BiffException, IOException {

		NcaCoreLambdaZCalc lmbdaZCalcMethod = NcaCoreLambdaZCalc
				.createCoreLamInst();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.setEnabled(true);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.setVisible(true);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileTable
				.getTableHeader().setVisible(true);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileIF.setVisible(true);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileIF.moveToFront();
		// set the selected method
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
				.getProcessingInputs().getLambdazDataobj().getLambdaZDetails()[selectedProfileNumber]
				.setMethodSelected(NcaLambdaZDispGui
						.createLambDispGuiInstance().lambdaZCalculationCombo
						.getSelectedIndex());


		double[] predictedY = lmbdaZCalcMethod.predictedValue;

		// setting the start time into the text field
		for (int i = 0; i < predictedY.length; i++) {
			if (predictedY[i] != 0) {
				
				break;
			}
		}

		// setting the end time into the text field
		for (int i = predictedY.length - 1; i >= 0; i--) {
			if (predictedY[i] != 0) {
				
				break;
			}
		}

		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZNormalGraphsInternalFramesArrayList = new ArrayList<JInternalFrame>();
		NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZLogarithmicGraphsInternalFramesArrayList = new ArrayList<JInternalFrame>();

		NCAoutputPlots
				.createNcaoutputPlotsInst()
				.generateLambdaZplotForTheProfileClicked(
						NcaLambdaZDispGui.createLambDispGuiInstance().observedX,
						NcaLambdaZDispGui.createLambDispGuiInstance().observedY,
						predictedY,
						NcaLambdaZDispGui.createLambDispGuiInstance().profileNamesUsedForProfileNumberDetermination
								.get(selectedProfileNumber), selectedProfileNumber);
		NcaLambdaZDispGui.createLambDispGuiInstance().profileGraphGenerated = true;
		if (NcaLambdaZDispGui.createLambDispGuiInstance().linearViewRadioButton
				.isSelected() == true) {
			NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZNormalGraphsInternalFramesArrayList
					.get(0).moveToFront();

		} else if (NcaLambdaZDispGui.createLambDispGuiInstance().logViewRadioButton
				.isSelected() == true) {
			NcaLambdaZDispGui.createLambDispGuiInstance().lambdaZLogarithmicGraphsInternalFramesArrayList
					.get(0).moveToFront();
		}

	}
}
