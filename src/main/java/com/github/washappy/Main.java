package com.github.washappy;

import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.Screen;
import com.github.washappy.screen.Screen22;

public class Main {
    public static void main(String[] args) {
        Screen22 screen = new Screen22();
        Navigator.INSTANCE.setFrame(screen);
        //new Screen();
    }
}