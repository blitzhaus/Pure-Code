package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.io.IOException;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

import Common.EventCodes;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class WeightDosePanelCreator extends JPanel {

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 * @throws BiffException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	/*
	 * public static void main(String[] args) throws RowsExceededException,
	 * WriteException, BiffException, IOException { JFrame frame = new JFrame();
	 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * 
	 * JDesktopPane desktop = new JDesktopPane(); ModelOptionsWeightingDosePanel
	 * internalFrame = new ModelOptionsWeightingDosePanel();
	 * internalFrame.setSize(750,300); desktop.add(internalFrame);
	 * 
	 * internalFrame.setVisible(true);
	 * 
	 * frame.add(desktop, BorderLayout.CENTER); frame.setSize(750, 300);
	 * frame.setVisible(true); }
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws BiffException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */

	Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	Font componentOptionseFontForComboBox = new Font("Arial", Font.PLAIN, 11);
	Font componentLablesFont = new Font("Arial", Font.BOLD, 11);

	public WeightDosePanelCreator() throws RowsExceededException,
			WriteException, BiffException, IOException {
		createWeightDosePanel();
	}

	public void createWeightDosePanel() throws RowsExceededException,
			WriteException, BiffException, IOException {

		// setBounds(100, 100, 450, 300);

		// setSize(450,400);
		CaWeightDoseDispGuiCreator weightDoseInst = CaWeightDoseDispGuiCreator
				.createCaWeightDoseInstance();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Library Model",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		// add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		weightDoseInst.algebraicEquationModelRadioButton = new JRadioButton(
				"Alg Model");
		GridBagConstraints gbc_rdbtnAlgModel = new GridBagConstraints();
		gbc_rdbtnAlgModel.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAlgModel.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAlgModel.gridx = 0;
		gbc_rdbtnAlgModel.gridy = 0;
		panel_1.add(weightDoseInst.algebraicEquationModelRadioButton,
				gbc_rdbtnAlgModel);

		weightDoseInst.availableLibraryModelComboBox = new JComboBox();
		weightDoseInst.availableLibraryModelComboBox.setMaximumRowCount(16);
		weightDoseInst.availableLibraryModelComboBox
				.setModel(new DefaultComboBoxModel(
						new String[] { "1:1-comp-IV-bolus" }));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.ipadx = 60;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		// gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 0;
		panel_1.add(weightDoseInst.availableLibraryModelComboBox, gbc_comboBox);

		weightDoseInst.differentialEquationModelRadioButton = new JRadioButton(
				"Diff Eqn Model");
		GridBagConstraints gbc_rdbtnDiffEqnModel = new GridBagConstraints();
		gbc_rdbtnDiffEqnModel.anchor = GridBagConstraints.WEST;
		gbc_rdbtnDiffEqnModel.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnDiffEqnModel.gridx = 0;
		gbc_rdbtnDiffEqnModel.gridy = 1;
		panel_1.add(weightDoseInst.differentialEquationModelRadioButton,
				gbc_rdbtnDiffEqnModel);

		weightDoseInst.asciiModelRadioButton = new JRadioButton("Ascii Model");
		GridBagConstraints gbc_rdbtnAsciiModel = new GridBagConstraints();
		gbc_rdbtnAsciiModel.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAsciiModel.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnAsciiModel.gridx = 0;
		gbc_rdbtnAsciiModel.gridy = 2;
		panel_1.add(weightDoseInst.asciiModelRadioButton, gbc_rdbtnAsciiModel);

		weightDoseInst.availableDifferentialEquationModelComboBox = new JComboBox();
		weightDoseInst.availableDifferentialEquationModelComboBox
				.setModel(new DefaultComboBoxModel(
						new String[] { "1:1-Comp-IV-Bolus-Rate-Constant" }));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.ipadx = 50;
		// gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 1;
		// panel_1.add(weightDoseInst.availableDifferentialEquationModelComboBox,
		// gbc_comboBox_1);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Data Type",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 0;
		gbc_panel.ipadx = 80;
		// add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		weightDoseInst.dataTypeLabel = new JLabel("Data Type");
		weightDoseInst.dataTypeLabel
				.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_lblDataType = new GridBagConstraints();
		gbc_lblDataType.insets = new Insets(0, 0, 0, 5);
		gbc_lblDataType.anchor = GridBagConstraints.EAST;
		gbc_lblDataType.gridx = 0;
		gbc_lblDataType.gridy = 0;
		panel.add(weightDoseInst.dataTypeLabel, gbc_lblDataType);

		weightDoseInst.dataTypeComboBoxForCA = new JComboBox();
		weightDoseInst.dataTypeComboBoxForCA.setModel(new DefaultComboBoxModel(
				new String[] { "Plasma Data" }));
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 1;
		gbc_comboBox_2.gridy = 0;
		panel.add(weightDoseInst.dataTypeComboBoxForCA, gbc_comboBox_2);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Weighting",
				TitledBorder.LEADING, TitledBorder.TOP, componentLablesFont,
				null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		gbc_panel_2.ipadx = 40;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		weightDoseInst.observedWeightingRadioButton = new JRadioButton(
				"observed");
		//weightDoseInst.observedWeightingRadioButton.setEnabled(false);
		weightDoseInst.observedWeightingRadioButton
				.setFont(componentLablesFont);
		weightDoseInst.observedWeightingRadioButton.addActionListener(DDViewLayer
				.createViewLayerInstance());
		weightDoseInst.observedWeightingRadioButton
				.setActionCommand(EventCodes.OWRB);
		GridBagConstraints gbc_rdbtnObserved = new GridBagConstraints();
		gbc_rdbtnObserved.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnObserved.gridx = 0;
		gbc_rdbtnObserved.gridy = 0;
		panel_2.add(weightDoseInst.observedWeightingRadioButton,
				gbc_rdbtnObserved);

		weightDoseInst.weightingOptionsComboBox = new JComboBox();
		weightDoseInst.weightingOptionsComboBox
				.setFont(componentOptionseFontForComboBox);
		weightDoseInst.weightingOptionsComboBox.addActionListener(DDViewLayer
				.createViewLayerInstance());
		weightDoseInst.weightingOptionsComboBox
				.setActionCommand(EventCodes.OWCB);
		weightDoseInst.weightingOptionsComboBox
				.setModel(new DefaultComboBoxModel(new String[] { "Uniform",
						"1/Y", "1/Y*Y", "1/Y*Y*Y" }));

	//	weightDoseInst.weightingOptionsComboBox.setEnabled(false);
		GridBagConstraints gbc_cbObserved = new GridBagConstraints();
		/*
		 * gbc_cbObserved.insets = new Insets(0, 0, 5, 0); gbc_cbObserved.fill =
		 * GridBagConstraints.HORIZONTAL;
		 */
		gbc_cbObserved.gridx = 1;
		gbc_cbObserved.gridy = 0;
		gbc_cbObserved.ipadx = 45;
		panel_2.add(weightDoseInst.weightingOptionsComboBox, gbc_cbObserved);

		weightDoseInst.predictedWeightingRadioButton = new JRadioButton(
				"Predicted");
		//weightDoseInst.predictedWeightingRadioButton.setEnabled(false);

		weightDoseInst.predictedWeightingRadioButton
				.setFont(componentLablesFont);
		weightDoseInst.predictedWeightingRadioButton
				.addActionListener(DDViewLayer.createViewLayerInstance());
		weightDoseInst.predictedWeightingRadioButton
				.setActionCommand(EventCodes.PWRB);
		GridBagConstraints gbc_rdbtnPredicted = new GridBagConstraints();
		gbc_rdbtnPredicted.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnPredicted.gridx = 0;
		gbc_rdbtnPredicted.gridy = 2;
		panel_2.add(weightDoseInst.predictedWeightingRadioButton,
				gbc_rdbtnPredicted);

		weightDoseInst.predictedWeightingOptionsComboBox = new JComboBox();
		//weightDoseInst.predictedWeightingOptionsComboBox.setEnabled(false);

		weightDoseInst.predictedWeightingOptionsComboBox
				.setFont(componentOptionseFontForComboBox);
		weightDoseInst.predictedWeightingOptionsComboBox
				.addActionListener(DDViewLayer.createViewLayerInstance());
		weightDoseInst.predictedWeightingOptionsComboBox
				.setActionCommand(EventCodes.PWCB);
		weightDoseInst.predictedWeightingOptionsComboBox
				.setModel(new DefaultComboBoxModel(new String[] { "Uniform",
						"1/Yhat", "1/Yhat*Yhat", "1/Yhat*Yhat*Yhat" }));
		GridBagConstraints gbc_cbPredicted = new GridBagConstraints();
		// gbc_cbPredicted.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbPredicted.gridx = 1;
		gbc_cbPredicted.gridy = 2;
		panel_2.add(weightDoseInst.predictedWeightingOptionsComboBox,
				gbc_cbPredicted);
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String analysisType = appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL()
				.get(
						appInfo
								.getProjectInfoAL()
								.get(appInfo.getSelectedProjectIndex())
								.getWorkBooksArrayList()
								.get(
										appInfo
												.getProjectInfoAL()
												.get(
														appInfo
																.getSelectedProjectIndex())
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getAnalysisType();

		if (analysisType.equals("pd")) {
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null, "Response Unit",
					TitledBorder.LEADING, TitledBorder.TOP,
					componentLablesFont, null));
			GridBagConstraints gbc_panel_3 = new GridBagConstraints();
			gbc_panel_3.gridwidth = 4;
			gbc_panel_3.insets = new Insets(0, 0, 5, 5);
			gbc_panel_3.fill = GridBagConstraints.BOTH;
			gbc_panel_3.gridx = 1;
			gbc_panel_3.gridy = 1;
			add(panel_3, gbc_panel_3);
			GridBagLayout gbl_panel_3 = new GridBagLayout();
			gbl_panel_3.columnWidths = new int[] { 0, 0, 0 };
			gbl_panel_3.rowHeights = new int[] { 0, 0, 0 };
			gbl_panel_3.columnWeights = new double[] { 0.0, 1.0,
					Double.MIN_VALUE };
			gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
			panel_3.setLayout(gbl_panel_3);

			weightDoseInst.responseUnitLabel = new JLabel("Response");
			weightDoseInst.responseUnitLabel.setFont(componentLablesFont);
			GridBagConstraints gbc_lblResponse = new GridBagConstraints();
			gbc_lblResponse.insets = new Insets(0, 0, 5, 5);
			gbc_lblResponse.anchor = GridBagConstraints.WEST;
			gbc_lblResponse.gridx = 0;
			gbc_lblResponse.gridy = 0;
			gbc_lblResponse.ipadx = 23;
			panel_3.add(weightDoseInst.responseUnitLabel, gbc_lblResponse);

			weightDoseInst.responseUnitTextField = new JTextField();
			weightDoseInst.responseUnitTextField.setFont(componentLablesFont);
			weightDoseInst.responseUnitTextField.addFocusListener(DDViewLayer
					.createViewLayerInstance());
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.anchor = GridBagConstraints.WEST;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			gbc_textField.ipadx = 15;
			panel_3.add(weightDoseInst.responseUnitTextField, gbc_textField);
			weightDoseInst.responseUnitTextField.setColumns(10);

		} else if (analysisType.equals("mm")) {
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null, "Dosing",
					TitledBorder.LEADING, TitledBorder.TOP,
					componentLablesFont, null));
			GridBagConstraints gbc_panel_3 = new GridBagConstraints();
			gbc_panel_3.insets = new Insets(0, 0, 5, 5);
			gbc_panel_3.fill = GridBagConstraints.BOTH;
			gbc_panel_3.gridx = 1;
			gbc_panel_3.gridy = 1;
			add(panel_3, gbc_panel_3);
			GridBagLayout gbl_panel_3 = new GridBagLayout();
			gbl_panel_3.columnWidths = new int[] { 0, 0, 0, 0 };
			gbl_panel_3.rowHeights = new int[] { 0, 0, 0, 0 };
			gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 1.0,
					Double.MIN_VALUE };
			gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0,
					Double.MIN_VALUE };
			panel_3.setLayout(gbl_panel_3);

			weightDoseInst.doseUnitButtonForCA = new JButton("Dose Unit");
			weightDoseInst.doseUnitButtonForCA.setFont(componentLablesFont);
			weightDoseInst.doseUnitButtonForCA.addActionListener(DDViewLayer
					.createViewLayerInstance());
			weightDoseInst.doseUnitButtonForCA.setActionCommand(EventCodes.DUB);
			GridBagConstraints gbc_doseUnitbtn = new GridBagConstraints();
			gbc_doseUnitbtn.anchor = GridBagConstraints.WEST;
			gbc_doseUnitbtn.insets = new Insets(0, 0, 5, 5);
			gbc_doseUnitbtn.gridx = 0;
			gbc_doseUnitbtn.gridy = 0;
			panel_3.add(weightDoseInst.doseUnitButtonForCA, gbc_doseUnitbtn);

			weightDoseInst.doseUnitTextFieldForCA = new JTextField();
			weightDoseInst.doseUnitTextFieldForCA.setFont(componentLablesFont);
			weightDoseInst.doseUnitTextFieldForCA.addActionListener(DDViewLayer
					.createViewLayerInstance());
			weightDoseInst.doseUnitTextFieldForCA
					.setActionCommand(EventCodes.DUTF);
			weightDoseInst.doseUnitTextFieldForCA.setColumns(10);
			GridBagConstraints gbc_doseUnitTf = new GridBagConstraints();
			gbc_doseUnitTf.ipadx = 10;
			gbc_doseUnitTf.insets = new Insets(0, 0, 5, 0);
			// gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_doseUnitTf.gridx = 1;
			gbc_doseUnitTf.gridy = 0;
			panel_3.add(weightDoseInst.doseUnitTextFieldForCA, gbc_doseUnitTf);

			weightDoseInst.normalizationUnitLabel = new JLabel("Normalization");
			GridBagConstraints gbc_normalizationUnitLabel = new GridBagConstraints();
			gbc_normalizationUnitLabel.anchor = GridBagConstraints.WEST;
			gbc_normalizationUnitLabel.insets = new Insets(0, 0, 5, 5);
			gbc_normalizationUnitLabel.gridx = 0;
			gbc_normalizationUnitLabel.gridy = 1;
			// panel_3.add(weightDoseInst.normalizationUnitLabel,
			// gbc_normalizationUnitLabel);

			weightDoseInst.normalizationUnitComboBoxForCA = new JComboBox();
			weightDoseInst.normalizationUnitComboBoxForCA
					.addActionListener(DDViewLayer.createViewLayerInstance());
			weightDoseInst.normalizationUnitComboBoxForCA
					.setActionCommand(EventCodes.NUCB);
			weightDoseInst.normalizationUnitComboBoxForCA
					.setModel(new DefaultComboBoxModel(new String[] { "",
							"/Kg", "/m*m" }));

			GridBagConstraints gbc_normalizationUnitCb = new GridBagConstraints();
			gbc_normalizationUnitCb.anchor = GridBagConstraints.WEST;
			gbc_normalizationUnitCb.insets = new Insets(0, 0, 5, 0);
			gbc_normalizationUnitCb.gridx = 1;
			gbc_normalizationUnitCb.gridy = 1;
			// panel_3.add(weightDoseInst.normalizationUnitComboBoxForCA,
			// gbc_normalizationUnitCb);

			weightDoseInst.noOfDoseLabel = new JLabel("No. of Doses");
			weightDoseInst.noOfDoseLabel.setFont(componentLablesFont);
			GridBagConstraints gbc_noOFDoseLabel = new GridBagConstraints();
			gbc_noOFDoseLabel.anchor = GridBagConstraints.WEST;
			gbc_noOFDoseLabel.insets = new Insets(0, 0, 5, 5);
			gbc_noOFDoseLabel.gridx = 0;
			gbc_rdbtnAsciiModel.gridy = 1;
			panel_3.add(weightDoseInst.noOfDoseLabel, gbc_noOFDoseLabel);

			weightDoseInst.numberOfDoseComboBox = new JComboBox();
			weightDoseInst.numberOfDoseComboBox
					.setFont(componentOptionseFontForComboBox);
			weightDoseInst.numberOfDoseComboBox.addActionListener(DDViewLayer
					.createViewLayerInstance());
			weightDoseInst.numberOfDoseComboBox
					.setActionCommand(EventCodes.NDCB);
			weightDoseInst.numberOfDoseComboBox
					.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
							"3", "4", "5", "6", "7", "8", "9", "10", "11",
							"12", "13", "14", "15", "16", "17", "18", "19",
							"20", "21", "22" }));
			/*
			for (int i = 1; i <= 20; i++) {
				
				weightDoseInst.numberOfDoseComboBox.addItem(i+"");
			}*/
			
			GridBagConstraints gbc_noOfDoseCb = new GridBagConstraints();
			gbc_noOfDoseCb.anchor = GridBagConstraints.WEST;
			gbc_noOfDoseCb.insets = new Insets(0, 0, 5, 0);
			gbc_noOfDoseCb.ipadx = 17;
			// gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_noOfDoseCb.gridx = 1;
			gbc_noOfDoseCb.gridy = 1;
			panel_3.add(weightDoseInst.numberOfDoseComboBox, gbc_noOfDoseCb);

			/*
			 * JPanel panel_3 = new JPanel(); panel_3.setBorder(new
			 * TitledBorder(null, "Dosing", TitledBorder.LEADING,
			 * TitledBorder.TOP, null, null)); GridBagConstraints gbc_panel_3 =
			 * new GridBagConstraints(); gbc_panel_3.gridwidth = 4;
			 * gbc_panel_3.insets = new Insets(0, 0, 0, 5); gbc_panel_3.fill =
			 * GridBagConstraints.BOTH; gbc_panel_3.gridx = 2; gbc_panel_3.gridy
			 * = 2; add(panel_3, gbc_panel_3); GridBagLayout gbl_panel_3 = new
			 * GridBagLayout(); gbl_panel_3.columnWidths = new int[]{0, 0, 0};
			 * gbl_panel_3.rowHeights = new int[]{0, 0, 0};
			 * gbl_panel_3.columnWeights = new double[]{0.0, 1.0,
			 * Double.MIN_VALUE}; gbl_panel_3.rowWeights = new double[]{0.0,
			 * 0.0, Double.MIN_VALUE}; panel_3.setLayout(gbl_panel_3);
			 * 
			 * weightDoseInst.doseUnitButtonForCA = new JButton("Dose Unit");
			 * weightDoseInst.doseUnitButtonForCA.addActionListener(ViewLayer
			 * .createViewLayerInstance());
			 * weightDoseInst.doseUnitButtonForCA.setActionCommand
			 * (EventCodes.DUB);
			 * weightDoseInst.doseUnitButtonForCA.setBounds(453, 193, 89, 23);
			 * add(weightDoseInst.doseUnitButtonForCA);
			 * 
			 * weightDoseInst.doseUnitTextFieldForCA = new JTextField();
			 * weightDoseInst.doseUnitTextFieldForCA.addActionListener(ViewLayer
			 * .createViewLayerInstance());
			 * weightDoseInst.doseUnitTextFieldForCA
			 * .setActionCommand(EventCodes.DUTF);
			 * weightDoseInst.doseUnitTextFieldForCA.setBounds(575, 194, 86,
			 * 20); add(weightDoseInst.doseUnitTextFieldForCA);
			 * weightDoseInst.doseUnitTextFieldForCA.setColumns(10);
			 * 
			 * weightDoseInst.noOfDoseLabel = new JLabel("No. of Doses");
			 * weightDoseInst.noOfDoseLabel.setBounds(453, 261, 89, 14);
			 * add(weightDoseInst.noOfDoseLabel);
			 * 
			 * weightDoseInst.numberOfDoseComboBox = new JComboBox();
			 * weightDoseInst.numberOfDoseComboBox.addActionListener(ViewLayer
			 * .createViewLayerInstance()); weightDoseInst.numberOfDoseComboBox
			 * .setActionCommand(EventCodes.NDCB);
			 * weightDoseInst.numberOfDoseComboBox .setModel(new
			 * DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6",
			 * "7", "8" })); weightDoseInst.numberOfDoseComboBox.setBounds(585,
			 * 261, 76, 20); add(weightDoseInst.numberOfDoseComboBox);
			 */

		} else if (analysisType.equals("irm")
				|| analysisType.equals("pkpdlink") || analysisType.equals("pk")) {
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null, "Dosing",
					TitledBorder.LEADING, TitledBorder.TOP,
					componentLablesFont, null));
			GridBagConstraints gbc_panel_3 = new GridBagConstraints();
			gbc_panel_3.insets = new Insets(0, 0, 5, 5);
			gbc_panel_3.fill = GridBagConstraints.BOTH;
			gbc_panel_3.gridx = 1;
			gbc_panel_3.gridy = 1;
			add(panel_3, gbc_panel_3);
			GridBagLayout gbl_panel_3 = new GridBagLayout();
			gbl_panel_3.columnWidths = new int[] { 0, 0, 0, 0 };
			gbl_panel_3.rowHeights = new int[] { 0, 0, 0, 0 };
			gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, 1.0,
					Double.MIN_VALUE };
			gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0,
					Double.MIN_VALUE };
			panel_3.setLayout(gbl_panel_3);

			weightDoseInst.doseUnitButtonForCA = new JButton("Dose Unit");
			weightDoseInst.doseUnitButtonForCA.setFont(componentLablesFont);
			weightDoseInst.doseUnitButtonForCA.addActionListener(DDViewLayer
					.createViewLayerInstance());
			weightDoseInst.doseUnitButtonForCA.setActionCommand(EventCodes.DUB);
			GridBagConstraints gbc_doseUnitbtn = new GridBagConstraints();
			gbc_doseUnitbtn.anchor = GridBagConstraints.WEST;
			gbc_doseUnitbtn.insets = new Insets(0, 0, 5, 5);
			gbc_doseUnitbtn.gridx = 0;
			gbc_doseUnitbtn.gridy = 0;
			panel_3.add(weightDoseInst.doseUnitButtonForCA, gbc_doseUnitbtn);

			weightDoseInst.doseUnitTextFieldForCA = new JTextField();
			weightDoseInst.doseUnitTextFieldForCA.setFont(componentLablesFont);
			weightDoseInst.doseUnitTextFieldForCA.addActionListener(DDViewLayer
					.createViewLayerInstance());
			weightDoseInst.doseUnitTextFieldForCA
					.setActionCommand(EventCodes.DUTF);
			weightDoseInst.doseUnitTextFieldForCA.setColumns(10);
			GridBagConstraints gbc_doseUnitTf = new GridBagConstraints();
			gbc_doseUnitTf.ipadx = 20;
			gbc_doseUnitTf.insets = new Insets(0, 0, 5, 0);
			// gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_doseUnitTf.gridx = 1;
			gbc_doseUnitTf.gridy = 0;
			panel_3.add(weightDoseInst.doseUnitTextFieldForCA, gbc_doseUnitTf);

			weightDoseInst.normalizationUnitLabel = new JLabel("Normalization");
			weightDoseInst.normalizationUnitLabel.setFont(componentLablesFont);
			GridBagConstraints gbc_normalizationUnitLabel = new GridBagConstraints();
			gbc_normalizationUnitLabel.anchor = GridBagConstraints.WEST;
			gbc_normalizationUnitLabel.insets = new Insets(0, 0, 5, 5);
			gbc_normalizationUnitLabel.gridx = 0;
			gbc_normalizationUnitLabel.gridy = 1;
			panel_3.add(weightDoseInst.normalizationUnitLabel,
					gbc_normalizationUnitLabel);

			weightDoseInst.normalizationUnitComboBoxForCA = new JComboBox();
			weightDoseInst.normalizationUnitComboBoxForCA
					.setFont(componentOptionseFontForComboBox);
			weightDoseInst.normalizationUnitComboBoxForCA
					.addActionListener(DDViewLayer.createViewLayerInstance());
			weightDoseInst.normalizationUnitComboBoxForCA
					.setActionCommand(EventCodes.NUCB);
			weightDoseInst.normalizationUnitComboBoxForCA
					.setModel(new DefaultComboBoxModel(new String[] { "",
							"/Kg", "/m*m" }));

			GridBagConstraints gbc_normalizationUnitCb = new GridBagConstraints();
			gbc_normalizationUnitCb.anchor = GridBagConstraints.WEST;
			gbc_normalizationUnitCb.insets = new Insets(0, 0, 5, 0);
			gbc_normalizationUnitCb.gridx = 1;
			gbc_normalizationUnitCb.gridy = 1;
			panel_3.add(weightDoseInst.normalizationUnitComboBoxForCA,
					gbc_normalizationUnitCb);

			weightDoseInst.noOfDoseLabel = new JLabel("No. of Doses");
			weightDoseInst.noOfDoseLabel.setFont(componentLablesFont);
			GridBagConstraints gbc_noOFDoseLabel = new GridBagConstraints();
			gbc_noOFDoseLabel.anchor = GridBagConstraints.WEST;
			gbc_noOFDoseLabel.insets = new Insets(0, 0, 5, 5);
			gbc_noOFDoseLabel.gridx = 0;
			gbc_rdbtnAsciiModel.gridy = 2;
			panel_3.add(weightDoseInst.noOfDoseLabel, gbc_noOFDoseLabel);

			weightDoseInst.numberOfDoseComboBox = new JComboBox();
			weightDoseInst.numberOfDoseComboBox
					.setFont(componentOptionseFontForComboBox);
			weightDoseInst.numberOfDoseComboBox.addActionListener(DDViewLayer
					.createViewLayerInstance());
			weightDoseInst.numberOfDoseComboBox
					.setActionCommand(EventCodes.NDCB);
			weightDoseInst.numberOfDoseComboBox
					.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
							"3", "4", "5", "6", "7", "8", "9", "10",  "11", "12",
							"13", "14", "15", "16", "17", "18", "19", "20"}));
		
			/*for (int i = 0; i <20; i++) {
				weightDoseInst.numberOfDoseComboBox.addItem(i+"");
			}*/
			
			GridBagConstraints gbc_noOfDoseCb = new GridBagConstraints();
			gbc_noOfDoseCb.anchor = GridBagConstraints.WEST;
			gbc_noOfDoseCb.insets = new Insets(0, 0, 5, 0);
			gbc_noOfDoseCb.ipadx = 17;
			// gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_noOfDoseCb.gridx = 1;
			gbc_noOfDoseCb.gridy = 2;
			panel_3.add(weightDoseInst.numberOfDoseComboBox, gbc_noOfDoseCb);

			/*
			 * JPanel panel_3 = new JPanel(); panel_3.setBorder(new
			 * TitledBorder(null, "Dosing", TitledBorder.LEADING,
			 * TitledBorder.TOP, null, null)); GridBagConstraints gbc_panel_3 =
			 * new GridBagConstraints(); gbc_panel_3.gridwidth = 4;
			 * gbc_panel_3.insets = new Insets(0, 0, 0, 5); gbc_panel_3.fill =
			 * GridBagConstraints.BOTH; gbc_panel_3.gridx = 2; gbc_panel_3.gridy
			 * = 2; add(panel_3, gbc_panel_3); GridBagLayout gbl_panel_3 = new
			 * GridBagLayout(); gbl_panel_3.columnWidths = new int[]{0, 0, 0};
			 * gbl_panel_3.rowHeights = new int[]{0, 0, 0};
			 * gbl_panel_3.columnWeights = new double[]{0.0, 1.0,
			 * Double.MIN_VALUE}; gbl_panel_3.rowWeights = new double[]{0.0,
			 * 0.0, Double.MIN_VALUE}; panel_3.setLayout(gbl_panel_3);
			 * 
			 * weightDoseInst.doseUnitButtonForCA = new JButton("Dose Unit");
			 * weightDoseInst.doseUnitButtonForCA.addActionListener(ViewLayer
			 * .createViewLayerInstance());
			 * weightDoseInst.doseUnitButtonForCA.setActionCommand
			 * (EventCodes.DUB);
			 * weightDoseInst.doseUnitButtonForCA.setBounds(453, 193, 89, 23);
			 * add(weightDoseInst.doseUnitButtonForCA);
			 * 
			 * weightDoseInst.doseUnitTextFieldForCA = new JTextField();
			 * weightDoseInst.doseUnitTextFieldForCA.addActionListener(ViewLayer
			 * .createViewLayerInstance());
			 * weightDoseInst.doseUnitTextFieldForCA
			 * .setActionCommand(EventCodes.DUTF);
			 * weightDoseInst.doseUnitTextFieldForCA.setBounds(575, 194, 86,
			 * 20); add(weightDoseInst.doseUnitTextFieldForCA);
			 * weightDoseInst.doseUnitTextFieldForCA.setColumns(10);
			 * 
			 * weightDoseInst.normalizationUnitLabel = new
			 * JLabel("Normalization");
			 * weightDoseInst.normalizationUnitLabel.setBounds(453, 233, 89,
			 * 14); add(weightDoseInst.normalizationUnitLabel);
			 * 
			 * weightDoseInst.normalizationUnitComboBoxForCA = new JComboBox();
			 * weightDoseInst.normalizationUnitComboBoxForCA
			 * .addActionListener(ViewLayer.createViewLayerInstance());
			 * weightDoseInst.normalizationUnitComboBoxForCA
			 * .setActionCommand(EventCodes.NUCB);
			 * weightDoseInst.normalizationUnitComboBoxForCA .setModel(new
			 * DefaultComboBoxModel(new String[] { "", "/Kg", "/m*m" }));
			 * weightDoseInst.normalizationUnitComboBoxForCA.setBounds(585, 230,
			 * 76, 20); add(weightDoseInst.normalizationUnitComboBoxForCA);
			 * 
			 * weightDoseInst.noOfDoseLabel = new JLabel("No. of Doses");
			 * weightDoseInst.noOfDoseLabel.setBounds(453, 261, 89, 14);
			 * add(weightDoseInst.noOfDoseLabel);
			 * 
			 * weightDoseInst.numberOfDoseComboBox = new JComboBox();
			 * weightDoseInst.numberOfDoseComboBox.addActionListener(ViewLayer
			 * .createViewLayerInstance()); weightDoseInst.numberOfDoseComboBox
			 * .setActionCommand(EventCodes.NDCB);
			 * weightDoseInst.numberOfDoseComboBox .setModel(new
			 * DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6",
			 * "7", "8" })); weightDoseInst.numberOfDoseComboBox.setBounds(585,
			 * 261, 76, 20); add(weightDoseInst.numberOfDoseComboBox);
			 */
		}

		if (analysisType.equals("irm") || analysisType.equals("pkpdlink")) {

			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null, "Response Unit",
					TitledBorder.LEADING, TitledBorder.TOP,
					componentLablesFont, null));
			GridBagConstraints gbc_panel_3 = new GridBagConstraints();
			gbc_panel_3.gridwidth = 4;
			gbc_panel_3.insets = new Insets(0, 0, 5, 5);
			gbc_panel_3.fill = GridBagConstraints.BOTH;
			gbc_panel_3.gridx = 1;
			gbc_panel_3.gridy = 2;
			add(panel_3, gbc_panel_3);
			GridBagLayout gbl_panel_3 = new GridBagLayout();
			gbl_panel_3.columnWidths = new int[] { 0, 0, 0 };
			gbl_panel_3.rowHeights = new int[] { 0, 0, 0 };
			gbl_panel_3.columnWeights = new double[] { 0.0, 1.0,
					Double.MIN_VALUE };
			gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
			panel_3.setLayout(gbl_panel_3);

			weightDoseInst.responseUnitLabel = new JLabel("Response");
			weightDoseInst.responseUnitLabel.setFont(componentLablesFont);
			GridBagConstraints gbc_lblResponse = new GridBagConstraints();
			gbc_lblResponse.insets = new Insets(0, 0, 5, 5);
			gbc_lblResponse.anchor = GridBagConstraints.WEST;
			gbc_lblResponse.gridx = 0;
			gbc_lblResponse.gridy = 0;
			gbc_lblResponse.ipadx = 23;
			panel_3.add(weightDoseInst.responseUnitLabel, gbc_lblResponse);

			weightDoseInst.responseUnitTextField = new JTextField();
			weightDoseInst.responseUnitTextField.setFont(componentLablesFont);
			weightDoseInst.responseUnitTextField.addFocusListener(DDViewLayer
					.createViewLayerInstance());
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.anchor = GridBagConstraints.WEST;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			gbc_textField.ipadx = 15;
			panel_3.add(weightDoseInst.responseUnitTextField, gbc_textField);
			weightDoseInst.responseUnitTextField.setColumns(10);

		}

	}

}
