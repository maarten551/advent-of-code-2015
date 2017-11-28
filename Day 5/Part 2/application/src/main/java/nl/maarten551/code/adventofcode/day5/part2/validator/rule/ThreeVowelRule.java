package nl.maarten551.code.adventofcode.day5.part2.validator.rule;

import java.util.regex.*;

/**
 * It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
 */
public class ThreeVowelRule implements IRule {
    private static Pattern threeVowelRegex = Pattern.compile("(.*[aeiou].*){3}");

    public boolean isValid(String toValidatedString) {
        Matcher matcher = ThreeVowelRule.threeVowelRegex.matcher(toValidatedString);

        return matcher.find();
    }
}
