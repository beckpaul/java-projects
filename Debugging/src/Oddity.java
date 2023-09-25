import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Oddity {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Oddity() {
    }

    /**
     * satisfy annoyance
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        out.println(12345 + 54321);
        out.close();
    }

}