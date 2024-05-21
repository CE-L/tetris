package com.github.washappy.tetris;


import com.github.washappy.tetris.mino.*;

public class Board {
    private AbstactMino[][] field; //20,40
    private NowMino nowMino;

    public Board() {
        this.field = new AbstactMino[20][40];
    }

    public AbstactMino[][] getField() {
        return field;
    }

    public boolean isRotatePossible(Rotates rotates) {
        for (int[] i: nowMino.getRotated(rotates).getCoodinates()) {
            int x = i[0];
            int y = i[1];
            if (field[x][y]!=null) {
                return false;
            }
        }
        return true;
    }

    public void rotate(Rotates rotates) {
        if (isRotatePossible(rotates)) {
            nowMino.rotate(rotates);
            nowDelete();
            minoSummon(nowMino.getMino(),nowMino.getRoation(),new int[]{nowMino.getX(), nowMino.getY()});
        }
    }

    private void nowDelete() {
        for (int[] i : nowMino.getCoodinates()) {
            int x = i[0];
            int y = i[1];
            field[x][y] = null;
        }
    }

    private void minoSummon(Minos minos, int roation) {
        minoSummon(minos,roation,new int[]{AbstactMino.START_X,AbstactMino.START_Y});
    }

    private void minoSummon(Minos minos, int roation, int[] coodinate) {
        NowMino now = new NowMino(minos,coodinate[0],coodinate[1],roation);
        for (int[] i : now.getCoodinates()) {
            int x = i[0];
            int y = i[1];
            field[x][y] = new NowMino(minos,x,y);
        }
    }

    public boolean isMovePossible(Move move) {
        //TODO
        return true;
    }

    public void move(Move move) {
        if (isMovePossible(move)) {
            //TODO
        }
    }

    public void drop() {
        //TODO
    }

    public void hold() {
        //TODO
    }

    public void goNextMino() {
        //TODO
    }
}
