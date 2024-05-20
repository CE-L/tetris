package com.github.washappy.tetris;


import com.github.washappy.tetris.mino.AbstactMino;

public class Board {
    private AbstactMino[][] field; //40,20

    public Board() {
        this.field = new AbstactMino[40][20];
    }

    public AbstactMino[][] getField() {
        return field;
    }
}
