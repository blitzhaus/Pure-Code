package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.EventCodes;

public class InVitroParamOptsEngSetingsPanelCreator extends JPanel{

	private JPanel panel;
	

	
	Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	Font componentOptionseFontForComboBox = new Font("Arial", Font.PLAIN, 11);
	Font componentLablesFont = new Font("Arial", Font.BOLD, 11);

	final class EngSettingTextFieldActionEventHandler implements
			DocumentListener {
		public void removeUpdate(DocumentEvent arg0) {

			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			InVitroEngSettingDispGuiCreator inVitroEngSetInst = null;
			try {
				inVitroEngSetInst = InVitroEngSettingDispGuiCreator
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
				String temp = inVitroEngSetInst.incrementsForpartialDerivativesTextField
						.getText();

				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.numberOfPredictedValuesTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.convergenceCriteriaTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.numberOfIterationTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.meanSquareTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.lambdaValueTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.muTextField.getText();
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
			InVitroEngSettingDispGuiCreator inVitroEngSetInst = InVitroEngSettingDispGuiCreator
					.createEngineSettingInstance();
			ProcessingInputsInfo procInputInst = CaMapingHandler
					.createCaMapHandInst().copyProcessingInput();

			inVitroEngSetInst.incrementsForpartialDerivativesTextField
					.setText(procInputInst.getModelInputTab2Obj()
							.getIncForPartialDerivative());

			inVitroEngSetInst.numberOfPredictedValuesTextField.setText(procInputInst
					.getModelInputTab2Obj().getNumberOfPredictedValue());

			inVitroEngSetInst.convergenceCriteriaTextField.setText(procInputInst
					.getModelInputTab2Obj().getConvergenceCriteria());

			inVitroEngSetInst.numberOfIterationTextField.setText(procInputInst
					.getModelInputTab2Obj().getNumberOfIterations());

			inVitroEngSetInst.meanSquareTextField.setText(procInputInst
					.getModelInputTab2Obj().getMeanSquareValue());

			inVitroEngSetInst.lambdaValueTextField.setText(procInputInst
					.getModelInputTab2Obj().getLambdaValue());

			inVitroEngSetInst.muTextField.setText(procInputInst
					.getModelInputTab2Obj().getMuValue());

		}

		public void insertUpdate(DocumentEvent arg0) {
			InVitroEngSettingDispGuiCreator inVitroEngSetInst = null;
			try {
				inVitroEngSetInst = InVitroEngSettingDispGuiCreator
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
				String temp = inVitroEngSetInst.incrementsForpartialDerivativesTextField
						.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.numberOfPredictedValuesTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.convergenceCriteriaTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.numberOfIterationTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.meanSquareTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.lambdaValueTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.muTextField.getText();
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
			InVitroEngSettingDispGuiCreator inVitroEngSetInst = null;
			try {
				inVitroEngSetInst = InVitroEngSettingDispGuiCreator
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
				String temp = inVitroEngSetInst.incrementsForpartialDerivativesTextField
						.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.numberOfPredictedValuesTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.convergenceCriteriaTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.numberOfIterationTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.meanSquareTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.lambdaValueTextField.getText();
				if (temp.equals("")) {

				} else
					Double.parseDouble(temp);
				temp = inVitroEngSetInst.muTextField.getText();
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

		InVitroEngSettingDispGuiCreator inVitroEngSetInst = InVitroEngSettingDispGuiCreator
				.createEngineSettingInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst()
				.copyProcessingInput();

		if (inVitroEngSetInst.incrementsForpartialDerivativesTextField.getText()
				.equals("")) {

		} else
			procInputInst.getModelInputTab2Obj().setIncForPartialDerivative(
					inVitroEngSetInst.incrementsForpartialDerivativesTextField
							.getText());
		procInputInst.getModelInputTab2Obj().setNumberOfPredictedValue(
				inVitroEngSetInst.numberOfPredictedValuesTextField.getText());

		procInputInst.getModelInputTab2Obj().setConvergenceCriteria(
				inVitroEngSetInst.convergenceCriteriaTextField.getText());

		procInputInst.getModelInputTab2Obj().setNumberOfIterations(
				inVitroEngSetInst.numberOfIterationTextField.getText());

		procInputInst.getModelInputTab2Obj().setMeanSquareValue(
				inVitroEngSetInst.meanSquareTextField.getText());

		procInputInst.getModelInputTab2Obj().setLambdaValue(
				inVitroEngSetInst.lambdaValueTextField.getText());

		procInputInst.getModelInputTab2Obj().setMuValue(
				inVitroEngSetInst.muTextField.getText());
		
	}

	public void updateView(String str) {
		System.out.println("ParamOptionsEngSettingsPanel notified" + str);
	}

	
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws BiffException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public InVitroParamOptsEngSetingsPanelCreator() throws RowsExceededException, WriteException, BiffException, IOException {
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
		
		InVitroEngSettingDispGuiCreator inVitroEngSetInst = InVitroEngSettingDispGuiCreator
		.createEngineSettingInstance();
		
		
		
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

		inVitroEngSetInst.initialParameterValueComboBox = new JComboBox();
		inVitroEngSetInst.initialParameterValueComboBox.setFont(componentOptionseFontForComboBox);
		inVitroEngSetInst.initialParameterValueComboBox.addActionListener(DDViewLayer
				.createViewLayerInstance());
		inVitroEngSetInst.initialParameterValueComboBox
				.setActionCommand(EventCodes.IPVCB);
		inVitroEngSetInst.initialParameterValueComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Code generated initial parameter value",
				"User supplied initial parameter value" }));
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		//gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 3;
		gbc_comboBox_2.gridy = 0;
		panel_1.add(inVitroEngSetInst.initialParameterValueComboBox, gbc_comboBox_2);
		
			
		

		

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

		inVitroEngSetInst.minimizationMthodLabel = new JLabel("Minimization");
		inVitroEngSetInst.minimizationMthodLabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(inVitroEngSetInst.minimizationMthodLabel, gbc_lblNewLabel);

		inVitroEngSetInst.minimizationMthodComboBox = new JComboBox();
		inVitroEngSetInst.minimizationMthodComboBox.setFont(componentOptionseFontForComboBox);
		inVitroEngSetInst.minimizationMthodComboBox.addActionListener(DDViewLayer
				.createViewLayerInstance());
		inVitroEngSetInst.minimizationMthodComboBox
				.setActionCommand(EventCodes.MMCB);
		inVitroEngSetInst.minimizationMthodComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"GN (Lavenberg-Marquardt)", "GN(Hartley)", "Nelder-Mead" }));
		GridBagConstraints gbc_minComboBox = new GridBagConstraints();
		gbc_minComboBox.insets = new Insets(0, 0, 5, 0);
	//	gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_minComboBox.gridx = 2;
		gbc_minComboBox.gridy = 1;
		panel.add(inVitroEngSetInst.minimizationMthodComboBox, gbc_minComboBox);

		inVitroEngSetInst.odeSolverMethodLabel = new JLabel("ODE Solver Method");
		inVitroEngSetInst.odeSolverMethodLabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(inVitroEngSetInst.odeSolverMethodLabel, gbc_lblNewLabel_1);

		inVitroEngSetInst.odeSolverMethodComboBox = new JComboBox();
		inVitroEngSetInst.odeSolverMethodComboBox.setFont(componentOptionseFontForComboBox);
		inVitroEngSetInst.odeSolverMethodComboBox.addActionListener(DDViewLayer
				.createViewLayerInstance());
		inVitroEngSetInst.odeSolverMethodComboBox
				.setActionCommand(EventCodes.ODESCB);
		inVitroEngSetInst.odeSolverMethodComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Runge Kutta Fehlberg", "Runge Kutta Cashcarp" }));
		GridBagConstraints gbc_odeComboBox_1 = new GridBagConstraints();
		gbc_odeComboBox_1.insets = new Insets(0, 0, 5, 0);
	//	gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_odeComboBox_1.gridx = 2;
		gbc_odeComboBox_1.gridy = 2;
		panel.add(inVitroEngSetInst.odeSolverMethodComboBox, gbc_odeComboBox_1);

		inVitroEngSetInst.incrementsForpartialDerivativesLabel = new JLabel("Increments for partial derivative");
		inVitroEngSetInst.incrementsForpartialDerivativesLabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(inVitroEngSetInst.incrementsForpartialDerivativesLabel, gbc_lblNewLabel_2);

		inVitroEngSetInst.incrementsForpartialDerivativesTextField = new JTextField("0.0001");
		inVitroEngSetInst.incrementsForpartialDerivativesTextField.setFont(componentLablesFont);
		inVitroEngSetInst.incrementsForpartialDerivativesTextField.getDocument()
		.addDocumentListener(
				new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
	//	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		panel.add(inVitroEngSetInst.incrementsForpartialDerivativesTextField, gbc_textField);
		inVitroEngSetInst.incrementsForpartialDerivativesTextField.setColumns(10);

		inVitroEngSetInst.numberOfPredictedValuesLabel = new JLabel("Number of predicted values");
		inVitroEngSetInst.numberOfPredictedValuesLabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 4;
		panel.add(inVitroEngSetInst.numberOfPredictedValuesLabel, gbc_lblNewLabel_3);

		inVitroEngSetInst.numberOfPredictedValuesTextField = new JTextField("100");
		inVitroEngSetInst.numberOfPredictedValuesTextField.setFont(componentLablesFont);
		inVitroEngSetInst.numberOfPredictedValuesTextField.getDocument()
		.addDocumentListener(
				new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		//gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 4;
		panel.add(inVitroEngSetInst.numberOfPredictedValuesTextField, gbc_textField_1);
		inVitroEngSetInst.numberOfPredictedValuesTextField.setColumns(10);

		inVitroEngSetInst.convergenceCriterialabel = new JLabel("Convergence Criterion");
		inVitroEngSetInst.convergenceCriterialabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 5;
		panel.add(inVitroEngSetInst.convergenceCriterialabel, gbc_lblNewLabel_4);

		inVitroEngSetInst.convergenceCriteriaTextField = new JTextField("0.000001");
		inVitroEngSetInst.convergenceCriteriaTextField.setFont(componentLablesFont);
		inVitroEngSetInst.convergenceCriteriaTextField.getDocument()
				.addDocumentListener(
						new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		//gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 5;
		panel.add(inVitroEngSetInst.convergenceCriteriaTextField, gbc_textField_2);
		inVitroEngSetInst.convergenceCriteriaTextField.setColumns(10);

		inVitroEngSetInst.noOfIterLabel = new JLabel("Number of Iterations");
		inVitroEngSetInst.noOfIterLabel .setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 6;
		panel.add(inVitroEngSetInst.noOfIterLabel, gbc_lblNewLabel_5);

		inVitroEngSetInst.numberOfIterationTextField = new JTextField("100");
		inVitroEngSetInst.numberOfIterationTextField.setFont(componentLablesFont);
		inVitroEngSetInst.numberOfIterationTextField.getDocument()
				.addDocumentListener(
						new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		//gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 6;
		panel.add(inVitroEngSetInst.numberOfIterationTextField, gbc_textField_3);
		inVitroEngSetInst.numberOfIterationTextField.setColumns(10);

		inVitroEngSetInst.meanSquareLabel = new JLabel("Mean Square Value");
		inVitroEngSetInst.meanSquareLabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 7;
		panel.add(inVitroEngSetInst.meanSquareLabel, gbc_lblNewLabel_6);

		inVitroEngSetInst.meanSquareTextField = new JTextField();
		inVitroEngSetInst.meanSquareTextField.setFont(componentLablesFont);
		inVitroEngSetInst.meanSquareTextField.getDocument().addDocumentListener(
				new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		//gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 7;
		panel.add(inVitroEngSetInst.meanSquareTextField, gbc_textField_4);
		inVitroEngSetInst.meanSquareTextField.setColumns(10);

		inVitroEngSetInst.lambdaValueLabel = new JLabel("Lambda Value");
		inVitroEngSetInst.lambdaValueLabel.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 8;
		panel.add(inVitroEngSetInst.lambdaValueLabel, gbc_lblNewLabel_7);

		inVitroEngSetInst.lambdaValueTextField = new JTextField("0.01");
		inVitroEngSetInst.lambdaValueTextField.setFont(componentLablesFont);
		inVitroEngSetInst.lambdaValueTextField.getDocument().addDocumentListener(
				new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		//gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 8;
		panel.add(inVitroEngSetInst.lambdaValueTextField, gbc_textField_5);
		inVitroEngSetInst.lambdaValueTextField.setColumns(10);

		inVitroEngSetInst.muLable = new JLabel("mu Value");
		inVitroEngSetInst.muLable.setFont(componentLablesFont);
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 9;
		panel.add(inVitroEngSetInst.muLable, gbc_lblNewLabel_8);

		inVitroEngSetInst.muTextField = new JTextField("0.2");
		inVitroEngSetInst.muTextField.setFont(componentLablesFont);
		inVitroEngSetInst.muTextField.getDocument().addDocumentListener(
				new EngSettingTextFieldActionEventHandler());
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		//gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 2;
		gbc_textField_6.gridy = 9;
		panel.add(inVitroEngSetInst.muTextField, gbc_textField_6);
		inVitroEngSetInst.muTextField.setColumns(10);

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
		
		
		
		inVitroEngSetInst.initialParameterValueByCS = new JRadioButton(
				"Use Curve Stripping");
		inVitroEngSetInst.initialParameterValueByCS.setFont(componentLablesFont);
		inVitroEngSetInst.initialParameterValueByCS.addActionListener(DDViewLayer
				.createViewLayerInstance());
		inVitroEngSetInst.initialParameterValueByCS
				.setActionCommand(EventCodes.PEBCSRB);
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton.gridx = 1;
		gbc_rdbtnNewRadioButton.gridy = 0;
		panel_2.add(inVitroEngSetInst.initialParameterValueByCS,
				gbc_rdbtnNewRadioButton);
		
		
		
		
		inVitroEngSetInst.initialParameterValueByGA = new JRadioButton(
				"Use Genetic Algorithm");
		inVitroEngSetInst.initialParameterValueByGA.setFont(componentLablesFont);
		inVitroEngSetInst.initialParameterValueByGA.addActionListener(DDViewLayer
				.createViewLayerInstance());
		inVitroEngSetInst.initialParameterValueByGA
				.setActionCommand(EventCodes.PEBGARB);
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		//gbc_rdbtnNewRadioButton_1.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 2;
		gbc_rdbtnNewRadioButton_1.gridy = 0;
		panel_2.add(inVitroEngSetInst.initialParameterValueByGA,
				gbc_rdbtnNewRadioButton_1);

		
		
		
		inVitroEngSetInst.initialParameterValueByGS = new JRadioButton(
				"Use Grid Search");
		inVitroEngSetInst.initialParameterValueByGS.setFont(componentLablesFont);
		inVitroEngSetInst.initialParameterValueByGS.addActionListener(DDViewLayer
				.createViewLayerInstance());
		inVitroEngSetInst.initialParameterValueByGS
				.setActionCommand(EventCodes.PEBGSRB);
		GridBagConstraints gbc_rdbtnNewRadioButton_3 = new GridBagConstraints();
		//gbc_rdbtnNewRadioButton_1.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 0;
		gbc_rdbtnNewRadioButton_1.gridy = 1;
		panel_2.add(inVitroEngSetInst.initialParameterValueByGS,
				gbc_rdbtnNewRadioButton_3);

		inVitroEngSetInst.previouslyGenInitilParam = new JRadioButton(
				"Carry Forward Initial Values");
		inVitroEngSetInst.previouslyGenInitilParam.setFont(componentLablesFont);
		GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNewRadioButton_2.gridx = 1;
		gbc_rdbtnNewRadioButton_2.gridy = 1;
		panel_2.add(inVitroEngSetInst.previouslyGenInitilParam,
				gbc_rdbtnNewRadioButton_2);
		
		
		
		
		
		
		
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

		inVitroEngSetInst.parameterBoundariesComboBox = new JComboBox();
		inVitroEngSetInst.parameterBoundariesComboBox.setFont(componentOptionseFontForComboBox);
		inVitroEngSetInst.parameterBoundariesComboBox.addActionListener(DDViewLayer
				.createViewLayerInstance());
		inVitroEngSetInst.parameterBoundariesComboBox
				.setActionCommand(EventCodes.PBCB);
		inVitroEngSetInst.parameterBoundariesComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Code generated parameter bounds",
				"User supplied parameter bounds",
				"Do not use any parameter bounds" }));
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.insets = new Insets(0, 0, 0, 5);
		//gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 2;
		gbc_comboBox_3.gridy = 0;
		panel_3.add(inVitroEngSetInst.parameterBoundariesComboBox, gbc_comboBox_3);
		
		inVitroEngSetInst.propFinalEstimateCb = new JCheckBox("Bring Forward Initial Estimate");
		inVitroEngSetInst.propFinalEstimateCb.setFont(componentLablesFont);
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.anchor = GridBagConstraints.WEST;
		gbc_checkBox.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox.gridx = 1;
		gbc_checkBox.gridy = 3;
		add(inVitroEngSetInst.propFinalEstimateCb, gbc_checkBox);
		
		
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
