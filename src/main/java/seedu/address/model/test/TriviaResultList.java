package seedu.address.model.test;

import static java.util.Objects.requireNonNull;
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
     * Creates a TriviaResultList using the TriviaResultList in the {@code toBeCopied}
     */
    public TriviaResultList(TriviaResultList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the ResultList with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setTriviaResults(List<TriviaResult> results) {
        triviaResults.clear();
        triviaResults.addAll(results);

        attemptsOfCards.clear();
        for (TriviaResult result : results) {
            mapCardsToAttempts(result.getAttempts());
        }
    }

    /**
     * Resets the existing data of this {@code TriviaBundleList} with {@code newData}.
     */
    public void resetData(TriviaResultList newData) {
        requireNonNull(newData);

        setTriviaResults(newData.getResultList());
    }

    /**
     * Adds a triviaResult into the list of triviaResults
     */
    public void addTriviaResult(TriviaResult triviaResult) {
        requireAllNonNull(triviaResult);

        triviaResults.add(triviaResult);
        mapCardsToAttempts(triviaResult.getAttempts());
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TriviaResultList // instanceof handles nulls
                && triviaResults.equals(((TriviaResultList) other).triviaResults));
    }

}
