package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class FontStylePanel extends JPanel {
	
	JLabel lblFontSelected;

	/**
	 * Create the panel.
	 */
	public FontStylePanel() {
		setLayout(null);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrDim = toolkit.getScreenSize();
		setSize(522,327);
		
		JButton btnResetToDef = new JButton("Reset All to Default");
		btnResetToDef.setHorizontalAlignment(SwingConstants.LEFT);
		btnResetToDef.setBounds(20, 213, 146, 23);
		add(btnResetToDef);
		
		btnResetToDef.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				 

			}
		});
		
		JButton btnSelectFont = new JButton("Select Font...");
		btnSelectFont.setBounds(190, 40, 136, 23);
		add(btnSelectFont);
		
		btnSelectFont.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("btnSelectFont component clicked.");
				fontButtonClickHandler();
			}
		});
		
		final JComboBox cbColor = new JComboBox();
		cbColor.setModel(new DefaultComboBoxModel(new String[] {"Black", "Blue", "Grey", "Red", "Green"}));
		cbColor.setBounds(382, 40, 96, 20);
		
		cbColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				
				
				ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
				
				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
				.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getTmInfo().setFontColor(cbColor.getName());

			}
		});
		add(cbColor);
		
		JLabel lblNewLabel = new JLabel("Alignment");
		lblNewLabel.setBounds(203, 217, 123, 19);
		add(lblNewLabel);
		
		final JComboBox cbAlign = new JComboBox();
		cbAlign.setModel(new DefaultComboBoxModel(new String[] {"Center", "Left", "Right"}));
		cbAlign.setBounds(382, 213, 96, 20);
		add(cbAlign);
		
		cbAlign.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				
				ApplicationInfo appInfo = DisplayContents
						.createAppInfoInstance();

				appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
						.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getTmInfo()
						.setTextAlignment(cbAlign.getName());

			}
		});
		
		JLabel lblFontChoice = new JLabel("");
		lblFontChoice.setBounds(259, 122, 113, 23);
		add(lblFontChoice);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 45, 146, 146);
		add(scrollPane);
		
		JList list_1 = new JList();
		scrollPane.setViewportView(list_1);
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Captions", "Column Titles", "Table Body", "Statistics", "Footers"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		lblFontSelected = new JLabel("");
		lblFontSelected.setBounds(176, 95, 336, 50);
		add(lblFontSelected);
		
	}
	
	public void fontButtonClickHandler()
	{
		// TODO Auto-generated method stub
		System.out.println("btnResetToDef component clicked.");
		
		 JFontChooser fontChooser = new JFontChooser();
		 int result = fontChooser.showDialog(this);
		 if (result == JFontChooser.OK_OPTION)
		 {
		 	Font font = fontChooser.getSelectedFont(); 
		 	ApplicationInfo appInfo = DisplayContents.createAppInfoInstance();
			
		 	appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getWorkSheetObjectsAL()
					.get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getWorkBooksArrayList().get(appInfo.getProjectInfoAL().get(appInfo.getSelectedProjectIndex()).getSelectedWorkBookIndex()).getSelectedSheetIndex()).getTmInfo().setSysLevelFont(font);
			
		 }
		
	}
}
