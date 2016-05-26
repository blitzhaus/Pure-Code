package Model;

import java.util.*;
import java.io.*;
import java.sql.*;
import Jama.*;

import org.apache.commons.collections.comparators.ComparatorChain;

class Sorter implements Comparator
{
	int colIndex = 0;

	Sorter(int cIdx, int numCols)
	{
		colIndex = cIdx;
	}

	public int compare(Object obj1, Object obj2)
	{
		String[] arrayOne = (String[]) obj1;
		String[] arrayTwo = (String[]) obj2;

		return arrayOne[colIndex].compareTo(arrayTwo[colIndex]);
	}

}

class NumberSorter implements Comparator
{
	int colIndex = 0;

	NumberSorter(int cIdx, int numCols)
	{
		colIndex = cIdx;
	}

	public int compare(Object obj1, Object obj2)
	{
		String[] arrayOne = (String[]) obj1;
		String[] arrayTwo = (String[]) obj2;

		double num1 = Double.parseDouble(arrayOne[colIndex]);
		double num2 = Double.parseDouble(arrayTwo[colIndex]);

		if( num1 > num2 )
			return 1;
		else if( num1 < num2 )
			return -1;
		else
			return 0;
	}

}

public class TMMultiLevelSort
{

public static String[][] sortData(String[][] data, Vector colIndices)
{
	ComparatorChain chainOfComparators = new ComparatorChain();
	
	int cCount = data[0].length;
	for(int i = 0; i < colIndices.size(); i++)
	{
		int idx = Integer.parseInt((String)colIndices.get(i));

		if (idx == cCount-1 || idx == cCount-2)
			chainOfComparators.addComparator(new NumberSorter(idx, data[0].length));
		else
			chainOfComparators.addComparator(new Sorter(idx, data[0].length));
	}
	Arrays.sort(data, chainOfComparators);
	
	return data;
}

	public static String[][] sortData(String[][] data,
			ArrayList<String> colIndices, ArrayList<String> numVarListing)
{
	ComparatorChain chainOfComparators = new ComparatorChain();
	
	int cCount = data[0].length;
	for(int i = 0; i < colIndices.size(); i++)
	{
		int idx = Integer.parseInt((String)colIndices.get(i));

		//if (idx == cCount-1 || idx == cCount-2)
		String currColIndex = colIndices.get(i);
		if (numVarListing.contains(currColIndex) == true)
		{
			chainOfComparators.addComparator(new NumberSorter(idx, data[0].length));
		}
		else
		{
			chainOfComparators.addComparator(new Sorter(idx, data[0].length));
		}
	}
	Arrays.sort(data, chainOfComparators);
	
	return data;
}
	
	public static String[][] sortData(String[][] data,
			ArrayList<String> colIndices) {
		ComparatorChain chainOfComparators = new ComparatorChain();

		int cCount = data[0].length;
		for (int i = 0; i < colIndices.size(); i++) {
			int idx = Integer.parseInt((String) colIndices.get(i));

			if (idx == cCount-1 || idx == cCount-2)
			{
				chainOfComparators.addComparator(new NumberSorter(idx,
						data[0].length));
			} else {
				chainOfComparators
						.addComparator(new Sorter(idx, data[0].length));
			}
		}
		Arrays.sort(data, chainOfComparators);

		return data;
	}
	
}

