package view;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class InVitroEngineSetActionHandler {
	


	public void odeSolverMethodCbActionHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {
		InVitroEngSettingDispGuiCreator engSetInst = InVitroEngSettingDispGuiCreator
				.createEngineSettingInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		
			procInputInst.getModelInputTab2Obj().setOdeSolverMethod(
					engSetInst.odeSolverMethodComboBox.getSelectedIndex());

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	public void minMethodCbActionhandler() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		InVitroEngSettingDispGuiCreator engSetInst = InVitroEngSettingDispGuiCreator
				.createEngineSettingInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		procInputInst.getModelInputTab2Obj().setMinimizationMethod(
					engSetInst.minimizationMthodComboBox.getSelectedIndex());
		
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		if (engSetInst.minimizationMthodComboBox.getSelectedIndex() == 2) {

			engSetInst.muTextField.setEnabled(true);
			engSetInst.muLable.setEnabled(true);
			engSetInst.lambdaValueLabel.setEnabled(true);
			engSetInst.lambdaValueTextField.setEnabled(true);
			engSetInst.incrementsForpartialDerivativesTextField
					.setEnabled(true);
			engSetInst.incrementsForpartialDerivativesLabel.setEnabled(true);

		} else if (engSetInst.minimizationMthodComboBox.getSelectedIndex() == 1) {

			engSetInst.muTextField.setEnabled(false);
			engSetInst.muLable.setEnabled(false);
			engSetInst.lambdaValueLabel.setEnabled(false);
			engSetInst.lambdaValueTextField.setEnabled(false);
			engSetInst.incrementsForpartialDerivativesTextField
					.setEnabled(false);
			engSetInst.incrementsForpartialDerivativesLabel.setEnabled(false);

		}

		else if (engSetInst.minimizationMthodComboBox.getSelectedIndex() == 2) {

			engSetInst.muTextField.setEnabled(true);
			engSetInst.muLable.setEnabled(true);
			engSetInst.lambdaValueLabel.setEnabled(true);
			engSetInst.lambdaValueTextField.setEnabled(true);
			engSetInst.incrementsForpartialDerivativesTextField
					.setEnabled(true);
			engSetInst.incrementsForpartialDerivativesLabel.setEnabled(true);

		}

	}

	public void actionListenerEventForInitParamCalByCS()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		InVitroEngSettingDispGuiCreator engSetInst = InVitroEngSettingDispGuiCreator
				.createEngineSettingInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		InVitroInitEstimateDispGuiCreator initParamInst = InVitroInitEstimateDispGuiCreator
				.createInVitroInitEstGui();

		if (engSetInst.initialParameterValueByCS.isSelected() == true) {
			if (procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 0)
				initParamInst.calculateInitialvalue = true;

			procInputInst.getModelInputTab2Obj().setInitialParamByCS(1);
			procInputInst.getModelInputTab2Obj().setInitialParamBYGA(0);
			procInputInst.getModelInputTab2Obj().setInitialParamBYGS(0);
			procInputInst.getModelInputTab2Obj().setPreviouslyGenInitVal(0);

			
			engSetInst.initialParameterValueByGA.setSelected(false);
			engSetInst.previouslyGenInitilParam.setSelected(false);

			engSetInst.initialParameterValueByGA.setEnabled(false);
			engSetInst.previouslyGenInitilParam.setEnabled(false);
			engSetInst.initialParameterValueByGS.setSelected(false);
			engSetInst.initialParameterValueByGS.setEnabled(false);
			
			CaMapingHandler.createCaMapHandInst().setProcessingInput(
					procInputInst);

		} else {

			procInputInst.getModelInputTab2Obj().setInitialParamByCS(0);

			engSetInst.initialParameterValueByGA.setEnabled(true);
			engSetInst.initialParameterValueByGS.setEnabled(true);
			for (int i = 0; i < procInputInst.getInitialParameterValueObj()
					.getCodeGenetratedInitialValue().length; i++)
				if (procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(i) != 0) {
					engSetInst.previouslyGenInitilParam.setEnabled(true);
				}

			CaMapingHandler.createCaMapHandInst().setProcessingInput(
					procInputInst);
		}

	}

	public void actionListenerEventForInitParamCalueByGA()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		CaEngineSettingDispGuiCreator engSetInst = CaEngineSettingDispGuiCreator
				.createEngineSettingInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		InVitroInitEstimateDispGuiCreator initParamInst = InVitroInitEstimateDispGuiCreator
				.createInVitroInitEstGui();

		if (engSetInst.initialParameterValueByGA.isSelected() == true) {
			if (procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 0)
				initParamInst.calculateInitialvalue = true;

			procInputInst.getModelInputTab2Obj().setInitialParamBYGA(1);
			procInputInst.getModelInputTab2Obj().setInitialParamBYGS(0);
			procInputInst.getModelInputTab2Obj().setInitialParamByCS(0);
			procInputInst.getModelInputTab2Obj().setPreviouslyGenInitVal(0);

			engSetInst.initialParameterValueByCS.setSelected(false);
			engSetInst.previouslyGenInitilParam.setSelected(false);
			engSetInst.initialParameterValueByCS.setEnabled(false);
			engSetInst.previouslyGenInitilParam.setEnabled(false);
			engSetInst.initialParameterValueByGS.setSelected(false);
			engSetInst.initialParameterValueByGS.setEnabled(false);
			CaMapingHandler.createCaMapHandInst().setProcessingInput(
					procInputInst);

		} else {

			procInputInst.getModelInputTab2Obj().setInitialParamBYGA(0);

			engSetInst.initialParameterValueByCS.setEnabled(true);
			engSetInst.initialParameterValueByGS.setEnabled(true);
			for (int i = 0; i < procInputInst.getInitialParameterValueObj()
					.getCodeGenetratedInitialValue().length; i++)
				if (procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(i) != 0) {
					engSetInst.previouslyGenInitilParam.setEnabled(true);

				}
			CaMapingHandler.createCaMapHandInst().setProcessingInput(
					procInputInst);
		}

	}

	public void actionListenerEventForInitParamCalueByGS()
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		CaEngineSettingDispGuiCreator engSetInst = CaEngineSettingDispGuiCreator
				.createEngineSettingInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();
		InVitroInitEstimateDispGuiCreator initParamInst = InVitroInitEstimateDispGuiCreator
				.createInVitroInitEstGui();

		if (engSetInst.initialParameterValueByGS.isSelected() == true) {
			if (procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 0)
				initParamInst.calculateInitialvalue = true;

			procInputInst.getModelInputTab2Obj().setInitialParamBYGS(1);

			procInputInst.getModelInputTab2Obj().setInitialParamByCS(0);
			procInputInst.getModelInputTab2Obj().setInitialParamBYGA(0);

			procInputInst.getModelInputTab2Obj().setPreviouslyGenInitVal(0);

			engSetInst.initialParameterValueByCS.setSelected(false);
			engSetInst.previouslyGenInitilParam.setSelected(false);
			engSetInst.initialParameterValueByCS.setEnabled(false);
			engSetInst.previouslyGenInitilParam.setEnabled(false);
			engSetInst.initialParameterValueByGA.setSelected(false);
			engSetInst.initialParameterValueByGA.setEnabled(false);
			
			CaMapingHandler.createCaMapHandInst().setProcessingInput(
					procInputInst);

		} else {

			procInputInst.getModelInputTab2Obj().setInitialParamBYGS(0);

			engSetInst.initialParameterValueByCS.setEnabled(true);
			engSetInst.initialParameterValueByGA.setEnabled(true);
			for (int i = 0; i < procInputInst.getInitialParameterValueObj()
					.getCodeGenetratedInitialValue().length; i++)
				if (procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(i) != 0) {
					engSetInst.previouslyGenInitilParam.setEnabled(true);

				}
			CaMapingHandler.createCaMapHandInst().setProcessingInput(
					procInputInst);
		}

	}

	public void actionListEventForInitParamValCB()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		
		if (InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueComboBox
				.getSelectedIndex() == 0) {
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setEnabled(true);
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setEnabled(true);
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
			.setEnabled(true);

			for (int i = 0; i < procInputInst.getInitialParameterValueObj()
					.getCodeGenetratedInitialValue().length; i++)
				if (procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(i) != 0) {
					InVitroEngSettingDispGuiCreator.createEngineSettingInstance().previouslyGenInitilParam
							.setEnabled(true);
				}

		} else {

			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setSelected(false);
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.revalidate();
			procInputInst.getModelInputTab2Obj().setInitialParamByCS(0);

			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setSelected(false);


			procInputInst.getModelInputTab2Obj().setInitialParamBYGA(0);
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setEnabled(false);
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().previouslyGenInitilParam
					.setEnabled(false);
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setEnabled(false);
			
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
					.setSelected(false);
			InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
					.setEnabled(false);
			procInputInst.getModelInputTab2Obj().setPreviouslyGenInitVal(0);

			procInputInst.getModelInputTab2Obj().setInitialParamByCS(0);

			procInputInst.getModelInputTab2Obj().setInitialParamBYGA(0);
			procInputInst.getModelInputTab2Obj().setInitialParamBYGS(0);

		}


		procInputInst
				.getModelInputTab2Obj()
				.setSourceOfIntParamValue(
						InVitroEngSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueComboBox
								.getSelectedIndex());

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		
		
		
		
		InVitroInitEstimateDispGuiCreator.createInVitroInitEstGui().initialValueSlection = InVitroEngSettingDispGuiCreator
				.createEngineSettingInstance().initialParameterValueComboBox
				.getSelectedIndex();

		InVitroInitEstimateDispGuiCreator.createInVitroInitEstGui().paramInitValIFForInVitro
				.repaint();
		
		InVitroInitEstimateDispGuiCreator.createInVitroInitEstGui()
				.createInitialParameterValueTable();
	

	}

	public void actionListEventForParameBoundCB() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		procInputInst
				.getModelInputTab2Obj()
				.setParamBoundarySelection(
						InVitroEngSettingDispGuiCreator.createEngineSettingInstance().parameterBoundariesComboBox
								.getSelectedIndex());

		if (InVitroEngSettingDispGuiCreator.createEngineSettingInstance().parameterBoundariesComboBox
				.getSelectedIndex() == 0
				|| InVitroEngSettingDispGuiCreator.createEngineSettingInstance().parameterBoundariesComboBox
						.getSelectedIndex() == 2) {
			InVitroInitEstimateDispGuiCreator.createInVitroInitEstGui().boundaryValueSelection = 0;
		} else {
			InVitroInitEstimateDispGuiCreator.createInVitroInitEstGui().boundaryValueSelection = 1;
		}

		

		
	
		InVitroInitEstimateDispGuiCreator.createInVitroInitEstGui().paramInitValIFForInVitro
				.repaint();
		InVitroInitEstimateDispGuiCreator.createInVitroInitEstGui().paramInitValIFForInVitro
				.remove(CaInitEstimateDispGuiCreator.createCaInitEstGui().initialValueScrollPane);

		InVitroInitEstimateDispGuiCreator.createInVitroInitEstGui()
				.createInitialParameterValueTable();

	

	}



}
