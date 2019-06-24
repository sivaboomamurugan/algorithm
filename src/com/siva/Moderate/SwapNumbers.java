package com.siva.Moderate;

/**
 * Created by sivam on 12/10/15.
 */
public class SwapNumbers {

    public static void main(String[] args) {
        swapNumbersViaMathematicalCalculation(4,6);
        swapNumbersViaBits(4,6);
    }

    private static void swapNumbersViaMathematicalCalculation(int a, int b) {
        System.out.println( "Value of a is : " + a + " and b is : " + b );
        a = a-b;
        b = a+b;
        a = b-a;
        System.out.println( "Value of a is : " + a + " and b is : " + b );
    }

    private static void swapNumbersViaBits(int a, int b) {
        System.out.println( "Value of a is : " + a + " and b is : " + b );
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println( "Value of a is : " + a + " and b is : " + b );
    }
}
