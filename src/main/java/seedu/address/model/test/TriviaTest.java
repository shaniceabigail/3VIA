package seedu.address.model.test;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyTriviaBundle;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.card.TopicIsKeywordPredicate;
import seedu.address.model.topic.Topic;

/**
 * A base model for the different kinds tests. Require a Tag and Trivia Bundle to start a test.
 */
public abstract class TriviaTest {
    protected final Topic tag;
    protected final Date testDate;

    protected final ObservableList<Card> cards;
    protected final ObservableList<Question> questions;
    protected final ObservableList<Answer> answers;

    public TriviaTest(Topic tag, ReadOnlyTriviaBundle triviaBundle) {
        this.tag = tag;
        this.testDate = new Date();

        cards = triviaBundle.getListOfCardFilteredByTag(new TopicIsKeywordPredicate(tag.topicName));
        questions = getQuestions(cards);
        answers = getAnswers(cards);
    }

    public abstract void startTest();

    public abstract void stopTest();

    public ObservableList<Question> getQuestions() {
        return this.questions;
    }

    /**
     * Retrieve an modifiable observable list of questions to allow changes in the UI.
     * @param cards The cards to retrieve the questions from.
     * @return an observable list of questions
     */
    private ObservableList<Question> getQuestions(List<Card> cards) {
        List<Question> questions = cards.stream()
                .map(card -> card.getQuestion())
                .collect(Collectors.toList());
        Collections.shuffle(questions);
        return FXCollections.observableList(questions);
    }

    public ObservableList<Answer> getAnswers() {
        return this.answers;
    }

    /**
     * Retrieve an modifiable observable list of answers to allow changes in the UI.
     * @param cards The cards to retrieve the answers from.
     * @return an observable list of answers
     */
    private ObservableList<Answer> getAnswers(List<Card> cards) {
        List<Answer> answers = cards.stream()
                .map(card -> card.getAnswer())
                .collect(Collectors.toList());
        Collections.shuffle(answers);
        return FXCollections.observableList(answers);
    }

    public Topic getTag() {
        return tag;
    }

}
