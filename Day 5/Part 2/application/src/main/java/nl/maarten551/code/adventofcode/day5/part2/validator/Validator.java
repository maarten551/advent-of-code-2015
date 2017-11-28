package nl.maarten551.code.adventofcode.day5.part2.validator;

import nl.maarten551.code.adventofcode.day5.part2.validator.rule.*;

import java.util.ArrayList;

public class Validator {
    ArrayList<IRule> validationRules = new ArrayList<IRule>();

    public Validator() {
        this.validationRules.add(new CombinationPairTwiceInString());
        this.validationRules.add(new KeyBetweenSameLetters());
    }

    public boolean isStringValid(String toValidate) {
        boolean isValid = true;

        for(IRule validationRule : validationRules) {
            if(!validationRule.isValid(toValidate)) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }
}
