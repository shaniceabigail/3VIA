package seedu.address.logic.parser;


import seedu.address.logic.commands.OpenEndedAnswerCommand;

public class OpenEndedCommandAnswerParser {

    public OpenEndedAnswerCommand parse(String userInput) {
        return new OpenEndedAnswerCommand(userInput);
    }
}
