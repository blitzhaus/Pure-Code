package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.undo.*;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.MouseInputListener;
import javax.swing.event.TableModelEvent;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;





public class ClipboardTable extends DefaultTableModel  {
	public static void tableFeatures(JvUndoableTableModel tableModel) {
		/*Object data[][] = { { "AN01", 1, 3.6, "micromg/ml" },
				{ "BN02", 1.2, 3.4, "micromg/ml" }, { "CN01", 1.33, 3.26, "" },
				{ "CN02", 1.56, 3.1, "" }, { "CN03", 2.13, 2.91, "" },
				{ "CN03", 2.52, 2.83, "" }, { "", 3.86, 2.76, "" },
				{ "", 4.66, 2.37, "" }, { "", 5.02, 2.21, "" },
				{ "", 6.69, 2.37, "" } };
		String columns[] = { "SobjectId", "Time", "Concentration", "UNIT" };
		JvUndoableTableModel tableModel = new JvUndoableTableModel(data,
				columns);*/
		final JvUndoableTable table = new JvUndoableTable(tableModel);
		table.setCellSelectionEnabled(true); // required for Fill Down
		// table.setTransferHandler(new JvTableTransferHandler());
 
		JScrollPane pane = new JScrollPane(table);
	      new ReorderableJList(table);

		JvUndoManager undoManager = new JvUndoManager();
		table.addUndoableEditListener(undoManager);
		
		JMenu editMenu = new JMenu("Edit");
		editMenu.add(undoManager.getUndoAction());
		editMenu.add(undoManager.getRedoAction());
		editMenu.addSeparator();
		editMenu.add(new JvFillDownAction(table));
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(editMenu);
		TableColumnModel columnModel = table.getColumnModel();
		int col_no=columnModel.getColumnCount();
		System.out.println("column no"+col_no);

		JFrame frame = new JFrame("Jtable");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);
		frame.add(pane, BorderLayout.CENTER);
		frame.setSize(400, 250);
		frame.setLocation(200, 300);
		frame.setVisible(true);
		table.getTableHeader().addMouseListener(new MouseAdapter() {

		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	
		    	
		    	JTableHeader header = table.getTableHeader();

			    TableColumnModel columns = header.getColumnModel();

			    if (!columns.getColumnSelectionAllowed())
			        return;

			    int column = header.columnAtPoint(e.getPoint());

			    if (column == -1)
			        return;

			    int count = table.getRowCount();

			    if (count != 0)
			        table.setRowSelectionInterval(0, count - 1);

			    ListSelectionModel selection = columns.getSelectionModel();

			    if (e.isShiftDown())
			    {
			        int anchor = selection.getAnchorSelectionIndex();
			        int lead = selection.getLeadSelectionIndex();

			        if (anchor != -1)
			        {
			            boolean old = selection.getValueIsAdjusting();
			            selection.setValueIsAdjusting(true);

			            boolean anchorSelected = selection.isSelectedIndex(anchor);

			            if (lead != -1)
			            {
			                if (anchorSelected)
			                    selection.removeSelectionInterval(anchor, lead);
			                else
			                    selection.addSelectionInterval(anchor, lead);
			                
			            }

			            if (anchorSelected)
			                selection.addSelectionInterval(anchor, column);
			            else
			                selection.removeSelectionInterval(anchor, column);

			            selection.setValueIsAdjusting(old);
			        }
			        else
			            selection.setSelectionInterval(column, column);
			    }
			    else if (e.isControlDown())
			    {
			        if (selection.isSelectedIndex(column))
			            selection.removeSelectionInterval(column, column);
			        else
			            selection.addSelectionInterval(column, column);
			    }
			    else
			    {
			        selection.setSelectionInterval(column, column);
			    }
			}
			
			
			public void tableChanged(TableModelEvent arg0) {
				
				
			}
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    });

//		table.getTableHeader().setReorderingAllowed(false);
		
		//table.getColumnModel().setColumnSelectionAllowed(false);
		//table.setRowSelectionAllowed(false);
	}
}





