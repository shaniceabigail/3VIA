package seedu.address.ui;

import com.google.common.eventbus.Subscribe;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.NewResultAvailableEvent;

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

//        @Subscribe
//        private void handleNewResultAvailableEvent(NewResultAvailableEvent event) {
//            logger.info(LogsCenter.getEventHandlingLogMessage(event));
//            Platform.runLater(() -> displayed.setValue(event.message));
//        }

}
