package com.siva.Moderate;

import java.util.*;

public class ManhatanDuplicates {
    public static void main(String args[] ) throws Exception {
        Scanner scan = new Scanner(System.in);
        //Read matrix length
        int matrixLength = Integer.parseInt(scan.nextLine());
        int[][] matrix = new int[matrixLength][matrixLength];

        //Read Matrix content
        for(int index=0; index < matrixLength && scan.hasNextLine(); index++) {
            String rowData = scan.nextLine();
            String[] data = rowData.split(" ");
            int columnIndex = 0;
            for(String datum : data) {
                matrix[index][columnIndex++] = Integer.parseInt(datum);
            }
        }

        //Read K value
        int kIndiceLength = scan.nextInt();
        if(checkDuplicatesWithinKIndices(matrix, kIndiceLength))
            System.out.println("YES");
        else {
            System.out.println("NO");
        }

    }

    private static boolean checkDuplicatesWithinKIndices(int[][] matrix, int kIndiceLength) {
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;
        kIndiceLength = Math.min(kIndiceLength, rowLength * columnLength);
        //Maps the integerElement and its absolute location
        Map<Integer, Set<Position>> dataHolder = new HashMap<>();

        for(int row = 0; row < rowLength; row++) {
            for(int column = 0; column < columnLength; column++) {
                if(dataHolder.containsKey(matrix[row][column])) {
                    Set<Position> locations = dataHolder.get(matrix[row][column]);
                    for(Position location : locations) {
                        int manhattanDistance = Math.abs(row-location.row) + Math.abs(column - location.col);
                        if(manhattanDistance <= kIndiceLength)
                            return true;
                        //If the distance between the current two matched elements is greater than k, we can remove it from the collection.
                        if(row - location.row > kIndiceLength)
                            locations.remove(location);
                    }
                    locations.add(new Position(row, column));
                } else {
                    Set<Position> locations = new HashSet<Position>();
                    locations.add(new Position(row, column));
                    dataHolder.put(matrix[row][column], locations);
                }
            }
        }
        return false;
    }
    static class Position {
        int row, col = 0;
        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}