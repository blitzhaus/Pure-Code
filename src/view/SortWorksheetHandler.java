package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

import com.itextpdf.text.DocumentException;

public class SortWorksheetHandler {
	
	void createSortWSOptionsDialog()
	{
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		JFrame frame = new JFrame("Analysis Results Storage Options");
		
		JPanel pnlForPrintTreebtn = new JPanel();

		JScrollPane scrollPane = new JScrollPane(pnlForPrintTreebtn);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.getContentPane().add(pnlForPrintTreebtn, BorderLayout.SOUTH);

		
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}


	public void handleSortWorkSheet() {
		System.out.println("I am in handleSortWorkSheet()");
		//createSortWSOptionsDialog();
		TableComboBoxByRow tblCbByRow = new TableComboBoxByRow();
		//tblCbByRow.pack();
		tblCbByRow.setVisible(true);
		tblCbByRow.setLocationRelativeTo(null);
	}

}
