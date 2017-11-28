package nl.maarten551.code.adventofcode.day5.part2.validator.rule;

/**
 * It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
 */
public class TwoTheSameLettersAfterEachOtherRule implements IRule {
    public boolean isValid(String toValidatedString) {
        char[] toValidatedCharArray = toValidatedString.toCharArray();
        char lastCheckedCharacter = ' '; //Shouldn't be in the string

        for(char nextChar : toValidatedCharArray) {
            if(nextChar != lastCheckedCharacter) {
                lastCheckedCharacter = nextChar;
                continue;
            }

            return true;
        }

        return false;
    }
}
