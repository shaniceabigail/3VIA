package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.File;

import java.util.Set;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.FileParseException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.fileParser.FileParser;
import seedu.address.model.card.Card;

/**
 * Parses input arguments and creates a new ImportCommand object
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns an ImportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCommand parse(String args) throws ParseException {
        try {
            File importFile = ParserUtil.parsePath(args);
            Set<Card> cards = FileParser.parseFileToCards(importFile);
            return new ImportCommand(importFile.getName(), cards);
        } catch (FileParseException fpe) {
            throw new FileParseException(ImportCommand.MESSAGE_INVALID_FILE, fpe);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE), pe);
        }
    }
}
