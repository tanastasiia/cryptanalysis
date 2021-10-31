package ua.kpi;

import java.util.*;

//1.0-1.3 3.0 5.1
public class Criterion {

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

}
