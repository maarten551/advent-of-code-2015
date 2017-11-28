package nl.maarten551.code.adventofcode.day7.part1.logicgates.operator;

import nl.maarten551.code.adventofcode.day7.part1.logicgates.LogicGateBoard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrOperator extends Operator {
    private static final Pattern commandDissectRegex = Pattern.compile("^([a-z0-9]{1,5}) OR ([a-z0-9]{1,5}) -> ([a-z]{1,2})$");

    private boolean isCompiled = false;
    private String sourceAddress;
    private String targetAddress;
    private String saveAddress;

    public boolean compileCommand(String command) {
        Matcher matcher = OrOperator.commandDissectRegex.matcher(command);
        if(matcher.find()) {
            this.sourceAddress = matcher.group(1);
            this.targetAddress = matcher.group(2);
            this.saveAddress = matcher.group(3);
            this.isCompiled = true;
        }

        return this.isCompiled;
    }

    public boolean executeCommand(LogicGateBoard logicGateBoard) {
        if(this.isCompiled) {
            Character sourceAddressValue = this.getCharFromAddressOrInteger(this.sourceAddress, logicGateBoard);
            Character targetAddressValue = this.getCharFromAddressOrInteger(this.targetAddress, logicGateBoard);

            if(sourceAddressValue != null && targetAddressValue != null) {
                Character value = (char)(sourceAddressValue | targetAddressValue);
                logicGateBoard.setEmulatedMemoryValue(this.saveAddress, value);

                return true;
            }
        }

        return false;
    }

    @Override
    protected Pattern getCommandDissectRegex() {
        return OrOperator.commandDissectRegex;
    }
}
