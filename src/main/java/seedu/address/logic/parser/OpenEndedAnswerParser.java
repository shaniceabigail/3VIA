package seedu.address.logic.parser;


import seedu.address.logic.commands.OpenEndedAnswerCommand;

public class OpenEndedAnswerParser implements Parser<OpenEndedAnswerCommand> {

    public OpenEndedAnswerCommand parse(String userInput) {
        return new OpenEndedAnswerCommand(userInput.trim());
    }

}
