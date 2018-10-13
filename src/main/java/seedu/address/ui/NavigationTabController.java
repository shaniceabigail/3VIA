package seedu.address.ui;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

import javax.swing.text.html.ImageView;
import java.awt.*;
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

    //private ArrayList<>
    //private final Logic logic;

    @FXML
    private JFXTabPane tabContainer;

    @FXML
    private Tab userProfileTab;

    @FXML
    private Tab settingsTab;

    @FXML
    private Tab customTab;



    public NavigationTabController () {
        super(FXML);
        this.configureTabPane();
        //this.logic = logic;
    }

    /*
     *  Handle the
     */


    private void configureTabPane() {
        tabContainer.setTabMinWidth(tabWidth);
        tabContainer.setTabMaxWidth(tabWidth);
        tabContainer.setTabMinHeight(tabWidth);
        tabContainer.setTabMaxHeight(tabWidth);
        tabContainer.setRotateGraphic(true);
        
        configureTab(userProfileTab, "User\nProfile", "/co/synappse/project01/resources/images/user-profile.png");
        configureTab(settingsTab, "Settings", "/co/synappse/project01/resources/images/settings.png");
        configureTab(logoutTab, "Logout", "/co/synappse/project01/resources/images/logout.png");
    }

    private void configureTab(Tab tab, String title, String iconPath) {
        double imageWidth = 40.0;

        /// 5.
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

        /// 6.
        tab.setText("");
        tab.setGraphic(tabPane);
    }
}
