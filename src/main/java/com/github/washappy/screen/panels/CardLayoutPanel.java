package com.github.washappy.screen.panels;

import javax.swing.*;
import java.awt.*;

import static com.github.washappy.Ingredient.SCREEN_HEIGHT;
import static com.github.washappy.Ingredient.SCREEN_WIDTH;

public class CardLayoutPanel {

    public CardLayout cardLayout = new CardLayout();
    public JPanel panel = new JPanel(cardLayout);
    public  IntroPanel introPanel = new IntroPanel();
    public  SinglePanel singlePanel = new SinglePanel();

    public void init(){

        //panel.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        panel.setBackground(new Color(0,0,0,0));
        panel.setLayout(cardLayout);

        System.out.println("card init");

        System.out.println(SwingUtilities.isEventDispatchThread());

        SwingUtilities.invokeLater(()->{
            panel.setBounds(0, 0, 1280, 720);

            panel.add(introPanel.panel, "intro");
            panel.add(singlePanel.panel, "single");
            cardLayout.show(panel, "intro");

            introPanel.init();
            introPanel.panel.setVisible(false);
            singlePanel.init();
            singlePanel.panel.setVisible(false);
            panel.revalidate();
            panel.repaint();
        });

    }
}
