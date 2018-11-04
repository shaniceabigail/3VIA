package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.NavigateToLearnPageEvent;
import seedu.address.commons.events.ui.ShowTriviaTestResultEvent;
import seedu.address.commons.events.ui.ShowTriviaTestViewEvent;
import seedu.address.logic.Logic;
import seedu.address.ui.home.Homepage;
import seedu.address.ui.test.TriviaTestPlaceholderPage;

/**
 * A Ui class that is responsible for storing the placeholders for all the pages that exist in the MainDisplay.
 */
public class MainDisplay extends UiPart<Region> {

    private static final String FXML = "MainDisplay.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Homepage homepage;
    private final TriviaTestPlaceholderPage triviaTestPlaceholderPage;

    @FXML
    private StackPane mainDisplay;
    @FXML
    private StackPane homepagePlaceholder;
    @FXML
    private StackPane triviaTestPlaceholder;

    public MainDisplay(Logic logic) {
        super(FXML);

        homepage = new Homepage(logic);
        homepagePlaceholder.getChildren().add(homepage.getRoot());

        triviaTestPlaceholderPage = new TriviaTestPlaceholderPage();
        triviaTestPlaceholder.getChildren().add(triviaTestPlaceholderPage.getRoot());

        bindNodesVisibilityProperty(mainDisplay);
        resetToOriginalState();
        registerAsAnEventHandler(this);
    }

    /**
     * Hides all the other pages except for the homepage. The default page should be the homepage.
     */
    public void resetToOriginalState() {
        mainDisplay.getChildren().forEach(node -> node.setVisible(false));
        homepagePlaceholder.setVisible(true);
    }

    /**
     * Change the given panel to the given UI region.
     */
    private void changeToScene(Node changeTo) {
        mainDisplay.getChildren().forEach(node -> {
            if (node.equals(changeTo)) {
                if (changeTo.equals(homepage.getRoot())) {
                    homepage.resetToOriginalState();
                }
                node.setVisible(true);
            } else {
                node.setVisible(false);
            }
        });
    }

    public void releaseResources() {
        homepage.releaseResources();
    }

    @Subscribe
    private void handleShowTriviaTestViewEvent(ShowTriviaTestViewEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        changeToScene(triviaTestPlaceholder);
    }

    @Subscribe
    private void handleShowTriviaTestResultPage(ShowTriviaTestResultEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        changeToScene(triviaTestPlaceholder);
    }

    @Subscribe
    private void handleNavigateToLearnPageEvent(NavigateToLearnPageEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        changeToScene(homepagePlaceholder);
    }
}
