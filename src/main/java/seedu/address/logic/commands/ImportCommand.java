package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Import cards from specified location to the trivia bundle.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports cards to the trivia. "
            + "Parameters: "
            + "location\n"
            + "Example: " + COMMAND_WORD + " "
            + "Path of file ";

    public static final String MESSAGE_SUCCESS = "New cards added: ";
    public static final String MESSAGE_DUPLICATE_CARD = "Some cards already exists in the trivia bundle";
    public static final String MESSAGE_INVALID_FILE = "Invalid file name.";
    public static final String MESSAGE_INVALID_FILE_TYPE = "Invalid file type.";
    public static final String MESSAGE_UNABLE_TO_READ = "Unable to read file.";
    private final File file;

    /**
     * Creates an ImportCommand to import the cards {@code File}
     */
    public ImportCommand(File file) {
        requireNonNull(file);
        this.file = file;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        isFileValid();

        // model.addCard(toAdd);
        model.commitTriviaBundle();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Validates the file to be imported.
     * @throws CommandException the exception caught.
     */
    private void isFileValid() throws CommandException {
        if (!file.isFile()) {
            throw new CommandException(MESSAGE_INVALID_FILE);
        }
        try {
            String fileType = Files.probeContentType(file.toPath());
            if (!fileType.equals("text/plain")) {
                throw new CommandException(MESSAGE_INVALID_FILE_TYPE);
            }
        } catch (IOException ioe) {
            throw new CommandException(MESSAGE_UNABLE_TO_READ);
        }
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && file.equals(((ImportCommand) other).file));
    }
}