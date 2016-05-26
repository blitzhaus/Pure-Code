package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.border.Border;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class ParamOptionsEngSettingsPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	public void updateView(String str)
	{
		System.out.println("ParamOptionsEngSettingsPanel notified"+str);
	}
	public ParamOptionsEngSettingsPanel() {
		
		setLayout(null);
		
		JPanel pnlSrcForInitParam = new JPanel();
		pnlSrcForInitParam.setBounds(18, 30, 297, 50);
		add(pnlSrcForInitParam);
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		
		Border titBorderSrcInitParams = BorderFactory.createTitledBorder("Source for Initial Parameters");
		titBorderSrcInitParams = BorderFactory.createCompoundBorder(titBorderSrcInitParams, raisedbevel);
		pnlSrcForInitParam.setBorder(titBorderSrcInitParams);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Code Generated Initial Parameter Value", "User Supplied Initial Parameter Value"}));
		pnlSrcForInitParam.add(comboBox);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Use Curve Stripping");
		rdbtnNewRadioButton_2.setBounds(41, 144, 187, 23);
		add(rdbtnNewRadioButton_2);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Propagate Final Estimate");
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox.setBounds(18, 352, 187, 23);
		add(chckbxNewCheckBox);
		
		JPanel pnlParamBounds = new JPanel();
		pnlParamBounds.setBounds(10, 265, 289, 40);
		
		
		Border titleBorderParamBoundary = BorderFactory.createTitledBorder("Parameter Boundary");
		titleBorderParamBoundary = BorderFactory.createCompoundBorder(titleBorderParamBoundary, raisedbevel);
		pnlParamBounds.setBorder(titleBorderParamBoundary);
		
		add(pnlParamBounds);
		
		
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Code Generated Parameter Bounds", "User Supplied Parameter Bounds", "Do not use any Parameter Bounds"}));
		pnlParamBounds.add(comboBox_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Use Genetic Algorithm");
		rdbtnNewRadioButton.setBounds(41, 170, 159, 23);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Previously Generated Initial Value");
		rdbtnNewRadioButton_1.setBounds(41, 195, 230, 23);
		add(rdbtnNewRadioButton_1);
		
		JPanel pnlIPEMethods = new JPanel();
		pnlIPEMethods.setBounds(18, 115, 289, 128);
		add(pnlIPEMethods);
		
		Border titleBorderInitValEstTechniks = BorderFactory.createTitledBorder("Initial Value Estimation Techniques");
		titleBorderInitValEstTechniks = BorderFactory.createCompoundBorder(titleBorderInitValEstTechniks, raisedbevel);
		
		pnlIPEMethods.setBorder(titleBorderInitValEstTechniks);
		
		JLabel lblNewLabel = new JLabel("Minimization");
		lblNewLabel.setBounds(351, 41, 101, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ODE Solver Method");
		lblNewLabel_1.setBounds(351, 69, 114, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("<html> Increments for <br>Partial Derivative</html>");
		lblNewLabel_2.setBounds(351, 106, 114, 29);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("<html>Number of <br>Predicted Values</html>");
		lblNewLabel_3.setBounds(351, 146, 101, 29);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("<html>Convergence <br>Criterion</html>");
		lblNewLabel_4.setBounds(351, 192, 101, 36);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("<html>Number of <br> iterations</html>");
		lblNewLabel_5.setBounds(351, 243, 101, 29);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Mean Square Value");
		lblNewLabel_6.setBounds(351, 289, 114, 14);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Lambda Value");
		lblNewLabel_7.setBounds(351, 324, 86, 14);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("mu Value");
		lblNewLabel_8.setBounds(351, 361, 101, 14);
		add(lblNewLabel_8);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"GN (Lavenberg_Marquardt)", "Nelder_Mead", "GN (Hartley)"}));
		comboBox_2.setBounds(475, 38, 180, 20);
		add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Runge Kutta Fehlberg", "Runge Kutta Cash Karp"}));
		comboBox_3.setBounds(475, 66, 180, 20);
		add(comboBox_3);
		
		textField = new JTextField();
		textField.setBounds(475, 112, 87, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(475, 146, 87, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(475, 195, 87, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(475, 240, 87, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(475, 286, 87, 20);
		add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(475, 321, 87, 20);
		add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(475, 358, 87, 20);
		add(textField_6);
		textField_6.setColumns(10);
		
		JPanel pnlInitialValue = new JPanel();
		pnlInitialValue.setBounds(10, 11, 312, 387);
		add(pnlInitialValue);
		
		Border titleBorderInitValEstimation = BorderFactory.createTitledBorder("Initial Parameters");
		titleBorderInitValEstimation = BorderFactory.createCompoundBorder(titleBorderInitValEstimation, raisedbevel);
		
		pnlInitialValue.setBorder(titleBorderInitValEstimation);
		
		JPanel pnlOptimizedValue = new JPanel();
		pnlOptimizedValue.setBounds(332, 11, 335, 387);
		add(pnlOptimizedValue);
		
		Border titleBorderOptParamEstimation = BorderFactory.createTitledBorder("Optimized Parameters");
		titleBorderOptParamEstimation = BorderFactory.createCompoundBorder(titleBorderOptParamEstimation, raisedbevel);
		
		pnlOptimizedValue.setBorder(titleBorderOptParamEstimation);
	}
	
	public static void main(String[] args) {
		ParamOptionsEngSettingsPanel tmMapPanel = new ParamOptionsEngSettingsPanel();
		
		JFrame frame = new JFrame();
		
		frame.setTitle("Parameter Options-Engine Settings Panel");
		
		frame.setLocationRelativeTo(null);
		//frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		Dimension dimen = toolkit.getScreenSize();
		
		tmMapPanel.setSize((int) dimen.getWidth()/2, (int) dimen.getHeight()/2);
		
		frame.setSize(tmMapPanel.getWidth()+50, tmMapPanel.getHeight()-50);
		
		frame.getContentPane().add(tmMapPanel);
		
		frame.setVisible(true);
		
	}
}
