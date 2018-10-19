package seedu.address.testutil;

import static seedu.address.testutil.TypicalAttempts.FLAT_EARTH_CORRECT_ATTEMPT;
import static seedu.address.testutil.TypicalAttempts.FLAT_EARTH_WRONG_ATTEMPT;
import static seedu.address.testutil.TypicalAttempts.FORCE_CORRECT_ATTEMPT;
import static seedu.address.testutil.TypicalAttempts.FORCE_WRONG_ATTEMPT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.test.TriviaResult;
import seedu.address.model.test.TriviaResultList;

/**
 * A utility class containing a list of {@code TriviaResult} objects to be used in tests.
 */
public class TypicalTriviaResults {

    public static final TriviaResult RESULT_ATTEMPT_ALL_CORRECT = new TriviaResultBuilder().withTopic("Physics")
            .withTestDate("18 Oct 2018, 11:30 PM").withDuration("20.0")
            .withAttempts(Arrays.asList(FLAT_EARTH_CORRECT_ATTEMPT, FORCE_CORRECT_ATTEMPT)).build();

    public static final TriviaResult RESULT_ATTEMPT_ALL_WRONG = new TriviaResultBuilder().withTopic("Physics")
            .withTestDate("18 Oct 2018, 11:30 PM").withDuration("20.0")
            .withAttempts(Arrays.asList(FLAT_EARTH_WRONG_ATTEMPT, FORCE_WRONG_ATTEMPT)).build();

    public static final TriviaResult RESULT_ATTEMPT_MIX = new TriviaResultBuilder().withTopic("Physics")
            .withTestDate("18 Oct 2018, 11:30 PM").withDuration("20.0")
            .withAttempts(Arrays.asList(FLAT_EARTH_WRONG_ATTEMPT, FORCE_CORRECT_ATTEMPT)).build();

    private TypicalTriviaResults() {} // prevents instantiation

    /**
     * Returns an {@code TriviaResultList} with all the typical TriviaResults.
     */
    public static TriviaResultList getTypicalTriviaResultList() {
        TriviaResultList resultList = new TriviaResultList();
        for (TriviaResult result : getTypicalTriviaResults()) {
            resultList.addTriviaResult(result);
        }
        return resultList;
    }

    public static List<TriviaResult> getTypicalTriviaResults() {
        return new ArrayList<>(Arrays.asList(RESULT_ATTEMPT_ALL_CORRECT, RESULT_ATTEMPT_ALL_WRONG, RESULT_ATTEMPT_MIX));
    }
}
