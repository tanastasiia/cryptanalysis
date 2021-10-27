package ua.kpi;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Text {
    private String fileName;
    private String str;
    private String alphabet;


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

    public int frequency_of_letters(){
        return str.length();
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





