import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Output a hailstone series given an input
 *
 * @author Beckham Paul
 *
 */
public final class Hailstone5 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone5() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer. Outputs the length of the series. Outputs the maximum of the
     * series.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     *
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int length = 1;
        int max = n;
        while (n != 1) {
            out.print(n + ", ");
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = (3 * n) + 1;
            }
            if (n > max) {
                max = n;
            }
            length++;
        }
        out.println(n);
        out.println("Length: " + length);
        out.println("Max: " + max);
    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        out.println("Input a positive integer:");
        String input = in.nextLine();

        while (!FormatChecker.canParseInt(input)
                || Integer.parseInt(input) <= 0) {
            out.println("Input a positive integer:");
            input = in.nextLine();
        }

        return Integer.parseInt(input);
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
        int n;

        n = getPositiveInteger(in, out);
        generateSeries(n, out);

        out.println("Do you wish to compute another series: ");
        char consent = in.nextLine().charAt(0);
        while (consent == 'y') {
            n = getPositiveInteger(in, out);
            generateSeries(n, out);

            out.println("Do you wish to compute another series: ");
            consent = in.nextLine().charAt(0);
        }

        /*
         * Close input and output streams
         */
        out.println("goodbye");
        in.close();
        out.close();
    }

}
