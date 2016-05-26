package Model;

import java.util.Comparator;

public class IndividualParticle {
	double fitnessValue;
	int noOfParams;
	double[] position = new double[noOfParams];
	double[] pbParameters = new double[noOfParams];
	double[] velocity = new double[noOfParams];

	public double[] getVelocity() {
		return velocity;
	}

	public void setVelocity(double[] velocity) {
		this.velocity = velocity;
	}

	public double[] getPbParameters() {
		return pbParameters;
	}

	public void setPbParameters(double[] pbParameters) {
		this.pbParameters = pbParameters;
	}

	public double getFitnessValue() {
		return fitnessValue;
	}

	public void setFitnessValue(double fitnessValue) {
		this.fitnessValue = fitnessValue;
	}

	public int getNoOfParams() {
		return position.length;
	}

	public void setNoOfParams(int noOfParams) {
		this.noOfParams = noOfParams;
	}

	public double[] getParameters() {
		return position;
	}

	public void setParameters(double[] params) {
		noOfParams = params.length;
		position = new double[noOfParams];
		position = params;
	}

	public IndividualParticle() {
		super();
		this.fitnessValue = 0.0;
		noOfParams = 0;
		position = new double[noOfParams];
		pbParameters = new double[noOfParams];
		velocity = new double[noOfParams];
	}

	public IndividualParticle(double fitnessValue, double[] params,
			double[] pBestParams, double[] vel) {
		super();
		this.fitnessValue = fitnessValue;
		noOfParams = params.length;
		position = new double[noOfParams];
		position = params;
		pbParameters = pBestParams;
		velocity = vel;
	}

	public void applyConstraints(double[] minPosition, double[] maxPosition,
			double[] minVelocity, double[] maxVelocity) {
		// ---
		// Every constraint is set? (do all of them it one loop)
		// ---
		if ((minPosition != null) && (maxPosition != null)
				&& (minVelocity != null) && (maxVelocity != null))
			for (int i = 0; i < position.length; i++) {
				if (!Double.isNaN(minPosition[i]))
					position[i] = (minPosition[i] >= position[i] ? minPosition[i]
							: position[i]);
				if (!Double.isNaN(maxPosition[i]))
					position[i] = (maxPosition[i] <= position[i] ? maxPosition[i]
							: position[i]);
				if (!Double.isNaN(minVelocity[i]))
					velocity[i] = (minVelocity[i] >= velocity[i] ? minVelocity[i]
							: velocity[i]);
				if (!Double.isNaN(maxVelocity[i]))
					velocity[i] = (maxVelocity[i] <= velocity[i] ? maxVelocity[i]
							: velocity[i]);
			}
		else {
			// ---
			// Position constraints are set? (do both of them in the same loop)
			// ---
			if ((minPosition != null) && (maxPosition != null))
				for (int i = 0; i < position.length; i++) {
					if (!Double.isNaN(minPosition[i]))
						position[i] = (minPosition[i] >= position[i] ? minPosition[i]
								: position[i]);
					if (!Double.isNaN(maxPosition[i]))
						position[i] = (maxPosition[i] <= position[i] ? maxPosition[i]
								: position[i]);
				}
			else {
				// ---
				// Do it individually
				// ---
				if (minPosition != null)
					for (int i = 0; i < position.length; i++)
						if (!Double.isNaN(minPosition[i]))
							position[i] = (minPosition[i] >= position[i] ? minPosition[i]
									: position[i]);
				if (maxPosition != null)
					for (int i = 0; i < position.length; i++)
						if (!Double.isNaN(maxPosition[i]))
							position[i] = (maxPosition[i] <= position[i] ? maxPosition[i]
									: position[i]);
			}
			// ---
			// Velocity constraints are set? (do both of them in the same loop)
			// ---
			if ((minVelocity != null) && (maxVelocity != null))
				for (int i = 0; i < velocity.length; i++) {
					if (!Double.isNaN(minVelocity[i]))
						velocity[i] = (minVelocity[i] >= velocity[i] ? minVelocity[i]
								: velocity[i]);
					if (!Double.isNaN(maxVelocity[i]))
						velocity[i] = (maxVelocity[i] <= velocity[i] ? maxVelocity[i]
								: velocity[i]);
				}
			else {
				// ---
				// Do it individually
				// ---
				if (minVelocity != null)
					for (int i = 0; i < velocity.length; i++)
						if (!Double.isNaN(minVelocity[i]))
							velocity[i] = (minVelocity[i] >= velocity[i] ? minVelocity[i]
									: velocity[i]);
				if (maxVelocity != null)
					for (int i = 0; i < velocity.length; i++)
						if (!Double.isNaN(maxVelocity[i]))
							velocity[i] = (maxVelocity[i] <= velocity[i] ? maxVelocity[i]
									: velocity[i]);
			}
		}
	}

	public String toString() {
		String str = "[";
		for (int i = 0; i < noOfParams; i++) {
			str += position[i] + ",";
		}
		str += "]";
		str += "\t[" + fitnessValue + "]";
		str += "]";
		return str;
	}
}
