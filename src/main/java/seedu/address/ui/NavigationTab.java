package seedu.address.ui;

import com.google.common.eventbus.Subscribe;
import com.jfoenix.controls.JFXTabPane;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;


/**
 * The NavigationTab class. Contains objects within the navigation tab in the UI.
 * Adapted from http://synappse.co/blog/vertical-stateful-jfxtabpane-with-icons-in-javafx/
 */
public class NavigationTab extends UiPart<Region> {

    @FXML
    private JFXTabPane tabContainer;

    @FXML
    private Tab userProfileTab;

    @FXML
    private AnchorPane userProfileContainer;

    @FXML
    private Tab settingsTab;

    @FXML
    private AnchorPane settingsContainer;

    @FXML
    private Tab logoutTab;
    private double tabWidth = 90.0;

    private static int lastSelectedTabIndex = 0;

    public NavigationTab() {
        this.initialize();
    }
    @FXML
    public void initialize() {
        configureView();
    }

    /**
     * Configures view of StackPane in MainWindow
     */
    private void configureView() {
        tabContainer.setTabMinWidth(tabWidth);
        tabContainer.setTabMaxWidth(tabWidth);
        tabContainer.setTabMinHeight(tabWidth);
        tabContainer.setTabMaxHeight(tabWidth);
        tabContainer.setRotateGraphic(true);

       setupTabs();
    }
    private void setupTabs() {
        configureTab(userProfileTab, "User\nProfile", "/co/synappse/project01/resources/images/user-profile.png", userProfileContainer, getClass().getResource("userprofile.fxml"), replaceBackgroundColorHandler);
        configureTab(settingsTab, "Settings", "/co/synappse/project01/resources/images/settings.png", settingsContainer, getClass().getResource("settings.fxml"), replaceBackgroundColorHandler);
        configureTab(logoutTab, "Logout", "/co/synappse/project01/resources/images/logout.png", null, null, logoutHandler);
        userProfileTab.setStyle("-fx-background-color: -fx-focus-color;");
    }

    /**
     * Creates
     * @param tab
     * @param title
     * @param iconPath
     * @param containerPane
     * @param resourceURL
     * @param onSelectionChangedEvent
     */
    private void configureTab(Tab tab, String title, String iconPath, AnchorPane containerPane, URL resourceURL, EventHandler<Event> onSelectionChangedEvent) {
        double imageWidth = 40.0;
        ImageView imageView = new ImageView(new Image(iconPath));
        imageView.setFitHeight(imageWidth);
        imageView.setFitWidth(imageWidth);
        Label label = new Label(title);
        label.setMaxWidth(tabWidth - 20);
        label.setPadding(new Insets(5, 0, 0, 0));
        label.setStyle("-fx-text-fill: black; -fx-font-size: 8pt; -fx-font-weight: normal;");
        label.setTextAlignment(TextAlignment.CENTER);
        BorderPane tabPane = new BorderPane();
        tabPane.setRotate(90.0);
        tabPane.setMaxWidth(tabWidth);
        tabPane.setCenter(imageView);
        tabPane.setBottom(label);
        tab.setText("");
        tab.setGraphic(tabPane);
        tab.setOnSelectionChanged(onSelectionChangedEvent);
        if (containerPane != null && resourceURL != null) {
            try {
                Parent contentView = FXMLLoader.load(resourceURL);
                containerPane.getChildren().add(contentView);
                AnchorPane.setTopAnchor(contentView, 0.0);
                AnchorPane.setBottomAnchor(contentView, 0.0);
                AnchorPane.setRightAnchor(contentView, 0.0);
                AnchorPane.setLeftAnchor(contentView, 0.0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    @Subscribe
    private void handleChangeTabEvent(ChangeTabEvent event) {
        lastSelectedTabIndex = tabContainer.getSelectionModel().getSelectedIndex();
        Tab currentTab = (Tab) event.getTarget();
        if (currentTab.isSelected()) {
            currentTab.setStyle("-fx-background-color: -fx-focus-color;");
        } else {
            currentTab.setStyle("-fx-background-color: -fx-accent;");
        }

    }

}
