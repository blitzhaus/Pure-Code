package view;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NPSDosingTabModelSelecHandler implements TableModelListener {

	@Override
	public void tableChanged(TableModelEvent arg0) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
		ProcessingInputsInfo procInputInst = NPSHandlers.createNPSHandlersInst().copyProcessingInput();
		
		
		
		try
		{
			int rowChanged = NPSDosingTableCreation
			.createNpsDosingGuiInst().dosingTableForNPS.getSelectedRow();
			int colChanged = NPSDosingTableCreation
			.createNpsDosingGuiInst().dosingTableForNPS.getSelectedColumn();
			
			if(rowChanged >=0 && colChanged >=0)
			for (int i = 0; i < NPSDosingTableCreation.createNpsDosingGuiInst().dosingTableForNPS
					.getRowCount(); i++) {
				for (int j = 0; j < NPSDosingTableCreation
						.createNpsDosingGuiInst().dosingTableForNPS
						.getColumnCount(); j++) {
					procInputInst.getModleInputForNPSObj()
							.setDosingValueAt(
									i,
									j,
									NPSDosingTableCreation
											.createNpsDosingGuiInst().dosingTableForNPS
											.getValueAt(i, j)
											+ "");
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		/*int rowChanged = 0;
		try {
			rowChanged = NPSDosingTableCreation.createNpsDosingGuiInst().dosingTableForNPS
					.getSelectedRow();
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
		int colChanged = 0;
		try {
			colChanged = NPSDosingTableCreation.createNpsDosingGuiInst().dosingTableForNPS
					.getSelectedColumn();
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

		String tempStr = null;
		try {
			tempStr = (String) NPSDosingTableCreation.createNpsDosingGuiInst().dosingTableForNPS
					.getValueAt(rowChanged, colChanged);
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

		try {

			if (tempStr.equals("")) {

			} else
				Double.parseDouble(tempStr);

		
				for (int i = 0; i < NPSDosingTableCreation.createNpsDosingGuiInst().dosingTableForNPS
						.getRowCount(); i++) {
					for (int j = 0; j <NPSDosingTableCreation.createNpsDosingGuiInst().dosingTableForNPS
							.getColumnCount(); j++) {
						appInfo.getWorkSheetObjectsAL().get(
								appInfo.getSelectedSheetIndex())
								.getCaInfo().getProcessingInputs()
								.getDosingDataObj().setDosingValueAt(
										i,
										j,
										NPSDosingTableCreation.createNpsDosingGuiInst().dosingTableForNPS
												.getValueAt(i, j)
												+ "");
					}
				}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(
					NPSMainPage.createNPSMainPageInst().npsMainInternalFrame,
					"Please insert a Number", "Confirm",
					JOptionPane.YES_OPTION);

			try {
				NPSDosingTableCreation.createNpsDosingGuiInst().dosingTableForNPS.setValueAt(appInfo
						.getWorkSheetObjectsAL().get(
								appInfo.getSelectedSheetIndex()).getNpsInfo()
						.getProcessingInputs().getModleInputForNPSObj().getDosingValueAt(rowChanged, colChanged),
						rowChanged, colChanged);
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
		}

	*/
		// TODO Auto-generated method stub

	}

}
