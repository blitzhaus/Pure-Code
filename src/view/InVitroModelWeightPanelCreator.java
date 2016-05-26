package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Common.EventCodes;

public class InVitroModelWeightPanelCreator extends JPanel {

	

	Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	Font componentOptionseFontForComboBox = new Font("Arial", Font.PLAIN, 11);
	Font componentLablesFont = new Font("Arial", Font.BOLD, 11);

	public InVitroModelWeightPanelCreator() throws RowsExceededException,
			WriteException, BiffException, IOException {
		createWeightDosePanel();
	}

	public void createWeightDosePanel() throws RowsExceededException,
			WriteException, BiffException, IOException {

		// setBounds(100, 100, 450, 300);

		// setSize(450,400);
		InVitroModelWeightDispGuiCreator modelWeightDoseInst = InVitroModelWeightDispGuiCreator
				.createInVitroModelWeightInstance();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Dissolution Model",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		modelWeightDoseInst.hillModelRadioButton = new JRadioButton(
				"Hill");
		GridBagConstraints gbc_rdbtnAlgModel = new GridBagConstraints();
		gbc_rdbtnAlgModel.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAlgModel.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAlgModel.gridx = 0;
		gbc_rdbtnAlgModel.gridy = 0;
		panel_1.add(modelWeightDoseInst.hillModelRadioButton,
				gbc_rdbtnAlgModel);
		
		modelWeightDoseInst.hillModelRadioButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		modelWeightDoseInst.hillModelRadioButton
				.setActionCommand(EventCodes.HILL);

		

		modelWeightDoseInst.weibulModelRadioButton = new JRadioButton(
				"Weibul");
		GridBagConstraints gbc_rdbtnDiffEqnModel = new GridBagConstraints();
		gbc_rdbtnDiffEqnModel.anchor = GridBagConstraints.WEST;
		gbc_rdbtnDiffEqnModel.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnDiffEqnModel.gridx = 0;
		gbc_rdbtnDiffEqnModel.gridy = 1;
		panel_1.add(modelWeightDoseInst.weibulModelRadioButton,
				gbc_rdbtnDiffEqnModel);
		
		modelWeightDoseInst.weibulModelRadioButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		modelWeightDoseInst.weibulModelRadioButton
				.setActionCommand(EventCodes.WEIBUL);

		modelWeightDoseInst.doubleWeibulModelRadioButton = new JRadioButton("Double Weibul");
		GridBagConstraints gbc_rdbtnAsciiModel = new GridBagConstraints();
		gbc_rdbtnAsciiModel.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAsciiModel.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnAsciiModel.gridx = 0;
		gbc_rdbtnAsciiModel.gridy = 2;
		panel_1.add(modelWeightDoseInst.doubleWeibulModelRadioButton, gbc_rdbtnAsciiModel);
		
		modelWeightDoseInst.doubleWeibulModelRadioButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		modelWeightDoseInst.doubleWeibulModelRadioButton
				.setActionCommand(EventCodes.DOUBLEWEIBUL);

		
		modelWeightDoseInst.makoiBanakarModelRadioButton = new JRadioButton("Makoid-Banakar");
		GridBagConstraints makoiBanakar = new GridBagConstraints();
		gbc_rdbtnAsciiModel.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAsciiModel.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnAsciiModel.gridx = 0;
		gbc_rdbtnAsciiModel.gridy = 3;
		panel_1.add(modelWeightDoseInst.makoiBanakarModelRadioButton, makoiBanakar);

		modelWeightDoseInst.makoiBanakarModelRadioButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		modelWeightDoseInst.makoiBanakarModelRadioButton
				.setActionCommand(EventCodes.MAKOID);
		

		ButtonGroup bg = new ButtonGroup();
		bg.add(modelWeightDoseInst.makoiBanakarModelRadioButton);
		bg.add(modelWeightDoseInst.hillModelRadioButton);
		bg.add(modelWeightDoseInst.weibulModelRadioButton);
		bg.add(modelWeightDoseInst.doubleWeibulModelRadioButton);
		
		
		
	
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Weighting",
				TitledBorder.LEADING, TitledBorder.TOP, componentLablesFont,
				null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		gbc_panel_2.ipadx = 40;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		modelWeightDoseInst.weightingLabel = new JLabel(
				"Weighting");
		
		modelWeightDoseInst.weightingLabel
				.setFont(componentLablesFont);
		
		GridBagConstraints gbc_rdbtnObserved = new GridBagConstraints();
		gbc_rdbtnObserved.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnObserved.gridx = 0;
		gbc_rdbtnObserved.gridy = 0;
		panel_2.add(modelWeightDoseInst.weightingLabel,
				gbc_rdbtnObserved);

		modelWeightDoseInst.weightingOptionsComboBox = new JComboBox();
		modelWeightDoseInst.weightingOptionsComboBox
				.setFont(componentOptionseFontForComboBox);
		modelWeightDoseInst.weightingOptionsComboBox.addActionListener(DDViewLayer
				.createViewLayerInstance());
		modelWeightDoseInst.weightingOptionsComboBox
				.setActionCommand(EventCodes.INVITROWEIGHT);
		modelWeightDoseInst.weightingOptionsComboBox
				.setModel(new DefaultComboBoxModel(new String[] { "Uniform",
						"1/Y", "1/Y*Y", "1/Y*Y*Y" }));

	
		GridBagConstraints gbc_cbObserved = new GridBagConstraints();
		
		gbc_cbObserved.gridx = 1;
		gbc_cbObserved.gridy = 0;
		gbc_cbObserved.ipadx = 45;
		panel_2.add(modelWeightDoseInst.weightingOptionsComboBox, gbc_cbObserved);

	

	}


}
