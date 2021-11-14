package ua.kpi;

public class App {

    public static void main(String[] args) {


        var lab = new Lab2();

        int L = 10000;
        int N = 1000;

        for (Lab2.Distortions distortion : Lab2.Distortions.values()) {
            System.out.format("%n%n%s ----------------------------------------------------------------------------%n", distortion.name());
            for (Lab2.Criterions criterion : Lab2.Criterions.values()) {
                System.out.format("%n%n%s +++++++++++++++%n", criterion.name());
                System.out.format("%nL=2");
                lab.testBigramCriterion(criterion, distortion, L, N);
                System.out.format("%n%nL=1");
                lab.testOnegramCriterion(criterion, distortion, L, N);
            }
        }

        /*for (Lab2.Criterions criterion : *//*List.of(Lab2.Criterions.CRITERION51)*//*Lab2.Criterions.values()) {
            System.out.format("%n%n%s%n", criterion.name());
            for (Lab2.Distortions distortion : Lab2.Distortions.values()) {
                System.out.format("%n%n%s%n", distortion.name());
                lab.testOnegramCriterion(criterion, distortion, L, N);
                lab.testBigramCriterion(criterion, distortion, 10000, 1000);
            }
        }*/


    }
}