class JvUndoableTableModel extends DefaultTableModel {
	public JvUndoableTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	public Class getColumnClass(int column) {
		if (column >= 0 && column < getColumnCount())
			return getValueAt(0, column).getClass();

		return Object.class;
	}
}

class JvCellEdit extends AbstractUndoableEdit {
	protected String presentationName;
	protected JvUndoableTable table;
	protected Object oldValue;
	protected Object newValue;
	protected int row;
	protected int column;

	public JvCellEdit(JvUndoableTable table, Object oldValue, Object newValue,
			int row, int column) {
		this(null, table, oldValue, newValue, row, column);
	}

	public JvCellEdit(String name, JvUndoableTable table, Object oldValue,
			Object newValue, int row, int column) {
		this.presentationName = name == null ? "Cell Edit" : name;
		this.table = table;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.row = row;
		this.column = column;
	}

	@Override
	public String getPresentationName() {
		return presentationName;
	}

	protected void setValue(Object value) {
		table.clearSelection();
		table.addRowSelectionInterval(row, row);
		table.addColumnSelectionInterval(column, column);

		table.setValueAt(value, row, column, false);
	}

	@Override
	public void undo() throws CannotUndoException {
		super.undo();

		setValue(oldValue);
	}

	@Override
	public void redo() throws CannotUndoException {
		super.redo();

		setValue(newValue);
	}
}

class JvUndoManager extends UndoManager {
	protected Action undoAction;
	protected Action redoAction;

	public JvUndoManager() {
		this.undoAction = new JvUndoAction(this);
		this.redoAction = new JvRedoAction(this);

		synchronizeActions(); // to set initial names
	}

	public Action getUndoAction() {
		return undoAction;
	}

	public Action getRedoAction() {
		return redoAction;
	}

	@Override
	public boolean addEdit(UndoableEdit anEdit) {
		try {
			return super.addEdit(anEdit);
		} finally {
			synchronizeActions();
		}
	}

	@Override
	protected void undoTo(UndoableEdit edit) throws CannotUndoException {
		try {
			super.undoTo(edit);
		} finally {
			synchronizeActions();
		}
	}

	@Override
	protected void redoTo(UndoableEdit edit) throws CannotRedoException {
		try {
			super.redoTo(edit);
		} finally {
			synchronizeActions();
		}
	}

	protected void synchronizeActions() {
		undoAction.setEnabled(canUndo());
		String undoName = (canUndo() ? "" : "Can't ")
				+ getUndoPresentationName();
		undoAction.putValue(Action.NAME, undoName);

		redoAction.setEnabled(canRedo());
		String redoName = (canRedo() ? "" : "Can't ")
				+ getRedoPresentationName();
		redoAction.putValue(Action.NAME, redoName);
	}
}

class JvUndoAction extends AbstractAction {
	protected final UndoManager manager;

	public JvUndoAction(UndoManager manager) {
		this.manager = manager;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			manager.undo();
		} catch (CannotUndoException ex) {
			ex.printStackTrace();
		}
	}
}

class JvRedoAction extends AbstractAction {
	protected final UndoManager manager;

	public JvRedoAction(UndoManager manager) {
		this.manager = manager;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			manager.redo();
		} catch (CannotRedoException ex) {
			ex.printStackTrace();
		}
	}
}

interface JvCellFill {
	public void doFill(JvUndoableTable table);

	public void undoFill(JvUndoableTable table);
}

class JvColumnFill implements JvCellFill {
	protected int columnIndex;
	protected int[] rowIndices;
	protected Object[] values;

	public JvColumnFill(JvUndoableTable table, int columnIndex, int[] rowIndices) {
		this.columnIndex = columnIndex;
		this.rowIndices = rowIndices;

		values = new Object[rowIndices.length];
		for (int i = 0; i < rowIndices.length; i++)
			values[i] = table.getValueAt(rowIndices[i], columnIndex);
	}

	public void doFill(JvUndoableTable table) {
		for (int i = 1; i < rowIndices.length; i++) {
			if (table.isCellEditable(rowIndices[i], columnIndex))
				table.setValueAt(values[0], rowIndices[i], columnIndex, false);
		}
	}

