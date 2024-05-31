package com.github.washappy.screen.panels;

import com.github.washappy.Music;

import javax.swing.*;

public interface AbstractPanel {

    JPanel panel = new JPanel();

    default JPanel getPanel(){
        return panel;
    }

    public void init();

    default void setPaintSetting(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
    }

    default void playButtonOn(){
        Music buttonEnteredMusic = new Music("buttonOn.mp3",false);
        buttonEnteredMusic.start();
    }

    default void playButtonClick(){
        Music buttonPressedMusic = new Music("buttonClick.mp3",false);
        buttonPressedMusic.start();
    }
}
