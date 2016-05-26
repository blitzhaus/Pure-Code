package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ChooseParameterComputationClass {

	NCA ncaInst;

	PlasmaPOSerialNoTauParamCal inst;
	PlasmaPOSerialAllHaveTauParamCal inst1;
	PlasmaSparsePONoTauParamCal inst2; 
	PlasmaSparsePOAllHaveTauParamCal inst3;
	SerialIvPlasmaSingleDoseParametersCal inst4;
	PlasmaSerialIVAllProfilesHasTtauParamCal inst5;
	PlasmaSparseIVNoTau inst6;
	PlasmaIVSparseAllHaveTAU inst7;
	PlasmaInfusionSerialNoTauParamCal inst8;
	PlasmaInfusionSerialAllHaveTau inst9;
	PlasmaInfusionSparseNoTau inst10;
	PlasmaInfusionSparseAllHaveTau inst11;
	UrineSerialIVPOnfusionParametersCal inst12;
	UrineSparseIVPOInfusionParametersCal inst13;
	
	public void chooseRequiredCombination(int currentProfileNumber) throws RowsExceededException,
			WriteException, BiffException, IOException {
		ncaInst = NCA.creaetNCAInst();

		if (ncaInst.modelType == 0) {
			if (ncaInst.rootOfAdminForCurrentProfile == 0) {

				if (ncaInst.ifserial_sampling == true) {
					if (ncaInst.ifSteadyState == 0) {

						PlasmaPOSerialNoTauParamCal inst = new PlasmaPOSerialNoTauParamCal();
						inst.calculateParameters(currentProfileNumber);
					} else {
						PlasmaPOSerialAllHaveTauParamCal inst = new PlasmaPOSerialAllHaveTauParamCal();
						inst.calculateParameters(currentProfileNumber);
					}
				} else {
					if (ncaInst.ifSteadyState == 0) {

						PlasmaSparsePONoTauParamCal inst = new PlasmaSparsePONoTauParamCal();
						inst.calculateParameters(currentProfileNumber);
					} else {
						PlasmaSparsePOAllHaveTauParamCal inst = new PlasmaSparsePOAllHaveTauParamCal();
						inst.calculateParameters(currentProfileNumber);
					}
				}

			} else if (ncaInst.rootOfAdminForCurrentProfile == 1) {
				if (ncaInst.ifserial_sampling == true) {
					if (ncaInst.ifSteadyState == 0) {

						SerialIvPlasmaSingleDoseParametersCal inst = new SerialIvPlasmaSingleDoseParametersCal();
						inst.calculateParameters(currentProfileNumber);
					} else {
						PlasmaSerialIVAllProfilesHasTtauParamCal inst = new PlasmaSerialIVAllProfilesHasTtauParamCal();
						inst.calculateParameters(currentProfileNumber);
					}
				} else {
					if (ncaInst.ifSteadyState == 0) {

						PlasmaSparseIVNoTau inst = new PlasmaSparseIVNoTau();
						inst.calculateParameters(currentProfileNumber);
					} else {
						PlasmaIVSparseAllHaveTAU inst = new PlasmaIVSparseAllHaveTAU();
						inst.calculateParameters(currentProfileNumber);
					}
				}

			} else {
				if (ncaInst.ifserial_sampling == true) {
					if (ncaInst.ifSteadyState == 0) {

						PlasmaInfusionSerialNoTauParamCal inst = new PlasmaInfusionSerialNoTauParamCal();
						inst.calculateParameters(currentProfileNumber);
					} else {
						PlasmaInfusionSerialAllHaveTau inst = new PlasmaInfusionSerialAllHaveTau();
						inst.calculateParameters(currentProfileNumber);
					}
				} else {
					if (ncaInst.ifSteadyState == 0) {

						PlasmaInfusionSparseNoTau inst = new PlasmaInfusionSparseNoTau();
						inst.calculateParameters(currentProfileNumber);
					} else {
						PlasmaInfusionSparseAllHaveTau inst = new PlasmaInfusionSparseAllHaveTau();
						inst.calculateParameters(currentProfileNumber);
					}
				}

			}

		} else if (ncaInst.modelType == 1) {
			if (ncaInst.ifserial_sampling == true) {
				UrineSerialIVPOnfusionParametersCal inst = new UrineSerialIVPOnfusionParametersCal();
				inst.calculateParameters(currentProfileNumber);

			} else {
				UrineSparseIVPOInfusionParametersCal inst = new UrineSparseIVPOInfusionParametersCal();
				inst.calculateParameters(currentProfileNumber);
			}

		} else {

		}

	}
	
	
	
	
}
