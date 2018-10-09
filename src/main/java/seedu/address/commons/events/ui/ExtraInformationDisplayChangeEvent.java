package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.card.Card;

/**
 * Represents the change in information presented.
 *
 */
public class ExtraInformationDisplayChangeEvent extends BaseEvent {
    private String toDisplay;

    public ExtraInformationDisplayChangeEvent(String toDisplay) {
        this.toDisplay = toDisplay;
    }

    public String getToDisplay() {
        return toDisplay;
    }
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
