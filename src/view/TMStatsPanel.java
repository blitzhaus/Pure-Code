package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

class TableTemplateListSelectionListener implements
ListSelectionListener {
	
	JTable tblTemplateListing = null;
	
	TableTemplateListSelectionListener(JTable tblTemplateListing)
	{
		this.tblTemplateListing = tblTemplateListing;
	}
	
	public void valueChanged(ListSelectionEvent arg0)
	{
		if(arg0.getValueIsAdjusting() == false){
			Hashtable<String, Boolean> htStatOptions = new Hashtable<String, Boolean>();

			for (int i = 0; i < tblTemplateListing.getRowCount(); i++) {
				String cellLabel = (String) tblTemplateListing
						.getModel().getValueAt(i, 0);

				Boolean cellBool = (Boolean) tblTemplateListing
						.getModel().getValueAt(i, 1);

				htStatOptions.put(cellLabel, cellBool);
			}

			ApplicationInfo appInfo = DisplayContents
					.createAppInfoInstance();

			TableWizardOpMetricOptions tmOpMetOptions = new TableWizardOpMetricOptions(
					htStatOptions);
			appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
									.get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getTmInfo().setTmOpMetOptions(tmOpMetOptions);
			
		}
		
		
	}
}

public class TMStatsPanel extends JPanel {
	/*
	 * private JTable table; private JTable table_1; private JTable table_2;
	 */
	private JTable tblTemplateListing;
	private JTextField tfConfInterval;
	private JTextField tfNumOfStdDevn;
	
	JComboBox cbTemplates;
	
	private boolean restoreMode = false;
	
	public boolean isRestoreMode() {
		return restoreMode;
	}

	public void setRestoreMode(boolean restoreMode) {
		this.restoreMode = restoreMode;
	}

	/**
	 * Create the panel.
	 */
	public TMStatsPanel() {}
	
	void enableListVariablesInMapPanel()
	{
		TMMappingPanel tmMappingPanel = TableMavenCreateUI.createTMMappingPanel();
		tmMappingPanel.lstAvailVariables.setVisible(true);
		tmMappingPanel.lstCrossVariables.setVisible(true);
		tmMappingPanel.lstGrpVariables.setVisible(true);
		tmMappingPanel.lstIDVariables.setVisible(true);
		tmMappingPanel.lstVariables.setVisible(true);		
	}
	