	public void undoFill(JvUndoableTable table) {
		for (int i = 1; i < rowIndices.length; i++) {
			if (table.isCellEditable(rowIndices[i], columnIndex))
				table.setValueAt(values[i], rowIndices[i], columnIndex, false);
		}
	}
}

class JvCellFillEdit extends AbstractUndoableEdit {
	protected JvUndoableTable table;
	protected ArrayList<JvCellFill> fills = new ArrayList<JvCellFill>();

	public JvCellFillEdit(JvUndoableTable table) {
		this.table = table;
	}

	public void addFill(JvCellFill fill) {
		fills.add(fill);
	}

	@Override
	public String getPresentationName() {
		return "Cell Fill";
	}

	public void fill() {
		for (JvCellFill fill : fills)
			fill.doFill(table);
	}

	@Override
	public void undo() throws CannotUndoException {
		super.undo();

		for (JvCellFill fill : fills)
			fill.undoFill(table);
	}

	@Override
	public void redo() throws CannotUndoException {
		super.redo();

		fill();
	}
}

class JvFillDownAction extends AbstractAction {
	protected JvUndoableTable table;

	public JvFillDownAction(JvUndoableTable table) {
		super("Fill Down");
		this.table = table;

		// As far as I can tell, in Excel the "Fill Down" action is always
		// enabled.
		setEnabled(true);

		KeyStroke accel = KeyStroke
				.getKeyStroke((char) InputEvent.BUTTON1_DOWN_MASK);
		putValue(ACCELERATOR_KEY, accel);

		table.getInputMap().put(accel, "fillDown");
		table.getActionMap().put("fillDown", this);
	}

	public void actionPerformed(ActionEvent event) {
		// Cell selection in JTable looks like a hack. Basically it looks like
		// the union of column and row selection.
		int[] rowIndices = table.getSelectedRows();
		if (rowIndices.length == 0)
			return;

		JvCellFillEdit fillEdit = new JvCellFillEdit(table);

		for (int columnIndex : table.getSelectedColumns()) {
			columnIndex = table.convertColumnIndexToModel(columnIndex);
			JvColumnFill fill = new JvColumnFill(table, columnIndex, rowIndices);
			fillEdit.addFill(fill);
		}

		fillEdit.fill();
		table.fireUndoableEdit(fillEdit);
	}
	
}


 @SuppressWarnings("serial")
