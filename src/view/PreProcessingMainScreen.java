package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;

public class PreProcessingMainScreen extends JInternalFrame implements MouseMotionListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int LARGE = 1000000; 
	private static JTextPane wizardinfo;
	private static GridBagConstraints wizardInfoCon;
	private static JLabel singleFileUploadLable;
	private static JButton uploadButton;
	protected static JTextField uploadedFilePath;
	private static JLabel addAnotherFileLable;
	private static int fileCount; 
	private static int presentGridx;
	private static int presentGridy;
	private static JRadioButton multipleFilesUploadradio;
	private static JRadioButton singleFileUploadRadio;
	private static JPanel uploadPanel;
	private static GridBagConstraints chooseTabbedPaneCon;
	private static JTabbedPane chooseTabbedPane;
	private static Container c;
	private static PreProcessingMainScreen obj;
	private static JButton BrowseFile1Button;
	private static boolean multipleFiles; 
	private static JLabel file1Lable;
	protected static JTextField file1Path;
	private static boolean singleFileContext;
	private static boolean multipleFileContext;
	private static JLabel addMoreFilesLable;
	protected static JTextField file2Path;
	private static JLabel file2Lable;
	private static JButton browseFile2Button;
	private static JLabel file3Lable;
	protected static JTextField file3Path;
	private static JButton browseFile3Button;
	private static int addMoreFilesLableClickCount = 0;
	private static JLabel file4Lable;
	protected static JTextField file4Path;
	private static JButton browseFile4button;
	private static JLabel file5Lable;
	protected static JTextField file5Path;
	private static JButton browseFile5Button;
	private static JLabel removeFile1Lable;
	private static JLabel removeFile2Lable;
	private static JLabel removeFile3Lable;
	private static JLabel removeFile4Lable;
	private static JLabel removeFile5Lable;
	private static JButton next;
	private static JButton cancel;
	protected static JCheckBox firstColumnHeader;
	
	
	public PreProcessingMainScreen(){
		GridBagLayout gridBagLayout = new GridBagLayout();
		//obj = new PreProcessingMainScreen();
		c = getContentPane();
		c.setBackground(Color.white);
		c.setLayout(gridBagLayout);
		//MainLayoutPage objectLayoutPage = new MainLayoutPage();
		createBasic();
		createMultipleFilePanel();
		
		
		chooseTabbedPane.addTab("upload File",uploadPanel);
		c.add(chooseTabbedPane, chooseTabbedPaneCon);
		//obj.setSize(650, 600);
		add(wizardinfo, wizardInfoCon);
		//setVisible(true);
		
	}
	private  void createMoreFileimports() {
		// TODO Auto-generated method stub
		if(addMoreFilesLableClickCount <= 2){
		  if(addMoreFilesLableClickCount == 1){
			file2Lable = new JLabel("2. ");
			GridBagConstraints file2LableCon = new GridBagConstraints();
			file2LableCon.gridx = 0;
			file2LableCon.gridy = 8;
			file2LableCon.gridheight = 2;
			file2LableCon.weighty = 0.4;
			uploadPanel.add(file2Lable,file2LableCon);
			
			file2Path = new JTextField();
			GridBagConstraints file2PathCon = new GridBagConstraints();
			file2PathCon.gridx = 1;
			file2PathCon.gridy = 8;
			file2PathCon.gridwidth = 3;
			file2PathCon.gridheight = 2;
			file2PathCon.weightx = 0.2;
			file2PathCon.weighty = 0.4;
			file2PathCon.fill = GridBagConstraints.HORIZONTAL;
			uploadPanel.add(file2Path,file2PathCon);
			
			browseFile2Button =  new JButton("Browse");
			browseFile2Button.addMouseListener(this);
			GridBagConstraints browseFile2ButtonCon = new GridBagConstraints();
			browseFile2ButtonCon.gridx = 4;
			browseFile2ButtonCon.gridy = 8;
			browseFile2ButtonCon.anchor = GridBagConstraints.LINE_END;
			browseFile2ButtonCon.gridheight = 2;
			browseFile2ButtonCon.weighty = 0.4;
			uploadPanel.add(browseFile2Button,browseFile2ButtonCon);
		
			
			//remove lable for first file
			removeFile2Lable = new JLabel("Remove");
			removeFile2Lable.addMouseListener(this);
			removeFile2Lable.addMouseMotionListener(this);
			GridBagConstraints removeFile2LableCon = new GridBagConstraints();
			removeFile2LableCon.gridx = 5;
			removeFile2LableCon.gridy = 9;
			removeFile2LableCon.weightx = 0.0002;
			removeFile2LableCon.anchor = GridBagConstraints.LINE_END;
			removeFile2Lable.setVisible(false);
			uploadPanel.add(removeFile2Lable,removeFile2LableCon);
			
			
			
			file3Lable = new JLabel("3. ");
			GridBagConstraints file3LableCon = new GridBagConstraints();
			file3LableCon.gridx = 0;
			file3LableCon.gridy = 10;
			file3LableCon.weighty = 0.4;
			file3LableCon.gridheight = 2;
			uploadPanel.add(file3Lable,file3LableCon);
		
			file3Path = new JTextField();
			GridBagConstraints file3PathCon = new GridBagConstraints();
			file3PathCon.gridx = 1;
			file3PathCon.gridy = 10;
			file3PathCon.gridwidth = 3;
			file3PathCon.weightx = 0.2;
			file3PathCon.gridheight = 2;
			file3PathCon.weighty = 0.4;
			file3PathCon.fill = GridBagConstraints.HORIZONTAL;
			uploadPanel.add(file3Path,file3PathCon);
		
			browseFile3Button =  new JButton("Browse");
			browseFile3Button.addMouseListener(this);
			GridBagConstraints browseFile3ButtonCon = new GridBagConstraints();
			browseFile3ButtonCon.gridx = 4;
			browseFile3ButtonCon.gridy = 10;
			browseFile3ButtonCon.weighty = 0.4;
			browseFile3ButtonCon.gridheight = 2;
			browseFile3ButtonCon.anchor = GridBagConstraints.LINE_END;
			uploadPanel.add(browseFile3Button,browseFile3ButtonCon);
			
			
			//remove lable for first file
			removeFile3Lable = new JLabel("Remove");
			removeFile3Lable.addMouseListener(this);
			removeFile3Lable.addMouseMotionListener(this);
			GridBagConstraints removeFile3LableCon = new GridBagConstraints();
			removeFile3LableCon.gridx = 5;
			removeFile3LableCon.gridy = 11;
			removeFile3LableCon.weightx = 0.0002;
			removeFile3LableCon.anchor = GridBagConstraints.LINE_END;
			removeFile3Lable.setVisible(false);
			uploadPanel.add(removeFile3Lable,removeFile3LableCon);
			
			
			
		  }
		  
		  if(addMoreFilesLableClickCount == 2){
			  
			  file4Lable =  new JLabel("4. ");
			  GridBagConstraints file4LableCon = new GridBagConstraints();
			  file4LableCon.gridx = 0;
			  file4LableCon.gridy = 12;
			  file4LableCon.gridheight = 2;
			  file4LableCon.weighty = 0.4;
			  uploadPanel.add(file4Lable,file4LableCon);
			  
			  file4Path = new JTextField();
			  GridBagConstraints file4PathCon = new GridBagConstraints();
			  file4PathCon.gridx = 1;
			  file4PathCon.gridy = 12;
			  file4PathCon.gridheight = 2;
			  file4PathCon.gridwidth = 3;
			  file4PathCon.weightx = 0.2;
			  file4PathCon.weighty = 0.4;
			  file4PathCon.fill = GridBagConstraints.HORIZONTAL;
			  uploadPanel.add(file4Path,file4PathCon);
			  
			  browseFile4button = new JButton("Browse");
			  browseFile4button.addMouseListener(this);
			  GridBagConstraints browseFile4ButtonCon = new GridBagConstraints();
			  browseFile4ButtonCon.gridx = 4;
			  browseFile4ButtonCon.gridy = 12;
			  browseFile4ButtonCon.gridheight = 2;
			  browseFile4ButtonCon.weighty = 0.2;
			  browseFile4ButtonCon.anchor = GridBagConstraints.LINE_END;
			  uploadPanel.add(browseFile4button,browseFile4ButtonCon);
			  
			//remove lable for first file
				removeFile4Lable = new JLabel("Remove");
				removeFile4Lable.addMouseListener(this);
				removeFile4Lable.addMouseMotionListener(this);
				GridBagConstraints removeFile4LableCon = new GridBagConstraints();
				removeFile4LableCon.gridx = 5;
				removeFile4LableCon.gridy = 13;
				removeFile4LableCon.weightx = 0.0002;
				removeFile4LableCon.anchor = GridBagConstraints.LINE_END;
				removeFile4Lable.setVisible(false);
				uploadPanel.add(removeFile4Lable,removeFile4LableCon);
			  
			  file5Lable =  new JLabel("5. ");
			  GridBagConstraints file5LableCon = new GridBagConstraints();
			  file5LableCon.gridx = 0;
			  file5LableCon.gridy = 14;
			  file5LableCon.gridheight = 2;
			  file5LableCon.weighty = 0.4;
			  uploadPanel.add(file5Lable,file5LableCon);
			  
			  file5Path = new JTextField();
			  GridBagConstraints file5PathCon = new GridBagConstraints();
			  file5PathCon.gridx = 1;
			  file5PathCon.gridy = 14;
			  file5PathCon.gridheight = 2;
			  file5PathCon.gridwidth = 3;
			  file5PathCon.weightx = 0.2;
			  file5PathCon.weighty = 0.4;
			  file5PathCon.fill = GridBagConstraints.HORIZONTAL;
			  uploadPanel.add(file5Path,file5PathCon);
			  
			  browseFile5Button = new JButton("Browse");
			  browseFile5Button.addMouseListener(this);
			  GridBagConstraints browseFile5ButtonCon = new GridBagConstraints();
			  browseFile5ButtonCon.gridx = 4;
			  browseFile5ButtonCon.gridy = 14;
			  browseFile5ButtonCon.gridheight = 2;
			  browseFile5ButtonCon.weighty = 0.2;
			  browseFile5ButtonCon.anchor = GridBagConstraints.LINE_END;
			  uploadPanel.add(browseFile5Button,browseFile5ButtonCon);
			  
			  
			//remove lable for first file
				removeFile5Lable = new JLabel("Remove");
				removeFile5Lable.addMouseListener(this);
				removeFile5Lable.addMouseMotionListener(this);
				GridBagConstraints removeFile5LableCon = new GridBagConstraints();
				removeFile5LableCon.gridx = 5;
				removeFile5LableCon.gridy = 15;
				removeFile5LableCon.weightx = 0.0002;
				removeFile5LableCon.anchor = GridBagConstraints.LINE_END;
				removeFile5Lable.setVisible(false);
				uploadPanel.add(removeFile5Lable,removeFile5LableCon);
			  
			  addMoreFilesLable.setEnabled(false);
			  addMoreFilesLable.disable();
			  
			  addMoreFilesLableClickCount = LARGE;
			  
		  }
		}
		
		chooseTabbedPane.addTab("upload File",uploadPanel);
		c.add(chooseTabbedPane, chooseTabbedPaneCon);
		
	}
	private void createBasic() {
		

		// TODO Auto-generated method stub
		wizardinfo = new JTextPane();
		wizardinfo.setText("\n\tWelcome to the Data Preprocessing wizard. This wizard will provide options " +
				"to process your data and make it ready for further analysis. Please upload a file(s) " +
				"and in the next step you can choose a model for importing data. The add link provides " +
				"an option for adding a file and remove link removes the chosen file from the list of selected" +
				"files. " +
				"Please note only the files corresponding to non empty path fields will be considered" +
				"for processing.\n ");
		wizardInfoCon = new GridBagConstraints();
		wizardinfo.disable();
		wizardinfo.setForeground(new Color(0,0,0));
		wizardInfoCon.gridx = 0;
		wizardInfoCon.gridy = 0;
		//wizardInfoCon.gridwidth = 2;
		wizardInfoCon.anchor = GridBagConstraints.PAGE_START;
		wizardInfoCon.fill = GridBagConstraints.HORIZONTAL;
		wizardInfoCon.weighty = 0.005;
		wizardInfoCon.weightx = 0.5;
		Border b = BorderFactory.createLineBorder(Color.BLACK);
		wizardinfo.setBorder(b);
		
		
		//tabbed pane
		chooseTabbedPane = new JTabbedPane();
		chooseTabbedPane.setBackground(Color.white);
		chooseTabbedPaneCon = new GridBagConstraints();
		chooseTabbedPaneCon.gridx = 0;
		chooseTabbedPaneCon.gridy = 2;
		chooseTabbedPaneCon.fill = GridBagConstraints.BOTH; 
		uploadPanel = new JPanel();
		GridBagLayout uploadTablayout = new GridBagLayout();
		uploadPanel.setLayout(uploadTablayout);
		chooseTabbedPaneCon.anchor = GridBagConstraints.PAGE_START;
		chooseTabbedPaneCon.weighty=0.10;
		
		
		ButtonGroup radios = new ButtonGroup();
		
		
		singleFileUploadRadio = new JRadioButton("Single File Upload");
		singleFileUploadRadio.addMouseListener(this);
		singleFileUploadRadio.doClick();
		singleFileContext = true;
		GridBagConstraints singleFileUploadRadioCon = new GridBagConstraints();
		singleFileUploadRadioCon.gridx = 0;
		singleFileUploadRadioCon.gridy = 0;
		singleFileUploadRadioCon.gridheight =2;
		singleFileUploadRadioCon.anchor = GridBagConstraints.LINE_START;
		radios.add(singleFileUploadRadio);
		uploadPanel.add(singleFileUploadRadio,singleFileUploadRadioCon);
		
		singleFileUploadLable = new JLabel("File path");
		GridBagConstraints singleFileUploadLableCon = new GridBagConstraints();
		singleFileUploadLableCon.gridx=0;
		singleFileUploadLableCon.gridy=2;
		uploadPanel.add(singleFileUploadLable,singleFileUploadLableCon);
		
		uploadedFilePath = new JTextField();
		uploadedFilePath.disable();
		GridBagConstraints uploadFilePathCon = new GridBagConstraints();
		uploadFilePathCon.gridx = 1;
		uploadFilePathCon.gridy = 2;
		uploadFilePathCon.gridwidth = 3;
		uploadFilePathCon.weightx = 0.01;
		uploadFilePathCon.anchor = GridBagConstraints.LINE_START;
		uploadFilePathCon.fill = GridBagConstraints.HORIZONTAL;
		uploadPanel.add(uploadedFilePath,uploadFilePathCon);
		
		
		
		//tabbed pane----upload tab------upload button
		uploadButton = new JButton("Browse");
		uploadButton.addMouseListener(this);
		GridBagConstraints uploadButtonCon = new GridBagConstraints();
		uploadButtonCon.gridx = 4;
		uploadButtonCon.gridy = 2;
		uploadButtonCon.anchor = GridBagConstraints.LINE_END;
		uploadPanel.add(uploadButton,uploadButtonCon);
		
		
	
		JSeparator s = new JSeparator(SwingConstants.HORIZONTAL);
		GridBagConstraints sCon = new GridBagConstraints();
		sCon.gridy = 3;
		uploadPanel.add(s,sCon);
		
		
		multipleFilesUploadradio =  new JRadioButton("Upload multiple files");
		multipleFilesUploadradio.addMouseListener(this);
		GridBagConstraints multipleFileUploadRadioCon = new GridBagConstraints();
		multipleFileUploadRadioCon.gridx = 0;
		multipleFileUploadRadioCon.gridy = 4;
		multipleFileUploadRadioCon.gridheight = 2;
		multipleFileUploadRadioCon.weighty = 0.8;
		multipleFileUploadRadioCon.anchor = GridBagConstraints.LINE_START;
		radios.add(multipleFilesUploadradio);
		
		//check box
		firstColumnHeader = new JCheckBox("Take first column as header");
		GridBagConstraints firstColumnHeaderCon = new GridBagConstraints();
		firstColumnHeaderCon.gridx = 1;
		firstColumnHeaderCon.gridy = 18;
		//firstColumnHeaderCon.weightx = 0.001;
		firstColumnHeaderCon.anchor = GridBagConstraints.LINE_START;
		uploadPanel.add(firstColumnHeader,firstColumnHeaderCon);
		
		//next button
		next = new JButton("Next");
		next.addMouseListener(this);
		GridBagConstraints nextCon = new GridBagConstraints();
		nextCon.gridx = 3;
		nextCon.gridy = 18;
		nextCon.weightx = 0.001;
		//nextCon.anchor = GridBagConstraints.LINE_END;
		uploadPanel.add(next, nextCon);
		
		//cancel button
		cancel = new JButton("Cancel");
		cancel.addMouseListener(this);
		GridBagConstraints cancelCon = new GridBagConstraints();
		cancelCon.gridx = 4;
		cancelCon.gridy = 18;
		cancelCon.weightx = 0.001;
		cancelCon.anchor = GridBagConstraints.LINE_START;
		uploadPanel.add(cancel,cancelCon);
		
		multipleFilesUploadradio.addMouseListener(obj);
		uploadPanel.add(multipleFilesUploadradio,multipleFileUploadRadioCon);
		
	}
	private void createMultipleFilePanel() {
		// TODO Auto-generated method stub
		
		//create first file lable
		file1Lable =  new JLabel("1. ");
		GridBagConstraints file1LableCon = new GridBagConstraints();
		file1LableCon.gridx = 0;
		file1LableCon.gridy = 6;
		file1LableCon.gridheight = 2;
		//file1LableCon.weightx = 0.8;
		file1Lable.setVisible(false);
		//file1LableCon.anchor =  GridBagConstraints.LINE_START;
		uploadPanel.add(file1Lable,file1LableCon);
		
		
		//create first file path text box
		file1Path = new JTextField();
		GridBagConstraints file1PathCon = new GridBagConstraints();
		file1PathCon.gridx = 1;
		file1PathCon.gridy = 6;
		file1PathCon.gridwidth = 3;
		file1PathCon.gridheight = 2;
		file1PathCon.weightx = 0.01;
		file1PathCon.anchor = GridBagConstraints.LINE_START;
		file1PathCon.fill = GridBagConstraints.HORIZONTAL;
		file1Path.setVisible(false);
		
		uploadPanel.add(file1Path,file1PathCon);
		
		//create first file browse button
		BrowseFile1Button = new JButton("Browse");
		BrowseFile1Button.addMouseListener(this);
		GridBagConstraints BrowseFile1ButtonCon = new GridBagConstraints();
		BrowseFile1ButtonCon.gridx = 4;
		BrowseFile1ButtonCon.gridy = 6;
		BrowseFile1ButtonCon.gridheight = 2;
		BrowseFile1ButtonCon.anchor = GridBagConstraints.LINE_END;
		BrowseFile1Button.setVisible(false);
		uploadPanel.add(BrowseFile1Button,BrowseFile1ButtonCon);
		
		//remove lable for first file
		removeFile1Lable = new JLabel("Remove");
		removeFile1Lable.addMouseListener(this);
		removeFile1Lable.addMouseMotionListener(this);
		GridBagConstraints removeFile1LableCon = new GridBagConstraints();
		removeFile1LableCon.gridx = 5;
		removeFile1LableCon.gridy = 7;
		removeFile1LableCon.weightx = 0.0002;
		removeFile1LableCon.anchor = GridBagConstraints.LINE_END;
		removeFile1Lable.setVisible(false);
		uploadPanel.add(removeFile1Lable,removeFile1LableCon);
	
		//create add more files lable at the end of the page
		addMoreFilesLable = new JLabel("Add More Files");
		addMoreFilesLable.addMouseMotionListener(this);
		addMoreFilesLable.addMouseListener(this);
		GridBagConstraints addMoreFilesLableCon = new GridBagConstraints();
		addMoreFilesLableCon.gridx = 4;
		addMoreFilesLableCon.gridy = 16;
		addMoreFilesLableCon.weighty = 0.2;
		addMoreFilesLableCon.anchor = GridBagConstraints.LINE_END;
		addMoreFilesLable.setVisible(false);
		uploadPanel.add(addMoreFilesLable,addMoreFilesLableCon);
		
	}
		JFileChooser importFile = new JFileChooser();
		private String importFile(){
		importFile.setCurrentDirectory(new File("."));
		importFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter textFileFilter = new ExtensionFileFilter("Text Files", new String[] { "txt" });
		importFile.setFileFilter(textFileFilter);
		FileFilter  cSVFileFilter= new ExtensionFileFilter("CSV Files", new String[] { "csv"});
		importFile.setFileFilter(cSVFileFilter);
		FileFilter  docFileFilter= new ExtensionFileFilter("Doc Files", new String[] { "doc" });
		importFile.setFileFilter(docFileFilter);
		FileFilter  odtFileFilter= new ExtensionFileFilter("odt Files", new String[] { "odt" });
		importFile.setFileFilter(odtFileFilter);
		FileFilter  excelFileFilter= new ExtensionFileFilter("Excel Files", new String[] { "xls" });
		importFile.setFileFilter(excelFileFilter);
		importFile.showOpenDialog(null);
		File selectedPfile = importFile.getSelectedFile();
		String path = selectedPfile.getPath();
		return path;
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		try{
			System.out.println("Inside the upload button click event 1");
			if((arg0.getComponent() == uploadButton)&&(singleFileContext == true)){
				String filepath = importFile();
				System.out.println("Inside the upload button click event");
				uploadedFilePath.setText(filepath);
				uploadedFilePath.setEditable(true);
				
				
				fileCount++;
				}
			
			if(arg0.getComponent() == multipleFilesUploadradio){
				
				singleFileContext = false;
				multipleFileContext = true;
				//disabling single file components
				uploadButton.setEnabled(false);
				
				uploadedFilePath.setBackground(new Color(238,238,238));
				uploadedFilePath.setEnabled(false);
				singleFileUploadLable.setEnabled(false);
				
				
				BrowseFile1Button.setVisible(true);
				file1Lable.setVisible(true);
				file1Path.setVisible(true);
				removeFile1Lable.setVisible(true);
				removeFile1Lable.setEnabled(true);
				BrowseFile1Button.setEnabled(true);
				file1Lable.setEnabled(true);
				file1Path.setEnabled(true);
				file1Path.setBackground(Color.white);
				
				
				
				addMoreFilesLable.setVisible(true);
				addMoreFilesLable.setEnabled(true);

			}
				
		if(arg0.getComponent() == singleFileUploadRadio){
			
			addMoreFilesLableClickCount = 0;
			singleFileContext = true;
			multipleFileContext = false;
			uploadButton.setEnabled(true);
			uploadedFilePath.setEnabled(true);
			uploadedFilePath.setBackground(Color.white);
			
			
			browseFile2Button.setEnabled(false);
			browseFile2Button.setVisible(false);
			file2Lable.setVisible(false);
			file2Path.setVisible(false);
			removeFile2Lable.setEnabled(false);
			removeFile2Lable.setVisible(false);
			
			browseFile3Button.setVisible(false);
			browseFile3Button.setEnabled(false);
			file3Path.setVisible(false);
			file3Path.setEnabled(false);
			file3Lable.setVisible(false);
			removeFile3Lable.setEnabled(false);
			removeFile3Lable.setVisible(false);
			
			file1Lable.setEnabled(false);
			file1Path.setBackground(new Color(238,238,238));
			file1Path.setEnabled(false);
			file1Lable.setVisible(false);
			file1Path.setVisible(false);
			BrowseFile1Button.setVisible(false);
			removeFile1Lable.setVisible(false);
			removeFile1Lable.setEnabled(false);
			
			file4Lable.setVisible(false);
			file4Path.setVisible(false);
			file4Path.setEnabled(false);
			browseFile4button.setVisible(false);
			browseFile4button.setEnabled(false);
			removeFile4Lable.setVisible(false);
			removeFile4Lable.setEnabled(false);
			
			file5Lable.setVisible(false);
			file5Path.setEnabled(false);
			file5Path.setVisible(false);
			browseFile5Button.setEnabled(false);
			browseFile5Button.setVisible(false);
			removeFile5Lable.setVisible(false);
			removeFile5Lable.setEnabled(false);
			
			addMoreFilesLable.setVisible(false);
			addMoreFilesLable.setEnabled(false);
		}
		
		if((arg0.getComponent() == BrowseFile1Button)&&(multipleFileContext == true)){
			
			String path = importFile();
			file1Path.setText(path);
			
		}
		if((arg0.getComponent() == browseFile2Button)&&(multipleFileContext == true)){
			String path = importFile();
			file2Path.setText(path);
		}
		if((arg0.getComponent() == browseFile3Button)&&(multipleFileContext == true)){
			String path = importFile();
			file3Path.setText(path);
		}
		if((arg0.getComponent() == browseFile4button)&&(multipleFileContext == true)){
			String path = importFile();
			file4Path.setText(path);
		}
		if((arg0.getComponent() == browseFile5Button)&&(multipleFileContext ==true)){
			String path = importFile();
			file5Path.setText(path);
		}
		
		if((arg0.getComponent() == addMoreFilesLable)&&(multipleFileContext == true)){
			
			if(addMoreFilesLableClickCount <= 2){
				addMoreFilesLableClickCount++;
				System.out.println("Ajith here");
				createMoreFileimports();
				browseFile2Button.setVisible(true);
				file2Lable.setVisible(true);
				file2Path.setVisible(true);
				removeFile2Lable.setVisible(true);
				removeFile2Lable.setEnabled(true);
				browseFile2Button.setEnabled(true);
				file2Lable.setEnabled(true);
				file2Path.setEnabled(true);
				file2Path.setBackground(Color.white);
				
				browseFile3Button.setVisible(true);
				file3Lable.setVisible(true);
				file3Path.setVisible(true);
				removeFile3Lable.setVisible(true);
				removeFile3Lable.setEnabled(true);
				browseFile3Button.setEnabled(true);
				file3Lable.setEnabled(true);
				file3Path.setEnabled(true);
				file3Path.setBackground(Color.white);
				
			  
			  if(addMoreFilesLableClickCount == LARGE){
				browseFile4button.setVisible(true);
				file4Lable.setVisible(true);
				file4Path.setVisible(true);
				removeFile4Lable.setVisible(true);
				removeFile4Lable.setEnabled(true);
				browseFile4button.setEnabled(true);
				file4Lable.setEnabled(true);
				file4Path.setEnabled(true);
				file4Path.setBackground(Color.white);
				
				
				browseFile5Button.setVisible(true);
				file5Lable.setVisible(true);
				file5Path.setVisible(true);
				removeFile5Lable.setVisible(true);
				removeFile5Lable.setEnabled(true);
				browseFile5Button.setEnabled(true);
				file5Lable.setEnabled(true);
				file5Path.setEnabled(true);
				file5Path.setBackground(Color.white);
			  }
			}
			if(arg0.getComponent() == cancel){
				
			}
		}
		if(arg0.getComponent() == removeFile1Lable){
			file1Path.setText(null);
		}
		if(arg0.getComponent() == removeFile2Lable){
			file2Path.setText(null);
		}
		if(arg0.getComponent() == removeFile3Lable){
			file3Path.setText(null);
		}
		if(arg0.getComponent() == removeFile4Lable){
			file4Path.setText(null);
		}
		if(arg0.getComponent() == removeFile5Lable){
			file5Path.setText(null);
		}
		if(arg0.getComponent() == next){/*
			System.out.println("Inside the next button");
			PreProcessingSecondScreen secondScreenFrame = new PreProcessingSecondScreen();
			//MainLayoutPage.removeTitleBarForinternalFrame(secondScreenFrame);
			 secondScreenFrame.setLocation(0,-20);
			    
		      secondScreenFrame.setSize(getWidth(),getHeight());
		     secondScreenFrame.setBackground(Color.white);
		      secondScreenFrame.setBorder(MainLayoutPage.b);
		      MainLayoutPage.ppmainDisplayDesktopPane.add(secondScreenFrame);
		      secondScreenFrame.moveToFront();
		      secondScreenFrame.setVisible(true);
		*/}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("The file is not found please check again");
		}
		
			
		
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
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getComponent() == addMoreFilesLable){
			Cursor c = new Cursor(JFrame.HAND_CURSOR);
			addMoreFilesLable.setCursor(c);
		}
		
		if(arg0.getComponent() == removeFile1Lable){
			Cursor c = new Cursor(JFrame.HAND_CURSOR);
			removeFile1Lable.setCursor(c);
		}
		if(arg0.getComponent() == removeFile2Lable){
			Cursor c = new Cursor(JFrame.HAND_CURSOR);
			removeFile2Lable.setCursor(c);
		}
		if(arg0.getComponent() == removeFile3Lable){
			Cursor c = new Cursor(JFrame.HAND_CURSOR);
			removeFile3Lable.setCursor(c);
		}
		if(arg0.getComponent() == removeFile4Lable){
			Cursor c = new Cursor(JFrame.HAND_CURSOR);
			removeFile4Lable.setCursor(c);
		}
		if(arg0.getComponent() == removeFile5Lable){
			Cursor c = new Cursor(JFrame.HAND_CURSOR);
			removeFile5Lable.setCursor(c);
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
	        if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
	          return true;
	        }
	      }
	    }
	    return false;
	  }
	}



