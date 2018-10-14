package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

/*
 * An event to handle toggling between tabs
 */
public class ToggleTabEvent extends BaseEvent {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
