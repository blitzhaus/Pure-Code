package view;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

import javax.swing.JList;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.io.IOException;

import javax.swing.border.BevelBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.AbstractListModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CarryAlongVarListPanelCreator extends JPanel {

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 * @throws BiffException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	CaMappingDispGuiCreator mappingInst;

	public CarryAlongVarListPanelCreator(String grpLabel)
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		createCarryAlongPanel(grpLabel);
	}

	public void createCarryAlongPanel(String grpLabel)
			throws RowsExceededException, WriteException, BiffException,
			IOException {

		setBorder(new CompoundBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), grpLabel,
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)), new LineBorder(null)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 0.0,
				1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		mappingInst = CaMappingDispGuiCreator.createMappingInstance();

		Icon icon = new ImageIcon("rightglossy.jpg");
		mappingInst.moveToCarryAlongButton = new JButton();
		mappingInst.moveToCarryAlongButton.setIcon(icon);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 5;
		gbc_button.anchor = GridBagConstraints.CENTER;
		add(mappingInst.moveToCarryAlongButton, gbc_button);

		mappingInst.carryAlongVariableListmodel = new DefaultListModel();
		mappingInst.carryAlongList = new JList(
				mappingInst.carryAlongVariableListmodel);

		/*
		 * list.setModel(new AbstractListModel() { String[] values = new
		 * String[] {}; public int getSize() { return values.length; } public
		 * Object getElementAt(int index) { return values[index]; } });
		 */
		JScrollPane scrlPane = new JScrollPane(mappingInst.carryAlongList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 6;
		gbc_list.gridy = 5;
		gbc_list.anchor = GridBagConstraints.CENTER;

		add(scrlPane, gbc_list);

	}

	/*
	 * public static void main(String[] args) throws RowsExceededException,
	 * WriteException, BiffException, IOException {
	 * CarryAlongVarListPanelCreator tmMapPanel = new
	 * CarryAlongVarListPanelCreator("Sort Variables");
	 * 
	 * JFrame frame = new JFrame();
	 * 
	 * frame.setTitle("Parameter Options-Engine Settings Panel");
	 * 
	 * frame.setLocationRelativeTo(null);
	 * //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	 * 
	 * Toolkit toolkit = Toolkit.getDefaultToolkit();
	 * 
	 * Dimension dimen = toolkit.getScreenSize();
	 * 
	 * tmMapPanel.setSize((int) dimen.getWidth()/2, (int) dimen.getHeight()/2);
	 * 
	 * frame.setSize(tmMapPanel.getWidth(), tmMapPanel.getHeight()+50);
	 * 
	 * frame.getContentPane().add(tmMapPanel);
	 * 
	 * frame.setVisible(true);
	 * 
	 * }
	 */

}
