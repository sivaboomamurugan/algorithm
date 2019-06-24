package com.siva.Generic;

/**
 * Created by sivam on 3/15/17.
 */
public class StringToLong {

    public static void main(String[] args) {
        System.out.println(stringToLong("123"));
        System.out.println(stringToLong("1023"));
        System.out.println(stringToLong("-1023"));
        System.out.println(stringToLong("-102039999"));
        System.out.println(stringToLong("0000"));
        System.out.println(stringToLong("-0000"));
        System.out.println(stringToLong("--0000"));
        System.out.println(stringToLong("siva"));
    }

    private static long stringToLong(String s) {
        char[] stringInChar = s.toCharArray();
        boolean isNegative = false;
        if(s.startsWith("-")) {
            isNegative = true;
            stringInChar[0] = '0';
        }
        Long result = new Long(0);
        int multiplier = 1;
        for(int index = stringInChar.length-1; index >= 0; index--, multiplier=multiplier*10) {
            int temp =  stringInChar[index] - 48;
            if(temp > 9 || temp < 0)
                return -1;
            result +=temp*multiplier;
        }
        if(isNegative)
            result *=-1;
        return result;
    }
}
