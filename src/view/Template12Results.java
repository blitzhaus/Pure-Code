package view;

import java.util.Arrays;
import java.util.Hashtable;

import Common.Comparator;
import Common.MyComparator;



/*
 * An array list of this structure is maintained for each composite key
 */

public class Template12Results {
	
	String compKey;
	String[][] levelData;
	transient Comparator comp = MyComparator.createMyCompInst();
		
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
	

	@Override
	public boolean equals(Object obj) {
		
		
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Template12Results other = (Template12Results) obj;
		if (compKey == null) {
			if (other.compKey != null)
				return false;
		} else if (!compKey.equals(other.compKey))
			return false;
		if (!Arrays.deepEquals(levelData, other.levelData))
			return false;
		if (varStatMetrics == null) {
			if (other.varStatMetrics != null)
				return false;
		} else if (!comp.compareStrHash(varStatMetrics,(other.varStatMetrics)))
			return false;
		return true;
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
