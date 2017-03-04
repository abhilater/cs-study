package com.abhi.study.dsa.heaps;

/**
 * @author abhishek
 * @version 1.0, 4/3/17
 */
public class BinaryMaxHeap {

    private int[] heap = null;
    private int maxSize = 0;
    private int size = 0;

    public BinaryMaxHeap(int maxSize) {
        heap = new int[maxSize + 1]; // index 0 is not used
        this.maxSize = maxSize;
    }

    public void insert(int data) {
        if (size == maxSize) throw new RuntimeException("Heap is maxed out");
        size++;
        heap[size] = data;
        siftUp(size);
    }

    public int extractMax() {
        int res = heap[1];
        heap[1] = heap[size];
        size--;
        siftDown(1);
        return res;
    }

    public int getMax() {
        return heap[1];
    }

    public void remove(int i) {
        heap[i] = Integer.MAX_VALUE;
        siftUp(i);
        extractMax();
    }

    public void changePriority(int i, int priority) {
        int oldP = heap[i];
        heap[i] = priority;
        if (priority > oldP) siftUp(i);
        else siftDown(i);
    }

    public void create(int[] arr) {
        for (int data : arr) {
            insert(data);
        }
    }

    public int size() {
        return size;
    }

    private void siftUp(int i) {
        while (i > 1 && heap[i] > heap[getParentIdx(i)]) {
            int temp = heap[getParentIdx(i)];
            heap[getParentIdx(i)] = heap[i];
            heap[i] = temp;
            i = getParentIdx(i);
        }
    }

    private void siftDown(int i) {
        int max = i;
        int left = getLeft(i);
        if (left <= size && heap[left] > heap[i]) max = left;
        int right = getRight(i);
        if (right <= size && heap[right] > heap[max]) max = right;

        if (i != max) {
            // swap
            int temp = heap[i];
            heap[i] = heap[max];
            heap[max] = temp;
            // recurse
            siftDown(max);
        }
    }

    private int getParentIdx(int childIdx) {
        return childIdx / 2;
    }

    private int getLeft(int idx) {
        return 2 * idx;
    }

    private int getRight(int idx) {
        return 2 * idx + 1;
    }

    public static void main(String[] args) {
        BinaryMaxHeap binaryMaxHeap = new BinaryMaxHeap(10);
        binaryMaxHeap.create(new int[]{4, 5, 8, 7, 1, 2, 10, 9, 11, 3});
        int res = binaryMaxHeap.size();
        assert res == 10 : "Failed create";

        res = binaryMaxHeap.getMax();
        assert res == 11 : "Failed getMax";

        res = binaryMaxHeap.extractMax();
        assert res == 11 : "Failed extractMax";
        res = binaryMaxHeap.getMax();
        assert res == 10 : "Failed extractMax";

        binaryMaxHeap.remove(3);
        res = binaryMaxHeap.size();
        assert res == 8 : "Failed remove";

        binaryMaxHeap.changePriority(2, 12);
        res = binaryMaxHeap.getMax();
        assert res == 12 : "Failed changePriority";

    }

}

