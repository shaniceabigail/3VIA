package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

/**
 * An UI event to trigger a navigation to learn page.
 */
public class NavigateToLearnPageEvent extends BaseEvent {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
