package ua.kpi;


import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.DeflaterOutputStream;

//1.0-1.3 3.0 5.1
public class CriterionsImpl {

    private TextUtil textUtil;
    private TextDistortion textDistortion;

    public CriterionsImpl(String alphabet) {
        this.textUtil = new TextUtil(alphabet);
        this.textDistortion = new TextDistortion(alphabet);
    }

    //true - text is in language; false - text is random
    public boolean criterion10(int L, String X, List<String> forbiddenNgrams, int ngramSize) {
        for (int i = 0; i < L - ngramSize; i++) {
            if (forbiddenNgrams.contains(X.substring(i, i + ngramSize))) {
                //  System.out.println("found : " +  X.substring(i, i + ngramSize));
                return false;
            }
        }
        return true;
    }

    public boolean criterion11(int L, String X, List<String> forbiddenNgrams, int ngramSize, int kp) {
        Set<String> Aap = new HashSet<>();
        for (int i = 0; i < L - ngramSize; i++) {
            String temp = X.substring(i, i + ngramSize);
            if (forbiddenNgrams.contains(temp)) {
                Aap.add(temp);
            }
        }
        //  System.out.format("%nAap.size(): %s kp : %s", Aap.size(), kp);
        return Aap.size() < kp;
    }

    public boolean criterion12(int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies) {
        Map<String, Double> substrNgramFreq = textUtil.countNgramsFrequencies(X.substring(0, L), ngramSize, false);
        for (int i = 0; i < L - ngramSize; i++) {
            String ngram = X.substring(i, i + ngramSize);
            if (forbiddenNgrams.contains(ngram) && substrNgramFreq.getOrDefault(ngram, 0.) > languageNgramsFrequencies.getOrDefault(ngram, 0.)) {
                return false;
            }
        }
        return true;
    }

    public boolean criterion13(int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies) {
        Map<String, Double> substrNgramFreq = textUtil.countNgramsFrequencies(X.substring(0, L), ngramSize, false);
        double Fp = substrNgramFreq.entrySet().stream()
                .filter(e -> forbiddenNgrams.contains(e.getKey()))
                .map(Map.Entry::getValue).reduce(Double::sum).orElse(0.0);
        double Kp = languageNgramsFrequencies.entrySet().stream()
                .filter(e -> forbiddenNgrams.contains(e.getKey()))
                .map(Map.Entry::getValue).reduce(Double::sum).orElse(0.0);
        //  System.out.format("%nFp: %s Kp: %s", Fp, Kp);
        return Fp <= Kp;
    }

    public boolean criterion51(int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies,
                               int j, long Kempty) {
        Map<String, Double> topNgrams = languageNgramsFrequencies.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(j)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<String, Long> freqs = IntStream.range(0, L - ngramSize)
                .mapToObj(i -> X.substring(i, i + ngramSize))
                .filter(topNgrams::containsKey)
                .collect(Collectors.groupingBy(ngram -> ngram, Collectors.counting()));

        long Fempty = topNgrams.entrySet().stream().filter(e -> !freqs.containsKey(e.getKey())).count();
        // System.out.format("%nFempty: %s Kempty : %s", Fempty, Kempty);
        return Fempty >= Kempty;
    }

    public boolean structureCriterion(int L, String Y, double coef, double epsilon) {
        try {
            String Z = textDistortion.generateUniformlyDistributedString(L);

            ByteArrayOutputStream outZ = new ByteArrayOutputStream();
            DeflaterOutputStream gzipZ = new DeflaterOutputStream(outZ);
            gzipZ.write(Z.getBytes());
            gzipZ.close();
            String ZCompressed = outZ.toString();

            ByteArrayOutputStream outY = new ByteArrayOutputStream();
            DeflaterOutputStream gzipY = new DeflaterOutputStream(outY);
            gzipY.write(Y.substring(0, L).getBytes());
            gzipY.close();
            String YCompressed = outY.toString();

            // System.out.format("%n ratio : %s", ((double) ZCompressed.length() / YCompressed.length()));
            var ratio = (double) ZCompressed.length() / YCompressed.length();
            return ratio > coef && ratio < coef + epsilon;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public boolean criterion30(int L, String X, int ngramSize, Map<String, Double> languageNgramsFrequencies, double kh) {

        double langH = textUtil.countEntropy(languageNgramsFrequencies, ngramSize);
        double H2 = textUtil.countEntropy(textUtil.countNgramsFrequencies(X.substring(0, L), ngramSize, true), ngramSize);

        // System.out.format("%n entr : %s  kh : %s", Math.abs(langH - H2), kh);
        return Math.abs(langH - H2) < kh;
    }

}
