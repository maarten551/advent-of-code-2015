package nl.maarten551.code.adventofcode.day4.part1.thread.adventcoinminer;

import nl.maarten551.code.adventofcode.day4.part1.thread.IThread;
import nl.maarten551.code.adventofcode.day4.part1.thread.ThreadPool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AdventCoinMinerThread implements IThread {
    private MessageDigest messageDigest;
    private IHashKeyDeliverer hashKeyDeliverer;
    private Thread currentThread;
    private ThreadPool threadPool;
    private boolean forceStop = false;

    public AdventCoinMinerThread(IHashKeyDeliverer hashKeyDeliverer, ThreadPool threadPool) {
        this.hashKeyDeliverer = hashKeyDeliverer;
        this.threadPool = threadPool;

        try {
            this.messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void startThread() {
        this.currentThread = new Thread(this);
        this.currentThread.setDaemon(true);
        this.currentThread.start();
    }

    public boolean isRunning() {
        return this.currentThread.isAlive();
    }

    public void stopThread() {
        this.forceStop = true;
    }

    public void run() {
        System.out.println("Thread is executed");
        while(!this.forceStop) {
            try {
                if (!this.hashKeyDeliverer.isOperationCompleted()) {
                    String keyToHash = this.hashKeyDeliverer.nextKeyToHash();
                    this.hashKeyDeliverer.controlCreatedHash(keyToHash, this.hashAndFormatAsHexadecimal(keyToHash));
                } else {
                    this.stopThread();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.threadPool.removeThread(this);
    }

    private String hashAndFormatAsHexadecimal(String keyToHash) {
        byte[] array = this.messageDigest.digest(keyToHash.getBytes());

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
            stringBuilder.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }

        return stringBuilder.toString();
    }

    public void join() throws InterruptedException {
        this.currentThread.join();
    }
}
