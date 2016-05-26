package Model;

import java.io.File;
import java.io.IOException;
import java.sql.Time;

import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DissolutionFunction {

	double Fmax;
	double zeroOrderRateConstant;
	double firstOrderRateConstant;
	double higuchiConstant;
	double koresPreppasConstant;
	double koresPreppasPowerConstant;
	double peppasShalinFirstConstant;
	double peppasShalinFirstPowerConstant;
	double peppasShalinsecondConstant;
	double peppasShalinsecondPowerConstant;
	double hopfenbergPowerConstant;
	double hopfenbergConstant;
	double quadraticFirstConstant;
	double quadraticSecondConstant;

	public double chooseFunction(double[] param, double time) {
		double value = 0;
		value = DoubleweibullWRelease(param, time);

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
		double Fmax = param[1];
		double a = param[2];
		double b = param[3];

		double a1 = param[4];
		double b1 = param[5];

		double intercept = param[6];
		double Tlag = param[7];
		double T = t - Tlag;

		if (T < 0)
			T = 0;

		double value = ((F * ((Fmax) * (1 - Math.pow(2.718, -(Math.pow((t / a),
				b)))))) + ((1 - F) * ((Fmax) * (1 - Math.pow(2.718, -(Math.pow(
				(t / a1), b1)))))));

		// double value = (F*((Fmax)*(1-Math.pow(2.718, -(Math.pow((T/a),
		// b))))))+((1-F)*((Fmax)*(1-Math.pow(2.718, -(Math.pow((T/a1),
		// b1))))));
		// double value = intercept+(F*((Fmax-intercept)*(1-Math.pow(2.718,
		// -(Math.pow((t/a), b))))))+((1-F)*((Fmax-intercept)*(1-Math.pow(2.718,
		// -(Math.pow((t/a1), b1))))));
		// double value = intercept+((F*((Fmax-intercept)*(1-Math.pow(2.718,
		// -(Math.pow((T/a), b))))))+((1-F)*((Fmax-intercept)*(1-Math.pow(2.718,
		// -(Math.pow((T/a1), b1)))))));

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

		double a = param[0];
		double b = param[1];
		double Fmax = param[2];
		double intercept = param[3];
		double Tlag = param[4];

		double T = t - Tlag;
		if (T < 0)

			T = 0;
		double f_lag = (Fmax);
		double t_mdt = (T / a);
		double ux = Math.pow(t_mdt, b);
		double expon = 1 - Math.pow(2.718, -(ux));
		double value = ((Fmax) * (1 - Math.pow(2.718, -(Math.pow((t / a), b)))));
		/*double value = (((Fmax) * (1 - Math.pow(2.718, -(Math.pow((T / a), b))))));
		double value = (intercept + ((Fmax - intercept) * (1 - Math.pow(2.718,
				-(Math.pow((t / a), b))))));
		double value = ((intercept) + (f_lag * expon));*/
		return value;

	}

	private double hillRelease(double[] param, double t) {

		double a = param[0];
		double b = param[1];
		double Fmax = param[2];
		double intercept = param[3];
		double Tlag = param[4];
		double T = t - Tlag;

		if (T < 0)

			T = 0;

		double value = (((Fmax) * (Math.pow(t, b))) / ((Math.pow(t, b)) + (Math
				.pow(a, b))));
		/*double value = ((((Fmax) * (Math.pow((T), b))) / ((Math.pow((T), b)) + Math
				.pow(a, b))));
		double value = (intercept + (((Fmax - intercept) * (Math.pow((t), b))) / ((Math
				.pow((t), b)) + Math.pow(a, b))));

		double value = intercept
				+ (((Fmax - intercept) * (Math.pow((T), b))) / ((Math.pow((T),
						b)) + (Math.pow(a, b))));*/

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
