package seedu.address.ui.test.matchtest;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.ui.FlashMatchOutcomeEvent;
import seedu.address.model.card.Question;
import seedu.address.ui.UiPart;

/**
 * The panel which contains all the questions in a particular test.
 */
public class QuestionListPanel extends UiPart<Region> {
    private static final String FXML = "/test/matchtest/QuestionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(QuestionListPanel.class);

    private final List<Index> displayIndexes;

    @FXML
    private ListView<Question> matchTestQuestionListView;

    public QuestionListPanel(ObservableList<Question> questionList, List<Index> displayIndexes) {
        super(FXML);

        this.displayIndexes = displayIndexes;
        setConnections(questionList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Question> questionList) {
        matchTestQuestionListView.setItems(questionList);
        matchTestQuestionListView.setCellFactory(listView -> new QuestionListViewCell(displayIndexes));
    }

    @Subscribe
    private void handleFlashMatchOutcomeEvent(FlashMatchOutcomeEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        matchTestQuestionListView.setCellFactory(listView -> new QuestionListViewCell(displayIndexes,
                event.indexOfQuestion, event.isCorrect));

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() ->
                            matchTestQuestionListView.setCellFactory(listView ->
                                    new QuestionListViewCell(displayIndexes)));
            };
        }, UiPart.FLASH_TIME);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Card} using a {@code QuestionView}.
     */
    class QuestionListViewCell extends ListCell<Question> {
        private List<Index> displayIndexes;
        private Integer actualIndexOfQuestion;
        private boolean isCorrect;

        public QuestionListViewCell(List<Index> displayIndexes) {
            actualIndexOfQuestion = null;
            this.displayIndexes = displayIndexes;
        }

        /**
         * Used for defining which cell to flash, with the boolean whether it is correct.
         * @param actualIndexOfQuestion The targetIndex that is needed to be flashed.
         * @param isCorrect Whether the matching card is correct.
         */
        public QuestionListViewCell(List<Index> displayIndexes, int actualIndexOfQuestion, boolean isCorrect) {
            this.actualIndexOfQuestion = actualIndexOfQuestion;
            this.isCorrect = isCorrect;
            this.displayIndexes = displayIndexes;
        }

        @Override
        protected void updateItem(Question question, boolean empty) {
            super.updateItem(question, empty);

            if (empty || question == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (actualIndexOfQuestion == null || actualIndexOfQuestion != getIndex()) {
                    setGraphic(new QuestionView(question, displayIndexes.get(getIndex()).getOneBased()).getRoot());
                } else {
                    setGraphic(new QuestionView(question, displayIndexes.get(getIndex()).getOneBased(),
                            isCorrect).getRoot());
                }
            }
        }
    }
}
