package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class NMAutomaticRestart {

	NelderMead1 nm1Instance;
	PkPdInfo pkpdInst;
	
	public double[] restartNM(double[] initial, int profileNo) throws RowsExceededException, WriteException, BiffException, IOException
	{
		nm1Instance = NelderMead1.CreateNMInstance();
		pkpdInst = PkPdInfo.createPKPDInstance();
		
		double[] result = nm1Instance
		.NelderMead(
				pkpdInst.modelNumber,
				initial,
				pkpdInst.X,
				pkpdInst.Y,
				pkpdInst.extraData,
				pkpdInst.row,
				pkpdInst.delta,
				PkParamEstimator
						.createPkParamEstimateInstance().infusionTime,
				PkParamEstimator
						.createPkParamEstimateInstance().dose,
				PkParamEstimator
						.createPkParamEstimateInstance().dosingTime,
				pkpdInst.numberOfSortVar,
				pkpdInst.dataSortVariables, pkpdInst.noOfParam,
				pkpdInst.pdIncrement,profileNo);
		return result;
	}
	
}
