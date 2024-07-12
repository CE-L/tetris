package com.github.washappy.screen;

import com.github.washappy.enums.Screens;
import com.github.washappy.screen.panels.*;

import javax.swing.*;
import java.util.Stack;

public class Navigator {
    public static final Navigator INSTANCE = new Navigator();

    private final Stack<AbstractPanel> navigationStack = new Stack<>();
    public JFrame frame;

    public void popScreen(){
        assert(!navigationStack.isEmpty());
        //remove
        frame.remove(navigationStack.pop().panel);

        //get panel shown before
        AbstractPanel showing = navigationStack.peek();
        setPanelSize(showing.panel);
        frame.add(showing.panel);
        showing.init();

        frame.revalidate();
        frame.repaint();
    }

    public void stackScreen(Screens screen) {
        if (!navigationStack.isEmpty()) frame.remove(navigationStack.peek().panel);

        //보여줄 패널 선택, 초기화
        AbstractPanel showing = getPanel(screen);
        setPanelSize(showing.panel);
        frame.add(showing.panel);
        showing.init();

        navigationStack.add(showing);
        frame.revalidate();
        frame.repaint();
    }

    private AbstractPanel getPanel(Screens screen) {
        return switch (screen) {
            case INTRO ->  new IntroPanel();
            case MULTI ->  new MultiPanel();
            case CREDIT -> new CreditPanel();
            case SINGLE ->  new SinglePanel();
            case SETTING ->  new SettingPanel();

            case PRACTICE -> new PracticePanel();
            case ONE_MINUTE -> new OneMinutePanel();
            case FOURTY_LINE -> new FourtyLinePanel();

            case GAMEOVER -> new GameOverPanel();
            case SUCCESS -> new SuccessPanel();

            case BOOK -> new BookPanel();
            case RANK -> new RankPanel();

            default -> null;
        };
    }

    public Screens getCurrentScreen() {
        if (!navigationStack.isEmpty()) {
            return navigationStack.peek().getScreen();
        } else {
            return Screens.INTRO;
        }
    }

    public AbstractPanel getCurrentPanel() {
        if (!navigationStack.isEmpty()) {
            return navigationStack.peek();
        } else {
            return new IntroPanel();
        }
    }

    public void setFrame(JFrame frame) {
        INSTANCE.frame = frame;
        stackScreen(Screens.INTRO);
    }

    private void setPanelSize(JPanel panel) {
        panel.setBounds(0, 0, 1200, 720);
        panel.setSize(1200, 720);
    }
}

