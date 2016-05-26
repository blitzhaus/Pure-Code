package view;

import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;

public class AsciiAlgEqcDispGui {

	public static AsciiAlgEqcDispGui ASCIIFUNCINST = null;
	public static AsciiAlgEqcDispGui createAsciiFuncInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(ASCIIFUNCINST == null){
			ASCIIFUNCINST = new AsciiAlgEqcDispGui();
		}
		return ASCIIFUNCINST;
	}
	
	public AsciiAlgEqcDispGui() throws RowsExceededException, WriteException, BiffException, IOException{
		createContainers();
		createFuncTA();
	}
	JTextArea asciiAlgEqnTA;
	private void createFuncTA() {
		
		asciiAlgEqnTA = new JTextArea();
		asciiAlgEqnTA.getDocument().addDocumentListener(DDViewLayer.createViewLayerInstance());
		JScrollPane asciiFuncSP = new JScrollPane(asciiAlgEqnTA);
		asciiFuncSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiFuncSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiFuncSP.setBorder(BorderFactory.createTitledBorder("Algebraic Equations"));
		asciiAlgEqnIF.getContentPane().add(asciiFuncSP);
	}

	JInternalFrame asciiAlgEqnIF;
	private void createContainers()
	throws RowsExceededException, WriteException, BiffException, IOException {
		asciiAlgEqnIF = new JInternalFrame("ascii Algebraic equations IF",false,false,false,false);
		asciiAlgEqnIF.setLocation(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getX(),
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getY());
		asciiAlgEqnIF.setSize(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getWidth()
				, AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getHeight());
		asciiAlgEqnIF.setVisible(true);
		asciiAlgEqnIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(asciiAlgEqnIF);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
		.add(asciiAlgEqnIF);
		
		
		
		
		
	}
}
