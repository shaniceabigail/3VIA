package seedu.address.testutil;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.model.portation.ImportFile;

/**
 * A utility class to generate files to be used in import tests.
 */
public class ImportFileUtil {
    public static final Path TYPICAL_FILE = Paths.get("src", "test", "data", "TxtFileUtilTest",
            "typicalTest.txt");
    public static final Path VALID_FILE_NO_TOPIC = Paths.get("src", "test", "data", "TxtFileUtilTest",
            "validTest.txt");
    public static final Path EMPTY_FILE = Paths.get("src", "test", "data", "TxtFileUtilTest",
            "emptyTest.txt");
    public static final Path INVALID_FILE = Paths.get("src", "test", "data", "TxtFileUtilTest",
            "invalidTest.txt.jpg");
    private static final String INVALID_FILE_NAME = "noSuchFile";

    /**
     * Returns an import command string for importing the {@code filePath}.
     */
    public static String getImportCommand(String filePath) {
        return ImportCommand.COMMAND_WORD + " " + filePath;
    }

    public static ImportFile getDummyImportFile() {
        return new ImportFile(INVALID_FILE_NAME);
    }

    /**
     * Returns a typical file to be imported.
     */
    public static ImportFile getTypicalImportFile() {
        return new ImportFile(TYPICAL_FILE.toString());
    }

    /**
     * Returns a valid file to be imported with one question and answer pair without a topic.
     */
    public static ImportFile getValidNoTopicImportFile() {
        return new ImportFile(VALID_FILE_NO_TOPIC.toString());
    }

    /**
    * Returns an empty valid txt file.
    */
    public static ImportFile getEmptyImportFile() {
        return new ImportFile(EMPTY_FILE.toString());
    }

    /**
     * Returns an invalid file type.
     */
    public static ImportFile getInvalidImportFile() {
        return new ImportFile(INVALID_FILE.toString());
    }
}
