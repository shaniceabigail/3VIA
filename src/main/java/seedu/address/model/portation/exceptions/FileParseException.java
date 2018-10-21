package seedu.address.model.portation.exceptions;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a parse error encountered by a file parser.
 */
public class FileParseException extends ParseException {

    public FileParseException(String message) {
        super(message);
    }

    public FileParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
