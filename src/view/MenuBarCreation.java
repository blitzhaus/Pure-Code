package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.sql.rowset.BaseRowSet;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.jfree.chart.ChartPanel;

import com.itextpdf.text.DocumentException;

import Common.EventCodes;
import Common.ExtensionFileFilter;
import Common.ThousandsSeperator;

import Common.SystemTime;

public class MenuBarCreation implements EventCodes {

	private final class SortWSMenuItemFunc implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			SortWSItemHandler swsInst = new SortWSItemHandler();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int height = screenSize.height;
			int width = screenSize.width;
			swsInst.setSize(width / 3, (int) (0.4 * height));
			// here's the part where i center the jframe on screen
			swsInst.setLocationRelativeTo(null);
			swsInst.setVisible(true);
		}
	}

	private final class ExportProjectMenuItemFunc implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			ResultsStorageOptionsDisplayer rsOptionsDisp = new ResultsStorageOptionsDisplayer();
			try {
				rsOptionsDisp.createResultsOptionsTree();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private final class PrintProjectMenuItemFunc implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			PrintOptionsDisplayer rsOptionsDisp = new PrintOptionsDisplayer();
			try {
				rsOptionsDisp.createResultsOptionsTree();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private final class WordExportMenuItemFunc implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			MSWStorageOptionsDisplayer rsOptionsDisp = new MSWStorageOptionsDisplayer();
			try {
				rsOptionsDisp.createResultsOptionsTree();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private final class ExportLogMenuItemFunc implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			LogFrameCreation logpibj = DDViewLayer.createLogFrameInstance();
			JFileChooser importFile = new JFileChooser();
			importFile.setCurrentDirectory(new File("."));
			importFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileFilter odtFileFilter = new ExtensionFileFilter("odt Files",
					new String[] { "odt" });
			importFile.setFileFilter(odtFileFilter);
			FileFilter excelFileFilter = new ExtensionFileFilter("Excel Files",
					new String[] { "xls", "xlsx" });
			importFile.setFileFilter(excelFileFilter);

			importFile.showSaveDialog(null);
			File selectedPfile = importFile.getSelectedFile();
			String path = selectedPfile.getPath();
			try {
				WritableWorkbook w = Workbook.createWorkbook(new File(path));
				WritableSheet s = w.createSheet("Log-  "
						+ sysTImeObj.systemTime(), 0);
				int columns = logpibj.logTable.getColumnCount();
				int rows = logpibj.logTable.getRowCount();
				int count = 0;

				// adding the header This is not there in pheonix
				JTableHeader h = logpibj.logTable.getTableHeader();
				for (int i = 0; i < logpibj.logTable.getColumnCount(); i++) {
					TableColumn tc = h.getColumnModel().getColumn(i);
					String headerName = tc.getHeaderValue().toString();
					jxl.write.Label label = new jxl.write.Label(i, 0,
							headerName);
					s.addCell(label);
				}

				for (int i = 0; i < columns; i++) {
					for (int j = 0; j < rows; j++) {
						jxl.write.Label label = new jxl.write.Label(i, j + 1,
								(String) logpibj.logTable.getValueAt(j, i));
						s.addCell(label);
					}
				}
				w.write();
				w.close();

			} catch (Exception e) {

			}

		}
	}

	private final class ExportTableMenuItemFunc implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser importFile = new JFileChooser();
			importFile.setCurrentDirectory(new File("."));
			importFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileFilter odtFileFilter = new ExtensionFileFilter("odt Files",
					new String[] { "odt" });
			importFile.setFileFilter(odtFileFilter);
			FileFilter excelFileFilter = new ExtensionFileFilter("Excel Files",
					new String[] { "xls", "xlsx" });
			importFile.setFileFilter(excelFileFilter);

			importFile.showSaveDialog(null);
			File selectedPfile = importFile.getSelectedFile();
			String path = selectedPfile.getPath();
			JTable table = null;
			String sheetName = null;
			
			try{
				String[] pathSplits = NcaResultsAvailableComp
				.createNcaResAvailCompInst().availableOutputsTree
				.getSelectionPath().toString().split(",");
				
				
			
				if(pathSplits[pathSplits.length - 1]
								.equalsIgnoreCase(" Final Parameters]")){
					table = NcaResultDispComp.createNcaResDispCompInst().finalparametersVerticalDisplayTable;
					sheetName = "Final Parameters";
				} else if(pathSplits[pathSplits.length - 1]
					.equals(" Transposed Final Parameters]")){
					
					JTable fixedTable = FixedColumnTable.createFixedColumnTableInst().getFixedTable();
					String[][] fixedData = copySelectedSheetData(fixedTable);
					String[][] sheeetData = copySelectedSheetData(NcaResultDispComp
							.createNcaResDispCompInst().finalParametersHorizontalDisplayTable);
					
					sheeetData = mergeSheetDataFixedData(sheeetData, fixedData);
					table = createTable(sheeetData);
					sheetName = "Transposed Final Parameters";
				} else if(pathSplits[pathSplits.length - 1].equals(" Dosing Table]")){
					table = NcaResultDispComp.createNcaResDispCompInst().resultsDosingTable;
					sheetName = "Dosing";
				} else if(pathSplits[pathSplits.length - 1].equals(" Sub Areas Table]")){
					table = NcaResultDispComp.createNcaResDispCompInst().resultsSubAreasTable;
					sheetName = "Sub Areas";
				} else if(pathSplits[pathSplits.length - 1]
					.equalsIgnoreCase(" Summary Table]")){
					table = NcaResultDispComp.createNcaResDispCompInst().resultsSummaryTable;
					sheetName = "Summary";
				}
			}catch (Exception e){
				
			}
			
			
			
			
			exportTableToExcel(table, path, sheetName);

		}

		
		private JTable createTable(String[][] sheeetData) {
			
			JTable table = new JTable(sheeetData.length, sheeetData[0].length);
			for(int i=0;i<table.getRowCount();i++){
				for(int j=0;j<table.getColumnCount();j++){
					table.setValueAt(sheeetData[i][j], i, j);
				}
			}
			return table;
		}


		private String[][] mergeSheetDataFixedData(String[][] sheeetData,
				String[][] fixedData) {
			
			String[][] data = new String[sheeetData.length][(sheeetData[0].length + fixedData[0].length)];
			for(int i=0;i<fixedData.length;i++){
				for(int j=0;j<fixedData[i].length;j++){
					data[i][j] = fixedData[i][j];
				}
			}
			
			for(int i=0;i<sheeetData.length;i++){
				for(int j=0;j<sheeetData[0].length;j++){
					data[i][fixedData[0].length+j] = sheeetData[i][j];
				}
			
			}
			
			return data;
			
		}
		
		private String[][] copySelectedSheetData(JTable table) {

			String[][] data = new String[table.getRowCount() + 1][table
					.getColumnCount()];
			TableColumnModel tm = table.getColumnModel();

			// include header as 1st row in data structure
			for (int j = 0; j < data[0].length; j++) {
				TableColumn tc = tm.getColumn(j);
				data[0][j] = tc.getHeaderValue().toString();
			}

			// copy the table data into the remaining rows of data.
			for (int i = 1; i < data.length; i++) {
				for (int j = 0; j < data[i].length; j++) {

					try {
						data[i][j] = ThousandsSeperator.createThouSepInst()
								.removeComma(table.getValueAt(i - 1, j).toString());
					} catch (NullPointerException ne) {
						data[i][j] = "";
					}

				}
			}

			return data;
		}
		private void exportTableToExcel(JTable table, String path, String sheetName) {
			try {
				WritableWorkbook w = Workbook.createWorkbook(new File(path));
				WritableSheet s = w.createSheet(sheetName, 0);
				int columns = table.getColumnCount();
				int rows = table.getRowCount();

				// adding the header This is not there in pheonix
				JTableHeader h = table.getTableHeader();
				for (int i = 0; i < columns; i++) {
					TableColumn tc = h.getColumnModel().getColumn(i);
					String headerName = tc.getHeaderValue().toString();
					jxl.write.Label label = new jxl.write.Label(i, 0,
							headerName);
					s.addCell(label);
				}

				for (int i = 0; i < columns; i++) {
					for (int j = 0; j < rows; j++) {
						jxl.write.Label label = new jxl.write.Label(
								i,
								j + 1,
								(String) table.getValueAt(j, i));
						s.addCell(label);
					}
				}
				w.write();
				w.close();

			} catch (Exception e) {

			}
			
		}
	}

	private final class ExportTextMenuItemHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			JFileChooser importFile = new JFileChooser();
			importFile.setCurrentDirectory(new File("."));
			importFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileFilter textFileFilter = new ExtensionFileFilter("Text Files",
					new String[] { "txt" });
			importFile.setFileFilter(textFileFilter);
			importFile.showSaveDialog(null);
			File selectedPfile = importFile.getSelectedFile();
			try {
				PrintStream textOutputStram = new PrintStream(
						new FileOutputStream(selectedPfile));
				textOutputStram.print(NcaResultDispComp
						.createNcaResDispCompInst().completeTextOutputTextArea
						.getText());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

		}
	}

	private final class ExportImageMenuItemHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			ChartPanel c = null;
			try {
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				if (NcaOptionsGui.createNcaOptionsInstance().viewsCombo
						.getSelectedIndex() == 0) {
					c = new ChartPanel(
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
									.getWorkSheetObjectsAL()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getWorkBooksArrayList()
													.get(
															appInfo
																	.getProjectInfoAL()
																	.get(
																			appInfo
																					.getSelectedProjectIndex())
																	.getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo()
									.getWorkSheetOutputs()
									.getPlotOutputs()
									.getLinearPlots()
									.get(
											NcaResultDispComp
													.createNcaResDispCompInst().selectedImage));
					c.setSize(1000, 1000);
				} else {
					c = new ChartPanel(
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
									.getWorkSheetObjectsAL()
									.get(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getWorkBooksArrayList()
													.get(
															appInfo
																	.getProjectInfoAL()
																	.get(
																			appInfo
																					.getSelectedProjectIndex())
																	.getSelectedWorkBookIndex())
													.getSelectedSheetIndex())
									.getNcaInfo()
									.getWorkSheetOutputs()
									.getPlotOutputs()
									.getLogplots()
									.get(
											NcaResultDispComp
													.createNcaResDispCompInst().selectedImage));
				}
			} catch (RowsExceededException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BiffException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				c.doSaveAs();
			} catch (IOException e) {

				e.printStackTrace();

			}

		}
	}

	private final class InsertColumnsMenuItemFunc implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			String string = JOptionPane.showInputDialog(
					"Please enter the number of columns to be inserted",
					JOptionPane.YES_NO_OPTION);
			try {
				numberColumns = Integer.parseInt(string);
				int columnSelected = ImportedDataSheet
						.createImportedDataSheetInstance().importedDataTable
						.getSelectedColumn();
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();
				int tempNumberColumns = numberColumns;
				int i = ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
						.getColumnCount();
				while (tempNumberColumns-- > 0) {
					((DefaultTableModel) ImportedDataSheet
							.createImportedDataSheetInstance().importedDataTable
							.getModel()).addColumn("column" + ++i);
					i--;
					for (int j = 0; j < ImportedDataSheet
							.createImportedDataSheetInstance().importedDataTable
							.getRowCount(); j++) {
						ImportedDataSheet.createImportedDataSheetInstance().importedDataTable
								.setValueAt(
										"",
										j,
										ImportedDataSheet
												.createImportedDataSheetInstance().importedDataTable
												.getColumnCount() - 1);
					}
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
											.get(
													appInfo
															.getSelectedProjectIndex())
											.getWorkBooksArrayList()
											.get(
													appInfo
															.getProjectInfoAL()
															.get(
																	appInfo
																			.getSelectedProjectIndex())
															.getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getColumnPropertiesArrayList().add(
									new ColumnProperties());
					ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableListModel
							.add(
									ImportedDataSheet
											.createImportedDataSheetInstance().columnsAvailableListModel
											.getSize(), "column" + ++i);

					// ImportedDataSheet.importedDataTable.moveColumn(ImportedDataSheet.importedDataTable.getColumnCount()-1,
					// columnSelected);
				}

				reflectColumnModelChangesInDS();

			} catch (Exception cannotConvertToInteger) {

				cannotConvertToInteger.printStackTrace();
				while (JOptionPane.YES_OPTION != JOptionPane
						.showConfirmDialog(
								ImportedDataSheet
										.createImportedDataSheetInstance().dataDisplayFrame,
								"Please enter a whole number")) {

				}

			}
		}
	}

	private final class InsertRowsMenuItemFunc implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String string = JOptionPane.showInputDialog(
					"Please enter the number of rows to be inserted",
					JOptionPane.YES_NO_OPTION);

			try {
				int numberOfRows = Integer.parseInt(string);

				int rowSelected = ImportedDataSheet
						.createImportedDataSheetInstance().importedDataTable
						.getSelectedRow();
				System.out.println("The selected row just befodre passing is "
						+ rowSelected);
				String[] s = new String[ImportedDataSheet
						.createImportedDataSheetInstance().importedDataTable
						.getColumnCount()];
				int tempNumberOfRows = numberOfRows;
				while (numberOfRows-- > 0)
					((DefaultTableModel) ImportedDataSheet
							.createImportedDataSheetInstance().importedDataTable
							.getModel()).insertRow(rowSelected + 1, s);

				reflectInDataStructures(tempNumberOfRows, rowSelected);
			} catch (Exception cannotConvertToInteger) {
				cannotConvertToInteger.printStackTrace();
				while (JOptionPane.YES_OPTION != JOptionPane
						.showConfirmDialog(
								ImportedDataSheet
										.createImportedDataSheetInstance().dataDisplayFrame,
								"Please enter a whole number")) {

				}
			}

		}

		private void reflectInDataStructures(int numberOfRows, int rowSelected) {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			ArrayList<String[]> tempDataAL = new ArrayList<String[]>();
			if (appInfo
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
									.getSelectedSheetIndex()).getHasHeader() == true) {
				for (int i = 0; i < appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getElementFromDS(
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
										.getSelectedSheetIndex()).length; i++) {
					if (i != rowSelected + 1) {

						tempDataAL
								.add(appInfo
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
										.getElementFromDS(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getWorkBooksArrayList()
														.get(
																appInfo
																		.getProjectInfoAL()
																		.get(
																				appInfo
																						.getSelectedProjectIndex())
																		.getSelectedWorkBookIndex())
														.getSelectedSheetIndex())[i]);

					} else if (i == rowSelected + 1) {
						tempDataAL
								.add(appInfo
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
										.getElementFromDS(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getWorkBooksArrayList()
														.get(
																appInfo
																		.getProjectInfoAL()
																		.get(
																				appInfo
																						.getSelectedProjectIndex())
																		.getSelectedWorkBookIndex())
														.getSelectedSheetIndex())[i]);
						while (numberOfRows-- > 0) {
							String[] s = new String[appInfo
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
									.getElementFromDS(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getWorkBooksArrayList()
													.get(
															appInfo
																	.getProjectInfoAL()
																	.get(
																			appInfo
																					.getSelectedProjectIndex())
																	.getSelectedWorkBookIndex())
													.getSelectedSheetIndex())[i].length];
							for (int index = 0; index < s.length; index++) {
								s[index] = "";
							}
							tempDataAL.add(s);
						}
					}

				}

			} else if (appInfo
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
									.getSelectedSheetIndex()).getHasHeader() == false) {

				for (int i = 0; i < appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getElementFromDS(
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
										.getSelectedSheetIndex()).length; i++) {
					if (i != rowSelected) {

						tempDataAL
								.add(appInfo
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
										.getElementFromDS(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getWorkBooksArrayList()
														.get(
																appInfo
																		.getProjectInfoAL()
																		.get(
																				appInfo
																						.getSelectedProjectIndex())
																		.getSelectedWorkBookIndex())
														.getSelectedSheetIndex())[i]);

					} else if (i == rowSelected + 1) {
						tempDataAL
								.add(appInfo
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
										.getElementFromDS(
												appInfo
														.getProjectInfoAL()
														.get(
																appInfo
																		.getSelectedProjectIndex())
														.getWorkBooksArrayList()
														.get(
																appInfo
																		.getProjectInfoAL()
																		.get(
																				appInfo
																						.getSelectedProjectIndex())
																		.getSelectedWorkBookIndex())
														.getSelectedSheetIndex())[i]);
						while (numberOfRows-- > 0) {
							String[] s = new String[appInfo
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
									.getElementFromDS(
											appInfo
													.getProjectInfoAL()
													.get(
															appInfo
																	.getSelectedProjectIndex())
													.getWorkBooksArrayList()
													.get(
															appInfo
																	.getProjectInfoAL()
																	.get(
																			appInfo
																					.getSelectedProjectIndex())
																	.getSelectedWorkBookIndex())
													.getSelectedSheetIndex())[i].length];
							for (int index = 0; index < s.length; index++) {
								s[index] = "";
							}
							tempDataAL.add(s);
						}
					}

				}

			}
			String[][] newDS = new String[tempDataAL.size()][];
			for (int i = 0; i < newDS.length; i++) {
				newDS[i] = tempDataAL.get(i);

			}
		}

		private void printDS(String[][] newDS) {
			// just for printing
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("printing");
			for (int i = 0; i < newDS.length; i++) {
				for (int j = 0; j < newDS[i].length; j++) {
					System.out.print(newDS[i][j] + ", ");
				}
				System.out.println();
			}

		}
	}

	public void MenuBarCreation() {
		menuBarPanelCreation();
	}

	public MenuBarCreation(String dummy) {

	}

	JPanel menuBarPanel;
	protected JMenuBar basicMenu;
	protected JMenu importMenu;
	protected JMenuItem importData;
	public JMenu utilities;
	JMenu add;
	JMenuItem statusCodes;
	JMenuItem descStats;
	JMenuItem nonParametricSuperPosition;
	JMenuItem semiCompartmentalModelling;
	JMenuItem tblMaven;
	JMenuItem bql;
	JMenuItem column;
	JMenuItem row;
	int numberColumns;
	JMenuItem exportImage;
	JMenuItem exportTextoutput;
	JMenuItem exportTable;
	SystemTime sysTImeObj = SystemTime.createSystemTimeInstance();
	public JMenuItem sort;
	public JMenuItem tableTransItem;
	public JMenuItem missingData;

	private void menuBarPanelCreation() {

		FeaturesPnaleCreation fpobj = DDViewLayer.createFeaturesPanelInstance();
		LogoPanelCreation lpobj = DDViewLayer.createLogoPanelInstance();
		DDViewLayer mlpobj = DDViewLayer.createViewLayerInstance();

		menuBarPanel = new JPanel();
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
		menuBarPanel.setLayout(flowLayout);
		menuBarPanel.setLocation(lpobj.logoPanel.getWidth(),
				fpobj.featuresPanel.getHeight());
		menuBarPanel.setSize((int) mlpobj.screenSize.getWidth(), 30);
		menuBarPanel.setVisible(true);
		// Menu bar creation
		basicMenu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu New = new JMenu("New");
		fileMenu.add(New);
		JMenuItem newProject = new JMenuItem("Project");
		New.add(newProject);
		newProject.setActionCommand(NEWPROJECT);
		newProject.addActionListener(DDViewLayer.createViewLayerInstance());

		JMenuItem newSheet = new JMenuItem("Sheet");
		New.add(newSheet);
		newSheet.setActionCommand(NEWSHEET);
		newSheet.addActionListener(DDViewLayer.createViewLayerInstance());

/*		JMenuItem openFile = new JMenuItem("Open File");
		openFile.addActionListener(ViewLayer.createViewLayerInstance());
		openFile.setActionCommand(OPEN);
		fileMenu.add(openFile);*/

		JMenuItem load = new JMenuItem("Load Project");
		load.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_L, java.awt.Event.CTRL_MASK));// revisit
		// this
		// key.
		load.addActionListener(DDViewLayer.createViewLayerInstance());
		load.setActionCommand(LOAD);
		fileMenu.add(load);

