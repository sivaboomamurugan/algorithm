package com.siva.TripleByte.TicTacToe;

import java.io.Console;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Board {

    Cell[][] gameBoard = new Cell[3][3];

    public void printBoard() {
        for(int row = 0; row < gameBoard.length; row++) {
            StringBuilder rowContent = new StringBuilder();
            for(int col = 0; col < gameBoard[0].length; col++) {
                Cell cell = gameBoard[row][col];
                rowContent.append(null == cell ? Player.DEFAULT.name : cell.player.name)
                        .append("|");
            }
            rowContent.setLength(rowContent.length()-1);
            System.out.println(rowContent);
        }
    }

    public boolean isBoardFull() {
        for(int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[0].length; col++) {
                if(null == gameBoard[row][col])
                    return false;
            }
        }
        return true;
    }

    public void makeRandomMove(Player movedPlayer) {
        if(isBoardFull())
            throw new IllegalArgumentException("Board is full");
        for(int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[0].length; col++) {
                if(null == gameBoard[row][col]) {
                    gameBoard[row][col] = new Cell(movedPlayer);
                    return;
                }

            }
        }
    }

    public void addToken(int row, int col, Player movedPlayer) {
        if(null == gameBoard || row < 0 || row >= gameBoard.length || col < 0 || col >= gameBoard[0].length )
            throw new IllegalArgumentException("Game not initialized or not a valid move");

        gameBoard[row][col] = new Cell(movedPlayer);
    }

    public static void main(String[] args) {
        Board board = new Board();

        while(!board.isBoardFull()) {
            board.printBoard();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nter the row : ");
            int humanMoveRow =scanner.nextInt();
            System.out.print("Nter the col : ");
            int humanMoveCol = scanner.nextInt();
            board.addToken(humanMoveRow, humanMoveCol, Player.HUMAN);
            if(!board.isBoardFull())
                board.makeRandomMove(Player.AI);

        }

    }
}
