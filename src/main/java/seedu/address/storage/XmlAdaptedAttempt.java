package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.test.Attempt;

/**
 * JAXB-friendly version of the Trivia Test's Attempt.
 */
public class XmlAdaptedAttempt {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Trivia attempt's %s field is missing!";

    @XmlElement(required = true)
    private XmlAdaptedCard attemptedCard;
    @XmlElement(required = true)
    private String answer;
    @XmlElement(required = true)
    private String isCorrect;

    /**
     * Constructs an XmlAdaptedAttempt.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedAttempt() {}

    /**
     * Constructs a {@code XmlAdaptedAttempt} with the given parameters.
     */
    public XmlAdaptedAttempt(XmlAdaptedCard attemptedCard, String answer, String isCorrect) {
        this.attemptedCard = attemptedCard;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    /**
     * Converts a given Attempt into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created
     */
    public XmlAdaptedAttempt(Attempt source) {
        attemptedCard = new XmlAdaptedCard(source.getAttemptedCard());
        answer = source.getRawAnswer();
        isCorrect = String.valueOf(source.isCorrect());
    }

    /**
     * Converts this jaxb-friendly adapted attempt object into the model's Attempt object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attempt
     */
    public Attempt toModelType() throws IllegalValueException {
        if (attemptedCard == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Card.class.getSimpleName()));
        }

        if (answer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName()));
        }

        if (isCorrect == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "isCorrect"));
        }

        if (!isCorrect.equals("true") && !isCorrect.equals("false")) {
            throw new IllegalValueException("Invalid boolean values for isCorrect field.");
        }

        return new Attempt(attemptedCard.toModelType(), answer, Boolean.valueOf(isCorrect));
    }
}
