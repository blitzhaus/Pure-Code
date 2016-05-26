package view;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class TMMappingPanel extends JPanel {

	JButton btnGrpVar;
	JButton btnCrossVar;
	JButton btnIdVar;
	JButton btnVar;

	JList lstAvailVariables = null;
	JList lstGrpVariables = null;
	JList lstCrossVariables = null;
	JList lstIDVariables = null;
	JList lstVariables = null;

	JScrollPane spCrossVar = null;
	JScrollPane spOrdVar = null;
	JScrollPane spIdVar = null;
	JScrollPane spGroupVar = null;
	
	//private boolean restoreMode = false;
	
	/*public boolean isRestoreMode() {
		return restoreMode;
	}

	public void setRestoreMode(boolean restoreMode) {
		this.restoreMode = restoreMode;
	}
*/
	/**
	 * Create the panel.
	 */
	public TMMappingPanel() {}
	
	public JList removeSelectedListItems(JList subjectList)
	{
		DefaultListModel dlm = (DefaultListModel) subjectList.getModel();

	      if(subjectList.getSelectedIndices().length > 0) {
	          int[] selectedIndices = subjectList.getSelectedIndices();
	          for (int i = selectedIndices.length-1; i >=0; i--) {
	              dlm.removeElementAt(selectedIndices[i]);
	          } 
	    } 
	      
	    return subjectList;
	}
	
	public void createTMMappingPanel()
	{


		// JComboBox comboBox = new JComboBox();
		setLayout(null);
		// add(comboBox);

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		int templateType = appInfo
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
								.getSelectedSheetIndex()).getTmInfo()
				.getTemplateType();

		JLabel lblGroupVar = new JLabel("Group Variables");
		lblGroupVar.setBounds(303, 17, 122, 28);
		add(lblGroupVar);

		JLabel lblCrossVar = new JLabel("Cross Variables");
		lblCrossVar.setBounds(543, 17, 122, 28);
		add(lblCrossVar);

		JLabel lblIdVariables = new JLabel("ID Variables");
		lblIdVariables.setBounds(303, 144, 122, 28);
		add(lblIdVariables);

		JLabel lblVariable = new JLabel("Variable");
		lblVariable.setBounds(543, 144, 97, 28);
		add(lblVariable);

		JLabel lblNewLabel_2 = new JLabel("Available Variables");
		lblNewLabel_2.setBounds(25, 21, 115, 20);
		add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(25, 51, 142, 275);
		add(scrollPane);
		
		lstAvailVariables = new JList();
		final DefaultListModel defListAvailModel = new DefaultListModel();


		ArrayList<ColumnProperties> arrListOfColProps = appInfo
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
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList();

		String[] colNames = new String[arrListOfColProps.size()];
		for (int i = 0; i < arrListOfColProps.size(); i++) {
			ColumnProperties colProp = arrListOfColProps.get(i);
			colNames[i] = colProp.getColumnName();
			System.out.println();

			defListAvailModel.addElement(colNames[i]);
		}

		
		lstAvailVariables.setModel(defListAvailModel);
		lstAvailVariables.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		//lstAvailVariables.setListData(colNames);

		lstAvailVariables.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub

				btnIdVar.setIcon(new ImageIcon("right.png"));
				btnGrpVar.setIcon(new ImageIcon("right.png"));
				btnCrossVar.setIcon(new ImageIcon("right.png"));
				btnVar.setIcon(new ImageIcon("right.png"));
				;
			}
		});
		scrollPane.setViewportView(lstAvailVariables);

		spGroupVar = new JScrollPane();
		spGroupVar
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spGroupVar
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spGroupVar.setBounds(303, 56, 127, 57);
		add(spGroupVar);

		final DefaultListModel deflistGrpModel = new DefaultListModel();
		lstGrpVariables = new JList();

		lstGrpVariables.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				btnGrpVar.setIcon(new ImageIcon("left.png"));
			}

		});
		spGroupVar.setViewportView(lstGrpVariables);

		spCrossVar = new JScrollPane();
		spCrossVar
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spCrossVar
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spCrossVar.setBounds(543, 56, 127, 77);
		add(spCrossVar);

		lstCrossVariables = new JList();
		final DefaultListModel deflistCrossModel = new DefaultListModel();

		lstCrossVariables.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				btnCrossVar.setIcon(new ImageIcon("left.png"));
			}

		});
		spCrossVar.setViewportView(lstCrossVariables);

		spIdVar = new JScrollPane();
		spIdVar
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spIdVar
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spIdVar.setBounds(303, 171, 127, 77);
		add(spIdVar);

		lstIDVariables = new JList();
		final DefaultListModel defListIDModel = new DefaultListModel();

		lstIDVariables.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				btnIdVar.setIcon(new ImageIcon("left.png"));

				
			
			}
		});
		spIdVar.setViewportView(lstIDVariables);

		spOrdVar = new JScrollPane();
		spOrdVar
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spOrdVar
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spOrdVar.setBounds(543, 171, 124, 74);
		add(spOrdVar);

		lstVariables = new JList();
		final DefaultListModel deflistOrdModel = new DefaultListModel();
		spOrdVar.setViewportView(lstVariables);

		btnGrpVar = new JButton();
		btnGrpVar.setBackground(null);
		btnGrpVar.setBorder(null);
		btnGrpVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (lstAvailVariables.getSelectedIndex() != -1){
					Object[] selListings = lstAvailVariables.getSelectedValues();

					Vector v = new Vector();
					
					for (int i = 0; i < selListings.length; i++) {
						deflistGrpModel.addElement((String) selListings[i]);
					}
					lstGrpVariables.setModel(deflistGrpModel);
					lstAvailVariables = removeSelectedListItems(lstAvailVariables);
					listGroupVariableFunctionality();
				}				
				
				if (lstGrpVariables.getSelectedIndex() != -1){
					Object[] selListings = lstGrpVariables.getSelectedValues();

					for (int i = 0; i < selListings.length; i++) {
						defListAvailModel.addElement((String) selListings[i]);
					}
					
					lstAvailVariables.setModel(defListAvailModel);
					
					lstGrpVariables = removeSelectedListItems(lstGrpVariables);
					listGroupVariableFunctionality();
				}
				
			}
		});
		btnGrpVar.setIcon(new ImageIcon("right.png"));
		btnGrpVar.setBounds(209, 75, 46, 36);
		add(btnGrpVar);

		btnIdVar = new JButton();
		btnIdVar.setBackground(null);
		btnIdVar.setBorder(null);
		btnIdVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (lstAvailVariables.getSelectedIndex() != -1){
					Object[] selListings = lstAvailVariables.getSelectedValues();

					for (int i = 0; i < selListings.length; i++) {
						String selListing = (String) selListings[i];
						defListIDModel.addElement((String) selListings[i]);
					}

					lstIDVariables.setModel(defListIDModel);
					lstAvailVariables = removeSelectedListItems(lstAvailVariables);
					listIdVariablesFunctionality();
				}
				
				if (lstIDVariables.getSelectedIndex() != -1) {

					Object[] selListings = lstIDVariables.getSelectedValues();
					
					for (int i = 0; i < selListings.length; i++) {
						defListAvailModel.addElement((String) selListings[i]);
					}
					lstAvailVariables.setModel(defListAvailModel);
					lstIDVariables = removeSelectedListItems(lstIDVariables);
					listIdVariablesFunctionality();
				}				
			}
		});
		btnIdVar.setIcon(new ImageIcon("right.png"));
		btnIdVar.setBounds(209, 205, 46, 36);
		add(btnIdVar);

		btnCrossVar = new JButton();
		btnCrossVar.setBorder(null);
		btnCrossVar.setBackground(null);
		btnCrossVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (lstAvailVariables.getSelectedIndex() != -1){
					
					Object[] selListings = lstAvailVariables.getSelectedValues();

					Vector v = new Vector();

					for (int i = 0; i < selListings.length; i++) {
						//v.add((String) selListings[i]);
						deflistCrossModel.addElement((String) selListings[i]);
					}

					lstCrossVariables.setModel(deflistCrossModel);
					lstAvailVariables = removeSelectedListItems(lstAvailVariables);
					listCrossVariableFunctionality();					
				}
				
				if (lstCrossVariables.getSelectedIndex() != -1){
					
					Object[] selListings = lstCrossVariables.getSelectedValues();

					
					for (int i = 0; i < selListings.length; i++) {
						defListAvailModel.addElement((String) selListings[i]);
					}
					
					lstAvailVariables.setModel(defListAvailModel);
					
					lstCrossVariables = removeSelectedListItems(lstCrossVariables);
					listCrossVariableFunctionality();				
				}

				
			}
		});
		btnCrossVar.setIcon(new ImageIcon("right.png"));
		btnCrossVar.setBounds(463, 75, 46, 36);
		add(btnCrossVar);

		btnVar = new JButton();
		btnVar.setBackground(null);
		btnVar.setBorder(null);
		btnVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (lstAvailVariables.getSelectedIndex() != -1){
					Object[] selListings = lstAvailVariables.getSelectedValues();

					Vector v = new Vector();

					for (int i = 0; i < selListings.length; i++) {
						deflistOrdModel.addElement((String) selListings[i]);
					}
					
					lstVariables.setModel(deflistOrdModel);
					lstAvailVariables = removeSelectedListItems(lstAvailVariables);
					listOrdinaryVariableFunctionality();
				}
				
				if (lstVariables.getSelectedIndex() != -1){
					
					Object[] selListings = lstVariables.getSelectedValues();

					for (int i = 0; i < selListings.length; i++) {
						defListAvailModel.addElement((String) selListings[i]);
					}
					
					lstAvailVariables.setModel(defListAvailModel);
					
					lstVariables = removeSelectedListItems(lstVariables);
					listOrdinaryVariableFunctionality();	
				}
				
			}
		});
		btnVar.setIcon(new ImageIcon("right.png"));
		btnVar.setBounds(463, 205, 46, 36);
		add(btnVar);

		lstVariables.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				btnVar.setIcon(new ImageIcon("left.png"));
			}

		});

		if (templateType == 0) {
			lstIDVariables.setEnabled(false);
			lstCrossVariables.setEnabled(false);
		}
	}
	
	void resetCrossVarsWithRestore() {

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

		ArrayList<String> crossVars = tmInfo.getCrossVariables();

		Vector v = new Vector();

		for (int i = 0; i < crossVars.size(); i++) {
			v.add(crossVars.get(i));
		}

		lstCrossVariables.setListData(v);

	}
	
	void resetIdVarsWithRestore()
	{

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

		ArrayList<String> idVars = tmInfo.getIdVariables();

		Vector v = new Vector();

		for (int i = 0; i < idVars.size(); i++) {
			v.add(idVars.get(i));
		}

		lstIDVariables.setListData(v);

	}
	
	void resetGroupVarsWithRestore() {

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

		ArrayList<String> idVars = tmInfo.getGroupVariables();

		Vector v = new Vector();

		for (int i = 0; i < idVars.size(); i++) {
			v.add(idVars.get(i));
		}

		lstGrpVariables.setListData(v);

	}
	
	void resetOrdVarsWithRestore() {
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

		ArrayList<String> ordVars = tmInfo.getOrdinaryVariables();

		Vector v = new Vector();

		for (int i = 0; i < ordVars.size(); i++) {
			v.add(ordVars.get(i));
		}

		lstVariables.setListData(v);
	}
	
	void resetVarsWithRestore()
	{
		resetCrossVarsWithRestore();
		resetIdVarsWithRestore();
		resetGroupVarsWithRestore();
		resetOrdVarsWithRestore();
	}

	private void listCrossVariableFunctionality() {
		// TODO Auto-generated method stub

		ListModel listModel = lstCrossVariables.getModel();

		Object[] crossVarListing = new Object[listModel.getSize()];

		for (int i = 0; i < listModel.getSize(); i++) {
			crossVarListing[i] = listModel.getElementAt(i);
		}

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ArrayList<String> crossVariables = generateArrayList(crossVarListing);
		appInfo
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
								.getSelectedSheetIndex()).getTmInfo()
				.setCrossVariables(crossVariables);

	}

	private void listGroupVariableFunctionality() {
		// TODO Auto-generated method stub

		ListModel listModel = lstGrpVariables.getModel();

		Object[] grpVarListing = new Object[listModel.getSize()];

		for (int i = 0; i < listModel.getSize(); i++) {
			grpVarListing[i] = listModel.getElementAt(i);
		}
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ArrayList<String> grpVariables = generateArrayList(grpVarListing);
		appInfo
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
								.getSelectedSheetIndex()).getTmInfo()
				.setGroupVariables(grpVariables);
	}

	private void listIdVariablesFunctionality() {
		// TODO Auto-generated method stub

		ListModel listModel = lstIDVariables.getModel();

		Object[] idVarListing = new Object[listModel.getSize()];

		for (int i = 0; i < listModel.getSize(); i++) {
			idVarListing[i] = listModel.getElementAt(i);
		}
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ArrayList<String> idVariables = generateArrayList(idVarListing);
		appInfo
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
								.getSelectedSheetIndex()).getTmInfo()
				.setIdVariables(idVariables);

	}

	private void listOrdinaryVariableFunctionality() {
		// TODO Auto-generated method stub
		System.out
				.println("I am in value changed event of lstVariables ordinary variable listings.");

		ListModel listModel = lstVariables.getModel();

		Object[] ordVarListing = new Object[listModel.getSize()];

		for (int i = 0; i < listModel.getSize(); i++) {
			ordVarListing[i] = listModel.getElementAt(i);
		}

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ArrayList<String> ordinaryVariables = generateArrayList(ordVarListing);
		appInfo
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
								.getSelectedSheetIndex()).getTmInfo()
				.setOrdinaryVariables(ordinaryVariables);

	}

	private ArrayList<String> generateArrayList(Object[] arrListStr) {
		ArrayList<String> arrList = new ArrayList<String>();
		for (int i = 0; i < arrListStr.length; i++) {
			arrList.add((String) arrListStr[i]);
		}

		return arrList;
	}
}
