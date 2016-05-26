package view;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Common.EventCodes;
import Common.JinternalFrameFunctions;
import Common.LogEntry;

public class DCInternalFramesCreation implements EventCodes{
	
	private final class SrtFocusLisImpl implements FocusListener {
		@Override
		public void focusLost(FocusEvent arg0) {

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			// if the user clicks the start row text field
			try {
				String startRow = dispContentsInst.startRowText.getText();
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

	private final class MvtFocusLisImp implements FocusListener {
		@Override
		public void focusLost(FocusEvent arg0) {
			
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		
			//If the user clicks on the missing value text field 
			try {
				String missingValue = dispContentsInst.missingValueText
						.getText();
				int replaceWith = Integer.parseInt(missingValue);
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.setMissingValue(replaceWith);

			}catch(NumberFormatException numberFormatException){
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

	DDViewLayer mlObj;
	DisplayContents dispContentsInst;
	public  DCInternalFramesCreation(){
		
	}
	public DCInternalFramesCreation(DisplayContents dispContInst){
		this.dispContentsInst = dispContInst;
		mlObj = DDViewLayer.createViewLayerInstance();
		createInternalFrames();
	}
	
	
	
	

	private  void createInternalFrames() {
		
		dispContentsInst.mainDesktopPane = new JDesktopPane();
		dispContentsInst.f.getContentPane().add(dispContentsInst.mainDesktopPane);
		dispContentsInst.filesFrame = new JInternalFrame("Files", false,false,false,false);
		dispContentsInst.filesFrame.setLocation(0,0);
		dispContentsInst.filesFrame.setSize(150,145);
		dispContentsInst.filesFrame.setBorder(mlObj.b);
		dispContentsInst.filesFrame.setBackground(Color.white);
		dispContentsInst.filesFrame.setVisible(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		dispContentsInst.filesFrame.getContentPane().setLayout(gridBagLayout);
		dispContentsInst.mainDesktopPane.add(dispContentsInst.filesFrame);
		
		
		
		JInternalFrame fileOptionsInternalFrame = new JInternalFrame("File Options", false,false,false,false);
		fileOptionsInternalFrame.setLocation(dispContentsInst.filesFrame.getX()+dispContentsInst.filesFrame.getWidth(), dispContentsInst.filesFrame.getY());
		
		//fileOptionsInternalFrame.setSize(getWidth()-filesFrame.getWidth(), filesFrame.getHeight());
		fileOptionsInternalFrame.setSize(550,150);
		fileOptionsInternalFrame.setBackground(Color.white);
		fileOptionsInternalFrame.setBorder(mlObj.b);
		fileOptionsInternalFrame.setVisible(true);
		fileOptionsInternalFrame.setLayout(gridBagLayout);
		
		dispContentsInst.hasHeaderRowCheck = new JCheckBox("Has Header Row");
		dispContentsInst.hasHeaderRowCheck.doClick();
		dispContentsInst.hasHeaderRowCheck.setActionCommand(HASHEADERROWCHECK);
		dispContentsInst.hasHeaderRowCheck.addActionListener(DDViewLayer.createViewLayerInstance());
	
		GridBagConstraints hasHeaderRowCheckCon = new GridBagConstraints();
		hasHeaderRowCheckCon.gridx = 0;
		hasHeaderRowCheckCon.gridy = 0;
		hasHeaderRowCheckCon.weighty = 0.5;
		hasHeaderRowCheckCon.weightx = 0.5;
		hasHeaderRowCheckCon.anchor = GridBagConstraints.LINE_START;
		dispContentsInst.hasHeaderRowCheck.setBackground(Color.white);
		
		fileOptionsInternalFrame.getContentPane().add(dispContentsInst.hasHeaderRowCheck,hasHeaderRowCheckCon);
		JLabel missingValueLable = new JLabel("Missing Value");
		GridBagConstraints missingVlaueLableCon = new GridBagConstraints();
		missingVlaueLableCon.gridx = 0;
		missingVlaueLableCon.gridy = 2;
		missingVlaueLableCon.weighty = 0.5;
		missingVlaueLableCon.weightx = 0.5;
		missingVlaueLableCon.anchor = GridBagConstraints.LINE_START;
		fileOptionsInternalFrame.getContentPane().add(missingValueLable,missingVlaueLableCon);
		//fileOptionsPanel.add(missingValueLable,missingVlaueLableCon);
		
		dispContentsInst.missingValueText = new JTextField("0");
	
		dispContentsInst.missingValueText.addFocusListener(new MvtFocusLisImp());
		dispContentsInst.missingValueText.setColumns(5);
		GridBagConstraints missingValueTextCon = new GridBagConstraints();
		missingValueTextCon.gridx = 1;
		missingValueTextCon.gridy = 2;
		missingValueTextCon.weighty = 0.5;
		missingValueTextCon.weightx = 0.5;
		missingValueTextCon.anchor = GridBagConstraints.LINE_START;
		fileOptionsInternalFrame.getContentPane().add(dispContentsInst.missingValueText,missingValueTextCon);
		
		JLabel startRowLable = new JLabel("Start Row");
		GridBagConstraints startRowLableCon = new GridBagConstraints();
		startRowLableCon.gridx = 0;
		startRowLableCon.gridy = 3;
		startRowLableCon.weightx = 0.5;
		startRowLableCon.weighty = 0.5;
		startRowLableCon.anchor = GridBagConstraints.LINE_START;
		fileOptionsInternalFrame.getContentPane().add(startRowLable,startRowLableCon);
		dispContentsInst.startRowText = new JTextField("1");
		dispContentsInst.startRowText.addFocusListener(new SrtFocusLisImpl());
		dispContentsInst.startRowText.setColumns(5);
		GridBagConstraints startRowTextCon = new GridBagConstraints();
		startRowTextCon.gridx = 1;
		startRowTextCon.gridy = 3;
		startRowTextCon.weightx = 0.5;
		startRowTextCon.weighty = 0.5;
		startRowTextCon.anchor = GridBagConstraints.LINE_START;
		fileOptionsInternalFrame.getContentPane().add(dispContentsInst.startRowText,startRowTextCon);
		dispContentsInst.mainDesktopPane.add(fileOptionsInternalFrame);
		
		
		dispContentsInst.sheetsFrame = new JInternalFrame("Sheets", false,false,false,false);
		dispContentsInst.sheetsFrame.setLocation(0, 151);
		dispContentsInst.sheetsFrame.setSize(150, 300);
		dispContentsInst.sheetsFrame.setVisible(true);
		dispContentsInst.sheetsFrame.setBorder(mlObj.b);
		dispContentsInst.sheetsFrame.setBackground(Color.white);
		//dispContentsInst.sheetsFrame.setLayout(gridBagLayout);
		dispContentsInst.mainDesktopPane.add(dispContentsInst.sheetsFrame);
		
		
		dispContentsInst.dataFrame = new JInternalFrame("Sheet Contents", false,false,false,false);
		dispContentsInst.dataFrame.setLocation(151,151);
		dispContentsInst.dataFrame.setSize(550, 300);
		dispContentsInst.dataFrame.setBorder(mlObj.b);
		dispContentsInst.dataFrame.setVisible(true);
		dispContentsInst.dataFrame.setBackground(Color.white);
		dispContentsInst.mainDesktopPane.add(dispContentsInst.dataFrame);
		
		JinternalFrameFunctions func = JinternalFrameFunctions.createIFFunctionsInstance();
		func.fix(dispContentsInst.mainDesktopPane);
		
		dispContentsInst.hasHeaderRowCheck.addActionListener(DDViewLayer.createViewLayerInstance());
		dispContentsInst.missingValueText.addActionListener(DDViewLayer.createViewLayerInstance());
		
}


}
