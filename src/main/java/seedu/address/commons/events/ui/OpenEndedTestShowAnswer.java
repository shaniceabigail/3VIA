package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

public class OpenEndedTestShowAnswer extends BaseEvent {
    public String userAnswer;

    public OpenEndedTestShowAnswer(String userInput) { this.userAnswer = userInput;    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
