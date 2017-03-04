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

    }
}
