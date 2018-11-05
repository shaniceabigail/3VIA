package seedu.address.ui.test.openendedtest;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.test.openendedtest.OpenEndedTest;
import seedu.address.ui.test.TriviaTestPage;
import seedu.address.ui.test.matchtest.QuestionListPanel;

/**
 * The page that shows up when the user is in a open ended test
 */

public class OpenEndedTestPage extends TriviaTestPage {
    private static final String FXML = "test/openendedtest/OpenEndedTestPage.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    private OpenEndedTest openEndedTest;
    private OpenEndedQuestionPage Qpage;
    private Openanswer Apage;

    @FXML
    private StackPane questionPagePlaceholder;

    @FXML
    private StackPane answerPagePlaceholder;

    @FXML
    private Label openTestTopicText;

    @FXML
    private Label openTestDurationText;

    public OpenEndedTestPage(OpenEndedTest openEndedTest) {
        super(FXML);

        this.openEndedTest = openEndedTest;


        registerAsAnEventHandler(this);
    }

    @Subscribe
    private void handler() {
        openEndedTest.getNextCard();
    }

}
