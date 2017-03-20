package com.abhi.study.dsa.multithreading;

/**
 * @author abhishek
 * @version 1.0, 19/3/17
 */
public class SimulateDeadlock {

    static void simulate() throws InterruptedException {
        Lock A = new Lock();
        Lock B = new Lock();
        Lock C = new Lock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                A.lock();
                System.out.println("T1: Locked A");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                B.lock();
                System.out.println("T1: Locked B");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                C.lock();
                System.out.println("T1: Locked C");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                B.lock();
                System.out.println("T2: Locked B");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                C.lock();
                System.out.println("T2: Locked C");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                A.lock();
                System.out.println("T2: Locked A");
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                C.lock();
                System.out.println("T3: Locked C");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                A.lock();
                System.out.println("T3: Locked A");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                B.lock();
                System.out.println("T3: Locked B");
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

    }

    public static void main(String[] args) throws InterruptedException {
        simulate();
    }
}
