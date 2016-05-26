package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Common.JinternalFrameFunctions;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class TMSetupDispComp{

	private final class ListGroupSelectionHandler implements
			ListSelectionListener {
		private final JList lstGrpVariables;

		private ListGroupSelectionHandler(JList lstGrpVariables) {
			this.lstGrpVariables = lstGrpVariables;
		}

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("I am in value changed event of grpVariableListings.");
			btnGrpVar.setIcon(new ImageIcon("left.png"));
			
			String[] grpVarListing = (String[])lstGrpVariables.getSelectedValues();
			TableMavenInfo tblWizInputs = null;
			try {
				tblWizInputs = TableMavenLaunchScreen.createTableMavenIPInstance();
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<String> grpVariables = new ArrayList<String>();
			
			for (int i = 0; i < grpVarListing.length; i++) {
				grpVariables.add(grpVarListing[i]);
			}
			
			tblWizInputs.setGroupVariables(grpVariables);
		}
	}

	private final class ListCrossVarSelectionHandler implements
			ListSelectionListener {
		private final JList lstCrossVariables;

		private ListCrossVarSelectionHandler(JList lstCrossVariables) {
			this.lstCrossVariables = lstCrossVariables;
		}

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			System.out
					.println("I am in value changed event of lstCrossVariables.");
			
			String[] crossVarListing = (String[])lstCrossVariables.getSelectedValues();
			TableMavenInfo tblWizInputs = null;
			try {
				tblWizInputs = TableMavenLaunchScreen.createTableMavenIPInstance();
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<String> crossVariables = new ArrayList<String>();
			
			for (int i = 0; i < crossVarListing.length; i++) {
				crossVariables.add(crossVarListing[i]);
			}
			tblWizInputs.setCrossVariables(crossVariables);
			
			btnCrossVar.setIcon(new ImageIcon("left.png"));
		}
	}

	private final class ListIDSelectionHandler implements ListSelectionListener {
		private final JList lstIDVariables;

		private ListIDSelectionHandler(JList lstIDVariables) {
			this.lstIDVariables = lstIDVariables;
		}

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			System.out
					.println("I am in value changed event of lstIDVariables.");
			
			String[] idVarListing = (String[])lstIDVariables.getSelectedValues();
			TableMavenInfo tblWizInputs = null;
			try {
				tblWizInputs = TableMavenLaunchScreen.createTableMavenIPInstance();
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<String> idVariables = new ArrayList<String>();
			
			for (int i = 0; i < idVarListing.length; i++) {
				idVariables.add(idVarListing[i]);
			}
			tblWizInputs.setIdVariables(idVariables);				
			
			btnIdVar.setIcon(new ImageIcon("left.png"));
		}
	}

	private final class ListVarSelectionHandler implements
			ListSelectionListener {
		private final JList lstVariables;

		private ListVarSelectionHandler(JList lstVariables) {
			this.lstVariables = lstVariables;
		}

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			System.out
					.println("I am in value changed event of lstVariables ordinary variable listings.");
			
			String[] ordVarListing = (String[])lstVariables.getSelectedValues();
			TableMavenInfo tblWizInputs = null;
			try {
				tblWizInputs = TableMavenLaunchScreen.createTableMavenIPInstance();
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<String> ordVariables = new ArrayList<String>();
			
			for (int i = 0; i < ordVarListing.length; i++) {
				ordVariables.add(ordVarListing[i]);
			}
			tblWizInputs.setOrdinaryVariables(ordVariables);	
			btnVar.setIcon(new ImageIcon("left.png"));
		}
	}

	public static TMSetupDispComp TM_SETUP_DISP_COM = null;
	public static TMSetupDispComp createTmSetupDispCompInst() {
		if(TM_SETUP_DISP_COM == null){
			TM_SETUP_DISP_COM = new TMSetupDispComp("just object creation");
		}
		return TM_SETUP_DISP_COM;
	}
	
	public TMSetupDispComp(String dummy){
		
	}

	JButton btnGrpVar;
	JButton btnCrossVar;
	JButton btnIdVar;
	JButton btnVar;
	JPanel tmMappingPanel;
	JInternalFrame tmSetupMappingDispCompIF;
	JList lstAvailVariables;
	JInternalFrame tmSetupModelIF;
	
	private boolean restoreMode = false;
	
	public boolean isRestoreMode() {
		return restoreMode;
	}

	public void setRestoreMode(boolean restoreMode) {
		this.restoreMode = restoreMode;
	}

	public void createSetupDispCompUI(){
		
		createMappingIF();
		createTableModelDisplayIF();
		tmSetupMappingDispCompIF.moveToFront();
		
	}

	private void createTableModelDisplayIF() {
		
		tmSetupModelIF = new JInternalFrame("model IF",false,false,false,false);
		tmSetupModelIF.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(tmSetupModelIF);
		tmSetupModelIF.setLocation(TMSetupAvailComp.createTmSetupAvailCompInst().tmSetupAvailCompIF.getWidth(),0);
		tmSetupModelIF.setSize(TableMavenCreateUI.createTableMavenUIInst().setupMainIF.getWidth()
				-TMSetupAvailComp.createTmSetupAvailCompInst().tmSetupAvailCompIF.getWidth(),
				TMSetupAvailComp.createTmSetupAvailCompInst().tmSetupAvailCompIF.getHeight());
		JScrollPane modelIFSP = new JScrollPane(tmSetupModelIF, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//tmSetupModelIF.moveToFront();
		tmSetupModelIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		TableMavenCreateUI.createTableMavenUIInst().setupMainIFDP.add(tmSetupModelIF);
		
		
		
	}
	
	
	private void createMappingIF() {
		
		tmSetupMappingDispCompIF = new JInternalFrame("display comp",false,false,false,false);
		tmSetupMappingDispCompIF.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(tmSetupMappingDispCompIF);
		tmSetupMappingDispCompIF.setLocation(TMSetupAvailComp.createTmSetupAvailCompInst().tmSetupAvailCompIF.getWidth(),0);
		tmSetupMappingDispCompIF.setSize(TableMavenCreateUI.createTableMavenUIInst().setupMainIF.getWidth()
				-TMSetupAvailComp.createTmSetupAvailCompInst().tmSetupAvailCompIF.getWidth(),
				TMSetupAvailComp.createTmSetupAvailCompInst().tmSetupAvailCompIF.getHeight());
		tmSetupMappingDispCompIF.moveToFront();
		
		JScrollPane tmSetupDispCompSP = new JScrollPane(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		tmSetupMappingDispCompIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		TableMavenCreateUI.createTableMavenUIInst().setupMainIFDP.add(tmSetupMappingDispCompIF);
		//createAvailableVariablesPanel();
		createCategoryVariablesPanel();		
	}
	
	private void createCategoryVariablesPanel() {
		TMMappingPanel categoryVarPanel = TableMavenCreateUI.createTMMappingPanel();
		categoryVarPanel.createTMMappingPanel();
		
		if (DDViewLayer.createViewLayerInstance().isTMRestore == true)
		{
			categoryVarPanel.resetVarsWithRestore();
		}
		
		JScrollPane tmSetupDispCompSP = new JScrollPane(categoryVarPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tmSetupMappingDispCompIF.getContentPane().add(tmSetupDispCompSP,BorderLayout.CENTER);
	}

	private void createAvailableVariablesPanel() {
		
		JPanel availableVarPanel = new JPanel();
		availableVarPanel.setLayout(new BorderLayout());
		tmSetupMappingDispCompIF.getContentPane().add(availableVarPanel,BorderLayout.WEST);
		lstAvailVariables = new JList();
		lstAvailVariables.setModel(new AbstractListModel() {
			String[] values = new String[] {"item1item1item1", "item2", "item3", "item4", "item5"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		lstAvailVariables.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				System.out
						.println("I am in value changed event of lstAvailVariables.");
				btnIdVar.setIcon(new ImageIcon("right.png"));
				btnGrpVar.setIcon(new ImageIcon("right.png"));
				btnCrossVar.setIcon(new ImageIcon("right.png"));
				btnVar.setIcon(new ImageIcon("right.png"));;
			}
		});
		JScrollPane availVarListSP = new JScrollPane(lstAvailVariables, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
				,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		availableVarPanel.add(availVarListSP,BorderLayout.CENTER);
		
		
	}

}
