package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JInternalFrame.JDesktopIcon;



import Common.EventCodes;
import Common.JinternalFrameFunctions;


final class SrtFocusLisImpl implements FocusListener {
	@Override
	public void focusLost(FocusEvent arg0) {

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		// if the user clicks the start row text field
		try {
			String startRow = NewSheetCreation.createNewSheetInst().startRowText.getText();
			int startWith = Integer.parseInt(startRow);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex()).setStartRow(
							startWith);
		} catch (NumberFormatException numberFormatException) {
			String[] error = new String[2];
			error[0] = "Not a number";
			error[1] = numberFormatException.getMessage();
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}


public class NewSheetCreation extends JFrame implements EventCodes{

	public static NewSheetCreation NEW_SHEET = null;
	public static NewSheetCreation createNewSheetInst() {
		if(NEW_SHEET == null){
			NEW_SHEET = new NewSheetCreation("just object creation");
		} 
		
		return NEW_SHEET;
	}
	
	public NewSheetCreation(String dummy){
		
	}
	
	JDesktopPane desktopPane;
	JCheckBox hasHeaderRowCheck;
	JTextField missingValueText;
	JTextField startRowText;
	JInternalFrame sheetOptionsIF;
	JInternalFrame tableFrame;
	JTable table;
	JComboBox rowsCombo;
	JComboBox columnsCombo;
	boolean whileGuiCreation;
	
	void createNewSheet() {
		setTitle("New Input Sheet");
		setLocationRelativeTo(null);
		setSize((int) (DDViewLayer.createViewLayerInstance().screenSize
				.getWidth() / 2), DDViewLayer.createViewLayerInstance()
				.getHeight() / 2);
		setVisible(true);
		desktopPane = new JDesktopPane();
		setContentPane(desktopPane);
		whileGuiCreation = true;
		createOptinsFrame();
		createModificationsFrame();
		createrTableFrame();
		createNavigationFrame();
		whileGuiCreation = false;
		boolean internalWBNodeExists;
	}
	private void createNavigationFrame() {
		JInternalFrame navigationFrame = new JInternalFrame("",false,false,false,false);
		navigationFrame.setLocation(tableFrame.getX(), tableFrame.getY()+ tableFrame.getHeight());
		navigationFrame.setSize(getWidth(), 50);
		navigationFrame.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(navigationFrame);
		desktopPane.add(navigationFrame);
		navigationFrame.moveToFront();
		navigationFrame.setLayout(new FlowLayout());
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(DDViewLayer.createViewLayerInstance());
		okButton.setActionCommand(NEWSHEETOK);
		okButton.setPreferredSize(new Dimension(70, 30));
		navigationFrame.getContentPane().add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(DDViewLayer.createViewLayerInstance());
		cancelButton.setActionCommand(NEWSHEETCANCEL);
		cancelButton.setPreferredSize(new Dimension(70, 30));
		navigationFrame.getContentPane().add(cancelButton);
	}

