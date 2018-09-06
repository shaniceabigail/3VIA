package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represent an question to a card.
 */
public class Question {
    public final String value;

    /**
     * Constructs a {@code question}.
     *
     * @param question A question.
     */
    public Question(String question) {
        requireNonNull(question);
        this.value = question;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Question
            && value.equals(((Question) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
