package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.ui.test.TriviaTestResultPage;

/**
 * An event requesting to show the result of trivia test page.
 */
public class ShowTriviaTestResultEvent extends BaseEvent {
    private final TriviaTestResultPage page;

    public ShowTriviaTestResultEvent(TriviaTestResultPage page) {
        this.page = page;
    }

    public TriviaTestResultPage getTriviaTestResultPage() {
        return page;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
