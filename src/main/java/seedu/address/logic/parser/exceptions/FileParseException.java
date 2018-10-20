package seedu.address.logic.parser.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

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
