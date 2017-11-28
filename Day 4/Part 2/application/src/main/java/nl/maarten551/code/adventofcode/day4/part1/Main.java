package nl.maarten551.code.adventofcode.day4.part1;

import nl.maarten551.code.adventofcode.day4.part1.thread.ThreadPool;
import nl.maarten551.code.adventofcode.day4.part1.thread.adventcoinminer.AdventCoinMinerThread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileHelper fileHelper = new FileHelper();
        File inputFile = fileHelper.getFileFromResources("input.txt");

        Scanner scanner = new Scanner(inputFile);
        if(scanner.hasNext()) {
            AdventCoinMiner miner = new AdventCoinMiner(scanner.nextLine());
            ThreadPool threadPool = new ThreadPool();

            while(!threadPool.hasLimitReached()) {
                threadPool.startThread(new AdventCoinMinerThread(miner, threadPool));
            }

            threadPool.waitTillAllCompleted();
            System.out.println(miner.getAdventCodeHashes());
        }
    }
}
