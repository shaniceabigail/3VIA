package seedu.address.ui.test.matchtest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.test.Attempt;
import seedu.address.model.test.matchtest.MatchAttempt;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code MatchAttempt}.
 */
public class MatchAttemptView extends UiPart<Region> {
    private static final String FXML = "/test/matchtest/MatchAttemptView.fxml";
    private final Attempt attempt;

    @FXML
    private Label attemptText;

    public MatchAttemptView(MatchAttempt attempt) {
        super(FXML);
        this.attempt = attempt;
        attemptText.setText(attempt.getAttemptedCard().getQuestion() + "->" + attempt.getCardWithAnswer().getAnswer());
    }
}
