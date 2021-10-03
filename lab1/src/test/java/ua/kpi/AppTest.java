package ua.kpi;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppTest {

    private static Variant16 variant = new Variant16();
    private static ProbabilitiesCount probabilitiesCount = new ProbabilitiesCount();

    @Test
    public void prCSumEqualsOne() {
        assertEquals(Arrays.stream(probabilitiesCount.prC(variant.M, variant.k, variant.C)).sum(), 1, 0.0000001);
    }

    @Test
    public void prMCCondSumEqualsOne() {
        double[][] prMC = probabilitiesCount.prMCConditional(variant.M, variant.k, variant.C);
        for (double[] pr : prMC) {
            assertEquals(Arrays.stream(pr).sum(), 1, 0.0000001);
        }
    }

    @Test
    public void prCTestPrint() {
        double[] pC = probabilitiesCount.prC(variant.M, variant.k, variant.C);
        //System.out.println((Arrays.toString(pC)));
        System.out.println(Arrays.stream(pC).mapToObj(x -> String.format("%,.4f", x)).collect(Collectors.toList()));

    }

    @Test
    public void prMCTestPrint() {
        double[][] prMC = probabilitiesCount.prMC(variant.M, variant.k, variant.C);
        for (double[] pr : prMC) {
            //System.out.println((Arrays.toString(pr)));
            System.out.println(Arrays.stream(pr).mapToObj(x -> String.format("%,.4f", x)).collect(Collectors.toList()));
        }
    }

    @Test
    public void prMCConditionalTestPrint() {
        double[][] prMCConditional = probabilitiesCount.prMCConditional(variant.M, variant.k, variant.C);
        for (double[] pr : prMCConditional) {
            //System.out.println((Arrays.toString(pr)));
            System.out.println(Arrays.stream(pr).mapToObj(x -> String.format("%,.4f", x)).collect(Collectors.toList()));
        }
    }

    @Test
    public void deterministicFunctionTestPrint() {
        int[] delta = probabilitiesCount.deterministicFunction(probabilitiesCount.prMCConditional(variant.M, variant.k, variant.C));
        System.out.println(Arrays.toString(delta));
    }

    @Test
    public void stochasticFunctionTestPrint() {
        double[][] delta = probabilitiesCount.stochasticFunction(probabilitiesCount.prMCConditional(variant.M, variant.k, variant.C));
        for (double[] pr : delta) {
            //System.out.println((Arrays.toString(pr)));
            System.out.println(Arrays.stream(pr).mapToObj(x -> String.format("%,.4f", x)).collect(Collectors.toList()));
        }
    }

    @Test
    public void avgLossFunctionDeterministicTestPrint() {
        double[][] prMCCond = probabilitiesCount.prMCConditional(variant.M, variant.k, variant.C);
        double l = probabilitiesCount.avgLossFunctionDeterministic(probabilitiesCount.deterministicFunction(prMCCond),
                prMCCond,
                probabilitiesCount.prC(variant.M, variant.k, variant.C));
        System.out.println(l);
    }

    @Test
    public void avgLossFunctionStochasticTestPrint() {
        double[][] prMCCond = probabilitiesCount.prMCConditional(variant.M, variant.k, variant.C);
        double l = probabilitiesCount.avgLossFunctionStochastic(probabilitiesCount.stochasticFunction(prMCCond), prMCCond,
                probabilitiesCount.prC(variant.M, variant.k, variant.C));
        System.out.println(l);
    }
}
