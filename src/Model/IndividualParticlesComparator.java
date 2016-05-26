package Model;

import java.util.Comparator;

class IndividualParticlesComparator implements Comparator {
	public int compare(Object ind1, Object ind2) {
		double distance1 = ((IndividualParticle) ind1).getFitnessValue();
		double distance2 = ((IndividualParticle) ind2).getFitnessValue();

		if (distance1 < distance2)
			return 1;
		else if (distance1 > distance2)
			return -1;
		else
			return 0;
	}

}
