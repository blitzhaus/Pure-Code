package view;

import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Common.JinternalFrameFunctions;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AsciiDeclarationDispGui {

	public static AsciiDeclarationDispGui ASCIIDecINST = null;
	public static AsciiDeclarationDispGui createAsciiTempInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(ASCIIDecINST == null){
			ASCIIDecINST = new AsciiDeclarationDispGui();
		}
		return ASCIIDecINST;
	}
	
	public AsciiDeclarationDispGui() throws RowsExceededException, WriteException, BiffException, IOException{
		createContainers();
		createTextArea();
	}

	JTextArea asciiDeclareTA;
	private void createTextArea() {
		
		asciiDeclareTA = new JTextArea();
		asciiDeclareTA.getDocument().addDocumentListener(DDViewLayer.createViewLayerInstance());
		JScrollPane asciiTempSP = new JScrollPane(asciiDeclareTA);
		asciiTempSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiTempSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiTempSP.setBorder(BorderFactory.createTitledBorder("Declaration"));
		asciiDecIF.getContentPane().add(asciiTempSP);
	}

	JInternalFrame asciiDecIF;
	private void createContainers()
	throws RowsExceededException, WriteException, BiffException, IOException {
		asciiDecIF = new JInternalFrame("ascii Declaration IF",false,false,false,false);
		asciiDecIF.setLocation(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getX(),
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getY());
		asciiDecIF.setSize(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getWidth()
				, AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getHeight());
		asciiDecIF.setVisible(true);
		asciiDecIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(asciiDecIF);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
		.add(asciiDecIF);
		
		
		
		
		
	}
}
