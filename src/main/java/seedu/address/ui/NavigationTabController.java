package seedu.address.ui;

import com.google.common.eventbus.Subscribe;
import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.ToggleTabEvent;

import java.util.logging.Logger;

/*
 * The UI component that is responsible for navigation
 *
 * Adapted from http://synappse.co/blog/vertical-stateful-jfxtabpane-with-icons-in-javafx/
 *
 */

public class NavigationTabController extends UiPart<Region> {

    private static final String FXML = "NavigationTabPane.fxml";
    private static final Logger logger = LogsCenter.getLogger(NavigationTabController.class);

    private double tabWidth = 90.0;
    public static int lastSelectedTabIndex = 0;

    @FXML
    private JFXTabPane tabContainer;

    @FXML
    private Tab HomeTab;

    @FXML
    private Tab settingsTab;

    @FXML
    private Tab customTab;



    public NavigationTabController () {
        super(FXML);
        tabContainer = new JFXTabPane();
        HomeTab = new Tab();
        settingsTab = new Tab();
        customTab = new Tab();
        this.configureView();
    }


    private void configureView() {
        tabContainer.setTabMinWidth(tabWidth);
        tabContainer.setTabMaxWidth(tabWidth);
        tabContainer.setTabMinHeight(tabWidth);
        tabContainer.setTabMaxHeight(tabWidth);
        tabContainer.setRotateGraphic(true);

        //list of tabs configured
        createTab(HomeTab, "Home", "/src/main/resources/images/tabIcons/home.png");
        createTab(settingsTab, "Settings", "/src/main/resources/images/tabIcons/settings.png");
        createTab(customTab, "Custom", "/src/main/resources/images/tabIcons/test.png");
    }

    /**
     * Configures a new tab
     */

    private void createTab(Tab tab, String title, String iconPath) {
        double imageWidth = 40.0;

        ImageView imageView = new ImageView(iconPath);
        imageView.setFitHeight(imageWidth);
        imageView.setFitWidth(imageWidth);

        Label label = new Label(title);

        BorderPane tabPane = new BorderPane();
        tabPane.setRotate(90.0);
        tabPane.setMaxWidth(tabWidth);
        tabPane.setCenter(imageView);
        tabPane.setBottom(label);

        tab.setText("");
        tab.setGraphic(tabPane);
    }

    @Subscribe
    private void handleToggleTab(ToggleTabEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        lastSelectedTabIndex = tabContainer.getSelectionModel().getSelectedIndex();
        Tab currentTab = (Tab) event.getTarget();
        if (currentTab.isSelected()) {
            currentTab.setStyle("-fx-background-color: -fx-focus-color;");
        } else {
            currentTab.setStyle("-fx-background-color: -fx-accent;");
        }

        }
    }
}
