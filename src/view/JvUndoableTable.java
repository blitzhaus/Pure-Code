package view;

import javax.swing.JTable;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.TableModel;
import javax.swing.undo.UndoableEdit;

public class JvUndoableTable extends JTable {
	public JvUndoableTable(TableModel dataModel) {
		super(dataModel);
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		setValueAt(value, row, column, true, null);
	}

	public void setValueAt(Object value, int row, int column, boolean undoable) {
		setValueAt(value, row, column, undoable, null);
	}

	public void setValueAt(Object value, int row, int column, boolean undoable,
			String editName) {
		UndoableEditListener[] listeners = getListeners(UndoableEditListener.class);
		if (undoable == false || listeners == null) {
			super.setValueAt(value, row, column);
			return;
		}

		Object oldValue = getValueAt(row, column);
		super.setValueAt(value, row, column);
		JvCellEdit cellEdit = new JvCellEdit(editName, this, oldValue, value,
				row, column);
		fireUndoableEdit(listeners, cellEdit);
	}

	public void addUndoableEditListener(UndoableEditListener listener) {
		listenerList.add(UndoableEditListener.class, listener);
	}

	public void fireUndoableEdit(UndoableEdit edit) {
		UndoableEditListener[] listeners = getListeners(UndoableEditListener.class);
		fireUndoableEdit(listeners, edit);
	}

	protected void fireUndoableEdit(UndoableEditListener[] listeners,
			UndoableEdit edit) {
		UndoableEditEvent editEvent = new UndoableEditEvent(this, edit);
		for (UndoableEditListener listener : listeners)
			listener.undoableEditHappened(editEvent);
	}

}
