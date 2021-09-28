package ua.kpi;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Possibles a = new Possibles();
        Variant b =new Variant();
        //System.out.println(a.toString());
        //System.out.println(Arrays.toString(a.P_M(b.M, b.k, b.C)));
        //int[][] g ={{3,2},{4,5}};
        //System.out.println(b.C.length);
        double[][] c = a.P_M_CC(b.M, b.k, b.C);
        for (double[] doubles : c) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println("<3");


    }
}
