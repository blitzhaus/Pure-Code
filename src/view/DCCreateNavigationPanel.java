package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;

import Common.JinternalFrameFunctions;
import Common.EventCodes;

public class DCCreateNavigationPanel implements EventCodes{

	private JButton next;
	private JButton finishButton;
	private JButton finishAndOpen;
	private JButton cancelButton;
	DisplayContents dispContentsInst;
	
	public DCCreateNavigationPanel(DisplayContents dispContentsInst) {
		this.dispContentsInst = dispContentsInst;
		createNavigationPanel();
	}
	
	public DCCreateNavigationPanel(){
		
	}
	

	
	private void createNavigationPanel() {
		JinternalFrameFunctions func = JinternalFrameFunctions.createIFFunctionsInstance();
		DDViewLayer mlObj = DDViewLayer.createViewLayerInstance();
		
		JInternalFrame navigationPanelFrame = new JInternalFrame("asdfasdfas",false,false,false,false);
		func.removeTitleBarForinternalFrame(navigationPanelFrame);
		JPanel navigationPanel = new JPanel();
		navigationPanelFrame.setLocation(0, 451);
		navigationPanelFrame.setSize(700, 70);
		navigationPanelFrame.setBorder(mlObj.b);
		navigationPanelFrame.setBackground(Color.white);
		navigationPanelFrame.setVisible(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		navigationPanelFrame.getContentPane().setLayout(gridBagLayout);
/*		next = new JButton(">");
		next.setVisible(true);
		GridBagConstraints nextButtonCon = new GridBagConstraints();
		nextButtonCon.gridx = 0;
		nextButtonCon.gridy = 0;
		nextButtonCon.weighty = 0.5;
		nextButtonCon.weightx = 0.1;
		nextButtonCon.anchor = GridBagConstraints.LINE_END;
		navigationPanelFrame.getContentPane().add(next);
		*/
/*		finishButton = new JButton("Finish");
		GridBagConstraints finishButtonCon = new GridBagConstraints();
		finishButtonCon.gridx = 1;
		finishButtonCon.gridy = 0;
		finishButtonCon.weightx = 0.1;
		finishButtonCon.weighty = 0.5;
		finishButtonCon.anchor = GridBagConstraints.LINE_END;
		navigationPanelFrame.getContentPane().add(finishButton,finishButtonCon);*/
		
		finishAndOpen = new JButton("Finish & Open");
		
		GridBagConstraints finishAndopenCon = new GridBagConstraints();
		finishAndopenCon.gridx = 1;
		finishAndopenCon.gridy = 0;
		finishAndopenCon.weightx = 0.5;
		finishAndopenCon.weighty = 0.5;
		finishAndopenCon.anchor = GridBagConstraints.LINE_END;
		navigationPanelFrame.getContentPane().add(finishAndOpen,finishAndopenCon);
		
		cancelButton = new JButton("Cancel");
		
		GridBagConstraints cancelButtonCon = new GridBagConstraints();
		cancelButtonCon.gridx = 2;
		cancelButtonCon.gridy = 0;
		cancelButtonCon.weightx = 0.1;
		cancelButtonCon.weighty = 0.5;
		cancelButtonCon.anchor = GridBagConstraints.LINE_END;
		navigationPanelFrame.getContentPane().add(cancelButton,cancelButtonCon);
		
		finishAndOpen.addActionListener(DDViewLayer.createViewLayerInstance());
		finishAndOpen.setActionCommand(FINISHANDOPEN);
		cancelButton.addActionListener(DDViewLayer.createViewLayerInstance());
		cancelButton.setActionCommand(IMPCANCEL);
		dispContentsInst.mainDesktopPane.add(navigationPanelFrame);
		
	}

}