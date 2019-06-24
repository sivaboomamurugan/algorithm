package com.siva.TripleByte.TicTacToe;

public enum Player {

    HUMAN("X"),

    AI("O"),

    DEFAULT("-");

    Player(String val) {
        this.name = val;
    }

    String name;
}
