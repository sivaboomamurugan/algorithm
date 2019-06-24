package com.siva.ArraysAndStrings;

import java.util.Scanner;

/**
 * Created by sivam on 1/17/16.
 */
public class StringReverse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String data = scanner.next();
        reverse(data);
    }

    private static void reverse(String data) {
        int start = 0;
        int end = data.length()-1;
        char[] arr = data.toCharArray();

        while(start < end) {
            char temp = data.charAt(start);
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        System.out.println(arr);
    }
}
