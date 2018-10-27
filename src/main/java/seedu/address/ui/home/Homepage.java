package seedu.address.ui.home;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.ui.UiPart;

/**
 * The default landing page of the application.
 */
public class Homepage extends UiPart<Region> {
    private static final String FXML = "home/Homepage.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private InfoPanel infoPanel;
    private CardListPanel cardListPanel;

    @FXML
    private StackPane infoPanelPlaceholder;

    @FXML
    private StackPane cardListPanelPlaceholder;

    public Homepage(Logic logic) {
        super(FXML);
        infoPanel = new InfoPanel();
        infoPanelPlaceholder.getChildren().add(infoPanel.getRoot());

        cardListPanel = new CardListPanel(logic.getFilteredCardList());
        cardListPanelPlaceholder.getChildren().add(cardListPanel.getRoot());

        registerAsAnEventHandler(this);
    }

    public CardListPanel getCardListPanel() {
        return cardListPanel;
    }

    public void releaseResources() {
        infoPanel.freeResources();
    }
}
