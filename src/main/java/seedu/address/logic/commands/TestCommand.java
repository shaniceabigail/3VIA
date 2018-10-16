package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOPIC;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.test.normalTest.Test;
import seedu.address.model.topic.Topic;

/**
 * The command which will executing the matching test of trivia.
 */
public class TestCommand extends Command {
    public static final String COMMAND_WORD = "test";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Starts a test. "
            + "Parameters: "
            + PREFIX_TOPIC + "TAG "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TOPIC + "Physics ";

    public static final String MESSAGE_SUCCESS = "Test started.";

    private final Topic tag;

    public TestCommand(Topic tag) {
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        try {
            Test test = new Test(tag, model.getTriviaBundle());
            model.startTriviaTest(test);
            return new CommandResult(String.format(MESSAGE_SUCCESS));
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }
    }
}