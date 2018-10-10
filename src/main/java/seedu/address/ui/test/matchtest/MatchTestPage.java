package seedu.address.ui.test.matchtest;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.test.matchtest.MatchTest;
import seedu.address.ui.UiPart;

/**
 * The page that shows up when the user is in a matching test.
 */
public class MatchTestPage extends UiPart<Region> {
    private static final String FXML = "/test/matchtest/MatchTestPage.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private QuestionListPanel questionListPanel;
    private AnswerListPanel answerListPanel;

    @FXML
    private StackPane testMQuestionListPanelPlaceholder;

    @FXML
    private StackPane testMAnswerListPanelPlaceholder;

    public MatchTestPage(MatchTest matchTest) {
        super(FXML);

        questionListPanel = new QuestionListPanel(matchTest.getQuestions());
        testMQuestionListPanelPlaceholder.getChildren().add(questionListPanel.getRoot());

        answerListPanel = new AnswerListPanel(matchTest.getAnswers());
        testMAnswerListPanelPlaceholder.getChildren().add(answerListPanel.getRoot());
        registerAsAnEventHandler(this);
    }

    /**
     * Will remove all existing questions and answers.
     */
    public void clearCards() {
        testMQuestionListPanelPlaceholder.getChildren().clear();
        testMAnswerListPanelPlaceholder.getChildren().clear();
    }
}
