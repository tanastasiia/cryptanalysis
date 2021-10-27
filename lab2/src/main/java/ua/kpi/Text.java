package ua.kpi;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Text {
    private String fileName;
    private String str;
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

    public int Text_length() {
        return str.length();
    }

    public double[] frequency_of_letters(){
        double[] frequency = new double[alphabet.length()];
        for (int i = 0; i < alphabet.length() ; i++) {
            double term = 0 ;
            for (int j = 0; j < str.length() ; j++) {
               if(alphabet.charAt(i) == str.charAt(j)){
                    term++;
               }
            frequency[i] = term/str.length();
            }
        }
        return frequency;
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





