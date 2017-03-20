package com.abhi.study.dsa.multithreading;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author abhishek
 * @version 1.0, 19/3/17
 */
public class FairLock {
    boolean isLocked = false;
    Thread lockedBy = null;
    Queue<QueueObject> queue = new LinkedList<>();

    public void lock() {
        boolean isLockedForCurrent = true;
        QueueObject queueObject = new QueueObject();
        synchronized (this) {
            queue.offer(queueObject);
        }

        while (isLockedForCurrent) {
            synchronized (this) {
                isLockedForCurrent = isLocked || queueObject != queue.peek();
                if (!isLockedForCurrent) {
                    isLocked = true;
                    queue.poll();
                    lockedBy = Thread.currentThread();
                } else {
                    try {
                        queueObject.doWait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void unlock() {
        if (Thread.currentThread() == lockedBy) {
            isLocked = false;
            lockedBy = null;
            if (!queue.isEmpty()) queue.peek().doNotify();
        }
    }

    static class QueueObject {
        boolean notified = false;

        public synchronized void doWait() throws InterruptedException {
            while (!notified) {
                wait();
            }
            notified = false;
        }

        public synchronized void doNotify() {
            notified = true;
            notify();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Lock.SharedObject sharedObject = new Lock.SharedObject();
        FairLock lock = new FairLock();
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
        Thread.sleep(1);
        t2.start();
        Thread.sleep(1);
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
