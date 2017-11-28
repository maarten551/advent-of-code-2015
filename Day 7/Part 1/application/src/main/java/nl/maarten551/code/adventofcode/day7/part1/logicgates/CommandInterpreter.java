package nl.maarten551.code.adventofcode.day7.part1.logicgates;

import nl.maarten551.code.adventofcode.day7.part1.logicgates.operator.*;

import java.util.ArrayList;

public class CommandInterpreter {
    private ArrayList<Operator> operators;
    private ArrayList<Operator> compiledNotExecutedOperators = new ArrayList<Operator>();

    public CommandInterpreter() {
        this.operators = new ArrayList<Operator>();

        operators.add(new AssignOperator());
        operators.add(new AndOperator());
        operators.add(new OrOperator());
        operators.add(new LeftShiftOperator());
        operators.add(new RightShiftOperator());
        operators.add(new NotOperator());
    }

    public void executeCommand(String command, LogicGateBoard logicGateBoard) throws NoOperatorFoundException {
        Operator compiledOperator = null;

        for(Operator operator : this.operators) {
            if(operator.isCommandParseable(command)) {
                try {
                    compiledOperator = operator.clone();
                    compiledOperator.compileCommand(command);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

                break;
            }
        }

        if(compiledOperator == null)
            throw new NoOperatorFoundException(String.format("Operator for command '%s' not found", command));

        if(compiledOperator.executeCommand(logicGateBoard)) {
            this.executeNotExecutedOperators(logicGateBoard);
            return;
        }

        this.compiledNotExecutedOperators.add(compiledOperator);
    }

    private void executeNotExecutedOperators(LogicGateBoard logicGateBoard) {
        for(int i = this.compiledNotExecutedOperators.size()-1; i >= 0; i--) {
            Operator operator = this.compiledNotExecutedOperators.get(i);
            if(operator.executeCommand(logicGateBoard)) {
                this.compiledNotExecutedOperators.remove(i);
                this.executeNotExecutedOperators(logicGateBoard);

                break;
            }
        }
    }
}
