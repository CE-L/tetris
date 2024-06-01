package com.github.washappy.screen.panels;

import com.github.washappy.Music;
import com.github.washappy.enums.Screens;

import javax.swing.*;
import java.awt.*;

import static com.github.washappy.Ingredient.SCREEN_HEIGHT;
import static com.github.washappy.Ingredient.SCREEN_WIDTH;

public abstract class AbstractPanel {

   public JPanel panel = new JPanel();

    public void init(){
        panel.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        panel.setBackground(new Color(0,0,0,0));
        panel.setLayout(null);
    }
    public abstract Screens getScreen();

    protected void setPaintSetting(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
    }

    protected void playButtonOn(){
        Music buttonEnteredMusic = new Music("buttonOn.mp3",false);
        buttonEnteredMusic.start();
    }

    protected void playButtonClick(){
        Music buttonPressedMusic = new Music("buttonClick.mp3",false);
        buttonPressedMusic.start();
    }

    public Graphics2D screenDraw(Graphics2D g){
        return g;
    }
}
