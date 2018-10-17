package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.test.TriviaResultList;

/**
 * Represents a storage for {@link TriviaResultList}.
 */
public interface TriviaResultsStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getTriviaResultsFilePath();

    /**
     * Returns TriviaResultList data as a {@link TriviaResultList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<TriviaResultList> readTriviaResults() throws DataConversionException, IOException;

    /**
     * @see #getTriviaResultsFilePath() ()
     */
    Optional<TriviaResultList> readTriviaResults(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link TriviaResultList} to the storage.
     * @param triviaResultList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTriviaResults(TriviaResultList triviaResultList) throws IOException;

    /**
     * @see #saveTriviaResults(TriviaResultList)
     */
    void saveTriviaResults(TriviaResultList triviaResultList, Path filePath) throws IOException;
}
