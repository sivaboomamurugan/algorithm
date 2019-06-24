package com.siva.flexe;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {
        int[] arr = {0,-1,3,1};
        Solution sol = new Solution();

        System.out.println(sol.solution(1, ""));


    }

    final static Map<Character, Integer> SEAT_MAPPER = Stream.of(
            new AbstractMap.SimpleEntry<>('A',0),
            new AbstractMap.SimpleEntry<>('B',1),
            new AbstractMap.SimpleEntry<>('C',2),
            new AbstractMap.SimpleEntry<>('D',3),
            new AbstractMap.SimpleEntry<>('E',4),
            new AbstractMap.SimpleEntry<>('F',5),
            new AbstractMap.SimpleEntry<>('G',6),
            new AbstractMap.SimpleEntry<>('H',7),
            new AbstractMap.SimpleEntry<>('J',8),
            new AbstractMap.SimpleEntry<>('K',9))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    final static String RESERVE_SEAT_SEPARATOR = " ";

    final static Integer RESERVED_SEAT = 1;

    public int solution(int N, String S) {
        // write your code in Java SE 8
        Set<Seat> reserveSeat = vailidatInputAndParseReserveSeat(N, S);
        int[][] seats = getSeats(N);
        fillReserveSeats(seats, reserveSeat);
        return getMaxThreeFamilyCanBeSeated(seats);
    }

    private int[][] getSeats(int numberOfRows) {
        int[][] seats = new int[numberOfRows][10];
        return seats;
    }

    private Set<Seat> vailidatInputAndParseReserveSeat(int numberOfRows, String reservedSeats) {
        if(0 >= numberOfRows || 51 <= numberOfRows)
            throw new IllegalArgumentException("Number of rows should be between 1 and 50");
        return parseReserveSeat(reservedSeats);
    }

    private Seat getSeat(String reserveSeat) {
        String rowAsString = reserveSeat.substring(0, reserveSeat.length()-1);
        Character seatCol = reserveSeat.charAt(reserveSeat.length()-1);

        int rowNumber = getMappedRowNumber(rowAsString);
        int colNumber = SEAT_MAPPER.get(seatCol);

        return new Seat(rowNumber,colNumber);
    }

    private static int getMappedRowNumber(String rowAsString) {
        AtomicInteger row = new AtomicInteger(0);
        IntStream.range(0, rowAsString.length())
                .forEach(e -> {
                    row.set(row.get()*10 + Character.getNumericValue(rowAsString.charAt(e)));
                });
        return row.get()-1;
    }

    private int getMaxThreeFamilyCanBeSeated(int[][] seats) {
        int maxThreeFamiliesCanBeSeated = 0;

        for(int rowIndex = 0; rowIndex < seats.length; rowIndex++) {
            //First block
            maxThreeFamiliesCanBeSeated = isAnySeatOccupied(seats, rowIndex, 0, 2) ?
                    maxThreeFamiliesCanBeSeated : maxThreeFamiliesCanBeSeated + 1;

            //Second block
            maxThreeFamiliesCanBeSeated = isAnySeatOccupied(seats, rowIndex, 3, 5) ?
                    isAnySeatOccupied(seats, rowIndex, 4, 6) ?
                            maxThreeFamiliesCanBeSeated : maxThreeFamiliesCanBeSeated + 1
                    : maxThreeFamiliesCanBeSeated + 1;

            //Third block
            maxThreeFamiliesCanBeSeated = isAnySeatOccupied(seats, rowIndex, 7, 9) ?
                    maxThreeFamiliesCanBeSeated : maxThreeFamiliesCanBeSeated + 1;
        }

        return maxThreeFamiliesCanBeSeated;
    }

    private boolean isAnySeatOccupied(int[][] seats, int row, int startCol, int endCol) {
        for(int colIndex = startCol; colIndex <= endCol; colIndex++) {
            if(RESERVED_SEAT == seats[row][colIndex])
                return true;
        }
        return false;
    }

    private Set<Seat> parseReserveSeat(String reservedSeats) {
        if("".equalsIgnoreCase(reservedSeats))
            return new HashSet<>();
        return Arrays.stream(reservedSeats.split(RESERVE_SEAT_SEPARATOR))
                .map(this::getSeat)
                .collect(Collectors.toSet());
    }

    private void fillReserveSeats(int[][] seats, Set<Seat> reservedSeat) {
        reservedSeat.stream().forEach(e -> {
            seats[e.rowNumber][e.colNumber] = RESERVED_SEAT;
        });
    }

    class Seat {
        int rowNumber;
        int colNumber;

        public Seat(int rowNumber, int colNumber) {
            this.rowNumber = rowNumber;
            this.colNumber = colNumber;
        }
    }
}