	private void createrTableFrame() {
		tableFrame = new JInternalFrame("", false,false,false,false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(tableFrame);
		tableFrame.setLocation(0, sheetOptionsIF.getY() + sheetOptionsIF.getHeight());
		tableFrame.setSize(getWidth(), getHeight()/2);
		tableFrame.setVisible(true);
		tableFrame.moveToFront();
		desktopPane.add(tableFrame);
		desktopPane.setBackground(Color.WHITE);
		
		table = new JTable(5,5);
		for(int i=0;i<table.getRowCount();i++){
			for(int j=0;j<table.getColumnCount();j++){
				table.setValueAt("", i, j);
			}
		}
		
		JScrollPane tableSP = new JScrollPane(table);
		
		tableFrame.getContentPane().add(tableSP);
		table.setCellSelectionEnabled(true);
		new ReorderableJList(table);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setColumnSelectionAllowed(true);
		
		
	}

	private void createModificationsFrame() {
		
		JInternalFrame modificationsFrame = new JInternalFrame("",false,false,false,false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(modificationsFrame);
		modificationsFrame.setLocation(sheetOptionsIF.getX() + sheetOptionsIF.getWidth(), sheetOptionsIF.getY());
		modificationsFrame.setSize(sheetOptionsIF.getWidth(), sheetOptionsIF.getHeight());
		modificationsFrame.setVisible(true);
		modificationsFrame.moveToFront();
		desktopPane.add(modificationsFrame);
		modificationsFrame.setLayout(new GridBagLayout());
		JLabel rows = new JLabel("Rows");
		GridBagConstraints rowsCon = new GridBagConstraints();
		rowsCon.gridx = 0;
		rowsCon.gridy = 0;
		rowsCon.weightx = 0.5;
		rowsCon.anchor = GridBagConstraints.LINE_START;
		modificationsFrame.getContentPane().add(rows, rowsCon);
		
		rowsCombo = new JComboBox();
		rowsCombo.addActionListener(DDViewLayer.createViewLayerInstance());
		rowsCombo.setActionCommand(NUMBEROFROWS);
		rowsCombo.setPreferredSize(new Dimension(50, 20));
		GridBagConstraints rowsComboCon = new GridBagConstraints();
		rowsComboCon.gridx = 1;
		rowsComboCon.gridy = 0;
		rowsComboCon.weightx = 0.5;
		modificationsFrame.getContentPane().add(rowsCombo,rowsComboCon);
		
		for(int i=1;i<=200;i++){
			rowsCombo.addItem(i);
		}
		rowsCombo.setSelectedIndex(4);
		
		JButton addRow = new JButton("Add Row");
		addRow.setActionCommand(ADDROW);
		addRow.addActionListener(DDViewLayer.createViewLayerInstance());
		addRow.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints addRowCon = new GridBagConstraints();
		addRowCon.gridx = 2;
		addRowCon.gridy = 0;
		addRowCon.weightx = 0.5;
		modificationsFrame.getContentPane().add(addRow,addRowCon);
		
		JLabel columns = new JLabel("Columns");
		GridBagConstraints columnsCon = new GridBagConstraints();
		columnsCon.gridx = 0;
		columnsCon.gridy = 1;
		columnsCon.weightx = 0.5;
		columnsCon.anchor = GridBagConstraints.LINE_START;
		modificationsFrame.getContentPane().add(columns, columnsCon);
		
		
		columnsCombo = new JComboBox();
		columnsCombo.setActionCommand(NUMBEROFCOLUMNS);
		columnsCombo.addActionListener(DDViewLayer.createViewLayerInstance());
		columnsCombo.setPreferredSize(new Dimension(50, 20));
		GridBagConstraints columnsComboCon = new GridBagConstraints();
		columnsComboCon.gridx = 1;
		columnsComboCon.gridy = 1;
		columnsComboCon.weightx = 0.5;
		modificationsFrame.getContentPane().add(columnsCombo,columnsComboCon);
		
		for(int i=1;i<=30;i++){
			columnsCombo.addItem(i);
		}
		columnsCombo.setSelectedIndex(4);
		
		JButton addColumn = new JButton("Add Column");
		addColumn.setActionCommand(ADDCOLUMN);
		addColumn.addActionListener(DDViewLayer.createViewLayerInstance());
		addColumn.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints addColCon = new GridBagConstraints();
		addColCon.gridx = 2;
		addColCon.gridy = 1;
		addColCon.weightx = 0.5;
		modificationsFrame.getContentPane().add(addColumn,addColCon);
		
		
	}

	private void createOptinsFrame() {
		sheetOptionsIF = new JInternalFrame("", false,false,false,false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(sheetOptionsIF);
		sheetOptionsIF.setLocation(0, 0);
		sheetOptionsIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		sheetOptionsIF.setSize(getWidth()/2, getHeight()/4);
		sheetOptionsIF.setVisible(true);
		desktopPane.add(sheetOptionsIF);
		sheetOptionsIF.moveToFront();
		
		sheetOptionsIF.setLayout(new GridBagLayout());
		
		hasHeaderRowCheck = new JCheckBox("Has Header Row");
		hasHeaderRowCheck.doClick();
		hasHeaderRowCheck.setActionCommand(HASHEADERROWCHECK);
		hasHeaderRowCheck.addActionListener(DDViewLayer.createViewLayerInstance());
	
		GridBagConstraints hasHeaderRowCheckCon = new GridBagConstraints();
		hasHeaderRowCheckCon.gridx = 0;
		hasHeaderRowCheckCon.gridy = 0;
		hasHeaderRowCheckCon.weighty = 0.5;
		hasHeaderRowCheckCon.weightx = 0.5;
		hasHeaderRowCheckCon.anchor = GridBagConstraints.LINE_START;
		
		
		sheetOptionsIF.getContentPane().add(hasHeaderRowCheck,hasHeaderRowCheckCon);
		JLabel missingValueLable = new JLabel("Missing Value");
		GridBagConstraints missingVlaueLableCon = new GridBagConstraints();
		missingVlaueLableCon.gridx = 0;
		missingVlaueLableCon.gridy = 2;
		missingVlaueLableCon.weighty = 0.5;
		missingVlaueLableCon.weightx = 0.5;
		missingVlaueLableCon.anchor = GridBagConstraints.LINE_START;
		sheetOptionsIF.getContentPane().add(missingValueLable,missingVlaueLableCon);
		//fileOptionsPanel.add(missingValueLable,missingVlaueLableCon);
		
		missingValueText = new JTextField("0");
	
		//missingValueText.addFocusListener(new MvtFocusLisImp());
		missingValueText.setColumns(5);
		GridBagConstraints missingValueTextCon = new GridBagConstraints();
		missingValueTextCon.gridx = 1;
		missingValueTextCon.gridy = 2;
		missingValueTextCon.weighty = 0.5;
		missingValueTextCon.weightx = 0.5;
		missingValueTextCon.anchor = GridBagConstraints.LINE_START;
		sheetOptionsIF.getContentPane().add(missingValueText,missingValueTextCon);
		
		JLabel startRowLable = new JLabel("Start Row");
		GridBagConstraints startRowLableCon = new GridBagConstraints();
		startRowLableCon.gridx = 0;
		startRowLableCon.gridy = 3;
		startRowLableCon.weightx = 0.5;
		startRowLableCon.weighty = 0.5;
		startRowLableCon.anchor = GridBagConstraints.LINE_START;
		sheetOptionsIF.getContentPane().add(startRowLable,startRowLableCon);
		startRowText = new JTextField("1");
		//startRowText.addFocusListener(new SrtFocusLisImpl());
		startRowText.setColumns(5);
		GridBagConstraints startRowTextCon = new GridBagConstraints();
		startRowTextCon.gridx = 1;
		startRowTextCon.gridy = 3;
		startRowTextCon.weightx = 0.5;
		startRowTextCon.weighty = 0.5;
		startRowTextCon.anchor = GridBagConstraints.LINE_START;
		sheetOptionsIF.getContentPane().add(startRowText,startRowTextCon);
		
		
	}
	
	
	

}
