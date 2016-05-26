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

public class AsciiEqnSecParamDispGui {

	public static AsciiEqnSecParamDispGui ASCIISECINST = null;
	public static AsciiEqnSecParamDispGui createAsciiSecInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(ASCIISECINST == null){
			ASCIISECINST = new AsciiEqnSecParamDispGui();
		}
		return ASCIISECINST;
	}
	
	public AsciiEqnSecParamDispGui() throws RowsExceededException, WriteException, BiffException, IOException{
		createContainers();
		createSeconTA();
		
	}
	
	JTextArea asciiEqnSecParamTA;
	private void createSeconTA() {
		
		asciiEqnSecParamTA = new JTextArea();
		asciiEqnSecParamTA.getDocument().addDocumentListener(DDViewLayer.createViewLayerInstance());
		JScrollPane asciiSeconSP = new JScrollPane(asciiEqnSecParamTA);
		asciiSeconSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiSeconSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiSeconSP.setBorder(BorderFactory.createTitledBorder("Secondary Parameter Equations"));
		asciiEqnSecParamIF.getContentPane().add(asciiSeconSP);
	}

	JInternalFrame asciiEqnSecParamIF;
	private void createContainers()
	throws RowsExceededException, WriteException, BiffException, IOException {
		asciiEqnSecParamIF = new JInternalFrame("ascii secondary parameter equations IF",false,false,false,false);
		asciiEqnSecParamIF.setLocation(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getX(),
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getY());
		asciiEqnSecParamIF.setSize(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getWidth()
				, AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getHeight());
		asciiEqnSecParamIF.setVisible(true);
		asciiEqnSecParamIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(asciiEqnSecParamIF);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
		.add(asciiEqnSecParamIF);
	}
	
}
