package com.github.washappy.screen.panels;

import com.github.washappy.enums.Screens;
import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.recources.CreditPanelResources;
import com.github.washappy.screen.recources.SettingPanelResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreditPanel extends AbstractPanel{

    private final CreditPanelResources resources = new CreditPanelResources();
    private final JButton backButton = new JButton(resources.backButtonBasic);

    public CreditPanel(){
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

        //뒤로가기 버튼 생성
        backButton.setBounds(0,30,143,144);
        setPaintSetting(backButton);
        backButton.setIcon(resources.backButtonBasic);
        panel.add(backButton);
    }

    @Override
    public Screens getScreen() {
        return Screens.CREDIT;
    }
}