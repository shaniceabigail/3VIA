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
public class TriviaResultList {
    private final List<TriviaResult> triviaResults;
    private final HashMap<Card, List<Attempt>> attemptsOfCards;

    public TriviaResultList() {
        triviaResults = new ArrayList<>();
        attemptsOfCards = new HashMap<>();
    }

    /**
     * Adds a triviaResult into the list of triviaResults
     */
    public void addTriviaResult(TriviaResult triviaResult) {
        requireAllNonNull(triviaResult);

        triviaResults.add(triviaResult);
        mapCardsToAttempts(triviaResult.attempts);
    }

    public List<TriviaResult> getResultList() {
        return triviaResults;
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
