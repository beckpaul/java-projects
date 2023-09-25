import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Compute a square root using newton's iterative method.
 *
 *
 * @author Beckham Paul
 *
 */
public final class Newton2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     * Edge guards for 0 value
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double guess = x;
        final double error = .0001;

        if (x == 0) {
            return 0.0;
        }

        guess = (guess + x / guess) / 2;

        while (Math.abs((guess * guess - x)) / x >= error * error) {
            guess = (guess + x / guess) / 2;
        }
        return guess;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println("Do you wish to compute a square root? (y/n)");
        char acceptance = in.nextLine().charAt(0);

        while (acceptance == 'y' || acceptance == 'Y') {

            out.println(
                    "Input a positive number to compure the square root of:");
            double value = in.nextDouble();

            out.println("The root of " + value + " is " + sqrt(value) + ".");
            out.println("Do you wish to compute another square root?");

            acceptance = in.nextLine().charAt(0);
        }

        out.println("Goodbye");

        in.close();
        out.close();
    }

}
