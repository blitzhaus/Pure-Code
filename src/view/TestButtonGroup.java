package view;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class TestButtonGroup {

	public TestButtonGroup() {
		//createTable();
	}

	public JTable createTable(HashMap<String, HashSet<String>> hm, String[] header, int col) {

		int columns = col;
		
		
		Set<Entry<String, HashSet<String>>> entry = hm.entrySet();
		Iterator<Entry<String, HashSet<String>>> it = entry.iterator();
		ArrayList<String> al = new ArrayList<String>();
		while(it.hasNext()){
			al.add(it.next().getKey());
		}
		int rows = al.size();
	
		JRadioButton[][] b = new JRadioButton[rows][columns -1];
		for(int i=0;i<b.length;i++){
			
			ButtonGroup bg = new ButtonGroup();
			for(int j=0;j<b[i].length;j++){
				b[i][j] = new JRadioButton();
				bg.add(b[i][j]);
			}
		}
		

		String[] h = header;
		JPanel[][] p = new JPanel[rows][columns];
		
		for(int i=0;i<p.length;i++){
			JLabel lable = new JLabel();
			lable.setText(al.get(i));
			p[i][0] = new JPanel();
			p[i][0].add(lable);
		}
		for(int i=0;i<p.length;i++){
			for(int j=1;j<(p[i].length);j++){
				p[i][j] = new JPanel();
				/*JLabel lable = new JLabel();
				lable.setText("this");*/
				p[i][j].add(b[i][j-1]);
				/*p[i][j].add(lable);*/
				
			}
		}
	
		System.out.println();
		DefaultTableModel model = new DefaultTableModel(p, h) {
			public Class getColumnClass(int column) {
				return JPanel.class;
			}
		};

		JTable table = new JTable(model) {
			public void tableChanged(TableModelEvent e) {
				super.tableChanged(e);
				repaint();
			}
		};
		TableColumnModel columnModel = table.getColumnModel();
		for(int i=0;i<columnModel.getColumnCount();i++){
			TableColumn column = columnModel.getColumn(i);

			TestRenderer renderer = new TestRenderer(new JCheckBox());
			column.setCellRenderer(renderer);
			column.setCellEditor(renderer);
		}

		table.setRowHeight(30);

		return table;
	
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
          TestButtonGroup test = new TestButtonGroup();
          
 
     }

	class TestRenderer extends DefaultCellEditor implements TableCellRenderer,
			TableCellEditor, ItemListener {

		JPanel editCell;

		public TestRenderer(JCheckBox checkBox) {
			super(checkBox);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			return (JPanel) value;
		}

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {

			editCell = (JPanel) value;
			((JRadioButton) editCell.getComponent(0)).addItemListener(this);
			return editCell;
		}

		public Object getCellEditorValue() {
			((JRadioButton) editCell.getComponent(0)).removeItemListener(this);
			return editCell;
		}

		public void itemStateChanged(ItemEvent e) {
			super.fireEditingStopped();
		}
	}

}