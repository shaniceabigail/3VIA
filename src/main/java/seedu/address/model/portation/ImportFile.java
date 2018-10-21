package seedu.address.model.portation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

import seedu.address.model.card.Card;
import seedu.address.model.portation.exceptions.FileParseException;

public class ImportFile {
    public static final String MESSAGE_INVALID_FILE = "Invalid file.";
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

    public Set<Card> parseFileToCards() throws FileParseException {
        return FileParser.parseFileToCards(importFile);
    }

    public String getFileName() {
        return importFile.getName();
    }

    // TODO: equals method
}
