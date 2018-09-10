package seedu.address.model.card;

import seedu.address.commons.util.StringUtil;
import java.util.function.Predicate;

import java.util.List;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class QuestionContainsKeywordsPredicate implements Predicate<Card> {
    private final List<String> keywords;

    public QuestionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Card card) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(card.getQuestion().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof QuestionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((QuestionContainsKeywordsPredicate) other).keywords)); // state check
    }
}
