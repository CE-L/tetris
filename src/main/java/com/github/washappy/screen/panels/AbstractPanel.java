package com.github.washappy.screen.panels;

import com.github.washappy.Music;
import com.github.washappy.enums.Screens;

import javax.swing.*;

public abstract class AbstractPanel {

   public JPanel panel = new JPanel();

    public abstract void init();
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
}
