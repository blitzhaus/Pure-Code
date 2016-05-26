package Model;

import javax.swing.*; //Java Extension Package  
import java.awt.*;
import java.awt.event.*;

public class simpleProgressBar extends JFrame {

	public class threadPlugin extends Thread {

		int Delay = 100; // Creating a delay or the speed of the progress bar
		JProgressBar pb; // Constructing Progress Bar

		// Creating a threadPlugin Method initializing JProgressBar so the Main
		// Program "simpleProgressBar.java"
		// can recognize by the time we call this class for JProgressBar action.
		public threadPlugin(JProgressBar progressbar) {
			pb = progressbar;
		}

		// run Method. This is the area where we can adjust the performance of
		// our progress bar.
		public void run() {
			int minimum = pb.getMinimum(); // initializing minimum value of the
											// progress bar
			int maximum = pb.getMaximum(); // initializing maximum value of the
											// progress bar

			// Initializing Progress from its minimum value 0 to its maximum
			// value 100
			for (int i = minimum; i < maximum; i++) {
				try {
					int value = pb.getValue();
					pb.setValue(value + 1);

					// Testing the progress bar if it already reaches to its
					// maximum value
					if (pb.getValue() >= maximum) {

						// Test confirmation if it runs perfectly
						JOptionPane.showMessageDialog(null, "Test Successful!",
								"Success!", JOptionPane.INFORMATION_MESSAGE);
					}

					Thread.sleep(Delay); // Implementing the speed of the
											// progress bar
				} catch (InterruptedException ignoredException) { // Catch an
																	// error if
																	// there is
																	// any
				}
			}
		}
	}

	// Constructing JProgressBar and JButton
	JProgressBar bar = new JProgressBar();
	JButton button = new JButton("Test Progress Bar");

	// Setting up GUI
	public simpleProgressBar() {

		// Setting up the Title of the Window
		super("Creating a Simple Progress Bar");

		// Set Size of the Window (WIDTH, HEIGHT)
		setSize(350, 100);

		// Exit Property of the Window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Setting up the container ready for the components to be added.
		Container pane = getContentPane();
		setContentPane(pane);

		// Display the progress bar completion percentage label
		bar.setStringPainted(true);

		// Setting up the container layout
		GridLayout grid = new GridLayout(2, 1);
		pane.setLayout(grid);

		// Adding the progress bar and the button to the container
		pane.add(bar);
		pane.add(button);

		// Implemeting Even-Listener on JButton
		button.addActionListener(new ActionListener() {

			// Handle JButton event if it is clicked
			public void actionPerformed(ActionEvent event) {
				button.setEnabled(false); // Disable the JButton if the progress
											// bar starts the progress
				Thread run = new threadPlugin(bar); // Calling the class
													// "threadPlugin" we created
													// that extends with Thread
				run.start(); // run the thread to start the progress
			}
		});

		setVisible(true);
	}

	// Main Method
	public void main(String[] args) {
		simpleProgressBar spb = new simpleProgressBar();
	}
}