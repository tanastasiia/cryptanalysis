package ua.kpi;

import java.io.IOException;
import java.util.*;

//1.0-1.3 3.0 5.1
public class Criterion {

    private TextUtil textUtil;

    public Criterion(TextUtil textUtil) {
        this.textUtil = textUtil;
    }

    //true - text is in language; false - text is random
    public boolean criterion10(int L, String X, List<String> forbiddenNgrams, int ngramSize) {
        for (int i = 0; i < L - ngramSize; i++) {
            if (forbiddenNgrams.contains(X.substring(i, i + ngramSize))) {
                return false;
            }
        }
        return true;
    }

    public boolean criterion11(int L, String X, List<String> forbiddenNgrams, int ngramSize, int kp) {
        Set<String> Aap = new HashSet<>();
        for (int i = 0; i < L - ngramSize; i++) {
            String temp = X.substring(i, i + ngramSize);
            if (forbiddenNgrams.contains(temp)) {
                Aap.add(temp);
            }
        }
        return Aap.size() < kp;
    }

    public boolean criterion12() {
        throw new UnsupportedOperationException();
    }

    // L - длина текст
    // Х - текст
    public boolean criterion30(int L, String X, int l, int kh) {
        String S = X.substring(0,L);

        double H1 = textUtil.countEntropy(X,l);
        double H2 = textUtil.countEntropy(S,l);

        return Math.abs(H1 - H2) > kh;
    }

}
