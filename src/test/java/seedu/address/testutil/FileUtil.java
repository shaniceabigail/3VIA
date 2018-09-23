package seedu.address.testutil;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * A utility class to generate files to be used in import tests.
 */
public class FileUtil {

    public static final Path EMPTY_FILE = Paths.get("src", "test", "data", "TxtFileUtilTest", "emptyTest.txt");
    public static final Path INVALID_FILE = Paths.get("src", "test", "data", "TxtFileUtilTest", "invalidTest.txt.JPG");
    private static final String INVALID_FILE_NAME = "no such file here m8";
    public static File getDummyFile() {
        return new File(INVALID_FILE_NAME);
    }

    /**
    * Returns an empty valid txt file.
    * @return empty txt file.
    */
    public static File getEmptyImportFile() {
        File file = EMPTY_FILE.toFile();
        assertTrue(file.exists());
        assertTrue(file.isFile());
        return file;
    }

    /**
     * Returns an invalid file type.
     * @return an invalid file type.
     */
    public static File getInvalidImportFile() {
        File file = INVALID_FILE.toFile();
        assertTrue(file.exists());
        return file;
    }
}
