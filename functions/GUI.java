package functions;
import mathematics.Polynomial;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    private String factorizedString = "";
    private static final char DEGREES[] = {
            '\u2070', '\u2071', '\u2072', '\u2073', '\u2074', '\u2075'
    };

    public GUI (ArrayList<Polynomial> pol) {
        super("Factorized Polynomial");

        factorizedString = convertPolToString(pol);
        System.out.println(factorizedString);

        setLayout(new FlowLayout());
        Label factorized = new Label(factorizedString);
        factorized.setFont(new Font("Serif", Font.PLAIN, 20));
        add(factorized);

        setSize(400, 80);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Convert an ArrayList<Polynomial> to a String formatted factorized Polynomial
     *
     * @param pol Factorized ArrayList Polynomials
     * @return String formatted of the factorized Polynomial
     * */
    public static String convertPolToString (ArrayList<Polynomial> pol) {
        String factorizedString = "f(x) = ";

        // Iterate through all the Polynomials
        for (int i = 0; i < pol.size(); i ++) {
            double coefs[] = pol.get(i).getCoefficients();
            // First, get the principal coefficient
            if (i == 0) {
                // Double checking
                if (coefs.length == 1) {
                    factorizedString += String.valueOf(coefs[0]);
                }
                continue;
            }
            factorizedString += "(";
            // Get the degree of each coefficient
            int degree = coefs.length-1;
            // Set to true if is the first coefficient in order not to add "+" to the fist value
            boolean started = true;
            // Iterat through all coefficients
            for (int j = coefs.length-1; j >= 0; j--) {
                // Do nothing if is 0
                if (coefs[j] == 0) continue;
                // Positive coefficient
                else if (coefs[j] > 0) {
                    if (started == false) { // Print plus sign if it is not the first coefficient
                        factorizedString += "+";
                    }
                    if (coefs[j] == 1 && degree != 0) {}
                    else {
                        factorizedString += String.valueOf(coefs[j]);
                    }
                }
                // Negative coefficients
                else {
                    factorizedString += "-";
                    if (coefs[j] == 1 && degree != 0) {}
                    else {
                        factorizedString += String.valueOf(coefs[j]);
                    }
                }
                // Print Xs
                if (degree == 1) {
                    factorizedString += "x";
                }
                else if (degree > 1) {
                    factorizedString += "x";
                    factorizedString += DEGREES[degree];
                }
                started = false;    // Set to false after fisrt iteration
                degree --;  // Turn down one degree after each iteration
            }
            factorizedString += ")";
        }
        return factorizedString;
    }
}
