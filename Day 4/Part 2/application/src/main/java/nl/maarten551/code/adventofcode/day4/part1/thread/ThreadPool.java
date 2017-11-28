package nl.maarten551.code.adventofcode.day4.part1.thread;

import java.util.ArrayList;

public class ThreadPool {
    public static int MAXIMUM_AMOUNT_OF_ACTIVE_THREADS = 5;
    ArrayList<IThread> activeThreads;

    public ThreadPool() {
        this.activeThreads = new ArrayList<IThread>();
    }

    public boolean startThread(IThread thread) {
        this.removeNotActiveThreads();
        if(!this.hasLimitReached()) {
            this.activeThreads.add(thread);
            thread.startThread();

            return true;
        }

        return false;
    }

    public boolean hasLimitReached() {
        return this.activeThreads.size() >= ThreadPool.MAXIMUM_AMOUNT_OF_ACTIVE_THREADS;
    }

    private void removeNotActiveThreads() {
        for(int i = this.activeThreads.size()-1; i > 0; i--) {
            IThread thread = this.activeThreads.get(i);
            if(!thread.isRunning()) {
                this.activeThreads.remove(thread);
            }
        }
    }

    public void removeThread(IThread thread) {
        this.activeThreads.remove(thread);
    }

    public void waitTillAllCompleted() {
        for(int i=0; i< this.activeThreads.size(); i++) {
            try {
                activeThreads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
