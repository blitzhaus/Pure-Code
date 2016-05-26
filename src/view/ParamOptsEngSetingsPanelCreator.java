package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.io.IOException;

import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



import Common.EventCodes;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ParamOptsEngSetingsPanelCreator extends JPanel {
	private JPanel panel;
	/*private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;*/

	/**
	 * Launch the application.
	 */
	Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	Font componentOptionseFontForComboBox = new Font("Arial", Font.PLAIN, 11);
	Font componentLablesFont = new Font("Arial", Font.BOLD, 11);

	final class EngSettingTextFieldActionEventHandler implements
			DocumentListener {
		public void removeUpdate(DocumentEvent arg0) {

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			CaEngineSettingDispGuiCreator pdEngSetInst = null;
			try {
				pdEngSetInst = CaEngineSettingDispGuiCreator
						.createEngineSettingInstance();
			} catch (RowsExceededException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (WriteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (BiffException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				String temp = pdEngSetInst.incrementsForpartialDerivativesTextField
						.getText();

				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.numberOfPredictedValuesTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.convergenceCriteriaTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.numberOfIterationTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.meanSquareTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.lambdaValueTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.muTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				setTextFieldValue();
			} catch (Exception e) {

				String message = "Please ensure that the textfield contains only numbers";

				try {
					CaMapingHandler.createCaMapHandInst().showMessage(message);
				} catch (RowsExceededException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BiffException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}

		private void restorePreviousValue() throws RowsExceededException,
				WriteException, BiffException, IOException {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			CaEngineSettingDispGuiCreator pdEngSetInst = CaEngineSettingDispGuiCreator
					.createEngineSettingInstance();
			ProcessingInputsInfo procInputInst = CaMapingHandler
					.createCaMapHandInst().copyProcessingInput();

			pdEngSetInst.incrementsForpartialDerivativesTextField
					.setText(procInputInst.getModelInputTab2Obj()
							.getIncForPartialDerivative());

			pdEngSetInst.numberOfPredictedValuesTextField.setText(procInputInst
					.getModelInputTab2Obj().getNumberOfPredictedValue());

			pdEngSetInst.convergenceCriteriaTextField.setText(procInputInst
					.getModelInputTab2Obj().getConvergenceCriteria());

			pdEngSetInst.numberOfIterationTextField.setText(procInputInst
					.getModelInputTab2Obj().getNumberOfIterations());

			pdEngSetInst.meanSquareTextField.setText(procInputInst
					.getModelInputTab2Obj().getMeanSquareValue());

			pdEngSetInst.lambdaValueTextField.setText(procInputInst
					.getModelInputTab2Obj().getLambdaValue());

			pdEngSetInst.muTextField.setText(procInputInst
					.getModelInputTab2Obj().getMuValue());

		}

		public void insertUpdate(DocumentEvent arg0) {
			CaEngineSettingDispGuiCreator pdEngSetInst = null;
			try {
				pdEngSetInst = CaEngineSettingDispGuiCreator
						.createEngineSettingInstance();
			} catch (RowsExceededException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (WriteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (BiffException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				String temp = pdEngSetInst.incrementsForpartialDerivativesTextField
						.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.numberOfPredictedValuesTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.convergenceCriteriaTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.numberOfIterationTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.meanSquareTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.lambdaValueTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.muTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				setTextFieldValue();
			} catch (Exception e) {

				String message = "Please ensure that the textfield contains only numbers";

				try {
					CaMapingHandler.createCaMapHandInst().showMessage(message);
				} catch (RowsExceededException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BiffException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}

		public void changedUpdate(DocumentEvent arg0) {
			CaEngineSettingDispGuiCreator pdEngSetInst = null;
			try {
				pdEngSetInst = CaEngineSettingDispGuiCreator
						.createEngineSettingInstance();
			} catch (RowsExceededException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (WriteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (BiffException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				String temp = pdEngSetInst.incrementsForpartialDerivativesTextField
						.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.numberOfPredictedValuesTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.convergenceCriteriaTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.numberOfIterationTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.meanSquareTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.lambdaValueTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = pdEngSetInst.muTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				setTextFieldValue();
			} catch (Exception e) {

				String message = "Please ensure that the textfield contains only numbers";

				try {
					CaMapingHandler.createCaMapHandInst().showMessage(message);
				} catch (RowsExceededException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BiffException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}

	public void setTextFieldValue() throws RowsExceededException,
			WriteException, BiffException, IOException {

		CaEngineSettingDispGuiCreator pdEngSetInst = CaEngineSettingDispGuiCreator
				.createEngineSettingInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		if (pdEngSetInst.incrementsForpartialDerivativesTextField.getText()
				.equals("")) {

		} else
			procInputInst.getModelInputTab2Obj().setIncForPartialDerivative(
					pdEngSetInst.incrementsForpartialDerivativesTextField
							.getText());
		procInputInst.getModelInputTab2Obj().setNumberOfPredictedValue(
				pdEngSetInst.numberOfPredictedValuesTextField.getText());

		procInputInst.getModelInputTab2Obj().setConvergenceCriteria(
				pdEngSetInst.convergenceCriteriaTextField.getText());

		procInputInst.getModelInputTab2Obj().setNumberOfIterations(
				pdEngSetInst.numberOfIterationTextField.getText());

		procInputInst.getModelInputTab2Obj().setMeanSquareValue(
				pdEngSetInst.meanSquareTextField.getText());

		procInputInst.getModelInputTab2Obj().setLambdaValue(
				pdEngSetInst.lambdaValueTextField.getText());

		procInputInst.getModelInputTab2Obj().setMuValue(
				pdEngSetInst.muTextField.getText());
		/*
		 * procInputInst.getModelInputTab2Obj().setRoundingOffValue(
		 * pdEngSetInst.roundToTextField.getText());
		 */
	}

	public void updateView(String str) {
		System.out.println("ParamOptionsEngSettingsPanel notified" + str);
	}

	/*public static void main(String[] args) throws RowsExceededException, WriteException, BiffException, IOException {
		ParamOptionsEngSetingsPanel tmMapPanel = null;
		try {
			tmMapPanel = new ParamOptionsEngSetingsPanel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JFrame frame = new JFrame();

		frame.setTitle("Parameter Options-Engine Settings Panel");

		frame.setLocationRelativeTo(null);
		// frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Toolkit toolkit = Toolkit.getDefaultToolkit();

		Dimension dimen = toolkit.getScreenSize();

		tmMapPanel.setSize((int) dimen.getWidth() / 2,
				(int) dimen.getHeight() / 2);

		frame.setSize(tmMapPanel.getWidth(), tmMapPanel.getHeight() + 50);

		frame.getContentPane().add(tmMapPanel);

		frame.setVisible(true);

	}
*/
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws BiffException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public ParamOptsEngSetingsPanelCreator() throws RowsExceededException, WriteException, BiffException, IOException {
		createParamOptEngSetpanel();
	}
	
	public void createParamOptEngSetpanel() throws RowsExceededException, WriteException, BiffException, IOException
	{

		setBackground(new Color(240, 240, 240));
		// setResizable(true);
		// setBounds(100, 100, 706, 485);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 1.0,
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		
		CaEngineSettingDispGuiCreator engineSetInst = CaEngineSettingDispGuiCreator
		.createEngineSettingInstance();
		
		
		CaWeightDoseDispGuiCreator weightDoseInst = CaWeightDoseDispGuiCreator
		.createCaWeightDoseInstance();
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Source for initial parameters", TitledBorder.LEADING, TitledBorder.TOP, componentLablesFont, null));
		
		
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		
		JLabel lblNewLabel_10 = new JLabel("Enter");
		lblNewLabel_10.setFont(componentLablesFont); 
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 0;
		gbc_lblNewLabel_10.ipadx = 30;
		panel_1.add(lblNewLabel_10, gbc_lblNewLabel_10);

		engineSetInst.initialParameterValueComboBox = new JComboBox();
		engineSetInst.initialParameterValueComboBox.setFont(componentOptionseFontForComboBox);
		engineSetInst.initialParameterValueComboBox.addActionListener(DDViewLayer
				.createViewLayerInstance());
		engineSetInst.initialParameterValueComboBox
				.setActionCommand(EventCodes.IPVCB);
		engineSetInst.initialParameterValueComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Code generated initial parameter value",
				"User supplied initial parameter value" }));
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		//gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 3;
		gbc_comboBox_2.gridy = 0;
		panel_1.add(engineSetInst.initialParameterValueComboBox, gbc_comboBox_2);
		
		
		weightDoseInst.algebraicEquationModelRadioButton = new JRadioButton("Alg Model");
		GridBagConstraints gbc_rdbtnAlgModel = new GridBagConstraints();
		//gbc_rdbtnAlgModel.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAlgModel.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAlgModel.gridx = 0;
		gbc_rdbtnAlgModel.gridy = 0;
		//panel_1.add(weightDoseInst.algebraicEquationModelRadioButton, gbc_rdbtnAlgModel);
		
		weightDoseInst.availableLibraryModelComboBox = new JComboBox();
		weightDoseInst.availableLibraryModelComboBox.setMaximumRowCount(16);
		weightDoseInst.availableLibraryModelComboBox.setModel(new DefaultComboBoxModel(new String[] {"1:1-comp-IV-bolus"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.ipadx = 40;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		//gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 0;
	//	panel_1.add(weightDoseInst.availableLibraryModelComboBox, gbc_comboBox);
		
		weightDoseInst.differentialEquationModelRadioButton = new JRadioButton("Diff Eqn Model");
		GridBagConstraints gbc_rdbtnDiffEqnModel = new GridBagConstraints();
		gbc_rdbtnDiffEqnModel.anchor = GridBagConstraints.WEST;
		gbc_rdbtnDiffEqnModel.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnDiffEqnModel.gridx = 0;
		gbc_rdbtnDiffEqnModel.gridy = 1;
	//	panel_1.add(weightDoseInst.differentialEquationModelRadioButton, gbc_rdbtnDiffEqnModel);
		
		weightDoseInst.asciiModelRadioButton = new JRadioButton("Ascii Model");
		GridBagConstraints gbc_rdbtnAsciiModel = new GridBagConstraints();
		gbc_rdbtnAsciiModel.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAsciiModel.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnAsciiModel.gridx = 0;
		gbc_rdbtnAsciiModel.gridy = 2;
	//	panel_1.add(weightDoseInst.asciiModelRadioButton, gbc_rdbtnAsciiModel);
		
		weightDoseInst.availableDifferentialEquationModelComboBox = new JComboBox();
		weightDoseInst.availableDifferentialEquationModelComboBox.setModel(new DefaultComboBoxModel(new String[] {"1:1-Comp-IV-Bolus-Rate-Constant"}));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.ipadx = 50;
		//gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 1;
		panel = new JPanel();
		
		panel.setBorder(new TitledBorder(null, "Options for optimized parameters", TitledBorder.LEADING, TitledBorder.TOP, componentLablesFont, null));

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 12;
		gbc_panel.gridwidth = 14;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 7;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		engineSetInst.minimizationMthodLabel = new JLabel("Minimization");
		engineSetInst.minimizationMthodLabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(engineSetInst.minimizationMthodLabel, gbc_lblNewLabel);

		engineSetInst.minimizationMthodComboBox = new JComboBox();
		engineSetInst.minimizationMthodComboBox.setFont(componentOptionseFontForComboBox);
		engineSetInst.minimizationMthodComboBox.addActionListener(DDViewLayer
				.createViewLayerInstance());
		engineSetInst.minimizationMthodComboBox
				.setActionCommand(EventCodes.MMCB);
		engineSetInst.minimizationMthodComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"GN (Lavenberg-Marquardt)", "GN(Hartley)", "Nelder-Mead", "User Selection" }));
		GridBagConstraints gbc_minComboBox = new GridBagConstraints();
		gbc_minComboBox.insets = new Insets(0, 0, 5, 0);
	//	gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_minComboBox.gridx = 2;
		gbc_minComboBox.gridy = 1;
		panel.add(engineSetInst.minimizationMthodComboBox, gbc_minComboBox);

		engineSetInst.odeSolverMethodLabel = new JLabel("ODE Solver Method");
		engineSetInst.odeSolverMethodLabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(engineSetInst.odeSolverMethodLabel, gbc_lblNewLabel_1);

		engineSetInst.odeSolverMethodComboBox = new JComboBox();
		engineSetInst.odeSolverMethodComboBox.setFont(componentOptionseFontForComboBox);
		engineSetInst.odeSolverMethodComboBox.addActionListener(DDViewLayer
				.createViewLayerInstance());
		engineSetInst.odeSolverMethodComboBox
				.setActionCommand(EventCodes.ODESCB);
		engineSetInst.odeSolverMethodComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Runge Kutta Fehlberg", "Runge Kutta Cashcarp" }));
		GridBagConstraints gbc_odeComboBox_1 = new GridBagConstraints();
		gbc_odeComboBox_1.insets = new Insets(0, 0, 5, 0);
	//	gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_odeComboBox_1.gridx = 2;
		gbc_odeComboBox_1.gridy = 2;
		panel.add(engineSetInst.odeSolverMethodComboBox, gbc_odeComboBox_1);

		engineSetInst.incrementsForpartialDerivativesLabel = new JLabel("Increments for partial derivative");
		engineSetInst.incrementsForpartialDerivativesLabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(engineSetInst.incrementsForpartialDerivativesLabel, gbc_lblNewLabel_2);

		engineSetInst.incrementsForpartialDerivativesTextField = new JTextField("0.0001");
		engineSetInst.incrementsForpartialDerivativesTextField.setFont(componentLablesFont);
		engineSetInst.incrementsForpartialDerivativesTextField.getDocument()
		.addDocumentListener(
				new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
	//	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		panel.add(engineSetInst.incrementsForpartialDerivativesTextField, gbc_textField);
		engineSetInst.incrementsForpartialDerivativesTextField.setColumns(10);

		engineSetInst.numberOfPredictedValuesLabel = new JLabel("Number of predicted values");
		engineSetInst.numberOfPredictedValuesLabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 4;
		panel.add(engineSetInst.numberOfPredictedValuesLabel, gbc_lblNewLabel_3);

		engineSetInst.numberOfPredictedValuesTextField = new JTextField("100");
		engineSetInst.numberOfPredictedValuesTextField.setFont(componentLablesFont);
		engineSetInst.numberOfPredictedValuesTextField.getDocument()
		.addDocumentListener(
				new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		//gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 4;
		panel.add(engineSetInst.numberOfPredictedValuesTextField, gbc_textField_1);
		engineSetInst.numberOfPredictedValuesTextField.setColumns(10);

		engineSetInst.convergenceCriterialabel = new JLabel("Convergence Criterion");
		engineSetInst.convergenceCriterialabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 5;
		panel.add(engineSetInst.convergenceCriterialabel, gbc_lblNewLabel_4);

		engineSetInst.convergenceCriteriaTextField = new JTextField("0.000001");
		engineSetInst.convergenceCriteriaTextField.setFont(componentLablesFont);
		engineSetInst.convergenceCriteriaTextField.getDocument()
				.addDocumentListener(
						new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		//gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 5;
		panel.add(engineSetInst.convergenceCriteriaTextField, gbc_textField_2);
		engineSetInst.convergenceCriteriaTextField.setColumns(10);

		engineSetInst.noOfIterLabel = new JLabel("Number of Iterations");
		engineSetInst.noOfIterLabel .setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 6;
		panel.add(engineSetInst.noOfIterLabel, gbc_lblNewLabel_5);

		engineSetInst.numberOfIterationTextField = new JTextField("100");
		engineSetInst.numberOfIterationTextField.setFont(componentLablesFont);
		engineSetInst.numberOfIterationTextField.getDocument()
				.addDocumentListener(
						new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		//gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 6;
		panel.add(engineSetInst.numberOfIterationTextField, gbc_textField_3);
		engineSetInst.numberOfIterationTextField.setColumns(10);

		engineSetInst.meanSquareLabel = new JLabel("Mean Square Value");
		engineSetInst.meanSquareLabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 7;
		panel.add(engineSetInst.meanSquareLabel, gbc_lblNewLabel_6);

		engineSetInst.meanSquareTextField = new JTextField();
		engineSetInst.meanSquareTextField.setFont(componentLablesFont);
		engineSetInst.meanSquareTextField.getDocument().addDocumentListener(
				new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		//gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 7;
		panel.add(engineSetInst.meanSquareTextField, gbc_textField_4);
		engineSetInst.meanSquareTextField.setColumns(10);

		engineSetInst.lambdaValueLabel = new JLabel("Lambda Value");
		engineSetInst.lambdaValueLabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 8;
		panel.add(engineSetInst.lambdaValueLabel, gbc_lblNewLabel_7);

		engineSetInst.lambdaValueTextField = new JTextField("0.01");
		engineSetInst.lambdaValueTextField.setFont(componentLablesFont);
		engineSetInst.lambdaValueTextField.getDocument().addDocumentListener(
				new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		//gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 8;
		panel.add(engineSetInst.lambdaValueTextField, gbc_textField_5);
		engineSetInst.lambdaValueTextField.setColumns(10);

		engineSetInst.muLable = new JLabel("mu Value");
		engineSetInst.muLable.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 9;
		panel.add(engineSetInst.muLable, gbc_lblNewLabel_8);

		engineSetInst.muTextField = new JTextField("0.2");
		engineSetInst.muTextField.setFont(componentLablesFont);
		engineSetInst.muTextField.getDocument().addDocumentListener(
				new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		//gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 2;
		gbc_textField_6.gridy = 9;
		panel.add(engineSetInst.muTextField, gbc_textField_6);
		engineSetInst.muTextField.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Initial value estimation techniques", TitledBorder.LEADING, TitledBorder.TOP, componentLablesFont, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		ButtonGroup bg = new ButtonGroup();
		
		engineSetInst.initialParameterValueByCS = new JRadioButton(
				"Use Curve Stripping");
		bg.add(engineSetInst.initialParameterValueByCS );
		engineSetInst.initialParameterValueByCS.setFont(componentLablesFont);
		engineSetInst.initialParameterValueByCS.addActionListener(DDViewLayer
				.createViewLayerInstance());
		engineSetInst.initialParameterValueByCS
				.setActionCommand(EventCodes.PEBCSRB);
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton.gridx = 1;
		gbc_rdbtnNewRadioButton.gridy = 0;
		panel_2.add(engineSetInst.initialParameterValueByCS,
				gbc_rdbtnNewRadioButton);
		
		
		
		
		engineSetInst.initialParameterValueByGA = new JRadioButton(
				"Use Genetic Algorithm");
		bg.add(engineSetInst.initialParameterValueByGA);
		engineSetInst.initialParameterValueByGA.setFont(componentLablesFont);
		engineSetInst.initialParameterValueByGA.addActionListener(DDViewLayer
				.createViewLayerInstance());
		engineSetInst.initialParameterValueByGA
				.setActionCommand(EventCodes.PEBGARB);
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		//gbc_rdbtnNewRadioButton_1.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 2;
		gbc_rdbtnNewRadioButton_1.gridy = 0;
		panel_2.add(engineSetInst.initialParameterValueByGA,
				gbc_rdbtnNewRadioButton_1);

		
		
		
		
		engineSetInst.initialParameterValueByGS = new JRadioButton(
				"Use Grid Search");
		bg.add(engineSetInst.initialParameterValueByGS);
		engineSetInst.initialParameterValueByGS.setFont(componentLablesFont);
		engineSetInst.initialParameterValueByGS.addActionListener(DDViewLayer
				.createViewLayerInstance());
		engineSetInst.initialParameterValueByGS
				.setActionCommand(EventCodes.PEBGSRB);
		GridBagConstraints gbc_rdbtnNewRadioButton_3 = new GridBagConstraints();
		//gbc_rdbtnNewRadioButton_1.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 0;
		gbc_rdbtnNewRadioButton_1.gridy = 1;
		panel_2.add(engineSetInst.initialParameterValueByGS,
				gbc_rdbtnNewRadioButton_3);

		engineSetInst.previouslyGenInitilParam = new JRadioButton(
				"Carry Forward Initial Values");
		bg.add(engineSetInst.previouslyGenInitilParam);
		engineSetInst.previouslyGenInitilParam.setFont(componentLablesFont);
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 1;
		gbc_rdbtnNewRadioButton_2.gridy = 1;
		panel_2.add(engineSetInst.previouslyGenInitilParam,
				gbc_rdbtnNewRadioButton_2);
		
		engineSetInst.useNcaCsEqns = new JRadioButton(
				"Use NCA/CS/Eqns");
		bg.add(engineSetInst.useNcaCsEqns);
		engineSetInst.useNcaCsEqns.setFont(componentLablesFont);
		GridBagConstraints gbc_rdbtnNewRadioButton_6 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 1;
		gbc_rdbtnNewRadioButton_2.gridy = 2;
		panel_2.add(engineSetInst.useNcaCsEqns,
				gbc_rdbtnNewRadioButton_6);
		engineSetInst.useNcaCsEqns.addActionListener(DDViewLayer.createViewLayerInstance());
		engineSetInst.useNcaCsEqns.setActionCommand(EventCodes.PEBNCA);
		
			
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Parameter Boundary", TitledBorder.LEADING, TitledBorder.TOP, componentLablesFont, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 2;
		add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_3);
		
		
		JLabel lblNewLabel_9 = new JLabel("Enter");
		lblNewLabel_9.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 0;
		panel_3.add(lblNewLabel_9, gbc_lblNewLabel_9);

		engineSetInst.parameterBoundariesComboBox = new JComboBox();
		engineSetInst.parameterBoundariesComboBox.setFont(componentOptionseFontForComboBox);
		engineSetInst.parameterBoundariesComboBox.addActionListener(DDViewLayer
				.createViewLayerInstance());
		engineSetInst.parameterBoundariesComboBox
				.setActionCommand(EventCodes.PBCB);
		engineSetInst.parameterBoundariesComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Code generated parameter bounds",
				"User supplied parameter bounds",
				"Do not use any parameter bounds" }));
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.insets = new Insets(0, 0, 0, 5);
		//gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 2;
		gbc_comboBox_3.gridy = 0;
		panel_3.add(engineSetInst.parameterBoundariesComboBox, gbc_comboBox_3);
		
		engineSetInst.propFinalEstimateCb = new JCheckBox("Bring Forward Initial Estimate");
		engineSetInst.propFinalEstimateCb.setFont(componentLablesFont);
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.anchor = GridBagConstraints.WEST;
		gbc_checkBox.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox.gridx = 1;
		gbc_checkBox.gridy = 3;
		add(engineSetInst.propFinalEstimateCb, gbc_checkBox);
		
		
		JCheckBox dummyCb = new JCheckBox("");
		dummyCb.setVisible(false);
		GridBagConstraints gbc_checkBox1 = new GridBagConstraints();
		gbc_checkBox1.anchor = GridBagConstraints.WEST;
		gbc_checkBox1.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox1.gridx = 1;
		gbc_checkBox1.gridy = 4;
		add(dummyCb, gbc_checkBox1);
		
		JCheckBox dummyCb1 = new JCheckBox("");
		dummyCb1.setVisible(false);
		GridBagConstraints gbc_checkBox2 = new GridBagConstraints();
		gbc_checkBox2.anchor = GridBagConstraints.WEST;
		gbc_checkBox2.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox2.gridx = 1;
		gbc_checkBox2.gridy = 6;
		add(dummyCb1, gbc_checkBox2);
}

}
