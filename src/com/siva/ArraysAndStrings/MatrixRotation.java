package com.siva.ArraysAndStrings;

/**
 * Created by sivam on 1/17/16.
 */
public class MatrixRotation {

    public static void main(String[] args) {
        int[][] matrix = new int[3][3];
        int val = 1;
        for(int index =0; index < matrix.length; index++) {
            for(int innerIndex = 0; innerIndex < matrix[0].length; innerIndex++) {
                matrix[index][innerIndex] = val++;
            }
        }

        rotateMatrix(matrix);
        System.out.println("done");
    }

    private static void rotateMatrix(int[][] matrix) {
        int n = matrix.length;
        for(int layer = 0; layer < matrix.length/2; layer++) {
            int first = layer;
            int last = n-1-layer;
            for(int i =first; i < last; i++) {
                int offset = i-first;
                int top = matrix[first][i];
                matrix[first][i] = matrix[last-offset][first];
                matrix[last-offset][first] = matrix[last][last-offset];
                matrix[last][last-offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }
    }
}
