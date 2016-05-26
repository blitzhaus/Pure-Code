package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTextField;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class InVitroMappingPanelCreator extends JPanel{

	private JTextField textField;
	InVitroMapLeftIFCreator mapLeftIF;
	InVitroMapRightIFCreator mapRightIF;
	

	
	public InVitroMappingPanelCreator() throws RowsExceededException, WriteException, BiffException, IOException {
		createMappingPanel();
		
	}
	
	public void createMappingPanel() throws RowsExceededException, WriteException, BiffException, IOException
	{

		//setBounds(100, 100, 450, 300);
		
		GridBagLayout gbl_srtVarPanel = new GridBagLayout();
		gbl_srtVarPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_srtVarPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_srtVarPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_srtVarPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_srtVarPanel);
		
	
		
		
		 mapLeftIF = new InVitroMapLeftIFCreator();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 4;
		gbc_textField.ipady = 100;
		gbc_textField.anchor = GridBagConstraints.WEST;
		add(mapLeftIF, gbc_textField);
	
		
		mapRightIF = new InVitroMapRightIFCreator();
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.insets = new Insets(0, 0, 0, 5);
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 5;
		gbc_list_1.gridy = 4;
		gbc_list_1.ipady = 100;
		add(mapRightIF, gbc_list_1);
		

	
	}
	
	

}
