package view;

import java.io.IOException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AsciiPrimaryParamFuncSelectionHandler implements ListSelectionListener {

	public AsciiPrimaryParamFuncSelectionHandler(){
		System.out.println();
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getValueIsAdjusting() == false){
			
			try{

				if(true){
					ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
					/*AsciiModelInfo	asciiModInfoInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
												appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).getAsciiInfo();*/
					

					int rowChanged = AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable.getSelectedRow();
					int columnChanged = AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable.getSelectedColumn();
					if((rowChanged >= 0)&&(columnChanged >= 0)){
						
						AsciiInitialEstGui.createAsciiinitGuiInst().isInitEstimateUpdateReq = true;
						//if the parameter is included for the function
						if( ((Boolean)AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable.getValueAt(rowChanged, columnChanged)) == true){
							
							String parameter = AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPrimParamTable.getValueAt(rowChanged, 0).toString();
							addParameter(parameter, columnChanged, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex()).getAsciiInfo());
							
						} else {
							String parameter = AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPrimParamTable.getValueAt(rowChanged, 0).toString();
							removeParameter(parameter, columnChanged, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getWorkSheetObjectsAL().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
													.getSelectedSheetIndex()).getAsciiInfo());
							
						}
						AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable.getSelectionModel().clearSelection();
						
					}
					System.out.println();
					
/**/
				
				}

			
			} catch(Exception e){
				
			}

		}
		
	}
	
	
	private void removeParameter(String parameter, int funcIndex, AsciiModelInfo asciiModInfoInst) {
	
	
		asciiModInfoInst.getProcessingInputs().getAsciiInitEstimates().getFuncArray().get(funcIndex).remove(parameter);
		int index = asciiModInfoInst.getProcessingInputs().getAsciiInitEstimates().getIndexedCopyOfPrarameters().indexOf(parameter);
		asciiModInfoInst.getProcessingInputs().getAsciiInitEstimates().getIndexedCopyOfPrarameters().remove(index);
	}

	private void addParameter(String parameter, int funcIndex, AsciiModelInfo asciiModInfoInst) throws RowsExceededException, WriteException, BiffException, IOException {
		
		String funcVar = AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiPriParamFuncTable.getColumnName(funcIndex);
		// TODO Auto-generated method stub
		asciiModInfoInst.getProcessingInputs().getAsciiInitEstimates().getFuncArray().get(funcVar).add(parameter);
		asciiModInfoInst.getProcessingInputs().getAsciiInitEstimates().getIndexedCopyOfPrarameters().add(parameter);
	}

}
