package com.github.washappy.screen;

import com.github.washappy.enums.Screens;
import com.github.washappy.screen.panels.IntroPanel;

import javax.swing.*;
import java.util.Stack;

public class Navigator {

    private final Stack<Screens> navigationStack = new Stack<>();
    private final JFrame frame;

    IntroPanel introPanel = new IntroPanel();

    public void stackScreen(Screens screen){
        navigationStack.add(screen);

        switch(screen){
            case INTRO -> {
                setPanelSize(introPanel.getPanel());
                frame.add(introPanel.getPanel());
                introPanel.init();
            }
            case MULTI -> {

            }
            case CREDIT -> {

            }
            case SINGLE -> {

            }
            case SETTING -> {

            }
            case PRACTICE -> {

            }
            case ONE_MINUTE -> {

            }
            case FOURTY_LINE -> {

            }

        }

        frame.revalidate();
        frame.repaint();
        //change

    }

    public Screens getCurrentScreen(){
        return navigationStack.peek();
    }

    public Navigator(JFrame frame){
        this.frame = frame;
        stackScreen(Screens.INTRO);
    }

    private void setPanelSize(JPanel panel){
        panel.setBounds(0, 0, 1200, 720);
        panel.setSize(1200, 720);
    }
}