class EditableHeader extends JTableHeader implements
		CellEditorListener {
	public final int HEADER_ROW = -10;

	transient protected int editingColumn;

	transient protected TableCellEditor cellEditor;

	transient protected Component editorComp;

//	public EditableHeader(TableColumnModel columnModel) {
//		super(columnModel);
//		setReorderingAllowed(false);
//		cellEditor = null;
//		recreateTableColumn(columnModel);
//	}

//	public void updateUI() {
//		setUI(new EditableHeaderUI());
//		resizeAndRepaint();
//		invalidate();
//	}

//	protected void recreateTableColumn(TableColumnModel columnModel) {
//		int n = columnModel.getColumnCount();
//		EditableHeaderTableColumn[] newCols = new EditableHeaderTableColumn[n];
//		TableColumn[] oldCols = new TableColumn[n];
//		for (int i = 0; i < n; i++) {
//			oldCols[i] = columnModel.getColumn(i);
//			newCols[i] = new EditableHeaderTableColumn();
//			newCols[i].copyValues(oldCols[i]);
//		}
//		for (int i = 0; i < n; i++) {
//			columnModel.removeColumn(oldCols[i]);
//		}
//		for (int i = 0; i < n; i++) {
//			columnModel.addColumn(newCols[i]);
//		}
//	}

	public boolean editCellAt(int index) {
		return editCellAt(index);
	}

//	public boolean editCellAt(int index, EventObject e) {
//		if (cellEditor != null && !cellEditor.stopCellEditing()) {
//			return false;
//		}
//		if (!isCellEditable(index)) {
//			return false;
//		}
//		TableCellEditor editor = getCellEditor(index);
//
//		if (editor != null && editor.isCellEditable(e)) {
//			editorComp = prepareEditor(editor, index);
//			editorComp.setBounds(getHeaderRect(index));
//			add(editorComp);
//			editorComp.validate();
//			setCellEditor(editor);
//			setEditingColumn(index);
//			editor.addCellEditorListener(this);
//
//			return true;
////		}
//		return false;
//	}

//	public boolean isCellEditable(int index) {
//		if (getReorderingAllowed()) {
//			return false;
//		}
//		int columnIndex = columnModel.getColumn(index).getModelIndex();
//		EditableHeaderTableColumn col = (EditableHeaderTableColumn) columnModel
//				.getColumn(columnIndex);
//		return col.isHeaderEditable();
//	}

//	public TableCellEditor getCellEditor(int index) {
//		int columnIndex = columnModel.getColumn(index).getModelIndex();
//		EditableHeaderTableColumn col = (EditableHeaderTableColumn) columnModel
//				.getColumn(columnIndex);
//		return col.getHeaderEditor();
//	}

	public void setCellEditor(TableCellEditor newEditor) {
		TableCellEditor oldEditor = cellEditor;
		cellEditor = newEditor;

		// firePropertyChange

		if (oldEditor != null && oldEditor instanceof TableCellEditor) {
			((TableCellEditor) oldEditor)
					.removeCellEditorListener((CellEditorListener) this);
		}
		if (newEditor != null && newEditor instanceof TableCellEditor) {
			((TableCellEditor) newEditor)
					.addCellEditorListener((CellEditorListener) this);
		}
	}

	@SuppressWarnings("deprecation")
	public Component prepareEditor(TableCellEditor editor, int index) {
		Object value = columnModel.getColumn(index).getHeaderValue();
		boolean isSelected = true;
		int row = HEADER_ROW;
		JTable table = getTable();
		Component comp = editor.getTableCellEditorComponent(table, value,
				isSelected, row, index);
		if (comp instanceof JComponent) {
			((JComponent) comp).setNextFocusableComponent(this);
		}
		return comp;
	}

	public TableCellEditor getCellEditor() {
		return cellEditor;
	}

	public Component getEditorComponent() {
		return editorComp;
	}

	public void setEditingColumn(int aColumn) {
		editingColumn = aColumn;
	}

	public int getEditingColumn() {
		return editingColumn;
	}

	public void removeEditor() {
		TableCellEditor editor = getCellEditor();
		if (editor != null) {
			editor.removeCellEditorListener(this);

			requestFocus();
			remove(editorComp);

			int index = getEditingColumn();
			Rectangle cellRect = getHeaderRect(index);

			setCellEditor(null);
			setEditingColumn(-1);
			editorComp = null;

			repaint(cellRect);
		}
	}

	public boolean isEditing() {
		return (cellEditor == null) ? false : true;
	}

	
	public void editingStopped(ChangeEvent e) {
		TableCellEditor editor = getCellEditor();
		if (editor != null) {
			Object value = editor.getCellEditorValue();
			int index = getEditingColumn();
			columnModel.getColumn(index).setHeaderValue(value);
			removeEditor();
		}
	}

	public void editingCanceled(ChangeEvent e) {
		removeEditor();
	}

	//
	// public void setReorderingAllowed(boolean b) {
	// reorderingAllowed = false;
	// }

}

