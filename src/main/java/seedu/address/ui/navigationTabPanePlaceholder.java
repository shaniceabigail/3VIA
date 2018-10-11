package seedu.address.ui;

import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

import java.net.URL;
import java.util.logging.Logger;

/*
 * The UI component that is responsible for navigation
 *
 */

public class navigationTabPanePlaceholder extends UiPart<Region> {

    private static final String FXML = "NavigationTabPane.fxml";
    private final Logger logger = LogsCenter.getLogger(navigationTabPanePlaceholder.class);
    private final Logic logic;


    public navigationTabPanePlaceholder(URL fxmlFileUrl, Logic logic) {
        super(fxmlFileUrl);
        this.logic = logic;
    }
}
