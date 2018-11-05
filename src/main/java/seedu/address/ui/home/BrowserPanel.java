package seedu.address.ui.home;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.card.Card;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    public static final String DEFAULT_PAGE = "default.html";
    public static final String DEFAULT_SEARCH_PAGE_URL = "https://www.google.com.sg/search?q=";
    public static final String SECONDARY_SEARCH_PAGE_URL = "https://duckduckgo.com/?q=";
    public static final String NO_CONNECTION_PAGE = "no_connection.html";
    public static final String WEB_ERROR_PAGE = "web_error.html";

    private static final String FXML = "home/BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private WebView browser;

    public BrowserPanel() {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        loadDefaultPage();
        registerAsAnEventHandler(this);
    }

    private void loadPersonPage(Person person) {
        loadPage(DEFAULT_SEARCH_PAGE_URL + person.getName().fullName);
    }

    /**
     * Load the research page of the given card.
     */
    public void loadCardPage(Card card) {
        if (isConnectedToInternet()) {
            String primaryUrl = DEFAULT_SEARCH_PAGE_URL + card.getQuestion().value;
            String secondaryUrl = SECONDARY_SEARCH_PAGE_URL + card.getQuestion().value;
            loadPage(primaryUrl, secondaryUrl);
        } else {
            URL noConnectionPage = MainApp.class.getResource(FXML_FILE_FOLDER + NO_CONNECTION_PAGE);
            loadPage(noConnectionPage.toExternalForm());
        }
    }

    /**
     * Loads the page with the given search param
     */
    public void loadPage(String ...urls) {
        Platform.runLater(() -> {
            for (String url : urls) {
                browser.getEngine().load(url);
                if (browser.getEngine().getLocation().equals(url)) {
                    break;
                }
            }
            if (!Arrays.asList(urls).contains(browser.getEngine().getLocation())) {
                browser.getEngine().load(FXML_FILE_FOLDER + WEB_ERROR_PAGE);
            }
        });
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        URL defaultPage = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        loadPage(defaultPage.toExternalForm());
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

    /**
     * Checks whether there is internet connection by pinging google.com.
     * @return true if there is an internet connection, else return false.
     */
    private boolean isConnectedToInternet() {
        try {
            if (InetAddress.getByName("www.google.com").isReachable(500)) {
                return true;
            } else {
                return false;
            }
        } catch (IOException ioe) {
            logger.warning("The checking of internet connectivity was interrupted.");
            return false;
        }
    }

    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadPersonPage(event.getNewSelection());
    }
}
