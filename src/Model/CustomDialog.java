package Model;

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.beans.*; //Property change stuff
import java.awt.*;
import java.awt.event.*;

public class CustomDialog extends JDialog {
	private String typedText = null;

	private String magicWord;
	private JOptionPane optionPane;

	public String getValidatedText() {
		return typedText;
	}

	public CustomDialog(Frame aFrame, String aWord) {
		super(aFrame, true);

		magicWord = aWord.toUpperCase();
		setTitle("Quiz");

		PkPdInfo pkpdInst= PkPdInfo.createPKPDInstance();
		final String msgString1 = "What was Dr. SEUSS's real last name?";
		final String msgString2 = "(The answer is \"" + magicWord + "\".)";
		final JTextField textField = new JTextField(10);
		Object[] array = new Object[pkpdInst.parameter.length + 2];
		array[0] = "Calculated Initial Parameter Value";
		for (int i = 0; i < pkpdInst.parameter.length; i++)
			array[i + 1] = pkpdInst.parameter[i];
		array[array.length - 1] = textField;
		final String btnString1 = "Enter";
		final String btnString2 = "Cancel";
		Object[] options = { btnString1, btnString2 };

		optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE,
				JOptionPane.YES_NO_OPTION, null, options, options[0]);
		setContentPane(optionPane);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				optionPane.setValue(new Integer(JOptionPane.CLOSED_OPTION));
			}
		});

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionPane.setValue(btnString1);
			}
		});

		optionPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				String prop = e.getPropertyName();

				if (isVisible()
						&& (e.getSource() == optionPane)
						&& (prop.equals(JOptionPane.VALUE_PROPERTY) || prop
								.equals(JOptionPane.INPUT_VALUE_PROPERTY))) {
					Object value = optionPane.getValue();

					if (value == JOptionPane.UNINITIALIZED_VALUE) {
						// ignore reset
						return;
					}

					if (value.equals(btnString1)) {
						typedText = textField.getText();
						String ucText = typedText.toUpperCase();
						if (ucText.equals(magicWord)) {
							// we're done; dismiss the dialog
							setVisible(false);
						} else {
							// text was invalid
							textField.selectAll();
							JOptionPane
									.showMessageDialog(
											CustomDialog.this,
											"Sorry, \""
													+ typedText
													+ "\" "
													+ "isn't a valid response.\n"
													+ "Please enter "
													+ magicWord + ".",
											"Try again",
											JOptionPane.ERROR_MESSAGE);
							typedText = null;
							// reset the JOptionPane's value
							optionPane
									.setValue(JOptionPane.UNINITIALIZED_VALUE);
						}
					} else {
					}
				}
			}
		});
	}
}
