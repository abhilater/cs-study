package com.abhi.study.dsa.sorting;

import java.util.Arrays;

/**
 * @author abhishek
 * @version 1.0, 4/3/17
 */
public class Sorting {

    public void selectionSort(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N - 1; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int t = arr[i];
            arr[i] = arr[min];
            arr[min] = t;
        }
    }

    public void bubbleSort(int[] arr) {
        int N = arr.length;
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j + 1] < arr[j]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
    }

    public void insertionSort(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int t = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = t;
                }
            }
        }
    }

    public void mergeSort(int[] arr) {
        mergeSortUtil(arr, 0, arr.length - 1);
    }

    void mergeSortUtil(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSortUtil(arr, l, m);
            mergeSortUtil(arr, m + 1, r);
            sortedMerge(arr, l, m, r);
        }
    }

    void sortedMerge(int[] arr, int l, int m, int r) {
        int A = m - l + 1, B = r - m;
        int[] a = new int[A];
        int[] b = new int[B];
        for (int i = 0; i < A; i++) a[i] = arr[l + i];
        for (int i = 0; i < B; i++) b[i] = arr[m + 1 + i];

        int i = 0, j = 0, k = l;
        while (i < A && j < B) {
            if (a[i] <= b[j]) {
                arr[k] = a[i];
                i++;
                k++;
            } else {
                arr[k] = b[j];
                j++;
                k++;
            }
        }
        while (i < A) arr[k++] = a[i++];
        while (j < B) arr[k++] = b[j++];
    }

    public void quickSort(int[] arr) {
        partition(arr, 0, arr.length - 1);
    }

    void partition(int[] arr, int start, int end) {
        if (start < end) {
            int p = arr[start];
            int l = start + 1, r = end;
            while (l <= r) {
                while (l <= end && arr[l] <= p) l++;
                while (r >= 0 && arr[r] > p) r--;
                if (l < r) {
                    int t = arr[l];
                    arr[l] = arr[r];
                    arr[r] = t;
                }
            }
            arr[start] = arr[r];
            arr[r] = p;
            partition(arr, start, r - 1);
            partition(arr, r + 1, end);
        }
    }

    public static void main(String args[]) {
        Sorting sorting = new Sorting();
        //int[] arr = new int[]{4, 6, 3, 1, 7, 5};
        //int[] arr = new int[]{1, 1, 1, 2, 2, 1};
        int[] arr = new int[]{4, 4, 4, 3, 2, 4, 1};
        sorting.selectionSort(arr);
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
        System.out.println();

        //arr = new int[]{4, 6, 3, 1, 7, 5};
        //arr = new int[]{1, 1, 1, 2, 2, 1};
        arr = new int[]{4, 4, 4, 3, 2, 4, 1};
        sorting.bubbleSort(arr);
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
        System.out.println();

        //arr = new int[]{4, 6, 3, 1, 7, 5};
        //arr = new int[]{1, 1, 1, 2, 2, 1};
        arr = new int[]{4, 4, 4, 3, 2, 4, 1};
        sorting.insertionSort(arr);
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
        System.out.println();

        //arr = new int[]{4, 6, 3, 1, 7, 5};
        //arr = new int[]{1, 1, 1, 2, 2, 1};
        arr = new int[]{4, 4, 4, 3, 2, 4, 1};
        sorting.mergeSort(arr);
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
        System.out.println();

        //arr = new int[]{4, 6, 3, 1, 7, 5};
        //arr = new int[]{1, 1, 1, 2, 2, 1};
        //arr = new int[]{4, 4, 4, 3, 2, 4, 1};
        arr = new int[]{5, 8, 1, 3, 7, 9, 2};
        //arr = new int[]{1, 3, 9, 8, 2, 7, 5};
        sorting.quickSort(arr);
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
        System.out.println();

    }
}
