package nl.maarten551.code.adventofcode.day5.part1;

import nl.maarten551.code.adventofcode.day5.part1.validator.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileHelper fileHelper = new FileHelper();
        File inputFile = fileHelper.getFileFromResources("input.txt");

        Validator validator = new Validator();

        ArrayList<String> validStrings = new ArrayList<String>();

        Scanner scanner = new Scanner(inputFile);

        while(scanner.hasNext()) {
            String nextLine = scanner.nextLine();

            if(validator.isStringValid(nextLine)) {
                validStrings.add(nextLine);
            }
        }

        System.out.println(validStrings);
    }
}
