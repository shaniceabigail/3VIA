package seedu.address.model.test.matchtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TOPIC_GIT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TOPIC_NO_TOPIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TOPIC_PHYSICS;
import static seedu.address.testutil.TypicalCards.Q_DENSITY_FORMULA;
import static seedu.address.testutil.TypicalCards.Q_EARTH_ROUND;
import static seedu.address.testutil.TypicalCards.Q_FORCE_FORMULA;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.topic.Topic;
import seedu.address.testutil.TypicalCards;
import seedu.address.testutil.TypicalPersons;

public class MatchTestTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ModelManager model;
    private MatchTest matchTest;

    @Before
    public void setUp() {
        model = new ModelManager(TypicalPersons.getTypicalAddressBook(),
                TypicalCards.getTypicalTriviaBundle(), new UserPrefs());

        // There will be 3 cards in this matchTest
        matchTest = new MatchTest(new Topic(VALID_TOPIC_PHYSICS), model.getTriviaBundle());
    }

    @Test
    public void test_matchTest() {
        assertEquals(matchTest.getAnswers().size(), matchTest.getQuestions().size());
        assertEquals(matchTest.getAnswers().size(), 3);

        assertTrue(matchTest.getAnswers().contains(Q_EARTH_ROUND.getAnswer()));
        assertTrue(matchTest.getQuestions().contains(Q_EARTH_ROUND.getQuestion()));
        matchTest.removeCardFromUi(new MatchAttempt(Q_EARTH_ROUND, Q_EARTH_ROUND));
        assertFalse(matchTest.getAnswers().contains(Q_EARTH_ROUND.getAnswer()));
        assertFalse(matchTest.getQuestions().contains(Q_EARTH_ROUND.getQuestion()));

        // Remove the remaining cards from the trivia bundle.
        matchTest.removeCardFromUi(new MatchAttempt(Q_DENSITY_FORMULA, Q_DENSITY_FORMULA));
        matchTest.removeCardFromUi(new MatchAttempt(Q_FORCE_FORMULA, Q_FORCE_FORMULA));

        assertTrue(matchTest.isEndOfTest());
    }

    @Test
    public void invalid_matchTest() {
        thrown.expect(IllegalArgumentException.class);
        matchTest = new MatchTest(new Topic(VALID_TOPIC_NO_TOPIC), model.getTriviaBundle());
    }

    @Test
    public void equals() {
        // same values -> returns true
        MatchTest matchTestCopy = new MatchTest(matchTest.getTopic(), model.getTriviaBundle());
        assertTrue(matchTest.equals(matchTestCopy));

        // same object -> returns true
        assertTrue(matchTest.equals(matchTest));

        // null -> returns false
        assertFalse(matchTest.equals(null));

        // different type -> returns false
        assertFalse(matchTest.equals(5));

        // different cards being tested -> returns false
        assertFalse(matchTest.equals(new MatchTest(new Topic(VALID_TOPIC_GIT), model.getTriviaBundle())));

    }
}
