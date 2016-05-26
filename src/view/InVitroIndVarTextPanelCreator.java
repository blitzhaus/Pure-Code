package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.EventCodes;

public class InVitroIndVarTextPanelCreator extends JPanel {

	String grpLabel = null;
	
	
	public String getGrpLabel() {
		return grpLabel;
	}

	public void setGrpLabel(String grpLabel) {
		this.grpLabel = grpLabel;
	}

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws BiffException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	InVitroMappingDispGuiCreator mappingInst;
	
	public InVitroIndVarTextPanelCreator(String grpLabel) throws RowsExceededException, WriteException, BiffException, IOException {
		createIndpVarTextPanel(grpLabel);
	}
	
	public void createIndpVarTextPanel(String grpLabel) throws RowsExceededException, WriteException, BiffException, IOException
	{

		setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), grpLabel, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new LineBorder(null)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		mappingInst = InVitroMappingDispGuiCreator.createMappingInstance(); 

		Icon icon = new ImageIcon("rightglossy.jpg");
		mappingInst.xAndAvailableButton = new JButton(icon);
		
		mappingInst.xAndAvailableButton.addActionListener(DDViewLayer.createViewLayerInstance());
		mappingInst.xAndAvailableButton.setActionCommand(EventCodes.AVAILANDXCA);
						
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 5;
		gbc_button.anchor = GridBagConstraints.CENTER;
		add(mappingInst.xAndAvailableButton, gbc_button);
		
		mappingInst.xVariableTextField = new JTextField();
		mappingInst.xVariableTextField.setEditable(false); 
		mappingInst.xVariableTextField.addFocusListener(DDViewLayer.createViewLayerInstance());
		mappingInst.xVariableTextField.setColumns(50);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.HORIZONTAL;
		gbc_list.gridx = 6;
		gbc_list.gridy = 5;
		gbc_list.anchor = GridBagConstraints.CENTER;
		add(mappingInst.xVariableTextField, gbc_list);

	
	}
	
	
}
