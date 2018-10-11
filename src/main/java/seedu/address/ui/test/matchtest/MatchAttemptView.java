package seedu.address.ui.test.matchtest;

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
public class MatchAttemptView extends UiPart<Region> {
    private static final String FXML = "/test/matchtest/MatchAttemptView.fxml";
    private final Attempt attempt;

    @FXML
    private Label id;

    @FXML
    private Label attemptText;

    @FXML
    private ImageView correctnessIcon;

    public MatchAttemptView(MatchAttempt attempt, int displayedIndex) {
        super(FXML);
        this.attempt = attempt;

        id.setText(displayedIndex + ". ");
        attemptText.setText(attempt.getAttemptedCard().getQuestion() + " ---> "
                + attempt.getCardWithAnswer().getAnswer());

        if (attempt.isCorrect()) {
            correctnessIcon.setImage(getImage("/images/tick_icon.png"));
        } else {
            correctnessIcon.setImage(getImage("/images/cross_icon.png"));
        }
    }
}
