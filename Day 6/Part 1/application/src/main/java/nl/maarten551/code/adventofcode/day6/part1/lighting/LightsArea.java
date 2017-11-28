package nl.maarten551.code.adventofcode.day6.part1.lighting;

public class LightsArea {
    private int widthLimit;
    private int heightLimit;

    private boolean[] lightStatuses;

    public LightsArea(int widthLimit, int heightLimit) {
        this.widthLimit = widthLimit;
        this.heightLimit = heightLimit;

        this.setupLightArea();
    }

    public void executeCommand(Command command) {
        for(int width = command.getStartLocation().width; width <= command.getEndLocation().width; width++) {
            for(int height = command.getStartLocation().height; height <= command.getEndLocation().height; height++) {
                int lightIndex = this.convertCoordinatesToLightIndex(width, height);
                this.lightStatuses[lightIndex] = this.determineNextLightStatus(this.lightStatuses[lightIndex], command.getLightCommand());
            }
        }
    }

    public boolean isLightOn(int width, int height) {
        return this.lightStatuses[this.convertCoordinatesToLightIndex(width, height)];
    }

    private int convertCoordinatesToLightIndex(int width, int height) {
        int lightIndex = 0;

        if(width <= this.widthLimit && height <= this.heightLimit) {
            lightIndex = width * 1000;
            lightIndex += height;
        }

        return lightIndex;
    }

    private void setupLightArea() {
        int amountOfLights = this.widthLimit * this.heightLimit;
        this.lightStatuses = new boolean[amountOfLights];

        for(int i = 0; i < amountOfLights; i++) {
            this.lightStatuses[i] = false;
        }
    }

    private boolean determineNextLightStatus(boolean currentStatus, Command.LightCommand lightCommand) {
        switch (lightCommand) {
            case TURN_ON:
                return true;
            case TURN_OFF:
                return false;
            case TOGGLE:
                return !currentStatus;
            default: //To be sure something is returned (even if something is added to the enum)
                return false;
        }
    }

    public int countLightsOn() {
        int amountOfLightsOn = 0;
        
        for(boolean lightOn : lightStatuses) {
            amountOfLightsOn += (lightOn) ? 1 : 0;
        }
        
        return amountOfLightsOn;
    }
}