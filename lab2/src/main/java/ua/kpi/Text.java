package ua.kpi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public int textLength() {
        return str.length();
    }

    public double[][] bigram(){
        double [][] bi = new double[alphabet.length()][alphabet.length()];


        return bi;
    }


    public double[] frequencyLetters() {
        double[] frequency = new double[alphabet.length()];
        for (int i = 0; i < alphabet.length(); i++) {
            double term = 0;
            for (int j = 0; j < str.length(); j++) {
                if (alphabet.charAt(i) == str.charAt(j)) {
                    term++;
                }
                frequency[i] = term / str.length();
            }
        }
        return frequency;
    }


    public double conformityIndex() {
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
    public double entropy() {
        double H = 0;
        double[] frequency = frequencyLetters();
        for (double v : frequency) {
            H = H - v * Math.log(v);
        }
        return H;
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





