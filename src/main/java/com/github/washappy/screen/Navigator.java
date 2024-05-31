package com.github.washappy.screen;

import com.github.washappy.enums.Screens;
import com.github.washappy.screen.panels.AbstractPanel;
import com.github.washappy.screen.panels.IntroPanel;
import com.github.washappy.screen.panels.SinglePanel;

import javax.swing.*;
import java.util.Stack;

public class Navigator {

    private final Stack<AbstractPanel> navigationStack = new Stack<>();
    private JFrame frame;

    IntroPanel introPanel = new IntroPanel();

    public static final Navigator INSTANCE = new Navigator();

    public void stackScreen(Screens screen){
        if(!navigationStack.isEmpty()) {
            frame.getContentPane().remove(navigationStack.peek().getPanel());
            frame.removeAll();
        }

        frame.revalidate();
        frame.repaint();

        AbstractPanel showing = introPanel;

        switch(screen){
            case INTRO -> {
                showing = introPanel;
            }
            case MULTI -> {

            }
            case CREDIT -> {

            }
            case SINGLE -> {
                showing = new SinglePanel();
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
        navigationStack.add(showing);
        setPanelSize(showing.getPanel());
        frame.add(showing.getPanel());
        showing.init();

        frame.revalidate();
        frame.repaint();
        //change

    }

    public AbstractPanel getCurrentScreen(){
        return navigationStack.peek();
    }

    public void setFrame(JFrame frame){
        INSTANCE.frame = frame;
        stackScreen(Screens.INTRO);
    }

    private void setPanelSize(JPanel panel){
        panel.setBounds(0, 0, 1200, 720);
        panel.setSize(1200, 720);
    }
}
