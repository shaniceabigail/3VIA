package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represent an answer to a question.
 */
public class Answer {
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
