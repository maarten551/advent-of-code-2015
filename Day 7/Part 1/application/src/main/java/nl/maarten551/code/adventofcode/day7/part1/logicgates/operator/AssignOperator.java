package nl.maarten551.code.adventofcode.day7.part1.logicgates.operator;

import nl.maarten551.code.adventofcode.day7.part1.logicgates.LogicGateBoard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssignOperator extends Operator {
    private static final Pattern commandDissectRegex = Pattern.compile("^([a-z0-9]{1,5}) -> ([a-z]{1,2})$");

    private String sourceAddress;
    private String address;

    public boolean compileCommand(String command) {
        Matcher matcher = AssignOperator.commandDissectRegex.matcher(command);
        if(matcher.find()) {
            this.sourceAddress = matcher.group(1);
            this.address = matcher.group(2);
            this.isCompiled = true;
        }

        return this.isCompiled;
    }

    public boolean executeCommand(LogicGateBoard logicGateBoard) {
        if(this.isCompiled) {
            Character sourceAddressValue = this.getCharFromAddressOrInteger(this.sourceAddress, logicGateBoard);

            if(sourceAddressValue != null) {
                logicGateBoard.setEmulatedMemoryValue(this.address, sourceAddressValue);

                return true;
            }
        }

        return false;
    }

    @Override
    protected Pattern getCommandDissectRegex() {
        return AssignOperator.commandDissectRegex;
    }
}
