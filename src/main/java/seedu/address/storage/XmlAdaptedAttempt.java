package seedu.address.storage;

import static seedu.address.commons.util.CollectionUtil.ifNullThrows;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.core.Messages;
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
        ifNullThrows(attemptedCard,
                new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Card.class.getSimpleName())));
        ifNullThrows(answer,
                new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName())));
        ifNullThrows(isCorrect,
                new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "isCorrect")));

        if (!isCorrect.equals("true") && !isCorrect.equals("false")) {
            throw new IllegalValueException(Messages.MESSAGE_INVALID_BOOLEAN_FOR_ISCORRECT);
        }

        return new Attempt(attemptedCard.toModelType(), answer, Boolean.valueOf(isCorrect));
    }
}
