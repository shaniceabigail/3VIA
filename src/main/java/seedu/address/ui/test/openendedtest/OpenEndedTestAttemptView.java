package seedu.address.ui.test.openendedtest;

import static seedu.address.commons.util.AppUtil.getImage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.model.test.Attempt;
import seedu.address.model.test.matchtest.MatchAttempt;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code MatchAttempt}.
 */
public class OpenEndedTestAttemptView extends UiPart<Region> {
    private static final String FXML = "test/openendedtest/OpenEndedAttemptView.fxml";

    @FXML
    private Label attemptQuestion;

    @FXML
    private Label attemptAnswer;

    @FXML
    private Label attemptUserAnswer;

    public OpenEndedTestAttemptView(Attempt attempt) {
        super(FXML);

        attemptQuestion.setText(attempt.getQuestion().toString());
        attemptAnswer.setText(attempt.getAnswer().toString());
        attemptUserAnswer.setText((attempt.getRawAnswer()));
    }
}