package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.test.TriviaResultList;

/**
 * Indicates the TriviaResultList has changed
 */
public class TriviaResultsChangedEvent extends BaseEvent {
    public final TriviaResultList data;

    public TriviaResultsChangedEvent(TriviaResultList data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of cards " + data.getResultList().size();
    }
}