//class EditableHeaderUI extends BasicTableHeaderUI {
//
//	protected MouseInputListener createMouseInputListener() {
//		return new MouseInputHandler((EditableHeader) header);
//	}
//
//	public class MouseInputHandler extends BasicTableHeaderUI.MouseInputHandler {
//		private Component dispatchComponent;
//
//		protected EditableHeader header;
//
//		public MouseInputHandler(EditableHeader header) {
//			this.header = header;
//		}
//
//		private void setDispatchComponent(MouseEvent e) {
//			Component editorComponent = header.getEditorComponent();
//			Point p = e.getPoint();
//			Point p2 = SwingUtilities.convertPoint(header, p, editorComponent);
//			dispatchComponent = SwingUtilities.getDeepestComponentAt(
//					editorComponent, p2.x, p2.y);
//		}
//
//		private boolean repostEvent(MouseEvent e) {
//			if (dispatchComponent == null) {
//				return false;
//			}
//			MouseEvent e2 = SwingUtilities.convertMouseEvent(header, e,
//					dispatchComponent);
//			dispatchComponent.dispatchEvent(e2);
//			return true;
//		}
//
//		public void mousePressed(MouseEvent e) {
//			if (!SwingUtilities.isLeftMouseButton(e)) {
//				return;
//			}
//			super.mousePressed(e);
//
//			if (header.getResizingColumn() == null) {
//				Point p = e.getPoint();
//				TableColumnModel columnModel = header.getColumnModel();
//				int index = columnModel.getColumnIndexAtX(p.x);
//				if (index != -1) {
//					if (header.editCellAt(index, e)) {
//						setDispatchComponent(e);
//						repostEvent(e);
//					}
//				}
//			}
//		}
//
//		public void mouseReleased(MouseEvent e) {
//			super.mouseReleased(e);
//			if (!SwingUtilities.isLeftMouseButton(e)) {
//				return;
//			}
//			repostEvent(e);
//			dispatchComponent = null;
//		}
//
//	}
//
//}
//
//class EditableHeaderTableColumn extends TableColumn {
//
//	protected TableCellEditor headerEditor;
//
//	protected boolean isHeaderEditable;
//
//	public EditableHeaderTableColumn() {
//		setHeaderEditor(createDefaultHeaderEditor());
//		isHeaderEditable = true;
//	}
//
//	public void setHeaderEditor(TableCellEditor headerEditor) {
//		this.headerEditor = headerEditor;
//	}
//
//	public TableCellEditor getHeaderEditor() {
//		return headerEditor;
//	}
//
//	public void setHeaderEditable(boolean isEditable) {
//		isHeaderEditable = isEditable;
//	}
//
//	public boolean isHeaderEditable() {
//		return isHeaderEditable;
//	}
//
//	public void copyValues(TableColumn base) {
//		modelIndex = base.getModelIndex();
//		identifier = base.getIdentifier();
//		width = base.getWidth();
//		minWidth = base.getMinWidth();
//		setPreferredWidth(base.getPreferredWidth());
//		maxWidth = base.getMaxWidth();
//		headerRenderer = base.getHeaderRenderer();
//		headerValue = base.getHeaderValue();
//		cellRenderer = base.getCellRenderer();
//		cellEditor = base.getCellEditor();
//		isResizable = base.getResizable();
//	}
//
//	protected TableCellEditor createDefaultHeaderEditor() {
//		return new DefaultCellEditor(new JTextField());
//	}
//
//}


