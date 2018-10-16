package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.test.TriviaTestResultList;

/**
 * Represents a storage for {@link TriviaTestResultList}.
 */
public interface TriviaTestResultsStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getTriviaTestResultsFilePath();

    /**
     * Returns TriviaTestResultList data as a {@link TriviaTestResultList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<TriviaTestResultList> readTriviaTestResults() throws DataConversionException, IOException;

    /**
     * @see #getTriviaTestResultsFilePath() ()
     */
    Optional<TriviaTestResultList> readTriviaTestResults(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link TriviaTestResultList} to the storage.
     * @param triviaTestResultList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTriviaTestResults(TriviaTestResultList triviaTestResultList) throws IOException;

    /**
     * @see #saveTriviaTestResults(TriviaTestResultList)
     */
    void saveTriviaTestResults(TriviaTestResultList triviaTestResultList, Path filePath) throws IOException;
}
