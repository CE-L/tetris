package com.github.washappy;

import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.Screen2;

public class Main {
    public static void main(String[] args) {
        Screen2 screen = new Screen2();
        Navigator.INSTANCE.setFrame(screen);
        //new Screen();
    }
}