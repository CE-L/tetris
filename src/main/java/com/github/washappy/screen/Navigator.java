package com.github.washappy.screen;

import com.github.washappy.enums.Screens;

import java.util.Stack;

public class Navigator {

    private final Stack<Screens> navigationStack = new Stack<>();

    public void stackScreen(Screens screen){
        navigationStack.add(screen);
        //change
    }

    public Screens getCurrentScreen(){
        return navigationStack.peek();
    }



    Navigator(){
        navigationStack.add(Screens.INTRO);
    }

}
