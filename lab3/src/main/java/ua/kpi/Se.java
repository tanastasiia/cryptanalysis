package ua.kpi;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class Se {


    static BigInteger x = new BigInteger("0");
    static BigInteger y = new BigInteger("0");


    private static BigInteger root(BigInteger num, int r) {

        var x0 = BigInteger.ONE;
        var x1 = BigInteger.ZERO;
        var rBI = BigInteger.valueOf(r);
        var rIncBI = BigInteger.valueOf(r - 1);

        while (x0.compareTo(x1) != 0) {
            var temp = x0;
            x0 = rIncBI.multiply(x0).add(num.divide(x0.pow(r - 1))).divide(rBI);
            x1 = temp;
        }
        return x0;

    }


    private static BigInteger chinese(List<BigInteger> Cs, List<BigInteger> Ns) {
        var M = Ns.stream().reduce(BigInteger::multiply).orElseThrow();
        var Ms = Ns.stream().map(M::divide).collect(Collectors.toList());
        var x = BigInteger.ZERO;
        for (int i = 0; i < Cs.size(); i++) {
            x = x.add(Cs.get(i).multiply(Ms.get(i)).multiply(Ms.get(i).modInverse(Ns.get(i)))).mod(M);
        }
        return x;
    }

    public static void attack(List<BigInteger> Cs, List<BigInteger> Ns, int e) {

        var C = chinese(Cs, Ns);
        System.out.println("C " + C.toString(16));
        var mulN = Ns.stream().reduce(BigInteger::multiply).orElse(BigInteger.ZERO);

        var M = root(C, e);
        System.out.println("M " + M.toString(16) + "\n");

        for (int i = 0; i < Cs.size(); i++) {
            System.out.println("M enc   " + M.modPow(BigInteger.valueOf(e), Ns.get(i)).toString(16));
            System.out.println("C       " + Cs.get(i).toString(16));
            System.out.println("equals: " + (Cs.get(i).compareTo(M.modPow(BigInteger.valueOf(e), Ns.get(i))) == 0) + "\n");

        }
    }
}
