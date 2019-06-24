package com.siva.Moderate;

import java.util.Arrays;


public class MaxSum {
    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{1, 2, 3, -4}));
    }

    public static int maxSubArray(final int[] A) {
        class Pair {
            public Integer currentSum;
            public Integer maxSum;

            public Pair(Integer currentSum, Integer maxSum) {
                this.currentSum = currentSum;
                this.maxSum = maxSum;
            }
        }

//        Integer maxSum = Integer.MIN_VALUE;
//        Integer currentSum = 0;
//        Arrays.stream(A).forEach((total,e) -> findMaxSum(total, e));

        /*
        // Our operator is not associative (I think)
        return Arrays.stream(A) // IntStream
                .mapToObj(Integer::valueOf) // Stream<Integer>
                .reduce(
                        new Pair(0, Integer.MIN_VALUE),
                        (p, a) -> {
                            final int newCurrentSum = p.getCurrentSum() + a;
                            final int newMaxSum = p.getMaxSum() < newCurrentSum ? newCurrentSum : p.getMaxSum();
                            final int newCurrentSum2 = newCurrentSum < 0 ? 0 : newCurrentSum;
                            return new Pair(newCurrentSum2, newMaxSum);
                        },
                        null).getMaxSum();
                        */

        final Pair p = new Pair(0, Integer.MIN_VALUE);

        Arrays.stream(A).forEach(a -> {
            p.currentSum += a;
            if(p.maxSum < p.currentSum) {
                p.maxSum = p.currentSum;
            }
            if(p.currentSum < 0)
                p.currentSum=0;
        });

        //Arrays.stream(A).forEach((p,a) -> findMaxSum(p,a)).maxSum();

        return p.maxSum;
/*
        for(int index =0; index < A.length; index++) {
            currentSum += A[index];
            if(maxSum < currentSum) {
                maxSum = currentSum;
            }
            if(currentSum < 0)
                currentSum=0;
        }
        return maxSum;
        */
    }

    private static int findMaxSum(int total, int x) {
        return total += x;
    }
}

