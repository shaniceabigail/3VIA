package seedu.address.ui.home;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.card.Card;
import seedu.address.ui.UiPart;

/**
 * Displays the information of the card in InfoPanel.
 */
public class CardInfoPanel extends UiPart<Region> {
    private static final String FXML = "home/CardInfoPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Card card;

    @FXML
    private Label cardInfoQuestion;

    public CardInfoPanel() {
        super(FXML);

        registerAsAnEventHandler(this);
    }

    /**
     * Will be called when a card in {@code CardListPanel} is selected.
     */
    public void loadCardPage(Card card) {
        this.card = card;
        cardInfoQuestion.setText(card.getQuestion().value);
    }

}
