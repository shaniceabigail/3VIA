package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.test.TriviaResults;

/**
 * Indicates the TriviaResults has changed
 */
public class TriviaResultsChangedEvent extends BaseEvent {
    public final TriviaResults data;

    public TriviaResultsChangedEvent(TriviaResults data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of cards " + data.getResultList().size();
    }
}
