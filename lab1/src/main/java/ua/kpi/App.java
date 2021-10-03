package ua.kpi;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        ProbabilitiesCount probabilitiesCount = new ProbabilitiesCount();
        Variant16 variant = new Variant16();

        double[][] c = probabilitiesCount.prMCConditional(variant.M, variant.k, variant.C);
        for (double[] doubles : c) {
            System.out.println("sum : " + Arrays.stream(doubles).sum() + ":  " + Arrays.toString(doubles));
        }

    }
}
