package view;

import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import Common.JinternalFrameFunctions;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AsciiParametersDispGUI {

	public static AsciiParametersDispGUI ASCIIPARAMGUI = null;

	public static AsciiParametersDispGUI createAsciiParamGuiInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (ASCIIPARAMGUI == null) {
			ASCIIPARAMGUI = new AsciiParametersDispGUI();
		}
		return ASCIIPARAMGUI;
	}
	
	public AsciiParametersDispGUI() throws RowsExceededException,
			WriteException, BiffException, IOException {
		createContainerframes();
		createParamTable();
	}

	JTable asciiParamTable;
	JTable asciiSecParamTable;
	private void createParamTable() {
		asciiParamTable = new JTable(0, 1);
		asciiParamTable.getModel().addTableModelListener(
				DDViewLayer.createViewLayerInstance());
		TableColumnModel tcm = asciiParamTable.getColumnModel();

		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue("Primary");
		JScrollPane asciiParamTableSP = new JScrollPane(asciiParamTable);
		asciiParamTableSP.setBorder(BorderFactory
				.createTitledBorder("Parameters"));
		asciiParamTableSP
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiParamTableSP
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiParametersIF.getContentPane().add(asciiParamTableSP);
		
		//Secondsary parameters table
		asciiSecParamTable = new JTable(0, 1);
		asciiSecParamTable.getModel().addTableModelListener(
				DDViewLayer.createViewLayerInstance());
		tcm = asciiSecParamTable.getColumnModel();
		tc = tcm.getColumn(0);
		tc.setHeaderValue("Secondary");

		JScrollPane asciiSecParamTableSP = new JScrollPane(asciiSecParamTable);
		asciiSecParamTableSP.setBorder(BorderFactory
				.createTitledBorder("Sec Parameters"));
		asciiSecParamTableSP
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiSecParamTableSP
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiSecParamIF.getContentPane().add(asciiSecParamTableSP);

	}

	JInternalFrame asciiParametersIF;
	JInternalFrame asciiSecParamIF;
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
		
		asciiSecParamIF = new JInternalFrame("ascii Sec parameters", false,false,false,false);
		
		asciiSecParamIF.setLocation(asciiParametersIF.getX() + asciiParametersIF.getWidth(),
				asciiParametersIF.getY());
		asciiSecParamIF.setSize(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getWidth()/2,
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getHeight());
		asciiSecParamIF.setVisible(true);
		asciiSecParamIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(asciiSecParamIF);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
		.add(asciiSecParamIF);
	}
	
}
