package seedu.address.commons.events.model;

import seedu.address.commons.events.BaseEvent;

/**
 * Updates the AppState in model when user clicks
 */

public class TabClickEvent extends BaseEvent {
    private String prevTab;
    private String updatedTab;

    public TabClickEvent() {
        this("learn", "test");
    }

    public TabClickEvent(String oldValue, String newValue) {
        prevTab = oldValue;
        updatedTab = newValue;
    }

    public String getPrevTab() {
        return prevTab;
    }

    public String getUpdatedTab() {
        return updatedTab;
    }

    @Override
    public String toString() {
        return ("Current tab selected is " + prevTab
        + ", ToGo tab is " + updatedTab + "/n");
    }
}
