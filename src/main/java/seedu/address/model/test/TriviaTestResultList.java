package seedu.address.model.test;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the mapping of all the test.
 */
public class TriviaTestResultList {
    private final List<TriviaTestResult> triviaTestResults;

    public TriviaTestResultList() {
        triviaTestResults = new ArrayList<>();
    }

    /**
     * Adds a triviaTestResult into the list of triviaTestResults
     */
    public void addTriviaTestResult(TriviaTestResult triviaTestResult) {
        requireAllNonNull(triviaTestResult);
        triviaTestResults.add(triviaTestResult);
    }

    public List<TriviaTestResult> getResultList() {
        return triviaTestResults;
    }
}