// class JvTableTransferHandler extends TransferHandler {
// protected Transferable createTransferable(JComponent c) {
// if (c instanceof JTable == false)
// return null;
//
// JTable table = (JTable) c;
// int[] rows = table.getSelectedRows();
// int[] columns = table.getSelectedColumns();
// if (rows == null || columns == null || rows.length != 1
// || columns.length != 1)
// return null;
//
// Object value = table.getValueAt(rows[0], columns[0]);
// if (value == null)
// return null;
//
// return new JvTransferable(value);
// }
//
// public int getSourceActions(JComponent c) {
// return COPY;
// }
//
// public boolean importData(JComponent comp, Transferable transferable) {
// if (comp instanceof JTable == false)
// return false;
//
// JTable table = (JTable) comp;
//
// if (importCellData(table, transferable))
// return true;
//
// return false;
// }
//
// protected boolean importCellData(JTable table, Transferable transferable) {
// int[] rows = table.getSelectedRows();
// int[] columns = table.getSelectedColumns();
// if (rows == null || columns == null || rows.length != 1
// || columns.length != 1)
// return false;
//
// int rowIndex = rows[0];
// int columnIndex = columns[0];
//
// if (table.isCellEditable(rowIndex, columnIndex) == false)
// return false;
//
// if (importCellObject(table, rowIndex, columnIndex, transferable))
// return true;
//
// Class valueClass = table.getColumnClass(columnIndex);
// PropertyEditor editor = PropertyEditorManager.findEditor(valueClass);
// DataFlavor stringFlavor = getStringFlavor(transferable);
// if (editor == null || stringFlavor == null)
// return false;
//
// try {
// System.out.println("Converting String to "
// + valueClass.getSimpleName());
// editor.setAsText((String) transferable
// .getTransferData(stringFlavor));
// setCellValue(table, rowIndex, columnIndex, editor.getValue());
// return true;
// } catch (UnsupportedFlavorException e) {
// } catch (IOException e) {
// } catch (IllegalArgumentException e) {
// Toolkit.getDefaultToolkit().beep();
// }
//
// return false;
// }
//
// protected DataFlavor getStringFlavor(Transferable transferable) {
// if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor))
// return DataFlavor.stringFlavor;
//
// DataFlavor[] flavors = transferable.getTransferDataFlavors();
// for (DataFlavor flavor : flavors) {
// if (flavor.getMimeType().startsWith("text/plain"))
// return flavor;
// }
//
// return null;
// }
//
// protected boolean importCellObject(JTable table, int row, int column,
// Transferable transferable) {
// Class clazz = table.getColumnClass(column);
// if (clazz.equals(String.class))
// return false;
//
// for (DataFlavor flavor : transferable.getTransferDataFlavors()) {
// if (flavor.getRepresentationClass().equals(clazz)) {
// try {
// System.out.println("importing "
// + flavor.getHumanPresentableName());
// setCellValue(table, row, column, transferable
// .getTransferData(flavor));
// return true;
// } catch (UnsupportedFlavorException e) {
// } catch (IOException e) {
// }
// }
// }
//
// return false;
// }
//
// protected void setCellValue(JTable table, int row, int column,
// Object newValue) {
// if (table instanceof JvUndoableTable == false)
// table.setValueAt(newValue, row, column);
// else {
// JvUndoableTable undoableTable = (JvUndoableTable) table;
// undoableTable.setValueAt(newValue, row, column, true, "Paste");
// }
// }
// }
//
// class JvTransferable implements Transferable {
// protected Object object;
// protected String string;
// protected ArrayList<DataFlavor> flavors = new ArrayList<DataFlavor>();
// protected DataFlavor objectFlavor;
//
// protected static List<DataFlavor> stringFlavors = new ArrayList<DataFlavor>(
// 3);
//
// static {
// try {
// stringFlavors.add(new DataFlavor(
// "text/plain;class=java.lang.String"));
// stringFlavors.add(new DataFlavor(
// DataFlavor.javaJVMLocalObjectMimeType
// + ";class=java.lang.String"));
// stringFlavors.add(DataFlavor.stringFlavor);
//
// } catch (ClassNotFoundException e) {
// System.err.println("error initializing JvTransferable: " + e);
// }
// }
//
// public JvTransferable(Object object) {
// this(object, object.toString());
// }
//
// public JvTransferable(Object object, String string) {
// this.object = object;
// this.string = string;
//
// objectFlavor = new DataFlavor(object.getClass(), object.getClass()
// .getSimpleName());
// flavors.add(objectFlavor);
// flavors.addAll(stringFlavors);
// }
//
// public DataFlavor[] getTransferDataFlavors() {
// DataFlavor[] arrayFlavors = new DataFlavor[flavors.size()];
// return flavors.toArray(arrayFlavors);
// }
//
// public boolean isDataFlavorSupported(DataFlavor flavor) {
// return flavors.contains(flavor);
// }
//
// public Object getTransferData(DataFlavor flavor)
// throws UnsupportedFlavorException, IOException {
// if (objectFlavor.equals(flavor)) {
// if (object.getClass().equals(flavor.getRepresentationClass()))
// return object;
// } else if (isStringFlavor(flavor)) {
// return string;
// }
//
// throw new UnsupportedFlavorException(flavor);
// }
//
// protected boolean isStringFlavor(DataFlavor flavor) {
// return stringFlavors.contains(flavor);
// }
// public void move(JTable table, int mIndex, int sIndex){
// table.moveColumn(mIndex,sIndex);
// }
//	
// }
