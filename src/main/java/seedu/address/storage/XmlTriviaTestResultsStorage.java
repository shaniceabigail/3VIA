package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.test.TriviaTestResultList;

/**
 *
 */
public class XmlTriviaTestResultsStorage implements TriviaTestResultsStorage {
    private static final Logger logger = LogsCenter.getLogger(XmlTriviaTestResultsStorage.class);

    private Path filePath;

    public XmlTriviaTestResultsStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTriviaTestResultsFilePath() {
        return filePath;
    }

    @Override
    public Optional<TriviaTestResultList> readTriviaTestResults() throws DataConversionException, IOException {
        return readTriviaTestResults(filePath);
    }

    /**
     * Similar to {@link #readTriviaTestResults()} ()}
     * @param filePath location of the data. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<TriviaTestResultList> readTriviaTestResults(Path filePath) throws DataConversionException,
            FileNotFoundException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            logger.info("TriviaResults file " + filePath + " not found");
            return Optional.empty();
        }

        XmlSerializableTriviaTestResult xmlTriviaTestResult = XmlFileStorage.loadDataFromSaveFile(filePath,
                XmlSerializableTriviaTestResult.class);
        try {
            return Optional.of(xmlTriviaTestResult.toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTriviaTestResults(TriviaTestResultList triviaTestResultList) throws IOException {
        saveTriviaTestResults(triviaTestResultList, filePath);
    }

    /**
     * Similar to {@link #saveTriviaTestResults(TriviaTestResultList)}
     * @param filePath location of the data. Cannot be null
     */
    public void saveTriviaTestResults(TriviaTestResultList triviaTestResultList, Path filePath) throws IOException {
        requireNonNull(triviaTestResultList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        XmlFileStorage.saveDataToFile(filePath, new XmlSerializableTriviaTestResult(triviaTestResultList));
    }
}
