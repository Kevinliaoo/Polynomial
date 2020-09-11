package functions;

import mathematics.Polynomial;
import java.util.ArrayList;
import java.util.Scanner;
import static mathematics.Calculations.factorize;

public class PolynomialUIMenu {
    private static Polynomial polynomial;

    /**
     * UI Menu for working with Polynomials
     *
     * @param pol Polynomial to be working with
     * */
    public static void showPolynomialMenu(Polynomial pol) {
        polynomial = pol;
        int response;
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("1. Factorize");
            System.out.println("2. f(x)");
            System.out.println("0. Return");
            try {
                response = Integer.valueOf(sc.nextLine());
                switch (response) {
                    case 0:
                        run = false;
                        break;
                    case 1:
                        GUI gui = new GUI(factorize(polynomial));
                        break;
                    case 2:
                        f();
                        break;
                    default:
                        break;
                }
            }
            catch (Exception e) {
                continue;
            }
        }
    }

    /**
     * Show the factorized function
     *
     * @return Factorized Polynomial
     * */
    private static ArrayList<Polynomial> getFactorized() {
        ArrayList<Polynomial> factorized = factorize(polynomial);
        for (Polynomial p: factorized) {
            System.out.println(p);
        }
        System.out.println("");
        return factorized;
    }

    /**
     * Get the y value for an x value
     * */
    private static double f() {
        System.out.println("Insert the value of x: ");
        double x = 0;
        Scanner sc = new Scanner(System.in);
        var run = true;
        while (run) {
            try {
                x = Double.valueOf(sc.nextLine());
                run = false;
            }
            catch (Exception e) {
                continue;
            }
        }
        double y = polynomial.f(x);
        System.out.println("f(" + x + ") = " + y + "\n");
        return y;
    }
}
