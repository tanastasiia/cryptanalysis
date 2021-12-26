package ua.kpi;

import java.math.BigInteger;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MitM {

    private static class Pair {

        BigInteger first;
        BigInteger second;

        Pair(BigInteger first, BigInteger second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void attack(BigInteger e, BigInteger c, BigInteger n, int l) {

        var XSize = 1 << (l / 2);
        System.out.println("XSize " + XSize);

        var XmapBySecond = IntStream.range(1, XSize + 1).parallel()
                .mapToObj(i -> new Pair(BigInteger.valueOf(i), BigInteger.valueOf(i).modPow(e, n)))
                .collect(Collectors.toMap(p -> p.second, p -> p));

        System.out.println("X done, size : " + XmapBySecond.size());

        for (Map.Entry<BigInteger, Pair> entry : XmapBySecond.entrySet()) {
            var cs = entry.getValue().second.modInverse(n).multiply(c).mod(n);
            if (XmapBySecond.containsKey(cs)) {
                var S = entry.getValue().first;
                var T = XmapBySecond.get(cs).first;
                System.out.println("S : " + S.toString(16));
                System.out.println("T : " + T.toString(16));
                System.out.println("M : " + S.multiply(T).toString(16));
                System.out.println("C : " + S.multiply(T).modPow(e, n).toString(16));
                System.out.println("equals: " + (S.multiply(T).modPow(e, n).compareTo(c) == 0));
                break;
            }
        }


    }
}
