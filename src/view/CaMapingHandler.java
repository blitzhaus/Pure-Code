package view;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.record.formula.atp.AnalysisToolPak;

import Model.WorkSheetOutputs;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CaMapingHandler {
	
	
	boolean isSortToAvailable;
	boolean isAvailableToSort;
	boolean isAvailableToFunc;
	boolean isXToAvailable;
	boolean isAvailableToX;
	boolean isYToAvailable;
	boolean isAvailableToEndTime;
	boolean isEndTimeToAvailable;
	boolean isAvailableToVolume;
	boolean isVolumeToAvailable;
	boolean isAvailableToCarryAlong;
	boolean isCarryAlongToAvailable;
	boolean isAvailableToSubject;
	boolean isSubjectToAvailable;
	boolean isAvailableToY;
	

	public static CaMapingHandler CA_MAP_HAND = null;

	public static CaMapingHandler createCaMapHandInst() {
		if (CA_MAP_HAND == null) {
			CA_MAP_HAND = new CaMapingHandler();
		}
		return CA_MAP_HAND;
	}

	
	
	CaMappingDispGuiCreator mapDispInst;

	

	public void availableVarAndXVarBtnEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		mapDispInst = CaMappingDispGuiCreator.createMappingInstance(); 
		
		if ((isAvailableToX == true)) {
			availableVarToXVarEvent();

		}

		if (isXToAvailable) {
			xVarToAvailableVarEvent();
			removeTimeUnit();
		}
		ProcessingInputsInfo procInputInst = copyProcessingInput();
		
		ParameterAndUnitListLoader paramAndUnitListInst = ParameterAndUnitListLoader
				.createParamAndUnitListInstance();
		paramAndUnitListInst.unitListForParameters();
		CaParamUnitsDispGuiCreator.createCaParUnitsDisInst()
		.paramUnitTableRebuilding(procInputInst);
		
	}

	
	
	public void xVarToAvailableVarEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		mapDispInst = CaMappingDispGuiCreator.createMappingInstance();
		
		String moving = CaMappingDispGuiCreator.createMappingInstance().xVariableTextField
				.getText();
		CaMappingDispGuiCreator.createMappingInstance().xVariableTextField
				.setText(null);

		ProcessingInputsInfo procInputInst = copyProcessingInput();

		procInputInst.getMappingDataObj().getAvailableColumnsVector().add(
				procInputInst.getMappingDataObj().getAvailableColumnsVector()
						.size(), moving);
		procInputInst.getMappingDataObj().setxColumnCorrespondinOriginalIndex(-1);
		procInputInst.getMappingDataObj().setxColumnName("");
		
		
		setProcessingInput(procInputInst);
		CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.add(
						CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
								.getSize(), moving);
	}

	public void availableVarToXVarEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		mapDispInst = CaMappingDispGuiCreator.createMappingInstance();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = (String) CaMappingDispGuiCreator.createMappingInstance().caavailableColumnsList
				.getSelectedValue();

		ProcessingInputsInfo procInputInst;

		procInputInst = copyProcessingInput();

		int index = 0;

		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getColumnPropertiesArrayList()
				.size(); i++) {
			if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName())) {
				index = i;
			}
		}

		ArrayList<ColumnProperties> columnPropAl = copyColumnPropAl();
		
		if (columnPropAl.get(index).getDataType().equals("Numeric")) {

			int selectedindex = CaMappingDispGuiCreator.createMappingInstance().caavailableColumnsList
					.getSelectedIndex();
			procInputInst.getMappingDataObj().getAvailableColumnsVector()
					.remove(selectedindex);

			CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel.remove(selectedindex);
			CaMappingDispGuiCreator.createMappingInstance().xVariableTextField.setText(moving);

			procInputInst.getMappingDataObj().setxColumnName(moving);

			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(i).getColumnName())) {
					procInputInst.getMappingDataObj()
							.setxColumnCorrespondinOriginalIndex(i);
				}
			}

			detectTimeUnit(procInputInst);
		} else {
			String message = "Please ensure that the column contains only numbers";
			showMessage(message);

		}

		setProcessingInput(procInputInst);
	}

	public void setProcessingInput(ProcessingInputsInfo procInputInst) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.equals("pk")) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("pd")) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPdInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("mm")) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getMmInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("pkpdlink")) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkpdLinkInfo()
					.setProcessingInputs(procInputInst);
		} else if (analysisType.equals("irm")) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getIrInfo()
					.setProcessingInputs(procInputInst);
		}else if (analysisType.equals("ascii")) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAsciiInfo()
					.setProcessingInputs(procInputInst);
		}else if (analysisType.equals("invitro")) {
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getInVitroInfo()
					.setProcessingInputs(procInputInst);
		} 

	}

	public void showMessage(String message) throws RowsExceededException,
			HeadlessException, WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		
		if (analysisType.equals("pk")) {
			JOptionPane.showMessageDialog(PkMainScreenCreator
					.createPkMainScreenInstance().pkMainInternalFrame, message,
					"Conform", JOptionPane.YES_OPTION);
		} else if (analysisType.equals("pd")) {
			JOptionPane.showMessageDialog(PdMainScreenCreator
					.createPdMainScreenInstance().pdMainInternalFrame, message,
					"Conform", JOptionPane.YES_OPTION);
		} else if (analysisType.equals("mm")) {
			JOptionPane.showMessageDialog(MmMainScreenCreator
					.createMmMainScreenInstance().mmMainInternalFrame, message,
					"Conform", JOptionPane.YES_OPTION);
		} else if (analysisType.equals("pkpdlink")) {
			JOptionPane.showMessageDialog(PkPdLinkMainScreenCreator
					.createPkPdLinkMainScreenInstance().pkpdLinkMainInternalFrame,
					message, "Conform", JOptionPane.YES_OPTION);
		} else if (analysisType.equals("irm")) {
			JOptionPane.showMessageDialog(IrmMainScreenCreator
					.createIrmMainScreenInstance().irmMainInternalFrame,
					message, "Conform", JOptionPane.YES_OPTION);
		}

	}
	
	

	public ProcessingInputsInfo copyProcessingInput() {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = null;
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.equals(
						"pk")) {
			procInputInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("pd")) {
			procInputInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPdInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("mm")) {
			procInputInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getMmInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("pkpdlink")) {
			procInputInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkpdLinkInfo()
					.getProcessingInputs();
		} else if (analysisType.equals("irm")) {
			procInputInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getIrInfo()
					.getProcessingInputs();
		}else if (analysisType.equals("ascii")) {
			procInputInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAsciiInfo()
					.getProcessingInputs();
		}else if (analysisType.equals("invitro")) {
			procInputInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getInVitroInfo()
					.getProcessingInputs();
		}
		return procInputInst;
	}

	
	private void removeTimeUnit()
	{

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		ProcessingInputsInfo procInputInst = copyProcessingInput();
		
		if(analysisType.equals("pk")
			||	analysisType.equals("mm")
			|| analysisType.equals("pkpdlink")
			|| analysisType.equals("irm"))
		{
			String timeUnit = "";

		procInputInst.getParameterUnitsDataObj().setTimeUnit(timeUnit);
		}
		else
		{
			String volumeUnit = "";

			procInputInst.getParameterUnitsDataObj().setVolumeUnit(volumeUnit);

			String concMassUnit = "";
			procInputInst.getParameterUnitsDataObj().setConcMassUnit(concMassUnit);
		}

		setProcessingInput(procInputInst);

	
	}
	
	
	private void detectTimeUnit(ProcessingInputsInfo procInputInst) {
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();

		if(analysisType.equals("pk")
			||	analysisType.equals("mm")
			|| analysisType.equals("pkpdlink")
			|| analysisType.equals("irm"))
		{
			String timeUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getColumnPropertiesArrayList()
				.get(
						procInputInst.getMappingDataObj()
								.getxColumnCorrespondinOriginalIndex())
				.getUnitBuilderDataObj().getTimeUnits();

		procInputInst.getParameterUnitsDataObj().setTimeUnit(timeUnit);
		}
		else
		{
			String volumeUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getColumnPropertiesArrayList()
					.get(
							procInputInst.getMappingDataObj()
									.getxColumnCorrespondinOriginalIndex())
					.getUnitBuilderDataObj().getVolumePrefixIndex()
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
							.getColumnPropertiesArrayList().get(
									procInputInst.getMappingDataObj()
											.getxColumnCorrespondinOriginalIndex())
							.getUnitBuilderDataObj().getVolumeUnitindex();

			procInputInst.getParameterUnitsDataObj().setVolumeUnit(volumeUnit);

			String concMassUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getColumnPropertiesArrayList()
					.get(
							procInputInst.getMappingDataObj()
									.getxColumnCorrespondinOriginalIndex())
					.getUnitBuilderDataObj().getMasPrefixIndex()
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
							.getColumnPropertiesArrayList().get(
									procInputInst.getMappingDataObj()
											.getxColumnCorrespondinOriginalIndex())
							.getUnitBuilderDataObj().getMassunitIndex();
			procInputInst.getParameterUnitsDataObj().setConcMassUnit(concMassUnit);
		}

		setProcessingInput(procInputInst);

	}
	
	private void removeConcUnit() {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		ProcessingInputsInfo procInputInst = copyProcessingInput();
		
		if(analysisType.equals("pk")
				||	analysisType.equals("mm")
				)
			{
			String volumeUnit = "";
			procInputInst.getParameterUnitsDataObj().setVolumeUnit(volumeUnit);

			String concMassUnit = "";
			procInputInst.getParameterUnitsDataObj().setConcMassUnit(concMassUnit);
			}
		

		setProcessingInput(procInputInst);

	}

	
	
	private void concUnitDetection(ProcessingInputsInfo procInputInst) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();

		
		if(analysisType.equals("pk")
				||	analysisType.equals("mm")
				)
			{
			String volumeUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getColumnPropertiesArrayList()
					.get(
							procInputInst.getMappingDataObj()
									.getyColumnCorrespondinOriginalIndex())
					.getUnitBuilderDataObj().getVolumePrefixIndex()
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
							.getColumnPropertiesArrayList().get(
									procInputInst.getMappingDataObj()
											.getyColumnCorrespondinOriginalIndex())
							.getUnitBuilderDataObj().getVolumeUnitindex();

			procInputInst.getParameterUnitsDataObj().setVolumeUnit(volumeUnit);

			String concMassUnit = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getColumnPropertiesArrayList()
					.get(
							procInputInst.getMappingDataObj()
									.getyColumnCorrespondinOriginalIndex())
					.getUnitBuilderDataObj().getMasPrefixIndex()
					+ appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
							.getColumnPropertiesArrayList().get(
									procInputInst.getMappingDataObj()
											.getyColumnCorrespondinOriginalIndex())
							.getUnitBuilderDataObj().getMassunitIndex();
			procInputInst.getParameterUnitsDataObj().setConcMassUnit(concMassUnit);
			}
		

		setProcessingInput(procInputInst);

	}

	
	

	public void xVarTfEvent() throws RowsExceededException, WriteException,
			BiffException, IOException {

		CaMappingDispGuiCreator.createMappingInstance().xAndAvailableButton
				.setIcon(CaMappingDispGuiCreator.createMappingInstance().toLeft);
		isXToAvailable = true;
		isAvailableToX = false;
	}

	private void availableVarAndSubjectBtnEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		if (isAvailableToSubject == true) {
			availableVarToSubjectEvent();
		}

		if (isSubjectToAvailable == true) {
			subjectToAvailableVarEvent();

		}
	}

	private void subjectToAvailableVarEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {}
	

	private void availableVarToSubjectEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {}


	void yVarAndAvailableVarBtnEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {
		ParameterAndUnitListLoader paramAndUnitListInst = ParameterAndUnitListLoader
				.createParamAndUnitListInstance();

		ProcessingInputsInfo processingInputs;
		if (isAvailableToY == true) {
			processingInputs = copyProcessingInput();
			processingInputs = availableVarToYVarEvent(processingInputs);

			setProcessingInput(processingInputs);

			processingInputs = copyProcessingInput();
			concUnitDetection(processingInputs);

			processingInputs = copyProcessingInput();
			paramAndUnitListInst.unitListForParameters();
			CaParamUnitsDispGuiCreator.createCaParUnitsDisInst()
					.paramUnitTableRebuilding(processingInputs);
		}

		if (isYToAvailable == true) {
			processingInputs = copyProcessingInput();
			yVarToAvailableVarEvent(processingInputs);
			removeConcUnit();
			processingInputs = copyProcessingInput();
			paramAndUnitListInst.unitListForParameters();
			CaParamUnitsDispGuiCreator.createCaParUnitsDisInst()
					.paramUnitTableRebuilding(processingInputs);
		}
		paramAndUnitListInst.unitListForParameters();
	}

	private void yVarToAvailableVarEvent(ProcessingInputsInfo procInputInst)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = CaMappingDispGuiCreator.createMappingInstance().yVariableTextField
				.getText();
		CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.add(
						CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
								.size(), moving);
		procInputInst.getMappingDataObj().getAvailableColumnsVector().add(
				moving);
		procInputInst.getMappingDataObj().setyColumnCorrespondinOriginalIndex(-1);
		procInputInst.getMappingDataObj().setYcolumnName("");
		
		
		setProcessingInput(procInputInst);

		CaMappingDispGuiCreator.createMappingInstance().yVariableTextField
				.setText(null);

	}

	private ProcessingInputsInfo availableVarToYVarEvent(
			ProcessingInputsInfo procInpuInst) throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String moving = (String) CaMappingDispGuiCreator.createMappingInstance().caavailableColumnsList
				.getSelectedValue();

		int index = 0;

		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getColumnPropertiesArrayList()
				.size(); i++) {
			if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName())) {
				index = i;
			}
		}
		
		ArrayList<ColumnProperties> columnPropAl = copyColumnPropAl();

		if (columnPropAl.get(index).getDataType().equals("Numeric")) {

			int selectedIndex = CaMappingDispGuiCreator.createMappingInstance().caavailableColumnsList
					.getSelectedIndex();
			procInpuInst.getMappingDataObj().getAvailableColumnsVector()
					.remove(selectedIndex);
			CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
					.remove(selectedIndex);
			procInpuInst.getMappingDataObj().setYcolumnName(moving);
			CaMappingDispGuiCreator.createMappingInstance().yVariableTextField
					.setText(moving);


			for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving.equals(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
						.getColumnPropertiesArrayList().get(i).getColumnName())) {
					procInpuInst.getMappingDataObj()
							.setyColumnCorrespondinOriginalIndex(i);
				}
			}

		} else {
			String message = "Please ensure that the column contains only numbers";
			showMessage(message);

		}
		return procInpuInst;
	}
	
	
	private  ArrayList<ColumnProperties> copyColumnPropAl()
	{
		ArrayList<ColumnProperties> columnPropAl = new ArrayList<ColumnProperties>();
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String analysisType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAnalysisType();
		
		if (analysisType.equals("pk")) {
			columnPropAl = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkInfo().getColumnPropertiesArrayList();
					
		} else if (analysisType.equals("pd")) {
			columnPropAl = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPdInfo().getColumnPropertiesArrayList();
					
		} else if (analysisType.equals("mm")) {
			columnPropAl = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getMmInfo().getColumnPropertiesArrayList();
					
		} else if (analysisType.equals("pkpdlink")) {
			columnPropAl = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getPkpdLinkInfo().getColumnPropertiesArrayList();
					
		} else if (analysisType.equals("irm")) {
			columnPropAl = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getIrInfo().getColumnPropertiesArrayList();
					
		} else if(analysisType.equals("ascii")){

			columnPropAl = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getAsciiInfo().getColumnPropertiesArrayList();
					
		
		}
		
		
		return columnPropAl;
		
	}

	
	
	void yVarTfEvent() throws RowsExceededException, WriteException,
			BiffException, IOException {

		CaMappingDispGuiCreator.createMappingInstance().YAndAvailableButton
				.setIcon(CaMappingDispGuiCreator.createMappingInstance().toLeft);
		isYToAvailable = true;
		isAvailableToY = false;
	}

	void sortVarListEvent() throws RowsExceededException, WriteException,
			BiffException, IOException {

		CaMappingDispGuiCreator.createMappingInstance().SortAndAvailableButton
				.setIcon(CaMappingDispGuiCreator.createMappingInstance().toLeft);
		isSortToAvailable = true;
		isAvailableToSort = false;
	}

	void availableVarToSortVarBtnEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		if (isAvailableToSort == true) {

			availableVarToSortVarEvent();

		}

		if (isSortToAvailable == true) {

			sortVarToAvailbleVarEvent();

		}
	}

	private void sortVarToAvailbleVarEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		ProcessingInputsInfo procInputInst = copyProcessingInput();

		String moving = (String)CaMappingDispGuiCreator.createMappingInstance().casortVariablesList
				.getSelectedValue();
		int selectedIndex = CaMappingDispGuiCreator.createMappingInstance().casortVariablesList
				.getSelectedIndex();
		CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.add(
						CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
								.getSize(), moving);
		procInputInst.getMappingDataObj().getSortVariablesListVector().add(
				procInputInst.getMappingDataObj().getAvailableColumnsVector()
						.size(), moving);

		procInputInst.getMappingDataObj().getSortVariablesListVector().remove(
				selectedIndex);
		CaMappingDispGuiCreator.createMappingInstance().sortVariableListModel
				.remove(selectedIndex);

		procInputInst.getMappingDataObj().getSortVariablesListVector().remove(
				selectedIndex);

		procInputInst.getMappingDataObj().getAvailableColumnsVector().add(
				moving);

		setProcessingInput(procInputInst);

		if (procInputInst.getMappingDataObj().getSortVariablesListVector()
				.size() == 0) {
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().propFinalEstimateCb
					.setEnabled(false);
		} else {
			CaEngineSettingDispGuiCreator.createEngineSettingInstance().propFinalEstimateCb
					.setEnabled(true);
		}
		
	}

	private void availableVarToSortVarEvent() throws RowsExceededException,
			WriteException, BiffException, IOException {

			ProcessingInputsInfo procInputInst = copyProcessingInput();

		int selectedIndex = CaMappingDispGuiCreator.createMappingInstance().caavailableColumnsList
				.getSelectedIndex();
		String moving = (String)CaMappingDispGuiCreator.createMappingInstance().caavailableColumnsList
				.getSelectedValue();
		procInputInst.getMappingDataObj().getSortVariablesListVector().add(
				procInputInst.getMappingDataObj().getSortVariablesListVector()
						.size(), moving);
		CaMappingDispGuiCreator.createMappingInstance().sortVariableListModel.add(
				CaMappingDispGuiCreator.createMappingInstance().sortVariableListModel
						.getSize(), moving);
		CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
				.remove(selectedIndex);

		procInputInst.getMappingDataObj().getAvailableColumnsVector().remove(
				selectedIndex);

		setProcessingInput(procInputInst);
		CaEngineSettingDispGuiCreator.createEngineSettingInstance().propFinalEstimateCb
				.setEnabled(true);
	}
	
	void settingVarForAvailableColList() throws RowsExceededException,
			WriteException, BiffException, IOException {
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
		CaMappingDispGuiCreator.createMappingInstance().SortAndAvailableButton.setIcon(CaMappingDispGuiCreator.createMappingInstance().toRight);
		CaMappingDispGuiCreator.createMappingInstance().xAndAvailableButton.setIcon(CaMappingDispGuiCreator.createMappingInstance().toRight);
		CaMappingDispGuiCreator.createMappingInstance().YAndAvailableButton.setIcon(CaMappingDispGuiCreator.createMappingInstance().toRight);
		CaMappingDispGuiCreator.createMappingInstance().moveToCarryAlongButton.setIcon(CaMappingDispGuiCreator.createMappingInstance().toRight);
		
		//
		if(analysisType.equals("ascii")){
			CaMappingDispGuiCreator.createMappingInstance().caFuncButton.setIcon(CaMappingDispGuiCreator.createMappingInstance().toRight);
		}
		
		isAvailableToSort = true;
		isSortToAvailable = false;
		isAvailableToX = true;
		isXToAvailable = false;
		isAvailableToY = true;
		isYToAvailable = false;
		isAvailableToEndTime = true;
		isEndTimeToAvailable = false;
		isAvailableToVolume = true;
		isVolumeToAvailable = false;
		isAvailableToCarryAlong = true;
		isCarryAlongToAvailable = false;
		isAvailableToSubject = true;
		isSubjectToAvailable = false;
		isAvailableToFunc = true;
	}

	public void funcAndAvailBtnHandler() throws RowsExceededException, WriteException, BiffException, IOException {
		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		if (isAvailableToFunc == true) {
			String moving = (String) CaMappingDispGuiCreator
					.createMappingInstance().caavailableColumnsList
					.getSelectedValue();

			int selectedindex = CaMappingDispGuiCreator.createMappingInstance().caavailableColumnsList
					.getSelectedIndex();
			int index = 0;
			ProcessingInputsInfo procInpuInst = copyProcessingInput();
			for (int i = 0; i < appInfo
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
					.getColumnPropertiesArrayList().size(); i++) {
				if (moving
						.equals(appInfo
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
								.getColumnPropertiesArrayList().get(i)
								.getColumnName())) {
					index = i;
					procInpuInst.getMappingDataObj().setFuncColName(moving);
					procInpuInst.getMappingDataObj()
							.setFuncColCorrespondngOrigIndex(index);

				}
			}

			setProcessingInput(procInpuInst);
			if (CaMappingDispGuiCreator.createMappingInstance().caFuncTF
					.getText().toString().equals("")) {
				CaMappingDispGuiCreator.createMappingInstance().caFuncTF
						.setText(moving);
				CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
						.remove(selectedindex);
			} else {
				String temp = CaMappingDispGuiCreator.createMappingInstance().caFuncTF
						.getText().toString();
				CaMappingDispGuiCreator.createMappingInstance().caFuncTF
						.setText(moving);
				CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
						.remove(selectedindex);
				int size = CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
						.getSize();
				CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
						.add(size, temp);
			}
		} else {
			
			CaMappingDispGuiCreator.createMappingInstance().caFuncButton.setIcon(CaMappingDispGuiCreator.createMappingInstance().toLeft);
			String temp = CaMappingDispGuiCreator.createMappingInstance().caFuncTF
			.getText().toString();
			int size = CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
			.getSize();
			CaMappingDispGuiCreator.createMappingInstance().availableVariablesListmodel
			.add(size, temp);
			CaMappingDispGuiCreator.createMappingInstance().caFuncTF
			.setText("");
		}
		
	
	}

}
