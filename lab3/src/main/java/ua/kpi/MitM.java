package ua.kpi;

import java.math.BigInteger;
import java.util.Map;
import java.util.stream.Collectors;
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

        var XSize = BigInteger.ONE.shiftLeft(l / 2);

        var XmapBySecond = Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE)).parallel()
                .map(i -> {
                    var second = i.modPow(e, n);
                    return new Triple(i, second, second.modInverse(n));
                })
                .takeWhile(p -> p.first.compareTo(XSize) <= 0).collect(Collectors.toMap(t-> t.second, t -> t));

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
