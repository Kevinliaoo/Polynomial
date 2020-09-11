package mathematics;

import java.util.ArrayList;
import static mathematics.Basics.getIntegerDivisors;
import static mathematics.Basics.reverseCoefs;

public class Calculations {

    /**
     * Get the sum of two polynomials.
     *
     * @param pol1 Fisrt argument
     * @param pol2 Second argument
     * @return The sum of both arguments
     * */
    public static Polynomial sum (Polynomial pol1, Polynomial pol2) {
        Polynomial maxPol, minPol;
        if (pol1.getCoefficients().length >= pol2.getCoefficients().length) {
            maxPol = pol1;
            minPol = pol2;
        }
        else {
            maxPol = pol2;
            minPol = pol1;
        }
        double coefs[] = new double[maxPol.getCoefficients().length];
        for (int i = 0; i < maxPol.getCoefficients().length; i ++) {
            try {
                coefs[i] = maxPol.getCoefficients()[i] + minPol.getCoefficients()[i];
            }
            catch (Exception e) {
                coefs[i] = maxPol.getCoefficients()[i];
            }
        }
        return new Polynomial(coefs);
    }

    /**
     * Get the substraction of two polynomials.
     *
     * @param pol1 Fisrt argument
     * @param pol2 Second argument
     * @return The substraction of both arguments
     * */
    public static Polynomial sub (Polynomial pol1, Polynomial pol2) {
        Polynomial maxPol, minPol;
        if (pol1.getCoefficients().length >= pol2.getCoefficients().length) {
            maxPol = pol1;
            minPol = pol2;
        }
        else {
            maxPol = pol2;
            minPol = pol1;
        }
        double coefs[] = new double[maxPol.getCoefficients().length];
        for (int i = 0; i < maxPol.getCoefficients().length; i ++) {
            try {
                coefs[i] = pol1.getCoefficients()[i] - pol2.getCoefficients()[i];
            }
            catch (Exception e) {
                if (maxPol == pol1) {
                    coefs[i] = pol1.getCoefficients()[i];
                }
                else {
                    coefs[i] = 0 - pol2.getCoefficients()[i];
                }
            }
        }
        return new Polynomial(coefs);
    }

    /**
     * Get the product of two polynomials
     *
     * @param pol1 Fist factor
     * @param pol2 Second factor
     * @return The product of the multiplication
     * */
    public static Polynomial mult (Polynomial pol1, Polynomial pol2) {
        double coefs[] = new double[pol1.getCoefficients().length + pol2.getCoefficients().length-1];
        for (int i = 0; i < pol1.getCoefficients().length; i ++) {
            for (int j = 0; j < pol2.getCoefficients().length; j ++) {
                coefs[i+j] += pol1.getCoefficients()[i] * pol2.getCoefficients()[j];
            }
        }
        return new Polynomial(coefs);
    }

    /**
     * Divides all coefficients of a Polynomial by a value.
     *
     * @param pol Polynomial to be divided
     * @return Polynomial with the coefficients reduced
     * */
    public static Polynomial scalarDiv (Polynomial pol, double divider) {
        double coefs[] = pol.getCoefficients();
        double newCoefs[] = new double[coefs.length];
        for (int i = 0; i < coefs.length; i ++) {
            newCoefs[i] = coefs[i] / divider;
        }
        return new Polynomial(newCoefs);
    }

    /**
     * This function gets all the roots of a Polynomial.
     *
     * @param pol Polynomial
     * @return ArrayList with the values of roots (roots values could be repeated)
     * */
    public static ArrayList<Double> gaussFactorization (Polynomial pol) {
        int first = (int)pol.getCoefficients()[0];
        int last = (int)pol.getCoefficients()[(int)pol.getCoefficients().length - 1];
        ArrayList<Integer> fistFactors = getIntegerDivisors(first);
        ArrayList<Integer> lastFactors = getIntegerDivisors(last);
        ArrayList<Double> roots = new ArrayList<Double>();
        for (Integer i: fistFactors) {
            for (Integer j: lastFactors) {
                if (pol.f((double)i / (double)j) == 0) {
                    roots.add((double)i / (double)j);
                }
                if (pol.f((double)-i / (double)j) == 0) {
                    roots.add((double)0-i / (double)j);
                }
                if (pol.f((double)-i / (double)-j) == 0) {
                    roots.add((double)-i / (double)-j);
                }
                if (pol.f((double)i / (double)-j) == 0) {
                    roots.add((double)i / (double)-j);
                }
            }
        }
        return roots;
    }

