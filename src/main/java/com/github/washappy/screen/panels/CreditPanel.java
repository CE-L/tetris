package com.github.washappy.screen.panels;

import com.github.washappy.enums.Screens;
import com.github.washappy.screen.Navigator;
import com.github.washappy.screen.recources.CreditPanelResources;
import com.github.washappy.screen.recources.SettingPanelResources;
import com.github.washappy.tetris.Board;
import com.github.washappy.tetris.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreditPanel extends AbstractPanel{

    private final CreditPanelResources resources = new CreditPanelResources();
    private final JButton backButton = new JButton(resources.backButtonBasic);

    public static Game game = new Game();


    private Image hutaoImage = resources.hutao;

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
    public Graphics2D screenDraw(Graphics2D g) {
        game.screenDraw(g);

        g.setColor(Color.white);
        g.setFont(new Font("Ariel",Font.BOLD,50));

        g.drawString("게임엔진 없이 java만을 이용해 만든 테트리스",200,100);

        g.drawImage(hutaoImage, 250, 250, 300,300,null);

        g.setFont(new Font("Ariel",Font.BOLD,50));
        g.drawString("제작자",650,480);
        g.drawString("21414 신동헌",650,550);

        return g;
    }

    @Override
    public Screens getScreen() {
        return Screens.CREDIT;
    }
}