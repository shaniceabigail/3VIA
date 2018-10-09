package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.test.TriviaTest;

/**
 * An event requesting to show the trivia test page.
 */
public class ShowTriviaTestViewEvent extends BaseEvent {
    private TriviaTest test;

    /** For navigation to the matching test page. */
    public ShowTriviaTestViewEvent(TriviaTest test) {
        this.test = test;
    }

    public TriviaTest getTest() {
        return test;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
