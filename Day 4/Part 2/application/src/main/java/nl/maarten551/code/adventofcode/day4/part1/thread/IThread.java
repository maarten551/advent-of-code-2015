package nl.maarten551.code.adventofcode.day4.part1.thread;

public interface IThread extends Runnable {
    boolean isRunning();
    void startThread();
    void stopThread();
    void join() throws InterruptedException;
}
