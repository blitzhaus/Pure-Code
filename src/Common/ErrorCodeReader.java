package Common;

import java.io.*;
import java.util.*;

public class ErrorCodeReader
{
	private Hashtable errorMsgDetails = new Hashtable();
	
	public Hashtable getErrorMessageDetails()
	{
		return errorMsgDetails;
	}

	ErrorCodeReader(String fileName)
	{
		try
		{
			BufferedReader in = new BufferedReader (new FileReader(fileName));
			String line = null;
			line = in.readLine();

			while (line != null)
			{
			  StringTokenizer stringToken = new StringTokenizer (line, "#");
			  int tknCount = stringToken.countTokens();

			  String errorCode = stringToken.nextToken();
			  String errorMessage = stringToken.nextToken();
			  errorMsgDetails.put(errorCode, errorMessage);
			  
			  line = in.readLine();
			}
			
			in.close();  //***** close file
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main (String[] aArguments)
	{
		try
		{
			System.out.println(" Enter the input file name:");
			String FileName = ConsoleInput.readLine();
			ErrorCodeReader rf = new ErrorCodeReader(FileName);

			System.exit(1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
