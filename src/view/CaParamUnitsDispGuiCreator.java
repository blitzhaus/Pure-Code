package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.JinternalFrameFunctions;

public class CaParamUnitsDispGuiCreator {
	
	UnitBuilder unitBuilderInst;
	private final class CaParaunitsTableModelHandler implements
			TableModelListener {
		@Override
		public void tableChanged(TableModelEvent arg0) {
			isRestoringParameterUnits = true;


			isFromPreferredUnitInternalFrame = true;
			try {
				NcaOptionsGui.createNcaOptionsInstance().isFromDoseUnitBuilding = false;
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
			if (parameterUnitsTable.getSelectedColumn() == 2) {
				
				rowNoForPreferredUnit = parameterUnitsTable.getSelectedRow();
				try {
					CaLibModelDispGuiCreator.createCalibraryModelInstance().ifFromPkUnit = false;
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
				isFromPreferredUnitInternalFrame = true;
				try {
					CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().ifFromDoseUnitOfCA = false;
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
				unitBuilderInst = UnitBuilder.createUBInstance();
				unitBuilderInst.unitBuilderFrame.setVisible(true);

			}
		
			
		}
	}
	
	
	public CaParamUnitsDispGuiCreator(){
		
	}
	
	JInternalFrame parameterUnitsInternalFrame;
	JTable parameterUnitsTable;
	boolean isRestoringParameterUnits;
	boolean isFromPreferredUnitInternalFrame;
	int rowNoForPreferredUnit;
	
	public static CaParamUnitsDispGuiCreator CA_PAR_UNITS_DISP = null;
	public static CaParamUnitsDispGuiCreator createCaParUnitsDisInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(CA_PAR_UNITS_DISP == null){
			CA_PAR_UNITS_DISP = new CaParamUnitsDispGuiCreator();
			
		}
		return CA_PAR_UNITS_DISP;
	}
	
	

	public void caParameterUnitsDispGuiCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createParameterUnitsInternalFrame();
	}
	public  void createParameterUnitsInternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {

		parameterUnitsInternalFrame = new JInternalFrame("unit specifier",
				true, true, true, true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(parameterUnitsInternalFrame);
		parameterUnitsInternalFrame.setLocation(CaMappingDispGuiCreator.createMappingInstance().mappingInternalFrame.getX(),
				CaMappingDispGuiCreator.createMappingInstance().mappingInternalFrame.getY());
		parameterUnitsInternalFrame.setSize(CaMappingDispGuiCreator.createMappingInstance().mappingInternalFrame.getWidth(),
				CaMappingDispGuiCreator.createMappingInstance().mappingInternalFrame.getHeight());
		parameterUnitsInternalFrame.setVisible(true);
		parameterUnitsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane.add(parameterUnitsInternalFrame);

		parameterUnitsTable = new JTable(0, 3) {

			public boolean isCellEditable(int row, int col) {
				if ((col == 0)||(col ==1)||(col == 2)) {
					return false;
				} else {
					return true;
				}
			}

		};
		//parameterUnitsTable.addMouseListener(this);
		parameterUnitsTable.getModel().addTableModelListener(new CaParaunitsTableModelHandler());
	
		JTableHeader parameterUnitsTableheader = parameterUnitsTable
				.getTableHeader();
		TableColumnModel tm = parameterUnitsTableheader.getColumnModel();
		TableColumn tc = tm.getColumn(0);
		tc.setHeaderValue("Parameters");
		tc = tm.getColumn(1);
		tc.setHeaderValue("Default units");
		tc = tm.getColumn(2);
		tc.setHeaderValue("Preferred Units");
		JScrollPane parameterUnitsTableScrollPane = new JScrollPane(
				parameterUnitsTable);
		parameterUnitsTable.setRowSelectionAllowed(true);
		parameterUnitsTable.setColumnSelectionAllowed(true);
		parameterUnitsTable.setCellSelectionEnabled(true);
		parameterUnitsTable.setBackground(Color.white);
		parameterUnitsInternalFrame.getContentPane().add(
				parameterUnitsTableScrollPane);
		
		
		
		
	}
	
	
	void paramUnitTableRebuilding(ProcessingInputsInfo procInputInst) {
		
		
		int rowCount = parameterUnitsTable.getRowCount();
		for (int i = rowCount; i > 0; i--) {
			((DefaultTableModel) parameterUnitsTable
					.getModel()).removeRow(i - 1);
			
		}
	
		for (int i = 0; i < procInputInst.getProfileAndParamInfoObj().getParameterNameByGroupForCA().length ; i++) {
			String[] s = new String[3];
			String c = procInputInst.getProfileAndParamInfoObj()
					.getParameterNameByGroupForCA()[i];
			s[0] = c;
			String u = procInputInst.getProfileAndParamInfoObj()
					.getDefaultParameterUnitForCA()[i];
			String u1 = procInputInst.getProfileAndParamInfoObj()
					.getPreferredParameterUnitForCA()[i];
			s[1] = u;
	
			s[2] = u1;
	
			((DefaultTableModel) parameterUnitsTable.getModel())
					.insertRow(parameterUnitsTable.getRowCount(), s);
	
			parameterUnitsTable.isCellEditable(i, 0);
			parameterUnitsTable.isCellEditable(i, 1);
		}
		
		parameterUnitsTable.repaint();
		parameterUnitsInternalFrame.repaint();
		parameterUnitsInternalFrame.validate();
	}



	/*@Override
	public void mouseClicked(MouseEvent f) {
		

		if (f.getSource() == parameterUnitsTable) {


		isFromPreferredUnitInternalFrame = true;
		try {
			NcaOptionsGui.createNcaOptionsInstance().isFromDoseUnitBuilding = false;
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
		if (parameterUnitsTable.getSelectedColumn() == 2) {
			
			rowNoForPreferredUnit = parameterUnitsTable.getSelectedRow();
			try {
				CaLibModelDispGuiCreator.createCalibraryModelInstance().ifFromPkUnit = false;
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
			isFromPreferredUnitInternalFrame = true;
			try {
				CaWeightDoseDispGuiCreator.createCaWeightDoseInstance().ifFromDoseUnitOfCA = false;
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
			UnitBuilder.createUBInstance().unitBuilderFrame.setVisible(true);

		}
		}
	
		
	
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}*/

}
