package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JCheckBox;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class TMOptionsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TMOptionsPanel() {
		setLayout(null);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrDim = toolkit.getScreenSize();
		setSize(850, 400);

		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Table") {
			{
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode("Precision Alignment");
				node_1.add(new DefaultMutableTreeNode("Treatment"));
				node_1.add(new DefaultMutableTreeNode("Phase"));
				node_1.add(new DefaultMutableTreeNode("Analyte"));
				node_1.add(new DefaultMutableTreeNode("Time"));
				node_1.add(new DefaultMutableTreeNode("Subject_Id"));
				node_1.add(new DefaultMutableTreeNode(
						"Plasma_Conc_of_analyte\t\t"));
				add(node_1);
				add(new DefaultMutableTreeNode("Captions"));
				add(new DefaultMutableTreeNode("Column Titles"));
				add(new DefaultMutableTreeNode("Table Body"));
				add(new DefaultMutableTreeNode("Statistics"));
				add(new DefaultMutableTreeNode("Footers"));
			}
		}));

		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				
			}
		});

		JScrollPane scrollPane = new JScrollPane(tree,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(34, 45, 140, 214);
		add(scrollPane);

		JCheckBox chckbxIncludeUnits = new JCheckBox("Include Units");
		chckbxIncludeUnits.setBounds(193, 57, 247, 23);
		add(chckbxIncludeUnits);

		chckbxIncludeUnits.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				

			}
		});

		JCheckBox chckbxPageBreakOnRS = new JCheckBox(
				"Page Break on Row Stratification");
		chckbxPageBreakOnRS.setBounds(193, 148, 247, 23);
		add(chckbxPageBreakOnRS);

		chckbxPageBreakOnRS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				

			}
		});

		JCheckBox chckbxRemoveEmptyCols = new JCheckBox("Remove Empty Columns");
		chckbxRemoveEmptyCols.setBounds(193, 103, 247, 23);
		add(chckbxRemoveEmptyCols);
		chckbxRemoveEmptyCols.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				

			}
		});

		JCheckBox chckbxCaptionsOnFirstPage = new JCheckBox(
				"Captions on First Page Only");
		chckbxCaptionsOnFirstPage.setBounds(193, 195, 247, 23);
		add(chckbxCaptionsOnFirstPage);

		chckbxCaptionsOnFirstPage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				

			}
		});

		JLabel lblNewLabel = new JLabel("Select Template Type");
		lblNewLabel.setBounds(473, 103, 200, 25);
		add(lblNewLabel);

		final JComboBox cbTemplates = new JComboBox();
		cbTemplates.setModel(new DefaultComboBoxModel(new String[] {
				"Template_1", "Template_2", "Template_3", "Template_4"/*,
				"Template_5", "Template_6", "Template_7", "Template_8" */}));
		cbTemplates.setBounds(473, 150, 200, 23);

		cbTemplates.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				
				int templateType = cbTemplates.getSelectedIndex();
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getTmInfo()
						.setTemplateType(templateType);

				int tempType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getTmInfo()
						.getTemplateType();

				if (tempType == 0) {
					TMMappingPanel tmMappingPanel = TableMavenCreateUI
							.createTMMappingPanel();
					tmMappingPanel.lstCrossVariables.setVisible(false);
					tmMappingPanel.lstIDVariables.setVisible(false);

					tmMappingPanel.spCrossVar.setVisible(false);
					tmMappingPanel.spIdVar.setVisible(false);

					tmMappingPanel.spCrossVar.revalidate();
					tmMappingPanel.spIdVar.revalidate();
					
					TMMappingPanel tmPanel = TableMavenCreateUI.createTMMappingPanel();
					tmPanel.lstCrossVariables.setEnabled(false);
					tmPanel.lstIDVariables.setEnabled(false);
					
					
				} else if (tempType == 1) {
					TMMappingPanel tmMappingPanel = TableMavenCreateUI
							.createTMMappingPanel();
					tmMappingPanel.lstCrossVariables.setVisible(false);
					tmMappingPanel.lstCrossVariables.setEnabled(false);
					tmMappingPanel.spCrossVar.revalidate();
				} else if (tempType == 2) {
					TMMappingPanel tmMappingPanel = TableMavenCreateUI
							.createTMMappingPanel();
					tmMappingPanel.lstCrossVariables.setFocusable(false);
							tmMappingPanel.spCrossVar.revalidate();
				} else if (tempType == 3) {
					System.out.println("I am in cb template 6 selection");
					/*
					 * TMMappingPanel tmMappingPanel =
					 * TableMavenCreateUI.createTMMappingPanel();
					 * tmMappingPanel.lstCrossVariables.setVisible(false);
					 * tmMappingPanel.spCrossVar.revalidate();
					 */
				}

				TMSetupDispComp.createTmSetupDispCompInst().tmSetupMappingDispCompIF
						.moveToFront();
				//TMSetupAvailComp.createTmSetupAvailCompInst().tree.setSelectionRow(1);

			}
		});
		add(cbTemplates);

	}

}
