package seedu.address.logic.parser;


import seedu.address.logic.commands.OpenEndedAnswerCommand;

public class OpenEndedAnswerParser {

    public OpenEndedAnswerCommand parse(String userInput) {
        return new OpenEndedAnswerCommand(userInput);
    }
}
