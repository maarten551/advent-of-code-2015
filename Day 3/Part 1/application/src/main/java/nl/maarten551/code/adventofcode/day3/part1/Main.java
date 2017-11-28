package nl.maarten551.code.adventofcode.day3.part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileHelper fileHelper = new FileHelper();
        File inputFile = fileHelper.getFileFromResources("input.txt");

        Scanner scanner = new Scanner(inputFile);

        while(scanner.hasNext()) {
            SantaPath santaPath = new SantaPath();
            HashMap<String, Integer> amountOfGivenPresentsToLocations = santaPath.calculateTakenRoute(scanner.nextLine());
            System.out.println(amountOfGivenPresentsToLocations);
        }
    }
}
