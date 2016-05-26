package view;

import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import Common.JinternalFrameFunctions;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AsciiPrimParametersDispGUI {

	public static AsciiPrimParametersDispGUI ASCIIPARAMGUI = null;

	public static AsciiPrimParametersDispGUI createAsciiPrimParamGuiInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (ASCIIPARAMGUI == null) {
			ASCIIPARAMGUI = new AsciiPrimParametersDispGUI();
		}
		return ASCIIPARAMGUI;
	}
	
	public AsciiPrimParametersDispGUI() throws RowsExceededException,
			WriteException, BiffException, IOException {
		createContainerframes();
		createParamTable();

	}

	JTable asciiPrimParamTable;
	JTable asciiPriParamFuncTable;
	JScrollPane asciiParamFuncTableSP;
	private void createParamTable() {
		
		asciiPrimParamTable = new JTable(0,1);
		asciiPrimParamTable.getModel().addTableModelListener(
				DDViewLayer.createViewLayerInstance());
		TableColumnModel tcm = asciiPrimParamTable.getColumnModel();

		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue("Primary");
		JScrollPane asciiParamTableSP = new JScrollPane(asciiPrimParamTable);
		asciiParamTableSP.setBorder(BorderFactory
				.createTitledBorder("Pri Parameters"));
		asciiParamTableSP
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiParamTableSP
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiParametersIF.getContentPane().add(asciiParamTableSP);
		
		
		DefaultTableModel model = new DefaultTableModel(0, 1);
		asciiPriParamFuncTable = new JTable(model){  
			
			private static final long serialVersionUID = 1L;
	        
	        @Override
	        public Class getColumnClass(int column) {
	        	return Boolean.class;
	        }
	        };
		/*asciiPriParamFuncTable.getModel().addTableModelListener(
				ViewLayer.createViewLayerInstance());*/
	    asciiPriParamFuncTable.getSelectionModel().addListSelectionListener(new AsciiPrimaryParamFuncSelectionHandler());
		tcm = asciiPriParamFuncTable.getColumnModel();

		tc = tcm.getColumn(0);
		tc.setHeaderValue("Function 1");
		asciiParamFuncTableSP = new JScrollPane(asciiPriParamFuncTable);
		asciiParamFuncTableSP.setBorder(BorderFactory
				.createTitledBorder("Parameter Functions"));
		asciiParamFuncTableSP
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiParamFuncTableSP
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiPriParamFuncIF.getContentPane().add(asciiParamFuncTableSP);
		

	}

	JInternalFrame asciiParametersIF;
	JInternalFrame asciiPriParamFuncIF;
	
	private void createContainerframes() throws RowsExceededException, WriteException, BiffException, IOException {
		
		asciiParametersIF = new JInternalFrame("ascii parameters", false,false,false,false);
		
		asciiParametersIF.setLocation(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getX(),
				
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getY());
		asciiParametersIF.setSize(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getWidth()/2,
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getHeight());
		asciiParametersIF.setVisible(true);
		asciiParametersIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(asciiParametersIF);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
		.add(asciiParametersIF);
		
		
		
		asciiPriParamFuncIF = new JInternalFrame("ascii Primary parameter functions mapping", false,false,false,false);
		
		asciiPriParamFuncIF.setLocation(asciiParametersIF.getX() + asciiParametersIF.getWidth(),
				
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getY());
		asciiPriParamFuncIF.setSize(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getWidth()/2,
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getHeight());
		asciiPriParamFuncIF.setVisible(true);
		asciiPriParamFuncIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(asciiPriParamFuncIF);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
		.add(asciiPriParamFuncIF);
		
		
	}
	
}
