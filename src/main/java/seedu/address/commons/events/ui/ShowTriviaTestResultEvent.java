package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.test.TriviaTest;

/**
 * An event requesting to show the result of trivia test page.
 */
public class ShowTriviaTestResultEvent extends BaseEvent {
    private final TriviaTest triviaTest;

    public ShowTriviaTestResultEvent(TriviaTest triviaTest) {
        this.triviaTest = triviaTest;
    }

    public TriviaTest getTriviaTest() {
        return triviaTest;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