/*		JMenuItem close = new JMenuItem("Close");
		close.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_W, java.awt.Event.CTRL_MASK));
		close.addActionListener(ViewLayer.createViewLayerInstance());
		close.setActionCommand(CLOSE);
		fileMenu.add(close);*/
		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));
		save.addActionListener(DDViewLayer.createViewLayerInstance());
		save.setActionCommand(SAVE);
		fileMenu.add(save);
		JMenuItem saveAs = new JMenuItem("Save As...");

		saveAs.addActionListener(DDViewLayer.createViewLayerInstance());
		saveAs.setActionCommand(SAVEAS);

		fileMenu.add(saveAs);
		JMenuItem saveLog = new JMenuItem("Save Log");
		fileMenu.add(saveLog);
		JMenuItem print = new JMenuItem("Print");
		fileMenu.add(print);
		print.setAccelerator(KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_P, java.awt.Event.CTRL_MASK));
		JMenuItem exit = new JMenuItem("Exit");
		fileMenu.add(exit);
		basicMenu.add(fileMenu);
		JMenu editMenu = new JMenu("Edit");
		sort = new JMenuItem("Sort Worksheet");
		sort.setEnabled(false);

		sort.addActionListener(new SortWSMenuItemFunc());
		editMenu.add(sort);

/*		JMenuItem undo = new JMenuItem("Undo");
		editMenu.add(undo);
		JMenuItem redo = new JMenuItem("Redo");
		editMenu.add(redo);*/
		JMenuItem copy = new JMenuItem("Copy");
		copy.addActionListener(DDViewLayer.createViewLayerInstance());
		copy.setActionCommand(COPY);
		editMenu.add(copy);
		JMenuItem paste = new JMenuItem("Paste");
		paste.addActionListener(DDViewLayer.createViewLayerInstance());
		paste.setActionCommand(PASTE);
		editMenu.add(paste);
		JMenuItem fildown = new JMenuItem("Fill Down");
		fildown.addActionListener(DDViewLayer.createViewLayerInstance());
		fildown.setActionCommand(FILLDOWN);
		editMenu.add(fildown);
		JMenuItem selectAll = new JMenuItem("Select All");
		selectAll.addActionListener(DDViewLayer.createViewLayerInstance());
		selectAll.setActionCommand(SELECTALL);
		editMenu.add(selectAll);
		JMenuItem delete = new JMenuItem("Delete");
		delete.addActionListener(DDViewLayer.createViewLayerInstance());
		delete.setActionCommand(DELETE);
		editMenu.add(delete);


		tableTransItem = new JMenuItem("Table Transformations");
		tableTransItem.addActionListener(DDViewLayer.createViewLayerInstance());
		tableTransItem.setActionCommand(TABTRANSAJITH);
		editMenu.add(tableTransItem);
		tableTransItem.setEnabled(false);
		
		missingData = new JMenuItem("Missing Data");
		missingData.addActionListener(DDViewLayer.createViewLayerInstance());
		missingData.setActionCommand(FINDREPLACE);
		missingData.setEnabled(false);
		editMenu.add(missingData);


		basicMenu.add(editMenu);
		
		/*JMenu projectMenu = new JMenu("project");
		JMenuItem openProject = new JMenuItem("open Project");
		projectMenu.add(openProject);
		JMenuItem importProject = new JMenuItem("Import Project");
		projectMenu.add(importProject);
		JMenuItem projectProperties = new JMenuItem("project Properties");
		projectMenu.add(projectProperties);
		basicMenu.add(projectMenu);*/
		importMenu = new JMenu("Import");
		importData = new JMenuItem("Import Data");
		importData.addActionListener(DDViewLayer.createViewLayerInstance());
		importData.setActionCommand(IMPORTDATA);
		importData.addMouseMotionListener(DDViewLayer.createViewLayerInstance());
		importData.addMouseListener(DDViewLayer.createViewLayerInstance());
		importMenu.add(importData);
		/*JMenuItem projectImport = new JMenuItem("Import Project");
		importMenu.add(projectImport);*/
		basicMenu.add(importMenu);

		utilities = new JMenu("Utilities");
		utilities.setEnabled(false);
		// JMenuItem sort = new JMenuItem("Sort");
		// utilities.add(sort);
