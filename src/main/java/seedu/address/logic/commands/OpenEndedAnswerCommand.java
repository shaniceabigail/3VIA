package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.test.openendedtest.OpenEndedTest;

public class OpenEndedAnswerCommand extends Command {
    public static String userAnswer;

    public static final String MESSAGE_ANSWER_RECORDED = "Answer recorded!\n";

    public OpenEndedAnswerCommand(String userInput) {
        this.userAnswer = userInput;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        assert model.getCurrentRunningTest() instanceof OpenEndedTest;
        model.recordAnswerToOpenEndedTest(userAnswer);
        return new CommandResult(MESSAGE_ANSWER_RECORDED);
    }

}