	void createTMStatsPanel()
	{


		setLayout(null);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrDim = toolkit.getScreenSize();
		setSize(850, 400);

		
		tblTemplateListing = new JTable();
		//scrollPane.setViewportView(tblTemplateListing);
		tblTemplateListing.setToolTipText("Table Wizard Template Listing");
		tblTemplateListing.setFillsViewportHeight(true);
		tblTemplateListing.setModel(new DefaultTableModel(new Object[][] {
				{ "N", Boolean.FALSE }, { "Nmiss", Boolean.FALSE },
				{ "NObs", Boolean.FALSE }, { "Mean", Boolean.FALSE },
				{ "SD", Boolean.FALSE }, { "Variance", Boolean.FALSE },
				{ "Min", Boolean.FALSE }, { "Median", Boolean.FALSE },
				{ "Max", Boolean.FALSE }, { "Range", Boolean.FALSE },
				/*{ "Percentiles", Boolean.FALSE }, */{ "CV%", Boolean.FALSE },
				/*{ "Geometric Mean", Boolean.FALSE },
				{ "Harmonic Mean", Boolean.FALSE },*/
				/*{ "Mean Of Logs", Boolean.FALSE },
				{ "SD of Logs", Boolean.FALSE },*/
				{ "95% CI Lower Mean", Boolean.FALSE },
				{ "95% CI Upper Mean", Boolean.FALSE },
				/*{ "Lower 1 SD", Boolean.FALSE },*/
				{ "95% CI Lower Var", Boolean.FALSE },
				{ "95% CI Upper Var", Boolean.FALSE }, }, new String[] {
				"Statistic", "Display" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Object.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(tblTemplateListing,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 248, 365);
		add(scrollPane);

		tblTemplateListing.setSize(tblTemplateListing.getWidth() + 500,
				tblTemplateListing.getHeight() - 200);
		
		
		tblTemplateListing.getSelectionModel().addListSelectionListener(
				new TableTemplateListSelectionListener(tblTemplateListing));
			
		tfConfInterval = new JTextField();
		tfConfInterval.setBounds(358, 129, 112, 20);
		add(tfConfInterval);
		tfConfInterval.setColumns(10);
		
		tfConfInterval.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void changedUpdate(DocumentEvent e) {

				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				int projIdx = appInfo.getSelectedProjectIndex();

				TableMavenInfo tmInfo = appInfo
						.getProjectInfoAL()
						.get(projIdx)
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(projIdx)
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(projIdx)
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(projIdx)
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getTmInfo();

				tmInfo.setConfInterval(Integer.parseInt(tfConfInterval
						.getText()));

			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				int projIdx = appInfo.getSelectedProjectIndex();

				TableMavenInfo tmInfo = appInfo
						.getProjectInfoAL()
						.get(projIdx)
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(projIdx)
										.getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL()
						.get(
								appInfo
										.getProjectInfoAL()
										.get(projIdx)
										.getWorkBooksArrayList()
										.get(
												appInfo
														.getProjectInfoAL()
														.get(projIdx)
														.getSelectedWorkBookIndex())
										.getSelectedSheetIndex())
						.getTmInfo();

				tmInfo.setConfInterval(Integer.parseInt(tfConfInterval
						.getText()));

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		tfNumOfStdDevn = new JTextField();
		tfNumOfStdDevn.setColumns(10);
		tfNumOfStdDevn.setBounds(358, 183, 112, 20);
		add(tfNumOfStdDevn);
		
		
		tfNumOfStdDevn.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {


				ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
				int projIdx = appInfo.getSelectedProjectIndex();
				
				TableMavenInfo tmInfo = appInfo.getProjectInfoAL().get(projIdx)
				.getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo();

				tmInfo.setNumOfStdDeviations(Integer.parseInt(tfNumOfStdDevn.getText()));
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {


				ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
				int projIdx = appInfo.getSelectedProjectIndex();
				
				TableMavenInfo tmInfo = appInfo.getProjectInfoAL().get(projIdx)
				.getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getSelectedWorkBookIndex())
				.getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(projIdx)
								.getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(projIdx)
												.getSelectedWorkBookIndex())
								.getSelectedSheetIndex()).getTmInfo();

				tmInfo.setNumOfStdDeviations(Integer.parseInt(tfNumOfStdDevn.getText()));
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		JLabel lblNewLabel = new JLabel("Confidence Interval");
		lblNewLabel.setBounds(358, 101, 117, 24);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Number of SD");
		lblNewLabel_1.setBounds(358, 161, 112, 23);
		add(lblNewLabel_1);

		JButton btnSelectAll = new JButton("Select All");
		btnSelectAll.setBounds(358, 225, 112, 23);
		add(btnSelectAll);

		btnSelectAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				System.out.println("btnSelectAll component clicked.");

			}
		});

		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.setBounds(358, 270, 112, 23);
		add(btnClearAll);

		btnClearAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("btnClearAll component clicked.");

			}
		});
		
		JLabel lblTemplateType = new JLabel("Select Template Type");
		lblTemplateType.setBounds(488, 101, 200, 25);
		add(lblTemplateType);

		cbTemplates = new JComboBox();
		cbTemplates.setModel(new DefaultComboBoxModel(new String[] {
				"Template_1", "Template_2", "Template_3", "Template_4"}));
		cbTemplates.setBounds(488, 130, 200, 23);
		
	//	cbTemplates.setBorder(BorderFactory.createTitledBorder("Select Template Type"));

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
					enableListVariablesInMapPanel();
					TMMappingPanel tmMappingPanel = TableMavenCreateUI.createTMMappingPanel();
					tmMappingPanel.lstCrossVariables.setVisible(false);
					tmMappingPanel.lstIDVariables.setVisible(false);
					
				} else if (tempType == 1) {
					enableListVariablesInMapPanel();
					TMMappingPanel tmMappingPanel = TableMavenCreateUI.createTMMappingPanel();
					tmMappingPanel.lstCrossVariables.setVisible(false);
						
				} else if (tempType == 2) {
					enableListVariablesInMapPanel();
					TMMappingPanel tmMappingPanel = TableMavenCreateUI.createTMMappingPanel();
					tmMappingPanel.lstCrossVariables.setVisible(false);
					
				} else if (tempType == 3) {
					enableListVariablesInMapPanel();
					System.out.println("I am in cb template 6 selection");
				}
				
				TMSetupDispComp.createTmSetupDispCompInst().tmSetupMappingDispCompIF
				.moveToFront();				
				TMSetupAvailComp.createTmSetupAvailCompInst().tree.setSelectionRow(1);

			}
		});
		//add(lblTemplateType);
		add(cbTemplates);	
	}
	
	void resetStatCompsWithAppInfo()
	{		
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		TableWizardOpMetricOptions tblWizOpMetOptions = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
		.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL()
		.get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex())
		.getTmInfo().getTmOpMetOptions();
		
		Hashtable<String,Boolean> statMetOptions = tblWizOpMetOptions.getStatMetricsListing();
		
		for (int i = 0; i < tblTemplateListing.getRowCount(); i++) {
			String cellLabel = (String) tblTemplateListing
					.getModel().getValueAt(i, 0);

			tblTemplateListing
					.getModel().setValueAt(statMetOptions.get((String) cellLabel), i, 1);
		}
		
		int tempType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
		.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL()
		.get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex())
		.getTmInfo().getTemplateType();
		cbTemplates.setSelectedIndex(tempType);
		
		int confInterval = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
		.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL()
		.get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex())
		.getTmInfo().getConfInterval();
		
		int numOfStdDeviations = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
		.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
		.getWorkSheetObjectsAL()
		.get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getSelectedSheetIndex())
		.getTmInfo().getNumOfStdDeviations();
		
		tfConfInterval.setText(confInterval+"");
		tfNumOfStdDevn.setText(numOfStdDeviations+"");		
	}
}
