package com.github.washappy.tetris.mino;

import com.github.washappy.enums.Rotates;


public class NowMino extends AbstactMino{

    private int roation = 0;

    public int getRoation() {
        return roation;
    }

    public int rotate(Rotates roationType) {
        switch (roationType) {
            case CLOCKWISE -> {
                if (roation<3) {
                    roation+=1;
                } else {
                    roation = 0;
                }
            }
            case COUNTERCLOCKWISE -> {
                if (roation>0) {
                    roation-=1;
                } else {
                    roation = 3;
                }
            }
            case HUNDREDWEIGHT -> {
                if (roation<2) {
                    roation+=2;
                } else {
                    roation-=2;
                }
            }
        }
        return roation;
    }

    public NowMino(Minos mino) {
        this.mino = mino;
        this.x = AbstactMino.START_X;
        this.y = AbstactMino.START_Y;
    }

    public NowMino(Minos mino,int x, int y) {
        this.mino = mino;
        this.x = x;
        this.y = y;
    }

    public NowMino(Minos mino,int x, int y, int roation) {
        this.mino = mino;
        this.x = x;
        this.y = y;
        this.roation = roation;
    }

    public NowMino getMoved(Move move) {
        int[] m = move.getXY();
        //System.out.println(Arrays.toString(m));
        return new NowMino(mino,x+m[0],y+m[1],roation);
    }

    public void move(Move move) {
        int[] m = move.getXY();
        this.x += m[0];
        this.y += m[1];
    }

    public NowMino getRotated(Rotates r) {

        int a = this.roation;

        switch (r) {
            case CLOCKWISE -> {
                if (a < 3) {
                    a += 1;
                } else {
                    a = 0;
                }
            }
            case COUNTERCLOCKWISE -> {
                if (a > 0) {
                    a -= 1;
                } else {
                    a = 3;
                }
            }
            case HUNDREDWEIGHT -> {
                if (a < 2) {
                    a += 2;
                } else {
                    a -= 2;
                }
            }
        }

        return new NowMino(this.mino, this.x, this.y,a);
    }

    public int[][] getCoordinates() {

        int[][] ret = new int[4][2];
        int k = 0;

        int p = 0;
        for (int[] i: mino.getRotation()[roation]) { // i -> 이차원 배열
            int q = 0;
            for (int j : i) {
                if (j==1) {
                    ret[k] = new int[]{x+q, y+p};
                    k+=1;
                }
                q+=1;
            }
            p+=1;
        }
        return ret;
    }
}
