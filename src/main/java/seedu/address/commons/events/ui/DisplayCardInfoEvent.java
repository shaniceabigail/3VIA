package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;
import seedu.address.model.card.Card;

/**
 * Represents a request to display the card info in the InfoPanel.
 */
public class DisplayCardInfoEvent extends BaseEvent {
    private final Card cardToDisplay;

    public DisplayCardInfoEvent(Card cardToDisplay) {
        this.cardToDisplay = cardToDisplay;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Card getCardToDisplay() {
        return cardToDisplay;
    }
}
