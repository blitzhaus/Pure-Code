package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Common.EventCodes;
import Common.ImageLoader;



public class FeaturesPnaleCreation implements EventCodes{
	
	public void FeaturesPnaleCreation(){
		
		featuresPanelCreation();
	}
	
	public FeaturesPnaleCreation(String dummy){
		
	}
	protected JPanel featuresPanel;
	private JButton importButton;
	 public JButton plotsLable;
	 public JButton executeLable;
	 JButton PreviewLable;
	 public JButton tableLabel;
	 JButton tableExeLabel;
	 public JButton avaiAnalButton;
	 private JButton openProjectLable;
	 private JButton redoLable;
	 private JButton undoLable;
	 private JButton saveProjectLable;
	 private JButton newProjectlable;
	
	
	ImageLoader imgLoaderObj = ImageLoader.createImageLoaderInstance();
	
	
	private void featuresPanelCreation() {
		DDViewLayer mlpobj = DDViewLayer.createViewLayerInstance();
		LogoPanelCreation logoPanelCreationObj = DDViewLayer.createLogoPanelInstance();
		
		featuresPanel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
		featuresPanel.setLayout(flowLayout);
		featuresPanel.setLocation(logoPanelCreationObj.logoPanel.getWidth(),0);
		featuresPanel.setSize((int) mlpobj.screenSize.getWidth(), 40);
		featuresPanel.setVisible(true);
		
		
		//Adding the icons pertaining to features
		//just for space lable
		JLabel spaceLable = new JLabel("          ");
		featuresPanel.add(spaceLable);
		
		//lable for new Project
		Image newProjectImage = imgLoaderObj.getImage(DDViewLayer.class, "newProject.jpeg");
		Icon newProjectIcon = new ImageIcon(newProjectImage);
		//JLabel newProjectlable = new JLabel(newProjectIcon);
		newProjectlable = new JButton(newProjectIcon);

		newProjectlable.setBackground(Color.lightGray);
		newProjectlable.setOpaque(false);
		newProjectlable.setContentAreaFilled(false); 
		newProjectlable.setBorderPainted(false); 
		
		newProjectlable.setToolTipText("New Project");
		featuresPanel.add(newProjectlable);
		newProjectlable.setBorder(null);
		newProjectlable.setBackground(null);
		newProjectlable.setActionCommand(NEWPROJECT);
		newProjectlable.addActionListener(DDViewLayer.createViewLayerInstance());
		JLabel seperatorLable = new JLabel("|");
		featuresPanel.add(seperatorLable);
		
		//lable for save project
		Image saveProjectImage = imgLoaderObj.getImage(DDViewLayer.class, "saveProject.jpeg");
		Icon saveprojectIcon = new ImageIcon(saveProjectImage);
		saveProjectLable = new JButton(saveprojectIcon);
		saveProjectLable.setToolTipText("Save Project");

		
		saveProjectLable.setOpaque(false);
		saveProjectLable.setContentAreaFilled(false); 
		saveProjectLable.setBorderPainted(false); 
		
		saveProjectLable.setBackground(Color.lightGray);
		saveProjectLable.setBackground(null);
		saveProjectLable.setBorder(null);
		
		featuresPanel.add(saveProjectLable);
		JLabel seperatorLable1 = new JLabel("|");
		featuresPanel.add(seperatorLable1);
		

		//Undo lable
		Image undoImage = imgLoaderObj.getImage(DDViewLayer.class, "undo.jpeg");
		Icon undoIcon = new ImageIcon(undoImage);
		//JLabel undoLable = new JLabel(undoIcon);
		undoLable = new JButton(undoIcon);
		undoLable.setToolTipText("Undo");
		undoLable.setBackground(Color.lightGray);
		
		undoLable.setOpaque(false);
		undoLable.setContentAreaFilled(false); 
		undoLable.setEnabled(false);
		undoLable.setBorderPainted(false); 
		undoLable.setBorder(null);
		undoLable.setBackground(null);

		
		featuresPanel.add(undoLable);
		JLabel seperatorLable3 = new JLabel("|");
		featuresPanel.add(seperatorLable3);
		
		

		//redo lable
		Image redoImage = imgLoaderObj.getImage(DDViewLayer.class, "redo.jpeg");
		Icon redoIcon = new ImageIcon(redoImage);
		//JLabel redoLable = new JLabel(redoIcon);
		redoLable = new JButton(redoIcon);
		redoLable.setBackground(Color.lightGray);
		redoLable.setEnabled(false);
		redoLable.setOpaque(false);
		redoLable.setContentAreaFilled(false); 
		redoLable.setBorderPainted(false); 
		redoLable.setBorder(null);
		redoLable.setBackground(null);
		
		redoLable.setToolTipText("Redo");
		
		featuresPanel.add(redoLable);
		JLabel seperatorLable4 = new JLabel("|");
		featuresPanel.add(seperatorLable4);
		
		//save project lable
		Image openProjectImage = imgLoaderObj.getImage(DDViewLayer.class, "openProject.jpeg");
		Icon openProjectIcon = new ImageIcon(openProjectImage);
		openProjectLable = new JButton(openProjectIcon);
		openProjectLable.setToolTipText("Open Project");
		openProjectLable.setBackground(null);
		openProjectLable.setBorder(null);
		openProjectLable.setOpaque(false);
		openProjectLable.setContentAreaFilled(false); 
		openProjectLable.setBorderPainted(false); 
		
		featuresPanel.add(openProjectLable);
		JLabel featureSeperator6 = new JLabel("|");
		featuresPanel.add(featureSeperator6);
		
		
		//Import lable
		Image importImage = imgLoaderObj.getImage(DDViewLayer.class, "importProject.jpeg");
		Icon importIcon = new ImageIcon(importImage);
		importButton = new JButton(importIcon);
		//
		
		
		importButton.setOpaque(false);
		importButton.setContentAreaFilled(false); 
		importButton.setBorderPainted(false); 
		importButton.setBorder(null);
		importButton.setBackground(null);
		importButton.setToolTipText("Import Project");
		featuresPanel.add(importButton);

		JLabel featureSeperator5 = new JLabel("|");
		featuresPanel.add(featureSeperator5);
		
		//export Project
		Image exportProjectImage = imgLoaderObj.getImage(DDViewLayer.class, "exportProject.jpeg");
		Icon exportProjectIcon = new ImageIcon(exportProjectImage);
		JLabel exportProjectLable = new JLabel(exportProjectIcon);
		exportProjectLable.setToolTipText("Export Project");
		featuresPanel.add(exportProjectLable);
		JLabel featureSeperator7 = new JLabel("|");
		featuresPanel.add(featureSeperator7);
		
		Image plotsImage = imgLoaderObj.getImage(DDViewLayer.class, "plots.jpeg");
		Icon plotsIcon = new ImageIcon(plotsImage);
		plotsLable = new JButton(plotsIcon);
		plotsLable.addActionListener(DDViewLayer.createViewLayerInstance());
		plotsLable.setActionCommand(ENTERPLOTMAVEN);
		plotsLable.setToolTipText("Plot Maven");
		plotsLable.setBackground(null);
		plotsLable.setBorder(null);
		plotsLable.setVisible(false);
		
		
		featuresPanel.add(plotsLable);
		JLabel featureSeperator8 = new JLabel("|");
		featuresPanel.add(featureSeperator8);
		
		Image executionImage = imgLoaderObj.getImage(DDViewLayer.class, "execute.jpeg");
		Icon executeIcon = new ImageIcon(executionImage);
		executeLable = new JButton(executeIcon);
		executeLable.setVisible(false);
		executeLable.setBackground(null);
		executeLable.setBorder(null);
		executeLable.setToolTipText("Execute");
		executeLable.addActionListener(DDViewLayer.createViewLayerInstance());
		executeLable.setActionCommand(EXECUTE);
		featuresPanel.add(executeLable);
		JLabel featureSeperator9 = new JLabel("|");
		featuresPanel.add(featureSeperator9);
		
		Image closePreviewImage = imgLoaderObj.getImage(DDViewLayer.class, "previewEnabled.jpeg");
		Icon closePreviewIcon = new ImageIcon(closePreviewImage);
		PreviewLable = new JButton(closePreviewIcon);
		PreviewLable.setVisible(false);
		PreviewLable.addActionListener(DDViewLayer.createViewLayerInstance());
		PreviewLable.setActionCommand(PREVIEW);
		PreviewLable.setToolTipText("Close Preview");
		featuresPanel.add(PreviewLable);
		PreviewLable.setBackground(null);
		PreviewLable.setBorder(null);
		JLabel featureSeperator10 = new JLabel("|");
		featuresPanel.add(featureSeperator10);
		
		Image tableMavenImage = imgLoaderObj.getImage(DDViewLayer.class, "table.jpg");
		Icon tableMavenIcon = new ImageIcon(tableMavenImage);
		tableLabel = new JButton(tableMavenIcon);
		tableLabel.setVisible(false);
		tableLabel.addActionListener(DDViewLayer.createViewLayerInstance());
		tableLabel.setActionCommand(ENTERTABLEMAVEN);
		tableLabel.setToolTipText("Table Maven");
		featuresPanel.add(tableLabel);
		tableLabel.setBackground(null);
		tableLabel.setBorder(null);
		JLabel featureSeperator11 = new JLabel("|");
		featuresPanel.add(featureSeperator11);
		
		Image globeImage = imgLoaderObj.getImage(DDViewLayer.class, "smallkaya.jpg");
		Icon globeIcon = new ImageIcon(globeImage);
		avaiAnalButton = new JButton(globeIcon);
		avaiAnalButton.setVisible(false);
		avaiAnalButton.addActionListener(DDViewLayer.createViewLayerInstance());
		avaiAnalButton.setActionCommand(SHOWAAFRAME);
		avaiAnalButton.setBackground(null);
		avaiAnalButton.setBorder(null);
		avaiAnalButton.setToolTipText("Available Analysis");
		featuresPanel.add(avaiAnalButton);
		
		
		
		
		
		/*
		 * This forces the panel to recalculate its 
		 * preferred size based on the preferred 
		 * sizes of its sub components.
		 */
		featuresPanel.setPreferredSize(featuresPanel.getPreferredSize());
		
		featuresPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		mlpobj.desktop.add(featuresPanel);
		
		//action listeners adding for all the components in this features panel
		importButton.setActionCommand(IMPORTPROJECT);
		importButton.addActionListener(DDViewLayer.createViewLayerInstance());
		openProjectLable.addActionListener(DDViewLayer.createViewLayerInstance());
		openProjectLable.setActionCommand(OPENPROJECT);
		redoLable.addActionListener(DDViewLayer.createViewLayerInstance());
		redoLable.setActionCommand(SAVE);
		undoLable.addActionListener(DDViewLayer.createViewLayerInstance());
		undoLable.setActionCommand(SAVE);
		saveProjectLable.addActionListener(DDViewLayer.createViewLayerInstance());
		saveProjectLable.setActionCommand(SAVE);
		newProjectlable.addActionListener(DDViewLayer.createViewLayerInstance());
		newProjectlable.setActionCommand(NEW);
		
		//mouse listeners registering for few components of this feature panel
		PreviewLable.addMouseListener(DDViewLayer.createViewLayerInstance());
		executeLable.addMouseListener(DDViewLayer.createViewLayerInstance());
		plotsLable.addMouseListener(DDViewLayer.createViewLayerInstance());
		
		//mouse motion listener registering for few components of this features panel
		PreviewLable.addMouseMotionListener(DDViewLayer.createViewLayerInstance());
		executeLable.addMouseMotionListener(DDViewLayer.createViewLayerInstance());
		plotsLable.addMouseMotionListener(DDViewLayer.createViewLayerInstance());
		importButton.addMouseMotionListener(DDViewLayer.createViewLayerInstance());
		
		
	}
	

}