package com.abhi.study.dsa.multithreading;

/**
 * @author abhishek
 * @version 1.0, 19/3/17
 */
public class ReentrantLock {
    boolean isLocked = false;
    Thread lockedBy = null;
    int lockCounter = 0;

    public synchronized void lock() throws InterruptedException {
        while (isLocked && lockedBy != Thread.currentThread()) {
            this.wait();
        }
        isLocked = true;
        lockCounter++;
        lockedBy = Thread.currentThread();
    }

    public synchronized void unlock() {
        if (lockedBy == Thread.currentThread()) {
            lockCounter--;
            if (lockCounter == 0) {
                isLocked = false;
                lockedBy = null;
                this.notifyAll();
            }
        }
    }

}
