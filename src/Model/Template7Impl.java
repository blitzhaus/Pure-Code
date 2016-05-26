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

public class Template7Impl {

	private String[][] inputMatrix;
	private int rowCount = 0;
	private int colCount = 0;
	private String[] stringArray;
	private String[] descrArray;
	private TableWizardInputOptions tableWizardinputs;
	private TableWizardOpMetricOptions opMetListing;
	TableWizardCoordinator tempImpl;

	public Template7Impl(TableWizardCoordinator tempImpl) {

		this.tempImpl = tempImpl;
		rowCount = tempImpl.getRowCount();
		colCount = tempImpl.getColCount();
		inputMatrix = tempImpl.getInputMatrix();
		stringArray = tempImpl.getStringArray();
		descrArray = tempImpl.getDescrArray();

		tempImpl.printMatrix("Input data..", inputMatrix, inputMatrix.length,
				inputMatrix[0].length);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.tcs.tablemaven.Template#setTabWizIpOptions(java.util.ArrayList,
	 * java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, int)
	 */
	public void setTabWizIpOptions(ArrayList<String> groupVariables,
			ArrayList<String> idVariables, ArrayList<String> crossVariables,
			ArrayList<String> ordinaryVariables, int templateType) {

		tableWizardinputs = new TableWizardInputOptions(templateType,
				groupVariables, idVariables, crossVariables, ordinaryVariables);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.tcs.tablemaven.Template#setTabWizOpOptions(org.tcs.tablemaven.
	 * TableWizardOpMetricOptions)
	 */
	public void setTabWizOpOptions(TableWizardOpMetricOptions opMetListing) {
		this.opMetListing = opMetListing;
	}

	public void initializeSetup() {
		ArrayList<String> groupVariables = new ArrayList<String>();
		ArrayList<String> idVariables = new ArrayList<String>();
		ArrayList<String> crossVariables = new ArrayList<String>();
		ArrayList<String> ordinaryVariables = new ArrayList<String>();

		groupVariables.add("Subject_ID");
		idVariables.add("Time");
		crossVariables.add("Formulation");

		ordinaryVariables.add("BP");
		ordinaryVariables.add("Conc");

		int templateType = 1;

		tableWizardinputs = new TableWizardInputOptions(templateType,
				groupVariables, idVariables, crossVariables, ordinaryVariables);
	}

	public void prepareTemplate7ForGrpCvCombn(String grpVariable,
			String crossVariable) {
		P2.println("*************************Group Variable Name.."
				+ grpVariable + "*************************");
		P2.println("#########################Cross Variable Name."
				+ crossVariable + "#########################");

		int columnIndexOfGrp = tempImpl.getColumnIndex(grpVariable);
		int columnIndexOfCrossVar = tempImpl.getColumnIndex(crossVariable);

		ArrayList<String> vec = new ArrayList<String>();

		vec.add(columnIndexOfGrp + "");
		vec.add(columnIndexOfCrossVar + "");

		ArrayList<String> idVariables = tableWizardinputs.getIdVariables();

		int[] columnIndices = tempImpl.getColumnIndices(idVariables);

		for (int i = 0; i < columnIndices.length; i++) {
			vec.add(columnIndices[i] + "");
		}

		ArrayList<String> numColIndices = new ArrayList<String>();

		for (int i = 0; i < columnIndices.length; i++) {
			numColIndices.add(columnIndices[i] + "");
		}

		/*
		 * Sort the data on the basis of group variable first followed by cross
		 * variable.
		 */
		String[][] sortedData = TMMultiLevelSort.sortData(inputMatrix, vec,
				numColIndices);

		String[][] columnToAppend = new String[inputMatrix.length][1];

		Vector ulForGVsCombn = new Vector();

		for (int i = 0; i < inputMatrix.length; i++) {

			String tempStr = new String();

			tempStr = inputMatrix[i][columnIndexOfGrp];
			tempStr += inputMatrix[i][columnIndexOfCrossVar];

			columnToAppend[i][0] = tempStr;

			if (ulForGVsCombn.contains(tempStr) != true)
				ulForGVsCombn.add(tempStr);
		}

		String[][] sortedAppendedData = tempImpl.appendColumns(sortedData,
				columnToAppend);

		ArrayList<String> ordVariables = tableWizardinputs
				.getOrdinaryVariables();
		for (int j = 0; j < ordVariables.size(); j++) {

			String ordVariableName = ordVariables.get(j);

			for (int i = 0; i < ulForGVsCombn.size(); i++) {

				String level = (String) ulForGVsCombn.get(i);
				String[][] levelData = tempImpl.pickDataForLevel(level,
						sortedAppendedData);

				P2.println("level details.." + level);

				tempImpl.printMatrix("levelData for-->" + level, levelData,
						levelData.length, levelData[0].length);

				double[][] varData = tempImpl.pickColumnData(ordVariableName, levelData);

				P2.println("ordVariableName.." + ordVariableName);

				double[] singVarData = tempImpl.convDblDimToSinDim(varData);

				double max = StatUtils.max(singVarData);
				double min = StatUtils.min(singVarData);
				double mean = StatUtils.mean(singVarData);
				double median = StatUtils.percentile(singVarData, 50);

				P2.println("maximum.." + max + " minimum:::" + min + " Mean:::"
						+ mean + " Median:::" + median);
			}
		}

	}

	public void prepareTemplate() {
		ArrayList<String> grpVariables = tableWizardinputs.getGroupVariables();
		ArrayList<String> crossVariables = tableWizardinputs
				.getCrossVariables();

		for (int i = 0; i < grpVariables.size(); i++) {

			String grpVarName = grpVariables.get(i);
			for (int j = 0; j < crossVariables.size(); j++) {

				String crossVarName = crossVariables.get(j);
				prepareTemplate7ForGrpCvCombn(grpVarName, crossVarName);
			}

		}
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
		Template7Impl ti7 = new Template7Impl(ti);

		/*
		 * Table Wizard Input Options
		 */

		ArrayList<String> groupVariables = new ArrayList<String>();
		ArrayList<String> idVariables = new ArrayList<String>();
		ArrayList<String> crossVariables = new ArrayList<String>();
		ArrayList<String> ordinaryVariables = new ArrayList<String>();

		groupVariables.add("Subject_ID");
		idVariables.add("Time");
		crossVariables.add("Formulation");

		ordinaryVariables.add("BP");
		ordinaryVariables.add("Conc");

		int templateType = 1;

		ti7.setTabWizIpOptions(groupVariables, idVariables, crossVariables,
				ordinaryVariables, templateType);

		/*
		 * Table Wizard Output Options
		 */

		Hashtable hTableOpOptions = new Hashtable();
		TableWizardOpMetricOptions opMetListing = new TableWizardOpMetricOptions(
				hTableOpOptions);
		ti7.setTabWizOpOptions(opMetListing);
		ti7.prepareTemplate();
	}

}
