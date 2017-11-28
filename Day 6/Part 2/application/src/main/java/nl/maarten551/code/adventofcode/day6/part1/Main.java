package nl.maarten551.code.adventofcode.day6.part1;

import nl.maarten551.code.adventofcode.day6.part1.lighting.Command;
import nl.maarten551.code.adventofcode.day6.part1.lighting.CommandInterpreter;
import nl.maarten551.code.adventofcode.day6.part1.lighting.LightsArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileHelper fileHelper = new FileHelper();
        File inputFile = fileHelper.getFileFromResources("input.txt");

        CommandInterpreter commandInterpreter = new CommandInterpreter();
        LightsArea lightsArea = new LightsArea(1000, 1000);

        ArrayList<String> validStrings = new ArrayList<String>();

        Scanner scanner = new Scanner(inputFile);

        while(scanner.hasNext()) {
            String nextLine = scanner.nextLine();

            Command command = commandInterpreter.interpretCommandString(nextLine);
            lightsArea.executeCommand(command);
        }

        System.out.println(lightsArea.countLightsOn());
    }
}
