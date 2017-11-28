package nl.maarten551.code.adventofcode.day3.part1;

import java.awt.*;
import java.util.HashMap;

public class SantaPath {
    private enum POSSIBLE_DIRECTIONS {UP, RIGHT, DOWN, LEFT}
    private HashMap<Character, POSSIBLE_DIRECTIONS> charToPossibleDirection;

    public SantaPath() {
        this.charToPossibleDirection = new HashMap<Character, POSSIBLE_DIRECTIONS>();
        this.charToPossibleDirection.put('^', POSSIBLE_DIRECTIONS.UP);
        this.charToPossibleDirection.put('>', POSSIBLE_DIRECTIONS.RIGHT);
        this.charToPossibleDirection.put('v', POSSIBLE_DIRECTIONS.DOWN);
        this.charToPossibleDirection.put('<', POSSIBLE_DIRECTIONS.LEFT);
    }

    public HashMap<String, Integer> calculateTakenRoute(String pathTaken) {
        HashMap<String, Integer> visitedHouses = new HashMap<String, Integer>();
        Dimension currentPosition = new Dimension(0, 0);
        this.addOrUpdateVisitedHouses(visitedHouses, currentPosition); //Present is given to first house

        for(Character directionChar : pathTaken.toCharArray()) {
            POSSIBLE_DIRECTIONS takenDirection = this.charToPossibleDirection.get(directionChar);
            if (takenDirection != null) {
                this.updateDimensionByTakenDirection(currentPosition, takenDirection);
                this.addOrUpdateVisitedHouses(visitedHouses, currentPosition);
            }
        }

        return visitedHouses;
    }

    private void addOrUpdateVisitedHouses(HashMap<String, Integer> visitedHouses, Dimension currentPosition) {
        String parsedCoordinates = String.format("%d/%d", currentPosition.height, currentPosition.width);
        Integer currentAmountOfPresentsGivenAtPostion = visitedHouses.get(parsedCoordinates);

        if(currentAmountOfPresentsGivenAtPostion == null) {
            visitedHouses.put(parsedCoordinates, 1);
        } else {
            visitedHouses.put(parsedCoordinates, currentAmountOfPresentsGivenAtPostion+1);
        }
    }

    private void updateDimensionByTakenDirection(Dimension dimension, POSSIBLE_DIRECTIONS takenDirection) {
        switch (takenDirection) {
            case UP:
                dimension.height++;
                break;
            case RIGHT:
                dimension.width++;
                break;
            case DOWN:
                dimension.height--;
                break;
            case LEFT:
                dimension.width--;
                break;
        }
    }
}
