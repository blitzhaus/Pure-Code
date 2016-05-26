package view;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import Common.JinternalFrameFunctions;


public class Graphics extends JInternalFrame{

	public static 
	
	final long serialVersionUID = 1L;

	public Graphics(){
		
		setTitle("Graphics");
		JinternalFrameFunctions.createIFFunctionsInstance().removeTitleBarForinternalFrame(this);
		setVisible(true);
		moveToFront();
		setLocation(0, 0);
		getContentPane().setBackground(Color.black);
		getContentPane().setLayout(new GridBagLayout());

/*		
		Image rightLower = ImageLoader.getImage(Graphics.class, "popPKPD.JPG");
		Icon rightLowerIcon = new ImageIcon(rightLower);
		JLabel rightLowerLable = new JLabel(rightLowerIcon);
		GridBagConstraints rightLowerlableCon = new GridBagConstraints();
		rightLowerlableCon.gridx = 3;
		rightLowerlableCon.gridy = 3;
		rightLowerlableCon.weightx = 1;
		rightLowerlableCon.weighty = 1;
		rightLowerlableCon.fill = GridBagConstraints.BOTH;
		getContentPane().add(rightLowerLable,rightLowerlableCon);
		getContentPane().setBackground(Color.white);
		
		

		Image middleLower = ImageLoader.getImage(Graphics.class, "ADMET2.JPG");
		Icon middleLowerIcon = new ImageIcon(middleLower);
		JLabel middleLowerLable = new JLabel(middleLowerIcon);
		GridBagConstraints middleLowerlableCon = new GridBagConstraints();
		middleLowerlableCon.gridx = 2;
		middleLowerlableCon.gridy = 4;
		middleLowerlableCon.weightx = 1;
		middleLowerlableCon.weighty = 1;
		middleLowerlableCon.fill = GridBagConstraints.BOTH;
		getContentPane().add(middleLowerLable,middleLowerlableCon);
		getContentPane().setBackground(Color.white);
		
		Image leftLower = ImageLoader.getImage(Graphics.class, "NLME.jpg");
		Icon leftLowerIcon = new ImageIcon(leftLower);
		JLabel leftLowerLable = new JLabel(leftLowerIcon);
		GridBagConstraints leftLowerlableCon = new GridBagConstraints();
		leftLowerlableCon.gridx = 1;
		leftLowerlableCon.gridy = 3;
		leftLowerlableCon.weightx = 1;
		leftLowerlableCon.weighty = 1;
		leftLowerlableCon.fill = GridBagConstraints.BOTH;
		getContentPane().add(leftLowerLable,leftLowerlableCon);
		getContentPane().setBackground(Color.white);
		
		
		Image medium1Image = ImageLoader.getImage(Graphics.class, "nca.jpg");
		Icon medium1Icon = new ImageIcon(medium1Image);
		JLabel medium1Lable = new JLabel(medium1Icon);
		GridBagConstraints medium1lableCon = new GridBagConstraints();
		medium1lableCon.gridx = 1;
		medium1lableCon.gridy = 1;
		medium1lableCon.weightx = 1;
		medium1lableCon.weighty = 1;
		medium1lableCon.fill = GridBagConstraints.BOTH;
		getContentPane().add(medium1Lable,medium1lableCon);
		
		Image mediumImage = ImageLoader.getImage(Graphics.class, "pk.jpg");
		Icon mediumIcon = new ImageIcon(mediumImage);
		JLabel mediumLable = new JLabel(mediumIcon);
		GridBagConstraints mediumlableCon = new GridBagConstraints();
		mediumlableCon.gridx = 2;
		mediumlableCon.gridy = 0;
		mediumlableCon.weightx = 1;
		mediumlableCon.weighty = 1;
		mediumlableCon.fill = GridBagConstraints.BOTH;
		getContentPane().add(mediumLable,mediumlableCon);
		
		
		*/
		

	}
	
	public static void main(String args[]){
		
		Graphics g = new Graphics();
	}
	
}

final class ImageLoader {

	  private ImageLoader() {
	  }

	  @SuppressWarnings("unchecked")
	public static Image getImage(Class relativeClass, String filename) {
	    Image returnValue = null;
	    java.io.InputStream is =  relativeClass.getResourceAsStream(filename);
	    if (is != null) {
	      BufferedInputStream bis = new BufferedInputStream(is);
	      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	      try {
	        int ch;
	        while ((ch = bis.read()) != -1) {
	          baos.write(ch);
	        }
	        returnValue = Toolkit.getDefaultToolkit().createImage(
	            baos.toByteArray());
	      } catch (IOException exception) {
	        System.err.println("Error loading: " + filename);
	      }
	    }
	    return returnValue;
	  }
	}