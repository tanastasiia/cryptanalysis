package ua.kpi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Text {
    private final String fileName;
    private final String str;
    private final String alphabet;

    // Чистим текст от мусора
    public Text(String fileName, String alphabet) throws IOException {
        this.fileName = fileName;
        this.alphabet = alphabet;
        Path path = Paths.get(fileName + ".txt");
        str = String.join(" ", Files.readAllLines(path))
                .toLowerCase()
                .replaceAll("ґ", "г")
                .replaceAll("[^" + alphabet + "]", "")
                .replaceAll("[ ]{2,}", " ")
                .trim();
    }

    public int getTextLength() {
        return str.length();
    }

    public double[][] countBigramFrequency() {
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


    public double[] countLettersFrequency() {
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


    public double countConformityIndex() {
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
    public double countEntropy() {
        double H = 0;
        double[] frequency = countLettersFrequency();
        for (double v : frequency) {
            H = H - v * Math.log(v);
        }
        return H;
    }

    public Map<String, Integer> countNgramsFrequencies(int n, boolean cross) {
        Map<String, Integer> ngramsFreq = new HashMap<>();
        //Pr(ngram) = amount of ngram / amount of all ngrams = amount of ngram *(1 / amount of all ngrams) =
        // = amount of ngram *(1 / (str.length() - n + 1))
        int amountOfNgrams = cross ? (str.length() - n + 1) : (str.length() / n);
        for (int i = 0; i < amountOfNgrams; i++) {
            int idx = cross ? i : (n * i);
            String ngram = str.substring(idx, idx + n);
            ngramsFreq.put(ngram, ngramsFreq.getOrDefault(ngram, 0) + 1);
        }
        return ngramsFreq;
    }



    @Override
    public String toString() {
        return "Text{" +
                "fileName='" + fileName + '\'' +
                ", str='" + str + '\'' +
                ", alphabet='" + alphabet + '\'' +
                '}';
    }
}





