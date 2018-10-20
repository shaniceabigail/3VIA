package seedu.address.logic.parser.fileParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ExtraInformationDisplay;
import seedu.address.commons.events.ui.ExtraInformationDisplayChangeEvent;
import seedu.address.logic.parser.exceptions.FileParseException;
import seedu.address.model.card.Card;

/**
 * Contains utility methods used for parsing a file to cards and vice-versa.
 */
public class FileParserUtil {
    public static final String MESSAGE_INVALID_FILE = "Invalid file name.";
    public static final String MESSAGE_INVALID_FILE_TYPE = "Invalid file type. Only .txt files are accepted";
    /**
     * Parses the file into a list of cards to be imported.
     * @return List of cards to import
     * @throws FileParseException If the format of the file does not conform to the expected format.
     */
    public static List<Card> parseFileToCards(File importFile) throws FileParseException {
        isFileValid(importFile);
        List<Card> cards = new LinkedList<>();
        // read each line
            // check if got topic?
                // parse tags
            // question and answers -> create card -> store in list
        // return cards to import command
        return cards;
    }

    /**
     * Validates the file to be imported.
     * @throws FileParseException If the file is not valid for import.
     */
    private static void isFileValid(File file) throws FileParseException {
        if (!file.isFile()) {
            EventsCenter
                    .getInstance()
                    .post(new ExtraInformationDisplayChangeEvent(ExtraInformationDisplay.IMPORT_HELP_DISPLAY));
            throw new FileParseException(MESSAGE_INVALID_FILE);
        } else if (!isValidFileType(file)) {
            throw new FileParseException(MESSAGE_INVALID_FILE_TYPE);
        }
    }

    /**
     * Checks if file is a readable .txt format.
     * @return true if file is a readable text file.
     */
    private static boolean isValidFileType(File file) {
        try {
            String fileType = Files.probeContentType(file.toPath());
            if (!fileType.equals("text/plain")) {
                return false;
            }
        } catch (IOException ioe) {
            return false;
        }
        return true;
    }
}
