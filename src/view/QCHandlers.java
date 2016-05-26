package view;

import javax.swing.JTable;

public class QCHandlers {

	public QCHandlers(JTable conditionsTable){
		handler(conditionsTable);
	}

	private void handler(JTable conditionsTable) {
		
		if(conditionsTable.getSelectedColumn() == 1){
			new QCConditionsTable();
		}
		
		
		
	}
	
}
