/**
 * Controller class.
 *
 * @author Beckham Paul
 */
public final class AppendUndoController1 implements AppendUndoController {

    /**
     * Model object.
     */
    private final AppendUndoModel model;

    /**
     * View object.
     */
    private final AppendUndoView view;

    /**
     * Updates view to display model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     */
    private static void updateViewToMatchModel(AppendUndoModel model,
            AppendUndoView view) {
        /*
         * Get model info
         */
        String input = model.input();
        // Get top string off stack
        String output = model.output().top();
        /*
         * Update view to reflect changes in model
         */

        view.updateInputDisplay(input);
        view.updateOutputDisplay(output);
    }

    /**
     * Constructor; connects {@code this} to the model and view it coordinates.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public AppendUndoController1(AppendUndoModel model, AppendUndoView view) {
        this.model = model;
        this.view = view;
        /*
         * Update view to reflect initial value of model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes reset event.
     */
    @Override
    public void processResetEvent() {
        /*
         * Update model in response to this event
         */

        //Clear and restore default for output
        this.model.output().clear();
        this.model.output().push("");
        // Set to initial input
        this.model.setInput("");
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes copy event.
     *
     * @param input
     *            value of input text (provided by view)
     */
    public void processCopyEvent(String input) {
        /*
         * Update model in response to this event
         */
        this.model.setInput(input);
        this.model.output().push(input);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAppendEvent(String input) {
        String currentVal = this.model.output().top();
        this.model.output().push(currentVal + input);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processUndoEvent() {
        String currentVal = this.model.output().pop();
        this.model.setInput(currentVal);

        this.model.output().push("");
        updateViewToMatchModel(this.model, this.view);
    }

}
