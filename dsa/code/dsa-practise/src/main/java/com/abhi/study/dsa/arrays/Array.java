package com.abhi.study.dsa.arrays;

import java.util.*;

/**
 * @author abhishek
 * @version 1.0, 15/3/17
 */
public class Array {

    public static void sortByFrequency(int[] arr) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int elem : arr) {
            if (!map.containsKey(elem)) map.put(elem, 0);
            map.put(elem, map.get(elem) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue()) * -1;
            }
        });

        int i = 0;
        for (Map.Entry<Integer, Integer> entry : list) {
            int freq = entry.getValue();
            int val = entry.getKey();
            while (freq-- > 0) {
                arr[i++] = val;
            }
        }
    }

    public static boolean isMajority(int[] arr, int x) {
        int idx = firstIdxOfX(arr, 0, arr.length - 1, x);
        if ((idx + arr.length / 2) < arr.length && arr[idx + arr.length / 2] == x) return true;
        return false;
    }

    public static int firstIdxOfX(int[] arr, int l, int r, int x) {
        if (l <= r) {
            int m = (l + r) / 2;
            if ((m == 0 || arr[m - 1] < x) && arr[m] == x) return m;
            else if (x > arr[m]) return firstIdxOfX(arr, m + 1, r, x);
            else return firstIdxOfX(arr, l, m - 1, x);
        }
        return -1;
    }

    public static int equilibriumIdx(int[] arr){
        int sum = 0;
        int leftSum = 0;
        for(int item: arr){
            sum += item;
        }
        for(int i=0; i < arr.length; i++){
            sum = sum - arr[i];
            if(sum == leftSum) return arr[i];
            leftSum += arr[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-6, 1, 5, 2, -4, 3, 0};
        System.out.println(equilibriumIdx(arr));

        arr = new int[]{1, 1, 2, 2, 2, 2, 8};
        System.out.println(isMajority(arr, 8));

        arr = new int[]{2, 5, 2, 8, 5, 6, 8, 8};
        sortByFrequency(arr);
        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
    }
}
