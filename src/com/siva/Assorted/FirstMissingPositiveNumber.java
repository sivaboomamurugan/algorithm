package com.siva.Assorted;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class FirstMissingPositiveNumber {

    public static void main(String[] args) {
        int[] arr = {0,-1,3,1};
        FirstMissingPositiveNumber sol = new FirstMissingPositiveNumber();
        System.out.println(sol.firstMissingPositive(arr));

    }

    public int firstMissingPositive(int[] nums) {
        boolean isOnePresentInTheStream = processInputStreamAndReturnIfOneIsPresent(nums);
        if(!isOnePresentInTheStream)
            return 1;
        if(1 == nums.length)
            return 2;
        processPostiveNumbers(nums);
        return getMissingNumber(nums);

    }

    private boolean processInputStreamAndReturnIfOneIsPresent(int[] num) {
        final int size = num.length;
        final AtomicBoolean isOnePresent = new AtomicBoolean();
        IntStream.range(0, size)
                .forEach(i -> {
                    if(1 == num[i]) {
                        isOnePresent.set(true);
                    }
                    if(num[i] <= 0 || num[i] > size) {
                        num[i] = 1;
                    }
                });
        return isOnePresent.get();
    }

    private void processPostiveNumbers(int[] num) {
        final int size = num.length;
        IntStream.range(0, size)
                .forEach(i -> {
                    int absValue = Math.abs(num[i]);
                    absValue = absValue == size ? 0 : absValue;
                    num[absValue] = num[absValue] < 0 ? num[absValue] : -num[absValue];
                });
    }

    private int getMissingNumber(int[] num) {
        final int size = num.length;
        int missingNumber = IntStream.range(1, size)
                .filter(i -> num[i] > 0)
                .findFirst().orElse(num[0] > 0 ? size : size + 1);

        return missingNumber;
    }
}