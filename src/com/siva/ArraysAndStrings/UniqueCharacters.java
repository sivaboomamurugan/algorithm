package com.siva.ArraysAndStrings;

import java.util.Scanner;

/**
 * Created by sivam on 1/17/16.
 */
public class UniqueCharacters {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String data = scanner.next();
        System.out.println(hasDupliactesViaChar(data));
        System.out.println(hasDupliactesViabit(data));
    }

    private static boolean hasDupliactesViabit(String data) {
        int checker = 0;
        for(int index = 0; index < data.length(); index++) {
            int val = data.charAt(index) - 'a';
            if((checker & (1 << val))>0)
                return false;
            checker |= (1 << val);
        }
        return true;
    }

    private static boolean hasDupliactesViaChar(String data) {
        boolean[] arr = new boolean[128];
        for(int index = 0; index < data.length(); index++) {
            if(arr[data.charAt(index)])
                return false;
            arr[data.charAt(index)] = true;
        }
        return true;

    }
}
