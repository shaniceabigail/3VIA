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
import seedu.address.model.card.Answer;
import seedu.address.ui.UiPart;

/**
 * The panel which contains all the answers in a particular test.
 */
public class AnswerListPanel extends UiPart<Region> {
    private static final String FXML = "/test/matchtest/AnswerListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AnswerListPanel.class);

    private final List<Index> displayIndexes;

    @FXML
    private ListView<Answer> matchTestAnswerListView;

    public AnswerListPanel(ObservableList<Answer> answerList, List<Index> displayIndexes) {
        super(FXML);

        this.displayIndexes = displayIndexes;
        setConnections(answerList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Answer> answerList) {
        matchTestAnswerListView.setItems(answerList);
        matchTestAnswerListView.setCellFactory(listView -> new AnswerListViewCell(displayIndexes));
    }

    @Subscribe
    private void handleFlashMatchOutcomeEvent(FlashMatchOutcomeEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        matchTestAnswerListView.setCellFactory(listView ->
                new AnswerListViewCell(displayIndexes, event.indexOfAnswer, event.isCorrect));

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> matchTestAnswerListView.setCellFactory(listView ->
                        new AnswerListViewCell(displayIndexes)));
            };
        }, UiPart.FLASH_TIME);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Card} using a {@code AnswerView}.
     */
    class AnswerListViewCell extends ListCell<Answer> {
        private List<Index> displayIndexes;
        private Integer actualIndexOfAnswer;
        private boolean isCorrect;

        public AnswerListViewCell(List<Index> displayIndexes) {
            this.displayIndexes = displayIndexes;
            actualIndexOfAnswer = null;
        }

        /**
         * Used for defining which cell to flash, with the boolean whether it is correct.
         * @param actualIndexOfAnswer The targetIndex that is needed to be flashed.
         * @param isCorrect Whether the matching card is correct.
         */
        public AnswerListViewCell(List<Index> displayIndexes, int actualIndexOfAnswer, boolean isCorrect) {
            this.displayIndexes = displayIndexes;
            this.actualIndexOfAnswer = actualIndexOfAnswer;
            this.isCorrect = isCorrect;
        }

        @Override
        protected void updateItem(Answer answer, boolean empty) {
            super.updateItem(answer, empty);

            if (empty || answer == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (actualIndexOfAnswer == null || actualIndexOfAnswer != getIndex()) {
                    setGraphic(new AnswerView(answer, displayIndexes.get(getIndex()).getOneBased()).getRoot());
                } else {
                    setGraphic(new AnswerView(answer, displayIndexes.get(getIndex()).getOneBased(),
                            isCorrect).getRoot());
                }
            }
        }
    }
}
