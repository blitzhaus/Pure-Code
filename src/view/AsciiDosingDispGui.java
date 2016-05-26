package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import Common.JinternalFrameFunctions;
import Common.TextFieldIntegerFilter;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AsciiDosingDispGui {

	int DEFAULTROWS = 1;
	int DEFAULTCOLUMNS = 5;	
	public static AsciiDosingDispGui ASCIIDOSEDISPGUI = null;
	public static AsciiDosingDispGui createAsciiDoseDispGui() throws RowsExceededException, WriteException, BiffException, IOException{
		if(ASCIIDOSEDISPGUI == null){
			ASCIIDOSEDISPGUI = new AsciiDosingDispGui();
		}
		return ASCIIDOSEDISPGUI;
	}
	
	public AsciiDosingDispGui() throws RowsExceededException, WriteException, BiffException, IOException{
		createContainers();
		createDoseTable(DEFAULTROWS, DEFAULTCOLUMNS);
		createProfList();
	}

	JList doseProfilesList;
	DefaultListModel doseProfListModel;
	JTextField nConTF;
	private void createProfList() {
		doseProfListModel = new DefaultListModel();
		doseProfilesList = new JList(doseProfListModel);
		doseProfilesList.addListSelectionListener(DDViewLayer.createViewLayerInstance());
		JScrollPane doseProfSP =  new JScrollPane(doseProfilesList);
		doseProfSP.setPreferredSize(new Dimension(asciiProfIF.getWidth()/2, asciiProfIF.getHeight()/2));
		doseProfSP.setBorder(BorderFactory.createTitledBorder("Profiles"));
		doseProfSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		doseProfSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel doseProfPanel = new JPanel();
		doseProfPanel.add(doseProfSP);
		asciiProfIF.getContentPane().add(doseProfPanel);
		
		nConTF = new JTextField();
		PlainDocument doc = (PlainDocument) nConTF.getDocument();
	//	doc.setDocumentFilter(new TextFieldIntegerFilter());
		nConTF.getDocument().addDocumentListener(DDViewLayer.createViewLayerInstance());
		nConTF.setColumns(5);
		
		JPanel nDosePanel = new JPanel();
		nDosePanel.add(nConTF);
		nDosePanel.setBorder(BorderFactory.createTitledBorder("nCOn"));
		asciiProfIF.add(nDosePanel);
		
	}

	protected JTable asciiDoseTable;
	protected JScrollPane asciiDoseSP;
	void createDoseTable(int rows, int columns) {

		asciiDoseTable = new JTable(rows, columns);
		asciiDoseTable.getModel().addTableModelListener(DDViewLayer.createViewLayerInstance()); 
		asciiDoseSP = new JScrollPane(asciiDoseTable);
		asciiDoseSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		asciiDoseSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		asciiDoseIF.getContentPane().add(asciiDoseSP);
	}

	protected JInternalFrame asciiDoseIF;
	private void createContainers() throws RowsExceededException, WriteException, BiffException, IOException {
		createTableIF();
		createProfilesIF();
	}

	protected JInternalFrame asciiProfIF;
	private void createProfilesIF() throws RowsExceededException, WriteException, BiffException, IOException {
		asciiProfIF = new JInternalFrame("Profiles IF", false,false,false,false);
		asciiProfIF.setLocation(asciiDoseIF.getX()+asciiDoseIF.getWidth(), asciiDoseIF.getY());
		asciiProfIF.setSize(asciiDoseIF.getWidth(), asciiDoseIF.getHeight());
		asciiProfIF.setLayout(new FlowLayout());
		asciiProfIF.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(asciiProfIF);
		asciiProfIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
		.add(asciiProfIF);
		
	}

	private void createTableIF() throws RowsExceededException, WriteException, BiffException, IOException {
		asciiDoseIF = new JInternalFrame("", false,false,false,false);
	JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(asciiDoseIF);
	asciiDoseIF.setBorder(DDViewLayer.createViewLayerInstance().b);
	asciiDoseIF.setLocation(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getX(),
	AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getY());
	asciiDoseIF.setSize(AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getWidth(),
	AsciiSelectionDispGUI.createAsciiCommandGuiInst().asciiSelectionIF.getHeight());
	asciiDoseIF.setVisible(true);
	CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
	.add(asciiDoseIF);
	}
}