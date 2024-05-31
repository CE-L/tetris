package com.github.washappy.tetris.mino;

public abstract class AbstactMino {
    protected Minos mino;
    protected int rotated;
    protected int x;
    protected int y;

    public static int START_X = 3;
    public static int START_Y = 23;

    public static int SOLO_X = 400;
    public static int SOLO_Y = 350;


    public Minos getMino() {
        return mino;
    }

    public int getRotated() {
        return rotated;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
