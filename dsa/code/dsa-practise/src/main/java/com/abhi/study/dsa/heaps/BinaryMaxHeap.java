package com.abhi.study.dsa.heaps;

import java.util.Arrays;

/**
 * @author abhishek
 * @version 1.0, 4/3/17
 */
public class BinaryMaxHeap {

    private int[] heap = null;
    private int maxSize = 0;
    private int index = -1;

    public BinaryMaxHeap(int maxSize) {
        heap = new int[maxSize];
        this.maxSize = maxSize;
    }

    public void insert(int data) {
        if (index == maxSize - 1) throw new RuntimeException("Heap is maxed out");
        index++;
        heap[index] = data;
        siftUp(index);
    }

    public int extractMax() {
        if (index == -1) throw new RuntimeException("Heap empty");
        int res = heap[0];
        heap[0] = heap[index];
        index--;
        siftDown(0);
        return res;
    }

    public int max() {
        if (index == -1) throw new RuntimeException("Heap empty");
        return heap[0];
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

    private void siftUp(int i) {
        while (i > 0 && heap[i] > heap[getParentIdx(i)]) {
            int temp = heap[getParentIdx(i)];
            heap[getParentIdx(i)] = heap[i];
            heap[i] = temp;
            i = getParentIdx(i);
        }
    }

    private void siftDown(int i) {
        int max = i; // find max
        int left = getLeft(i);
        if (left <= index && heap[left] > heap[i]) max = left;
        int right = getRight(i);
        if (right <= index && heap[right] > heap[max]) max = right;

        if (i != max) {
            // swap i and max
            int temp = heap[i];
            heap[i] = heap[max];
            heap[max] = temp;
            // recurse from max
            siftDown(max);
        }
    }

    public void build(int[] arr) {
        for (int data : arr) {
            insert(data);
        }
    }

    public int size() {
        return index + 1;
    }

    public boolean isEmpty() {
        return index == -1;
    }

    public int[] getMemoryView() {
        return heap;
    }

    public void buildHeap(int n) {
        for (int i = n / 2; i >= 0; i--) {
            siftDown(i);
        }
    }

    public void sort(int[] arr) {
        heap = arr;
        index = arr.length - 1;
        buildHeap(index);
        while (index > 0) {
            int temp = heap[0];
            heap[0] = heap[index];
            heap[index] = temp;
            index--;
            siftDown(0);
        }
    }

    private int getParentIdx(int childIdx) {
        return (childIdx - 1) / 2;
    }

    private int getLeft(int idx) {
        return 2 * idx + 1;
    }

    private int getRight(int idx) {
        return 2 * idx + 2;
    }

    public static void main(String[] args) {
        BinaryMaxHeap binaryMaxHeap = new BinaryMaxHeap(10);
        binaryMaxHeap.build(new int[]{4, 5, 8, 7, 1, 2, 10, 9, 11, 3});
        Arrays.stream(binaryMaxHeap.getMemoryView()).forEach(n -> System.out.print(n + " "));
        System.out.println();
        int res = binaryMaxHeap.size();
        assert res == 10 : "Failed build";

        res = binaryMaxHeap.max();
        assert res == 11 : "Failed max";

        res = binaryMaxHeap.extractMax();
        assert res == 11 : "Failed extractMax";
        res = binaryMaxHeap.max();
        assert res == 10 : "Failed extractMax";

        binaryMaxHeap.remove(5);
        res = binaryMaxHeap.size();
        assert res == 8 : "Failed remove";

        binaryMaxHeap.changePriority(2, 12);
        res = binaryMaxHeap.max();
        assert res == 12 : "Failed changePriority";

        // print mem view
        Arrays.stream(binaryMaxHeap.getMemoryView()).forEach(n -> System.out.print(n + " "));
        System.out.println();
        while (!binaryMaxHeap.isEmpty()) {
            System.out.print(binaryMaxHeap.extractMax() + " ");
        }
        System.out.println();
        // test heap sort
        binaryMaxHeap.sort(new int[]{4, 5, 1, 7, 8, 6, 2, 9});
        Arrays.stream(binaryMaxHeap.getMemoryView()).forEach(n -> System.out.print(n + " "));
    }

}

