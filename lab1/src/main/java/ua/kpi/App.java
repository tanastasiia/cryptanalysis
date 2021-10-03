package ua.kpi;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        Possibles possibles = new Possibles();
        Variant variant = new Variant();

        double[][] c = possibles.prMCConditional(variant.M, variant.k, variant.C);
        for (double[] doubles : c) {
            System.out.println("sum : " + Arrays.stream(doubles).sum() + ":  " + Arrays.toString(doubles));
        }

    }
}
