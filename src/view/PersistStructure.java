package view;
import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import java.io.File;
import java.util.StringTokenizer;

import view.ApplicationInfo;


public class PersistStructure 
{
	public PersistStructure(String folderPath)
	{
		
	}
	
	public PersistStructure() {
		// TODO Auto-generated constructor stub
	}
	
	JarResources jarReader;
	public ApplicationInfo readApplicationInfo(String jarFileName) throws IOException, ClassNotFoundException
	{
		jarReader = new JarResources(jarFileName);
		ApplicationInfo appInfo = (ApplicationInfo)jarReader.getResource("store.ser");
		
		return appInfo;
	}
	
	EncryptionDecryption encryptDecrypt;
	ApplicationInfo readEncryptedFile(String encFileName)
	{
		ApplicationInfo appInfo = null;
		encryptDecrypt = new EncryptionDecryption();
		encryptDecrypt.decrypt(encFileName, "demo");
		
		StringTokenizer sTknzer = new StringTokenizer(encFileName, ".");
		
		String tknFirst = sTknzer.nextToken();
		String jarFileName = tknFirst.concat(".jar");
		
		try {
			appInfo = (ApplicationInfo) readApplicationInfo(jarFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return appInfo;
	}

	public Object readMyObject(String jarFileName) throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new FileInputStream(jarFileName); 
		ObjectInputStream ois = new ObjectInputStream(fis);
		ApplicationInfo appInfo = (ApplicationInfo) ois.readObject();
		return appInfo;
	}
	
	JarUtility jarUtil;
	public void writeMyObject(Object obj, String fileName, String folderPath) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(fileName); 
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		jarUtil = new JarUtility();
		jarUtil.createAJar(folderPath, "outjar.jar");
		EncryptionDecryption encryptdecrypt = new EncryptionDecryption();
		encryptdecrypt.encrypt("outjar.jar", "demo");
	}
	
}
