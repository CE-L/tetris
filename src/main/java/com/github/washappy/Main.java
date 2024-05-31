package com.github.washappy;

import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.Screen;
import com.github.washappy.screen.Screen22;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Navigator navigator = new Navigator(new Screen22());
    }
}