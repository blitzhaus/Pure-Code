package view;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.EventCodes;
import Common.JinternalFrameFunctions;

public class NcaParameterUnitsDispGui {
	
	private final class NcaParaunitsTableModelHandler implements
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
				UnitBuilder unitBuilderFrame = new UnitBuilder();
				//unitBuilderFrame.createUnitBuilderFrame();
				
				//if(unitBuilderFrame.)

			}
		
			
		}
	}
	
	
	public NcaParameterUnitsDispGui(String dummy){
		
	}
	
	JInternalFrame parameterUnitsInternalFrame;
	JTable parameterUnitsTable;
	boolean isRestoringParameterUnits;
	boolean isFromPreferredUnitInternalFrame;
	
	public static NcaParameterUnitsDispGui NCA_PAR_UNITS_DISP = null;
	public static NcaParameterUnitsDispGui createNcaParUnitsDisInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(NCA_PAR_UNITS_DISP == null){
			NCA_PAR_UNITS_DISP = new NcaParameterUnitsDispGui("just object creation");
			
		}
		return NCA_PAR_UNITS_DISP;
	}
	
	

	public void NcaParameterUnitsDispGuiCreation() throws RowsExceededException, WriteException, BiffException, IOException{
		createParameterUnitsInternalFrame();
	}
	private void createParameterUnitsInternalFrame() throws RowsExceededException, WriteException, BiffException, IOException {

		parameterUnitsInternalFrame = new JInternalFrame("unit specifier",
				true, true, true, true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(parameterUnitsInternalFrame);
		parameterUnitsInternalFrame.setLocation(NcaMappingDispGui.createMappingInstance().MappingInternalFrame.getX(),
				NcaMappingDispGui.createMappingInstance().MappingInternalFrame.getY());
		parameterUnitsInternalFrame.setSize(NcaMappingDispGui.createMappingInstance().MappingInternalFrame.getWidth(),
				NcaMappingDispGui.createMappingInstance().MappingInternalFrame.getHeight());
		parameterUnitsInternalFrame.setVisible(true);
		parameterUnitsInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		NcaTabbedPanes.createNcaTabbedPaneInstance().setupTabDesktopPane.add(parameterUnitsInternalFrame);

		parameterUnitsTable = new JTable(0, 3) {

			public boolean isCellEditable(int row, int col) {
				if ((col == 0)||(col ==1)||(col == 2)) {
					return false;
				} else {
					return true;
				}
			}

		};
		//parameterUnitsTable.addMouseListener(ViewLayer.createViewLayerInstance());
	//	parameterUnitsTable.getModel().addTableModelListener(new NcaParaunitsTableModelHandler());
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
		parameterUnitsTable.addMouseListener(DDViewLayer.createViewLayerInstance());
		parameterUnitsInternalFrame.getContentPane().add(
				parameterUnitsTableScrollPane);

	}

}
