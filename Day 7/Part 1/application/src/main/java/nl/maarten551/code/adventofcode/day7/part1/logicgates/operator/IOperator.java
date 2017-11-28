package nl.maarten551.code.adventofcode.day7.part1.logicgates.operator;

import nl.maarten551.code.adventofcode.day7.part1.logicgates.LogicGateBoard;

public interface IOperator {
    boolean compileCommand(String command);
    boolean executeCommand(LogicGateBoard logicGateBoard);
}
