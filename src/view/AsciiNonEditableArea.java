package view;

import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Common.JinternalFrameFunctions;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AsciiNonEditableArea {

	public static AsciiNonEditableArea ASCIINONEDAREA = null;
	public static AsciiNonEditableArea createAsciiNonEditInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(ASCIINONEDAREA == null){
			ASCIINONEDAREA = new AsciiNonEditableArea();
		}
		return ASCIINONEDAREA;
	}
	
	public AsciiNonEditableArea() throws RowsExceededException, WriteException, BiffException, IOException{
		createContainerComponents();
		createInternalComponents();
	}

	JInternalFrame asciiNonEditAreaIF;
	JTextArea asciiSummaryTA;
	private void createInternalComponents() {
		asciiSummaryTA = new JTextArea();
		asciiSummaryTA.setEditable(false);
		JScrollPane asciiSummarySP = new JScrollPane(asciiSummaryTA);
		asciiSummarySP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiSummarySP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiSummarySP.setBorder(DDViewLayer.createViewLayerInstance().b);
		asciiSummarySP.setVisible(true);
		asciiNonEditAreaIF.getContentPane().add(asciiSummarySP);
		
	}

	private void createContainerComponents() throws RowsExceededException,
			WriteException, BiffException, IOException {

		asciiNonEditAreaIF = new JInternalFrame("Non editable area", false,
				false, false, false);
		asciiNonEditAreaIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(asciiNonEditAreaIF);
		asciiNonEditAreaIF
				.setLocation(
						AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF
								.getX()
								+ AsciiSelectionDispGUI
										.createAsciiCommandGuiInst().asciiSelectionIF
										.getWidth(), AsciiSelectionDispGUI
								.createAsciiCommandGuiInst().asciiSelectionIF
								.getY());

		asciiNonEditAreaIF.setSize(AsciiSelectionDispGUI
				.createAsciiCommandGuiInst().asciiSelectionIF.getWidth(),
				AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF
						.getHeight());
		asciiNonEditAreaIF.setVisible(true);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
				.add(asciiNonEditAreaIF);

	}
	
}
