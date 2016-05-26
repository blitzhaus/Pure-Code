package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import Common.ImageLoader;
import Common.JinternalFrameFunctions;

public class PMOptions {

	public static PMOptions PM_OPT = null;
	public static PMOptions createPMOptionsInst() throws RowsExceededException, WriteException, BiffException, IOException{
		if(PM_OPT == null){
			PM_OPT = new PMOptions("just object creation");
			
		}
		return PM_OPT;
	}
	
	JInternalFrame plotMavenOptionsinernalFrame;
    JRadioButton xyScatterRadioButton;
    JPanel scatterJPanel;
	private Font componentOptionseFont = new Font("Arial", Font.BOLD, 11);
	private Font componentLablesFont = new Font("Arial", Font.BOLD, 11);
    private TitledBorder titledBorder;
    
	public PMOptions(String dummy) throws RowsExceededException, WriteException, BiffException, IOException{
		
	}
    
	 void createPlotMavenoptionsFrame() throws RowsExceededException, WriteException, BiffException, IOException {
		plotMavenOptionsinernalFrame = new JInternalFrame("options",false,false,false,false);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(plotMavenOptionsinernalFrame);
		plotMavenOptionsinernalFrame.setLocation(NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame.getX(),
				NcaSetupAvailableComp.createNcaSetupAvailCompInst().setupAvailableComponentsInternalFrame.getY()+
				(PlotMavenCreateUI.createPlotMavenUIInst().PlotMavenMainInternalFrame.getHeight()/2));
		plotMavenOptionsinernalFrame.setSize(PlotMavenCreateUI.createPlotMavenUIInst().PlotMavenMainInternalFrame.getWidth(),
				PlotMavenCreateUI.createPlotMavenUIInst().PlotMavenMainInternalFrame.getHeight()/2);
		plotMavenOptionsinernalFrame.setVisible(true);
		plotMavenOptionsinernalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		plotMavenOptionsinernalFrame.setBackground(Color.white);
		PlotMavenCreateUI.createPlotMavenUIInst().plotMavenSetupDesktopPane.add(plotMavenOptionsinernalFrame);
		plotMavenOptionsinernalFrame.moveToFront();
		JDesktopPane plotMavenOptionDesktopPane = new JDesktopPane();
		plotMavenOptionsinernalFrame.setContentPane(plotMavenOptionDesktopPane);
		
		
		
		JInternalFrame imagesInternalFrame = new JInternalFrame();
		imagesInternalFrame.setLocation(0,0);
		imagesInternalFrame.setBorder(DDViewLayer.createViewLayerInstance().b);
		imagesInternalFrame.setSize(plotMavenOptionsinernalFrame.getWidth(),plotMavenOptionsinernalFrame.getHeight());
		imagesInternalFrame.setVisible(true);
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(imagesInternalFrame);
		imagesInternalFrame.getContentPane().setLayout(new FlowLayout());
		plotMavenOptionDesktopPane.add(imagesInternalFrame);
		imagesInternalFrame.moveToFront();
		
		
		titledBorder = BorderFactory.createTitledBorder(null, "XYScatter ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, componentLablesFont, Color.black);
		scatterJPanel = new JPanel();
		scatterJPanel.setBorder(titledBorder);
		
		Image scatterImage = ImageLoader.createImageLoaderInstance().getImage(DDViewLayer.class
				, "scatter5.jpg");
		Icon scatterIcon = new ImageIcon(scatterImage);
		JLabel scatterLable = new JLabel(scatterIcon);
		scatterJPanel.add(scatterLable);
		imagesInternalFrame.getContentPane().add(scatterJPanel);
		
		
		
		
		titledBorder = BorderFactory.createTitledBorder(null, "Histogram", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, componentLablesFont, Color.black);
		JPanel histogramPanel = new JPanel();
		histogramPanel.setEnabled(false);
		histogramPanel.setBorder(titledBorder);
		
		Image histogramImage = ImageLoader.createImageLoaderInstance().getImage(DDViewLayer.class, "histo1.jpg");
		Icon histogramIcon = new ImageIcon(histogramImage);
		JLabel histogramLable = new JLabel(histogramIcon);
		histogramPanel.add(histogramLable);
		
		imagesInternalFrame.getContentPane().add(histogramPanel);
		
		
		titledBorder = BorderFactory.createTitledBorder(null, "Bar Graph", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, componentLablesFont, Color.black);
		JPanel barGraphJPanel = new JPanel();
		barGraphJPanel.setEnabled(false);
		barGraphJPanel.setBorder(titledBorder);
		
		Image barImage = ImageLoader.createImageLoaderInstance().getImage(DDViewLayer.class, "bargraph2.jpg");
		Icon barIcon = new ImageIcon(barImage);
		JLabel barLable = new JLabel(barIcon);
		barGraphJPanel.add(barLable);
		imagesInternalFrame.getContentPane().add(barGraphJPanel);
	}
}
