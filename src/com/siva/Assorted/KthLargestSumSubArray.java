package com.siva.Assorted;

// Java program to find the k-th
// argest sum of subarray
import java.util.*;

class KthLargestSumSubArray
{
    // function to calculate kth largest
    // element in contiguous subarray sum
    static int kthLargestSum(int arr[], int n, int k)
    {
        // array to store predix sums
        int sum[] = new int[n + 1];
        sum[0] = 0;
        sum[1] = arr[0];
        for (int i = 1; i <= n; i++)
            sum[i] = sum[i - 1] + arr[i - 1];

        // priority_queue of min heap
        PriorityQueue<Integer> Q = new PriorityQueue<Integer> ();

        for (int i = 1; i <= n; i++)
        {
            for (int j = i; j <= n; j++)

            {
                int x = sum[j] - sum[i - 1];

                if (Q.size() < k)
                    Q.add(x);

                else
                {
                    if (Q.peek() < x)
                    {
                        Q.poll();
                        Q.add(x);
                    }
                }
            }
        }

        return Q.poll();
    }

    // Driver Code
    public static void main(String[] args)
    {
        int a[] = new int[]{ 10, -10, 20, -40 };
        int n = a.length;
        int k = 6;

        // calls the function to find out the
        // k-th largest sum
        System.out.println("x"+kthLargestSum(a, n, k));
    }
}

//10,0,20,-20,-10,10,-30,20,-20,-40
//20,20,10,10,0,-10,-20,-20,-30,-40
