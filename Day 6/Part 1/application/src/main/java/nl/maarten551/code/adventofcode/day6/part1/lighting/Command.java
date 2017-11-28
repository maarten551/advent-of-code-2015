package nl.maarten551.code.adventofcode.day6.part1.lighting;

import java.awt.*;

public class Command {
    public enum LightCommand{TURN_ON, TURN_OFF, TOGGLE}

    private LightCommand lightCommand;
    private Dimension startLocation;
    private Dimension endLocation;

    public Command(LightCommand lightCommand, Dimension startLocation, Dimension endLocation) {
        this.lightCommand = lightCommand;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public LightCommand getLightCommand() {
        return lightCommand;
    }

    public void setLightCommand(LightCommand lightCommand) {
        this.lightCommand = lightCommand;
    }

    public Dimension getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Dimension startLocation) {
        this.startLocation = startLocation;
    }

    public Dimension getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Dimension endLocation) {
        this.endLocation = endLocation;
    }
}
