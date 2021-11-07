package ua.kpi;

import java.io.IOException;
import java.util.Arrays;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException {
        TextUtil text  = new TextUtil("абвгдеєжзиіїйклмнопрстуфхцчшщьюя");
        String cleanText = text.readAndCleanText("The Master and Margarita");
        System.out.println(cleanText.length());
        System.out.println(Arrays.deepToString(text.countBigramFrequency(cleanText)));


    }
}
