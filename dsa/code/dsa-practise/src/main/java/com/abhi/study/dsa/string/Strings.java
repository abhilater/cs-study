package com.abhi.study.dsa.string;

/**
 * @author abhishek
 * @version 1.0, 23/3/17
 */
public class Strings {

    public static String replaceAll(String inputStr) {
        char[] input = inputStr.toCharArray();
        int i = 0, j = 0;
        while (i < input.length - 2) {
            if (input[i] == 'A' && i + 1 < input.length && input[i + 1] == 'B'
                    && i + 2 < input.length && input[i + 2] == 'C') {
                input[j++] = 'X';
                i = i + 3;
            } else input[j++] = input[i++];
        }
        while (i < input.length) {
            input[j++] = input[i++];
        }
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < j; i++) {
            sb.append(input[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(replaceAll("efABC"));
    }
}
