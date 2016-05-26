package Model;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import Jama.Matrix;
import Jama.SingularValueDecomposition;

public class CalculateResidual {

	GaussNewton gnInstance;
	PkPdInfo pkpdInfoInst;

	public void residualCalculation(double[] par, double[] infusionTime,
			double[] dose, double[] dosingTime, double[][] indX, double[][] Y,
			double[][] Extra_DATA, double[] delta, int fn_no, int[] row,
			int profileNo) throws RowsExceededException, WriteException,
			BiffException, IOException {
		pkpdInfoInst = PkPdInfo.createPKPDInstance();
		gnInstance = new GaussNewton();
		double[][] X = gnInstance.Jacobian(par, infusionTime, dose, dosingTime,
				indX, Y, Extra_DATA, delta, fn_no, row, profileNo);
		Matrix matX = new Matrix(X);
		double[][] transX = transpose(X);
		Matrix matTransX = new Matrix(transX);
		Matrix matTransXX = matTransX.times(matX);
		double[][] xTransX = matTransXX.getArray();
		double[][] xTransXInverse = inverseMatrix(xTransX);
		Matrix inverse = new Matrix(xTransXInverse);
		Matrix A = matX.times(inverse);
		Matrix H = A.times(matTransX);
		double[][] arrayH = H.getArray();
		double[][] error = gnInstance.error(par, indX, Y, Extra_DATA, fn_no,
				row, infusionTime, dose, dosingTime, profileNo);
		double s = 0;
		for (int i = 0; i < error.length; i++) {
			s = s + error[i][0] * error[i][0];
		}
		s = s / (error.length - 2);
		s = Math.sqrt(s);
		pkpdInfoInst.stdResidual = new double[X.length];
		pkpdInfoInst.sePredy = new double[X.length];
		double temp = 0;
		for (int i = 0; i < error.length; i++) {
			temp = error[i][0] / (s * Math.sqrt(1 - arrayH[i][i]));
			pkpdInfoInst.stdResidual[i] = temp;
			temp = (Math.sqrt(s * s * arrayH[i][i]));
			pkpdInfoInst.sePredy[i] = temp;
		}

	}

	public static double[][] inverseMatrix(double[][] A) {
		int row = A.length;
		double[][] A_inv = new double[row][row];
		Matrix M = new Matrix(A);
		SingularValueDecomposition USV = new SingularValueDecomposition(M);
		Matrix U = USV.getU();
		Matrix V = USV.getV();
		Matrix S = USV.getS();
		double[][] U_mat = U.getArray();
		double[][] S_mat = S.getArray();
		double[][] S_inv = new double[row][row];
		for (int i = 0; i < row; i++) {
			if (S_mat[i][i] <= 0.00000001)
				S_inv[i][i] = 0.0;
			else
				S_inv[i][i] = 1.0 / S_mat[i][i];

		}
		Matrix Sinv = new Matrix(S_inv);

		double[][] UT_mat = transpose(U_mat);
		Matrix UT = new Matrix(UT_mat);

		Matrix VS_inv = V.times(Sinv);
		Matrix VSinvUT = VS_inv.times(UT);
		A_inv = VSinvUT.getArray();

		return A_inv;
	}

	// Matrix Transpose
	public static double[][] transpose(double[][] mat) {
		int rowCount = mat.length, colCount = mat[0].length;

		double[][] transposedMat = new double[colCount][rowCount];

		for (int i = 0; i < rowCount; i++)
			for (int j = 0; j < colCount; j++)
				transposedMat[j][i] = mat[i][j];

		return transposedMat;
	}

	// Computing B
	public static double[][] computeB(double[][] J_mat, double[][] Q_mat,
			double[][] error_mat) {
		int Npar = J_mat[0].length;
		double[][] B = new double[Npar][1];

		Matrix J = new Matrix(J_mat);
		Matrix Q = new Matrix(Q_mat);
		Matrix JT = J.transpose();

		Matrix error = new Matrix(error_mat);
		Matrix JTQ = JT.times(Q);
		Matrix B_mat = JTQ.times(error);
		B = B_mat.getArray();

		return B;
	}
	public static double[] par_inc(double[] par, double increment, int idx) {
		double incPar_set[] = new double[par.length];
		for (int i = 0; i < par.length; i++) {
			if (i == idx)
				incPar_set[i] = par[i] + increment;
			else
				incPar_set[i] = par[i];
		}
		return incPar_set;
	}

}
