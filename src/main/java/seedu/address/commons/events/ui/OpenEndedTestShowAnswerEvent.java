package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

public class OpenEndedTestShowAnswerEvent extends BaseEvent {
    public String userAnswer;

    public OpenEndedTestShowAnswerEvent(String userInput) { this.userAnswer = userInput; }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
