package com.abhi.study.dsa.multithreading;

/**
 * @author abhishek
 * @version 1.0, 19/3/17
 */
public class ReadWriteLock {
    int readers;
    int writers;
    int writeReqs;

    public synchronized void readLock() {
        while (writers > 0 || writeReqs > 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        readers++;
    }

    public synchronized void readUnlock() {
        readers--;
        this.notifyAll();
    }

    public synchronized void writeLock() {
        writeReqs++;
        while (readers > 0 || writers > 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writeReqs--;
        writers++;
    }

    public synchronized void writeUnlock() {
        writers--;
        notifyAll();
    }
}
