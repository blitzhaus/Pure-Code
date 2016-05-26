package view;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.BorderFactory;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.text.PlainDocument;



import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.JinternalFrameFunctions;
import Common.TextFieldIntegerFilter;


public class AsciiSelectionDispGUI {

	JInternalFrame asciiSelectionIF;

	public AsciiSelectionDispGUI() {

	}

	public static AsciiSelectionDispGUI ASCII_TA_DISP = null;

	public static AsciiSelectionDispGUI createAsciiCommandGuiInst()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (ASCII_TA_DISP == null) {
			ASCII_TA_DISP = new AsciiSelectionDispGUI();
		}
		return ASCII_TA_DISP;
	}

	public void asciiSelectionGuiCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {
		selectionComponentsCreation();
	}
	 
	private void selectionComponentsCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {

		commandsIFCreation();
		CmdDispInternalComponentsCreation();
	}

	private void CmdDispInternalComponentsCreation() {
		// internal gui components creation
		createnParam();
		createnSecParam();
		//createnCon();
		createnFunc();
		createnDer();
	}
	
	JTextField nDerTF;
	private void createnDer() {
		// TODO Auto-generated method stub
		JLabel lable = new JLabel("Number of Derivatives");
		GridBagConstraints lableCon = new GridBagConstraints();
		lableCon.gridx = 0;
		lableCon.gridy = 4;
		lableCon.weighty = 0.5;
		lableCon.anchor = GridBagConstraints.LINE_START;
		optionsPanel.add(lable, lableCon);
		nDerTF = new JTextField();
		nDerTF.getDocument().addDocumentListener(
				DDViewLayer.createViewLayerInstance());
		nDerTF.setColumns(5);
		GridBagConstraints textFieldCon = new GridBagConstraints();
		textFieldCon.gridx = 1;
		textFieldCon.gridy = 4;
		textFieldCon.weightx = 0.5;
		textFieldCon.weighty = 0.5;
		optionsPanel.add(nDerTF, textFieldCon);
	}

	JTextField nFuncTF;
	private void createnFunc() {
		// TODO Auto-generated method stub
		JLabel lable = new JLabel("Number of Functions");
		GridBagConstraints lableCon = new GridBagConstraints();
		lableCon.gridx = 0;
		lableCon.gridy = 3;
		lableCon.weighty = 0.5;
		lableCon.anchor = GridBagConstraints.LINE_START;
		optionsPanel.add(lable, lableCon);
		nFuncTF = new JTextField();
		nFuncTF.getDocument().addDocumentListener(
				DDViewLayer.createViewLayerInstance());
		nFuncTF.setColumns(5);
		GridBagConstraints textFieldCon = new GridBagConstraints();
		textFieldCon.gridx = 1;
		textFieldCon.gridy = 3;
		textFieldCon.weightx = 0.5;
		textFieldCon.weighty = 0.5;
		optionsPanel.add(nFuncTF, textFieldCon);
	}

	JTextField nConTF;
	private void createnCon() {
		// TODO Auto-generated method stub
		JLabel lable = new JLabel("Number of Constants");
		GridBagConstraints lableCon = new GridBagConstraints();
		lableCon.gridx = 0;
		lableCon.gridy = 2;
		lableCon.weighty = 0.5;
		lableCon.anchor = GridBagConstraints.LINE_START;
		optionsPanel.add(lable, lableCon);
		nConTF = new JTextField();
		nConTF.getDocument().addDocumentListener(
				DDViewLayer.createViewLayerInstance());
		nConTF.setColumns(5);
		GridBagConstraints textFieldCon = new GridBagConstraints();
		textFieldCon.gridx = 1;
		textFieldCon.gridy = 2;
		textFieldCon.weightx = 0.5;
		textFieldCon.weighty = 0.5;
		optionsPanel.add(nConTF, textFieldCon);
	}

	JTextField nSecParamTF;
	private void createnSecParam() {
		// TODO Auto-generated method stub
		JLabel lable = new JLabel("Number of Secondary Parameters");
		GridBagConstraints lableCon = new GridBagConstraints();
		lableCon.gridx = 0;
		lableCon.gridy = 1;
		lableCon.weighty = 0.5;
		lableCon.anchor = GridBagConstraints.LINE_START;
		optionsPanel.add(lable, lableCon);
		nSecParamTF = new JTextField();
		PlainDocument doc = (PlainDocument) nSecParamTF.getDocument();
		doc.setDocumentFilter(new TextFieldIntegerFilter());
		nSecParamTF.getDocument().addDocumentListener(
				DDViewLayer.createViewLayerInstance());
		nSecParamTF.setColumns(5);
		GridBagConstraints textFieldCon = new GridBagConstraints();
		textFieldCon.gridx = 1;
		textFieldCon.gridy = 1;
		textFieldCon.weightx = 0.5;
		textFieldCon.weighty = 0.5;
		optionsPanel.add(nSecParamTF, textFieldCon);
	}

	JTextField nParamTF;
	private void createnParam() {
		// TODO Auto-generated method stub
		JLabel lable = new JLabel("Number of Primary Parameters");
		GridBagConstraints lableCon = new GridBagConstraints();
		lableCon.gridx = 0;
		lableCon.gridy = 0;
		lableCon.weighty = 0.5;
		lableCon.anchor = GridBagConstraints.LINE_START;
		optionsPanel.add(lable, lableCon);
		nParamTF = new JTextField();
		PlainDocument doc = (PlainDocument) nParamTF.getDocument();
		doc.setDocumentFilter(new TextFieldIntegerFilter());
		nParamTF.getDocument().addDocumentListener(
				DDViewLayer.createViewLayerInstance());
		nParamTF.setColumns(5);
		GridBagConstraints textFieldCon = new GridBagConstraints();
		textFieldCon.gridx = 1;
		textFieldCon.gridy = 0;
		textFieldCon.weightx = 0.5;
		textFieldCon.weighty = 0.5;
		optionsPanel.add(nParamTF, textFieldCon);
		
		
	}

	JPanel optionsPanel;
	private void commandsIFCreation() throws RowsExceededException,
			WriteException, BiffException, IOException {
		asciiSelectionIF = new JInternalFrame("Selection", false, false, false,
				false);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(asciiSelectionIF);
		DDViewLayer.createMTInstance().mainTabbedFrame.setSize(DDViewLayer
				.createMTInstance().mainTabbedFrameOriginalWidth, DDViewLayer
				.createMTInstance().mainTabbedFrameoriginalHeight);
		int width = 0;
		int height = 0;
		width = (AsciiMainScreen.createAsciiMainScreenInstance().asciiMainInternalFrame
				.getWidth() - CaSetupAvailableCompCreator
				.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
				.getWidth()) / 2;
		height = CaSetupAvailableCompCreator.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
				.getHeight();
		asciiSelectionIF
				.setLocation(
						CaSetupAvailableCompCreator
								.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getX()
								+ CaSetupAvailableCompCreator
										.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
										.getWidth(),
						CaSetupAvailableCompCreator
								.createPdSetupAvailCompInst().setupAvailableComponentsInternalFrame
								.getY());
		asciiSelectionIF.setSize(width, height);
		asciiSelectionIF.setVisible(true);
		asciiSelectionIF.setBorder(DDViewLayer.createViewLayerInstance().b);
			CaTabbedPanesCreator.createPdTabbedPaneInstance().setupTabDesktopPane
					.add(asciiSelectionIF);

		// command options panel creation
		optionsPanel = new JPanel();
		optionsPanel.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.RAISED));
		optionsPanel.setLayout(new GridBagLayout());

		// commands panel scroll pane
		JScrollPane commandPanelSP = new JScrollPane(optionsPanel);
		commandPanelSP
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		commandPanelSP
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		commandPanelSP.setBorder(BorderFactory.createTitledBorder("Selection"));
		asciiSelectionIF.getContentPane().add(commandPanelSP);
		
	}
}