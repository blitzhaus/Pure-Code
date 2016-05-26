package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Hashtable;

import Common.Comparator;
import Common.MyComparator;

/*
 * An array list of this structure is maintained for each composite key
 */

public class Template12Results implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String compKey;
	String[][] levelData;
	
	/*
	 *  Hashtable<String ordVars, Hashtable<String metricName,Double metricValue>> varStatMetrics
	 *  This is a hashtable which pertains to ordinary variables and its associated (metric,stat)
	 *  values.
	*/	
	Hashtable<String, Hashtable<String,Double>> varStatMetrics;
	
	Template12Results()
	{
		
	}
	
/*	public static void main(String[] args) {
		Hashtable<String, Hashtable<String,Double>> var = new Hashtable<String, Hashtable<String,Double>>();
		Hashtable<String, Hashtable<String,Double>> var1 = new Hashtable<String, Hashtable<String,Double>>();
		
		Hashtable<String, Double> varInner = new Hashtable<String, Double>();
		varInner.put("Swift", 3338.0);
		varInner.put("TVS", 3589.0);
		var.put("Ajith@25", varInner);
		
		Hashtable<String, Double> var1Inner = new Hashtable<String, Double>();
		var1Inner.put("Swift", 3338.0);
		var1Inner.put("TVS Apache", 3589.0);
		var1.put("Ajith@25", var1Inner);
		
		Comparator comp = MyComparator.createMyCompInst();
		System.out.println(comp.compareStrHash(var, var1));
		
		
	}*/

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
