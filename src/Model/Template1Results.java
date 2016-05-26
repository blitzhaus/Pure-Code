package Model;

import java.io.Serializable;
import java.util.Hashtable;

/*
 * An array list of this structure is maintained for each composite key
 */

public class Template1Results implements Serializable{
	
	String compKey;
	String[][] levelData;
	
	/*
	 *  Hashtable<String ordVars, Hashtable<String metricName,Double metricValue>> varStatMetrics
	 *  This is a hashtable which pertains to ordinary variables and its associated (metric,stat)
	 *  values.
	*/	
	Hashtable<String, Hashtable<String,Double>> varStatMetrics;
	
	Template1Results()
	{
		
	}

	public String getCompKey() {
		return compKey;
	}

	public void setCompKey(String compKey) {
		this.compKey = compKey;
	}

	public String[][] getLevelData() {
		return levelData;
	}

	public void setLevelData(String[][] levelData) {
		this.levelData = levelData;
	}

	public Hashtable<String, Hashtable<String, Double>> getVarStatMetrics() {
		return varStatMetrics;
	}

	public void setVarStatMetrics(
			Hashtable<String, Hashtable<String, Double>> varStatMetrics) {
		this.varStatMetrics = varStatMetrics;
	}
		
}
