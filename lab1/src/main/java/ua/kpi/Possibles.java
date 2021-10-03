package ua.kpi;

public class Possibles extends Variant {

    public double[] prC(double[] M, double[] k, int[][] C) {
        double[] prC = new double[C.length];
        for (int j = 0; j < C.length; j++) {
            for (int l = 0; l < C.length; l++) {
                prC[C[j][l]] = prC[C[j][l]] + k[j] * M[l];
            }
        }
        return prC;
    }

    //P(C,M)
    public double[][] prMC(double[] M, double[] k, int[][] C) {
        double[][] prMC = new double[C.length][C.length];
        for (int j = 0; j < C.length; j++) { // rows - k
            for (int l = 0; l < C.length; l++) { //columns - M
                prMC[C[j][l]][l] = prMC[C[j][l]][l] + k[j] * M[l];
            }
        }
        return prMC;
    }

    //P(M|C) = P(M,C) / P(C)
    public double[][] prMCConditional(double[] M, double[] k, int[][] C) {
        double[][] res = new double[C.length][C.length];
        double[] prC = prC(M, k, C);
        double[][] prMC = prMC(M, k, C);

        for (int i = 0; i < C.length; i++) { //c
            for (int j = 0; j < C.length; j++) { //m
                res[i][j] = prMC[i][j] / prC[i];
            }
        }
        return res;
    }

}