    /**
     * This function makes the Ruffini division of a Polynomial.
     * The function automatically get a root to divide the Polynomial.
     *
     * @param pol Polynomial to be divided
     * @return ArrayList containing the quotient, divider and the remaining
     * */
    public static ArrayList<Polynomial> ruffini(Polynomial pol) {
        // Get roots
        ArrayList<Double> roots = gaussFactorization(pol);
        if (roots.size() == 0) {
            return null;
        }
        // Get the first root
        double root = roots.get(0);
        double orderedCoefs[] = reverseCoefs(pol.getCoefficients());
        double quotient[] = new double[pol.getCoefficients().length-1];
        quotient[0] = orderedCoefs[0];
        double value = orderedCoefs[0] * root;
        int i = 1;
        for (i = i; i < orderedCoefs.length-1; i ++) {
            double val = orderedCoefs[i] + value;
            quotient[i] = val;
            value = val * root;
        }
        value = orderedCoefs[i] + value;
        ArrayList<Polynomial> result = new ArrayList<>();
        // Add the quotient
        result.add(new Polynomial((reverseCoefs(quotient))));
        // Add the divider (root)
        double divisor[] = {-root, 1};
        result.add(new Polynomial(divisor));
        // Add the remaining
        double remain[] = {value};
        result.add(new Polynomial(remain));

        return result;
    }

    /**
     * This function factorizes a Polynomial.
     * Only works well if roots are Integer values.
     * If the Polynomial does not have any Integer root, it will return null.
     *
     * @param pol Polynomial to be factorized
     * @return ArrayListt with all the roots of the Polynomial
     * */
    public static ArrayList<Polynomial> factorize(Polynomial pol) {
        ArrayList<Polynomial> roots = new ArrayList<>();
        double mainCoeff = pol.getCoefficients()[pol.getCoefficients().length-1];
        double mainCoefficient[] = {mainCoeff};
        roots.add(new Polynomial(mainCoefficient));
        pol = scalarDiv(pol, mainCoeff);

        // Quadratic Polynomials
        if (pol.getCoefficients().length == 3) {
            // Standard quadratic equation
            double a = pol.getCoefficients()[2];
            double b = pol.getCoefficients()[1];
            double c = pol.getCoefficients()[0];
            double discrimitant = Math.pow(b, 2) - 4 * a * c;

            if (discrimitant == 0) {
                double root[][] = {{-((-b) / (2 * a)), 1}};
                for (int i = 0; i <= 1; i ++) {
                    roots.add(new Polynomial(root[0]));
                }
                return roots;
            }
            else if (discrimitant > 0) {
                double root[][] = {
                        {-((-b + Math.sqrt(discrimitant)) / (2 * a)), 1},
                        {-((-b - Math.sqrt(discrimitant)) / (2 * a)), 1}
                };
                for (double d[]: root) {
                    roots.add (new Polynomial(d));
                }
                return roots;
            }
            else {
                // Can not factorize
                roots.add(pol);
                return roots;
            }

        }

        ArrayList<Polynomial> res = ruffini(pol);
        while (true) {
            if (res == null) {
                break;
            }
            if (res.get(2).getCoefficients()[0] == 0) {
                roots.add(res.get(1));
                if (ruffini(res.get(0)) != null) {
                    res = ruffini(res.get(0));
                    continue;
                }
                else {
                    roots.add(res.get(0));
                    break;
                }
            }
        }
        return roots;
    }
}
