package nl.maarten551.code.adventofcode.day5.part2.validator.rule;

public class KeyBetweenSameLetters implements IRule {
    public boolean isValid(String toValidatedString) {
        if(toValidatedString != null && toValidatedString.length() >= 3) {
            char[] currentCharCombination = new char[]{toValidatedString.charAt(0), toValidatedString.charAt(1), toValidatedString.charAt(2)};

            for(int i = 3; i < toValidatedString.length(); i++) {
                if(currentCharCombination[0] == currentCharCombination[2]) {
                    return true;
                }

                currentCharCombination[0] = currentCharCombination[1];
                currentCharCombination[1] = currentCharCombination[2];
                currentCharCombination[2] = toValidatedString.charAt(i);
            }
        }

        return false;
    }
}
