package ua.kpi;

@FunctionalInterface
public interface Distortion {
    String distort(String str, int len, int l);
}
