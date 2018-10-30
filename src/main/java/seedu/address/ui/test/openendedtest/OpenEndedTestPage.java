package seedu.address.ui.test.openendedtest;

import java.util.logging.Logger;

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

    private QuestionListPanel questionListPanel;

    @javafx.fxml.FXML
    private StackPane matchTestQuestionListPanelPlaceholder;

    @FXML
    private StackPane matchTestAnswerListPanelPlaceholder;

    @FXML
    private Label matchTestTopicText;

    @FXML
    private Label matchTestDurationText;

    public OpenEndedTestPage(OpenEndedTest openEndedTest) {
        super(FXML);

        //questionListPanel = new QuestionListPanel(openEndedTest.getQuestions());
        matchTestQuestionListPanelPlaceholder.getChildren().add(questionListPanel.getRoot());

        matchTestTopicText.setText(openEndedTest.getTopic().topicName);
        matchTestDurationText.textProperty().bind(openEndedTest.getDuration().asString());

        registerAsAnEventHandler(this);
    }

}
