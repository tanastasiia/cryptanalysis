package ua.kpi;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class TextUtil {

    private final String alphabet;

    public TextUtil(String alphabet) {
        this.alphabet = alphabet;
    }

    public String readAndCleanText(String fileName) throws IOException {
        return String.join(" ", Files.readAllLines(Paths.get(fileName + ".txt"), Charset.forName("windows-1251")))
                .toLowerCase()
                .replaceAll("ґ", "г")
                .replaceAll("i", "і")
                .replaceAll("a", "а")
                .replaceAll("c", "с")
                .replaceAll("o", "о")
                .replaceAll("x", "х")
                .replaceAll("y", "у")
                .replaceAll("p", "р")
                .replaceAll("e", "е")
                .replaceAll("[^" + alphabet + "]", "")
                .replaceAll("[ ]{2,}", " ")
                .trim();
    }

    public double countEntropy(Map<String, Double> ngramFreq, int ngramSize) {
        return (1 - ngramFreq.values().stream()
                .map(aDouble -> aDouble * Math.log(aDouble) / Math.log(2))
                .reduce(Double::sum).orElse(0.0)) / ngramSize;
    }

    public Map<String, Double> countNgramsFrequencies(String str, int n, boolean cross) {
        Map<String, Double> ngramsFreq = new HashMap<>();
        //Pr(ngram) = amount of ngram / amount of all ngrams = amount of ngram *(1 / amount of all ngrams) =
        // = amount of ngram *(1 / (str.length() - n + 1))
        int amountOfNgrams = cross ? (str.length() - n + 1) : (str.length() / n);
        double probabilityBit = 1.0 / (amountOfNgrams);
        for (int i = 0; i < amountOfNgrams; i++) {
            int idx = cross ? i : (n * i);
            String ngram = str.substring(idx, idx + n);
            ngramsFreq.put(ngram, ngramsFreq.getOrDefault(ngram, 0.0) + probabilityBit);
        }
        return ngramsFreq;
    }

    public List<String> forbiddenNgrams(Map<String, Double> ngrams) {
        List<String> leastCommonNgrams  = ngrams.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getKey).limit(2).collect(Collectors.toList());
        if(leastCommonNgrams.get(0).length() == 1) {
            System.out.println("forbidden symbols " + Arrays.toString(leastCommonNgrams.toArray()));
            return leastCommonNgrams;
        }

        List<String> forbiddenNgrams = new ArrayList<>();
        for (int i = 0; i < alphabet.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                String temp = "" + alphabet.charAt(i) + alphabet.charAt(j);
                if (!ngrams.containsKey(temp)) {
                    forbiddenNgrams.add(temp);
                }
            }
        }
        return forbiddenNgrams;
    }


    public double[][] countBigramFrequency(String str) {
        double[][] bi = new double[alphabet.length()][alphabet.length()];
        for (int i = 0; i < alphabet.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                double term = 0;
                for (int k = 0; k < str.length() - 1; k++) {
                    if (alphabet.charAt(i) == str.charAt(k) & alphabet.charAt(j) == str.charAt(k + 1)) {
                        term++;
                    }
                }
                bi[i][j] = term / str.length();
            }
        }
        return bi;
    }


    public double[] countLettersFrequency(String str) {
        double[] frequency = new double[alphabet.length()];
        for (int i = 0; i < alphabet.length(); i++) {
            double term = 0;
            for (int j = 0; j < str.length(); j++) {
                if (alphabet.charAt(i) == str.charAt(j)) {
                    term++;
                }
                frequency[i] = term / (str.length() - 1);
            }
        }
        return frequency;
    }


    public double countConformityIndex(String str) {
        double sum = 0;
        double index;
        for (int i = 0; i < alphabet.length(); i++) {
            double term = 0;
            for (int j = 0; j < str.length(); j++) {
                if (alphabet.charAt(i) == str.charAt(j)) {
                    term++;
                }
            }
            sum = sum + (term * (term - 1)) / str.length();
        }
        index = sum / (str.length() - 1);
        return index;
    }

}





