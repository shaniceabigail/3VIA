package seedu.address.model.test;

import java.util.Arrays;

/**
 * Represents all the different kinds of test in 3VIA.
 */
public enum TestType {
    MATCH_TEST;

    public static final String MESSAGE_TEST_TYPE_CONSTRAINTS = "TestType should belong to one of the defined "
            + "TestType enum.";

    public static boolean consistOf(String value) {
        return Arrays.asList(TestType.values()).contains(valueOf(value));
    }
}
