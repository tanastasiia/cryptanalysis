package ua.kpi;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Lab2 {

    public static String alphabet = "абвгдеєжзиійклмнопрстуфхцчшщьюя";
    public static String languageTextFile = "lab2/src/main/resources/prust";
    public static String testTextFile = "lab2/src/main/resources/znedoleni";
    private static final Random random = new Random(82);
    private static final TextUtil textUtil = new TextUtil(alphabet);
    private static final TextDistortion textDistortion = new TextDistortion(alphabet);
    private static final CriterionsImpl criterions = new CriterionsImpl(alphabet);
    public static Map<String, Double> bigramsFreq;
    public static Map<String, Double> onegramsFreq;
    public static List<String> forbiddenBigrams;
    public static String language;
    public static String testStr;

    public enum Distortions {
        VIGENERE1, VIGENERE5, VIGENERE10, AFFINE, UNIFORM, G, NONE
    }

    public enum Criterions {
        CRITERION10, CRITERION11, CRITERION12, CRITERION13, CRITERION30, CRITERION51, STRUCTURE_CRITERION,
    }

    public static Criterion criterion10 = (int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies) -> criterions.criterion10(L, X, forbiddenNgrams, ngramSize);
    public static Criterion criterion11 = (int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies) -> criterions.criterion11(L, X, forbiddenNgrams, ngramSize, ngramSize == 2 ? 2 : 1);
    public static Criterion criterion12 = (int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies) -> criterions.criterion12(L, X, forbiddenNgrams, ngramSize, languageNgramsFrequencies);
    public static Criterion criterion13 = (int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies) -> criterions.criterion13(L, X, forbiddenNgrams, ngramSize, languageNgramsFrequencies);
    public static Criterion criterion30 = (int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies) -> criterions.criterion30(L, X, ngramSize, languageNgramsFrequencies, ngramSize == 2 ? 0.1 : 0.3);
    public static Criterion criterion51 = (int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies) -> criterions.criterion51(L, X, forbiddenNgrams, ngramSize, languageNgramsFrequencies, ngramSize == 2 ? 50 : 5, ngramSize == 2 ? 47 : 0);
    public static Criterion criterionStructure = (int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies) -> criterions.structureCriterion(L, X, ngramSize == 2 ? 1.22 : 1.2, ngramSize == 2 ? 0.1 : 0.1);

    public static Map<Criterions, Criterion> allCriterions = Map.of(
            Criterions.CRITERION10, criterion10,
            Criterions.CRITERION11, criterion11,
            Criterions.CRITERION12, criterion12,
            Criterions.CRITERION13, criterion13,
            Criterions.CRITERION30, criterion30,
            Criterions.CRITERION51, criterion51,
            Criterions.STRUCTURE_CRITERION, criterionStructure);


    public static Distortion distortVigenere1 = (String str, int len, int l) -> textDistortion.distortVigenere(str, 1);
    public static Distortion distortVigenere5 = (String str, int len, int l) -> textDistortion.distortVigenere(str, 5);
    public static Distortion distortVigenere10 = (String str, int len, int l) -> textDistortion.distortVigenere(str, 10);
    public static Distortion distortAffine = (String str, int len, int l) -> textDistortion.distortAffine(str, l);
    public static Distortion distortUniformly = (String str, int len, int l) -> textDistortion.generateUniformlyDistributedString(len);
    public static Distortion generateG = (String str, int len, int l) -> textDistortion.generateG(len, l);
    public static Distortion noneDistortion = (String str, int len, int l) -> str;

    public static Map<Distortions, Distortion> allDistortions = Map.of(
            Distortions.VIGENERE1, distortVigenere1,
            Distortions.VIGENERE5, distortVigenere5,
            Distortions.VIGENERE10, distortVigenere10,
            Distortions.AFFINE, distortAffine,
            Distortions.UNIFORM, distortUniformly,
            Distortions.G, generateG,
            Distortions.NONE, noneDistortion);

    static {
        try {
            language = textUtil.readAndCleanText(languageTextFile);
            testStr = textUtil.readAndCleanText(testTextFile);
            System.out.println("language file length : " + language.length());
            System.out.println("test     file length : " + testStr.length());

            bigramsFreq = textUtil.countNgramsFrequencies(language, 2, true);
            onegramsFreq = textUtil.countNgramsFrequencies(language, 1, false);
            forbiddenBigrams = textUtil.forbiddenNgrams(bigramsFreq);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void testCriterion(Criterions criterion, Distortions distortion, int L, int N, int ngramSize,
                              Map<String, Double> languageNgramFreq, List<String> forbiddenBigrams) {
        int trues = 0;
        int falses = 0;

        var distortedText = allDistortions.get(distortion).distort(testStr, testStr.length(), ngramSize);
        var criterionImp = allCriterions.get(criterion);
        //System.out.format("%ndistorted text example %s", distortedText.substring(0, 50));
        //System.out.println("text len : " + distortedText.length());
        for (int i = 0; i < N; i++) {
            var r = random.nextInt(testStr.length() - L);
            var block = distortedText.substring(r, r + L);
            if (criterionImp.test(L, block, forbiddenBigrams, ngramSize, languageNgramFreq)) {
                trues++;
            } else {
                falses++;
            }
        }
        System.out.format("%ntrues : %s%nfalses : %s", trues, falses);
        if (distortion == Distortions.NONE) {
            System.out.format("%nFP : %s", (double) falses / N);
        } else {
            System.out.format("%nFN : %s", (double) trues / N);
        }
    }

    public void testBigramCriterion(Criterions criterion, Distortions distortion, int L, int N) {
        testCriterion(criterion, distortion, L, N, 2, bigramsFreq, forbiddenBigrams);
    }

    public void testOnegramCriterion(Criterions criterion, Distortions distortion, int L, int N) {
        var forbiddenOnegrams = List.of(onegramsFreq.entrySet().stream()
                .min(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(" "));
        testCriterion(criterion, distortion, L, N, 1, onegramsFreq, List.of());
    }

}
