package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ToggleTabEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Navigates to a different page
 */
public class NavigationCommand extends Command {
    public static final String COMMAND_WORD = "go/";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " : navigates to test menu\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Successfully navigated to test";

    private String tabToGo;

    public NavigationCommand(String tabName) {
        tabToGo = tabName;
    }

    private String getTabToGo() {
        return tabToGo;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        EventsCenter.getInstance().post(new ToggleTabEvent(this.getTabToGo()));
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
