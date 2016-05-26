package Model;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import org.jfree.chart.ChartPanel;
public class ExportImage {
	
	
	public void imageExporting(ArrayList<JInternalFrame> imageArrayList, int selectedImage){
		
		

		System.out.println("Trying to export image");
		ChartPanel c;
		
			 c = (ChartPanel) imageArrayList.get(selectedImage).getContentPane();
		
		
		try {
			c.doSaveAs();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Saving the image");
		}
		
	
		
	}

}
