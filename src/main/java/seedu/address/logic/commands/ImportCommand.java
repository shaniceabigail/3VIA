package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;

import java.util.List;
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ExtraInformationDisplay;
import seedu.address.commons.events.ui.ExtraInformationDisplayChangeEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.card.Card;

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

    public static final String MESSAGE_SUCCESS = "Imported cards from: %1$s";
    public static final String MESSAGE_INVALID_FILE = "File is invalid!";
    public static final String MESSAGE_DUPLICATE_CARD = "Some cards already exists in the trivia bundle";
    private final String fileName;
    private final List<Card> cardsToImport;

    /**
     * Creates an ImportCommand with the file name {@code fileName} to import the cards {@code cards}
     */
    public ImportCommand(String fileName, List<Card> cards) { // TODO: take in a list of cards
        requireNonNull(fileName);
        requireNonNull(cards);
        this.fileName = fileName;
        cardsToImport = cards;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        // add multiple cards to model
        // exception if file empty, file wrong format, file contains duplicated
        // model.addCard(toAdd);
        model.commitTriviaBundle();
        return new CommandResult(String.format(MESSAGE_SUCCESS, fileName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && cardsToImport.equals(((ImportCommand) other).cardsToImport));
    }
}
