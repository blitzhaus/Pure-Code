package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class InVitroMapLeftIFCreator extends JPanel{
	


	
	
	InVitroMappingDispGuiCreator mappingInst;
	
	public InVitroMapLeftIFCreator() throws RowsExceededException, WriteException, BiffException, IOException {
		createMappingLeftIF();
	}
	
	
	public void createMappingLeftIF() throws RowsExceededException, WriteException, BiffException, IOException
	{

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		mappingInst = InVitroMappingDispGuiCreator.createMappingInstance();
		
		mappingInst.availableVariablesListmodel = new DefaultListModel();
		

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		ProcessingInputsInfo procInputInst = CaMapingHandler.createCaMapHandInst().copyProcessingInput();
		
		
		procInputInst.getMappingDataObj()
				.getAvailableColumnsVector().setSize(0);
		for (int i = 0; i < appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
						appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
								appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getColumnPropertiesArrayList()
				.size(); i++) {
			mappingInst.availableVariablesListmodel.add(i, appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
					appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
					.getWorkSheetObjectsAL().get(
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
					.getColumnPropertiesArrayList().get(i).getColumnName());
			
			procInputInst.getMappingDataObj()
					.getAvailableColumnsVector().add(
							i,
							appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
									appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(
											appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(
													appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex())
									.getColumnPropertiesArrayList().get(i)
									.getColumnName());
		}
		mappingInst.inVitroAvailableColumnsList = new JList(mappingInst.availableVariablesListmodel);
		mappingInst.inVitroAvailableColumnsList.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Available Variables", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		mappingInst.inVitroAvailableColumnsList.addListSelectionListener(DDViewLayer.createViewLayerInstance());
		JScrollPane scrlPane = new JScrollPane(mappingInst.inVitroAvailableColumnsList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.anchor = GridBagConstraints.WEST;
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 2;
		gbc_list.gridy = 2;
		gbc_list.ipadx = 180;
		gbc_list.ipady = 200;
		add(scrlPane, gbc_list);

	
	}



}
