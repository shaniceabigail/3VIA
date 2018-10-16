package seedu.address.model.test;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Date;
import java.util.List;

import seedu.address.model.topic.Topic;

/**
 * A class that is being used to store the data of the trivia test.
 */
public class TriviaTestResult {
    public static final String INCOMPLETE_TRIVIA_TEST_MESSAGE =
            "Trivia test have to completed before it can be recorded.";

    public final TestType testType;
    public final Topic topic;
    public final List<? extends Attempt> attempts;
    public final Date testDate;
    public final double duration;


    public TriviaTestResult(TriviaTest triviaTest) {
        checkArgument(triviaTest.isCompleted(), INCOMPLETE_TRIVIA_TEST_MESSAGE);

        testType = triviaTest.getTestType();
        topic = triviaTest.getTopic();
        attempts = triviaTest.getAttempts();
        testDate = triviaTest.getTestDate();
        duration = triviaTest.getDuration();
    }

    public TriviaTestResult(TestType testType, Topic topic, Date testDate, double duration,
                            List<? extends Attempt> attempts) {
        this.testType = testType;
        this.topic = topic;
        this.attempts = attempts;
        this.testDate = testDate;
        this.duration = duration;
    }
}
