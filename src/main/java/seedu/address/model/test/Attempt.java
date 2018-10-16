package seedu.address.model.test;

import seedu.address.model.card.Card;
import seedu.address.model.card.Question;

/**
 * A base model for the different kinds of attempts that can be made in different tests.
 * It is also a model for storing the database of attempts made during the various tests.
 */
public class Attempt {
    protected final Card attemptedCard;
    protected final String answer;
    protected final boolean isCorrect;

    public Attempt(Card attemptedCard, String answer, boolean isCorrect) {
        this.attemptedCard = attemptedCard;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public Question getQuestion() {
        return attemptedCard.getQuestion();
    }

    public Card getAttemptedCard() {
        return attemptedCard;
    }

    public String getRawAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
