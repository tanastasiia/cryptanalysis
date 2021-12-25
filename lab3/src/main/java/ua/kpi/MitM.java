package ua.kpi;

import java.math.BigInteger;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MitM {

    private static class Triple {
        BigInteger first;
        BigInteger second;
        BigInteger secondInv;

        Triple(BigInteger first, BigInteger second, BigInteger secondInv) {
            this.first = first;
            this.second = second;
            this.secondInv = secondInv;
        }
    }

    public static void attack(BigInteger e, BigInteger c, BigInteger n, int l) {

        var XSize = 1 << (l / 2);
        System.out.println("XSize " + XSize);

        var XmapBySecond = IntStream.range(1, XSize + 1).parallel()
                .mapToObj(i -> {
                    if(i % 10000 == 0) {
                        System.out.println(i);
                    }
                    var ii = BigInteger.valueOf(i);
                    var second = BigInteger.valueOf(i).modPow(e, n);
                    return new Triple(ii, second, second.modInverse(n));
                })
                .collect(Collectors.toMap(t -> t.second, t -> t));

        System.out.println("X done, size : " + XmapBySecond.size());

        for (Map.Entry<BigInteger, Triple> entry : XmapBySecond.entrySet()) {
            var cs = entry.getValue().secondInv.multiply(c).mod(n);
            if (XmapBySecond.containsKey(cs)) {
                var S = entry.getValue().first;
                var T = XmapBySecond.get(cs).first;
                System.out.println("S : " + S.toString(16));
                System.out.println("T : " + T.toString(16));
                System.out.println("M : " + S.multiply(T).toString(16));
                break;
            }
        }


    }
}
