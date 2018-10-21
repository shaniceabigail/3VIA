package seedu.address.model.portation;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TOPIC;
import static seedu.address.model.portation.ImportFile.MESSAGE_INVALID_FILE_TYPE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import java.util.stream.Stream;
import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.events.ExtraInformationDisplay;
import seedu.address.commons.events.ui.ExtraInformationDisplayChangeEvent;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.portation.exceptions.FileParseException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Card;
import seedu.address.model.card.Question;
import seedu.address.model.topic.Topic;

/**
 * Contains methods used for parsing a file to cards and vice-versa.
 */
public class FileParser {
    public static final String MESSAGE_INVALID_FILE_FORMAT = "Invalid file format.";
    public static final String MESSAGE_INVALID_TOPIC_FORMAT = "Topic format.";

    /**
     * Parses the file into a list of cards to be imported.
     * @return Set of cards.
     * @throws FileParseException If the format of the file does not conform to the expected format.
     */
    public static Set<Card> parseFileToCards(File importFile) throws FileParseException {
        Set<Card> cards = new HashSet<>();

        // TODO: add support for quotes
        try (BufferedReader br = new BufferedReader(new FileReader(importFile))) {
            String line;
            String[] cardString;
            Set<Topic> topicList = new HashSet<>(); // default empty topic list

            while ((line = br.readLine()) != null) {
                if (line.contains(PREFIX_TOPIC.toString())) {
                    ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(line, PREFIX_TOPIC);
                    if (!isPrefixPresent(argMultimap, PREFIX_TOPIC) || !argMultimap.getPreamble().isEmpty()) {
                        throw new FileParseException(String
                                .format(Messages.MESSAGE_INVALID_FILE_FORMAT, MESSAGE_INVALID_TOPIC_FORMAT));
                    }
                    topicList = ParserUtil.parseTopics(argMultimap.getAllValues(PREFIX_TOPIC));
                } else {
                    cardString = parseLine(line);
                    cards.add(stringToCard(cardString, topicList));
                }
            }
        } catch (IOException ioe) {
            throw new FileParseException(MESSAGE_INVALID_FILE_TYPE);
        } catch (ParseException pe) {
            EventsCenter
                    .getInstance()
                    .post(new ExtraInformationDisplayChangeEvent(ExtraInformationDisplay.IMPORT_HELP_DISPLAY));
            throw new FileParseException(MESSAGE_INVALID_FILE_FORMAT);
        }

        return cards;
    }

    /**
     * Returns true if the prefixes does not contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix topic) {
        return Stream.of(topic).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Splits a line from a text file into one question and answer pair string. Using ";" as
     * a line separator.
     * @param line Line read from text file.
     * @return An array containing a pair of question and answer in that order.
     * @throws FileParseException If the format of the line read is different from the expected.
     */
    private static String[] parseLine(String line) throws FileParseException {
        String[] cardString = line.split(";");

        if (cardString.length != 2) {
            throw new FileParseException(MESSAGE_INVALID_FILE_FORMAT);
        }
        return cardString;
    }

    /**
     * Converts a question and answer string pair into a card with specified topics.
     * @param tokenizeCardString The question and answer string pair.
     * @return The card created from the question and answer string pair.
     * @throws FileParseException if the question and/ or answer format is different from expected.
     */
    private static Card stringToCard (String[] tokenizeCardString, Set<Topic> topicList) throws FileParseException {
        try {
            Question question = ParserUtil.parseQuestion(tokenizeCardString[0]);
            Answer answer = ParserUtil.parseAnswer(tokenizeCardString[0]);
            return new Card(question, answer, topicList);
        } catch (ParseException pe) {
            throw new FileParseException(MESSAGE_INVALID_FILE_FORMAT);
        }
    }
}
