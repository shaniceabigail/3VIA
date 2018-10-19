package seedu.address.testutil;

import seedu.address.model.test.TriviaResult;
import seedu.address.model.test.TriviaResultList;

/**
 * A utility class to build a TriviaResultList class.
 */
public class TriviaResultListBuilder {
    private TriviaResultList triviaResultList;

    public TriviaResultListBuilder() {
        triviaResultList = new TriviaResultList();
    }

    public TriviaResultListBuilder(TriviaResultList triviaResultList) {
        this.triviaResultList = triviaResultList;
    }

    /**
     * Adds a new {@code TriviaResult} to the {@code TriviaResultList} that we are building.
     */
    public TriviaResultListBuilder withTriviaResult(TriviaResult triviaResult) {
        triviaResultList.addTriviaResult(triviaResult);
        return this;
    }

    public TriviaResultList build() {
        return triviaResultList;
    }
}
