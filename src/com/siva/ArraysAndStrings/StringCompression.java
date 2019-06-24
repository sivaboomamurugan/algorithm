package com.siva.ArraysAndStrings;

import java.util.Scanner;

/**
 * Created by sivam on 1/17/16.
 */
public class StringCompression {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String data = scanner.next();
        System.out.println(compressString(data));
    }

    private static String compressString(String data) {
        int count = countCompression(data);
        if (count >= data.length())
            return data;
        char last = data.charAt(0);
        count = 1;
        StringBuffer content = new StringBuffer();
        for (int index = 1; index < data.length(); index++) {
            if (last == data.charAt(index))
                count++;
            else {
                content.append(last)
                        .append(count);
                last = data.charAt(index);
                count = 1;
            }
        }
        content.append(last)
                .append(count);
        return content.toString();
    }

    private static int countCompression(String data) {
        if(null == data || data.length() == 0)
            return 0;
        int size = 0;
        char last = data.charAt(0);
        int count = 1;
        for(int index = 1; index < data.length(); index++) {
            if (last == data.charAt(index))
                count++;
            else {
                last = data.charAt(index);
                size += 1 + String.valueOf(count).length();
                count = 1;
            }
        }
        size += 1 + String.valueOf(count).length();
        return size;
    }
}
