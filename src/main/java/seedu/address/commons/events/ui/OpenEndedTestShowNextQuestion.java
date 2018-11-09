package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.card.Card;

public class OpenEndedTestShowNextQuestion extends BaseEvent {
    public Card card;

    public OpenEndedTestShowNextQuestion(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
