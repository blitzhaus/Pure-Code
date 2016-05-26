package view;

import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.DefaultListModel;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class QCConditionsTable extends JFrame {
	
	private JTable conditionsTable;

	public QCConditionsTable(){
		
		createFrame();
		
		createTablesInternalFrame();
		createListinternalFrame();
		JTable conditionsTable;

	
			
		
	}

	private void createFrame() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = ge.getMaximumWindowBounds();
		setSize(bounds.width/3, bounds.height/3);
		setLocationRelativeTo(null);
		setVisible(true);
		setContentPane(new JDesktopPane());
		
	}

	JInternalFrame listsIF;
	private void createListinternalFrame() {
		listsIF = new JInternalFrame("", false,false,false,false);
		listsIF.setLocation(0, tableIF.getWidth());
		listsIF.setSize(getWidth() - tableIF.getWidth(), getHeight());
		listsIF.setVisible(true);
		getContentPane().add(listsIF);
		listsIF.moveToFront();
		createLists();
		
	}

	private void createLists() {

		listsIF.setLayout(new GridBagLayout());
		DefaultListModel operatorsModel = new DefaultListModel();
		JList operatorsList  = new JList(operatorsModel);
	
		operatorsModel.add(0, "greater than");
		operatorsModel.add(1, "less than");
		operatorsModel.add(2, "equal to");
		
		GridBagConstraints operatorsCon = new GridBagConstraints();
		operatorsCon.gridx = 0;
		operatorsCon.gridy = 0;
		operatorsCon.anchor = GridBagConstraints.LINE_START;
		
		
	}

	JInternalFrame tableIF;
	private void createTablesInternalFrame() {
		
		tableIF = new JInternalFrame("", false,false,false,false);
		tableIF.setLocation(0, 0);
		tableIF.setSize(getWidth()/2, getHeight());
		tableIF.setVisible(true);
		getContentPane().add(tableIF);
		tableIF.moveToFront();
			
			conditionsTable = new JTable(0,3);
			TableColumnModel tcm = conditionsTable.getColumnModel();
			TableColumn tc = tcm.getColumn(0);
			tc.setHeaderValue("Parameter");
			
			tc = tcm.getColumn(1);
			tc.setHeaderValue("Condition");
			
			tc = tcm.getColumn(2);
			tc.setHeaderValue("Replace with");
			
			tableIF.getContentPane().add(conditionsTable);
		
	}

}
