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

public class Template8Impl {

	private String[][] inputMatrix;
	private int rowCount = 0;
	private int colCount = 0;
	private String[] stringArray;
	private String[] descrArray;
	private TableWizardInputOptions tableWizardinputs;
	private TableWizardOpMetricOptions opMetListing;
	TableWizardCoordinator tempImpl;

	public Template8Impl(TableWizardCoordinator tempImpl) {

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
	
	
	public void prepareTemplate8ForGrpCvCombn(String grpVariable,
			String idVariable, String crossVariable) {
		P2.println("*************************Group Variable Name.."
				+ grpVariable + "*************************");
		P2.println("#########################Cross Variable Name." + idVariable
				+ "#########################");

		int columnIndexOfGrp = tempImpl.getColumnIndex(grpVariable);
		int columnIndexOfIdVar = tempImpl.getColumnIndex(idVariable);
		int columnIndexOfCrossVar = tempImpl.getColumnIndex(crossVariable);

		ArrayList<String> vec = new ArrayList<String>();

		vec.add(columnIndexOfGrp + "");
		vec.add(columnIndexOfIdVar + "");

		ArrayList<String> idVariables = tableWizardinputs.getIdVariables();

		int[] columnIndices = tempImpl.getColumnIndices(idVariables);

		for (int i = 0; i < columnIndices.length; i++) {
			vec.add(columnIndices[i] + "");
		}

		/*
		 * Sort the data on the basis of group variable first followed by cross
		 * variable.
		 */
		String[][] sortedData = TMMultiLevelSort.sortData(inputMatrix, vec);

		String[][] columnToAppend = new String[inputMatrix.length][1];

		Vector ulForGVsCombn = new Vector();

		for (int i = 0; i < inputMatrix.length; i++) {

			String tempStr = new String();

			tempStr = inputMatrix[i][columnIndexOfGrp];
			tempStr += inputMatrix[i][columnIndexOfIdVar];

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

				P2.println("\n\n\nlevel details.." + level);

				tempImpl.printMatrix("levelData for-->" + level, levelData,
						levelData.length, levelData[0].length);

				double[][] varData = tempImpl.pickColumnData(ordVariableName, levelData);
				double[][] timeData = tempImpl.pickColumnData(crossVariable,
						levelData);

				tempImpl.printMatrix("Time Data for-->" + level, timeData,
						timeData.length, timeData[0].length);
				tempImpl.printMatrix("ordinary variable Data for-->" + level, varData,
						varData.length, varData[0].length);

				P2.println("ordVariableName.." + ordVariableName);

				double[] singVarData = tempImpl.convDblDimToSinDim(varData);
				// double x =
				// DDUtils.confIntLowerVariance95PerCent(singVarData);
				double x = DDUtils.confIntUpperVariance95PerCent(singVarData);

				System.out.println("chi squared distribution table value" + x);

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
		ArrayList<String> idVariables = tableWizardinputs.getIdVariables();
		ArrayList<String> crossVariables = tableWizardinputs
				.getCrossVariables();

		for (int i = 0; i < grpVariables.size(); i++) {

			String grpVarName = grpVariables.get(i);
			for (int j = 0; j < idVariables.size(); j++) {

				String idVarName = idVariables.get(j);

				for (int k = 0; k < crossVariables.size(); k++) {
					String crossVarName = crossVariables.get(k);
					prepareTemplate8ForGrpCvCombn(grpVarName, idVarName,
							crossVarName);
				}

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
		Template8Impl ti8 = new Template8Impl(ti);

		/*
		 * Table Wizard Input Options
		 */

		ArrayList<String> groupVariables = new ArrayList<String>();
		ArrayList<String> idVariables = new ArrayList<String>();
		ArrayList<String> crossVariables = new ArrayList<String>();
		ArrayList<String> ordinaryVariables = new ArrayList<String>();

		groupVariables.add("Formulation");
		idVariables.add("Subject_ID");
		crossVariables.add("Time");

		// ordinaryVariables.add("BP");
		ordinaryVariables.add("Conc");

		int templateType = 1;

		ti8.setTabWizIpOptions(groupVariables, idVariables, crossVariables,
				ordinaryVariables, templateType);

		/*
		 * Table Wizard Output Options
		 */

		Hashtable hTableOpOptions = new Hashtable();
		TableWizardOpMetricOptions opMetListing = new TableWizardOpMetricOptions(
				hTableOpOptions);
		ti8.setTabWizOpOptions(opMetListing);
		ti8.prepareTemplate();
	}

}
