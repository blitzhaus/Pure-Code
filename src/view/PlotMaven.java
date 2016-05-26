package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import jxl.Workbook;
import jxl.read.biff.BiffException;





public class PlotMaven extends JFrame implements MouseListener{

	
	 protected static JTextField filePathTextField;
	 protected static JButton browsePlotMavenButton;
	 protected static JInternalFrame existingDataInternalFrame;
	 protected static JInternalFrame chooseDataInternalFrame;
	 protected static JDesktopPane welcomeToPlotMavenDesktopPane;
	 int browsePlotMavenButtonClickCount = 0;
	 static JFrame welcomeToPlotMavenFrame;
	 JInternalFrame NavigationInternalFrame ;
	 JInternalFrame ImageInternalFrame;
	static  DefaultListModel existingDataListModel;
	 JButton nextButton;
	 JButton cancelButton;
	 static JList existingDataList;
	
	 public void createFrame(String title) throws BiffException, IOException {/*
			// TODO Auto-generated method stub
		 	welcomeToPlotMavenFrame = new JFrame(title);
		 	//welcomeToPlotMavenFrame.setDefaultCloseOperation(cancelButton.doClick());
		 	welcomeToPlotMavenFrame.addWindowListener(new WindowAdapter() {
		 		 public void windowClosing(WindowEvent winEvt) {
		 	       MainLayoutPage.isDirectEntryIntoPlotMaven = false;
		 	       System.out.println("The value is "+MainLayoutPage.isDirectEntryIntoPlotMaven);
		 	       // System.exit(0); 
		 	    }

			});
			welcomeToPlotMavenFrame.setLocation((int)MainLayoutPage.screenSize.getWidth()/2-200, (int)MainLayoutPage.screenSize.getHeight()/2-200);
			
			welcomeToPlotMavenFrame.setSize(400, 400);
			welcomeToPlotMavenFrame.setResizable(false);
			welcomeToPlotMavenDesktopPane = new JDesktopPane();
			welcomeToPlotMavenFrame.getContentPane().add(welcomeToPlotMavenDesktopPane);
			welcomeToPlotMavenFrame.setVisible(true);
			welcomeToPlotMavenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			existingDataInternalFrame = new JInternalFrame("Imported Data",false,false,false,false);
			existingDataInternalFrame.setLocation(0, 200);
			existingDataInternalFrame.setSize(welcomeToPlotMavenFrame.getWidth()/2, welcomeToPlotMavenFrame.getHeight()/4 );
			existingDataInternalFrame.setVisible(true);
			existingDataInternalFrame.setBorder(MainLayoutPage.b);
			existingDataInternalFrame.setBackground(Color.white);
			welcomeToPlotMavenDesktopPane.add(existingDataInternalFrame);
			
			
			
			chooseDataInternalFrame = new JInternalFrame("Choose Data", true,true,true,true);
			chooseDataInternalFrame.setLayout(new GridBagLayout());
			if(isDirectEntryIntoPlotMaven == false){
				chooseDataInternalFrame.setLocation(existingDataInternalFrame.getX()+existingDataInternalFrame.getWidth(), existingDataInternalFrame.getY());
				chooseDataInternalFrame.setSize(existingDataInternalFrame.getWidth(),existingDataInternalFrame.getHeight());
			}else{
				
				chooseDataInternalFrame.setLocation(0, 0);
				chooseDataInternalFrame.setSize(welcomeToPlotMavenFrame.getWidth(), welcomeToPlotMavenFrame.getHeight()/2);
			}
			chooseDataInternalFrame.setLocation(existingDataInternalFrame.getX()+existingDataInternalFrame.getWidth(), existingDataInternalFrame.getY());
			chooseDataInternalFrame.setSize(existingDataInternalFrame.getWidth(), existingDataInternalFrame.getHeight());
			chooseDataInternalFrame.setVisible(true);
			chooseDataInternalFrame.setBorder(MainLayoutPage.b);
			chooseDataInternalFrame.setBackground(Color.white);
			welcomeToPlotMavenDesktopPane.add(chooseDataInternalFrame);
			
			
			filePathTextField = new JTextField();
			GridBagConstraints filePathTextFieldCon = new GridBagConstraints();
			filePathTextFieldCon.gridx = 0;
			filePathTextFieldCon.gridy = 0;
			filePathTextField.setColumns(15);
			chooseDataInternalFrame.getContentPane().add(filePathTextField,filePathTextFieldCon);
			
			browsePlotMavenButton = new JButton("...");
			browsePlotMavenButton.addMouseListener(this);
			GridBagConstraints browseButtonCon = new GridBagConstraints();
			browseButtonCon.gridx = 0;
			browseButtonCon.gridy = 1;
			browsePlotMavenButton.setPreferredSize(new Dimension(50, 20));
			chooseDataInternalFrame.getContentPane().add(browsePlotMavenButton,browseButtonCon);
			
			NavigationInternalFrame = new JInternalFrame("navigation", false,false,false,false);
			NavigationInternalFrame.setLayout(new GridBagLayout());
			NavigationInternalFrame.setBackground(Color.white);
			NavigationInternalFrame.setBorder(MainLayoutPage.b);
			NavigationInternalFrame.setLocation(0, chooseDataInternalFrame.getY()+chooseDataInternalFrame.getHeight());
			NavigationInternalFrame.setSize(welcomeToPlotMavenFrame.getWidth(), welcomeToPlotMavenFrame.getHeight()-(chooseDataInternalFrame.getY()+chooseDataInternalFrame.getHeight()));
			NavigationInternalFrame.setVisible(true);
			welcomeToPlotMavenDesktopPane.add(NavigationInternalFrame);
			
			ImageInternalFrame = new JInternalFrame("Image Frame",false,false,false,false);
			ImageInternalFrame.setBorder(MainLayoutPage.b);
			ImageInternalFrame.setLocation(0, 0);
			ImageInternalFrame.setSize(welcomeToPlotMavenFrame.getWidth(), welcomeToPlotMavenFrame.getHeight()/2);
			ImageInternalFrame.setVisible(true);
			welcomeToPlotMavenDesktopPane.add(ImageInternalFrame);
			
			
			
			Image plotMavenImage = ImageLoader.getImage(MainLayoutPage.class, "PlotMaven.jpg");
			Icon plotMavenIcon = new ImageIcon(plotMavenImage);
			JLabel plotMavenLable = new JLabel(plotMavenIcon);
			plotMavenLable.setVisible(true);
			ImageInternalFrame.getContentPane().add(plotMavenLable);
			
			
			if(MainLayoutPage.isDirectEntryIntoPlotMaven == true){
				
				//existingDataInternalFrame.getContentPane().repaint();
			}else{
				existingDataInternalFrame.getContentPane().setBackground(new Color(236, 236, 236));
			}
			
			MainLayoutPage.fix(welcomeToPlotMavenDesktopPane);
			MainLayoutPage.removeTitleBarForinternalFrame(NavigationInternalFrame);
			MainLayoutPage.removeTitleBarForinternalFrame(ImageInternalFrame);
			
			// next button in the navigatio pane
			nextButton = new JButton("Next");
			nextButton.addMouseListener(this);
			nextButton.setPreferredSize(new Dimension(80, 25));
			GridBagConstraints nextbuttonCon = new GridBagConstraints();
			nextbuttonCon.gridx = 0;
			nextbuttonCon.gridy = 0;
			nextbuttonCon.weightx = 0.5;
			nextbuttonCon.anchor = GridBagConstraints.LINE_END;
			NavigationInternalFrame.getContentPane().add(nextButton,nextbuttonCon);
			nextButton.setEnabled(false);
			
			cancelButton = new JButton("Cancel");
			cancelButton.addMouseListener(this);
			cancelButton.setPreferredSize(new Dimension(100, 25));
			GridBagConstraints cancelButtonCon = new GridBagConstraints();
			cancelButtonCon.gridx = 1;
			cancelButtonCon.gridy = 0;
			cancelButtonCon.weightx =0.5;
			cancelButtonCon.anchor = GridBagConstraints.LINE_END;
			NavigationInternalFrame.getContentPane().add(cancelButton,cancelButtonCon);
		*/}
	
	
	public PlotMaven(){
		//JDesktopPane 
		
		
	}





	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
		if(arg0.getSource() == existingDataList){
			System.out.println("The selected sheet is "+existingDataListModel.get(existingDataList.getSelectedIndex()));
			nextButton.setEnabled(true);
		}
		
		
		if(arg0.getSource() == nextButton){
			//MainLayoutPage.isDirectEntryIntoPlotMaven = true;
			String plotMavenextensionFile = null;
			int importedFileCount = 1;
			try{
				String plotMavenFilepath = filePathTextField.getText();
				//importLable.removeMouseListener(object);
				//importLable.removeMouseMotionListener(object);
				//importLable.enable(false);
				System.out.println("path is "+plotMavenFilepath);
				File fileExtension = new File(plotMavenFilepath);
				if(!fileExtension.exists()){
					System.out.println("The file does not exist.... while trying to read the extension in second screen");
				}
				else{
					
					int dotPos = plotMavenFilepath.lastIndexOf(".");
					
					plotMavenextensionFile = plotMavenFilepath.substring(dotPos);
					System.out.println("Extension is "+plotMavenextensionFile);
					
				}
				
				DisplayContents d = new DisplayContents();
				
					d.createFrame();
				d.fillFilesFrame(plotMavenFilepath,importedFileCount);
				d.getdata(plotMavenextensionFile, plotMavenFilepath);
				welcomeToPlotMavenFrame.setVisible(false);
			
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
			/*PlotMavenMainScreen PlotMavenMainScreenFrame = new PlotMavenMainScreen();
			
			PlotMavenMainScreenFrame.createUI();
			MainLayoutPage.availableAnalysisFrame.setVisible(false);
			MainLayoutPage.logFrame.setVisible(false);
			MainLayoutPage.mainTabbedFrame.setSize(MainLayoutPage.mainTabbedFrame.getWidth()+MainLayoutPage.availableAnalysisFrame.getWidth(), MainLayoutPage.mainTabbedFrame.getHeight()+MainLayoutPage.logFrame.getHeight());
		*/}
		
		if(arg0.getSource() == cancelButton){/*
			MainLayoutPage.isDirectEntryIntoPlotMaven = false;
			MainLayoutPage.plotsLable.setEnabled(true);
			MainLayoutPage.plotsLable.addMouseListener(MainLayoutPage.object);
			welcomeToPlotMavenFrame.dispose();
			
		*/}
		
		
		if(arg0.getSource() == browsePlotMavenButton){/*

			String plotMavenFilePath = MainLayoutPage.importFile();
			browsePlotMavenButtonClickCount++;
			Workbook plotDataWorkBook = null;
			try {
				plotDataWorkBook = Workbook.getWorkbook(new File(plotMavenFilePath));
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Error while creating the work book for plot macen");
				e.printStackTrace();
			}
			filePathTextField.setText(plotMavenFilePath);
			
			if((MainLayoutPage.isDirectEntryIntoPlotMaven == true)&&(browsePlotMavenButtonClickCount == 1)){
				chooseDataInternalFrame.setLocation(existingDataInternalFrame.getX()+existingDataInternalFrame.getWidth(), existingDataInternalFrame.getY());
				chooseDataInternalFrame.setSize(existingDataInternalFrame.getWidth(),existingDataInternalFrame.getHeight());
				filePathTextField.setColumns(5);
				existingDataListModel = new DefaultListModel(); 
				System.out.println("The number of work sheets are"+ plotDataWorkBook.getNumberOfSheets());
				for(int i=0;i<plotDataWorkBook.getNumberOfSheets();i++){
					existingDataListModel.add(existingDataListModel.getSize(), plotDataWorkBook.getSheet(i).getName());
				}
				
				System.out.println("The list model size is "+existingDataListModel.size());
				existingDataList = new JList(existingDataListModel);
				existingDataList.addMouseListener(this);
				//existingDataList.setVisibleRowCount(5);
				JScrollPane existingDataScrollPane = new JScrollPane(existingDataList);
				existingDataInternalFrame.getContentPane().add(existingDataScrollPane);
				existingDataInternalFrame.setBackground(Color.white);
				

				
			} else if((MainLayoutPage.isDirectEntryIntoPlotMaven == true)&&(browsePlotMavenButtonClickCount!=1)){
				
					existingDataListModel.removeAllElements();
				
				for(int i=0;i<plotDataWorkBook.getNumberOfSheets();i++){
					existingDataListModel.add(existingDataListModel.getSize(), plotDataWorkBook.getSheet(i).getName());
					
				}
				
			}
			
						
		
		*/}
	}





	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}





	

}
