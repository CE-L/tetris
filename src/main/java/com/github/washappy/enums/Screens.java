package com.github.washappy.enums;

public enum Screens {
    INTRO(0),
    SINGLE(0),
    MULTI(0),
    SETTING(0),
    CREDIT(0),


    FOURTY_LINE(1),
    ONE_MINUTE(1),
    PRACTICE(1);

    public int inGame;

    Screens(int i) {
        this.inGame = i;
    }
}
