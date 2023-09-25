import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Approximate u using the de Jager formula and user input.
 *
 * @author Beckham Paul
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

//    private static boolean isDoubleGrreaterThan(double value) {
//        return FormatChecker.canParseDouble(value) && Double.parseDouble(value)
//    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.println("Enter a positive value for u: ");
        String value = in.nextLine();
        while (!FormatChecker.canParseDouble(value)
                || Double.parseDouble(value) <= 0) {
            out.println("Try again: ");
            value = in.nextLine();
        }

        return Double.parseDouble(value);
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        out.println("Enter a positive value that is not one: ");
        String value = in.nextLine();
        while (!FormatChecker.canParseDouble(value)
                || Double.parseDouble(value) <= 0
                || Double.parseDouble(value) == 1.0) {
            out.println("Try again: ");
            value = in.nextLine();
        }

        return Double.parseDouble(value);
    }

    /**
     * User the Jagerval method to approximate a value, and output error
     *
     * @param userVal
     *            value to guess
     * @param personalNumbers
     *            values used to guess
     * @param out
     *            the output stream
     */

    private static void calcValuesWithJager(double userVal,
            double[] personalNumbers, SimpleWriter out) {
        double[] jagerVals = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3, -1 / 4, 0,
                1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };

        int a, b, c, d;

        int max = jagerVals.length - 1;

        double error, diff, newDiff;
        //Set an initial difference to compare to and set again
        diff = ((Math.pow(personalNumbers[0], jagerVals[0]))
                * (Math.pow(personalNumbers[1], jagerVals[0]))
                * (Math.pow(personalNumbers[2], jagerVals[0]))
                * (Math.pow(personalNumbers[3], jagerVals[0]))) - userVal;

        for (d = 0; d <= max; d++) {
            for (c = 0; c <= max; c++) {
                for (b = 0; b <= max; b++) {
                    for (a = 0; a <= max; a++) {
                        newDiff = ((Math.pow(personalNumbers[0], jagerVals[a]))
                                * (Math.pow(personalNumbers[1], jagerVals[b]))
                                * (Math.pow(personalNumbers[2], jagerVals[c]))
                                * (Math.pow(personalNumbers[3], jagerVals[d])))
                                - userVal;
                        if (Math.abs(newDiff) < Math.abs(diff)) {
                            diff = newDiff;
                        }
                    }
                    a = 0;
                }
                b = 0;
            }
            c = 0;
        }

        out.print("Value to guess: ");
        out.print(userVal, 0, false);

        out.print("Error: ");
        out.println(((diff / userVal) * 100), 5, false);

        out.print("Final guess: ");
        out.println((userVal + diff), 0, false);

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

        // Get value to approximate
        double userVal = getPositiveDouble(in, out);

        // Get personal numbers, set into array - placeholder garbage
        // Should modify and use something mutable
        double[] personalNumbers = { 0.0, 0.0, 0.0, 0.0 };
        int count = 0;
        while (count < personalNumbers.length) {
            personalNumbers[count] = getPositiveDoubleNotOne(in, out);
            count++;
        }
        calcValuesWithJager(userVal, personalNumbers, out);

        in.close();
        out.close();
    }

}
