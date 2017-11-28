package nl.maarten551.code.adventofcode.day6.part1.lighting;

import java.awt.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandInterpreter {
    private final static Pattern coordinateExtractPattern = Pattern.compile(".*?(\\d{1,3},\\d{1,3}).*?(\\d{1,3},\\d{1,3}).*");

    private HashMap<String, Command.LightCommand> stringToLightCommandConversion;

    public CommandInterpreter() {
        this.stringToLightCommandConversion = new HashMap<String, Command.LightCommand>();
        this.stringToLightCommandConversion.put("turn on", Command.LightCommand.TURN_ON);
        this.stringToLightCommandConversion.put("turn off", Command.LightCommand.TURN_OFF);
        this.stringToLightCommandConversion.put("toggle", Command.LightCommand.TOGGLE);
    }

    public Command interpretCommandString(String commandString) {
        Command.LightCommand lightCommand = this.interpretCommandType(commandString);
        Command interpretedCommand = new Command(lightCommand, new Dimension(0, 0), new Dimension(0, 0));

        this.extractCoordinatesToCommand(interpretedCommand, commandString);

        return interpretedCommand;
    }

    private void extractCoordinatesToCommand(Command command, String commandString) {
        Matcher matcher = CommandInterpreter.coordinateExtractPattern.matcher(commandString);

        if(matcher.find() && matcher.groupCount() >= 2) {
            command.setStartLocation(this.convertStringToDimension(matcher.group(1)));
            command.setEndLocation(this.convertStringToDimension(matcher.group(2)));
        }
    }

    private Dimension convertStringToDimension(String coordinatesString) {
        String[] splitCoordinates = coordinatesString.split(",");

        return new Dimension(Integer.parseInt(splitCoordinates[0]), Integer.parseInt(splitCoordinates[1]));
    }

    private Command.LightCommand interpretCommandType(String commandString) {
        for(String lightCommandString : this.stringToLightCommandConversion.keySet()) {
            if(commandString.startsWith(lightCommandString)) {
                return this.stringToLightCommandConversion.get(lightCommandString);
            }
        }

        return Command.LightCommand.TOGGLE; //Default
    }
}
