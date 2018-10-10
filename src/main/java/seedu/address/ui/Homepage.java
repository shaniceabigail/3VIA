package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.CloseTriviaTestViewEvent;
import seedu.address.commons.events.ui.ShowTriviaTestViewEvent;
import seedu.address.logic.Logic;

/**
 * A class to allow the application to switch between different scenes.
 */
public class Homepage extends UiPart<Region> {
    private static final String FXML = "Homepage.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    // Independent Ui parts residing in this Ui container
    private BrowserPanel browserPanel;
    private PersonListPanel personListPanel;
    private CardListPanel cardListPanel;
    private QuestionListPanel questionListPanel;
    private AnswerListPanel answerListPanel;

    @FXML
    private StackPane browserPlaceholder;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane cardListPanelPlaceholder;

    @FXML
    private StackPane testMQuestionListPanelPlaceholder;

    @FXML
    private StackPane testMAnswerListPanelPlaceholder;

    public Homepage(Logic logic) {
        super(FXML);
        browserPanel = new BrowserPanel();
        browserPlaceholder.getChildren().add(browserPanel.getRoot());

        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        cardListPanel = new CardListPanel(logic.getFilteredCardList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        cardListPanelPlaceholder.getChildren().add(cardListPanel.getRoot());

        registerAsAnEventHandler(this);
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    public CardListPanel getCardListPanel() {
        return cardListPanel;
    }

    void releaseResources() {
        browserPanel.freeResources();
    }

    @Subscribe
    private void handleShowTriviaTestViewEvent(ShowTriviaTestViewEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));

        questionListPanel = new QuestionListPanel(event.getTest().getQuestions());
        testMQuestionListPanelPlaceholder.getChildren().add(questionListPanel.getRoot());

        answerListPanel = new AnswerListPanel(event.getTest().getAnswers());
        testMAnswerListPanelPlaceholder.getChildren().add(answerListPanel.getRoot());
    }

    @Subscribe
    private void handleCloseTriviaTestViewEvent(CloseTriviaTestViewEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));

        testMQuestionListPanelPlaceholder.getChildren().clear();
        testMAnswerListPanelPlaceholder.getChildren().clear();
    }
}
