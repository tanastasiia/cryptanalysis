package ua.kpi;

import java.util.Random;

public class TextDistortion {

    private final String alphabet;
    private final Random random = new Random(57);

    public TextDistortion(String alphabet) {
        this.alphabet = alphabet;
    }

    //A
    public String distortVigenere(String str, int keyLen) {
        StringBuilder sb = new StringBuilder();
        var key = generateUniformlyDistributedString(keyLen);
        //System.out.println("key : " + key);
        for (int i = 0; i < str.length(); i++) {
            sb.append(intToChar(charToInt(str.charAt(i)) + charToInt(key.charAt(i % key.length()))));
        }
        return sb.toString();
    }

    //B
    public String distortAffine(String str, int l) {
        StringBuilder sb = new StringBuilder();
        var a = random.nextInt((int) Math.pow(alphabet.length(), l) - 1) + 1;
        var b = random.nextInt((int) Math.pow(alphabet.length(), l) - 1) + 1;
        //System.out.println("a  " + a +" b " + b + "  l  " + l );
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
    public String generateG(int len, int l) {
        StringBuilder sb = new StringBuilder();
        int ml = (int) Math.pow(alphabet.length(), l);
        int y0 = random.nextInt(ml);
        int y1 = random.nextInt(ml);
        sb.append(intToNgram(y0, l));
        sb.append(intToNgram(y1, l));
        int numberOfNgrams = len / l;
        for (int i = 0; i < numberOfNgrams; i++) {
            int temp = (y0 + y1) % ml;
            sb.append(intToNgram(temp, l));
            y0 = y1;
            y1 = temp;
        }
        //System.out.println("G " + sb.toString());
        return sb.toString();
    }

    private int charToInt(char c) {
        return alphabet.indexOf(c);
    }

    private char intToChar(int a) {
        return alphabet.charAt(a % alphabet.length());
    }

    private int ngramToInt(String ngram) {
        int res = 0;
        for (int i = 0; i < ngram.length(); i++) {
            res += alphabet.indexOf(ngram.charAt(i)) * (int) Math.pow(alphabet.length(), ngram.length() - i - 1);
        }
        return res;
    }

    private String intToNgram(int a, int n) {
        if (n == 1) {
            return String.valueOf(intToChar(a));
        } else {
            return "" + intToChar(a / alphabet.length()) + intToChar(a % alphabet.length());
        }
    }

}
