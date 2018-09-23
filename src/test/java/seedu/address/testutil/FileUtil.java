package seedu.address.testutil;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;

/**
 * A utility class to generate files to be used in import tests.
 */
public class FileUtil {
    private static final String INVALID_FILE_NAME = "no such file here m8";
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "TxtFileUtilTest", "emptyTest.txt");

    public static File getDummyFile() {
        return new File(INVALID_FILE_NAME);
    }

    /**
     *  Returns an empty valid txt file.
     * @return empty txt file.
     */
     public static File createEmptyImportFile() {
        File file = TEST_DATA_FOLDER.toFile();
        assertTrue(file.exists());
        return file;
     }

}
