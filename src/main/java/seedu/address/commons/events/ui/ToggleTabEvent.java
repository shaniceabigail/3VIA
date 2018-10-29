package seedu.address.commons.events.ui;

import javafx.scene.control.Tab;
import seedu.address.commons.events.BaseEvent;

/**
 * An event requesting to toggle a tab
 */
public class ToggleTabEvent extends BaseEvent {
    private Tab toToggleTo;

    ToggleTabEvent(Tab tabToGo) {
        toToggleTo = tabToGo;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
