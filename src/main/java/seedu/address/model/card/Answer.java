package seedu.address.model.card;

import static java.util.Objects.requireNonNull;

/**
 * Represent an answer to a question.
 */
public class Answer {
    public static final String MESSAGE_ANSWER_CONSTRAINTS = "Answer must not be an empty string.";
    public final String value;

    /**
     * Constructs a {@code answer}.
     *
     * @param answer A answer.
     */
    public Answer(String answer) {
        requireNonNull(answer);
        this.value = answer;
    }

    /**
     * Returns if a given string is a valid answer.
     */
    public static boolean isValidAnswer(String test) {
        return !test.equals("");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Answer
                && value.equals(((Answer) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
