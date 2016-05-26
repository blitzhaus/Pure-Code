package view;

import java.io.*;
import java.util.jar.*;

public class CreateJar {
	public static int buffer = 10240;

	public CreateJar(String folderPath) {
		File folder = new File(folderPath);
		File[] files = folder.listFiles();
		File file = new File("output.jar");
		createJarArchive(file, files);
	}

	protected void createJarArchive(File jarFile, File[] listFiles) {
		try {
			byte b[] = new byte[buffer];
			FileOutputStream fout = new FileOutputStream(jarFile);
			JarOutputStream out = new JarOutputStream(fout, new Manifest());
			for (int i = 0; i < listFiles.length; i++) {
				if (listFiles[i] == null || !listFiles[i].exists()
						|| listFiles[i].isDirectory())
					System.out.println();
				JarEntry addFiles = new JarEntry(listFiles[i].getName());
				addFiles.setTime(listFiles[i].lastModified());
				out.putNextEntry(addFiles);

				FileInputStream fin = new FileInputStream(listFiles[i]);
				while (true) {
					int len = fin.read(b, 0, b.length);
					if (len <= 0)
						break;
					out.write(b, 0, len);
				}
				fin.close();
			}
			out.close();
			fout.close();
			System.out.println("Jar File is created successfully.");
		} catch (Exception ex) {
		}
	}

	public static void main(String[] args) {

	}
}