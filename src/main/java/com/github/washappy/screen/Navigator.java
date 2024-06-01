package com.github.washappy.screen;

import com.github.washappy.enums.Screens;
import com.github.washappy.screen.panels.AbstractPanel;
import com.github.washappy.screen.panels.IntroPanel;
import com.github.washappy.screen.panels.SinglePanel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

public class Navigator {

    private final Stack<AbstractPanel> navigationStack = new Stack<>();

    CardLayout cardLayout = new CardLayout();
    JPanel cards = new JPanel();
    private JFrame frame;

    IntroPanel introPanel = new IntroPanel();
    SinglePanel singlePanel = new SinglePanel();

    public static final Navigator INSTANCE = new Navigator();

    public void popScreen(){
        assert(!navigationStack.isEmpty());

        //remove
        frame.remove(navigationStack.pop().panel);
        //get panel before shown

        AbstractPanel showing = navigationStack.peek();
        setPanelSize(showing.panel);
        frame.add(showing.panel);
        showing.init();

        frame.revalidate();
        frame.repaint();

        System.out.println("remove");
        navigationStack.elements().asIterator().forEachRemaining((AbstractPanel e)->{
            System.out.print(e.getScreen());
            System.out.print(", ");
                }
        );
        System.out.println();



    }

    public void stackScreen(Screens screen) {

        System.out.println(SwingUtilities.isEventDispatchThread());

        if (!navigationStack.isEmpty()) {
            frame.remove(navigationStack.peek().panel);
        }

        //보여줄 패널 선택, 초기화
        AbstractPanel showing = getPanel(screen);
        setPanelSize(showing.panel);
        frame.add(showing.panel);
        showing.init();


        navigationStack.add(showing);
        frame.revalidate();
        frame.repaint();

        System.out.println("add");
        navigationStack.elements().asIterator().forEachRemaining((AbstractPanel e)->{
                    System.out.print(e.getScreen());
                    System.out.print(", ");
                }
        );
        System.out.println();
    }

    private AbstractPanel getPanel(Screens screen) {
        AbstractPanel showing = introPanel;

        switch (screen) {
            case INTRO -> {
                showing = introPanel;
            }
            case MULTI -> {

            }
            case CREDIT -> {

            }
            case SINGLE -> {
                showing = singlePanel;
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
        return showing;
    }

    public Screens getCurrentScreen() {
        if (!navigationStack.isEmpty()) {
            return navigationStack.peek().getScreen();
        } else {
            return Screens.INTRO;
        }
    }

    public void setFrame(JFrame frame) {
        INSTANCE.frame = frame;
     //   INSTANCE.frame.add(cards);
        stackScreen(Screens.INTRO);
    }

    public void initPanels(){
        cards.add(introPanel.panel, introPanel.getScreen().name());
        cards.add(singlePanel.panel, singlePanel.getScreen().name());
    }

    private void setPanelSize(JPanel panel) {
        panel.setBounds(0, 0, 1200, 720);
        panel.setSize(1200, 720);
    }
}

