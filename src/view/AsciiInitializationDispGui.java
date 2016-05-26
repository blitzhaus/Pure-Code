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

public class AsciiInitializationDispGui {

	public static AsciiInitializationDispGui ASCIISTARTINST = null;
	public static AsciiInitializationDispGui createAsciiStartInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(ASCIISTARTINST == null){
			ASCIISTARTINST = new AsciiInitializationDispGui();
		}
		return ASCIISTARTINST;
	}
	
	public AsciiInitializationDispGui() throws RowsExceededException, WriteException, BiffException, IOException{
		createContainers();
		createStartTA();
	}
	
	JTextArea asciiInitializationTA;
	private void createStartTA() {
		
		asciiInitializationTA = new JTextArea();
		asciiInitializationTA.getDocument().addDocumentListener(DDViewLayer.createViewLayerInstance());
		JScrollPane asciiStartSP = new JScrollPane(asciiInitializationTA);
		asciiStartSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiStartSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiStartSP.setBorder(BorderFactory.createTitledBorder("Initialization"));
		asciiInitializationIF.getContentPane().add(asciiStartSP);
	}

	JInternalFrame asciiInitializationIF;
	private void createContainers()
	throws RowsExceededException, WriteException, BiffException, IOException {
		asciiInitializationIF = new JInternalFrame("ascii Initialization IF",false,false,false,false);
		asciiInitializationIF.setLocation(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getX(),
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getY());
		asciiInitializationIF.setSize(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getWidth()
				, AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getHeight());
		asciiInitializationIF.setVisible(true);
		asciiInitializationIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(asciiInitializationIF);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
		.add(asciiInitializationIF);
		
		
		
		
		
	}
}
