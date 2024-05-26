package com.github.washappy.tetris.mino;

public class PlacedMino extends AbstactMino{

    public PlacedMino(Minos mino) {
        this.mino = mino;
        this.x = AbstactMino.START_X;
        this.y = AbstactMino.START_Y;
    }

    public PlacedMino(Minos mino,int x, int y) {
        this.mino = mino;
        this.x = x;
        this.y = y;
    }
}
