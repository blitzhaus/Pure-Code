package Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.commons.math.stat.StatUtils;
import Jama.Matrix;
import edu.umbc.cs.maple.utils.JamaUtils;


public class Template2Impl {
	
	private String[][] inputMatrix;
	private int rowCount = 0;
	private int colCount = 0;
	private String[] stringArray;
	private String[] descrArray;
	private TableWizardInputOptions tableWizardinputs;
	private TableWizardOpMetricOptions opMetListing;
	TableWizardCoordinator tempImpl;
	
	public Template2Impl(TableWizardCoordinator tempImpl) {

		this.tempImpl = tempImpl;
		rowCount = tempImpl.getRowCount();
		colCount = tempImpl.getColCount();
		inputMatrix = tempImpl.getInputMatrix();
		stringArray = tempImpl.getStringArray();
		descrArray = tempImpl.getDescrArray();

		tempImpl.printMatrix("Input data..", inputMatrix, inputMatrix.length,
				inputMatrix[0].length);
	}
	
	/* (non-Javadoc)
	 * @see org.tcs.tablemaven.Template#setTabWizIpOptions(java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, int)
	 */
	public void setTabWizIpOptions(ArrayList<String> groupVariables,
			ArrayList<String> idVariables, ArrayList<String> crossVariables,
			ArrayList<String> ordinaryVariables, int templateType) {

		tableWizardinputs = new TableWizardInputOptions(templateType, groupVariables,
				idVariables, crossVariables, ordinaryVariables);
	}
	
	/* (non-Javadoc)
	 * @see org.tcs.tablemaven.Template#setTabWizOpOptions(org.tcs.tablemaven.TableWizardOpMetricOptions)
	 */
	public void setTabWizOpOptions(TableWizardOpMetricOptions opMetListing) {
		this.opMetListing = opMetListing;
	}
		
	public Template12Results[] prepareTemplate() {
		ArrayList<String> grpVariables = tableWizardinputs.getGroupVariables();

		int[] columnIndices = tempImpl.getColumnIndices(grpVariables);

		ArrayList<String> vec = new ArrayList<String>();

		for (int i = 0; i < columnIndices.length; i++) {
			vec.add(columnIndices[i] + "");
		}
		
		ArrayList<String> idVariables = tableWizardinputs.getIdVariables();

		columnIndices = tempImpl.getColumnIndices(idVariables);
		
		ArrayList<String> numColIndices = new ArrayList<String>();

		for (int i = 0; i < columnIndices.length; i++) {
			vec.add(columnIndices[i] + "");
			numColIndices.add(columnIndices[i] + "");
		}
		
		String[][] sortedData = TMMultiLevelSort.sortData(inputMatrix, vec, numColIndices);
		
		tempImpl.printMatrix("Template-2..", sortedData, sortedData.length, sortedData[0].length);
		
		String[][] columnToAppend = new String[inputMatrix.length][1];

		Vector ulForGVsCombn = new Vector();
		
		columnIndices = tempImpl.getColumnIndices(grpVariables);

		for (int i = 0; i < inputMatrix.length; i++) {

			String tmpStr = new String();

			for (int j = 0; j < columnIndices.length; j++) {
				tmpStr += "#"+inputMatrix[i][columnIndices[j]];
			}
			columnToAppend[i][0] = tmpStr;

			if (ulForGVsCombn.contains(tmpStr) != true)
				ulForGVsCombn.add(tmpStr);
		}

		String[][] sortedAppendedData = tempImpl.appendColumns(sortedData,
				columnToAppend);

		tempImpl.printMatrix("Sorted Appended Data", sortedAppendedData,
				sortedAppendedData.length, sortedAppendedData[0].length);
		
		Template12Results[] temp1Results = new Template12Results[ulForGVsCombn.size()];

		for (int i = 0; i < ulForGVsCombn.size(); i++) {

			String level = (String) ulForGVsCombn.get(i);
			
			temp1Results[i] = new Template12Results();
			
			temp1Results[i].setCompKey(level);
			
			String[][] levelData = tempImpl.pickDataForLevel(level, sortedAppendedData);

			tempImpl.printMatrix("levelData for-->" + level, levelData,
					levelData.length, levelData[0].length);
			
			temp1Results[i].setLevelData(levelData);
		}

		return temp1Results;
	}
	
	public static PrintStream P2;
	public static void main(String[] args) {
		
		try {
			P2 = new PrintStream(new FileOutputStream("output.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TableWizardCoordinator ti = new TableWizardCoordinator(P2);// 
		Template2Impl ti2 = new Template2Impl(ti);
		
		/*
		 * Table Wizard Input Options
		 */
		
		ArrayList<String> groupVariables = new ArrayList<String>();
		ArrayList<String> idVariables = new ArrayList<String>();
		ArrayList<String> crossVariables = new ArrayList<String>();
		ArrayList<String> ordinaryVariables = new ArrayList<String>();

		groupVariables.add("Subject_ID");
		groupVariables.add("Formulation");

		idVariables.add(" ");

		crossVariables.add(" ");

		ordinaryVariables.add("BP");
		ordinaryVariables.add("Conc");

		int templateType = 1;
		
		ti2.setTabWizIpOptions(groupVariables, idVariables, crossVariables,
				ordinaryVariables, templateType);
		
		/*
		 * Table Wizard Output Options
		 */
		
		Hashtable hTableOpOptions = new Hashtable();
		TableWizardOpMetricOptions opMetListing = new TableWizardOpMetricOptions(hTableOpOptions);
		//ti2.setTabWizOpOptions(opMetListing);		
		ti2.prepareTemplate();
	}


}
