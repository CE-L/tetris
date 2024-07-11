package com.github.washappy.screen.panels;

import com.github.washappy.enums.Screens;
import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.Screen2;
import com.github.washappy.screen.recources.IntroPanelResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IntroPanel extends AbstractPanel{

    private final IntroPanelResources recources= new IntroPanelResources();

    private final JButton singleButton = new JButton(recources.singleButtonBasic);
    private final JButton multiButton = new JButton(recources.multiButtonBasic);
    private final JButton settingButton = new JButton(recources.settingButtonBasic);
    private final JButton creditButton = new JButton(recources.creditButtonBasic);


    public IntroPanel(){

        //리스너는 한번만 추가하자
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
                Navigator.INSTANCE.stackScreen(Screens.SINGLE);
            }
        });

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
                Navigator.INSTANCE.stackScreen(Screens.MULTI);
            }
        });

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
                Navigator.INSTANCE.stackScreen(Screens.SETTING);
            }
        });

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
                Navigator.INSTANCE.stackScreen(Screens.CREDIT);
            }
        });
    }

    @Override
    public void init(){
        super.init();

        //기본 배경음악 재생
        if(!Screen2.introMusic.isAlive()) {
            Screen2.introMusic.start();
        }

        //single 버튼 생성
        singleButton.setBounds(1050,300,200,200);
        setPaintSetting(singleButton);
        singleButton.setOpaque(false);
        singleButton.setIcon(recources.singleButtonBasic);
        panel.add(singleButton);

        //singleButton.removeMouseListener();

        //multi 버튼 생성
        multiButton.setBounds(825,400,200,200);
        setPaintSetting(multiButton);
        multiButton.setIcon(recources.multiButtonBasic);
        panel.add(multiButton);

        //setting 버튼 생성
        settingButton.setBounds(600,500,200,200);
        setPaintSetting(settingButton);
        settingButton.setIcon(recources.settingButtonBasic);
        panel.add(settingButton);

        //credit 버튼 생성
        creditButton.setBounds(1125,575,143,144);
        setPaintSetting(creditButton);
        creditButton.setIcon(recources.creditButtonBasic);
        panel.add(creditButton);

    }

    @Override
    public Screens getScreen() {
        return Screens.INTRO;
    }


    public void dispose(){

    }

}
