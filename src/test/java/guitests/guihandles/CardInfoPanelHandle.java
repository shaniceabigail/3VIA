package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * A handler for the {@code CardInfoPanelHandle} of the UI.
 */
public class CardInfoPanelHandle extends NodeHandle<Node> {

    public static final String QUESTION_ID = "#cardInfoQuestion";

    private Label question;
    private Node cardInfoNode;

    public CardInfoPanelHandle(Node cardInfoNode) {
        super(cardInfoNode);
        question = getChildNode(QUESTION_ID);
        this.cardInfoNode = cardInfoNode;
    }

    public String getQuestion() {
        return question.getText();
    }

    public boolean isVisible() {
        return cardInfoNode.isVisible();
    }
}
