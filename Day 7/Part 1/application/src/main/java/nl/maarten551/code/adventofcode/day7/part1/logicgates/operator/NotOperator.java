package nl.maarten551.code.adventofcode.day7.part1.logicgates.operator;

import nl.maarten551.code.adventofcode.day7.part1.logicgates.LogicGateBoard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotOperator extends Operator {
    private static final Pattern commandDissectRegex = Pattern.compile("^NOT ([a-z0-9]{1,5}) -> ([a-z]{1,2})$");

    private boolean isCompiled = false;
    private String sourceAddress;
    private String saveAddress;

    public boolean compileCommand(String command) {
        Matcher matcher = NotOperator.commandDissectRegex.matcher(command);
        if(matcher.find()) {
            this.sourceAddress = matcher.group(1);
            this.saveAddress = matcher.group(2);
            this.isCompiled = true;
        }

        return this.isCompiled;
    }

    public boolean executeCommand(LogicGateBoard logicGateBoard) {
        if(this.isCompiled) {
            Character sourceAddressValue = this.getCharFromAddressOrInteger(this.sourceAddress, logicGateBoard);

            if(sourceAddressValue != null) {
                char value = (char) ~sourceAddressValue;
                logicGateBoard.setEmulatedMemoryValue(this.saveAddress, value);

                return true;
            }
        }

        return false;
    }

    @Override
    protected Pattern getCommandDissectRegex() {
        return NotOperator.commandDissectRegex;
    }
}
