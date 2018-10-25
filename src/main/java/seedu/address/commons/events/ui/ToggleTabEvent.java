package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

public class ToggleTabEvent extends BaseEvent {
    private boolean isTest = false;

    public ToggleTabEvent() {

    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