/*		JMenuItem join = new JMenuItem("Join");
		utilities.add(join);
		JMenuItem merge = new JMenuItem("Merge");
		utilities.add(merge);*/

		descStats = new JMenuItem("Descriptive Stat");
		utilities.add(descStats);
		descStats.addActionListener(DDViewLayer.createViewLayerInstance());
		descStats.setActionCommand(ENTERDESSTATS);
		nonParametricSuperPosition = new JMenuItem(
				"Non Parametric Superposition");
		utilities.add(nonParametricSuperPosition);
		nonParametricSuperPosition.addActionListener(DDViewLayer
				.createViewLayerInstance());
		nonParametricSuperPosition.setActionCommand(ENTERNPS);
		semiCompartmentalModelling = new JMenuItem(
				"Semi- Compartmental Modelling");
		utilities.add(semiCompartmentalModelling);
		semiCompartmentalModelling.addActionListener(DDViewLayer
				.createViewLayerInstance());
		semiCompartmentalModelling.setActionCommand(ENTERSCA);

		tblMaven = new JMenuItem("Table Maven");
		utilities.add(tblMaven);
		tblMaven.addActionListener(DDViewLayer.createViewLayerInstance());
		tblMaven.setActionCommand(ENTERTABLEMAVEN);

		JMenuItem chartWizard = new JMenuItem("Plot Maven");
		utilities.add(chartWizard);
		bql = new JMenuItem("BQL");
		bql.addActionListener(DDViewLayer.createViewLayerInstance());
		bql.setActionCommand(ENTERBQL);
		utilities.add(bql);
		basicMenu.add(utilities);


		add = new JMenu("Insert");
		row = new JMenuItem("Rows");
		row.addActionListener(new InsertRowsMenuItemFunc());
		add.add(row);

		column = new JMenuItem("Column");
		column.addActionListener(new InsertColumnsMenuItemFunc());

		add.add(column);
		add.setEnabled(false);
		basicMenu.add(add);

		JMenu helpmenu = new JMenu("Help");
		JMenuItem welcome = new JMenuItem("Welcome");
		helpmenu.add(welcome);
		welcome.setEnabled(false);
		JMenuItem helpDoc = new JMenuItem("Help Document");
		helpmenu.add(helpDoc);
		JMenuItem dynamicHelp = new JMenuItem("Dynamic Help");
		helpmenu.add(dynamicHelp);
		dynamicHelp.setEnabled(false);
		JMenuItem search = new JMenuItem("Search");
		helpmenu.add(search);
		search.setEnabled(false);
		JMenuItem about = new JMenuItem("About TCS qBench");
		helpmenu.add(about);
		about.setEnabled(false);
		basicMenu.add(helpmenu);
		menuBarPanel.add(basicMenu);

		JMenu exportMenu = new JMenu("Export");
		exportImage = new JMenuItem("Export Image");

		// exportImage.setEnabled(false);
		exportImage.addActionListener(new ExportImageMenuItemHandler());

		exportImage.setEnabled(false);
		exportMenu.add(exportImage);

		exportTextoutput = new JMenuItem("Export Text");
		exportTextoutput.addActionListener(new ExportTextMenuItemHandler());
		exportTextoutput.setEnabled(false);
		exportMenu.add(exportTextoutput);

		exportTable = new JMenuItem("Export Table");
		exportTable.addActionListener(new ExportTableMenuItemFunc());
		exportMenu.add(exportTable);
		exportTable.setEnabled(false);

		/*JMenuItem exportLog = new JMenuItem("Export Log");
		exportLog.addActionListener(new ExportLogMenuItemFunc());
		exportMenu.add(exportLog);*/

		JMenuItem exportProject = new JMenuItem("Export To Pdf");
		exportProject.addActionListener(new ExportProjectMenuItemFunc());
		exportMenu.add(exportProject);

		JMenuItem printProject = new JMenuItem("Print Analysis");
		printProject.addActionListener(new PrintProjectMenuItemFunc());
		exportMenu.add(printProject);

		JMenuItem wordExport = new JMenuItem("Export To Word");
		wordExport.addActionListener(new WordExportMenuItemFunc());
		exportMenu.add(wordExport);

		basicMenu.add(exportMenu);
		menuBarPanel.add(basicMenu);

		mlpobj.desktop.add(menuBarPanel);
	}

	public void reflectColumnModelChangesInDS() {

		// The logic is to take the entire importedDataSheet.importedDataTable
		// along with header and store it in appInfo
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		String[][] tempData = new String[ImportedDataSheet
				.createImportedDataSheetInstance().importedDataTable.getModel()
				.getRowCount() + 1][ImportedDataSheet
				.createImportedDataSheetInstance().importedDataTable.getModel()
				.getColumnCount()];
		TableColumnModel tm = ImportedDataSheet
				.createImportedDataSheetInstance().importedDataTable
				.getColumnModel();

		if (appInfo
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
								.getSelectedSheetIndex()).getHasHeader() == true) {

			// filling the already present columns
			for (int i = 0; i < appInfo
					.getProjectInfoAL()
					.get(appInfo.getSelectedProjectIndex())
					.getWorkBooksArrayList()
					.get(
							appInfo.getProjectInfoAL().get(
									appInfo.getSelectedProjectIndex())
									.getSelectedWorkBookIndex())
					.getElementFromDS(
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
									.getSelectedSheetIndex())[0].length; i++) {
				tempData[0][i] = appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getElementFromDS(
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
										.getSelectedSheetIndex())[0][i];
				TableColumn tc = tm.getColumn(i);
				tc.setHeaderValue(tempData[0][i]);

			}

			// filling the added columns and add them oly while inserting but
			// not while moving
			if (ImportedDataSheet.createImportedDataSheetInstance().isColumnMoving == true) {

			} else if (ImportedDataSheet.createImportedDataSheetInstance().isColumnMoving == false) {
				for (int i = appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getElementFromDS(
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
										.getSelectedSheetIndex())[0].length; i < (appInfo
						.getProjectInfoAL()
						.get(appInfo.getSelectedProjectIndex())
						.getWorkBooksArrayList()
						.get(
								appInfo.getProjectInfoAL().get(
										appInfo.getSelectedProjectIndex())
										.getSelectedWorkBookIndex())
						.getElementFromDS(
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
										.getSelectedSheetIndex())[0].length + numberColumns); i++) {
					tempData[0][i] = "Column " + (i + 1);
					TableColumn tc = tm.getColumn(i);
					tc.setHeaderValue(tempData[0][i]);
				}
			}

		} else {
			for (int i = 0; i < tempData[0].length; i++) {
				tempData[0][i] = "Column " + (i + 1);
			}
		}

		for (int i = 1; i < tempData.length; i++) {
			for (int j = 0; j < tempData[i].length; j++) {
				tempData[i][j] = (String) ImportedDataSheet
						.createImportedDataSheetInstance().importedDataTable
						.getValueAt(i - 1, j);

			}

		}

		appInfo
				.getProjectInfoAL()
				.get(appInfo.getSelectedProjectIndex())
				.getWorkBooksArrayList()
				.get(
						appInfo.getProjectInfoAL().get(
								appInfo.getSelectedProjectIndex())
								.getSelectedWorkBookIndex())
				.setElementInDS(
						tempData,
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
								.getSelectedSheetIndex());
		refreshColumnsDisplay();

	}

	private void refreshColumnsDisplay() {
		ImportedDataSheet.createImportedDataSheetInstance()
				.fillColumnsAvailableList();
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableList
				.revalidate();
		ImportedDataSheet.createImportedDataSheetInstance().columnsAvailableInternalFrame
				.repaint();

	}

}
