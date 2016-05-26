package view;

import Model.Template12Results;

public class TableMavenOutputGeneration {
	
	public static TableMavenOutputGeneration m_tmOpGeneration = null;
	
	public static TableMavenOutputGeneration createTableOutputInst() {
		if (m_tmOpGeneration == null)
		{
			m_tmOpGeneration = new TableMavenOutputGeneration("Just Object creation");
		}
		return m_tmOpGeneration;
	}
	
	TableMavenOutputGeneration(String str)
	{
		
	}

	public void tableMavenOutputGeneration() {
		TableMavenCreateUI tmCreateUI = TableMavenCreateUI.createTableMavenUIInst();
		tmCreateUI.rebuildResultsTab();		
	}

}
