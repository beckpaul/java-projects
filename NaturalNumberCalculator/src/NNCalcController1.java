import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Beckham Paul
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        // Update displays based on changes to model
        NaturalNumber top = model.top();
        view.updateTopDisplay(top);
        NaturalNumber bot = model.bottom();
        view.updateBottomDisplay(bot);

        Boolean canSub = false, canDiv = false, canPower = false,
                canRoot = false;

        // Alter values used to update button state

        // Subtraction
        if (bot.compareTo(top) < 0) {
            canSub = true;
        }

        //Division
        if (bot.compareTo(new NaturalNumber2(0)) > 0) {
            canDiv = true;
        }

        // Root
        if (bot.compareTo(INT_LIMIT) <= 0 && bot.compareTo(TWO) >= 0) {
            canRoot = true;
        }

        // Power
        if (bot.compareTo(INT_LIMIT) <= 0) {
            canPower = true;
        }

        // Update view buttons
        view.updateSubtractAllowed(canSub);
        view.updateDivideAllowed(canDiv);
        view.updateRootAllowed(canRoot);
        view.updatePowerAllowed(canPower);
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    /**
     * Updates output display by clearing it.
     *
     */
    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Swaps and updates output and input displays.
     */
    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber bot = this.model.bottom(), top = this.model.top();
        /*
         * Update model in response to this event
         */
        top.copyFrom(bot);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber bot = this.model.bottom(), top = this.model.top();
        /*
         * Update model in response to this event
         */
        top.add(bot);
        bot.transferFrom(top);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSubtractEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber bot = this.model.bottom(), top = this.model.top();
        /*
         * Update model in response to this event
         */
        top.subtract(bot);
        bot.transferFrom(top);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processMultiplyEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber bot = this.model.bottom(), top = this.model.top();
        /*
         * Update model in response to this event
         */
        top.multiply(bot);
        bot.transferFrom(top);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processDivideEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber bot = this.model.bottom(), top = this.model.top();
        /*
         * Update model in response to this event
         */
        top.divide(bot);
        bot.transferFrom(top);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber bot = this.model.bottom(), top = this.model.top();
        /*
         * Update model in response to this event
         */
        top.power(bot.toInt());
        bot.transferFrom(top);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber bot = this.model.bottom(), top = this.model.top();
        /*
         * Update model in response to this event
         */
        top.root(bot.toInt());
        bot.transferFrom(top);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Updates output display based on integer provided as argument.
     *
     * @param digit
     *            value to add to right side of output display
     */
    @Override
    public void processAddNewDigitEvent(int digit) {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bot = this.model.bottom();
        bot.multiplyBy10(digit);
        /*
         * Update model in response to this event
         */
        updateViewToMatchModel(this.model, this.view);
    }

}
