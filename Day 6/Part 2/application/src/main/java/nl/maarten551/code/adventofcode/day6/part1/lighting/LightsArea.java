package nl.maarten551.code.adventofcode.day6.part1.lighting;

public class LightsArea {
    private int widthLimit;
    private int heightLimit;

    private int[] lightStatuses;

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
        this.lightStatuses = new int[amountOfLights];

        // Should be all 0 be default
//        for(int i = 0; i < amountOfLights; i++) {
//            this.lightStatuses[i] = 0;
//        }
    }

    private int determineNextLightStatus(int currentLightStrength, Command.LightCommand lightCommand) {
        switch (lightCommand) {
            case TURN_ON:
                return currentLightStrength+1;
            case TURN_OFF:
                if(currentLightStrength > 0) {
                    return currentLightStrength-1;
                }

                return 0;
            case TOGGLE:
                return currentLightStrength+2;
            default: //To be sure something is returned (even if something is added to the enum)
                return 0;
        }
    }

    public int countLightsOn() {
        int amountOfLightsOn = 0;
        
        for(int lightStrength : lightStatuses) {
            amountOfLightsOn += lightStrength;
        }
        
        return amountOfLightsOn;
    }
}