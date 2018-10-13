package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.ui.test.TriviaTestPage;

/**
 * An event requesting to show the trivia test page.
 */
public class ShowTriviaTestViewEvent extends BaseEvent {
    private TriviaTestPage page;

    public ShowTriviaTestViewEvent(TriviaTestPage page) {
        this.page = page;
    }

    public TriviaTestPage getTriviaTestPage() {
        return page;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
