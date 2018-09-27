package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalCards.getTypicalTriviaBundle;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.FileUtil;

public class ImportCommandTest {
    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTriviaBundle(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullFile_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ImportCommand(null);
    }

    @Test
    public void execute_isFileValid_throwsCommandException() throws Exception {
        // no such file
        File noSuchFile = FileUtil.getDummyImportFile();
        ImportCommand importCommand = new ImportCommand(noSuchFile);
        thrown.expect(CommandException.class);
        thrown.expectMessage(ImportCommand.MESSAGE_INVALID_FILE);
        importCommand.execute(model, commandHistory);
        // invalid file type
        File invalidFile = FileUtil.getInvalidImportFile();
        importCommand = new ImportCommand(invalidFile);
        thrown.expect(CommandException.class);
        thrown.expectMessage(ImportCommand.MESSAGE_INVALID_FILE_TYPE);
        importCommand.execute(model, commandHistory);
    }

    // TODO: test import success

    // @Test
    // TODO: ignore duplicates found in import file

    // @Test
    // TODO: compare imported questions and answers to expected
    // public void equals() {
    // }
}
