package com.github.washappy.screen.panels;

import com.github.washappy.Music;
import com.github.washappy.enums.Screens;
import com.github.washappy.screen.IntroScreenRecources;
import com.github.washappy.tetris.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static com.github.washappy.Ingredient.SCREEN_HEIGHT;
import static com.github.washappy.Ingredient.SCREEN_WIDTH;

public class HomePanel {

    private final IntroScreenRecources recources= new IntroScreenRecources();

    private JButton singleButton = new JButton(recources.singleButtonBasic);
    private JButton multiButton = new JButton(recources.multiButtonBasic);
    private JButton settingButton = new JButton(recources.settingButtonBasic);
    private JButton creditButton = new JButton(recources.creditButtonBasic);
    private JButton backButton = new JButton(recources.backButtonBasic);

    private Music introMusic = new Music("introMusic.mp3",true);


    private JPanel panel = new JPanel();

    public JPanel getPanel(){
        return panel;
    }



    public void init(){

        panel.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
//        setVisible(true);
        panel.setBackground(new Color(0,0,0,0));
        panel.setLayout(null);

        //기본 배경음악 재생
        introMusic.start();

        //single 버튼 생성

        singleButton.setBounds(1050,300,200,200);
        setPaintSetting(singleButton);
        singleButton.setOpaque(false);
        singleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                singleButton.setIcon(recources.singleButtonPressed);
                singleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                singleButton.setIcon(recources.singleButtonBasic);
                singleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                //whatScreen = Screens.SINGLE;
                //changeScreen(whatScreen);
            }
        });
        panel.add(singleButton);

        //multi 버튼 생성
        multiButton.setBounds(825,400,200,200);
        setPaintSetting(multiButton);

        multiButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                multiButton.setIcon(recources.multiButtonPressed);
                multiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                multiButton.setIcon(recources.multiButtonBasic);
                multiButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                //whatScreen = Screens.MULTI;
                //changeScreen(whatScreen);
            }
        });
        panel.add(multiButton);

        //setting 버튼 생성
        settingButton.setBounds(600,500,200,200);
        setPaintSetting(settingButton);
        settingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                settingButton.setIcon(recources.settingButtonPressed);
                settingButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                settingButton.setIcon(recources.settingButtonBasic);
                settingButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                //whatScreen = Screens.SETTING;
                //changeScreen(whatScreen);
            }
        });
        panel.add(settingButton);

        //credit 버튼 생성
        creditButton.setBounds(1125,575,143,144);
        setPaintSetting(creditButton);
        creditButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                creditButton.setIcon(recources.creditButtonPressed);
                creditButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                creditButton.setIcon(recources.creditButtonBasic);
                creditButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                //whatScreen = Screens.CREDIT;
                //changeScreen(whatScreen);
            }
        });
        panel.add(creditButton);

        //뒤로가기 버튼 생성
        backButton.setVisible(false);
        backButton.setBounds(0,30,143,144);
        setPaintSetting(backButton);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(recources.backButtonPressed);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(recources.backButtonBasic);
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                //whatScreen = Screens.INTRO;
                //changeScreen(whatScreen);
            }
        });
        panel.add(backButton);
        panel.repaint();
    }



    private void setPaintSetting(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
    }

    private void playButtonOn(){
        Music buttonEnteredMusic = new Music("buttonOn.mp3",false);
        buttonEnteredMusic.start();
    }

    private void playButtonClick(){
        Music buttonPressedMusic = new Music("buttonClick.mp3",false);
        buttonPressedMusic.start();
    }



}
