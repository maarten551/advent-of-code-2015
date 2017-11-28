package nl.maarten551.code.adventofcode.day4.part1.thread.adventcoinminer;

public interface IHashKeyDeliverer {
    String nextKeyToHash();
    boolean isOperationCompleted();
    void controlCreatedHash(String usedKey, String hash);
}
