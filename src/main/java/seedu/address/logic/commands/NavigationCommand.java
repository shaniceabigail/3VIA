package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;


import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Navigates to a different page
 */
public class NavigationCommand extends Command {
    public static final String COMMAND_WORD = "test";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": navigates to test menu"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Successfully navigated to test";
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
