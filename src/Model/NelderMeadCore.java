package Model;

import java.util.Comparator;

import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.direct.DirectSearchOptimizer;

/**
 * This class implements the Nelder-Mead direct search method.
 * 
 * @version $Revision: 811685 $ $Date: 2009-09-05 13:36:48 -0400 (Sat, 05 Sep
 *          2009) $
 * @see MultiDirectional
 * @since 1.2
 */
public class NelderMeadCore extends DirectSearchOptimizer {

	/** Reflection coefficient. */
	private double rho;

	/** Expansion coefficient. */
	private double khi;

	/** Contraction coefficient. */
	private double gamma;

	/** Shrinkage coefficient. */
	private double sigma;

	private RealPointValuePair[] newSimplex;

	/**
	 * Build a Nelder-Mead optimizer with default coefficients.
	 * <p>
	 * The default coefficients are 1.0 for rho, 2.0 for khi and 0.5 for both
	 * gamma and sigma.
	 * </p>
	 */
	public NelderMeadCore() {
		this.rho = 1.0;
		this.khi = 2.0;
		this.gamma = 0.5;
		this.sigma = 0.5;
	}

	public RealPointValuePair[] getNewSimplex() {
		return newSimplex;
	}

	public void setNewCoefficients(final double rho, final double khi,
			final double gamma, final double sigma) {
		this.rho = rho;
		this.khi = khi;
		this.gamma = gamma;
		this.sigma = sigma;
	}

	/**
	 * Build a Nelder-Mead optimizer with specified coefficients.
	 * 
	 * @param rho
	 *            reflection coefficient
	 * @param khi
	 *            expansion coefficient
	 * @param gamma
	 *            contraction coefficient
	 * @param sigma
	 *            shrinkage coefficient
	 */
	public NelderMeadCore(final double rho, final double khi,
			final double gamma, final double sigma) {
		this.rho = rho;
		this.khi = khi;
		this.gamma = gamma;
		this.sigma = sigma;
	}

	/** {@inheritDoc} */
	@Override
	protected void iterateSimplex(
			final Comparator<RealPointValuePair> comparator)
			throws FunctionEvaluationException, OptimizationException {

		incrementIterationsCounter();

		// the simplex has n+1 point if dimension is n
		final int n = simplex.length - 1;
		newSimplex = simplex;

		// interesting values
		final RealPointValuePair best = simplex[0];
		final RealPointValuePair secondBest = simplex[n - 1];
		final RealPointValuePair worst = simplex[n];
		final double[] xWorst = worst.getPointRef();

		// compute the centroid of the best vertices
		// (dismissing the worst point at index n)
		final double[] centroid = new double[n];
		for (int i = 0; i < n; ++i) {
			final double[] x = simplex[i].getPointRef();
			for (int j = 0; j < n; ++j) {
				centroid[j] += x[j];
			}
		}
		final double scaling = 1.0 / n;
		for (int j = 0; j < n; ++j) {
			centroid[j] *= scaling;
		}

		// compute the reflection point
		final double[] xR = new double[n];
		for (int j = 0; j < n; ++j) {
			xR[j] = centroid[j] + rho * (centroid[j] - xWorst[j]);
		}
		final RealPointValuePair reflected = new RealPointValuePair(xR,
				evaluate(xR), false);

		if ((comparator.compare(best, reflected) <= 0)
				&& (comparator.compare(reflected, secondBest) < 0)) {

			// accept the reflected point
			replaceWorstPoint(reflected, comparator);

		} else if (comparator.compare(reflected, best) < 0) {

			// compute the expansion point
			final double[] xE = new double[n];
			for (int j = 0; j < n; ++j) {
				xE[j] = centroid[j] + khi * (xR[j] - centroid[j]);
			}
			final RealPointValuePair expanded = new RealPointValuePair(xE,
					evaluate(xE), false);

			if (comparator.compare(expanded, reflected) < 0) {
				// accept the expansion point
				replaceWorstPoint(expanded, comparator);
			} else {
				// accept the reflected point
				replaceWorstPoint(reflected, comparator);
			}

		} else {

			if (comparator.compare(reflected, worst) < 0) {

				// perform an outside contraction
				final double[] xC = new double[n];
				for (int j = 0; j < n; ++j) {
					xC[j] = centroid[j] + gamma * (xR[j] - centroid[j]);
				}
				final RealPointValuePair outContracted = new RealPointValuePair(
						xC, evaluate(xC), false);

				if (comparator.compare(outContracted, reflected) <= 0) {
					// accept the contraction point
					replaceWorstPoint(outContracted, comparator);
					return;
				}

			} else {

				// perform an inside contraction
				final double[] xC = new double[n];
				for (int j = 0; j < n; ++j) {
					xC[j] = centroid[j] - gamma * (centroid[j] - xWorst[j]);
				}
				final RealPointValuePair inContracted = new RealPointValuePair(
						xC, evaluate(xC), false);

				if (comparator.compare(inContracted, worst) < 0) {
					// accept the contraction point
					replaceWorstPoint(inContracted, comparator);
					return;
				}

			}

			// perform a shrink
			final double[] xSmallest = simplex[0].getPointRef();
			for (int i = 1; i < simplex.length; ++i) {
				final double[] x = simplex[i].getPoint();
				for (int j = 0; j < n; ++j) {
					x[j] = xSmallest[j] + sigma * (x[j] - xSmallest[j]);
				}
				simplex[i] = new RealPointValuePair(x, Double.NaN, false);
			}
			evaluateSimplex(comparator);

		}

	}

}
