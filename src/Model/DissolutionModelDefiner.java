package Model;

import java.io.File;
import java.io.IOException;
import java.sql.Time;

import view.ProcessingInputsInfo;

import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DissolutionModelDefiner {

	
	ProcessingInputsInfo procInputInst;

	public double evaluateModelFunction(double[] param, double time) {
		
		procInputInst = PkPdInfo.createPKPDInstance().copyProcessingInput();
		
		double value = 0;
		
		if(procInputInst.getInVitroModelInputInst().getIfHillModel() == 1)
		{
			value = hillRelease(param, time);
		}else if(procInputInst.getInVitroModelInputInst().getIfWeibulModel() == 1)
		{
			value = weibullWRelease(param, time);
		}else if(procInputInst.getInVitroModelInputInst().getIfDoubleWeibulModel() == 1)
		{
			value = DoubleweibullWRelease(param, time);
		}else if(procInputInst.getInVitroModelInputInst().getIfMakoidBanakarModel() == 1)
		{
			value = makoiBanakarRelease(param, time);
		}
		
		

		return value;

	}

	private double kpRelease(double[] param, double t) {

		double intercept = param[0];
		double kpConstant = param[1];
		double n = param[1];

		double value = intercept + (kpConstant * Math.pow((t), n));
		;

		return value;

	}

	private double hpRelease(double[] param, double t) {

		double Fmax = param[0];
		double hpParmeter = param[1];
		double n = param[2];
		double intercept = param[3];
		double Tlag = param[4];
		double value = intercept
				+ ((Fmax - intercept) * (1 - (Math.pow(
						(1 - (hpParmeter * (t))), n))));

		return value;

	}

	private double DoubleweibullWRelease(double[] param, double t) {

		double F = param[0];
		double Finf = param[1];
		double mdt1 = param[2];
		double b1 = param[3];

		double mdt2 = param[4];
		double b2 = param[5];

		double intercept = param[6];
		double Tlag = param[7];
		double T = t - Tlag;

		if (T < 0)
			T = 0;

		/*double value = ((F * ((Fmax) * (1 - Math.pow(2.718, -(Math.pow((t / a),
				b)))))) + ((1 - F) * ((Fmax) * (1 - Math.pow(2.718, -(Math.pow(
				(t / a1), b1)))))));

		 double value = (F*((Fmax)*(1-Math.pow(2.718, -(Math.pow((T/a),
		 b))))))+((1-F)*((Fmax)*(1-Math.pow(2.718, -(Math.pow((T/a1),
		 b1))))));
		 double value = intercept+(F*((Fmax-intercept)*(1-Math.pow(2.718,
		 -(Math.pow((t/a), b))))))+((1-F)*((Fmax-intercept)*(1-Math.pow(2.718,
		 -(Math.pow((t/a1), b1))))));*/
		 double value = intercept+((F*((Finf-intercept)*(1 - Math.exp(-(Math.pow((t / mdt1), b1))))))+((1-F)*((Finf-intercept)*(1-Math.pow(2.718,
		 -(Math.pow((T/mdt2), b2)))))));

		return value;

	}

	private double makoiBanakarRelease(double[] param, double t) {

		double Tmax = param[0];
		double b = param[1];
		double Fmax = param[2];
		double value;
		double intercept = param[3];
		double Tlag = param[4];

		double T = t - Tlag;

		if (T < 0)
			T = 0;

		if (t < Tmax) {
			value = (((Fmax) * (Math.pow(((t) / Tmax), b)) * (Math.pow(Math.E,
					(1 - ((t) / Tmax))))));
		} else
			value = Fmax;

		if (t < Tmax) {
			value = (((Fmax) * (Math.pow(((T) / Tmax), b)) * (Math.pow(Math.E,
					(1 - ((T) / Tmax))))));
		} else
			value = Fmax;

		if (t < Tmax) {
			value = (intercept + ((Fmax - intercept)
					* (Math.pow(((t) / Tmax), b)) * (Math.pow(Math.E,
					(1 - ((t) / Tmax))))));
		} else
			value = Fmax;

		if (t < Tmax) {
			value = (intercept + ((Fmax - intercept)
					* (Math.pow(((T) / Tmax), b)) * (Math.pow(Math.E,
					(1 - ((T) / Tmax))))));
		} else
			value = Fmax;

		return value;

	}

	private double weibullWRelease(double[] param, double t) {

		double MDT = param[0];
		double b = param[1];
		double Finf = param[2];
		double intercept = param[3];
		double Tlag = param[4];

		double T = t - Tlag;
		if (T < 0)

			T = 0;
		double f_lag = (Finf);
		double t_mdt = (T / MDT);
		double ux = Math.pow(t_mdt, b);
		double expon = 1 - Math.pow(2.718, -(ux));
		//double value = ((Fmax) * (1 - Math.pow(2.718, -(Math.pow((t / a), b)))));
		//double value = (((Fmax) * (1 - Math.pow(2.718, -(Math.pow((T / a), b))))));
		double value = intercept + ((Finf - intercept) * (1 - Math.exp(-(Math.pow((t / MDT), b)))));
		//double value = ((intercept) + (f_lag * expon));
		return value;

	}

	private double hillRelease(double[] param, double t) {

		double mdt = param[0];
		double b = param[1];
		double Finf = param[2];
		double intercept = param[3];
		double Tlag = param[4];
		double T = t - Tlag;

		if (T < 0)

			T = 0;

		/*double value = (((Fmax) * (Math.pow(t, b))) / ((Math.pow(t, b)) + (Math
				.pow(a, b))));
		double value = ((((Fmax) * (Math.pow((T), b))) / ((Math.pow((T), b)) + Math
				.pow(a, b))));
		double value = (intercept + (((Fmax - intercept) * (Math.pow((t), b))) / ((Math
				.pow((t), b)) + Math.pow(a, b))));*/

		double value = intercept
				+ (((Finf - intercept) * (Math.pow((T), b))) / ((Math.pow((T),
						b)) + (Math.pow(mdt, b))));

		return value;

	}
	/*
	 * private void firstOrderRelease() { for (int i=0;i<time.length;i++){ f[i]=
	 * Fmax*(1-(Math.pow(Math.E, -(firstOrderRateConstant*time[i])))); } }
	 * 
	 * private void hixonCrowellDiss() { for (int i = 0; i < time.length; i++) {
	 * f[i] = (1-(Math.pow((1-(hopfenbergConstant * (time[i]))), 3)));
	 * 
	 * 
	 * } }
	 * 
	 * private void higuchiDiss() { for (int i = 0; i < time.length; i++) { f[i]
	 * = higuchiConstant * Math.pow((time[i]), 0.5); } }
	 * 
	 * private void korsmeyerPeppasDiss() { for (int i = 0; i < time.length;
	 * i++) { f[i] = koresPreppasConstant * Math.pow((time[i]),
	 * koresPreppasPowerConstant); }
	 * 
	 * 
	 * }
	 * 
	 * private static void peppasShallinDiss() { for (int i = 0; i <
	 * time.length; i++) { f[i] = (peppasShalinFirstConstant *
	 * Math.pow((time[i]), peppasShalinFirstPowerConstant)) +
	 * (peppasShalinsecondConstant * Math.pow((time[i]), (2 *
	 * peppasShalinsecondPowerConstant))); } }
	 * 
	 * private static void hopfenbergDiss() { for (int i = 0; i < time.length;
	 * i++) { f[i] = (1-(Math.pow((1-(hopfenbergConstant * (time[i]))),
	 * hopfenbergPowerConstant)));
	 * 
	 * 
	 * } }
	 * 
	 * private static void quadraticDiss() { for (int i = 0; i < time.length;
	 * i++) { f[i] = (quadraticFirstConstant * (time[i]))
	 * 
	 * + (quadraticSecondConstant * (time[i] )); } }
	 * 
	 * private static void gompertz() {
	 * 
	 * for (int i = 0; i < time.length; i++) { f[i] = (quadraticFirstConstant *
	 * (time[i]))
	 * 
	 * + (quadraticSecondConstant * (time[i] )); } }
	 * 
	 * private static void logisticDiss() {
	 * 
	 * }
	 */

}
