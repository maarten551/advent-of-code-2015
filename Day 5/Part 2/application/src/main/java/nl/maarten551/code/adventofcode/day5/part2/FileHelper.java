package nl.maarten551.code.adventofcode.day5.part2;

import java.io.File;

public class FileHelper {
    public File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getPath().replace("%20", " ")); //Whitespace gives problems
    }
}
