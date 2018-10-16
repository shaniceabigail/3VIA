package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.test.TriviaTestResult;
import seedu.address.model.test.TriviaTestResultList;

/**
 * An Immutable TriviaTestResultList that is serializable to XML format
 */
@XmlRootElement(name = "testresult")
public class XmlSerializableTriviaTestResult {
    @XmlElement
    private List<XmlAdaptedTriviaTestResult> results;

    /**
     * Creates an empty XmlSerializableTriviaBundle.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableTriviaTestResult() {
        results = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableTriviaTestResult(TriviaTestResultList src) {
        this();
        results.addAll(src.getResultList().stream()
                .map(XmlAdaptedTriviaTestResult::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this triviabundle into the model's {@code TriviaBundle} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedCard}.
     */
    public TriviaTestResultList toModelType() throws IllegalValueException {
        TriviaTestResultList triviaTestResults = new TriviaTestResultList();
        for (XmlAdaptedTriviaTestResult r : results) {
            TriviaTestResult result = r.toModelType();
            triviaTestResults.addTriviaTestResult(result);
        }
        return triviaTestResults;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializableTriviaTestResult)) {
            return false;
        }
        return results.equals(((XmlSerializableTriviaTestResult) other).results);
    }
}
