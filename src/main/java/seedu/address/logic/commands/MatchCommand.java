package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.ui.ShowTriviaTestResultEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.test.TriviaTest;
import seedu.address.model.test.matchtest.MatchAttempt;
import seedu.address.model.test.matchtest.MatchTest;
import seedu.address.ui.UiPart;

/**
 * The MatchCommand can only be used in a Matching test.
 */
public class MatchCommand extends Command {
    public static final String COMMAND_WORD = "";

    public static final String MESSAGE_USAGE = "Match a question to its answer. "
            + "Parameters: INDEX_OF_QUESTION INDEX_OF_ANSWER \n"
            + "Example: 1 4";

    public static final String MESSAGE_MATCH_SUCCESS = "Perfect Match!";
    public static final String MESSAGE_MATCH_FAILURE = "Wrong Match!";
    public static final String MESSAGE_NOT_IN_MATCHING_TEST = "The undergoing test is not a matching test";
    public static final String MESSAGE_INDEX_OUT_OF_BOUND = "The index specified is out of bound.";

    private final Index questionIndex;
    private final Index answerIndex;

    public MatchCommand(Index questionIndex, Index answerIndex) {
        this.questionIndex = questionIndex;
        this.answerIndex = answerIndex;

    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        TriviaTest test = model.getCurrentRunningTest();
        if (!(test instanceof MatchTest)) {
            throw new CommandException(MESSAGE_NOT_IN_MATCHING_TEST);
        }

        MatchTest matchTest = (MatchTest) test;
        try {
            MatchAttempt attempt = matchTest.addAttempt(questionIndex, answerIndex);
            if (attempt.isCorrect()) {
                matchTest.postOutcomeOfMatch(attempt);

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            matchTest.removeCardFromUi(attempt);
                            if (matchTest.isEndOfTest()) {
                                model.addMatchTestResult(matchTest);
                                matchTest.stopTest();
                                EventsCenter.getInstance().post(new ShowTriviaTestResultEvent(matchTest));
                            }
                        });
                    }
                }, UiPart.FLASH_TIME);
            } else {
                matchTest.postOutcomeOfMatch(attempt);
                throw new CommandException(MESSAGE_MATCH_FAILURE);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INDEX_OUT_OF_BOUND);
        }

        return new CommandResult(String.format(MESSAGE_MATCH_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        // All the tests are different even if they have the same parameters.
        return other == this
                || (other instanceof MatchCommand // instanceof handles nulls
                && questionIndex.equals(((MatchCommand) other).questionIndex)
                && answerIndex.equals(((MatchCommand) other).answerIndex));
    }
}
