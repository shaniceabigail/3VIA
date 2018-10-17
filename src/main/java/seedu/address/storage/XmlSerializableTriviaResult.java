package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.test.TriviaResult;
import seedu.address.model.test.TriviaResultList;

/**
 * An Immutable TriviaResultList that is serializable to XML format
 */
@XmlRootElement(name = "testresult")
public class XmlSerializableTriviaResult {
    @XmlElement
    private List<XmlAdaptedTriviaResult> results;

    /**
     * Creates an empty XmlSerializableTriviaBundle.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableTriviaResult() {
        results = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableTriviaResult(TriviaResultList src) {
        this();
        results.addAll(src.getResultList().stream()
                .map(XmlAdaptedTriviaResult::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this triviabundle into the model's {@code TriviaBundle} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedCard}.
     */
    public TriviaResultList toModelType() throws IllegalValueException {
        TriviaResultList triviaResults = new TriviaResultList();
        for (XmlAdaptedTriviaResult r : results) {
            TriviaResult result = r.toModelType();
            triviaResults.addTriviaResult(result);
        }
        return triviaResults;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializableTriviaResult)) {
            return false;
        }
        return results.equals(((XmlSerializableTriviaResult) other).results);
    }
}
