package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.FileUtil.createEmptyImportFile;

import java.io.File;
import org.junit.Test;
import seedu.address.logic.commands.ImportCommand;

public class ImportCommandParserTest {
    private ImportCommandParser parser = new ImportCommandParser();
    // TODO: to implement after setting up FileUtil class
     // @Test
     // public void parse_allFieldsPresent_success() {
        // whitespace only preamble
     //   assertParseSuccess(parser, PREAMBLE_WHITESPACE , new ImportCommand());
     // }

    @Test
    public void parse_invalidValue_failure() {
        // empty string
        assertParseFailure(parser, PREAMBLE_WHITESPACE, String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
    }
}
