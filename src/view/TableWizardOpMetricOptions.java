package view;

import java.io.Serializable;
import java.util.Hashtable;

import Common.Comparator;
import Common.MyComparator;

public class TableWizardOpMetricOptions implements Serializable{
	
	Hashtable<String, Boolean> statMetricsListing = new Hashtable<String,Boolean>();

	public Hashtable<String, Boolean> getStatMetricsListing() {
		return statMetricsListing;
	}

	transient Comparator comp = MyComparator.createMyCompInst();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TableWizardOpMetricOptions other = (TableWizardOpMetricOptions) obj;
		if (statMetricsListing == null) {
			if (other.statMetricsListing != null)
				return false;
		} else if (!statMetricsListing.equals(other.statMetricsListing))
			return false;
		return true;
	}

	public void setStatMetricsListing(Hashtable<String, Boolean> statMetricsListing) {
		this.statMetricsListing = statMetricsListing;
	}
	
	public boolean ifComputableMetric(String metricLabel)
	{
		Boolean boolMetric = statMetricsListing.get(metricLabel);
		return boolMetric.booleanValue();
	}

	public TableWizardOpMetricOptions(Hashtable<String, Boolean> statMetricsListing) {
		this.statMetricsListing = statMetricsListing;
	}
}
