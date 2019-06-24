package com.siva.Moderate;

/**
 * Created by sivam on 12/10/15.
 */
public class TicTacToe {

    public static void main(String[] args) {

        Piece x = new Piece('x');
        Piece y = new Piece('y');

        Piece[][] board = new Piece[4][4];
        board[0][0] = x;
        board[1][1] = x;
        board[2][2] = x;
        board[3][3] = x;

        System.out.println("Player " + hasWon(board).identifier + " has won");

        Piece[][] board1 = new Piece[4][4];
        board1[0][0] = y;
        board1[1][0] = y;
        board1[2][0] = y;
        board1[3][0] = y;

        System.out.println("Player " + hasWon(board1).identifier + " has won");

        Piece[][] board2 = new Piece[4][4];
        board2[1][0] = x;
        board2[1][1] = x;
        board2[1][2] = x;
        board2[1][3] = x;

        System.out.println("Player " + hasWon(board2).identifier + " has won");

        Piece[][] board3 = new Piece[4][4];
        board3[0][3] = y;
        board3[1][2] = y;
        board3[2][1] = y;
        board3[3][0] = y;

        System.out.println("Player " + hasWon(board3).identifier + " has won");

    }

    private static Piece hasWon(Piece[][] board) {
        if (null == board)
            return null;

        int rows = board.length;
        int columns = board[0].length;
        int row = 0;
        int column = 0;

        //Check Rows
        for (row = 0; row < rows; row++) {
            if (board[row][0] != null) {
                for (column = 1; column < columns; column++) {
                    if (board[row][column] != board[row][column - 1])
                        break;
                }

                if (column == columns)
                    return board[row][0];

            }
        }

        //Check columns
        for (column = 0; column < columns; column++) {
            if (board[0][column] != null) {
                for (row = 1; row < rows; row++) {
                    if (board[row][column] != board[row - 1][column])
                        break;
                }

                if (row == rows)
                    return board[0][column];
            }
        }

        //Check diagonal
        if (board[0][0] != null) {
            for (column = 1; column < columns; column++) {

                if (board[column][column] != board[column - 1][column - 1])
                    break;
            }
            if (column == columns)
                return board[0][0];
        }

        //Check bottom diagonal
        if (board[rows - 1][0] != null) {
            for (row = 1; rows < columns; row++) {

                if (board[rows - row - 1][row] != board[rows - row][row - 1])
                    break;
            }
            if (column == columns)
                return board[columns - 1][0];

        }
        return null;

    }

    static class Piece {
        char identifier;

        public Piece(char y) {
            this.identifier = y;
        }

        @Override
        public boolean equals(Object obj) {

            return this.identifier == ((Piece) obj).identifier;
        }
    }
}
