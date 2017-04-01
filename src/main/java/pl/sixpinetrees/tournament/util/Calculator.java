package pl.sixpinetrees.tournament.util;

/**
 * Compute the ceil of lg(n) and 2^n.
 */
public class Calculator {

    public static Integer log2ceil(Integer number) {

        if (number > 0) {
            return 32 - Integer.numberOfLeadingZeros(number - 1);
        }
        throw new IllegalArgumentException();
    }

    public static Integer pow2N(Integer n) {
        return (Integer) 1 << n;
    }

}
