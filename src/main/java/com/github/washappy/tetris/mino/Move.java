package com.github.washappy.tetris.mino;

import com.github.washappy.enums.Direction;

public class Move {
    private int amount;
    private Direction direction;

    public int getAmount() {
        return amount;
    }

    public Direction getDirection() {
        return direction;
    }

    public int[] getXY() {
        switch (direction) {
            case LEFT -> {
                return new int[]{-amount,0};
            }

            case RIGHT -> {
                return new int[]{amount,0};
            }

            case DOWN -> {
                return new int[]{0,amount};
            }
        }
        return null;
    }

    public Move(Direction direction, int amount) {
        this.direction = direction;
        this.amount = amount;
    }

    public Move(Direction direction) {
        this.direction = direction;
        this.amount = 1;
    }
}
