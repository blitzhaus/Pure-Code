package Model;

import java.util.Hashtable;

public class TableWizardOpMetricOptions {
	
	Hashtable<String, Boolean> statMetricsListing = new Hashtable<String,Boolean>();

	public Hashtable<String, Boolean> getStatMetricsListing() {
		return statMetricsListing;
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
