package view;

import java.awt.Component;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Common.JinternalFrameFunctions;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AsciiSecParameterDispGui {

	public static AsciiSecParameterDispGui ASCIISECPARAMGUI = null;

	public static AsciiSecParameterDispGui createAsciiSecParamGuiInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (ASCIISECPARAMGUI == null) {
			ASCIISECPARAMGUI = new AsciiSecParameterDispGui();
		}
		return ASCIISECPARAMGUI;
	}
	
	public AsciiSecParameterDispGui() throws RowsExceededException, WriteException, BiffException, IOException{
		createContainerframes();
		createParamTable();
	}
	JTable asciiSecParamTable;
	JTable asciiSecParamFuncTalbe;
	JScrollPane asciiParamFuncTableSP;
	private void createParamTable() {
		asciiSecParamTable = new JTable(0,1);
		asciiSecParamTable.getModel().addTableModelListener(
				DDViewLayer.createViewLayerInstance());
		TableColumnModel tcm = asciiSecParamTable.getColumnModel();
		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue("Secondary");

		JScrollPane asciiSecParamTableSP = new JScrollPane(asciiSecParamTable);
		asciiSecParamTableSP.setBorder(BorderFactory
				.createTitledBorder("Sec Parameters"));
		asciiSecParamTableSP
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiSecParamTableSP
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiSecParamIF.getContentPane().add(asciiSecParamTableSP);
		
		//secondary parameter's corresponding function table
		DefaultTableModel model = new DefaultTableModel(0,1);
		asciiSecParamFuncTalbe = new JTable(model){
	        private static final long serialVersionUID = 1L;
	        
	        @Override
	        public Class getColumnClass(int column) {
	        	return Boolean.class;
	        }
	        };
		asciiSecParamFuncTalbe.getModel().addTableModelListener(
				DDViewLayer.createViewLayerInstance());
		tcm = asciiSecParamFuncTalbe.getColumnModel();

		tc = tcm.getColumn(0);
		tc.setHeaderValue("Function 1");
		asciiParamFuncTableSP = new JScrollPane(asciiSecParamFuncTalbe);
		asciiParamFuncTableSP.setBorder(BorderFactory
				.createTitledBorder("Parameter Functions"));
		asciiParamFuncTableSP
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiParamFuncTableSP
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiSecParamFuncIF.getContentPane().add(asciiParamFuncTableSP);
		
	}

	JInternalFrame asciiSecParamIF;
	JInternalFrame asciiSecParamFuncIF;
	private void createContainerframes() throws RowsExceededException, WriteException, BiffException, IOException {
		
		asciiSecParamIF = new JInternalFrame("ascii Sec parameters", false,false,false,false);
		
		asciiSecParamIF.setLocation(AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiParametersIF.getX() 
				,
				AsciiPrimParametersDispGUI.createAsciiPrimParamGuiInst().asciiParametersIF.getY());
		asciiSecParamIF.setSize(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getWidth()/2,
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getHeight());
		asciiSecParamIF.setVisible(true);
		asciiSecParamIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(asciiSecParamIF);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
		.add(asciiSecParamIF);
		
		
		asciiSecParamFuncIF = new JInternalFrame("ascii Primary parameter functions mapping", false,false,false,false);
		
		asciiSecParamFuncIF.setLocation(asciiSecParamIF.getX() + asciiSecParamIF.getWidth(),
				
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getY());
		asciiSecParamFuncIF.setSize(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getWidth()/2,
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getHeight());
		asciiSecParamFuncIF.setVisible(true);
		asciiSecParamFuncIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(asciiSecParamFuncIF);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
		.add(asciiSecParamFuncIF);
		
		
	}
	
}
