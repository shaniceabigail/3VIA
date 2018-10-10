package seedu.address.model.test.matchtest;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import seedu.address.model.topic.Topic;

/**
 * Contains the mapping of all the test.
 */
public class MatchTestResults {
    public static final int MAX_RESULTS = 10;
    private final HashMap<Topic, LinkedList<MatchTest>> matchTestResults;

    public MatchTestResults() {
        matchTestResults = new HashMap<>();
    }

    /**
     * Will add a newly completed matchTest to the list of matching test results.
     */
    public void addMatchTestResult(MatchTest matchTest) {
        requireAllNonNull(matchTest);

        LinkedList<MatchTest> resultsFromTopic = getMatchTestResultsByTopic(matchTest.getTopic());
        if (resultsFromTopic == null) {
            matchTestResults.put(matchTest.getTopic(), new LinkedList<>(Arrays.asList(matchTest)));
        } else {
            if (resultsFromTopic.size() >= MAX_RESULTS) {
                resultsFromTopic.pop();
            }
            resultsFromTopic.add(matchTest);
        }
    }

    public LinkedList<MatchTest> getMatchTestResultsByTopic(Topic topic) {
        return matchTestResults.get(topic);
    }
}
