package ua.kpi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProbabilitiesCount {

    //P(C)
    public double[] prC(double[] M, double[] k, int[][] C) {
        double[] prC = new double[C.length];
        for (int j = 0; j < C.length; j++) {
            for (int l = 0; l < C.length; l++) {
                prC[C[j][l]] = prC[C[j][l]] + k[j] * M[l];
            }
        }
        return prC;
    }

    //P(M,C)
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
    // res[C][M]
    public double[][] prMCConditional(double[] M, double[] k, int[][] C) {
        double[][] prMCCond = new double[C.length][C.length];
        double[] prC = prC(M, k, C);
        double[][] prMC = prMC(M, k, C);

        for (int i = 0; i < C.length; i++) { //c
            for (int j = 0; j < C.length; j++) { //m
                prMCCond[i][j] = prMC[i][j] / prC[i];
            }
        }
        return prMCCond;
    }

    public int[] deterministicFunction(double[][] prMCConditional) {
        int[] deltaD = new int[prMCConditional.length];
        for (int i = 0; i < prMCConditional.length; i++) { //c
            double maxPr = -1;
            int maxIdx = -1;
            for (int j = 0; j < prMCConditional.length; j++) { //m
                if (maxPr < prMCConditional[i][j]) {
                    maxPr = prMCConditional[i][j];
                    maxIdx = j;
                }
            }
            deltaD[i] = maxIdx;
        }
        return deltaD;
    }

    public double[][] stochasticFunction(double[][] prMCConditional) {
        double[][] deltaS = new double[prMCConditional.length][prMCConditional.length];
        int[] deltaD = deterministicFunction(prMCConditional);
        for (int i = 0; i < prMCConditional.length; i++) { //c
            double maxPr = prMCConditional[i][deltaD[i]];
            List<Integer> maxIdxs = new ArrayList<>();
            for (int j = 0; j < prMCConditional.length; j++) { //m
                if (maxPr == prMCConditional[i][j]) {
                    maxIdxs.add(j);
                }
            }
            for (int idx : maxIdxs) {
                deltaS[i][idx] = 1. / maxIdxs.size();
            }
        }
        return deltaS;
    }

    public double avgLossFunctionDeterministic(int[] deterministicFunction, double[][] prMCConditional, double[] prC) {
        double l = 0;
        for (int i = 0; i < prMCConditional.length; i++) {
            l += prC[i] * (1 - prMCConditional[i][deterministicFunction[i]]);
        }
        return l;
    }

    public double avgLossFunctionStochastic(double[][] stochasticFunction, double[][] prMCConditional, double[] prC) {
        double l = 0;
        for (int i = 0; i < prMCConditional.length; i++) { //c
            double temp = 0;
            for (int j = 0; j < prMCConditional.length; j++) { //m
                temp += prMCConditional[i][j] * stochasticFunction[i][j];
            }
            l += (1 - temp) * prC[i];
        }
        return l;
    }

}
