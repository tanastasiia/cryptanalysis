package ua.kpi;

import java.util.Random;

public class TextDistortion {

    private final String alphabet;
    private final Random random = new Random(42);

    public TextDistortion(String alphabet) {
        this.alphabet = alphabet;
    }

    //A
    public String distortVigenere(String str, String key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(intToChar(charToInt(str.charAt(i)) + charToInt(key.charAt(i % key.length()))));
        }
        return sb.toString();
    }

    //B
    public String distortAffine(String str, int a, int b, int l) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length() - l; i = i + l) {
            sb.append(intToNgram(a * ngramToInt(str.substring(i, i + l)) + b, l));
        }
        return sb.toString();
    }

    //C
    public String generateUniformlyDistributedString(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(intToChar(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    //D
    public String generateG(int numberOfNgrams, int l) {
        StringBuilder sb = new StringBuilder();
        int ml = (int) Math.pow(alphabet.length(), l);
        int y0 = random.nextInt(ml);
        int y1 = random.nextInt(ml);
        sb.append(intToNgram(y0, l));
        sb.append(intToNgram(y1, l));
        for (int i = 0; i < numberOfNgrams; i++) {
            int temp = (y0 + y1) % ml;
            sb.append(intToNgram(temp, l));
            y0 = y1;
            y1 = temp;
        }
        return sb.toString();
    }

    private int charToInt(char c) {
        return alphabet.indexOf(c);
    }

    private int intToChar(int a) {
        return alphabet.charAt(a % alphabet.length());
    }

    private int ngramToInt(String ngram) {
        int res = 0;
        for (int i = 0; i < ngram.length(); i++) {
            res += alphabet.indexOf(ngram.charAt(i)) * (int)Math.pow(alphabet.length(), ngram.length() - i - 1);
        }
        return res;
    }

    /*
        abc  = a * 32**2 +  b * 32**1 + c * 32**0 = x = 0 + 32 + 2 = 34
        c = x % 32 = 2
        b = (x - c) %(32**2) = 32
        a = (x - b - c) % (32**3) = 0
    */
    private String intToNgram(int a, int n) {
        StringBuilder ngram = new StringBuilder();
        //add  first mod m**l?
        for (int i = 0; i < n; i++) {
            int temp = a % (int) Math.pow(alphabet.length(), i + 1);
            a = a - temp;
            ngram.append(intToChar(temp));
        }
        return ngram.toString();
    }

}
