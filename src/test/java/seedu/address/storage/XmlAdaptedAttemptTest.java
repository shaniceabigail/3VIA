package seedu.address.storage;

import static org.junit.Assert.assertEquals;

import static seedu.address.storage.XmlAdaptedAttempt.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalAttempts.FLAT_EARTH_CORRECT_ATTEMPT;
import static seedu.address.testutil.TypicalCards.Q_FLAT_EARTH;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.testutil.Assert;

public class XmlAdaptedAttemptTest {
    private static final String INVALID_CORRECTNESS = "not correct";

    private static final String VALID_CORRECTNESS = String.valueOf(FLAT_EARTH_CORRECT_ATTEMPT.isCorrect());
    private static final String VALID_EMPTY_ANSWER = ""; // empty strings allowed for attempt
    private static final String VALID_ANSWER = Q_FLAT_EARTH.getAnswer().value;
    private static final XmlAdaptedCard VALID_XML_CARD = new XmlAdaptedCard(Q_FLAT_EARTH);

    @Test
    public void toModelType_validAttempt_returnsAttempt() throws Exception {
        XmlAdaptedAttempt attempt = new XmlAdaptedAttempt(FLAT_EARTH_CORRECT_ATTEMPT);
        assertEquals(FLAT_EARTH_CORRECT_ATTEMPT, attempt.toModelType());
    }

    @Test
    public void toModelType_invalidCorrectness_throwsIllegalValueException() {
        XmlAdaptedAttempt attempt =
                new XmlAdaptedAttempt(VALID_XML_CARD, VALID_ANSWER, INVALID_CORRECTNESS);
        String expectedMessage = "Invalid boolean values for isCorrect field.";
        Assert.assertThrows(IllegalValueException.class, expectedMessage, attempt::toModelType);
    }

    @Test
    public void toModelType_nullCorrectness_throwsIllegalValueException() {
        XmlAdaptedAttempt attempt =
                new XmlAdaptedAttempt(VALID_XML_CARD, VALID_EMPTY_ANSWER, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "isCorrect");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, attempt::toModelType);
    }

    @Test
    public void toModelType_nullAnswer_throwsIllegalValueException() {
        XmlAdaptedAttempt attempt =
                new XmlAdaptedAttempt(VALID_XML_CARD, null, VALID_CORRECTNESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, attempt::toModelType);
    }

    @Test
    public void toModelType_nullAttemptedCard_throwsIllegalValueException() {
        XmlAdaptedAttempt attempt =
                new XmlAdaptedAttempt(null, VALID_ANSWER, VALID_CORRECTNESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Card.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, attempt::toModelType);
    }
}
