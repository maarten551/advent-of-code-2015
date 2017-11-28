package nl.maarten551.code.adventofcode.day7.part1.logicgates.operator;

import nl.maarten551.code.adventofcode.day7.part1.logicgates.LogicGateBoard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Make it package protected, so only the classes in the same packaged can extend from it
 */
public abstract class Operator implements Cloneable, IOperator {
    protected boolean isCompiled = false;

    protected abstract Pattern getCommandDissectRegex();

    public boolean isCommandParseable(String command) {
        Matcher matcher = this.getCommandDissectRegex().matcher(command);

        return matcher.find();
    }

    protected Character getCharFromAddressOrInteger(String addressOrNumber, LogicGateBoard logicGateBoard) {
        if(this.isNumeric(addressOrNumber)) {
            return (char)Integer.parseInt(addressOrNumber);
        }

        return logicGateBoard.getEmulatedMemoryValue(addressOrNumber);
    }

    private boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    @Override
    public Operator clone() throws CloneNotSupportedException {
        return (Operator)super.clone();
    }
}
