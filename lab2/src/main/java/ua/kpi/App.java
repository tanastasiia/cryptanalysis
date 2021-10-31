package ua.kpi;

import java.io.IOException;
import java.util.Arrays;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException {
        Text a  = new Text("The Master and Margarita", "абвгдеєжзиіїйклмнопрстуфхцчшщьюя");
        System.out.println(a.getTextLength());
        //System.out.println(a.conformityIndex());
        //System.out.println(a.entropy());
        System.out.println(Arrays.deepToString(a.countBigramFrequency()));


    }
}
