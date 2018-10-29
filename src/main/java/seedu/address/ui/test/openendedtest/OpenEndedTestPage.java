package seedu.address.ui.test.openendedtest;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.test.matchtest.MatchTest;
import seedu.address.ui.test.TriviaTestPage;
import seedu.address.ui.test.matchtest.AnswerListPanel;
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

    public OpenEndedTestPage(MatchTest matchTest) {
        super(FXML);

        questionListPanel = new QuestionListPanel(matchTest.getQuestions());
        matchTestQuestionListPanelPlaceholder.getChildren().add(questionListPanel.getRoot());

        answerListPanel = new AnswerListPanel(matchTest.getAnswers());
        matchTestAnswerListPanelPlaceholder.getChildren().add(answerListPanel.getRoot());

        matchTestTopicText.setText(matchTest.getTopic().topicName);
        matchTestDurationText.textProperty().bind(matchTest.getDuration().asString());

        registerAsAnEventHandler(this);
    }

}
