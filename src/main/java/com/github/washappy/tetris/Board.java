package com.github.washappy.tetris;


import com.github.washappy.enums.DeathMessage;
import com.github.washappy.enums.Direction;
import com.github.washappy.enums.Rotates;
import com.github.washappy.enums.Screens;
import com.github.washappy.screen.Navigator;
import com.github.washappy.tetris.mino.*;

import java.util.ArrayList;
import java.util.Collections;

import static com.github.washappy.screen.panels.GameOverPanel.GAME_OVER_MESSAGE;

public class Board {

    public static int[] SOLO_BOARD_PLACE = new int[] {550,220};
    public static int[] SOLO_HOLD_PLACE = new int[] {430,250};
    public static int[] SOLO_HOLD_MINO_PLACE = new int[] {430,290};
    public static int[] SOLO_NEXT_PLACE = new int[] {0,0};
    public static int[] SOLO_NEXT_MINO_PLACE = new int[] {750,200};

    public static int[] SOLO_FIELD_MINO_PLACE = new int[] {550,600};

    private AbstactMino[][] field; //10,40
    private NowMino nowMino;


    public Board() {
        this.field = new AbstactMino[10][40];
    }

    public AbstactMino[][] getField() {
        return field;
    }

    public boolean isRotatePossible(Rotates rotates) {
        for (int[] i: nowMino.getRotated(rotates).getCoordinates()) {
            int x = i[0];
            int y = i[1];
            //System.out.println("x : "+x+" ,y : "+y);
            if ((x<0 || y<0) || (x>9 || y>39)) {
                return false;
            }
            if (field[x][y]!=null && !(field[x][y] instanceof NowMino)) {
                return false;
            }
        }
        //System.out.println("true");
        return true;
    }

    public boolean rotate(Rotates rotates) {
        if (isRotatePossible(rotates)) {
            try {
                nowDelete();
                nowMino.rotate(rotates);
                nowMinoSummon(nowMino.getMino(),nowMino.getRoation(),new int[]{nowMino.getX(), nowMino.getY()});
                //System.out.println(Arrays.deepToString(nowMino.getCoodinates()));
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println(exception.getMessage());
            }

            return true;
        }
        return false;
    }

