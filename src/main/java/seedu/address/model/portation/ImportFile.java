package seedu.address.model.portation;

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
import seedu.address.model.card.Card;
import seedu.address.model.portation.exceptions.FileParseException;
import seedu.address.model.topic.Topic;

/**
 * Represents a file to be imported.
 */
public class ImportFile {
    public static final String MESSAGE_INVALID_FILE = "Invalid file.";
    public static final String MESSAGE_INVALID_FILE_FORMAT = "Invalid file format.";
    public static final String MESSAGE_INVALID_FILE_NAME = "Invalid file name.";
    public static final String MESSAGE_INVALID_FILE_TYPE = "Invalid file type. Only .txt files are accepted";
    public static final String MESSAGE_EMPTY_FILE = "Empty file.";

    private final File importFile;

    public ImportFile(String filePath) {
        importFile = new File(filePath);
    }

    /**
     * Ensures the file to be imported is valid, readable and non empty.
     * @throws FileParseException If the file is not suitable for import.
     */
    public static boolean isFileValid(String filePath) throws FileParseException {
        File file = new File(filePath);
        if (!file.isFile()) {
            throw new FileParseException(MESSAGE_INVALID_FILE_NAME);
        } else if (!isValidFileType(file)) {
            throw new FileParseException(MESSAGE_INVALID_FILE_TYPE);
        } else if (file.length() == 0) {
            throw new FileParseException(MESSAGE_EMPTY_FILE);
        }
        return true;
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

    /**
     * Parses a file in a specific format into a set of cards.
     * @return The set of cards to be imported.
     * @throws FileParseException If the format of the file is different from expected.
     */
    public Set<Card> parseFileToCards() throws FileParseException {
        Set<Card> cards = new HashSet<>();
        Set<Topic> topicSet = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(importFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (FileParserUtil.isTopic(line)) {
                    topicSet = FileParserUtil.parseLineToTopicSet(line);
                } else {
                    String[] cardString = FileParserUtil.parseLineToQuestionAnswerPair(line);
                    Card cardToAdd = FileParserUtil.stringToCard(cardString, topicSet);
                    cards.add(cardToAdd);
                }
            }
        } catch (IOException ioe) {
            throw new FileParseException(MESSAGE_INVALID_FILE_TYPE);
        } catch (FileParseException fpe) {
            EventsCenter
                    .getInstance()
                    .post(new ExtraInformationDisplayChangeEvent(ExtraInformationDisplay.IMPORT_HELP_DISPLAY));
            throw new FileParseException(MESSAGE_INVALID_FILE_FORMAT);
        }

        if (cards.isEmpty()) {
            throw new FileParseException(MESSAGE_INVALID_FILE_FORMAT);
        }
        return cards;
    }

    public String getFileName() {
        return importFile.getName();
    }

    @Override
    public String toString() {
        return importFile.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportFile
                && importFile.equals(((ImportFile) other).importFile));
    }
}
