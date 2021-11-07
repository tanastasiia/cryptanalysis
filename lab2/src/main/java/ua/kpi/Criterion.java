package ua.kpi;

import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.GZIPOutputStream;

//1.0-1.3 3.0 5.1
public class Criterion {

    private TextUtil textUtil;
    private TextDistortion textDistortion;

    public Criterion(String alphabet) {
        this.textUtil = new TextUtil(alphabet);
        this.textDistortion = new TextDistortion(alphabet);
    }

    //true - text is in language; false - text is random
    public boolean criterion10(int L, String X, List<String> forbiddenNgrams, int ngramSize) {
        for (int i = 0; i < L - ngramSize; i++) {
            if (forbiddenNgrams.contains(X.substring(i, i + ngramSize))) {
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
        return Aap.size() < kp;
    }

    public boolean criterion12(int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies){
        Map<String, Double> substrNgramFreq = textUtil.countNgramsFrequencies(X.substring(0, L), ngramSize, false);
        for (int i = 0; i < L - ngramSize; i++) {
            String ngram = X.substring(i, i + ngramSize);
            if(forbiddenNgrams.contains(ngram) && substrNgramFreq.get(ngram) > languageNgramsFrequencies.get(ngram)) {
                return false;
            }
        }
        return true;
    }

    public boolean criterion13(int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies){
        Map<String, Double> substrNgramFreq = textUtil.countNgramsFrequencies(X.substring(0, L), ngramSize, false);
        double Fp = substrNgramFreq.entrySet().stream()
                .filter(e -> forbiddenNgrams.contains(e.getKey()))
                .map(Map.Entry::getValue).reduce(Double::sum).orElse(0.0);
        double Kp = languageNgramsFrequencies.entrySet().stream()
                .filter(e -> forbiddenNgrams.contains(e.getKey()))
                .map(Map.Entry::getValue).reduce(Double::sum).orElse(0.0);
        return Fp < Kp;
    }

    public boolean criterion51(int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies,
                               int j, long Kempty){
        Map<String, Double> topNgrams = languageNgramsFrequencies.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(j)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<String, Long> freqs = IntStream.range(0, L - ngramSize)
                .mapToObj(i -> X.substring(i, i + ngramSize))
                .filter(topNgrams::containsKey)
                .collect(Collectors.groupingBy(ngram -> ngram, Collectors.counting()));

        long Fempty = topNgrams.entrySet().stream().filter(e -> !freqs.containsKey(e.getKey())).count();
        return Fempty < Kempty;
    }

    public boolean structureCriterion(int L, String Y, double coef) throws Exception {
        String Z = textDistortion.generateUniformlyDistributedString(L);

        ByteArrayOutputStream outZ = new ByteArrayOutputStream();
        GZIPOutputStream gzipZ = new GZIPOutputStream(outZ);
        gzipZ.write(Z.getBytes());
        gzipZ.close();
        String ZCompressed = outZ.toString();

        ByteArrayOutputStream outY = new ByteArrayOutputStream();
        GZIPOutputStream gzipY = new GZIPOutputStream(outY);
        gzipY.write(Y.substring(0, L).getBytes());
        gzipY.close();
        String YCompressed = outY.toString();


        return (double) ZCompressed.length() / YCompressed.length() < coef;

    }

    // L - длина текст
    // Х - текст
    public boolean criterion30(int L, String X, int l, int kh) {
        String S = X.substring(0,L);

        double H1 = textUtil.countEntropy(X,l);
        double H2 = textUtil.countEntropy(S,l);

        return Math.abs(H1 - H2) > kh;
    }

}
