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

public class AsciiDiffRqnDispGui {

	public static AsciiDiffRqnDispGui ASCIIDIFFINST = null;
	public static AsciiDiffRqnDispGui createAsciiDiffInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(ASCIIDIFFINST == null){
			ASCIIDIFFINST = new AsciiDiffRqnDispGui();
		}
		
		return ASCIIDIFFINST;
	}
	
	public AsciiDiffRqnDispGui() throws RowsExceededException, WriteException, BiffException, IOException{
		createContainers();
		createDiffTA();
	}
	
	JTextArea asciiDiffEqnTA;
	private void createDiffTA() {
		
		asciiDiffEqnTA = new JTextArea();
		asciiDiffEqnTA.getDocument().addDocumentListener(DDViewLayer.createViewLayerInstance());
		JScrollPane asciiDiffSP = new JScrollPane(asciiDiffEqnTA);
		asciiDiffSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiDiffSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiDiffSP.setBorder(BorderFactory.createTitledBorder("Differential Equations"));
		asciiDiffIF.getContentPane().add(asciiDiffSP);
	}

	JInternalFrame asciiDiffIF;
	private void createContainers()
	throws RowsExceededException, WriteException, BiffException, IOException {
		asciiDiffIF = new JInternalFrame("ascii Differential equations IF",false,false,false,false);
		asciiDiffIF.setLocation(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getX(),
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getY());
		asciiDiffIF.setSize(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getWidth()
				, AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getHeight());
		asciiDiffIF.setVisible(true);
		asciiDiffIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(asciiDiffIF);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
		.add(asciiDiffIF);
	}
}
