package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import CaIpCalculations.ComputationsDispatcher;
import Common.CAChoosenLibraryCombination;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CaEngineSetActionHandler {

	public void odeSolverMethodCbActionHandler() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		CaEngineSettingDispGuiCreator engSetInst = CaEngineSettingDispGuiCreator
				.createEngineSettingInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();
		if (analysisType.equals("pk")) {

			procInputInst.getModelInputTab2Obj().setOdeSolverMethod(
					engSetInst.odeSolverMethodComboBox.getSelectedIndex());

		} else if (analysisType.equals("pd")) {
			procInputInst.getModelInputTab2Obj().setOdeSolverMethod(
					engSetInst.odeSolverMethodComboBox.getSelectedIndex());
		} else if (analysisType.equals("mm")) {
			procInputInst.getModelInputTab2Obj().setOdeSolverMethod(
					engSetInst.odeSolverMethodComboBox.getSelectedIndex());
		} else if (analysisType.equals("pkpdlink")) {
			procInputInst.getModelInputTab2Obj().setOdeSolverMethod(
					engSetInst.odeSolverMethodComboBox.getSelectedIndex());
		} else if (analysisType.equals("irm")) {
			procInputInst.getModelInputTab2Obj().setOdeSolverMethod(
					engSetInst.odeSolverMethodComboBox.getSelectedIndex());
		}
		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

	}

	public void minMethodCbActionhandler() throws Exception {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		CaEngineSettingDispGuiCreator engSetInst = CaEngineSettingDispGuiCreator
				.createEngineSettingInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.equals("pk")) {
			procInputInst.getModelInputTab2Obj().setMinimizationMethod(
					engSetInst.minimizationMthodComboBox.getSelectedIndex());
		} else if (analysisType.equals("pd")) {

			procInputInst.getModelInputTab2Obj().setMinimizationMethod(
					engSetInst.minimizationMthodComboBox.getSelectedIndex());
		} else if (analysisType.equals("mm")) {

			procInputInst.getModelInputTab2Obj().setMinimizationMethod(
					engSetInst.minimizationMthodComboBox.getSelectedIndex());
		} else if (analysisType.equals("pkpdlink")) {
			procInputInst.getModelInputTab2Obj().setMinimizationMethod(
					engSetInst.minimizationMthodComboBox.getSelectedIndex());
		} else if (analysisType.equals("irm")) {
			procInputInst.getModelInputTab2Obj().setMinimizationMethod(
					engSetInst.minimizationMthodComboBox.getSelectedIndex());
		} else if (analysisType.equals("ascii")) {
			procInputInst.getModelInputTab2Obj().setMinimizationMethod(
					engSetInst.minimizationMthodComboBox.getSelectedIndex());
		}
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
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		CaEngineSettingDispGuiCreator engSetInst = CaEngineSettingDispGuiCreator
				.createEngineSettingInstance();

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		CaInitEstimateDispGuiCreator initParamInst = CaInitEstimateDispGuiCreator
				.createCaInitEstGui();

		if (engSetInst.initialParameterValueByCS.isSelected() == true) {
			if (procInputInst.getModelInputTab2Obj().getInitialParamByCS() == 0)
				initParamInst.calculateInitialvalue = true;

			procInputInst.getModelInputTab2Obj().setInitialParamByCS(1);
			procInputInst.getModelInputTab2Obj().setInitialParamBYGA(0);
			procInputInst.getModelInputTab2Obj().setInitialParamBYGS(0);
			procInputInst.getModelInputTab2Obj().setPreviouslyGenInitVal(0);
			procInputInst.getModelInputTab2Obj().setInitialParamBYNCA(0);

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

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		CaInitEstimateDispGuiCreator initParamInst = CaInitEstimateDispGuiCreator
				.createCaInitEstGui();

		if (engSetInst.initialParameterValueByGA.isSelected() == true) {
			if (procInputInst.getModelInputTab2Obj().getInitialParamBYGA() == 0)
				initParamInst.calculateInitialvalue = true;

			procInputInst.getModelInputTab2Obj().setInitialParamBYGA(1);
			procInputInst.getModelInputTab2Obj().setInitialParamBYGS(0);
			procInputInst.getModelInputTab2Obj().setInitialParamByCS(0);
			procInputInst.getModelInputTab2Obj().setPreviouslyGenInitVal(0);
			procInputInst.getModelInputTab2Obj().setInitialParamBYNCA(0);

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

		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();
		CaInitEstimateDispGuiCreator initParamInst = CaInitEstimateDispGuiCreator
				.createCaInitEstGui();

		if (engSetInst.initialParameterValueByGS.isSelected() == true) {
			if (procInputInst.getModelInputTab2Obj().getInitialParamBYGS() == 0)
				initParamInst.calculateInitialvalue = true;

			procInputInst.getModelInputTab2Obj().setInitialParamBYGS(1);

			procInputInst.getModelInputTab2Obj().setInitialParamByCS(0);
			procInputInst.getModelInputTab2Obj().setInitialParamBYGA(0);

			procInputInst.getModelInputTab2Obj().setPreviouslyGenInitVal(0);
			procInputInst.getModelInputTab2Obj().setInitialParamBYNCA(0);

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
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		if (CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueComboBox
				.getSelectedIndex() == 0) {
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setEnabled(true);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setEnabled(true);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
					.setEnabled(true);

			for (int i = 0; i < procInputInst.getInitialParameterValueObj()
					.getCodeGenetratedInitialValue().length; i++)
				if (procInputInst.getInitialParameterValueObj()
						.getInitialParameterValueAt(i) != 0) {
					CaEngineSettingDispGuiCreator.createEngineSettingInstance().previouslyGenInitilParam
							.setEnabled(true);
				}

		} else {

			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setSelected(false);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.revalidate();
			procInputInst.getModelInputTab2Obj().setInitialParamByCS(0);

			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setSelected(false);

			// bgForGA.setSelected(bgForGA.getSelection(), false);

			procInputInst.getModelInputTab2Obj().setInitialParamBYGA(0);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByCS
					.setEnabled(false);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().previouslyGenInitilParam
					.setEnabled(false);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGA
					.setEnabled(false);

			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
					.setSelected(false);
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().initialParameterValueByGS
					.setEnabled(false);
			procInputInst.getModelInputTab2Obj().setPreviouslyGenInitVal(0);

			procInputInst.getModelInputTab2Obj().setInitialParamByCS(0);

			procInputInst.getModelInputTab2Obj().setInitialParamBYGA(0);
			procInputInst.getModelInputTab2Obj().setInitialParamBYGS(0);

		}

		// }

		procInputInst
				.getModelInputTab2Obj()
				.setSourceOfIntParamValue(
						CaEngineSettingDispGuiCreator
								.createEngineSettingInstance().initialParameterValueComboBox
								.getSelectedIndex());

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.equals("pk") || analysisType.equals("pd")
				|| analysisType.equals("mm")) {

			CaInitEstimateDispGuiCreator.createCaInitEstGui().initialValueSlection = CaEngineSettingDispGuiCreator
					.createEngineSettingInstance().initialParameterValueComboBox
					.getSelectedIndex();

			CaInitEstimateDispGuiCreator.createCaInitEstGui().parameterInitialValueInternalFrameForCA
					.repaint();

			CaInitEstimateDispGuiCreator.createCaInitEstGui()
					.createInitialParameterValueTable();
		}

		if (analysisType.equals("pkpdlink") || analysisType.equals("irm")) {

			CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().linkInitParamValueForCAIF
					.repaint();
			CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().linkInitParamValueForCAIF
					.remove(CaInitEstimateDispGuiCreator.createCaInitEstGui().initialValueScrollPane);
			CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().initialValueSlection = CaEngineSettingDispGuiCreator
					.createEngineSettingInstance().initialParameterValueComboBox
					.getSelectedIndex();
			CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui()
					.createInitialParameterValueTable();
		}

	}

	public void actionListEventForParameBoundCB() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler
				.createCaMapHandInst().copyProcessingInput();

		procInputInst
				.getModelInputTab2Obj()
				.setParamBoundarySelection(
						CaEngineSettingDispGuiCreator
								.createEngineSettingInstance().parameterBoundariesComboBox
								.getSelectedIndex());

		if (CaEngineSettingDispGuiCreator.createEngineSettingInstance().parameterBoundariesComboBox
				.getSelectedIndex() == 0
				|| CaEngineSettingDispGuiCreator.createEngineSettingInstance().parameterBoundariesComboBox
						.getSelectedIndex() == 2) {
			CaInitEstimateDispGuiCreator.createCaInitEstGui().boundaryValueSelection = 0;
		} else {
			CaInitEstimateDispGuiCreator.createCaInitEstGui().boundaryValueSelection = 1;
		}

		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();

		CaMapingHandler.createCaMapHandInst().setProcessingInput(procInputInst);

		if (analysisType.equals("pk") || analysisType.equals("pd")
				|| analysisType.equals("mm")) {
			CaInitEstimateDispGuiCreator.createCaInitEstGui().parameterInitialValueInternalFrameForCA
					.repaint();
			CaInitEstimateDispGuiCreator.createCaInitEstGui().parameterInitialValueInternalFrameForCA
					.remove(CaInitEstimateDispGuiCreator.createCaInitEstGui().initialValueScrollPane);

			CaInitEstimateDispGuiCreator.createCaInitEstGui()
					.createInitialParameterValueTable();

		}

		if (analysisType.equals("pkpdlink") || analysisType.equals("irm")) {

			CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().linkInitParamValueForCAIF
					.repaint();
			CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().linkInitParamValueForCAIF
					.remove(CaInitEstimateDispGuiCreator.createCaInitEstGui().initialValueScrollPane);
			CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui().initialValueSlection = CaEngineSettingDispGuiCreator
					.createEngineSettingInstance().initialParameterValueComboBox
					.getSelectedIndex();
			CaLinkParamInitEstimateGuiCreator.createLinkInitEstGui()
					.createInitialParameterValueTable();
		}

	}

	public void actionListenerEventForInitParamCalByEqns() throws Exception {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo
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
								.getSelectedSheetIndex()).getAnalysisType();
		CAChoosenLibraryCombination obj = new CAChoosenLibraryCombination();

		ProcessingInputsInfo processInfo = obj
				.getprocessingInputs(analysisType);
		processInfo.getModelInputTab2Obj().setInitialParamBYNCA(1);
		
		processInfo.getModelInputTab2Obj().setInitialParamBYGS(0);

		processInfo.getModelInputTab2Obj().setInitialParamByCS(0);
		processInfo.getModelInputTab2Obj().setInitialParamBYGA(0);

		
		processInfo.getModelInputTab2Obj().setPreviouslyGenInitVal(0);

		obj.setProcessingInputs(processInfo, analysisType);

		ComputationsDispatcher dispatcher = new ComputationsDispatcher();
		dispatcher.dispacher(analysisType);

	}

}
