package view;

import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Common.JinternalFrameFunctions;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AsciiInitialEstGui {

	public static AsciiInitialEstGui ASCIIINITESTGUI = null;
	public static AsciiInitialEstGui createAsciiinitGuiInst(){
		if(ASCIIINITESTGUI == null){
			ASCIIINITESTGUI = new AsciiInitialEstGui();
		}
		return ASCIIINITESTGUI;
	}
	
	public AsciiInitialEstGui(){
		
	}
	boolean isInitEstimateUpdateReq;

	public void createInitEstimatesScreen() throws RowsExceededException, WriteException, BiffException, IOException {
		createContainers();
		createEstimatesTable();
		
	}

	JTable asciiIntEstTable;
	void createEstimatesTable() {
		
		asciiIntEstTable = new JTable(1,1);
		JScrollPane asciiInitEstSP = new JScrollPane(asciiIntEstTable);
		asciiIntEstTable.getModel().addTableModelListener(DDViewLayer.createViewLayerInstance());
		asciiInitEstSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiInitEstSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiInitEstIF.getContentPane().add(asciiInitEstSP);
		
	}

	JInternalFrame asciiInitEstIF; 
	private void createContainers() throws RowsExceededException, WriteException, BiffException, IOException {
		asciiInitEstIF = new JInternalFrame("ascii initial estimares", false,false,false,false);
		asciiInitEstIF.setLocation(CaMappingDispGuiCreator.createMappingInstance().mappingInternalFrame.getX(),
				CaMappingDispGuiCreator.createMappingInstance().mappingInternalFrame.getY());
		asciiInitEstIF.setSize(CaMappingDispGuiCreator.createMappingInstance().mappingInternalFrame.getWidth(),
				CaMappingDispGuiCreator.createMappingInstance().mappingInternalFrame.getHeight());
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(asciiInitEstIF);
		asciiInitEstIF.setVisible(true);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane.add(asciiInitEstIF);
		asciiInitEstIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		
	}
}
