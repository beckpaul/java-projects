import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Beckham Paul
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        int result = 0, val1 = 0, val2 = 0;

        if (!exp.child(0).hasAttribute("value")) {
            val1 = evaluate(exp.child(0));
        } else {
            val1 = Integer.parseInt(exp.child(0).attributeValue("value"));
        }

        if (!exp.child(1).hasAttribute("value")) {
            val2 = evaluate(exp.child(1));
        } else {
            val2 = Integer.parseInt(exp.child(1).attributeValue("value"));
        }

        switch (exp.label()) {
            case "divide":
                result = val1 / val2;
                break;
            case "plus":
                result = val1 + val2;
                break;
            case "minus":
                result = val1 - val2;
                break;
            case "times":
                result = val1 * val2;
                break;
            default:
                break;
        }

        return result;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
