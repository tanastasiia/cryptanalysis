package ua.kpi;

import java.io.IOException;
import java.util.Arrays;

/**
 * Hello world!
 */
public class App extends Text {

    public App(String fileName, String alphabet) throws IOException {
        super(fileName, alphabet);
    }

    public static void main(String[] args) throws IOException {
        Text a  = new Text("The Master and Margarita", "абвгдеєжзиіїйклмнопрстуфхцчшщьюя");
        System.out.println(Arrays.toString(a.frequency_of_letters()));


    }
}
