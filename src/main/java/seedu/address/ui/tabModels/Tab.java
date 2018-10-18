package seedu.address.ui.tabModels;

import java.lang.String;

/*
 * An abstract Tab class to be inherited by other tabs created
 */

public interface Tab {

    private String Title;


    public String getTabName() {
        return Title;
    }
}
