package mathematics;
import java.util.ArrayList;

public class Basics {

    /**
     * Get all divisors of an Integer value.
     *
     * @param val Value to get divisors
     * @return ArrayList of Integer divisors
     * */
    public static ArrayList<Integer> getIntegerDivisors(int val) {
        val = Math.abs(val);
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 1; i <= val; i ++) {
            if (val % i == 0) {
                res.add(i);
            }
        }
        return res;
    }

    public static double[] reverseCoefs(double[] coefs) {
        double newArray[] = new double[coefs.length];
        int j = coefs.length;
        for (int i = 0; i < coefs.length; i ++) {
            newArray[j-1] = coefs[i];
            j --;
        }
        return newArray;
    }
}
