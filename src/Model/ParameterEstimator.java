package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ParameterEstimator {

	GaussNewton gnInstance;
	NelderMead nmInstance;
	PkPdInfo pkpdInst;
	ListOfDataStructures dataStructInst;

	public void estimateModelParameter(int profileNo) throws RowsExceededException,
			WriteException, BiffException, IOException {
		pkpdInst = PkPdInfo.createPKPDInstance();
		dataStructInst = ListOfDataStructures.createListOfDataStructInstance();


		gnInstance = new GaussNewton();
		nmInstance = new NelderMead();

		if (pkpdInst.analysisType.equals("pk")) {

			if (pkpdInst.methodNo == 1) {
				pkpdInst.parameter = gnInstance
						.gaussNewtonLM(
								pkpdInst.modelNumber,
								pkpdInst.parameter,
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
			}

			else if (pkpdInst.methodNo == 3)
				pkpdInst.parameter = nmInstance
						.nMProcess(
								pkpdInst.parameter,
								pkpdInst.X,
								pkpdInst.Y,
								pkpdInst.extraData,
								pkpdInst.row,
								pkpdInst.delta,
								pkpdInst.initSimplex,
								PkParamEstimator
										.createPkParamEstimateInstance().infusionTime,
								PkParamEstimator
										.createPkParamEstimateInstance().dose,
								PkParamEstimator
										.createPkParamEstimateInstance().dosingTime, profileNo);

			else if (pkpdInst.methodNo == 2) {
				pkpdInst.parameter = gnInstance
						.GaussNewtonLH(
								pkpdInst.modelNumber,
								pkpdInst.parameter,
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

			}

		} else if (pkpdInst.analysisType.equals("pd")){
			
			
			if (pkpdInst.methodNo == 1) {
				pkpdInst.parameter = gnInstance.gaussNewtonLM(dataStructInst.modelNumber,
						pkpdInst.parameter, pkpdInst.X, pkpdInst.Y,
						pkpdInst.extraData, pkpdInst.row, pkpdInst.delta,
						dataStructInst.infusionTime, dataStructInst.dose, dataStructInst.dosingTime,
						pkpdInst.numberOfSortVar,
						pkpdInst.dataSortVariables, pkpdInst.noOfParam,
						pkpdInst.pdIncrement,profileNo);
			}

			else if (pkpdInst.methodNo == 2)
				pkpdInst.parameter = nmInstance.nMProcess(pkpdInst.parameter,
						pkpdInst.X, pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta, pkpdInst.initSimplex, dataStructInst.infusionTime,
						dataStructInst.dose, dataStructInst.dosingTime, profileNo);

			else if (pkpdInst.methodNo == 3) {
				pkpdInst.parameter = gnInstance.GaussNewtonLH(dataStructInst.modelNumber,
						pkpdInst.parameter, pkpdInst.X, pkpdInst.Y,
						pkpdInst.extraData, pkpdInst.row, pkpdInst.delta,
						dataStructInst.infusionTime, dataStructInst.dose, dataStructInst.dosingTime,
						pkpdInst.numberOfSortVar, pkpdInst.dataSortVariables, pkpdInst.noOfParam,
						pkpdInst.pdIncrement,profileNo);

			}

		}else if(pkpdInst.analysisType.equals("mm"))
		{
			if (pkpdInst.methodNo == 1) {
				pkpdInst.parameter = gnInstance.gaussNewtonLM(
						dataStructInst.modelNumber, pkpdInst.parameter, pkpdInst.X,
						pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta, dataStructInst.infusionTime,
						dataStructInst.dose, dataStructInst.dosingTime,
						pkpdInst.numberOfSortVar, pkpdInst.dataSortVariables,
						pkpdInst.noOfParam, pkpdInst.pdIncrement,profileNo);
			}

			else if (pkpdInst.methodNo == 2)
				pkpdInst.parameter = nmInstance.nMProcess(pkpdInst.parameter,
						pkpdInst.X, pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta, pkpdInst.initSimplex,
						dataStructInst.infusionTime, dataStructInst.dose,
						dataStructInst.dosingTime, profileNo);

			else if (pkpdInst.methodNo == 3) {
				pkpdInst.parameter = gnInstance.GaussNewtonLH(
						dataStructInst.modelNumber, pkpdInst.parameter, pkpdInst.X,
						pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta, dataStructInst.infusionTime,
						dataStructInst.dose, dataStructInst.dosingTime,
						pkpdInst.numberOfSortVar, pkpdInst.dataSortVariables,
						pkpdInst.noOfParam, pkpdInst.pdIncrement,profileNo);

			}
		}else if(pkpdInst.analysisType.equals("irm"))
		{
			if (pkpdInst.methodNo == 1) {
				pkpdInst.parameter = gnInstance.gaussNewtonLM(
						dataStructInst.modelNumber, pkpdInst.parameter, pkpdInst.X,
						pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta, dataStructInst.infusionTime,
						dataStructInst.dose, dataStructInst.dosingTime,
						pkpdInst.numberOfSortVar, pkpdInst.dataSortVariables,
						pkpdInst.noOfParam, pkpdInst.pdIncrement,profileNo);
			}

			else if (pkpdInst.methodNo == 2)
				pkpdInst.parameter = nmInstance.nMProcess(pkpdInst.parameter,
						pkpdInst.X, pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta, pkpdInst.initSimplex,
						dataStructInst.infusionTime, dataStructInst.dose,
						dataStructInst.dosingTime, profileNo);

			else if (pkpdInst.methodNo == 3) {
				pkpdInst.parameter = gnInstance.GaussNewtonLH(
						dataStructInst.modelNumber, pkpdInst.parameter, pkpdInst.X,
						pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta, dataStructInst.infusionTime,
						dataStructInst.dose, dataStructInst.dosingTime,
						pkpdInst.numberOfSortVar, pkpdInst.dataSortVariables,
						pkpdInst.noOfParam, pkpdInst.pdIncrement,profileNo);

			}

			
		}else if(pkpdInst.analysisType.equals("pkpdlink"))
		{
			if (pkpdInst.methodNo == 1) {
				pkpdInst.parameter = gnInstance.gaussNewtonLM(
						dataStructInst.modelNumber, pkpdInst.parameter, pkpdInst.X,
						pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta, dataStructInst.infusionTime,
						dataStructInst.dose, dataStructInst.dosingTime,
						pkpdInst.numberOfSortVar, pkpdInst.dataSortVariables,
						pkpdInst.noOfParam, pkpdInst.pdIncrement,profileNo);
			}

			else if (pkpdInst.methodNo == 2)
				pkpdInst.parameter = nmInstance.nMProcess(pkpdInst.parameter,
						pkpdInst.X, pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta, pkpdInst.initSimplex,
						dataStructInst.infusionTime, dataStructInst.dose,
						dataStructInst.dosingTime, profileNo);

			else if (pkpdInst.methodNo == 3) {
				pkpdInst.parameter = gnInstance.GaussNewtonLH(
						dataStructInst.modelNumber, pkpdInst.parameter, pkpdInst.X,
						pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta, dataStructInst.infusionTime,
						dataStructInst.dose, dataStructInst.dosingTime,
						pkpdInst.numberOfSortVar, pkpdInst.dataSortVariables,
						pkpdInst.noOfParam, pkpdInst.pdIncrement,profileNo);

			}

		}else if(pkpdInst.analysisType.equals("invitro"))
		{
			if (pkpdInst.methodNo == 1) {
				pkpdInst.parameter = gnInstance.gaussNewtonLM(
						dataStructInst.modelNumber, pkpdInst.parameter, pkpdInst.X,
						pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta, dataStructInst.infusionTime,
						dataStructInst.dose, dataStructInst.dosingTime,
						pkpdInst.numberOfSortVar, pkpdInst.dataSortVariables,
						pkpdInst.noOfParam, pkpdInst.pdIncrement,profileNo);
			}

			else if (pkpdInst.methodNo == 2)
				pkpdInst.parameter = nmInstance.nMProcess(pkpdInst.parameter,
						pkpdInst.X, pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta, pkpdInst.initSimplex,
						dataStructInst.infusionTime, dataStructInst.dose,
						dataStructInst.dosingTime, profileNo);

			else if (pkpdInst.methodNo == 3) {
				pkpdInst.parameter = gnInstance.GaussNewtonLH(
						dataStructInst.modelNumber, pkpdInst.parameter, pkpdInst.X,
						pkpdInst.Y, pkpdInst.extraData, pkpdInst.row,
						pkpdInst.delta, dataStructInst.infusionTime,
						dataStructInst.dose, dataStructInst.dosingTime,
						pkpdInst.numberOfSortVar, pkpdInst.dataSortVariables,
						pkpdInst.noOfParam, pkpdInst.pdIncrement,profileNo);

			}

		}
		
		
	}

}
