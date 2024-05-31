package com.github.washappy.tetris;


import com.github.washappy.enums.Direction;
import com.github.washappy.enums.Rotates;
import com.github.washappy.tetris.mino.*;

import java.util.ArrayList;

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
            if ((x<=0 || y<0) || (x>=9 || y>=39)) {
                return false;
            }
            if (field[x][y]!=null && !(field[x][y] instanceof NowMino)) {
                return false;
            }
        }
        return true;
    }

    public void rotate(Rotates rotates) {
        if (isRotatePossible(rotates)) {
            try {
                nowDelete();
                nowMino.rotate(rotates);
                nowMinoSummon(nowMino.getMino(),nowMino.getRoation(),new int[]{nowMino.getX(), nowMino.getY()});
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void nowDelete() {
        for (int[] i : nowMino.getCoodinates()) {
            int x = i[0];
            int y = i[1];
            field[x][y] = null;
        }
    }

    public void nowMinoSummon(NowMino nowMino) {
        nowMinoSummon(nowMino.getMino(),0);
    }

    public void nowMinoSummon(Minos minos, int roation) {
        nowMinoSummon(minos,roation,new int[]{AbstactMino.START_X,AbstactMino.START_Y});
    }

    public void nowMinoSummon(Minos minos, int roation, int[] coodinate) {
        NowMino now = new NowMino(minos,coodinate[0],coodinate[1],roation);
        for (int[] i : now.getCoodinates()) {
            //System.out.println(Arrays.toString(i));
            int x = i[0];
            int y = i[1];
            field[x][y] = new NowMino(minos,x,y);
        }
    }

    public void placedMinoSummon(Minos minos, int roation, int[] coodinate) {
        NowMino now = new NowMino(minos,coodinate[0],coodinate[1],roation);
        for (int[] i : now.getCoodinates()) {
            //System.out.println(Arrays.toString(i));
            int x = i[0];
            int y = i[1];
            field[x][y] = new PlacedMino(minos,x,y);
        }
    }

    public boolean isMovePossible(Move move) {

        for (int[] i: nowMino.getMoved(move).getCoodinates()) {
            //System.out.println(Arrays.toString(i));
            int x = i[0];
            int y = i[1];
            if ((x<0 || y<0) || (x>9 || y>=39)) {
                return false;
            }
            if (field[x][y]!=null && !(field[x][y] instanceof NowMino)) {
                return false;
            }
        }
        return true;
    }

    public void move(Move move) {
        if (isMovePossible(move)) {
            //System.out.println("yes");
            nowDelete();
            nowMino.move(move);
            nowMinoSummon(nowMino.getMino(),nowMino.getRoation(),new int[]{nowMino.getX(), nowMino.getY()});
        } else {
            //System.out.println("no");
        }
    }

    public void drop() {
        //System.out.println("drop");
        int i = 0;
        while (isMovePossible(new Move(Direction.DOWN,i))) {
            i-=1;
        }
        i+=1;
        nowDelete();
        nowMino.move(new Move(Direction.DOWN,i));
        placedMinoSummon(nowMino.getMino(),nowMino.getRoation(),new int[]{nowMino.getX(), nowMino.getY()});


        boolean isFull = true;
        ArrayList<Integer> fullLine = new ArrayList<>();
        for (int[] mino : nowMino.getCoodinates()) {
            for (int j =0; j<10; j++) {
                if (field[j][mino[1]] == null) {
                    System.out.println("y");
                    isFull = false;
                    break;
                } else {
                    System.out.println("n");
                    isFull = true;
                }
            }
            System.out.println(">>");
            if (isFull && !fullLine.contains(mino[1])) {
                System.out.println("yes");
                fullLine.add(mino[1]);
            } else {
                System.out.println("no");
            }
            isFull = true;
        }
        System.out.println("------");

        if (!fullLine.isEmpty()) {
            for (int j : fullLine) {
                for (int x=0; x<10; x++) {
                    field[x][j]=null;
                }
            }
            for (int j : fullLine) {
                for (int x=0;x<10;x++) {
                    for (int y=j; y<39; y++) {
                        field[x][y] = field[x][y+1];
                    }
                }
            }
        }
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
