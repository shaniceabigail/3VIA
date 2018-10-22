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
import seedu.address.logic.parser.fileparser.FileParserUtil;
import seedu.address.model.card.Card;
import seedu.address.logic.parser.fileparser.exceptions.FileParseException;
import seedu.address.model.topic.Topic;

/**
 * Represents a file to be imported.
 */
public class ImportFile {
    public static final String MESSAGE_INVALID_FILE = "Invalid file.";
    public static final String MESSAGE_INVALID_FILE_FORMAT = "Invalid file format.";
    public static final String MESSAGE_INVALID_FILE_TYPE = "Invalid file type. Only .txt files are accepted";

    private final File importFile;

    public ImportFile(String filePath) {
        importFile = new File(filePath);
    }

    /**
     * Ensures the file to be imported is valid, readable and non empty.
     */
    public boolean isFileValid() {
        if (!isValidImportFile()) {
            return false;
        } else if (!isValidImportFileType()) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the import file is a file and is not empty.
     */
    private boolean isValidImportFile() {
        if (!importFile.isFile()) {
            return false;
        } else if (importFile.length() == 0) {
            return false;
        }
        return true;
    }

    /**
     * Checks if file is a readable .txt format.
     * @return true if file is a readable text file.
     */
    private boolean isValidImportFileType() {
        try {
            String fileType = Files.probeContentType(importFile.toPath());
            if (!fileType.equals("text/plain")) {
                return false;
            }
        } catch (IOException ioe) {
            return false;
        }
        return true;
    }

    // TODO: write the format here
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
                if (FileParserUtil.isEmpty(line)) {
                    continue;
                }

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
