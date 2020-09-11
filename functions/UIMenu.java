package functions;

import mathematics.Polynomial;
import java.util.ArrayList;
import java.util.Scanner;
import static functions.PolynomialUIMenu.showPolynomialMenu;

public class UIMenu {
    public static ArrayList<Polynomial> myPolynomials = new ArrayList<>();

    public static void showHomeMenu() {
        System.out.println("Welcome!");
        int response = 0;
        do {
            System.out.println("1. Add a new Polynomial");
            System.out.println("2. View my Polynomials");
            System.out.println("0. Exit");
            Scanner scanner = new Scanner(System.in);
            try {
                response = Integer.valueOf(scanner.nextLine());
                switch (response) {
                    case 1:
                        double polCoefs[] = arrayListToArray(getCoefficient());
                        myPolynomials.add(new Polynomial(polCoefs));
                        break;
                    case 2:
                        if (myPolynomials.size() > 0) {
                            for (int i = 0; i < myPolynomials.size(); i++) {
                                System.out.println(i + ". " + myPolynomials.get(i));
                            }
                            int selected = selectPolynomial();
                            System.out.println("\nPolynomial selected: ");
                            System.out.println(myPolynomials.get(selected) + "\n");

                            showPolynomialMenu(myPolynomials.get(selected));
                        }
                        break;
                    case 0:
                        response = 0;
                        System.out.println("Thank you for your visit");
                        break;
                    default:
                        continue;
                }
            }
            catch (Exception e) {
                response = -1;
            }
        }
        while (response != 0);
    }

    /**
     * Convert an ArrayList with Double values to a double[]
     *
     * @param arr ArrayList to be converted, must be Double type
     * @return Array of double values
     * */
    private static double[] arrayListToArray (ArrayList<Double> arr) {
        double res[] = new double[arr.size()];
        for (int i = 0; i < arr.size(); i ++) {
            res[i] = arr.get(i);
        }
        return res;
    }

    /**
     * This function asks the user to select a Plynomial in the myPolynomials ArrayList
     *
     * @return Index value selected by the user
     * */
    private static int selectPolynomial() {
        int response = 0;
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        while (run) {
            try {
                response = Integer.valueOf(sc.nextLine());
            }
            catch (Exception e) {
                continue;
            }
            if (response < myPolynomials.size()) run = false;
        }
        return response;
    }

    /**
     * This function asks the user to insert coefficients of a Polynomial.
     * The user can directly insert the values.
     * Press enter to exit.
     *
     * @return ArrayList containing the coefficients
     * */
    private static ArrayList<Double> getCoefficient() {
        System.out.println("\nInsert coefficients:");
        ArrayList<Double> coefs = new ArrayList<Double>();
        String enter = "";
        String error = "X";
        String response = "";
        do {
            Scanner scanner = new Scanner(System.in);
            response = scanner.nextLine();
            try {
                coefs.add(Double.valueOf(response));
            }
            catch (Exception e) {
                if (response.equals(enter)) break;
                response = error;
            }
        }
        while (!response.equals(enter));
        System.out.println("Your coefficients: ");
        for (double d: coefs) {
            System.out.print(d + "   ");
        }
        System.out.println("\n");
        return coefs;
    }
}
