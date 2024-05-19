package com.github.washappy.tetris;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Minos {
    T(0,0x8a2be2),
    I(1,0x40e0d0),
    O(2,0xdaa520),
    L(3,0xff8c00),
    J(4,0x0000cd),
    S(5,0x32cd32),
    Z(6,0xdc143c);

    private final int number;
    private final int color;

    Minos(int number, int color) {
        this.number = number;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public Minos[] randomBag() {
        Minos[] ret = {T,I,O,L,J,S,Z};
        List<Minos> list = Arrays.asList(ret);
        Collections.shuffle(list);
        list.toArray(ret);
        return ret;
    }
}
