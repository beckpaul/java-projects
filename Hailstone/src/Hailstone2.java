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
public final class Hailstone2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone2() {
    }

    /**
     * Generates and outputs the Hailstone series, and it's length starting with
     * the given integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     *
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int length = 1;
        while (n != 1) {
            out.print(n + ", ");
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = (3 * n) + 1;
            }
            length++;
        }
        out.println(n);
        out.println("Length: " + length);
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
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
