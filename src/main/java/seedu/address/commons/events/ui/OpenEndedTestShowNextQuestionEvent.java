package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.card.Card;

public class OpenEndedTestShowNextQuestionEvent extends BaseEvent {
    public Card card;

    public OpenEndedTestShowNextQuestionEvent(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
