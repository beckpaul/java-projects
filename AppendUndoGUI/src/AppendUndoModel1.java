import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Model class.
 *
 * @author Beckham Paul
 */
public final class AppendUndoModel1 implements AppendUndoModel {

    /**
     * Model variable.
     */
    private String input;
    /**
     * Model variable.
     */
    private Stack<String> output = new Stack1L<>();

    /**
     * Default constructor.
     */
    public AppendUndoModel1() {
        /*
         * Initialize model; both variables start as empty strings
         */
        this.input = "";
        this.output.push("");
    }

    @Override
    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String input() {
        return this.input;
    }

    /**
     * Updates this.output.
     *
     * @aliases reference returned by {@code output}
     * @ensures <pre>
     * {@code output = this.output}
     * </pre>
     */
    public void setOutput(Stack<String> output) {
        this.output = output;
    }

    @Override
    public Stack<String> output() {
        return this.output;
    }

}
