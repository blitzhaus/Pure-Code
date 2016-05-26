package view;

import java.io.IOException;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DosingTabModelSelecHandler implements
TableModelListener {
	
	ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
	DosingData dosingDataInst = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
	.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
			.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo().getProcessingInputs()
			.getDosingDataObj();
	
	NcaDosingDispForDosingDefinedGui inst;
	
	@Override
	public void tableChanged(TableModelEvent arg0) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		
		int columnChanged = 0;
		try {
			columnChanged = NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing.getSelectedColumn();
		} catch (RowsExceededException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (WriteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (BiffException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			NcaDosingDispGui.createNcaDosingGuiInst(). rowChanged  = NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing.getSelectedRow();
		} catch (RowsExceededException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (WriteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (BiffException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		if(columnChanged < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo().getProcessingInputs().getMappingDataObj()
				.getSortVariablesListVector().size() + 4)
		{
			try {
				if((columnChanged>=0)&&(NcaDosingDispGui.createNcaDosingGuiInst().rowChanged >= 0))
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
							.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getNcaInfo().getProcessingInputs()
							.getDosingDataObj().setDosingValueAt(NcaDosingDispGui.createNcaDosingGuiInst().rowChanged, columnChanged,
									(String) NcaDosingDispGui.createNcaDosingGuiInst().tableForDosing.getValueAt(NcaDosingDispGui.createNcaDosingGuiInst().rowChanged, columnChanged));
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
		}
		else
		{
			 inst = new NcaDosingDispForDosingDefinedGui();
			
				try {
					inst.actionEventForDosingDefinedRouteAdmin(NcaDosingDispGui.createNcaDosingGuiInst().rowChanged, columnChanged);
					NcaDosingDispGui.createNcaDosingGuiInst(). tableForDosing.validate();
					NcaDosingDispGui.createNcaDosingGuiInst(). DosingInternalFrame.moveToFront();
					NcaDosingDispGui.createNcaDosingGuiInst(). DosingInternalFrame.validate();
					NcaDosingDispGui.createNcaDosingGuiInst(). DosingInternalFrame.setSize(
							NcaDosingDispGui.createNcaDosingGuiInst(). DosingInternalFrame.getSize());
				
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
				
				
			
		}
	
		
	}
}

