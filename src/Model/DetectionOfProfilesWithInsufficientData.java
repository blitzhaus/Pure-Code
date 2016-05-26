package Model;

public class DetectionOfProfilesWithInsufficientData {
	
	public boolean detectProfileWithInsufficientDataInCA(double[][] X, double[][] Y, int numberOfParameter)
	{
		boolean profileStatus = false;
		int noOfNonZeroConc = 0;
		for(int i=0; i<X.length;i++)
		{
			if(Y[i][0]!= 0 )
				noOfNonZeroConc++;
		}
		
		if(noOfNonZeroConc < 2*numberOfParameter)
			profileStatus = true;
		else
			profileStatus = false;
		return profileStatus;
	}
	
	
	boolean detectProfileWithInsufficientDataInNCA(double[] X, double[] Y, int lambdaZCalcultionMethodNo, 
			double startTime,double endTime,int routeOfAdministration, double infusionLength ,double dosingTime)
 {
		boolean profileStatus = false;
		int noOfNonZeroConc = 0;

		if (lambdaZCalcultionMethodNo == 1 || lambdaZCalcultionMethodNo == 2
				|| lambdaZCalcultionMethodNo == 3) {
			if (routeOfAdministration == 1) {
				double Cmax = maxVal(Y);
				double Tmax = X[findVal(Y, Cmax)];
				for (int i = 0; i < X.length; i++) {
					if (Y[i] != 0 && X[i] > Tmax)
						noOfNonZeroConc++;
				}
			} else if (routeOfAdministration == 2) {
				for (int i = 0; i < X.length; i++) {
					if (Y[i] != 0)
						noOfNonZeroConc++;
				}
			} else if (routeOfAdministration == 3) {
				for (int i = 0; i < X.length; i++) {
					if (Y[i] != 0 && X[i] > infusionLength + dosingTime)
						noOfNonZeroConc++;
				}
			}

			if (noOfNonZeroConc < 3)
				profileStatus = true;
			else
				profileStatus = false;
		} else {
			for (int i = 0; i < X.length; i++) {
				if (Y[i] != 0 && startTime <= X[i] && X[i] <= endTime)
					noOfNonZeroConc++;
			}

			if (noOfNonZeroConc < 2)
				profileStatus = true;
			else
				profileStatus = false;
		}
		return profileStatus;
	}
	
	double maxVal(double[] mat) {
		double max = mat[0];
		for (int i = 1; i < mat.length; i++) {
			if (mat[i] > max) {
				max = mat[i];
			}
		}
		return max;
	}		

	int findVal(double[] mat, double val) {

		for (int i = 0; i < mat.length; i++) {
			if (mat[i] == val)
				return i;
		}
		return -1;
	}



}
