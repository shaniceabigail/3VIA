package seedu.address.model.tab;

import static java.util.Objects.requireNonNull;

/**
 * Represents a tab in 3VIA.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTabListed(String)}
 */
public class Tab extends javafx.scene.control.Tab {
    private String _tabName;

    public Tab(String tabName) {
        requireNonNull(tabName);
        _tabName = tabName;
    }

    @Override
    private
}