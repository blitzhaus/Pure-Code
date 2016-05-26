package view;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.JinternalFrameFunctions;

public class NcaParameterNamesDispGui {

	
	ParameterNamesData paramNamesInst;
	private final class NcaParametersNamesTableHandler implements
			TableModelListener {
		public void tableChanged(TableModelEvent e) {
			ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			if (parameterNamesTable.getSelectedColumn() == 1) {
				isRestoringParameterNames = true;
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterNamesObj()
						.setPrefferedNameAt(
								parameterNamesTable.getSelectedRow(),
								(String) parameterNamesTable
										.getValueAt(parameterNamesTable
												.getSelectedRow(),
												parameterNamesTable
														.getSelectedColumn()));

				String c = (String) parameterNamesTable.getValueAt(
						parameterNamesTable.getSelectedRow(),
						parameterNamesTable.getSelectedColumn());
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
						.getWorkSheetObjectsAL().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
										.getSelectedSheetIndex()).getNcaInfo()
						.getProcessingInputs().getParameterNamesObj()
						.setPrefferedNameAt(
								parameterNamesTable.getSelectedRow(), c);

			} else if (parameterNamesTable.getSelectedColumn() == 2) {/*
				isRestoringParameterNames = true;

				if (parameterNamesTable.getValueAt(
						parameterNamesTable.getSelectedRow(),
						parameterNamesTable.getSelectedColumn()).equals("yes")) {
					parameterNamesTable.setValueAt("no", parameterNamesTable
							.getSelectedRow(), parameterNamesTable
							.getSelectedColumn());
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getParameterNamesObj().getIncludeInOutput()
							.set(parameterNamesTable.getSelectedRow(), false);
				} else if (parameterNamesTable.getValueAt(
						parameterNamesTable.getSelectedRow(),
						parameterNamesTable.getSelectedColumn()).equals("no")) {
					parameterNamesTable.setValueAt("yes", parameterNamesTable
							.getSelectedRow(), parameterNamesTable
							.getSelectedColumn());
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
							.getWorkSheetObjectsAL().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
											.getSelectedSheetIndex())
							.getNcaInfo().getProcessingInputs()
							.getParameterNamesObj().getIncludeInOutput().set(
									parameterNamesTable.getSelectedRow(), true);
				}

			*/} else if (parameterNamesTable.getSelectedColumn() == 3) {
				isRestoringParameterNames = true;
			}
		}
	}

	JInternalFrame parameterNamesInternalFrame;
	JTable parameterNamesTable;
	boolean isRestoringParameterNames;

	public static NcaParameterNamesDispGui NCA_PAR_NAMES_DISP = null;

	public static NcaParameterNamesDispGui createParameterNamesInstance()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		if (NCA_PAR_NAMES_DISP == null) {
			NCA_PAR_NAMES_DISP = new NcaParameterNamesDispGui(
					"just object creation");
		}

		return NCA_PAR_NAMES_DISP;
	}

	public NcaParameterNamesDispGui(String dummy) {

	}

	public void NcaParameterNamesDispGuiCreation()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		createParameterNamesInternalFrame();
	}

	private void createParameterNamesInternalFrame()
			throws RowsExceededException, WriteException, BiffException,
			IOException {
		parameterNamesInternalFrame = new JInternalFrame("Parameter Names ",
				true, true, true, true);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(parameterNamesInternalFrame);
		parameterNamesInternalFrame.setLocation(NcaMappingDispGui
				.createMappingInstance().MappingInternalFrame.getX(),
				NcaMappingDispGui.createMappingInstance().MappingInternalFrame
						.getY());
		parameterNamesInternalFrame.setSize(NcaMappingDispGui
				.createMappingInstance().MappingInternalFrame.getWidth(),
				NcaMappingDispGui.createMappingInstance().MappingInternalFrame
						.getHeight());
		parameterNamesInternalFrame.setVisible(true);
		NcaTabbedPanes.createNcaTabbedPaneInstance().setupTabDesktopPane
				.add(parameterNamesInternalFrame);

		parameterNamesTable = new JTable(0, 3) {
			public boolean isCellEditable(int row, int col) {
				if ((col == 2) || (col == 0)) {
					return false;
				} else {
					return true;
				}
			}
		};
		// parameterNamesTable.addMouseListener(ViewLayer.createViewLayerInstance());
		parameterNamesTable.getModel().addTableModelListener(
				new NcaParametersNamesTableHandler());
		JTableHeader parameterNamesTableHeader = parameterNamesTable
				.getTableHeader();
		TableColumnModel tm = parameterNamesTableHeader.getColumnModel();
		TableColumn tc = tm.getColumn(0);
		tc.setHeaderValue("Default Name");
		;
		tc = tm.getColumn(1);
		tc.setHeaderValue("preferred Name");
		tc = tm.getColumn(2);
		tc.setHeaderValue("Include in output");
		parameterNamesTable.setRowSelectionAllowed(true);
		parameterNamesTable.setColumnSelectionAllowed(true);
		parameterNamesTable.setCellSelectionEnabled(true);
		parameterNamesTable.setBackground(Color.white);
		parameterNamesTable.addMouseListener(DDViewLayer.createViewLayerInstance());
		JScrollPane parameterNamesScrollPane = new JScrollPane(
				parameterNamesTable);
		parameterNamesInternalFrame.getContentPane().add(
				parameterNamesScrollPane);
		

	}

}
