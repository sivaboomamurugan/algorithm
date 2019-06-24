package com.siva.Generic;

/**
 * @author by ${user}
 */
public class TurbulenceArray {

    public static void main(String[] args) {
        System.out.println(maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9}));
    }

    public static int maxTurbulenceSize(int[] A) {

        if(null == A || 0 == A.length)
            return 0;
        if(1 == A.length)
            return 1;

        int max=1;
        int currentCount=1;
        boolean isLess = false;

        if(A[0] < A[1])
            isLess = true;
        for(int index =1; index < A.length; index++) {
            boolean isSwapped = false;
            if((isLess && A[index-1] < A[index]) || (!isLess && A[index-1] > A[index]) ) {
                currentCount++;
                isSwapped = true;
            }
            if(currentCount > max) {
                max = currentCount;
            }


            if(!isSwapped) {
                currentCount = 2;
                if(A[index-1] < A[index])
                    isLess = true;
                else
                    isLess = false;
            }
            isLess = !isLess;
        }
        return max;
    }
}
