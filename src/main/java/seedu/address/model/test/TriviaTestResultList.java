package seedu.address.model.test;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import seedu.address.model.card.Card;

/**
 * Contains the mapping of all the test.
 */
public class TriviaTestResultList {
    private final List<TriviaTestResult> triviaTestResults;
    private final HashMap<Card, List<Attempt>> attemptsOfCards;

    public TriviaTestResultList() {
        triviaTestResults = new ArrayList<>();
        attemptsOfCards = new HashMap<>();
    }

    /**
     * Adds a triviaTestResult into the list of triviaTestResults
     */
    public void addTriviaTestResult(TriviaTestResult triviaTestResult) {
        requireAllNonNull(triviaTestResult);

        triviaTestResults.add(triviaTestResult);
        mapCardsToAttempts(triviaTestResult.attempts);
    }

    public List<TriviaTestResult> getResultList() {
        return triviaTestResults;
    }

    /**
     * Will map cards to attempts for efficient retrieval of attempts by card.
     */
    private void mapCardsToAttempts(List<? extends Attempt> attempts) {
        for (Attempt attempt : attempts) {
            List<Attempt> existingAttemptsByCard = attemptsOfCards.get(attempt.attemptedCard);
            if (existingAttemptsByCard == null) {
                attemptsOfCards.put(attempt.attemptedCard, new ArrayList<>(Arrays.asList(attempt)));
            } else {
                existingAttemptsByCard.add(attempt);
            }
        }
    }

}
