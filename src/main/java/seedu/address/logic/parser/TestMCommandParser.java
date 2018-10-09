package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOPIC;

import java.util.stream.Stream;

import seedu.address.logic.commands.TestMCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.topic.Topic;

/**
 * Parses input arguments and creates a new TestMCommand object
 */
public class TestMCommandParser implements Parser<TestMCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TestMCommand
     * and returns an TestMCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TestMCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TOPIC);

        if (!arePrefixesPresent(argMultimap, PREFIX_TOPIC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TestMCommand.MESSAGE_USAGE));
        }

        Topic tag = ParserUtil.parseTopic(argMultimap.getValue(PREFIX_TOPIC).get());

        return new TestMCommand(tag);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
