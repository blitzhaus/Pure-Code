package Common;

import java.io.*;
import java.util.*;

import view.DisplayContents;
import view.ImportFile;
import view.DDViewLayer;


public class DataReader2 {

	private static DataReader2 D_DATAREADER = null;

	public static DataReader2 createDataReaderInstance(String fileName,
			String dataType) {
		if (D_DATAREADER == null) {
			D_DATAREADER = new DataReader2(fileName, dataType);
		}

		return D_DATAREADER;
	}

	public double[][] fileArray;
	public double[][] new_fileArray; // After removing missing values
	public String[][] StringFileArray;
	public String[][] new_StringFileArray; // After removing missing values
	public int rowCount = 0;
	public int colCount = 0;
	public int new_rowCount = 0;
	public int new_colCount = 0;
	public String fileName;
	public boolean isFileExists = false;
	public static int numberOfFilesRead = 0;
	public boolean isRowBig = false;
	public String delims = "";
	public String Extension = "";

	/**
	 * Constructor-1 (File name provided through console) (file type - .txt,
	 * .csv, .dat, .dta, , .bat, .prn)
	 */
	// ***********************************************************************************************************************
	public DataReader2(String dataType) {

		numberOfFilesRead = numberOfFilesRead + 1;

		while (isFileExists == false) {

			try {

				fileName = ConsoleInput.readLine();
				rowCount = 0;
				colCount = 0;
				new_rowCount = 0;
				new_colCount = 0;

				BufferedReader in = new BufferedReader(new FileReader(fileName));
				String line = null;
				boolean lineStart = false;
				line = in.readLine();

				int dot = fileName.lastIndexOf('.');
				Extension = fileName.substring(dot + 1);
				while (line != null) {

					if (line.contains(","))
						delims = ",";
					else if (line.contains(";"))
						delims = ";";
					else if (line.contains("\t"))
						delims = "\t";
					else if (line.contains("     "))
						delims = "     ";
					else if (line.contains("    "))
						delims = "    ";
					else if (line.contains("   "))
						delims = "   ";
					else if (line.contains("  "))
						delims = "  ";
					else if (line.contains(" "))
						delims = " ";

					StringTokenizer stringToken = new StringTokenizer(line,
							delims);
					lineStart = line.startsWith("#");

					if (lineStart == false) {
						boolean missingValue = line.contains("--");
						if (missingValue == false)
							new_rowCount = new_rowCount + 1;

						rowCount = rowCount + 1;
					}

					if (rowCount == 1)
						colCount = stringToken.countTokens();

					if (new_rowCount == 1)
						new_colCount = stringToken.countTokens();

					line = in.readLine();

				} // ********* end while line ! =null

				in.close(); // ***** close file

				// ******************************************************
				// To check ROW count is bigger than COL count

				if (rowCount >= colCount)
					isRowBig = true;
				else
					isRowBig = false;

				// *******************************************************
				int rcount = 0;
				int new_rcount = 0;
				int ccount = 0;
				int new_ccount = 0;
				fileArray = new double[rowCount][colCount]; // ****** Array set
				new_fileArray = new double[new_rowCount][new_colCount]; // ******
																		// Array
																		// set
				StringFileArray = new String[rowCount][colCount]; // ******
																	// Array set
				new_StringFileArray = new String[new_rowCount][new_colCount]; // ******
																				// Array
																				// set

				BufferedReader inp = new BufferedReader(
						new FileReader(fileName));
				String lineRead = null;
				boolean ssStart = false;
				boolean missingValue = false;

				lineRead = inp.readLine();

				Blue: while (lineRead != null) {

					missingValue = lineRead.contains("--");
					while (missingValue == true) {
						StringTokenizer stringToken1 = new StringTokenizer(
								lineRead, delims);
						while (stringToken1.hasMoreTokens()) {
							String s1 = stringToken1.nextToken();
							ssStart = s1.startsWith("#");
							if (ssStart == false) {
								if ((s1 == "--")
										|| (s1.startsWith("--") == true)
										|| (s1.contains("--") == true)) {
									if (dataType.equals("double"))
										fileArray[rcount][ccount] = 999999.9;
									if (dataType.equals("String"))
										StringFileArray[rcount][ccount] = "missing";
								} else {
									if (dataType.equals("double"))
										fileArray[rcount][ccount] = Double
												.parseDouble(s1);
									if (dataType.equals("String"))
										StringFileArray[rcount][ccount] = s1;
								}
								ccount = ccount + 1;
							}
						}
						ccount = 0;
						rcount = rcount + 1;
						lineRead = inp.readLine();
						if (lineRead == null)
							break Blue;
						missingValue = lineRead.contains("--");
					}

					StringTokenizer stringToken2 = new StringTokenizer(
							lineRead, delims);

					while (stringToken2.hasMoreTokens()) {

						String ss = stringToken2.nextToken();
						ssStart = ss.startsWith("#");
						if (ssStart == false) {
							new_fileArray[new_rcount][new_ccount] = Double
									.parseDouble(ss);
							fileArray[rcount][ccount] = Double.parseDouble(ss);
							new_ccount = new_ccount + 1;
							ccount = ccount + 1;
						}
					} // ******* end while hasMoreTokens
					lineRead = inp.readLine();
					if (ssStart == false) {
						rcount = rcount + 1;
						new_rcount = new_rcount + 1;
					}
					ccount = 0;
					new_ccount = 0;

				} // ********* end while line ! =null

				inp.close();
				isFileExists = true;

				// *******************************************************
			} // end try

			catch (ArrayIndexOutOfBoundsException e) {
				System.out
						.println(" The Number of Rows or Colums are more than declared in a array ");
				// System.exit(0);

			}

			catch (FileNotFoundException e) {
				System.out.println(" ERROR : File Data not found ");
				// System.exit(0);
			}

			catch (IOException e) {
				System.out.println(" ERROR ");
			}

			catch (NoSuchElementException e) {
				System.out.println(" NUMBER ERROR ");
			}

		} // end while isFileExists

	} // end default cconstructor

