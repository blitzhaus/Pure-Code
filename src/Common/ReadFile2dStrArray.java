package Common;

import java.io.*;
import java.util.*;
import java.sql.*;

public class ReadFile2dStrArray {
	public double[][] inputMatrix;
	public String[][] fileArray;
	public int rowCount = 0;
	public int colCount = 0;
	public String fileName;
	public String[] stringArray;
	public String[] descrArray;
	public boolean isFileExists = false;
	public static int numberOfFilesRead = 0;

	public void FUNCTION_ENTER(String func_name) {
		try {
			P2.println("Entering\t" + func_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void FUNCTION_EXIT(String func_name) {
		try {
			P2.println("Leaving\t" + func_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** Creates new ReadFile2dArray */
	public ReadFile2dStrArray() {

		numberOfFilesRead = numberOfFilesRead + 1;

		while (isFileExists == false) {

			try {

				Scanner scan = new Scanner(System.in);
				fileName = scan.nextLine();
				BufferedReader in = new BufferedReader(new FileReader(fileName));
				String line = null;
				line = in.readLine();

				while (line != null) {
					rowCount = rowCount + 1;
					StringTokenizer stringToken = new StringTokenizer(line);

					if (rowCount == 1) {
						colCount = stringToken.countTokens();
					}

					line = in.readLine();

				} // ********* end while line ! =null

				in.close(); // ***** close file

				int rcount = 0;
				int ccount = 0;
				int strcount = 0;
				colCount = colCount - 1;

				fileArray = new String[rowCount][colCount]; // ****** Array set
				stringArray = new String[rowCount];
				BufferedReader inp = new BufferedReader(
						new FileReader(fileName));
				String lineRead = null;
				lineRead = inp.readLine();

				while (lineRead != null) {

					StringTokenizer stringToken2 = new StringTokenizer(lineRead);

					stringArray[strcount] = stringToken2.nextToken();

					while (stringToken2.hasMoreTokens()) {

						String ss = stringToken2.nextToken();
						fileArray[rcount][ccount] = ss;
						ccount = ccount + 1;

					} // ******* end while hasMoreTokens

					lineRead = inp.readLine();
					rcount = rcount + 1;
					strcount = strcount + 1;
					ccount = 0;

				} // ********* end while line ! =null

				inp.close();

				isFileExists = true;

			} // end try

			catch (ArrayIndexOutOfBoundsException e) {
				System.out
						.println(" The Number of Rows or Colums are more than declared in a array ");
				System.exit(0);

			}

			catch (FileNotFoundException e) {

				System.out.println(" ERROR : File Data not found ");
				System.exit(0);
			}

			catch (IOException e) {
				System.out.println(" ERROR ");
			}

			catch (NoSuchElementException e) {
				System.out.println(" NUMBER ERROR ");
			}

		}// end while

		descrArray = loadDescDetails(fileArray);

		fileArray = filterHeader(fileArray);
		rowCount = fileArray.length;
		colCount = fileArray[0].length;
		stringArray = filterLabels(stringArray);

	} // end Constructor

	private String[] loadDescDetails(String[][] readArray) {
		String[] descrLabels = new String[readArray[0].length];
		for (int j = 0; j < readArray[0].length; j++) {
			descrLabels[j] = readArray[0][j];
		}
		return descrLabels;
	}

	public String[][] filterHeader(String[][] ipArray) {
		String[][] filData = new String[ipArray.length - 1][ipArray[0].length];

		for (int i = 1; i < ipArray.length; i++) {
			for (int j = 0; j < ipArray[0].length; j++) {
				filData[i - 1][j] = ipArray[i][j];
			}
		}

		return filData;
	}

	public String[] filterLabels(String[] lblArrays) {
		String[] filLabels = new String[lblArrays.length - 1];

		for (int i = 1; i < lblArrays.length; i++) {
			filLabels[i - 1] = lblArrays[i];
		}

		return filLabels;
	}

	public ReadFile2dStrArray(String fileLocation) {

		fileName = fileLocation;
		numberOfFilesRead = numberOfFilesRead + 1;

		while (isFileExists == false) {

			try {
				BufferedReader in = new BufferedReader(new FileReader(fileName));
				String line = null;
				line = in.readLine();

				while (line != null) {

					rowCount = rowCount + 1;
					StringTokenizer stringToken = new StringTokenizer(line);

					if (rowCount == 1) {
						colCount = stringToken.countTokens();
					}

					line = in.readLine();

				} // ********* end while line ! =null

				in.close(); // ***** close file

				int rcount = 0;
				int ccount = 0;
				int strcount = 0;
				colCount = colCount - 1;
				System.out.println(" ROWw " + rowCount);
				System.out.println(" COLl " + colCount);
				fileArray = new String[rowCount][colCount]; // ****** Array set
				stringArray = new String[rowCount];
				BufferedReader inp = new BufferedReader(
						new FileReader(fileName));
				String lineRead = null;
				lineRead = inp.readLine();

				while (lineRead != null) {
					StringTokenizer stringToken2 = new StringTokenizer(lineRead);
					stringArray[strcount] = stringToken2.nextToken();

					while (stringToken2.hasMoreTokens()) {
						String ss = stringToken2.nextToken();
						fileArray[rcount][ccount] = ss;
						ccount = ccount + 1;
					} // ******* end while hasMoreTokens

					lineRead = inp.readLine();
					rcount = rcount + 1;
					strcount = strcount + 1;
					ccount = 0;

				} // ********* end while line ! =null

				inp.close();
				isFileExists = true;
			} // end try

			catch (ArrayIndexOutOfBoundsException e) {
				System.out
						.println(" The Number of Rows or Colums are more than declared in a array ");
				System.exit(0);

			}
			catch (FileNotFoundException e) {
				System.out.println(" ERROR : File Data not found ");
				System.exit(0);
			}

			catch (IOException e) {
				System.out.println(" ERROR ");
			}

			catch (NoSuchElementException e) {
				System.out.println(" NUMBER ERROR ");
			}

		}// end while

		descrArray = loadDescDetails(fileArray);
		fileArray = filterHeader(fileArray);
		rowCount = fileArray.length;
		colCount = fileArray[0].length;
		stringArray = filterLabels(stringArray);
	} // end Constructor

	// ****************************************END CONSTRUCTOR
	// *********************************

	public void printMatrix(String str, String[][] matrix, int rowDim,
			int colDim) {

		FUNCTION_ENTER("printMatrix");

		P2.println("Input String:" + str);
		P2.println("row Dimension:" + rowDim + "\n");
		P2.println("col Dimension:" + colDim + "\n");

		for (int r = 0; r < rowDim; r++) {
			for (int c = 0; c < colDim; c++) {
				P2.print(matrix[r][c] + "\t");
			}
			P2.println();
		}

		P2.println("\n");

		FUNCTION_EXIT("printMatrix");
	}

	public void printArray(String str, String[] array, int rowDim) {
		FUNCTION_ENTER("printArray");

		P2.println("Input String:" + str);
		P2.println("row Dimension:" + rowDim + "\n");

		for (int r = 0; r < rowDim; r++) {
			P2.println(array[r] + "\t");
		}

		FUNCTION_EXIT("printArray");
	}

	public void printMatrix(String str, double[][] matrix, int rowDim,
			int colDim) {

		FUNCTION_ENTER("printMatrix");

		P2.println("Input String:" + str);
		P2.println("row Dimension:" + rowDim + "\n");
		P2.println("col Dimension:" + colDim + "\n");

		for (int r = 0; r < rowDim; r++) {
			for (int c = 0; c < colDim; c++) {
				P2.print(matrix[r][c] + "\t");
			}
			P2.println();
		}

		P2.println("\n");

		FUNCTION_EXIT("printMatrix");
	}

	private double[][] convStrArrayToDbl(String[][] ipMatrix, int rows, int cols) {
		double[][] ipData = new double[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				ipData[i][j] = Double.parseDouble(fileArray[i][j]);
			}
		}

		return ipData;
	}

	public static PrintStream P2;

	public static void main(String[] aArguments) {
		try {
			System.out.println(" Enter output file name:");
			Scanner scan = new Scanner(System.in);
			String oFileName = scan.nextLine();
			P2 = new PrintStream(new FileOutputStream(oFileName));

			Time t1 = new Time(System.currentTimeMillis());
			P2.println("Time at start is:" + t1);

			System.out.println(" Enter input file name:");
			ReadFile2dStrArray rf = new ReadFile2dStrArray();

			Time t2 = new Time(System.currentTimeMillis());
			P2.println("Time at start is:" + t2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

} // end Class
