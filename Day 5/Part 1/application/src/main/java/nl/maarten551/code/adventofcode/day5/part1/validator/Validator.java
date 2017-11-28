package nl.maarten551.code.adventofcode.day5.part1.validator;

import nl.maarten551.code.adventofcode.day5.part1.validator.rule.IRule;
import nl.maarten551.code.adventofcode.day5.part1.validator.rule.InvalidKeyCombinationsRule;
import nl.maarten551.code.adventofcode.day5.part1.validator.rule.ThreeVowelRule;
import nl.maarten551.code.adventofcode.day5.part1.validator.rule.TwoTheSameLettersAfterEachOtherRule;

import java.util.ArrayList;

public class Validator {
    ArrayList<IRule> validationRules = new ArrayList<IRule>();

    public Validator() {
        this.validationRules.add(new ThreeVowelRule());
        this.validationRules.add(new TwoTheSameLettersAfterEachOtherRule());
        this.validationRules.add(new InvalidKeyCombinationsRule());
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
