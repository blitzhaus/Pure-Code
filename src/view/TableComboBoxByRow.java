package view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

class ArrowsPanel extends JPanel {
	JButton btnUpArrow;
	JButton btnDnArrow;

	ArrowsPanel(int height) {
		btnUpArrow = new JButton("uparrow.gif");
		btnDnArrow = new JButton("downarrow.gif");
		setLayout(null);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrDim = toolkit.getScreenSize();
		setSize(50, height);

		btnUpArrow.setBounds(0, 0, 30, 50);
		btnDnArrow.setBounds(0, 50, 30, 50);

	}
}

public class TableComboBoxByRow extends JFrame {
	List<TableCellEditor> editors = new ArrayList<TableCellEditor>(3);

	public TableComboBoxByRow() {
		// Create the editors to be used for each row

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		//

		String[] items1 = { "None", "Ascending", "Descending" };
		JComboBox comboBox1 = new JComboBox(items1);
		DefaultCellEditor dce1 = new DefaultCellEditor(comboBox1);
		editors.add(dce1);

		String[] items2 = { "None", "Ascending", "Descending" };
		JComboBox comboBox2 = new JComboBox(items2);
		DefaultCellEditor dce2 = new DefaultCellEditor(comboBox2);
		editors.add(dce2);

		String[] items3 = { "None", "Ascending", "Descending" };
		JComboBox comboBox3 = new JComboBox(items3);
		DefaultCellEditor dce3 = new DefaultCellEditor(comboBox3);
		editors.add(dce3);

		// Create the table with default data

		Object[][] data = { { "Color", "Red" }, { "Shape", "Square" },
				{ "Fruit", "Banana" }, { "Plain", "Text" } };

		int colsCount = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getSelectedSheetIndex())
				.getColumnPropertiesArrayList().size();

		data = new Object[colsCount][2];

		for (int i = 0; i < colsCount; i++) {
			String colName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName();
			data[i][0] = colName;
			data[i][1] = "None";
		}

		String[] columnNames = { "Column Name", "Sort Direction" };
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable table = new JTable(model) {
			// Determine editor to be used by row
			public TableCellEditor getCellEditor(int row, int column) {
				int modelColumn = convertColumnIndexToModel(column);

				if (modelColumn == 1 && row < 3)
					return editors.get(row);
				else
					return super.getCellEditor(row, column);
			}
		};

		JPanel panel1 = new JPanel();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		setSize((int) (width * 0.5), (int) (height * 0.6));
		getContentPane().add(panel1, BorderLayout.WEST);

		JScrollPane scrollPane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// panel1.add( scrollPane );

		ArrowsPanel arrowsPanel = new ArrowsPanel((int) (0.75 * height));
		getContentPane().add(arrowsPanel, BorderLayout.EAST);
	}

	public static void main(String[] args) {
		TableComboBoxByRow frame = new TableComboBoxByRow();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
