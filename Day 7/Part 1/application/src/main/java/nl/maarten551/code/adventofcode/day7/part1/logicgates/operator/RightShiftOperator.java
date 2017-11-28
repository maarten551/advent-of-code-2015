package nl.maarten551.code.adventofcode.day7.part1.logicgates.operator;

import nl.maarten551.code.adventofcode.day7.part1.logicgates.LogicGateBoard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RightShiftOperator extends Operator {
    private static final Pattern commandDissectRegex = Pattern.compile("([a-z0-9]{1,5}) RSHIFT ([a-z0-9]{1,5}) -> ([a-z]{1,2})$");

    private String sourceAddress;
    private byte jumpByValue;
    private String saveAddress;

    public boolean compileCommand(String command) {
        Matcher matcher = RightShiftOperator.commandDissectRegex.matcher(command);
        if(matcher.find()) {
            this.sourceAddress = matcher.group(1);
            this.jumpByValue = (byte)Integer.parseInt(matcher.group(2));
            this.saveAddress = matcher.group(3);
            this.isCompiled = true;
        }

        return this.isCompiled;
    }

    public boolean executeCommand(LogicGateBoard logicGateBoard) {
        if(this.isCompiled) {
            Character sourceAddressValue = this.getCharFromAddressOrInteger(this.sourceAddress, logicGateBoard);

            if(sourceAddressValue != null) {
                char value = (char)(sourceAddressValue >> this.jumpByValue);
                logicGateBoard.setEmulatedMemoryValue(this.saveAddress, value);

                return true;
            }
        }

        return false;
    }

    @Override
    protected Pattern getCommandDissectRegex() {
        return RightShiftOperator.commandDissectRegex;
    }
}
