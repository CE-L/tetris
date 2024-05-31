package com.github.washappy;

import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.Screen22;

public class Main {
    public static void main(String[] args) {
        Navigator.INSTANCE.setFrame(new Screen22());
    }
}