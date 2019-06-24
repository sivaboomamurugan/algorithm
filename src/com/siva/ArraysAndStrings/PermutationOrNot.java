package com.siva.ArraysAndStrings;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by sivam on 1/17/16.
 */
public class PermutationOrNot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String data = scanner.next();
        String data1 = scanner.next();
        System.out.println(permutationViaEquals(data, data1));
        System.out.println(permutationViaArray(data, data1));
    }

    private static boolean permutationViaEquals(String data, String data1) {
        if(data.length() != data1.length())
            return false;
        char[] arr = data.toCharArray();
        char[] arr1 = data1.toCharArray();
        Arrays.sort(arr);
        Arrays.sort(arr1);
        data = String.valueOf(arr);
        data1 = String.valueOf(arr1);
        return data.equals(data1);
    }

    private static boolean permutationViaArray(String data, String data1) {
        if(data.length() != data1.length())
            return false;
        int[] letters = new int[128];
        for(int index = 0; index < data.length(); index++) {
            letters[data.charAt(index)]++;
        }

        for(int index = 0; index < data1.length(); index++) {
            if(--letters[data1.charAt(index)] < 0)
                return false;
        }

        return true;
    }
}
