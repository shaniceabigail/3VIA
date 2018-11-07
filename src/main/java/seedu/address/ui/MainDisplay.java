package seedu.address.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;
import com.jfoenix.controls.JFXTabPane;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.CloseTriviaTestViewEvent;
import seedu.address.commons.events.ui.NavigateToLearnPageEvent;
import seedu.address.commons.events.ui.ShowTriviaTestResultEvent;
import seedu.address.commons.events.ui.ShowTriviaTestViewEvent;
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

    private ArrayList<Tab> listOfTabs;
    private Tab currentTab;

    @FXML
    private JFXTabPane tabContainer;
    @FXML
    private Tab homeTab;
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
        listOfTabs = new ArrayList<Tab>();

        tabContainer = new JFXTabPane();
        currentTab = homeTab;
        this.configureView();

        homepage = new Homepage(logic);
        homepagePlaceholder.getChildren().add(homepage.getRoot());

        triviaTestPlaceholderPage = new TriviaTestPlaceholderPage();
        triviaTestPlaceholder.getChildren().add(triviaTestPlaceholderPage.getRoot());

        resetToOriginalState();
        registerAsAnEventHandler(this);
    }

    /**
     * Hides all the other pages except for the homepage. The default page should be the homepage.
     */
    public void resetToOriginalState() {
        homepagePlaceholder.setVisible(true);
    }

    /**
     * creates the view configuration of the tabs
     */
    private void configureView() {
        tabContainer.setSide(Side.LEFT);
        tabContainer.setTabMinWidth(tabWidth);
        tabContainer.setTabMaxWidth(tabWidth);
        tabContainer.setTabMinHeight(tabWidth);
        tabContainer.setTabMaxHeight(tabWidth);
        tabContainer.setRotateGraphic(true);
        tabContainer.getStyleClass().add("root");

        //list of tabs configured
        createTab(homeTab, "Home", "file:/src/main/resources/images/tabIcons/home.png", homepagePlaceholder);
        createTab(testTab, "Test", "file:/src/main/resources/images/tabIcons/settings.png", triviaTestPlaceholder);
        createTab(reviewTab, "Review", "file:/main/resources/images/tabIcons/test.png", reviewPlaceholder);
    }

    /**
     * Configures a new tab
     */
    private void createTab(Tab tab, String title, String iconPath, StackPane containerPane) {
        double imageWidth = 40.0;

        ImageView imageView = new ImageView(new Image(iconPath));
        imageView.setFitHeight(imageWidth);
        imageView.setFitWidth(imageWidth);

        Label label = new Label(title);

        //set the tab's outlook into a border pane
        BorderPane tabPane = new BorderPane();
        tabPane.setRotate(90.0);
        tabPane.setMaxWidth(tabWidth);
        tabPane.setCenter(imageView);
        tabPane.setBottom(label);

        if (containerPane != null) {
            try {
                tab.setContent(containerPane);
                listOfTabs.add(tab);
            } catch (Exception e) {
                throw new IllegalArgumentException("Tab not added");
            }

        }
    }
    public void releaseResources() {
        homepage.releaseResources();
    }
}
