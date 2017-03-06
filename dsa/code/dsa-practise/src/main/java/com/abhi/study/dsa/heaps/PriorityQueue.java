package com.abhi.study.dsa.heaps;

/**
 * @author abhishek
 * @version 1.0, 6/3/17
 */
public class PriorityQueue<T extends PriorityQueue.Priorityable> {

    private T[] heap;
    private int maxSize = 0;
    private int index = -1;

    public PriorityQueue(int maxSize) {
        heap = (T[]) new Priorityable[maxSize];
        this.maxSize = maxSize;
    }

    public void insert(T data) {
        if (index == maxSize - 1) throw new RuntimeException("Queue maxed out");
        index++;
        heap[index] = data;
        siftUp(index);
    }

    public T extractMin() {
        if (isEmpty()) throw new RuntimeException("Queue empty");

        T result = heap[0];
        heap[0] = heap[index];
        index--;
        siftDown(0);
        return result;
    }

    public void changePriority(int i, T newP) {
        T oldP = heap[i];
        heap[i] = newP;
        if (newP.priority() < oldP.priority()) siftUp(i);
        else siftDown(i);
    }

    public void changePriority(T old, T newP) {
        int i = getIndex(old);
        if (i != -1) changePriority(i, newP);
    }

    public int getIndex(T elem) {
        for (int i = 0; i <= index; i++) {
            if (elem.equals(heap[i])) return i;
        }
        return -1;
    }

    public boolean isEmpty() {
        return index == -1;
    }

    private void siftUp(int i) {
        while (i > 0 && heap[i].priority() < heap[parentIdx(i)].priority()) {
            T t = heap[parentIdx(i)];
            heap[parentIdx(i)] = heap[i];
            heap[i] = t;
            i = parentIdx(i);
        }
    }

    private void siftDown(int i) {
        int min = i;
        int left = getLeft(i);
        if (left <= index && heap[left].priority() < heap[i].priority()) min = left;
        int right = getRight(i);
        if (right <= index && heap[right].priority() < heap[min].priority()) min = right;
        if (i != min) {
            T t = heap[i];
            heap[i] = heap[min];
            heap[min] = t;
            siftDown(min);
        }
    }

    public void build(T[] arr) {
        for (T data : arr) {
            insert(data);
        }
    }

    private int parentIdx(int childIdx) {
        return (childIdx - 1) / 2;
    }

    private int getLeft(int idx) {
        return 2 * idx + 1;
    }

    private int getRight(int idx) {
        return 2 * idx + 2;
    }

    public interface Priorityable {
        int priority();
    }

    static class Data implements Priorityable {
        int data;

        public Data(int data) {
            this.data = data;
        }

        @Override
        public int priority() {
            return data;
        }
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(10);
        queue.build(new Data[]{new Data(4), new Data(5), new Data(8), new Data(7), new Data(1), new Data(2), new Data(10), new Data(9), new Data(11), new Data(3)});
        Priorityable res = queue.extractMin();
        assert res.priority() == 1 : "extractMin failed";

        res = queue.extractMin();
        assert res.priority() == 2 : "extractMin failed";

        queue.changePriority(0, new Data(15));

        res = queue.extractMin();
        assert res.priority() == 4 : "extractMin failed";

        while (!queue.isEmpty()) {
            System.out.print(queue.extractMin().priority() + " ");
        }
    }
}
