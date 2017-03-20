package com.abhi.study.dsa.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author abhishek
 * @version 1.0, 19/3/17
 */
public class MyConcurrentHashMap<K, V> {

    private Entry[] buckets = null;
    private ReadWriteLock[] segments = null;
    private int size = 0;

    public MyConcurrentHashMap(int size) {
        this.buckets = new Entry[size];
        this.segments = new ReadWriteLock[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = null;
            segments[i] = new ReadWriteLock();
        }
        this.size = size;
    }

    public Object get(K key) {
        Object result = null;
        int bucketIdx = key.hashCode() % size;
        ReadWriteLock lock = segments[bucketIdx];
        lock.readLock();
        result = buckets[bucketIdx] != null ? buckets[bucketIdx].getValue() : null;
        lock.readUnlock();
        return result;
    }

    public void put(K key, V value) {
        int bucketIdx = key.hashCode() % size;
        ReadWriteLock lock = segments[bucketIdx];
        lock.writeLock();
        buckets[bucketIdx] = new Entry(key, value);
        lock.writeUnlock();
    }

    static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MyConcurrentHashMap<Integer, Integer> myConcurrentHashMap = new MyConcurrentHashMap<>(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 8; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println(myConcurrentHashMap.get(((int) (Math.random() * 10)) % 10));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }
        AtomicInteger c = new AtomicInteger(0);
        for (int i = 0; i < 2; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {

                    while (true) {
                        int key = ((int) (Math.random() * 10)) % 10;
                        int value = c.incrementAndGet();
                        System.out.println("Put " + key + ":" + value);
                        myConcurrentHashMap.put(key, value);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }
    }
}
