package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;
import com.jfoenix.controls.JFXTabPane;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.ToggleTabEvent;
import seedu.address.commons.exceptions.IllegalValueException;

/**
 * The UI component that is responsible for navigation
 *
 * Adapted from http://synappse.co/blog/vertical-stateful-jfxtabpane-with-icons-in-javafx/
 *
 */
public class NavigationTabController extends UiPart<Region> {

    private static final String FXML = "NavigationTab.fxml";
    private static final Logger logger = LogsCenter.getLogger(NavigationTabController.class);

    private double tabWidth = 90.0;

    private ArrayList<Tab> listOfTabs;
    private Tab currentTab;

    @FXML
    private JFXTabPane tabContainer;

    @FXML
    private Tab homeTab;

    @FXML
    private Tab settingsTab;

    @FXML
    private Tab customTab;



    public NavigationTabController () {
        super(FXML);
        listOfTabs = new ArrayList<>();
        tabContainer = new JFXTabPane();
        homeTab = new Tab();
        settingsTab = new Tab();
        customTab = new Tab();
        currentTab = homeTab;
        this.configureView();
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

        //list of tabs configured
        createTab(homeTab, "Home", "file:/src/main/resources/images/tabIcons/home.png");
        createTab(settingsTab, "Settings", "file:/src/main/resources/images/tabIcons/settings.png");
        createTab(customTab, "Custom", "file:/main/resources/images/tabIcons/test.png");
    }

    /**
     * Configures a new tab
     */
    private void createTab(Tab tab, String title, String iconPath) {
        double imageWidth = 40.0;

        ImageView imageView = new ImageView(new Image(iconPath));
        imageView.setFitHeight(imageWidth);
        imageView.setFitWidth(imageWidth);

        Label label = new Label(title);

        BorderPane tabPane = new BorderPane();
        tabPane.setRotate(90.0);
        tabPane.setMaxWidth(tabWidth);
        tabPane.setCenter(imageView);
        tabPane.setBottom(label);

        tab.setText(title);
        tab.setGraphic(tabPane);

        listOfTabs.add(tab);
    }

    /**
     * Checks if tab called is a created tab
     * @param newSelected
     * @return
     */
    public boolean isValidTab(Tab newSelected) {
        return listOfTabs.contains(newSelected);
    }

    @Subscribe
    public void handleToggleTab(ToggleTabEvent event) throws IllegalValueException {
        requireNonNull(event.toString());
        Tab newSelected = new Tab(event.toString());

        if (!isValidTab(newSelected)) {
            throw new IllegalValueException("Navigated tab does not exist.");
        } else {
            logger.info(LogsCenter.getEventHandlingLogMessage(event));

            //reset colour of current tab
            currentTab.setStyle("-fx-background-color: -fx-accent;");
            currentTab = newSelected;
            currentTab.setStyle("-fx-background-color: -fx-accent;");
        }
    }

}
