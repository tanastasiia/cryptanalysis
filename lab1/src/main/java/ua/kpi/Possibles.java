package ua.kpi;

import java.util.Arrays;

public class Possibles extends Variant {
    double[] tt = new double[20];
    // P(C)

    public double[] P_M(double[] M, double[] k, double[][] C) {
        for (int i = 0; i < C.length; i++) {
            double temp = C[0][i];
            double result = 0;
            for (int j = 0; j < C.length; j++) {
                for (int l = 0; l < C.length; l++) {
                    if (temp == C[j][l]) {
                        result = result + (k[j] * M[l]);
                    }
                }
            }
            tt[i] = result;
        }
        return tt;
    }

    double[][] ttt = new double[20][20];
    //P(C,M)

    public double[][] P_M_С(double[] M, double[] k, double[][] C) {
        for (int i = 0; i < C.length; i++) {
            double temp = C[0][i];
            for (int j = 0; j < C.length; j++) {
                for (int l = 0; l < C.length; l++) {
                    if (temp == C[j][l]) {
                        ttt[i][j] = k[j] * M[l];
                    }
                }
            }
        }
        return ttt;
    }

    double[][] t = new double[20][20];
    //P(C|M)

    public double[][] P_M_CC(double[] M, double[] k, double[][] C) {
        double[] a = P_M(M, k, C);
        double[][] b = P_M_С(M, k, C);

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C.length; j++) {
                t[i][j] = b[i][j]/a[j];
            }
        }
        return t;
    }

}
