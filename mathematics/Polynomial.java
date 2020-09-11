package mathematics;

/**
 * The polynomial must be ordered from low degree to high.
 */
public class Polynomial {
    public double coefficients[];

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }

    /**
     * Get the image value of x.
     * @param x Value of x
     * @return Image value of x
     * */
    public double f (double x) {
        double res = 0;
        for (int i = 0; i < this.coefficients.length; i ++) {
            res += coefficients[i] * Math.pow(x, i);
        }
        return res;
    }

    @Override
    public String toString() {
        String pol = "";
        for (int i = 0; i < coefficients.length; i ++) {
            String term = "";
            if (coefficients[i] > 0) {
                term += "+";
            }
            else {
                term += "-";
            }
            term += String.valueOf(coefficients[i]) + "X" + String.valueOf(i) + " ";
            pol += term;
        }
        return pol;
    }
}