	// **************************************************************************************************************

	/**
	 * Constructor-2 (File name provided through argument) (file type - .txt,
	 * .csv, .dat, .dta, , .bat, .prn)
	 */
	// ***********************************************************************************************************************
	public DataReader2(String fileName, String dataType) {

		numberOfFilesRead = numberOfFilesRead + 1;

		rowCount = 0;
		colCount = 0;
		new_rowCount = 0;
		new_colCount = 0;

		while (isFileExists == false) {

			try {
				BufferedReader in = new BufferedReader(new FileReader(fileName));
				String line = null;
				boolean lineStart = false;
				line = in.readLine();

				int dot = fileName.lastIndexOf('.');
				Extension = fileName.substring(dot + 1);
				while (line != null) {

					if (line.contains(","))
						delims = ",";
					else if (line.contains(";"))
						delims = ";";
					else if (line.contains("\t"))
						delims = "\t";
					else if (line.contains("     "))
						delims = "     ";
					else if (line.contains("    "))
						delims = "    ";
					else if (line.contains("   "))
						delims = "   ";
					else if (line.contains("  "))
						delims = "  ";
					else if (line.contains(" "))
						delims = " ";

					StringTokenizer stringToken = new StringTokenizer(line,
							delims);
					lineStart = line.startsWith("#");
					if (lineStart == false) {
						boolean missingValue = line.contains("--");
						if (missingValue == false)
							new_rowCount = new_rowCount + 1;

						rowCount = rowCount + 1;
					}

					if (rowCount == 1) {

						colCount = stringToken.countTokens();
						new_colCount = stringToken.countTokens();
					}

					line = in.readLine();

				} // ********* end while line ! =null

				in.close(); // ***** close file

				// ******************************************************
				// To check ROW count is bigger than COL count

				if (rowCount >= colCount)
					isRowBig = true;
				else
					isRowBig = false;

				// *******************************************************
				int rcount = 0;
				int new_rcount = 0;
				int ccount = 0;
				int new_ccount = 0;
				fileArray = new double[rowCount][colCount]; // ****** Array set
				new_fileArray = new double[new_rowCount][new_colCount]; // ******
																		// Array
																		// set
				StringFileArray = new String[rowCount][colCount]; // ******
																	// Array set
				new_StringFileArray = new String[new_rowCount][new_colCount]; // ******
																				// Array
																				// set

				BufferedReader inp = new BufferedReader(
						new FileReader(fileName));
				String lineRead = null;
				boolean ssStart = false;
				boolean missingValue = false;

				lineRead = inp.readLine();
				Red: while (lineRead != null) {

					missingValue = lineRead.contains("--");
					while (missingValue == true) {
						StringTokenizer stringToken1 = new StringTokenizer(
								lineRead, delims);
						while (stringToken1.hasMoreTokens()) {
							String s1 = stringToken1.nextToken();
							ssStart = s1.startsWith("#");
							if (ssStart == false) {
								if ((s1 == "--")
										|| (s1.startsWith("--") == true)
										|| (s1.contains("--") == true)) {
									if (dataType.equals("double"))
										fileArray[rcount][ccount] = 999999.9;
									if (dataType.equals("String"))
										StringFileArray[rcount][ccount] = "missing";
								} else {
									if (dataType.equals("double"))
										fileArray[rcount][ccount] = Double
												.parseDouble(s1);
									if (dataType.equals("String"))
										StringFileArray[rcount][ccount] = s1;
								}
								ccount = ccount + 1;
							}
						}
						ccount = 0;
						rcount = rcount + 1;
						lineRead = inp.readLine();
						if (lineRead == null)
							break Red;
						else
							missingValue = lineRead.contains("--");

					}

					StringTokenizer stringToken2 = new StringTokenizer(
							lineRead, delims);

					while (stringToken2.hasMoreTokens()) {

						String ss = stringToken2.nextToken();
						ssStart = ss.startsWith("#");
						if (ssStart == false) {
							if (dataType.equals("double")) {
								new_fileArray[new_rcount][new_ccount] = Double
										.parseDouble(ss);
								fileArray[rcount][ccount] = Double
										.parseDouble(ss);
							}
							if (dataType.equals("String")) {
								new_StringFileArray[new_rcount][new_ccount] = ss;
								StringFileArray[rcount][ccount] = ss;
							}

							new_ccount = new_ccount + 1;
							ccount = ccount + 1;
						}
					} // ******* end while hasMoreTokens
					lineRead = inp.readLine();
					if (ssStart == false) {
						rcount = rcount + 1;
						new_rcount = new_rcount + 1;
					}
					ccount = 0;
					new_ccount = 0;

				} // ********* end while line ! =null

				inp.close();
				isFileExists = true;

				// *******************************************************
			} // end try

			catch (ArrayIndexOutOfBoundsException e) {
				/*TestException test = new TestException();
				test.throwCustomException(7);
				//ImportFile.createDispContentsInstance().f.dispose();
				ImportFile.DISP_CONTENTS = null;*/
				e.printStackTrace();

			}

			catch (FileNotFoundException e) {
				TestException test = new TestException();
				test.throwCustomException(1);
				ImportFile.createDispContentsInstance().f.dispose();
				ImportFile.DISP_CONTENTS = null;
			}

			catch (IOException e) {
				TestException test = new TestException();
				test.throwCustomException(9);
				ImportFile.createDispContentsInstance().f.dispose();
				ImportFile.DISP_CONTENTS = null;
			}

			catch (NoSuchElementException e) {
				TestException test = new TestException();
				test.throwCustomException(9);
				ImportFile.createDispContentsInstance().f.dispose();
				ImportFile.DISP_CONTENTS = null;
			}
		}
	}
}
