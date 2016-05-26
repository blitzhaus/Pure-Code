package view;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Hashtable;

public final class JarResources
{

public boolean debugOn=false;
private Hashtable htSizes=new Hashtable();
private Hashtable htJarContents=new Hashtable();
private String jarFileName;


public JarResources(String jarFileName)
{
	 this.jarFileName=jarFileName;
	 init();
}

private void init()
{
	 try
	 {
		ZipFile zf=new ZipFile(jarFileName);
		Enumeration e=zf.entries();
		while (e.hasMoreElements())
		{
			 ZipEntry ze=(ZipEntry)e.nextElement();
			 htSizes.put(ze.getName(),new Integer((int)ze.getSize()));
		}

		zf.close();

		FileInputStream fis=new FileInputStream(jarFileName);
		BufferedInputStream bis=new BufferedInputStream(fis);
		ZipInputStream zis=new ZipInputStream(bis);
		ZipEntry ze=null;
		while ((ze=zis.getNextEntry())!=null)
		{
		   if (ze.isDirectory())
		   {
			  continue;
		   }
		   if (debugOn)
		   {
			  System.out.println("ze.getName()="+ze.getName()+","+"getSize()="+ze.getSize());
		   }
		   int size=(int)ze.getSize();
		   // -1 means unknown size.
		   if (size==-1)
		   {
			  size=((Integer)htSizes.get(ze.getName())).intValue();
		   }
		   byte[] b=new byte[(int)size];
		   int rb=0;
		   int chunk=0;
		   while (((int)size - rb) > 0)
		   {
			   chunk=zis.read(b,rb,(int)size - rb);
			   if (chunk==-1) {
				  break;
			   }
			   rb+=chunk;
		   }
		   // add to internal resource hashtable
		   htJarContents.put(ze.getName(),b);
		}
	}
	catch (NullPointerException e)
	{
		System.out.println("done.");
	}
	catch (FileNotFoundException e)
	{
		e.printStackTrace();
	}
	catch (IOException e)
	{
		e.printStackTrace();
	}
}

public Object getResource(String entryName)
{
	byte[] b = (byte[]) htJarContents.get(entryName);
	Object obj = toObject(b);
	return obj;
}

private Object toObject(byte[] bytes)
{
	Object object = null;
	try
	{
		object = new java.io.ObjectInputStream(new java.io.ByteArrayInputStream(bytes)).readObject();
	}
	catch(java.io.IOException ioe)
	{
		java.util.logging.Logger.global.log(java.util.logging.Level.SEVERE, ioe.getMessage());
	}
	catch(java.lang.ClassNotFoundException cnfe)
	{
		java.util.logging.Logger.global.log(java.util.logging.Level.SEVERE, cnfe.getMessage());
	}
	return object;
}

public static void main(String[] args) throws IOException
{
	//JarResources jar = new JarResources ("Images.jar");
	//Image logo = Toolkit.getDefaultToolkit().createImage (jar.getResource ("logo.gif");

	if (args.length!=2)
	{
	   System.err.println("usage: java JarResources <jar file name> <resource name>");
	   System.exit(1);
	}

	JarResources jr = new JarResources(args[0]);
	Object obj = jr.getResource(args[1]);

	System.out.println("Object got created..");
}


}   // End of JarResources class.

