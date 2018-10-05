package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PHYSICS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.Q_EARTH_ROUND;
import static seedu.address.testutil.TypicalCards.Q_FORCE_FORMULA;
import static seedu.address.testutil.TypicalCards.getTypicalTriviaBundle;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.card.Card;
import seedu.address.model.tag.Tag;
import seedu.address.model.test.TriviaTest;
import seedu.address.model.test.matchtest.MatchTest;

public class MatchCommandTest {
    private MatchTest matchTest;
    private Model model;

    private MatchTest expectedMatchTest;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTriviaBundle(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), getTypicalTriviaBundle(), new UserPrefs());

        matchTest = new MatchTest(new Tag(VALID_TAG_PHYSICS), model.getTriviaBundle());
        model.startTriviaTest(matchTest);

        expectedMatchTest = new MatchTest(new Tag(VALID_TAG_PHYSICS), model.getTriviaBundle());
        expectedModel.startTriviaTest(expectedMatchTest);
    }

    @After
    public void cleanUp() {
        if (model.isInTestingState()) {
            model.stopTriviaTest();
        }
        if (expectedModel.isInTestingState()) {
            expectedModel.stopTriviaTest();
        }
    }

    @Test
    public void execute_matchSuccess() {
        // Matching for the first time
        assertMatchCommandSuccess(Q_EARTH_ROUND);

        // Matching for the second time
        assertMatchCommandSuccess(Q_FORCE_FORMULA);
    }

    @Test
    public void execute_matchFailure() {
        Index wrongCardCombiQ = getIndexes(matchTest, Q_EARTH_ROUND)[0];
        Index wrongCardCombiA = getIndexes(matchTest, Q_FORCE_FORMULA)[1];
        assertCommandFailure(new MatchCommand(wrongCardCombiQ, wrongCardCombiA), model, commandHistory,
                MatchCommand.MESSAGE_MATCH_FAILURE);
    }

    @Test
    public void execute_matchError() {
        // Index out of bound
        assertCommandFailure(new MatchCommand(Index.fromZeroBased(100), Index.fromZeroBased(105)), model,
                commandHistory, MatchCommand.MESSAGE_INDEX_OUT_OF_BOUND);

        // Unable to execute matching command because not in matching test state.
        model.stopTriviaTest();
        assertCommandFailure(new MatchCommand(Index.fromZeroBased(1), Index.fromZeroBased(2)), model,
                commandHistory, MatchCommand.MESSAGE_NOT_IN_MATCHING_TEST);
    }

    /**
     * Used to obtain the base0 indexes of matching question and answer in the test.
     * @param test The matchTest that is ongoing.
     * @param card The card that is you want to match.
     * @return the an array of indexes of size 2. With the first and second indexes representing the question and
     * answer respectively.
     */
    private Index[] getIndexes(TriviaTest test, Card card) {
        return new Index[] {
                Index.fromZeroBased(test.getQuestions().indexOf(card.getQuestion())),
                Index.fromZeroBased(test.getAnswers().indexOf(card.getAnswer()))
        };
    }

    /**
     * Execute the match command using the card's question and answer to simulate a matching success.
     */
    private void assertMatchCommandSuccess(Card cardToMatch) {
        Index[] expectedCorrectIndexes = getIndexes(expectedMatchTest, cardToMatch);
        expectedMatchTest.addAttempt(expectedCorrectIndexes[0], expectedCorrectIndexes[1]);

        Index[] correctIndexes = getIndexes(matchTest, cardToMatch);
        assertCommandSuccess(new MatchCommand(correctIndexes[0], correctIndexes[1]), model, commandHistory,
                MatchCommand.MESSAGE_MATCH_SUCCESS, expectedModel);
    }
}
