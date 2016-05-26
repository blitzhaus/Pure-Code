package view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public final class IMPDataTableListSelectionHandler implements
ListSelectionListener {
private final ApplicationInfo appInfo;

public IMPDataTableListSelectionHandler(ApplicationInfo appInfo) {
this.appInfo = appInfo;
}

@Override
public void valueChanged(ListSelectionEvent arg0) {
	
	if(arg0.getValueIsAdjusting() == false){
		int selectedRow = ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getSelectedRow();
		int selectedColumn = ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getSelectedColumn();
		
		
		
		final KeyStroke deleteKey = KeyStroke.getKeyStroke("DELETE");
		
		Action newDeleteAction = new AbstractAction("") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				int selectedRow = ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getSelectedRow();
			
				if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(ImportedDataSheet.createImportedDataSheetInstance().dataDisplayFrame, "Conform Rows Deletion")){
					
					
					
						((DefaultTableModel)ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getModel()).removeRow(selectedRow);
						reflectTheDeletionInDataStructure(selectedRow);
					
				}
			}

			private void reflectTheDeletionInDataStructure(
int selectedRow) {

					ArrayList<String[]> tempDataAL = new ArrayList<String[]>();

					if (appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getHasHeader() == true) {
						for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getElementFromDS(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).length; i++) {

							if (i != selectedRow + 1)
								tempDataAL
										.add(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getElementFromDS(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())[i]);
						}

					} else {
						for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getElementFromDS(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex()).length; i++) {

							if (i != selectedRow)
								tempDataAL
										.add(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getElementFromDS(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
																.get(
																		appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
																.getSelectedSheetIndex())[i]);
						}

					}

					String[][] newDS = new String[tempDataAL.size()][];

					for (int i = 0; i < newDS.length; i++) {
						newDS[i] = tempDataAL.get(i);
						for (int j = 0; j < newDS[i].length; j++) {
							System.out.print(newDS[i][j] + ", ");
						}
						System.out.println();
					}

					// setting the modified data into the data structure
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).setElementInDS(
							newDS,
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
									.getSelectedSheetIndex());

				}
		};
		ImportedDataSheet.createImportedDataSheetInstance().importedDataTable.getInputMap(JComponent.WHEN_FOCUSED).put(deleteKey,
         "EmptyRowAction");
		ImportedDataSheet.createImportedDataSheetInstance(). importedDataTable.getActionMap().put("EmptyRowAction", newDeleteAction);
	
	}
}
}