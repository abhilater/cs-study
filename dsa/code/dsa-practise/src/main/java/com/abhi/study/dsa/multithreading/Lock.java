package com.abhi.study.dsa.multithreading;

/**
 * @author abhishek
 * @version 1.0, 19/3/17
 */
public class Lock {
    boolean isLocked = false;
    Thread lockedBy = null;

    public synchronized void lock() {
        while (isLocked) {
            try {
                this.wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        lockedBy = Thread.currentThread();
        isLocked = true;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == lockedBy) {
            lockedBy = null;
            isLocked = false;
            this.notifyAll();
        }
    }

    static class SharedObject {
        int counter = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        SharedObject sharedObject = new SharedObject();
        Lock lock = new Lock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                while (sharedObject.counter < 5) {
                    sharedObject.counter++;
                    System.out.println("Thread1: Count: " + sharedObject.counter);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                sharedObject.counter = 0;
                lock.unlock();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                while (sharedObject.counter < 5) {
                    sharedObject.counter++;
                    System.out.println("Thread2: Count: " + sharedObject.counter);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                sharedObject.counter = 0;
                lock.unlock();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                while (sharedObject.counter < 5) {
                    sharedObject.counter++;
                    System.out.println("Thread3: Count: " + sharedObject.counter);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                sharedObject.counter = 0;
                lock.unlock();
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}


