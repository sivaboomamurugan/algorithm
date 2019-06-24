package com.siva.Assorted;

public class LargestSumNonAdjacentInteger {

    public static void main(String[] args) {
        int [] arr = new int [] {5, 5, 10, 100, 10, 5};
        System.out.println(findMaxSum(arr));
        System.out.println(largestSum(arr));
        System.out.println(largestSumConstantSpace(arr));

        arr = new int [] {5};
        System.out.println(findMaxSum(arr));
        System.out.println(largestSum(arr));
        System.out.println(largestSumConstantSpace(arr));

        arr = new int [] {10, 6};
        System.out.println(findMaxSum(arr));
        System.out.println(largestSum(arr));
        System.out.println(largestSumConstantSpace(arr));
    }

    private static int largestSum(int[] arr) {

        int [] largestSumarr = new int[arr.length];
        largestSumarr[0]  = arr[0];
        if(arr.length > 1 && arr[1] < largestSumarr[0])
            largestSumarr[1] = largestSumarr[0];
        else if(arr.length > 1)
            largestSumarr[1] = arr[1];

        for(int index =2; index < arr.length; index++) {
            if(arr[index] + largestSumarr[index-2] > largestSumarr[index-1])
                largestSumarr[index] = arr[index] + largestSumarr[index-2];
            else
                largestSumarr[index] = largestSumarr[index-1];
        }

        return largestSumarr[largestSumarr.length-1];

    }

    private static int largestSumConstantSpace(int[] arr) {
        int prevLargestSum = 0;
        int currentLargestSum = arr[0];

        for(int index =1; index < arr.length; index++) {
            if(arr[index] + prevLargestSum > currentLargestSum) {
                int temp = currentLargestSum;
                currentLargestSum = arr[index] + prevLargestSum;
                prevLargestSum = temp;
            }
            else
                prevLargestSum = currentLargestSum;
        }

        return currentLargestSum;

    }

    private static int findMaxSum(int[] arr) {
        int excludedValue = 0;
        int includedValue = 0;

        //arr[0]
        includedValue = arr[0];

        for(int index =1; index < arr.length; index++) {

            int temp = includedValue > excludedValue ? includedValue : excludedValue;

            includedValue = excludedValue + arr[index];
            excludedValue = temp;
        }

        return includedValue > excludedValue ? includedValue : excludedValue;

    }

}
