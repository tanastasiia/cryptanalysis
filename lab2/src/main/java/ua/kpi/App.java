package ua.kpi;

import java.io.IOException;

/**
 * Hello world!
 */
public class App extends Text {

    public App(String fileName, String alphabet) throws IOException {
        super(fileName, alphabet);
    }

    public static void main(String[] args) throws IOException {
        Text a  = new Text("The Master and Margarita", "абвгдеєжзиіїйклмнопрстуфхцчшщьюя");
        System.out.println(a.frequency_of_letters());
    }
}
