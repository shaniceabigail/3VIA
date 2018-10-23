package seedu.address.commons.events.ui;

import javafx.scene.control.Tab;
import seedu.address.commons.events.BaseEvent;

/**
 * Represents a change in current app state
 */
public class ToggleTabEvent extends BaseEvent {
    private Tab currentTab;

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

