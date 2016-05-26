package Common;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;




public final class ImageLoader {

	
	//singleton design pattern for the image loader object
	public static ImageLoader IMGLoaderObj = null;
	public static ImageLoader createImageLoaderInstance(){
		if(IMGLoaderObj == null){
			IMGLoaderObj = new ImageLoader();
		}
		return IMGLoaderObj;
	}
	
	  private ImageLoader() {
	  }

	  @SuppressWarnings("unchecked")
	public Image getImage(Class relativeClass, String filename) {
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
