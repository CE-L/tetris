package com.github.washappy.screen.panels;

import com.github.washappy.enums.Screens;
import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.recources.SinglePanelResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.github.washappy.Ingredient.SCREEN_HEIGHT;
import static com.github.washappy.Ingredient.SCREEN_WIDTH;

public class SinglePanel extends AbstractPanel{

    private final SinglePanelResources resources = new SinglePanelResources();

    private final JButton fourtyLineButton = new JButton(resources.fourtyLineButtonBasic);
    private final JButton oneMinuteButton = new JButton(resources.oneMinuteButtonBasic);
    private final JButton practiceButton = new JButton(resources.practiceButtonBasic);
    private final JButton backButton = new JButton(resources.backButtonBasic);

    @Override
    public void init() {

        panel.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        panel.setBackground(new Color(0,0,0,0));
        panel.setLayout(null);

        //40라인 버튼 생성
        fourtyLineButton.setBounds(500,250,200,200);
        setPaintSetting(fourtyLineButton);
        fourtyLineButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                fourtyLineButton.setIcon(resources.fourtyLineButtonPressed);
                fourtyLineButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                fourtyLineButton.setIcon(resources.fourtyLineButtonBasic);
                fourtyLineButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                //whatScreen = Screens.FOURTY_LINE;
                //changeScreen(whatScreen);
            }
        });
        panel.add(fourtyLineButton);

        //1분 버튼 생성
        oneMinuteButton.setBounds(300,450,200,200);
        setPaintSetting(oneMinuteButton);
        oneMinuteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                oneMinuteButton.setIcon(resources.oneMinuteButtonPressed);
                oneMinuteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                oneMinuteButton.setIcon(resources.oneMinuteButtonBasic);
                oneMinuteButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                //whatScreen = Screens.ONE_MINUTE;
                //changeScreen(whatScreen);
            }
        });
        panel.add(oneMinuteButton);

        //연습 버튼 생성
        practiceButton.setBounds(700,450,200,200);
        setPaintSetting(practiceButton);
        practiceButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                practiceButton.setIcon(resources.practiceButtonPressed);
                practiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                practiceButton.setIcon(resources.practiceButtonBasic);
                practiceButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                //whatScreen = Screens.PRACTICE;
                //changeScreen(whatScreen);
            }
        });
        panel.add(practiceButton);

        //뒤로가기 버튼 생성
        backButton.setBounds(0,30,143,144);
        setPaintSetting(backButton);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(resources.backButtonPressed);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                playButtonOn();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(resources.backButtonBasic);
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                playButtonClick();
                Navigator.INSTANCE.popScreen();
            }
        });
        panel.add(backButton);
    }

    @Override
    public Screens getScreen() {
        return Screens.SINGLE;
    }
}