    public void nowDelete() {
        for (int[] i : nowMino.getCoordinates()) {
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
        int k =0;
        for (int[] i :new NowMino(minos,AbstactMino.START_X,AbstactMino.START_Y).getCoordinates()) {
            int x = i[0];
            int y = i[1];

            if (field[x][y]!=null && !(field[x][y] instanceof NowMino)) {
                k = 1;
            }
        }
        NowMino now = new NowMino(minos,coodinate[0],coodinate[1],roation);

        for (int[] i : now.getCoordinates()) {
            //System.out.println(Arrays.toString(i));
            int x = i[0];
            int y = i[1];
            field[x][y] = new NowMino(minos,x,y);
        }

        if (k==1) {
            gameOver();
        }
    }

    public void placedMinoSummon(Minos minos, int roation, int[] coodinate) {

        NowMino now = new NowMino(minos,coodinate[0],coodinate[1],roation);
        for (int[] i : now.getCoordinates()) {
            //System.out.println(Arrays.toString(i));
            int x = i[0];
            int y = i[1];
            field[x][y] = new PlacedMino(minos,x,y);
        }
    }

    public boolean isMovePossible(Move move) {

        for (int[] i: nowMino.getMoved(move).getCoordinates()) {
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

    public boolean move(Move move) {
        if (isMovePossible(move)) {
            //System.out.println("yes");
            nowDelete();
            nowMino.move(move);
            nowMinoSummon(nowMino.getMino(),nowMino.getRoation(),new int[]{nowMino.getX(), nowMino.getY()});
            //System.out.println(getFieldString());
            return true;
        }
        //System.out.println("no");
        return false;
    }

    public int drop() {
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
        for (int[] mino : nowMino.getCoordinates()) {
            for (int j =0; j<10; j++) {
                if (field[j][mino[1]] == null) {
                    //System.out.println("y");
                    isFull = false;
                    break;
                } else {
                    //System.out.println("n");
                    isFull = true;
                }
            }
            //System.out.println(">>");
            if (isFull && !fullLine.contains(mino[1])) {
                //System.out.println("yes");
                fullLine.add(mino[1]);
            } else {
                //System.out.println("no");
            }
            isFull = true;
        }

        if (!fullLine.isEmpty()) {

            Collections.sort(fullLine);
            //System.out.println("fulline : "+fullLine.toString());
            Collections.reverse(fullLine);
            for (int line : fullLine) {

                for (int j = 0; j < 10; j++) {
                    field[j][line] = null;
                }
                AbstactMino[][] temp_field = new AbstactMino[10][40];

                for (int y=39; y>= line+1; y--) {
                    for (int x=0; x<10; x++) {
                        temp_field[x][y] = field[x][y];
                    }
                }

                for (int x=0; x<10; x++) field[x][39] = null;

                for (int y=38; y>=line; y--){
                    for (int x=0; x<10; x++) {
                        field[x][y] = temp_field[x][y+1];
                    }
                }

//                for (int y=39; y>=line; y--) {
//                    for (int x=0; x<10;x++) {
//                        field[x][y] = field[x][y+1];
//                        /*for (int a=0;a<field.length;a++) {
//                            AbstactMino[] c = field[a];
//                            for (int b=0; b<c.length;b++) {
//                                if (c[b]==null) {
//                                    System.out.print("0 ");
//                                } else {
//                                    System.out.print("▢ ");
//                                }
//                            }
//                            System.out.println();
//                        }
//                        //System.out.println("-----------");*/
//                    }
//                }
            }

            /*for (int j : fullLine) {
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
            }*/

            //System.out.println(getFieldString());

            return fullLine.size();
        }

        //System.out.println(getFieldString());

        return 0;
    }

    public NowMino getNowMino() {
        return nowMino;
    }

    public void setNowMino(NowMino nowMino) {
        this.nowMino = nowMino;
    }

    public void goNextMino() {
        //TODO(굳이?)
    }

    public String getFieldString() {
        StringBuilder result = new StringBuilder();
        for (AbstactMino[] a : field) {
            for (AbstactMino b: a) {
                if (b==null) {
                    result.append(0);
                } else if (b instanceof NowMino) {
                    result.append(2);
                } else if (b instanceof PlacedMino) {
                    result.append(1);
                } else {
                    result.append("ASdfasdfas6513fe6485w132dsf");
                }
                result.append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    public NowMino getBottom() {
        NowMino temp = new NowMino(nowMino.getMino(),nowMino.getX(),nowMino.getY(),nowMino.getRoation());

        Move down = new Move(Direction.DOWN,-1);

        boolean isPossible = true;

        while (isPossible) {
            for (int[] i : temp.getMoved(down).getCoordinates()) {
                int x = i[0];
                int y = i[1];
                if ((x < 0 || y < 0) || (x > 9 || y >= 39)) {
                    isPossible = false;
                    break;
                }
                if (field[x][y] != null && !(field[x][y] instanceof NowMino)) {
                    isPossible = false;
                    break;
                }
            }
            temp.move(down);
        }
        temp.move(new Move(Direction.DOWN,1));
        return temp;
    }

    public void gameOver() {
        System.out.println("died");
        //Navigator.INSTANCE.stackScreen();
        GAME_OVER_MESSAGE = DeathMessage.A.getRandom().getMessage();
        Navigator.INSTANCE.stackScreen(Screens.GAMEOVER);
    }
}


//TODO(감도, 중력해결, 넥스트 사진, pps보여주기, SRS+, 키 입력이슈,사망멘트 추가)
