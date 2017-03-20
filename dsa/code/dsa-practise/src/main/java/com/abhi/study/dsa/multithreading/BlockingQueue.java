package com.abhi.study.dsa.multithreading;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author abhishek
 * @version 1.0, 19/3/17
 */
public class BlockingQueue<T> {

    private Queue<T> queue = null;
    private int capacity;

    public BlockingQueue(int capacity) {
        this.queue = new LinkedList<T>();
        this.capacity = capacity;
    }

    public synchronized void offer(T item) {
        while (capacity == queue.size()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (queue.size() == 0) notifyAll();
        queue.offer(item);
    }

    public synchronized T poll() {
        while (queue.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (queue.size() == capacity) notifyAll();
        return queue.poll();
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new BlockingQueue(10);
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Started producer");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = 0;
                while (i++ <= 15) {
                    System.out.println("Produced " + i);
                    blockingQueue.offer(i);
                }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Started consumer");
                while (true) {
                    Object r = blockingQueue.poll();
                    if((int)r == 10)  {
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Consumed " + r);
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

}
