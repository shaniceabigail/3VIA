package seedu.address.logic.parser.fileParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ExtraInformationDisplay;
import seedu.address.commons.events.ui.ExtraInformationDisplayChangeEvent;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.FileParseException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.topic.Topic;

/**
 * Contains utility methods used for parsing a file to cards and vice-versa.
 */
public class FileParserUtil {
    public static final String MESSAGE_INVALID_FILE_NAME = "Invalid file name.";
    public static final String MESSAGE_INVALID_FILE_TYPE = "Invalid file type. Only .txt files are accepted";
    public static final String MESSAGE_INVALID_FILE_FORMAT = "Invalid file format.";

    private static BufferedReader br = null;
    /**
     * Parses the file into a list of cards to be imported.
     * @return List of cards.
     * @throws FileParseException If the format of the file does not conform to the expected format.
     */
    public static Set<Card> parseFileToCards(File importFile) throws FileParseException {
        isFileValid(importFile);
        Set<Card> cards = new HashSet<>();
        // exception if file empty, file wrong format, file contains duplicated

        // TODO: add support for quotes
        try {
            String line;
            br = new BufferedReader(new FileReader(importFile));
            while ((line = br.readLine()) != null) {
                String[] cardString = line.split(";");
                // TODO: check the size of the string
                if (cardString.length != 2) {
                    throw new FileParseException(MESSAGE_INVALID_FILE_FORMAT);
                }
                Question question = ParserUtil.parseQuestion(cardString[0]);
                Answer answer = ParserUtil.parseAnswer(cardString[0]);
                // TODO: add tag support
                Set<Topic> tagList = new HashSet<>(); // default tag
                tagList.add(new Topic("NoTopic"));
                Card card = new Card(question, answer, tagList);
                cards.add(card);
            }
        } catch (IOException ioe) {
            throw new FileParseException(MESSAGE_INVALID_FILE_TYPE);
        } catch (ParseException pe) {
            throw new FileParseException(MESSAGE_INVALID_FILE_FORMAT);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ioe) {
                    throw new FileParseException(MESSAGE_INVALID_FILE_TYPE);
                }
            }
        }

        return cards;
    }

    /**
     * Validates the file to be imported.
     * @throws FileParseException If the file is not suitable for import.
     */
    private static void isFileValid(File file) throws FileParseException {
        if (!file.isFile()) {
            EventsCenter
                    .getInstance()
                    .post(new ExtraInformationDisplayChangeEvent(ExtraInformationDisplay.IMPORT_HELP_DISPLAY));
            throw new FileParseException(MESSAGE_INVALID_FILE_NAME);
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
