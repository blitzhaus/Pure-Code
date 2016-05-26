package view;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;

import Common.EventCodes;
import Common.PETreeFunctions;

public class ProjectExplorerCreation implements EventCodes {

	public void ProjectExplorerCreation() {
		projectExplorerinternalFrame();
	}

	public ProjectExplorerCreation(String dummy) {

	}

	JInternalFrame projectExplorerFrame;
	int projectExplorerOriginalHeight;
	int projectExplorerOriginalWidth;
	DefaultMutableTreeNode workSpace;
	DefaultTreeModel tree1Model;
	JTree tree1;
	HashMap<Object[], DefaultMutableTreeNode> treeHash;
	public boolean isProjectCreation;

	private void projectExplorerinternalFrame() {

		ComponentsPanelCreation cpobj = DDViewLayer
				.createComponentsPaneInstance();
		FeaturesPnaleCreation fpobj = DDViewLayer.createFeaturesPanelInstance();
		MenuBarCreation mbobj = DDViewLayer.createmenuBarInstance();
		DDViewLayer mlobj = DDViewLayer.createViewLayerInstance();

		projectExplorerFrame = new JInternalFrame("Project Explorer", false,
				false, false, false);
		projectExplorerFrame.setLocation(cpobj.componentsPanel.getX()
				+ cpobj.componentsPanel.getWidth(), fpobj.featuresPanel
				.getHeight()
				+ mbobj.menuBarPanel.getHeight());
		projectExplorerFrame.setBorder(mlobj.b);
		projectExplorerFrame.setSize((int) (mlobj.screenSize.getWidth() / 6.5),
				(int) (mlobj.screenSize.getHeight() / 1.15));
		projectExplorerFrame.setVisible(true);
		projectExplorerFrame.getContentPane().addMouseListener(
				new PopClickListener());

		projectExplorerOriginalWidth = projectExplorerFrame.getWidth();
		projectExplorerOriginalHeight = projectExplorerFrame.getHeight();
		workSpace = new DefaultMutableTreeNode("Work Space");

		// Create a tree that allows one selection at a time.
		tree1 = new JTree(workSpace);
		tree1.addMouseListener(new PopClickListener());

		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();

		String workSpaceNodeDispString = PETreeFunctions.createPETreeFuncInst()
				.convertObjToStr(workSpace);

		appInfo.getpENodes().put(workSpaceNodeDispString, workSpace);

		DefaultMutableTreeNode project1 = new DefaultMutableTreeNode(
				"Project 1");
		workSpace.add(project1);
		tree1.setRootVisible(true);
		// tree1.expandPath(workSpace.getPath());
		tree1.setEditable(false);
		treeHash = new HashMap<Object[], DefaultMutableTreeNode>();
		treeHash.put(workSpace.getUserObjectPath(), workSpace);
		tree1Model = (DefaultTreeModel) tree1.getModel();

		// fill defaults for project info
		fillAppInfoProjectInformation(project1);

		tree1.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Listen for when the selection changes.
		tree1.addTreeSelectionListener(DDViewLayer.createViewLayerInstance());
		tree1.addMouseListener(new PopClickListener());

		// Create the scroll pane and add the tree to it.
		JScrollPane treeView = new JScrollPane(tree1);

		treeView.addMouseListener(new PopClickListener());
		projectExplorerFrame.add(treeView);
		mlobj.desktop.add(projectExplorerFrame);
	}

	private void fillAppInfoProjectInformation(DefaultMutableTreeNode project1) {

		// set the selected project as the first project created
		ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
		appInfo.setSelectedprojectPath(PETreeFunctions.createPETreeFuncInst()
				.convertObjToStr(project1));

		appInfo.getpENodes().put(
				PETreeFunctions.createPETreeFuncInst()
						.convertObjToStr(project1), project1);

		// add a project to the array list of projects
		appInfo.getProjectInfoAL().add(new ProjectInfo());

		// set the default project name
		appInfo.getProjectInfoAL().get(appInfo.getProjectInfoAL().size() - 1)
				.setProjectName("Project 1");

	}
}

class PopClickListener extends MouseAdapter {
	public void mousePressed(MouseEvent e) {
		if ((e.isPopupTrigger()))

			doPop(e);
	}

	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger())
			doPop(e);
	}

	private void doPop(MouseEvent e) {
		PopUpForRightClick menu = new PopUpForRightClick();
		Point p = e.getPoint();

		menu.show(e.getComponent(), e.getX(), e.getY());
	}
}

class PopUpForRightClick extends JPopupMenu implements EventCodes {

	JMenuItem newProject;
	JMenuItem importFile;
	JMenuItem ExportToPDF;
	JMenuItem exportToWord;
	JMenuItem save;
	JMenuItem saveas;

	public PopUpForRightClick() {

		newProject = new JMenuItem("New Project");

		newProject.addActionListener(DDViewLayer.createViewLayerInstance());
		newProject.setActionCommand(NEWPROJECT);

		add(newProject);

		importFile = new JMenuItem("Import File");
		importFile.addActionListener(DDViewLayer.createViewLayerInstance());
		importFile.setActionCommand(IMPORTDATA);
		add(importFile);

		ExportToPDF = new JMenuItem("Export To PDf");
		ExportToPDF.addActionListener(DDViewLayer.createViewLayerInstance());
		// ExportToPDF.setActionCommand()
		add(ExportToPDF);

		exportToWord = new JMenuItem("Export To Word");
		exportToWord.addActionListener(DDViewLayer.createViewLayerInstance());
		add(exportToWord);
		// exportToWord.setActionCommand(actionCommand)

		save = new JMenuItem("Save");
		save.addActionListener(DDViewLayer.createViewLayerInstance());
		save.setActionCommand(SAVE);
		add(save);

		saveas = new JMenuItem("Save As");
		saveas.addActionListener(DDViewLayer.createViewLayerInstance());
		saveas.setActionCommand(SAVEAS);
		add(saveas);

	}
}



