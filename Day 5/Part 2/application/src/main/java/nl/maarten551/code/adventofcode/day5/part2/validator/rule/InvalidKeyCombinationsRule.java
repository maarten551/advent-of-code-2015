package nl.maarten551.code.adventofcode.day5.part2.validator.rule;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
 */
public class InvalidKeyCombinationsRule implements IRule {
    private static String[] invalidKeyCombinations = new String[]{"ab", "cd", "pq", "xy"};
    private ArrayList<Pattern> invalidKeyCombinationsPatterns;

    public InvalidKeyCombinationsRule() {
        this.setupRegexPatterns();
    }

    private void setupRegexPatterns() {
        this.invalidKeyCombinationsPatterns = new ArrayList<Pattern>();

        for(String invalidKeyCombination : InvalidKeyCombinationsRule.invalidKeyCombinations) {
            Pattern invalidKeyCombinationPattern = Pattern.compile(String.format(".*%s.*", invalidKeyCombination));
            this.invalidKeyCombinationsPatterns.add(invalidKeyCombinationPattern);
        }
    }

    public boolean isValid(String toValidatedString) {
        boolean isValid = true;

        for(Pattern invalidKeyCombinationsPattern : this.invalidKeyCombinationsPatterns) {
            Matcher matcher = invalidKeyCombinationsPattern.matcher(toValidatedString);
            if(matcher.find()) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }
}
