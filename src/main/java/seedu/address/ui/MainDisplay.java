package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.fxml.FXML;
<<<<<<< HEAD
import javafx.geometry.Side;
=======
>>>>>>> origin/update-documentations
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;

import seedu.address.commons.events.ui.CloseTriviaTestViewEvent;
import seedu.address.commons.events.ui.ShowTriviaTestViewEvent;
import seedu.address.commons.events.ui.ToggleTabEvent;
import seedu.address.logic.Logic;
import seedu.address.ui.home.Homepage;
import seedu.address.ui.test.TriviaTestPlaceholderPage;

/**
 * A Ui class that is responsible for storing the placeholders for all the pages that exist in the MainDisplay.
 */
public class MainDisplay extends UiPart<Region> {

    private static final String FXML = "MainDisplay.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Homepage homepage;
    private final TriviaTestPlaceholderPage triviaTestPlaceholderPage;
    private final double tabWidth = 90.0;

    @FXML
    private TabPane tabContainer;
    @FXML
    private Tab learnTab;
    @FXML
    private Tab testTab;
    @FXML
    private Tab reviewTab;
    @FXML
    private StackPane homepagePlaceholder;
    @FXML
    private StackPane triviaTestPlaceholder;
    @FXML
    private StackPane reviewPlaceholder;

    public MainDisplay(Logic logic) {
        super(FXML);
        configureView();

        homepage = new Homepage(logic);
        homepagePlaceholder.getChildren().add(homepage.getRoot());

        triviaTestPlaceholderPage = new TriviaTestPlaceholderPage();
        triviaTestPlaceholder.getChildren().add(triviaTestPlaceholderPage.getRoot());

        registerAsAnEventHandler(this);
    }

    /**
     * Restore all the pages to its original state. Currently only homepage is required for this restoration.
     */
    private void resetToOriginalState() {
        homepage.resetToOriginalState();
    }

    /**
     * creates the view configuration of the tabs
     */
    private void configureView() {
        //tabContainer.setSide(side.LEFT);
        tabContainer.setTabMinWidth(tabWidth);
        tabContainer.setTabMaxWidth(tabWidth);
        tabContainer.setTabMinHeight(tabWidth);
        tabContainer.setTabMaxHeight(tabWidth);
        tabContainer.setRotateGraphic(true);
        //tabContainer.getStyleClass().add("root");

        //list of tabs configured
        createTab(learnTab, "Learn", "file:/src/main/resources/images/tabIcons/home.png", homepagePlaceholder);
        createTab(testTab, "Test", "file:/src/main/resources/images/tabIcons/settings.png", triviaTestPlaceholder);
        createTab(reviewTab, "Review", "file:/main/resources/images/tabIcons/test.png", reviewPlaceholder);

        logger.info("View has been configured");
    }

    /**
     * Creates a tab with the following parameters
     * @param tab
     * @param title
     * @param iconPath
     * @param containerPane
     */
    private void createTab(Tab tab, String title, String iconPath, StackPane containerPane) {
        double imageWidth = 40.0;

        ImageView imageView = new ImageView(new Image(iconPath));
        imageView.setFitHeight(imageWidth);
        imageView.setFitWidth(imageWidth);

        Label label = new Label(title);

        //set the tab's outlook into a border pane
        BorderPane borderPane = new BorderPane();
        borderPane.setRotate(90.0);
        borderPane.setMaxWidth(tabWidth);
        borderPane.setCenter(imageView);
        borderPane.setBottom(label);

        try {
            tab.setContent(containerPane);
            //tabPane = tab.getTabPane();
            logger.info("tab created");
        } catch (Exception e) {
            throw new IllegalArgumentException("Tab not added");
        }

    }

    public void releaseResources() {
        homepage.releaseResources();
    }

    @Subscribe
    public void handleToggleTabEvent(ToggleTabEvent event) {
        if (event.getToToggleTo().equals("learn")) {
            tabContainer.getSelectionModel().select(learnTab);
        } else if (event.getToToggleTo().equals("test")) {
            tabContainer.getSelectionModel().select(testTab);
        } else if (event.getToToggleTo().equals("review")) {
            tabContainer.getSelectionModel().select(reviewTab);
        }
    }

    @Subscribe
    private void handleShowTriviaTestViewEvent(ShowTriviaTestViewEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        tabContainer.getSelectionModel().select(testTab);
        learnTab.setDisable(true);
        reviewTab.setDisable(true);
    }

    @Subscribe
    private void handleCloseTriviaTestViewEvent(CloseTriviaTestViewEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        learnTab.setDisable(false);
        reviewTab.setDisable(false);
        resetToOriginalState();
    }
}
