package Model;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class TableExport {

	public void exportingTable(JTable outputTable) {

		System.out.println("Trying to export table");

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
			System.out.println("created work book");
			// Sheet s = w.getSheet(0);
			WritableSheet s = w.createSheet("Final Parameters", 0);
			System.out.println("created work sheet");
			System.out.println("The table column count is "
					+ outputTable.getColumnCount());
			int columns = outputTable.getColumnCount();
			System.out.println("The table row count is "
					+ outputTable.getRowCount());
			int rows = outputTable.getRowCount();
			int count = 0;

			// adding the header This is not there in pheonix
			JTableHeader h = outputTable.getTableHeader();
			for (int i = 0; i < outputTable.getColumnCount(); i++) {
				TableColumn tc = h.getColumnModel().getColumn(i);
				String headerName = tc.getHeaderValue().toString();
				System.out.println(headerName);
				jxl.write.Label label = new jxl.write.Label(i, 0, headerName);
				s.addCell(label);
			}

			for (int i = 0; i < columns; i++) {
				for (int j = 0; j < rows; j++) {
					jxl.write.Label label = new jxl.write.Label(i, j + 1,
							(String) outputTable.getValueAt(j, i));
					System.out.print((String) outputTable.getValueAt(j, i)
							+ ", ");
					s.addCell(label);
				}
				System.out.println();
			}
			w.write();
			w.close();

		} catch (Exception e) {

		}

	}

}

class ExtensionFileFilter extends FileFilter {
	String description;

	String extensions[];

	public ExtensionFileFilter(String description, String extension) {
		this(description, new String[] { extension });
	}

	public ExtensionFileFilter(String description, String extensions[]) {
		if (description == null) {
			this.description = extensions[0];
		} else {
			this.description = description;
		}
		this.extensions = (String[]) extensions.clone();
		toLower(this.extensions);
	}

	private void toLower(String array[]) {
		for (int i = 0, n = array.length; i < n; i++) {
			array[i] = array[i].toLowerCase();
		}
	}

	public String getDescription() {
		return description;
	}

	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		} else {
			String path = file.getAbsolutePath().toLowerCase();
			for (int i = 0, n = extensions.length; i < n; i++) {
				String extension = extensions[i];
				if ((path.endsWith(extension) && (path.charAt(path.length()
						- extension.length() - 1)) == '.')) {
					return true;
				}
			}
		}
		return false;
	}
}
