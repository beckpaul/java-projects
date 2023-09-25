import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Output a hailstone series given an input
 *
 * @author Beckham Paul
 *
 */
public final class Hailstone4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone4() {
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
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println("Input a positive integer:");
        int n = in.nextInteger();
        generateSeries(n, out);

        out.println("Do you wish to compute another series: ");
        char consent = in.nextLine().charAt(0);
        while (consent == 'y') {
            out.println("Input a positive integer:");
            n = in.nextInteger();

            generateSeries(n, out);

            out.println("Do you wish to compute another series: ");
            consent = in.nextLine().charAt(0);
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
