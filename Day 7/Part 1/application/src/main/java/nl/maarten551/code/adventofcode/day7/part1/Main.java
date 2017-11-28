package nl.maarten551.code.adventofcode.day7.part1;

import nl.maarten551.code.adventofcode.day7.part1.logicgates.LogicGateBoard;
import nl.maarten551.code.adventofcode.day7.part1.logicgates.CommandInterpreter;
import nl.maarten551.code.adventofcode.day7.part1.logicgates.operator.NoOperatorFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileHelper fileHelper = new FileHelper();
        File inputFile = fileHelper.getFileFromResources("input.txt");

        CommandInterpreter commandInterpreter = new CommandInterpreter();
        LogicGateBoard logicGateBoard = new LogicGateBoard();

        Scanner scanner = new Scanner(inputFile);

        try {
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();

                commandInterpreter.executeCommand(nextLine, logicGateBoard);
            }
        } catch (NoOperatorFoundException e) {
            e.printStackTrace();
        }

        logicGateBoard.printMemory();
        System.out.println((int)logicGateBoard.getEmulatedMemoryValue("a"));
    }
}
