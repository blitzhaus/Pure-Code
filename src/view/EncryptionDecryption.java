package view;

import java.util.Scanner;
import java.io.*;

public class EncryptionDecryption {

	public EncryptionDecryption() {

		marker = new String("EncryptDecrypt demo");
	}

	void encrypt(String s, String s1) {

		try {
			if (s.equals("")) {
				throw new Exception();
			}

			FileInputStream fileinputstream = new FileInputStream(s);
			if (s1.equals("")) {
				throw new Exception();
			}
			if (s1.length() < 4) {
				throw new Exception();
			}
			byte abyte0[] = new byte[marker.length()];
			fileinputstream.read(abyte0);
			if ((new String(abyte0)).equals(marker)) {
				fileinputstream.close();
				fileinputstream.close();
				fileinputstream = new FileInputStream(s);
			} else {
				fileinputstream.close();
				fileinputstream = new FileInputStream(s);
			}
			int i = s.indexOf('.');
			String s2;
			if (i != -1)
				s2 = new String(s.substring(i + 1));
			else
				s2 = null;
			String s3;
			if (i != -1) {
				s3 = s.substring(0, i);
				if (s2.equals("enc"))
					s3 = s3 + "1";
			} else {
				s3 = new String(s);
			}
			try {
				FileInputStream fileinputstream1 = new FileInputStream(s3
						+ ".enc");
				throw new Exception();
			} catch (FileNotFoundException filenotfoundexception1) {
			} catch (Exception exception1) {
			}
			FileOutputStream fileoutputstream = new FileOutputStream(s3
					+ ".enc");
			fileoutputstream.write(marker.getBytes());
			if (s2 != null) {
				fileoutputstream.write(s2.length());
				fileoutputstream.write(s2.getBytes());
			} else {
				fileoutputstream.write(-1);
			}
			fileoutputstream.write(s1.length() + 100);
			char ac[] = s1.toCharArray();
			for (int j = 0; j < ac.length; j++)
				fileoutputstream.write(encrypted((byte) ac[j], (byte) ac[j],
						(byte) j));

			int k = fileinputstream.available();
			int l = s1.length();
			byte abyte1[] = new byte[k];
			byte abyte2[] = new byte[l];
			abyte2 = s1.getBytes();
			if (fileinputstream.read(abyte1) != -1) {
				for (int i1 = 0; i1 < k; i1++)
					fileoutputstream.write(encrypted(abyte1[i1],
							abyte2[i1 % l], (byte) i1));

			} else {
				throw new Exception();
			}
			fileinputstream.close();
			fileoutputstream.close();
		} catch (FileNotFoundException filenotfoundexception) {
		} catch (IOException ioexception) {
		} catch (Exception exception) {
			System.out.println("Password mismatch..");
		}
	}

	void decrypt(String s, String s1) {
		try {
			if (s.equals("")) {
				throw new Exception();
			}
			// if(s1.equals("demo")!=true)
			if (s1.equals("")) {
				throw new Exception();
			}
			FileInputStream fileinputstream = new FileInputStream(s);
			byte abyte0[] = new byte[marker.length()];
			fileinputstream.read(abyte0);
			if (!(new String(abyte0)).equals(marker)) {
				fileinputstream.close();
				throw new Exception();
			}
			int i = fileinputstream.read();
			String s2 = new String("");
			if ((byte) i != -1) {
				byte abyte1[] = new byte[i];
				fileinputstream.read(abyte1);
				s2 = new String(abyte1);
			}
			int j = s1.length();
			if (fileinputstream.read() - 100 != j) {
				fileinputstream.close();
				throw new Exception();
			}
			byte abyte2[] = new byte[j];
			byte abyte3[] = new byte[j];
			abyte3 = s1.getBytes();
			fileinputstream.read(abyte2);
			for (int k = 0; k < j; k++)
				abyte2[k] = decrypted(abyte2[k], abyte3[k], (byte) k);

			if (!s1.equals(new String(abyte2))) {
				fileinputstream.close();
			}
			int l = s.indexOf('.');
			String s3;
			if (l != -1) {
				s3 = s.substring(0, l);
				if (s2.equals("enc"))
					s3 = s3 + "1";
			} else {
				s3 = new String(s);
			}
			try {
				FileInputStream fileinputstream1 = new FileInputStream(s3 + "."
						+ s2);
				//throw new Exception();
			} catch (FileNotFoundException filenotfoundexception1) {
			} catch (Exception exception1) {
			}
			FileOutputStream fileoutputstream = new FileOutputStream(s3 + "."
					+ s2);
			int i1 = fileinputstream.available();
			byte abyte4[] = new byte[i1];
			if (fileinputstream.read(abyte4) != -1) {
				for (int j1 = 0; j1 < i1; j1++)
					fileoutputstream.write(decrypted(abyte4[j1],
							abyte3[j1 % j], (byte) j1));

			} else {
				throw new Exception();
			}
			fileinputstream.close();
			fileoutputstream.close();
		} catch (FileNotFoundException filenotfoundexception) {
		} catch (IOException ioexception) {
		} catch (Exception exception) {
		}
	}

	byte encrypted(byte byte0, byte byte1, byte byte2) {
		return (byte) (byte0 + byte1 + byte2 * byte2);
	}

	byte decrypted(byte byte0, byte byte1, byte byte2) {
		return (byte) (byte0 - byte1 - byte2 * byte2);
	}

	public static void main(String args[]) throws IOException {
		try {
			EncryptionDecryption encryptdecrypt = new EncryptionDecryption();
			
			System.out.println("Enter integer to encrypt / decrypt ( 1 or 2)");
			Scanner sin = new Scanner(System.in);
			int ipInt = sin.nextInt();
			String fileName = null;
			if (ipInt == 1)
			{
				System.out.println("Enter jar file name");
				Scanner sin1 = new Scanner(System.in);
				fileName = sin1.nextLine();
			}
			else
			{
				System.out.println("Enter encrypted file name");
				Scanner sin1 = new Scanner(System.in);
				fileName = sin1.nextLine();				
			}		

			if (ipInt == 1)
				encryptdecrypt.encrypt(fileName, "demo");
			else
				encryptdecrypt.decrypt(fileName, "demo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String marker;
}