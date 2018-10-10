package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * The default landing page of the application.
 */
public class Homepage extends UiPart<Region> {
    private static final String FXML = "Homepage.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private BrowserPanel browserPanel;
    private PersonListPanel personListPanel;
    private CardListPanel cardListPanel;

    @FXML
    private StackPane browserPlaceholder;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane cardListPanelPlaceholder;

    public Homepage(Logic logic) {
        super(FXML);
        browserPanel = new BrowserPanel();
        browserPlaceholder.getChildren().add(browserPanel.getRoot());

        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        cardListPanel = new CardListPanel(logic.getFilteredCardList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        cardListPanelPlaceholder.getChildren().add(cardListPanel.getRoot());

        registerAsAnEventHandler(this);
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    public CardListPanel getCardListPanel() {
        return cardListPanel;
    }

    void releaseResources() {
        browserPanel.freeResources();
    }
}
