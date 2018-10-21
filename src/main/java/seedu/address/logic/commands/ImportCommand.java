package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.card.Card;
import seedu.address.model.portation.ImportFile;
import seedu.address.model.portation.exceptions.FileParseException;

/**
 * Import cards from specified location to the trivia bundle.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports cards to the trivia. "
            + "Parameters: "
            + "File path\n"
            + "Example: " + COMMAND_WORD + " "
            + "C:\\Users\\username\\Desktop\\cards.txt";

    public static final String MESSAGE_SUCCESS = "Imported cards from: %1$s.";
    public static final String MESSAGE_IMPORT_FAIL = "Unable to import cards.";
    public static final String MESSAGE_DUPLICATE_CARD = "Some cards already exists in the trivia bundle.";
    private final ImportFile importFile;

    /**
     * Creates an ImportCommand with the file name {@code fileName} to import the cards {@code cards}
     */
    public ImportCommand(ImportFile file) {
        requireNonNull(file);
        importFile = file;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        Set<Card> cardsToImport;
        try {
            cardsToImport = importFile.parseFileToCards();
        } catch (FileParseException fpe) {
            throw new CommandException(MESSAGE_IMPORT_FAIL);
        }

        for (Card toAdd : cardsToImport) {
            if (model.hasCard(toAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_CARD);
            }
        }
        model.addMultipleCards(cardsToImport);
        model.commitTriviaBundle();
        return new CommandResult(String.format(MESSAGE_SUCCESS, importFile.getFileName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && importFile.equals(((ImportCommand) other).importFile));
    }
}
