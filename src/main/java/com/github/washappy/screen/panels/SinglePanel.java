package com.github.washappy.screen.panels;

import com.github.washappy.enums.Screens;
import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.recources.SinglePanelResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SinglePanel extends AbstractPanel{

    private final SinglePanelResources resources = new SinglePanelResources();

    private final JButton fourtyLineButton = new JButton(resources.fourtyLineButtonBasic);
    private final JButton oneMinuteButton = new JButton(resources.oneMinuteButtonBasic);
    private final JButton practiceButton = new JButton(resources.practiceButtonBasic);
    private final JButton backButton = new JButton(resources.backButtonBasic);

    public SinglePanel(){
        //리스너는 한번만 추가하자
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
                Navigator.INSTANCE.stackScreen(Screens.FOURTY_LINE);
            }
        });
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
                Navigator.INSTANCE.stackScreen(Screens.ONE_MINUTE);

            }
        });
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
                Navigator.INSTANCE.stackScreen(Screens.PRACTICE);

            }
        });
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
    }

    @Override
    public void init() {
        super.init();

        //40라인 버튼 생성
        fourtyLineButton.setBounds(500,250,200,200);
        setPaintSetting(fourtyLineButton);
        fourtyLineButton.setIcon(resources.fourtyLineButtonBasic);
        panel.add(fourtyLineButton);

        //1분 버튼 생성
        oneMinuteButton.setBounds(300,450,200,200);
        setPaintSetting(oneMinuteButton);
        oneMinuteButton.setIcon(resources.oneMinuteButtonBasic);
        panel.add(oneMinuteButton);

        //연습 버튼 생성
        practiceButton.setBounds(700,450,200,200);
        setPaintSetting(practiceButton);
        practiceButton.setIcon(resources.practiceButtonBasic);
        panel.add(practiceButton);

        //뒤로가기 버튼 생성
        backButton.setBounds(0,30,143,144);
        setPaintSetting(backButton);
        backButton.setIcon(resources.backButtonBasic);
        panel.add(backButton);
    }

    @Override
    public Screens getScreen() {
        return Screens.SINGLE;
    }
}
