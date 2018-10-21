package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TypicalCards.getTypicalTriviaBundle;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.portation.ImportFile;
import seedu.address.testutil.ImportFileUtil;

public class ImportCommandTest {
    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTriviaBundle(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_cardsAcceptedByModel_importSuccessful() throws Exception {
        ImportFile file = ImportFileUtil.getTypicalImportFile();

        CommandResult commandResult = new ImportCommand(file).execute(model, commandHistory);

        assertEquals(String.format(ImportCommand.MESSAGE_SUCCESS, file.getFileName()), commandResult.feedbackToUser);

        // TODO: verify same cards imported


        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void constructor_nullFile_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new ImportCommand(null);
    }
}
