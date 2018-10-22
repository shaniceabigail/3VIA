package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAVIGATION;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Navigates to a different page
 */
public class NavigationCommand extends Command {
    public static final String COMMAND_WORD = "go/"

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": navigates to a different page"
            + "Parameters: PAGE_NAME \n"
            + "Example: " + COMMAND_WORD
            + " test";

    public static final String MESSAGE_SUCCESS = "Successfully navigated to ";
    public static final String MESSAGE_UNSUCCESSFUL = "Did not navigate to ";

    private String pageName;

    public NavigationCommand(String pageName) {
        this.pageName = pageName;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        try {

        } catch(IllegalArgumentException) {

        }
    }
}
