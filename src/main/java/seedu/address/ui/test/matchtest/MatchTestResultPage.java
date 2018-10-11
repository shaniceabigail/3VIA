package seedu.address.ui.test.matchtest;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.test.matchtest.MatchTest;
import seedu.address.ui.test.TriviaTestResultPage;

/**
 * The page that shows up after the user has completed a matching test.
 */
public class MatchTestResultPage extends TriviaTestResultPage {
    private static final String FXML = "/test/matchtest/MatchTestResultPage.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    public MatchTestResultPage(MatchTest matchTest) {
        super(FXML);

        registerAsAnEventHandler(this);
    }
}
