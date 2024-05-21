package com.github.washappy.tetris.mino;

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

    public NowMino getRotated(Rotates r) {
        NowMino n = new NowMino(mino,x,y);
        n.roation = n.rotate(r);
        return n;
    }

    public int[][] getCoodinates() {

        int[][] ret = new int[4][2];
        int k = 0;
        int p = 0;

        for (int[] i: mino.getRotation()[roation]) {
            for (int j : i) {
                if (j==1) {
                    ret[k] = new int[]{x+j, y+p};
                    k+=1;
                }
            }
            p+=1;
        }
        return ret;
    }
}
