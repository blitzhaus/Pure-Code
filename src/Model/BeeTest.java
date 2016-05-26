package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class BeeTest {
	 BeeColony bee = new BeeColony();
	 
	 PkPdInfo pkPdInfoInst;

	public double[] runBees(double[] upperBound, double[] lowerBound,  double[][] X, double[][] Y, double[][] Extra_DATA, int[] row, double[] delta) throws RowsExceededException, WriteException, BiffException, IOException {
		pkPdInfoInst = PkPdInfo.createPKPDInstance();
		
		bee.loadData();
		int iter = 0;
		int run = 0;
		int j = 0;
		double mean = 0;
		
		double[] lBnd;
		double[] uBnd;
		
		
		lBnd = lowerBound;
		uBnd = upperBound;
		
		bee.D = lowerBound.length;
		
		bee.initialization();
		
		for (run = 0; run < bee.runtime; run++) {
			bee.initial(lBnd, uBnd);
			bee.memorizeBestSource();
			for (iter = 0; iter < bee.maxCycle; iter++) {
				bee.delegateToEmployedBees();
				bee.computeProbs();
				bee.delegateToOnlookerBees();
				bee.memorizeBestSource();
				bee.delegateToScoutBees();
			}
			for (j = 0; j < bee.D; j++) {
				// System.out.println("GlobalParam[%d]: %f\n",j+1,GlobalParams[j]);
				System.out.println("GlobalParam[" + (j + 1) + "]:"
						+ bee.globalParams[j]);
			}
			// System.out.println("%d. run: %e \n",run+1,GlobalMin);
			System.out.println((run + 1) + ".run:" + bee.globalMin);
			bee.globalMins[run] = bee.globalMin;
			mean = mean + bee.globalMin;
		}
		mean = mean / bee.runtime;
		// System.out.println("Means of %d runs: %e\n",runtime,mean);
		System.out.println("Means  of " + bee.runtime + "runs: " + mean);
		
		return  bee.globalParams;

	}

}
