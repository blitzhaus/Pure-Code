package view;

import java.util.*;
import java.util.jar.*;
import java.util.zip.*;
import java.io.*;

public class JarReader
{
	private String[] fileListing;

	public String[] getFileListing()
	{
		return fileListing;
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter jar file name: ");
		String filename = in.readLine();
		JarReader mc = new JarReader(filename);
	}

	public JarReader(String fileName) throws IOException{
		//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		///System.out.print("Enter jar file name: ");
		//String filename = in.readLine();
		File file = new File(fileName);
		if(!fileName.endsWith(".jar"))
		{
			System.out.println("Invalid file name!");
			System.exit(0);
		}
		else if(!file.exists()){
			System.out.println("File not exist!");
			System.exit(0);
		}

		try{
			JarFile jarfile = new JarFile(fileName);
			Enumeration em = jarfile.entries();

			int sze = jarfile.size();
			fileListing = new String[sze];
			int counter = 0;

			for (Enumeration em1 = jarfile.entries(); em1.hasMoreElements();)
			{
				fileListing[counter] = em1.nextElement()+"";
				System.out.println(em1.nextElement());
				counter++;
			}
		}
		catch(ZipException ze){
			System.out.println(ze.getMessage());
			System.exit(0);
		}
	}
}

