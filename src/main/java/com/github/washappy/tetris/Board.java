package com.github.washappy.tetris;


import com.github.washappy.enums.Direction;
import com.github.washappy.enums.Rotates;
import com.github.washappy.tetris.mino.*;

import java.util.Arrays;

public class Board {
    private AbstactMino[][] field; //10,40
    private NowMino nowMino;

    public Board() {
        this.field = new AbstactMino[10][40];
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

    public void nowDelete() {
        for (int[] i : nowMino.getCoodinates()) {
            int x = i[0];
            int y = i[1];
            field[x][y] = null;
        }
    }

    public void minoSummon(NowMino nowMino) {
        minoSummon(nowMino.getMino(),0);
    }

    public void minoSummon(Minos minos, int roation) {
        minoSummon(minos,roation,new int[]{AbstactMino.START_X,AbstactMino.START_Y});
    }

    public void minoSummon(Minos minos, int roation, int[] coodinate) {
        NowMino now = new NowMino(minos,coodinate[0],coodinate[1],roation);
        System.out.println("----");
        for (int[] i : now.getCoodinates()) {
            System.out.println(Arrays.toString(i));
            int x = i[0];
            int y = i[1];
            field[x][y] = new NowMino(minos,x,y);
        }
    }

    public boolean isMovePossible(Move move) {

        for (int[] i: nowMino.getMoved(move).getCoodinates()) {
            int x = i[0];
            int y = i[1];
            if (field[x][y]!=null) {
                return false;
            }
        }
        return true;
    }

    public void move(Move move) {
        if (isMovePossible(move)) {
            nowMino.move(move);
            nowDelete();
            minoSummon(nowMino.getMino(),nowMino.getRoation(),new int[]{nowMino.getX(), nowMino.getY()});
        }
    }

    public void drop() {
        int i = 1;
        while (isMovePossible(new Move(Direction.DOWN,i))) {
            i+=1;
        }
        nowMino.move(new Move(Direction.DOWN,i));
        nowDelete();
        minoSummon(nowMino.getMino(),nowMino.getRoation(),new int[]{nowMino.getX(), nowMino.getY()});
    }

    public NowMino getNowMino() {
        return nowMino;
    }

    public void setNowMino(NowMino nowMino) {
        this.nowMino = nowMino;
    }

    public void goNextMino() {
        //TODO
    }
}
