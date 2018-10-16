package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.test.TriviaTestResultList;

/**
 * Indicates the TriviaTestResultList has changed
 */
public class TriviaTestResultsChangedEvent extends BaseEvent {
    public final TriviaTestResultList data;

    public TriviaTestResultsChangedEvent(TriviaTestResultList data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of cards " + data.getResultList().size();
    }
}
