package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.JinternalFrameFunctions;

public class TMSetupAvailComp {

	JInternalFrame tmSetupAvailCompIF;

	public static TMSetupAvailComp TM_SETUP_AVAIL_COMP = null;

	public static TMSetupAvailComp createTmSetupAvailCompInst() {

		if (TM_SETUP_AVAIL_COMP == null) {
			TM_SETUP_AVAIL_COMP = new TMSetupAvailComp("just object creation");
		}
		return TM_SETUP_AVAIL_COMP;
	}

	public TMSetupAvailComp(String dummy) {

	}

	JTree tree;

	void createSetupAvailCompUI() {

		tree = new JTree();
		ApplicationInfo appInfo =  DisplayContents.createAppInfoInstance();
		
		String sheetName = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
		.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getSelectedWorkBookIndex()).getWorkSheetObjectsAL().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
		.getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex())
				.getSelectedWorkBookIndex()).getSelectedSheetIndex()).getSheetName();
		
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(
				sheetName) {
			{
				add(new DefaultMutableTreeNode("Main (Sheet1)"));
				add(new DefaultMutableTreeNode("Table preview"));
			}
		}));

		tree.addTreeSelectionListener(new TreeSelectionListener() {

			
			private TableMavenInfo getTmInfo() {
				ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
				return appInfo
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
								.getSelectedSheetIndex()).getTmInfo();
				}
			
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {

				if (tree.getSelectionPath() != null) {
					TreePath path = tree.getSelectionPath();
					String[] pathSplits = path.toString().split(",");
					if(tree.isRowSelected(0) == true){
					
						try {
							PreviewSelectedSheet.createpreviewShtInstance().showPreview();
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
					}else if (pathSplits[1].contains("Main") == true) {
						
						if(			getTmInfo().isDataChanged() == true){
							
							getTmInfo().setDataChanged(false);
							JOptionPane.showConfirmDialog(TMSetupDispComp.createTmSetupDispCompInst().tmSetupMappingDispCompIF,"Data Changed");
							
						}
						
						TMSetupDispComp.createTmSetupDispCompInst().tmSetupMappingDispCompIF
								.moveToFront();
					} else {
						ApplicationInfo appInfo = DisplayContents
								.createAppInfoInstance();
						int selectedTemplateType = appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
								.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
								.getWorkSheetObjectsAL()
								.get(
										appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList()
												.get(
														appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex())
												.getSelectedSheetIndex())
								.getTmInfo().getTemplateType();
						final ImageIcon templateIcon = TableMavenLaunchScreen
								.createTableMavenLauncherInst()
								.createImageIcon(selectedTemplateType+1);
						TMSetupDispComp.createTmSetupDispCompInst().tmSetupModelIF
								.getContentPane().removeAll();
						
						JTextArea textArea = new JTextArea() {
							Image image = templateIcon.getImage();
							Image grayImage = GrayFilter
									.createDisabledImage(image);
							{
								setOpaque(false);
							} // instance initializer

							public void paintComponent(Graphics g) {
								g
										.drawImage(
												image,
												0,
												0,
												TMSetupDispComp
														.createTmSetupDispCompInst().tmSetupModelIF
														.getWidth(),
												TMSetupDispComp
														.createTmSetupDispCompInst().tmSetupModelIF
														.getHeight(),
												Color.WHITE, this);
								super.paintComponent(g);
							}
						};
						TMSetupDispComp.createTmSetupDispCompInst().tmSetupModelIF
								.getContentPane().add(textArea);
						TMSetupDispComp.createTmSetupDispCompInst().tmSetupModelIF
								.moveToFront();
						TMSetupDispComp.createTmSetupDispCompInst().tmSetupModelIF.validate();

					}
				}

			}


		});
		JScrollPane scrlPaneTree = new JScrollPane(tree,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		tmSetupAvailCompIF = new JInternalFrame("", false, false, false, false);
		tmSetupAvailCompIF.setLocation(0, 0);
		tmSetupAvailCompIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		JinternalFrameFunctions.createIFFunctionsInstance()
				.removeTitleBarForinternalFrame(tmSetupAvailCompIF);
		tmSetupAvailCompIF.setBorder(DDViewLayer.createViewLayerInstance().b);
		tmSetupAvailCompIF.setSize(
				TableMavenCreateUI.createTableMavenUIInst().setupMainIF
						.getWidth() / 5, TableMavenCreateUI
						.createTableMavenUIInst().setupMainIF.getHeight());
		tmSetupAvailCompIF.getContentPane().add(scrlPaneTree);
		tmSetupAvailCompIF.setVisible(true);
		tmSetupAvailCompIF.moveToFront();

		TableMavenCreateUI.createTableMavenUIInst().setupMainIFDP
				.add(tmSetupAvailCompIF);

	}

}
