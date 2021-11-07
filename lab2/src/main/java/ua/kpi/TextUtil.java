package ua.kpi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TextUtil {
  //  private final String fileName;
 //   private final String str;
    private final String alphabet;

    public TextUtil(String alphabet) {
        this.alphabet = alphabet;
    }

    // Чистим текст от мусора
    public String readAndCleanText(String fileName) throws IOException {
        return  String.join(" ", Files.readAllLines(Paths.get(fileName + ".txt")))
                .toLowerCase()
                .replaceAll("ґ", "г")
                .replaceAll("[^" + alphabet + "]", "")
                .replaceAll("[ ]{2,}", " ")
                .trim();
    }

    public double[][] countBigramFrequency(String str) {
        double[][] bi = new double[alphabet.length()][alphabet.length()];
        for (int i = 0; i < alphabet.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                double term = 0;
                for (int k = 0; k <str.length() - 1 ; k++) {
                    if (alphabet.charAt(i) == str.charAt(k) & alphabet.charAt(j) == str.charAt(k+1)) {
                        term++;
                    }
                }
                bi[i][j] = term/str.length();
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

    // H = - Sum p_i * log p_i
    public double countEntropy(String str) {
        double H = 0;
        double[] frequency = countLettersFrequency(str);
        for (double v : frequency) {
            H = H - v * Math.log(v);
        }
        return H;
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

}





