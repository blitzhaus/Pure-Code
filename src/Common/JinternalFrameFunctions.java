package Common;

import java.awt.Component;
import java.awt.event.MouseMotionListener;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class JinternalFrameFunctions {
	
	
	public static JinternalFrameFunctions JIF_OBJ = null;
	
	//singleton design pattern implementation of create internal frame functions  
	public static JinternalFrameFunctions createIFFunctionsInstance(){
		if(JIF_OBJ == null){
			JIF_OBJ = new JinternalFrameFunctions();
		}
		
		return JIF_OBJ;
	}

	public void fix(JDesktopPane desktop) {
		try
		    {
		    JInternalFrame[] frames = desktop.getAllFrames();
		    frames[0].setSelected(true);
		    }
		    catch (java.beans.PropertyVetoException e) {
		    	//e.printStackTrace();
		    }
		    JInternalFrame[] frames = desktop.getAllFrames();
		    for(int frameNo =0;frameNo<frames.length;frameNo++){
		    	JInternalFrame f = frames[frameNo];
		    	BasicInternalFrameUI ui = (BasicInternalFrameUI)f.getUI();
		    	Component north = ui.getNorthPane();
		    	MouseMotionListener[] actions =
		    		(MouseMotionListener[])north.getListeners(MouseMotionListener.class);
		    	for (int i = 0; i < actions.length; i++)
		    		north.removeMouseMotionListener( actions[i] );
		    }
		   }
	

	public void removeTitleBarForinternalFrame(JInternalFrame j){
		javax.swing.plaf.InternalFrameUI ifu= j.getUI();
		  ((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);
	}
	
}
