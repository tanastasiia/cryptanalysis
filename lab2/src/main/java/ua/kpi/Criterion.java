package ua.kpi;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface Criterion {
    boolean test(int L, String X, List<String> forbiddenNgrams, int ngramSize, Map<String, Double> languageNgramsFrequencies);

}
