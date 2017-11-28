package nl.maarten551.code.adventofcode.day5.part2.validator.rule;

import java.util.ArrayList;

public class CombinationPairTwiceInString implements IRule {
    public boolean isValid(String toValidatedString) {
        if(toValidatedString != null && toValidatedString.length() > 0) {
            ArrayList<String> combinationsPair = new ArrayList<String>();
            Character lastLetter = toValidatedString.charAt(0);
            Character currentLetter = null;

            for(int i = 1; i < toValidatedString.length(); i++) {
                currentLetter = toValidatedString.charAt(i);
                String currentCombination = lastLetter.toString() + currentLetter.toString();
                String previousKeyCombination = null;

                if(i > 1) { //Otherwise a character before is getting grabbed
                    previousKeyCombination = toValidatedString.substring(i-2, i);
                }

                if(!currentCombination.equals(previousKeyCombination) && this.combinationExists(combinationsPair, currentCombination)) {
                    return true;
                } else if (!currentCombination.equals(previousKeyCombination)) {
                    combinationsPair.add(currentCombination);
                }

                lastLetter = currentLetter;
            }
        }

        return false;
    }

    private boolean combinationExists(ArrayList<String> combinations, String checkCombination) {
        for(String combination : combinations) {
            if(combination.equals(checkCombination)) {
                return true;
            }
        }

        return false;
    }
}
