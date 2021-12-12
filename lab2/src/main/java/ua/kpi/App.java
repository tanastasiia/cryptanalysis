package ua.kpi;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) {


        var lab = new Lab2();

        int L = 10000;
        int N = 1000;

        var random = new Random(33);

        List<Integer> rs = IntStream.range(0, N).mapToObj(i -> random.nextInt(lab.getTestTextLen() - L)).collect(Collectors.toList());



        for (Lab2.Distortions distortion : Lab2.Distortions.values()) {
            System.out.format("%n%n%s ----------------------------------------------------------------------------%n", distortion.name());
            for (Lab2.Criterions criterion : Lab2.Criterions.values()) {
                System.out.format("%n%n%s +++++++++++++++%n", criterion.name());
                System.out.format("%nL=2");
                lab.testBigramCriterion(criterion, distortion, L, N, rs);
                System.out.format("%n%nL=1");
                lab.testOnegramCriterion(criterion, distortion, L, N, rs);
            }
        }
/*
        for (Lab2.Criterions criterion : List.of(Lab2.Criterions.CRITERION51)
          */
/*  Lab2.Criterions.values()*//*

        ) {
            System.out.format("%n%n%s%n", criterion.name());
            for (Lab2.Distortions distortion : Lab2.Distortions.values()) {
                System.out.format("%n%n%s%n", distortion.name());
                // lab.testOnegramCriterion(criterion, distortion, L, N);
                lab.testOnegramCriterion(criterion, distortion, 1000, 10, rs);
                //lab.testBigramCriterion(criterion, distortion, 10000, 10, rs);
            }
        }
*/


    }
}
