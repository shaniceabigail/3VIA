package seedu.address.ui.home;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.DisplayImportHelpChangedEvent;
import seedu.address.ui.UiPart;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ImportHelpDisplay extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(ImportHelpDisplay.class);
    private static final String FXML = "ImportHelpDisplay.fxml";

    @FXML
    private TextArea importHelp;

    public ImportHelpDisplay() {
        super(FXML);
        importHelp.setText("Bye World.");
        registerAsAnEventHandler(this);
    }

    @Subscribe
    private void handleDisplayImportHelpEvent(DisplayImportHelpChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        importHelp.setVisible(event.isImportHelpDisplayVisible());
    }
}
